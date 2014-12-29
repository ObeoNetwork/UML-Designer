/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.api.services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.pde.internal.ui.PDEPlugin;
import org.eclipse.pde.internal.ui.wizards.ResizableWizardDialog;
import org.eclipse.pde.internal.ui.wizards.exports.PluginExportWizard;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWizard;
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
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.obeonetwork.dsl.uml2.design.internal.dialogs.ExtraAssociationSelectionDialog;
import org.obeonetwork.dsl.uml2.design.internal.dialogs.ImportMetaclassDialog;
import org.obeonetwork.dsl.uml2.design.internal.dialogs.InitProfilePluginDialog;
import org.obeonetwork.dsl.uml2.design.internal.services.AssociationServices;
import org.obeonetwork.dsl.uml2.design.internal.services.EcoreToGenmodel;
import org.obeonetwork.dsl.uml2.design.internal.services.ElementServices;
import org.obeonetwork.dsl.uml2.design.internal.services.GenerateModelCode;
import org.obeonetwork.dsl.uml2.design.internal.services.GenericUMLProfileTools;
import org.obeonetwork.dsl.uml2.design.internal.services.LabelServices;
import org.obeonetwork.dsl.uml2.design.internal.services.LogServices;
import org.obeonetwork.dsl.uml2.design.internal.services.NewPluginProject;
import org.obeonetwork.dsl.uml2.design.internal.services.NodeInverseRefsServices;
import org.obeonetwork.dsl.uml2.design.internal.services.ProfileServices;
import org.obeonetwork.dsl.uml2.design.internal.services.UmlToEcore;
import org.obeonetwork.dsl.uml2.design.internal.services.ValidateEmfElement;
import org.obeonetwork.dsl.uml2.design.internal.services.ValidateUMLElement;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * A set of services to handle the Package containment diagram.
 *
 * @author Mohamed-Lamine BOUKHANOUFA <a href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class ProfileDiagramServices extends AbstractDiagramServices {

	private static final String BASE = "base_"; //$NON-NLS-1$

	private static final String YES = "Yes"; //$NON-NLS-1$

	private static final String NO = "No"; //$NON-NLS-1$

	private static final String OK = "OK"; //$NON-NLS-1$

	private static final String UNDEFINE_PROFILE = "Undefine the Profile"; //$NON-NLS-1$

	private static final String OBEO_NETWORK_PLUGIN_NAME = new String("org.obeonetwork"); //$NON-NLS-1$

	private static final String OBEO_NETWORK_URI = new String("http://www.obeonetwork.org/"); //$NON-NLS-1$

	private String profileName = new String();

	private static final String DEFAULT_PROFILE_NAME = new String("profile"); //$NON-NLS-1$

	private String profilePluginName = new String();

	private String rootProfileURI = new String();

	private static final String SEPARATOR = new String("/"); //$NON-NLS-1$

	private static final String NS_URI = new String("nsURI"); //$NON-NLS-1$

	private static final String EXTENSION = new String("extension"); //$NON-NLS-1$

	private static final String POINT = new String("point"); //$NON-NLS-1$

	private static final String PLATFORM_PLUGIN = new String("platform:/plugin"); //$NON-NLS-1$

	/**
	 * Add to the plug-in the extension for the profile.
	 *
	 * @param profilePlugin
	 *            the plug-in
	 * @param modelFolder
	 *            the folder of the profile
	 * @param profileCopy
	 *            the profile
	 */
	private void addProfileExtensions(final IProject profilePlugin, final IFolder modelFolder,
			final Profile profileCopy) {
		final String exceptionMsg = "addProfileExtensions(" //$NON-NLS-1$
				+ profilePlugin.getClass() + "," + modelFolder.getClass() + "," //$NON-NLS-1$ //$NON-NLS-2$
				+ profileCopy.getClass() + ") not handled"; //$NON-NLS-1$
		final IFile pluginXML = profilePlugin.getFile("plugin.xml"); //$NON-NLS-1$

		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (final ParserConfigurationException e) {
			LogServices.INSTANCE.error(exceptionMsg, e);
		}
		org.w3c.dom.Document document = null;
		try {
			if (builder != null) {
				document = builder.parse(pluginXML.getLocation().toFile());
			}
		} catch (final SAXException e) {
			LogServices.INSTANCE.error(exceptionMsg, e);
		} catch (final IOException e) {
			LogServices.INSTANCE.error(exceptionMsg, e);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
		if (document != null) {
			final String xmiIdProfileCopy = profileCopy.eResource().getURIFragment(profileCopy);
			final Node racine = document.getDocumentElement();
			org.w3c.dom.Element extensionForGeneratedPackage = document.createElement(EXTENSION);
			extensionForGeneratedPackage.setAttribute(POINT, "org.eclipse.uml2.uml.generated_package"); //$NON-NLS-1$
			org.w3c.dom.Element profile = document.createElement(DEFAULT_PROFILE_NAME);
			profile.setAttribute("location", PLATFORM_PLUGIN //$NON-NLS-1$
					+ profileCopy.eResource().getURI() + "#" + xmiIdProfileCopy); //$NON-NLS-1$
			profile.setAttribute("uri", profileCopy.getURI()); //$NON-NLS-1$
			extensionForGeneratedPackage.appendChild(profile);
			racine.appendChild(extensionForGeneratedPackage);
			final EList<Element> allOwnedElements = profileCopy.allOwnedElements();
			for (final Element ownedPackageElement : allOwnedElements) {
				if (ownedPackageElement instanceof Profile) {
					final Profile ownedProfile = (Profile)ownedPackageElement;

					final String xmiIdownedPackage = ownedProfile.eResource().getURIFragment(ownedProfile);
					extensionForGeneratedPackage = document.createElement(EXTENSION);
					extensionForGeneratedPackage
					.setAttribute(POINT, "org.eclipse.uml2.uml.generated_package"); //$NON-NLS-1$
					profile = document.createElement(DEFAULT_PROFILE_NAME);
					profile.setAttribute("location", PLATFORM_PLUGIN //$NON-NLS-1$
							+ ownedProfile.eResource().getURI() + "#" //$NON-NLS-1$
							+ xmiIdownedPackage);
					profile.setAttribute("uri", ownedProfile.getURI()); //$NON-NLS-1$
					extensionForGeneratedPackage.appendChild(profile);
					racine.appendChild(extensionForGeneratedPackage);

				}
			}
			final org.w3c.dom.Element extensionForUriMapping = document.createElement(EXTENSION);
			extensionForUriMapping.setAttribute(POINT, "org.eclipse.emf.ecore.uri_mapping"); //$NON-NLS-1$
			final org.w3c.dom.Element mapping = document.createElement("mapping"); //$NON-NLS-1$
			mapping.setAttribute("source", "pathmap://MY_PROFILES/"); //$NON-NLS-1$ //$NON-NLS-2$
			mapping.setAttribute("target", //$NON-NLS-1$
					PLATFORM_PLUGIN + modelFolder.getFullPath());
			extensionForUriMapping.appendChild(mapping);

			racine.appendChild(extensionForUriMapping);

			// serialise the document to an xml file.
			Transformer transformer = null;
			try {
				transformer = TransformerFactory.newInstance().newTransformer();
			} catch (final TransformerConfigurationException e) {
				LogServices.INSTANCE.error(exceptionMsg, e);
			} catch (final TransformerFactoryConfigurationError e) {
				LogServices.INSTANCE.error(exceptionMsg, e);
			}
			final Result output = new StreamResult(pluginXML.getLocation().toFile());
			final Source input = new DOMSource(document);

			try {
				if (transformer != null) {
					transformer.transform(input, output);
				}
			} catch (final TransformerException e) {
				LogServices.INSTANCE.error(exceptionMsg, e);
			}
		}
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
	private boolean canCreateExtension(final EObject source, final EObject target) {
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
	 * Can create extension.
	 *
	 * @param source
	 *            Source
	 * @param target
	 *            Target
	 * @return True if extension can be created between source and target
	 */
	public boolean canCreateExtension(final Stereotype source, final ElementImport target) {
		return canCreateExtension((EObject)source, (EObject)target);
	}

	/**
	 * Can create extension.
	 *
	 * @param source
	 *            Source
	 * @param target
	 *            Target
	 * @return True if extension can be created between source and target
	 */
	public boolean canCreateExtension(final Stereotype source, final Stereotype target) {
		return canCreateExtension((EObject)source, (EObject)target);
	}

	/**
	 * Test if is possible to drop an ElementImport from an old profile to a new profile.
	 *
	 * @param elementImport
	 * @param oldProfile
	 * @param newProfile
	 * @return true if is possible to drop, else false.
	 */
	private boolean canDropElementImport(final ElementImport elementImport, final Profile oldProfile,
			final Profile newProfile) {
		if (newProfile.getImportedMember(elementImport.getImportedElement().getName()) != null) {
			return false;
		}
		for (final Extension extension : oldProfile.getOwnedExtensions(false)) {
			if (extension.getMetaclass() != null
					&& extension.getMetaclass().equals(elementImport.getImportedElement())) {
				return false;
			}
		}
		return true;
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
	 * Compute element import label.
	 *
	 * @param element
	 *            Element import
	 * @return Label
	 */
	public String computeElementImportLabel(ElementImport element) {
		return LabelServices.INSTANCE.computeElementImportLabel(element);
	}

	/**
	 * Compute profile label.
	 *
	 * @param element
	 *            Profile
	 * @return Label
	 */
	public String computeProfileLabel(Profile element) {
		return LabelServices.INSTANCE.computeProfileLabel(element);
	}

	/**
	 * Compute the source label of for the Extra association defined by the stereotype "stereotype".
	 *
	 * @param stereotype
	 *            the stereotype
	 * @return the label
	 */
	public String computeSourceLabel(final Stereotype stereotype) {
		final ArrayList<Property> properties = ProfileServices.INSTANCE
				.getRelationShipSourceTargetPeroperties(getMetaclass(stereotype.getOwnedAttributes().get(0)));

		return LabelServices.INSTANCE.computeAssociationEndLabel(properties.get(0));
	}

	/**
	 * Compute the label of the given stereotype.
	 *
	 * @param element
	 *            the {@link Stereotype} for which to retrieve a label.
	 * @return the computed label.
	 */
	public String computeStereotypeLabel(Stereotype element) {
		return LabelServices.INSTANCE.computeStereotypeLabel(element);
	}

	/**
	 * Compute the target label of for the Extra association defined by the stereotype "stereotype".
	 *
	 * @param stereotype
	 *            the stereotype
	 * @return the label
	 */
	public String computeTargetLabel(final Stereotype stereotype) {
		final ArrayList<Property> properties = ProfileServices.INSTANCE
				.getRelationShipSourceTargetPeroperties(getMetaclass(stereotype.getOwnedAttributes().get(0)));

		return LabelServices.INSTANCE.computeAssociationEndLabel(properties.get(1));
	}

	/**
	 * compute the uri of element.
	 *
	 * @param element
	 *            for which the uri will be computed
	 * @return the uri
	 */
	private String computeURI(final NamedElement element) {
		String uri = null;
		if (element.getOwner() == null && element.getNearestPackage().getURI() != null
				&& element.getNearestPackage().getURI().length() != 0) {
			uri = element.getNearestPackage().getURI();
		} else if (element.getOwner() != null && element.getOwner().getNearestPackage() != null
				&& element.getOwner().getNearestPackage().getURI() != null) {
			uri = element.getOwner().getNearestPackage().getURI() + element.getName() + SEPARATOR;
		} else {
			uri = rootProfileURI;
		}

		return uri;
	}

	/**
	 * Create an UML Association between the stereotypes source and target.
	 *
	 * @param stereotypeSource
	 *            stereotype source.
	 * @param stereotypeTarget
	 *            stereotype target.
	 * @return the created association.
	 */
	public Association createAssociation(final Stereotype stereotypeSource, final Stereotype stereotypeTarget) {
		return createAssociation(stereotypeSource, stereotypeTarget, stereotypeSource.getName() + "To" //$NON-NLS-1$
				+ stereotypeTarget.getName());
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
	private Association createAssociation(final Stereotype stereotypeSource,
			final Stereotype stereotypeTarget, final String associationName) {
		final Association association = UMLFactory.eINSTANCE.createAssociation();

		// The name is provided by the item provider

		final Property sourceStereoProperty = UMLFactory.eINSTANCE.createProperty();
		sourceStereoProperty.setName(stereotypeSource.getName() + "s"); //$NON-NLS-1$
		sourceStereoProperty.setType(stereotypeSource);
		sourceStereoProperty.setLower(0);
		sourceStereoProperty.setUpper(-1);
		association.getOwnedEnds().add(sourceStereoProperty);

		final Property targetStereoProperty = UMLFactory.eINSTANCE.createProperty();
		targetStereoProperty.setName(stereotypeTarget.getName() + "s"); //$NON-NLS-1$
		targetStereoProperty.setType(stereotypeTarget);
		targetStereoProperty.setLower(0);
		targetStereoProperty.setUpper(-1);
		association.getOwnedEnds().add(targetStereoProperty);

		association.getNavigableOwnedEnds().add(targetStereoProperty);
		stereotypeSource.getProfile().getPackagedElements().add(association);
		return association;
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
	 * Create an extra association between the stereotype source 'stereotypeSource' and the the stereotype
	 * target 'stereotypeTarget'. the result stereotype is named 'stereotypName'.
	 *
	 * @param stereotypeSource
	 *            the stereotype source.
	 * @param stereotypeTarget
	 *            the stereotype target.
	 * @param stereotypName
	 *            the stereotype name.
	 * @param result
	 *            the metaclass selected for of the creation of the extra association.
	 * @param profile
	 *            owner of the stereotypes.
	 * @return the new stereotype of the extra association.
	 */
	private Stereotype createExtraAssociation(final Stereotype stereotypeSource,
			final Stereotype stereotypeTarget, final String stereotypName, final Object[] result,
			final Profile profile) {
		ElementImport metaClass = null;
		Stereotype newStereotype = null;
		if (result != null && result[0] instanceof Class) {
			final Class selectedMetaclass = (Class)result[0];
			final PackageableElement importedMember = profile.getImportedMember(selectedMetaclass.getName());
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
			createStereotypeAssociation(newStereotype, metaClass, stereotypeSource, stereotypeTarget);
		}
		return newStereotype;
	}

	/**
	 * A message dialog for the creation of an extra association between the stereotype source
	 * 'stereotypeSource' and the the stereotype target 'targetElement'.
	 *
	 * @param stereotypeSource
	 *            stereotype source.
	 * @param targetElement
	 *            stereotype target.
	 * @param profile
	 *            owner of the stereotypes.
	 */
	public void createExtraAssociationDialog(final Stereotype stereotypeSource, final Element targetElement,
			final Profile profile) {

		final ExtraAssociationSelectionDialog dialog = new ExtraAssociationSelectionDialog(PlatformUI
				.getWorkbench().getDisplay().getActiveShell(), profile, stereotypeSource, targetElement,
				false);
		dialog.open();
		if (dialog.getResult() != null) {
			if (targetElement instanceof Stereotype) {
				final Stereotype stereotypeTarget = (Stereotype)targetElement;
				createExtraAssociation(stereotypeSource, stereotypeTarget, dialog.getAssociationName(),
						dialog.getResult(), profile);
			}
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
	private void createGeneralization(final Stereotype stereotypeSource, final Stereotype stereotypeTarget) {
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
	private Extension createMetaclassExtension(final Stereotype stereotype, final ElementImport elementImport) {
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
	 * Create a new plug-in project with progress bar. see {@link createPluginProject}
	 *
	 * @param pluginName
	 * @return
	 */
	private IProject createPluginProjectWithProgress(final String pluginName) {
		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		final NewPluginProject newPluginProject = new NewPluginProject();
		final IProject[] profilePlugin = new IProject[1];

		final IRunnableWithProgress validationRunnable = new IRunnableWithProgress() {
			public void run(final IProgressMonitor progressMonitor) throws InvocationTargetException,
			InterruptedException {
				try {
					profilePlugin[0] = newPluginProject.createPluginProject(
							pluginName,
							new ArrayList<String>(Arrays.asList("src")), //$NON-NLS-1$
							new ArrayList<IProject>(), new HashSet<String>(),
							new ArrayList<String>(Arrays.asList("org.eclipse.ui", //$NON-NLS-1$
									"org.eclipse.core.runtime")), //$NON-NLS-1$
									new NullProgressMonitor(), shell);

				} finally {
					progressMonitor.done();
				}
			}
		};

		try {
			new ProgressMonitorDialog(shell).run(true, true, validationRunnable);
		} catch (final Exception exception) {
			EMFEditUIPlugin.INSTANCE.log(exception);
		}

		return profilePlugin[0];
	}

	/**
	 * Create a new {@link Stereotype} in the profile that directly or indirectly contains the elementImport
	 * in parameter and create a extension to this elementImport from the new created stereotype.
	 *
	 * @param elementImport
	 *            the elementImport.
	 * @return a new stereotype.
	 */
	public Stereotype createStereotype(ElementImport elementImport) {
		final Stereotype newStereotype = UMLFactory.eINSTANCE.createStereotype();
		getProfileOwner(elementImport).getOwnedStereotypes().add(newStereotype);
		createMetaclassExtension(newStereotype, elementImport);
		return newStereotype;
	}

	/**
	 * Create a new {@link Stereotype} in the profile that directly or indirectly contains the package_ in
	 * parameter.
	 *
	 * @param package_
	 *            the package_.
	 * @return a new stereotype.
	 */
	public Stereotype createStereotype(Package package_) {
		final Stereotype newStereotype = UMLFactory.eINSTANCE.createStereotype();
		package_.getOwnedStereotypes().add(newStereotype);
		return newStereotype;
	}

	/**
	 * Create a new {@link Stereotype} in the profile.
	 *
	 * @param profile
	 *            the profile.
	 * @return a new stereotype.
	 */
	public Stereotype createStereotype(Profile profile) {
		final Stereotype newStereotype = UMLFactory.eINSTANCE.createStereotype();
		profile.getOwnedStereotypes().add(newStereotype);
		return newStereotype;
	}

	/**
	 * Create a new {@link Stereotype} in the profile that directly or indirectly contains the stereotype in
	 * parameter and create a generalization to this stereotype from the new created stereotype.
	 *
	 * @param stereotype
	 *            the stereotype.
	 * @return a new stereotype.
	 */
	public Stereotype createStereotype(Stereotype stereotype) {
		final Stereotype newStereotype = UMLFactory.eINSTANCE.createStereotype();
		if (stereotype.getOwner() instanceof Package) {
			((Package)stereotype.getOwner()).getOwnedStereotypes().add(newStereotype);
		}
		createGeneralization(newStereotype, stereotype);
		return newStereotype;
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
	private void createStereotypeAssociation(final Stereotype stereotype, final ElementImport elementImport,
			final Stereotype stereotypeSource, final Stereotype stereotypeTarget) {
		final PackageableElement importedElement = elementImport.getImportedElement();

		if (importedElement != null && importedElement instanceof Type
				&& isExtendedBy((Type)importedElement, stereotype)) {

			final Type metaclass = (Type)importedElement;
			final Class metaclassClass = (Class)importedElement;
			final ArrayList<Property> properties = ProfileServices.INSTANCE
					.getRelationShipSourceTargetPeroperties(metaclassClass);
			final Property sourceProperty = properties.get(0);
			final Property targetProperty = properties.get(1);
			metaclassClass.getAttributes();

			final Property associationSourceEnd = UMLFactory.eINSTANCE.createProperty();
			associationSourceEnd.setName("sourceRole_" + sourceProperty.getName()); //$NON-NLS-1$
			associationSourceEnd.setType(stereotypeSource);
			stereotype.getOwnedAttributes().add(associationSourceEnd);

			final Property associationTargetEnd = UMLFactory.eINSTANCE.createProperty();
			associationTargetEnd.setName("targetRole_" + targetProperty.getName()); //$NON-NLS-1$
			associationTargetEnd.setType(stereotypeTarget);
			stereotype.getOwnedAttributes().add(associationTargetEnd);

			final Extension extension = getExtension(stereotype, metaclass);

			extension.getMemberEnds().add(associationSourceEnd);
			extension.getMemberEnds().add(associationTargetEnd);
		}
	}

	/**
	 * Dialog message to define a profile.
	 *
	 * @param rootProfile
	 *            to define
	 */
	public void defineProfileDialog(final Profile rootProfile) {
		defineProfileDialog(rootProfile, ProfileServices.INSTANCE.getAllSubProfiles(rootProfile));
	}

	/**
	 * Dialog message to define a profile.
	 *
	 * @param rootProfile
	 *            to define
	 * @param allContentProfile
	 *            to define
	 */
	private void defineProfileDialog(final Profile rootProfile, final List<Profile> allContentProfile) {
		boolean result = false;

		final String[] buttonYes = {OK};
		final Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		MessageDialog msgDialogYes = null;

		result = ProfileServices.INSTANCE.defineProfile(rootProfile, allContentProfile);

		if (result) {
			msgDialogYes = new MessageDialog(activeShell, "Define the Profile", null, //$NON-NLS-1$
					"The profile is defined", MessageDialog.INFORMATION, buttonYes, 0); //$NON-NLS-1$
			msgDialogYes.open();
		}
	}

	/**
	 * deleteElementImport delete an elementImport and all its references (base_ attributes of the
	 * stereotypes).
	 *
	 * @param elementImport
	 *            to delete.
	 * @param profile
	 *            owner of element import.
	 */
	private void deleteElementImport(final ElementImport elementImport, final Profile profile) {
		for (final Extension extension : profile.getOwnedExtensions(false)) {
			if (extension.getMetaclass() != null
					&& extension.getMetaclass().equals(elementImport.getImportedElement())) {
				deleteExtension(extension, profile);
			}
		}
		EcoreUtil.delete(elementImport, true);
	}

	/**
	 * deleteExtension delete an extension and all its references (base_ attributes of the stereotype).
	 *
	 * @param extension
	 *            to delete.
	 * @param profile
	 *            owner of extension.
	 */
	private void deleteExtension(final Extension extension, final Profile profile) {
		for (final Element relatedElement : extension.getRelatedElements()) {
			if (relatedElement instanceof Stereotype && extension.getMetaclass() != null) {
				final EList<Property> properties = ((Stereotype)relatedElement).getAttributes();
				int propertiesSize = properties.size();
				for (int i = 0; i < propertiesSize; i++) {
					if (properties.get(i).getAssociation() != null
							&& properties.get(i).getAssociation().equals(extension)) {
						properties.get(i).destroy();
						i--;
						propertiesSize--;
					}
				}
			}
		}
		EcoreUtil.delete(extension, true);
	}

	/**
	 * deleteStereotype delete an stereotype and all its references (Extensions, Generalizations).
	 *
	 * @param stereotype
	 *            to delete.
	 * @param profile
	 *            owner of stereotype.
	 */
	private void deleteStereotype(final Stereotype stereotype, final Profile profile) {
		for (final Extension extension : profile.getOwnedExtensions(false)) {
			if (extension.getEndType(stereotype.getName()) != null) {
				final Property baseProperty = extension.getMemberEnd("base_" //$NON-NLS-1$
						+ extension.getMetaclass().getName(), extension.getMetaclass());
				if (baseProperty != null && baseProperty.getOwner().equals(stereotype)) {
					EcoreUtil.delete(extension, true);
				}
			}
		}
		EcoreUtil.delete(stereotype, true);
	}

	/**
	 * deleteUMLElment delete an UML element and all its references.
	 *
	 * @param element
	 *            to delete.
	 * @param profile
	 *            owner of element.
	 */
	public void deleteUMLElement(final Element element, final Profile profile) {
		if (element instanceof ElementImport) {
			deleteElementImport((ElementImport)element, profile);
		} else if (element instanceof Stereotype) {
			deleteStereotype((Stereotype)element, profile);
		} else if (element instanceof Extension) {
			deleteExtension((Extension)element, profile);
		} else {
			EcoreUtil.delete(element, true);
		}
	}

	/**
	 * Delete an UML Package.
	 *
	 * @param package_
	 *            the package
	 */
	public void deleteUMLPackage(final Package package_) {
		EcoreUtil.delete(package_, true);
	}

	/**
	 * Drop from diagram.
	 *
	 * @param newContainer
	 *            Container
	 * @param semanticElement
	 *            Semantic element
	 * @param containerView
	 *            Container view
	 */
	public void dropFromDiagramProfileService(final Element newContainer, final Element semanticElement,
			final DSemanticDecorator containerView) {
		final Session session = SessionManager.INSTANCE.getSession(newContainer);
		boolean doTheDrop = true;

		final Element oldContainer = semanticElement.getOwner();
		final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
		Command cmd = null;
		if (newContainer instanceof Profile && oldContainer instanceof Profile
				&& newContainer != oldContainer) {
			final Profile newProfile = (Profile)newContainer;
			final Profile oldProfile = (Profile)oldContainer;

			// Add the ElementImport to 'MetaclassReference' of newContainer
			// (profile)
			if (semanticElement instanceof ElementImport) {
				final ElementImport elementImport = (ElementImport)semanticElement;
				if (canDropElementImport(elementImport, oldProfile, newProfile)) {
					cmd = AddCommand.create(domain, newContainer,
							UMLPackage.Literals.PROFILE__METACLASS_REFERENCE, elementImport);
					if (cmd.canExecute()) {
						cmd.execute();
					}
				} else {
					doTheDrop = false;
					if (newProfile.getImportedMember(elementImport.getImportedElement().getName()) == null) {
						newProfile.createMetaclassReference(elementImport.getImportedElement());
					}
				}
			}
			// Move the Element import and extension of a stereotype if the
			// newContainer is a profile
			if (semanticElement instanceof Stereotype) {
				for (final Property property : ((Stereotype)semanticElement).getAttributes()) {
					if (property.getName().startsWith("base_")) { //$NON-NLS-1$
						Extension extension = null;
						if (property.getAssociation() != null
								&& property.getAssociation() instanceof Extension) {
							extension = (Extension)property.getAssociation();
						}
						if (extension != null
								&& ((Profile)oldContainer).getElementImport(extension.getMetaclass()) != null) {
							final ElementImport elementImport = ((Profile)oldContainer)
									.getElementImport(extension.getMetaclass());
							dropFromDiagramProfileService(newContainer, extension, containerView);
							if (elementImport != null) {
								dropFromDiagramProfileService(newContainer, elementImport, containerView);
							}
						}
					}
				}

				// Reset the current element as subject
				cmd = AddCommand.create(domain, newContainer,
						UMLPackage.Literals.PROFILE__METACLASS_REFERENCE, semanticElement);
				if (cmd.canExecute()) {
					cmd.execute();
				}
			}
		}
		if (doTheDrop) {
			final ReusedDescriptionServices reusedDescriptionServices = new ReusedDescriptionServices();
			reusedDescriptionServices.dropFromDiagram(newContainer, semanticElement, containerView);
		}

	}

	/**
	 * Export profile.
	 *
	 * @param rootProfile
	 *            Profile
	 */
	public void exportProfile(final Profile rootProfile) {
		final Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();

		final boolean isProfile = isProfileRoot(rootProfile);
		if (isProfile && validateUmlElementWithProgress(rootProfile)) {

			if (initParameters(rootProfile) == IDialogConstants.OK_ID
					&& ProfileServices.INSTANCE.defineAllProfiles(rootProfile)) {
				GenericUMLProfileTools.save(rootProfile);
				final IProject profilePlugin = createPluginProjectWithProgress(profilePluginName);

				// the following code is OK.
				final IFolder modelFolder = profilePlugin.getFolder("model"); //$NON-NLS-1$
				try {
					modelFolder.create(false, true, null);
				} catch (final CoreException e) {
					LogServices.INSTANCE.error("exportProfile(" + rootProfile.getClass() //$NON-NLS-1$
							+ ") not handled", e); //$NON-NLS-1$
				}

				// make a copy of the profile into the new plug-in used for the creation of static profile
				final IFile profileCopyIFile = modelFolder.getFile(profileName + "." //$NON-NLS-1$
						+ UMLResource.FILE_EXTENSION);

				final IFile rootProfileIFile = GenericUMLProfileTools
						.resourceToIFile(rootProfile.eResource());
				try {
					rootProfileIFile.copy(profileCopyIFile.getFullPath(), true, new NullProgressMonitor());
				} catch (final CoreException e) {
					LogServices.INSTANCE.error("exportProfile(" + rootProfile.getClass() //$NON-NLS-1$
							+ ") not handled", e); //$NON-NLS-1$
				}

				final Resource profileCopyResource = new ResourceSetImpl().createResource(URI
						.createURI(profileCopyIFile.getFullPath().toString()));

				final Profile profileCopy = (Profile)GenericUMLProfileTools
						.load(profileCopyResource.getURI());

				initEPackageStereotype(profileCopy);

				final UmlToEcore umlToEcore = new UmlToEcore();
				final Resource profileEcoreResource = umlToEcore.umlToEcore(profileCopy);

				if (validateEObjectWithProgress(profileEcoreResource.getContents().get(0))) {

					final EcoreToGenmodel ecoreToGenmodel = new EcoreToGenmodel();
					final GenModel genModel = ecoreToGenmodel.ecoreToGenmodel(profileEcoreResource);

					final GenerateModelCode generateModelCode = new GenerateModelCode();
					generateModelCode.generateModelCode(genModel);

					addProfileExtensions(profilePlugin, modelFolder, profileCopy);

					// the following code is OK.
					// IFolder targetBuild =
					// profilePlugin.getFolder("targetBuild");
					// try {
					// targetBuild.create(false, true, null);
					// } catch (CoreException e) {
					// e.printStackTrace();
					// }
					try {
						profilePlugin.build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
					} catch (final CoreException e) {
						LogServices.INSTANCE.error("exportProfile(" //$NON-NLS-1$
								+ rootProfile.getClass() + ") not handled", //$NON-NLS-1$
								e);
					}
					final IWorkbenchWizard wizard = new PluginExportWizard();

					final StructuredSelection selection = new StructuredSelection(profilePlugin);
					wizard.init(PlatformUI.getWorkbench(), selection);
					final WizardDialog wd = new ResizableWizardDialog(PDEPlugin.getActiveWorkbenchShell(),
							wizard);
					wd.create();
					wd.open();

					// Close the generated plugin, it contains uml.ecore and
					// ecore.ecore that confused Acceleo in the VP.
					try {
						profilePlugin.close(new NullProgressMonitor());
					} catch (final CoreException e) {
						e.printStackTrace();
					}
				} else {
					MessageDialog.openError(activeShell, "Exportation error", //$NON-NLS-1$
							"Due to the error, the exportation will be stopped."); //$NON-NLS-1$
				}

			} else {
				MessageDialog.openInformation(activeShell, "Exportation canceled", //$NON-NLS-1$
						"Exportation canceled by user."); //$NON-NLS-1$
			}
		} else {
			if (!isProfile) {
				MessageDialog
				.openError(activeShell, "Exportation error", //$NON-NLS-1$
						"The root element of this model is not a profile. Due to the error, the exportation will be stopped."); //$NON-NLS-1$
			} else {
				MessageDialog.openError(activeShell, "Exportation error", //$NON-NLS-1$
						"Due to the error, the exportation will be stopped."); //$NON-NLS-1$
			}
		}

	}

	/**
	 * Iterate over the given {@link Collection} of root elements to find a {@link Type} element with the
	 * given name.
	 *
	 * @param roots
	 *            the elements to inspect
	 * @param typeName
	 *            the name to match
	 * @return the found {@link Type} or <code>null</code>
	 */
	public Type findTypeByName(Collection<EObject> roots, String typeName) {
		return ElementServices.INSTANCE.findTypeByName(roots, typeName);
	}

	/**
	 * @see NodeInverseRefsServices#getAssociationInverseRefs(DDiagram)
	 */
	@SuppressWarnings("javadoc")
	public Collection<EObject> getAssociationInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getAssociationInverseRefs(diagram);
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
		for (final Property property : classifier.getAttributes()) {
			if (property.getAssociation() == null || !(property.getAssociation() instanceof Extension)) {
				properties.add(property);
			}
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
	 * Find the Extension of stereotype to metacalss.
	 *
	 * @param stereotype
	 *            of the extension.
	 * @param metaclass
	 *            of the extension.
	 * @return the found extension, or null.
	 */
	private Extension getExtension(final Stereotype stereotype, final Type metaclass) {
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
	 * Get the metaclass indicated by the association of the property 'property'.
	 *
	 * @param property
	 *            the property
	 * @return the metaclass
	 */
	private Class getMetaclass(final Property property) {
		Extension extension = null;
		if (property.getAssociation() instanceof Extension) {
			try {
				extension = (Extension)property.getAssociation();
			} catch (final Exception e) {
				LogServices.INSTANCE.error("getMetaclass(" + property.getClass() + ") not handled", e); //$NON-NLS-1$ //$NON-NLS-2$
				throw new RuntimeException(e);
			}
		}
		return extension != null ? extension.getMetaclass() : null;
	}

	/**
	 * Get the profile owner of this element.
	 *
	 * @param umlElement
	 *            the element
	 * @return the profile
	 */
	private Profile getProfileOwner(final Element umlElement) {

		if (umlElement.getOwner() instanceof Profile) {
			return (Profile)umlElement.getOwner();
		}
		return getProfileOwner(umlElement.getOwner());
	}

	/**
	 * @see AssociationServices#getSource(Association)
	 */
	@SuppressWarnings("javadoc")
	public Property getSource(Association association) {
		return AssociationServices.INSTANCE.getSource(association);
	}

	/**
	 * Return the source of an Extra Association.
	 *
	 * @param stereotype
	 *            the {@link Association} context
	 * @return first end of the Extra association
	 */
	public Stereotype getStereotypeSource(final Stereotype stereotype) {
		if (stereotype.getAllAttributes() != null && stereotype.getAllAttributes().size() > 1) {
			if (stereotype.getAllAttributes().get(1).getType() instanceof Stereotype) {
				return (Stereotype)stereotype.getAllAttributes().get(1).getType();
			}
		}
		return null;
	}

	/**
	 * Return the target of an Extra Association.
	 *
	 * @param stereotype
	 *            the {@link Association} context
	 * @return second end of the Extra association
	 */
	public Stereotype getStereotypeTarget(final Stereotype stereotype) {
		if (stereotype.getAllAttributes() != null && stereotype.getAllAttributes().size() > 2) {
			if (stereotype.getAllAttributes().get(2).getType() instanceof Stereotype) {
				return (Stereotype)stereotype.getAllAttributes().get(2).getType();
			}
		}
		return null;
	}

	/**
	 * @see AssociationServices#getTarget(Association)
	 */
	@SuppressWarnings("javadoc")
	public Property getTarget(Association association) {
		return AssociationServices.INSTANCE.getTarget(association);
	}

	/**
	 * Find the imported metaclass to the profile.
	 *
	 * @param profile
	 *            to test.
	 * @return a list of class.
	 */
	public List<PackageableElement> importMetaclass(final Profile profile) {
		final List<PackageableElement> alreadyOnProfile = new ArrayList<PackageableElement>();
		final List<String> alreadyOnProfileNames = new ArrayList<String>();

		final ImportMetaclassDialog dialog = new ImportMetaclassDialog(PlatformUI.getWorkbench().getDisplay()
				.getActiveShell(), profile, true);
		dialog.setHeaderMessageText("Select the UML Metaclasses to import."); //$NON-NLS-1$
		dialog.open();

		if (dialog.getResult() != null) {
			final List<Object> selectedObjects = Arrays.asList(dialog.getResult());
			for (final Object object : selectedObjects) {
				if (object instanceof Class) {
					final Class selectedMetaclass = (Class)object;
					final PackageableElement importedMember = profile.getImportedMember(selectedMetaclass
							.getName());
					if (importedMember == null) {
						profile.createMetaclassReference(selectedMetaclass);
					} else {
						// reserved for a future utilisation
						alreadyOnProfile.add(importedMember);
						alreadyOnProfileNames.add(importedMember.getName());
					}
				}
			}
		}
		if (alreadyOnProfile.size() > 0) {
			final String[] buttonList = {"Ok"}; //$NON-NLS-1$
			final MessageDialog msgDialog = new MessageDialog(PlatformUI.getWorkbench().getDisplay()
					.getActiveShell(), "Already imported Metaclasses", null, //$NON-NLS-1$
					"The following Metaclasses are already imported:" + "\n" //$NON-NLS-1$ //$NON-NLS-2$
					+ alreadyOnProfileNames, MessageDialog.INFORMATION, buttonList, 0);
			msgDialog.open();
		}
		return alreadyOnProfile;
	}

	/**
	 * Apply the stereotype EPackage to the profile and fill out the tagged value.
	 *
	 * @param profile
	 *            to initiate
	 */
	private void initEPackageStereotype(final Profile profile) {
		final EList<Element> allOwningPackages = profile.allOwnedElements();
		// allOwningPackages.add(profile);
		final Profile ecoreProfile = (Profile)GenericUMLProfileTools.load(URI
				.createURI("pathmap://UML_PROFILES/Ecore.profile.uml")); //$NON-NLS-1$
		profile.applyProfile(ecoreProfile);
		final Stereotype ePackage = ecoreProfile.getOwnedStereotype("EPackage"); //$NON-NLS-1$
		profile.applyStereotype(ePackage);
		if (profile.getName() != null) {
			profile.setValue(ePackage, "packageName", profile.getName()); //$NON-NLS-1$
			profile.setValue(ePackage, "nsPrefix", profile.getName()); //$NON-NLS-1$
			profile.setValue(ePackage, "prefix", profile.getName()); //$NON-NLS-1$
		}
		// if (profile.getURI() != null && profile.getURI().length() != 0)
		// profile.setValue(ePackage, nsURI, profile.getURI());
		// else {
		// profile.setValue(ePackage, nsURI, computeURI(profile));
		// profile.setURI(computeURI(profile));
		// }
		profile.setValue(ePackage, NS_URI, rootProfileURI);
		profile.setURI(rootProfileURI);

		for (final Element ownedPackageElement : allOwningPackages) {
			if (ownedPackageElement instanceof Package) {
				final Package ownedPackage = (Package)ownedPackageElement;
				ownedPackage.applyStereotype(ePackage);
				if (ownedPackage.getName() != null) {
					ownedPackage.setValue(ePackage, "packageName", //$NON-NLS-1$
							ownedPackage.getName());
					ownedPackage.setValue(ePackage, "nsPrefix", //$NON-NLS-1$
							ownedPackage.getName());
					ownedPackage.setValue(ePackage, "prefix", //$NON-NLS-1$
							ownedPackage.getName());
				}
				if (ownedPackage.getURI() != null && profile.getURI().length() != 0) {
					ownedPackage.setValue(ePackage, NS_URI, ownedPackage.getURI());
				} else {
					ownedPackage.setValue(ePackage, NS_URI, computeURI(ownedPackage));
					ownedPackage.setURI(computeURI(ownedPackage));
				}
			}
		}
		GenericUMLProfileTools.save(profile);
	}

	/**
	 * Initiate the needed parameters for the export of the profile.
	 *
	 * @param profile
	 *            to export.
	 * @return the pressed button, OK or CANCEL.
	 */
	private int initParameters(final Profile profile) {

		if (profile.getName() != null && profile.getName().length() != 0) {
			profileName = profile.getName().toLowerCase();
		} else {
			profileName = DEFAULT_PROFILE_NAME;
		}

		if (profile.getURI() != null && profile.getURI().length() != 0) {
			rootProfileURI = profile.getURI() + SEPARATOR;
			profilePluginName = rootProfileURI.replace("http://", "").replace( //$NON-NLS-1$ //$NON-NLS-2$
					SEPARATOR, ".") //$NON-NLS-1$
					+ "plugin"; //$NON-NLS-1$
		} else {
			rootProfileURI = OBEO_NETWORK_URI + profileName + SEPARATOR;
			profilePluginName = OBEO_NETWORK_PLUGIN_NAME + "." + profileName + "." //$NON-NLS-1$ //$NON-NLS-2$
					+ "plugin"; //$NON-NLS-1$
		}

		final InitProfilePluginDialog dialog = new InitProfilePluginDialog(profileName, rootProfileURI,
				profilePluginName);

		dialog.open();
		if (dialog.getReturnCode() == IDialogConstants.OK_ID) {
			profileName = dialog.getProfileName();
			rootProfileURI = dialog.getRootProfileURI();
			profilePluginName = dialog.getProfilePluginName();
			return IDialogConstants.OK_ID;
		}
		return IDialogConstants.CANCEL_ID;
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
	private boolean isExtendedBy(final Type metaclassType, final Stereotype stereotype) {
		return !(stereotype.getOwnedAttribute(BASE + metaclassType.getName(), metaclassType) == null);
	}

	/**
	 * Test if the stereotype target is a generalisation of the stereotype source.
	 *
	 * @param stereotypeTarget
	 *            of the generalisation.
	 * @param stereotypeSource
	 *            of the generalisation.
	 * @return true if the stereotype target is a generalisation of the stereotype source.
	 */
	private boolean isGeneraleFor(final Stereotype stereotypeTarget, final Stereotype stereotypeSource) {
		for (final Generalization generalization : stereotypeSource.getGeneralizations()) {
			if (generalization.getGeneral().equals(stereotypeTarget)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Verify if the model root is a Profile or not of a given profile.
	 *
	 * @param rootProfile
	 *            the given profile
	 * @return true if the model root is a profile else false
	 */
	private boolean isProfileRoot(final Profile rootProfile) {
		final EObject rootContainer = EcoreUtil.getRootContainer(rootProfile);
		if (rootContainer != null && rootContainer instanceof Profile) {
			return true;
		}
		return false;
	}

	/**
	 * Undefine the profile.
	 *
	 * @param rootProfile
	 *            to undefine
	 */
	private void undefineProfile(final Profile rootProfile) {
		if (rootProfile.getDefinition() != null) {
			final List<Profile> allContentProfile = ProfileServices.INSTANCE.getAllSubProfiles(rootProfile);
			rootProfile.getEAnnotations().remove(rootProfile.getDefinition().eContainer());
			for (final Profile profile : allContentProfile) {
				if (profile.getDefinition() != null) {
					profile.getEAnnotations().remove(profile.getDefinition().eContainer());
				}
			}
		}
	}

	/**
	 * Dialog message to undefine the profile.
	 *
	 * @param rootProfile
	 *            to undefine
	 */
	public void undefineProfileDialog(final Profile rootProfile) {
		final String[] buttonYesNo = {YES, NO};
		final String[] buttonYes = {OK};
		final Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		MessageDialog msgDialogYesNo = null;
		MessageDialog msgDialogYes = null;
		if (rootProfile.getDefinition() != null) {
			msgDialogYesNo = new MessageDialog(activeShell, UNDEFINE_PROFILE, null,
					"Would you like to undefine this profile ?", MessageDialog.QUESTION, buttonYesNo, 1); //$NON-NLS-1$
			final int diagResult = msgDialogYesNo.open();
			if (diagResult == 0) {
				undefineProfile(rootProfile);
				msgDialogYes = new MessageDialog(activeShell, UNDEFINE_PROFILE, null,
						"The profile is undefined", MessageDialog.INFORMATION, buttonYes, 0); //$NON-NLS-1$
				msgDialogYes.open();
			}
		} else {
			msgDialogYes = new MessageDialog(activeShell, UNDEFINE_PROFILE, null,
					"The profile is not defined !", MessageDialog.WARNING, buttonYes, 0); //$NON-NLS-1$
			msgDialogYes.open();
		}
	}

	/**
	 * Validate an EObject using the emf validator.
	 *
	 * @param eObject
	 *            the EObject
	 * @return true if no error or warning in the EObject otherwise return false.
	 */
	private boolean validateEObjectWithProgress(final EObject eObject) {
		final ValidateEmfElement validateAction = new ValidateEmfElement();
		final IStructuredSelection selection = new StructuredSelection(eObject);
		validateAction.updateSelection(selection);
		validateAction.setActiveWorkbenchPart(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getActiveEditor());
		validateAction.run();
		return validateAction.getDiagnosticResult();
	}

	/**
	 * Validate an UML Element using the UML validator.
	 *
	 * @param element
	 *            the EObject
	 * @return true if no error or warning in the EObject otherwise return false.
	 */
	private boolean validateUmlElementWithProgress(final Profile element) {
		final ValidateUMLElement umlValidator = new ValidateUMLElement();
		umlValidator.validateUMLmodel(element);
		return umlValidator.getDiagnosticResult();
	}

}
