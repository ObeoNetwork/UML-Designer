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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ReadIsClassifiedObjectAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ReadIsClassifiedObjectAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ReadIsClassifiedObjectActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass readIsClassifiedObjectActionMetaClass = UMLPackage.eINSTANCE.getReadIsClassifiedObjectAction();

	/**
	 * The type to edit
	 */
	private EObject readIsClassifiedObjectAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class classifier
	 */
	private Object referenceValueForClassifier;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

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
	protected void initializeExpectedModelForReadIsClassifiedObjectActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditReadIsClassifiedObjectActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadIsClassifiedObjectActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ReadIsClassifiedObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadIsClassifiedObjectActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditReadIsClassifiedObjectActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ReadIsClassifiedObjectAction)readIsClassifiedObjectAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForReadIsClassifiedObjectActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ReadIsClassifiedObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadIsClassifiedObjectActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ReadIsClassifiedObjectAction)readIsClassifiedObjectAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditReadIsClassifiedObjectActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadIsClassifiedObjectActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadIsClassifiedObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForReadIsClassifiedObjectActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readIsClassifiedObjectAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveReadIsClassifiedObjectActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadIsClassifiedObjectActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadIsClassifiedObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadIsClassifiedObjectActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditReadIsClassifiedObjectActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadIsClassifiedObjectActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ReadIsClassifiedObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadIsClassifiedObjectActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ReadIsClassifiedObjectAction)readIsClassifiedObjectAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditReadIsClassifiedObjectActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadIsClassifiedObjectActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadIsClassifiedObjectAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForReadIsClassifiedObjectActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveReadIsClassifiedObjectActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadIsClassifiedObjectActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadIsClassifiedObjectAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForReadIsClassifiedObjectActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ReadIsClassifiedObjectAction)readIsClassifiedObjectAction).getActivity());
		cc.append(SetCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditReadIsClassifiedObjectActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadIsClassifiedObjectActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReadIsClassifiedObjectAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForReadIsClassifiedObjectActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveReadIsClassifiedObjectActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadIsClassifiedObjectActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReadIsClassifiedObjectAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.activity);
		

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
	protected void initializeExpectedModelForReadIsClassifiedObjectActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ReadIsClassifiedObjectAction)readIsClassifiedObjectAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditReadIsClassifiedObjectActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadIsClassifiedObjectActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadIsClassifiedObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForReadIsClassifiedObjectActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readIsClassifiedObjectAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveReadIsClassifiedObjectActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadIsClassifiedObjectActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadIsClassifiedObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadIsClassifiedObjectActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ReadIsClassifiedObjectAction)readIsClassifiedObjectAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditReadIsClassifiedObjectActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadIsClassifiedObjectActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadIsClassifiedObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForReadIsClassifiedObjectActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readIsClassifiedObjectAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveReadIsClassifiedObjectActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadIsClassifiedObjectActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadIsClassifiedObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadIsClassifiedObjectActionIsDirect() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readIsClassifiedObjectAction, UMLPackage.eINSTANCE.getReadIsClassifiedObjectAction_IsDirect(), UPDATED_VALUE));
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
	public void testEditReadIsClassifiedObjectActionIsDirect() throws Exception {

		// Import the input model
		initializeInputModel();

		readIsClassifiedObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (readIsClassifiedObjectAction == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadIsClassifiedObjectActionIsDirect();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadIsClassifiedObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readIsClassifiedObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readIsClassifiedObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readIsClassifiedObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the isDirect feature of the ReadIsClassifiedObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadIsClassifiedObjectAction.Properties.isDirect, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
