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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.DataStoreNode;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for DataStoreNode
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class DataStoreNodeTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass dataStoreNodeMetaClass = UMLPackage.eINSTANCE.getDataStoreNode();

	/**
	 * The type to edit
	 */
	private EObject dataStoreNode;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class ordering
	 */
	private Object enumValueForOrdering;
	/**
	 * The reference value for the reference class selection
	 */
	private Object referenceValueForSelection;

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
	 * The reference value for the reference class inState
	 */
	private Object referenceValueForInState;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;
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
	protected void initializeExpectedModelForDataStoreNodeName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditDataStoreNodeName() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataStoreNodeName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the DataStoreNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDataStoreNodeVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditDataStoreNodeVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((DataStoreNode)dataStoreNode).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForDataStoreNodeVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the DataStoreNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDataStoreNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((DataStoreNode)dataStoreNode).getClientDependency());
		cc.append(AddCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditDataStoreNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataStoreNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DataStoreNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForDataStoreNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)dataStoreNode.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveDataStoreNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataStoreNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DataStoreNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDataStoreNodeIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditDataStoreNodeIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataStoreNodeIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the DataStoreNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDataStoreNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((DataStoreNode)dataStoreNode).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditDataStoreNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataStoreNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the DataStoreNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForDataStoreNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveDataStoreNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataStoreNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the DataStoreNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForDataStoreNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((DataStoreNode)dataStoreNode).getActivity());
		cc.append(SetCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditDataStoreNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataStoreNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the DataStoreNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForDataStoreNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveDataStoreNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataStoreNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the DataStoreNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.activity);
		

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
	protected void initializeExpectedModelForDataStoreNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((DataStoreNode)dataStoreNode).getInPartition());
		cc.append(AddCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditDataStoreNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataStoreNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the DataStoreNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForDataStoreNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)dataStoreNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveDataStoreNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataStoreNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the DataStoreNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDataStoreNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((DataStoreNode)dataStoreNode).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditDataStoreNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataStoreNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the DataStoreNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForDataStoreNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)dataStoreNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveDataStoreNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataStoreNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the DataStoreNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDataStoreNodeOrdering() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getObjectNode_Ordering(), UPDATED_VALUE));
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
	public void testEditDataStoreNodeOrdering() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		enumValueForOrdering = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getObjectNodeOrderingKind(), ((DataStoreNode)dataStoreNode).getOrdering().getLiteral());
		// Create the expected model
		initializeExpectedModelForDataStoreNodeOrdering();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the ordering feature of the DataStoreNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.ordering, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDataStoreNodeIsControlType() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getObjectNode_IsControlType(), UPDATED_VALUE));
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
	public void testEditDataStoreNodeIsControlType() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataStoreNodeIsControlType();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the isControlType feature of the DataStoreNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.isControlType, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDataStoreNodeInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForInState = bot.changeReferenceValue(allInstancesOf, ((DataStoreNode)dataStoreNode).getInState());
		cc.append(AddCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getObjectNode_InState(), referenceValueForInState));
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
	public void testEditDataStoreNodeInState() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataStoreNodeInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the DataStoreNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.inState, referenceValueForInState);

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
	protected void initializeRemoveExpectedModelForDataStoreNodeInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)dataStoreNode.eGet(UMLPackage.eINSTANCE.getObjectNode_InState());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, dataStoreNode, UMLPackage.eINSTANCE.getObjectNode_InState(), allReferencedInstances.get(0)));
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
	public void testRemoveDataStoreNodeInState() throws Exception {

		// Import the input model
		initializeInputModel();

		dataStoreNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (dataStoreNode == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataStoreNodeInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataStoreNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataStoreNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataStoreNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataStoreNodeMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the DataStoreNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataStoreNode.Properties.inState, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
























}
