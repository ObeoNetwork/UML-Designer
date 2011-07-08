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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.TimeExpression;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for TimeExpression
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class TimeExpressionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass timeExpressionMetaClass = UMLPackage.eINSTANCE.getTimeExpression();

	/**
	 * The type to edit
	 */
	private EObject timeExpression;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class observation
	 */
	private Object referenceValueForObservation;

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
	private EClass observationMetaClass = UMLPackage.eINSTANCE.getObservation();

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
	protected void initializeExpectedModelForTimeExpressionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, timeExpression, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditTimeExpressionName() throws Exception {

		// Import the input model
		initializeInputModel();

		timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeExpressionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeExpressionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the TimeExpression element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.TimeExpression.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTimeExpressionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, timeExpression, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditTimeExpressionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((TimeExpression)timeExpression).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForTimeExpressionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeExpressionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the TimeExpression element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.TimeExpression.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTimeExpressionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((TimeExpression)timeExpression).getClientDependency());
		cc.append(AddCommand.create(editingDomain, timeExpression, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditTimeExpressionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeExpressionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeExpressionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the TimeExpression element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.TimeExpression.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForTimeExpressionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)timeExpression.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, timeExpression, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveTimeExpressionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeExpressionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeExpressionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the TimeExpression element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.TimeExpression.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForTimeExpressionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((TimeExpression)timeExpression).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, timeExpression, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditTimeExpressionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeExpressionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeExpressionMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the TimeExpression element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeExpression.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForTimeExpressionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, timeExpression, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveTimeExpressionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeExpressionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeExpressionMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the TimeExpression element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeExpression.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForTimeExpressionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((TimeExpression)timeExpression).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, timeExpression, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditTimeExpressionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeExpressionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeExpressionMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the TimeExpression element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeExpression.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForTimeExpressionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, timeExpression, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveTimeExpressionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (timeExpression == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeExpressionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeExpressionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeExpressionMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the TimeExpression element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeExpression.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
