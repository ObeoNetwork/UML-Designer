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

import com.sun.org.apache.xpath.internal.operations.Variable;
/**
 * TestCase for Variable
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class VariableTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass variableMetaClass = UMLPackage.eINSTANCE.getVariable();

	/**
	 * The type to edit
	 */
	private EObject variable;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class activityScope
	 */
	private Object referenceValueForActivityScope;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class scope
	 */
	private Object referenceValueForScope;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass structuredActivityNodeMetaClass = UMLPackage.eINSTANCE.getStructuredActivityNode();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityMetaClass = UMLPackage.eINSTANCE.getActivity();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass typeMetaClass = UMLPackage.eINSTANCE.getType();

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
	protected void initializeExpectedModelForVariableName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditVariableName() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForVariableName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Variable element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Variable.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForVariableVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditVariableVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Variable)variable).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForVariableVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Variable element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Variable.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForVariableClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Variable)variable).getClientDependency());
		cc.append(AddCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditVariableClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForVariableClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Variable element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Variable.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForVariableClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)variable.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveVariableClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForVariableClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Variable element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Variable.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForVariableOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Variable)variable).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditVariableOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForVariableOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Variable element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Variable.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForVariableOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveVariableOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForVariableOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Variable element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Variable.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForVariableTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Variable)variable).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditVariableTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForVariableTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Variable element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Variable.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForVariableTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveVariableTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForVariableTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Variable element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Variable.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForVariableIsOrdered() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), UPDATED_VALUE));
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
	public void testEditVariableIsOrdered() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForVariableIsOrdered();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the isOrdered feature of the Variable element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Variable.Properties.isOrdered, UPDATED_VALUE);

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
	protected void initializeExpectedModelForVariableIsUnique() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), UPDATED_VALUE));
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
	public void testEditVariableIsUnique() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForVariableIsUnique();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the isUnique feature of the Variable element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Variable.Properties.isUnique, UPDATED_VALUE);

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
	protected void initializeExpectedModelForVariableScope() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForScope = bot.changeReferenceValue(allInstancesOf, ((Variable)variable).getScope());
		cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getVariable_Scope(), referenceValueForScope));
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
	public void testEditVariableScope() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForVariableScope();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the scope feature of the Variable element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Variable.Properties.scope, allInstancesOf.indexOf(referenceValueForScope)+1);

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
	protected void initializeRemoveExpectedModelForVariableScope() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getVariable_Scope(), null));
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
	public void testRemoveVariableScope() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForVariableScope();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the scope feature of the Variable element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Variable.Properties.scope);
		

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
	protected void initializeExpectedModelForVariableActivityScope() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivityScope = bot.changeReferenceValue(allInstancesOf, ((Variable)variable).getActivityScope());
		cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getVariable_ActivityScope(), referenceValueForActivityScope));
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
	public void testEditVariableActivityScope() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForVariableActivityScope();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the activityScope feature of the Variable element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Variable.Properties.activityScope, allInstancesOf.indexOf(referenceValueForActivityScope)+1);

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
	protected void initializeRemoveExpectedModelForVariableActivityScope() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject variable = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, variable, UMLPackage.eINSTANCE.getVariable_ActivityScope(), null));
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
	public void testRemoveVariableActivityScope() throws Exception {

		// Import the input model
		initializeInputModel();

		variable = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (variable == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForVariableActivityScope();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Variable element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), variableMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(variableMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, variableMetaClass, firstInstanceOf, null);

		// Change value of the activityScope feature of the Variable element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Variable.Properties.activityScope);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
