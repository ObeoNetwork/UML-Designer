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

import javax.swing.event.ChangeEvent;

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ChangeEvent
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ChangeEventTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass changeEventMetaClass = UMLPackage.eINSTANCE.getChangeEvent();

	/**
	 * The type to edit
	 */
	private EObject changeEvent;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;
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
	protected void initializeExpectedModelForChangeEventName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, changeEvent, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditChangeEventName() throws Exception {

		// Import the input model
		initializeInputModel();

		changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForChangeEventName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ChangeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, changeEventMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ChangeEvent element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ChangeEvent.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForChangeEventVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, changeEvent, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditChangeEventVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ChangeEvent)changeEvent).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForChangeEventVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ChangeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, changeEventMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ChangeEvent element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ChangeEvent.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForChangeEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ChangeEvent)changeEvent).getClientDependency());
		cc.append(AddCommand.create(editingDomain, changeEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditChangeEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForChangeEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ChangeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, changeEventMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ChangeEvent element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ChangeEvent.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForChangeEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)changeEvent.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, changeEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveChangeEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForChangeEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ChangeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, changeEventMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ChangeEvent element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ChangeEvent.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForChangeEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((ChangeEvent)changeEvent).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, changeEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditChangeEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForChangeEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ChangeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, changeEventMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the ChangeEvent element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ChangeEvent.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForChangeEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, changeEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveChangeEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForChangeEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ChangeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, changeEventMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the ChangeEvent element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ChangeEvent.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForChangeEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((ChangeEvent)changeEvent).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, changeEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditChangeEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForChangeEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ChangeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, changeEventMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the ChangeEvent element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ChangeEvent.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForChangeEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, changeEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveChangeEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		changeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (changeEvent == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForChangeEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ChangeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), changeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(changeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, changeEventMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the ChangeEvent element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ChangeEvent.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
