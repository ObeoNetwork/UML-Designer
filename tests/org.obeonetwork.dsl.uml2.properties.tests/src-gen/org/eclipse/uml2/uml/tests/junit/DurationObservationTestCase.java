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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.DurationObservation;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for DurationObservation
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class DurationObservationTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass durationObservationMetaClass = UMLPackage.eINSTANCE.getDurationObservation();

	/**
	 * The type to edit
	 */
	private EObject durationObservation;

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
	 * The reference value for the reference class event
	 */
	private Object referenceValueForEvent;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;
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
	protected void initializeExpectedModelForDurationObservationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, durationObservation, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditDurationObservationName() throws Exception {

		// Import the input model
		initializeInputModel();

		durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationObservationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationObservationMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the DurationObservation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DurationObservation.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDurationObservationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, durationObservation, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditDurationObservationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((DurationObservation)durationObservation).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForDurationObservationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationObservationMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the DurationObservation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DurationObservation.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDurationObservationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((DurationObservation)durationObservation).getClientDependency());
		cc.append(AddCommand.create(editingDomain, durationObservation, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditDurationObservationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationObservationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationObservationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DurationObservation element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DurationObservation.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForDurationObservationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)durationObservation.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, durationObservation, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveDurationObservationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDurationObservationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationObservationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DurationObservation element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DurationObservation.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDurationObservationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((DurationObservation)durationObservation).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, durationObservation, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditDurationObservationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationObservationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationObservationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the DurationObservation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DurationObservation.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDurationObservationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, durationObservation, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveDurationObservationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDurationObservationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationObservationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the DurationObservation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DurationObservation.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForDurationObservationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((DurationObservation)durationObservation).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, durationObservation, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditDurationObservationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationObservationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationObservationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the DurationObservation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DurationObservation.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDurationObservationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, durationObservation, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveDurationObservationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDurationObservationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationObservationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the DurationObservation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DurationObservation.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForDurationObservationFirstEvent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, durationObservation, UMLPackage.eINSTANCE.getDurationObservation_FirstEvent(), UPDATED_VALUE));
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
	public void testEditDurationObservationFirstEvent() throws Exception {

		// Import the input model
		initializeInputModel();

		durationObservation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (durationObservation == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationObservationFirstEvent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationObservation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationObservationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationObservationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationObservationMetaClass, firstInstanceOf, null);

		// Change value of the firstEvent feature of the DurationObservation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DurationObservation.Properties.firstEvent, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
