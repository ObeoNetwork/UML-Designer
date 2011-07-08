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
import org.eclipse.uml2.uml.SendObjectAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for SendObjectAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class SendObjectActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass sendObjectActionMetaClass = UMLPackage.eINSTANCE.getSendObjectAction();

	/**
	 * The type to edit
	 */
	private EObject sendObjectAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class onPort
	 */
	private Object referenceValueForOnPort;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

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
	protected void initializeExpectedModelForSendObjectActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditSendObjectActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendObjectActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the SendObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSendObjectActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditSendObjectActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((SendObjectAction)sendObjectAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForSendObjectActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the SendObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSendObjectActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((SendObjectAction)sendObjectAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditSendObjectActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendObjectActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SendObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForSendObjectActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)sendObjectAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveSendObjectActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendObjectActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SendObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSendObjectActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditSendObjectActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendObjectActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the SendObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSendObjectActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((SendObjectAction)sendObjectAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditSendObjectActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendObjectActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the SendObjectAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForSendObjectActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveSendObjectActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendObjectActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the SendObjectAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForSendObjectActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((SendObjectAction)sendObjectAction).getActivity());
		cc.append(SetCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditSendObjectActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendObjectActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the SendObjectAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForSendObjectActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveSendObjectActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendObjectActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the SendObjectAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.activity);
		

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
	protected void initializeExpectedModelForSendObjectActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((SendObjectAction)sendObjectAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditSendObjectActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendObjectActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the SendObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForSendObjectActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)sendObjectAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveSendObjectActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendObjectActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the SendObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSendObjectActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((SendObjectAction)sendObjectAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditSendObjectActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendObjectActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the SendObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForSendObjectActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)sendObjectAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveSendObjectActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendObjectActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the SendObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSendObjectActionOnPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, portMetaClass);
		referenceValueForOnPort = bot.changeReferenceValue(allInstancesOf, ((SendObjectAction)sendObjectAction).getOnPort());
		cc.append(SetCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getInvocationAction_OnPort(), referenceValueForOnPort));
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
	public void testEditSendObjectActionOnPort() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendObjectActionOnPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the onPort feature of the SendObjectAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.onPort, allInstancesOf.indexOf(referenceValueForOnPort)+1);

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
	protected void initializeRemoveExpectedModelForSendObjectActionOnPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, portMetaClass);
		cc.append(SetCommand.create(editingDomain, sendObjectAction, UMLPackage.eINSTANCE.getInvocationAction_OnPort(), null));
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
	public void testRemoveSendObjectActionOnPort() throws Exception {

		// Import the input model
		initializeInputModel();

		sendObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (sendObjectAction == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendObjectActionOnPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the onPort feature of the SendObjectAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendObjectAction.Properties.onPort);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
