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
import org.eclipse.uml2.uml.DestroyObjectAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for DestroyObjectAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class DestroyObjectActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass destroyObjectActionMetaClass = UMLPackage.eINSTANCE.getDestroyObjectAction();

	/**
	 * The type to edit
	 */
	private EObject destroyObjectAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

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
	protected void initializeExpectedModelForDestroyObjectActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditDestroyObjectActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDestroyObjectActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the DestroyObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDestroyObjectActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditDestroyObjectActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((DestroyObjectAction)destroyObjectAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForDestroyObjectActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the DestroyObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDestroyObjectActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((DestroyObjectAction)destroyObjectAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditDestroyObjectActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDestroyObjectActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DestroyObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForDestroyObjectActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)destroyObjectAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveDestroyObjectActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDestroyObjectActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DestroyObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDestroyObjectActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditDestroyObjectActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDestroyObjectActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the DestroyObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDestroyObjectActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((DestroyObjectAction)destroyObjectAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditDestroyObjectActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDestroyObjectActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the DestroyObjectAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForDestroyObjectActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveDestroyObjectActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDestroyObjectActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the DestroyObjectAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForDestroyObjectActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((DestroyObjectAction)destroyObjectAction).getActivity());
		cc.append(SetCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditDestroyObjectActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDestroyObjectActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the DestroyObjectAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForDestroyObjectActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveDestroyObjectActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDestroyObjectActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the DestroyObjectAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.activity);
		

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
	protected void initializeExpectedModelForDestroyObjectActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((DestroyObjectAction)destroyObjectAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditDestroyObjectActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDestroyObjectActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the DestroyObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForDestroyObjectActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)destroyObjectAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveDestroyObjectActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDestroyObjectActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the DestroyObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDestroyObjectActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((DestroyObjectAction)destroyObjectAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditDestroyObjectActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDestroyObjectActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the DestroyObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForDestroyObjectActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)destroyObjectAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveDestroyObjectActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDestroyObjectActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the DestroyObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDestroyObjectActionIsDestroyLinks() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getDestroyObjectAction_IsDestroyLinks(), UPDATED_VALUE));
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
	public void testEditDestroyObjectActionIsDestroyLinks() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDestroyObjectActionIsDestroyLinks();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the isDestroyLinks feature of the DestroyObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.isDestroyLinks, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDestroyObjectActionIsDestroyOwnedObjects() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, destroyObjectAction, UMLPackage.eINSTANCE.getDestroyObjectAction_IsDestroyOwnedObjects(), UPDATED_VALUE));
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
	public void testEditDestroyObjectActionIsDestroyOwnedObjects() throws Exception {

		// Import the input model
		initializeInputModel();

		destroyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (destroyObjectAction == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDestroyObjectActionIsDestroyOwnedObjects();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DestroyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), destroyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(destroyObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, destroyObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the isDestroyOwnedObjects feature of the DestroyObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DestroyObjectAction.Properties.isDestroyOwnedObjects, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
