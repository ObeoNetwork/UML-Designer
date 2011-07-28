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
import org.eclipse.uml2.uml.ReadLinkObjectEndAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ReadLinkObjectEndAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ReadLinkObjectEndActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass readLinkObjectEndActionMetaClass = UMLPackage.eINSTANCE.getReadLinkObjectEndAction();

	/**
	 * The type to edit
	 */
	private EObject readLinkObjectEndAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class end
	 */
	private Object referenceValueForEnd;

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
	private EClass propertyMetaClass = UMLPackage.eINSTANCE.getProperty();

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
	protected void initializeExpectedModelForReadLinkObjectEndActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditReadLinkObjectEndActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkObjectEndActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ReadLinkObjectEndAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadLinkObjectEndActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditReadLinkObjectEndActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ReadLinkObjectEndAction)readLinkObjectEndAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForReadLinkObjectEndActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ReadLinkObjectEndAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadLinkObjectEndActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ReadLinkObjectEndAction)readLinkObjectEndAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditReadLinkObjectEndActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkObjectEndActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadLinkObjectEndAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForReadLinkObjectEndActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readLinkObjectEndAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveReadLinkObjectEndActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadLinkObjectEndActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReadLinkObjectEndAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadLinkObjectEndActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditReadLinkObjectEndActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkObjectEndActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ReadLinkObjectEndAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReadLinkObjectEndActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ReadLinkObjectEndAction)readLinkObjectEndAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditReadLinkObjectEndActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkObjectEndActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadLinkObjectEndAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForReadLinkObjectEndActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveReadLinkObjectEndActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadLinkObjectEndActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReadLinkObjectEndAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForReadLinkObjectEndActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ReadLinkObjectEndAction)readLinkObjectEndAction).getActivity());
		cc.append(SetCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditReadLinkObjectEndActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkObjectEndActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReadLinkObjectEndAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForReadLinkObjectEndActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveReadLinkObjectEndActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadLinkObjectEndActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ReadLinkObjectEndAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.activity);
		

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
	protected void initializeExpectedModelForReadLinkObjectEndActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ReadLinkObjectEndAction)readLinkObjectEndAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditReadLinkObjectEndActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkObjectEndActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadLinkObjectEndAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForReadLinkObjectEndActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readLinkObjectEndAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveReadLinkObjectEndActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadLinkObjectEndActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReadLinkObjectEndAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadLinkObjectEndActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ReadLinkObjectEndAction)readLinkObjectEndAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditReadLinkObjectEndActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkObjectEndActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadLinkObjectEndAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForReadLinkObjectEndActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)readLinkObjectEndAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveReadLinkObjectEndActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReadLinkObjectEndActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReadLinkObjectEndAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReadLinkObjectEndActionEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForEnd = bot.changeReferenceValue(allInstancesOf, ((ReadLinkObjectEndAction)readLinkObjectEndAction).getEnd());
		cc.append(SetCommand.create(editingDomain, readLinkObjectEndAction, UMLPackage.eINSTANCE.getReadLinkObjectEndAction_End(), referenceValueForEnd));
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
	public void testEditReadLinkObjectEndActionEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		readLinkObjectEndAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (readLinkObjectEndAction == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReadLinkObjectEndActionEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ReadLinkObjectEndAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), readLinkObjectEndActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(readLinkObjectEndActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, readLinkObjectEndActionMetaClass, firstInstanceOf, null);

		// Change value of the end feature of the ReadLinkObjectEndAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ReadLinkObjectEndAction.Properties.end, allInstancesOf.indexOf(referenceValueForEnd));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
