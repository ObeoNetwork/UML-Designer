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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.LiteralBoolean;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for LiteralBoolean
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class LiteralBooleanPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass literalBooleanMetaClass = UMLPackage.eINSTANCE.getLiteralBoolean();

	/**
	 * The type to edit
	 */
	private EObject literalBoolean;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

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
	protected void initializeExpectedModelForLiteralBooleanName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, literalBoolean, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditLiteralBooleanName() throws Exception {

		// Import the input model
		initializeInputModel();

		literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralBooleanName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LiteralBoolean element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the LiteralBoolean element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.LiteralBoolean.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForLiteralBooleanVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, literalBoolean, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditLiteralBooleanVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((LiteralBoolean)literalBoolean).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForLiteralBooleanVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LiteralBoolean element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the LiteralBoolean element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.LiteralBoolean.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForLiteralBooleanClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((LiteralBoolean)literalBoolean).getClientDependency());
		cc.append(AddCommand.create(editingDomain, literalBoolean, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditLiteralBooleanClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralBooleanClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LiteralBoolean element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the LiteralBoolean element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.LiteralBoolean.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForLiteralBooleanClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)literalBoolean.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, literalBoolean, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveLiteralBooleanClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralBooleanClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LiteralBoolean element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the LiteralBoolean element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.LiteralBoolean.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForLiteralBooleanOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((LiteralBoolean)literalBoolean).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, literalBoolean, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditLiteralBooleanOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralBooleanOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LiteralBoolean element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the LiteralBoolean element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.LiteralBoolean.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForLiteralBooleanOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, literalBoolean, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveLiteralBooleanOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralBooleanOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LiteralBoolean element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the LiteralBoolean element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.LiteralBoolean.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForLiteralBooleanTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((LiteralBoolean)literalBoolean).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, literalBoolean, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditLiteralBooleanTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralBooleanTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LiteralBoolean element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the LiteralBoolean element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.LiteralBoolean.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForLiteralBooleanTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, literalBoolean, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveLiteralBooleanTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralBooleanTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LiteralBoolean element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the LiteralBoolean element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.LiteralBoolean.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForLiteralBooleanValue() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, literalBoolean, UMLPackage.eINSTANCE.getLiteralBoolean_Value(), UPDATED_VALUE));
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
	public void testEditLiteralBooleanValue() throws Exception {

		// Import the input model
		initializeInputModel();

		literalBoolean = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (literalBoolean == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralBooleanValue();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the LiteralBoolean element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalBooleanMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalBooleanMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the value feature of the LiteralBoolean element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.LiteralBoolean.Properties.value, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
