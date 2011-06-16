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
 * Utility services on operations services
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class OperationServicesTest extends TestCase {
	private static final String EXISTING = "Existing";

	private boolean initialized = false;
	
	private static final String PRIMITIVE_TYPE_STRING = "String";
	private static final String PRIMITIVE_TYPE_INTEGER = "Integer";
	
	private static final String OLD_NAME = "oldName";
	
	private ResourceSet resourceSet;
	
	private PrimitiveType TYPE_STRING;
	private PrimitiveType TYPE_INTEGER;
	private Class CLASS_EXISTING;
	
	@Override
	protected void setUp() throws Exception {
		if (!initialized) {
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
				@SuppressWarnings("unused")
				EPackage pkg = UMLPackage.eINSTANCE;
				Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
			}
			
			resourceSet = new ResourceSetImpl();
			Resource resource = new ResourceImpl();
			resourceSet.getResources().add(resource);
			
			
			TYPE_STRING = UMLFactory.eINSTANCE.createPrimitiveType();
			TYPE_STRING.setName(PRIMITIVE_TYPE_STRING);
			resource.getContents().add(TYPE_STRING);
			
			TYPE_INTEGER = UMLFactory.eINSTANCE.createPrimitiveType();
			TYPE_INTEGER.setName(PRIMITIVE_TYPE_INTEGER);
			resource.getContents().add(TYPE_INTEGER);
	
			CLASS_EXISTING = UMLFactory.eINSTANCE.createClass();
			CLASS_EXISTING.setName(EXISTING);
			resource.getContents().add(CLASS_EXISTING);
			
			initialized = true;
		}
	}
	
	private Operation createOperation(String name, Type returnType) {
		Operation operation = UMLFactory.eINSTANCE.createOperation();
		operation.setName(name);
		operation.setType(returnType);
		
		resourceSet.getResources().get(0).getContents().add(operation);
		
		return operation;
	}
	
	private Parameter createInputParameter(Operation operation, String paramName, Type paramType) {
		Parameter param = UMLFactory.eINSTANCE.createParameter();
		param.setName(paramName);
		param.setType(paramType);
		param.setDirection(ParameterDirectionKind.IN_LITERAL);
		operation.getOwnedParameters().add(param);
		return param;
	}
	
	public void testNameOnly() {
		Operation op = createOperation(OLD_NAME, TYPE_STRING);
		
		OperationServices.parseInputLabel(op, " toto ");
		assertEquals("toto", op.getName());
		assertEquals(TYPE_STRING, op.getType());
		assertEquals(TYPE_STRING, op.getReturnResult().getType());
	}
	
	public void testNameAndReturnType() {
		Operation op = createOperation(OLD_NAME, TYPE_STRING);
		
		OperationServices.parseInputLabel(op, " toto : Integer ");
		assertEquals("toto", op.getName());
		assertEquals(TYPE_INTEGER, op.getType());
		assertEquals(TYPE_INTEGER, op.getReturnResult().getType());
	}
	
	public void testNameAndReturnTypeNull() {
		Operation op = createOperation(OLD_NAME, TYPE_STRING);
		
		OperationServices.parseInputLabel(op, " toto :  ");
		assertEquals("toto", op.getName());
		assertEquals(null, op.getType());
		assertEquals(null, op.getReturnResult().getType());
	}
		
	public void testWithSpaces() {
		Operation op = createOperation(OLD_NAME, TYPE_STRING);
		createInputParameter(op, "param1", TYPE_STRING);
		
		OperationServices.parseInputLabel(op, " an operation (a param : String) : Integer ");
		assertEquals("an operation", op.getName());
		assertEquals(TYPE_INTEGER, op.getType());
		assertEquals(TYPE_INTEGER, op.getReturnResult().getType());
		
		checkInputParameter(op, 0, "a param", TYPE_STRING);
	}
	
	public void testNameParamsAndReturnType() {
		Operation op = createOperation(OLD_NAME, TYPE_STRING);
		createInputParameter(op, "param1", TYPE_STRING);
		createInputParameter(op, "param2", TYPE_INTEGER);
		createInputParameter(op, "param3", TYPE_STRING);
		
		OperationServices.parseInputLabel(op, " toto ( hello : Integer, hi : Integer, param3 : String ) : Integer ");
		assertEquals("toto", op.getName());
		assertEquals(TYPE_INTEGER, op.getType());
		assertEquals(TYPE_INTEGER, op.getReturnResult().getType());
		
		checkInputParameter(op, 0, "hello", TYPE_INTEGER);
		checkInputParameter(op, 1, "hi", TYPE_INTEGER);
		checkInputParameter(op, 2, "param3", TYPE_STRING);
	}
	
	public void testNameParamsAndNoReturnType() {
		Operation op = createOperation(OLD_NAME, TYPE_STRING);
		createInputParameter(op, "param1", TYPE_STRING);
		createInputParameter(op, "param2", TYPE_INTEGER);
		createInputParameter(op, "param3", TYPE_STRING);
		
		OperationServices.parseInputLabel(op, " toto ( hello : Integer, hi : Integer, param3 : String )");
		assertEquals("toto", op.getName());
		assertEquals(TYPE_STRING, op.getType());
		assertEquals(TYPE_STRING, op.getReturnResult().getType());
		
		checkInputParameter(op, 0, "hello", TYPE_INTEGER);
		checkInputParameter(op, 1, "hi", TYPE_INTEGER);
		checkInputParameter(op, 2, "param3", TYPE_STRING);
	}
	
	public void testToString() {
		Operation op = createOperation(OLD_NAME, TYPE_STRING);
		createInputParameter(op, "param1", TYPE_STRING);
		createInputParameter(op, "param2", TYPE_INTEGER);
		createInputParameter(op, "param3", TYPE_STRING);
		
		OperationServices.parseInputLabel(op, "toString() : Integer");
		assertEquals("toString", op.getName());
		assertEquals(TYPE_INTEGER, op.getType());
		assertEquals(TYPE_INTEGER, op.getReturnResult().getType());
		
		assertParametersSize(op, 0, ParameterDirectionKind.IN_LITERAL);
	}
	
	public void testToStringAddingAParameterAndChangingType() {
		Operation op = createOperation("toString", TYPE_STRING);
		
		OperationServices.parseInputLabel(op, "toString(a : Existing) : Integer");
		assertEquals("toString", op.getName());
		assertEquals(TYPE_INTEGER, op.getType());
		assertEquals(TYPE_INTEGER, op.getReturnResult().getType());
		
		assertParametersSize(op, 1, ParameterDirectionKind.IN_LITERAL);
		checkInputParameter(op, 0, "a", CLASS_EXISTING);
	}
	
	public void testNameNoParamAndReturnType() {
		Operation op = createOperation(OLD_NAME, TYPE_STRING);
		createInputParameter(op, "param1", TYPE_STRING);
		createInputParameter(op, "param2", TYPE_INTEGER);
		createInputParameter(op, "param3", TYPE_STRING);
		
		OperationServices.parseInputLabel(op, " toto (  ) : Integer ");
		assertEquals("toto", op.getName());
		assertEquals(TYPE_INTEGER, op.getType());
		assertEquals(TYPE_INTEGER, op.getReturnResult().getType());
		
		assertParametersSize(op, 0, ParameterDirectionKind.IN_LITERAL);
	}
	
	public void testNameNoParamAndReturnType2() {
		Operation op = createOperation(OLD_NAME, TYPE_STRING);
		createInputParameter(op, "param1", TYPE_STRING);
		createInputParameter(op, "param2", TYPE_INTEGER);
		createInputParameter(op, "param3", TYPE_STRING);
		
		OperationServices.parseInputLabel(op, " oldName  : Integer ");
		assertEquals("oldName", op.getName());
		assertEquals(TYPE_INTEGER, op.getType());
		assertEquals(TYPE_INTEGER, op.getReturnResult().getType());
		
		assertParametersSize(op, 0, ParameterDirectionKind.IN_LITERAL);
	}
	
	public void testExistingParamChangeTheirTypeAndPlace() {
		Operation op = createOperation(OLD_NAME, TYPE_STRING);
		createInputParameter(op, "param1", TYPE_STRING);
		createInputParameter(op, "param1bis", TYPE_INTEGER);
		createInputParameter(op, "param2", TYPE_INTEGER);
		createInputParameter(op, "param3", TYPE_STRING);
		
		OperationServices.parseInputLabel(op, " oldName (param1 : Integer , newParam:String, param3 : String, param2:String) : Integer ");
		assertEquals("oldName", op.getName());
		assertEquals(TYPE_INTEGER, op.getType());
		assertEquals(TYPE_INTEGER, op.getReturnResult().getType());
		
		assertParametersSize(op, 4, ParameterDirectionKind.IN_LITERAL);
		checkInputParameter(op, 0, "param1", TYPE_INTEGER);
		checkInputParameter(op, 1, "newParam", TYPE_STRING);
		checkInputParameter(op, 2, "param3", TYPE_STRING);
		checkInputParameter(op, 3, "param2", TYPE_STRING);
		
	}
	
	public void testMultiplicityNotChangedOnExistingParams() {
		Operation op = createOperation(OLD_NAME, TYPE_STRING);
		op.getReturnResult().setLower(0);
		op.getReturnResult().setUpper(-1);
		Parameter param1 = createInputParameter(op, "param1", TYPE_STRING);
		param1.setLower(0);
		param1.setUpper(-1);
		createInputParameter(op, "param2", TYPE_INTEGER);
		createInputParameter(op, "param3", TYPE_STRING);
		
		OperationServices.parseInputLabel(op, " oldName (param1 : String , param2:integer, param3 : String, new param:string) : String ");
		assertEquals("oldName", op.getName());
		assertEquals(TYPE_STRING, op.getType());
		assertEquals(TYPE_STRING, op.getReturnResult().getType());
		assertEquals(0, op.getReturnResult().getLower());
		assertEquals(-1, op.getReturnResult().getUpper());
		
		assertParametersSize(op, 4, ParameterDirectionKind.IN_LITERAL);
		checkInputParameter(op, 0, "param1", TYPE_STRING);
		checkInputParameter(op, 1, "param2", TYPE_INTEGER);
		checkInputParameter(op, 2, "param3", TYPE_STRING);
		checkInputParameter(op, 3, "new param", TYPE_STRING);
		
		assertEquals(0, op.getOwnedParameter("param1", TYPE_STRING).getLower());
		assertEquals(-1, op.getOwnedParameter("param1", TYPE_STRING).getUpper());
		
		assertEquals(1, op.getOwnedParameter("new param", TYPE_STRING).getLower());
		assertEquals(1, op.getOwnedParameter("new param", TYPE_STRING).getUpper());
	}
	
	private void assertParametersSize(Operation operation, int nbParams, ParameterDirectionKind direction) {
		int count = 0;
		for (Parameter param : operation.getOwnedParameters()) {
			if (direction == null || direction.equals(param.getDirection())) {
				count++;
			}
		}
		assertEquals(nbParams, count);
	}
	
	private void checkInputParameter(Operation operation, int index, String name, Type type) {
		List<Parameter> inputParams = new ArrayList<Parameter>();
		for (Parameter	param : operation.getOwnedParameters()) {
			if (!ParameterDirectionKind.RETURN_LITERAL.equals(param.getDirection())) {
				inputParams.add(param);
			}
		}
		assertTrue("Not enough parameters in list (index = " + index + ", size = " + inputParams.size() + ")",
				index < inputParams.size());
		Parameter param = inputParams.get(index);
		assertEquals(name, param.getName());
		assertEquals(type, param.getType());
	}
}
