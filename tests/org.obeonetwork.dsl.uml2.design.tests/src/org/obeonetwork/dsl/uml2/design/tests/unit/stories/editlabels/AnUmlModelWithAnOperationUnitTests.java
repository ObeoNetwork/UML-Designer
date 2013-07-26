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

package org.obeonetwork.dsl.uml2.design.tests.unit.stories.editlabels;
import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.AnUmlModelWithAnOperation;

public class AnUmlModelWithAnOperationUnitTests {
	@Rule
	public AnUmlModelWithAnOperation context = new AnUmlModelWithAnOperation();

	@Test
	public void operationName() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo(" toto");
		context.assertTheOperationNameEquals("toto");
	}
	@Test
	public void operationNameAndReturnType() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo(" toto : Integer ");
		context.assertTheOperationNameEquals("toto");
		context.assertTheOperationTypeEquals("Integer");
		context.assertTheOperationReturnTypeEquals("Integer");
	}
	@Test
	public void operationNameAndReturnTypeNull() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo(" toto : ");
		context.assertTheOperationNameEquals("toto");
		context.assertTheOperationTypeEquals("null");
		context.assertTheOperationReturnTypeEquals("null");
	}
	@Test
	public void operationWithSpaces() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo(" an operation (a param : String) : Integer ");
		context.assertTheOperationNameEquals("an operation");
		context.assertTheOperationTypeEquals("Integer");
		context.assertTheOperationReturnTypeEquals("Integer");
		context.assertTheFirstInputParameterNameAndTypeEquals("a param","String");
	}
	@Test
	public void operationNameParamsAndReturnType() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo(" toto ( hello : Integer, hi : Integer, param3 : String ) : Integer ");
		context.assertTheOperationNameEquals("toto");
		context.assertTheOperationTypeEquals("Integer");
		context.assertTheOperationReturnTypeEquals("Integer");
		context.assertTheFirstInputParameterNameAndTypeEquals("hello","Integer");
		context.assertTheSecondInputParameterNameAndTypeEquals("hi","Integer");
		context.assertTheThirdInputParameterNameAndTypeEquals("param3","String");
	}
	@Test
	public void operationNameParamsAndNoReturnType() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo(" toto ( hello : Integer, hi : Integer, param3 : String )");
		context.assertTheOperationNameEquals("toto");
		context.assertTheOperationTypeEquals("String");
		context.assertTheOperationReturnTypeEquals("String");
		context.assertTheFirstInputParameterNameAndTypeEquals("hello","Integer");
		context.assertTheSecondInputParameterNameAndTypeEquals("hi","Integer");
		context.assertTheThirdInputParameterNameAndTypeEquals("param3","String");
	}
	@Test
	public void operationToString() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo("toString() : Integer");
		context.assertTheOperationNameEquals("toString");
		context.assertTheOperationTypeEquals("Integer");
		context.assertTheOperationReturnTypeEquals("Integer");
		context.assertTheNumberOfParametersWithTheDirectionIs("0","in");
	}
	@Test
	public void operationToStringAddingAParameterAndChangingType() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo("toString(a : Existing) : Integer");
		context.assertTheOperationNameEquals("toString");
		context.assertTheOperationTypeEquals("Integer");
		context.assertTheOperationReturnTypeEquals("Integer");
		context.assertTheNumberOfParametersWithTheDirectionIs("1","in");
		context.assertTheFirstInputParameterNameAndTypeEquals("a","Existing");
	}
	@Test
	public void operationNameNoParamAndReturnType() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo(" toto (  ) : Integer ");
		context.assertTheOperationNameEquals("toto");
		context.assertTheOperationTypeEquals("Integer");
		context.assertTheOperationReturnTypeEquals("Integer");
		context.assertTheNumberOfParametersWithTheDirectionIs("0","in");
	}
	@Test
	public void operationNameNoParamAndReturnType2() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo(" oldName  : Integer");
		context.assertTheOperationNameEquals("oldName");
		context.assertTheOperationTypeEquals("Integer");
		context.assertTheOperationReturnTypeEquals("Integer");
		context.assertTheNumberOfParametersWithTheDirectionIs("0","in");
	}
	@Test
	public void operationExistingParamChangeTheirTypeAndPlace() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo(" oldName (param1 : Integer , newParam:String, param3 : String, param2:String) : Integer ");
		context.assertTheOperationNameEquals("oldName");
		context.assertTheOperationTypeEquals("Integer");
		context.assertTheOperationReturnTypeEquals("Integer");
		context.assertTheNumberOfParametersWithTheDirectionIs("4","in");
		context.assertTheFirstInputParameterNameAndTypeEquals("param1","Integer");
		context.assertTheSecondInputParameterNameAndTypeEquals("newParam","String");
		context.assertTheThirdInputParameterNameAndTypeEquals("param3","String");
		context.assertTheFourthInputParameterNameAndTypeEquals("param2","String");
	}
	@Test
	public void operationMultiplicityNotChangedOnExistingParams() throws Exception {
		context.actionIEditTheLabelOfTheOperationTo(" oldName (param1 : String , param2:integer, param3 : String, new param:string) : String ");
		context.assertTheOperationNameEquals("oldName");
		context.assertTheOperationTypeEquals("String");
		context.assertTheOperationReturnTypeEquals("String");
		context.assertTheLowerBoundOfTheReturnParameterEquals("0");
		context.assertTheUpperBoundOfTheReturnParameterEquals("-1");
		context.assertTheNumberOfParametersWithTheDirectionIs("4","in");
		context.assertTheFirstInputParameterNameAndTypeEquals("param1","String");
		context.assertTheSecondInputParameterNameAndTypeEquals("param2","Integer");
		context.assertTheThirdInputParameterNameAndTypeEquals("param3","String");
		context.assertTheFourthInputParameterNameAndTypeEquals("new param","String");
		context.assertTheLowerBoundOfTheFirstInputParameter("param1","0");
		context.assertTheUpperBoundOfTheFirstInputParameter("param1","0");
		context.assertTheLowerBoundOfTheFourthInputParameter("new param","1");
		context.assertTheUpperBoundOfTheFourthInputParameter("new param","1");
	}
}
