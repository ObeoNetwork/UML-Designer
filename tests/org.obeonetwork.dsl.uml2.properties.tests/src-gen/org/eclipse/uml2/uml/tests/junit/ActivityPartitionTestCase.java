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
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ActivityPartition
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ActivityPartitionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass activityPartitionMetaClass = UMLPackage.eINSTANCE.getActivityPartition();

	/**
	 * The type to edit
	 */
	private EObject activityPartition;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class node
	 */
	private Object referenceValueForNode;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class edge
	 */
	private Object referenceValueForEdge;

	/**
	 * The reference value for the reference class superPartition
	 */
	private Object referenceValueForSuperPartition;

	/**
	 * The reference value for the reference class represents
	 */
	private Object referenceValueForRepresents;

	/**
	 * The reference value for the reference class inActivity
	 */
	private Object referenceValueForInActivity;
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
	private EClass activityEdgeMetaClass = UMLPackage.eINSTANCE.getActivityEdge();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass elementMetaClass = UMLPackage.eINSTANCE.getElement();

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
	protected void initializeExpectedModelForActivityPartitionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, activityPartition, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditActivityPartitionName() throws Exception {

		// Import the input model
		initializeInputModel();

		activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityPartitionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityPartition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityPartitionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ActivityPartition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActivityPartition.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActivityPartitionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, activityPartition, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditActivityPartitionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ActivityPartition)activityPartition).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForActivityPartitionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityPartition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityPartitionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ActivityPartition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActivityPartition.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActivityPartitionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ActivityPartition)activityPartition).getClientDependency());
		cc.append(AddCommand.create(editingDomain, activityPartition, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditActivityPartitionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityPartitionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityPartition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityPartitionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ActivityPartition element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActivityPartition.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForActivityPartitionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)activityPartition.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, activityPartition, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveActivityPartitionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActivityPartitionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityPartition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityPartitionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ActivityPartition element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ActivityPartition.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForActivityPartitionInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForInActivity = bot.changeReferenceValue(allInstancesOf, ((ActivityPartition)activityPartition).getInActivity());
		cc.append(SetCommand.create(editingDomain, activityPartition, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), referenceValueForInActivity));
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
	public void testEditActivityPartitionInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityPartitionInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityPartition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityPartitionMetaClass, firstInstanceOf, null);

		// Change value of the inActivity feature of the ActivityPartition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActivityPartition.Properties.inActivity, allInstancesOf.indexOf(referenceValueForInActivity)+1);

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
	protected void initializeRemoveExpectedModelForActivityPartitionInActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, activityPartition, UMLPackage.eINSTANCE.getActivityGroup_InActivity(), null));
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
	public void testRemoveActivityPartitionInActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActivityPartitionInActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityPartition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityPartitionMetaClass, firstInstanceOf, null);

		// Change value of the inActivity feature of the ActivityPartition element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActivityPartition.Properties.inActivity);
		

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
	protected void initializeExpectedModelForActivityPartitionIsDimension() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, activityPartition, UMLPackage.eINSTANCE.getActivityPartition_IsDimension(), UPDATED_VALUE));
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
	public void testEditActivityPartitionIsDimension() throws Exception {

		// Import the input model
		initializeInputModel();

		activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityPartitionIsDimension();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityPartition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityPartitionMetaClass, firstInstanceOf, null);

		// Change value of the isDimension feature of the ActivityPartition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActivityPartition.Properties.isDimension, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActivityPartitionIsExternal() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, activityPartition, UMLPackage.eINSTANCE.getActivityPartition_IsExternal(), UPDATED_VALUE));
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
	public void testEditActivityPartitionIsExternal() throws Exception {

		// Import the input model
		initializeInputModel();

		activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityPartitionIsExternal();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityPartition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityPartitionMetaClass, firstInstanceOf, null);

		// Change value of the isExternal feature of the ActivityPartition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ActivityPartition.Properties.isExternal, UPDATED_VALUE);

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
	protected void initializeExpectedModelForActivityPartitionSuperPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForSuperPartition = bot.changeReferenceValue(allInstancesOf, ((ActivityPartition)activityPartition).getSuperPartition());
		cc.append(SetCommand.create(editingDomain, activityPartition, UMLPackage.eINSTANCE.getActivityPartition_SuperPartition(), referenceValueForSuperPartition));
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
	public void testEditActivityPartitionSuperPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActivityPartitionSuperPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityPartition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityPartitionMetaClass, firstInstanceOf, null);

		// Change value of the superPartition feature of the ActivityPartition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActivityPartition.Properties.superPartition, allInstancesOf.indexOf(referenceValueForSuperPartition)+1);

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
	protected void initializeRemoveExpectedModelForActivityPartitionSuperPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		cc.append(SetCommand.create(editingDomain, activityPartition, UMLPackage.eINSTANCE.getActivityPartition_SuperPartition(), null));
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
	public void testRemoveActivityPartitionSuperPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		activityPartition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (activityPartition == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActivityPartitionSuperPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ActivityPartition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), activityPartitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(activityPartitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, activityPartitionMetaClass, firstInstanceOf, null);

		// Change value of the superPartition feature of the ActivityPartition element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ActivityPartition.Properties.superPartition);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
