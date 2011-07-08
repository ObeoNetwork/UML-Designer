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
package org.eclipse.uml2.uml.tests.junit.properties;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.OccurrenceSpecification;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for OccurrenceSpecification
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class OccurrenceSpecificationPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass occurrenceSpecificationMetaClass = UMLPackage.eINSTANCE.getOccurrenceSpecification();

	/**
	 * The type to edit
	 */
	private EObject occurrenceSpecification;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class toAfter
	 */
	private Object referenceValueForToAfter;

	/**
	 * The reference value for the reference class enclosingInteraction
	 */
	private Object referenceValueForEnclosingInteraction;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class event
	 */
	private Object referenceValueForEvent;

	/**
	 * The reference value for the reference class toBefore
	 */
	private Object referenceValueForToBefore;

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
	protected void initializeExpectedModelForOccurrenceSpecificationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditOccurrenceSpecificationName() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOccurrenceSpecificationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the OccurrenceSpecification element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOccurrenceSpecificationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditOccurrenceSpecificationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((OccurrenceSpecification)occurrenceSpecification).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForOccurrenceSpecificationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the OccurrenceSpecification element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOccurrenceSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((OccurrenceSpecification)occurrenceSpecification).getClientDependency());
		cc.append(AddCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditOccurrenceSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOccurrenceSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the OccurrenceSpecification element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOccurrenceSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)occurrenceSpecification.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveOccurrenceSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOccurrenceSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the OccurrenceSpecification element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOccurrenceSpecificationCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, lifelineMetaClass);
		referenceValueForCovered = bot.changeReferenceValue(allInstancesOf, ((OccurrenceSpecification)occurrenceSpecification).getCovered());
		cc.append(AddCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), referenceValueForCovered));
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
	public void testEditOccurrenceSpecificationCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOccurrenceSpecificationCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the covered feature of the OccurrenceSpecification element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.covered, referenceValueForCovered, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOccurrenceSpecificationCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)occurrenceSpecification.eGet(UMLPackage.eINSTANCE.getInteractionFragment_Covered());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), allReferencedInstances.get(0)));
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
	public void testRemoveOccurrenceSpecificationCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOccurrenceSpecificationCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the covered feature of the OccurrenceSpecification element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.covered, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOccurrenceSpecificationEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForEnclosingInteraction = bot.changeReferenceValue(allInstancesOf, ((OccurrenceSpecification)occurrenceSpecification).getEnclosingInteraction());
		cc.append(SetCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), referenceValueForEnclosingInteraction));
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
	public void testEditOccurrenceSpecificationEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOccurrenceSpecificationEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the OccurrenceSpecification element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.enclosingInteraction, allInstancesOf.indexOf(referenceValueForEnclosingInteraction)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOccurrenceSpecificationEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		cc.append(SetCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), null));
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
	public void testRemoveOccurrenceSpecificationEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOccurrenceSpecificationEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the OccurrenceSpecification element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.enclosingInteraction, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForOccurrenceSpecificationEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		referenceValueForEnclosingOperand = bot.changeReferenceValue(allInstancesOf, ((OccurrenceSpecification)occurrenceSpecification).getEnclosingOperand());
		cc.append(SetCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), referenceValueForEnclosingOperand));
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
	public void testEditOccurrenceSpecificationEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOccurrenceSpecificationEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the OccurrenceSpecification element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.enclosingOperand, allInstancesOf.indexOf(referenceValueForEnclosingOperand)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOccurrenceSpecificationEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		cc.append(SetCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), null));
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
	public void testRemoveOccurrenceSpecificationEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOccurrenceSpecificationEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the OccurrenceSpecification element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.enclosingOperand, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForOccurrenceSpecificationToBefore() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalOrderingMetaClass);
		referenceValueForToBefore = bot.changeReferenceValue(allInstancesOf, ((OccurrenceSpecification)occurrenceSpecification).getToBefore());
		cc.append(AddCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore(), referenceValueForToBefore));
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
	public void testEditOccurrenceSpecificationToBefore() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOccurrenceSpecificationToBefore();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the toBefore feature of the OccurrenceSpecification element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.toBefore, referenceValueForToBefore, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOccurrenceSpecificationToBefore() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)occurrenceSpecification.eGet(UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore(), allReferencedInstances.get(0)));
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
	public void testRemoveOccurrenceSpecificationToBefore() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOccurrenceSpecificationToBefore();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the toBefore feature of the OccurrenceSpecification element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.toBefore, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOccurrenceSpecificationToAfter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalOrderingMetaClass);
		referenceValueForToAfter = bot.changeReferenceValue(allInstancesOf, ((OccurrenceSpecification)occurrenceSpecification).getToAfter());
		cc.append(AddCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter(), referenceValueForToAfter));
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
	public void testEditOccurrenceSpecificationToAfter() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOccurrenceSpecificationToAfter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the toAfter feature of the OccurrenceSpecification element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.toAfter, referenceValueForToAfter, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOccurrenceSpecificationToAfter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)occurrenceSpecification.eGet(UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, occurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter(), allReferencedInstances.get(0)));
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
	public void testRemoveOccurrenceSpecificationToAfter() throws Exception {

		// Import the input model
		initializeInputModel();

		occurrenceSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (occurrenceSpecification == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOccurrenceSpecificationToAfter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OccurrenceSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), occurrenceSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(occurrenceSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the toAfter feature of the OccurrenceSpecification element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OccurrenceSpecification.Properties.toAfter, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
