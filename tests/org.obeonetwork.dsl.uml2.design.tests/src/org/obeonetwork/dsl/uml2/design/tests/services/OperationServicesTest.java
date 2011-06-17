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
package org.obeonetwork.dsl.uml2.design.tests.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

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

/**
 * Utility services on operations services.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class OperationServicesTest extends TestCase {
	/**
	 * New param.
	 */
	private static final String NEW_PARAM = "new param";

	/**
	 * toString.
	 */
	private static final String TO_STRING = "toString";

	/**
	 * Param3.
	 */
	private static final String PARAM3 = "param3";

	/**
	 * Param2.
	 */
	private static final String PARAM2 = "param2";

	/**
	 * Param1.
	 */
	private static final String PARAM1 = "param1";

	/**
	 * Toto.
	 */
	private static final String TOTO = "toto";

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
	 * Constructor.
	 */
	public OperationServicesTest() {
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
	}

	/**
	 * Create a new {@link Operation} with the given parameters and store it in the first {@link Resource}.
	 * 
	 * @param name the operation name.
	 * @param returnType the operation return {@link Type}.
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
	 * Add a {@link Parameter} to the given {@link Operation}.
	 * 
	 * @param operation the {@link Operation} on which to add the input parameter
	 * @param paramName the parameter name
	 * @param paramType the parameter {@link Type}
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

	/**
	 * Test the operation name only update.
	 */
	public void testNameOnly() {
		final Operation op = createOperation(OLD_NAME, stringPrimitiveType);

		OperationServices.parseInputLabel(op, " toto ");
		assertEquals(TOTO, op.getName());
		assertEquals(stringPrimitiveType, op.getType());
		assertEquals(stringPrimitiveType, op.getReturnResult().getType());
	}

	/**
	 * Test the operation name and return type update.
	 */
	public void testNameAndReturnType() {
		final Operation op = createOperation(OLD_NAME, stringPrimitiveType);

		OperationServices.parseInputLabel(op, " toto : Integer ");
		assertEquals(TOTO, op.getName());
		assertEquals(integerPrimitiveType, op.getType());
		assertEquals(integerPrimitiveType, op.getReturnResult().getType());
	}

	/**
	 * Test the operation name with null return type update.
	 */
	public void testNameAndReturnTypeNull() {
		final Operation op = createOperation(OLD_NAME, stringPrimitiveType);

		OperationServices.parseInputLabel(op, " toto :  ");
		assertEquals(TOTO, op.getName());
		assertEquals(null, op.getType());
		assertEquals(null, op.getReturnResult().getType());
	}

	/**
	 * Test the operation update with whitespace in user input.
	 */
	public void testWithSpaces() {
		final Operation op = createOperation(OLD_NAME, stringPrimitiveType);
		createInputParameter(op, PARAM1, stringPrimitiveType);

		OperationServices.parseInputLabel(op, " an operation (a param : String) : Integer ");
		assertEquals("an operation", op.getName());
		assertEquals(integerPrimitiveType, op.getType());
		assertEquals(integerPrimitiveType, op.getReturnResult().getType());

		checkInputParameter(op, 0, "a param", stringPrimitiveType);
	}

	/**
	 * Test the operation update with name, parameters & return type.
	 */
	public void testNameParamsAndReturnType() {
		final Operation op = createOperation(OLD_NAME, stringPrimitiveType);
		createInputParameter(op, PARAM1, stringPrimitiveType);
		createInputParameter(op, PARAM2, integerPrimitiveType);
		createInputParameter(op, PARAM3, stringPrimitiveType);

		OperationServices.parseInputLabel(op,
				" toto ( hello : Integer, hi : Integer, param3 : String ) : Integer ");
		assertEquals(TOTO, op.getName());
		assertEquals(integerPrimitiveType, op.getType());
		assertEquals(integerPrimitiveType, op.getReturnResult().getType());

		checkInputParameter(op, 0, "hello", integerPrimitiveType);
		checkInputParameter(op, 1, "hi", integerPrimitiveType);
		checkInputParameter(op, 2, PARAM3, stringPrimitiveType);
	}

	/**
	 * Test the operation update without return type.
	 */
	public void testNameParamsAndNoReturnType() {
		final Operation op = createOperation(OLD_NAME, stringPrimitiveType);
		createInputParameter(op, PARAM1, stringPrimitiveType);
		createInputParameter(op, PARAM2, integerPrimitiveType);
		createInputParameter(op, PARAM3, stringPrimitiveType);

		OperationServices.parseInputLabel(op, " toto ( hello : Integer, hi : Integer, param3 : String )");
		assertEquals(TOTO, op.getName());
		assertEquals(stringPrimitiveType, op.getType());
		assertEquals(stringPrimitiveType, op.getReturnResult().getType());

		checkInputParameter(op, 0, "hello", integerPrimitiveType);
		checkInputParameter(op, 1, "hi", integerPrimitiveType);
		checkInputParameter(op, 2, PARAM3, stringPrimitiveType);
	}

	/**
	 * Test toString operation update.
	 */
	public void testToString() {
		final Operation op = createOperation(OLD_NAME, stringPrimitiveType);
		createInputParameter(op, PARAM1, stringPrimitiveType);
		createInputParameter(op, PARAM2, integerPrimitiveType);
		createInputParameter(op, PARAM3, stringPrimitiveType);

		OperationServices.parseInputLabel(op, "toString() : Integer");
		assertEquals(TO_STRING, op.getName());
		assertEquals(integerPrimitiveType, op.getType());
		assertEquals(integerPrimitiveType, op.getReturnResult().getType());

		assertParametersSize(op, 0, ParameterDirectionKind.IN_LITERAL);
	}

	/**
	 * Test the parameter addition.
	 */
	public void testToStringAddingAParameterAndChangingType() {
		final Operation op = createOperation(TO_STRING, stringPrimitiveType);

		OperationServices.parseInputLabel(op, "toString(a : Existing) : Integer");
		assertEquals(TO_STRING, op.getName());
		assertEquals(integerPrimitiveType, op.getType());
		assertEquals(integerPrimitiveType, op.getReturnResult().getType());

		assertParametersSize(op, 1, ParameterDirectionKind.IN_LITERAL);
		checkInputParameter(op, 0, "a", existingClass);
	}

	/**
	 * Test parameter removal.
	 */
	public void testNameNoParamAndReturnType() {
		final Operation op = createOperation(OLD_NAME, stringPrimitiveType);
		createInputParameter(op, PARAM1, stringPrimitiveType);
		createInputParameter(op, PARAM2, integerPrimitiveType);
		createInputParameter(op, PARAM3, stringPrimitiveType);

		OperationServices.parseInputLabel(op, " toto (  ) : Integer ");
		assertEquals(TOTO, op.getName());
		assertEquals(integerPrimitiveType, op.getType());
		assertEquals(integerPrimitiveType, op.getReturnResult().getType());

		assertParametersSize(op, 0, ParameterDirectionKind.IN_LITERAL);
	}

	/**
	 * Test parameter removal without brace.
	 */
	public void testNameNoParamAndReturnType2() {
		final Operation op = createOperation(OLD_NAME, stringPrimitiveType);
		createInputParameter(op, PARAM1, stringPrimitiveType);
		createInputParameter(op, PARAM2, integerPrimitiveType);
		createInputParameter(op, PARAM3, stringPrimitiveType);

		OperationServices.parseInputLabel(op, " oldName  : Integer ");
		assertEquals(OLD_NAME, op.getName());
		assertEquals(integerPrimitiveType, op.getType());
		assertEquals(integerPrimitiveType, op.getReturnResult().getType());

		assertParametersSize(op, 0, ParameterDirectionKind.IN_LITERAL);
	}

	/**
	 * Test parameter update & move.
	 */
	public void testExistingParamChangeTheirTypeAndPlace() {
		final Operation op = createOperation(OLD_NAME, stringPrimitiveType);
		createInputParameter(op, PARAM1, stringPrimitiveType);
		createInputParameter(op, "param1bis", integerPrimitiveType);
		createInputParameter(op, PARAM2, integerPrimitiveType);
		createInputParameter(op, PARAM3, stringPrimitiveType);

		OperationServices.parseInputLabel(op,
				" oldName (param1 : Integer , newParam:String, param3 : String, param2:String) : Integer ");
		assertEquals(OLD_NAME, op.getName());
		assertEquals(integerPrimitiveType, op.getType());
		assertEquals(integerPrimitiveType, op.getReturnResult().getType());

		assertParametersSize(op, 4, ParameterDirectionKind.IN_LITERAL);
		checkInputParameter(op, 0, PARAM1, integerPrimitiveType);
		checkInputParameter(op, 1, "newParam", stringPrimitiveType);
		checkInputParameter(op, 2, PARAM3, stringPrimitiveType);
		checkInputParameter(op, 3, PARAM2, stringPrimitiveType);

	}

	/**
	 * Test parameter multiplicity update.
	 */
	public void testMultiplicityNotChangedOnExistingParams() {
		final Operation op = createOperation(OLD_NAME, stringPrimitiveType);
		op.getReturnResult().setLower(0);
		op.getReturnResult().setUpper(-1);
		final Parameter param1 = createInputParameter(op, PARAM1, stringPrimitiveType);
		param1.setLower(0);
		param1.setUpper(-1);
		createInputParameter(op, PARAM2, integerPrimitiveType);
		createInputParameter(op, PARAM3, stringPrimitiveType);

		OperationServices.parseInputLabel(op,
				" oldName (param1 : String , param2:integer, param3 : String, new param:string) : String ");
		assertEquals(OLD_NAME, op.getName());
		assertEquals(stringPrimitiveType, op.getType());
		assertEquals(stringPrimitiveType, op.getReturnResult().getType());
		assertEquals(0, op.getReturnResult().getLower());
		assertEquals(-1, op.getReturnResult().getUpper());

		assertParametersSize(op, 4, ParameterDirectionKind.IN_LITERAL);
		checkInputParameter(op, 0, PARAM1, stringPrimitiveType);
		checkInputParameter(op, 1, PARAM2, integerPrimitiveType);
		checkInputParameter(op, 2, PARAM3, stringPrimitiveType);
		checkInputParameter(op, 3, NEW_PARAM, stringPrimitiveType);

		assertEquals(0, op.getOwnedParameter(PARAM1, stringPrimitiveType).getLower());
		assertEquals(-1, op.getOwnedParameter(PARAM1, stringPrimitiveType).getUpper());

		assertEquals(1, op.getOwnedParameter(NEW_PARAM, stringPrimitiveType).getLower());
		assertEquals(1, op.getOwnedParameter(NEW_PARAM, stringPrimitiveType).getUpper());
	}

	/**
	 * Assert parameter the parameter size of the given {@link Operation}.
	 * 
	 * @param operation the operation to test.
	 * @param nbParams the expected parameter count.
	 * @param direction the attended parameter direction.
	 */
	private void assertParametersSize(Operation operation, int nbParams, ParameterDirectionKind direction) {
		int count = 0;
		for (Parameter param : operation.getOwnedParameters()) {
			if (direction == null || direction.equals(param.getDirection())) {
				count++;
			}
		}
		assertEquals(nbParams, count);
	}

	/**
	 * Check the input parameter of the given {@link Operation}.
	 * 
	 * @param operation the operation to check
	 * @param index the index of input parameter to check
	 * @param name the name of the input parameter
	 * @param type the type of the input parameter.
	 */
	private void checkInputParameter(Operation operation, int index, String name, Type type) {
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
		assertEquals(name, param.getName());
		assertEquals(type, param.getType());
	}
}
