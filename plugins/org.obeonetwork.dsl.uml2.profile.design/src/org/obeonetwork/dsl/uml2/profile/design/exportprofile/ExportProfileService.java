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
package org.obeonetwork.dsl.uml2.profile.design.exportprofile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.pde.internal.ui.PDEPlugin;
import org.eclipse.pde.internal.ui.wizards.ResizableWizardDialog;
import org.eclipse.pde.internal.ui.wizards.exports.PluginExportWizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.obeonetwork.dsl.uml2.design.services.LogServices;
import org.obeonetwork.dsl.uml2.profile.design.dialogs.InitProfilePluginDialog;
import org.obeonetwork.dsl.uml2.profile.design.services.GenericUMLProfileTools;
import org.obeonetwork.dsl.uml2.profile.design.services.UMLProfileServices;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ExportProfileService {

	String obeoNetworkPluginName = "org.obeonetwork";

	String obeoNetworkURI = "http://www.obeonetwork.org/";

	String profileName = "";

	String defaultProfileName = "profile";

	String profilePluginName = "";

	String rootProfileURI = "";

	String separator = "/";

	String nsURI = "nsURI";

	String extension = "extension";

	String point = "point";

	String platformPlugin = "platform:/plugin";

	/**
	 * Create an instance of this class.
	 */
	public ExportProfileService() {
	}

	@SuppressWarnings("restriction")
	public void exportProfile(final Profile rootProfile) {
		if (initParameters(rootProfile) == IDialogConstants.OK_ID
				&& UMLProfileServices.defineAllProfiles(rootProfile)) {

			final Shell activeShell = PlatformUI.getWorkbench().getDisplay()
					.getActiveShell();
			NewPluginProject newPluginProject = new NewPluginProject();
			final IProject profilePlugin = newPluginProject
					.createPluginProject(
							profilePluginName,
					new ArrayList<String>(Arrays.asList("src")), new ArrayList<IProject>(),
					new HashSet<String>(),
					new ArrayList<String>(Arrays.asList("org.eclipse.ui", "org.eclipse.core.runtime")),
					new NullProgressMonitor(), activeShell);

			// the following code is OK.
			final IFolder modelFolder = profilePlugin.getFolder("model");
			try {
				modelFolder.create(false, true, null);
			} catch (CoreException e) {
				new LogServices().error(
						"exportProfile(" + rootProfile.getClass()
								+ ") not handled", e);
			}

			// make a copy of the profile into the new plug-in used for the creation of static profile
			final IFile profileCopyIFile = modelFolder.getFile(profileName
					+ "." + UMLResource.FILE_EXTENSION);

			final IFile rootProfileIFile = GenericUMLProfileTools
					.resourceToIFile(rootProfile.eResource());
			try {
				rootProfileIFile.copy(profileCopyIFile.getFullPath(), true, new NullProgressMonitor());
			} catch (final CoreException e) {
				new LogServices().error(
						"exportProfile(" + rootProfile.getClass()
								+ ") not handled", e);
			}

			final Resource profileCopyResource = new ResourceSetImpl()
					.createResource(URI
					.createURI(profileCopyIFile.getFullPath().toString()));

			final Profile profileCopy = (Profile) GenericUMLProfileTools
					.load(profileCopyResource.getURI());

			initEPackageStereotype(profileCopy);

			final UmlToEcore umlToEcore = new UmlToEcore();
			final Resource profileEcoreResource = umlToEcore
					.umlToEcore(profileCopy);

			final EcoreToGenmodel ecoreToGenmodel = new EcoreToGenmodel();
			final GenModel genModel = ecoreToGenmodel
					.ecoreToGenmodel(profileEcoreResource);

			final GenerateModelCode generateModelCode = new GenerateModelCode();
			generateModelCode.generateModelCode(genModel);

			addProfileExtensions(profilePlugin, modelFolder, profileCopy);

			// the following code is OK.
			// IFolder targetBuild = profilePlugin.getFolder("targetBuild");
			// try {
			// targetBuild.create(false, true, null);
			// } catch (CoreException e) {
			// e.printStackTrace();
			// }
			try {
				profilePlugin.build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
			} catch (final CoreException e) {
				new LogServices().error(
						"exportProfile(" + rootProfile.getClass()
								+ ") not handled", e);
			}
			final IWorkbenchWizard wizard = new PluginExportWizard();

			final StructuredSelection selection = new StructuredSelection(
					profilePlugin);
			wizard.init(PlatformUI.getWorkbench(), selection);
			final WizardDialog wd = new ResizableWizardDialog(
					PDEPlugin.getActiveWorkbenchShell(), wizard);
			wd.create();
			wd.open();

		}
	}

	/**
	 * Initiate the needed parameters for the export of the profile.
	 * 
	 * @param profile
	 *            to export.
	 * @return the pressed button, OK or CANCEL.
	 */
	public Integer initParameters(final Profile profile) {

		if (profile.getName() != null && profile.getName().length() != 0)
			profileName = profile.getName().toLowerCase();
		else
			profileName = defaultProfileName;

		if (profile.getURI() != null && profile.getURI().length() != 0) {
			rootProfileURI = profile.getURI() + separator;
			profilePluginName = rootProfileURI.replace("http://", "").replace(
					separator, ".")
					+ "plugin";
		} else {
			rootProfileURI = obeoNetworkURI + profileName + separator;
			profilePluginName = obeoNetworkPluginName + "." + profileName + "."
					+ "plugin";
		}


		final InitProfilePluginDialog dialog = new InitProfilePluginDialog(
				profileName, rootProfileURI,
				profilePluginName);

		dialog.open();
		if (dialog.getReturnCode() == IDialogConstants.OK_ID) {
			profileName = dialog.getProfileName();
			rootProfileURI = dialog.getRootProfileURI();
			profilePluginName = dialog.getProfilePluginName();
			return IDialogConstants.OK_ID;
		} else
			return IDialogConstants.CANCEL_ID;
	}

	/**
	 * Apply the stereotype EPackage to the profile and fill out the tagged
	 * value.
	 * 
	 * @param profile
	 *            to initiate
	 */
	public void initEPackageStereotype(final Profile profile) {
		final EList<Element> allOwningPackages = profile.allOwnedElements();
		// allOwningPackages.add(profile);
		final Profile ecoreProfile = (Profile) GenericUMLProfileTools.load(URI
				.createURI("pathmap://UML_PROFILES/Ecore.profile.uml"));
		profile.applyProfile(ecoreProfile);
		final Stereotype ePackage = ecoreProfile.getOwnedStereotype("EPackage");
		profile.applyStereotype(ePackage);
		if (profile.getName() != null) {
			profile.setValue(ePackage, "packageName", profile.getName());
			profile.setValue(ePackage, "nsPrefix", profile.getName());
			profile.setValue(ePackage, "prefix", profile.getName());
		}
		// if (profile.getURI() != null && profile.getURI().length() != 0)
		// profile.setValue(ePackage, nsURI, profile.getURI());
		// else {
		// profile.setValue(ePackage, nsURI, computeURI(profile));
		// profile.setURI(computeURI(profile));
		// }
		profile.setValue(ePackage, nsURI, rootProfileURI);
		profile.setURI(rootProfileURI);

		for (Element ownedPackageElement : allOwningPackages) {
			if (ownedPackageElement instanceof Package) {
				final Package ownedPackage = (Package) ownedPackageElement;
				ownedPackage.applyStereotype(ePackage);
				if (ownedPackage.getName() != null) {
					ownedPackage.setValue(ePackage, "packageName", ownedPackage.getName());
					ownedPackage.setValue(ePackage, "nsPrefix", ownedPackage.getName());
					ownedPackage.setValue(ePackage, "prefix", ownedPackage.getName());
				}
				if (ownedPackage.getURI() != null
						&& profile.getURI().length() != 0)
					ownedPackage.setValue(ePackage, nsURI,
							ownedPackage.getURI());
				else {
					ownedPackage.setValue(ePackage, nsURI,
							computeURI(ownedPackage));
					ownedPackage.setURI(computeURI(ownedPackage));
				}
			}
		}
		GenericUMLProfileTools.save(profile);
	}

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
	public void addProfileExtensions(final IProject profilePlugin,
			final IFolder modelFolder, final Profile profileCopy) {
		final String exceptionMsg = "addProfileExtensions("
				+ profilePlugin.getClass() + "," + modelFolder.getClass() + ","
				+ profileCopy.getClass() + ") not handled";
		final IFile pluginXML = profilePlugin.getFile("plugin.xml");

		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (final ParserConfigurationException e) {
			new LogServices().error(exceptionMsg, e);
		}
		org.w3c.dom.Document document = null;
		try {
			document = builder.parse(pluginXML.getLocation().toFile());
		} catch (final SAXException e) {
			new LogServices().error(exceptionMsg, e);
		} catch (final IOException e) {
			new LogServices().error(
exceptionMsg, e);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
		if (document != null) {
			final String xmiIdProfileCopy = profileCopy.eResource()
					.getURIFragment(profileCopy);
			final Node racine = document.getDocumentElement();
			org.w3c.dom.Element extensionForGeneratedPackage = document
					.createElement(extension);
			extensionForGeneratedPackage.setAttribute(point,
					"org.eclipse.uml2.uml.generated_package");
			org.w3c.dom.Element profile = document
					.createElement(defaultProfileName);
			profile.setAttribute("location", platformPlugin
					+ profileCopy.eResource().getURI() + "#"
					+ xmiIdProfileCopy);
			profile.setAttribute("uri", profileCopy.getURI());
			extensionForGeneratedPackage.appendChild(profile);
			racine.appendChild(extensionForGeneratedPackage);
			final EList<Element> allOwnedElements = profileCopy
					.allOwnedElements();
			for (Element ownedPackageElement : allOwnedElements) {
				if (ownedPackageElement instanceof Profile) {
					final Profile ownedProfile = (Profile) ownedPackageElement;

					final String xmiIdownedPackage = ownedProfile.eResource()
							.getURIFragment(ownedProfile);
					extensionForGeneratedPackage = document
							.createElement(extension);
					extensionForGeneratedPackage.setAttribute(point,
							"org.eclipse.uml2.uml.generated_package");
					profile = document.createElement(defaultProfileName);
					profile.setAttribute("location", platformPlugin
							+ ownedProfile.eResource().getURI()
							+ "#" + xmiIdownedPackage);
					profile.setAttribute("uri", ownedProfile.getURI());
					extensionForGeneratedPackage.appendChild(profile);
					racine.appendChild(extensionForGeneratedPackage);

				}
			}
			final org.w3c.dom.Element extensionForUriMapping = document
					.createElement(extension);
			extensionForUriMapping.setAttribute(point,
					"org.eclipse.emf.ecore.uri_mapping");
			final org.w3c.dom.Element mapping = document
					.createElement("mapping");
			mapping.setAttribute("source", "pathmap://MY_PROFILES/");
			mapping.setAttribute("target",
					platformPlugin + modelFolder.getFullPath());
			extensionForUriMapping.appendChild(mapping);

			racine.appendChild(extensionForUriMapping);

			// serialise the document to an xml file.
			Transformer transformer = null;
			try {
				transformer = TransformerFactory.newInstance().newTransformer();
			} catch (TransformerConfigurationException e) {
				new LogServices().error(exceptionMsg, e);
			} catch (TransformerFactoryConfigurationError e) {
				new LogServices().error(exceptionMsg, e);
			}
			Result output = new StreamResult(pluginXML.getLocation().toFile());
			Source input = new DOMSource(document);

			try {
				transformer.transform(input, output);
			} catch (TransformerException e) {
				new LogServices().error(exceptionMsg, e);
			}
		}
	}

	/**
	 * compute the uri of element.
	 * 
	 * @param element
	 *            for which the uri will be computed
	 * @return the uri
	 */
	public String computeURI(final NamedElement element) {
		String uri = null;
		if (element.getOwner() == null && element.getNearestPackage().getURI() != null
				&& element.getNearestPackage().getURI().length() != 0)
			uri = element.getNearestPackage().getURI();
		else if (element.getOwner() != null && element.getOwner().getNearestPackage() != null
				&& element.getOwner().getNearestPackage().getURI() != null)
			uri = element.getOwner().getNearestPackage().getURI()
					+ element.getName() + separator;
		else
			uri = rootProfileURI;

		return uri;
	}
}
