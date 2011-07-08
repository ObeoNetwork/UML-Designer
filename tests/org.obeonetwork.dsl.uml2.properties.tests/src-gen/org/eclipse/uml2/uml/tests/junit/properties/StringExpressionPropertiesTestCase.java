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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.StringExpression;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for StringExpression
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class StringExpressionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass stringExpressionMetaClass = UMLPackage.eINSTANCE.getStringExpression();

	/**
	 * The type to edit
	 */
	private EObject stringExpression;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class owningExpression
	 */
	private Object referenceValueForOwningExpression;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

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
	protected void initializeExpectedModelForStringExpressionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, stringExpression, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditStringExpressionName() throws Exception {

		// Import the input model
		initializeInputModel();

		stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStringExpressionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StringExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the StringExpression element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.StringExpression.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStringExpressionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, stringExpression, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditStringExpressionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((StringExpression)stringExpression).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForStringExpressionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StringExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the StringExpression element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.StringExpression.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStringExpressionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((StringExpression)stringExpression).getClientDependency());
		cc.append(AddCommand.create(editingDomain, stringExpression, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditStringExpressionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStringExpressionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StringExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the StringExpression element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StringExpression.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStringExpressionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)stringExpression.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, stringExpression, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveStringExpressionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStringExpressionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StringExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the StringExpression element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.StringExpression.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStringExpressionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((StringExpression)stringExpression).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, stringExpression, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditStringExpressionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStringExpressionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StringExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the StringExpression element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StringExpression.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStringExpressionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, stringExpression, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveStringExpressionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStringExpressionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StringExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the StringExpression element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StringExpression.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForStringExpressionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((StringExpression)stringExpression).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, stringExpression, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditStringExpressionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStringExpressionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StringExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the StringExpression element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StringExpression.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStringExpressionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, stringExpression, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveStringExpressionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStringExpressionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StringExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the StringExpression element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StringExpression.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForStringExpressionSymbol() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, stringExpression, UMLPackage.eINSTANCE.getExpression_Symbol(), UPDATED_VALUE));
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
	public void testEditStringExpressionSymbol() throws Exception {

		// Import the input model
		initializeInputModel();

		stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStringExpressionSymbol();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StringExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the symbol feature of the StringExpression element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.StringExpression.Properties.symbol, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForStringExpressionOwningExpression() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stringExpressionMetaClass);
		referenceValueForOwningExpression = bot.changeReferenceValue(allInstancesOf, ((StringExpression)stringExpression).getOwningExpression());
		cc.append(SetCommand.create(editingDomain, stringExpression, UMLPackage.eINSTANCE.getStringExpression_OwningExpression(), referenceValueForOwningExpression));
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
	public void testEditStringExpressionOwningExpression() throws Exception {

		// Import the input model
		initializeInputModel();

		stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStringExpressionOwningExpression();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StringExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningExpression feature of the StringExpression element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StringExpression.Properties.owningExpression, allInstancesOf.indexOf(referenceValueForOwningExpression)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForStringExpressionOwningExpression() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stringExpressionMetaClass);
		cc.append(SetCommand.create(editingDomain, stringExpression, UMLPackage.eINSTANCE.getStringExpression_OwningExpression(), null));
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
	public void testRemoveStringExpressionOwningExpression() throws Exception {

		// Import the input model
		initializeInputModel();

		stringExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (stringExpression == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStringExpressionOwningExpression();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the StringExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stringExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stringExpressionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningExpression feature of the StringExpression element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.StringExpression.Properties.owningExpression, bot.selectNode(modelEditor, firstInstanceOf));
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
