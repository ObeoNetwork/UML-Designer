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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Utility services to manage label edition on classes.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public final class ClassServices {

	/**
	 * Hidden constructor.
	 */
	private ClassServices() {
	}

	/**
	 * Parse the edited label content and update the underlying {@link Class}.
	 * 
	 * @param aClass
	 *            the context {@link Class} object.
	 * @param inputLabel
	 *            the user edited label content.
	 * @return Class name
	 */
	public static String parseInputLabel(org.eclipse.uml2.uml.Class aClass, String inputLabel) {
		String result = inputLabel;
		final String validLabel = "[a-zA-Z_0-9]+((\\s)*<[a-zA-Z_0-9]+(,(\\s)*[a-zA-Z_0-9]+)*>)?";
		final String templatedLabel = "[a-zA-Z_0-9]+((\\s)*<[a-zA-Z_0-9]+(,(\\s)*[a-zA-Z_0-9]+)*>)";

		if (inputLabel.matches(validLabel)) {
			if (inputLabel.matches(templatedLabel)) {
				final String[] splittedLabel = inputLabel.split("(\\s)*<");
				result = splittedLabel[0].trim();

				// rename the template parameter
				String templateSignatureLabel = splittedLabel[1].replace(">", "");
				String[] templateParamLabels = templateSignatureLabel.split(",(\\s)*");
				TemplateSignature templateSignature = aClass.getOwnedTemplateSignature();
				if (templateSignature == null) {
					// create a template signature
					templateSignature = aClass.createOwnedTemplateSignature();
				}
				List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
				for (int i = 0; i < templateParamLabels.length; i++) {
					String templateParamLabel = templateParamLabels[i].trim();
					try {
						TemplateParameter templateParameter = templateParameters.get(i);
						if (templateParameter.getParameteredElement() instanceof NamedElement) {
							((NamedElement)templateParameter.getParameteredElement())
									.setName(templateParamLabel);
						}
					} catch (IndexOutOfBoundsException e) {
						ClassifierTemplateParameter createNewClassifierTemplateParameter = createNewClassifierTemplateParameter(
								aClass, templateSignature, templateParamLabel);

						Session sess = SessionManager.INSTANCE.getSession(templateSignature);
						Collection<Setting> inverseReferences = sess.getSemanticCrossReferencer()
								.getInverseReferences(templateSignature);
						for (Setting setting : inverseReferences) {
							if (setting.getEObject() instanceof TemplateBinding) {
								TemplateBinding templateBinding = (TemplateBinding)setting.getEObject();
								TemplateParameterSubstitution templateParameterSubstitution = templateBinding
										.createParameterSubstitution();
								templateParameterSubstitution.setFormal(createNewClassifierTemplateParameter);
							}
						}
					}
				}
				while (templateParamLabels.length < templateParameters.size()) {
					// remove related binding
					TemplateParameter templateParameterToRemove = templateParameters.get(templateParameters
							.size() - 1);
					Session sess = SessionManager.INSTANCE.getSession(templateParameterToRemove);
					Collection<Setting> inverseReferences = sess.getSemanticCrossReferencer()
							.getInverseReferences(templateParameterToRemove);
					for (Setting setting : inverseReferences) {
						if (setting.getEObject() instanceof TemplateParameterSubstitution) {
							TemplateParameterSubstitution templateParameterSubstitution = (TemplateParameterSubstitution)setting
									.getEObject();
							TemplateBinding templateBinding = templateParameterSubstitution
									.getTemplateBinding();
							templateBinding.getParameterSubstitutions().remove(templateParameterSubstitution);
							templateParameterSubstitution = null;
						}
					}
					templateSignature.getParameters().remove(templateParameterToRemove);
				}
			} else {
				TemplateSignature templateSignature = aClass.getOwnedTemplateSignature();
				if (templateSignature != null) {

					// delete templateBinding
					Session sess = SessionManager.INSTANCE.getSession(templateSignature);
					Collection<Setting> inverseReferences = sess.getSemanticCrossReferencer()
							.getInverseReferences(templateSignature);
					for (Setting setting : inverseReferences) {
						if (setting.getEObject() instanceof TemplateBinding) {
							TemplateBinding templateBinding = (TemplateBinding)setting.getEObject();
							templateBinding.getParameterSubstitutions().clear();
							templateBinding.setSignature(null);
							TemplateableElement boundElement = templateBinding.getBoundElement();
							boundElement.setOwnedTemplateSignature(null);
							boundElement.getTemplateBindings().clear();
						}
					}

					// delete templateSignature and templateParameter
					templateSignature.getParameters().clear();
					templateSignature.getOwnedParameters().clear();
					aClass.setTemplateParameter(null);
					aClass.setOwnedTemplateSignature(null);
				}
			}
		}
		return result;
	}

	private static ClassifierTemplateParameter createNewClassifierTemplateParameter(
			ParameterableElement context, TemplateSignature templateSignature, String newTemplateClassName) {
		ClassifierTemplateParameter result = UMLFactory.eINSTANCE.createClassifierTemplateParameter();
		Class newGenericClass = UMLFactory.eINSTANCE.createClass();
		newGenericClass.setName(newTemplateClassName);
		result.setOwnedDefault(newGenericClass);
		result.setParameteredElement(newGenericClass);

		templateSignature.getOwnedParameters().add(result);
		return result;
	}
}
