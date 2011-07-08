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
package org.eclipse.uml2.uml.tests.junit.properties;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ExecutionEnvironment;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ExecutionEnvironment
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ExecutionEnvironmentPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass executionEnvironmentMetaClass = UMLPackage.eINSTANCE.getExecutionEnvironment();

	/**
	 * The type to edit
	 */
	private EObject executionEnvironment;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class classifierBehavior
	 */
	private Object referenceValueForClassifierBehavior;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

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
	protected void initializeExpectedModelForExecutionEnvironmentName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditExecutionEnvironmentName() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionEnvironmentName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the ExecutionEnvironment element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExecutionEnvironmentVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditExecutionEnvironmentVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ExecutionEnvironment)executionEnvironment).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForExecutionEnvironmentVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the ExecutionEnvironment element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExecutionEnvironmentClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ExecutionEnvironment)executionEnvironment).getClientDependency());
		cc.append(AddCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditExecutionEnvironmentClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionEnvironmentClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExecutionEnvironment element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExecutionEnvironmentClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)executionEnvironment.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveExecutionEnvironmentClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionEnvironmentClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExecutionEnvironment element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExecutionEnvironmentIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditExecutionEnvironmentIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionEnvironmentIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ExecutionEnvironment element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExecutionEnvironmentOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((ExecutionEnvironment)executionEnvironment).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditExecutionEnvironmentOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionEnvironmentOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the ExecutionEnvironment element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExecutionEnvironmentOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveExecutionEnvironmentOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionEnvironmentOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the ExecutionEnvironment element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForExecutionEnvironmentTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((ExecutionEnvironment)executionEnvironment).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditExecutionEnvironmentTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionEnvironmentTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the ExecutionEnvironment element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExecutionEnvironmentTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveExecutionEnvironmentTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionEnvironmentTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the ExecutionEnvironment element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForExecutionEnvironmentIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditExecutionEnvironmentIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionEnvironmentIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isAbstract feature of the ExecutionEnvironment element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.isAbstract, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExecutionEnvironmentPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((ExecutionEnvironment)executionEnvironment).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditExecutionEnvironmentPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionEnvironmentPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the ExecutionEnvironment element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.powertypeExtent, referenceValueForPowertypeExtent, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExecutionEnvironmentPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)executionEnvironment.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveExecutionEnvironmentPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionEnvironmentPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the ExecutionEnvironment element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExecutionEnvironmentRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((ExecutionEnvironment)executionEnvironment).getRepresentation());
		cc.append(SetCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditExecutionEnvironmentRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionEnvironmentRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the representation feature of the ExecutionEnvironment element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExecutionEnvironmentRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveExecutionEnvironmentRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionEnvironmentRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the representation feature of the ExecutionEnvironment element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.representation, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForExecutionEnvironmentUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((ExecutionEnvironment)executionEnvironment).getUseCase());
		cc.append(AddCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditExecutionEnvironmentUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionEnvironmentUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the useCase feature of the ExecutionEnvironment element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.useCase, referenceValueForUseCase, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForExecutionEnvironmentUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)executionEnvironment.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveExecutionEnvironmentUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExecutionEnvironmentUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the useCase feature of the ExecutionEnvironment element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForExecutionEnvironmentIsActive() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, executionEnvironment, UMLPackage.eINSTANCE.getClass_IsActive(), UPDATED_VALUE));
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
	public void testEditExecutionEnvironmentIsActive() throws Exception {

		// Import the input model
		initializeInputModel();

		executionEnvironment = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (executionEnvironment == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExecutionEnvironmentIsActive();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ExecutionEnvironment element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), executionEnvironmentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(executionEnvironmentMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isActive feature of the ExecutionEnvironment element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ExecutionEnvironment.Properties.isActive, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
























}
