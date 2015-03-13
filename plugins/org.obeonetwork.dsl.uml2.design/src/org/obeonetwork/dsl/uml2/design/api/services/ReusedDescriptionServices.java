/*******************************************************************************
 * Copyright (c) 2009, 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.api.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Device;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionEnvironment;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UMLPlugin;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.obeonetwork.dsl.uml2.design.internal.services.DirectEditLabelSwitch;
import org.obeonetwork.dsl.uml2.design.internal.services.ElementServices;
import org.obeonetwork.dsl.uml2.design.internal.services.LabelServices;
import org.obeonetwork.dsl.uml2.design.internal.services.MoveDownElementSwitch;
import org.obeonetwork.dsl.uml2.design.internal.services.MoveUpElementSwitch;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * A set of services to handle the Reused Description diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ReusedDescriptionServices extends AbstractDiagramServices {

	/**
	 * Add existing elements.
	 *
	 * @param containerView
	 *            Container
	 * @param semanticElementList
	 *            Semantic elements
	 */
	public void addExistingElements(final EObject containerView, final List<EObject> semanticElementList) {
		if (!(containerView instanceof DSemanticDecorator) || semanticElementList == null
				|| semanticElementList.isEmpty()) {
			return;
		}
		final Session session = SessionManager.INSTANCE.getSession(semanticElementList.get(0));
		for (final EObject semanticElement : semanticElementList) {
			// Mark for auto-size
			markForAutosize(semanticElement);

			// Show the added element on the diagram
			showView(semanticElement, (DSemanticDecorator)containerView, session, "containerView"); //$NON-NLS-1$
		}
	}

	private List<EObject> allValidSessionElements(EObject cur, Predicate<EObject> validForClassDiagram) {
		final Session found = SessionManager.INSTANCE.getSession(cur);
		final List<EObject> result = Lists.newArrayList();
		if (found != null) {
			for (final Resource res : found.getSemanticResources()) {
				if (res.getURI().isPlatformResource() || res.getURI().isPlatformPlugin()) {
					Iterators.addAll(result, Iterators.filter(res.getAllContents(), validForClassDiagram));
				}
			}
		}
		return result;
	}

	/**
	 * Apply stereotypes.
	 *
	 * @param element
	 *            Element
	 * @param stereotypesToApply
	 *            Stereotyped to apply
	 * @return The element on which stereotypes are applied
	 */
	public Element applyAllStereotypes(Element element, List<Stereotype> stereotypesToApply) {
		// Unapplying not selected stereotypes
		final List<Stereotype> alreadyAppliedStereotypes = element.getAppliedStereotypes();
		for (final Stereotype alreadyAppliedStereotype : alreadyAppliedStereotypes) {
			if (stereotypesToApply == null || !stereotypesToApply.contains(alreadyAppliedStereotype)) {
				element.unapplyStereotype(alreadyAppliedStereotype);
			}
		}

		if (stereotypesToApply == null) {
			return element;
		}

		// Applying selected stereotypes
		for (final Stereotype stereotypeToApply : stereotypesToApply) {
			final Profile profile = stereotypeToApply.getProfile();
			final Package pkg = element.getNearestPackage();
			if (!pkg.isProfileApplied(profile)) {
				pkg.applyProfile(profile);
			}

			if (!element.isStereotypeApplied(stereotypeToApply)) {
				element.applyStereotype(stereotypeToApply);
			}
		}
		return element;
	}

	/**
	 * Check if the default Ecore primitive types are already imported.
	 *
	 * @param element
	 *            Element
	 * @return true if the types are already imported, else false
	 */
	public boolean areEcorePrimitiveTypesNotImported(NamedElement element) {
		return !arePrimitiveTypesImported(element, UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if the default Java primitive types are already imported.
	 *
	 * @param element
	 *            Element
	 * @return true if the types are already imported, else false
	 */
	public boolean areJavaPrimitiveTypesNotImported(NamedElement element) {
		return !arePrimitiveTypesImported(element, UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if the given library is already imported in the given {@link Namespace}.
	 *
	 * @param element
	 *            the element
	 * @param libraryUri
	 *            URI of the library.
	 * @return <code>true</code> if the library is imported, <code>false</code> otherwise
	 */
	private boolean arePrimitiveTypesImported(NamedElement element, String libraryUri) {
		final Namespace namespace = ElementServices.INSTANCE.getNamespace(element);
		final ResourceSet resourceSet = namespace.eResource().getResourceSet();
		final Resource resource = resourceSet.getResource(URI.createURI(libraryUri), true);
		final Package root = (Package)EcoreUtil.getObjectByType(resource.getContents(),
				UMLPackage.Literals.PACKAGE);
		// We check if a package import already exists
		return namespace.getImportedPackages().contains(root);
	}

	/**
	 * Check if the default UML primitive types are already imported.
	 *
	 * @param element
	 *            Element to be checked
	 * @return true if the types are already imported, else false
	 */
	public boolean areUmlPrimitiveTypesNotImported(NamedElement element) {
		return !arePrimitiveTypesImported(element, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if the default XML primitive types are already imported.
	 *
	 * @param element
	 *            Element to be checked
	 * @return true if the types are already imported, else false
	 */
	public boolean areXmlPrimitiveTypesNotImported(NamedElement element) {
		return !arePrimitiveTypesImported(element, UMLResource.XML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Compute the label of the given element for direct edit.
	 *
	 * @param element
	 *            the {@link Element} for which to retrieve a label.
	 * @return the computed label.
	 */
	public String computeUmlDirectEditLabel(Element element) {
		final DirectEditLabelSwitch directEditLabel = new DirectEditLabelSwitch();

		return directEditLabel.doSwitch(element);
	}

	/**
	 * Create interaction a new interaction in package.
	 *
	 * @param pkg
	 *            Package containing new interaction.
	 * @return Interaction
	 */
	public Interaction createInteraction(EObject pkg) {
		final UMLFactory factory = UMLFactory.eINSTANCE;
		final Interaction interaction = factory.createInteraction();
		interaction.setName(ElementServices.INSTANCE.getNewInteractionName(pkg));
		((Package)pkg).getPackagedElements().add(interaction);
		return interaction;
	}

	/**
	 * Parse the edited label string and update the underlying context {@link Element}.
	 *
	 * @param context
	 *            the context object on which to execute this service.
	 * @param editedLabelContent
	 *            the content entered by the user.
	 * @return the context {@link Element}
	 */
	public Element editUmlLabel(Element context, String editedLabelContent) {
		return LabelServices.INSTANCE.editUmlLabel(context, editedLabelContent);
	}

	/**
	 * Check if an element is a container.
	 *
	 * @param container
	 *            Semantic container
	 * @param containerView
	 *            Container view
	 * @return True if element is valid for the given container view
	 */
	public boolean existValidElementsForContainerView(final EObject container, final EObject containerView) {
		if (container instanceof Element && containerView instanceof DSemanticDecorator) {
			final Session session = SessionManager.INSTANCE.getSession(container);
			// Get all available mappings applicable for the selected element in the
			// current container
			final List<DiagramElementMapping> semanticElementMappings = getMappings(
					(DSemanticDecorator)containerView, session);

			return semanticElementMappings.size() > 0;
		}
		return false;
	}

	/**
	 * Get an activity.
	 *
	 * @param parent
	 *            Parent
	 * @return Activity
	 */
	private Activity getActivity(NamedElement parent) {
		// Check if an activity already exists
		if (parent.eContents() != null && parent.eContents().size() > 0) {
			for (final EObject obj : parent.eContents()) {
				if (obj instanceof Activity) {
					// There's already an activity
					// Do nothing
					return (Activity)obj;
				}
			}
		}
		final Activity activity = UMLFactory.eINSTANCE.createActivity();
		final String activityLabel = parent.getName() + " activity"; //$NON-NLS-1$
		activity.setName(activityLabel);
		return activity;
	}

	/**
	 * Get all the associations relative to an element.
	 *
	 * @param classifier
	 *            the current classifier.
	 * @return a list of association
	 */
	public Collection<Association> getAllAssociations(Classifier classifier) {
		final Collection<Association> result = new ArrayList<Association>();
		for (final EObject child : classifier.getRelationships()) {
			if (child instanceof Association && !(child instanceof AssociationClass)) {
				result.add((Association)child);
			}
		}
		return result;
	}

	/**
	 * Retrieves all the possible profiles in the platform for the given context object.
	 *
	 * @param element
	 *            Element
	 * @return a {@link Collection} of all the profiles of the current platform.
	 */
	public Collection<Profile> getAllProfilesInPlatform(Element element) {
		// Get element package container
		final org.eclipse.uml2.uml.Package package_ = element.getNearestPackage();
		final List<Profile> roots = Lists.newArrayList();

		final org.eclipse.uml2.uml.Package packageUML = package_;
		final ResourceSet resourceSet = packageUML.eResource().getResourceSet();

		for (final URI profileURI : UMLPlugin.getEPackageNsURIToProfileLocationMap().values()) {
			try {
				resourceSet.getResource(profileURI.trimFragment(), true);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		for (final Resource resource : resourceSet.getResources()) {
			final TreeIterator<EObject> allContents = resource.getAllContents();
			while (allContents.hasNext()) {
				new UMLSwitch<Object>() {
					@Override
					public Object caseProfile(Profile profile) {
						if (profile.isDefined()) {
							packageUML.getProfileApplication(profile);
							// use this condition in order to not add the already applied profiles to the
							// result list
							// if (profileApplication == null
							// || profileApplication.getAppliedDefinition() != profile
							// .getDefinition()) {
							// roots.add(profile);
							// }
							roots.add(profile);
						}
						return profile;
					}
				}.doSwitch(allContents.next());
			}
		}
		return roots;
	}

	private List<Stereotype> getAllStereotypes(Element element, Collection<Profile> profiles) {
		final List<Stereotype> stereotypes = Lists.newArrayList();
		for (final Profile profile : profiles) {
			final org.eclipse.uml2.uml.Package pkg = element.getNearestPackage();
			boolean isProfileApplied = false;
			if (pkg.isProfileApplied(profile)) {
				isProfileApplied = true;
			}

			if (!isProfileApplied) {
				pkg.applyProfile(profile);
			}
			stereotypes.addAll(element.getApplicableStereotypes());

			if (!isProfileApplied) {
				pkg.unapplyProfile(profile);
			}
		}

		return stereotypes;
	}

	/**
	 * Get all stereotypes and profiles.
	 *
	 * @param element
	 *            Element
	 * @return All stereotypes and profiles associated to an element
	 */
	public List<EObject> getAllStereotypesAndProfiles(Element element) {
		final List<EObject> stereotypesAndProfiles = Lists.newArrayList();
		final Collection<Profile> profiles = getAllProfilesInPlatform(element);
		// Get all stereotypes
		stereotypesAndProfiles.addAll(getAllStereotypes(element, profiles));
		// Get all profiles
		stereotypesAndProfiles.addAll(profiles);
		return stereotypesAndProfiles;
	}

	/**
	 * Retrieve the nearest package associated to an execution. Method used to find the class diagram package.
	 *
	 * @param execution
	 *            Execution
	 * @return Class diagram package
	 */
	public Package getClassDiagramFromExecution(BehaviorExecutionSpecification execution) {
		if (execution != null) {
			final Behavior behavior = execution.getBehavior();
			if (behavior != null) {
				final BehavioralFeature feature = behavior.getSpecification();
				if (feature != null) {
					return feature.getNearestPackage();
				}
			}
		}
		return null;
	}

	/**
	 * Retrieve the nearest package associated to a lifeline. Method used to find the class diagram package.
	 *
	 * @param lifeline
	 *            Lifeline
	 * @return Class diagram package
	 */
	public Package getClassDiagramFromLifeline(Lifeline lifeline) {
		if (lifeline != null) {
			final ConnectableElement element = lifeline.getRepresents();
			if (element != null) {
				final Type type = element.getType();
				if (type != null) {
					return type.getPackage();
				}
			}
		}
		return null;
	}

	/**
	 * Get the list of comments to display on the given DDiagram.<br>
	 * <br>
	 * This gets only the comments attached to the current visible diagram elements.<br>
	 * This query is equivalent to:<br>
	 * <code>[self.oclAsType(Element).ownedComments->union(diagram.ownedDiagramElements->select(elt : DDiagramElement | elt.visible = true and elt.target.oclAsType(Element).ownedComment->notEmpty()).target.oclAsType(Element).ownedComment)/]</code>
	 *
	 * @param diagram
	 *            The diagram representation
	 * @return the list of comments to display.
	 */
	public List<Comment> getCommentsOnVisibleGraphicalElements(DDiagram diagram) {
		final List<Comment> result = new ArrayList<Comment>();

		if (diagram instanceof DSemanticDecorator) {
			final EObject target = ((DSemanticDecorator)diagram).getTarget();
			if (target instanceof Element) {
				result.addAll(((Element)target).getOwnedComments());
			}
		}

		for (final DDiagramElement elt : diagram.getOwnedDiagramElements()) {
			if (elt.isVisible() && elt.getTarget() instanceof Element && !((Element)elt.getTarget()).getOwnedComments().isEmpty()) {
				result.addAll(((Element)elt.getTarget()).getOwnedComments());
			}
		}

		return result;
	}

	/**
	 * Get an statemachine.
	 *
	 * @param parent
	 *            Parent
	 * @return Statemachine
	 */
	private StateMachine getStatemachine(NamedElement parent) {
		// Check if an statemachine already exists
		if (parent.eContents() != null && parent.eContents().size() > 0) {
			for (final EObject obj : parent.eContents()) {
				if (obj instanceof StateMachine) {
					// There's already an statemachine
					// Do nothing
					return (StateMachine)obj;
				}
			}
		}
		final StateMachine statemachine = UMLFactory.eINSTANCE.createStateMachine();
		final String statemachineLabel = parent.getName() + " statemachine"; //$NON-NLS-1$
		final Region region = UMLFactory.eINSTANCE.createRegion();
		region.setName("Region1"); //$NON-NLS-1$
		statemachine.getRegions().add(region);
		statemachine.setName(statemachineLabel);
		return statemachine;
	}

	private List<EObject> getValidsForClassDiagram(EObject cur) {
		final Predicate<EObject> validForClassDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				return input instanceof Package || input instanceof Interface || input instanceof DataType
						|| "Class".equals(input.eClass().getName()) //$NON-NLS-1$
						|| "Component".equals(input.eClass().getName()); //$NON-NLS-1$
			}
		};
		return allValidSessionElements(cur, validForClassDiagram);
	}

	private List<EObject> getValidsForComponentDiagram(EObject cur) {
		final Predicate<EObject> validForComponentDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				return input instanceof Package || input instanceof Interface
						|| "Class".equals(input.eClass().getName()) //$NON-NLS-1$
						|| "Component".equals(input.eClass().getName()); //$NON-NLS-1$
			}
		};
		return allValidSessionElements(cur, validForComponentDiagram);
	}

	private List<EObject> getValidsForCompositeDiagram(EObject cur) {
		final Predicate<EObject> validForCompositeDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				if (input instanceof StructuredClassifier) {
					return !(input instanceof Interaction || input instanceof StateMachine || input instanceof Activity);
				}
				return input instanceof Package || input instanceof Interface
						|| "Port".equals(input.eClass().getName()) //$NON-NLS-1$
						|| "Property".equals(input.eClass().getName()); //$NON-NLS-1$

			}
		};
		return allValidSessionElements(cur, validForCompositeDiagram);
	}

	private List<EObject> getValidsForDeploymentDiagram(EObject cur) {
		final Predicate<EObject> validForDeploymentDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				return input instanceof Package || input instanceof ExecutionEnvironment
						|| input instanceof Node || input instanceof Artifact || input instanceof Device;
			}
		};
		return allValidSessionElements(cur, validForDeploymentDiagram);
	}

	/**
	 * Get valid elements for a diagram.
	 *
	 * @param element
	 *            Element
	 * @param containerView
	 *            Container view
	 * @return List of valid elements for the current representation
	 */
	public List<EObject> getValidsForDiagram(final EObject element, final DSemanticDecorator containerView) {
		// Get representation
		DRepresentation representation = null;
		if (containerView instanceof DRepresentation) {
			representation = (DRepresentation)containerView;
		} else if (containerView instanceof DDiagramElement) {
			representation = ((DDiagramElement)containerView).getParentDiagram();
		}
		List<EObject> results = null;
		if (representation instanceof DSemanticDiagram) {
			final DiagramDescription description = ((DSemanticDiagram)representation).getDescription();

			if ("Class Diagram".equals(description.getName()) //$NON-NLS-1$
					|| "Profile Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForClassDiagram(element);
			} else if ("Component Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForComponentDiagram(element);
			} else if ("Composite Structure Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForCompositeDiagram(element);
			} else if ("Composite Structure Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForCompositeDiagram(element);
			} else if ("Deployment Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForDeploymentDiagram(element);
			} else if ("Package Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForPackageDiagram(element);
			} else if ("Use Case Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForUseCaseDiagram(element);
			}
		}

		return results;
	}

	/**
	 * Get valid elements for a diagram.
	 *
	 * @param cur
	 *            Element
	 * @return List of valid elements for the current representation
	 */
	public List<EObject> getValidsForPackageDiagram(EObject cur) {
		final Predicate<EObject> validForPackageDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				return input instanceof Package;
			}
		};
		return allValidSessionElements(cur, validForPackageDiagram);
	}

	private List<EObject> getValidsForUseCaseDiagram(EObject cur) {
		final Predicate<EObject> validForUseCaseDiagram = new Predicate<EObject>() {

			public boolean apply(EObject input) {
				return input instanceof Package || input instanceof Class || input instanceof Component
						|| input instanceof Artifact || input instanceof DataType
						|| input instanceof Interface || input instanceof Collaboration
						|| input instanceof UseCase;
			}
		};
		return allValidSessionElements(cur, validForUseCaseDiagram);
	}

	/**
	 * Import the package containing default Ecore primitive types.
	 *
	 * @param element
	 *            Named element
	 */
	public void importEcorePrimitiveTypes(NamedElement element) {
		ElementServices.INSTANCE.importEcorePrimitiveTypes(element);
	}

	/**
	 * Import the package containing default Java primitive types.
	 *
	 * @param element
	 *            Named element
	 */
	public void importJavaPrimitiveTypes(NamedElement element) {
		ElementServices.INSTANCE.importJavaPrimitiveTypes(element);
	}

	/**
	 * Import the package containing default UML primitive types.
	 *
	 * @param element
	 *            Named element
	 */
	public void importUmlPrimitiveTypes(NamedElement element) {
		ElementServices.INSTANCE.importUmlPrimitiveTypes(element);
	}

	/**
	 * Import the package containing default XML primitive types.
	 *
	 * @param element
	 *            Named element
	 */
	public void importXmlPrimitiveTypes(NamedElement element) {
		ElementServices.INSTANCE.importXmlPrimitiveTypes(element);
	}

	/**
	 * Create an activity under a behaviored classifier (class, component, use case).
	 *
	 * @param parent
	 *            The parent
	 * @return An activity
	 */
	public Activity initActivityForClass(org.eclipse.uml2.uml.BehavioredClassifier parent) {
		final Activity activity = getActivity(parent);
		parent.getOwnedBehaviors().add(activity);
		return activity;
	}

	/**
	 * Initializes an activity for an operation to be able to create an activity diagram on it. Does nothing
	 * if an activity already exists.
	 *
	 * @param op
	 *            Operation to be associated with the activity
	 * @return the found or created {@link Activity}
	 */
	public Activity initActivityForOperation(Operation op) {
		// Check if an activity already exists
		if (op.getMethods() != null && op.getMethods().size() > 0) {
			for (final Behavior behavior : op.getMethods()) {
				if (behavior instanceof Activity) {
					// There's already an activity
					// Do nothing
					return (Activity)behavior;
				}
			}
		}

		// We have to create a new activity
		final Activity activity = UMLFactory.eINSTANCE.createActivity();
		final String activityLabel = op.getName() + " activity"; //$NON-NLS-1$
		activity.setName(activityLabel);
		op.getClass_().getOwnedBehaviors().add(activity);

		// Associate the activity to the operation
		op.getMethods().add(activity);

		return activity;
	}

	/**
	 * Create an activity under a package.
	 *
	 * @param pkg
	 *            The package
	 * @return An activity
	 */
	public Activity initActivityForPackage(org.eclipse.uml2.uml.Package pkg) {
		final Activity activity = getActivity(pkg);
		pkg.getPackagedElements().add(activity);
		return activity;
	}

	/**
	 * Create an statemachine under a behaviored classifier (class, component, use case).
	 *
	 * @param parent
	 *            The parent
	 * @return An statemachine
	 */
	public StateMachine initStatemachineForClass(org.eclipse.uml2.uml.BehavioredClassifier parent) {
		final StateMachine statemachine = getStatemachine(parent);
		parent.getOwnedBehaviors().add(statemachine);
		return statemachine;
	}

	/**
	 * Create an statemachine under a package.
	 *
	 * @param pkg
	 *            The package
	 * @return An statemachine
	 */
	public StateMachine initStatemachineForPackage(org.eclipse.uml2.uml.Package pkg) {
		final StateMachine statemachine = getStatemachine(pkg);
		pkg.getPackagedElements().add(statemachine);
		return statemachine;
	}

	/**
	 * Check if an element is a component.
	 *
	 * @param element
	 *            Element
	 * @return True if element is an instance of component
	 */
	public boolean isComponent(EObject element) {
		return ElementServices.INSTANCE.isComponent(element);
	}

	/**
	 * Check if a container view is not a diagram.
	 *
	 * @param containerView
	 *            Container view
	 * @return True if element is not a diagram
	 */
	public boolean isNotDiagram(EObject containerView) {
		return !(containerView instanceof DDiagram);
	}

	/**
	 * Change properties order in Class and Interface.
	 *
	 * @param currentOperation
	 *            Operation
	 * @param operationsToMove
	 *            operations to move
	 */
	public void moveDownOperations(Operation currentOperation, List<Operation> operationsToMove) {
		final List<Operation> operationsInRightOrder = retrieveTheRightOrderForOperation(operationsToMove);
		final Object[] operationsArray = operationsInRightOrder.toArray();
		final MoveDownElementSwitch moveDownElementSwitch = new MoveDownElementSwitch();
		for (int i = operationsArray.length - 1; i >= 0; i--) {
			if (operationsArray[i] instanceof Operation) {
				moveDownElementSwitch.moveDownElement((Operation)operationsArray[i]);
			}
		}
	}

	/**
	 * Change properties order in Class and Interface.
	 *
	 * @param packageableElementsToMove
	 *            packageableElements to move
	 */
	public void moveDownPackageableElements(List<PackageableElement> packageableElementsToMove) {

		final MoveDownElementSwitch moveDownElementSwitch = new MoveDownElementSwitch();
		final List<PackageableElement> packageableElementsInRightOrder = retrieveTheRightOrderForPackageableElement(packageableElementsToMove);
		final Object[] elementsArray = packageableElementsInRightOrder.toArray();
		for (int i = elementsArray.length - 1; i >= 0; i--) {
			if (elementsArray[i] instanceof Element) {
				moveDownElementSwitch.moveDownElement((Element)elementsArray[i]);
			}
		}
	}

	/**
	 * Change properties order in Class and Interface.
	 *
	 * @param currentProperty
	 *            Property
	 * @param propertiesToMove
	 *            properties to move
	 */
	public void moveDownProperties(Property currentProperty, List<Property> propertiesToMove) {

		final List<Property> propertiesInRightOrder = retrieveTheRightOrderForProperties(propertiesToMove);
		final Object[] propertiesArray = propertiesInRightOrder.toArray();
		final MoveDownElementSwitch moveDownElementSwitch = new MoveDownElementSwitch();
		for (int i = propertiesArray.length - 1; i >= 0; i--) {
			if (propertiesArray[i] instanceof Property) {
				moveDownElementSwitch.moveDownElement((Property)propertiesArray[i]);
			}
		}
	}

	/**
	 * Change properties order in Class and Interface.
	 *
	 * @param currentOperation
	 *            Operation
	 * @param operationsToMove
	 *            operations to move
	 */
	public void moveUpOperations(Operation currentOperation, List<Operation> operationsToMove) {

		final List<Operation> operationsInRightOrder = retrieveTheRightOrderForOperation(operationsToMove);
		final MoveUpElementSwitch moveUpElementsSwitch = new MoveUpElementSwitch();
		final Iterator<Operation> iterator = operationsInRightOrder.iterator();
		while (iterator.hasNext()) {
			final Operation operation = iterator.next();
			moveUpElementsSwitch.moveUpElement(operation);
		}
	}

	/**
	 * Change properties order in Class and Interface.
	 *
	 * @param packageableElement
	 *            Packageable element
	 * @param packageableElementsToMove
	 *            packageableElements to move
	 */
	public void moveUpPackageableElements(PackageableElement packageableElement,
			List<PackageableElement> packageableElementsToMove) {
		final MoveUpElementSwitch moveUpElementsSwitch = new MoveUpElementSwitch();
		final List<PackageableElement> packageableElementsInRightOrder = retrieveTheRightOrderForPackageableElement(packageableElementsToMove);
		final Iterator<PackageableElement> iterator = packageableElementsInRightOrder.iterator();
		while (iterator.hasNext()) {
			final Element element = iterator.next();
			moveUpElementsSwitch.moveUpElement(element);
		}
	}

	/**
	 * Change properties order in Class and Interface.
	 *
	 * @param currentProperty
	 *            Current property
	 * @param propertiesToMove
	 *            properties to move
	 */
	public void moveUpProperties(Property currentProperty, List<Property> propertiesToMove) {

		final List<Property> propertiesInRightOrder = retrieveTheRightOrderForProperties(propertiesToMove);
		final MoveUpElementSwitch moveUpElementsSwitch = new MoveUpElementSwitch();
		// move all properties contain in propertiesInRightOrder (to move in the right order)
		final Iterator<Property> iterator = propertiesInRightOrder.iterator();
		while (iterator.hasNext()) {
			final Property property = iterator.next();
			moveUpElementsSwitch.moveUpElement(property);
		}
	}

	/**
	 * Paste a semantic element and create the corresponding view in the given container
	 *
	 * @param container
	 *            Semantic container
	 * @param semanticElement
	 *            Element to paste
	 * @param elementView
	 *            Element view
	 * @param containerView
	 *            Container view
	 */
	public void paste(final Element container, final Element semanticElement,
			final DSemanticDecorator elementView, final DSemanticDecorator containerView) {
		// Paste the semantic element from the clipboard to the selected
		// container
		final Session session = SessionManager.INSTANCE.getSession(container);
		final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
		// The feature is set to null because the domain will deduce it
		final Command cmd = AddCommand.create(domain, container, null, semanticElement);
		if (cmd.canExecute()) {
			cmd.execute();
		}
		// Mark for auto size
		markForAutosize(semanticElement);
		// Create the view for the pasted element
		createView(semanticElement, containerView, session, "containerView"); //$NON-NLS-1$
	}

	/**
	 * Refresh a representation. This service has been created to handle a bug on the generic tool : the force
	 * refresh option does not work as expected
	 *
	 * @param representation
	 *            Representation to be refreshed
	 * @return the specified representation
	 */
	public DRepresentation refreshDiagram(DRepresentation representation) {
		DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
		return representation;
	}

	private List<Operation> retrieveTheRightOrderForOperation(List<Operation> operationsInWrongOrder) {

		final List<Operation> operationsInRightOrder = new ArrayList<Operation>();

		// retrieve all eContainers (operation could be in different eContainers)
		final List<EObject> eContainers = new ArrayList<EObject>();
		for (final Operation operation : operationsInWrongOrder) {
			final EObject eContainer = operation.eContainer();
			if (eContainer != null && !eContainers.contains(eContainer)) {
				eContainers.add(eContainer);
			}
		}

		// on all eContainers found
		for (final EObject eContainer : eContainers) {
			if (eContainer instanceof org.eclipse.uml2.uml.Class || eContainer instanceof Interface) {
				// get all operations for a specific eContainer
				EList<Operation> operations = null;
				if (eContainer instanceof org.eclipse.uml2.uml.Class) {
					final org.eclipse.uml2.uml.Class eclass = (org.eclipse.uml2.uml.Class)eContainer;
					operations = eclass.getOwnedOperations();
				} else {
					final Interface eInterface = (Interface)eContainer;
					operations = eInterface.getOwnedOperations();
				}

				// add all operations contain in operationsInWrongOrder (to retrieve the right order)
				final Iterator<Operation> iterator = operations.iterator();
				while (iterator.hasNext()) {
					final Operation operation = iterator.next();
					if (operationsInWrongOrder.contains(operation)) {
						operationsInRightOrder.add(operation);
					}
				}
			}
		}

		return operationsInRightOrder;
	}

	private List<PackageableElement> retrieveTheRightOrderForPackageableElement(
			List<PackageableElement> packageableElementsInWrongOrder) {

		final List<PackageableElement> packageableElementsInRightOrder = new ArrayList<PackageableElement>();

		// retrieve all eContainers (packageableElement could be in different eContainers)
		final List<EObject> eContainers = new ArrayList<EObject>();
		for (final PackageableElement packageableElement : packageableElementsInWrongOrder) {
			final EObject eContainer = packageableElement.eContainer();
			if (eContainer != null && !eContainers.contains(eContainer)) {
				eContainers.add(eContainer);
			}
		}

		// on all eContainers found
		for (final EObject eContainer : eContainers) {
			if (eContainer instanceof org.eclipse.uml2.uml.Package || eContainer instanceof Component) {
				// get all packageableElements for a specific eContainer
				EList<PackageableElement> packageableElements = null;
				if (eContainer instanceof org.eclipse.uml2.uml.Package) {
					final org.eclipse.uml2.uml.Package ePackage = (org.eclipse.uml2.uml.Package)eContainer;
					packageableElements = ePackage.getPackagedElements();
				} else {
					final Component component = (Component)eContainer;
					packageableElements = component.getPackagedElements();
				}

				// add all packageableElements contain in packageableElementsInWrongOrder (to retrieve the
				// right order)
				final Iterator<PackageableElement> iterator = packageableElements.iterator();
				while (iterator.hasNext()) {
					final PackageableElement packageableElement = iterator.next();
					if (packageableElementsInWrongOrder.contains(packageableElement)) {
						packageableElementsInRightOrder.add(packageableElement);
					}
				}
			}
		}

		return packageableElementsInRightOrder;
	}

	private List<Property> retrieveTheRightOrderForProperties(List<Property> propertiesInWrongOrder) {

		final List<Property> propertiesInRightOrder = new ArrayList<Property>();

		// retrieve all eContainers (property could be in different eContainers)
		final List<EObject> eContainers = new ArrayList<EObject>();
		for (final Property property : propertiesInWrongOrder) {
			final EObject eContainer = property.eContainer();
			if (eContainer != null && !eContainers.contains(eContainer)) {
				eContainers.add(eContainer);
			}
		}

		// on all eContainers found
		for (final EObject eContainer : eContainers) {
			if (eContainer instanceof StructuredClassifier) {
				// get all properties for a specific eContainer
				final EList<Property> properties = ((StructuredClassifier)eContainer).getOwnedAttributes();

				// add all properties contain in propertiesInWrongOrder (to retrieve the right order)
				final Iterator<Property> iterator = properties.iterator();
				while (iterator.hasNext()) {
					final Property property = iterator.next();
					if (propertiesInWrongOrder.contains(property)) {
						propertiesInRightOrder.add(property);
					}
				}
			}
		}

		return propertiesInRightOrder;
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
}
