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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.InterruptibleActivityRegion;
/**
 * TestCase for InterruptibleActivityRegion
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class InterruptibleActivityRegionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass interruptibleActivityRegionMetaClass = UMLPackage.eINSTANCE.getInterruptibleActivityRegion();

	/**
	 * The type to edit
	 */
	private EObject interruptibleActivityRegion;

	/**
	 * The reference value for the reference class interruptingEdge
	 */
	private Object referenceValueForInterruptingEdge;

	/**
	 * The reference value for the reference class inActivity
	 */
	private Object referenceValueForInActivity;

	/**
	 * The reference value for the reference class node
	 */
	private Object referenceValueForNode;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityMetaClass = UMLPackage.eINSTANCE.getActivity();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityNodeMetaClass = UMLPackage.eINSTANCE.getActivityNode();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityEdgeMetaClass = UMLPackage.eINSTANCE.getActivityEdge();
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
	protected void initializeExpectedModelForInterruptibleActivityRegionInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interruptibleActivityRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interruptibleActivityRegionMetaClass);
		if (interruptibleActivityRegion == null)
			throw new InputModelInvalidException(interruptibleActivityRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForInActivity = bot.changeReferenceValue(allInstancesOf, ((InterruptibleActivityRegion)interruptibleActivityRegion).getInActivity());
		cc.append(SetCommand.create(editingDomain, interruptibleActivityRegion, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), referenceValueForInActivity));
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
	public void testEditInterruptibleActivityRegionInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		interruptibleActivityRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interruptibleActivityRegionMetaClass);
		if (interruptibleActivityRegion == null)
			throw new InputModelInvalidException(interruptibleActivityRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterruptibleActivityRegionInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InterruptibleActivityRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interruptibleActivityRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interruptibleActivityRegionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inActivity feature of the InterruptibleActivityRegion element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity, allInstancesOf.indexOf(referenceValueForInActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInterruptibleActivityRegionInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interruptibleActivityRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interruptibleActivityRegionMetaClass);
		if (interruptibleActivityRegion == null)
			throw new InputModelInvalidException(interruptibleActivityRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, interruptibleActivityRegion, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), null));
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
	public void testRemoveInterruptibleActivityRegionInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		interruptibleActivityRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interruptibleActivityRegionMetaClass);
		if (interruptibleActivityRegion == null)
			throw new InputModelInvalidException(interruptibleActivityRegionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInterruptibleActivityRegionInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InterruptibleActivityRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interruptibleActivityRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interruptibleActivityRegionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inActivity feature of the InterruptibleActivityRegion element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity, bot.selectNode(modelEditor, firstInstanceOf));
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




}
