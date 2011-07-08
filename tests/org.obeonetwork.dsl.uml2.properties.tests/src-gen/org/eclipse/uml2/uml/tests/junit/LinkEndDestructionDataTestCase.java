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
import org.eclipse.uml2.uml.LinkEndDestructionData;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
/**
 * TestCase for LinkEndDestructionData
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class LinkEndDestructionDataTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass linkEndDestructionDataMetaClass = UMLPackage.eINSTANCE.getLinkEndDestructionData();

	/**
	 * The type to edit
	 */
	private EObject linkEndDestructionData;

	/**
	 * The reference value for the reference class value
	 */
	private Object referenceValueForValue;

	/**
	 * The reference value for the reference class destroyAt
	 */
	private Object referenceValueForDestroyAt;

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
	protected void initializeExpectedModelForLinkEndDestructionDataValue() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, inputPinMetaClass);
		referenceValueForValue = bot.changeReferenceValue(allInstancesOf, ((LinkEndDestructionData)linkEndDestructionData).getValue());
		cc.append(SetCommand.create(editingDomain, linkEndDestructionData, UMLPackage.eINSTANCE.getLinkEndData_Value(), referenceValueForValue));
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
	public void testEditLinkEndDestructionDataValue() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLinkEndDestructionDataValue();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndDestructionData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndDestructionDataMetaClass, firstInstanceOf, null);

		// Change value of the value feature of the LinkEndDestructionData element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LinkEndDestructionData.Properties.value, allInstancesOf.indexOf(referenceValueForValue)+1);

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
	protected void initializeRemoveExpectedModelForLinkEndDestructionDataValue() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, inputPinMetaClass);
		cc.append(SetCommand.create(editingDomain, linkEndDestructionData, UMLPackage.eINSTANCE.getLinkEndData_Value(), null));
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
	public void testRemoveLinkEndDestructionDataValue() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLinkEndDestructionDataValue();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndDestructionData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndDestructionDataMetaClass, firstInstanceOf, null);

		// Change value of the value feature of the LinkEndDestructionData element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LinkEndDestructionData.Properties.value);
		

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
	protected void initializeExpectedModelForLinkEndDestructionDataEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForEnd = bot.changeReferenceValue(allInstancesOf, ((LinkEndDestructionData)linkEndDestructionData).getEnd());
		cc.append(SetCommand.create(editingDomain, linkEndDestructionData, UMLPackage.eINSTANCE.getLinkEndData_End(), referenceValueForEnd));
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
	public void testEditLinkEndDestructionDataEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLinkEndDestructionDataEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndDestructionData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndDestructionDataMetaClass, firstInstanceOf, null);

		// Change value of the end feature of the LinkEndDestructionData element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LinkEndDestructionData.Properties.end, allInstancesOf.indexOf(referenceValueForEnd));

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
	protected void initializeExpectedModelForLinkEndDestructionDataIsDestroyDuplicates() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, linkEndDestructionData, UMLPackage.eINSTANCE.getLinkEndDestructionData_IsDestroyDuplicates(), UPDATED_VALUE));
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
	public void testEditLinkEndDestructionDataIsDestroyDuplicates() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLinkEndDestructionDataIsDestroyDuplicates();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndDestructionData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndDestructionDataMetaClass, firstInstanceOf, null);

		// Change value of the isDestroyDuplicates feature of the LinkEndDestructionData element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLinkEndDestructionDataDestroyAt() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, inputPinMetaClass);
		referenceValueForDestroyAt = bot.changeReferenceValue(allInstancesOf, ((LinkEndDestructionData)linkEndDestructionData).getDestroyAt());
		cc.append(SetCommand.create(editingDomain, linkEndDestructionData, UMLPackage.eINSTANCE.getLinkEndDestructionData_DestroyAt(), referenceValueForDestroyAt));
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
	public void testEditLinkEndDestructionDataDestroyAt() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLinkEndDestructionDataDestroyAt();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndDestructionData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndDestructionDataMetaClass, firstInstanceOf, null);

		// Change value of the destroyAt feature of the LinkEndDestructionData element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt, allInstancesOf.indexOf(referenceValueForDestroyAt)+1);

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
	protected void initializeRemoveExpectedModelForLinkEndDestructionDataDestroyAt() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, inputPinMetaClass);
		cc.append(SetCommand.create(editingDomain, linkEndDestructionData, UMLPackage.eINSTANCE.getLinkEndDestructionData_DestroyAt(), null));
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
	public void testRemoveLinkEndDestructionDataDestroyAt() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndDestructionData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (linkEndDestructionData == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLinkEndDestructionDataDestroyAt();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LinkEndDestructionData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDestructionDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndDestructionDataMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, linkEndDestructionDataMetaClass, firstInstanceOf, null);

		// Change value of the destroyAt feature of the LinkEndDestructionData element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}










}
