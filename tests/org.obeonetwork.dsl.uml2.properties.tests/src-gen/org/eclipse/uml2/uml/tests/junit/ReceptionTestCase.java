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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Reception;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Reception
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ReceptionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass receptionMetaClass = UMLPackage.eINSTANCE.getReception();

	/**
	 * The type to edit
	 */
	private EObject reception;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class concurrency
	 */
	private Object enumValueForConcurrency;
	/**
	 * The reference value for the reference class signal
	 */
	private Object referenceValueForSignal;

	/**
	 * The reference value for the reference class raisedException
	 */
	private Object referenceValueForRaisedException;

	/**
	 * The reference value for the reference class method
	 */
	private Object referenceValueForMethod;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass behaviorMetaClass = UMLPackage.eINSTANCE.getBehavior();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass signalMetaClass = UMLPackage.eINSTANCE.getSignal();

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
	protected void initializeExpectedModelForReceptionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reception = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, reception, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditReceptionName() throws Exception {

		// Import the input model
		initializeInputModel();

		reception = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReceptionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Reception element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, receptionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Reception element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Reception.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReceptionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reception = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, reception, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditReceptionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		reception = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Reception)reception).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForReceptionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Reception element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, receptionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Reception element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Reception.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReceptionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reception = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Reception)reception).getClientDependency());
		cc.append(AddCommand.create(editingDomain, reception, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditReceptionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		reception = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReceptionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Reception element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, receptionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Reception element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Reception.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForReceptionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reception = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)reception.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, reception, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveReceptionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		reception = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReceptionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Reception element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, receptionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Reception element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Reception.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForReceptionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reception = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, reception, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditReceptionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		reception = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReceptionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Reception element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, receptionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Reception element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Reception.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReceptionIsStatic() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reception = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, reception, UMLPackage.eINSTANCE.getFeature_IsStatic(), UPDATED_VALUE));
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
	public void testEditReceptionIsStatic() throws Exception {

		// Import the input model
		initializeInputModel();

		reception = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReceptionIsStatic();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Reception element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, receptionMetaClass, firstInstanceOf, null);

		// Change value of the isStatic feature of the Reception element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Reception.Properties.isStatic, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReceptionIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reception = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, reception, UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract(), UPDATED_VALUE));
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
	public void testEditReceptionIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		reception = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReceptionIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Reception element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, receptionMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the Reception element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Reception.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReceptionConcurrency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reception = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, reception, UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency(), UPDATED_VALUE));
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
	public void testEditReceptionConcurrency() throws Exception {

		// Import the input model
		initializeInputModel();

		reception = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		enumValueForConcurrency = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getCallConcurrencyKind(), ((Reception)reception).getConcurrency().getLiteral());
		// Create the expected model
		initializeExpectedModelForReceptionConcurrency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Reception element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, receptionMetaClass, firstInstanceOf, null);

		// Change value of the concurrency feature of the Reception element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Reception.Properties.concurrency, UPDATED_VALUE);

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
	protected void initializeExpectedModelForReceptionSignal() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reception = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, signalMetaClass);
		referenceValueForSignal = bot.changeReferenceValue(allInstancesOf, ((Reception)reception).getSignal());
		cc.append(SetCommand.create(editingDomain, reception, UMLPackage.eINSTANCE.getReception_Signal(), referenceValueForSignal));
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
	public void testEditReceptionSignal() throws Exception {

		// Import the input model
		initializeInputModel();

		reception = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReceptionSignal();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Reception element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, receptionMetaClass, firstInstanceOf, null);

		// Change value of the signal feature of the Reception element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Reception.Properties.signal, allInstancesOf.indexOf(referenceValueForSignal)+1);

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
	protected void initializeRemoveExpectedModelForReceptionSignal() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reception = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, signalMetaClass);
		cc.append(SetCommand.create(editingDomain, reception, UMLPackage.eINSTANCE.getReception_Signal(), null));
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
	public void testRemoveReceptionSignal() throws Exception {

		// Import the input model
		initializeInputModel();

		reception = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (reception == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReceptionSignal();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Reception element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), receptionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(receptionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, receptionMetaClass, firstInstanceOf, null);

		// Change value of the signal feature of the Reception element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Reception.Properties.signal);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
