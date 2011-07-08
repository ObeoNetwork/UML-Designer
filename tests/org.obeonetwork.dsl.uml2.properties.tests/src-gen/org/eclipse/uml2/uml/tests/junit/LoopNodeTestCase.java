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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.LoopNode;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for LoopNode
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class LoopNodeTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass loopNodeMetaClass = UMLPackage.eINSTANCE.getLoopNode();

	/**
	 * The type to edit
	 */
	private EObject loopNode;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class loopVariable
	 */
	private Object referenceValueForLoopVariable;

	/**
	 * The reference value for the reference class bodyPart
	 */
	private Object referenceValueForBodyPart;

	/**
	 * The reference value for the reference class bodyOutput
	 */
	private Object referenceValueForBodyOutput;

	/**
	 * The reference value for the reference class inActivity
	 */
	private Object referenceValueForInActivity;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class setupPart
	 */
	private Object referenceValueForSetupPart;

	/**
	 * The reference value for the reference class test
	 */
	private Object referenceValueForTest;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class decider
	 */
	private Object referenceValueForDecider;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass outputPinMetaClass = UMLPackage.eINSTANCE.getOutputPin();

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
	private EClass activityNodeMetaClass = UMLPackage.eINSTANCE.getActivityNode();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityPartitionMetaClass = UMLPackage.eINSTANCE.getActivityPartition();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass executableNodeMetaClass = UMLPackage.eINSTANCE.getExecutableNode();
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
	protected void initializeExpectedModelForLoopNodeName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditLoopNodeName() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the LoopNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLoopNodeVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditLoopNodeVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((LoopNode)loopNode).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForLoopNodeVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the LoopNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLoopNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((LoopNode)loopNode).getClientDependency());
		cc.append(AddCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditLoopNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the LoopNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForLoopNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)loopNode.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveLoopNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLoopNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the LoopNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForLoopNodeIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditLoopNodeIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the LoopNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLoopNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((LoopNode)loopNode).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditLoopNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the LoopNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForLoopNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveLoopNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLoopNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the LoopNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForLoopNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((LoopNode)loopNode).getActivity());
		cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditLoopNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the LoopNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForLoopNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveLoopNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLoopNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the LoopNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.activity);
		

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
	protected void initializeExpectedModelForLoopNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((LoopNode)loopNode).getInPartition());
		cc.append(AddCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditLoopNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the LoopNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForLoopNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)loopNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveLoopNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLoopNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the LoopNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForLoopNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((LoopNode)loopNode).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditLoopNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the LoopNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForLoopNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)loopNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveLoopNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLoopNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the LoopNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForLoopNodeInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForInActivity = bot.changeReferenceValue(allInstancesOf, ((LoopNode)loopNode).getInActivity());
		cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), referenceValueForInActivity));
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
	public void testEditLoopNodeInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the inActivity feature of the LoopNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.inActivity, allInstancesOf.indexOf(referenceValueForInActivity)+1);

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
	protected void initializeRemoveExpectedModelForLoopNodeInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), null));
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
	public void testRemoveLoopNodeInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLoopNodeInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the inActivity feature of the LoopNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.inActivity);
		

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
	protected void initializeExpectedModelForLoopNodeMustIsolate() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate(), UPDATED_VALUE));
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
	public void testEditLoopNodeMustIsolate() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeMustIsolate();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the mustIsolate feature of the LoopNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.mustIsolate, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLoopNodeIsTestedFirst() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getLoopNode_IsTestedFirst(), UPDATED_VALUE));
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
	public void testEditLoopNodeIsTestedFirst() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeIsTestedFirst();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the isTestedFirst feature of the LoopNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.isTestedFirst, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLoopNodeDecider() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, outputPinMetaClass);
		referenceValueForDecider = bot.changeReferenceValue(allInstancesOf, ((LoopNode)loopNode).getDecider());
		cc.append(SetCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getLoopNode_Decider(), referenceValueForDecider));
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
	public void testEditLoopNodeDecider() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeDecider();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the decider feature of the LoopNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.decider, allInstancesOf.indexOf(referenceValueForDecider));

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
	protected void initializeExpectedModelForLoopNodeLoopVariable() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, outputPinMetaClass);
		referenceValueForLoopVariable = bot.changeReferenceValue(allInstancesOf, ((LoopNode)loopNode).getLoopVariable());
		cc.append(AddCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getLoopNode_LoopVariable(), referenceValueForLoopVariable));
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
	public void testEditLoopNodeLoopVariable() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeLoopVariable();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the loopVariable feature of the LoopNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.loopVariable, referenceValueForLoopVariable);

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
	protected void initializeRemoveExpectedModelForLoopNodeLoopVariable() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)loopNode.eGet(UMLPackage.eINSTANCE.getLoopNode_LoopVariable());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getLoopNode_LoopVariable(), allReferencedInstances.get(0)));
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
	public void testRemoveLoopNodeLoopVariable() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLoopNodeLoopVariable();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the loopVariable feature of the LoopNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.loopVariable, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForLoopNodeBodyOutput() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, outputPinMetaClass);
		referenceValueForBodyOutput = bot.changeReferenceValue(allInstancesOf, ((LoopNode)loopNode).getBodyOutput());
		cc.append(AddCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getLoopNode_BodyOutput(), referenceValueForBodyOutput));
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
	public void testEditLoopNodeBodyOutput() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLoopNodeBodyOutput();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the bodyOutput feature of the LoopNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.bodyOutput, referenceValueForBodyOutput);

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
	protected void initializeRemoveExpectedModelForLoopNodeBodyOutput() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject loopNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)loopNode.eGet(UMLPackage.eINSTANCE.getLoopNode_BodyOutput());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, loopNode, UMLPackage.eINSTANCE.getLoopNode_BodyOutput(), allReferencedInstances.get(0)));
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
	public void testRemoveLoopNodeBodyOutput() throws Exception {

		// Import the input model
		initializeInputModel();

		loopNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (loopNode == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLoopNodeBodyOutput();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LoopNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), loopNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(loopNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, loopNodeMetaClass, firstInstanceOf, null);

		// Change value of the bodyOutput feature of the LoopNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LoopNode.Properties.bodyOutput, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






























}
