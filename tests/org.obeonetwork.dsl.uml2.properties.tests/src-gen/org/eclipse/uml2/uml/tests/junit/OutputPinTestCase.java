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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.OutputPin;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for OutputPin
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class OutputPinTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass outputPinMetaClass = UMLPackage.eINSTANCE.getOutputPin();

	/**
	 * The type to edit
	 */
	private EObject outputPin;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class ordering
	 */
	private Object enumValueForOrdering;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

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
	 * The reference value for the reference class selection
	 */
	private Object referenceValueForSelection;

	/**
	 * The reference value for the reference class inState
	 */
	private Object referenceValueForInState;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;
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
	protected void initializeExpectedModelForOutputPinName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditOutputPinName() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the OutputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOutputPinVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditOutputPinVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((OutputPin)outputPin).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForOutputPinVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the OutputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOutputPinClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((OutputPin)outputPin).getClientDependency());
		cc.append(AddCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditOutputPinClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the OutputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForOutputPinClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)outputPin.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveOutputPinClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOutputPinClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the OutputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOutputPinIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditOutputPinIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the OutputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOutputPinInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((OutputPin)outputPin).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditOutputPinInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the OutputPin element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForOutputPinInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveOutputPinInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOutputPinInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the OutputPin element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForOutputPinActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((OutputPin)outputPin).getActivity());
		cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditOutputPinActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the OutputPin element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForOutputPinActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveOutputPinActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOutputPinActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the OutputPin element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.activity);
		

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
	protected void initializeExpectedModelForOutputPinInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((OutputPin)outputPin).getInPartition());
		cc.append(AddCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditOutputPinInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the OutputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForOutputPinInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)outputPin.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveOutputPinInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOutputPinInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the OutputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOutputPinInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((OutputPin)outputPin).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditOutputPinInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the OutputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForOutputPinInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)outputPin.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveOutputPinInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOutputPinInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the OutputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOutputPinOrdering() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getObjectNode_Ordering(), UPDATED_VALUE));
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
	public void testEditOutputPinOrdering() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		enumValueForOrdering = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getObjectNodeOrderingKind(), ((OutputPin)outputPin).getOrdering().getLiteral());
		// Create the expected model
		initializeExpectedModelForOutputPinOrdering();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the ordering feature of the OutputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.ordering, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOutputPinIsControlType() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getObjectNode_IsControlType(), UPDATED_VALUE));
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
	public void testEditOutputPinIsControlType() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinIsControlType();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the isControlType feature of the OutputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.isControlType, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOutputPinInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForInState = bot.changeReferenceValue(allInstancesOf, ((OutputPin)outputPin).getInState());
		cc.append(AddCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getObjectNode_InState(), referenceValueForInState));
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
	public void testEditOutputPinInState() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the OutputPin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.inState, referenceValueForInState);

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
	protected void initializeRemoveExpectedModelForOutputPinInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)outputPin.eGet(UMLPackage.eINSTANCE.getObjectNode_InState());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getObjectNode_InState(), allReferencedInstances.get(0)));
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
	public void testRemoveOutputPinInState() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOutputPinInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the OutputPin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.inState, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOutputPinIsOrdered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), UPDATED_VALUE));
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
	public void testEditOutputPinIsOrdered() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinIsOrdered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the isOrdered feature of the OutputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.isOrdered, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOutputPinIsUnique() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), UPDATED_VALUE));
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
	public void testEditOutputPinIsUnique() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinIsUnique();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the isUnique feature of the OutputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.isUnique, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOutputPinIsControl() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject outputPin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, outputPin, UMLPackage.eINSTANCE.getPin_IsControl(), UPDATED_VALUE));
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
	public void testEditOutputPinIsControl() throws Exception {

		// Import the input model
		initializeInputModel();

		outputPin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (outputPin == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOutputPinIsControl();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OutputPin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), outputPinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(outputPinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, outputPinMetaClass, firstInstanceOf, null);

		// Change value of the isControl feature of the OutputPin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OutputPin.Properties.isControl, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






























}
