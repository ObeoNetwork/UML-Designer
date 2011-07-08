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
import org.eclipse.uml2.uml.InteractionUse;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for InteractionUse
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class InteractionUseTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass interactionUseMetaClass = UMLPackage.eINSTANCE.getInteractionUse();

	/**
	 * The type to edit
	 */
	private EObject interactionUse;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
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
	 * The reference value for the reference class refersTo
	 */
	private Object referenceValueForRefersTo;

	/**
	 * The reference value for the reference class enclosingOperand
	 */
	private Object referenceValueForEnclosingOperand;
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
	protected void initializeExpectedModelForInteractionUseName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interactionUse, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditInteractionUseName() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionUseName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionUseMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the InteractionUse element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InteractionUse.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInteractionUseVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interactionUse, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditInteractionUseVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((InteractionUse)interactionUse).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForInteractionUseVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionUseMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the InteractionUse element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InteractionUse.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInteractionUseClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((InteractionUse)interactionUse).getClientDependency());
		cc.append(AddCommand.create(editingDomain, interactionUse, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditInteractionUseClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionUseClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionUseMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InteractionUse element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InteractionUse.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForInteractionUseClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)interactionUse.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, interactionUse, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveInteractionUseClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInteractionUseClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionUseMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InteractionUse element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InteractionUse.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInteractionUseCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, lifelineMetaClass);
		referenceValueForCovered = bot.changeReferenceValue(allInstancesOf, ((InteractionUse)interactionUse).getCovered());
		cc.append(AddCommand.create(editingDomain, interactionUse, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), referenceValueForCovered));
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
	public void testEditInteractionUseCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionUseCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionUseMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the InteractionUse element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InteractionUse.Properties.covered, referenceValueForCovered);

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
	protected void initializeRemoveExpectedModelForInteractionUseCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)interactionUse.eGet(UMLPackage.eINSTANCE.getInteractionFragment_Covered());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, interactionUse, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), allReferencedInstances.get(0)));
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
	public void testRemoveInteractionUseCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInteractionUseCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionUseMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the InteractionUse element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InteractionUse.Properties.covered, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInteractionUseEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForEnclosingInteraction = bot.changeReferenceValue(allInstancesOf, ((InteractionUse)interactionUse).getEnclosingInteraction());
		cc.append(SetCommand.create(editingDomain, interactionUse, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), referenceValueForEnclosingInteraction));
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
	public void testEditInteractionUseEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionUseEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionUseMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the InteractionUse element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionUse.Properties.enclosingInteraction, allInstancesOf.indexOf(referenceValueForEnclosingInteraction)+1);

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
	protected void initializeRemoveExpectedModelForInteractionUseEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		cc.append(SetCommand.create(editingDomain, interactionUse, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), null));
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
	public void testRemoveInteractionUseEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInteractionUseEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionUseMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the InteractionUse element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionUse.Properties.enclosingInteraction);
		

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
	protected void initializeExpectedModelForInteractionUseEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		referenceValueForEnclosingOperand = bot.changeReferenceValue(allInstancesOf, ((InteractionUse)interactionUse).getEnclosingOperand());
		cc.append(SetCommand.create(editingDomain, interactionUse, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), referenceValueForEnclosingOperand));
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
	public void testEditInteractionUseEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionUseEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionUseMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the InteractionUse element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionUse.Properties.enclosingOperand, allInstancesOf.indexOf(referenceValueForEnclosingOperand)+1);

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
	protected void initializeRemoveExpectedModelForInteractionUseEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		cc.append(SetCommand.create(editingDomain, interactionUse, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), null));
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
	public void testRemoveInteractionUseEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInteractionUseEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionUseMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the InteractionUse element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionUse.Properties.enclosingOperand);
		

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
	protected void initializeExpectedModelForInteractionUseRefersTo() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForRefersTo = bot.changeReferenceValue(allInstancesOf, ((InteractionUse)interactionUse).getRefersTo());
		cc.append(SetCommand.create(editingDomain, interactionUse, UMLPackage.eINSTANCE.getInteractionUse_RefersTo(), referenceValueForRefersTo));
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
	public void testEditInteractionUseRefersTo() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (interactionUse == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionUseRefersTo();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionUseMetaClass, firstInstanceOf, null);

		// Change value of the refersTo feature of the InteractionUse element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionUse.Properties.refersTo, allInstancesOf.indexOf(referenceValueForRefersTo));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
