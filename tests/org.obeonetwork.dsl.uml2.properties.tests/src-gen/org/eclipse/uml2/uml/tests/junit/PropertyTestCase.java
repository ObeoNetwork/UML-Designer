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
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Property
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class PropertyTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass propertyMetaClass = UMLPackage.eINSTANCE.getProperty();

	/**
	 * The type to edit
	 */
	private EObject property;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class aggregation
	 */
	private Object enumValueForAggregation;
	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class class
	 */
	private Object referenceValueForClass;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class subsettedProperty
	 */
	private Object referenceValueForSubsettedProperty;

	/**
	 * The reference value for the reference class datatype
	 */
	private Object referenceValueForDatatype;

	/**
	 * The reference value for the reference class associationEnd
	 */
	private Object referenceValueForAssociationEnd;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class owningAssociation
	 */
	private Object referenceValueForOwningAssociation;

	/**
	 * The reference value for the reference class association
	 */
	private Object referenceValueForAssociation;

	/**
	 * The reference value for the reference class redefinedProperty
	 */
	private Object referenceValueForRedefinedProperty;
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
	private EClass associationMetaClass = UMLPackage.eINSTANCE.getAssociation();

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
	protected void initializeExpectedModelForPropertyName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditPropertyName() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Property element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Property.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPropertyVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditPropertyVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Property)property).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForPropertyVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Property element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Property.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPropertyClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Property)property).getClientDependency());
		cc.append(AddCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditPropertyClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Property element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Property.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForPropertyClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)property.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemovePropertyClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPropertyClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Property element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Property.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPropertyIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditPropertyIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Property element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Property.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPropertyIsStatic() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getFeature_IsStatic(), UPDATED_VALUE));
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
	public void testEditPropertyIsStatic() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyIsStatic();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the isStatic feature of the Property element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Property.Properties.isStatic, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPropertyIsOrdered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), UPDATED_VALUE));
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
	public void testEditPropertyIsOrdered() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyIsOrdered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the isOrdered feature of the Property element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Property.Properties.isOrdered, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPropertyIsUnique() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), UPDATED_VALUE));
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
	public void testEditPropertyIsUnique() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyIsUnique();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the isUnique feature of the Property element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Property.Properties.isUnique, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPropertyIsReadOnly() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly(), UPDATED_VALUE));
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
	public void testEditPropertyIsReadOnly() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyIsReadOnly();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the isReadOnly feature of the Property element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Property.Properties.isReadOnly, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPropertyOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Property)property).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditPropertyOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Property element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForPropertyOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemovePropertyOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPropertyOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Property element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForPropertyTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Property)property).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditPropertyTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Property element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForPropertyTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemovePropertyTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPropertyTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Property element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForPropertyClass() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, classMetaClass);
		referenceValueForClass = bot.changeReferenceValue(allInstancesOf, ((Property)property).getClass());
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_Class(), referenceValueForClass));
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
	public void testEditPropertyClass() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyClass();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the class feature of the Property element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.class_, allInstancesOf.indexOf(referenceValueForClass)+1);

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
	protected void initializeRemoveExpectedModelForPropertyClass() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, classMetaClass);
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_Class(), null));
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
	public void testRemovePropertyClass() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPropertyClass();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the class feature of the Property element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.class_);
		

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
	protected void initializeExpectedModelForPropertyDatatype() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dataTypeMetaClass);
		referenceValueForDatatype = bot.changeReferenceValue(allInstancesOf, ((Property)property).getDatatype());
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_Datatype(), referenceValueForDatatype));
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
	public void testEditPropertyDatatype() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyDatatype();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the datatype feature of the Property element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.datatype, allInstancesOf.indexOf(referenceValueForDatatype)+1);

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
	protected void initializeRemoveExpectedModelForPropertyDatatype() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dataTypeMetaClass);
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_Datatype(), null));
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
	public void testRemovePropertyDatatype() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPropertyDatatype();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the datatype feature of the Property element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.datatype);
		

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
	protected void initializeExpectedModelForPropertyIsDerived() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_IsDerived(), UPDATED_VALUE));
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
	public void testEditPropertyIsDerived() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyIsDerived();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the isDerived feature of the Property element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Property.Properties.isDerived, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPropertyIsDerivedUnion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_IsDerivedUnion(), UPDATED_VALUE));
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
	public void testEditPropertyIsDerivedUnion() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyIsDerivedUnion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the isDerivedUnion feature of the Property element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Property.Properties.isDerivedUnion, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPropertyAggregation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_Aggregation(), UPDATED_VALUE));
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
	public void testEditPropertyAggregation() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		enumValueForAggregation = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getAggregationKind(), ((Property)property).getAggregation().getLiteral());
		// Create the expected model
		initializeExpectedModelForPropertyAggregation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the aggregation feature of the Property element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Property.Properties.aggregation, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPropertyRedefinedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForRedefinedProperty = bot.changeReferenceValue(allInstancesOf, ((Property)property).getRedefinedProperty());
		cc.append(AddCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_RedefinedProperty(), referenceValueForRedefinedProperty));
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
	public void testEditPropertyRedefinedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyRedefinedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the redefinedProperty feature of the Property element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Property.Properties.redefinedProperty, referenceValueForRedefinedProperty);

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
	protected void initializeRemoveExpectedModelForPropertyRedefinedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)property.eGet(UMLPackage.eINSTANCE.getProperty_RedefinedProperty());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_RedefinedProperty(), allReferencedInstances.get(0)));
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
	public void testRemovePropertyRedefinedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPropertyRedefinedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the redefinedProperty feature of the Property element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Property.Properties.redefinedProperty, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPropertyOwningAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		referenceValueForOwningAssociation = bot.changeReferenceValue(allInstancesOf, ((Property)property).getOwningAssociation());
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_OwningAssociation(), referenceValueForOwningAssociation));
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
	public void testEditPropertyOwningAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyOwningAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the owningAssociation feature of the Property element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.owningAssociation, allInstancesOf.indexOf(referenceValueForOwningAssociation)+1);

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
	protected void initializeRemoveExpectedModelForPropertyOwningAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_OwningAssociation(), null));
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
	public void testRemovePropertyOwningAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPropertyOwningAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the owningAssociation feature of the Property element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.owningAssociation);
		

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
	protected void initializeExpectedModelForPropertySubsettedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForSubsettedProperty = bot.changeReferenceValue(allInstancesOf, ((Property)property).getSubsettedProperty());
		cc.append(AddCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_SubsettedProperty(), referenceValueForSubsettedProperty));
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
	public void testEditPropertySubsettedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertySubsettedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the subsettedProperty feature of the Property element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Property.Properties.subsettedProperty, referenceValueForSubsettedProperty);

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
	protected void initializeRemoveExpectedModelForPropertySubsettedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)property.eGet(UMLPackage.eINSTANCE.getProperty_SubsettedProperty());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_SubsettedProperty(), allReferencedInstances.get(0)));
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
	public void testRemovePropertySubsettedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPropertySubsettedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the subsettedProperty feature of the Property element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Property.Properties.subsettedProperty, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPropertyAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		referenceValueForAssociation = bot.changeReferenceValue(allInstancesOf, ((Property)property).getAssociation());
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_Association(), referenceValueForAssociation));
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
	public void testEditPropertyAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the association feature of the Property element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.association, allInstancesOf.indexOf(referenceValueForAssociation)+1);

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
	protected void initializeRemoveExpectedModelForPropertyAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_Association(), null));
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
	public void testRemovePropertyAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPropertyAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the association feature of the Property element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.association);
		

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
	protected void initializeExpectedModelForPropertyAssociationEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForAssociationEnd = bot.changeReferenceValue(allInstancesOf, ((Property)property).getAssociationEnd());
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_AssociationEnd(), referenceValueForAssociationEnd));
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
	public void testEditPropertyAssociationEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPropertyAssociationEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the associationEnd feature of the Property element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.associationEnd, allInstancesOf.indexOf(referenceValueForAssociationEnd)+1);

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
	protected void initializeRemoveExpectedModelForPropertyAssociationEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject property = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		cc.append(SetCommand.create(editingDomain, property, UMLPackage.eINSTANCE.getProperty_AssociationEnd(), null));
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
	public void testRemovePropertyAssociationEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		property = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (property == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPropertyAssociationEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Property element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), propertyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(propertyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, propertyMetaClass, firstInstanceOf, null);

		// Change value of the associationEnd feature of the Property element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Property.Properties.associationEnd);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}










































}
