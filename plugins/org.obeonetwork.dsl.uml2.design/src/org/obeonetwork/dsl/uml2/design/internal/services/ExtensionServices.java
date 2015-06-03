/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.services;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Services to handle typed Extension concerns.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class ExtensionServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final ExtensionServices INSTANCE = new ExtensionServices();

	private static final String BASE = "base_"; //$NON-NLS-1$

	/**
	 * Hidden constructor.
	 */
	private ExtensionServices() {

	}

	/**
	 * Test if is it possible to create an extension from stereotype source to the stereotype extension.
	 *
	 * @param source
	 *            stereotype.
	 * @param target
	 *            stereotype.
	 * @return true if the extension between source and target is possible.
	 */
	public boolean canCreateExtension(final EObject source, final EObject target) {
		if (source instanceof Stereotype) {
			final Stereotype stereotypeSource = (Stereotype)source;
			if (target instanceof ElementImport) {
				final ElementImport elementImport = (ElementImport)target;
				final NamedElement metaClass = elementImport.getImportedElement();

				// this code find all extension including inherited.
				final NamedElement baseProperty = stereotypeSource.getMember(BASE + metaClass.getName());
				if (baseProperty == null) {
					return true;
				} else if (baseProperty instanceof Property) {
					if (((Property)baseProperty).getType().equals(metaClass)) {
						return true;
					}
				}

				// this code find local extension.
				// if (stereotypeSource.getAttribute(base +
				// metaClass.getName(), (Type)metaClass) == null
				// || stereotypeSource.getAttribute(base +
				// metaClass.getName(), (Type)metaClass)
				// .getAssociation() == null) {
				// return true;
				// }
			}
			if (target instanceof Stereotype && !source.equals(target)) {

				final Stereotype stereotypeTarget = (Stereotype)target;
				if (stereotypeSource.getGeneralization(stereotypeTarget) == null
						&& stereotypeTarget.getGeneralization(stereotypeSource) == null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Test if is it possible to reconnect an uml Extension.
	 *
	 * @param element
	 *            is the extension to reconnect.
	 * @param target
	 *            of the reconnection.
	 * @return true if the reconnection is possible, else false.
	 */
	public boolean canReconnectExtension(final EObject element, final EObject target) {
		if (element instanceof Extension) {
			final Extension extension = (Extension)element;
			if (target instanceof ElementImport) {
				final Class newPointedMetaClass = (Class)((ElementImport)target).getImportedElement();
				final NamedElement baseProperty = extension.getStereotype().getMember(
						BASE + newPointedMetaClass.getName());
				if (baseProperty == null) {
					return true;
				} else if (baseProperty instanceof Property) {
					if (!((Property)baseProperty).getType().equals(newPointedMetaClass)) {
						return true;
					}
				}
			}
			if (target instanceof Stereotype) {
				final NamedElement baseProperty = ((Stereotype)target).getMember(BASE
						+ extension.getMetaclass().getName());
				if (baseProperty == null) {
					return true;
				} else if (baseProperty instanceof Property) {
					if (!((Property)baseProperty).getType().equals(extension.getMetaclass())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Create an UML Extension between the stereotype source and the target element.
	 *
	 * @param stereotype
	 *            source.
	 * @param targetElement
	 *            the target.
	 */
	public void createExtension(final Stereotype stereotype, final Element targetElement) {
		if (targetElement instanceof ElementImport) {
			createMetaclassExtension(stereotype, (ElementImport)targetElement);
		} else if (targetElement instanceof Stereotype) {
			createGeneralization(stereotype, (Stereotype)targetElement);
		}
	}

	/**
	 * Create an UML Generalization between the stereotypes source and target.
	 *
	 * @param stereotypeSource
	 *            of the generalization.
	 * @param stereotypeTarget
	 *            of the generalization.
	 */
	public void createGeneralization(final Stereotype stereotypeSource, final Stereotype stereotypeTarget) {
		if (!isGeneraleFor(stereotypeTarget, stereotypeSource)) {
			final Generalization generalization = UMLFactory.eINSTANCE.createGeneralization();
			generalization.setGeneral(stereotypeTarget);
			generalization.setSpecific(stereotypeSource);
			stereotypeSource.getGeneralizations().add(generalization);
		}
	}

	/**
	 * Create an extension from stereotype to the imported element.
	 *
	 * @param stereotype
	 *            of the extension.
	 * @param elementImport
	 *            of the extension.
	 * @return new extension.
	 */
	public Extension createMetaclassExtension(final Stereotype stereotype, final ElementImport elementImport) {
		Extension extension = null;
		final PackageableElement importedElement = elementImport.getImportedElement();
		if (importedElement != null && importedElement instanceof Type
				&& !isExtendedBy((Type)importedElement, stereotype)) {

			final Type metaclass = (Type)importedElement;
			final Property baseMetaclass = UMLFactory.eINSTANCE.createProperty();
			baseMetaclass.setName(BASE + metaclass.getName());
			baseMetaclass.setType(metaclass);
			stereotype.getOwnedAttributes().add(baseMetaclass);

			final ExtensionEnd extensionEnd = UMLFactory.eINSTANCE.createExtensionEnd();
			extensionEnd.setName("extension_" + stereotype.getName()); //$NON-NLS-1$
			extensionEnd.setType(stereotype);

			extension = UMLFactory.eINSTANCE.createExtension();
			extension.getOwnedEnds().add(extensionEnd);
			// No needs to set the name as it is provided by the item provider
			extension.getMemberEnds().add(baseMetaclass);

			stereotype.getProfile().getPackagedElements().add(extension);
		}

		return extension;
	}

	/**
	 * Return the extension of an element import.
	 *
	 * @param extension
	 *            source of element import
	 * @return The found element import.
	 */
	public ElementImport getElementImport(final Extension extension) {
		final Profile profileOwner = getProfileOwner(extension);
		if (profileOwner.getMetaclassReference(extension.getMetaclass()) != null) {
			return profileOwner.getMetaclassReference(extension.getMetaclass());
		}

		return null;
	}

	/**
	 * Find the Extension of stereotype to metaclass.
	 *
	 * @param stereotype
	 *            of the extension.
	 * @param metaclass
	 *            of the extension.
	 * @return the found extension, or null.
	 */
	public Extension getExtension(final Stereotype stereotype, final Type metaclass) {
		for (final Extension extention : stereotype.getProfile().getOwnedExtensions(false)) {
			if (extention.getName() != null) {
				if (extention.getName().equals(stereotype.getName() + "Extend" + metaclass.getName())) { //$NON-NLS-1$
					return extention;
				}
			}
		}

		return null;
	}

	/**
	 * Get the profile owner of this element.
	 *
	 * @param umlElement
	 *            the element
	 * @return the profile
	 */
	public Profile getProfileOwner(final Element umlElement) {

		if (umlElement.getOwner() instanceof Profile) {
			return (Profile)umlElement.getOwner();
		}
		return getProfileOwner(umlElement.getOwner());
	}

	/**
	 * Test if the metaclass is extended by the stereotype.
	 *
	 * @param metaclassType
	 *            of extension
	 * @param stereotype
	 *            of extension.
	 * @return true if the metaclass is extended by the stereotype.
	 */
	public boolean isExtendedBy(final Type metaclassType, final Stereotype stereotype) {
		return !(stereotype.getOwnedAttribute(BASE + metaclassType.getName(), metaclassType) == null);
	}

	/**
	 * Test if the stereotype target is a generalization of the stereotype source.
	 *
	 * @param stereotypeTarget
	 *            of the generalization.
	 * @param stereotypeSource
	 *            of the generalization.
	 * @return true if the stereotype target is a generalization of the stereotype source.
	 */
	private boolean isGeneraleFor(final Stereotype stereotypeTarget, final Stereotype stereotypeSource) {
		for (final Generalization generalization : stereotypeSource.getGeneralizations()) {
			if (generalization.getGeneral().equals(stereotypeTarget)) {
				return true;
			}
		}
		return false;

	}
}
