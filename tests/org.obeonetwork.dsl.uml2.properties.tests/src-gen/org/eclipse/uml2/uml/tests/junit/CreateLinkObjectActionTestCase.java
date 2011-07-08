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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.CreateLinkObjectAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for CreateLinkObjectAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class CreateLinkObjectActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass createLinkObjectActionMetaClass = UMLPackage.eINSTANCE.getCreateLinkObjectAction();

	/**
	 * The type to edit
	 */
	private EObject createLinkObjectAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;
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
	protected void initializeExpectedModelForCreateLinkObjectActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditCreateLinkObjectActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCreateLinkObjectActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the CreateLinkObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCreateLinkObjectActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditCreateLinkObjectActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((CreateLinkObjectAction)createLinkObjectAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForCreateLinkObjectActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the CreateLinkObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCreateLinkObjectActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((CreateLinkObjectAction)createLinkObjectAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditCreateLinkObjectActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCreateLinkObjectActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CreateLinkObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForCreateLinkObjectActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)createLinkObjectAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveCreateLinkObjectActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCreateLinkObjectActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CreateLinkObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCreateLinkObjectActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditCreateLinkObjectActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCreateLinkObjectActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the CreateLinkObjectAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCreateLinkObjectActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((CreateLinkObjectAction)createLinkObjectAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditCreateLinkObjectActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCreateLinkObjectActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the CreateLinkObjectAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForCreateLinkObjectActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveCreateLinkObjectActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCreateLinkObjectActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the CreateLinkObjectAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForCreateLinkObjectActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((CreateLinkObjectAction)createLinkObjectAction).getActivity());
		cc.append(SetCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditCreateLinkObjectActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCreateLinkObjectActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the CreateLinkObjectAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForCreateLinkObjectActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveCreateLinkObjectActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCreateLinkObjectActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the CreateLinkObjectAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.activity);
		

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
	protected void initializeExpectedModelForCreateLinkObjectActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((CreateLinkObjectAction)createLinkObjectAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditCreateLinkObjectActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCreateLinkObjectActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the CreateLinkObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForCreateLinkObjectActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)createLinkObjectAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveCreateLinkObjectActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCreateLinkObjectActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the CreateLinkObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCreateLinkObjectActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((CreateLinkObjectAction)createLinkObjectAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditCreateLinkObjectActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCreateLinkObjectActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the CreateLinkObjectAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForCreateLinkObjectActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)createLinkObjectAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, createLinkObjectAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveCreateLinkObjectActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		createLinkObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (createLinkObjectAction == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCreateLinkObjectActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreateLinkObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), createLinkObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(createLinkObjectActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, createLinkObjectActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the CreateLinkObjectAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CreateLinkObjectAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
