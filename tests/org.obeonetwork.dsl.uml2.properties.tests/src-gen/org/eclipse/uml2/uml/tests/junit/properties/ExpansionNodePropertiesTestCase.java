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
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ExpansionNode
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ExpansionNodePropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass expansionNodeMetaClass = UMLPackage.eINSTANCE.getExpansionNode();

	/**
	 * The type to edit
	 */
	private EObject expansionNode;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class ordering
	 */
	private Object enumValueForOrdering;
	/**
	 * The reference value for the reference class regionAsOutput
	 */
	private Object referenceValueForRegionAsOutput;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class regionAsInput
	 */
	private Object referenceValueForRegionAsInput;

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
	 * The reference value for the reference class inState
	 */
	private Object referenceValueForInState;

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
	 * The reference value for the reference class selection
	 */
	private Object referenceValueForSelection;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass typeMetaClass = UMLPackage.eINSTANCE.getType();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityEdgeMetaClass = UMLPackage.eINSTANCE.getActivityEdge();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass expansionRegionMetaClass = UMLPackage.eINSTANCE.getExpansionRegion();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass dependencyMetaClass = UMLPackage.eINSTANCE.getDependency();

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
	private EClass activityPartitionMetaClass = UMLPackage.eINSTANCE.getActivityPartition();
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
	protected void initializeExpectedModelForExpansionNodeName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditExpansionNodeName() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionNodeName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the ExpansionNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ExpansionNode.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExpansionNodeVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditExpansionNodeVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ExpansionNode)expansionNode).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForExpansionNodeVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the ExpansionNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ExpansionNode.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExpansionNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ExpansionNode)expansionNode).getClientDependency());
		cc.append(AddCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditExpansionNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExpansionNode element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExpansionNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)expansionNode.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveExpansionNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExpansionNode element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExpansionNodeIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditExpansionNodeIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionNodeIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ExpansionNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ExpansionNode.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExpansionNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ExpansionNode)expansionNode).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditExpansionNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ExpansionNode element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExpansionNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveExpansionNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ExpansionNode element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForExpansionNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ExpansionNode)expansionNode).getActivity());
		cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditExpansionNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ExpansionNode element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExpansionNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveExpansionNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ExpansionNode element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForExpansionNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ExpansionNode)expansionNode).getInPartition());
		cc.append(AddCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditExpansionNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ExpansionNode element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExpansionNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)expansionNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveExpansionNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ExpansionNode element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExpansionNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ExpansionNode)expansionNode).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditExpansionNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ExpansionNode element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExpansionNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)expansionNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveExpansionNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ExpansionNode element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExpansionNodeOrdering() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getObjectNode_Ordering(), UPDATED_VALUE));
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
	public void testEditExpansionNodeOrdering() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		enumValueForOrdering = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getObjectNodeOrderingKind(), ((ExpansionNode)expansionNode).getOrdering().getLiteral());
		// Create the expected model
		initializeExpectedModelForExpansionNodeOrdering();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the ordering feature of the ExpansionNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ExpansionNode.Properties.ordering, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExpansionNodeIsControlType() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getObjectNode_IsControlType(), UPDATED_VALUE));
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
	public void testEditExpansionNodeIsControlType() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionNodeIsControlType();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isControlType feature of the ExpansionNode element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ExpansionNode.Properties.isControlType, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExpansionNodeInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForInState = bot.changeReferenceValue(allInstancesOf, ((ExpansionNode)expansionNode).getInState());
		cc.append(AddCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getObjectNode_InState(), referenceValueForInState));
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
	public void testEditExpansionNodeInState() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionNodeInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inState feature of the ExpansionNode element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.inState, referenceValueForInState, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExpansionNodeInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)expansionNode.eGet(UMLPackage.eINSTANCE.getObjectNode_InState());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getObjectNode_InState(), allReferencedInstances.get(0)));
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
	public void testRemoveExpansionNodeInState() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionNodeInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inState feature of the ExpansionNode element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.inState, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExpansionNodeRegionAsOutput() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, expansionRegionMetaClass);
		referenceValueForRegionAsOutput = bot.changeReferenceValue(allInstancesOf, ((ExpansionNode)expansionNode).getRegionAsOutput());
		cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getExpansionNode_RegionAsOutput(), referenceValueForRegionAsOutput));
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
	public void testEditExpansionNodeRegionAsOutput() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionNodeRegionAsOutput();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the regionAsOutput feature of the ExpansionNode element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.regionAsOutput, allInstancesOf.indexOf(referenceValueForRegionAsOutput)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExpansionNodeRegionAsOutput() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, expansionRegionMetaClass);
		cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getExpansionNode_RegionAsOutput(), null));
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
	public void testRemoveExpansionNodeRegionAsOutput() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionNodeRegionAsOutput();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the regionAsOutput feature of the ExpansionNode element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.regionAsOutput, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForExpansionNodeRegionAsInput() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, expansionRegionMetaClass);
		referenceValueForRegionAsInput = bot.changeReferenceValue(allInstancesOf, ((ExpansionNode)expansionNode).getRegionAsInput());
		cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getExpansionNode_RegionAsInput(), referenceValueForRegionAsInput));
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
	public void testEditExpansionNodeRegionAsInput() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionNodeRegionAsInput();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the regionAsInput feature of the ExpansionNode element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.regionAsInput, allInstancesOf.indexOf(referenceValueForRegionAsInput)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExpansionNodeRegionAsInput() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, expansionRegionMetaClass);
		cc.append(SetCommand.create(editingDomain, expansionNode, UMLPackage.eINSTANCE.getExpansionNode_RegionAsInput(), null));
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
	public void testRemoveExpansionNodeRegionAsInput() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (expansionNode == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionNodeRegionAsInput();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExpansionNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionNodeMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the regionAsInput feature of the ExpansionNode element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExpansionNode.Properties.regionAsInput, bot.selectNode(modelEditor, firstInstanceOf));
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




























}
