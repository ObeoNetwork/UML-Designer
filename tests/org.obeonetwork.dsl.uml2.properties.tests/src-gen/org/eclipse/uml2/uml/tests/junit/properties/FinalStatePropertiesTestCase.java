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
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for FinalState
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class FinalStatePropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass finalStateMetaClass = UMLPackage.eINSTANCE.getFinalState();

	/**
	 * The type to edit
	 */
	private EObject finalState;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class redefinedState
	 */
	private Object referenceValueForRedefinedState;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class container
	 */
	private Object referenceValueForContainer;

	/**
	 * The reference value for the reference class submachine
	 */
	private Object referenceValueForSubmachine;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass regionMetaClass = UMLPackage.eINSTANCE.getRegion();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass stateMetaClass = UMLPackage.eINSTANCE.getState();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass stateMachineMetaClass = UMLPackage.eINSTANCE.getStateMachine();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass dependencyMetaClass = UMLPackage.eINSTANCE.getDependency();
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
	protected void initializeExpectedModelForFinalStateName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject finalState = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, finalState, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditFinalStateName() throws Exception {

		// Import the input model
		initializeInputModel();

		finalState = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFinalStateName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FinalState element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the FinalState element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FinalState.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFinalStateVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject finalState = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, finalState, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditFinalStateVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		finalState = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((FinalState)finalState).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForFinalStateVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FinalState element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the FinalState element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FinalState.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFinalStateClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject finalState = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((FinalState)finalState).getClientDependency());
		cc.append(AddCommand.create(editingDomain, finalState, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditFinalStateClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		finalState = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFinalStateClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FinalState element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the FinalState element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FinalState.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFinalStateClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject finalState = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)finalState.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, finalState, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
		}
		else {
			throw new InputModelInvalidException();
		}
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
	public void testRemoveFinalStateClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		finalState = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFinalStateClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FinalState element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the FinalState element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FinalState.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFinalStateIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject finalState = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, finalState, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditFinalStateIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		finalState = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFinalStateIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FinalState element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the FinalState element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FinalState.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFinalStateContainer() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject finalState = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		referenceValueForContainer = bot.changeReferenceValue(allInstancesOf, ((FinalState)finalState).getContainer());
		cc.append(SetCommand.create(editingDomain, finalState, UMLPackage.eINSTANCE.getVertex_Container(), referenceValueForContainer));
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
	public void testEditFinalStateContainer() throws Exception {

		// Import the input model
		initializeInputModel();

		finalState = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFinalStateContainer();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FinalState element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the container feature of the FinalState element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FinalState.Properties.container, allInstancesOf.indexOf(referenceValueForContainer)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFinalStateContainer() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject finalState = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		cc.append(SetCommand.create(editingDomain, finalState, UMLPackage.eINSTANCE.getVertex_Container(), null));
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
	public void testRemoveFinalStateContainer() throws Exception {

		// Import the input model
		initializeInputModel();

		finalState = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFinalStateContainer();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FinalState element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the container feature of the FinalState element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FinalState.Properties.container, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForFinalStateSubmachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject finalState = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMachineMetaClass);
		referenceValueForSubmachine = bot.changeReferenceValue(allInstancesOf, ((FinalState)finalState).getSubmachine());
		cc.append(SetCommand.create(editingDomain, finalState, UMLPackage.eINSTANCE.getState_Submachine(), referenceValueForSubmachine));
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
	public void testEditFinalStateSubmachine() throws Exception {

		// Import the input model
		initializeInputModel();

		finalState = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFinalStateSubmachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FinalState element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the submachine feature of the FinalState element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FinalState.Properties.submachine, allInstancesOf.indexOf(referenceValueForSubmachine)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFinalStateSubmachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject finalState = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMachineMetaClass);
		cc.append(SetCommand.create(editingDomain, finalState, UMLPackage.eINSTANCE.getState_Submachine(), null));
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
	public void testRemoveFinalStateSubmachine() throws Exception {

		// Import the input model
		initializeInputModel();

		finalState = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFinalStateSubmachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FinalState element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the submachine feature of the FinalState element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FinalState.Properties.submachine, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForFinalStateRedefinedState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject finalState = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForRedefinedState = bot.changeReferenceValue(allInstancesOf, ((FinalState)finalState).getRedefinedState());
		cc.append(SetCommand.create(editingDomain, finalState, UMLPackage.eINSTANCE.getState_RedefinedState(), referenceValueForRedefinedState));
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
	public void testEditFinalStateRedefinedState() throws Exception {

		// Import the input model
		initializeInputModel();

		finalState = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFinalStateRedefinedState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FinalState element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the redefinedState feature of the FinalState element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FinalState.Properties.redefinedState, allInstancesOf.indexOf(referenceValueForRedefinedState)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFinalStateRedefinedState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject finalState = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		cc.append(SetCommand.create(editingDomain, finalState, UMLPackage.eINSTANCE.getState_RedefinedState(), null));
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
	public void testRemoveFinalStateRedefinedState() throws Exception {

		// Import the input model
		initializeInputModel();

		finalState = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (finalState == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFinalStateRedefinedState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FinalState element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), finalStateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(finalStateMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the redefinedState feature of the FinalState element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FinalState.Properties.redefinedState, bot.selectNode(modelEditor, firstInstanceOf));
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
