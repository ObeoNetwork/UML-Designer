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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ExtensionEnd;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ExtensionEnd
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ExtensionEndTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass extensionEndMetaClass = UMLPackage.eINSTANCE.getExtensionEnd();

	/**
	 * The type to edit
	 */
	private EObject extensionEnd;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class aggregation
	 */
	private Object enumValueForAggregation;
	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class owningAssociation
	 */
	private Object referenceValueForOwningAssociation;

	/**
	 * The reference value for the reference class associationEnd
	 */
	private Object referenceValueForAssociationEnd;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class subsettedProperty
	 */
	private Object referenceValueForSubsettedProperty;

	/**
	 * The reference value for the reference class datatype
	 */
	private Object referenceValueForDatatype;

	/**
	 * The reference value for the reference class redefinedProperty
	 */
	private Object referenceValueForRedefinedProperty;

	/**
	 * The reference value for the reference class class
	 */
	private Object referenceValueForClass;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class association
	 */
	private Object referenceValueForAssociation;
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
	private EClass propertyMetaClass = UMLPackage.eINSTANCE.getProperty();

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
	protected void initializeExpectedModelForExtensionEndName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditExtensionEndName() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ExtensionEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionEndVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditExtensionEndVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ExtensionEnd)extensionEnd).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForExtensionEndVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ExtensionEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionEndClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ExtensionEnd)extensionEnd).getClientDependency());
		cc.append(AddCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditExtensionEndClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExtensionEnd element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForExtensionEndClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)extensionEnd.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveExtensionEndClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionEndClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExtensionEnd element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExtensionEndIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditExtensionEndIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ExtensionEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionEndIsStatic() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getFeature_IsStatic(), UPDATED_VALUE));
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
	public void testEditExtensionEndIsStatic() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndIsStatic();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the isStatic feature of the ExtensionEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.isStatic, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionEndIsOrdered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), UPDATED_VALUE));
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
	public void testEditExtensionEndIsOrdered() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndIsOrdered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the isOrdered feature of the ExtensionEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.isOrdered, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionEndIsUnique() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), UPDATED_VALUE));
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
	public void testEditExtensionEndIsUnique() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndIsUnique();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the isUnique feature of the ExtensionEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.isUnique, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionEndIsReadOnly() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly(), UPDATED_VALUE));
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
	public void testEditExtensionEndIsReadOnly() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndIsReadOnly();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the isReadOnly feature of the ExtensionEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.isReadOnly, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionEndOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((ExtensionEnd)extensionEnd).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditExtensionEndOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the ExtensionEnd element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForExtensionEndOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveExtensionEndOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionEndOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the ExtensionEnd element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForExtensionEndTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((ExtensionEnd)extensionEnd).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditExtensionEndTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the ExtensionEnd element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForExtensionEndTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveExtensionEndTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionEndTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the ExtensionEnd element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForExtensionEndClass() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, classMetaClass);
		referenceValueForClass = bot.changeReferenceValue(allInstancesOf, ((ExtensionEnd)extensionEnd).getClass());
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_Class(), referenceValueForClass));
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
	public void testEditExtensionEndClass() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndClass();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the class feature of the ExtensionEnd element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.class_, allInstancesOf.indexOf(referenceValueForClass)+1);

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
	protected void initializeRemoveExpectedModelForExtensionEndClass() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, classMetaClass);
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_Class(), null));
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
	public void testRemoveExtensionEndClass() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionEndClass();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the class feature of the ExtensionEnd element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.class_);
		

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
	protected void initializeExpectedModelForExtensionEndDatatype() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dataTypeMetaClass);
		referenceValueForDatatype = bot.changeReferenceValue(allInstancesOf, ((ExtensionEnd)extensionEnd).getDatatype());
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_Datatype(), referenceValueForDatatype));
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
	public void testEditExtensionEndDatatype() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndDatatype();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the datatype feature of the ExtensionEnd element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.datatype, allInstancesOf.indexOf(referenceValueForDatatype)+1);

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
	protected void initializeRemoveExpectedModelForExtensionEndDatatype() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dataTypeMetaClass);
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_Datatype(), null));
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
	public void testRemoveExtensionEndDatatype() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionEndDatatype();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the datatype feature of the ExtensionEnd element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.datatype);
		

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
	protected void initializeExpectedModelForExtensionEndIsDerived() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_IsDerived(), UPDATED_VALUE));
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
	public void testEditExtensionEndIsDerived() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndIsDerived();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the isDerived feature of the ExtensionEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.isDerived, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionEndIsDerivedUnion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_IsDerivedUnion(), UPDATED_VALUE));
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
	public void testEditExtensionEndIsDerivedUnion() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndIsDerivedUnion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the isDerivedUnion feature of the ExtensionEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.isDerivedUnion, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionEndAggregation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_Aggregation(), UPDATED_VALUE));
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
	public void testEditExtensionEndAggregation() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		enumValueForAggregation = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getAggregationKind(), ((ExtensionEnd)extensionEnd).getAggregation().getLiteral());
		// Create the expected model
		initializeExpectedModelForExtensionEndAggregation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the aggregation feature of the ExtensionEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.aggregation, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionEndRedefinedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForRedefinedProperty = bot.changeReferenceValue(allInstancesOf, ((ExtensionEnd)extensionEnd).getRedefinedProperty());
		cc.append(AddCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_RedefinedProperty(), referenceValueForRedefinedProperty));
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
	public void testEditExtensionEndRedefinedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndRedefinedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the redefinedProperty feature of the ExtensionEnd element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.redefinedProperty, referenceValueForRedefinedProperty);

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
	protected void initializeRemoveExpectedModelForExtensionEndRedefinedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)extensionEnd.eGet(UMLPackage.eINSTANCE.getProperty_RedefinedProperty());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_RedefinedProperty(), allReferencedInstances.get(0)));
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
	public void testRemoveExtensionEndRedefinedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionEndRedefinedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the redefinedProperty feature of the ExtensionEnd element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.redefinedProperty, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExtensionEndOwningAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		referenceValueForOwningAssociation = bot.changeReferenceValue(allInstancesOf, ((ExtensionEnd)extensionEnd).getOwningAssociation());
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_OwningAssociation(), referenceValueForOwningAssociation));
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
	public void testEditExtensionEndOwningAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndOwningAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the owningAssociation feature of the ExtensionEnd element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.owningAssociation, allInstancesOf.indexOf(referenceValueForOwningAssociation)+1);

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
	protected void initializeRemoveExpectedModelForExtensionEndOwningAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_OwningAssociation(), null));
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
	public void testRemoveExtensionEndOwningAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionEndOwningAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the owningAssociation feature of the ExtensionEnd element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.owningAssociation);
		

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
	protected void initializeExpectedModelForExtensionEndSubsettedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForSubsettedProperty = bot.changeReferenceValue(allInstancesOf, ((ExtensionEnd)extensionEnd).getSubsettedProperty());
		cc.append(AddCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_SubsettedProperty(), referenceValueForSubsettedProperty));
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
	public void testEditExtensionEndSubsettedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndSubsettedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the subsettedProperty feature of the ExtensionEnd element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.subsettedProperty, referenceValueForSubsettedProperty);

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
	protected void initializeRemoveExpectedModelForExtensionEndSubsettedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)extensionEnd.eGet(UMLPackage.eINSTANCE.getProperty_SubsettedProperty());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_SubsettedProperty(), allReferencedInstances.get(0)));
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
	public void testRemoveExtensionEndSubsettedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionEndSubsettedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the subsettedProperty feature of the ExtensionEnd element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.subsettedProperty, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExtensionEndAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		referenceValueForAssociation = bot.changeReferenceValue(allInstancesOf, ((ExtensionEnd)extensionEnd).getAssociation());
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_Association(), referenceValueForAssociation));
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
	public void testEditExtensionEndAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the association feature of the ExtensionEnd element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.association, allInstancesOf.indexOf(referenceValueForAssociation)+1);

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
	protected void initializeRemoveExpectedModelForExtensionEndAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_Association(), null));
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
	public void testRemoveExtensionEndAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionEndAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the association feature of the ExtensionEnd element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.association);
		

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
	protected void initializeExpectedModelForExtensionEndAssociationEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForAssociationEnd = bot.changeReferenceValue(allInstancesOf, ((ExtensionEnd)extensionEnd).getAssociationEnd());
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_AssociationEnd(), referenceValueForAssociationEnd));
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
	public void testEditExtensionEndAssociationEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionEndAssociationEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the associationEnd feature of the ExtensionEnd element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.associationEnd, allInstancesOf.indexOf(referenceValueForAssociationEnd)+1);

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
	protected void initializeRemoveExpectedModelForExtensionEndAssociationEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		cc.append(SetCommand.create(editingDomain, extensionEnd, UMLPackage.eINSTANCE.getProperty_AssociationEnd(), null));
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
	public void testRemoveExtensionEndAssociationEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (extensionEnd == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionEndAssociationEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionEndMetaClass, firstInstanceOf, null);

		// Change value of the associationEnd feature of the ExtensionEnd element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionEnd.Properties.associationEnd);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}










































}
