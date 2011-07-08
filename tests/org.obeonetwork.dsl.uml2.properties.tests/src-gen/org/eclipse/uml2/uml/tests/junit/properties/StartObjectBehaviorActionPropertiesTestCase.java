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

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.StartObjectBehaviorAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for StartObjectBehaviorAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class StartObjectBehaviorActionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass startObjectBehaviorActionMetaClass = UMLPackage.eINSTANCE.getStartObjectBehaviorAction();

	/**
	 * The type to edit
	 */
	private EObject startObjectBehaviorAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class onPort
	 */
	private Object referenceValueForOnPort;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

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
	protected void initializeExpectedModelForStartObjectBehaviorActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditStartObjectBehaviorActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStartObjectBehaviorActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the StartObjectBehaviorAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStartObjectBehaviorActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditStartObjectBehaviorActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((StartObjectBehaviorAction)startObjectBehaviorAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForStartObjectBehaviorActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the StartObjectBehaviorAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStartObjectBehaviorActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((StartObjectBehaviorAction)startObjectBehaviorAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditStartObjectBehaviorActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStartObjectBehaviorActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the StartObjectBehaviorAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStartObjectBehaviorActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)startObjectBehaviorAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveStartObjectBehaviorActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStartObjectBehaviorActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the StartObjectBehaviorAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStartObjectBehaviorActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditStartObjectBehaviorActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStartObjectBehaviorActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the StartObjectBehaviorAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStartObjectBehaviorActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((StartObjectBehaviorAction)startObjectBehaviorAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditStartObjectBehaviorActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStartObjectBehaviorActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the StartObjectBehaviorAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStartObjectBehaviorActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveStartObjectBehaviorActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStartObjectBehaviorActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the StartObjectBehaviorAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForStartObjectBehaviorActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((StartObjectBehaviorAction)startObjectBehaviorAction).getActivity());
		cc.append(SetCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditStartObjectBehaviorActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStartObjectBehaviorActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the StartObjectBehaviorAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStartObjectBehaviorActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveStartObjectBehaviorActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStartObjectBehaviorActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the StartObjectBehaviorAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForStartObjectBehaviorActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((StartObjectBehaviorAction)startObjectBehaviorAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditStartObjectBehaviorActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStartObjectBehaviorActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the StartObjectBehaviorAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStartObjectBehaviorActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)startObjectBehaviorAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveStartObjectBehaviorActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStartObjectBehaviorActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the StartObjectBehaviorAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStartObjectBehaviorActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((StartObjectBehaviorAction)startObjectBehaviorAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditStartObjectBehaviorActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStartObjectBehaviorActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the StartObjectBehaviorAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStartObjectBehaviorActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)startObjectBehaviorAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveStartObjectBehaviorActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStartObjectBehaviorActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the StartObjectBehaviorAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStartObjectBehaviorActionOnPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, portMetaClass);
		referenceValueForOnPort = bot.changeReferenceValue(allInstancesOf, ((StartObjectBehaviorAction)startObjectBehaviorAction).getOnPort());
		cc.append(SetCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getInvocationAction_OnPort(), referenceValueForOnPort));
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
	public void testEditStartObjectBehaviorActionOnPort() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStartObjectBehaviorActionOnPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the onPort feature of the StartObjectBehaviorAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.onPort, allInstancesOf.indexOf(referenceValueForOnPort)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStartObjectBehaviorActionOnPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, portMetaClass);
		cc.append(SetCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getInvocationAction_OnPort(), null));
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
	public void testRemoveStartObjectBehaviorActionOnPort() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStartObjectBehaviorActionOnPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the onPort feature of the StartObjectBehaviorAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.onPort, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForStartObjectBehaviorActionIsSynchronous() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, startObjectBehaviorAction, UMLPackage.eINSTANCE.getCallAction_IsSynchronous(), UPDATED_VALUE));
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
	public void testEditStartObjectBehaviorActionIsSynchronous() throws Exception {

		// Import the input model
		initializeInputModel();

		startObjectBehaviorAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (startObjectBehaviorAction == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStartObjectBehaviorActionIsSynchronous();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StartObjectBehaviorAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), startObjectBehaviorActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(startObjectBehaviorActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isSynchronous feature of the StartObjectBehaviorAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.StartObjectBehaviorAction.Properties.isSynchronous, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
