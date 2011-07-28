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
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.uml2.uml.ActionExecutionSpecification;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ActionExecutionSpecification
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ActionExecutionSpecificationPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass actionExecutionSpecificationMetaClass = UMLPackage.eINSTANCE.getActionExecutionSpecification();

	/**
	 * The type to edit
	 */
	private EObject actionExecutionSpecification;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class finish
	 */
	private Object referenceValueForFinish;

	/**
	 * The reference value for the reference class covered
	 */
	private Object referenceValueForCovered;

	/**
	 * The reference value for the reference class enclosingInteraction
	 */
	private Object referenceValueForEnclosingInteraction;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class enclosingOperand
	 */
	private Object referenceValueForEnclosingOperand;

	/**
	 * The reference value for the reference class start
	 */
	private Object referenceValueForStart;

	/**
	 * The reference value for the reference class action
	 */
	private Object referenceValueForAction;
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
	private EClass actionMetaClass = UMLPackage.eINSTANCE.getAction();

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
	protected void initializeExpectedModelForActionExecutionSpecificationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditActionExecutionSpecificationName() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionExecutionSpecificationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the ActionExecutionSpecification element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForActionExecutionSpecificationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditActionExecutionSpecificationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ActionExecutionSpecification)actionExecutionSpecification).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForActionExecutionSpecificationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the ActionExecutionSpecification element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForActionExecutionSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ActionExecutionSpecification)actionExecutionSpecification).getClientDependency());
		cc.append(AddCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditActionExecutionSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionExecutionSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ActionExecutionSpecification element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForActionExecutionSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)actionExecutionSpecification.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveActionExecutionSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActionExecutionSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ActionExecutionSpecification element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForActionExecutionSpecificationCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, lifelineMetaClass);
		referenceValueForCovered = bot.changeReferenceValue(allInstancesOf, ((ActionExecutionSpecification)actionExecutionSpecification).getCovered());
		cc.append(AddCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), referenceValueForCovered));
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
	public void testEditActionExecutionSpecificationCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionExecutionSpecificationCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the covered feature of the ActionExecutionSpecification element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.covered, referenceValueForCovered, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForActionExecutionSpecificationCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)actionExecutionSpecification.eGet(UMLPackage.eINSTANCE.getInteractionFragment_Covered());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), allReferencedInstances.get(0)));
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
	public void testRemoveActionExecutionSpecificationCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActionExecutionSpecificationCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the covered feature of the ActionExecutionSpecification element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.covered, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForActionExecutionSpecificationEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForEnclosingInteraction = bot.changeReferenceValue(allInstancesOf, ((ActionExecutionSpecification)actionExecutionSpecification).getEnclosingInteraction());
		cc.append(SetCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), referenceValueForEnclosingInteraction));
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
	public void testEditActionExecutionSpecificationEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionExecutionSpecificationEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the ActionExecutionSpecification element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction, allInstancesOf.indexOf(referenceValueForEnclosingInteraction)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForActionExecutionSpecificationEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		cc.append(SetCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), null));
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
	public void testRemoveActionExecutionSpecificationEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActionExecutionSpecificationEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the ActionExecutionSpecification element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForActionExecutionSpecificationEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		referenceValueForEnclosingOperand = bot.changeReferenceValue(allInstancesOf, ((ActionExecutionSpecification)actionExecutionSpecification).getEnclosingOperand());
		cc.append(SetCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), referenceValueForEnclosingOperand));
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
	public void testEditActionExecutionSpecificationEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionExecutionSpecificationEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the ActionExecutionSpecification element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand, allInstancesOf.indexOf(referenceValueForEnclosingOperand)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForActionExecutionSpecificationEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		cc.append(SetCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), null));
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
	public void testRemoveActionExecutionSpecificationEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForActionExecutionSpecificationEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the ActionExecutionSpecification element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForActionExecutionSpecificationStart() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, occurrenceSpecificationMetaClass);
		referenceValueForStart = bot.changeReferenceValue(allInstancesOf, ((ActionExecutionSpecification)actionExecutionSpecification).getStart());
		cc.append(SetCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getExecutionSpecification_Start(), referenceValueForStart));
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
	public void testEditActionExecutionSpecificationStart() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionExecutionSpecificationStart();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the start feature of the ActionExecutionSpecification element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.start, allInstancesOf.indexOf(referenceValueForStart), bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForActionExecutionSpecificationFinish() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, occurrenceSpecificationMetaClass);
		referenceValueForFinish = bot.changeReferenceValue(allInstancesOf, ((ActionExecutionSpecification)actionExecutionSpecification).getFinish());
		cc.append(SetCommand.create(editingDomain, actionExecutionSpecification, UMLPackage.eINSTANCE.getExecutionSpecification_Finish(), referenceValueForFinish));
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
	public void testEditActionExecutionSpecificationFinish() throws Exception {

		// Import the input model
		initializeInputModel();

		actionExecutionSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (actionExecutionSpecification == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForActionExecutionSpecificationFinish();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ActionExecutionSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), actionExecutionSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(actionExecutionSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the finish feature of the ActionExecutionSpecification element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ActionExecutionSpecification.Properties.finish, allInstancesOf.indexOf(referenceValueForFinish), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
