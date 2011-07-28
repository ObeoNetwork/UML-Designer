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
import org.eclipse.uml2.uml.ClearAssociationAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ClearAssociationAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ClearAssociationActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass clearAssociationActionMetaClass = UMLPackage.eINSTANCE.getClearAssociationAction();

	/**
	 * The type to edit
	 */
	private EObject clearAssociationAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class association
	 */
	private Object referenceValueForAssociation;

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
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

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
	private EClass activityNodeMetaClass = UMLPackage.eINSTANCE.getActivityNode();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass associationMetaClass = UMLPackage.eINSTANCE.getAssociation();

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
	protected void initializeExpectedModelForClearAssociationActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditClearAssociationActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearAssociationActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ClearAssociationAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForClearAssociationActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditClearAssociationActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ClearAssociationAction)clearAssociationAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForClearAssociationActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ClearAssociationAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForClearAssociationActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ClearAssociationAction)clearAssociationAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditClearAssociationActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearAssociationActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ClearAssociationAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForClearAssociationActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clearAssociationAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveClearAssociationActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearAssociationActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ClearAssociationAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForClearAssociationActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditClearAssociationActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearAssociationActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ClearAssociationAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForClearAssociationActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ClearAssociationAction)clearAssociationAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditClearAssociationActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearAssociationActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ClearAssociationAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForClearAssociationActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveClearAssociationActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearAssociationActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ClearAssociationAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForClearAssociationActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ClearAssociationAction)clearAssociationAction).getActivity());
		cc.append(SetCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditClearAssociationActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearAssociationActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ClearAssociationAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForClearAssociationActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveClearAssociationActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearAssociationActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ClearAssociationAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.activity);
		

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
	protected void initializeExpectedModelForClearAssociationActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ClearAssociationAction)clearAssociationAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditClearAssociationActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearAssociationActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ClearAssociationAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForClearAssociationActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clearAssociationAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveClearAssociationActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearAssociationActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ClearAssociationAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForClearAssociationActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ClearAssociationAction)clearAssociationAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditClearAssociationActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearAssociationActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ClearAssociationAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForClearAssociationActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clearAssociationAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveClearAssociationActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClearAssociationActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ClearAssociationAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForClearAssociationActionAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		referenceValueForAssociation = bot.changeReferenceValue(allInstancesOf, ((ClearAssociationAction)clearAssociationAction).getAssociation());
		cc.append(SetCommand.create(editingDomain, clearAssociationAction, UMLPackage.eINSTANCE.getClearAssociationAction_Association(), referenceValueForAssociation));
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
	public void testEditClearAssociationActionAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		clearAssociationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (clearAssociationAction == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClearAssociationActionAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ClearAssociationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clearAssociationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clearAssociationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clearAssociationActionMetaClass, firstInstanceOf, null);

		// Change value of the association feature of the ClearAssociationAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ClearAssociationAction.Properties.association, allInstancesOf.indexOf(referenceValueForAssociation));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
