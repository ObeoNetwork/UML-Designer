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
package org.obeonetwork.dsl.uml2.design.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.metamodel.spec.DSemanticDiagramSpec;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.Device;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.ExecutionEnvironment;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.FlowFinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Manifestation;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.services.internal.ReconnectSwitch;
import org.obeonetwork.dsl.uml2.design.services.internal.RelatedCompositeStructureElementsSwitch;
import org.obeonetwork.dsl.uml2.design.services.internal.RelatedElementsSwitch;
import org.obeonetwork.dsl.uml2.design.services.internal.SemanticElementsSwitch;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Services for UML.
 * 
 * @author Cedric Brun <a href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane .thibaudeau@obeo.fr</a>
 * @author Melanie Bats<a href="mailto:melanie.bats@obeo.fr">melanie .bats@obeo.fr</a>
 */
public class UMLServices {

	private static final String ROLE = "Role";

	private static final String THING = "Thing";

	private static final String DESCRIPTION = "Description";

	private static final String MOMENT_INTERVAL = "MomentInterval";

	public void openContextHelp(EObject any, String contextID) throws IOException {
		if (PlatformUI.getWorkbench() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
				&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell() != null) {
			IPath path = Platform.getStateLocation(UMLDesignerPlugin.getDefault().getBundle());
			if (path != null) {
				path = path.append(contextID);
				if (!path.toFile().exists()) {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					PlatformUI
							.getWorkbench()
							.getHelpSystem()
							.setHelp(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
									contextID);
					PlatformUI.getWorkbench().getHelpSystem().displayDynamicHelp();
					path.toFile().createNewFile();
				}
			}

		}
	}

	public Package applyAllProfiles(Package packagge, List<Profile> profilesToApply) {
		// Unapplying not selected profiles
		List<Profile> alreadyAppliedProfiles = packagge.getAppliedProfiles();
		for (Profile alreadyAppliedProfile : alreadyAppliedProfiles) {
			if (!profilesToApply.contains(alreadyAppliedProfile)) {
				packagge.unapplyProfile(alreadyAppliedProfile);
			}
		}
		// Aplying selected profiles
		for (Profile profileToApply : profilesToApply) {
			if (!packagge.isProfileApplied(profileToApply)) {
				packagge.applyProfile(profileToApply);
			}
		}
		return packagge;
	}

