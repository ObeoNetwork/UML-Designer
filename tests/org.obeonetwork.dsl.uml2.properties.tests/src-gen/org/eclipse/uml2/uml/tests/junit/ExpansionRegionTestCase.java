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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ExpansionRegion;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ExpansionRegion
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ExpansionRegionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass expansionRegionMetaClass = UMLPackage.eINSTANCE.getExpansionRegion();

	/**
	 * The type to edit
	 */
	private EObject expansionRegion;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class mode
	 */
	private Object enumValueForMode;
	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inActivity
	 */
	private Object referenceValueForInActivity;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class inputElement
	 */
	private Object referenceValueForInputElement;

	/**
	 * The reference value for the reference class outputElement
	 */
	private Object referenceValueForOutputElement;
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
	private EClass expansionNodeMetaClass = UMLPackage.eINSTANCE.getExpansionNode();

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
	protected void initializeExpectedModelForExpansionRegionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditExpansionRegionName() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionRegionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ExpansionRegion element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExpansionRegionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditExpansionRegionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ExpansionRegion)expansionRegion).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForExpansionRegionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ExpansionRegion element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExpansionRegionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ExpansionRegion)expansionRegion).getClientDependency());
		cc.append(AddCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditExpansionRegionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionRegionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExpansionRegion element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForExpansionRegionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)expansionRegion.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveExpansionRegionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionRegionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExpansionRegion element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExpansionRegionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditExpansionRegionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionRegionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ExpansionRegion element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExpansionRegionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ExpansionRegion)expansionRegion).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditExpansionRegionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionRegionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ExpansionRegion element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForExpansionRegionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveExpansionRegionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionRegionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ExpansionRegion element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForExpansionRegionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ExpansionRegion)expansionRegion).getActivity());
		cc.append(SetCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditExpansionRegionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionRegionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ExpansionRegion element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForExpansionRegionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveExpansionRegionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionRegionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ExpansionRegion element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.activity);
		

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
	protected void initializeExpectedModelForExpansionRegionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ExpansionRegion)expansionRegion).getInPartition());
		cc.append(AddCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditExpansionRegionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionRegionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ExpansionRegion element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForExpansionRegionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)expansionRegion.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveExpansionRegionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionRegionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ExpansionRegion element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExpansionRegionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ExpansionRegion)expansionRegion).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditExpansionRegionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionRegionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ExpansionRegion element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForExpansionRegionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)expansionRegion.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveExpansionRegionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionRegionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ExpansionRegion element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExpansionRegionInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForInActivity = bot.changeReferenceValue(allInstancesOf, ((ExpansionRegion)expansionRegion).getInActivity());
		cc.append(SetCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), referenceValueForInActivity));
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
	public void testEditExpansionRegionInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionRegionInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the inActivity feature of the ExpansionRegion element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.inActivity, allInstancesOf.indexOf(referenceValueForInActivity)+1);

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
	protected void initializeRemoveExpectedModelForExpansionRegionInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), null));
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
	public void testRemoveExpansionRegionInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionRegionInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the inActivity feature of the ExpansionRegion element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.inActivity);
		

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
	protected void initializeExpectedModelForExpansionRegionMustIsolate() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate(), UPDATED_VALUE));
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
	public void testEditExpansionRegionMustIsolate() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionRegionMustIsolate();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the mustIsolate feature of the ExpansionRegion element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.mustIsolate, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExpansionRegionMode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getExpansionRegion_Mode(), UPDATED_VALUE));
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
	public void testEditExpansionRegionMode() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		enumValueForMode = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getExpansionKind(), ((ExpansionRegion)expansionRegion).getMode().getLiteral());
		// Create the expected model
		initializeExpectedModelForExpansionRegionMode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the mode feature of the ExpansionRegion element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.mode, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExpansionRegionInputElement() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, expansionNodeMetaClass);
		referenceValueForInputElement = bot.changeReferenceValue(allInstancesOf, ((ExpansionRegion)expansionRegion).getInputElement());
		cc.append(AddCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getExpansionRegion_InputElement(), referenceValueForInputElement));
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
	public void testEditExpansionRegionInputElement() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionRegionInputElement();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the inputElement feature of the ExpansionRegion element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.inputElement, referenceValueForInputElement);

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
	protected void initializeRemoveExpectedModelForExpansionRegionInputElement() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)expansionRegion.eGet(UMLPackage.eINSTANCE.getExpansionRegion_InputElement());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getExpansionRegion_InputElement(), allReferencedInstances.get(0)));
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
	public void testRemoveExpansionRegionInputElement() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionRegionInputElement();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the inputElement feature of the ExpansionRegion element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.inputElement, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExpansionRegionOutputElement() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, expansionNodeMetaClass);
		referenceValueForOutputElement = bot.changeReferenceValue(allInstancesOf, ((ExpansionRegion)expansionRegion).getOutputElement());
		cc.append(AddCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getExpansionRegion_OutputElement(), referenceValueForOutputElement));
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
	public void testEditExpansionRegionOutputElement() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExpansionRegionOutputElement();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the outputElement feature of the ExpansionRegion element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.outputElement, referenceValueForOutputElement);

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
	protected void initializeRemoveExpectedModelForExpansionRegionOutputElement() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)expansionRegion.eGet(UMLPackage.eINSTANCE.getExpansionRegion_OutputElement());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, expansionRegion, UMLPackage.eINSTANCE.getExpansionRegion_OutputElement(), allReferencedInstances.get(0)));
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
	public void testRemoveExpansionRegionOutputElement() throws Exception {

		// Import the input model
		initializeInputModel();

		expansionRegion = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (expansionRegion == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExpansionRegionOutputElement();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExpansionRegion element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), expansionRegionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(expansionRegionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, expansionRegionMetaClass, firstInstanceOf, null);

		// Change value of the outputElement feature of the ExpansionRegion element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExpansionRegion.Properties.outputElement, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




























}
