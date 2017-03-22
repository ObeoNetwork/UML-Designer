/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityContent;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.DeploymentTarget;
import org.eclipse.uml2.uml.Device;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.ExecutableNode;
import org.eclipse.uml2.uml.ExecutionEnvironment;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.FlowFinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.InvocationAction;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Manifestation;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageMerge;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.Realization;
import org.eclipse.uml2.uml.RedefinableElement;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.util.UMLSwitch;

/**
 * Switch for to display features in General group.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class PropertyGeneralSwitch extends UMLSwitch<Boolean> {
	private EStructuralFeature feature = null;

	/**
	 * Constructor.
	 *
	 * @param feature
	 *            structural feature
	 */
	public PropertyGeneralSwitch(EStructuralFeature feature) {
		super();
		this.feature = feature;
	}

	@Override
	public Boolean caseAbstraction(Abstraction object) {
		return caseDependency(object);
	}

	@Override
	public Boolean caseAcceptEventAction(AcceptEventAction object) {
		if (UMLPackage.Literals.ACCEPT_EVENT_ACTION__IS_UNMARSHALL.equals(feature)
				|| UMLPackage.Literals.ACCEPT_EVENT_ACTION__TRIGGER.equals(feature)) {
			return new Boolean(true);
		}
		return caseAction(object);
	}

	@Override
	public Boolean caseAction(Action object) {
		return caseExecutableNode(object);
	}

	@Override
	public Boolean caseActivityContent(ActivityContent object) {
		return new Boolean(false);
	}

	@Override
	public Boolean caseActivityEdge(ActivityEdge object) {
		return caseRedefinableElement(object);
	}

	@Override
	public Boolean caseActivityFinalNode(ActivityFinalNode object) {
		return caseFinalNode(object);
	}
	@Override
	public Boolean caseActivityNode(ActivityNode object) {
		return new Boolean(
				caseRedefinableElement(object).booleanValue() || caseActivityContent(object).booleanValue());
	}

	@Override
	public Boolean caseActivityParameterNode(ActivityParameterNode object) {
		return caseObjectNode(object);
	}

	@Override
	public Boolean caseActor(Actor object) {
		return caseBehavioredClassifier(object);
	}

	@Override
	public Boolean caseArtifact(Artifact object) {
		if (UMLPackage.Literals.ARTIFACT__OWNED_ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.ARTIFACT__OWNED_OPERATION.equals(feature)) {
			return new Boolean(true);
		}
		return caseClassifier(object);
	}

	@Override
	public Boolean caseAssociation(Association object) {
		if (UMLPackage.Literals.ASSOCIATION__IS_DERIVED.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(caseClassifier(object).booleanValue() || caseRelationship(object).booleanValue());
	}

	@Override
	public Boolean caseAssociationClass(AssociationClass object) {
		return new Boolean(caseClass(object).booleanValue() || caseAssociation(object).booleanValue());
	}

	@Override
	public Boolean caseBehavior(Behavior object) {
		if (UMLPackage.Literals.BEHAVIOR__IS_REENTRANT.equals(feature)){
			return new Boolean(true);
		}
		return caseClass(object);
	}

	@Override
	public Boolean caseBehavioralFeature(BehavioralFeature object) {
		if (UMLPackage.Literals.BEHAVIORAL_FEATURE__IS_ABSTRACT.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(caseNamespace(object).booleanValue() || caseFeature(object).booleanValue());
	}

	@Override
	public Boolean caseBehavioredClassifier(BehavioredClassifier object) {
		return caseClassifier(object);
	}

	@Override
	public Boolean caseBehaviorExecutionSpecification(BehaviorExecutionSpecification object) {
		if (UMLPackage.Literals.BEHAVIOR_EXECUTION_SPECIFICATION__BEHAVIOR.equals(feature)) {
			return new Boolean(true);
		}
		return caseExecutionSpecification(object);
	}

	@Override
	public Boolean caseCallAction(CallAction object) {
		return caseInvocationAction(object);
	}

	@Override
	public Boolean caseCallBehaviorAction(CallBehaviorAction object) {
		if (UMLPackage.Literals.CALL_BEHAVIOR_ACTION__BEHAVIOR.equals(feature)) {
			return new Boolean(true);
		}
		return super.caseCallBehaviorAction(object);
	}

	@Override
	public Boolean caseCallOperationAction(CallOperationAction object) {
		return caseCallAction(object);
	}

	@Override
	public Boolean caseClass(Class object) {
		if (UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(caseEncapsulatedClassifier(object).booleanValue()
				|| caseBehavioredClassifier(object).booleanValue());
	}

	@Override
	public Boolean caseClassifier(Classifier object) {
		if (UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.CLASSIFIER__IS_ABSTRACT.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(
				caseNamedElement(object).booleanValue() || caseRedefinableElement(object).booleanValue()
				|| caseType(object).booleanValue() || caseTemplateableElement(object).booleanValue());
	}

	@Override
	public Boolean caseCollaboration(Collaboration object) {
		return new Boolean(caseStructuredClassifier(object).booleanValue()
				|| caseBehavioredClassifier(object).booleanValue());
	}

	@Override
	public Boolean caseComponent(Component object) {
		return caseClass(object);
	}

	@Override
	public Boolean caseComponentRealization(ComponentRealization object) {
		return caseRealization(object);
	}

	@Override
	public Boolean caseConnectableElement(ConnectableElement object) {
		return  new Boolean(
				caseTypedElement(object).booleanValue() || caseParameterableElement(object).booleanValue());
	}

	@Override
	public Boolean caseConnector(Connector object) {
		if (UMLPackage.Literals.CONNECTOR___GET_KIND.equals(feature)){
			return new Boolean(true);
		}
		return caseFeature(object);
	}

	@Override
	public Boolean caseControlFlow(ControlFlow object) {
		return caseActivityEdge(object);
	}

	@Override
	public Boolean caseControlNode(ControlNode object) {
		return caseActivityNode(object);
	}

	@Override
	public Boolean caseDataType(DataType object) {
		if (UMLPackage.Literals.DATA_TYPE__OWNED_ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.DATA_TYPE__OWNED_OPERATION.equals(feature)) {
			return new Boolean(true);
		}
		return caseClassifier(object);
	}

	@Override
	public Boolean caseDecisionNode(DecisionNode object) {
		return caseControlNode(object);
	}

	@Override
	public Boolean caseDependency(Dependency object) {
		if (UMLPackage.Literals.DEPENDENCY__CLIENT.equals(feature)
				|| UMLPackage.Literals.DEPENDENCY__SUPPLIER.equals(feature)){
			return new Boolean(true);
		}
		return new Boolean(casePackageableElement(object).booleanValue()
				|| caseDirectedRelationship(object).booleanValue());
	}

	@Override
	public Boolean caseDeployment(Deployment object) {
		return caseDependency(object);
	}

	@Override
	public Boolean caseDeploymentTarget(DeploymentTarget object) {
		return caseNamedElement(object);
	}

	@Override
	public Boolean caseDevice(Device object) {
		return caseNode(object);
	}

	@Override
	public Boolean caseDirectedRelationship(DirectedRelationship object) {
		return caseRelationship(object);
	}

	@Override
	public Boolean caseElement(Element object) {
		if (UMLPackage.Literals.ELEMENT___GET_RELATIONSHIPS.equals(feature)){
			return new Boolean(true);
		}
		return new Boolean(false);
	}

	@Override
	public Boolean caseEncapsulatedClassifier(EncapsulatedClassifier object) {
		return caseStructuredClassifier(object);
	}

	@Override
	public Boolean caseEnumeration(Enumeration object) {
		if (UMLPackage.Literals.ENUMERATION__OWNED_LITERAL.equals(feature)) {
			return new Boolean(true);
		}
		return caseDataType(object);
	}

	@Override
	public Boolean caseExecutableNode(ExecutableNode object) {
		return caseActivityNode(object);
	}

	@Override
	public Boolean caseExecutionEnvironment(ExecutionEnvironment object) {
		return caseNode(object);
	}

	@Override
	public Boolean caseExecutionSpecification(ExecutionSpecification object) {
		if (UMLPackage.Literals.EXECUTION_SPECIFICATION__START.equals(feature)
				|| UMLPackage.Literals.EXECUTION_SPECIFICATION__FINISH.equals(feature)) {
			return new Boolean(true);
		}
		return caseInteractionFragment(object);
	}

	@Override
	public Boolean caseExtend(Extend object) {
		if (UMLPackage.Literals.EXTEND__EXTENDED_CASE.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(
				caseDirectedRelationship(object).booleanValue() || caseNamedElement(object).booleanValue());
	}

	@Override
	public Boolean caseFeature(Feature object) {
		if (UMLPackage.Literals.FEATURE__IS_STATIC.equals(feature)){
			return new Boolean(true);
		}
		return new Boolean(caseRedefinableElement(object).booleanValue());
	}

	@Override
	public Boolean caseFinalNode(FinalNode object) {
		return caseControlNode(object);
	}

	@Override
	public Boolean caseFinalState(FinalState object) {
		if (UMLPackage.Literals.STATE__SUBMACHINE.equals(feature)
				|| UMLPackage.Literals.STATE__ENTRY.equals(feature)
				|| UMLPackage.Literals.STATE__EXIT.equals(feature)
				|| UMLPackage.Literals.STATE__DO_ACTIVITY.equals(feature)) {
			return new Boolean(false);
		}
		return caseState(object);
	}

	@Override
	public Boolean caseFlowFinalNode(FlowFinalNode object) {
		return caseFinalNode(object);
	}

	@Override
	public Boolean caseForkNode(ForkNode object) {
		return caseControlNode(object);
	}

	@Override
	public Boolean caseGeneralization(Generalization object) {
		if (UMLPackage.Literals.GENERALIZATION__GENERAL.equals(feature)
				|| UMLPackage.Literals.GENERALIZATION__SPECIFIC.equals(feature)) {
			return new Boolean(true);
		}
		return caseDirectedRelationship(object);
	}

	@Override
	public Boolean caseInclude(Include object) {
		if (UMLPackage.Literals.INCLUDE__ADDITION.equals(feature)) {
			return new Boolean(true);
		}
		return caseDirectedRelationship(object);
	}
	@Override
	public Boolean caseInitialNode(InitialNode object) {
		return caseControlNode(object);
	}

	@Override
	public Boolean caseInteraction(Interaction object) {
		// TODO Auto-generated method stub
		return new Boolean(caseBehavior(object).booleanValue()
				||caseInteractionFragment(object).booleanValue());
	}

	@Override
	public Boolean caseInteractionFragment(InteractionFragment object) {
		return caseNamedElement(object);
	}

	@Override
	public Boolean caseInterface(Interface object) {
		if (UMLPackage.Literals.CLASS__IS_ACTIVE.equals(feature)
				|| UMLPackage.Literals.INTERFACE__OWNED_ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.INTERFACE__OWNED_OPERATION.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(caseNamedElement(object).booleanValue() || caseClassifier(object).booleanValue());
	}

	@Override
	public Boolean caseInterfaceRealization(InterfaceRealization object) {
		if (UMLPackage.Literals.INTERFACE_REALIZATION__CONTRACT.equals(feature)) {
			return new Boolean(true);
		}
		return caseRealization(object);
	}

	@Override
	public Boolean caseInvocationAction(InvocationAction object) {
		return caseAction(object);
	}
	@Override
	public Boolean caseJoinNode(JoinNode object) {
		return caseControlNode(object);
	}

	@Override
	public Boolean caseLifeline(Lifeline object) {
		if (UMLPackage.Literals.LIFELINE__REPRESENTS.equals(feature)) {
			return new Boolean(true);
		}
		return caseNamedElement(object);
	}

	@Override
	public Boolean caseManifestation(Manifestation object) {
		return caseAbstraction(object);
	}

	@Override
	public Boolean caseMergeNode(MergeNode object) {
		return caseControlNode(object);
	}

	@Override
	public Boolean caseMessage(Message object) {
		if (UMLPackage.Literals.MESSAGE__MESSAGE_SORT.equals(feature)
				|| UMLPackage.Literals.MESSAGE__RECEIVE_EVENT.equals(feature)
				|| UMLPackage.Literals.MESSAGE__SEND_EVENT.equals(feature)
				|| UMLPackage.Literals.MESSAGE__SIGNATURE.equals(feature)) {
			return new Boolean(true);
		}
		return caseNamedElement(object);
	}

	@Override
	public Boolean caseMultiplicityElement(MultiplicityElement object) {
		if (UMLPackage.Literals.MULTIPLICITY_ELEMENT__IS_ORDERED.equals(feature)
				||UMLPackage.Literals.MULTIPLICITY_ELEMENT__IS_UNIQUE.equals(feature)
				||UMLPackage.Literals.MULTIPLICITY_ELEMENT__LOWER.equals(feature)
				|| UMLPackage.Literals.MULTIPLICITY_ELEMENT__UPPER.equals(feature)){
			return new Boolean(true);
		}
		return caseElement(object);
	}

	@Override
	public  Boolean caseNamedElement(NamedElement object) {
		if (UMLPackage.Literals.NAMED_ELEMENT__NAME.equals(feature)
				|| UMLPackage.Literals.NAMED_ELEMENT__VISIBILITY.equals(feature)){
			return new Boolean(true);
		}
		return caseElement(object);
	}

	@Override
	public Boolean caseNamespace(Namespace object) {
		return caseNamedElement(object);
	}

	@Override
	public Boolean caseNode(Node object) {
		if (UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return new Boolean(caseClass(object).booleanValue() || caseDeploymentTarget(object).booleanValue());
	}

	@Override
	public Boolean caseObjectFlow(ObjectFlow object) {
		return caseActivityEdge(object);
	}

	@Override
	public Boolean caseObjectNode(ObjectNode object) {
		return new Boolean(caseActivityNode(object).booleanValue()|| caseTypedElement(object).booleanValue()) ;
	}

	@Override
	public Boolean caseOpaqueAction(OpaqueAction object) {
		return caseAction(object);
	}

	@Override
	public Boolean caseOperation(Operation object) {
		if (UMLPackage.Literals.OPERATION__IS_QUERY.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(
				caseNamedElement(object).booleanValue() || caseBehavioralFeature(object).booleanValue());
	}

	@Override
	public Boolean caseOutputPin(OutputPin object) {
		return casePin(object);
	}

	@Override
	public Boolean casePackage(org.eclipse.uml2.uml.Package object) {
		if (UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return new Boolean (caseNamespace(object).booleanValue()
				||casePackageableElement(object).booleanValue()
				|| caseTemplateableElement(object).booleanValue());
	}

	@Override
	public Boolean casePackageableElement(PackageableElement object) {
		return new Boolean(caseNamedElement(object).booleanValue()|| caseParameterableElement(object).booleanValue());
	}

	@Override
	public Boolean casePackageImport(PackageImport object) {
		if (UMLPackage.Literals.PACKAGE_IMPORT__IMPORTED_PACKAGE.equals(feature)){
			return new Boolean(true);
		}
		return caseDirectedRelationship(object);
	}

	@Override
	public Boolean casePackageMerge(PackageMerge object) {
		if (UMLPackage.Literals.PACKAGE_MERGE__MERGED_PACKAGE.equals(feature)) {
			return new Boolean(true);
		}
		return caseDirectedRelationship (object);
	}

	@Override
	public Boolean caseParameterableElement(ParameterableElement object) {
		return caseElement(object);
	}

	@Override
	public Boolean casePin(Pin object) {
		return new Boolean(
				caseObjectNode(object).booleanValue() || caseMultiplicityElement(object).booleanValue());
	}

	@Override
	public Boolean casePort(Port object) {
		// for Port some attribute from Property have to be shown in Advanced instead of General
		if (UMLPackage.Literals.MULTIPLICITY_ELEMENT__IS_ORDERED.equals(feature)
				|| UMLPackage.Literals.MULTIPLICITY_ELEMENT__IS_UNIQUE.equals(feature)
				|| UMLPackage.Literals.STRUCTURAL_FEATURE__IS_READ_ONLY.equals(feature)
				|| UMLPackage.Literals.PROPERTY__QUALIFIER.equals(feature)) {
			return new Boolean(false);
		}
		if (UMLPackage.Literals.PORT__REDEFINED_PORT.equals(feature)
				|| UMLPackage.Literals.PORT__IS_SERVICE.equals(feature)) {
			return new Boolean(true);
		}
		return caseProperty(object);
	}

	@Override
	public Boolean casePrimitiveType(PrimitiveType object) {
		return caseDataType(object);
	}

	@Override
	public Boolean caseProperty(Property object) {
		if (UMLPackage.Literals.PROPERTY__IS_DERIVED.equals(feature)
				|| UMLPackage.Literals.PROPERTY__IS_DERIVED_UNION.equals(feature)
				|| UMLPackage.Literals.PROPERTY__AGGREGATION.equals(feature)
				|| UMLPackage.Literals.PROPERTY__DEFAULT_VALUE.equals(feature)
				|| UMLPackage.Literals.PROPERTY__QUALIFIER.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(
				caseStructuralFeature(object).booleanValue() || caseConnectableElement(object).booleanValue()
				|| caseDeploymentTarget(object).booleanValue());
	}

	@Override
	public Boolean casePseudostate(Pseudostate object) {
		return caseVertex(object);
	}

	@Override
	public Boolean caseRealization(Realization object) {
		return caseAbstraction(object);
	}
	@Override
	public Boolean caseRedefinableElement(RedefinableElement object) {
		return caseNamedElement(object);
	}

	@Override
	public Boolean caseRegion(Region object) {
		return new Boolean(caseNamespace(object).booleanValue() || caseRedefinableElement(object).booleanValue());
	}

	@Override
	public Boolean caseRelationship(Relationship object) {
		return caseElement(object);
	}

	@Override
	public Boolean caseState(State object) {
		if (UMLPackage.Literals.STATE__SUBMACHINE.equals(feature)
				|| UMLPackage.Literals.STATE__ENTRY.equals(feature)
				|| UMLPackage.Literals.STATE__EXIT.equals(feature)
				|| UMLPackage.Literals.STATE__DO_ACTIVITY.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(caseNamespace(object).booleanValue()
				|| caseRedefinableElement(object).booleanValue() || caseVertex(object).booleanValue());
	}

	@Override
	public Boolean caseStateMachine(StateMachine object) {
		if (UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return caseBehavior(object);
	}

	@Override
	public Boolean caseStructuralFeature(StructuralFeature object) {
		if (UMLPackage.Literals.STRUCTURAL_FEATURE__IS_READ_ONLY.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(caseFeature(object).booleanValue() || caseTypedElement(object).booleanValue()
				|| caseMultiplicityElement(object).booleanValue());
	}

	@Override
	public Boolean caseStructuredClassifier(StructuredClassifier object) {
		if (UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(caseClassifier(object).booleanValue());
	}

	@Override
	public Boolean caseTemplateableElement(TemplateableElement object) {
		return caseElement(object);
	}

	@Override
	public Boolean caseTemplateBinding(TemplateBinding object) {
		return caseDirectedRelationship(object);
	}

	@Override
	public Boolean caseTransition(Transition object) {
		if (UMLPackage.Literals.TRANSITION__KIND.equals(feature)
				|| UMLPackage.Literals.TRANSITION__TRIGGER.equals(feature)
				|| UMLPackage.Literals.TRANSITION__EFFECT.equals(feature)
				|| UMLPackage.Literals.TRANSITION__GUARD.equals(feature)
				|| UMLPackage.Literals.TRANSITION__SOURCE.equals(feature)
				|| UMLPackage.Literals.TRANSITION__TARGET.equals(feature)) {
			return new Boolean(true);
		} else if (UMLPackage.Literals.NAMESPACE__OWNED_RULE.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(
				caseNamespace(object).booleanValue() || caseRedefinableElement(object).booleanValue());
	}

	@Override
	public Boolean caseType(Type object) {
		return casePackageableElement(object);
	}

	@Override
	public Boolean caseTypedElement(TypedElement object) {
		if (UMLPackage.Literals.TYPED_ELEMENT__TYPE.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(caseNamedElement(object).booleanValue());
	}

	@Override
	public Boolean caseUsage(Usage object) {
		return caseDependency(object);
	}

	@Override
	public Boolean caseUseCase(UseCase object) {
		if (UMLPackage.Literals.USE_CASE__SUBJECT.equals(feature)) {
			return new Boolean(true);
		}
		return caseBehavioredClassifier(object);
	}

	@Override
	public Boolean caseVertex(Vertex object) {
		return caseNamedElement(object);
	}

	@Override
	public Boolean defaultCase(EObject object) {
		return new Boolean(true);
	}
}
