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
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for TimeEvent
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class TimeEventTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass timeEventMetaClass = UMLPackage.eINSTANCE.getTimeEvent();

	/**
	 * The type to edit
	 */
	private EObject timeEvent;

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
	protected void initializeExpectedModelForTimeEventName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, timeEvent, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditTimeEventName() throws Exception {

		// Import the input model
		initializeInputModel();

		timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeEventName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeEventMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the TimeEvent element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.TimeEvent.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTimeEventVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, timeEvent, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditTimeEventVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((TimeEvent)timeEvent).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForTimeEventVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeEventMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the TimeEvent element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.TimeEvent.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTimeEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((TimeEvent)timeEvent).getClientDependency());
		cc.append(AddCommand.create(editingDomain, timeEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditTimeEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeEventMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the TimeEvent element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.TimeEvent.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForTimeEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)timeEvent.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, timeEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveTimeEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeEventMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the TimeEvent element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.TimeEvent.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForTimeEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((TimeEvent)timeEvent).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, timeEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditTimeEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeEventMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the TimeEvent element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeEvent.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForTimeEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, timeEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveTimeEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeEventMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the TimeEvent element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeEvent.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForTimeEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((TimeEvent)timeEvent).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, timeEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditTimeEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeEventMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the TimeEvent element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeEvent.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForTimeEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, timeEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveTimeEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeEventMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the TimeEvent element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeEvent.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForTimeEventIsRelative() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, timeEvent, UMLPackage.eINSTANCE.getTimeEvent_IsRelative(), UPDATED_VALUE));
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
	public void testEditTimeEventIsRelative() throws Exception {

		// Import the input model
		initializeInputModel();

		timeEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (timeEvent == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeEventIsRelative();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeEventMetaClass, firstInstanceOf, null);

		// Change value of the isRelative feature of the TimeEvent element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.TimeEvent.Properties.isRelative, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
