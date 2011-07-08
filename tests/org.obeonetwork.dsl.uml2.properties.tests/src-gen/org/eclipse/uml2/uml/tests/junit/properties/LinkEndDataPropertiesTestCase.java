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

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.LinkEndData;
/**
 * TestCase for LinkEndData
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class LinkEndDataPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass linkEndDataMetaClass = UMLPackage.eINSTANCE.getLinkEndData();

	/**
	 * The type to edit
	 */
	private EObject linkEndData;

	/**
	 * The reference value for the reference class value
	 */
	private Object referenceValueForValue;

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
	protected void initializeExpectedModelForLinkEndDataValue() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndDataMetaClass);
		if (linkEndData == null)
			throw new InputModelInvalidException(linkEndDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, inputPinMetaClass);
		referenceValueForValue = bot.changeReferenceValue(allInstancesOf, ((LinkEndData)linkEndData).getValue());
		cc.append(SetCommand.create(editingDomain, linkEndData, UMLPackage.eINSTANCE.getLinkEndData_Value(), referenceValueForValue));
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
	public void testEditLinkEndDataValue() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDataMetaClass);
		if (linkEndData == null)
			throw new InputModelInvalidException(linkEndDataMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLinkEndDataValue();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LinkEndData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndDataMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the value feature of the LinkEndData element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.LinkEndData.Properties.value, allInstancesOf.indexOf(referenceValueForValue)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForLinkEndDataValue() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndDataMetaClass);
		if (linkEndData == null)
			throw new InputModelInvalidException(linkEndDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, inputPinMetaClass);
		cc.append(SetCommand.create(editingDomain, linkEndData, UMLPackage.eINSTANCE.getLinkEndData_Value(), null));
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
	public void testRemoveLinkEndDataValue() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDataMetaClass);
		if (linkEndData == null)
			throw new InputModelInvalidException(linkEndDataMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLinkEndDataValue();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LinkEndData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndDataMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the value feature of the LinkEndData element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.LinkEndData.Properties.value, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForLinkEndDataEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject linkEndData = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, linkEndDataMetaClass);
		if (linkEndData == null)
			throw new InputModelInvalidException(linkEndDataMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForEnd = bot.changeReferenceValue(allInstancesOf, ((LinkEndData)linkEndData).getEnd());
		cc.append(SetCommand.create(editingDomain, linkEndData, UMLPackage.eINSTANCE.getLinkEndData_End(), referenceValueForEnd));
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
	public void testEditLinkEndDataEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		linkEndData = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDataMetaClass);
		if (linkEndData == null)
			throw new InputModelInvalidException(linkEndDataMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLinkEndDataEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LinkEndData element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), linkEndDataMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(linkEndDataMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the end feature of the LinkEndData element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.LinkEndData.Properties.end, allInstancesOf.indexOf(referenceValueForEnd), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






}
