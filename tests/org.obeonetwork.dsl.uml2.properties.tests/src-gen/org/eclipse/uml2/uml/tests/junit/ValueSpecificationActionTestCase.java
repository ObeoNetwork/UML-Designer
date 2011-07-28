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
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecificationAction;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ValueSpecificationAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ValueSpecificationActionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass valueSpecificationActionMetaClass = UMLPackage.eINSTANCE.getValueSpecificationAction();

	/**
	 * The type to edit
	 */
	private EObject valueSpecificationAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

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
	protected void initializeExpectedModelForValueSpecificationActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditValueSpecificationActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForValueSpecificationActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ValueSpecificationAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForValueSpecificationActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditValueSpecificationActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ValueSpecificationAction)valueSpecificationAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForValueSpecificationActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ValueSpecificationAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForValueSpecificationActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ValueSpecificationAction)valueSpecificationAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditValueSpecificationActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForValueSpecificationActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ValueSpecificationAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForValueSpecificationActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)valueSpecificationAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveValueSpecificationActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForValueSpecificationActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ValueSpecificationAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForValueSpecificationActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditValueSpecificationActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForValueSpecificationActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ValueSpecificationAction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForValueSpecificationActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ValueSpecificationAction)valueSpecificationAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditValueSpecificationActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForValueSpecificationActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ValueSpecificationAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1);

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
	protected void initializeRemoveExpectedModelForValueSpecificationActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveValueSpecificationActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForValueSpecificationActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ValueSpecificationAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.inStructuredNode);
		

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
	protected void initializeExpectedModelForValueSpecificationActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ValueSpecificationAction)valueSpecificationAction).getActivity());
		cc.append(SetCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditValueSpecificationActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForValueSpecificationActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ValueSpecificationAction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1);

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
	protected void initializeRemoveExpectedModelForValueSpecificationActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveValueSpecificationActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForValueSpecificationActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the activity feature of the ValueSpecificationAction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.activity);
		

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
	protected void initializeExpectedModelForValueSpecificationActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ValueSpecificationAction)valueSpecificationAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditValueSpecificationActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForValueSpecificationActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ValueSpecificationAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.inPartition, referenceValueForInPartition);

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
	protected void initializeRemoveExpectedModelForValueSpecificationActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)valueSpecificationAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveValueSpecificationActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForValueSpecificationActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the inPartition feature of the ValueSpecificationAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForValueSpecificationActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ValueSpecificationAction)valueSpecificationAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditValueSpecificationActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForValueSpecificationActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ValueSpecificationAction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion);

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
	protected void initializeRemoveExpectedModelForValueSpecificationActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)valueSpecificationAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, valueSpecificationAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveValueSpecificationActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		valueSpecificationAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (valueSpecificationAction == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForValueSpecificationActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ValueSpecificationAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), valueSpecificationActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(valueSpecificationActionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, valueSpecificationActionMetaClass, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ValueSpecificationAction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ValueSpecificationAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
