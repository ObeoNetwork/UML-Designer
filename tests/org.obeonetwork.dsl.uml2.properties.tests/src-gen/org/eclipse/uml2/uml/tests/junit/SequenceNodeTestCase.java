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
import org.eclipse.uml2.uml.SequenceNode;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for SequenceNode
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class SequenceNodeTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass sequenceNodeMetaClass = UMLPackage.eINSTANCE.getSequenceNode();

	/**
	 * The type to edit
	 */
	private EObject sequenceNode;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inActivity
	 */
	private Object referenceValueForInActivity;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
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
	protected void initializeExpectedModelForSequenceNodeName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditSequenceNodeName() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSequenceNodeName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the SequenceNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSequenceNodeVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditSequenceNodeVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((SequenceNode)sequenceNode).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForSequenceNodeVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the SequenceNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSequenceNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((SequenceNode)sequenceNode).getClientDependency());
		cc.append(AddCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditSequenceNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSequenceNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SequenceNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForSequenceNodeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)sequenceNode.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveSequenceNodeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSequenceNodeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SequenceNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSequenceNodeIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditSequenceNodeIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSequenceNodeIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the SequenceNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSequenceNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((SequenceNode)sequenceNode).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditSequenceNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSequenceNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the SequenceNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForSequenceNodeInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveSequenceNodeInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSequenceNodeInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the SequenceNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForSequenceNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((SequenceNode)sequenceNode).getActivity());
		cc.append(SetCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditSequenceNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSequenceNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the SequenceNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForSequenceNodeActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveSequenceNodeActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSequenceNodeActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the SequenceNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.activity);
		

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
	protected void initializeExpectedModelForSequenceNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((SequenceNode)sequenceNode).getInPartition());
		cc.append(AddCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditSequenceNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSequenceNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the SequenceNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForSequenceNodeInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)sequenceNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveSequenceNodeInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSequenceNodeInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the SequenceNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSequenceNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((SequenceNode)sequenceNode).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditSequenceNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSequenceNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the SequenceNode element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForSequenceNodeInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)sequenceNode.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveSequenceNodeInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSequenceNodeInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the SequenceNode element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSequenceNodeInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForInActivity = bot.changeReferenceValue(allInstancesOf, ((SequenceNode)sequenceNode).getInActivity());
		cc.append(SetCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), referenceValueForInActivity));
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
	public void testEditSequenceNodeInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSequenceNodeInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the inActivity feature of the SequenceNode element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.inActivity, allInstancesOf.indexOf(referenceValueForInActivity)+1);

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
	protected void initializeRemoveExpectedModelForSequenceNodeInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), null));
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
	public void testRemoveSequenceNodeInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSequenceNodeInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the inActivity feature of the SequenceNode element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.inActivity);
		

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
	protected void initializeExpectedModelForSequenceNodeMustIsolate() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sequenceNode, UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate(), UPDATED_VALUE));
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
	public void testEditSequenceNodeMustIsolate() throws Exception {

		// Import the input model
		initializeInputModel();

		sequenceNode = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (sequenceNode == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSequenceNodeMustIsolate();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SequenceNode element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sequenceNodeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sequenceNodeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sequenceNodeMetaClass, firstInstanceOf, null);

		// Change value of the mustIsolate feature of the SequenceNode element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SequenceNode.Properties.mustIsolate, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
