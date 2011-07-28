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
import org.eclipse.uml2.uml.ClearStructuralFeatureAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ClearStructuralFeatureAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ClearStructuralFeatureActionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass clearStructuralFeatureActionMetaClass = UMLPackage.eINSTANCE.getClearStructuralFeatureAction();

	/**
	 * The type to edit
	 */
	private EObject clearStructuralFeatureAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class structuralFeature
	 */
	private Object referenceValueForStructuralFeature;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

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
	protected void initializeExpectedModelForClearStructuralFeatureActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditClearStructuralFeatureActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearStructuralFeatureActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the ClearStructuralFeatureAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForClearStructuralFeatureActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditClearStructuralFeatureActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ClearStructuralFeatureAction)clearStructuralFeatureAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForClearStructuralFeatureActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the ClearStructuralFeatureAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForClearStructuralFeatureActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ClearStructuralFeatureAction)clearStructuralFeatureAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditClearStructuralFeatureActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearStructuralFeatureActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ClearStructuralFeatureAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForClearStructuralFeatureActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clearStructuralFeatureAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveClearStructuralFeatureActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearStructuralFeatureActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ClearStructuralFeatureAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForClearStructuralFeatureActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditClearStructuralFeatureActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearStructuralFeatureActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ClearStructuralFeatureAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForClearStructuralFeatureActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ClearStructuralFeatureAction)clearStructuralFeatureAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditClearStructuralFeatureActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearStructuralFeatureActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ClearStructuralFeatureAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForClearStructuralFeatureActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveClearStructuralFeatureActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearStructuralFeatureActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ClearStructuralFeatureAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForClearStructuralFeatureActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ClearStructuralFeatureAction)clearStructuralFeatureAction).getActivity());
		cc.append(SetCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditClearStructuralFeatureActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearStructuralFeatureActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ClearStructuralFeatureAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForClearStructuralFeatureActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveClearStructuralFeatureActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearStructuralFeatureActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ClearStructuralFeatureAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForClearStructuralFeatureActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ClearStructuralFeatureAction)clearStructuralFeatureAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditClearStructuralFeatureActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearStructuralFeatureActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ClearStructuralFeatureAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForClearStructuralFeatureActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clearStructuralFeatureAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveClearStructuralFeatureActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearStructuralFeatureActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ClearStructuralFeatureAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForClearStructuralFeatureActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ClearStructuralFeatureAction)clearStructuralFeatureAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditClearStructuralFeatureActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearStructuralFeatureActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ClearStructuralFeatureAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForClearStructuralFeatureActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clearStructuralFeatureAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clearStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveClearStructuralFeatureActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		clearStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (clearStructuralFeatureAction == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearStructuralFeatureActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ClearStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearStructuralFeatureActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ClearStructuralFeatureAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ClearStructuralFeatureAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
