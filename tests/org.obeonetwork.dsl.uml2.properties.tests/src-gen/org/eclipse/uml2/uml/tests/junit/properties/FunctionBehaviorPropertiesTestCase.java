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
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for FunctionBehavior
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class FunctionBehaviorPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass functionBehaviorMetaClass = UMLPackage.eINSTANCE.getFunctionBehavior();

	/**
	 * The type to edit
	 */
	private EObject functionBehavior;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class precondition
	 */
	private Object referenceValueForPrecondition;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class postcondition
	 */
	private Object referenceValueForPostcondition;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class classifierBehavior
	 */
	private Object referenceValueForClassifierBehavior;

	/**
	 * The reference value for the reference class redefinedBehavior
	 */
	private Object referenceValueForRedefinedBehavior;

	/**
	 * The reference value for the reference class specification
	 */
	private Object referenceValueForSpecification;
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
	protected void initializeExpectedModelForFunctionBehaviorName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditFunctionBehaviorName() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the FunctionBehavior element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditFunctionBehaviorVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((FunctionBehavior)functionBehavior).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForFunctionBehaviorVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the FunctionBehavior element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((FunctionBehavior)functionBehavior).getClientDependency());
		cc.append(AddCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditFunctionBehaviorClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the FunctionBehavior element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFunctionBehaviorClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)functionBehavior.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveFunctionBehaviorClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFunctionBehaviorClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the FunctionBehavior element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditFunctionBehaviorIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the FunctionBehavior element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((FunctionBehavior)functionBehavior).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditFunctionBehaviorOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the FunctionBehavior element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFunctionBehaviorOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveFunctionBehaviorOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFunctionBehaviorOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the FunctionBehavior element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForFunctionBehaviorTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((FunctionBehavior)functionBehavior).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditFunctionBehaviorTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the FunctionBehavior element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFunctionBehaviorTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveFunctionBehaviorTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFunctionBehaviorTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the FunctionBehavior element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForFunctionBehaviorIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditFunctionBehaviorIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isAbstract feature of the FunctionBehavior element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.isAbstract, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((FunctionBehavior)functionBehavior).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditFunctionBehaviorPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the FunctionBehavior element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent, referenceValueForPowertypeExtent, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFunctionBehaviorPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)functionBehavior.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveFunctionBehaviorPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFunctionBehaviorPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the FunctionBehavior element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((FunctionBehavior)functionBehavior).getRepresentation());
		cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditFunctionBehaviorRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the representation feature of the FunctionBehavior element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFunctionBehaviorRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveFunctionBehaviorRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFunctionBehaviorRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the representation feature of the FunctionBehavior element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.representation, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForFunctionBehaviorUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((FunctionBehavior)functionBehavior).getUseCase());
		cc.append(AddCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditFunctionBehaviorUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the useCase feature of the FunctionBehavior element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.useCase, referenceValueForUseCase, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFunctionBehaviorUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)functionBehavior.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveFunctionBehaviorUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFunctionBehaviorUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the useCase feature of the FunctionBehavior element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorIsActive() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getClass_IsActive(), UPDATED_VALUE));
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
	public void testEditFunctionBehaviorIsActive() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorIsActive();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isActive feature of the FunctionBehavior element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.isActive, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorIsReentrant() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getBehavior_IsReentrant(), UPDATED_VALUE));
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
	public void testEditFunctionBehaviorIsReentrant() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorIsReentrant();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isReentrant feature of the FunctionBehavior element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.isReentrant, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorPrecondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPrecondition = bot.changeReferenceValue(allInstancesOf, ((FunctionBehavior)functionBehavior).getPrecondition());
		cc.append(AddCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getBehavior_Precondition(), referenceValueForPrecondition));
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
	public void testEditFunctionBehaviorPrecondition() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorPrecondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the precondition feature of the FunctionBehavior element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.precondition, referenceValueForPrecondition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFunctionBehaviorPrecondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)functionBehavior.eGet(UMLPackage.eINSTANCE.getBehavior_Precondition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getBehavior_Precondition(), allReferencedInstances.get(0)));
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
	public void testRemoveFunctionBehaviorPrecondition() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFunctionBehaviorPrecondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the precondition feature of the FunctionBehavior element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.precondition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorPostcondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPostcondition = bot.changeReferenceValue(allInstancesOf, ((FunctionBehavior)functionBehavior).getPostcondition());
		cc.append(AddCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getBehavior_Postcondition(), referenceValueForPostcondition));
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
	public void testEditFunctionBehaviorPostcondition() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorPostcondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the postcondition feature of the FunctionBehavior element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.postcondition, referenceValueForPostcondition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForFunctionBehaviorPostcondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)functionBehavior.eGet(UMLPackage.eINSTANCE.getBehavior_Postcondition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getBehavior_Postcondition(), allReferencedInstances.get(0)));
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
	public void testRemoveFunctionBehaviorPostcondition() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForFunctionBehaviorPostcondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the postcondition feature of the FunctionBehavior element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.postcondition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorBody() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getOpaqueBehavior_Body(), UPDATED_VALUE));
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
	public void testEditFunctionBehaviorBody() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorBody();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the body feature of the FunctionBehavior element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.body, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForFunctionBehaviorLanguage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, functionBehavior, UMLPackage.eINSTANCE.getOpaqueBehavior_Language(), UPDATED_VALUE));
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
	public void testEditFunctionBehaviorLanguage() throws Exception {

		// Import the input model
		initializeInputModel();

		functionBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (functionBehavior == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForFunctionBehaviorLanguage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the FunctionBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), functionBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(functionBehaviorMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the language feature of the FunctionBehavior element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.FunctionBehavior.Properties.language, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


































}
