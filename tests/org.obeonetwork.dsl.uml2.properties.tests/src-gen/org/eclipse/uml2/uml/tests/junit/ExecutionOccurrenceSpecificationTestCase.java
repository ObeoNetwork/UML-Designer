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
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ExecutionOccurrenceSpecification
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ExecutionOccurrenceSpecificationTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass executionOccurrenceSpecificationMetaClass = UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification();

	/**
	 * The type to edit
	 */
	private EObject executionOccurrenceSpecification;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class execution
	 */
	private Object referenceValueForExecution;

	/**
	 * The reference value for the reference class enclosingInteraction
	 */
	private Object referenceValueForEnclosingInteraction;

	/**
	 * The reference value for the reference class toBefore
	 */
	private Object referenceValueForToBefore;

	/**
	 * The reference value for the reference class enclosingOperand
	 */
	private Object referenceValueForEnclosingOperand;

	/**
	 * The reference value for the reference class event
	 */
	private Object referenceValueForEvent;

	/**
	 * The reference value for the reference class toAfter
	 */
	private Object referenceValueForToAfter;

	/**
	 * The reference value for the reference class covered
	 */
	private Object referenceValueForCovered;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass interactionOperandMetaClass = UMLPackage.eINSTANCE.getInteractionOperand();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass interactionMetaClass = UMLPackage.eINSTANCE.getInteraction();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass executionSpecificationMetaClass = UMLPackage.eINSTANCE.getExecutionSpecification();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass eventMetaClass = UMLPackage.eINSTANCE.getEvent();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass generalOrderingMetaClass = UMLPackage.eINSTANCE.getGeneralOrdering();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass lifelineMetaClass = UMLPackage.eINSTANCE.getLifeline();

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
	protected void initializeExpectedModelForExecutionOccurrenceSpecificationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditExecutionOccurrenceSpecificationName() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionOccurrenceSpecificationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ExecutionOccurrenceSpecification element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExecutionOccurrenceSpecificationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditExecutionOccurrenceSpecificationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ExecutionOccurrenceSpecification)executionOccurrenceSpecification).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForExecutionOccurrenceSpecificationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ExecutionOccurrenceSpecification element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExecutionOccurrenceSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ExecutionOccurrenceSpecification)executionOccurrenceSpecification).getClientDependency());
		cc.append(AddCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditExecutionOccurrenceSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionOccurrenceSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExecutionOccurrenceSpecification element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForExecutionOccurrenceSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)executionOccurrenceSpecification.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveExecutionOccurrenceSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionOccurrenceSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExecutionOccurrenceSpecification element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExecutionOccurrenceSpecificationCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, lifelineMetaClass);
		referenceValueForCovered = bot.changeReferenceValue(allInstancesOf, ((ExecutionOccurrenceSpecification)executionOccurrenceSpecification).getCovered());
		cc.append(AddCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), referenceValueForCovered));
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
	public void testEditExecutionOccurrenceSpecificationCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionOccurrenceSpecificationCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the ExecutionOccurrenceSpecification element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered, referenceValueForCovered);

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
	protected void initializeRemoveExpectedModelForExecutionOccurrenceSpecificationCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)executionOccurrenceSpecification.eGet(UMLPackage.eINSTANCE.getInteractionFragment_Covered());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), allReferencedInstances.get(0)));
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
	public void testRemoveExecutionOccurrenceSpecificationCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionOccurrenceSpecificationCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the ExecutionOccurrenceSpecification element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExecutionOccurrenceSpecificationEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForEnclosingInteraction = bot.changeReferenceValue(allInstancesOf, ((ExecutionOccurrenceSpecification)executionOccurrenceSpecification).getEnclosingInteraction());
		cc.append(SetCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), referenceValueForEnclosingInteraction));
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
	public void testEditExecutionOccurrenceSpecificationEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionOccurrenceSpecificationEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the ExecutionOccurrenceSpecification element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction, allInstancesOf.indexOf(referenceValueForEnclosingInteraction)+1);

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
	protected void initializeRemoveExpectedModelForExecutionOccurrenceSpecificationEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		cc.append(SetCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), null));
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
	public void testRemoveExecutionOccurrenceSpecificationEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionOccurrenceSpecificationEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the ExecutionOccurrenceSpecification element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction);
		

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
	protected void initializeExpectedModelForExecutionOccurrenceSpecificationEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		referenceValueForEnclosingOperand = bot.changeReferenceValue(allInstancesOf, ((ExecutionOccurrenceSpecification)executionOccurrenceSpecification).getEnclosingOperand());
		cc.append(SetCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), referenceValueForEnclosingOperand));
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
	public void testEditExecutionOccurrenceSpecificationEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionOccurrenceSpecificationEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the ExecutionOccurrenceSpecification element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand, allInstancesOf.indexOf(referenceValueForEnclosingOperand)+1);

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
	protected void initializeRemoveExpectedModelForExecutionOccurrenceSpecificationEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		cc.append(SetCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), null));
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
	public void testRemoveExecutionOccurrenceSpecificationEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionOccurrenceSpecificationEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the ExecutionOccurrenceSpecification element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand);
		

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
	protected void initializeExpectedModelForExecutionOccurrenceSpecificationToBefore() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalOrderingMetaClass);
		referenceValueForToBefore = bot.changeReferenceValue(allInstancesOf, ((ExecutionOccurrenceSpecification)executionOccurrenceSpecification).getToBefore());
		cc.append(AddCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore(), referenceValueForToBefore));
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
	public void testEditExecutionOccurrenceSpecificationToBefore() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionOccurrenceSpecificationToBefore();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the toBefore feature of the ExecutionOccurrenceSpecification element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore, referenceValueForToBefore);

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
	protected void initializeRemoveExpectedModelForExecutionOccurrenceSpecificationToBefore() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)executionOccurrenceSpecification.eGet(UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore(), allReferencedInstances.get(0)));
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
	public void testRemoveExecutionOccurrenceSpecificationToBefore() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionOccurrenceSpecificationToBefore();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the toBefore feature of the ExecutionOccurrenceSpecification element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExecutionOccurrenceSpecificationToAfter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalOrderingMetaClass);
		referenceValueForToAfter = bot.changeReferenceValue(allInstancesOf, ((ExecutionOccurrenceSpecification)executionOccurrenceSpecification).getToAfter());
		cc.append(AddCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter(), referenceValueForToAfter));
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
	public void testEditExecutionOccurrenceSpecificationToAfter() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionOccurrenceSpecificationToAfter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the toAfter feature of the ExecutionOccurrenceSpecification element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter, referenceValueForToAfter);

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
	protected void initializeRemoveExpectedModelForExecutionOccurrenceSpecificationToAfter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)executionOccurrenceSpecification.eGet(UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, executionOccurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter(), allReferencedInstances.get(0)));
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
	public void testRemoveExecutionOccurrenceSpecificationToAfter() throws Exception {

		// Import the input model
		initializeInputModel();

		executionOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (executionOccurrenceSpecification == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionOccurrenceSpecificationToAfter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExecutionOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, executionOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the toAfter feature of the ExecutionOccurrenceSpecification element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
