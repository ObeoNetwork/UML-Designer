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
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for LiteralInteger
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class LiteralIntegerTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass literalIntegerMetaClass = UMLPackage.eINSTANCE.getLiteralInteger();

	/**
	 * The type to edit
	 */
	private EObject literalInteger;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
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
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;
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
	protected void initializeExpectedModelForLiteralIntegerName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, literalInteger, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditLiteralIntegerName() throws Exception {

		// Import the input model
		initializeInputModel();

		literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralIntegerName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralInteger element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalIntegerMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the LiteralInteger element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LiteralInteger.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLiteralIntegerVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, literalInteger, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditLiteralIntegerVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((LiteralInteger)literalInteger).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForLiteralIntegerVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralInteger element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalIntegerMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the LiteralInteger element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LiteralInteger.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLiteralIntegerClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((LiteralInteger)literalInteger).getClientDependency());
		cc.append(AddCommand.create(editingDomain, literalInteger, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditLiteralIntegerClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralIntegerClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralInteger element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalIntegerMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the LiteralInteger element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LiteralInteger.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForLiteralIntegerClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)literalInteger.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, literalInteger, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveLiteralIntegerClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralIntegerClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralInteger element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalIntegerMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the LiteralInteger element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LiteralInteger.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForLiteralIntegerOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((LiteralInteger)literalInteger).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, literalInteger, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditLiteralIntegerOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralIntegerOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralInteger element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalIntegerMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the LiteralInteger element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralInteger.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForLiteralIntegerOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, literalInteger, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveLiteralIntegerOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralIntegerOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralInteger element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalIntegerMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the LiteralInteger element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralInteger.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForLiteralIntegerTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((LiteralInteger)literalInteger).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, literalInteger, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditLiteralIntegerTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralIntegerTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralInteger element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalIntegerMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the LiteralInteger element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralInteger.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForLiteralIntegerTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, literalInteger, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveLiteralIntegerTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralIntegerTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralInteger element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalIntegerMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the LiteralInteger element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralInteger.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForLiteralIntegerValue() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, literalInteger, UMLPackage.eINSTANCE.getLiteralInteger_Value(), UPDATED_VALUE));
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
	public void testEditLiteralIntegerValue() throws Exception {

		// Import the input model
		initializeInputModel();

		literalInteger = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (literalInteger == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralIntegerValue();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralInteger element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalIntegerMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalIntegerMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalIntegerMetaClass, firstInstanceOf, null);

		// Change value of the value feature of the LiteralInteger element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LiteralInteger.Properties.value, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
