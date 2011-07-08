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
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for InstanceValue
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class InstanceValueTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass instanceValueMetaClass = UMLPackage.eINSTANCE.getInstanceValue();

	/**
	 * The type to edit
	 */
	private EObject instanceValue;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class instance
	 */
	private Object referenceValueForInstance;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

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
	private EClass typeMetaClass = UMLPackage.eINSTANCE.getType();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass instanceSpecificationMetaClass = UMLPackage.eINSTANCE.getInstanceSpecification();

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
	protected void initializeExpectedModelForInstanceValueName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, instanceValue, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditInstanceValueName() throws Exception {

		// Import the input model
		initializeInputModel();

		instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInstanceValueName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InstanceValue element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, instanceValueMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the InstanceValue element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InstanceValue.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInstanceValueVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, instanceValue, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditInstanceValueVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((InstanceValue)instanceValue).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForInstanceValueVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InstanceValue element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, instanceValueMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the InstanceValue element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InstanceValue.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInstanceValueClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((InstanceValue)instanceValue).getClientDependency());
		cc.append(AddCommand.create(editingDomain, instanceValue, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditInstanceValueClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInstanceValueClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InstanceValue element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, instanceValueMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InstanceValue element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InstanceValue.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForInstanceValueClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)instanceValue.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, instanceValue, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveInstanceValueClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInstanceValueClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InstanceValue element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, instanceValueMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InstanceValue element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InstanceValue.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInstanceValueOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((InstanceValue)instanceValue).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, instanceValue, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditInstanceValueOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInstanceValueOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InstanceValue element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, instanceValueMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the InstanceValue element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InstanceValue.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForInstanceValueOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, instanceValue, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveInstanceValueOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInstanceValueOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InstanceValue element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, instanceValueMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the InstanceValue element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InstanceValue.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForInstanceValueTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((InstanceValue)instanceValue).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, instanceValue, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditInstanceValueTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInstanceValueTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InstanceValue element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, instanceValueMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the InstanceValue element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InstanceValue.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForInstanceValueTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, instanceValue, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveInstanceValueTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInstanceValueTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InstanceValue element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, instanceValueMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the InstanceValue element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InstanceValue.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForInstanceValueInstance() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, instanceSpecificationMetaClass);
		referenceValueForInstance = bot.changeReferenceValue(allInstancesOf, ((InstanceValue)instanceValue).getInstance());
		cc.append(SetCommand.create(editingDomain, instanceValue, UMLPackage.eINSTANCE.getInstanceValue_Instance(), referenceValueForInstance));
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
	public void testEditInstanceValueInstance() throws Exception {

		// Import the input model
		initializeInputModel();

		instanceValue = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (instanceValue == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInstanceValueInstance();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InstanceValue element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), instanceValueMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(instanceValueMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, instanceValueMetaClass, firstInstanceOf, null);

		// Change value of the instance feature of the InstanceValue element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InstanceValue.Properties.instance, allInstancesOf.indexOf(referenceValueForInstance));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
