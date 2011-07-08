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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Clause;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Clause
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ClauseTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass clauseMetaClass = UMLPackage.eINSTANCE.getClause();

	/**
	 * The type to edit
	 */
	private EObject clause;

	/**
	 * The reference value for the reference class predecessorClause
	 */
	private Object referenceValueForPredecessorClause;

	/**
	 * The reference value for the reference class successorClause
	 */
	private Object referenceValueForSuccessorClause;

	/**
	 * The reference value for the reference class test
	 */
	private Object referenceValueForTest;

	/**
	 * The reference value for the reference class bodyOutput
	 */
	private Object referenceValueForBodyOutput;

	/**
	 * The reference value for the reference class decider
	 */
	private Object referenceValueForDecider;

	/**
	 * The reference value for the reference class body
	 */
	private Object referenceValueForBody;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass outputPinMetaClass = UMLPackage.eINSTANCE.getOutputPin();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass executableNodeMetaClass = UMLPackage.eINSTANCE.getExecutableNode();
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
	protected void initializeExpectedModelForClausePredecessorClause() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clause = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, clauseMetaClass);
		referenceValueForPredecessorClause = bot.changeReferenceValue(allInstancesOf, ((Clause)clause).getPredecessorClause());
		cc.append(AddCommand.create(editingDomain, clause, UMLPackage.eINSTANCE.getClause_PredecessorClause(), referenceValueForPredecessorClause));
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
	public void testEditClausePredecessorClause() throws Exception {

		// Import the input model
		initializeInputModel();

		clause = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClausePredecessorClause();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Clause element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clauseMetaClass, firstInstanceOf, null);

		// Change value of the predecessorClause feature of the Clause element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Clause.Properties.predecessorClause, referenceValueForPredecessorClause);

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
	protected void initializeRemoveExpectedModelForClausePredecessorClause() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clause = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clause.eGet(UMLPackage.eINSTANCE.getClause_PredecessorClause());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clause, UMLPackage.eINSTANCE.getClause_PredecessorClause(), allReferencedInstances.get(0)));
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
	public void testRemoveClausePredecessorClause() throws Exception {

		// Import the input model
		initializeInputModel();

		clause = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClausePredecessorClause();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Clause element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clauseMetaClass, firstInstanceOf, null);

		// Change value of the predecessorClause feature of the Clause element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Clause.Properties.predecessorClause, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForClauseSuccessorClause() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clause = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, clauseMetaClass);
		referenceValueForSuccessorClause = bot.changeReferenceValue(allInstancesOf, ((Clause)clause).getSuccessorClause());
		cc.append(AddCommand.create(editingDomain, clause, UMLPackage.eINSTANCE.getClause_SuccessorClause(), referenceValueForSuccessorClause));
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
	public void testEditClauseSuccessorClause() throws Exception {

		// Import the input model
		initializeInputModel();

		clause = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClauseSuccessorClause();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Clause element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clauseMetaClass, firstInstanceOf, null);

		// Change value of the successorClause feature of the Clause element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Clause.Properties.successorClause, referenceValueForSuccessorClause);

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
	protected void initializeRemoveExpectedModelForClauseSuccessorClause() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clause = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clause.eGet(UMLPackage.eINSTANCE.getClause_SuccessorClause());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clause, UMLPackage.eINSTANCE.getClause_SuccessorClause(), allReferencedInstances.get(0)));
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
	public void testRemoveClauseSuccessorClause() throws Exception {

		// Import the input model
		initializeInputModel();

		clause = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClauseSuccessorClause();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Clause element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clauseMetaClass, firstInstanceOf, null);

		// Change value of the successorClause feature of the Clause element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Clause.Properties.successorClause, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForClauseDecider() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clause = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, outputPinMetaClass);
		referenceValueForDecider = bot.changeReferenceValue(allInstancesOf, ((Clause)clause).getDecider());
		cc.append(SetCommand.create(editingDomain, clause, UMLPackage.eINSTANCE.getClause_Decider(), referenceValueForDecider));
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
	public void testEditClauseDecider() throws Exception {

		// Import the input model
		initializeInputModel();

		clause = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClauseDecider();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Clause element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clauseMetaClass, firstInstanceOf, null);

		// Change value of the decider feature of the Clause element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Clause.Properties.decider, allInstancesOf.indexOf(referenceValueForDecider));

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
	protected void initializeExpectedModelForClauseBodyOutput() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clause = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, outputPinMetaClass);
		referenceValueForBodyOutput = bot.changeReferenceValue(allInstancesOf, ((Clause)clause).getBodyOutput());
		cc.append(AddCommand.create(editingDomain, clause, UMLPackage.eINSTANCE.getClause_BodyOutput(), referenceValueForBodyOutput));
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
	public void testEditClauseBodyOutput() throws Exception {

		// Import the input model
		initializeInputModel();

		clause = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClauseBodyOutput();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Clause element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clauseMetaClass, firstInstanceOf, null);

		// Change value of the bodyOutput feature of the Clause element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Clause.Properties.bodyOutput, referenceValueForBodyOutput);

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
	protected void initializeRemoveExpectedModelForClauseBodyOutput() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject clause = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)clause.eGet(UMLPackage.eINSTANCE.getClause_BodyOutput());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, clause, UMLPackage.eINSTANCE.getClause_BodyOutput(), allReferencedInstances.get(0)));
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
	public void testRemoveClauseBodyOutput() throws Exception {

		// Import the input model
		initializeInputModel();

		clause = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (clause == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClauseBodyOutput();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Clause element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), clauseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(clauseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, clauseMetaClass, firstInstanceOf, null);

		// Change value of the bodyOutput feature of the Clause element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Clause.Properties.bodyOutput, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}










}
