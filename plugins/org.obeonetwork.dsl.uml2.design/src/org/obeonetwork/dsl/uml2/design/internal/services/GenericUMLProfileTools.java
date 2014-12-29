/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * A Generic UML Profile tools.
 *
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class GenericUMLProfileTools {
	/**
	 * Return a usable string label for passed object.
	 *
	 * @param object
	 *            subject.
	 * @param shortLabel
	 *            a label
	 * @return the label
	 */
	public static String getLabel(final Object object, final boolean shortLabel) {
		String label = ""; //$NON-NLS-1$

		if (object == null) {
			return "undefined"; //$NON-NLS-1$
		}

		if (object instanceof ValueSpecification) {
			label = getLabel((ValueSpecification) object);

		} else if (object instanceof Element) {
			final Element cE = (Element) object;
			String cName = null;
			String suffix = ""; //$NON-NLS-1$
			String cComLabel = ""; //$NON-NLS-1$

			NamedElement cNE = null;
			if (object instanceof NamedElement) {
				cNE = (NamedElement) object;

			} else if (object instanceof PackageImport) {
				final PackageImport cPI = (PackageImport) object;
				suffix = " (PackageImport)"; //$NON-NLS-1$
				cNE = cPI.getImportedPackage();

			} else if (object instanceof ElementImport) {
				final ElementImport cEI = (ElementImport) object;
				suffix = " (ElementImport)"; //$NON-NLS-1$
				cNE = cEI.getImportedElement();

			} else if (object instanceof ProfileApplication) {
				final ProfileApplication cPA = (ProfileApplication) object;
				suffix = " (ProfileApplication)"; //$NON-NLS-1$
				cNE = cPA.getAppliedProfile();

			} else if (object instanceof Comment) {
				final Comment cCom = (Comment) object;
				suffix = " (Comment)"; //$NON-NLS-1$
				final String cComBody = cCom.getBody();
				if (cComBody.length() >= 10) {
					cComLabel = cComBody.substring(0, 9) + "..."; //$NON-NLS-1$
				} else {
					cComLabel = cComBody;
				}
			}

			if (shortLabel) {
				if (object instanceof Comment) {
					cName = cComLabel;
				} else {
					if (cNE != null) {
						cName = cNE.getName();
					}
				}
			} else {
				if (object instanceof Comment) {
					cName = cComLabel + suffix;
				} else {
					if (cNE != null) {
						cName = cNE.getQualifiedName() + suffix;
					}
				}
			}

			if (cName != null) {
				label = cName;
			} else {
				label = cE.toString();
			}
		}

		return label;
	}

	/**
	 * Return the label for a value specification.
	 *
	 * @param value
	 *            object
	 * @return the label
	 */
	public static String getLabel(final ValueSpecification value) {
		String label = ""; //$NON-NLS-1$

		if (value instanceof LiteralBoolean) {
			label = "<LiteralBoolean> "; //$NON-NLS-1$
		} else if (value instanceof LiteralInteger) {
			label = "<LiteralInteger> "; //$NON-NLS-1$
		} else if (value instanceof LiteralString) {
			label = "<LiteralString> "; //$NON-NLS-1$
		} else if (value instanceof LiteralUnlimitedNatural) {
			label = "<LiteralUnlimitedNatural> "; //$NON-NLS-1$
		} else {
			label = "<ValueSpecification> "; //$NON-NLS-1$
		}

		// Add the value
		label = label + value.stringValue();

		return label;
	}

	/**
	 * Check if a type is a meta class.
	 *
	 * @param type
	 *            to check
	 * @return true if type is meta class, else false
	 */
	public static boolean isMetaclass(final Type type) {
		boolean isMetaclass = false;

		if (type instanceof org.eclipse.uml2.uml.Class && type.getAppliedStereotypes() != null
				&& type.getAppliedStereotypes().size() > 0) {

			final Stereotype firstStereotype = type.getAppliedStereotypes()
					.get(0);

			if (firstStereotype.getName().equals("Metaclass")) { //$NON-NLS-1$
				isMetaclass = true;
			}
		}
		return isMetaclass;
	}

	/**
	 * Load a model from file.
	 *
	 * @param uri
	 *            of the file
	 * @return UML Package
	 */
	public static org.eclipse.uml2.uml.Package load(final URI uri) {
		org.eclipse.uml2.uml.Package package_ = null;
		final ResourceSet resourceSet = new ResourceSetImpl();

		try {
			final Resource resource = resourceSet.getResource(uri, true);

			package_ = (org.eclipse.uml2.uml.Package) EcoreUtil
					.getObjectByType(resource.getContents(),
							UMLPackage.Literals.PACKAGE);
		} catch (final WrappedException we) {
			LogServices.INSTANCE.error("load(" + uri.getClass() + ") not handled", //$NON-NLS-1$ //$NON-NLS-2$
					we);
			System.exit(1);
		}

		return package_;
	}

	/**
	 * Load a model from file.
	 *
	 * @param uri
	 *            the file
	 * @param type
	 *            (an EElement)
	 * @return EPackage
	 */
	public static EPackage load(final URI uri, final EClassifier type) {
		EPackage package_ = null;
		final ResourceSet resourceSet = new ResourceSetImpl();

		try {
			final Resource resource = resourceSet.getResource(uri, true);

			package_ = (EPackage) EcoreUtil.getObjectByType(
					resource.getContents(), type);
		} catch (final WrappedException we) {
			LogServices.INSTANCE.error(
					"load(" + uri.getClass() + "," + type.getClass() //$NON-NLS-1$ //$NON-NLS-2$
					+ ") not handled", we); //$NON-NLS-1$
			System.exit(1);
		}

		return package_;
	}

	/**
	 * resolve resource to ifile.
	 *
	 * @param resource
	 *            to resolve
	 * @return ifile
	 */
	public static IFile resourceToIFile(final Resource resource) {
		final URI uriEcoreProfile = resource.getURI();
		final URI resolvedUriEcoreProfile = CommonPlugin
				.resolve(uriEcoreProfile);
		final IFile ecoreProfileIFile = ResourcesPlugin
				.getWorkspace()
				.getRoot()
				.getFileForLocation(new Path(resolvedUriEcoreProfile.toFileString()));
		return ecoreProfileIFile;
	}

	/**
	 * Save a model to file.
	 *
	 * @param package_
	 */
	public static void save(org.eclipse.uml2.uml.Package package_) {
		Resource resource = null;
		if (package_.eResource() != null) {
			resource = package_.eResource();
			try {
				resource.save(null);
			} catch (final IOException ioe) {
				LogServices.INSTANCE.error("save(" + package_.getClass() //$NON-NLS-1$
						+ ") not handled", ioe); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Save a model to file of the a given uri.
	 *
	 * @param package_
	 * @param uri
	 *            of the file
	 */
	public static void save(final org.eclipse.uml2.uml.Package package_,
			final URI uri) {
		final Resource resource = new ResourceSetImpl().createResource(uri);
		resource.getContents().add(package_);

		try {
			resource.save(null);
		} catch (final IOException ioe) {
			LogServices.INSTANCE.error(
					"save(" + package_.getClass() + "," + uri.getClass() //$NON-NLS-1$ //$NON-NLS-2$
					+ ") not handled", ioe); //$NON-NLS-1$
		}
	}

	/**
	 * Constructor of this class.
	 */
	public GenericUMLProfileTools() {
	}
}
