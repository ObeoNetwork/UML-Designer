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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ReplyAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ReplyAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ReplyActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass replyActionMetaClass = UMLPackage.eINSTANCE.getReplyAction();

	/**
	 * The type to edit
	 */
	private EObject replyAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class replyToCall
	 */
	private Object referenceValueForReplyToCall;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

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
	private EClass triggerMetaClass = UMLPackage.eINSTANCE.getTrigger();

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
	protected void initializeExpectedModelForReplyActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditReplyActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReplyActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ReplyAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReplyActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditReplyActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ReplyAction)replyAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForReplyActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ReplyAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReplyActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ReplyAction)replyAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditReplyActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReplyActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReplyAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForReplyActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)replyAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveReplyActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReplyActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReplyAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReplyActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditReplyActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReplyActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ReplyAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReplyActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ReplyAction)replyAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditReplyActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReplyActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReplyAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForReplyActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveReplyActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReplyActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReplyAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForReplyActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ReplyAction)replyAction).getActivity());
		cc.append(SetCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditReplyActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReplyActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReplyAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForReplyActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveReplyActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReplyActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReplyAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.activity);
		

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
	protected void initializeExpectedModelForReplyActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ReplyAction)replyAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditReplyActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReplyActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReplyAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForReplyActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)replyAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveReplyActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReplyActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReplyAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReplyActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ReplyAction)replyAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditReplyActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReplyActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReplyAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForReplyActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)replyAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveReplyActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReplyActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReplyAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReplyActionReplyToCall() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject replyAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, triggerMetaClass);
		referenceValueForReplyToCall = bot.changeReferenceValue(allInstancesOf, ((ReplyAction)replyAction).getReplyToCall());
		cc.append(SetCommand.create(editingDomain, replyAction, UMLPackage.eINSTANCE.getReplyAction_ReplyToCall(), referenceValueForReplyToCall));
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
	public void testEditReplyActionReplyToCall() throws Exception {

		// Import the input model
		initializeInputModel();

		replyAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (replyAction == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReplyActionReplyToCall();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReplyAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), replyActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(replyActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, replyActionMetaClass, firstInstanceOf, null);

		// Change value of the replyToCall feature of the ReplyAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReplyAction.Properties.replyToCall, allInstancesOf.indexOf(referenceValueForReplyToCall));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
