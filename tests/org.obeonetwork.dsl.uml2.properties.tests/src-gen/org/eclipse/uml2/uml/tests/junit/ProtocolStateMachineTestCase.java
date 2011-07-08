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
import org.eclipse.uml2.uml.ProtocolStateMachine;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ProtocolStateMachine
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ProtocolStateMachineTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass protocolStateMachineMetaClass = UMLPackage.eINSTANCE.getProtocolStateMachine();

	/**
	 * The type to edit
	 */
	private EObject protocolStateMachine;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class postcondition
	 */
	private Object referenceValueForPostcondition;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class specification
	 */
	private Object referenceValueForSpecification;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class submachineState
	 */
	private Object referenceValueForSubmachineState;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class precondition
	 */
	private Object referenceValueForPrecondition;

	/**
	 * The reference value for the reference class redefinedBehavior
	 */
	private Object referenceValueForRedefinedBehavior;

	/**
	 * The reference value for the reference class classifierBehavior
	 */
	private Object referenceValueForClassifierBehavior;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class extendedStateMachine
	 */
	private Object referenceValueForExtendedStateMachine;
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
	private EClass stateMachineMetaClass = UMLPackage.eINSTANCE.getStateMachine();

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
	protected void initializeExpectedModelForProtocolStateMachineName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditProtocolStateMachineName() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ProtocolStateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProtocolStateMachineVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditProtocolStateMachineVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ProtocolStateMachine)protocolStateMachine).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForProtocolStateMachineVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ProtocolStateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProtocolStateMachineClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ProtocolStateMachine)protocolStateMachine).getClientDependency());
		cc.append(AddCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditProtocolStateMachineClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ProtocolStateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForProtocolStateMachineClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)protocolStateMachine.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveProtocolStateMachineClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolStateMachineClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ProtocolStateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForProtocolStateMachineIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditProtocolStateMachineIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ProtocolStateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProtocolStateMachineOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((ProtocolStateMachine)protocolStateMachine).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditProtocolStateMachineOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the ProtocolStateMachine element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForProtocolStateMachineOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveProtocolStateMachineOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolStateMachineOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the ProtocolStateMachine element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForProtocolStateMachineTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((ProtocolStateMachine)protocolStateMachine).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditProtocolStateMachineTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the ProtocolStateMachine element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForProtocolStateMachineTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveProtocolStateMachineTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolStateMachineTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the ProtocolStateMachine element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForProtocolStateMachineIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditProtocolStateMachineIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the ProtocolStateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProtocolStateMachinePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((ProtocolStateMachine)protocolStateMachine).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditProtocolStateMachinePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachinePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the ProtocolStateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForProtocolStateMachinePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)protocolStateMachine.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveProtocolStateMachinePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolStateMachinePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the ProtocolStateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForProtocolStateMachineRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((ProtocolStateMachine)protocolStateMachine).getRepresentation());
		cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditProtocolStateMachineRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the ProtocolStateMachine element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForProtocolStateMachineRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveProtocolStateMachineRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolStateMachineRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the ProtocolStateMachine element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.representation);
		

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
	protected void initializeExpectedModelForProtocolStateMachineUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((ProtocolStateMachine)protocolStateMachine).getUseCase());
		cc.append(AddCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditProtocolStateMachineUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the ProtocolStateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForProtocolStateMachineUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)protocolStateMachine.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveProtocolStateMachineUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolStateMachineUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the ProtocolStateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForProtocolStateMachineIsActive() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getClass_IsActive(), UPDATED_VALUE));
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
	public void testEditProtocolStateMachineIsActive() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineIsActive();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the isActive feature of the ProtocolStateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.isActive, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProtocolStateMachineIsReentrant() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getBehavior_IsReentrant(), UPDATED_VALUE));
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
	public void testEditProtocolStateMachineIsReentrant() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineIsReentrant();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the isReentrant feature of the ProtocolStateMachine element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.isReentrant, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProtocolStateMachinePrecondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPrecondition = bot.changeReferenceValue(allInstancesOf, ((ProtocolStateMachine)protocolStateMachine).getPrecondition());
		cc.append(AddCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getBehavior_Precondition(), referenceValueForPrecondition));
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
	public void testEditProtocolStateMachinePrecondition() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachinePrecondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the precondition feature of the ProtocolStateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.precondition, referenceValueForPrecondition);

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
	protected void initializeRemoveExpectedModelForProtocolStateMachinePrecondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)protocolStateMachine.eGet(UMLPackage.eINSTANCE.getBehavior_Precondition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getBehavior_Precondition(), allReferencedInstances.get(0)));
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
	public void testRemoveProtocolStateMachinePrecondition() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolStateMachinePrecondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the precondition feature of the ProtocolStateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.precondition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForProtocolStateMachinePostcondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPostcondition = bot.changeReferenceValue(allInstancesOf, ((ProtocolStateMachine)protocolStateMachine).getPostcondition());
		cc.append(AddCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getBehavior_Postcondition(), referenceValueForPostcondition));
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
	public void testEditProtocolStateMachinePostcondition() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachinePostcondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the postcondition feature of the ProtocolStateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.postcondition, referenceValueForPostcondition);

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
	protected void initializeRemoveExpectedModelForProtocolStateMachinePostcondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)protocolStateMachine.eGet(UMLPackage.eINSTANCE.getBehavior_Postcondition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getBehavior_Postcondition(), allReferencedInstances.get(0)));
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
	public void testRemoveProtocolStateMachinePostcondition() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolStateMachinePostcondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the postcondition feature of the ProtocolStateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.postcondition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForProtocolStateMachineSubmachineState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForSubmachineState = bot.changeReferenceValue(allInstancesOf, ((ProtocolStateMachine)protocolStateMachine).getSubmachineState());
		cc.append(AddCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getStateMachine_SubmachineState(), referenceValueForSubmachineState));
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
	public void testEditProtocolStateMachineSubmachineState() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineSubmachineState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the submachineState feature of the ProtocolStateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.submachineState, referenceValueForSubmachineState);

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
	protected void initializeRemoveExpectedModelForProtocolStateMachineSubmachineState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)protocolStateMachine.eGet(UMLPackage.eINSTANCE.getStateMachine_SubmachineState());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getStateMachine_SubmachineState(), allReferencedInstances.get(0)));
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
	public void testRemoveProtocolStateMachineSubmachineState() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolStateMachineSubmachineState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the submachineState feature of the ProtocolStateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.submachineState, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForProtocolStateMachineExtendedStateMachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMachineMetaClass);
		referenceValueForExtendedStateMachine = bot.changeReferenceValue(allInstancesOf, ((ProtocolStateMachine)protocolStateMachine).getExtendedStateMachine());
		cc.append(AddCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getStateMachine_ExtendedStateMachine(), referenceValueForExtendedStateMachine));
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
	public void testEditProtocolStateMachineExtendedStateMachine() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolStateMachineExtendedStateMachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the extendedStateMachine feature of the ProtocolStateMachine element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.extendedStateMachine, referenceValueForExtendedStateMachine);

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
	protected void initializeRemoveExpectedModelForProtocolStateMachineExtendedStateMachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)protocolStateMachine.eGet(UMLPackage.eINSTANCE.getStateMachine_ExtendedStateMachine());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, protocolStateMachine, UMLPackage.eINSTANCE.getStateMachine_ExtendedStateMachine(), allReferencedInstances.get(0)));
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
	public void testRemoveProtocolStateMachineExtendedStateMachine() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolStateMachine = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (protocolStateMachine == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolStateMachineExtendedStateMachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolStateMachine element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolStateMachineMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolStateMachineMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolStateMachineMetaClass, firstInstanceOf, null);

		// Change value of the extendedStateMachine feature of the ProtocolStateMachine element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolStateMachine.Properties.extendedStateMachine, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


































}
