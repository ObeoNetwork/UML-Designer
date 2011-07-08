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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ActionInputPin;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ActionInputPin
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ActionInputPinTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass actionInputPinMetaClass = UMLPackage.eINSTANCE.getActionInputPin();

	/**
	 * The type to edit
	 */
	private EObject actionInputPin;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class ordering
	 */
	private Object enumValueForOrdering;
	/**
	 * The reference value for the reference class inState
	 */
	private Object referenceValueForInState;

	/**
	 * The reference value for the reference class selection
	 */
	private Object referenceValueForSelection;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass interruptibleActivityRegionMetaClass = UMLPackage.eINSTANCE.getInterruptibleActivityRegion();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass structuredActivityNodeMetaClass = UMLPackage.eINSTANCE.getStructuredActivityNode();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityMetaClass = UMLPackage.eINSTANCE.getActivity();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass behaviorMetaClass = UMLPackage.eINSTANCE.getBehavior();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass stateMetaClass = UMLPackage.eINSTANCE.getState();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityNodeMetaClass = UMLPackage.eINSTANCE.getActivityNode();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass typeMetaClass = UMLPackage.eINSTANCE.getType();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityPartitionMetaClass = UMLPackage.eINSTANCE.getActivityPartition();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityEdgeMetaClass = UMLPackage.eINSTANCE.getActivityEdge();

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
	protected void initializeExpectedModelForActionInputPinName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditActionInputPinName() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ActionInputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActionInputPinVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditActionInputPinVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ActionInputPin)actionInputPin).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForActionInputPinVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ActionInputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActionInputPinClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ActionInputPin)actionInputPin).getClientDependency());
		cc.append(AddCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditActionInputPinClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ActionInputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForActionInputPinClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)actionInputPin.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveActionInputPinClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActionInputPinClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ActionInputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForActionInputPinIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditActionInputPinIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ActionInputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActionInputPinInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ActionInputPin)actionInputPin).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditActionInputPinInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ActionInputPin element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForActionInputPinInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveActionInputPinInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActionInputPinInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ActionInputPin element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForActionInputPinActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ActionInputPin)actionInputPin).getActivity());
		cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditActionInputPinActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ActionInputPin element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForActionInputPinActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveActionInputPinActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActionInputPinActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ActionInputPin element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.activity);
		

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
	protected void initializeExpectedModelForActionInputPinInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ActionInputPin)actionInputPin).getInPartition());
		cc.append(AddCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditActionInputPinInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ActionInputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForActionInputPinInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)actionInputPin.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveActionInputPinInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActionInputPinInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ActionInputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForActionInputPinInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ActionInputPin)actionInputPin).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditActionInputPinInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ActionInputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForActionInputPinInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)actionInputPin.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveActionInputPinInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActionInputPinInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ActionInputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForActionInputPinOrdering() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getObjectNode_Ordering(), UPDATED_VALUE));
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
	public void testEditActionInputPinOrdering() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		enumValueForOrdering = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getObjectNodeOrderingKind(), ((ActionInputPin)actionInputPin).getOrdering().getLiteral());
		// Create the expected model
		initializeExpectedModelForActionInputPinOrdering();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the ordering feature of the ActionInputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.ordering, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActionInputPinIsControlType() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getObjectNode_IsControlType(), UPDATED_VALUE));
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
	public void testEditActionInputPinIsControlType() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinIsControlType();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the isControlType feature of the ActionInputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.isControlType, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActionInputPinInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForInState = bot.changeReferenceValue(allInstancesOf, ((ActionInputPin)actionInputPin).getInState());
		cc.append(AddCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getObjectNode_InState(), referenceValueForInState));
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
	public void testEditActionInputPinInState() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the ActionInputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.inState, referenceValueForInState);

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
	protected void initializeRemoveExpectedModelForActionInputPinInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)actionInputPin.eGet(UMLPackage.eINSTANCE.getObjectNode_InState());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getObjectNode_InState(), allReferencedInstances.get(0)));
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
	public void testRemoveActionInputPinInState() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActionInputPinInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the ActionInputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.inState, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForActionInputPinIsOrdered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), UPDATED_VALUE));
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
	public void testEditActionInputPinIsOrdered() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinIsOrdered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the isOrdered feature of the ActionInputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.isOrdered, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActionInputPinIsUnique() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), UPDATED_VALUE));
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
	public void testEditActionInputPinIsUnique() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinIsUnique();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the isUnique feature of the ActionInputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.isUnique, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActionInputPinIsControl() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, actionInputPin, UMLPackage.eINSTANCE.getPin_IsControl(), UPDATED_VALUE));
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
	public void testEditActionInputPinIsControl() throws Exception {

		// Import the input model
		initializeInputModel();

		actionInputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (actionInputPin == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionInputPinIsControl();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActionInputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionInputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionInputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, actionInputPinMetaClass, firstInstanceOf, null);

		// Change value of the isControl feature of the ActionInputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActionInputPin.Properties.isControl, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






























}
