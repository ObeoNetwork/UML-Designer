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
package org.obeonetwork.dsl.uml2.design.services.internal;

import java.util.Iterator;

import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.obeonetwork.dsl.uml2.design.services.UMLServices;

public class DisplayLabelSwitch  extends UMLSwitch<String> implements ILabelConstants {
	
	@Override
	public String caseActivityEdge(ActivityEdge object) {
		ValueSpecification value = object.getGuard();
		
		if(value instanceof OpaqueExpression) {
			String expr = ((OpaqueExpression)value).getBodies().get(0);
			if(expr != null && !"".equalsIgnoreCase(expr) && !"true".equalsIgnoreCase(expr) && !"1".equalsIgnoreCase(expr)) {
				return "[" + expr + "]";
			}
		}
		
		return "";
	}

	@Override
	public String caseTransition(Transition object) {
		String result = object.getName();
		if (result !=null) {
			result += " ";
		} else
		{
			result = "";
		}
			
		Constraint constraint = object.getGuard();
		if (constraint != null) {
			ValueSpecification value = constraint.getSpecification();
			
			if(value instanceof OpaqueExpression && ((OpaqueExpression) value).getBodies().size() > 0) {
				result += "[" ;
				Iterator<String> it = ((OpaqueExpression) value).getBodies().iterator();
				while (it.hasNext()){
					String expr = it.next();
					if(expr != null && !"".equalsIgnoreCase(expr) && !"true".equalsIgnoreCase(expr) && !"1".equalsIgnoreCase(expr)) {
						result += expr;
					}
					if (it.hasNext())
					{
						result += ",";
					}
					
				}
				result += "]";
			}
		}
		
		return result;
	}

	public String computeStereotypes(Element element) {
		Iterator<Stereotype> it = element.getAppliedStereotypes().iterator();
		
		if (!it.hasNext()) {
			return "";
		}
		
		StringBuffer stereotypeLabel = new StringBuffer();
		stereotypeLabel.append(OPEN_QUOTE_MARK);
		for (;;) {
			Stereotype appliedStereotype = it.next();

			stereotypeLabel.append(appliedStereotype.getName());
			if(it.hasNext()) {
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
	public String caseProperty(Property object) {
		String label = "";
		if (object.isDerived()) {
			label += "/";			
		}
		label += caseStructuralFeature(object);
		if (object.getDefault() != null && !"".equals(object.getDefault().trim())) {
			// is the label on multiple lines ?
			if (object.getDefault().contains(NL)) {
				label += " = ...";
			} else {
				label += " = " + object.getDefault();
			}
		} else if (object.getDefaultValue() != null && object.getDefaultValue() instanceof InstanceValue) {
			label += " = " + ((InstanceValue)object.getDefaultValue()).getName();
		}
		return label;
	}
	
	@Override
	public String caseOperation(Operation object) {
		StringBuilder label = new StringBuilder(caseNamedElement(object));
		label.append("(");
		
		boolean first = true;
		for (Parameter parameter : object.getOwnedParameters()) {
			if (!ParameterDirectionKind.RETURN_LITERAL.equals(parameter.getDirection())) {
				if (first == false) {
					label.append(", ");
				} else {
					first = false;
				}
				label.append(caseTypedElement(parameter));
			}
		}
		label.append(")");
		if (object.getType() != null) {
			label.append(" : ");
			label.append(object.getType().getName());
		}
		return label.toString();
	}

	@Override
	public String caseStructuralFeature(StructuralFeature object) {
		return caseTypedElement(object) + caseMultiplicityElement(object);
	}

	@Override
	public String caseMultiplicityElement(MultiplicityElement object) {
		if (object.getLower() == 1 && object.getUpper() == 1) {
			// [1..1]
			return "[1]";
		} else if (object.getLower() == 0 && object.getUpper() == -1) {
			// [0..*]
			return "[*]";
		} else {
			String label = "[" + object.getLower() + "..";
			if (object.getUpper() == -1) {
				label += "*]";
			} else {
				label += object.getUpper() + "]";
			}
			return label;
		}
	}

	@Override
	public String caseTypedElement(TypedElement object) {
		if (object.getType() != null) {
			return caseNamedElement(object) + " : " + object.getType().getName();
		} else {
			return caseNamedElement(object) + " : ";
		}
	}
	
	@Override
	public String caseActivityPartition(ActivityPartition object) {
		if(object.getRepresents() instanceof NamedElement) {
			return caseNamedElement(object)  + " : " + ((NamedElement)object.getRepresents()).getName();
		}
		
		return null;
	}
	
	@Override
	public String casePin(Pin object) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(caseTypedElement(object));
		buffer.append(caseMultiplicityElement(object));
		return buffer.toString();
	}
	
	@Override
	public String caseDataStoreNode(DataStoreNode object) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(OPEN_QUOTE_MARK);
		buffer.append("Datastore");
		buffer.append(CLOSE_QUOTE_MARK);
		buffer.append(NL);
		buffer.append(caseNamedElement(object));
		return buffer.toString();
	}

	@Override
	public String caseCallOperationAction(CallOperationAction object) {
		if(object.getOperation() != null) {
			return caseNamedElement(object) + " : " + object.getOperation().getName();
		}
		
		return null;
	}

	@Override
	public String caseOpaqueAction(OpaqueAction object) {
		Iterator<String> it = object.getBodies().iterator();
		
		if(it.hasNext()) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(caseNamedElement(object));
			buffer.append(NL);
			
			while (it.hasNext()) {
				buffer.append(NL);
				buffer.append(it.next());
			}
			
			return buffer.toString();
		}
		
		return null;
	}

	@Override
	public String caseNamedElement(NamedElement object) {
		return computeStereotypes(object) + object.getName();  
	}

	@Override
	public String caseAssociation(Association object) {
		Property source = UMLServices.getSource(object);
		Property target = UMLServices.getTarget(object);
		if (source.isNavigable() && target.isNavigable()) {
			return getAssociationEndLabel(target) + " - " + getAssociationEndLabel(source); 
		} else if (source.isNavigable()) {
			return getAssociationEndLabel(source);
		} else if (target.isNavigable()) {
			return getAssociationEndLabel(target);
		} else {
			return object.getName();
		}
	}
	
	private String getAssociationEndLabel(Property p) {
		StringBuilder sb = new StringBuilder("");
		if (p.isDerived()) {
			sb.append("/");
		}
		sb.append(p.getName());
		sb.append(caseMultiplicityElement(p));
		return sb.toString();
	}

}
