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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.FlowFinalNode;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for FlowFinalNode
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class FlowFinalNodePropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass flowFinalNodeMetaClass = UMLPackage.eINSTANCE.getFlowFinalNode();

	/**
	 * The type to edit
	 */
	private EObject flowFinalNode;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

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
	protected void initializeExpectedModelForFlowFinalNodeName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditFlowFinalNodeName() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFlowFinalNodeName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the FlowFinalNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFlowFinalNodeVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditFlowFinalNodeVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((FlowFinalNode)flowFinalNode).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForFlowFinalNodeVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the FlowFinalNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFlowFinalNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((FlowFinalNode)flowFinalNode).getClientDependency());
		cc.append(AddCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditFlowFinalNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFlowFinalNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the FlowFinalNode element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFlowFinalNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)flowFinalNode.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveFlowFinalNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFlowFinalNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the FlowFinalNode element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFlowFinalNodeIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditFlowFinalNodeIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFlowFinalNodeIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the FlowFinalNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFlowFinalNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((FlowFinalNode)flowFinalNode).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditFlowFinalNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFlowFinalNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the FlowFinalNode element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFlowFinalNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveFlowFinalNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFlowFinalNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the FlowFinalNode element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForFlowFinalNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((FlowFinalNode)flowFinalNode).getActivity());
		cc.append(SetCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditFlowFinalNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFlowFinalNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the FlowFinalNode element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFlowFinalNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveFlowFinalNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFlowFinalNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the FlowFinalNode element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForFlowFinalNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((FlowFinalNode)flowFinalNode).getInPartition());
		cc.append(AddCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditFlowFinalNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFlowFinalNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the FlowFinalNode element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFlowFinalNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)flowFinalNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveFlowFinalNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFlowFinalNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the FlowFinalNode element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFlowFinalNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((FlowFinalNode)flowFinalNode).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditFlowFinalNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFlowFinalNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the FlowFinalNode element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFlowFinalNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)flowFinalNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, flowFinalNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveFlowFinalNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		flowFinalNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (flowFinalNode == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFlowFinalNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FlowFinalNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), flowFinalNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(flowFinalNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the FlowFinalNode element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FlowFinalNode.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
