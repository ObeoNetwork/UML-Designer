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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ActivityParameterNode;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ActivityParameterNode
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ActivityParameterNodeTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass activityParameterNodeMetaClass = UMLPackage.eINSTANCE.getActivityParameterNode();

	/**
	 * The type to edit
	 */
	private EObject activityParameterNode;

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
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class parameter
	 */
	private Object referenceValueForParameter;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inState
	 */
	private Object referenceValueForInState;

	/**
	 * The reference value for the reference class selection
	 */
	private Object referenceValueForSelection;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;
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
	 * The EClass of the reference to edit
	 */
	private EClass parameterMetaClass = UMLPackage.eINSTANCE.getParameter();
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
	protected void initializeExpectedModelForActivityParameterNodeName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditActivityParameterNodeName() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityParameterNodeName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ActivityParameterNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActivityParameterNodeVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditActivityParameterNodeVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ActivityParameterNode)activityParameterNode).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForActivityParameterNodeVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ActivityParameterNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActivityParameterNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ActivityParameterNode)activityParameterNode).getClientDependency());
		cc.append(AddCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditActivityParameterNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityParameterNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ActivityParameterNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForActivityParameterNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)activityParameterNode.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveActivityParameterNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActivityParameterNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ActivityParameterNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForActivityParameterNodeIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditActivityParameterNodeIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityParameterNodeIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ActivityParameterNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActivityParameterNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ActivityParameterNode)activityParameterNode).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditActivityParameterNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityParameterNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ActivityParameterNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForActivityParameterNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveActivityParameterNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActivityParameterNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ActivityParameterNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForActivityParameterNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ActivityParameterNode)activityParameterNode).getActivity());
		cc.append(SetCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditActivityParameterNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityParameterNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ActivityParameterNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForActivityParameterNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveActivityParameterNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActivityParameterNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ActivityParameterNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.activity);
		

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
	protected void initializeExpectedModelForActivityParameterNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ActivityParameterNode)activityParameterNode).getInPartition());
		cc.append(AddCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditActivityParameterNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityParameterNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ActivityParameterNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForActivityParameterNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)activityParameterNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveActivityParameterNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActivityParameterNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ActivityParameterNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForActivityParameterNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ActivityParameterNode)activityParameterNode).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditActivityParameterNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityParameterNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ActivityParameterNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForActivityParameterNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)activityParameterNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveActivityParameterNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActivityParameterNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ActivityParameterNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForActivityParameterNodeOrdering() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getObjectNode_Ordering(), UPDATED_VALUE));
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
	public void testEditActivityParameterNodeOrdering() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		enumValueForOrdering = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getObjectNodeOrderingKind(), ((ActivityParameterNode)activityParameterNode).getOrdering().getLiteral());
		// Create the expected model
		initializeExpectedModelForActivityParameterNodeOrdering();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the ordering feature of the ActivityParameterNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.ordering, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActivityParameterNodeIsControlType() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getObjectNode_IsControlType(), UPDATED_VALUE));
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
	public void testEditActivityParameterNodeIsControlType() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityParameterNodeIsControlType();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the isControlType feature of the ActivityParameterNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.isControlType, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActivityParameterNodeInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForInState = bot.changeReferenceValue(allInstancesOf, ((ActivityParameterNode)activityParameterNode).getInState());
		cc.append(AddCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getObjectNode_InState(), referenceValueForInState));
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
	public void testEditActivityParameterNodeInState() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityParameterNodeInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the ActivityParameterNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.inState, referenceValueForInState);

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
	protected void initializeRemoveExpectedModelForActivityParameterNodeInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)activityParameterNode.eGet(UMLPackage.eINSTANCE.getObjectNode_InState());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getObjectNode_InState(), allReferencedInstances.get(0)));
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
	public void testRemoveActivityParameterNodeInState() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActivityParameterNodeInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the ActivityParameterNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.inState, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForActivityParameterNodeParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, parameterMetaClass);
		referenceValueForParameter = bot.changeReferenceValue(allInstancesOf, ((ActivityParameterNode)activityParameterNode).getParameter());
		cc.append(SetCommand.create(editingDomain, activityParameterNode, UMLPackage.eINSTANCE.getActivityParameterNode_Parameter(), referenceValueForParameter));
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
	public void testEditActivityParameterNodeParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		activityParameterNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (activityParameterNode == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityParameterNodeParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityParameterNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityParameterNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityParameterNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityParameterNodeMetaClass, firstInstanceOf, null);

		// Change value of the parameter feature of the ActivityParameterNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActivityParameterNode.Properties.parameter, allInstancesOf.indexOf(referenceValueForParameter));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


























}
