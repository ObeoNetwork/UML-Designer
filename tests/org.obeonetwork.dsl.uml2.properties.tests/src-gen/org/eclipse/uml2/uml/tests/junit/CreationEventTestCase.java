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
import org.eclipse.uml2.uml.CreationEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for CreationEvent
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class CreationEventTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass creationEventMetaClass = UMLPackage.eINSTANCE.getCreationEvent();

	/**
	 * The type to edit
	 */
	private EObject creationEvent;

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
	protected void initializeExpectedModelForCreationEventName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, creationEvent, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditCreationEventName() throws Exception {

		// Import the input model
		initializeInputModel();

		creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCreationEventName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, creationEventMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the CreationEvent element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CreationEvent.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCreationEventVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, creationEvent, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditCreationEventVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((CreationEvent)creationEvent).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForCreationEventVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, creationEventMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the CreationEvent element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CreationEvent.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCreationEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((CreationEvent)creationEvent).getClientDependency());
		cc.append(AddCommand.create(editingDomain, creationEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditCreationEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCreationEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, creationEventMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CreationEvent element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CreationEvent.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForCreationEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)creationEvent.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, creationEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveCreationEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCreationEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, creationEventMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CreationEvent element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CreationEvent.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCreationEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((CreationEvent)creationEvent).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, creationEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditCreationEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCreationEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, creationEventMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the CreationEvent element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CreationEvent.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForCreationEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, creationEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveCreationEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCreationEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, creationEventMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the CreationEvent element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CreationEvent.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForCreationEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((CreationEvent)creationEvent).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, creationEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditCreationEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCreationEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, creationEventMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the CreationEvent element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CreationEvent.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForCreationEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, creationEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveCreationEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		creationEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (creationEvent == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCreationEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CreationEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), creationEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(creationEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, creationEventMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the CreationEvent element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CreationEvent.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
