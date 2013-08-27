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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.services.EcoreServices;
import org.obeonetwork.dsl.uml2.design.services.LabelServices;

/**
 * Utility services to manage direct label edition on operations.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public final class OperationServices {

	/**
	 * Hidden constructor.
	 */
	private OperationServices() {
	}

	/**
	 * Parse the edited label content and update the underlying {@link Operation}.
	 * 
	 * @param operation
	 *            the context {@link Operation} object.
	 * @param inputLabel
	 *            the user edited label content.
	 */
	public static void parseInputLabel(Operation operation, String inputLabel) {
		// The discriminating parts in the label are :
		// - name of the operation
		// - list of parameters between parens
		// - a ":" preceding the return type
		// original regexp = ^([^(]*)\(?([^)]*)\)?\s*:([^)]*)$
		final Pattern ptnWithType = Pattern.compile("^([^(]*)\\(?([^)]*)\\)?\\s*:([^)]*)$");
		final Matcher mtchWithType = ptnWithType.matcher(inputLabel.trim());

		boolean matchFound = false;
		boolean typeWasSpecified = false;

		String name = null;
		String parametersInfo = null;
		String returnType = null;

		if (mtchWithType.find()) {
			matchFound = true;
			typeWasSpecified = true;

			name = mtchWithType.group(1).trim();
			parametersInfo = mtchWithType.group(2).trim();
			returnType = mtchWithType.group(3).trim();
			final Pattern ptnTypeInfo = Pattern.compile("^(.*)\\[\\s*((\\S+)\\s*\\.\\.)?\\s*(\\S+)\\s*\\]$");
			final Matcher mtchTypeInfo = ptnTypeInfo.matcher(returnType);

			if (mtchTypeInfo.find()) {
				final String lowerBound = mtchTypeInfo.group(3);
				final String upperBound = mtchTypeInfo.group(4);

				// Checking validity of bounds
				// "-1" and "*" can't be used as a lower bound
				// "0" can't be used as a upper bound
				if ((lowerBound == null || lowerBound.matches("^[0-9]+$"))
						&& upperBound.matches("^[1-9][0-9]*|\\*|-1$")) {
					// Handling multiplicity
					if (lowerBound == null || "".equals(lowerBound)) {
						handleMultiplicity(operation, upperBound, upperBound);
					} else {
						handleMultiplicity(operation, lowerBound, upperBound);
					}
				}
			}

		} else {
			// The pattern with an explicit return type did not match
			// Let's assume the user did not specify the return type
			final Pattern ptnWithoutType = Pattern.compile("^([^(]*)\\(?([^)]*)\\)?");
			final Matcher mtchWithoutType = ptnWithoutType.matcher(inputLabel.trim());
			if (mtchWithoutType.find()) {
				matchFound = true;
				typeWasSpecified = false;
				name = mtchWithoutType.group(1).trim();
				parametersInfo = mtchWithoutType.group(2).trim();
			}
		}

		if (matchFound) {
			// Sets the name of the operation
			operation.setName(name);

			// Handle parameters
			handleParameters(operation, parametersInfo);

			// Handle the return type if it has been specified
			if (typeWasSpecified) {
				handleReturnType(operation, returnType);
			}
		}
	}

	/**
	 * Update the {@link Operation} multiplicity.
	 * 
	 * @param operation
	 *            the context {@link Operation} object.
	 * @param lowerBound
	 *            the lower bound user content
	 * @param upperBound
	 *            the upper bound user content
	 */
	private static void handleMultiplicity(Operation operation, String lowerBound, String upperBound) {
		final Integer lower = LabelServices.convertBound(lowerBound);
		final Integer upper = LabelServices.convertBound(upperBound);

		if (lower != null && upper != null && (lower <= upper || upper == -1)) {
			if (lower == -1) {
				operation.setLower(0);
			} else {
				operation.setLower(lower);
			}
			operation.setUpper(upper);
		}
	}

	/**
	 * Parse the edited label content for parameters part and update the underlying {@link Operation}.
	 * 
	 * @param operation
	 *            the context {@link Operation} object.
	 * @param parametersInfo
	 *            the user edited label content for parameters.
	 */
	private static void handleParameters(Operation operation, String parametersInfo) {
		// Regexp used to extract name and type from a string as "name : type" or "name" or ":type"
		final Pattern ptnParam = Pattern.compile("^([^:]*)\\s*:?\\s*(.*)$");

		// Used to store the name and types of the parameters we'll have to create
		final List<NameAndType> paramsToBeCreated = new ArrayList<NameAndType>();

		// Let's compile information on the parameters
		if (parametersInfo != null && !"".equals(parametersInfo.trim())) {
			final String[] paramInfos = parametersInfo.split("\\s*,\\s*");
			for (int i = 0; i < paramInfos.length; i++) {
				final String paramInfo = paramInfos[i];
				final Matcher mtchParam = ptnParam.matcher(paramInfo);
				if (mtchParam.find()) {
					final String name = mtchParam.group(1).trim();
					final String typeName = mtchParam.group(2).trim();
					Type foundType = null;

					if (typeName != null && !"".equals(typeName)) {
						foundType = EcoreServices.INSTANCE.findTypeByName(operation, typeName);
					}

					paramsToBeCreated.add(new NameAndType(name, foundType));
				}
			}
		}
		handleParameters(operation, paramsToBeCreated);
	}

	/**
	 * Update the given {@link Operation} with the given parameter descriptions.
	 * 
	 * @param operation
	 *            the context {@link Operation} to update.
	 * @param paramsToBeCreated
	 *            the parsed parameter informations.
	 */
	private static void handleParameters(Operation operation, List<NameAndType> paramsToBeCreated) {
		// Keep only the non-return params and detach them from the operation (only temporarily for some of
		// them)
		final List<Parameter> existingParams = new ArrayList<Parameter>(operation.getOwnedParameters());
		final List<Parameter> existingNonReturnParams = new ArrayList<Parameter>();
		for (Parameter parameter : existingParams) {
			if (!ParameterDirectionKind.RETURN_LITERAL.equals(parameter.getDirection())) {
				parameter.getOperation().getOwnedParameters().remove(parameter);
				existingNonReturnParams.add(parameter);
			}
		}

		// We keep the new parameters in an array
		// because we're going to build them in random order
		final Parameter[] tabParameters = new Parameter[paramsToBeCreated.size()];
		final boolean[] tabMarkers = new boolean[paramsToBeCreated.size()];

		// First pass, we try to reuse parameters with the same name
		for (int i = 0; i < paramsToBeCreated.size(); i++) {
			final NameAndType paramInfo = paramsToBeCreated.get(i);
			final String newName = paramInfo.getName();

			// If a parameter with the same name already existed we reuse it
			for (int j = 0; j < existingNonReturnParams.size(); j++) {
				final Parameter existingParam = existingNonReturnParams.get(j);
				final String oldName = existingParam.getName();
				if ((newName == null && oldName == null)
						|| (newName != null && newName.equalsIgnoreCase(oldName))) {
					tabParameters[i] = existingNonReturnParams.remove(j);
					updateType(tabParameters[i], paramInfo.getType());
					tabMarkers[i] = true;
					break;
				}
			}
		}

		// Second pass, we reuse the old params in their order of appearance
		for (int i = 0; i < paramsToBeCreated.size(); i++) {
			if (!tabMarkers[i]) {
				final NameAndType paramInfo = paramsToBeCreated.get(i);

				if (existingNonReturnParams.isEmpty()) {
					// We create a new parameter
					tabParameters[i] = createInputParameter(paramInfo.getName(), paramInfo.getType());
				} else {
					// We reuse an old parameter
					tabParameters[i] = existingNonReturnParams.remove(0);
					tabParameters[i].setName(paramInfo.getName());
					updateType(tabParameters[i], paramInfo.getType());
				}
			}
		}

		// Third, we delete the unused old parameters
		for (Parameter unusedParam : existingNonReturnParams) {
			EcoreUtil.delete(unusedParam);
		}

		// Fourth; we re-attach the remaining parameters to the operation
		for (Parameter remainingParam : tabParameters) {
			operation.getOwnedParameters().add(remainingParam);
		}
	}

	/**
	 * Update the parameter with the given type.
	 * 
	 * @param param
	 *            the parameter to update.
	 * @param type
	 *            the type to set.
	 */
	private static void updateType(Parameter param, Type type) {
		if (type == null) {
			param.setType(null);
		} else {
			if (!type.equals(param.getType())) {
				param.setType(type);
			}
		}
	}

	/**
	 * Create a new input {@link Parameter}.
	 * 
	 * @param name
	 *            the parameter name.
	 * @param type
	 *            the parameter type.
	 * @return the new {@link Parameter}.
	 */
	private static Parameter createInputParameter(String name, Type type) {
		final Parameter param = UMLFactory.eINSTANCE.createParameter();
		param.setDirection(ParameterDirectionKind.IN_LITERAL);
		param.setName(name);
		param.setType(type);
		return param;
	}

	/**
	 * Update the return type of the given {@link Operation}.
	 * 
	 * @param operation
	 *            the context {@link Operation} to update.
	 * @param returnTypeName
	 *            the name of {@link Operation}'s return type.
	 */
	private static void handleReturnType(Operation operation, String returnTypeName) {
		// Search the type and sets it on the property if found
		if (returnTypeName != null && !"".equals(returnTypeName)) {
			if (operation.getType() == null
					|| !returnTypeName.equalsIgnoreCase(operation.getType().getName())) {
				final Type foundType = EcoreServices.INSTANCE.findTypeByName(operation, returnTypeName);
				if (foundType != null) {
					operation.setType(foundType);
				}
			}
		} else {
			operation.setType(null);
		}
	}
}
