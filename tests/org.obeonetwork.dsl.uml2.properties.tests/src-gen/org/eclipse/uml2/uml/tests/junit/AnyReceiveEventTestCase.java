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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.AnyReceiveEvent;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for AnyReceiveEvent
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class AnyReceiveEventTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass anyReceiveEventMetaClass = UMLPackage.eINSTANCE.getAnyReceiveEvent();

	/**
	 * The type to edit
	 */
	private EObject anyReceiveEvent;

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
	protected void initializeExpectedModelForAnyReceiveEventName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, anyReceiveEvent, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditAnyReceiveEventName() throws Exception {

		// Import the input model
		initializeInputModel();

		anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAnyReceiveEventName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AnyReceiveEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, anyReceiveEventMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the AnyReceiveEvent element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.AnyReceiveEvent.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForAnyReceiveEventVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, anyReceiveEvent, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditAnyReceiveEventVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((AnyReceiveEvent)anyReceiveEvent).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForAnyReceiveEventVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AnyReceiveEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, anyReceiveEventMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the AnyReceiveEvent element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.AnyReceiveEvent.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForAnyReceiveEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((AnyReceiveEvent)anyReceiveEvent).getClientDependency());
		cc.append(AddCommand.create(editingDomain, anyReceiveEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditAnyReceiveEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAnyReceiveEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AnyReceiveEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, anyReceiveEventMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the AnyReceiveEvent element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AnyReceiveEvent.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForAnyReceiveEventClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)anyReceiveEvent.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, anyReceiveEvent, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveAnyReceiveEventClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAnyReceiveEventClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AnyReceiveEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, anyReceiveEventMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the AnyReceiveEvent element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AnyReceiveEvent.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForAnyReceiveEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((AnyReceiveEvent)anyReceiveEvent).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, anyReceiveEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditAnyReceiveEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAnyReceiveEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AnyReceiveEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, anyReceiveEventMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the AnyReceiveEvent element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.AnyReceiveEvent.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForAnyReceiveEventOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, anyReceiveEvent, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveAnyReceiveEventOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAnyReceiveEventOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AnyReceiveEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, anyReceiveEventMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the AnyReceiveEvent element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.AnyReceiveEvent.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForAnyReceiveEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((AnyReceiveEvent)anyReceiveEvent).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, anyReceiveEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditAnyReceiveEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAnyReceiveEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AnyReceiveEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, anyReceiveEventMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the AnyReceiveEvent element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.AnyReceiveEvent.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForAnyReceiveEventTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, anyReceiveEvent, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveAnyReceiveEventTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		anyReceiveEvent = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (anyReceiveEvent == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAnyReceiveEventTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AnyReceiveEvent element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), anyReceiveEventMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(anyReceiveEventMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, anyReceiveEventMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the AnyReceiveEvent element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.AnyReceiveEvent.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
