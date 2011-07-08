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
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;

import sun.misc.Signal;
/**
 * TestCase for Signal
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class SignalTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass signalMetaClass = UMLPackage.eINSTANCE.getSignal();

	/**
	 * The type to edit
	 */
	private EObject signal;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
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
	protected void initializeExpectedModelForSignalName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditSignalName() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Signal element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Signal.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSignalVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditSignalVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Signal)signal).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForSignalVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Signal element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Signal.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSignalClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Signal)signal).getClientDependency());
		cc.append(AddCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditSignalClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Signal element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Signal.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForSignalClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)signal.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveSignalClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSignalClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Signal element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Signal.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSignalIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditSignalIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Signal element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Signal.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSignalOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Signal)signal).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditSignalOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Signal element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Signal.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForSignalOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveSignalOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSignalOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Signal element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Signal.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForSignalTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Signal)signal).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditSignalTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Signal element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Signal.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForSignalTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveSignalTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSignalTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Signal element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Signal.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForSignalIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditSignalIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the Signal element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Signal.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSignalPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((Signal)signal).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditSignalPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Signal element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Signal.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForSignalPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)signal.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveSignalPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSignalPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Signal element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Signal.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSignalRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((Signal)signal).getRepresentation());
		cc.append(SetCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditSignalRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Signal element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Signal.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForSignalRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveSignalRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSignalRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Signal element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Signal.Properties.representation);
		

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
	protected void initializeExpectedModelForSignalUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((Signal)signal).getUseCase());
		cc.append(AddCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditSignalUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSignalUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Signal element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Signal.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForSignalUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject signal = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)signal.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, signal, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveSignalUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		signal = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (signal == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSignalUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Signal element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), signalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(signalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, signalMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Signal element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Signal.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
