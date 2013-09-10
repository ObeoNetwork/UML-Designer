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

package org.obeonetwork.dsl.uml2.design.tests.contexts;

import static org.junit.Assert.*;
	import org.obeonetwork.dsl.uml2.design.tests.automation.Context;

// Start of user code AnUmlModelWithAnOperation imports
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.obeonetwork.dsl.uml2.design.services.internal.OperationServices;
// End of user code

/**
 * Context : An Uml model with an operation
 */
public class AnUmlModelWithAnOperation extends Context {
// Start of user code AnUmlModelWithAnOperation variables
	/**
	 * Existing constant.
	 */
	private static final String EXISTING = "Existing";

	/**
	 * String primitive type name.
	 */
	private static final String PRIMITIVE_TYPE_STRING = "String";

	/**
	 * Integer primitive type name.
	 */
	private static final String PRIMITIVE_TYPE_INTEGER = "Integer";

	/**
	 * Old name constant.
	 */
	private static final String OLD_NAME = "oldName";

	/**
	 * The test resource set.
	 */
	private ResourceSet resourceSet;

	/**
	 * The String primitive type.
	 */
	private PrimitiveType stringPrimitiveType;

	/**
	 * The Integer primitive type.
	 */
	private PrimitiveType integerPrimitiveType;

	/**
	 * The existing test class.
	 */
	private Class existingClass;

	/**
	 * The operation.
	 */
	private Operation op;

	// End of user code

