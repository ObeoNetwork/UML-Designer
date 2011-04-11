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

import java.util.Iterator;

import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.util.UMLSwitch;

public class EditLabelServices extends UMLSwitch<Element> {
	public final static String NL = System.getProperty("line.separator");

	public final static String OPEN_QUOTE_MARK = "\u00AB";

	public final static String CLOSE_QUOTE_MARK = "\u00BB";
	
	private String editedLabelContent;
	
	public Element editUmlLabel(Element context, String editedLabelContent) {
		this.editedLabelContent = editedLabelContent;
		
		return doSwitch(context);
	}
	
	@Override
	public Element caseProperty(Property object) {
		PropertyServices.parseInputLabel(object, editedLabelContent);
		return object;
	}

	@Override
	public Element caseOperation(Operation object) {
		OperationServices.parseInputLabel(object, editedLabelContent);
		return object;
	}

	@Override
	public Element caseActivityEdge(ActivityEdge object) {
		OpaqueExpression expr = (OpaqueExpression) object.getGuard();
		if(expr == null) {
			expr = UMLFactory.eINSTANCE.createOpaqueExpression();
			expr.setName(object.getName() + "_guard");
			object.setGuard(expr);
		}

		if (editedLabelContent.matches("\\[.*\\]")){
			// Remove the first & last bracket character.
			editedLabelContent = editedLabelContent.substring(1, editedLabelContent.length()-1);
		}
		
		expr.getBodies().clear();
		expr.getBodies().add(editedLabelContent);
		
		return object;
	}
	
	@Override
	public Element caseTransition(Transition object) {
		OpaqueExpression expr;
		Constraint constraint = object.getGuard();
		if (constraint != null) {
			expr = (OpaqueExpression)constraint.getSpecification();
			if(expr == null) {
				expr = UMLFactory.eINSTANCE.createOpaqueExpression();
				expr.setName(object.getName() + "_guard");
				constraint.setSpecification(expr);
			}
		} else {
			constraint = UMLFactory.eINSTANCE.createConstraint();
			constraint.setName(object.getName() + "_transition");
			expr = UMLFactory.eINSTANCE.createOpaqueExpression();
			expr.setName(object.getName() + "_guard");
			constraint.setSpecification(expr);
			object.setGuard(constraint);
		}
		
		if (editedLabelContent.matches("\\[.*\\]")){
			// Remove the first & last bracket character.
			editedLabelContent = editedLabelContent.substring(1, editedLabelContent.length()-1);
		}
		
		expr.getBodies().clear();
		expr.getBodies().add(editedLabelContent);
		
		return object;
	}

	@Override
	public Element caseNamedElement(NamedElement object) {
		object.setName(editedLabelContent.replace(computeStereotypes(object), ""));
		return object;
	}

	private String computeStereotypes(Element element) {
		Iterator<Stereotype> it = element.getAppliedStereotypes().iterator();

		if (!it.hasNext()) {
			return "";
		}

		StringBuffer stereotypeLabel = new StringBuffer();
		stereotypeLabel.append(OPEN_QUOTE_MARK);
		for (;;) {
			Stereotype appliedStereotype = it.next();

			stereotypeLabel.append(appliedStereotype.getName());
			if (it.hasNext()) {
				stereotypeLabel.append(", ");
			} else {
				break;
			}
		}
		stereotypeLabel.append(CLOSE_QUOTE_MARK);
		stereotypeLabel.append(NL);

		return stereotypeLabel.toString();
	}

	
	@Override
	public Element caseTypedElement(TypedElement object) {
		String[] splittedLabel = editedLabelContent.split("\\s*:\\s*");
		
		if(splittedLabel.length == 1) {
			return caseNamedElement(object);
		} else {
			object.setName(splittedLabel[0]);
			
			// TODO retrieve and set the element type
		}
		
		return object;
	}
	
	@Override
	public Element caseDataStoreNode(DataStoreNode object) {
		editedLabelContent = editedLabelContent.replaceFirst("(<<Datastore>>|\u00ABDatastore\u00BB)\\s*", "");
		
		return caseNamedElement(object);
	}

	@Override
	public Element caseRegion(Region region) {
		// Name of a region is in the form "OwnerName : RegionName"
		int pos = editedLabelContent.indexOf(":");
		if (pos > -1) {
			String ownerName = editedLabelContent.substring(0, pos).trim();
			if (region.getOwner() != null && region.getOwner() instanceof NamedElement) {
				((NamedElement)region.getOwner()).setName(ownerName);
			}
			region.setName(editedLabelContent.substring(pos + 1).trim());
		} else {
			region.setName(editedLabelContent.trim());
		}
		
		return region;
	}
	
}
