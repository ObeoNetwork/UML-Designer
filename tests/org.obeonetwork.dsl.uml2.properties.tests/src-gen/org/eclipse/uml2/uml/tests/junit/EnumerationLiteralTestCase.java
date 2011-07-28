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
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for EnumerationLiteral
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class EnumerationLiteralTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass enumerationLiteralMetaClass = UMLPackage.eINSTANCE.getEnumerationLiteral();

	/**
	 * The type to edit
	 */
	private EObject enumerationLiteral;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class classifier
	 */
	private Object referenceValueForClassifier;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class enumeration
	 */
	private Object referenceValueForEnumeration;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass classifierMetaClass = UMLPackage.eINSTANCE.getClassifier();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass enumerationMetaClass = UMLPackage.eINSTANCE.getEnumeration();

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
	protected void initializeExpectedModelForEnumerationLiteralName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, enumerationLiteral, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditEnumerationLiteralName() throws Exception {

		// Import the input model
		initializeInputModel();

		enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationLiteralName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the EnumerationLiteral element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationLiteralMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the EnumerationLiteral element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.EnumerationLiteral.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForEnumerationLiteralVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, enumerationLiteral, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditEnumerationLiteralVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((EnumerationLiteral)enumerationLiteral).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForEnumerationLiteralVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the EnumerationLiteral element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationLiteralMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the EnumerationLiteral element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.EnumerationLiteral.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForEnumerationLiteralClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((EnumerationLiteral)enumerationLiteral).getClientDependency());
		cc.append(AddCommand.create(editingDomain, enumerationLiteral, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditEnumerationLiteralClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationLiteralClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the EnumerationLiteral element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationLiteralMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the EnumerationLiteral element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.EnumerationLiteral.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForEnumerationLiteralClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)enumerationLiteral.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, enumerationLiteral, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveEnumerationLiteralClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForEnumerationLiteralClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the EnumerationLiteral element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationLiteralMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the EnumerationLiteral element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.EnumerationLiteral.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForEnumerationLiteralOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((EnumerationLiteral)enumerationLiteral).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, enumerationLiteral, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditEnumerationLiteralOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationLiteralOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the EnumerationLiteral element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationLiteralMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the EnumerationLiteral element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.EnumerationLiteral.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForEnumerationLiteralOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, enumerationLiteral, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveEnumerationLiteralOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForEnumerationLiteralOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the EnumerationLiteral element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationLiteralMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the EnumerationLiteral element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.EnumerationLiteral.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForEnumerationLiteralTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((EnumerationLiteral)enumerationLiteral).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, enumerationLiteral, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditEnumerationLiteralTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationLiteralTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the EnumerationLiteral element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationLiteralMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the EnumerationLiteral element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.EnumerationLiteral.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForEnumerationLiteralTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, enumerationLiteral, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveEnumerationLiteralTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForEnumerationLiteralTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the EnumerationLiteral element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationLiteralMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the EnumerationLiteral element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.EnumerationLiteral.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForEnumerationLiteralEnumeration() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, enumerationMetaClass);
		referenceValueForEnumeration = bot.changeReferenceValue(allInstancesOf, ((EnumerationLiteral)enumerationLiteral).getEnumeration());
		cc.append(SetCommand.create(editingDomain, enumerationLiteral, UMLPackage.eINSTANCE.getEnumerationLiteral_Enumeration(), referenceValueForEnumeration));
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
	public void testEditEnumerationLiteralEnumeration() throws Exception {

		// Import the input model
		initializeInputModel();

		enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationLiteralEnumeration();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the EnumerationLiteral element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationLiteralMetaClass, firstInstanceOf, null);

		// Change value of the enumeration feature of the EnumerationLiteral element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.EnumerationLiteral.Properties.enumeration, allInstancesOf.indexOf(referenceValueForEnumeration)+1);

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
	protected void initializeRemoveExpectedModelForEnumerationLiteralEnumeration() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, enumerationMetaClass);
		cc.append(SetCommand.create(editingDomain, enumerationLiteral, UMLPackage.eINSTANCE.getEnumerationLiteral_Enumeration(), null));
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
	public void testRemoveEnumerationLiteralEnumeration() throws Exception {

		// Import the input model
		initializeInputModel();

		enumerationLiteral = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (enumerationLiteral == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForEnumerationLiteralEnumeration();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the EnumerationLiteral element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationLiteralMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationLiteralMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationLiteralMetaClass, firstInstanceOf, null);

		// Change value of the enumeration feature of the EnumerationLiteral element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.EnumerationLiteral.Properties.enumeration);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
