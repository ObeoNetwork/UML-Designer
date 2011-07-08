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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.CombinedFragment;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for CombinedFragment
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class CombinedFragmentTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass combinedFragmentMetaClass = UMLPackage.eINSTANCE.getCombinedFragment();

	/**
	 * The type to edit
	 */
	private EObject combinedFragment;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class interactionOperator
	 */
	private Object enumValueForInteractionOperator;
	/**
	 * The reference value for the reference class covered
	 */
	private Object referenceValueForCovered;

	/**
	 * The reference value for the reference class enclosingOperand
	 */
	private Object referenceValueForEnclosingOperand;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class enclosingInteraction
	 */
	private Object referenceValueForEnclosingInteraction;
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
	protected void initializeExpectedModelForCombinedFragmentName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, combinedFragment, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditCombinedFragmentName() throws Exception {

		// Import the input model
		initializeInputModel();

		combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCombinedFragmentName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CombinedFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, combinedFragmentMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the CombinedFragment element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CombinedFragment.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCombinedFragmentVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, combinedFragment, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditCombinedFragmentVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((CombinedFragment)combinedFragment).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForCombinedFragmentVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CombinedFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, combinedFragmentMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the CombinedFragment element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CombinedFragment.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCombinedFragmentClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((CombinedFragment)combinedFragment).getClientDependency());
		cc.append(AddCommand.create(editingDomain, combinedFragment, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditCombinedFragmentClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCombinedFragmentClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CombinedFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, combinedFragmentMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CombinedFragment element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CombinedFragment.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForCombinedFragmentClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)combinedFragment.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, combinedFragment, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveCombinedFragmentClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCombinedFragmentClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CombinedFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, combinedFragmentMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CombinedFragment element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CombinedFragment.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCombinedFragmentCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, lifelineMetaClass);
		referenceValueForCovered = bot.changeReferenceValue(allInstancesOf, ((CombinedFragment)combinedFragment).getCovered());
		cc.append(AddCommand.create(editingDomain, combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), referenceValueForCovered));
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
	public void testEditCombinedFragmentCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCombinedFragmentCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CombinedFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, combinedFragmentMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the CombinedFragment element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CombinedFragment.Properties.covered, referenceValueForCovered);

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
	protected void initializeRemoveExpectedModelForCombinedFragmentCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)combinedFragment.eGet(UMLPackage.eINSTANCE.getInteractionFragment_Covered());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), allReferencedInstances.get(0)));
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
	public void testRemoveCombinedFragmentCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCombinedFragmentCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CombinedFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, combinedFragmentMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the CombinedFragment element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CombinedFragment.Properties.covered, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCombinedFragmentEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForEnclosingInteraction = bot.changeReferenceValue(allInstancesOf, ((CombinedFragment)combinedFragment).getEnclosingInteraction());
		cc.append(SetCommand.create(editingDomain, combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), referenceValueForEnclosingInteraction));
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
	public void testEditCombinedFragmentEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCombinedFragmentEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CombinedFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, combinedFragmentMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the CombinedFragment element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CombinedFragment.Properties.enclosingInteraction, allInstancesOf.indexOf(referenceValueForEnclosingInteraction)+1);

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
	protected void initializeRemoveExpectedModelForCombinedFragmentEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		cc.append(SetCommand.create(editingDomain, combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), null));
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
	public void testRemoveCombinedFragmentEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCombinedFragmentEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CombinedFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, combinedFragmentMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the CombinedFragment element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CombinedFragment.Properties.enclosingInteraction);
		

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
	protected void initializeExpectedModelForCombinedFragmentEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		referenceValueForEnclosingOperand = bot.changeReferenceValue(allInstancesOf, ((CombinedFragment)combinedFragment).getEnclosingOperand());
		cc.append(SetCommand.create(editingDomain, combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), referenceValueForEnclosingOperand));
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
	public void testEditCombinedFragmentEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCombinedFragmentEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CombinedFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, combinedFragmentMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the CombinedFragment element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CombinedFragment.Properties.enclosingOperand, allInstancesOf.indexOf(referenceValueForEnclosingOperand)+1);

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
	protected void initializeRemoveExpectedModelForCombinedFragmentEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		cc.append(SetCommand.create(editingDomain, combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), null));
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
	public void testRemoveCombinedFragmentEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCombinedFragmentEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CombinedFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, combinedFragmentMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the CombinedFragment element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CombinedFragment.Properties.enclosingOperand);
		

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
	protected void initializeExpectedModelForCombinedFragmentInteractionOperator() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, combinedFragment, UMLPackage.eINSTANCE.getCombinedFragment_InteractionOperator(), UPDATED_VALUE));
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
	public void testEditCombinedFragmentInteractionOperator() throws Exception {

		// Import the input model
		initializeInputModel();

		combinedFragment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (combinedFragment == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		enumValueForInteractionOperator = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getInteractionOperatorKind(), ((CombinedFragment)combinedFragment).getInteractionOperator().getLiteral());
		// Create the expected model
		initializeExpectedModelForCombinedFragmentInteractionOperator();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CombinedFragment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), combinedFragmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(combinedFragmentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, combinedFragmentMetaClass, firstInstanceOf, null);

		// Change value of the interactionOperator feature of the CombinedFragment element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CombinedFragment.Properties.interactionOperator, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
