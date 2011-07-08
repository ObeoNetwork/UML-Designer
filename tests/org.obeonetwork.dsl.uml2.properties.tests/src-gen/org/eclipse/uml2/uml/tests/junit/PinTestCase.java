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
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Pin
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class PinTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass pinMetaClass = UMLPackage.eINSTANCE.getPin();

	/**
	 * The type to edit
	 */
	private EObject pin;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class ordering
	 */
	private Object enumValueForOrdering;
	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

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
	 * The reference value for the reference class selection
	 */
	private Object referenceValueForSelection;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class inState
	 */
	private Object referenceValueForInState;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

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
	private EClass behaviorMetaClass = UMLPackage.eINSTANCE.getBehavior();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass stateMetaClass = UMLPackage.eINSTANCE.getState();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityNodeMetaClass = UMLPackage.eINSTANCE.getActivityNode();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass typeMetaClass = UMLPackage.eINSTANCE.getType();

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
	protected void initializeExpectedModelForPinName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditPinName() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Pin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Pin.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPinVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditPinVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Pin)pin).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForPinVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Pin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Pin.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPinClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Pin)pin).getClientDependency());
		cc.append(AddCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditPinClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Pin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Pin.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForPinClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)pin.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemovePinClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPinClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Pin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Pin.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPinIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditPinIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Pin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Pin.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPinInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((Pin)pin).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditPinInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the Pin element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Pin.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForPinInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemovePinInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPinInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the Pin element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Pin.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForPinActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((Pin)pin).getActivity());
		cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditPinActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the Pin element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Pin.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForPinActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemovePinActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPinActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the Pin element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Pin.Properties.activity);
		

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
	protected void initializeExpectedModelForPinInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((Pin)pin).getInPartition());
		cc.append(AddCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditPinInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the Pin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Pin.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForPinInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)pin.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemovePinInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPinInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the Pin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Pin.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPinInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((Pin)pin).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditPinInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the Pin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Pin.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForPinInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)pin.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemovePinInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPinInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the Pin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Pin.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPinOrdering() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getObjectNode_Ordering(), UPDATED_VALUE));
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
	public void testEditPinOrdering() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		enumValueForOrdering = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getObjectNodeOrderingKind(), ((Pin)pin).getOrdering().getLiteral());
		// Create the expected model
		initializeExpectedModelForPinOrdering();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the ordering feature of the Pin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Pin.Properties.ordering, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPinIsControlType() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getObjectNode_IsControlType(), UPDATED_VALUE));
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
	public void testEditPinIsControlType() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinIsControlType();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the isControlType feature of the Pin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Pin.Properties.isControlType, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPinInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForInState = bot.changeReferenceValue(allInstancesOf, ((Pin)pin).getInState());
		cc.append(AddCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getObjectNode_InState(), referenceValueForInState));
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
	public void testEditPinInState() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the Pin element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Pin.Properties.inState, referenceValueForInState);

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
	protected void initializeRemoveExpectedModelForPinInState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)pin.eGet(UMLPackage.eINSTANCE.getObjectNode_InState());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getObjectNode_InState(), allReferencedInstances.get(0)));
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
	public void testRemovePinInState() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPinInState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the inState feature of the Pin element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Pin.Properties.inState, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPinIsOrdered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), UPDATED_VALUE));
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
	public void testEditPinIsOrdered() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinIsOrdered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the isOrdered feature of the Pin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Pin.Properties.isOrdered, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPinIsUnique() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), UPDATED_VALUE));
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
	public void testEditPinIsUnique() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinIsUnique();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the isUnique feature of the Pin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Pin.Properties.isUnique, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPinIsControl() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject pin = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getPin_IsControl(), UPDATED_VALUE));
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
	public void testEditPinIsControl() throws Exception {

		// Import the input model
		initializeInputModel();

		pin = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (pin == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPinIsControl();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Pin element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), pinMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(pinMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, pinMetaClass, firstInstanceOf, null);

		// Change value of the isControl feature of the Pin element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Pin.Properties.isControl, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






























}
