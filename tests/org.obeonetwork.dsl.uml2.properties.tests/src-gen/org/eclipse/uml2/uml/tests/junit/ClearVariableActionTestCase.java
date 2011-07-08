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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ClearVariableAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ClearVariableAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ClearVariableActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass clearVariableActionMetaClass = UMLPackage.eINSTANCE.getClearVariableAction();

	/**
	 * The type to edit
	 */
	private EObject clearVariableAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class variable
	 */
	private Object referenceValueForVariable;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

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
	protected void initializeExpectedModelForClearVariableActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditClearVariableActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearVariableActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ClearVariableAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForClearVariableActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditClearVariableActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ClearVariableAction)clearVariableAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForClearVariableActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ClearVariableAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForClearVariableActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ClearVariableAction)clearVariableAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditClearVariableActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearVariableActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ClearVariableAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForClearVariableActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clearVariableAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveClearVariableActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearVariableActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ClearVariableAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForClearVariableActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditClearVariableActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearVariableActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ClearVariableAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForClearVariableActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ClearVariableAction)clearVariableAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditClearVariableActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearVariableActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ClearVariableAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForClearVariableActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveClearVariableActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearVariableActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ClearVariableAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForClearVariableActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ClearVariableAction)clearVariableAction).getActivity());
		cc.append(SetCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditClearVariableActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearVariableActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ClearVariableAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForClearVariableActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveClearVariableActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearVariableActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ClearVariableAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.activity);
		

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
	protected void initializeExpectedModelForClearVariableActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ClearVariableAction)clearVariableAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditClearVariableActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearVariableActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ClearVariableAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForClearVariableActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clearVariableAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveClearVariableActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearVariableActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ClearVariableAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForClearVariableActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ClearVariableAction)clearVariableAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditClearVariableActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearVariableActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ClearVariableAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForClearVariableActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clearVariableAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveClearVariableActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearVariableActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ClearVariableAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForClearVariableActionVariable() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, variableMetaClass);
		referenceValueForVariable = bot.changeReferenceValue(allInstancesOf, ((ClearVariableAction)clearVariableAction).getVariable());
		cc.append(SetCommand.create(editingDomain, clearVariableAction, UMLPackage.eINSTANCE.getVariableAction_Variable(), referenceValueForVariable));
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
	public void testEditClearVariableActionVariable() throws Exception {

		// Import the input model
		initializeInputModel();

		clearVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (clearVariableAction == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearVariableActionVariable();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearVariableActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearVariableActionMetaClass, firstInstanceOf, null);

		// Change value of the variable feature of the ClearVariableAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ClearVariableAction.Properties.variable, allInstancesOf.indexOf(referenceValueForVariable));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
