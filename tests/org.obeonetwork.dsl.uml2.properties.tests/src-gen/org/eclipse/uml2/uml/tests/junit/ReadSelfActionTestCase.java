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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ReadSelfAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ReadSelfAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ReadSelfActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass readSelfActionMetaClass = UMLPackage.eINSTANCE.getReadSelfAction();

	/**
	 * The type to edit
	 */
	private EObject readSelfAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

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
	protected void initializeExpectedModelForReadSelfActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditReadSelfActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadSelfActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ReadSelfAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadSelfActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditReadSelfActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ReadSelfAction)readSelfAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForReadSelfActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ReadSelfAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadSelfActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ReadSelfAction)readSelfAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditReadSelfActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadSelfActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadSelfAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForReadSelfActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readSelfAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveReadSelfActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadSelfActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadSelfAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadSelfActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditReadSelfActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadSelfActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ReadSelfAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadSelfActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ReadSelfAction)readSelfAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditReadSelfActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadSelfActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadSelfAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForReadSelfActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveReadSelfActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadSelfActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadSelfAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForReadSelfActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ReadSelfAction)readSelfAction).getActivity());
		cc.append(SetCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditReadSelfActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadSelfActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReadSelfAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForReadSelfActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveReadSelfActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadSelfActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReadSelfAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.activity);
		

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
	protected void initializeExpectedModelForReadSelfActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ReadSelfAction)readSelfAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditReadSelfActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadSelfActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadSelfAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForReadSelfActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readSelfAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveReadSelfActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadSelfActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadSelfAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadSelfActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ReadSelfAction)readSelfAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditReadSelfActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadSelfActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadSelfAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForReadSelfActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readSelfAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readSelfAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveReadSelfActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readSelfAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (readSelfAction == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadSelfActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadSelfAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readSelfActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readSelfActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readSelfActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadSelfAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadSelfAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
