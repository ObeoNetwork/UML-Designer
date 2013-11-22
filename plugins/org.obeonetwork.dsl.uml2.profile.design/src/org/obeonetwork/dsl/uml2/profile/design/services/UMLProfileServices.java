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
package org.obeonetwork.dsl.uml2.profile.design.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
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
import org.obeonetwork.dsl.uml2.profile.design.dialogs.ExtraAssociationSelectionDialog;
import org.obeonetwork.dsl.uml2.profile.design.dialogs.ProfileVersionDialog;

/**
 * Services for UML Profile.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class UMLProfileServices {

	String yes = "Yes";

	String no = "No";

	String undefineProfile = "Undefine the Profile";

	String base = "base_";

	/**
	 * Dialog message to define a profile.
	 * 
	 * @param rootProfile
	 *            to define
	 * @param allContentProfile
	 *            to define
	 */
	public void defineProfileDialog(final Profile rootProfile,
			final List<Profile> allContentProfile) {
		boolean result = false;

		// String[] buttonYesNo = {yes, no};
		final String[] buttonYes = { yes };
		final Shell activeShell = PlatformUI.getWorkbench().getDisplay()
				.getActiveShell();
		// MessageDialog msgDialogYesNo = null;
		MessageDialog msgDialogYes = null;
		//
		// msgDialogYesNo = new MessageDialog(activeShell, "Define the Profile",
		// null,
		// "Would you like to define this profile in order to apply it ?",
		// MessageDialog.QUESTION,
		// buttonYesNo, 1);
		// int diagResult = msgDialogYesNo.open();
		//
		// if (diagResult == IDialogConstants.OK_ID) {
		result = defineProfile(rootProfile, allContentProfile);

		if (result) {
			msgDialogYes = new MessageDialog(activeShell, "Define the Profile",
					null, "The profile is defined", MessageDialog.INFORMATION,
					buttonYes, 0);
			msgDialogYes.open();
		}
		// else {
		// msgDialogYes = new MessageDialog(activeShell, "Define the Profile",
		// null,
		// "The profile isn't defined", MessageDialog.WARNING, buttonYes, 0);
		// msgDialogYes.open();
		// }
		// }
	}

	/**
	 * Define the profile and compute the version.
	 * 
	 * @param rootProfile
	 *            to define.
	 * @param allContentProfile
	 *            to define.
	 * @return true if defined, else false.
	 */
	public static boolean defineProfile(final Profile rootProfile,
			final List<Profile> allContentProfile) {
		boolean result = false;

		final ProfileVersionDialog versionDialog = new ProfileVersionDialog(
				PlatformUI.getWorkbench().getDisplay().getActiveShell(),
				rootProfile);
		versionDialog.open();

		if (versionDialog.getReturnCode() == IDialogConstants.OK_ID) {
			final UMLDesignerProfileVersion uMLDesignerProfileVersion = versionDialog
					.getUMLDesignerProfileVersion();

			result = defineAndAnnotateProfile(rootProfile,
					uMLDesignerProfileVersion);

			// Remove the oldest definitions of the sub profiles then redefine
			// the whole
			for (Profile profile : allContentProfile) {

				result = defineAndAnnotateProfile(profile,
						uMLDesignerProfileVersion);
			}
		}
		return result;
	}

	/**
	 * Define and annotate the profile with the version
	 * profileVersionAnnotation.
	 * 
	 * @param profile
	 *            to define.
	 * @param profileVersionAnnotation
	 *            to use for the annotation of the profile.
	 * @return true if defined, else false.
	 */
	public static boolean defineAndAnnotateProfile(final Profile profile,
			final UMLDesignerProfileVersion profileVersionAnnotation) {
		boolean result = true;
		// TODO to review if we must remove the old definition of the profile
		// Remove the old definition of the root profile
		// if (rootProfile.getDefinition() != null) {
		// rootProfile.getEAnnotations().remove(rootProfile.getDefinition().eContainer());
		// }
		// Define the root profile
		if (profile.define() == null)
			result = false;
		else {
			final EPackage rootProfileDefinition = profile.getDefinition();
			// use this condition if the old definition of the profile is
			// removed
			// if (rootProfileDefinition
			// .getEAnnotation(UMLDesignerProfileVersion.UML_DESIGNER_PROFILE_EANNOTATION_SOURCE)
			// ==
			// null) {
			final EAnnotation eAnnotation = EcoreFactory.eINSTANCE
					.createEAnnotation();
			final UMLDesignerProfileVersion umlDesignerProfileVersion = new UMLDesignerProfileVersion();
			umlDesignerProfileVersion.initEAnnotationVersion(eAnnotation,
					profileVersionAnnotation);
			rootProfileDefinition.getEAnnotations().add(eAnnotation);
			// }
		}
		return result;
	}

	/**
	 * Define the profile root and all sub profile.
	 * 
	 * @param rootProfile
	 *            to define
	 * @return true if defined, else false.
	 */
	public static boolean defineAllProfiles(final Profile rootProfile) {

		final List<Profile> allContentProfile = new ArrayList<Profile>();

		for (TreeIterator<EObject> eObjects = rootProfile.eAllContents(); eObjects
				.hasNext();) {
			final EObject eObject = eObjects.next();
			if (eObject instanceof Profile) {
				final Profile profile = (Profile) eObject;
				allContentProfile.add(profile);
			}
		}
		return defineProfile(rootProfile, allContentProfile);
	}

	/**
	 * Undefine the profile.
	 * 
	 * @param rootProfile
	 *            to undefine
	 * @param allContentProfile
	 *            the sub profile to undefine
	 */
	public void undefineProfile(final Profile rootProfile,
			final List<Profile> allContentProfile) {
		final String[] buttonYesNo = { yes, no };
		final String[] buttonYes = { yes };
		final Shell activeShell = PlatformUI.getWorkbench().getDisplay()
				.getActiveShell();
		MessageDialog msgDialogYesNo = null;
		MessageDialog msgDialogYes = null;
		if (rootProfile.getDefinition() != null) {
			msgDialogYesNo = new MessageDialog(activeShell, undefineProfile,
					null, "Would you like to undefine this profile ?",
					MessageDialog.QUESTION, buttonYesNo, 1);
			final int diagResult = msgDialogYesNo.open();
			if (diagResult == 0) {
				rootProfile.getEAnnotations().remove(
						rootProfile.getDefinition().eContainer());
				for (Profile profile : allContentProfile) {
					if (profile.getDefinition() != null) {
						profile.getEAnnotations().remove(
								profile.getDefinition().eContainer());
					}
				}
				msgDialogYes = new MessageDialog(activeShell, undefineProfile,
						null, "The profile is undefined",
						MessageDialog.INFORMATION, buttonYes, 0);
				msgDialogYes.open();
			}
		} else {
			msgDialogYes = new MessageDialog(activeShell, undefineProfile,
					null, "The profile is not defined !",
					MessageDialog.WARNING, buttonYes, 0);
			msgDialogYes.open();
		}
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
	public boolean canReconnectExtension(final EObject element,
			final EObject target) {
		if (element instanceof Extension) {
			final Extension extension = (Extension) element;
			if (target instanceof ElementImport) {
				final Class newPointedMetaClass = (Class) ((ElementImport) target)
						.getImportedElement();

				final NamedElement baseProperty = extension.getStereotype()
						.getMember(base + newPointedMetaClass.getName());
				if (baseProperty == null)
					return true;
				else if (baseProperty instanceof Property) {
					if (!((Property) baseProperty).getType().equals(
							newPointedMetaClass))
						return true;
				}
			}
			if (target instanceof Stereotype) {
				final NamedElement baseProperty = ((Stereotype) target)
						.getMember(base + extension.getMetaclass().getName());
				if (baseProperty == null)
					return true;
				else if (baseProperty instanceof Property) {
					if (!((Property) baseProperty).getType().equals(
							extension.getMetaclass()))
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * Test if is it possible to create an extension from stereotype source to
	 * the stereotype extension.
	 * 
	 * @param source
	 *            stereotype.
	 * @param target
	 *            stereotype.
	 * @return true if the extension between source and target is possible.
	 */
	public boolean canCreateExtension(final EObject source, final EObject target) {
		if (source instanceof Stereotype) {
			final Stereotype stereotypeSource = (Stereotype) source;
			if (target instanceof ElementImport) {
				final ElementImport elementImport = (ElementImport) target;
				final NamedElement metaClass = elementImport
						.getImportedElement();

				// this code find all extension including inherited.
				final NamedElement baseProperty = stereotypeSource
						.getMember(base + metaClass.getName());
				if (baseProperty == null)
					return true;
				else if (baseProperty instanceof Property) {
					if (!((Property) baseProperty).getType().equals(metaClass))
						return true;
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

				final Stereotype stereotypeTarget = (Stereotype) target;
				if (stereotypeSource.getGeneralization(stereotypeTarget) == null
						&& stereotypeTarget.getGeneralization(stereotypeSource) == null)
					return true;
			}
		}
		return false;
	}

	/**
	 * Get all owned attributes of the classifier.
	 * 
	 * @param classifier
	 *            to find attribute from.
	 * @return a list of found attribute.
	 */
	public List<Property> getClassifierAttributes(final Classifier classifier) {
		if (classifier instanceof Profile) {
			return null;
		}
		final List<Property> properties = new ArrayList<Property>();
		for (Property property : classifier.getAttributes()) {
			if (property.getAssociation() == null
					|| !(property.getAssociation() instanceof Extension))
				properties.add(property);
		}
		return properties;
	}

	/**
	 * Get all owned attributes of the classifier.
	 * 
	 * @param classifier
	 *            to find attribute from.
	 * @return a list of found attribute.
	 */
	public List<Property> getClassifierAttributes(final Profile classifier) {
		return null;
	}

	/**
	 * A message dialog for the creation of an extra association between the
	 * stereotype source 'stereotypeSource' and the the stereotype target
	 * 'targetElement'.
	 * 
	 * @param stereotypeSource
	 *            stereotype source.
	 * @param targetElement
	 *            stereotype target.
	 * @param profile
	 *            owner of the stereotypes.
	 */
	public void createExtraAssociationDialog(final Stereotype stereotypeSource,
			final Element targetElement, final Profile profile) {

		final ExtraAssociationSelectionDialog dialog = new ExtraAssociationSelectionDialog(
				PlatformUI.getWorkbench().getDisplay().getActiveShell(),
				profile, stereotypeSource, targetElement, false);
		dialog.open();
		if (dialog.getResult() != null) {
			if (targetElement instanceof Stereotype) {
				final Stereotype stereotypeTarget = (Stereotype) targetElement;
				if (dialog.isExtraAssociation()) {
					createExtraAssociation(stereotypeSource, stereotypeTarget,
							dialog.getAssociationName(), dialog.getResult(),
							profile);
				} else {
					createAssociation(stereotypeSource, stereotypeTarget,
							dialog.getAssociationName());
				}
			}
		}
	}

	/**
	 * Create an extra association between the stereotype source
	 * 'stereotypeSource' and the the stereotype target 'stereotypeTarget'. the
	 * result stereotype is named 'stereotypName'.
	 * 
	 * @param stereotypeSource
	 *            the stereotype source.
	 * @param stereotypeTarget
	 *            the stereotype target.
	 * @param stereotypName
	 *            the stereotype name.
	 * @param result
	 *            the metaclass selected for of the creation of the extra
	 *            association.
	 * @param profile
	 *            owner of the stereotypes.
	 * @return the new stereotype of the extra association.
	 */
	public Stereotype createExtraAssociation(final Stereotype stereotypeSource,
			final Stereotype stereotypeTarget, final String stereotypName,
			final Object[] result, final Profile profile) {
		ElementImport metaClass = null;
		Stereotype newStereotype = null;
		if (result != null && result[0] instanceof Class) {
			final Class selectedMetaclass = (Class) result[0];
			final PackageableElement importedMember = profile
					.getImportedMember(selectedMetaclass.getName());
			if (importedMember == null) {
				metaClass = profile.createMetaclassReference(selectedMetaclass);
			} else {
				metaClass = profile.getElementImport(importedMember);
			}
		}
		if (metaClass != null) {
			newStereotype = UMLFactory.eINSTANCE.createStereotype();
			newStereotype.setName(stereotypName);
			profile.getOwnedStereotypes().add(newStereotype);
			createMetaclassExtension(newStereotype, metaClass);
			createStereotypeAssociation(newStereotype, metaClass,
					stereotypeSource, stereotypeTarget);
		}
		return newStereotype;
	}

	/**
	 * Create an UML Association between the stereotypes source and target.
	 * 
	 * @param stereotypeSource
	 *            stereotype source.
	 * @param stereotypeTarget
	 *            stereotype target.
	 * @param associationName
	 *            the name of the Association.
	 * @return the created association.
	 */
	public Association createAssociation(final Stereotype stereotypeSource,
			final Stereotype stereotypeTarget, final String associationName) {
		final Association association = UMLFactory.eINSTANCE
				.createAssociation();
		association.setName(associationName);

		final Property sourceStereoProperty = UMLFactory.eINSTANCE
				.createProperty();
		sourceStereoProperty.setName(stereotypeSource.getName() + "s");
		sourceStereoProperty.setType(stereotypeSource);
		sourceStereoProperty.setLower(0);
		sourceStereoProperty.setUpper(-1);
		association.getOwnedEnds().add(sourceStereoProperty);

		final Property targetStereoProperty = UMLFactory.eINSTANCE
				.createProperty();
		targetStereoProperty.setName(stereotypeTarget.getName() + "s");
		targetStereoProperty.setType(stereotypeTarget);
		targetStereoProperty.setLower(0);
		targetStereoProperty.setUpper(-1);
		association.getOwnedEnds().add(targetStereoProperty);

		association.getNavigableOwnedEnds().add(targetStereoProperty);
		stereotypeSource.getProfile().getPackagedElements().add(association);
		return association;
	}

	/**
	 * Create an UML Extension between the stereotype source and the target
	 * element.
	 * 
	 * @param stereotype
	 *            source.
	 * @param targetElement
	 *            the target.
	 */
	public void createExtension(final Stereotype stereotype,
			final Element targetElement) {
		if (targetElement instanceof ElementImport) {
			createMetaclassExtension(stereotype, (ElementImport) targetElement);
		} else if (targetElement instanceof Stereotype) {
			createGeneralization(stereotype, (Stereotype) targetElement);
		}
	}

	/**
	 * Create an UML Generalisation between the stereotypes source and target.
	 * 
	 * @param stereotypeSource
	 *            of the generalisation.
	 * @param stereotypeTarget
	 *            of the generalisation.
	 */
	public void createGeneralization(final Stereotype stereotypeSource,
			final Stereotype stereotypeTarget) {
		if (!isGeneraleFor(stereotypeTarget, stereotypeSource)) {
			final Generalization generalization = UMLFactory.eINSTANCE
					.createGeneralization();
			generalization.setGeneral(stereotypeTarget);
			generalization.setSpecific(stereotypeSource);
			stereotypeSource.getGeneralizations().add(generalization);
		}
	}

	/**
	 * Test if the stereotype target is a generalisation of the stereotype
	 * source.
	 * 
	 * @param stereotypeTarget
	 *            of the generalisation.
	 * @param stereotypeSource
	 *            of the generalisation.
	 * @return true if the stereotype target is a generalisation of the
	 *         stereotype source.
	 */
	public boolean isGeneraleFor(final Stereotype stereotypeTarget,
			final Stereotype stereotypeSource) {
		for (Generalization generalization : stereotypeSource
				.getGeneralizations()) {
			if (generalization.getGeneral().equals(stereotypeTarget)) {
				return true;
			}
		}
		return false;

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
	public Extension createMetaclassExtension(final Stereotype stereotype,
			final ElementImport elementImport) {
		Extension extension = null;
		final PackageableElement importedElement = elementImport
				.getImportedElement();
		if (importedElement != null && importedElement instanceof Type
				&& !isExtentedBy((Type) importedElement, stereotype)) {

			final Type metaclass = (Type) importedElement;
			final Property baseMetaclass = UMLFactory.eINSTANCE
					.createProperty();
			baseMetaclass.setName(base + metaclass.getName());
			baseMetaclass.setType(metaclass);
			stereotype.getOwnedAttributes().add(baseMetaclass);

			final ExtensionEnd extensionEnd = UMLFactory.eINSTANCE
					.createExtensionEnd();
			extensionEnd.setName("extension_" + stereotype.getName());
			extensionEnd.setType(stereotype);

			extension = UMLFactory.eINSTANCE.createExtension();
			extension.getOwnedEnds().add(extensionEnd);
			extension.setName(stereotype.getName() + "Extend"
					+ metaclass.getName());
			extension.getMemberEnds().add(baseMetaclass);

			stereotype.getProfile().getPackagedElements().add(extension);
		}

		return extension;
	}

	/**
	 * Create an StereotypeAssociation for the extra Association.
	 * 
	 * @param stereotype
	 *            of the extra Association
	 * @param elementImport
	 *            of the extra Association
	 * @param stereotypeSource
	 *            of the extra Association
	 * @param stereotypeTarget
	 *            of the extra Association
	 */
	public void createStereotypeAssociation(final Stereotype stereotype,
			final ElementImport elementImport,
			final Stereotype stereotypeSource, final Stereotype stereotypeTarget) {
		final PackageableElement importedElement = elementImport
				.getImportedElement();

		if (importedElement != null && importedElement instanceof Type
				&& isExtentedBy((Type) importedElement, stereotype)) {

			final Type metaclass = (Type) importedElement;
			final Class metaclassClass = (Class) importedElement;
			final ArrayList<Property> properties = UMLServices
					.getRelationShipSourceTargetPeroperties(metaclassClass);
			final Property sourceProperty = properties.get(0);
			final Property targetProperty = properties.get(1);
			metaclassClass.getAttributes();

			final Property associationSourceEnd = UMLFactory.eINSTANCE
					.createProperty();
			associationSourceEnd.setName("sourceRole_"
					+ sourceProperty.getName());
			associationSourceEnd.setType(stereotypeSource);
			stereotype.getOwnedAttributes().add(associationSourceEnd);

			final Property associationTargetEnd = UMLFactory.eINSTANCE
					.createProperty();
			associationTargetEnd.setName("targetRole_"
					+ targetProperty.getName());
			associationTargetEnd.setType(stereotypeTarget);
			stereotype.getOwnedAttributes().add(associationTargetEnd);

			final Extension extension = getExtenstion(stereotype, metaclass);

			extension.getMemberEnds().add(associationSourceEnd);
			extension.getMemberEnds().add(associationTargetEnd);
		}
	}

	/**
	 * Find the Extension of stereotype to metacalss.
	 * 
	 * @param stereotype
	 *            of the extension.
	 * @param metaclass
	 *            of the extension.
	 * @return the found extension, or null.
	 */
	public Extension getExtenstion(final Stereotype stereotype,
			final Type metaclass) {
		for (Extension extention : stereotype.getProfile().getOwnedExtensions(
				false)) {
			if (extention.getName() != null) {
				if (extention.getName().equals(
						stereotype.getName() + "Extend" + metaclass.getName())) {
					return extention;
				}
			}
		}

		return null;
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
	public boolean isExtentedBy(final Type metaclassType,
			final Stereotype stereotype) {
		return !(stereotype.getOwnedAttribute(base + metaclassType.getName(),
				metaclassType) == null);
	}
}
