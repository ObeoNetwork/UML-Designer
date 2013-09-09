package org.obeonetwork.dsl.uml2.profile.design.services;

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
 * @author Mohamed-Lamine BOUKHANOUFA
 */
public class GenericUMLProfileTools {
	/**
	 * Constructor of this class.
	 */
	public GenericUMLProfileTools() {
	}

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
		String label = "";

		if (object == null) {
			return "undefined";
		}

		if (object instanceof ValueSpecification) {
			label = getLabel((ValueSpecification) object);

		} else if (object instanceof Element) {
			final Element cE = (Element) object;
			String cName = null;
			String suffix = "";
			String cComLabel = "";

			NamedElement cNE = null;
			if (object instanceof NamedElement) {
				cNE = (NamedElement) object;

			} else if (object instanceof PackageImport) {
				final PackageImport cPI = (PackageImport) object;
				suffix = " (PackageImport)";
				cNE = cPI.getImportedPackage();

			} else if (object instanceof ElementImport) {
				final ElementImport cEI = (ElementImport) object;
				suffix = " (ElementImport)";
				cNE = cEI.getImportedElement();

			} else if (object instanceof ProfileApplication) {
				final ProfileApplication cPA = (ProfileApplication) object;
				suffix = " (ProfileApplication)";
				cNE = cPA.getAppliedProfile();

			} else if (object instanceof Comment) {
				final Comment cCom = (Comment) object;
				suffix = " (Comment)";
				final String cComBody = cCom.getBody();
				if (cComBody.length() >= 10)
					cComLabel = cComBody.substring(0, 9) + "...";
				else
					cComLabel = cComBody;
			}

			if (shortLabel) {
				if (object instanceof Comment)
					cName = cComLabel;
				else
					cName = cNE.getName();
			} else {
				if (object instanceof Comment)
					cName = cComLabel + suffix;
				else
					cName = cNE.getQualifiedName() + suffix;
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
		String label = "";

		if (value instanceof LiteralBoolean) {
			label = "<LiteralBoolean> ";
		} else if (value instanceof LiteralInteger) {
			label = "<LiteralInteger> ";
		} else if (value instanceof LiteralString) {
			label = "<LiteralString> ";
		} else if (value instanceof LiteralUnlimitedNatural) {
			label = "<LiteralUnlimitedNatural> ";
		} else {
			label = "<ValueSpecification> ";
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

		if ((type instanceof org.eclipse.uml2.uml.Class) && (type.getAppliedStereotypes() != null)
				&& (type.getAppliedStereotypes().size() > 0)) {

			final Stereotype firstStereotype = type.getAppliedStereotypes()
					.get(0);

			if (firstStereotype.getName().equals("Metaclass")) {
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
			we.printStackTrace();
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
			we.printStackTrace();
			System.exit(1);
		}

		return package_;
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
				ioe.printStackTrace();
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
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
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
}
