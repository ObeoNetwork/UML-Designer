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
package org.eclipse.uml2.uml.tests.junit;

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
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.uml2.uml.LinkEndCreationData;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
/**
 * TestCase for LinkEndCreationData
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class LinkEndCreationDataTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass linkEndCreationDataMetaClass = UMLPackage.eINSTANCE.getLinkEndCreationData();

	/**
	 * The type to edit
	 */
	private EObject linkEndCreationData;

	/**
	 * The reference value for the reference class value
	 */
	private Object referenceValueForValue;

	/**
	 * The reference value for the reference class insertAt
	 */
	private Object referenceValueForInsertAt;

	/**
	 * The reference value for the reference class end
	 */
	private Object referenceValueForEnd;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass propertyMetaClass = UMLPackage.eINSTANCE.getProperty();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass inputPinMetaClass = UMLPackage.eINSTANCE.getInputPin();
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
	protected void initializeExpectedModelForLinkEndCreationDataValue() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, inputPinMetaClass);
		referenceValueForValue = bot.changeReferenceValue(allInstancesOf, ((LinkEndCreationData)linkEndCreationData).getValue());
		cc.append(SetCommand.create(editingDomain, linkEndCreationData, UMLPackage.eINSTANCE.getLinkEndData_Value(), referenceValueForValue));
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
	public void testEditLinkEndCreationDataValue() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLinkEndCreationDataValue();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndCreationData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndCreationDataMetaClass, firstInstanceOf, null);

		// Change value of the value feature of the LinkEndCreationData element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LinkEndCreationData.Properties.value, allInstancesOf.indexOf(referenceValueForValue)+1);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
	/**
	 * Create the expected model from the input model
	 * @throws InputModelInvalidException error during expected model initialization
	 * @throws IOException error during expected model serialization
	 */
	protected void initializeRemoveExpectedModelForLinkEndCreationDataValue() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, inputPinMetaClass);
		cc.append(SetCommand.create(editingDomain, linkEndCreationData, UMLPackage.eINSTANCE.getLinkEndData_Value(), null));
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
	public void testRemoveLinkEndCreationDataValue() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLinkEndCreationDataValue();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndCreationData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndCreationDataMetaClass, firstInstanceOf, null);

		// Change value of the value feature of the LinkEndCreationData element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LinkEndCreationData.Properties.value);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
	/**
	 * Create the expected model from the input model
	 * @throws InputModelInvalidException error during expected model initialization
	 * @throws IOException error during expected model serialization
	 */
	protected void initializeExpectedModelForLinkEndCreationDataEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForEnd = bot.changeReferenceValue(allInstancesOf, ((LinkEndCreationData)linkEndCreationData).getEnd());
		cc.append(SetCommand.create(editingDomain, linkEndCreationData, UMLPackage.eINSTANCE.getLinkEndData_End(), referenceValueForEnd));
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
	public void testEditLinkEndCreationDataEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLinkEndCreationDataEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndCreationData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndCreationDataMetaClass, firstInstanceOf, null);

		// Change value of the end feature of the LinkEndCreationData element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LinkEndCreationData.Properties.end, allInstancesOf.indexOf(referenceValueForEnd));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
	/**
	 * Create the expected model from the input model
	 * @throws InputModelInvalidException error during expected model initialization
	 * @throws IOException error during expected model serialization
	 */
	protected void initializeExpectedModelForLinkEndCreationDataIsReplaceAll() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, linkEndCreationData, UMLPackage.eINSTANCE.getLinkEndCreationData_IsReplaceAll(), UPDATED_VALUE));
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
	public void testEditLinkEndCreationDataIsReplaceAll() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLinkEndCreationDataIsReplaceAll();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndCreationData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndCreationDataMetaClass, firstInstanceOf, null);

		// Change value of the isReplaceAll feature of the LinkEndCreationData element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
	/**
	 * Create the expected model from the input model
	 * @throws InputModelInvalidException error during expected model initialization
	 * @throws IOException error during expected model serialization
	 */
	protected void initializeExpectedModelForLinkEndCreationDataInsertAt() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, inputPinMetaClass);
		referenceValueForInsertAt = bot.changeReferenceValue(allInstancesOf, ((LinkEndCreationData)linkEndCreationData).getInsertAt());
		cc.append(SetCommand.create(editingDomain, linkEndCreationData, UMLPackage.eINSTANCE.getLinkEndCreationData_InsertAt(), referenceValueForInsertAt));
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
	public void testEditLinkEndCreationDataInsertAt() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLinkEndCreationDataInsertAt();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndCreationData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndCreationDataMetaClass, firstInstanceOf, null);

		// Change value of the insertAt feature of the LinkEndCreationData element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LinkEndCreationData.Properties.insertAt, allInstancesOf.indexOf(referenceValueForInsertAt)+1);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
	/**
	 * Create the expected model from the input model
	 * @throws InputModelInvalidException error during expected model initialization
	 * @throws IOException error during expected model serialization
	 */
	protected void initializeRemoveExpectedModelForLinkEndCreationDataInsertAt() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, inputPinMetaClass);
		cc.append(SetCommand.create(editingDomain, linkEndCreationData, UMLPackage.eINSTANCE.getLinkEndCreationData_InsertAt(), null));
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
	public void testRemoveLinkEndCreationDataInsertAt() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndCreationData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (linkEndCreationData == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLinkEndCreationDataInsertAt();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndCreationData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndCreationDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndCreationDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndCreationDataMetaClass, firstInstanceOf, null);

		// Change value of the insertAt feature of the LinkEndCreationData element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LinkEndCreationData.Properties.insertAt);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}










}
