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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ReceiveOperationEvent;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ReceiveOperationEvent
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ReceiveOperationEventPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass receiveOperationEventMetaClass = UMLPackage.eINSTANCE.getReceiveOperationEvent();

	/**
	 * The type to edit
	 */
	private EObject receiveOperationEvent;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class operation
	 */
	private Object referenceValueForOperation;

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
	protected void initializeExpectedModelForReceiveOperationEventName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, receiveOperationEvent, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditReceiveOperationEventName() throws Exception {

		// Import the input model
		initializeInputModel();

		receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReceiveOperationEventName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReceiveOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the ReceiveOperationEvent element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ReceiveOperationEvent.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReceiveOperationEventVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, receiveOperationEvent, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditReceiveOperationEventVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ReceiveOperationEvent)receiveOperationEvent).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForReceiveOperationEventVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReceiveOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the ReceiveOperationEvent element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ReceiveOperationEvent.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReceiveOperationEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ReceiveOperationEvent)receiveOperationEvent).getClientDependency());
		cc.append(AddCommand.create(editingDomain, receiveOperationEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditReceiveOperationEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReceiveOperationEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReceiveOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReceiveOperationEvent element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReceiveOperationEvent.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReceiveOperationEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)receiveOperationEvent.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, receiveOperationEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveReceiveOperationEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReceiveOperationEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReceiveOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReceiveOperationEvent element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReceiveOperationEvent.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReceiveOperationEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((ReceiveOperationEvent)receiveOperationEvent).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, receiveOperationEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditReceiveOperationEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReceiveOperationEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReceiveOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the ReceiveOperationEvent element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReceiveOperationEvent.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReceiveOperationEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, receiveOperationEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveReceiveOperationEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReceiveOperationEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReceiveOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the ReceiveOperationEvent element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReceiveOperationEvent.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForReceiveOperationEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((ReceiveOperationEvent)receiveOperationEvent).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, receiveOperationEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditReceiveOperationEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReceiveOperationEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReceiveOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the ReceiveOperationEvent element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReceiveOperationEvent.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReceiveOperationEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, receiveOperationEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveReceiveOperationEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReceiveOperationEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReceiveOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the ReceiveOperationEvent element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReceiveOperationEvent.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForReceiveOperationEventOperation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, operationMetaClass);
		referenceValueForOperation = bot.changeReferenceValue(allInstancesOf, ((ReceiveOperationEvent)receiveOperationEvent).getOperation());
		cc.append(SetCommand.create(editingDomain, receiveOperationEvent, UMLPackage.eINSTANCE.getReceiveOperationEvent_Operation(), referenceValueForOperation));
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
	public void testEditReceiveOperationEventOperation() throws Exception {

		// Import the input model
		initializeInputModel();

		receiveOperationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (receiveOperationEvent == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReceiveOperationEventOperation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReceiveOperationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receiveOperationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receiveOperationEventMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the operation feature of the ReceiveOperationEvent element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReceiveOperationEvent.Properties.operation, allInstancesOf.indexOf(referenceValueForOperation), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
