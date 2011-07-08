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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ReadStructuralFeatureAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ReadStructuralFeatureAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ReadStructuralFeatureActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass readStructuralFeatureActionMetaClass = UMLPackage.eINSTANCE.getReadStructuralFeatureAction();

	/**
	 * The type to edit
	 */
	private EObject readStructuralFeatureAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

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
	 * The reference value for the reference class structuralFeature
	 */
	private Object referenceValueForStructuralFeature;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

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
	protected void initializeExpectedModelForReadStructuralFeatureActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditReadStructuralFeatureActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadStructuralFeatureActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ReadStructuralFeatureAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadStructuralFeatureActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditReadStructuralFeatureActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ReadStructuralFeatureAction)readStructuralFeatureAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForReadStructuralFeatureActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ReadStructuralFeatureAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadStructuralFeatureActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ReadStructuralFeatureAction)readStructuralFeatureAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditReadStructuralFeatureActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadStructuralFeatureActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadStructuralFeatureAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForReadStructuralFeatureActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readStructuralFeatureAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveReadStructuralFeatureActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadStructuralFeatureActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadStructuralFeatureAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadStructuralFeatureActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditReadStructuralFeatureActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadStructuralFeatureActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ReadStructuralFeatureAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadStructuralFeatureActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ReadStructuralFeatureAction)readStructuralFeatureAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditReadStructuralFeatureActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadStructuralFeatureActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadStructuralFeatureAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForReadStructuralFeatureActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveReadStructuralFeatureActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadStructuralFeatureActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadStructuralFeatureAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForReadStructuralFeatureActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ReadStructuralFeatureAction)readStructuralFeatureAction).getActivity());
		cc.append(SetCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditReadStructuralFeatureActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadStructuralFeatureActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReadStructuralFeatureAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForReadStructuralFeatureActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveReadStructuralFeatureActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadStructuralFeatureActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReadStructuralFeatureAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.activity);
		

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
	protected void initializeExpectedModelForReadStructuralFeatureActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ReadStructuralFeatureAction)readStructuralFeatureAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditReadStructuralFeatureActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadStructuralFeatureActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadStructuralFeatureAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForReadStructuralFeatureActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readStructuralFeatureAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveReadStructuralFeatureActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadStructuralFeatureActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadStructuralFeatureAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadStructuralFeatureActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ReadStructuralFeatureAction)readStructuralFeatureAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditReadStructuralFeatureActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadStructuralFeatureActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadStructuralFeatureAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForReadStructuralFeatureActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readStructuralFeatureAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readStructuralFeatureAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveReadStructuralFeatureActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readStructuralFeatureAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (readStructuralFeatureAction == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadStructuralFeatureActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadStructuralFeatureAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readStructuralFeatureActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readStructuralFeatureActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readStructuralFeatureActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadStructuralFeatureAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
