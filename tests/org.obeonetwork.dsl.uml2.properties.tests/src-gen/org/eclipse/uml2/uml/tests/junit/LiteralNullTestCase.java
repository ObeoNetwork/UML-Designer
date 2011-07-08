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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.LiteralNull;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for LiteralNull
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class LiteralNullTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass literalNullMetaClass = UMLPackage.eINSTANCE.getLiteralNull();

	/**
	 * The type to edit
	 */
	private EObject literalNull;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

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
	protected void initializeExpectedModelForLiteralNullName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalNull = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, literalNull, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditLiteralNullName() throws Exception {

		// Import the input model
		initializeInputModel();

		literalNull = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralNullName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralNull element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalNullMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the LiteralNull element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LiteralNull.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLiteralNullVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalNull = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, literalNull, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditLiteralNullVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		literalNull = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((LiteralNull)literalNull).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForLiteralNullVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralNull element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalNullMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the LiteralNull element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.LiteralNull.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLiteralNullClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalNull = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((LiteralNull)literalNull).getClientDependency());
		cc.append(AddCommand.create(editingDomain, literalNull, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditLiteralNullClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		literalNull = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralNullClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralNull element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalNullMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the LiteralNull element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LiteralNull.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForLiteralNullClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalNull = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)literalNull.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, literalNull, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveLiteralNullClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		literalNull = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralNullClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralNull element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalNullMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the LiteralNull element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.LiteralNull.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForLiteralNullOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalNull = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((LiteralNull)literalNull).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, literalNull, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditLiteralNullOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalNull = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralNullOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralNull element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalNullMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the LiteralNull element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralNull.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForLiteralNullOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalNull = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, literalNull, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveLiteralNullOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalNull = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralNullOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralNull element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalNullMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the LiteralNull element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralNull.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForLiteralNullTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalNull = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((LiteralNull)literalNull).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, literalNull, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditLiteralNullTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalNull = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLiteralNullTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralNull element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalNullMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the LiteralNull element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralNull.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForLiteralNullTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject literalNull = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, literalNull, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveLiteralNullTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		literalNull = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (literalNull == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLiteralNullTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the LiteralNull element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), literalNullMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(literalNullMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, literalNullMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the LiteralNull element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.LiteralNull.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
