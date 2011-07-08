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
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for InputPin
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class InputPinTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass inputPinMetaClass = UMLPackage.eINSTANCE.getInputPin();

	/**
	 * The type to edit
	 */
	private EObject inputPin;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class ordering
	 */
	private Object enumValueForOrdering;
	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class selection
	 */
	private Object referenceValueForSelection;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class inState
	 */
	private Object referenceValueForInState;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;
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
	protected void initializeExpectedModelForInputPinName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditInputPinName() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the InputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InputPin.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInputPinVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditInputPinVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((InputPin)inputPin).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForInputPinVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the InputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InputPin.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInputPinClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((InputPin)inputPin).getClientDependency());
		cc.append(AddCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditInputPinClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InputPin.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForInputPinClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)inputPin.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveInputPinClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInputPinClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InputPin.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInputPinIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditInputPinIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the InputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InputPin.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInputPinInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((InputPin)inputPin).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditInputPinInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the InputPin element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InputPin.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForInputPinInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveInputPinInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInputPinInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the InputPin element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InputPin.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForInputPinActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((InputPin)inputPin).getActivity());
		cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditInputPinActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the InputPin element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InputPin.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForInputPinActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveInputPinActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInputPinActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the InputPin element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InputPin.Properties.activity);
		

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
	protected void initializeExpectedModelForInputPinInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((InputPin)inputPin).getInPartition());
		cc.append(AddCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditInputPinInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the InputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InputPin.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForInputPinInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)inputPin.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveInputPinInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInputPinInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the InputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InputPin.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInputPinInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((InputPin)inputPin).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditInputPinInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the InputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InputPin.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForInputPinInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)inputPin.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveInputPinInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInputPinInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the InputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InputPin.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInputPinOrdering() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getObjectNode_Ordering(), UPDATED_VALUE));
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
	public void testEditInputPinOrdering() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		enumValueForOrdering = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getObjectNodeOrderingKind(), ((InputPin)inputPin).getOrdering().getLiteral());
		// Create the expected model
		initializeExpectedModelForInputPinOrdering();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the ordering feature of the InputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InputPin.Properties.ordering, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInputPinIsControlType() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getObjectNode_IsControlType(), UPDATED_VALUE));
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
	public void testEditInputPinIsControlType() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinIsControlType();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the isControlType feature of the InputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InputPin.Properties.isControlType, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInputPinInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForInState = bot.changeReferenceValue(allInstancesOf, ((InputPin)inputPin).getInState());
		cc.append(AddCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getObjectNode_InState(), referenceValueForInState));
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
	public void testEditInputPinInState() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the InputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InputPin.Properties.inState, referenceValueForInState);

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
	protected void initializeRemoveExpectedModelForInputPinInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)inputPin.eGet(UMLPackage.eINSTANCE.getObjectNode_InState());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getObjectNode_InState(), allReferencedInstances.get(0)));
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
	public void testRemoveInputPinInState() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInputPinInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the InputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InputPin.Properties.inState, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInputPinIsOrdered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), UPDATED_VALUE));
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
	public void testEditInputPinIsOrdered() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinIsOrdered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the isOrdered feature of the InputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InputPin.Properties.isOrdered, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInputPinIsUnique() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), UPDATED_VALUE));
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
	public void testEditInputPinIsUnique() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinIsUnique();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the isUnique feature of the InputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InputPin.Properties.isUnique, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInputPinIsControl() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject inputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, inputPin, UMLPackage.eINSTANCE.getPin_IsControl(), UPDATED_VALUE));
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
	public void testEditInputPinIsControl() throws Exception {

		// Import the input model
		initializeInputModel();

		inputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (inputPin == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInputPinIsControl();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), inputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(inputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, inputPinMetaClass, firstInstanceOf, null);

		// Change value of the isControl feature of the InputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InputPin.Properties.isControl, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






























}
