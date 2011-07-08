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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.RemoveVariableValueAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for RemoveVariableValueAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class RemoveVariableValueActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass removeVariableValueActionMetaClass = UMLPackage.eINSTANCE.getRemoveVariableValueAction();

	/**
	 * The type to edit
	 */
	private EObject removeVariableValueAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class variable
	 */
	private Object referenceValueForVariable;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;
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
	protected void initializeExpectedModelForRemoveVariableValueActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditRemoveVariableValueActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveVariableValueActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the RemoveVariableValueAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRemoveVariableValueActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditRemoveVariableValueActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((RemoveVariableValueAction)removeVariableValueAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForRemoveVariableValueActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the RemoveVariableValueAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRemoveVariableValueActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((RemoveVariableValueAction)removeVariableValueAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditRemoveVariableValueActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveVariableValueActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the RemoveVariableValueAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForRemoveVariableValueActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)removeVariableValueAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveRemoveVariableValueActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRemoveVariableValueActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the RemoveVariableValueAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForRemoveVariableValueActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditRemoveVariableValueActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveVariableValueActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the RemoveVariableValueAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRemoveVariableValueActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((RemoveVariableValueAction)removeVariableValueAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditRemoveVariableValueActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveVariableValueActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the RemoveVariableValueAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForRemoveVariableValueActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveRemoveVariableValueActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRemoveVariableValueActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the RemoveVariableValueAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForRemoveVariableValueActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((RemoveVariableValueAction)removeVariableValueAction).getActivity());
		cc.append(SetCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditRemoveVariableValueActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveVariableValueActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the RemoveVariableValueAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForRemoveVariableValueActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveRemoveVariableValueActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRemoveVariableValueActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the RemoveVariableValueAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.activity);
		

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
	protected void initializeExpectedModelForRemoveVariableValueActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((RemoveVariableValueAction)removeVariableValueAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditRemoveVariableValueActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveVariableValueActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the RemoveVariableValueAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForRemoveVariableValueActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)removeVariableValueAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveRemoveVariableValueActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRemoveVariableValueActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the RemoveVariableValueAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForRemoveVariableValueActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((RemoveVariableValueAction)removeVariableValueAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditRemoveVariableValueActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveVariableValueActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the RemoveVariableValueAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForRemoveVariableValueActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)removeVariableValueAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveRemoveVariableValueActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRemoveVariableValueActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the RemoveVariableValueAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForRemoveVariableValueActionVariable() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, variableMetaClass);
		referenceValueForVariable = bot.changeReferenceValue(allInstancesOf, ((RemoveVariableValueAction)removeVariableValueAction).getVariable());
		cc.append(SetCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getVariableAction_Variable(), referenceValueForVariable));
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
	public void testEditRemoveVariableValueActionVariable() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveVariableValueActionVariable();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the variable feature of the RemoveVariableValueAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.variable, allInstancesOf.indexOf(referenceValueForVariable));

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
	protected void initializeExpectedModelForRemoveVariableValueActionIsRemoveDuplicates() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, removeVariableValueAction, UMLPackage.eINSTANCE.getRemoveVariableValueAction_IsRemoveDuplicates(), UPDATED_VALUE));
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
	public void testEditRemoveVariableValueActionIsRemoveDuplicates() throws Exception {

		// Import the input model
		initializeInputModel();

		removeVariableValueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (removeVariableValueAction == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRemoveVariableValueActionIsRemoveDuplicates();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RemoveVariableValueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), removeVariableValueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(removeVariableValueActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, removeVariableValueActionMetaClass, firstInstanceOf, null);

		// Change value of the isRemoveDuplicates feature of the RemoveVariableValueAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.RemoveVariableValueAction.Properties.isRemoveDuplicates, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
