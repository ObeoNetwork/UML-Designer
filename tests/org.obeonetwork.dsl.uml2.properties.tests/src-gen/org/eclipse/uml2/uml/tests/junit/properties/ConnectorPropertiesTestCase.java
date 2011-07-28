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
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Connector
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ConnectorPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass connectorMetaClass = UMLPackage.eINSTANCE.getConnector();

	/**
	 * The type to edit
	 */
	private EObject connector;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class kind
	 */
	private Object enumValueForKind;
	/**
	 * The reference value for the reference class redefinedConnector
	 */
	private Object referenceValueForRedefinedConnector;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class contract
	 */
	private Object referenceValueForContract;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass behaviorMetaClass = UMLPackage.eINSTANCE.getBehavior();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass associationMetaClass = UMLPackage.eINSTANCE.getAssociation();

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
	protected void initializeExpectedModelForConnectorName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connector = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, connector, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditConnectorName() throws Exception {

		// Import the input model
		initializeInputModel();

		connector = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectorName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Connector element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the Connector element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Connector.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConnectorVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connector = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, connector, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditConnectorVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		connector = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Connector)connector).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForConnectorVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Connector element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the Connector element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Connector.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConnectorClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connector = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Connector)connector).getClientDependency());
		cc.append(AddCommand.create(editingDomain, connector, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditConnectorClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		connector = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectorClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Connector element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Connector element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.Connector.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForConnectorClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connector = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)connector.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, connector, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveConnectorClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		connector = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConnectorClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Connector element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Connector element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.Connector.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConnectorIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connector = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, connector, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditConnectorIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		connector = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectorIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Connector element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Connector element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Connector.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConnectorIsStatic() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connector = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, connector, UMLPackage.eINSTANCE.getFeature_IsStatic(), UPDATED_VALUE));
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
	public void testEditConnectorIsStatic() throws Exception {

		// Import the input model
		initializeInputModel();

		connector = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectorIsStatic();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Connector element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isStatic feature of the Connector element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Connector.Properties.isStatic, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConnectorType() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connector = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		referenceValueForType = bot.changeReferenceValue(allInstancesOf, ((Connector)connector).getType());
		cc.append(SetCommand.create(editingDomain, connector, UMLPackage.eINSTANCE.getConnector_Type(), referenceValueForType));
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
	public void testEditConnectorType() throws Exception {

		// Import the input model
		initializeInputModel();

		connector = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectorType();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Connector element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the type feature of the Connector element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.Connector.Properties.type, allInstancesOf.indexOf(referenceValueForType)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForConnectorType() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connector = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, associationMetaClass);
		cc.append(SetCommand.create(editingDomain, connector, UMLPackage.eINSTANCE.getConnector_Type(), null));
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
	public void testRemoveConnectorType() throws Exception {

		// Import the input model
		initializeInputModel();

		connector = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConnectorType();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Connector element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the type feature of the Connector element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.Connector.Properties.type, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForConnectorRedefinedConnector() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connector = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, connectorMetaClass);
		referenceValueForRedefinedConnector = bot.changeReferenceValue(allInstancesOf, ((Connector)connector).getRedefinedConnector());
		cc.append(AddCommand.create(editingDomain, connector, UMLPackage.eINSTANCE.getConnector_RedefinedConnector(), referenceValueForRedefinedConnector));
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
	public void testEditConnectorRedefinedConnector() throws Exception {

		// Import the input model
		initializeInputModel();

		connector = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectorRedefinedConnector();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Connector element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the redefinedConnector feature of the Connector element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.Connector.Properties.redefinedConnector, referenceValueForRedefinedConnector, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForConnectorRedefinedConnector() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connector = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)connector.eGet(UMLPackage.eINSTANCE.getConnector_RedefinedConnector());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, connector, UMLPackage.eINSTANCE.getConnector_RedefinedConnector(), allReferencedInstances.get(0)));
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
	public void testRemoveConnectorRedefinedConnector() throws Exception {

		// Import the input model
		initializeInputModel();

		connector = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConnectorRedefinedConnector();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Connector element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the redefinedConnector feature of the Connector element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.Connector.Properties.redefinedConnector, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForConnectorKind() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connector = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, connector, UMLPackage.eINSTANCE.getConnector_Kind(), UPDATED_VALUE));
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
	public void testEditConnectorKind() throws Exception {

		// Import the input model
		initializeInputModel();

		connector = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (connector == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());

		enumValueForKind = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getConnectorKind(), ((Connector)connector).getKind().getLiteral());
		// Create the expected model
		initializeExpectedModelForConnectorKind();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Connector element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the kind feature of the Connector element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Connector.Properties.kind, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
