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
import org.eclipse.uml2.uml.ConnectionPointReference;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ConnectionPointReference
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ConnectionPointReferenceTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass connectionPointReferenceMetaClass = UMLPackage.eINSTANCE.getConnectionPointReference();

	/**
	 * The type to edit
	 */
	private EObject connectionPointReference;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class state
	 */
	private Object referenceValueForState;

	/**
	 * The reference value for the reference class exit
	 */
	private Object referenceValueForExit;

	/**
	 * The reference value for the reference class entry
	 */
	private Object referenceValueForEntry;

	/**
	 * The reference value for the reference class container
	 */
	private Object referenceValueForContainer;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass regionMetaClass = UMLPackage.eINSTANCE.getRegion();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass stateMetaClass = UMLPackage.eINSTANCE.getState();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass pseudostateMetaClass = UMLPackage.eINSTANCE.getPseudostate();

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
	protected void initializeExpectedModelForConnectionPointReferenceName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditConnectionPointReferenceName() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectionPointReferenceName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ConnectionPointReference element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForConnectionPointReferenceVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditConnectionPointReferenceVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ConnectionPointReference)connectionPointReference).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForConnectionPointReferenceVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ConnectionPointReference element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForConnectionPointReferenceClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ConnectionPointReference)connectionPointReference).getClientDependency());
		cc.append(AddCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditConnectionPointReferenceClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectionPointReferenceClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ConnectionPointReference element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForConnectionPointReferenceClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)connectionPointReference.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveConnectionPointReferenceClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConnectionPointReferenceClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ConnectionPointReference element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForConnectionPointReferenceContainer() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		referenceValueForContainer = bot.changeReferenceValue(allInstancesOf, ((ConnectionPointReference)connectionPointReference).getContainer());
		cc.append(SetCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getVertex_Container(), referenceValueForContainer));
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
	public void testEditConnectionPointReferenceContainer() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectionPointReferenceContainer();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the container feature of the ConnectionPointReference element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.container, allInstancesOf.indexOf(referenceValueForContainer)+1);

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
	protected void initializeRemoveExpectedModelForConnectionPointReferenceContainer() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		cc.append(SetCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getVertex_Container(), null));
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
	public void testRemoveConnectionPointReferenceContainer() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConnectionPointReferenceContainer();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the container feature of the ConnectionPointReference element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.container);
		

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
	protected void initializeExpectedModelForConnectionPointReferenceEntry() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, pseudostateMetaClass);
		referenceValueForEntry = bot.changeReferenceValue(allInstancesOf, ((ConnectionPointReference)connectionPointReference).getEntry());
		cc.append(AddCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getConnectionPointReference_Entry(), referenceValueForEntry));
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
	public void testEditConnectionPointReferenceEntry() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectionPointReferenceEntry();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the entry feature of the ConnectionPointReference element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.entry, referenceValueForEntry);

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
	protected void initializeRemoveExpectedModelForConnectionPointReferenceEntry() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)connectionPointReference.eGet(UMLPackage.eINSTANCE.getConnectionPointReference_Entry());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getConnectionPointReference_Entry(), allReferencedInstances.get(0)));
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
	public void testRemoveConnectionPointReferenceEntry() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConnectionPointReferenceEntry();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the entry feature of the ConnectionPointReference element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.entry, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForConnectionPointReferenceExit() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, pseudostateMetaClass);
		referenceValueForExit = bot.changeReferenceValue(allInstancesOf, ((ConnectionPointReference)connectionPointReference).getExit());
		cc.append(AddCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getConnectionPointReference_Exit(), referenceValueForExit));
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
	public void testEditConnectionPointReferenceExit() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectionPointReferenceExit();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the exit feature of the ConnectionPointReference element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.exit, referenceValueForExit);

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
	protected void initializeRemoveExpectedModelForConnectionPointReferenceExit() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)connectionPointReference.eGet(UMLPackage.eINSTANCE.getConnectionPointReference_Exit());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getConnectionPointReference_Exit(), allReferencedInstances.get(0)));
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
	public void testRemoveConnectionPointReferenceExit() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConnectionPointReferenceExit();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the exit feature of the ConnectionPointReference element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.exit, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForConnectionPointReferenceState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForState = bot.changeReferenceValue(allInstancesOf, ((ConnectionPointReference)connectionPointReference).getState());
		cc.append(SetCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getConnectionPointReference_State(), referenceValueForState));
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
	public void testEditConnectionPointReferenceState() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConnectionPointReferenceState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the state feature of the ConnectionPointReference element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.state, allInstancesOf.indexOf(referenceValueForState)+1);

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
	protected void initializeRemoveExpectedModelForConnectionPointReferenceState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		cc.append(SetCommand.create(editingDomain, connectionPointReference, UMLPackage.eINSTANCE.getConnectionPointReference_State(), null));
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
	public void testRemoveConnectionPointReferenceState() throws Exception {

		// Import the input model
		initializeInputModel();

		connectionPointReference = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (connectionPointReference == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConnectionPointReferenceState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConnectionPointReference element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), connectionPointReferenceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(connectionPointReferenceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, connectionPointReferenceMetaClass, firstInstanceOf, null);

		// Change value of the state feature of the ConnectionPointReference element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ConnectionPointReference.Properties.state);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
