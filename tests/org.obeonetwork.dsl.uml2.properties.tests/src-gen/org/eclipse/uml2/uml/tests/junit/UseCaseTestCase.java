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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.UseCase;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for UseCase
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class UseCaseTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass useCaseMetaClass = UMLPackage.eINSTANCE.getUseCase();

	/**
	 * The type to edit
	 */
	private EObject useCase;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class subject
	 */
	private Object referenceValueForSubject;

	/**
	 * The reference value for the reference class classifierBehavior
	 */
	private Object referenceValueForClassifierBehavior;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;
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
	private EClass behaviorMetaClass = UMLPackage.eINSTANCE.getBehavior();

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
	protected void initializeExpectedModelForUseCaseName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditUseCaseName() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUseCaseName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the UseCase element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.UseCase.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForUseCaseVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditUseCaseVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((UseCase)useCase).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForUseCaseVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the UseCase element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.UseCase.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForUseCaseClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((UseCase)useCase).getClientDependency());
		cc.append(AddCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditUseCaseClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUseCaseClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the UseCase element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UseCase.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForUseCaseClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)useCase.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveUseCaseClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForUseCaseClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the UseCase element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UseCase.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForUseCaseIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditUseCaseIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUseCaseIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the UseCase element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.UseCase.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForUseCaseOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((UseCase)useCase).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditUseCaseOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUseCaseOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the UseCase element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.UseCase.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForUseCaseOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveUseCaseOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForUseCaseOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the UseCase element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.UseCase.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForUseCaseTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((UseCase)useCase).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditUseCaseTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUseCaseTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the UseCase element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.UseCase.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForUseCaseTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveUseCaseTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForUseCaseTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the UseCase element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.UseCase.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForUseCaseIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditUseCaseIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUseCaseIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the UseCase element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.UseCase.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForUseCasePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((UseCase)useCase).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditUseCasePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUseCasePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the UseCase element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UseCase.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForUseCasePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)useCase.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveUseCasePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForUseCasePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the UseCase element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UseCase.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForUseCaseRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((UseCase)useCase).getRepresentation());
		cc.append(SetCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditUseCaseRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUseCaseRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the UseCase element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.UseCase.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForUseCaseRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveUseCaseRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForUseCaseRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the UseCase element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.UseCase.Properties.representation);
		

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
	protected void initializeExpectedModelForUseCaseUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((UseCase)useCase).getUseCase());
		cc.append(AddCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditUseCaseUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForUseCaseUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the UseCase element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UseCase.Properties.useCase_, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForUseCaseUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject useCase = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)useCase.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, useCase, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveUseCaseUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		useCase = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (useCase == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForUseCaseUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the UseCase element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), useCaseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(useCaseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, useCaseMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the UseCase element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.UseCase.Properties.useCase_, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
