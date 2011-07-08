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
 * TestCase for Port
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class PortTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass portMetaClass = UMLPackage.eINSTANCE.getPort();

	/**
	 * The type to edit
	 */
	private EObject port;

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
	 * The reference value for the reference class association
	 */
	private Object referenceValueForAssociation;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class protocol
	 */
	private Object referenceValueForProtocol;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class associationEnd
	 */
	private Object referenceValueForAssociationEnd;

	/**
	 * The reference value for the reference class datatype
	 */
	private Object referenceValueForDatatype;

	/**
	 * The reference value for the reference class subsettedProperty
	 */
	private Object referenceValueForSubsettedProperty;

	/**
	 * The reference value for the reference class class
	 */
	private Object referenceValueForClass;

	/**
	 * The reference value for the reference class redefinedPort
	 */
	private Object referenceValueForRedefinedPort;

	/**
	 * The reference value for the reference class redefinedProperty
	 */
	private Object referenceValueForRedefinedProperty;

	/**
	 * The reference value for the reference class owningAssociation
	 */
	private Object referenceValueForOwningAssociation;
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
	private EClass protocolStateMachineMetaClass = UMLPackage.eINSTANCE.getProtocolStateMachine();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass propertyMetaClass = UMLPackage.eINSTANCE.getProperty();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass dependencyMetaClass = UMLPackage.eINSTANCE.getDependency();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass classMetaClass = UMLPackage.eINSTANCE.getClass();
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
	protected void initializeExpectedModelForPortName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditPortName() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditPortVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Port)port).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForPortVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Port)port).getClientDependency());
		cc.append(AddCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditPortClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Port element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Port.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForPortClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)port.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemovePortClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Port element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Port.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPortIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditPortIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortIsStatic() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getFeature_IsStatic(), UPDATED_VALUE));
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
	public void testEditPortIsStatic() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortIsStatic();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the isStatic feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.isStatic, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortIsOrdered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), UPDATED_VALUE));
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
	public void testEditPortIsOrdered() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortIsOrdered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the isOrdered feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.isOrdered, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortIsUnique() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), UPDATED_VALUE));
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
	public void testEditPortIsUnique() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortIsUnique();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the isUnique feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.isUnique, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortIsReadOnly() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly(), UPDATED_VALUE));
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
	public void testEditPortIsReadOnly() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortIsReadOnly();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the isReadOnly feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.isReadOnly, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Port)port).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditPortOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Port element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForPortOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemovePortOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Port element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForPortTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Port)port).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditPortTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Port element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForPortTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemovePortTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Port element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForPortClass() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, classMetaClass);
		referenceValueForClass = bot.changeReferenceValue(allInstancesOf, ((Port)port).getClass());
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_Class(), referenceValueForClass));
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
	public void testEditPortClass() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortClass();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the class feature of the Port element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.class_, allInstancesOf.indexOf(referenceValueForClass)+1);

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
	protected void initializeRemoveExpectedModelForPortClass() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, classMetaClass);
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_Class(), null));
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
	public void testRemovePortClass() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortClass();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the class feature of the Port element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.class_);
		

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
	protected void initializeExpectedModelForPortDatatype() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dataTypeMetaClass);
		referenceValueForDatatype = bot.changeReferenceValue(allInstancesOf, ((Port)port).getDatatype());
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_Datatype(), referenceValueForDatatype));
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
	public void testEditPortDatatype() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortDatatype();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the datatype feature of the Port element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.datatype, allInstancesOf.indexOf(referenceValueForDatatype)+1);

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
	protected void initializeRemoveExpectedModelForPortDatatype() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dataTypeMetaClass);
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_Datatype(), null));
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
	public void testRemovePortDatatype() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortDatatype();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the datatype feature of the Port element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.datatype);
		

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
	protected void initializeExpectedModelForPortIsDerived() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_IsDerived(), UPDATED_VALUE));
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
	public void testEditPortIsDerived() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortIsDerived();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the isDerived feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.isDerived, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortIsDerivedUnion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_IsDerivedUnion(), UPDATED_VALUE));
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
	public void testEditPortIsDerivedUnion() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortIsDerivedUnion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the isDerivedUnion feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.isDerivedUnion, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortAggregation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_Aggregation(), UPDATED_VALUE));
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
	public void testEditPortAggregation() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		enumValueForAggregation = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getAggregationKind(), ((Port)port).getAggregation().getLiteral());
		// Create the expected model
		initializeExpectedModelForPortAggregation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the aggregation feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.aggregation, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortRedefinedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForRedefinedProperty = bot.changeReferenceValue(allInstancesOf, ((Port)port).getRedefinedProperty());
		cc.append(AddCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_RedefinedProperty(), referenceValueForRedefinedProperty));
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
	public void testEditPortRedefinedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortRedefinedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the redefinedProperty feature of the Port element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Port.Properties.redefinedProperty, referenceValueForRedefinedProperty);

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
	protected void initializeRemoveExpectedModelForPortRedefinedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)port.eGet(UMLPackage.eINSTANCE.getProperty_RedefinedProperty());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_RedefinedProperty(), allReferencedInstances.get(0)));
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
	public void testRemovePortRedefinedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortRedefinedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the redefinedProperty feature of the Port element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Port.Properties.redefinedProperty, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPortOwningAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		referenceValueForOwningAssociation = bot.changeReferenceValue(allInstancesOf, ((Port)port).getOwningAssociation());
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_OwningAssociation(), referenceValueForOwningAssociation));
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
	public void testEditPortOwningAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortOwningAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the owningAssociation feature of the Port element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.owningAssociation, allInstancesOf.indexOf(referenceValueForOwningAssociation)+1);

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
	protected void initializeRemoveExpectedModelForPortOwningAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_OwningAssociation(), null));
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
	public void testRemovePortOwningAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortOwningAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the owningAssociation feature of the Port element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.owningAssociation);
		

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
	protected void initializeExpectedModelForPortSubsettedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForSubsettedProperty = bot.changeReferenceValue(allInstancesOf, ((Port)port).getSubsettedProperty());
		cc.append(AddCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_SubsettedProperty(), referenceValueForSubsettedProperty));
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
	public void testEditPortSubsettedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortSubsettedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the subsettedProperty feature of the Port element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Port.Properties.subsettedProperty, referenceValueForSubsettedProperty);

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
	protected void initializeRemoveExpectedModelForPortSubsettedProperty() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)port.eGet(UMLPackage.eINSTANCE.getProperty_SubsettedProperty());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_SubsettedProperty(), allReferencedInstances.get(0)));
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
	public void testRemovePortSubsettedProperty() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortSubsettedProperty();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the subsettedProperty feature of the Port element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Port.Properties.subsettedProperty, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPortAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		referenceValueForAssociation = bot.changeReferenceValue(allInstancesOf, ((Port)port).getAssociation());
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_Association(), referenceValueForAssociation));
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
	public void testEditPortAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the association feature of the Port element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.association, allInstancesOf.indexOf(referenceValueForAssociation)+1);

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
	protected void initializeRemoveExpectedModelForPortAssociation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_Association(), null));
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
	public void testRemovePortAssociation() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortAssociation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the association feature of the Port element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.association);
		

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
	protected void initializeExpectedModelForPortAssociationEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForAssociationEnd = bot.changeReferenceValue(allInstancesOf, ((Port)port).getAssociationEnd());
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_AssociationEnd(), referenceValueForAssociationEnd));
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
	public void testEditPortAssociationEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortAssociationEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the associationEnd feature of the Port element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.associationEnd, allInstancesOf.indexOf(referenceValueForAssociationEnd)+1);

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
	protected void initializeRemoveExpectedModelForPortAssociationEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getProperty_AssociationEnd(), null));
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
	public void testRemovePortAssociationEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortAssociationEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the associationEnd feature of the Port element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.associationEnd);
		

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
	protected void initializeExpectedModelForPortIsBehavior() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getPort_IsBehavior(), UPDATED_VALUE));
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
	public void testEditPortIsBehavior() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortIsBehavior();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the isBehavior feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.isBehavior, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortIsService() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getPort_IsService(), UPDATED_VALUE));
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
	public void testEditPortIsService() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortIsService();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the isService feature of the Port element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Port.Properties.isService, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPortRedefinedPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, portMetaClass);
		referenceValueForRedefinedPort = bot.changeReferenceValue(allInstancesOf, ((Port)port).getRedefinedPort());
		cc.append(AddCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getPort_RedefinedPort(), referenceValueForRedefinedPort));
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
	public void testEditPortRedefinedPort() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortRedefinedPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the redefinedPort feature of the Port element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Port.Properties.redefinedPort, referenceValueForRedefinedPort);

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
	protected void initializeRemoveExpectedModelForPortRedefinedPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)port.eGet(UMLPackage.eINSTANCE.getPort_RedefinedPort());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getPort_RedefinedPort(), allReferencedInstances.get(0)));
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
	public void testRemovePortRedefinedPort() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortRedefinedPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the redefinedPort feature of the Port element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Port.Properties.redefinedPort, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPortProtocol() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, protocolStateMachineMetaClass);
		referenceValueForProtocol = bot.changeReferenceValue(allInstancesOf, ((Port)port).getProtocol());
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getPort_Protocol(), referenceValueForProtocol));
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
	public void testEditPortProtocol() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPortProtocol();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the protocol feature of the Port element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.protocol, allInstancesOf.indexOf(referenceValueForProtocol)+1);

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
	protected void initializeRemoveExpectedModelForPortProtocol() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject port = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, protocolStateMachineMetaClass);
		cc.append(SetCommand.create(editingDomain, port, UMLPackage.eINSTANCE.getPort_Protocol(), null));
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
	public void testRemovePortProtocol() throws Exception {

		// Import the input model
		initializeInputModel();

		port = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (port == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPortProtocol();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Port element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), portMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(portMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, portMetaClass, firstInstanceOf, null);

		// Change value of the protocol feature of the Port element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Port.Properties.protocol);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















































}
