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
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Parameter
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ParameterPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass parameterMetaClass = UMLPackage.eINSTANCE.getParameter();

	/**
	 * The type to edit
	 */
	private EObject parameter;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class direction
	 */
	private Object enumValueForDirection;

	/**
	 * The enum value for the enum class effect
	 */
	private Object enumValueForEffect;
	/**
	 * The reference value for the reference class operation
	 */
	private Object referenceValueForOperation;

	/**
	 * The reference value for the reference class parameterSet
	 */
	private Object referenceValueForParameterSet;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

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
	private EClass operationMetaClass = UMLPackage.eINSTANCE.getOperation();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass parameterSetMetaClass = UMLPackage.eINSTANCE.getParameterSet();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass typeMetaClass = UMLPackage.eINSTANCE.getType();

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
	protected void initializeExpectedModelForParameterName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditParameterName() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the Parameter element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Parameter.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForParameterVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditParameterVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Parameter)parameter).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForParameterVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the Parameter element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Parameter.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForParameterClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Parameter)parameter).getClientDependency());
		cc.append(AddCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditParameterClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Parameter element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.Parameter.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForParameterClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)parameter.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveParameterClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForParameterClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Parameter element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.Parameter.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForParameterOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Parameter)parameter).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditParameterOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Parameter element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.Parameter.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForParameterOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveParameterOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForParameterOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Parameter element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.Parameter.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForParameterTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Parameter)parameter).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditParameterTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Parameter element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.Parameter.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForParameterTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveParameterTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForParameterTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Parameter element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.Parameter.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForParameterIsOrdered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), UPDATED_VALUE));
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
	public void testEditParameterIsOrdered() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterIsOrdered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isOrdered feature of the Parameter element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Parameter.Properties.isOrdered, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForParameterIsUnique() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), UPDATED_VALUE));
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
	public void testEditParameterIsUnique() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterIsUnique();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isUnique feature of the Parameter element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Parameter.Properties.isUnique, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForParameterParameterSet() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, parameterSetMetaClass);
		referenceValueForParameterSet = bot.changeReferenceValue(allInstancesOf, ((Parameter)parameter).getParameterSet());
		cc.append(AddCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameter_ParameterSet(), referenceValueForParameterSet));
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
	public void testEditParameterParameterSet() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterParameterSet();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the parameterSet feature of the Parameter element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.Parameter.Properties.parameterSet, referenceValueForParameterSet, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForParameterParameterSet() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)parameter.eGet(UMLPackage.eINSTANCE.getParameter_ParameterSet());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameter_ParameterSet(), allReferencedInstances.get(0)));
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
	public void testRemoveParameterParameterSet() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForParameterParameterSet();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the parameterSet feature of the Parameter element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.Parameter.Properties.parameterSet, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForParameterOperation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, operationMetaClass);
		referenceValueForOperation = bot.changeReferenceValue(allInstancesOf, ((Parameter)parameter).getOperation());
		cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameter_Operation(), referenceValueForOperation));
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
	public void testEditParameterOperation() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterOperation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the operation feature of the Parameter element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.Parameter.Properties.operation, allInstancesOf.indexOf(referenceValueForOperation)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForParameterOperation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, operationMetaClass);
		cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameter_Operation(), null));
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
	public void testRemoveParameterOperation() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForParameterOperation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the operation feature of the Parameter element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.Parameter.Properties.operation, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForParameterDirection() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameter_Direction(), UPDATED_VALUE));
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
	public void testEditParameterDirection() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		enumValueForDirection = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getParameterDirectionKind(), ((Parameter)parameter).getDirection().getLiteral());
		// Create the expected model
		initializeExpectedModelForParameterDirection();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the direction feature of the Parameter element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Parameter.Properties.direction, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForParameterIsException() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameter_IsException(), UPDATED_VALUE));
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
	public void testEditParameterIsException() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterIsException();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isException feature of the Parameter element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Parameter.Properties.isException, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForParameterIsStream() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameter_IsStream(), UPDATED_VALUE));
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
	public void testEditParameterIsStream() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterIsStream();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isStream feature of the Parameter element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Parameter.Properties.isStream, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForParameterEffect() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameter = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, parameter, UMLPackage.eINSTANCE.getParameter_Effect(), UPDATED_VALUE));
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
	public void testEditParameterEffect() throws Exception {

		// Import the input model
		initializeInputModel();

		parameter = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (parameter == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());

		enumValueForEffect = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getParameterEffectKind(), ((Parameter)parameter).getEffect().getLiteral());
		// Create the expected model
		initializeExpectedModelForParameterEffect();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Parameter element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the effect feature of the Parameter element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Parameter.Properties.effect, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




























}
