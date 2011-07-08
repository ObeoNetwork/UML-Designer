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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.SendOperationEvent;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for SendOperationEvent
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class SendOperationEventTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass sendOperationEventMetaClass = UMLPackage.eINSTANCE.getSendOperationEvent();

	/**
	 * The type to edit
	 */
	private EObject sendOperationEvent;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class operation
	 */
	private Object referenceValueForOperation;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass operationMetaClass = UMLPackage.eINSTANCE.getOperation();

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
	protected void initializeExpectedModelForSendOperationEventName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sendOperationEvent, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditSendOperationEventName() throws Exception {

		// Import the input model
		initializeInputModel();

		sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendOperationEventName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendOperationEventMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the SendOperationEvent element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SendOperationEvent.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSendOperationEventVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sendOperationEvent, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditSendOperationEventVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((SendOperationEvent)sendOperationEvent).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForSendOperationEventVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendOperationEventMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the SendOperationEvent element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.SendOperationEvent.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSendOperationEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((SendOperationEvent)sendOperationEvent).getClientDependency());
		cc.append(AddCommand.create(editingDomain, sendOperationEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditSendOperationEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendOperationEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendOperationEventMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SendOperationEvent element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendOperationEvent.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForSendOperationEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)sendOperationEvent.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, sendOperationEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveSendOperationEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendOperationEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendOperationEventMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SendOperationEvent element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.SendOperationEvent.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSendOperationEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((SendOperationEvent)sendOperationEvent).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, sendOperationEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditSendOperationEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendOperationEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendOperationEventMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the SendOperationEvent element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendOperationEvent.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForSendOperationEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, sendOperationEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveSendOperationEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendOperationEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendOperationEventMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the SendOperationEvent element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendOperationEvent.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForSendOperationEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((SendOperationEvent)sendOperationEvent).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, sendOperationEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditSendOperationEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendOperationEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendOperationEventMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the SendOperationEvent element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendOperationEvent.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForSendOperationEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, sendOperationEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveSendOperationEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendOperationEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendOperationEventMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the SendOperationEvent element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendOperationEvent.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForSendOperationEventOperation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, operationMetaClass);
		referenceValueForOperation = bot.changeReferenceValue(allInstancesOf, ((SendOperationEvent)sendOperationEvent).getOperation());
		cc.append(SetCommand.create(editingDomain, sendOperationEvent, UMLPackage.eINSTANCE.getSendOperationEvent_Operation(), referenceValueForOperation));
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
	public void testEditSendOperationEventOperation() throws Exception {

		// Import the input model
		initializeInputModel();

		sendOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (sendOperationEvent == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendOperationEventOperation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the SendOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendOperationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, sendOperationEventMetaClass, firstInstanceOf, null);

		// Change value of the operation feature of the SendOperationEvent element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.SendOperationEvent.Properties.operation, allInstancesOf.indexOf(referenceValueForOperation));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
