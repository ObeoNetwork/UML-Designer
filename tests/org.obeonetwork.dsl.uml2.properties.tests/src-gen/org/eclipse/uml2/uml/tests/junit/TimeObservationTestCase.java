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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.TimeObservation;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for TimeObservation
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class TimeObservationTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass timeObservationMetaClass = UMLPackage.eINSTANCE.getTimeObservation();

	/**
	 * The type to edit
	 */
	private EObject timeObservation;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class event
	 */
	private Object referenceValueForEvent;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass namedElementMetaClass = UMLPackage.eINSTANCE.getNamedElement();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

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
	protected void initializeExpectedModelForTimeObservationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, timeObservation, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditTimeObservationName() throws Exception {

		// Import the input model
		initializeInputModel();

		timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeObservationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeObservationMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the TimeObservation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.TimeObservation.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTimeObservationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, timeObservation, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditTimeObservationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((TimeObservation)timeObservation).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForTimeObservationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeObservationMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the TimeObservation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.TimeObservation.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTimeObservationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((TimeObservation)timeObservation).getClientDependency());
		cc.append(AddCommand.create(editingDomain, timeObservation, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditTimeObservationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeObservationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeObservationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the TimeObservation element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.TimeObservation.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForTimeObservationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)timeObservation.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, timeObservation, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveTimeObservationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeObservationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeObservationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the TimeObservation element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.TimeObservation.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForTimeObservationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((TimeObservation)timeObservation).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, timeObservation, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditTimeObservationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeObservationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeObservationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the TimeObservation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeObservation.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForTimeObservationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, timeObservation, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveTimeObservationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeObservationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeObservationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the TimeObservation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeObservation.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForTimeObservationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((TimeObservation)timeObservation).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, timeObservation, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditTimeObservationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeObservationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeObservationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the TimeObservation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeObservation.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForTimeObservationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, timeObservation, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveTimeObservationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeObservationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeObservationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the TimeObservation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeObservation.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForTimeObservationFirstEvent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, timeObservation, UMLPackage.eINSTANCE.getTimeObservation_FirstEvent(), UPDATED_VALUE));
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
	public void testEditTimeObservationFirstEvent() throws Exception {

		// Import the input model
		initializeInputModel();

		timeObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (timeObservation == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeObservationFirstEvent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeObservationMetaClass, firstInstanceOf, null);

		// Change value of the firstEvent feature of the TimeObservation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.TimeObservation.Properties.firstEvent, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
