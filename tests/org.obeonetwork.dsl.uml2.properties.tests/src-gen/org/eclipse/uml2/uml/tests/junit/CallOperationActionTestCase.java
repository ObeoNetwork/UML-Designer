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
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for CallOperationAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class CallOperationActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass callOperationActionMetaClass = UMLPackage.eINSTANCE.getCallOperationAction();

	/**
	 * The type to edit
	 */
	private EObject callOperationAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class operation
	 */
	private Object referenceValueForOperation;

	/**
	 * The reference value for the reference class onPort
	 */
	private Object referenceValueForOnPort;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

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
	private EClass operationMetaClass = UMLPackage.eINSTANCE.getOperation();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass portMetaClass = UMLPackage.eINSTANCE.getPort();

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
	protected void initializeExpectedModelForCallOperationActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditCallOperationActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCallOperationActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the CallOperationAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCallOperationActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditCallOperationActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((CallOperationAction)callOperationAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForCallOperationActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the CallOperationAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCallOperationActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((CallOperationAction)callOperationAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditCallOperationActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCallOperationActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CallOperationAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForCallOperationActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)callOperationAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveCallOperationActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCallOperationActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CallOperationAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCallOperationActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditCallOperationActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCallOperationActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the CallOperationAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCallOperationActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((CallOperationAction)callOperationAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditCallOperationActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCallOperationActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the CallOperationAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForCallOperationActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveCallOperationActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCallOperationActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the CallOperationAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForCallOperationActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((CallOperationAction)callOperationAction).getActivity());
		cc.append(SetCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditCallOperationActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCallOperationActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the CallOperationAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForCallOperationActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveCallOperationActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCallOperationActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the CallOperationAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.activity);
		

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
	protected void initializeExpectedModelForCallOperationActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((CallOperationAction)callOperationAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditCallOperationActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCallOperationActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the CallOperationAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForCallOperationActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)callOperationAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveCallOperationActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCallOperationActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the CallOperationAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCallOperationActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((CallOperationAction)callOperationAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditCallOperationActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCallOperationActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the CallOperationAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForCallOperationActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)callOperationAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveCallOperationActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCallOperationActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the CallOperationAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCallOperationActionOnPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, portMetaClass);
		referenceValueForOnPort = bot.changeReferenceValue(allInstancesOf, ((CallOperationAction)callOperationAction).getOnPort());
		cc.append(SetCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getInvocationAction_OnPort(), referenceValueForOnPort));
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
	public void testEditCallOperationActionOnPort() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCallOperationActionOnPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the onPort feature of the CallOperationAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.onPort, allInstancesOf.indexOf(referenceValueForOnPort)+1);

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
	protected void initializeRemoveExpectedModelForCallOperationActionOnPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, portMetaClass);
		cc.append(SetCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getInvocationAction_OnPort(), null));
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
	public void testRemoveCallOperationActionOnPort() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCallOperationActionOnPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the onPort feature of the CallOperationAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.onPort);
		

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
	protected void initializeExpectedModelForCallOperationActionIsSynchronous() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getCallAction_IsSynchronous(), UPDATED_VALUE));
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
	public void testEditCallOperationActionIsSynchronous() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCallOperationActionIsSynchronous();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the isSynchronous feature of the CallOperationAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.isSynchronous, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCallOperationActionOperation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, operationMetaClass);
		referenceValueForOperation = bot.changeReferenceValue(allInstancesOf, ((CallOperationAction)callOperationAction).getOperation());
		cc.append(SetCommand.create(editingDomain, callOperationAction, UMLPackage.eINSTANCE.getCallOperationAction_Operation(), referenceValueForOperation));
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
	public void testEditCallOperationActionOperation() throws Exception {

		// Import the input model
		initializeInputModel();

		callOperationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (callOperationAction == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCallOperationActionOperation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CallOperationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), callOperationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(callOperationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, callOperationActionMetaClass, firstInstanceOf, null);

		// Change value of the operation feature of the CallOperationAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CallOperationAction.Properties.operation, allInstancesOf.indexOf(referenceValueForOperation));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
























}
