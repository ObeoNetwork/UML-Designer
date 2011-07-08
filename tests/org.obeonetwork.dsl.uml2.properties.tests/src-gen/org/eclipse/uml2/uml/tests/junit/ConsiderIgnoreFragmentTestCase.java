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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ConsiderIgnoreFragment;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ConsiderIgnoreFragment
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ConsiderIgnoreFragmentTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass considerIgnoreFragmentMetaClass = UMLPackage.eINSTANCE.getConsiderIgnoreFragment();

	/**
	 * The type to edit
	 */
	private EObject considerIgnoreFragment;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class interactionOperator
	 */
	private Object enumValueForInteractionOperator;
	/**
	 * The reference value for the reference class enclosingInteraction
	 */
	private Object referenceValueForEnclosingInteraction;

	/**
	 * The reference value for the reference class enclosingOperand
	 */
	private Object referenceValueForEnclosingOperand;

	/**
	 * The reference value for the reference class message
	 */
	private Object referenceValueForMessage;

	/**
	 * The reference value for the reference class covered
	 */
	private Object referenceValueForCovered;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass namedElementMetaClass = UMLPackage.eINSTANCE.getNamedElement();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass interactionOperandMetaClass = UMLPackage.eINSTANCE.getInteractionOperand();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass interactionMetaClass = UMLPackage.eINSTANCE.getInteraction();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass lifelineMetaClass = UMLPackage.eINSTANCE.getLifeline();

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
	protected void initializeExpectedModelForConsiderIgnoreFragmentName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, considerIgnoreFragment, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditConsiderIgnoreFragmentName() throws Exception {

		// Import the input model
		initializeInputModel();

		considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConsiderIgnoreFragmentName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConsiderIgnoreFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, considerIgnoreFragmentMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ConsiderIgnoreFragment element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ConsiderIgnoreFragment.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForConsiderIgnoreFragmentVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, considerIgnoreFragment, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditConsiderIgnoreFragmentVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ConsiderIgnoreFragment)considerIgnoreFragment).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForConsiderIgnoreFragmentVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConsiderIgnoreFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, considerIgnoreFragmentMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ConsiderIgnoreFragment element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ConsiderIgnoreFragment.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForConsiderIgnoreFragmentClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ConsiderIgnoreFragment)considerIgnoreFragment).getClientDependency());
		cc.append(AddCommand.create(editingDomain, considerIgnoreFragment, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditConsiderIgnoreFragmentClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConsiderIgnoreFragmentClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConsiderIgnoreFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, considerIgnoreFragmentMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ConsiderIgnoreFragment element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ConsiderIgnoreFragment.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForConsiderIgnoreFragmentClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)considerIgnoreFragment.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, considerIgnoreFragment, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveConsiderIgnoreFragmentClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConsiderIgnoreFragmentClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConsiderIgnoreFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, considerIgnoreFragmentMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ConsiderIgnoreFragment element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ConsiderIgnoreFragment.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForConsiderIgnoreFragmentCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, lifelineMetaClass);
		referenceValueForCovered = bot.changeReferenceValue(allInstancesOf, ((ConsiderIgnoreFragment)considerIgnoreFragment).getCovered());
		cc.append(AddCommand.create(editingDomain, considerIgnoreFragment, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), referenceValueForCovered));
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
	public void testEditConsiderIgnoreFragmentCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConsiderIgnoreFragmentCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConsiderIgnoreFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, considerIgnoreFragmentMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the ConsiderIgnoreFragment element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ConsiderIgnoreFragment.Properties.covered, referenceValueForCovered);

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
	protected void initializeRemoveExpectedModelForConsiderIgnoreFragmentCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)considerIgnoreFragment.eGet(UMLPackage.eINSTANCE.getInteractionFragment_Covered());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, considerIgnoreFragment, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), allReferencedInstances.get(0)));
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
	public void testRemoveConsiderIgnoreFragmentCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConsiderIgnoreFragmentCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConsiderIgnoreFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, considerIgnoreFragmentMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the ConsiderIgnoreFragment element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ConsiderIgnoreFragment.Properties.covered, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForConsiderIgnoreFragmentEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForEnclosingInteraction = bot.changeReferenceValue(allInstancesOf, ((ConsiderIgnoreFragment)considerIgnoreFragment).getEnclosingInteraction());
		cc.append(SetCommand.create(editingDomain, considerIgnoreFragment, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), referenceValueForEnclosingInteraction));
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
	public void testEditConsiderIgnoreFragmentEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConsiderIgnoreFragmentEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConsiderIgnoreFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, considerIgnoreFragmentMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the ConsiderIgnoreFragment element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ConsiderIgnoreFragment.Properties.enclosingInteraction, allInstancesOf.indexOf(referenceValueForEnclosingInteraction)+1);

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
	protected void initializeRemoveExpectedModelForConsiderIgnoreFragmentEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		cc.append(SetCommand.create(editingDomain, considerIgnoreFragment, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), null));
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
	public void testRemoveConsiderIgnoreFragmentEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConsiderIgnoreFragmentEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConsiderIgnoreFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, considerIgnoreFragmentMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the ConsiderIgnoreFragment element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ConsiderIgnoreFragment.Properties.enclosingInteraction);
		

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
	protected void initializeExpectedModelForConsiderIgnoreFragmentEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		referenceValueForEnclosingOperand = bot.changeReferenceValue(allInstancesOf, ((ConsiderIgnoreFragment)considerIgnoreFragment).getEnclosingOperand());
		cc.append(SetCommand.create(editingDomain, considerIgnoreFragment, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), referenceValueForEnclosingOperand));
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
	public void testEditConsiderIgnoreFragmentEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForConsiderIgnoreFragmentEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConsiderIgnoreFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, considerIgnoreFragmentMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the ConsiderIgnoreFragment element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ConsiderIgnoreFragment.Properties.enclosingOperand, allInstancesOf.indexOf(referenceValueForEnclosingOperand)+1);

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
	protected void initializeRemoveExpectedModelForConsiderIgnoreFragmentEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		cc.append(SetCommand.create(editingDomain, considerIgnoreFragment, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), null));
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
	public void testRemoveConsiderIgnoreFragmentEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForConsiderIgnoreFragmentEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConsiderIgnoreFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, considerIgnoreFragmentMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the ConsiderIgnoreFragment element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ConsiderIgnoreFragment.Properties.enclosingOperand);
		

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
	protected void initializeExpectedModelForConsiderIgnoreFragmentInteractionOperator() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, considerIgnoreFragment, UMLPackage.eINSTANCE.getCombinedFragment_InteractionOperator(), UPDATED_VALUE));
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
	public void testEditConsiderIgnoreFragmentInteractionOperator() throws Exception {

		// Import the input model
		initializeInputModel();

		considerIgnoreFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (considerIgnoreFragment == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		enumValueForInteractionOperator = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getInteractionOperatorKind(), ((ConsiderIgnoreFragment)considerIgnoreFragment).getInteractionOperator().getLiteral());
		// Create the expected model
		initializeExpectedModelForConsiderIgnoreFragmentInteractionOperator();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ConsiderIgnoreFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), considerIgnoreFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(considerIgnoreFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, considerIgnoreFragmentMetaClass, firstInstanceOf, null);

		// Change value of the interactionOperator feature of the ConsiderIgnoreFragment element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ConsiderIgnoreFragment.Properties.interactionOperator, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
