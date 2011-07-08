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
import org.eclipse.uml2.uml.Duration;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Duration
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class DurationTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass durationMetaClass = UMLPackage.eINSTANCE.getDuration();

	/**
	 * The type to edit
	 */
	private EObject duration;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class observation
	 */
	private Object referenceValueForObservation;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass typeMetaClass = UMLPackage.eINSTANCE.getType();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass observationMetaClass = UMLPackage.eINSTANCE.getObservation();

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
	protected void initializeExpectedModelForDurationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject duration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, duration, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditDurationName() throws Exception {

		// Import the input model
		initializeInputModel();

		duration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Duration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Duration element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Duration.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDurationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject duration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, duration, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditDurationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		duration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Duration)duration).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForDurationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Duration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Duration element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Duration.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDurationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject duration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Duration)duration).getClientDependency());
		cc.append(AddCommand.create(editingDomain, duration, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditDurationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		duration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Duration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Duration element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Duration.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForDurationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject duration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)duration.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, duration, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveDurationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		duration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDurationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Duration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Duration element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Duration.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDurationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject duration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Duration)duration).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, duration, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditDurationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		duration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Duration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Duration element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Duration.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDurationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject duration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, duration, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveDurationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		duration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDurationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Duration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Duration element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Duration.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForDurationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject duration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Duration)duration).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, duration, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditDurationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		duration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Duration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Duration element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Duration.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDurationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject duration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, duration, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveDurationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		duration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (duration == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDurationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Duration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Duration element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Duration.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
