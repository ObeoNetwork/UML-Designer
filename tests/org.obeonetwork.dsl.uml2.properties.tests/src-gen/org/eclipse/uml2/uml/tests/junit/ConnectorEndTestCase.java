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
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
/**
 * TestCase for ConnectorEnd
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ConnectorEndTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass connectorEndMetaClass = UMLPackage.eINSTANCE.getConnectorEnd();

	/**
	 * The type to edit
	 */
	private EObject connectorEnd;

	/**
	 * The reference value for the reference class partWithPort
	 */
	private Object referenceValueForPartWithPort;

	/**
	 * The reference value for the reference class role
	 */
	private Object referenceValueForRole;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass connectableElementMetaClass = UMLPackage.eINSTANCE.getConnectableElement();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass propertyMetaClass = UMLPackage.eINSTANCE.getProperty();
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
	protected void initializeExpectedModelForConnectorEndIsOrdered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectorEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorEndMetaClass);
		if (connectorEnd == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, connectorEnd, UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), UPDATED_VALUE));
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
	public void testEditConnectorEndIsOrdered() throws Exception {

		// Import the input model
		initializeInputModel();

		connectorEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorEndMetaClass);
		if (connectorEnd == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectorEndIsOrdered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectorEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectorEndMetaClass, firstInstanceOf, null);

		// Change value of the isOrdered feature of the ConnectorEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ConnectorEnd.Properties.isOrdered, UPDATED_VALUE);

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
	protected void initializeExpectedModelForConnectorEndIsUnique() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectorEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorEndMetaClass);
		if (connectorEnd == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, connectorEnd, UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), UPDATED_VALUE));
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
	public void testEditConnectorEndIsUnique() throws Exception {

		// Import the input model
		initializeInputModel();

		connectorEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorEndMetaClass);
		if (connectorEnd == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectorEndIsUnique();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectorEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectorEndMetaClass, firstInstanceOf, null);

		// Change value of the isUnique feature of the ConnectorEnd element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ConnectorEnd.Properties.isUnique, UPDATED_VALUE);

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
	protected void initializeExpectedModelForConnectorEndPartWithPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectorEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorEndMetaClass);
		if (connectorEnd == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForPartWithPort = bot.changeReferenceValue(allInstancesOf, ((ConnectorEnd)connectorEnd).getPartWithPort());
		cc.append(SetCommand.create(editingDomain, connectorEnd, UMLPackage.eINSTANCE.getConnectorEnd_PartWithPort(), referenceValueForPartWithPort));
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
	public void testEditConnectorEndPartWithPort() throws Exception {

		// Import the input model
		initializeInputModel();

		connectorEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorEndMetaClass);
		if (connectorEnd == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectorEndPartWithPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectorEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectorEndMetaClass, firstInstanceOf, null);

		// Change value of the partWithPort feature of the ConnectorEnd element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ConnectorEnd.Properties.partWithPort, allInstancesOf.indexOf(referenceValueForPartWithPort)+1);

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
	protected void initializeRemoveExpectedModelForConnectorEndPartWithPort() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectorEnd = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectorEndMetaClass);
		if (connectorEnd == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		cc.append(SetCommand.create(editingDomain, connectorEnd, UMLPackage.eINSTANCE.getConnectorEnd_PartWithPort(), null));
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
	public void testRemoveConnectorEndPartWithPort() throws Exception {

		// Import the input model
		initializeInputModel();

		connectorEnd = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorEndMetaClass);
		if (connectorEnd == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConnectorEndPartWithPort();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectorEnd element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectorEndMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectorEndMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectorEndMetaClass, firstInstanceOf, null);

		// Change value of the partWithPort feature of the ConnectorEnd element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ConnectorEnd.Properties.partWithPort);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}








}
