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
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Lifeline
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class LifelineTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass lifelineMetaClass = UMLPackage.eINSTANCE.getLifeline();

	/**
	 * The type to edit
	 */
	private EObject lifeline;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class decomposedAs
	 */
	private Object referenceValueForDecomposedAs;

	/**
	 * The reference value for the reference class interaction
	 */
	private Object referenceValueForInteraction;

	/**
	 * The reference value for the reference class represents
	 */
	private Object referenceValueForRepresents;

	/**
	 * The reference value for the reference class coveredBy
	 */
	private Object referenceValueForCoveredBy;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass interactionMetaClass = UMLPackage.eINSTANCE.getInteraction();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass partDecompositionMetaClass = UMLPackage.eINSTANCE.getPartDecomposition();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass interactionFragmentMetaClass = UMLPackage.eINSTANCE.getInteractionFragment();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass connectableElementMetaClass = UMLPackage.eINSTANCE.getConnectableElement();

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
	protected void initializeExpectedModelForLifelineName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject lifeline = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, lifeline, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditLifelineName() throws Exception {

		// Import the input model
		initializeInputModel();

		lifeline = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLifelineName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Lifeline element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, lifelineMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Lifeline element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Lifeline.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLifelineVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject lifeline = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, lifeline, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditLifelineVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		lifeline = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Lifeline)lifeline).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForLifelineVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Lifeline element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, lifelineMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Lifeline element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Lifeline.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForLifelineClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject lifeline = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Lifeline)lifeline).getClientDependency());
		cc.append(AddCommand.create(editingDomain, lifeline, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditLifelineClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		lifeline = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLifelineClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Lifeline element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, lifelineMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Lifeline element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Lifeline.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForLifelineClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject lifeline = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)lifeline.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, lifeline, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveLifelineClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		lifeline = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLifelineClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Lifeline element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, lifelineMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Lifeline element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Lifeline.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForLifelineInteraction() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject lifeline = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interactionMetaClass);
		referenceValueForInteraction = bot.changeReferenceValue(allInstancesOf, ((Lifeline)lifeline).getInteraction());
		cc.append(SetCommand.create(editingDomain, lifeline, UMLPackage.eINSTANCE.getLifeline_Interaction(), referenceValueForInteraction));
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
	public void testEditLifelineInteraction() throws Exception {

		// Import the input model
		initializeInputModel();

		lifeline = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLifelineInteraction();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Lifeline element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, lifelineMetaClass, firstInstanceOf, null);

		// Change value of the interaction feature of the Lifeline element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Lifeline.Properties.interaction, allInstancesOf.indexOf(referenceValueForInteraction));

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
	protected void initializeExpectedModelForLifelineDecomposedAs() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject lifeline = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, partDecompositionMetaClass);
		referenceValueForDecomposedAs = bot.changeReferenceValue(allInstancesOf, ((Lifeline)lifeline).getDecomposedAs());
		cc.append(SetCommand.create(editingDomain, lifeline, UMLPackage.eINSTANCE.getLifeline_DecomposedAs(), referenceValueForDecomposedAs));
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
	public void testEditLifelineDecomposedAs() throws Exception {

		// Import the input model
		initializeInputModel();

		lifeline = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForLifelineDecomposedAs();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Lifeline element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, lifelineMetaClass, firstInstanceOf, null);

		// Change value of the decomposedAs feature of the Lifeline element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Lifeline.Properties.decomposedAs, allInstancesOf.indexOf(referenceValueForDecomposedAs)+1);

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
	protected void initializeRemoveExpectedModelForLifelineDecomposedAs() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject lifeline = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, partDecompositionMetaClass);
		cc.append(SetCommand.create(editingDomain, lifeline, UMLPackage.eINSTANCE.getLifeline_DecomposedAs(), null));
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
	public void testRemoveLifelineDecomposedAs() throws Exception {

		// Import the input model
		initializeInputModel();

		lifeline = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (lifeline == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForLifelineDecomposedAs();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Lifeline element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), lifelineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(lifelineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, lifelineMetaClass, firstInstanceOf, null);

		// Change value of the decomposedAs feature of the Lifeline element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Lifeline.Properties.decomposedAs);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