	/**
	 * Retrieve the cross references of the given type of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @param typeName
	 *            the expected type.
	 * @return the list of cross reference of the given
	 */
	private Collection<EObject> getNodeInverseRefs(DDiagram diagram, String typeName) {
		Set<EObject> result = Sets.newLinkedHashSet();
		if (diagram instanceof DSemanticDecorator) {
			Session sess = SessionManager.INSTANCE.getSession(((DSemanticDecorator)diagram).getTarget());

			Iterator<EObject> it = Iterators.transform(
					Iterators.filter(diagram.eAllContents(), AbstractDNode.class),
					new Function<AbstractDNode, EObject>() {

						public EObject apply(AbstractDNode input) {
							return input.getTarget();
						}
					});
			while (it.hasNext()) {
				EObject displayedAsANode = it.next();
				if (displayedAsANode != null) {
					for (Setting xRef : sess.getSemanticCrossReferencer().getInverseReferences(
							displayedAsANode)) {
						EObject eObject = xRef.getEObject();
						if (sess.getModelAccessor().eInstanceOf(eObject, typeName)) {
							result.add(eObject);
						}
						/*
						 * In the case of an association the real interesting object is the association linked
						 * to the Property and not the direct cross reference.
						 */
						if (eObject instanceof Property) {
							if (((Property)eObject).getAssociation() != null) {
								if (sess.getModelAccessor().eInstanceOf(((Property)eObject).getAssociation(),
										typeName)) {
									result.add(((Property)eObject).getAssociation());
								}
							}

						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Retrieve the cross references of the association of all the UML elements displayed as node in a
	 * Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getAssociationInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Association");
	}

	/**
	 * Retrieve the cross references of the component realization of all the UML elements displayed as node in
	 * a Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getComponentRealizationInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "ComponentRealization");
	}

	/**
	 * Retrieve the cross references of the connector of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getConnectorInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Connector");
	}

	/**
	 * Retrieve the cross references of the dependency of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getDependencyInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Dependency");
	}

	/**
	 * Retrieve the cross references of the dependency of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getDependencyOnlyInverseRefs(DDiagram diagram) {
		final Predicate<EObject> predicate = new Predicate<EObject>() {
			public boolean apply(EObject eObj) {
				String className = eObj.eClass().getName();
				return "Dependency".equals(className);
			}
		};
		return Collections2.filter(getDependencyInverseRefs(diagram), predicate);
	}

	/**
	 * Retrieve the cross references of the dependency of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getDependencyOrManifestationInverseRefs(DDiagram diagram) {
		final Predicate<EObject> predicate = new Predicate<EObject>() {
			public boolean apply(EObject eObj) {
				String className = eObj.eClass().getName();
				return "Dependency".equals(className) || "Manifestation".equals(className);
			}
		};
		return Collections2.filter(getDependencyInverseRefs(diagram), predicate);
	}

	/**
	 * Retrieve the cross references of the deployment of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getDeploymentInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Deployment");
	}

	/**
	 * Retrieve the cross references of the extend of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getExtendInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Extend");
	}

	/**
	 * Retrieve the cross references of the generalization of all the UML elements displayed as node in a
	 * Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getGeneralizationInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Generalization");
	}

	/**
	 * Retrieve the cross references of the include of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getIncludeInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Include");
	}

	/**
	 * Retrieve the cross references of the interface realization of all the UML elements displayed as node in
	 * a Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getInterfaceRealizationInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "InterfaceRealization");
	}

	/**
	 * Retrieve the cross references of the slot of all the UML elements displayed as node in a Diagram. Note
	 * that a Property cross reference will lead to retrieve the cross references of this property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getSlotInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Slot");
	}

	/**
	 * Retrieve the cross references of the template binding of all the UML elements displayed as node in a
	 * Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getTemplateBindingInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "TemplateBinding");
	}

	/**
	 * Retrieve the cross references of the usage of all the UML elements displayed as node in a Diagram. Note
	 * that a Property cross reference will lead to retrieve the cross references of this property.
	 * 
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getUsageInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Usage");
	}

	public Element applyAllStereotypes(Element element, List<Stereotype> stereotypesToApply) {
		// Unapplying not selected stereotypes
		List<Stereotype> alreadyAppliedStereotypes = element.getAppliedStereotypes();
		for (Stereotype alreadyAppliedStereotype : alreadyAppliedStereotypes) {
			if (stereotypesToApply == null || !stereotypesToApply.contains(alreadyAppliedStereotype)) {
				element.unapplyStereotype(alreadyAppliedStereotype);
			}
		}

		if (stereotypesToApply == null) {
			return element;
		}

		// Applying selected stereotypes
		for (Stereotype stereotypeToApply : stereotypesToApply) {
			if (!element.isStereotypeApplied(stereotypeToApply)) {
				element.applyStereotype(stereotypeToApply);
			}
		}
		return element;
	}

	/**
	 * Unapply all the stereotypes of a given element.
	 * 
	 * @param element
	 *            Element
	 * @return Element
	 */
	public Element unApplyAllStereotypes(Element element) {
		return applyAllStereotypes(element, null);
	}

	/**
	 * return the list of semantic elements we should bind with the given element in the property view.
	 * 
	 * @param e
	 *            a semantic element.
	 * @return the list of semantic elements we should bind with the given element in the property view.
	 */
	public Collection<EObject> getSemanticElements(EObject e) {
		return new SemanticElementsSwitch().getSemanticElements(e);
	}

	/**
	 * Returns a human readable string for the specified visibility.
	 * 
	 * @param visibilityString
	 *            the visibility.
	 * @return a human readable string for the specified visibility.
	 */
	public String visibilityToString(String visibilityString) {
		final VisibilityKind visibilityKind = VisibilityKind.getByName(visibilityString);
		String str = "";
		switch (visibilityKind.getValue()) {
			case VisibilityKind.PRIVATE:
				str = "-";
				break;
			case VisibilityKind.PUBLIC:
				str = "+";
				break;
			case VisibilityKind.PROTECTED:
				str = "#";
				break;
			case VisibilityKind.PACKAGE:
			default:
				break;
		}
		return str;
	}

	/**
	 * Import the package containing default UML primitive types.
	 * 
	 * @param namespace
	 *            Namespace into which importing the types
	 */
	public void importUmlPrimitiveTypes(NamedElement element) {
		importPrimitiveTypes(element, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if the default UML primitive types are already imported.
	 * 
	 * @param namespace
	 *            Namespace to be checked
	 * @return true if the types are already imported, else false
	 */
	public boolean areUmlPrimitiveTypesNotImported(NamedElement element) {
		return !arePrimitiveTypesImported(element, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Import the package containing default Java primitive types.
	 * 
	 * @param namespace
	 *            Namespace into which importing the types
	 */
	public void importJavaPrimitiveTypes(NamedElement element) {
		importPrimitiveTypes(element, UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if the default Java primitive types are already imported.
	 * 
	 * @param namespace
	 *            Namespace to be checked
	 * @return true if the types are already imported, else false
	 */
	public boolean areJavaPrimitiveTypesNotImported(NamedElement element) {
		return !arePrimitiveTypesImported(element, UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Import the package containing default Ecore primitive types.
	 * 
	 * @param namespace
	 *            Namespace into which importing the types
	 */
	public void importEcorePrimitiveTypes(NamedElement element) {
		importPrimitiveTypes(element, UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if the default Ecore primitive types are already imported.
	 * 
	 * @param namespace
	 *            Namespace to be checked
	 * @return true if the types are already imported, else false
	 */
	public boolean areEcorePrimitiveTypesNotImported(NamedElement element) {
		return !arePrimitiveTypesImported(element, UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Import the package containing default XML primitive types.
	 * 
	 * @param namespace
	 *            Namespace into which importing the types
	 */
	public void importXmlPrimitiveTypes(NamedElement element) {
		importPrimitiveTypes(element, UMLResource.XML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if the default XML primitive types are already imported.
	 * 
	 * @param namespace
	 *            Namespace to be checked
	 * @return true if the types are already imported, else false
	 */
	public boolean areXmlPrimitiveTypesNotImported(NamedElement element) {
		return !arePrimitiveTypesImported(element, UMLResource.XML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Loads & import library into the {@link Namespace}.
	 * 
	 * @param namespace
	 *            the {@link Namespace} context
	 * @param libraryUri
	 *            the URI of the library to load.
	 */
	private void importPrimitiveTypes(NamedElement element, String libraryUri) {
		Namespace namespace = getNamespace(element);
		final ResourceSet resourceSet = namespace.eResource().getResourceSet();
		final Resource resource = resourceSet.getResource(URI.createURI(libraryUri), true);
		// Add the resource to the session's semantic resources
		final Session session = SessionManager.INSTANCE.getSession(namespace);
		if (session != null) {
			session.addSemanticResource(resource.getURI(), new NullProgressMonitor());
		}
		final Package root = (Package)EcoreUtil.getObjectByType(resource.getContents(),
				UMLPackage.Literals.PACKAGE);
		// We check if a package import already exists
		if (!namespace.getImportedPackages().contains(root)) {
			namespace.createPackageImport(root);
		}
	}

	/**
	 * Indicates if a type name is a UML primitive type.
	 * 
	 * @param namespace
	 *            the {@link Namespace} context
	 * @param typeName
	 *            Type name
	 * @return True if type name is a primitive type otherwise false
	 */
	public boolean isUmlPrimitiveType(Namespace namespace, String typeName) {
		return isPrimitiveType(namespace, typeName, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Indicates if a type name is an Ecore primitive type.
	 * 
	 * @param namespace
	 *            the {@link Namespace} context
	 * @param typeName
	 *            Type name
	 * @return True if type name is a primitive type otherwise false
	 */
	public boolean isEcorePrimitiveType(Namespace namespace, String typeName) {
		return isPrimitiveType(namespace, typeName, UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Indicates if a type name is a Java primitive type.
	 * 
	 * @param namespace
	 *            the {@link Namespace} context
	 * @param typeName
	 *            Type name
	 * @return True if type name is a primitive type otherwise false
	 */
	public boolean isJavaPrimitiveType(Namespace namespace, String typeName) {
		return isPrimitiveType(namespace, typeName, UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Indicates if a type name is an XML primitive type.
	 * 
	 * @param namespace
	 *            the {@link Namespace} context
	 * @param typeName
	 *            Type name
	 * @return True if type name is a primitive type otherwise false
	 */
	public boolean isXmlPrimitiveType(Namespace namespace, String typeName) {
		return isPrimitiveType(namespace, typeName, UMLResource.XML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Indicates if a type name is a primitive type.
	 * 
	 * @param namespace
	 *            the {@link Namespace} context
	 * @param typeName
	 *            Type name
	 * @param libraryUri
	 *            the URI of the library to load.
	 * @return True if type name is a primitive type otherwise false
	 */
	private boolean isPrimitiveType(Namespace namespace, String typeName, String libraryUri) {
		final ResourceSet resourceSet = namespace.eResource().getResourceSet();
		final Resource resource = resourceSet.getResource(URI.createURI(libraryUri), true);
		// Add the resource to the session's semantic resources
		final Package root = (Package)EcoreUtil.getObjectByType(resource.getContents(),
				UMLPackage.Literals.PACKAGE);
		// Search the type and sets it on the property if found
		if (typeName != null && !"".equals(typeName)) {
			final Type foundType = EcoreServices.INSTANCE.findTypeByName(root, typeName);
			if (foundType != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Import a package.
	 * 
	 * @param model
	 *            Model
	 * @param pkg
	 *            Package to import in model
	 */
	public void importPackage(Model model, Package pkg) {
		// Add the resource to the session's semantic resources
		Resource resource = pkg.eResource();
		final Session session = SessionManager.INSTANCE.getSession(model);
		if (session != null) {
			session.addSemanticResource(resource.getURI(), new NullProgressMonitor());
		}
		// We check if a package import already exists
		if (!model.getImportedPackages().contains(pkg)) {
			model.createPackageImport(pkg);
		}
	}

	public void deletePackage(Model model, Package pkg) {
		// Check if package is an imported package
		List<PackageImport> copy = new ArrayList<PackageImport>();
		copy.addAll(model.getPackageImports());
		for (PackageImport pkgImport : copy) {
			if (pkg.equals(pkgImport.getImportedPackage())) {
				model.getPackageImports().remove(pkgImport);
				return;
			}
		}
		pkg.destroy();
	}

	public List<EObject> getValidsForPackageDiagram(EObject cur) {
		Predicate<EObject> validForPackageDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				return input instanceof Package;
			}
		};
		return allValidSessionElements(cur, validForPackageDiagram);
	}

	private List<EObject> getValidsForClassDiagram(EObject cur) {
		Predicate<EObject> validForClassDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				return input instanceof Package || input instanceof Interface || input instanceof DataType
						|| "Class".equals(input.eClass().getName())
						|| "Component".equals(input.eClass().getName());
			}
		};
		return allValidSessionElements(cur, validForClassDiagram);
	}

	private List<EObject> getValidsForUseCaseDiagram(EObject cur) {
		Predicate<EObject> validForUseCaseDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				return input instanceof Package || input instanceof Class || input instanceof Component
						|| input instanceof Artifact || input instanceof DataType
						|| input instanceof Interface || input instanceof Collaboration
						|| input instanceof UseCase;
			}
		};
		return allValidSessionElements(cur, validForUseCaseDiagram);
	}

	public List<EObject> getValidsForComponentDiagram(EObject cur) {
		Predicate<EObject> validForComponentDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				return input instanceof Package || input instanceof Interface
						|| "Class".equals(input.eClass().getName())
						|| "Component".equals(input.eClass().getName());
			}
		};
		return allValidSessionElements(cur, validForComponentDiagram);
	}

	private List<EObject> getValidsForObjectDiagram(EObject cur) {
		Predicate<EObject> validForObjetDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				boolean result = false;
				if (input instanceof InstanceSpecification || input instanceof Classifier) {
					result = true;
				} else if (input instanceof Package) {
					// A package is interesting only if it contains a Classifier or an InstanceSpecification
					TreeIterator<EObject> eAllContents = input.eAllContents();
					while (eAllContents.hasNext()) {
						EObject eObject = (EObject)eAllContents.next();
						if (eObject instanceof InstanceSpecification || eObject instanceof Classifier) {
							result = true;
							break;
						}
					}
				}
				return result;
			}
		};
		return allValidSessionElements(cur, validForObjetDiagram);
	}

	public List<EObject> getValidsForCompositeDiagram(EObject cur) {
		Predicate<EObject> validForCompositeDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				if (input instanceof StructuredClassifier) {
					return !(input instanceof Interaction || input instanceof StateMachine || input instanceof Activity);
				} else {
					return input instanceof Package || input instanceof Interface
							|| "Port".equals(input.eClass().getName())
							|| "Property".equals(input.eClass().getName());
				}

			}
		};
		return allValidSessionElements(cur, validForCompositeDiagram);
	}

	private List<EObject> getValidsForDeploymentDiagram(EObject cur) {
		Predicate<EObject> validForDeploymentDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				return input instanceof Package || input instanceof ExecutionEnvironment
						|| input instanceof Node || input instanceof Artifact || input instanceof Device;
			}
		};
		return allValidSessionElements(cur, validForDeploymentDiagram);
	}

	private List<EObject> allValidSessionElements(EObject cur, Predicate<EObject> validForClassDiagram) {
		Session found = SessionManager.INSTANCE.getSession(cur);
		List<EObject> result = Lists.newArrayList();
		if (found != null) {
			for (Resource res : found.getSemanticResources()) {
				if (res.getURI().isPlatformResource() || res.getURI().isPlatformPlugin()) {
					Iterators.addAll(result, Iterators.filter(res.getAllContents(), validForClassDiagram));
				}
			}
		}
		return result;
	}

	@SuppressWarnings("restriction")
	public List<EObject> getValidsForDiagram(final EObject element, final DSemanticDecorator containerView) {
		// Get representation
		DRepresentation representation = null;
		if (containerView instanceof DRepresentation) {
			representation = (DRepresentation)containerView;
		} else if (containerView instanceof DDiagramElement) {
			representation = ((DDiagramElement)containerView).getParentDiagram();
		}
		UMLServices service = new UMLServices();
		List<EObject> results = null;
		if (representation instanceof DSemanticDiagramSpec) {
			DiagramDescription description = ((DSemanticDiagramSpec)representation).getDescription();

			if ("Class Diagram".equals(description.getName())
					|| "Profile Diagram".equals(description.getName())) {
				results = service.getValidsForClassDiagram(element);
			} else if ("Component Diagram".equals(description.getName())) {
				results = service.getValidsForComponentDiagram(element);
			} else if ("Composite Structure Diagram".equals(description.getName())) {
				results = service.getValidsForCompositeDiagram(element);
			} else if ("Composite Structure Diagram".equals(description.getName())) {
				results = service.getValidsForCompositeDiagram(element);
			} else if ("Deployment Diagram".equals(description.getName())) {
				results = service.getValidsForDeploymentDiagram(element);
			} else if ("Object Diagram".equals(description.getName())) {
				results = service.getValidsForObjectDiagram(element);
			} else if ("Package Diagram".equals(description.getName())) {
				results = service.getValidsForPackageDiagram(element);
			} else if ("Use Case Diagram".equals(description.getName())) {
				results = service.getValidsForUseCaseDiagram(element);
			}
		}

		return results;
	}

	/**
	 * Get all imported packages
	 * 
	 * @param model
	 *            Model
	 * @return Imported packages
	 */
	public List<Package> getImportedPackages(Model model) {
		return model.getImportedPackages();
	}

	/**
	 * Check if the given library is already imported in the given {@link Namespace}.
	 * 
	 * @param namespace
	 *            the {@link Namespace} context to inspect
	 * @param libraryUri
	 *            URI of the library.
	 * @return <code>true</code> if the library is imported, <code>false</code> otherwise
	 */
	private boolean arePrimitiveTypesImported(NamedElement element, String libraryUri) {
		Namespace namespace = getNamespace(element);
		final ResourceSet resourceSet = namespace.eResource().getResourceSet();
		final Resource resource = resourceSet.getResource(URI.createURI(libraryUri), true);
		final Package root = (Package)EcoreUtil.getObjectByType(resource.getContents(),
				UMLPackage.Literals.PACKAGE);
		// We check if a package import already exists
		return namespace.getImportedPackages().contains(root);
	}

	/**
	 * Get the namespace associated to a named element.
	 * 
	 * @param element
	 *            Named element
	 * @return Namespace, if the element is a model or a profile the namespace is itself
	 */
	private Namespace getNamespace(NamedElement element) {
		if (element instanceof Model || element instanceof Profile) {
			return (Namespace)element;
		}
		return element.getNamespace();
	}

	/**
	 * Return the source of an association.
	 * 
	 * @param association
	 *            the {@link Association} context
	 * @return first end of the association
	 */
	private Property getSource(Association association) {
		if (association.getMemberEnds() != null && association.getMemberEnds().size() > 0) {
			return association.getMemberEnds().get(0);
		}
		return null;
	}

	/**
	 * Return the target of an association.
	 * 
	 * @param association
	 *            the {@link Association} context
	 * @return second end of the association
	 */
	private Property getTarget(Association association) {
		if (association.getMemberEnds() != null && association.getMemberEnds().size() > 1) {
			return association.getMemberEnds().get(1);
		}
		return null;
	}

	public Collection<EObject> getRelated(EObject cur) {
		return new RelatedElementsSwitch().getRelatedElements(cur);
	}

	public Collection<EObject> getRelatedForCompositeStructure(EObject cur) {
		return new RelatedCompositeStructureElementsSwitch().getRelatedElements(cur);
	}

	public Collection<EObject> getRelatedForUseCase(EObject cur) {
		if (!(cur instanceof UseCase || cur instanceof Actor)) {
			List result = ImmutableList.copyOf(Iterables.filter(getRelated(cur), new Predicate<EObject>() {

				public boolean apply(EObject input) {
					return input instanceof UseCase || input instanceof Actor;
				}
			}));
			return result;
		}
		return getRelated(cur);
	}

	/**
	 * Indicates if a property has "shared" as its aggregation kind.
	 * 
	 * @param p
	 *            property to test
	 * @return boolean
	 */
	public boolean isShared(Property p) {
		return AggregationKind.SHARED_LITERAL.equals(p.getAggregation());
	}

	/**
	 * Indicates if a property has "composite" as its aggregation kind.
	 * 
	 * @param p
	 *            property to test
	 * @return boolean
	 */
	public boolean isComposite(Property p) {
		return AggregationKind.COMPOSITE_LITERAL.equals(p.getAggregation());
	}

	public boolean isNotComposite(Property p) {
		return !isComposite(p);
	}

	public boolean isRelated(EObject toFilter, List<EObject> context) {
		boolean related = false;
		for (EObject eObject : context) {
			related = isRelated(toFilter, eObject);
			if (related) {
				break;
			}
		}
		return related;
	}

	/**
	 * States if the given object is related to the context {@link Classifier}.
	 * 
	 * @param toFilter
	 *            the candidate to check for relation
	 * @param context
	 *            the classifier context object.
	 * @return <code>true</code> if the given object is related to the context {@link Classifier},
	 *         <code>false</code> otherwise.
	 */
	public boolean isRelated(EObject toFilter, EObject context) {
		boolean res = false;
		if (toFilter.equals(context)) {
			res = true;
		} else if (context instanceof Classifier) {
			if (toFilter instanceof Generalization) {
				res = ((Classifier)context).getGeneralizations().contains(toFilter)
						|| ((Generalization)toFilter).getGeneral() == context;
			} else if (toFilter instanceof InterfaceRealization && context instanceof Class) {
				res = ((Class)context).getInterfaceRealizations().contains(toFilter)
						|| ((InterfaceRealization)toFilter).getContract() == context;
			} else if (toFilter instanceof Association) {
				res = ((Classifier)context).getAssociations().contains(toFilter);
			} else if (toFilter instanceof Artifact && context instanceof ExecutionEnvironment) {
				for (Deployment deployment : ((ExecutionEnvironment)context).getDeployments()) {
					if (deployment.getSuppliers().contains(toFilter))
						res = true;
				}
			} else if (toFilter instanceof ExecutionEnvironment && context instanceof Artifact) {
				for (Deployment deployment : ((ExecutionEnvironment)toFilter).getDeployments()) {
					if (deployment.getSuppliers().contains(context))
						res = true;
				}
			} else if (toFilter instanceof PackageableElement && context instanceof Artifact) {
				res = ((Artifact)context).getManifestations().contains(toFilter);
				for (Manifestation manifestation : ((Artifact)context).getManifestations()) {
					if (manifestation.getTargets().contains(toFilter))
						res = true;
				}
			} else if (toFilter instanceof Artifact && context instanceof PackageableElement) {
				res = ((Artifact)toFilter).getManifestations().contains(context);
				for (Manifestation manifestation : ((Artifact)toFilter).getManifestations()) {
					if (manifestation.getTargets().contains(context))
						res = true;
				}
			} else if (toFilter instanceof Feature) {
				res = isRelated(toFilter.eContainer(), context);
			} else if (toFilter instanceof Classifier) {
				if (context == toFilter)
					return false;
				res = context == toFilter;

				if (context instanceof EncapsulatedClassifier && toFilter instanceof EncapsulatedClassifier) {
					if (!res) {
						if (((EncapsulatedClassifier)context).getOwnedElements().contains(toFilter)) {
							res = true;
						} else {
							for (Port portContext : ((EncapsulatedClassifier)context).getOwnedPorts()) {
								if (portIsRelated(toFilter, portContext)) {
									res = true;
									break;
								}
							}
						}
					}
				}

				if (context instanceof EncapsulatedClassifier && toFilter instanceof Property) {
					if (!res) {
						if (((EncapsulatedClassifier)context).getOwnedAttributes().contains(toFilter)) {
							res = true;
						}
					}
				}

				// is it a generalization end
				if (!res) {
					for (Generalization generalization : ((Classifier)context).getGeneralizations()) {
						if (generalization.getGeneral() == toFilter) {
							res = true;
							break;
						}
					}
				}
				// is it a generalization opposite end
				if (!res) {
					for (Generalization generalization : ((Classifier)toFilter).getGeneralizations()) {
						if (generalization.getGeneral() == context) {
							res = true;
							break;
						}
					}
				}
				if (toFilter instanceof NamedElement && context instanceof NamedElement) {
					// is it a dependency end
					if (!res) {
						for (Dependency dependency : ((NamedElement)context).getClientDependencies()) {
							if (dependency.getClients().contains(toFilter)
									|| dependency.getSuppliers().contains(toFilter)) {
								res = true;
								break;
							}
						}
					}
				}
				if (context instanceof NamedElement && toFilter instanceof NamedElement) {
					// is it a dependency end
					if (!res) {
						for (Dependency dependency : ((NamedElement)toFilter).getClientDependencies()) {
							if (dependency.getClients().contains(context)
									|| dependency.getSuppliers().contains(context)) {
								res = true;
								break;
							}
						}
					}
				}
				if (toFilter instanceof Interface && context instanceof Class) {
					// is it a realization end
					if (!res) {
						for (InterfaceRealization realization : ((Class)context).getInterfaceRealizations()) {
							if (realization.getContract() == toFilter) {
								res = true;
								break;
							}
						}
					}
				}
				if (context instanceof Interface && toFilter instanceof Class) {
					// is it a realization end
					if (!res) {
						for (InterfaceRealization realization : ((Class)toFilter).getInterfaceRealizations()) {
							if (realization.getContract() == context) {
								res = true;
								break;
							}
						}
					}
				}

				// is it an association end
				if (!res) {
					final List<Association> toFilterAsso = ((Classifier)toFilter).getAssociations();
					final List<Association> contextAsso = ((Classifier)context).getAssociations();
					for (Association association : toFilterAsso) {
						if (contextAsso.contains(association)) {
							res = true;
							break;
						}
					}
				}
			} else if (toFilter instanceof Package) {
				for (EObject content : toFilter.eContents()) {
					if (isRelated(content, context)) {
						res = true;
						break;
					}
				}
			}
		} else if (context instanceof Package) {
			if (toFilter instanceof Package) {
				res = ((Package)context).getNestedPackages().contains(toFilter)
						|| ((Package)context).getImportedPackages().contains(toFilter);
				for (PackageImport packageImport : ((Package)context).getPackageImports()) {
					if (packageImport.getImportedPackage().equals(toFilter))
						res = true;
				}
			} else {
				res = ((Package)context).getOwnedElements().contains(toFilter);
			}
		} else if (context instanceof Port) {
			res = portIsRelated(toFilter, (Port)context);
		}

		return res;
	}

	private boolean portIsRelated(EObject toFilter, Port portContext) {

		if (portContext == toFilter)
			return false;

		if (toFilter instanceof Port) {
			List<ConnectorEnd> ends = ((Port)portContext).getEnds();
			for (ConnectorEnd portEnd : ends) {
				EObject eContainer = portEnd.eContainer();
				if (eContainer instanceof Connector) {
					Connector connector = (Connector)eContainer;
					EList<ConnectorEnd> connectorEnds = connector.getEnds();
					for (ConnectorEnd connectorEnd : connectorEnds) {
						if (connectorEnd.getRole() != null && connectorEnd.getRole().equals(toFilter)) {
							return true;
						}
					}
				}
			}
		} else if (toFilter instanceof EncapsulatedClassifier) {
			List<Port> ownedPortsToFilter = ((EncapsulatedClassifier)toFilter).getOwnedPorts();
			for (Port portToFilter : ownedPortsToFilter) {
				if (portIsRelated(portToFilter, portContext)) {
					return true;
				}
			}
		}

		return false;
	}

	private Predicate<EObject> classifierLike = new Predicate<EObject>() {

		public boolean apply(EObject eObj) {
			String className = eObj.eClass().getName();
			return "Class".equals(className) || "AssociationClass".equals(className)
					|| "Interface".equals(className) || "Enumeration".equals(className)
					|| "PrimitiveType".equals(className) || "DataType".equals(className);
		}
	};

	public Set<EObject> getOwnedClassifiersLike(Package pak) {
		Set<EObject> result = Sets.newLinkedHashSet();
		for (EObject eObject : getDirectContent(pak)) {
			result.add(eObject);
			result.addAll(Lists.newArrayList(Iterators.filter(eObject.eAllContents(), classifierLike)));
		}
		for (Component comp : Iterables.filter(pak.eContents(), Component.class)) {
			result.addAll(Lists.newArrayList(Iterators.filter(comp.eAllContents(), classifierLike)));
		}
		return result;
	}

	private Collection<EObject> getDirectContent(EObject pak) {
		return Collections2.filter(pak.eContents(), classifierLike);
	}

	public Set<EObject> getExternalClassifiersLike(Package pak) {
		Set<EObject> valids = Sets.newLinkedHashSet();
		Session sess = SessionManager.INSTANCE.getSession(pak);
		if (sess != null) {
			for (Resource rootResource : sess.getSemanticResources()) {
				Iterator<EObject> it = Iterators.filter(rootResource.getAllContents(), classifierLike);
				while (it.hasNext()) {
					EObject cur = it.next();
					valids.add(cur);
				}
			}
		}
		return Sets.difference(valids, getOwnedClassifiersLike(pak));
	}

	/**
	 * Generic service used to process treatments on a reconnect The processing has to be defined by
	 * overriding the corresponding caseXXX.
	 * 
	 * @param context
	 *            Element attached to the existing edge
	 * @param edgeView
	 *            Represents the graphical new edge
	 * @param sourceView
	 *            Represents the graphical element pointed by the edge before reconnecting
	 * @param targetView
	 *            Represents the graphical element pointed by the edge after reconnecting
	 * @param source
	 *            Represents the semantic element pointed by the edge before reconnecting
	 * @param target
	 *            Represents the semantic element pointed by the edge after reconnecting
	 * @return the Element attached to the edge once it has been modified
	 */
	public Element reconnectEdge(Element context, DEdge edgeView, EdgeTarget sourceView,
			EdgeTarget targetView, Element source, Element target) {
		final ReconnectSwitch reconnectService = new ReconnectSwitch();

		// The edgeview represents the new graphical edge
		// with testing of its source and target nodes we can
		// know if the user reconnected the source or the target of the edge
		if (edgeView.getSourceNode().equals(targetView)) {
			reconnectService.setReconnectKind(ReconnectSwitch.RECONNECT_SOURCE);
		} else {
			reconnectService.setReconnectKind(ReconnectSwitch.RECONNECT_TARGET);
		}
		reconnectService.setOldPointedElement(source);
		reconnectService.setNewPointedElement(target);
		return reconnectService.doSwitch(context);
	}

	/**
	 * Check if a reconnect is possible and is not involving creating a cycle in the model.
	 * 
	 * @param host
	 *            the current element.
	 * @param source
	 *            potential source of the edge.
	 * @param target
	 *            potential target of the edge
	 * @param element
	 *            element represented by the edge.
	 * @return true if no cycle is detected.
	 */
	public Boolean reconnectContainmentPrecondition(Element host, Element source, Element target,
			Element element) {
		if (element == target)
			return false;
		Iterator<EObject> it = element.eAllContents();
		while (it.hasNext()) {
			EObject child = it.next();
			if (child == target)
				return false;
		}
		return true;
	}

	/**
	 * Get all the associations relative to an element.
	 * 
	 * @param container
	 *            the current container.
	 * @return a list of association
	 */
	public Collection<Association> getAllAssociations(Classifier classifier) {
		Collection<Association> result = new ArrayList<Association>();
		for (EObject child : classifier.getRelationships()) {
			if (child instanceof Association && !(child instanceof AssociationClass)) {
				result.add((Association)child);
			}
		}
		return result;
	}

	/**
	 * @param container
	 *            the current container.
	 * @return a list of association which might be considered as "broken", we are not able to display them as
	 *         edges.
	 */
	public Collection<Association> getBrokenAssociations(EObject container) {
		Collection<Association> result = new ArrayList<Association>();
		for (EObject child : container.eContents()) {
			if (child instanceof Association && !(child instanceof AssociationClass)
					&& isBroken((Association)child)) {
				result.add((Association)child);
			}
		}
		return result;

	}

	private boolean isBroken(Association child) {
		Property target = getTarget(child);
		Property source = getSource(child);
		if (target != null && target.getType() != null) {
			if (source != null && source.getType() != null)
				return false;
		}
		return true;
	}

	public void fixAssociation(EObject host, EObject a, EObject b) {
		if (a instanceof Association && b instanceof Type) {
			fixAssociation((Association)a, (Type)b);
		} else if (b instanceof Association && a instanceof Type) {
			fixAssociation((Association)b, (Type)a);
		}
	}

	private void fixAssociation(Association a, Type b) {
		Property target = getTarget(a);
		Property source = getSource(a);
		String name = "";
		Property newOne = UMLFactory.eINSTANCE.createProperty();
		newOne.setName(name);
		newOne.setUpper(-1);
		newOne.setUpper(0);
		newOne.setType(b);
		if (a.getName() != null) {
			name = a.getName().toLowerCase();
		}
		if (target == null) {
			a.getOwnedEnds().add(newOne);
		} else if (source == null) {
			a.getOwnedEnds().add(newOne);
		} else {
			/*
			 * we already have both property ends, we just need to set the type
			 */
			if (target.getType() == null) {
				target.setType(b);
			} else if (source.getType() == null) {
				source.setType(b);
			}
		}

	}

	public boolean isValidForEdgeMapping(AbstractDNode sourceView, AbstractDNode targetView) {
		return sourceView != targetView;
	}

	public void addArchetypeKeyword(Class clazz, String archetype) {
		clearArchetypesKeywords(clazz);
		clazz.addKeyword(archetype);
	}

	private void clearArchetypesKeywords(Class clazz) {
		clazz.removeKeyword(MOMENT_INTERVAL);
		clazz.removeKeyword(DESCRIPTION);
		clazz.removeKeyword(THING);
		clazz.removeKeyword(ROLE);
	}

	public boolean isMomentInterval(EObject clazz) {
		return hasKeyword(clazz, MOMENT_INTERVAL);
	}

	public boolean isDescription(EObject clazz) {
		return hasKeyword(clazz, DESCRIPTION);
	}

	public boolean isThing(EObject clazz) {
		return hasKeyword(clazz, THING);
	}

	public boolean isRole(EObject clazz) {
		return hasKeyword(clazz, ROLE);
	}

	private boolean hasKeyword(EObject clazz, String keyword) {
		if (clazz instanceof Class) {
			return ((Class)clazz).getKeywords().contains(keyword);
		}
		return false;
	}

	public boolean isInvalidArchetypesLink(Association association) {
		EList<Property> ends = association.getMemberEnds();
		Property end1 = ends.get(0);
		Property end2 = ends.get(1);
		Type end1Type = end1.getType();
		Type end2Type = end2.getType();

		if ((isDescription(end1Type) && isRole(end2Type)) || (isDescription(end2Type) && isRole(end1Type))) {
			return true;
		} else if ((isDescription(end1Type) && isMomentInterval(end2Type))
				|| (isDescription(end2Type) && isMomentInterval(end1Type))) {
			return true;
		} else if ((isThing(end1Type) && isMomentInterval(end2Type))
				|| (isThing(end2Type) && isMomentInterval(end1Type))) {
			return true;
		} else if ((isRole(end1Type) && isDescription(end2Type))
				|| (isRole(end2Type) && isDescription(end1Type))) {
			return true;
		} else if ((isRole(end1Type) && isRole(end2Type)) || (isRole(end2Type) && isRole(end1Type))) {
			return true;
		} else if ((isMomentInterval(end1Type) && isDescription(end2Type))
				|| (isMomentInterval(end2Type) && isDescription(end1Type))) {
			return true;
		} else if ((isMomentInterval(end1Type) && isThing(end2Type))
				|| (isMomentInterval(end2Type) && isThing(end1Type))) {
			return true;
		} else if ((isDescription(end1Type) && isThing(end2Type) && !end1.getAggregation().equals(
				AggregationKind.NONE_LITERAL))
				|| (isDescription(end2Type) && isThing(end1Type) && !end2.getAggregation().equals(
						AggregationKind.NONE_LITERAL))) {
			return true;
		} else if ((isThing(end1Type) && isRole(end2Type) && !end1.getAggregation().equals(
				AggregationKind.NONE_LITERAL))
				|| (isThing(end2Type) && isRole(end1Type) && !end2.getAggregation().equals(
						AggregationKind.NONE_LITERAL))) {
			return true;
		}
		return false;
	}

	/**
	 * Compute default name.
	 * 
	 * @param element
	 *            New element
	 * @return Name for the new element, he name will looks like 'ElementType'+total of existing elements of
	 *         the same type.
	 */
	public String computeDefaultName(final EObject element) {
		Predicate<EObject> predicate = null;
		String name = element.getClass().getSimpleName();
		name = name.substring(0, name.indexOf("Impl"));
		predicate = new Predicate<EObject>() {
			public boolean apply(EObject input) {
				return input.getClass().getName().equals(element.getClass().getName());
			}
		};
		if (element instanceof AssociationClass) {
			name = "AssociationClass";
		} else if (element instanceof ActivityFinalNode) {
			name = "ActivityFinal";
		} else if (element instanceof StateMachine) {
			name = "StateMachine";
		} else if (element instanceof FlowFinalNode) {
			name = "FlowFinal";
		} else if (element instanceof MergeNode) {
			name = "Merge";
		} else if (element instanceof DataStoreNode) {
			name = "DataStore";
		} else if (element instanceof ActivityParameterNode) {
			name = "Parameter";
		} else if (element instanceof ForkNode) {
			name = "Fork";
		} else if (element instanceof EnumerationLiteral) {
			name = "Literal";
		} else if (element instanceof Property) {
			name = "property";
		} else if (element instanceof Pseudostate) {
			String kind = (((Pseudostate)element).getKind()).getLiteral();
			name = Character.toUpperCase(kind.charAt(0)) + kind.substring(1);
		} else if (element instanceof Association) {
			String end1 = ((Association)element).getOwnedEnds().get(0).getName();
			String end2 = ((Association)element).getOwnedEnds().get(1).getName();
			return end1 + "To" + Character.toUpperCase(end2.charAt(0)) + end2.substring(1);
		} else if (element instanceof InstanceSpecification) {
			predicate = new Predicate<EObject>() {
				public boolean apply(EObject input) {
					return input instanceof InstanceSpecification;
				}
			};
			name = "anObject";
			List<Classifier> classifiers = ((InstanceSpecification)element).getClassifiers();
			if (!classifiers.isEmpty()) {
				String classifierName = classifiers.get(0).getName();
				if (classifierName != null && classifierName.length() > 0) {
					if (startWithVowel(classifierName)) {
						name = "an";
					} else {
						name = "a";
					}
					name += classifierName;
				}
			}
		}

		List<EObject> existingElements = Lists.newArrayList(Iterables.filter(
				element.eContainer().eContents(), predicate));

		return name + existingElements.size();
	}

	private static boolean startWithVowel(String str) {
		boolean result = false;
		if (str != null && str.length() > 0) {
			char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};
			for (char vowel : vowels) {
				if (str.startsWith(Character.toString(vowel))
						|| str.startsWith(Character.toString(vowel).toUpperCase())) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	public List<Package> getAllAvailableRootPackages(Element element) {
		// <%script type="uml.Element" name="allAvailableRootPackages"%>
		// <%(getRootContainer().filter("Package") + rootPackagesFromImportedModel).nMinimize()%>
		List<Package> packages = Lists.newArrayList();
		packages.add(element.getModel());
		packages.addAll(Lists.newArrayList(Iterables.filter(element.getModel().getImportedPackages(),
				Model.class)));
		return packages;
	}

	public boolean existComments(Element element) {
		return element.getOwnedComments() != null && element.getOwnedComments().size() > 0;
	}

	public boolean notExistComments(Element element) {
		return !existComments(element);
	}

	public String getComment(Element element) {
		if (element.getOwnedComments().size() > 0)
			return element.getOwnedComments().get(0).getBody();
		return null;
	}

	public boolean inactive(Element element) {
		return false;
	}

	public boolean active(Element element) {
		return true;
	}

	public boolean isNotDiagram(EObject containerView) {
		return !(containerView instanceof DDiagram);
	}

	public boolean isComponent(EObject element) {
		return element instanceof Component;
	}

	public boolean isClassifier(EObject element) {
		return element instanceof Classifier;
	}

	public boolean isPackage(EObject element) {
		return element instanceof Package;
	}

	public boolean isTypeOfClass(EObject element) {
		return "Class".equals(element.eClass().getName());
	}
}
