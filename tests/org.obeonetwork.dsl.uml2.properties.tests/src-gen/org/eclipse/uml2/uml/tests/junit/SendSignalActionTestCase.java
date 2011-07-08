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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.SendSignalAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for SendSignalAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class SendSignalActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass sendSignalActionMetaClass = UMLPackage.eINSTANCE.getSendSignalAction();

	/**
	 * The type to edit
	 */
	private EObject sendSignalAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class signal
	 */
	private Object referenceValueForSignal;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class onPort
	 */
	private Object referenceValueForOnPort;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;
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
	private EClass portMetaClass = UMLPackage.eINSTANCE.getPort();

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
	private EClass signalMetaClass = UMLPackage.eINSTANCE.getSignal();

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
	protected void initializeExpectedModelForSendSignalActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditSendSignalActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the SendSignalAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSendSignalActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditSendSignalActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((SendSignalAction)sendSignalAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForSendSignalActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the SendSignalAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSendSignalActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((SendSignalAction)sendSignalAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditSendSignalActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SendSignalAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForSendSignalActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)sendSignalAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveSendSignalActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendSignalActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SendSignalAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSendSignalActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditSendSignalActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the SendSignalAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSendSignalActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((SendSignalAction)sendSignalAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditSendSignalActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the SendSignalAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForSendSignalActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveSendSignalActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendSignalActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the SendSignalAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForSendSignalActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((SendSignalAction)sendSignalAction).getActivity());
		cc.append(SetCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditSendSignalActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the SendSignalAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForSendSignalActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveSendSignalActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendSignalActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the SendSignalAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.activity);
		

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
	protected void initializeExpectedModelForSendSignalActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((SendSignalAction)sendSignalAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditSendSignalActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the SendSignalAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForSendSignalActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)sendSignalAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveSendSignalActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendSignalActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the SendSignalAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSendSignalActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((SendSignalAction)sendSignalAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditSendSignalActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the SendSignalAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForSendSignalActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)sendSignalAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveSendSignalActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendSignalActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the SendSignalAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSendSignalActionOnPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, portMetaClass);
		referenceValueForOnPort = bot.changeReferenceValue(allInstancesOf, ((SendSignalAction)sendSignalAction).getOnPort());
		cc.append(SetCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getInvocationAction_OnPort(), referenceValueForOnPort));
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
	public void testEditSendSignalActionOnPort() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalActionOnPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the onPort feature of the SendSignalAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.onPort, allInstancesOf.indexOf(referenceValueForOnPort)+1);

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
	protected void initializeRemoveExpectedModelForSendSignalActionOnPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, portMetaClass);
		cc.append(SetCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getInvocationAction_OnPort(), null));
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
	public void testRemoveSendSignalActionOnPort() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendSignalActionOnPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the onPort feature of the SendSignalAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.onPort);
		

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
	protected void initializeExpectedModelForSendSignalActionSignal() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, signalMetaClass);
		referenceValueForSignal = bot.changeReferenceValue(allInstancesOf, ((SendSignalAction)sendSignalAction).getSignal());
		cc.append(SetCommand.create(editingDomain, sendSignalAction, UMLPackage.eINSTANCE.getSendSignalAction_Signal(), referenceValueForSignal));
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
	public void testEditSendSignalActionSignal() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (sendSignalAction == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalActionSignal();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendSignalActionMetaClass, firstInstanceOf, null);

		// Change value of the signal feature of the SendSignalAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendSignalAction.Properties.signal, allInstancesOf.indexOf(referenceValueForSignal));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
