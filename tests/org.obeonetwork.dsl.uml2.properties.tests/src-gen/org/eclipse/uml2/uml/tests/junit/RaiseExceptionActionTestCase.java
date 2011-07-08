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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.RaiseExceptionAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for RaiseExceptionAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class RaiseExceptionActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass raiseExceptionActionMetaClass = UMLPackage.eINSTANCE.getRaiseExceptionAction();

	/**
	 * The type to edit
	 */
	private EObject raiseExceptionAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

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
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

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
	protected void initializeExpectedModelForRaiseExceptionActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditRaiseExceptionActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRaiseExceptionActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the RaiseExceptionAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRaiseExceptionActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditRaiseExceptionActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((RaiseExceptionAction)raiseExceptionAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForRaiseExceptionActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the RaiseExceptionAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRaiseExceptionActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((RaiseExceptionAction)raiseExceptionAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditRaiseExceptionActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRaiseExceptionActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the RaiseExceptionAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForRaiseExceptionActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)raiseExceptionAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveRaiseExceptionActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRaiseExceptionActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the RaiseExceptionAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForRaiseExceptionActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditRaiseExceptionActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRaiseExceptionActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the RaiseExceptionAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRaiseExceptionActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((RaiseExceptionAction)raiseExceptionAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditRaiseExceptionActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRaiseExceptionActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the RaiseExceptionAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForRaiseExceptionActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveRaiseExceptionActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRaiseExceptionActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the RaiseExceptionAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForRaiseExceptionActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((RaiseExceptionAction)raiseExceptionAction).getActivity());
		cc.append(SetCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditRaiseExceptionActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRaiseExceptionActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the RaiseExceptionAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForRaiseExceptionActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveRaiseExceptionActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRaiseExceptionActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the RaiseExceptionAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.activity);
		

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
	protected void initializeExpectedModelForRaiseExceptionActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((RaiseExceptionAction)raiseExceptionAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditRaiseExceptionActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRaiseExceptionActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the RaiseExceptionAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForRaiseExceptionActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)raiseExceptionAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveRaiseExceptionActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRaiseExceptionActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the RaiseExceptionAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForRaiseExceptionActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((RaiseExceptionAction)raiseExceptionAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditRaiseExceptionActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRaiseExceptionActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the RaiseExceptionAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForRaiseExceptionActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)raiseExceptionAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, raiseExceptionAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveRaiseExceptionActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		raiseExceptionAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (raiseExceptionAction == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRaiseExceptionActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RaiseExceptionAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), raiseExceptionActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(raiseExceptionActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, raiseExceptionActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the RaiseExceptionAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RaiseExceptionAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
