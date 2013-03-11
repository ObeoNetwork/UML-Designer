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
package org.obeonetwork.dsl.uml2.properties.service;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.obeonetwork.dsl.uml2.design.services.LabelServices;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class TableLabelService {
	private final static String EMPTY = "";
	private EObject source = null;
	private final LabelServices labelServices = new LabelServices();

	public enum TableColumnName {
		NAME("Name"), TYPE("Type"), DEFAULT_VALUE("Default value"), VISIBILITY(
				"Visibility"), IS_STATIC("Static"), MULTIPLICITY("Multiplicity"), IS_ABSTRACT(
				"Abstract"), PARAMETERS("Parameters"), DIRECTION("Direction"), STEREOTYPE(
				"Stereotype"), PROFILE("Profile"), REQUIRED("Required"), OTHER_RELATED_ELEMENTS(
				"Other Related Element(s)");

		private String label;

		TableColumnName(String label) {
			this.label = label;
		}

		public String label() {
			return label;
		}
	}

	public TableLabelService() {
	}

	public TableLabelService(EObject source) {
		this.source = source;
	}

	public String caseStereotype(Object object) {
		if (object instanceof Stereotype)
			return ((Stereotype) object).getName();
		throw new IllegalArgumentException();
	}

	public String caseProfile(Object object) {
		if (object instanceof Stereotype)
			return ((Stereotype) object).getProfile().getName();
		throw new IllegalArgumentException();
	}

	public String caseRequired(Object object) {
		if (object instanceof Stereotype && source != null
				&& source instanceof Element)
			return String.valueOf(((Element) source)
					.isStereotypeRequired((Stereotype) object));
		throw new IllegalArgumentException();
	}

	public String caseIsStatic(Object object) {
		if (object instanceof Feature)
			return String.valueOf(((Feature) object).isStatic());
		throw new IllegalArgumentException();
	}

	public String caseParameters(Object object) {
		if (object instanceof Operation) {
			List<Parameter> parameters = ((Operation) object)
					.getOwnedParameters();
			StringBuffer ownedParameter = new StringBuffer();
			for (int i = 0; i < parameters.size(); i++) {
				Parameter parameter = parameters.get(i);
				if (!ParameterDirectionKind.RETURN_LITERAL.equals(parameter
						.getDirection())) {
					ownedParameter.append(parameter.getName());
					ownedParameter.append(":");
					if (parameter.getType() != null)
						ownedParameter.append(parameter.getType().getName());
					if ((i == 0 && parameters.size() > 1)
							|| i < parameters.size() - 1) {
						ownedParameter.append(" ");
					}
				}
			}
			return ownedParameter.toString();
		}
		throw new IllegalArgumentException();
	}

	public String caseIsAbstract(Object object) {
		if (object instanceof Classifier)
			return String.valueOf(((Classifier) object).isAbstract());
		if (object instanceof BehavioralFeature)
			return String.valueOf(((BehavioralFeature) object).isAbstract());
		throw new IllegalArgumentException();
	}

	public String caseVisibility(Object object) {
		if (object instanceof NamedElement)
			return ((NamedElement) object).getVisibility().getName();
		throw new IllegalArgumentException();
	}

	public String caseDirection(Object object) {
		if (object instanceof Parameter)
			return ((Parameter) object).getDirection().getName();
		throw new IllegalArgumentException();
	}

	public String caseDefaultValue(Object object) {
		if (object instanceof Property) {
			if (((Property) object).getDefaultValue() != null)
				return ((Property) object).getDefault();
			return EMPTY;
		}
		throw new IllegalArgumentException();
	}

	public String caseName(Object object) {
		if (object instanceof Element) {
			String label = labelServices.computeUmlLabel((Element) object);
			if (label != null && !label.isEmpty() && !"null".equals(label))
				return label;
			return EMPTY;
		}
		throw new IllegalArgumentException();
	}

	public String caseType(Object object) {
		if (object instanceof Operation) {
			Type type = ((Operation) object).getType();
			if (type != null)
				return type.getName();
			return "void";
		}
		if (object instanceof TypedElement) {
			Type type = ((TypedElement) object).getType();
			if (type != null)
				return type.getName();
			return EMPTY;
		}
		if (object instanceof Relationship) {
			return ((Relationship) object).eClass().getName();
		}
		throw new IllegalArgumentException();
	}

	public String caseMultiplicity(Object object) {
		if (object instanceof MultiplicityElement) {
			int lower = ((MultiplicityElement) object).getLower();
			StringBuffer label = new StringBuffer(lower + "..");

			int upper = ((MultiplicityElement) object).getUpper();
			if (upper < 0)
				label.append("*");
			else
				label.append(upper);
			return label.toString();
		}
		throw new IllegalArgumentException();
	}

	public String caseOtherRelatedElements(Object object) {
		if (object instanceof Relationship) {
			List<Element> elements = ((Relationship) object)
					.getRelatedElements();
			List<Element> elementsWithoutSource = new ArrayList<Element>();
			elementsWithoutSource.addAll(elements);
			elementsWithoutSource.remove(source);
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < elementsWithoutSource.size(); i++) {
				Element element = elementsWithoutSource.get(i);
				result.append(labelServices.computeUmlLabel(element));
				if (i < elementsWithoutSource.size() - 1)
					result.append(",");
			}
			return result.toString();
		}
		throw new IllegalArgumentException();
	}

}
