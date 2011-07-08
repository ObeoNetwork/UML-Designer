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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ProtocolTransition;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ProtocolTransition
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ProtocolTransitionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass protocolTransitionMetaClass = UMLPackage.eINSTANCE.getProtocolTransition();

	/**
	 * The type to edit
	 */
	private EObject protocolTransition;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;

	/**
	 * The enum value for the enum class kind
	 */
	private Object enumValueForKind;
	/**
	 * The reference value for the reference class preCondition
	 */
	private Object referenceValueForPreCondition;

	/**
	 * The reference value for the reference class guard
	 */
	private Object referenceValueForGuard;

	/**
	 * The reference value for the reference class redefinedTransition
	 */
	private Object referenceValueForRedefinedTransition;

	/**
	 * The reference value for the reference class source
	 */
	private Object referenceValueForSource;

	/**
	 * The reference value for the reference class postCondition
	 */
	private Object referenceValueForPostCondition;

	/**
	 * The reference value for the reference class container
	 */
	private Object referenceValueForContainer;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class target
	 */
	private Object referenceValueForTarget;
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
	private EClass transitionMetaClass = UMLPackage.eINSTANCE.getTransition();

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
	protected void initializeExpectedModelForProtocolTransitionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditProtocolTransitionName() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolTransitionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ProtocolTransition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProtocolTransitionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditProtocolTransitionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ProtocolTransition)protocolTransition).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForProtocolTransitionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ProtocolTransition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProtocolTransitionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ProtocolTransition)protocolTransition).getClientDependency());
		cc.append(AddCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditProtocolTransitionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolTransitionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ProtocolTransition element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForProtocolTransitionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)protocolTransition.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveProtocolTransitionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolTransitionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ProtocolTransition element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForProtocolTransitionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditProtocolTransitionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolTransitionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ProtocolTransition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProtocolTransitionKind() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getTransition_Kind(), UPDATED_VALUE));
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
	public void testEditProtocolTransitionKind() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		enumValueForKind = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getTransitionKind(), ((ProtocolTransition)protocolTransition).getKind().getLiteral());
		// Create the expected model
		initializeExpectedModelForProtocolTransitionKind();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the kind feature of the ProtocolTransition element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.kind, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProtocolTransitionContainer() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		referenceValueForContainer = bot.changeReferenceValue(allInstancesOf, ((ProtocolTransition)protocolTransition).getContainer());
		cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getTransition_Container(), referenceValueForContainer));
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
	public void testEditProtocolTransitionContainer() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolTransitionContainer();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the container feature of the ProtocolTransition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.container, allInstancesOf.indexOf(referenceValueForContainer));

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
	protected void initializeExpectedModelForProtocolTransitionRedefinedTransition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, transitionMetaClass);
		referenceValueForRedefinedTransition = bot.changeReferenceValue(allInstancesOf, ((ProtocolTransition)protocolTransition).getRedefinedTransition());
		cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getTransition_RedefinedTransition(), referenceValueForRedefinedTransition));
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
	public void testEditProtocolTransitionRedefinedTransition() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolTransitionRedefinedTransition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the redefinedTransition feature of the ProtocolTransition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.redefinedTransition, allInstancesOf.indexOf(referenceValueForRedefinedTransition)+1);

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
	protected void initializeRemoveExpectedModelForProtocolTransitionRedefinedTransition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, transitionMetaClass);
		cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getTransition_RedefinedTransition(), null));
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
	public void testRemoveProtocolTransitionRedefinedTransition() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolTransitionRedefinedTransition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the redefinedTransition feature of the ProtocolTransition element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.redefinedTransition);
		

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
	protected void initializeExpectedModelForProtocolTransitionGuard() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForGuard = bot.changeReferenceValue(allInstancesOf, ((ProtocolTransition)protocolTransition).getGuard());
		cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getTransition_Guard(), referenceValueForGuard));
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
	public void testEditProtocolTransitionGuard() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolTransitionGuard();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the guard feature of the ProtocolTransition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.guard, allInstancesOf.indexOf(referenceValueForGuard)+1);

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
	protected void initializeRemoveExpectedModelForProtocolTransitionGuard() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getTransition_Guard(), null));
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
	public void testRemoveProtocolTransitionGuard() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolTransitionGuard();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the guard feature of the ProtocolTransition element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.guard);
		

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
	protected void initializeExpectedModelForProtocolTransitionPostCondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPostCondition = bot.changeReferenceValue(allInstancesOf, ((ProtocolTransition)protocolTransition).getPostCondition());
		cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getProtocolTransition_PostCondition(), referenceValueForPostCondition));
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
	public void testEditProtocolTransitionPostCondition() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolTransitionPostCondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the postCondition feature of the ProtocolTransition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.postCondition, allInstancesOf.indexOf(referenceValueForPostCondition)+1);

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
	protected void initializeRemoveExpectedModelForProtocolTransitionPostCondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getProtocolTransition_PostCondition(), null));
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
	public void testRemoveProtocolTransitionPostCondition() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolTransitionPostCondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the postCondition feature of the ProtocolTransition element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.postCondition);
		

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
	protected void initializeExpectedModelForProtocolTransitionPreCondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPreCondition = bot.changeReferenceValue(allInstancesOf, ((ProtocolTransition)protocolTransition).getPreCondition());
		cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getProtocolTransition_PreCondition(), referenceValueForPreCondition));
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
	public void testEditProtocolTransitionPreCondition() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProtocolTransitionPreCondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the preCondition feature of the ProtocolTransition element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.preCondition, allInstancesOf.indexOf(referenceValueForPreCondition)+1);

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
	protected void initializeRemoveExpectedModelForProtocolTransitionPreCondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		cc.append(SetCommand.create(editingDomain, protocolTransition, UMLPackage.eINSTANCE.getProtocolTransition_PreCondition(), null));
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
	public void testRemoveProtocolTransitionPreCondition() throws Exception {

		// Import the input model
		initializeInputModel();

		protocolTransition = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (protocolTransition == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProtocolTransitionPreCondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ProtocolTransition element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), protocolTransitionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(protocolTransitionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, protocolTransitionMetaClass, firstInstanceOf, null);

		// Change value of the preCondition feature of the ProtocolTransition element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ProtocolTransition.Properties.preCondition);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
