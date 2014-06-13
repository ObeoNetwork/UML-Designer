/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.providers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Duration;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Expression;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Interval;
import org.eclipse.uml2.uml.LiteralSpecification;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.Vertex;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;

/**
 * Provides the filter used by the plugin.xml to assign
 * {@link GeneralPropertiesEditionPart} to an element.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class GeneralEditionFilter implements IFilter {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	public boolean select(Object toTest) {
		EObject eObj = EEFUtils.resolveSemanticObject(toTest);
		return eObj != null
				&& (eObj instanceof org.eclipse.uml2.uml.Package
						|| eObj instanceof Class || eObj instanceof Interface
						|| eObj instanceof DataType
						|| eObj instanceof PrimitiveType
						|| eObj instanceof Enumeration
						|| eObj instanceof EnumerationLiteral
						|| eObj instanceof Operation
						|| eObj instanceof Parameter
						|| eObj instanceof Property
						|| eObj instanceof Generalization
						|| eObj instanceof Association
						|| eObj instanceof AssociationClass
						|| eObj instanceof Dependency
						|| eObj instanceof Component
						|| eObj instanceof Connector
						|| eObj instanceof Transition || eObj instanceof Port
						|| eObj instanceof Stereotype || eObj instanceof Actor
						|| eObj instanceof Artifact
						|| eObj instanceof CallOperationAction
						|| eObj instanceof Collaboration
						|| eObj instanceof Comment
						|| eObj instanceof ConnectorEnd
						|| eObj instanceof ActivityEdge
						|| eObj instanceof DataStoreNode
						|| eObj instanceof ControlNode
						|| eObj instanceof Extend || eObj instanceof FinalState
						|| eObj instanceof Include || eObj instanceof Pin
						|| eObj instanceof OpaqueAction
						|| eObj instanceof Vertex || eObj instanceof Region
						|| eObj instanceof UseCase
						|| eObj instanceof ConnectorEnd
						|| eObj instanceof State || eObj instanceof Constraint
						|| eObj instanceof Duration || eObj instanceof Interval
						|| eObj instanceof Expression
						|| eObj instanceof InstanceValue
						|| eObj instanceof LiteralSpecification
						|| eObj instanceof InstanceSpecification
						|| eObj instanceof Trigger || eObj instanceof TimeEvent
						|| eObj instanceof ChangeEvent
						|| eObj instanceof TimeExpression
						|| eObj instanceof MessageEvent
						|| eObj instanceof CallBehaviorAction
						|| eObj instanceof StateMachine
						|| eObj instanceof Region
						|| eObj instanceof Interaction
						|| eObj instanceof OpaqueBehavior || eObj instanceof Activity);
	}

}
