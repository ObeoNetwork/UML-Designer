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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.InvocationAction;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.UMLFactory;

public class ActivityServices {
	
	/**
	 * Retrieves the child {@link ActivityPartition} from either {@link Activity} or {@link ActivityPartition} context object.
	 * This is used has the semantic candidates expression of AD_ActivityPartition container mapping.
	 * 
	 * @param context
	 * @return
	 */
	public EList<ActivityPartition> getActivityPartitions(Element context) {
		if(context instanceof Activity) {
			return ((Activity)context).getPartitions();
		} else if(context instanceof ActivityPartition) {
			return ((ActivityPartition)context).getSubpartitions();
		}
		
		return null;
	}
	
	/**
	 * Return the given context object if it's an {@link Activity} or find it into the parent elements.<br>
	 * This is used to compute the context of Activity diagram creation tools. Activity Nodes & Actions
	 * can be created within either an {@link Activity} or an {@link ActivityPartition}, but they are always contained by
	 * the parent {@link Activity}.
	 * 
	 * @param context
	 * @return
	 */
	public Activity findParentActivity(Element context) {
		if(context instanceof Activity) {
			return (Activity) context;
		}
		
		if(context.eContainer() != null) {
			return findParentActivity((Element) context.eContainer());
		}
		
		return null;
	}
	
	/**
	 * Get the child {@link ActivityNode} elements for the given context.
	 * This is used to retrieve the semantic candidates of {@link ActivityNode} mappings
	 * from the context semantic object which can be either the {@link Activity} or an {@link ActivityPartition}.
	 * 
	 * @param context
	 * @return
	 */
	public Collection<ActivityNode> getActivityNodes(Element context) {
		Collection<ActivityNode> childNodes = Collections.emptyList();
		
		if (context instanceof Activity){
			EList<ActivityNode> allActivityNodes = ((Activity)context).getNodes();
			childNodes = new ArrayList<ActivityNode>(allActivityNodes);
			
			for (ActivityNode activityNode : allActivityNodes) {
				if(activityNode.getInPartitions().size() > 0)
					childNodes.remove(activityNode);
			}
		} else if (context instanceof ActivityPartition){
			childNodes = ((ActivityPartition)context).getNodes();
		}
		
		return childNodes;
	}

	public InputPin createInputPin(Action context) {
		InputPin pin = UMLFactory.eINSTANCE.createInputPin();
		pin.setName("Input_" + context.getInputs().size());
		
		if(context instanceof InvocationAction) {
			((InvocationAction)context).getArguments().add(pin);
		} else if (context instanceof OpaqueAction) {
			((OpaqueAction)context).getInputValues().add(pin);
		} else {
			throw new UnsupportedOperationException("Can't create InputPin for context of type: " + context.eClass().getName());
		}
		
		return pin;
	}
	
	public OutputPin createOutputPin(Action context) {
		OutputPin pin = UMLFactory.eINSTANCE.createOutputPin();
		pin.setName("Output_" + context.getOutputs().size());
		
		if (context instanceof CallAction) {
			((CallAction)context).getResults().add(pin);
		} else if (context instanceof OpaqueAction) {
			((OpaqueAction)context).getOutputValues().add(pin);
		} else {
			throw new UnsupportedOperationException("Can't create InputPin for context of type: " + context.eClass().getName());
		}
		
		return pin;
	}
	
	/**
	 * Manages dispatch to corresponding services.
	 * This is done to workaround polymorphism issue with Java service from a deployed VP.
	 */
	public Element dropNode(Element context, Element node) {
		if(node instanceof ActivityNode) {
			return dropNode(context, (ActivityNode)node);
		} else if (node instanceof ActivityPartition) {
			return dropNode(context, (ActivityPartition)node);
		}
		
		return null;
	}
	
	/**
	 * Manages the drag & drop of {@link ActivityNode}.
	 * 
	 * @param context object ({@link Activity} or {@link ActivityPartition}) on which to drop the node
	 * @param node the node to drop
	 * @return the given node object
	 */
	private ActivityNode dropNode(Element context, ActivityNode node) {
		node.getInPartitions().clear();
		
		if (context instanceof ActivityPartition) {
			node.getInPartitions().add((ActivityPartition) context);
		}
		
		return node;
	}
	
	/**
	 * Manages the drag & drop of {@link ActivityPartition}.
	 * 
	 * @param context object ({@link Activity} or {@link ActivityPartition}) on which to drop the node
	 * @param partition the partition to drop
	 * @return the given partition object
	 */
	private ActivityPartition dropNode(Element context, ActivityPartition partition) {
		if(context instanceof Activity) {
			((Activity)context).getPartitions().add(partition);
		} else if (context instanceof ActivityPartition) {
			((ActivityPartition)context).getSubpartitions().add(partition);
		}
		return partition;
	}
}
