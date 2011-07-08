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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.MessageOccurrenceSpecification;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for MessageOccurrenceSpecification
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class MessageOccurrenceSpecificationTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass messageOccurrenceSpecificationMetaClass = UMLPackage.eINSTANCE.getMessageOccurrenceSpecification();

	/**
	 * The type to edit
	 */
	private EObject messageOccurrenceSpecification;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class enclosingInteraction
	 */
	private Object referenceValueForEnclosingInteraction;

	/**
	 * The reference value for the reference class toBefore
	 */
	private Object referenceValueForToBefore;

	/**
	 * The reference value for the reference class message
	 */
	private Object referenceValueForMessage;

	/**
	 * The reference value for the reference class event
	 */
	private Object referenceValueForEvent;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class toAfter
	 */
	private Object referenceValueForToAfter;

	/**
	 * The reference value for the reference class enclosingOperand
	 */
	private Object referenceValueForEnclosingOperand;

	/**
	 * The reference value for the reference class covered
	 */
	private Object referenceValueForCovered;
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
	private EClass messageMetaClass = UMLPackage.eINSTANCE.getMessage();

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
	protected void initializeExpectedModelForMessageOccurrenceSpecificationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditMessageOccurrenceSpecificationName() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForMessageOccurrenceSpecificationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the MessageOccurrenceSpecification element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForMessageOccurrenceSpecificationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditMessageOccurrenceSpecificationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((MessageOccurrenceSpecification)messageOccurrenceSpecification).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForMessageOccurrenceSpecificationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the MessageOccurrenceSpecification element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForMessageOccurrenceSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((MessageOccurrenceSpecification)messageOccurrenceSpecification).getClientDependency());
		cc.append(AddCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditMessageOccurrenceSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForMessageOccurrenceSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the MessageOccurrenceSpecification element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForMessageOccurrenceSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)messageOccurrenceSpecification.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveMessageOccurrenceSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForMessageOccurrenceSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the MessageOccurrenceSpecification element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForMessageOccurrenceSpecificationCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, lifelineMetaClass);
		referenceValueForCovered = bot.changeReferenceValue(allInstancesOf, ((MessageOccurrenceSpecification)messageOccurrenceSpecification).getCovered());
		cc.append(AddCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), referenceValueForCovered));
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
	public void testEditMessageOccurrenceSpecificationCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForMessageOccurrenceSpecificationCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the MessageOccurrenceSpecification element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered, referenceValueForCovered);

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
	protected void initializeRemoveExpectedModelForMessageOccurrenceSpecificationCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)messageOccurrenceSpecification.eGet(UMLPackage.eINSTANCE.getInteractionFragment_Covered());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), allReferencedInstances.get(0)));
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
	public void testRemoveMessageOccurrenceSpecificationCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForMessageOccurrenceSpecificationCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the MessageOccurrenceSpecification element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForMessageOccurrenceSpecificationEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForEnclosingInteraction = bot.changeReferenceValue(allInstancesOf, ((MessageOccurrenceSpecification)messageOccurrenceSpecification).getEnclosingInteraction());
		cc.append(SetCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), referenceValueForEnclosingInteraction));
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
	public void testEditMessageOccurrenceSpecificationEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForMessageOccurrenceSpecificationEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the MessageOccurrenceSpecification element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingInteraction, allInstancesOf.indexOf(referenceValueForEnclosingInteraction)+1);

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
	protected void initializeRemoveExpectedModelForMessageOccurrenceSpecificationEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		cc.append(SetCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), null));
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
	public void testRemoveMessageOccurrenceSpecificationEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForMessageOccurrenceSpecificationEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the MessageOccurrenceSpecification element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingInteraction);
		

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
	protected void initializeExpectedModelForMessageOccurrenceSpecificationEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		referenceValueForEnclosingOperand = bot.changeReferenceValue(allInstancesOf, ((MessageOccurrenceSpecification)messageOccurrenceSpecification).getEnclosingOperand());
		cc.append(SetCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), referenceValueForEnclosingOperand));
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
	public void testEditMessageOccurrenceSpecificationEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForMessageOccurrenceSpecificationEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the MessageOccurrenceSpecification element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingOperand, allInstancesOf.indexOf(referenceValueForEnclosingOperand)+1);

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
	protected void initializeRemoveExpectedModelForMessageOccurrenceSpecificationEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		cc.append(SetCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), null));
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
	public void testRemoveMessageOccurrenceSpecificationEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForMessageOccurrenceSpecificationEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the MessageOccurrenceSpecification element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingOperand);
		

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
	protected void initializeExpectedModelForMessageOccurrenceSpecificationToBefore() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalOrderingMetaClass);
		referenceValueForToBefore = bot.changeReferenceValue(allInstancesOf, ((MessageOccurrenceSpecification)messageOccurrenceSpecification).getToBefore());
		cc.append(AddCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore(), referenceValueForToBefore));
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
	public void testEditMessageOccurrenceSpecificationToBefore() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForMessageOccurrenceSpecificationToBefore();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the toBefore feature of the MessageOccurrenceSpecification element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore, referenceValueForToBefore);

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
	protected void initializeRemoveExpectedModelForMessageOccurrenceSpecificationToBefore() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)messageOccurrenceSpecification.eGet(UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore(), allReferencedInstances.get(0)));
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
	public void testRemoveMessageOccurrenceSpecificationToBefore() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForMessageOccurrenceSpecificationToBefore();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the toBefore feature of the MessageOccurrenceSpecification element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForMessageOccurrenceSpecificationToAfter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalOrderingMetaClass);
		referenceValueForToAfter = bot.changeReferenceValue(allInstancesOf, ((MessageOccurrenceSpecification)messageOccurrenceSpecification).getToAfter());
		cc.append(AddCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter(), referenceValueForToAfter));
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
	public void testEditMessageOccurrenceSpecificationToAfter() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForMessageOccurrenceSpecificationToAfter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the toAfter feature of the MessageOccurrenceSpecification element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter, referenceValueForToAfter);

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
	protected void initializeRemoveExpectedModelForMessageOccurrenceSpecificationToAfter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)messageOccurrenceSpecification.eGet(UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter(), allReferencedInstances.get(0)));
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
	public void testRemoveMessageOccurrenceSpecificationToAfter() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForMessageOccurrenceSpecificationToAfter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the toAfter feature of the MessageOccurrenceSpecification element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForMessageOccurrenceSpecificationMessage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, messageMetaClass);
		referenceValueForMessage = bot.changeReferenceValue(allInstancesOf, ((MessageOccurrenceSpecification)messageOccurrenceSpecification).getMessage());
		cc.append(SetCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getMessageEnd_Message(), referenceValueForMessage));
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
	public void testEditMessageOccurrenceSpecificationMessage() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForMessageOccurrenceSpecificationMessage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the message feature of the MessageOccurrenceSpecification element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.message, allInstancesOf.indexOf(referenceValueForMessage)+1);

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
	protected void initializeRemoveExpectedModelForMessageOccurrenceSpecificationMessage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, messageMetaClass);
		cc.append(SetCommand.create(editingDomain, messageOccurrenceSpecification, UMLPackage.eINSTANCE.getMessageEnd_Message(), null));
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
	public void testRemoveMessageOccurrenceSpecificationMessage() throws Exception {

		// Import the input model
		initializeInputModel();

		messageOccurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (messageOccurrenceSpecification == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForMessageOccurrenceSpecificationMessage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the MessageOccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), messageOccurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(messageOccurrenceSpecificationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, messageOccurrenceSpecificationMetaClass, firstInstanceOf, null);

		// Change value of the message feature of the MessageOccurrenceSpecification element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.MessageOccurrenceSpecification.Properties.message);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
