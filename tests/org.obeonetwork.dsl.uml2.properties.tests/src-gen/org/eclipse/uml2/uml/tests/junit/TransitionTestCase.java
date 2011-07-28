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
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Transition
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class TransitionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass transitionMetaClass = UMLPackage.eINSTANCE.getTransition();

	/**
	 * The type to edit
	 */
	private EObject transition;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class kind
	 */
	private Object enumValueForKind;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class container
	 */
	private Object referenceValueForContainer;

	/**
	 * The reference value for the reference class target
	 */
	private Object referenceValueForTarget;

	/**
	 * The reference value for the reference class redefinedTransition
	 */
	private Object referenceValueForRedefinedTransition;

	/**
	 * The reference value for the reference class guard
	 */
	private Object referenceValueForGuard;

	/**
	 * The reference value for the reference class source
	 */
	private Object referenceValueForSource;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass regionMetaClass = UMLPackage.eINSTANCE.getRegion();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass constraintMetaClass = UMLPackage.eINSTANCE.getConstraint();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass vertexMetaClass = UMLPackage.eINSTANCE.getVertex();

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
	protected void initializeExpectedModelForTransitionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject transition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, transition, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditTransitionName() throws Exception {

		// Import the input model
		initializeInputModel();

		transition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTransitionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Transition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, transitionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Transition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Transition.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTransitionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject transition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, transition, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditTransitionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		transition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Transition)transition).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForTransitionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Transition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, transitionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Transition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Transition.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTransitionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject transition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Transition)transition).getClientDependency());
		cc.append(AddCommand.create(editingDomain, transition, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditTransitionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		transition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTransitionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Transition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, transitionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Transition element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Transition.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForTransitionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject transition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)transition.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, transition, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveTransitionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		transition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTransitionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Transition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, transitionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Transition element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Transition.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForTransitionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject transition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, transition, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditTransitionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		transition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTransitionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Transition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, transitionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Transition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Transition.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTransitionKind() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject transition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, transition, UMLPackage.eINSTANCE.getTransition_Kind(), UPDATED_VALUE));
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
	public void testEditTransitionKind() throws Exception {

		// Import the input model
		initializeInputModel();

		transition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		enumValueForKind = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getTransitionKind(), ((Transition)transition).getKind().getLiteral());
		// Create the expected model
		initializeExpectedModelForTransitionKind();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Transition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, transitionMetaClass, firstInstanceOf, null);

		// Change value of the kind feature of the Transition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Transition.Properties.kind, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTransitionContainer() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject transition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		referenceValueForContainer = bot.changeReferenceValue(allInstancesOf, ((Transition)transition).getContainer());
		cc.append(SetCommand.create(editingDomain, transition, UMLPackage.eINSTANCE.getTransition_Container(), referenceValueForContainer));
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
	public void testEditTransitionContainer() throws Exception {

		// Import the input model
		initializeInputModel();

		transition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTransitionContainer();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Transition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, transitionMetaClass, firstInstanceOf, null);

		// Change value of the container feature of the Transition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Transition.Properties.container, allInstancesOf.indexOf(referenceValueForContainer));

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
	protected void initializeExpectedModelForTransitionRedefinedTransition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject transition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, transitionMetaClass);
		referenceValueForRedefinedTransition = bot.changeReferenceValue(allInstancesOf, ((Transition)transition).getRedefinedTransition());
		cc.append(SetCommand.create(editingDomain, transition, UMLPackage.eINSTANCE.getTransition_RedefinedTransition(), referenceValueForRedefinedTransition));
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
	public void testEditTransitionRedefinedTransition() throws Exception {

		// Import the input model
		initializeInputModel();

		transition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTransitionRedefinedTransition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Transition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, transitionMetaClass, firstInstanceOf, null);

		// Change value of the redefinedTransition feature of the Transition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Transition.Properties.redefinedTransition, allInstancesOf.indexOf(referenceValueForRedefinedTransition)+1);

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
	protected void initializeRemoveExpectedModelForTransitionRedefinedTransition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject transition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, transitionMetaClass);
		cc.append(SetCommand.create(editingDomain, transition, UMLPackage.eINSTANCE.getTransition_RedefinedTransition(), null));
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
	public void testRemoveTransitionRedefinedTransition() throws Exception {

		// Import the input model
		initializeInputModel();

		transition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTransitionRedefinedTransition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Transition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, transitionMetaClass, firstInstanceOf, null);

		// Change value of the redefinedTransition feature of the Transition element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Transition.Properties.redefinedTransition);
		

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
	protected void initializeExpectedModelForTransitionGuard() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject transition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForGuard = bot.changeReferenceValue(allInstancesOf, ((Transition)transition).getGuard());
		cc.append(SetCommand.create(editingDomain, transition, UMLPackage.eINSTANCE.getTransition_Guard(), referenceValueForGuard));
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
	public void testEditTransitionGuard() throws Exception {

		// Import the input model
		initializeInputModel();

		transition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTransitionGuard();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Transition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, transitionMetaClass, firstInstanceOf, null);

		// Change value of the guard feature of the Transition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Transition.Properties.guard, allInstancesOf.indexOf(referenceValueForGuard)+1);

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
	protected void initializeRemoveExpectedModelForTransitionGuard() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject transition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		cc.append(SetCommand.create(editingDomain, transition, UMLPackage.eINSTANCE.getTransition_Guard(), null));
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
	public void testRemoveTransitionGuard() throws Exception {

		// Import the input model
		initializeInputModel();

		transition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (transition == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTransitionGuard();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Transition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), transitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(transitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, transitionMetaClass, firstInstanceOf, null);

		// Change value of the guard feature of the Transition element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Transition.Properties.guard);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
