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
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for OpaqueExpression
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class OpaqueExpressionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass opaqueExpressionMetaClass = UMLPackage.eINSTANCE.getOpaqueExpression();

	/**
	 * The type to edit
	 */
	private EObject opaqueExpression;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class behavior
	 */
	private Object referenceValueForBehavior;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

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
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass behaviorMetaClass = UMLPackage.eINSTANCE.getBehavior();

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
	protected void initializeExpectedModelForOpaqueExpressionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueExpression, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditOpaqueExpressionName() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueExpressionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the OpaqueExpression element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.OpaqueExpression.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOpaqueExpressionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueExpression, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditOpaqueExpressionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((OpaqueExpression)opaqueExpression).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForOpaqueExpressionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the OpaqueExpression element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.OpaqueExpression.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOpaqueExpressionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((OpaqueExpression)opaqueExpression).getClientDependency());
		cc.append(AddCommand.create(editingDomain, opaqueExpression, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditOpaqueExpressionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueExpressionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the OpaqueExpression element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OpaqueExpression.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOpaqueExpressionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)opaqueExpression.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, opaqueExpression, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveOpaqueExpressionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueExpressionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the OpaqueExpression element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OpaqueExpression.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOpaqueExpressionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((OpaqueExpression)opaqueExpression).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, opaqueExpression, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditOpaqueExpressionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueExpressionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the OpaqueExpression element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OpaqueExpression.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOpaqueExpressionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, opaqueExpression, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveOpaqueExpressionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueExpressionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the OpaqueExpression element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OpaqueExpression.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForOpaqueExpressionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((OpaqueExpression)opaqueExpression).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, opaqueExpression, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditOpaqueExpressionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueExpressionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the OpaqueExpression element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OpaqueExpression.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOpaqueExpressionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, opaqueExpression, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveOpaqueExpressionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueExpressionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the OpaqueExpression element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OpaqueExpression.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForOpaqueExpressionBody() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueExpression, UMLPackage.eINSTANCE.getOpaqueExpression_Body(), UPDATED_VALUE));
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
	public void testEditOpaqueExpressionBody() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueExpressionBody();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the body feature of the OpaqueExpression element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.OpaqueExpression.Properties.body, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOpaqueExpressionLanguage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueExpression, UMLPackage.eINSTANCE.getOpaqueExpression_Language(), UPDATED_VALUE));
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
	public void testEditOpaqueExpressionLanguage() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueExpression = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (opaqueExpression == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueExpressionLanguage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueExpression element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueExpressionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueExpressionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the language feature of the OpaqueExpression element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.OpaqueExpression.Properties.language, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
