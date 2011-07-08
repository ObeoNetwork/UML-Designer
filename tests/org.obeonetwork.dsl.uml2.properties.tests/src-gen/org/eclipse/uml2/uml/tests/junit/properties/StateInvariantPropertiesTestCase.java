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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.StateInvariant;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for StateInvariant
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class StateInvariantPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass stateInvariantMetaClass = UMLPackage.eINSTANCE.getStateInvariant();

	/**
	 * The type to edit
	 */
	private EObject stateInvariant;

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
	protected void initializeExpectedModelForStateInvariantName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, stateInvariant, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditStateInvariantName() throws Exception {

		// Import the input model
		initializeInputModel();

		stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateInvariantName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StateInvariant element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the StateInvariant element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.StateInvariant.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStateInvariantVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, stateInvariant, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditStateInvariantVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((StateInvariant)stateInvariant).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForStateInvariantVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StateInvariant element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the StateInvariant element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.StateInvariant.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStateInvariantClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((StateInvariant)stateInvariant).getClientDependency());
		cc.append(AddCommand.create(editingDomain, stateInvariant, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditStateInvariantClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateInvariantClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StateInvariant element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the StateInvariant element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StateInvariant.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStateInvariantClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)stateInvariant.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, stateInvariant, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveStateInvariantClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateInvariantClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StateInvariant element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the StateInvariant element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StateInvariant.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStateInvariantCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, lifelineMetaClass);
		referenceValueForCovered = bot.changeReferenceValue(allInstancesOf, ((StateInvariant)stateInvariant).getCovered());
		cc.append(AddCommand.create(editingDomain, stateInvariant, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), referenceValueForCovered));
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
	public void testEditStateInvariantCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateInvariantCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StateInvariant element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the covered feature of the StateInvariant element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StateInvariant.Properties.covered, referenceValueForCovered, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStateInvariantCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)stateInvariant.eGet(UMLPackage.eINSTANCE.getInteractionFragment_Covered());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, stateInvariant, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), allReferencedInstances.get(0)));
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
	public void testRemoveStateInvariantCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateInvariantCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StateInvariant element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the covered feature of the StateInvariant element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StateInvariant.Properties.covered, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStateInvariantEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForEnclosingInteraction = bot.changeReferenceValue(allInstancesOf, ((StateInvariant)stateInvariant).getEnclosingInteraction());
		cc.append(SetCommand.create(editingDomain, stateInvariant, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), referenceValueForEnclosingInteraction));
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
	public void testEditStateInvariantEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateInvariantEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StateInvariant element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the StateInvariant element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StateInvariant.Properties.enclosingInteraction, allInstancesOf.indexOf(referenceValueForEnclosingInteraction)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStateInvariantEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		cc.append(SetCommand.create(editingDomain, stateInvariant, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), null));
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
	public void testRemoveStateInvariantEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateInvariantEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StateInvariant element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the StateInvariant element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StateInvariant.Properties.enclosingInteraction, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForStateInvariantEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		referenceValueForEnclosingOperand = bot.changeReferenceValue(allInstancesOf, ((StateInvariant)stateInvariant).getEnclosingOperand());
		cc.append(SetCommand.create(editingDomain, stateInvariant, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), referenceValueForEnclosingOperand));
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
	public void testEditStateInvariantEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateInvariantEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StateInvariant element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the StateInvariant element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StateInvariant.Properties.enclosingOperand, allInstancesOf.indexOf(referenceValueForEnclosingOperand)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStateInvariantEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		cc.append(SetCommand.create(editingDomain, stateInvariant, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), null));
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
	public void testRemoveStateInvariantEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		stateInvariant = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (stateInvariant == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateInvariantEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StateInvariant element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateInvariantMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateInvariantMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the StateInvariant element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StateInvariant.Properties.enclosingOperand, bot.selectNode(modelEditor, firstInstanceOf));
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
