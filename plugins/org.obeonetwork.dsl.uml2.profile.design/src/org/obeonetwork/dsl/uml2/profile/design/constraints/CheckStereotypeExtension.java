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
package org.obeonetwork.dsl.uml2.profile.design.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.obeonetwork.dsl.uml2.profile.design.services.GenericUMLProfileTools;

/**
 * This class check the the stereotype extend a metaclass/stereotype or not.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class CheckStereotypeExtension extends AbstractModelConstraint {

	/**
	 * a local contextual information about the validation
	 */
	private IValidationContext localCTX;

	/**
	 * Check the the stereotype extend a metaclass/stereotype or not.
	 * 
	 * @param ctx
	 *            a stereotype to test
	 * @return SuccessStatus if the stereotype ctx extend a metaclass or an
	 *         other stereotype.
	 * @see org.eclipse.emf.validation.AbstractModelConstraint
	 *      #validate(org.eclipse.emf.validation.IValidationContext )
	 */
	@Override
	public IStatus validate(final IValidationContext ctx) {
		this.localCTX = ctx;
		final EObject eObject = ctx.getTarget();
		IStatus result = null;
		if (eObject instanceof Stereotype) {
			final Stereotype stereotype = (Stereotype) eObject;
			// check if the stereotype extend a classifier element
			result = isExtendClassifier(stereotype);
			// check if the stereotype extend a meta-class
			if (result == null) {
				result = isExtandMetaclass(stereotype);
			}
		} else {
			result = ctx.createFailureStatus(GenericUMLProfileTools.getLabel(
					ctx, true));
		}
		return result;
	}

	private IStatus isExtendClassifier(final Stereotype stereotype) {
		for (Generalization target : stereotype.getGeneralizations()) {
			if (target instanceof Generalization) {
				return localCTX.createSuccessStatus();
			}
		}
		return null;
	}

	private IStatus isExtandMetaclass(final Stereotype stereotype) {
		if (!stereotype.getOwnedAttributes().isEmpty()) {
			for (Property property : stereotype.getOwnedAttributes()) {
				final Type propertyType = property.getType();
				if ((propertyType != null) && (propertyType instanceof Class)) {
					if (GenericUMLProfileTools.isMetaclass(propertyType)) {
						return localCTX.createSuccessStatus();
					}
				}
			}
		}
		return localCTX.createFailureStatus(stereotype.getName());
	}
}
