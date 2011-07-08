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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.LiteralString;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for LiteralString
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class LiteralStringTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass literalStringMetaClass = UMLPackage.eINSTANCE.getLiteralString();

	/**
	 * The type to edit
	 */
	private EObject literalString;

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
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

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
	protected void initializeExpectedModelForLiteralStringName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalString = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, literalString, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditLiteralStringName() throws Exception {

		// Import the input model
		initializeInputModel();

		literalString = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralStringName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralString element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalStringMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the LiteralString element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LiteralString.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLiteralStringVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalString = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, literalString, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditLiteralStringVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		literalString = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((LiteralString)literalString).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForLiteralStringVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralString element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalStringMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the LiteralString element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LiteralString.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLiteralStringClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalString = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((LiteralString)literalString).getClientDependency());
		cc.append(AddCommand.create(editingDomain, literalString, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditLiteralStringClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		literalString = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralStringClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralString element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalStringMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the LiteralString element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LiteralString.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForLiteralStringClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalString = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)literalString.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, literalString, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveLiteralStringClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		literalString = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralStringClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralString element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalStringMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the LiteralString element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LiteralString.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForLiteralStringOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalString = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((LiteralString)literalString).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, literalString, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditLiteralStringOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalString = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralStringOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralString element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalStringMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the LiteralString element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralString.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForLiteralStringOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalString = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, literalString, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveLiteralStringOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalString = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralStringOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralString element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalStringMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the LiteralString element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralString.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForLiteralStringTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalString = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((LiteralString)literalString).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, literalString, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditLiteralStringTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalString = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralStringTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralString element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalStringMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the LiteralString element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralString.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForLiteralStringTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalString = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, literalString, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveLiteralStringTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalString = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralStringTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralString element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalStringMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the LiteralString element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralString.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForLiteralStringValue() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalString = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, literalString, UMLPackage.eINSTANCE.getLiteralString_Value(), UPDATED_VALUE));
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
	public void testEditLiteralStringValue() throws Exception {

		// Import the input model
		initializeInputModel();

		literalString = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (literalString == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralStringValue();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralString element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalStringMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalStringMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalStringMetaClass, firstInstanceOf, null);

		// Change value of the value feature of the LiteralString element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LiteralString.Properties.value, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
