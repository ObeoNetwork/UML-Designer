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
package org.obeonetwork.dsl.uml2.properties.components;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.uml2.types.TypesPackage;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;
import org.obeonetwork.dsl.uml2.properties.uml.components.PinPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * Custom component to override the edition of upper/lower bound on pins.
 * 
 * @author Melanie Bats <a
 *         href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class PinCustomPropertiesEditionComponent extends
		PinPropertiesEditionComponent {

	public PinCustomPropertiesEditionComponent(
			PropertiesEditingContext editingContext, EObject pin,
			String editing_mode) {
		super(editingContext, pin, editing_mode);
	}

	@Override
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		Pin pin = (Pin) semanticObject;
		if (UmlViewsRepository.General.lowerValue == event.getAffectedEditor()) {
			ValueSpecification lower = pin.getLowerValue();
			LiteralInteger lowerValue = null;
			if (lower == null) {
				lowerValue = UMLFactory.eINSTANCE.createLiteralInteger();
			} else {
				if (lower instanceof LiteralInteger) {
					lowerValue = (LiteralInteger) lower;
				}
			}
			if (lowerValue != null) {
				lowerValue.setValue((EEFConverterUtil.createIntFromString(
						TypesPackage.Literals.INTEGER,
						(String) event.getNewValue())));
				pin.setLowerValue(lowerValue);
				pin.setLower(lowerValue.getValue());
			}
		} else if (UmlViewsRepository.General.upperValue == event
				.getAffectedEditor()) {
			ValueSpecification upper = pin.getUpperValue();
			LiteralUnlimitedNatural upperValue = null;
			if (upper == null) {
				upperValue = UMLFactory.eINSTANCE
						.createLiteralUnlimitedNatural();
			} else {
				if (upper instanceof LiteralUnlimitedNatural) {
					upperValue = (LiteralUnlimitedNatural) upper;
				}
			}
			if (upperValue != null) {
				upperValue.setValue((EEFConverterUtil.createIntFromString(
						TypesPackage.Literals.UNLIMITED_NATURAL,
						(String) event.getNewValue())));
				pin.setUpperValue(upperValue);
				pin.setUpperBound(upperValue);
				pin.setUpper(upperValue.getValue());
			}
		} else {
			super.updateSemanticModel(event);
		}
	}

}
