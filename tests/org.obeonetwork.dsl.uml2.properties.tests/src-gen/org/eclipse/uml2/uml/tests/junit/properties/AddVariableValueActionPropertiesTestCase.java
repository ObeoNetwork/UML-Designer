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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.AddVariableValueAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for AddVariableValueAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class AddVariableValueActionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass addVariableValueActionMetaClass = UMLPackage.eINSTANCE.getAddVariableValueAction();

	/**
	 * The type to edit
	 */
	private EObject addVariableValueAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
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
	 * The reference value for the reference class variable
	 */
	private Object referenceValueForVariable;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

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
	private EClass variableMetaClass = UMLPackage.eINSTANCE.getVariable();

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
	protected void initializeExpectedModelForAddVariableValueActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditAddVariableValueActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddVariableValueActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the AddVariableValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddVariableValueActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditAddVariableValueActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((AddVariableValueAction)addVariableValueAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForAddVariableValueActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the AddVariableValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddVariableValueActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((AddVariableValueAction)addVariableValueAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditAddVariableValueActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddVariableValueActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the AddVariableValueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForAddVariableValueActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)addVariableValueAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveAddVariableValueActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAddVariableValueActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the AddVariableValueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddVariableValueActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditAddVariableValueActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddVariableValueActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the AddVariableValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddVariableValueActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((AddVariableValueAction)addVariableValueAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditAddVariableValueActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddVariableValueActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the AddVariableValueAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForAddVariableValueActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveAddVariableValueActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAddVariableValueActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the AddVariableValueAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForAddVariableValueActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((AddVariableValueAction)addVariableValueAction).getActivity());
		cc.append(SetCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditAddVariableValueActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddVariableValueActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the AddVariableValueAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForAddVariableValueActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveAddVariableValueActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAddVariableValueActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the AddVariableValueAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForAddVariableValueActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((AddVariableValueAction)addVariableValueAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditAddVariableValueActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddVariableValueActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the AddVariableValueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForAddVariableValueActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)addVariableValueAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveAddVariableValueActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAddVariableValueActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the AddVariableValueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddVariableValueActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((AddVariableValueAction)addVariableValueAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditAddVariableValueActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddVariableValueActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the AddVariableValueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForAddVariableValueActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)addVariableValueAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveAddVariableValueActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAddVariableValueActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the AddVariableValueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddVariableValueActionVariable() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, variableMetaClass);
		referenceValueForVariable = bot.changeReferenceValue(allInstancesOf, ((AddVariableValueAction)addVariableValueAction).getVariable());
		cc.append(SetCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getVariableAction_Variable(), referenceValueForVariable));
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
	public void testEditAddVariableValueActionVariable() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddVariableValueActionVariable();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the variable feature of the AddVariableValueAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.variable, allInstancesOf.indexOf(referenceValueForVariable), bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForAddVariableValueActionIsReplaceAll() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, addVariableValueAction, UMLPackage.eINSTANCE.getAddVariableValueAction_IsReplaceAll(), UPDATED_VALUE));
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
	public void testEditAddVariableValueActionIsReplaceAll() throws Exception {

		// Import the input model
		initializeInputModel();

		addVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (addVariableValueAction == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAddVariableValueActionIsReplaceAll();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the AddVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), addVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(addVariableValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isReplaceAll feature of the AddVariableValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.AddVariableValueAction.Properties.isReplaceAll, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
