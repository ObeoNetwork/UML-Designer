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
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for DataType
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class DataTypeTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass dataTypeMetaClass = UMLPackage.eINSTANCE.getDataType();

	/**
	 * The type to edit
	 */
	private EObject dataType;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass generalizationSetMetaClass = UMLPackage.eINSTANCE.getGeneralizationSet();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass useCaseMetaClass = UMLPackage.eINSTANCE.getUseCase();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass collaborationUseMetaClass = UMLPackage.eINSTANCE.getCollaborationUse();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass classifierMetaClass = UMLPackage.eINSTANCE.getClassifier();

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
	protected void initializeExpectedModelForDataTypeName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditDataTypeName() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataTypeName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the DataType element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DataType.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDataTypeVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditDataTypeVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((DataType)dataType).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForDataTypeVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the DataType element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DataType.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDataTypeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((DataType)dataType).getClientDependency());
		cc.append(AddCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditDataTypeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataTypeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DataType element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataType.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForDataTypeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)dataType.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveDataTypeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataTypeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DataType element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataType.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDataTypeIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditDataTypeIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataTypeIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the DataType element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DataType.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDataTypeOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((DataType)dataType).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditDataTypeOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataTypeOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the DataType element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DataType.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDataTypeOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveDataTypeOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataTypeOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the DataType element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DataType.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForDataTypeTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((DataType)dataType).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditDataTypeTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataTypeTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the DataType element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DataType.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDataTypeTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveDataTypeTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataTypeTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the DataType element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DataType.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForDataTypeIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditDataTypeIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataTypeIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the DataType element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DataType.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDataTypePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((DataType)dataType).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditDataTypePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataTypePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the DataType element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataType.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForDataTypePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)dataType.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveDataTypePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataTypePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the DataType element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataType.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDataTypeRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((DataType)dataType).getRepresentation());
		cc.append(SetCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditDataTypeRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataTypeRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the DataType element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DataType.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForDataTypeRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveDataTypeRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataTypeRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the DataType element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DataType.Properties.representation);
		

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
	protected void initializeExpectedModelForDataTypeUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((DataType)dataType).getUseCase());
		cc.append(AddCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditDataTypeUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDataTypeUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the DataType element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataType.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForDataTypeUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dataType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)dataType.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, dataType, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveDataTypeUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		dataType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (dataType == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDataTypeUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DataType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dataTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dataTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dataTypeMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the DataType element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DataType.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
