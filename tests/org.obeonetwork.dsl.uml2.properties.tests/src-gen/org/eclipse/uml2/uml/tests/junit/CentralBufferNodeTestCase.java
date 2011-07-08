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
import org.eclipse.uml2.uml.CentralBufferNode;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for CentralBufferNode
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class CentralBufferNodeTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass centralBufferNodeMetaClass = UMLPackage.eINSTANCE.getCentralBufferNode();

	/**
	 * The type to edit
	 */
	private EObject centralBufferNode;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class ordering
	 */
	private Object enumValueForOrdering;
	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class inState
	 */
	private Object referenceValueForInState;

	/**
	 * The reference value for the reference class selection
	 */
	private Object referenceValueForSelection;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

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
	protected void initializeExpectedModelForCentralBufferNodeName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditCentralBufferNodeName() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCentralBufferNodeName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the CentralBufferNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCentralBufferNodeVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditCentralBufferNodeVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((CentralBufferNode)centralBufferNode).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForCentralBufferNodeVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the CentralBufferNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCentralBufferNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((CentralBufferNode)centralBufferNode).getClientDependency());
		cc.append(AddCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditCentralBufferNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCentralBufferNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CentralBufferNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForCentralBufferNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)centralBufferNode.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveCentralBufferNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCentralBufferNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CentralBufferNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCentralBufferNodeIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditCentralBufferNodeIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCentralBufferNodeIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the CentralBufferNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCentralBufferNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((CentralBufferNode)centralBufferNode).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditCentralBufferNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCentralBufferNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the CentralBufferNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForCentralBufferNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveCentralBufferNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCentralBufferNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the CentralBufferNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForCentralBufferNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((CentralBufferNode)centralBufferNode).getActivity());
		cc.append(SetCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditCentralBufferNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCentralBufferNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the CentralBufferNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForCentralBufferNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveCentralBufferNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCentralBufferNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the CentralBufferNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.activity);
		

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
	protected void initializeExpectedModelForCentralBufferNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((CentralBufferNode)centralBufferNode).getInPartition());
		cc.append(AddCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditCentralBufferNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCentralBufferNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the CentralBufferNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForCentralBufferNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)centralBufferNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveCentralBufferNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCentralBufferNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the CentralBufferNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCentralBufferNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((CentralBufferNode)centralBufferNode).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditCentralBufferNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCentralBufferNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the CentralBufferNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForCentralBufferNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)centralBufferNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveCentralBufferNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCentralBufferNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the CentralBufferNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCentralBufferNodeOrdering() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getObjectNode_Ordering(), UPDATED_VALUE));
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
	public void testEditCentralBufferNodeOrdering() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		enumValueForOrdering = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getObjectNodeOrderingKind(), ((CentralBufferNode)centralBufferNode).getOrdering().getLiteral());
		// Create the expected model
		initializeExpectedModelForCentralBufferNodeOrdering();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the ordering feature of the CentralBufferNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.ordering, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCentralBufferNodeIsControlType() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getObjectNode_IsControlType(), UPDATED_VALUE));
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
	public void testEditCentralBufferNodeIsControlType() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCentralBufferNodeIsControlType();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the isControlType feature of the CentralBufferNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.isControlType, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCentralBufferNodeInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForInState = bot.changeReferenceValue(allInstancesOf, ((CentralBufferNode)centralBufferNode).getInState());
		cc.append(AddCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getObjectNode_InState(), referenceValueForInState));
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
	public void testEditCentralBufferNodeInState() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCentralBufferNodeInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the CentralBufferNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.inState, referenceValueForInState);

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
	protected void initializeRemoveExpectedModelForCentralBufferNodeInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)centralBufferNode.eGet(UMLPackage.eINSTANCE.getObjectNode_InState());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, centralBufferNode, UMLPackage.eINSTANCE.getObjectNode_InState(), allReferencedInstances.get(0)));
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
	public void testRemoveCentralBufferNodeInState() throws Exception {

		// Import the input model
		initializeInputModel();

		centralBufferNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (centralBufferNode == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCentralBufferNodeInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CentralBufferNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), centralBufferNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(centralBufferNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, centralBufferNodeMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the CentralBufferNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CentralBufferNode.Properties.inState, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
























}
