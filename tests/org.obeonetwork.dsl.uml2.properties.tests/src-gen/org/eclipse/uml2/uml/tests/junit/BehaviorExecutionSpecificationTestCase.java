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
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for BehaviorExecutionSpecification
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class BehaviorExecutionSpecificationTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass behaviorExecutionSpecificationMetaClass = UMLPackage.eINSTANCE.getBehaviorExecutionSpecification();

	/**
	 * The type to edit
	 */
	private EObject behaviorExecutionSpecification;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class behavior
	 */
	private Object referenceValueForBehavior;

	/**
	 * The reference value for the reference class covered
	 */
	private Object referenceValueForCovered;

	/**
	 * The reference value for the reference class enclosingOperand
	 */
	private Object referenceValueForEnclosingOperand;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class finish
	 */
	private Object referenceValueForFinish;

	/**
	 * The reference value for the reference class enclosingInteraction
	 */
	private Object referenceValueForEnclosingInteraction;

	/**
	 * The reference value for the reference class start
	 */
	private Object referenceValueForStart;
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
	private EClass behaviorMetaClass = UMLPackage.eINSTANCE.getBehavior();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass occurrenceSpecificationMetaClass = UMLPackage.eINSTANCE.getOccurrenceSpecification();

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
	protected void initializeExpectedModelForBehaviorExecutionSpecificationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditBehaviorExecutionSpecificationName() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBehaviorExecutionSpecificationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the BehaviorExecutionSpecification element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForBehaviorExecutionSpecificationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditBehaviorExecutionSpecificationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((BehaviorExecutionSpecification)behaviorExecutionSpecification).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForBehaviorExecutionSpecificationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the BehaviorExecutionSpecification element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForBehaviorExecutionSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((BehaviorExecutionSpecification)behaviorExecutionSpecification).getClientDependency());
		cc.append(AddCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditBehaviorExecutionSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBehaviorExecutionSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the BehaviorExecutionSpecification element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForBehaviorExecutionSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)behaviorExecutionSpecification.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveBehaviorExecutionSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForBehaviorExecutionSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the BehaviorExecutionSpecification element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForBehaviorExecutionSpecificationCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, lifelineMetaClass);
		referenceValueForCovered = bot.changeReferenceValue(allInstancesOf, ((BehaviorExecutionSpecification)behaviorExecutionSpecification).getCovered());
		cc.append(AddCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), referenceValueForCovered));
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
	public void testEditBehaviorExecutionSpecificationCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBehaviorExecutionSpecificationCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the BehaviorExecutionSpecification element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.covered, referenceValueForCovered);

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
	protected void initializeRemoveExpectedModelForBehaviorExecutionSpecificationCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)behaviorExecutionSpecification.eGet(UMLPackage.eINSTANCE.getInteractionFragment_Covered());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), allReferencedInstances.get(0)));
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
	public void testRemoveBehaviorExecutionSpecificationCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForBehaviorExecutionSpecificationCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the BehaviorExecutionSpecification element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.covered, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForBehaviorExecutionSpecificationEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForEnclosingInteraction = bot.changeReferenceValue(allInstancesOf, ((BehaviorExecutionSpecification)behaviorExecutionSpecification).getEnclosingInteraction());
		cc.append(SetCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), referenceValueForEnclosingInteraction));
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
	public void testEditBehaviorExecutionSpecificationEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBehaviorExecutionSpecificationEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the BehaviorExecutionSpecification element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.enclosingInteraction, allInstancesOf.indexOf(referenceValueForEnclosingInteraction)+1);

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
	protected void initializeRemoveExpectedModelForBehaviorExecutionSpecificationEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		cc.append(SetCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), null));
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
	public void testRemoveBehaviorExecutionSpecificationEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForBehaviorExecutionSpecificationEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the BehaviorExecutionSpecification element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.enclosingInteraction);
		

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
	protected void initializeExpectedModelForBehaviorExecutionSpecificationEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		referenceValueForEnclosingOperand = bot.changeReferenceValue(allInstancesOf, ((BehaviorExecutionSpecification)behaviorExecutionSpecification).getEnclosingOperand());
		cc.append(SetCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), referenceValueForEnclosingOperand));
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
	public void testEditBehaviorExecutionSpecificationEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBehaviorExecutionSpecificationEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the BehaviorExecutionSpecification element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.enclosingOperand, allInstancesOf.indexOf(referenceValueForEnclosingOperand)+1);

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
	protected void initializeRemoveExpectedModelForBehaviorExecutionSpecificationEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		cc.append(SetCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), null));
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
	public void testRemoveBehaviorExecutionSpecificationEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForBehaviorExecutionSpecificationEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the BehaviorExecutionSpecification element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.enclosingOperand);
		

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
	protected void initializeExpectedModelForBehaviorExecutionSpecificationStart() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, occurrenceSpecificationMetaClass);
		referenceValueForStart = bot.changeReferenceValue(allInstancesOf, ((BehaviorExecutionSpecification)behaviorExecutionSpecification).getStart());
		cc.append(SetCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getExecutionSpecification_Start(), referenceValueForStart));
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
	public void testEditBehaviorExecutionSpecificationStart() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBehaviorExecutionSpecificationStart();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the start feature of the BehaviorExecutionSpecification element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.start, allInstancesOf.indexOf(referenceValueForStart));

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
	protected void initializeExpectedModelForBehaviorExecutionSpecificationFinish() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, occurrenceSpecificationMetaClass);
		referenceValueForFinish = bot.changeReferenceValue(allInstancesOf, ((BehaviorExecutionSpecification)behaviorExecutionSpecification).getFinish());
		cc.append(SetCommand.create(editingDomain, behaviorExecutionSpecification, UMLPackage.eINSTANCE.getExecutionSpecification_Finish(), referenceValueForFinish));
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
	public void testEditBehaviorExecutionSpecificationFinish() throws Exception {

		// Import the input model
		initializeInputModel();

		behaviorExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (behaviorExecutionSpecification == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForBehaviorExecutionSpecificationFinish();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the BehaviorExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), behaviorExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(behaviorExecutionSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, behaviorExecutionSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the finish feature of the BehaviorExecutionSpecification element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.BehaviorExecutionSpecification.Properties.finish, allInstancesOf.indexOf(referenceValueForFinish));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
