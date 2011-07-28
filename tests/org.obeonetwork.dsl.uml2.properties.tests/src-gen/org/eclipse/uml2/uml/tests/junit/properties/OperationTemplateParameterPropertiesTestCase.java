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
package org.eclipse.uml2.uml.tests.junit.properties;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.uml2.uml.OperationTemplateParameter;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
/**
 * TestCase for OperationTemplateParameter
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class OperationTemplateParameterPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass operationTemplateParameterMetaClass = UMLPackage.eINSTANCE.getOperationTemplateParameter();

	/**
	 * The type to edit
	 */
	private EObject operationTemplateParameter;

	/**
	 * The reference value for the reference class default
	 */
	private Object referenceValueForDefault;

	/**
	 * The reference value for the reference class signature
	 */
	private Object referenceValueForSignature;

	/**
	 * The reference value for the reference class parameteredElement
	 */
	private Object referenceValueForParameteredElement;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateSignatureMetaClass = UMLPackage.eINSTANCE.getTemplateSignature();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass parameterableElementMetaClass = UMLPackage.eINSTANCE.getParameterableElement();
	/**
	 * The eObjects list contained in widgets
	 */
	private List allInstancesOf;
	/**
	 * Updated value of the feature
	 */
	private static final String UPDATED_VALUE = "value2";

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase#getExpectedModelName()
	 */
	protected String getExpectedModelName() {
		return "expected.uML";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase#getInputModelFolder()
	 */
	protected String getInputModelFolder() {
		return "input";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase#getInputModelName()
	 */
	protected String getInputModelName() {
		return "input.uML";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase#getTestsProjectName()
	 */
	protected String getTestsProjectName() {
		return "org.obeonetwork.dsl.uml2.properties.tests";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase#getExpectedModelFolder()
	 */
	protected String getExpectedModelFolder() {
		// The project that contains models for tests
		return "expected";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase#getImportModelsFolder()
	 */
	protected String getImportModelsFolder() {
		return  "models";
	}
	/**
	 * Create the expected model from the input model
	 * @throws InputModelInvalidException error during expected model initialization
	 * @throws IOException error during expected model serialization
	 */
	protected void initializeExpectedModelForOperationTemplateParameterSignature() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operationTemplateParameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationTemplateParameterMetaClass);
		if (operationTemplateParameter == null)
			throw new InputModelInvalidException(operationTemplateParameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateSignatureMetaClass);
		referenceValueForSignature = bot.changeReferenceValue(allInstancesOf, ((OperationTemplateParameter)operationTemplateParameter).getSignature());
		cc.append(SetCommand.create(editingDomain, operationTemplateParameter, UMLPackage.eINSTANCE.getTemplateParameter_Signature(), referenceValueForSignature));
		editingDomain.getCommandStack().execute(cc);
		expectedModel.save(Collections.EMPTY_MAP);
	}
	/**
	 * Test the editor properties :
	 * - init the input model
	 * - calculate the expected model
	 * - initialize the model editor
	 * - change the properties in the editor properties
	 * - compare the expected and the real model : if they are equals the test pass
	 * - delete the models
	 */
	public void testEditOperationTemplateParameterSignature() throws Exception {

		// Import the input model
		initializeInputModel();

		operationTemplateParameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationTemplateParameterMetaClass);
		if (operationTemplateParameter == null)
			throw new InputModelInvalidException(operationTemplateParameterMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationTemplateParameterSignature();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OperationTemplateParameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationTemplateParameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationTemplateParameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the signature feature of the OperationTemplateParameter element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OperationTemplateParameter.Properties.signature, allInstancesOf.indexOf(referenceValueForSignature), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




}
