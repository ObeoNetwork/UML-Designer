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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Gate;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Gate
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class GateTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass gateMetaClass = UMLPackage.eINSTANCE.getGate();

	/**
	 * The type to edit
	 */
	private EObject gate;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class message
	 */
	private Object referenceValueForMessage;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass messageMetaClass = UMLPackage.eINSTANCE.getMessage();

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
	protected void initializeExpectedModelForGateName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject gate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, gate, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditGateName() throws Exception {

		// Import the input model
		initializeInputModel();

		gate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGateName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Gate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, gateMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Gate element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Gate.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForGateVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject gate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, gate, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditGateVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		gate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Gate)gate).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForGateVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Gate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, gateMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Gate element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Gate.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForGateClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject gate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Gate)gate).getClientDependency());
		cc.append(AddCommand.create(editingDomain, gate, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditGateClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		gate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGateClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Gate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, gateMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Gate element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Gate.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForGateClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject gate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)gate.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, gate, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveGateClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		gate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForGateClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Gate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, gateMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Gate element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Gate.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForGateMessage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject gate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, messageMetaClass);
		referenceValueForMessage = bot.changeReferenceValue(allInstancesOf, ((Gate)gate).getMessage());
		cc.append(SetCommand.create(editingDomain, gate, UMLPackage.eINSTANCE.getMessageEnd_Message(), referenceValueForMessage));
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
	public void testEditGateMessage() throws Exception {

		// Import the input model
		initializeInputModel();

		gate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGateMessage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Gate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, gateMetaClass, firstInstanceOf, null);

		// Change value of the message feature of the Gate element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Gate.Properties.message, allInstancesOf.indexOf(referenceValueForMessage)+1);

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
	protected void initializeRemoveExpectedModelForGateMessage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject gate = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, messageMetaClass);
		cc.append(SetCommand.create(editingDomain, gate, UMLPackage.eINSTANCE.getMessageEnd_Message(), null));
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
	public void testRemoveGateMessage() throws Exception {

		// Import the input model
		initializeInputModel();

		gate = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (gate == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForGateMessage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Gate element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), gateMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(gateMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, gateMetaClass, firstInstanceOf, null);

		// Change value of the message feature of the Gate element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Gate.Properties.message);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}










}
