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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.AddStructuralFeatureValueAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for AddStructuralFeatureValueAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class AddStructuralFeatureValueActionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass addStructuralFeatureValueActionMetaClass = UMLPackage.eINSTANCE.getAddStructuralFeatureValueAction();

	/**
	 * The type to edit
	 */
	private EObject addStructuralFeatureValueAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class structuralFeature
	 */
	private Object referenceValueForStructuralFeature;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;
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
	private EClass structuralFeatureMetaClass = UMLPackage.eINSTANCE.getStructuralFeature();

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
	protected void initializeExpectedModelForAddStructuralFeatureValueActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditAddStructuralFeatureValueActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddStructuralFeatureValueActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the AddStructuralFeatureValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddStructuralFeatureValueActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditAddStructuralFeatureValueActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((AddStructuralFeatureValueAction)addStructuralFeatureValueAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForAddStructuralFeatureValueActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the AddStructuralFeatureValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddStructuralFeatureValueActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((AddStructuralFeatureValueAction)addStructuralFeatureValueAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditAddStructuralFeatureValueActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddStructuralFeatureValueActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the AddStructuralFeatureValueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForAddStructuralFeatureValueActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)addStructuralFeatureValueAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveAddStructuralFeatureValueActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAddStructuralFeatureValueActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the AddStructuralFeatureValueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddStructuralFeatureValueActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditAddStructuralFeatureValueActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddStructuralFeatureValueActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the AddStructuralFeatureValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddStructuralFeatureValueActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((AddStructuralFeatureValueAction)addStructuralFeatureValueAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditAddStructuralFeatureValueActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddStructuralFeatureValueActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the AddStructuralFeatureValueAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForAddStructuralFeatureValueActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveAddStructuralFeatureValueActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAddStructuralFeatureValueActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the AddStructuralFeatureValueAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForAddStructuralFeatureValueActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((AddStructuralFeatureValueAction)addStructuralFeatureValueAction).getActivity());
		cc.append(SetCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditAddStructuralFeatureValueActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddStructuralFeatureValueActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the AddStructuralFeatureValueAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForAddStructuralFeatureValueActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveAddStructuralFeatureValueActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAddStructuralFeatureValueActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the AddStructuralFeatureValueAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForAddStructuralFeatureValueActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((AddStructuralFeatureValueAction)addStructuralFeatureValueAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditAddStructuralFeatureValueActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddStructuralFeatureValueActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the AddStructuralFeatureValueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForAddStructuralFeatureValueActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)addStructuralFeatureValueAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveAddStructuralFeatureValueActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAddStructuralFeatureValueActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the AddStructuralFeatureValueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddStructuralFeatureValueActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((AddStructuralFeatureValueAction)addStructuralFeatureValueAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditAddStructuralFeatureValueActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddStructuralFeatureValueActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the AddStructuralFeatureValueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForAddStructuralFeatureValueActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)addStructuralFeatureValueAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveAddStructuralFeatureValueActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAddStructuralFeatureValueActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the AddStructuralFeatureValueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddStructuralFeatureValueActionIsReplaceAll() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, addStructuralFeatureValueAction, UMLPackage.eINSTANCE.getAddStructuralFeatureValueAction_IsReplaceAll(), UPDATED_VALUE));
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
	public void testEditAddStructuralFeatureValueActionIsReplaceAll() throws Exception {

		// Import the input model
		initializeInputModel();

		addStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (addStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddStructuralFeatureValueActionIsReplaceAll();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isReplaceAll feature of the AddStructuralFeatureValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.AddStructuralFeatureValueAction.Properties.isReplaceAll, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
