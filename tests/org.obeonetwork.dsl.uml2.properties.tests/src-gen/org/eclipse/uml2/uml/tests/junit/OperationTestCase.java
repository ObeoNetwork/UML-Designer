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
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Operation
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class OperationTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass operationMetaClass = UMLPackage.eINSTANCE.getOperation();

	/**
	 * The type to edit
	 */
	private EObject operation;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class concurrency
	 */
	private Object enumValueForConcurrency;
	/**
	 * The reference value for the reference class method
	 */
	private Object referenceValueForMethod;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class redefinedOperation
	 */
	private Object referenceValueForRedefinedOperation;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class datatype
	 */
	private Object referenceValueForDatatype;

	/**
	 * The reference value for the reference class bodyCondition
	 */
	private Object referenceValueForBodyCondition;

	/**
	 * The reference value for the reference class raisedException
	 */
	private Object referenceValueForRaisedException;

	/**
	 * The reference value for the reference class postcondition
	 */
	private Object referenceValueForPostcondition;

	/**
	 * The reference value for the reference class interface
	 */
	private Object referenceValueForInterface;

	/**
	 * The reference value for the reference class precondition
	 */
	private Object referenceValueForPrecondition;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class class
	 */
	private Object referenceValueForClass;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass interfaceMetaClass = UMLPackage.eINSTANCE.getInterface();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass dataTypeMetaClass = UMLPackage.eINSTANCE.getDataType();

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
	private EClass classMetaClass = UMLPackage.eINSTANCE.getClass();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass constraintMetaClass = UMLPackage.eINSTANCE.getConstraint();

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
	protected void initializeExpectedModelForOperationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditOperationName() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Operation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Operation.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOperationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditOperationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Operation)operation).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForOperationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Operation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Operation.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOperationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Operation)operation).getClientDependency());
		cc.append(AddCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditOperationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Operation element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Operation.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForOperationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)operation.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveOperationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOperationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Operation element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Operation.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOperationIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditOperationIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Operation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Operation.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOperationIsStatic() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getFeature_IsStatic(), UPDATED_VALUE));
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
	public void testEditOperationIsStatic() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationIsStatic();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the isStatic feature of the Operation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Operation.Properties.isStatic, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOperationIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract(), UPDATED_VALUE));
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
	public void testEditOperationIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the Operation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Operation.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOperationConcurrency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency(), UPDATED_VALUE));
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
	public void testEditOperationConcurrency() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		enumValueForConcurrency = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getCallConcurrencyKind(), ((Operation)operation).getConcurrency().getLiteral());
		// Create the expected model
		initializeExpectedModelForOperationConcurrency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the concurrency feature of the Operation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Operation.Properties.concurrency, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOperationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Operation)operation).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditOperationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Operation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForOperationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveOperationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOperationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Operation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForOperationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Operation)operation).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditOperationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Operation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForOperationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveOperationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOperationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Operation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForOperationInterface() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interfaceMetaClass);
		referenceValueForInterface = bot.changeReferenceValue(allInstancesOf, ((Operation)operation).getInterface());
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_Interface(), referenceValueForInterface));
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
	public void testEditOperationInterface() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationInterface();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the interface feature of the Operation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.interface_, allInstancesOf.indexOf(referenceValueForInterface)+1);

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
	protected void initializeRemoveExpectedModelForOperationInterface() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interfaceMetaClass);
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_Interface(), null));
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
	public void testRemoveOperationInterface() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOperationInterface();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the interface feature of the Operation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.interface_);
		

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
	protected void initializeExpectedModelForOperationClass() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, classMetaClass);
		referenceValueForClass = bot.changeReferenceValue(allInstancesOf, ((Operation)operation).getClass());
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_Class(), referenceValueForClass));
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
	public void testEditOperationClass() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationClass();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the class feature of the Operation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.class_, allInstancesOf.indexOf(referenceValueForClass)+1);

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
	protected void initializeRemoveExpectedModelForOperationClass() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, classMetaClass);
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_Class(), null));
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
	public void testRemoveOperationClass() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOperationClass();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the class feature of the Operation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.class_);
		

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
	protected void initializeExpectedModelForOperationIsQuery() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_IsQuery(), UPDATED_VALUE));
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
	public void testEditOperationIsQuery() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationIsQuery();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the isQuery feature of the Operation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Operation.Properties.isQuery, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOperationPrecondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPrecondition = bot.changeReferenceValue(allInstancesOf, ((Operation)operation).getPrecondition());
		cc.append(AddCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_Precondition(), referenceValueForPrecondition));
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
	public void testEditOperationPrecondition() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationPrecondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the precondition feature of the Operation element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Operation.Properties.precondition, referenceValueForPrecondition);

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
	protected void initializeRemoveExpectedModelForOperationPrecondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)operation.eGet(UMLPackage.eINSTANCE.getOperation_Precondition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_Precondition(), allReferencedInstances.get(0)));
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
	public void testRemoveOperationPrecondition() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOperationPrecondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the precondition feature of the Operation element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Operation.Properties.precondition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOperationPostcondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPostcondition = bot.changeReferenceValue(allInstancesOf, ((Operation)operation).getPostcondition());
		cc.append(AddCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_Postcondition(), referenceValueForPostcondition));
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
	public void testEditOperationPostcondition() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationPostcondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the postcondition feature of the Operation element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Operation.Properties.postcondition, referenceValueForPostcondition);

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
	protected void initializeRemoveExpectedModelForOperationPostcondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)operation.eGet(UMLPackage.eINSTANCE.getOperation_Postcondition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_Postcondition(), allReferencedInstances.get(0)));
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
	public void testRemoveOperationPostcondition() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOperationPostcondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the postcondition feature of the Operation element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Operation.Properties.postcondition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOperationRedefinedOperation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, operationMetaClass);
		referenceValueForRedefinedOperation = bot.changeReferenceValue(allInstancesOf, ((Operation)operation).getRedefinedOperation());
		cc.append(AddCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_RedefinedOperation(), referenceValueForRedefinedOperation));
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
	public void testEditOperationRedefinedOperation() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationRedefinedOperation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the redefinedOperation feature of the Operation element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Operation.Properties.redefinedOperation, referenceValueForRedefinedOperation);

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
	protected void initializeRemoveExpectedModelForOperationRedefinedOperation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)operation.eGet(UMLPackage.eINSTANCE.getOperation_RedefinedOperation());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_RedefinedOperation(), allReferencedInstances.get(0)));
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
	public void testRemoveOperationRedefinedOperation() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOperationRedefinedOperation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the redefinedOperation feature of the Operation element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Operation.Properties.redefinedOperation, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOperationDatatype() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dataTypeMetaClass);
		referenceValueForDatatype = bot.changeReferenceValue(allInstancesOf, ((Operation)operation).getDatatype());
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_Datatype(), referenceValueForDatatype));
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
	public void testEditOperationDatatype() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationDatatype();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the datatype feature of the Operation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.datatype, allInstancesOf.indexOf(referenceValueForDatatype)+1);

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
	protected void initializeRemoveExpectedModelForOperationDatatype() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dataTypeMetaClass);
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_Datatype(), null));
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
	public void testRemoveOperationDatatype() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOperationDatatype();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the datatype feature of the Operation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.datatype);
		

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
	protected void initializeExpectedModelForOperationBodyCondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForBodyCondition = bot.changeReferenceValue(allInstancesOf, ((Operation)operation).getBodyCondition());
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_BodyCondition(), referenceValueForBodyCondition));
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
	public void testEditOperationBodyCondition() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOperationBodyCondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the bodyCondition feature of the Operation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.bodyCondition, allInstancesOf.indexOf(referenceValueForBodyCondition)+1);

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
	protected void initializeRemoveExpectedModelForOperationBodyCondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject operation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		cc.append(SetCommand.create(editingDomain, operation, UMLPackage.eINSTANCE.getOperation_BodyCondition(), null));
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
	public void testRemoveOperationBodyCondition() throws Exception {

		// Import the input model
		initializeInputModel();

		operation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (operation == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOperationBodyCondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Operation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), operationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(operationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, operationMetaClass, firstInstanceOf, null);

		// Change value of the bodyCondition feature of the Operation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Operation.Properties.bodyCondition);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




































}
