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

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Pseudostate;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Pseudostate
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class PseudostateTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass pseudostateMetaClass = UMLPackage.eINSTANCE.getPseudostate();

	/**
	 * The type to edit
	 */
	private EObject pseudostate;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class kind
	 */
	private Object enumValueForKind;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class stateMachine
	 */
	private Object referenceValueForStateMachine;

	/**
	 * The reference value for the reference class state
	 */
	private Object referenceValueForState;

	/**
	 * The reference value for the reference class container
	 */
	private Object referenceValueForContainer;
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
	protected void initializeExpectedModelForPseudostateName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, pseudostate, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditPseudostateName() throws Exception {

		// Import the input model
		initializeInputModel();

		pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPseudostateName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pseudostate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pseudostateMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Pseudostate element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Pseudostate.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPseudostateVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, pseudostate, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditPseudostateVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Pseudostate)pseudostate).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForPseudostateVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pseudostate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pseudostateMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Pseudostate element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Pseudostate.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPseudostateClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Pseudostate)pseudostate).getClientDependency());
		cc.append(AddCommand.create(editingDomain, pseudostate, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditPseudostateClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPseudostateClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pseudostate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pseudostateMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Pseudostate element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Pseudostate.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForPseudostateClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)pseudostate.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, pseudostate, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemovePseudostateClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPseudostateClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pseudostate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pseudostateMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Pseudostate element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Pseudostate.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPseudostateContainer() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		referenceValueForContainer = bot.changeReferenceValue(allInstancesOf, ((Pseudostate)pseudostate).getContainer());
		cc.append(SetCommand.create(editingDomain, pseudostate, UMLPackage.eINSTANCE.getVertex_Container(), referenceValueForContainer));
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
	public void testEditPseudostateContainer() throws Exception {

		// Import the input model
		initializeInputModel();

		pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPseudostateContainer();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pseudostate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pseudostateMetaClass, firstInstanceOf, null);

		// Change value of the container feature of the Pseudostate element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Pseudostate.Properties.container, allInstancesOf.indexOf(referenceValueForContainer)+1);

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
	protected void initializeRemoveExpectedModelForPseudostateContainer() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		cc.append(SetCommand.create(editingDomain, pseudostate, UMLPackage.eINSTANCE.getVertex_Container(), null));
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
	public void testRemovePseudostateContainer() throws Exception {

		// Import the input model
		initializeInputModel();

		pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPseudostateContainer();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pseudostate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pseudostateMetaClass, firstInstanceOf, null);

		// Change value of the container feature of the Pseudostate element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Pseudostate.Properties.container);
		

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
	protected void initializeExpectedModelForPseudostateKind() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, pseudostate, UMLPackage.eINSTANCE.getPseudostate_Kind(), UPDATED_VALUE));
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
	public void testEditPseudostateKind() throws Exception {

		// Import the input model
		initializeInputModel();

		pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		enumValueForKind = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getPseudostateKind(), ((Pseudostate)pseudostate).getKind().getLiteral());
		// Create the expected model
		initializeExpectedModelForPseudostateKind();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pseudostate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pseudostateMetaClass, firstInstanceOf, null);

		// Change value of the kind feature of the Pseudostate element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Pseudostate.Properties.kind, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPseudostateStateMachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMachineMetaClass);
		referenceValueForStateMachine = bot.changeReferenceValue(allInstancesOf, ((Pseudostate)pseudostate).getStateMachine());
		cc.append(SetCommand.create(editingDomain, pseudostate, UMLPackage.eINSTANCE.getPseudostate_StateMachine(), referenceValueForStateMachine));
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
	public void testEditPseudostateStateMachine() throws Exception {

		// Import the input model
		initializeInputModel();

		pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPseudostateStateMachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pseudostate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pseudostateMetaClass, firstInstanceOf, null);

		// Change value of the stateMachine feature of the Pseudostate element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Pseudostate.Properties.stateMachine, allInstancesOf.indexOf(referenceValueForStateMachine)+1);

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
	protected void initializeRemoveExpectedModelForPseudostateStateMachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMachineMetaClass);
		cc.append(SetCommand.create(editingDomain, pseudostate, UMLPackage.eINSTANCE.getPseudostate_StateMachine(), null));
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
	public void testRemovePseudostateStateMachine() throws Exception {

		// Import the input model
		initializeInputModel();

		pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPseudostateStateMachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pseudostate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pseudostateMetaClass, firstInstanceOf, null);

		// Change value of the stateMachine feature of the Pseudostate element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Pseudostate.Properties.stateMachine);
		

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
	protected void initializeExpectedModelForPseudostateState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForState = bot.changeReferenceValue(allInstancesOf, ((Pseudostate)pseudostate).getState());
		cc.append(SetCommand.create(editingDomain, pseudostate, UMLPackage.eINSTANCE.getPseudostate_State(), referenceValueForState));
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
	public void testEditPseudostateState() throws Exception {

		// Import the input model
		initializeInputModel();

		pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPseudostateState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pseudostate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pseudostateMetaClass, firstInstanceOf, null);

		// Change value of the state feature of the Pseudostate element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Pseudostate.Properties.state, allInstancesOf.indexOf(referenceValueForState)+1);

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
	protected void initializeRemoveExpectedModelForPseudostateState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		cc.append(SetCommand.create(editingDomain, pseudostate, UMLPackage.eINSTANCE.getPseudostate_State(), null));
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
	public void testRemovePseudostateState() throws Exception {

		// Import the input model
		initializeInputModel();

		pseudostate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (pseudostate == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPseudostateState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pseudostate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pseudostateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pseudostateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pseudostateMetaClass, firstInstanceOf, null);

		// Change value of the state feature of the Pseudostate element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Pseudostate.Properties.state);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
