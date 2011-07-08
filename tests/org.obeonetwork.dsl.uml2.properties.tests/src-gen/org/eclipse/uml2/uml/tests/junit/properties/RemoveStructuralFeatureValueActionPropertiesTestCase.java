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
import org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for RemoveStructuralFeatureValueAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class RemoveStructuralFeatureValueActionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass removeStructuralFeatureValueActionMetaClass = UMLPackage.eINSTANCE.getRemoveStructuralFeatureValueAction();

	/**
	 * The type to edit
	 */
	private EObject removeStructuralFeatureValueAction;

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
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class structuralFeature
	 */
	private Object referenceValueForStructuralFeature;

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
	protected void initializeExpectedModelForRemoveStructuralFeatureValueActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditRemoveStructuralFeatureValueActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveStructuralFeatureValueActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the RemoveStructuralFeatureValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForRemoveStructuralFeatureValueActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditRemoveStructuralFeatureValueActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((RemoveStructuralFeatureValueAction)removeStructuralFeatureValueAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForRemoveStructuralFeatureValueActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the RemoveStructuralFeatureValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForRemoveStructuralFeatureValueActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((RemoveStructuralFeatureValueAction)removeStructuralFeatureValueAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditRemoveStructuralFeatureValueActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveStructuralFeatureValueActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the RemoveStructuralFeatureValueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForRemoveStructuralFeatureValueActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)removeStructuralFeatureValueAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveRemoveStructuralFeatureValueActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRemoveStructuralFeatureValueActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the RemoveStructuralFeatureValueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForRemoveStructuralFeatureValueActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditRemoveStructuralFeatureValueActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveStructuralFeatureValueActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the RemoveStructuralFeatureValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForRemoveStructuralFeatureValueActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((RemoveStructuralFeatureValueAction)removeStructuralFeatureValueAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditRemoveStructuralFeatureValueActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveStructuralFeatureValueActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the RemoveStructuralFeatureValueAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForRemoveStructuralFeatureValueActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveRemoveStructuralFeatureValueActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRemoveStructuralFeatureValueActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the RemoveStructuralFeatureValueAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForRemoveStructuralFeatureValueActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((RemoveStructuralFeatureValueAction)removeStructuralFeatureValueAction).getActivity());
		cc.append(SetCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditRemoveStructuralFeatureValueActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveStructuralFeatureValueActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the RemoveStructuralFeatureValueAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForRemoveStructuralFeatureValueActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveRemoveStructuralFeatureValueActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRemoveStructuralFeatureValueActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the RemoveStructuralFeatureValueAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForRemoveStructuralFeatureValueActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((RemoveStructuralFeatureValueAction)removeStructuralFeatureValueAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditRemoveStructuralFeatureValueActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveStructuralFeatureValueActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the RemoveStructuralFeatureValueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForRemoveStructuralFeatureValueActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)removeStructuralFeatureValueAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveRemoveStructuralFeatureValueActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRemoveStructuralFeatureValueActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the RemoveStructuralFeatureValueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForRemoveStructuralFeatureValueActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((RemoveStructuralFeatureValueAction)removeStructuralFeatureValueAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditRemoveStructuralFeatureValueActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveStructuralFeatureValueActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the RemoveStructuralFeatureValueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForRemoveStructuralFeatureValueActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)removeStructuralFeatureValueAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveRemoveStructuralFeatureValueActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRemoveStructuralFeatureValueActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the RemoveStructuralFeatureValueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForRemoveStructuralFeatureValueActionIsRemoveDuplicates() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, removeStructuralFeatureValueAction, UMLPackage.eINSTANCE.getRemoveStructuralFeatureValueAction_IsRemoveDuplicates(), UPDATED_VALUE));
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
	public void testEditRemoveStructuralFeatureValueActionIsRemoveDuplicates() throws Exception {

		// Import the input model
		initializeInputModel();

		removeStructuralFeatureValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (removeStructuralFeatureValueAction == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveStructuralFeatureValueActionIsRemoveDuplicates();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the RemoveStructuralFeatureValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeStructuralFeatureValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeStructuralFeatureValueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isRemoveDuplicates feature of the RemoveStructuralFeatureValueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.RemoveStructuralFeatureValueAction.Properties.isRemoveDuplicates, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
