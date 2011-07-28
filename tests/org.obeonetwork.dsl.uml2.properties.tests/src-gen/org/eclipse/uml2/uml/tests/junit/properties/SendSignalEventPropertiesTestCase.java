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
import org.eclipse.uml2.uml.SendSignalEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for SendSignalEvent
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class SendSignalEventPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass sendSignalEventMetaClass = UMLPackage.eINSTANCE.getSendSignalEvent();

	/**
	 * The type to edit
	 */
	private EObject sendSignalEvent;

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
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class signal
	 */
	private Object referenceValueForSignal;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass signalMetaClass = UMLPackage.eINSTANCE.getSignal();

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
	protected void initializeExpectedModelForSendSignalEventName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sendSignalEvent, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditSendSignalEventName() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalEventName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SendSignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the SendSignalEvent element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.SendSignalEvent.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForSendSignalEventVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, sendSignalEvent, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditSendSignalEventVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((SendSignalEvent)sendSignalEvent).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForSendSignalEventVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SendSignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the SendSignalEvent element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.SendSignalEvent.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForSendSignalEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((SendSignalEvent)sendSignalEvent).getClientDependency());
		cc.append(AddCommand.create(editingDomain, sendSignalEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditSendSignalEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SendSignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SendSignalEvent element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.SendSignalEvent.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForSendSignalEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)sendSignalEvent.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, sendSignalEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveSendSignalEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendSignalEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SendSignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SendSignalEvent element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.SendSignalEvent.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForSendSignalEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((SendSignalEvent)sendSignalEvent).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, sendSignalEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditSendSignalEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SendSignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the SendSignalEvent element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.SendSignalEvent.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForSendSignalEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, sendSignalEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveSendSignalEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendSignalEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SendSignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the SendSignalEvent element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.SendSignalEvent.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForSendSignalEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((SendSignalEvent)sendSignalEvent).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, sendSignalEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditSendSignalEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SendSignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the SendSignalEvent element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.SendSignalEvent.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForSendSignalEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, sendSignalEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveSendSignalEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSendSignalEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SendSignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the SendSignalEvent element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.SendSignalEvent.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForSendSignalEventSignal() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, signalMetaClass);
		referenceValueForSignal = bot.changeReferenceValue(allInstancesOf, ((SendSignalEvent)sendSignalEvent).getSignal());
		cc.append(SetCommand.create(editingDomain, sendSignalEvent, UMLPackage.eINSTANCE.getSendSignalEvent_Signal(), referenceValueForSignal));
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
	public void testEditSendSignalEventSignal() throws Exception {

		// Import the input model
		initializeInputModel();

		sendSignalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (sendSignalEvent == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSendSignalEventSignal();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SendSignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), sendSignalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(sendSignalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the signal feature of the SendSignalEvent element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.SendSignalEvent.Properties.signal, allInstancesOf.indexOf(referenceValueForSignal), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
