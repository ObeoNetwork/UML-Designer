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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ConditionalNode;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ConditionalNode
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ConditionalNodePropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass conditionalNodeMetaClass = UMLPackage.eINSTANCE.getConditionalNode();

	/**
	 * The type to edit
	 */
	private EObject conditionalNode;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class inActivity
	 */
	private Object referenceValueForInActivity;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;
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
	protected void initializeExpectedModelForConditionalNodeName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditConditionalNodeName() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConditionalNodeName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the ConditionalNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ConditionalNode.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConditionalNodeVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditConditionalNodeVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ConditionalNode)conditionalNode).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForConditionalNodeVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the ConditionalNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ConditionalNode.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConditionalNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ConditionalNode)conditionalNode).getClientDependency());
		cc.append(AddCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditConditionalNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConditionalNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ConditionalNode element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForConditionalNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)conditionalNode.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveConditionalNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConditionalNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ConditionalNode element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConditionalNodeIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditConditionalNodeIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConditionalNodeIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ConditionalNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ConditionalNode.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConditionalNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ConditionalNode)conditionalNode).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditConditionalNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConditionalNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ConditionalNode element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForConditionalNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveConditionalNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConditionalNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ConditionalNode element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForConditionalNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ConditionalNode)conditionalNode).getActivity());
		cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditConditionalNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConditionalNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ConditionalNode element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForConditionalNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveConditionalNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConditionalNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ConditionalNode element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForConditionalNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ConditionalNode)conditionalNode).getInPartition());
		cc.append(AddCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditConditionalNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConditionalNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ConditionalNode element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForConditionalNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)conditionalNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveConditionalNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConditionalNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ConditionalNode element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConditionalNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ConditionalNode)conditionalNode).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditConditionalNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConditionalNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ConditionalNode element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForConditionalNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)conditionalNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveConditionalNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConditionalNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ConditionalNode element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConditionalNodeInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForInActivity = bot.changeReferenceValue(allInstancesOf, ((ConditionalNode)conditionalNode).getInActivity());
		cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), referenceValueForInActivity));
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
	public void testEditConditionalNodeInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConditionalNodeInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inActivity feature of the ConditionalNode element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.inActivity, allInstancesOf.indexOf(referenceValueForInActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForConditionalNodeInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), null));
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
	public void testRemoveConditionalNodeInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConditionalNodeInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inActivity feature of the ConditionalNode element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ConditionalNode.Properties.inActivity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForConditionalNodeMustIsolate() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate(), UPDATED_VALUE));
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
	public void testEditConditionalNodeMustIsolate() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConditionalNodeMustIsolate();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the mustIsolate feature of the ConditionalNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ConditionalNode.Properties.mustIsolate, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConditionalNodeIsDeterminate() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getConditionalNode_IsDeterminate(), UPDATED_VALUE));
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
	public void testEditConditionalNodeIsDeterminate() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConditionalNodeIsDeterminate();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isDeterminate feature of the ConditionalNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ConditionalNode.Properties.isDeterminate, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConditionalNodeIsAssured() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, conditionalNode, UMLPackage.eINSTANCE.getConditionalNode_IsAssured(), UPDATED_VALUE));
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
	public void testEditConditionalNodeIsAssured() throws Exception {

		// Import the input model
		initializeInputModel();

		conditionalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (conditionalNode == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConditionalNodeIsAssured();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ConditionalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), conditionalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(conditionalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isAssured feature of the ConditionalNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ConditionalNode.Properties.isAssured, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


























}
