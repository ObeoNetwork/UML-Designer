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
package org.obeonetwork.dsl.uml2.core.internal.services;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLSwitch;

/**
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class PropertiesLabelSwitch extends UMLSwitch<String> {
	final static String ATTRIBUTES_LABEL = "Attributes";

	final static String LITERALS_LABEL = "Literals";

	final static String OPERATIONS_LABEL = "Operations";

	private final EStructuralFeature structuralFeature;

	/**
	 * Constructor.
	 *
	 * @param structuralFeature
	 *            feature
	 */
	public PropertiesLabelSwitch(EStructuralFeature structuralFeature) {
		this.structuralFeature = structuralFeature;
	}

	@Override
	public String caseClass(Class object) {
		if (UMLPackage.eINSTANCE.getClass_OwnedOperation().equals(structuralFeature)) {
			return OPERATIONS_LABEL;
		}
		return caseStructuredClassifier(object);
	}

	@Override
	public String caseClassifier(Classifier object) {
		return defaultCase(object);
	}

	@Override
	public String caseDataType(DataType object) {
		if (UMLPackage.eINSTANCE.getDataType_OwnedAttribute().equals(structuralFeature)) {
			return ATTRIBUTES_LABEL;
		} else if (UMLPackage.eINSTANCE.getDataType_OwnedOperation().equals(structuralFeature)) {
			return OPERATIONS_LABEL;
		}
		return caseClassifier(object);
	}

	@Override
	public String caseEnumeration(Enumeration object) {
		if (UMLPackage.eINSTANCE.getEnumeration_OwnedLiteral().equals(structuralFeature)) {
			return LITERALS_LABEL;
		}
		return caseDataType(object);
	}

	@Override
	public String caseInterface(Interface object) {
		if (UMLPackage.eINSTANCE.getInterface_OwnedAttribute().equals(structuralFeature)) {
			return ATTRIBUTES_LABEL;
		} else if (UMLPackage.eINSTANCE.getInterface_OwnedOperation().equals(structuralFeature)) {
			return OPERATIONS_LABEL;
		}
		return caseClassifier(object);
	}

	@Override
	public String casePrimitiveType(PrimitiveType object) {
		return caseDataType(object);
	}

	@Override
	public String caseStructuredClassifier(StructuredClassifier object) {
		if (UMLPackage.eINSTANCE.getStructuredClassifier_OwnedAttribute().equals(structuralFeature)) {
			return ATTRIBUTES_LABEL;
		}
		return caseClassifier(object);
	}

	@Override
	public String defaultCase(EObject object) {
		String text = getText(object, structuralFeature);
		text = separateWord(text);
		return text;
	}

	/**
	 * Get label for feature.
	 *
	 * @param object
	 *            element
	 * @param eStructuralFeature
	 *            feature
	 * @return label
	 */
	private String getText(EObject object, EStructuralFeature eStructuralFeature) {
		final Object value = object.eGet(eStructuralFeature);
		String label = eStructuralFeature.getName();
		if (value instanceof Boolean) {
			if (label.startsWith("is")) { //$NON-NLS-1$
				label = label.substring(2, label.length());
			}
		}
		return label;
	}

	/**
	 * Separate words.
	 *
	 * @param entry
	 *            string
	 * @return string
	 */
	private String separateWord(String entry) {
		String ret = ""; //$NON-NLS-1$
		int index = 0;
		while (index < entry.length()) {
			final char character = entry.charAt(index);
			if (index == 0 && Character.isLowerCase(character)) {
				ret = ret + Character.toUpperCase(character);
			} else if (index > 0 && !Character.isLowerCase(character)
					&& Character.isLowerCase(entry.charAt(index - 1))) {
				ret = ret + " "; //$NON-NLS-1$
				ret = ret + entry.charAt(index);
			} else {
				ret = ret + entry.charAt(index);
			}
			index++;
		}
		return ret;
	}
}