	@Override
	public void setup() {
		// Start of user code AnUmlModelWithAnOperation setup
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			@SuppressWarnings("unused")
			final EPackage pkg = UMLPackage.eINSTANCE;
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
					UMLResource.Factory.INSTANCE);
		}

		resourceSet = new ResourceSetImpl();
		final Resource resource = new ResourceImpl();
		resourceSet.getResources().add(resource);

		stringPrimitiveType = UMLFactory.eINSTANCE.createPrimitiveType();
		stringPrimitiveType.setName(PRIMITIVE_TYPE_STRING);
		resource.getContents().add(stringPrimitiveType);

		integerPrimitiveType = UMLFactory.eINSTANCE.createPrimitiveType();
		integerPrimitiveType.setName(PRIMITIVE_TYPE_INTEGER);
		resource.getContents().add(integerPrimitiveType);

		existingClass = UMLFactory.eINSTANCE.createClass();
		existingClass.setName(EXISTING);
		resource.getContents().add(existingClass);

		op = createOperation(OLD_NAME, stringPrimitiveType);
		Parameter param1 = createInputParameter(op, "param1", stringPrimitiveType);
		createInputParameter(op, "param1bis", integerPrimitiveType);
		createInputParameter(op, "param2", integerPrimitiveType);
		createInputParameter(op, "param3", stringPrimitiveType);
		op.getReturnResult().setLower(0);
		op.getReturnResult().setUpper(-1);
		param1.setLower(0);
		param1.setUpper(-1);
		// End of user code
	}

	@Override
	public void tearDown() {
		// Start of user code AnUmlModelWithAnOperation tear down
		// Nothing
		// End of user code
	}
	/**
	 * Action : I edit the label of the operation to
	 */
	public void actionIEditTheLabelOfTheOperationTo(String iEditTheLabelOfTheOperationTo0) {
		// Start of user code IEditTheLabelOfTheOperationTo
		OperationServices.parseInputLabel(op, iEditTheLabelOfTheOperationTo0);
		// End of user code
	}

	/**
	 * Behavior : The upper bound of the return parameter equals
	 */
	public void assertTheUpperBoundOfTheReturnParameterEquals(String theUpperBoundOfTheReturnParameterEquals0) {
		// Start of user code TheUpperBoundOfTheReturnParameterEquals
		assertEquals(Integer.parseInt(theUpperBoundOfTheReturnParameterEquals0), op.getReturnResult()
				.getUpper());
		// End of user code
	}
	/**
	 * Behavior : The third input parameter name and type equals
	 */
	public void assertTheThirdInputParameterNameAndTypeEquals(String theThirdInputParameterNameAndTypeEquals0,String theThirdInputParameterNameAndTypeEquals1) {
		// Start of user code TheThirdInputParameterNameAndTypeEquals
		checkInputParameter(op, 2, theThirdInputParameterNameAndTypeEquals0,
				theThirdInputParameterNameAndTypeEquals1);
		// End of user code
	}
	/**
	 * Behavior : The operation type equals
	 */
	public void assertTheOperationTypeEquals(String theOperationTypeEquals0) {
		// Start of user code TheOperationTypeEquals
		if ("null".equals(theOperationTypeEquals0)) {
			assertEquals(null, op.getType());
		} else {
			assertEquals(theOperationTypeEquals0, op.getType().getName());
		}
		// End of user code
	}
	/**
	 * Behavior : The operation return type equals
	 */
	public void assertTheOperationReturnTypeEquals(String theOperationReturnTypeEquals0) {
		// Start of user code TheOperationReturnTypeEquals
		if ("null".equals(theOperationReturnTypeEquals0)) {
			assertEquals(null, op.getReturnResult().getType());
		} else {
			assertEquals(theOperationReturnTypeEquals0, op.getReturnResult().getType().getName());
		}
		// End of user code
	}
	/**
	 * Behavior : The lower bound of the first input parameter
	 */
	public void assertTheLowerBoundOfTheFirstInputParameter(String theLowerBoundOfTheFirstInputParameter0,String theLowerBoundOfTheFirstInputParameter1) {
		// Start of user code TheLowerBoundOfTheFirstInputParameter
		assertEquals(Integer.parseInt(theLowerBoundOfTheFirstInputParameter1),
				op.getOwnedParameter(theLowerBoundOfTheFirstInputParameter0, stringPrimitiveType).getLower());
		// End of user code
	}
	/**
	 * Behavior : The second input parameter name and type equals
	 */
	public void assertTheSecondInputParameterNameAndTypeEquals(String theSecondInputParameterNameAndTypeEquals0,String theSecondInputParameterNameAndTypeEquals1) {
		// Start of user code TheSecondInputParameterNameAndTypeEquals
		checkInputParameter(op, 1, theSecondInputParameterNameAndTypeEquals0,
				theSecondInputParameterNameAndTypeEquals1);
		// End of user code
	}
	/**
	 * Behavior : The first input parameter name and type equals
	 */
	public void assertTheFirstInputParameterNameAndTypeEquals(String theFirstInputParameterNameAndTypeEquals0,String theFirstInputParameterNameAndTypeEquals1) {
		// Start of user code TheFirstInputParameterNameAndTypeEquals
		checkInputParameter(op, 0, theFirstInputParameterNameAndTypeEquals0,
				theFirstInputParameterNameAndTypeEquals1);
		// End of user code
	}
	/**
	 * Behavior : The number of parameters with the direction is
	 */
	public void assertTheNumberOfParametersWithTheDirectionIs(String theNumberOfParametersWithTheDirectionIs0,String theNumberOfParametersWithTheDirectionIs1) {
		// Start of user code TheNumberOfParametersWithTheDirectionIs
		assertParametersSize(op, Integer.parseInt(theNumberOfParametersWithTheDirectionIs0),
				theNumberOfParametersWithTheDirectionIs1);
		// End of user code
	}
	/**
	 * Behavior : The lower bound of the return parameter equals
	 */
	public void assertTheLowerBoundOfTheReturnParameterEquals(String theLowerBoundOfTheReturnParameterEquals0) {
		// Start of user code TheLowerBoundOfTheReturnParameterEquals
		assertEquals(Integer.parseInt(theLowerBoundOfTheReturnParameterEquals0), op.getReturnResult()
				.getLower());
		// End of user code
	}
	/**
	 * Behavior : The lower bound of the fourth input parameter
	 */
	public void assertTheLowerBoundOfTheFourthInputParameter(String theLowerBoundOfTheFourthInputParameter0,String theLowerBoundOfTheFourthInputParameter1) {
		// Start of user code TheLowerBoundOfTheFourthInputParameter
		assertEquals(Integer.parseInt(theLowerBoundOfTheFourthInputParameter1),
				op.getOwnedParameter(theLowerBoundOfTheFourthInputParameter0, stringPrimitiveType).getLower());
		// End of user code
	}
	/**
	 * Behavior : The operation name equals
	 */
	public void assertTheOperationNameEquals(String theOperationNameEquals0) {
		// Start of user code TheOperationNameEquals
		assertEquals(theOperationNameEquals0, op.getName());
		// End of user code
	}
	/**
	 * Behavior : The upper bound of the first input parameter
	 */
	public void assertTheUpperBoundOfTheFirstInputParameter(String theUpperBoundOfTheFirstInputParameter0,String theUpperBoundOfTheFirstInputParameter1) {
		// Start of user code TheUpperBoundOfTheFirstInputParameter
		assertEquals(Integer.parseInt(theUpperBoundOfTheFirstInputParameter1),
				op.getOwnedParameter(theUpperBoundOfTheFirstInputParameter0, stringPrimitiveType).getLower());
		// End of user code
	}
	/**
	 * Behavior : The upper bound of the fourth input parameter
	 */
	public void assertTheUpperBoundOfTheFourthInputParameter(String theUpperBoundOfTheFourthInputParameter0,String theUpperBoundOfTheFourthInputParameter1) {
		// Start of user code TheUpperBoundOfTheFourthInputParameter
		assertEquals(Integer.parseInt(theUpperBoundOfTheFourthInputParameter1),
				op.getOwnedParameter(theUpperBoundOfTheFourthInputParameter0, stringPrimitiveType).getLower());
		// End of user code
	}
	/**
	 * Behavior : The fourth input parameter name and type equals
	 */
	public void assertTheFourthInputParameterNameAndTypeEquals(String theFourthInputParameterNameAndTypeEquals0,String theFourthInputParameterNameAndTypeEquals1) {
		// Start of user code TheFourthInputParameterNameAndTypeEquals
		checkInputParameter(op, 3, theFourthInputParameterNameAndTypeEquals0,
				theFourthInputParameterNameAndTypeEquals1);
		// End of user code
	}

