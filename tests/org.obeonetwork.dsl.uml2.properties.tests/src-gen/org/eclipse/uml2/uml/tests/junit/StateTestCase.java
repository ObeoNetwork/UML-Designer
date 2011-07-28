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
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for State
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class StateTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass stateMetaClass = UMLPackage.eINSTANCE.getState();

	/**
	 * The type to edit
	 */
	private EObject state;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class redefinedState
	 */
	private Object referenceValueForRedefinedState;

	/**
	 * The reference value for the reference class container
	 */
	private Object referenceValueForContainer;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

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
	protected void initializeExpectedModelForStateName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject state = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, state, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditStateName() throws Exception {

		// Import the input model
		initializeInputModel();

		state = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the State element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the State element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.State.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForStateVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject state = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, state, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditStateVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		state = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((State)state).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForStateVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the State element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the State element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.State.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForStateClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject state = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((State)state).getClientDependency());
		cc.append(AddCommand.create(editingDomain, state, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditStateClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		state = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the State element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the State element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.State.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForStateClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject state = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)state.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, state, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveStateClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		state = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the State element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the State element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.State.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForStateIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject state = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, state, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditStateIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		state = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the State element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the State element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.State.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForStateContainer() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject state = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		referenceValueForContainer = bot.changeReferenceValue(allInstancesOf, ((State)state).getContainer());
		cc.append(SetCommand.create(editingDomain, state, UMLPackage.eINSTANCE.getVertex_Container(), referenceValueForContainer));
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
	public void testEditStateContainer() throws Exception {

		// Import the input model
		initializeInputModel();

		state = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateContainer();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the State element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMetaClass, firstInstanceOf, null);

		// Change value of the container feature of the State element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.State.Properties.container, allInstancesOf.indexOf(referenceValueForContainer)+1);

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
	protected void initializeRemoveExpectedModelForStateContainer() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject state = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		cc.append(SetCommand.create(editingDomain, state, UMLPackage.eINSTANCE.getVertex_Container(), null));
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
	public void testRemoveStateContainer() throws Exception {

		// Import the input model
		initializeInputModel();

		state = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateContainer();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the State element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMetaClass, firstInstanceOf, null);

		// Change value of the container feature of the State element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.State.Properties.container);
		

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
	protected void initializeExpectedModelForStateSubmachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject state = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMachineMetaClass);
		referenceValueForSubmachine = bot.changeReferenceValue(allInstancesOf, ((State)state).getSubmachine());
		cc.append(SetCommand.create(editingDomain, state, UMLPackage.eINSTANCE.getState_Submachine(), referenceValueForSubmachine));
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
	public void testEditStateSubmachine() throws Exception {

		// Import the input model
		initializeInputModel();

		state = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateSubmachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the State element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMetaClass, firstInstanceOf, null);

		// Change value of the submachine feature of the State element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.State.Properties.submachine, allInstancesOf.indexOf(referenceValueForSubmachine)+1);

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
	protected void initializeRemoveExpectedModelForStateSubmachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject state = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMachineMetaClass);
		cc.append(SetCommand.create(editingDomain, state, UMLPackage.eINSTANCE.getState_Submachine(), null));
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
	public void testRemoveStateSubmachine() throws Exception {

		// Import the input model
		initializeInputModel();

		state = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateSubmachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the State element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMetaClass, firstInstanceOf, null);

		// Change value of the submachine feature of the State element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.State.Properties.submachine);
		

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
	protected void initializeExpectedModelForStateRedefinedState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject state = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForRedefinedState = bot.changeReferenceValue(allInstancesOf, ((State)state).getRedefinedState());
		cc.append(SetCommand.create(editingDomain, state, UMLPackage.eINSTANCE.getState_RedefinedState(), referenceValueForRedefinedState));
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
	public void testEditStateRedefinedState() throws Exception {

		// Import the input model
		initializeInputModel();

		state = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateRedefinedState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the State element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMetaClass, firstInstanceOf, null);

		// Change value of the redefinedState feature of the State element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.State.Properties.redefinedState, allInstancesOf.indexOf(referenceValueForRedefinedState)+1);

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
	protected void initializeRemoveExpectedModelForStateRedefinedState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject state = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		cc.append(SetCommand.create(editingDomain, state, UMLPackage.eINSTANCE.getState_RedefinedState(), null));
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
	public void testRemoveStateRedefinedState() throws Exception {

		// Import the input model
		initializeInputModel();

		state = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (state == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateRedefinedState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the State element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMetaClass, firstInstanceOf, null);

		// Change value of the redefinedState feature of the State element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.State.Properties.redefinedState);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
