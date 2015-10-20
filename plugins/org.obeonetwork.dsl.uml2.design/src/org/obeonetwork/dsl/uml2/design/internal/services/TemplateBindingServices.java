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
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.List;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Utility services to manage label edition on templateBindings.
 *
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public final class TemplateBindingServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final TemplateBindingServices INSTANCE = new TemplateBindingServices();

	/**
	 * Hidden constructor.
	 */
	private TemplateBindingServices() {
	}

	/**
	 * Create new classifier template parameter.
	 *
	 * @param context
	 *            Context
	 * @param templateSignature
	 *            Template signature
	 * @param newTemplateClassName
	 *            Template class name
	 * @return Template parameter
	 */
	private ClassifierTemplateParameter createNewClassifierTemplateParameter(ParameterableElement context,
			TemplateSignature templateSignature, String newTemplateClassName) {
		final ClassifierTemplateParameter result = UMLFactory.eINSTANCE.createClassifierTemplateParameter();
		final Class newGenericClass = UMLFactory.eINSTANCE.createClass();
		newGenericClass.setName(newTemplateClassName);
		result.setOwnedDefault(newGenericClass);
		result.setParameteredElement(newGenericClass);

		templateSignature.getOwnedParameters().add(result);
		return result;
	}

	/**
	 * Parse the edited label content and update the underlying {@link TemplateBinding}.
	 *
	 * @param templateBinding
	 *            the context {@link TemplateBinding} object.
	 * @param inputLabel
	 *            the user edited label content.
	 * @return TemplateBinding name
	 */
	public String parseInputLabel(TemplateBinding templateBinding, String inputLabel) {
		final String result = inputLabel;
		final String validLabel = "[a-zA-Z_0-9]+(\\s)*->(\\s)*[a-zA-Z_0-9\\?]+((\\s)*,(\\s)*[a-zA-Z_0-9]+(\\s)*->(\\s)*[a-zA-Z_0-9\\?]+)*"; //$NON-NLS-1$

		final TemplateSignature templateSignature = templateBinding.getSignature();
		if (inputLabel.matches(validLabel) && templateSignature != null) {
			final String[] splittedSubstitutionLabels = inputLabel.split("(\\s)*,"); //$NON-NLS-1$
			int i = 0;
			final List<TemplateParameterSubstitution> paramSubstitutions = templateBinding
					.getParameterSubstitutions();
			for (final String substitutionLabel : splittedSubstitutionLabels) {
				final String[] substitutionArray = substitutionLabel.split("(\\s)*->(\\s)*"); //$NON-NLS-1$
				final String formalLabel = substitutionArray[0].trim();
				final String actualLabel = substitutionArray[1].trim();

				// override or create a templateParamSubstitution at the i index
				if (paramSubstitutions.size() > i) {
					final TemplateParameterSubstitution templateParamSubstitution = paramSubstitutions.get(i);
					// rename or create the formal template
					final TemplateParameter formal = templateParamSubstitution.getFormal();
					if (formal == null) {
						if (templateSignature.getOwnedParameters().size() > i) {
							final TemplateParameter templateParam = templateSignature.getOwnedParameters()
									.get(i);
							templateParamSubstitution.setFormal(templateParam);
						} else {
							final ClassifierTemplateParameter createTemplateParam = createNewClassifierTemplateParameter(
									(ParameterableElement)templateSignature.getTemplate(), templateSignature,
									formalLabel);

							templateParamSubstitution.setFormal(createTemplateParam);
						}

					} else {
						final ParameterableElement ownedDefault = formal.getOwnedDefault();
						if (ownedDefault instanceof NamedElement) {
							((NamedElement)ownedDefault).setName(formalLabel);
						}
					}
					// set or replace the actual binding
					if ("?".equals(actualLabel)) { //$NON-NLS-1$
						templateParamSubstitution.setActual(null);
					} else {
						final Type foundType = ElementServices.INSTANCE.findTypeByName(templateBinding,
								actualLabel);
						if (foundType != null) {
							templateParamSubstitution.setActual(foundType);
						}
					}
				} else {
					break;
				}
				i++;
			}
		}
		return result;
	}
}
