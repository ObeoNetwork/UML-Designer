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
import org.eclipse.uml2.uml.ReadLinkAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ReadLinkAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ReadLinkActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass readLinkActionMetaClass = UMLPackage.eINSTANCE.getReadLinkAction();

	/**
	 * The type to edit
	 */
	private EObject readLinkAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

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
	protected void initializeExpectedModelForReadLinkActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditReadLinkActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ReadLinkAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadLinkActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditReadLinkActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ReadLinkAction)readLinkAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForReadLinkActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ReadLinkAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadLinkActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ReadLinkAction)readLinkAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditReadLinkActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadLinkAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForReadLinkActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readLinkAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveReadLinkActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadLinkActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadLinkAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadLinkActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditReadLinkActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ReadLinkAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadLinkActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ReadLinkAction)readLinkAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditReadLinkActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadLinkAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForReadLinkActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveReadLinkActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadLinkActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadLinkAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForReadLinkActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ReadLinkAction)readLinkAction).getActivity());
		cc.append(SetCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditReadLinkActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReadLinkAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForReadLinkActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveReadLinkActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadLinkActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReadLinkAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.activity);
		

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
	protected void initializeExpectedModelForReadLinkActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ReadLinkAction)readLinkAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditReadLinkActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadLinkAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForReadLinkActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readLinkAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveReadLinkActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadLinkActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadLinkAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadLinkActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ReadLinkAction)readLinkAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditReadLinkActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadLinkAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForReadLinkActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readLinkAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readLinkAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveReadLinkActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (readLinkAction == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadLinkActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadLinkAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
