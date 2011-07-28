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
import org.eclipse.uml2.uml.BroadcastSignalAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for BroadcastSignalAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class BroadcastSignalActionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass broadcastSignalActionMetaClass = UMLPackage.eINSTANCE.getBroadcastSignalAction();

	/**
	 * The type to edit
	 */
	private EObject broadcastSignalAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class signal
	 */
	private Object referenceValueForSignal;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

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
	protected void initializeExpectedModelForBroadcastSignalActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditBroadcastSignalActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBroadcastSignalActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the BroadcastSignalAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForBroadcastSignalActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditBroadcastSignalActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((BroadcastSignalAction)broadcastSignalAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForBroadcastSignalActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the BroadcastSignalAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForBroadcastSignalActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((BroadcastSignalAction)broadcastSignalAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditBroadcastSignalActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBroadcastSignalActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the BroadcastSignalAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForBroadcastSignalActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)broadcastSignalAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveBroadcastSignalActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForBroadcastSignalActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the BroadcastSignalAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForBroadcastSignalActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditBroadcastSignalActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBroadcastSignalActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the BroadcastSignalAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForBroadcastSignalActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((BroadcastSignalAction)broadcastSignalAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditBroadcastSignalActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBroadcastSignalActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the BroadcastSignalAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForBroadcastSignalActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveBroadcastSignalActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForBroadcastSignalActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the BroadcastSignalAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForBroadcastSignalActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((BroadcastSignalAction)broadcastSignalAction).getActivity());
		cc.append(SetCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditBroadcastSignalActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBroadcastSignalActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the BroadcastSignalAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForBroadcastSignalActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveBroadcastSignalActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForBroadcastSignalActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the BroadcastSignalAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForBroadcastSignalActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((BroadcastSignalAction)broadcastSignalAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditBroadcastSignalActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBroadcastSignalActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the BroadcastSignalAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForBroadcastSignalActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)broadcastSignalAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveBroadcastSignalActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForBroadcastSignalActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the BroadcastSignalAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForBroadcastSignalActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((BroadcastSignalAction)broadcastSignalAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditBroadcastSignalActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBroadcastSignalActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the BroadcastSignalAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForBroadcastSignalActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)broadcastSignalAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveBroadcastSignalActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForBroadcastSignalActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the BroadcastSignalAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForBroadcastSignalActionOnPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, portMetaClass);
		referenceValueForOnPort = bot.changeReferenceValue(allInstancesOf, ((BroadcastSignalAction)broadcastSignalAction).getOnPort());
		cc.append(SetCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getInvocationAction_OnPort(), referenceValueForOnPort));
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
	public void testEditBroadcastSignalActionOnPort() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBroadcastSignalActionOnPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the onPort feature of the BroadcastSignalAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.onPort, allInstancesOf.indexOf(referenceValueForOnPort)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForBroadcastSignalActionOnPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, portMetaClass);
		cc.append(SetCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getInvocationAction_OnPort(), null));
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
	public void testRemoveBroadcastSignalActionOnPort() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForBroadcastSignalActionOnPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the onPort feature of the BroadcastSignalAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.onPort, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForBroadcastSignalActionSignal() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, signalMetaClass);
		referenceValueForSignal = bot.changeReferenceValue(allInstancesOf, ((BroadcastSignalAction)broadcastSignalAction).getSignal());
		cc.append(SetCommand.create(editingDomain, broadcastSignalAction, UMLPackage.eINSTANCE.getBroadcastSignalAction_Signal(), referenceValueForSignal));
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
	public void testEditBroadcastSignalActionSignal() throws Exception {

		// Import the input model
		initializeInputModel();

		broadcastSignalAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (broadcastSignalAction == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBroadcastSignalActionSignal();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the BroadcastSignalAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), broadcastSignalActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(broadcastSignalActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the signal feature of the BroadcastSignalAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.BroadcastSignalAction.Properties.signal, allInstancesOf.indexOf(referenceValueForSignal), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
