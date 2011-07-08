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
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UnmarshallAction;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for UnmarshallAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class UnmarshallActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass unmarshallActionMetaClass = UMLPackage.eINSTANCE.getUnmarshallAction();

	/**
	 * The type to edit
	 */
	private EObject unmarshallAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class unmarshallType
	 */
	private Object referenceValueForUnmarshallType;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

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
	private EClass classifierMetaClass = UMLPackage.eINSTANCE.getClassifier();

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
	protected void initializeExpectedModelForUnmarshallActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditUnmarshallActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUnmarshallActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the UnmarshallAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForUnmarshallActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditUnmarshallActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((UnmarshallAction)unmarshallAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForUnmarshallActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the UnmarshallAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForUnmarshallActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((UnmarshallAction)unmarshallAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditUnmarshallActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUnmarshallActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the UnmarshallAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForUnmarshallActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)unmarshallAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveUnmarshallActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForUnmarshallActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the UnmarshallAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForUnmarshallActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditUnmarshallActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUnmarshallActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the UnmarshallAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForUnmarshallActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((UnmarshallAction)unmarshallAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditUnmarshallActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUnmarshallActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the UnmarshallAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForUnmarshallActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveUnmarshallActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForUnmarshallActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the UnmarshallAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForUnmarshallActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((UnmarshallAction)unmarshallAction).getActivity());
		cc.append(SetCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditUnmarshallActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUnmarshallActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the UnmarshallAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForUnmarshallActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveUnmarshallActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForUnmarshallActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the UnmarshallAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.activity);
		

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
	protected void initializeExpectedModelForUnmarshallActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((UnmarshallAction)unmarshallAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditUnmarshallActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUnmarshallActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the UnmarshallAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForUnmarshallActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)unmarshallAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveUnmarshallActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForUnmarshallActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the UnmarshallAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForUnmarshallActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((UnmarshallAction)unmarshallAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditUnmarshallActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUnmarshallActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the UnmarshallAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForUnmarshallActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)unmarshallAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, unmarshallAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveUnmarshallActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		unmarshallAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (unmarshallAction == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForUnmarshallActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UnmarshallAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), unmarshallActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(unmarshallActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, unmarshallActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the UnmarshallAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UnmarshallAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
