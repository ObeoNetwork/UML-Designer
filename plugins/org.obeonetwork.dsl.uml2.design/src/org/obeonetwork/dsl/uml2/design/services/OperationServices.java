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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Utility services to manage direct label edition on operations
 * 
 * @author St�phane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class OperationServices {

	static public void parseInputLabel(Operation operation, String inputLabel) {
		// The discriminating parts in the label are : 
		// - name of the operation
		// - list of parameters between parens
		// - a ":" preceding the return type
		// original regexp = ^([^(]*)\(?([^)]*)\)?\s*:([^)]*)$
		Pattern ptnWithType = Pattern.compile("^([^(]*)\\(?([^)]*)\\)?\\s*:([^)]*)$");
		Matcher mtchWithType = ptnWithType.matcher(inputLabel.trim());
	
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
			
		} else {
			// The pattern with an explicit return type did not match
			// Let's assume the user did not specify the return type
			Pattern ptnWithoutType = Pattern.compile("^([^(]*)\\(?([^)]*)\\)?");
			Matcher mtchWithoutType = ptnWithoutType.matcher(inputLabel.trim());
			if (mtchWithoutType.find()) {
				matchFound = true;
				typeWasSpecified = false;
				name = mtchWithoutType.group(1).trim();
				parametersInfo = mtchWithoutType.group(2).trim();
			}
		}
		
		if (matchFound == true) {
			// Sets the name of the operation
			operation.setName(name);
			
			// Handle parameters
			handleParameters(operation, parametersInfo);

			// Handle the return type if it has been specified
			if (typeWasSpecified == true) {
				handleReturnType(operation, returnType);
			}
		}
	}

	private static void handleParameters(Operation operation, String parametersInfo) {
		// Regexp used to extract name and type from a string as "name : type" or "name" or ":type"
		Pattern ptnParam = Pattern.compile("^([^:]*)\\s*:?\\s*(.*)$");

		// Used to store the name and types of the parameters we'll have to create
		List<NameAndType> paramsToBeCreated = new ArrayList<NameAndType>();
		
		// Let's compile information on the parameters
		if (parametersInfo != null && !"".equals(parametersInfo.trim())) { 
			String[] paramInfos = parametersInfo.split("\\s*,\\s*");
			for (int i = 0; i < paramInfos.length; i++) {
				String paramInfo = paramInfos[i];
				Matcher mtchParam = ptnParam.matcher(paramInfo);
				if (mtchParam.find()) {
					String name = mtchParam.group(1).trim();
					String typeName = mtchParam.group(2).trim();
					Type foundType = null;
					
					if (typeName != null && !"".equals(typeName)) {
						foundType = EcoreServices.findTypeByName(operation, typeName);
					}
					
					paramsToBeCreated.add(new NameAndType(name, foundType));
				}
			}
		}
		handleParameters(operation, paramsToBeCreated);
	}
	
	private static void handleParameters(Operation operation, List<NameAndType> paramsToBeCreated) {
		// Keep only the non-return params and detach them from the operation (only temporarily for some of them)
		List<Parameter> existingParams = new ArrayList<Parameter>(operation.getOwnedParameters());
		List<Parameter> existingNonReturnParams = new ArrayList<Parameter>();
		for (Parameter parameter : existingParams) {
			if (!ParameterDirectionKind.RETURN_LITERAL.equals(parameter.getDirection())) {
				parameter.getOperation().getOwnedParameters().remove(parameter);
				existingNonReturnParams.add(parameter);
			}
		}
		
		// We keep the new parameters in an array
		// because we're going to build them in random order
		Parameter[] tabParameters = new Parameter[paramsToBeCreated.size()];
		boolean[] tabMarkers = new boolean[paramsToBeCreated.size()];
		
		// First pass, we try to reuse parameters with the same name
		for (int i = 0; i < paramsToBeCreated.size(); i++) {
			NameAndType paramInfo = paramsToBeCreated.get(i);
			String newName = paramInfo.getName();
			
			// If a parameter with the same name already existed we reuse it
			for (int j = 0; j < existingNonReturnParams.size(); j++) {
				Parameter existingParam = existingNonReturnParams.get(j);
				String oldName = existingParam.getName();
				if ((newName == null && oldName == null) || newName.equalsIgnoreCase(oldName)) {
					tabParameters[i] = existingNonReturnParams.remove(j);
					updateType(tabParameters[i], paramInfo.getType());
					tabMarkers[i] = true;
					break;
				}
			}
		}
		
		// Second pass, we reuse the old params in their order of appearance
		for (int i = 0; i < paramsToBeCreated.size(); i++) {
			if (tabMarkers[i] == false) {
				NameAndType paramInfo = paramsToBeCreated.get(i);
				
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
	
	private static void updateType(Parameter param, Type type) {
		if (type == null) {
			param.setType(null);
		} else {
			if (!type.equals(param.getType())) {
				param.setType(type);
			}
		}
	}
	
	private static Parameter createInputParameter(String name, Type type) {
		Parameter param = UMLFactory.eINSTANCE.createParameter();
		param.setDirection(ParameterDirectionKind.IN_LITERAL);
		param.setName(name);
		param.setType(type);
		return param;
	}


	private static void handleReturnType(Operation operation, String returnTypeName) {
		// Search the type and sets it on the property if found
		if (returnTypeName != null && !"".equals(returnTypeName)) {
			if (operation.getType() == null || !returnTypeName.equalsIgnoreCase(operation.getType().getName())) {
				Type foundType = EcoreServices.findTypeByName(operation, returnTypeName);
				if (foundType != null) {
					operation.setType(foundType);
				}
			}
		} else {
			operation.setType(null);
		}
	}
}
