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

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Slot;
/**
 * TestCase for Slot
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class SlotPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass slotMetaClass = UMLPackage.eINSTANCE.getSlot();

	/**
	 * The type to edit
	 */
	private EObject slot;

	/**
	 * The reference value for the reference class definingFeature
	 */
	private Object referenceValueForDefiningFeature;

	/**
	 * The reference value for the reference class owningInstance
	 */
	private Object referenceValueForOwningInstance;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass structuralFeatureMetaClass = UMLPackage.eINSTANCE.getStructuralFeature();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass instanceSpecificationMetaClass = UMLPackage.eINSTANCE.getInstanceSpecification();
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
	protected void initializeExpectedModelForSlotOwningInstance() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject slot = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, slotMetaClass);
		if (slot == null)
			throw new InputModelInvalidException(slotMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, instanceSpecificationMetaClass);
		referenceValueForOwningInstance = bot.changeReferenceValue(allInstancesOf, ((Slot)slot).getOwningInstance());
		cc.append(SetCommand.create(editingDomain, slot, UMLPackage.eINSTANCE.getSlot_OwningInstance(), referenceValueForOwningInstance));
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
	public void testEditSlotOwningInstance() throws Exception {

		// Import the input model
		initializeInputModel();

		slot = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), slotMetaClass);
		if (slot == null)
			throw new InputModelInvalidException(slotMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSlotOwningInstance();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Slot element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), slotMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(slotMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningInstance feature of the Slot element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.Slot.Properties.owningInstance, allInstancesOf.indexOf(referenceValueForOwningInstance), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




}
