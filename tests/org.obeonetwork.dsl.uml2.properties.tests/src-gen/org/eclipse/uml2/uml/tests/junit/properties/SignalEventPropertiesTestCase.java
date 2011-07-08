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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.SignalEvent;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for SignalEvent
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class SignalEventPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass signalEventMetaClass = UMLPackage.eINSTANCE.getSignalEvent();

	/**
	 * The type to edit
	 */
	private EObject signalEvent;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class signal
	 */
	private Object referenceValueForSignal;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
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
	protected void initializeExpectedModelForSignalEventName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, signalEvent, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditSignalEventName() throws Exception {

		// Import the input model
		initializeInputModel();

		signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalEventName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the SignalEvent element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.SignalEvent.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForSignalEventVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, signalEvent, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditSignalEventVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((SignalEvent)signalEvent).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForSignalEventVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the SignalEvent element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.SignalEvent.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForSignalEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((SignalEvent)signalEvent).getClientDependency());
		cc.append(AddCommand.create(editingDomain, signalEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditSignalEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SignalEvent element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.SignalEvent.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForSignalEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)signalEvent.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, signalEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveSignalEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSignalEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the SignalEvent element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.SignalEvent.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForSignalEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((SignalEvent)signalEvent).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, signalEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditSignalEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the SignalEvent element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.SignalEvent.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForSignalEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, signalEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveSignalEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSignalEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the SignalEvent element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.SignalEvent.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForSignalEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((SignalEvent)signalEvent).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, signalEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditSignalEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the SignalEvent element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.SignalEvent.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForSignalEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, signalEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveSignalEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSignalEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the SignalEvent element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.SignalEvent.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForSignalEventSignal() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, signalMetaClass);
		referenceValueForSignal = bot.changeReferenceValue(allInstancesOf, ((SignalEvent)signalEvent).getSignal());
		cc.append(SetCommand.create(editingDomain, signalEvent, UMLPackage.eINSTANCE.getSignalEvent_Signal(), referenceValueForSignal));
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
	public void testEditSignalEventSignal() throws Exception {

		// Import the input model
		initializeInputModel();

		signalEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (signalEvent == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalEventSignal();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the SignalEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the signal feature of the SignalEvent element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.SignalEvent.Properties.signal, allInstancesOf.indexOf(referenceValueForSignal), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
