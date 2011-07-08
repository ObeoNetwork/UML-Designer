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
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for InteractionOperand
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class InteractionOperandTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass interactionOperandMetaClass = UMLPackage.eINSTANCE.getInteractionOperand();

	/**
	 * The type to edit
	 */
	private EObject interactionOperand;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class enclosingOperand
	 */
	private Object referenceValueForEnclosingOperand;

	/**
	 * The reference value for the reference class enclosingInteraction
	 */
	private Object referenceValueForEnclosingInteraction;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class covered
	 */
	private Object referenceValueForCovered;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass interactionMetaClass = UMLPackage.eINSTANCE.getInteraction();

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
	protected void initializeExpectedModelForInteractionOperandName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interactionOperand, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditInteractionOperandName() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionOperandName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionOperand element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionOperandMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the InteractionOperand element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InteractionOperand.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInteractionOperandVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interactionOperand, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditInteractionOperandVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((InteractionOperand)interactionOperand).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForInteractionOperandVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionOperand element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionOperandMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the InteractionOperand element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InteractionOperand.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInteractionOperandClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((InteractionOperand)interactionOperand).getClientDependency());
		cc.append(AddCommand.create(editingDomain, interactionOperand, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditInteractionOperandClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionOperandClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionOperand element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionOperandMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InteractionOperand element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InteractionOperand.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForInteractionOperandClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)interactionOperand.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, interactionOperand, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveInteractionOperandClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInteractionOperandClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionOperand element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionOperandMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InteractionOperand element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InteractionOperand.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInteractionOperandCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, lifelineMetaClass);
		referenceValueForCovered = bot.changeReferenceValue(allInstancesOf, ((InteractionOperand)interactionOperand).getCovered());
		cc.append(AddCommand.create(editingDomain, interactionOperand, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), referenceValueForCovered));
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
	public void testEditInteractionOperandCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionOperandCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionOperand element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionOperandMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the InteractionOperand element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InteractionOperand.Properties.covered, referenceValueForCovered);

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
	protected void initializeRemoveExpectedModelForInteractionOperandCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)interactionOperand.eGet(UMLPackage.eINSTANCE.getInteractionFragment_Covered());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, interactionOperand, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), allReferencedInstances.get(0)));
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
	public void testRemoveInteractionOperandCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInteractionOperandCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionOperand element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionOperandMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the InteractionOperand element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InteractionOperand.Properties.covered, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInteractionOperandEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForEnclosingInteraction = bot.changeReferenceValue(allInstancesOf, ((InteractionOperand)interactionOperand).getEnclosingInteraction());
		cc.append(SetCommand.create(editingDomain, interactionOperand, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), referenceValueForEnclosingInteraction));
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
	public void testEditInteractionOperandEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionOperandEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionOperand element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionOperandMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the InteractionOperand element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionOperand.Properties.enclosingInteraction, allInstancesOf.indexOf(referenceValueForEnclosingInteraction)+1);

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
	protected void initializeRemoveExpectedModelForInteractionOperandEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		cc.append(SetCommand.create(editingDomain, interactionOperand, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), null));
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
	public void testRemoveInteractionOperandEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInteractionOperandEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionOperand element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionOperandMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the InteractionOperand element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionOperand.Properties.enclosingInteraction);
		

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
	protected void initializeExpectedModelForInteractionOperandEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		referenceValueForEnclosingOperand = bot.changeReferenceValue(allInstancesOf, ((InteractionOperand)interactionOperand).getEnclosingOperand());
		cc.append(SetCommand.create(editingDomain, interactionOperand, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), referenceValueForEnclosingOperand));
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
	public void testEditInteractionOperandEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionOperandEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionOperand element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionOperandMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the InteractionOperand element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionOperand.Properties.enclosingOperand, allInstancesOf.indexOf(referenceValueForEnclosingOperand)+1);

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
	protected void initializeRemoveExpectedModelForInteractionOperandEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		cc.append(SetCommand.create(editingDomain, interactionOperand, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), null));
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
	public void testRemoveInteractionOperandEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionOperand = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (interactionOperand == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInteractionOperandEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionOperand element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionOperandMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionOperandMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionOperandMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the InteractionOperand element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionOperand.Properties.enclosingOperand);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
