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
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for StateMachine
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class StateMachineTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass stateMachineMetaClass = UMLPackage.eINSTANCE.getStateMachine();

	/**
	 * The type to edit
	 */
	private EObject stateMachine;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class specification
	 */
	private Object referenceValueForSpecification;

	/**
	 * The reference value for the reference class redefinedBehavior
	 */
	private Object referenceValueForRedefinedBehavior;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class postcondition
	 */
	private Object referenceValueForPostcondition;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class precondition
	 */
	private Object referenceValueForPrecondition;

	/**
	 * The reference value for the reference class extendedStateMachine
	 */
	private Object referenceValueForExtendedStateMachine;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class submachineState
	 */
	private Object referenceValueForSubmachineState;

	/**
	 * The reference value for the reference class classifierBehavior
	 */
	private Object referenceValueForClassifierBehavior;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass generalizationSetMetaClass = UMLPackage.eINSTANCE.getGeneralizationSet();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass useCaseMetaClass = UMLPackage.eINSTANCE.getUseCase();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass collaborationUseMetaClass = UMLPackage.eINSTANCE.getCollaborationUse();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass classifierMetaClass = UMLPackage.eINSTANCE.getClassifier();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass constraintMetaClass = UMLPackage.eINSTANCE.getConstraint();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass dependencyMetaClass = UMLPackage.eINSTANCE.getDependency();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass stateMetaClass = UMLPackage.eINSTANCE.getState();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass behaviorMetaClass = UMLPackage.eINSTANCE.getBehavior();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass behavioralFeatureMetaClass = UMLPackage.eINSTANCE.getBehavioralFeature();
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
	protected void initializeExpectedModelForStateMachineName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditStateMachineName() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the StateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForStateMachineVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditStateMachineVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((StateMachine)stateMachine).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForStateMachineVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the StateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForStateMachineClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((StateMachine)stateMachine).getClientDependency());
		cc.append(AddCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditStateMachineClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the StateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForStateMachineClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)stateMachine.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveStateMachineClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateMachineClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the StateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForStateMachineIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditStateMachineIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the StateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForStateMachineOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((StateMachine)stateMachine).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditStateMachineOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the StateMachine element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForStateMachineOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveStateMachineOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateMachineOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the StateMachine element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForStateMachineTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((StateMachine)stateMachine).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditStateMachineTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the StateMachine element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForStateMachineTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveStateMachineTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateMachineTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the StateMachine element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForStateMachineIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditStateMachineIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the StateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForStateMachinePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((StateMachine)stateMachine).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditStateMachinePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachinePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the StateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForStateMachinePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)stateMachine.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveStateMachinePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateMachinePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the StateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForStateMachineRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((StateMachine)stateMachine).getRepresentation());
		cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditStateMachineRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the StateMachine element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForStateMachineRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveStateMachineRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateMachineRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the StateMachine element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.representation);
		

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
	protected void initializeExpectedModelForStateMachineUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((StateMachine)stateMachine).getUseCase());
		cc.append(AddCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditStateMachineUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the StateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForStateMachineUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)stateMachine.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveStateMachineUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateMachineUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the StateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForStateMachineIsActive() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getClass_IsActive(), UPDATED_VALUE));
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
	public void testEditStateMachineIsActive() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineIsActive();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the isActive feature of the StateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.isActive, UPDATED_VALUE);

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
	protected void initializeExpectedModelForStateMachineIsReentrant() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getBehavior_IsReentrant(), UPDATED_VALUE));
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
	public void testEditStateMachineIsReentrant() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineIsReentrant();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the isReentrant feature of the StateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.isReentrant, UPDATED_VALUE);

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
	protected void initializeExpectedModelForStateMachinePrecondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPrecondition = bot.changeReferenceValue(allInstancesOf, ((StateMachine)stateMachine).getPrecondition());
		cc.append(AddCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getBehavior_Precondition(), referenceValueForPrecondition));
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
	public void testEditStateMachinePrecondition() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachinePrecondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the precondition feature of the StateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.precondition, referenceValueForPrecondition);

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
	protected void initializeRemoveExpectedModelForStateMachinePrecondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)stateMachine.eGet(UMLPackage.eINSTANCE.getBehavior_Precondition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getBehavior_Precondition(), allReferencedInstances.get(0)));
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
	public void testRemoveStateMachinePrecondition() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateMachinePrecondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the precondition feature of the StateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.precondition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForStateMachinePostcondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPostcondition = bot.changeReferenceValue(allInstancesOf, ((StateMachine)stateMachine).getPostcondition());
		cc.append(AddCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getBehavior_Postcondition(), referenceValueForPostcondition));
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
	public void testEditStateMachinePostcondition() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachinePostcondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the postcondition feature of the StateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.postcondition, referenceValueForPostcondition);

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
	protected void initializeRemoveExpectedModelForStateMachinePostcondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)stateMachine.eGet(UMLPackage.eINSTANCE.getBehavior_Postcondition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getBehavior_Postcondition(), allReferencedInstances.get(0)));
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
	public void testRemoveStateMachinePostcondition() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateMachinePostcondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the postcondition feature of the StateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.postcondition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForStateMachineSubmachineState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForSubmachineState = bot.changeReferenceValue(allInstancesOf, ((StateMachine)stateMachine).getSubmachineState());
		cc.append(AddCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getStateMachine_SubmachineState(), referenceValueForSubmachineState));
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
	public void testEditStateMachineSubmachineState() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineSubmachineState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the submachineState feature of the StateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.submachineState, referenceValueForSubmachineState);

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
	protected void initializeRemoveExpectedModelForStateMachineSubmachineState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)stateMachine.eGet(UMLPackage.eINSTANCE.getStateMachine_SubmachineState());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getStateMachine_SubmachineState(), allReferencedInstances.get(0)));
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
	public void testRemoveStateMachineSubmachineState() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateMachineSubmachineState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the submachineState feature of the StateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.submachineState, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForStateMachineExtendedStateMachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMachineMetaClass);
		referenceValueForExtendedStateMachine = bot.changeReferenceValue(allInstancesOf, ((StateMachine)stateMachine).getExtendedStateMachine());
		cc.append(AddCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getStateMachine_ExtendedStateMachine(), referenceValueForExtendedStateMachine));
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
	public void testEditStateMachineExtendedStateMachine() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForStateMachineExtendedStateMachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the extendedStateMachine feature of the StateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.extendedStateMachine, referenceValueForExtendedStateMachine);

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
	protected void initializeRemoveExpectedModelForStateMachineExtendedStateMachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)stateMachine.eGet(UMLPackage.eINSTANCE.getStateMachine_ExtendedStateMachine());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, stateMachine, UMLPackage.eINSTANCE.getStateMachine_ExtendedStateMachine(), allReferencedInstances.get(0)));
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
	public void testRemoveStateMachineExtendedStateMachine() throws Exception {

		// Import the input model
		initializeInputModel();

		stateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (stateMachine == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForStateMachineExtendedStateMachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the StateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), stateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(stateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, stateMachineMetaClass, firstInstanceOf, null);

		// Change value of the extendedStateMachine feature of the StateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.StateMachine.Properties.extendedStateMachine, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


































}
