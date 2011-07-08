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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.PartDecomposition;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for PartDecomposition
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class PartDecompositionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass partDecompositionMetaClass = UMLPackage.eINSTANCE.getPartDecomposition();

	/**
	 * The type to edit
	 */
	private EObject partDecomposition;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class enclosingInteraction
	 */
	private Object referenceValueForEnclosingInteraction;

	/**
	 * The reference value for the reference class refersTo
	 */
	private Object referenceValueForRefersTo;

	/**
	 * The reference value for the reference class enclosingOperand
	 */
	private Object referenceValueForEnclosingOperand;

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
	protected void initializeExpectedModelForPartDecompositionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, partDecomposition, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditPartDecompositionName() throws Exception {

		// Import the input model
		initializeInputModel();

		partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPartDecompositionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PartDecomposition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, partDecompositionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the PartDecomposition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.PartDecomposition.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPartDecompositionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, partDecomposition, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditPartDecompositionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((PartDecomposition)partDecomposition).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForPartDecompositionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PartDecomposition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, partDecompositionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the PartDecomposition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.PartDecomposition.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPartDecompositionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((PartDecomposition)partDecomposition).getClientDependency());
		cc.append(AddCommand.create(editingDomain, partDecomposition, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditPartDecompositionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPartDecompositionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PartDecomposition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, partDecompositionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the PartDecomposition element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.PartDecomposition.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForPartDecompositionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)partDecomposition.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, partDecomposition, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemovePartDecompositionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPartDecompositionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PartDecomposition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, partDecompositionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the PartDecomposition element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.PartDecomposition.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPartDecompositionCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, lifelineMetaClass);
		referenceValueForCovered = bot.changeReferenceValue(allInstancesOf, ((PartDecomposition)partDecomposition).getCovered());
		cc.append(AddCommand.create(editingDomain, partDecomposition, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), referenceValueForCovered));
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
	public void testEditPartDecompositionCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPartDecompositionCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PartDecomposition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, partDecompositionMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the PartDecomposition element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.PartDecomposition.Properties.covered, referenceValueForCovered);

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
	protected void initializeRemoveExpectedModelForPartDecompositionCovered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)partDecomposition.eGet(UMLPackage.eINSTANCE.getInteractionFragment_Covered());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, partDecomposition, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), allReferencedInstances.get(0)));
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
	public void testRemovePartDecompositionCovered() throws Exception {

		// Import the input model
		initializeInputModel();

		partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPartDecompositionCovered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PartDecomposition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, partDecompositionMetaClass, firstInstanceOf, null);

		// Change value of the covered feature of the PartDecomposition element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.PartDecomposition.Properties.covered, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPartDecompositionEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForEnclosingInteraction = bot.changeReferenceValue(allInstancesOf, ((PartDecomposition)partDecomposition).getEnclosingInteraction());
		cc.append(SetCommand.create(editingDomain, partDecomposition, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), referenceValueForEnclosingInteraction));
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
	public void testEditPartDecompositionEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPartDecompositionEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PartDecomposition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, partDecompositionMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the PartDecomposition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.PartDecomposition.Properties.enclosingInteraction, allInstancesOf.indexOf(referenceValueForEnclosingInteraction)+1);

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
	protected void initializeRemoveExpectedModelForPartDecompositionEnclosingInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		cc.append(SetCommand.create(editingDomain, partDecomposition, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(), null));
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
	public void testRemovePartDecompositionEnclosingInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPartDecompositionEnclosingInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PartDecomposition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, partDecompositionMetaClass, firstInstanceOf, null);

		// Change value of the enclosingInteraction feature of the PartDecomposition element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.PartDecomposition.Properties.enclosingInteraction);
		

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
	protected void initializeExpectedModelForPartDecompositionEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		referenceValueForEnclosingOperand = bot.changeReferenceValue(allInstancesOf, ((PartDecomposition)partDecomposition).getEnclosingOperand());
		cc.append(SetCommand.create(editingDomain, partDecomposition, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), referenceValueForEnclosingOperand));
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
	public void testEditPartDecompositionEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPartDecompositionEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PartDecomposition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, partDecompositionMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the PartDecomposition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.PartDecomposition.Properties.enclosingOperand, allInstancesOf.indexOf(referenceValueForEnclosingOperand)+1);

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
	protected void initializeRemoveExpectedModelForPartDecompositionEnclosingOperand() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionOperandMetaClass);
		cc.append(SetCommand.create(editingDomain, partDecomposition, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(), null));
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
	public void testRemovePartDecompositionEnclosingOperand() throws Exception {

		// Import the input model
		initializeInputModel();

		partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPartDecompositionEnclosingOperand();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PartDecomposition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, partDecompositionMetaClass, firstInstanceOf, null);

		// Change value of the enclosingOperand feature of the PartDecomposition element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.PartDecomposition.Properties.enclosingOperand);
		

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
	protected void initializeExpectedModelForPartDecompositionRefersTo() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForRefersTo = bot.changeReferenceValue(allInstancesOf, ((PartDecomposition)partDecomposition).getRefersTo());
		cc.append(SetCommand.create(editingDomain, partDecomposition, UMLPackage.eINSTANCE.getInteractionUse_RefersTo(), referenceValueForRefersTo));
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
	public void testEditPartDecompositionRefersTo() throws Exception {

		// Import the input model
		initializeInputModel();

		partDecomposition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (partDecomposition == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPartDecompositionRefersTo();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PartDecomposition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), partDecompositionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(partDecompositionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, partDecompositionMetaClass, firstInstanceOf, null);

		// Change value of the refersTo feature of the PartDecomposition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.PartDecomposition.Properties.refersTo, allInstancesOf.indexOf(referenceValueForRefersTo));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
