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
package org.eclipse.uml2.uml.tests.junit.properties;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ReadVariableAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ReadVariableAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ReadVariableActionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass readVariableActionMetaClass = UMLPackage.eINSTANCE.getReadVariableAction();

	/**
	 * The type to edit
	 */
	private EObject readVariableAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
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
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

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
	protected void initializeExpectedModelForReadVariableActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditReadVariableActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadVariableActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the ReadVariableAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReadVariableActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditReadVariableActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ReadVariableAction)readVariableAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForReadVariableActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the ReadVariableAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReadVariableActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ReadVariableAction)readVariableAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditReadVariableActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadVariableActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadVariableAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReadVariableActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readVariableAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveReadVariableActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadVariableActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadVariableAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReadVariableActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditReadVariableActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadVariableActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ReadVariableAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReadVariableActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ReadVariableAction)readVariableAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditReadVariableActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadVariableActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadVariableAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReadVariableActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveReadVariableActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadVariableActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadVariableAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForReadVariableActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ReadVariableAction)readVariableAction).getActivity());
		cc.append(SetCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditReadVariableActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadVariableActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ReadVariableAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReadVariableActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveReadVariableActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadVariableActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ReadVariableAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForReadVariableActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ReadVariableAction)readVariableAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditReadVariableActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadVariableActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadVariableAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReadVariableActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readVariableAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveReadVariableActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadVariableActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadVariableAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReadVariableActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ReadVariableAction)readVariableAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditReadVariableActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadVariableActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadVariableAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReadVariableActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readVariableAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveReadVariableActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadVariableActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadVariableAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReadVariableActionVariable() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, variableMetaClass);
		referenceValueForVariable = bot.changeReferenceValue(allInstancesOf, ((ReadVariableAction)readVariableAction).getVariable());
		cc.append(SetCommand.create(editingDomain, readVariableAction, UMLPackage.eINSTANCE.getVariableAction_Variable(), referenceValueForVariable));
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
	public void testEditReadVariableActionVariable() throws Exception {

		// Import the input model
		initializeInputModel();

		readVariableAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (readVariableAction == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadVariableActionVariable();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReadVariableAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readVariableActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readVariableActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the variable feature of the ReadVariableAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReadVariableAction.Properties.variable, allInstancesOf.indexOf(referenceValueForVariable), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