// Start of user code AnUmlModelWithAnOperation private methods
	/**
	 * Create a new {@link Operation} with the given parameters and store it in the first {@link Resource}.
	 * 
	 * @param name
	 *            the operation name.
	 * @param returnType
	 *            the operation return {@link Type}.
	 * @return the new {@link Operation}
	 */
	private Operation createOperation(String name, Type returnType) {
		final Operation operation = UMLFactory.eINSTANCE.createOperation();
		operation.setName(name);
		operation.setType(returnType);

		resourceSet.getResources().get(0).getContents().add(operation);

		return operation;
	}

	/**
	 * Check the input parameter of the given {@link Operation}.
	 * 
	 * @param operation
	 *            the operation to check
	 * @param index
	 *            the index of input parameter to check
	 * @param name
	 *            the name of the input parameter
	 * @param type
	 *            the type of the input parameter.
	 */
	private void checkInputParameter(Operation operation, int index, String name, String type) {
		final List<Parameter> inputParams = new ArrayList<Parameter>();
		for (Parameter param : operation.getOwnedParameters()) {
			if (!ParameterDirectionKind.RETURN_LITERAL.equals(param.getDirection())) {
				inputParams.add(param);
			}
		}
		assertTrue(
				"Not enough parameters in list (index = " + index + ", size = " + inputParams.size() + ")",
				index < inputParams.size());
		final Parameter param = inputParams.get(index);
		assertEquals(name.trim(), param.getName());
		assertEquals(type, param.getType().getName());
	}

	/**
	 * Assert parameter the parameter size of the given {@link Operation}.
	 * 
	 * @param operation
	 *            the operation to test.
	 * @param nbParams
	 *            the expected parameter count.
	 * @param direction
	 *            the attended parameter direction.
	 */
	private void assertParametersSize(Operation operation, int nbParams, String direction) {
		int count = 0;
		for (Parameter param : operation.getOwnedParameters()) {
			if (direction == null || direction.equalsIgnoreCase(param.getDirection().getName())) {
				count++;
			}
		}
		assertEquals(nbParams, count);
	}

	/**
	 * Add a {@link Parameter} to the given {@link Operation}.
	 * 
	 * @param operation
	 *            the {@link Operation} on which to add the input parameter
	 * @param paramName
	 *            the parameter name
	 * @param paramType
	 *            the parameter {@link Type}
	 * @return the new Parameter
	 */
	private Parameter createInputParameter(Operation operation, String paramName, Type paramType) {
		final Parameter param = UMLFactory.eINSTANCE.createParameter();
		param.setName(paramName);
		param.setType(paramType);
		param.setDirection(ParameterDirectionKind.IN_LITERAL);
		operation.getOwnedParameters().add(param);
		return param;
	}
	// End of user code
}
