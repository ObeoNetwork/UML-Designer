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
package org.obeonetwork.dsl.uml2.design.internal.services;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityContent;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralReal;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageMerge;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
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
	public Boolean caseAcceptEventAction(AcceptEventAction object) {
		if (UMLPackage.Literals.ACCEPT_EVENT_ACTION__IS_UNMARSHALL.equals(feature)
				|| UMLPackage.Literals.ACCEPT_EVENT_ACTION__TRIGGER.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseActivity(Activity object) {
		if (UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseActivityContent(ActivityContent object) {
		if (UMLPackage.Literals.ACTIVITY_CONTENT___CONTAINING_ACTIVITY.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseActivityNode(ActivityNode object) {
		if (UMLPackage.Literals.ACTIVITY_NODE__INCOMING.equals(feature)
				|| UMLPackage.Literals.ACTIVITY_NODE__OUTGOING.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseActor(Actor object) {
		if (UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseArtifact(Artifact object) {
		if (UMLPackage.Literals.ARTIFACT__OWNED_ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.ARTIFACT__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseAssociation(Association object) {
		if (UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		if (UMLPackage.Literals.ASSOCIATION__IS_DERIVED.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseAssociationClass(AssociationClass object) {
		if (UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseBehavior(Behavior object) {
		if (UMLPackage.Literals.BEHAVIOR__IS_REENTRANT.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseBehavioralFeature(BehavioralFeature object) {
		if (UMLPackage.Literals.BEHAVIORAL_FEATURE__IS_ABSTRACT.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseBehaviorExecutionSpecification(BehaviorExecutionSpecification object) {
		if (UMLPackage.Literals.BEHAVIOR_EXECUTION_SPECIFICATION__BEHAVIOR.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseCallBehaviorAction(CallBehaviorAction object) {
		if (UMLPackage.Literals.CALL_BEHAVIOR_ACTION__BEHAVIOR.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseClass(Class object) {
		if (UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseClassifier(Classifier object) {
		if (UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.CLASSIFIER__IS_ABSTRACT.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseComment(Comment object) {
		if (UMLPackage.Literals.COMMENT__BODY.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseComponent(Component object) {
		if (UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseConnector(Connector object) {
		if (UMLPackage.Literals.CONNECTOR___GET_KIND.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseDataType(DataType object) {
		if (UMLPackage.Literals.DATA_TYPE__OWNED_ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.DATA_TYPE__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseDependency(Dependency object) {
		if (UMLPackage.Literals.DEPENDENCY__CLIENT.equals(feature)
				|| UMLPackage.Literals.DEPENDENCY__SUPPLIER.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseElement(Element object) {
		if (UMLPackage.Literals.ELEMENT___GET_RELATIONSHIPS.equals(feature)) {
			return new Boolean(true);
		}
		return new Boolean(false);
	}

	@Override
	public Boolean caseEnumeration(Enumeration object) {
		if (UMLPackage.Literals.ENUMERATION__OWNED_LITERAL.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseExecutionSpecification(ExecutionSpecification object) {
		if (UMLPackage.Literals.EXECUTION_SPECIFICATION__START.equals(feature)
				|| UMLPackage.Literals.EXECUTION_SPECIFICATION__FINISH.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseExtend(Extend object) {
		if (UMLPackage.Literals.EXTEND__EXTENDED_CASE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseFeature(Feature object) {
		if (UMLPackage.Literals.FEATURE__IS_STATIC.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseFinalState(FinalState object) {
		if (UMLPackage.Literals.STATE__SUBMACHINE.equals(feature)
				|| UMLPackage.Literals.STATE__ENTRY.equals(feature)
				|| UMLPackage.Literals.STATE__EXIT.equals(feature)
				|| UMLPackage.Literals.STATE__DO_ACTIVITY.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseGeneralization(Generalization object) {
		if (UMLPackage.Literals.GENERALIZATION__GENERAL.equals(feature)
				|| UMLPackage.Literals.GENERALIZATION__SPECIFIC.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseInclude(Include object) {
		if (UMLPackage.Literals.INCLUDE__ADDITION.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseInterface(Interface object) {
		if (UMLPackage.Literals.CLASS__IS_ACTIVE.equals(feature)
				|| UMLPackage.Literals.INTERFACE__OWNED_ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.INTERFACE__OWNED_OPERATION.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseInterfaceRealization(InterfaceRealization object) {
		if (UMLPackage.Literals.INTERFACE_REALIZATION__CONTRACT.equals(feature)) {
			return new Boolean(true);
		}
		if (UMLPackage.Literals.DEPENDENCY__CLIENT.equals(feature)
				|| UMLPackage.Literals.DEPENDENCY__SUPPLIER.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseLifeline(Lifeline object) {
		if (UMLPackage.Literals.LIFELINE__REPRESENTS.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseLiteralBoolean(LiteralBoolean object) {
		if (UMLPackage.Literals.LITERAL_BOOLEAN__VALUE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseLiteralInteger(LiteralInteger object) {
		if (UMLPackage.Literals.LITERAL_INTEGER__VALUE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseLiteralReal(LiteralReal object) {
		if (UMLPackage.Literals.LITERAL_REAL__VALUE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseLiteralString(LiteralString object) {
		if (UMLPackage.Literals.LITERAL_STRING__VALUE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseLiteralUnlimitedNatural(LiteralUnlimitedNatural object) {
		if (UMLPackage.Literals.LITERAL_UNLIMITED_NATURAL__VALUE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseMessage(Message object) {
		if (UMLPackage.Literals.MESSAGE__MESSAGE_SORT.equals(feature)
				|| UMLPackage.Literals.MESSAGE__RECEIVE_EVENT.equals(feature)
				|| UMLPackage.Literals.MESSAGE__SEND_EVENT.equals(feature)
				|| UMLPackage.Literals.MESSAGE__SIGNATURE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseMultiplicityElement(MultiplicityElement object) {
		if (UMLPackage.Literals.MULTIPLICITY_ELEMENT__IS_ORDERED.equals(feature)
				|| UMLPackage.Literals.MULTIPLICITY_ELEMENT__IS_UNIQUE.equals(feature)
				|| UMLPackage.Literals.MULTIPLICITY_ELEMENT__LOWER.equals(feature)
				|| UMLPackage.Literals.MULTIPLICITY_ELEMENT__UPPER.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseNamedElement(NamedElement object) {
		if (UMLPackage.Literals.NAMED_ELEMENT__NAME.equals(feature)
				|| UMLPackage.Literals.NAMED_ELEMENT__VISIBILITY.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseNode(Node object) {
		if (UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseOperation(Operation object) {
		if (UMLPackage.Literals.OPERATION__IS_QUERY.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean casePackage(org.eclipse.uml2.uml.Package object) {
		if (UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean casePackageImport(PackageImport object) {
		if (UMLPackage.Literals.PACKAGE_IMPORT__IMPORTED_PACKAGE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean casePackageMerge(PackageMerge object) {
		if (UMLPackage.Literals.PACKAGE_MERGE__MERGED_PACKAGE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
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
		return null;
	}

	@Override
	public Boolean casePrimitiveType(PrimitiveType object) {
		if (UMLPackage.Literals.DATA_TYPE__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.DATA_TYPE__OWNED_ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseProfileApplication(ProfileApplication object) {
		if (UMLPackage.Literals.PROFILE_APPLICATION__APPLIED_PROFILE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseProperty(Property object) {
		if (UMLPackage.Literals.PROPERTY__IS_DERIVED.equals(feature)
				|| UMLPackage.Literals.PROPERTY__IS_ID.equals(feature)
				|| UMLPackage.Literals.PROPERTY__AGGREGATION.equals(feature)
				|| UMLPackage.Literals.PROPERTY__DEFAULT_VALUE.equals(feature)
				|| UMLPackage.Literals.PROPERTY__QUALIFIER.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseState(State object) {
		if (UMLPackage.Literals.STATE__SUBMACHINE.equals(feature)
				|| UMLPackage.Literals.STATE__ENTRY.equals(feature)
				|| UMLPackage.Literals.STATE__EXIT.equals(feature)
				|| UMLPackage.Literals.STATE__DO_ACTIVITY.equals(feature)) {
			return new Boolean(true);
		}
		if (UMLPackage.Literals.VERTEX__CONTAINER.equals(feature) && object.getSubmachine() != null) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseStateMachine(StateMachine object) {
		if (UMLPackage.Literals.CLASS__OWNED_OPERATION.equals(feature)
				|| UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE.equals(feature)
				|| UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseStructuralFeature(StructuralFeature object) {
		if (UMLPackage.Literals.STRUCTURAL_FEATURE__IS_READ_ONLY.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseStructuredClassifier(StructuredClassifier object) {
		if (UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseTemplateBinding(TemplateBinding object) {
		if (UMLPackage.Literals.TEMPLATE_BINDING__BOUND_ELEMENT.equals(feature)
				|| UMLPackage.Literals.TEMPLATE_BINDING__SIGNATURE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseTemplateParameterSubstitution(TemplateParameterSubstitution object) {
		if (UMLPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION__FORMAL.equals(feature)
				|| UMLPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION__ACTUAL.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseTransition(Transition object) {
		if (UMLPackage.Literals.TRANSITION__KIND.equals(feature)
				|| UMLPackage.Literals.TRANSITION__TRIGGER.equals(feature)
				|| UMLPackage.Literals.TRANSITION__EFFECT.equals(feature)
				|| UMLPackage.Literals.TRANSITION__GUARD.equals(feature)
				|| UMLPackage.Literals.TRANSITION__SOURCE.equals(feature)
				|| UMLPackage.Literals.TRANSITION__TARGET.equals(feature)
				|| UMLPackage.Literals.NAMESPACE__OWNED_RULE.equals(feature)) {
			return new Boolean(true);
		}
		if (UMLPackage.Literals.NAMED_ELEMENT__NAME.equals(feature)
				|| UMLPackage.Literals.NAMED_ELEMENT__VISIBILITY.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}

	@Override
	public Boolean caseTypedElement(TypedElement object) {
		if (UMLPackage.Literals.TYPED_ELEMENT__TYPE.equals(feature)) {
			return new Boolean(true);
		}
		return null;
	}

	@Override
	public Boolean caseUseCase(UseCase object) {
		if (UMLPackage.Literals.USE_CASE__SUBJECT.equals(feature)) {
			return new Boolean(true);
		}
		if (UMLPackage.Literals.CLASSIFIER__ATTRIBUTE.equals(feature)) {
			return new Boolean(false);
		}
		return null;
	}
}
