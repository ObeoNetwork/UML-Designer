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
import org.eclipse.uml2.uml.TimeConstraint;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for TimeConstraint
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class TimeConstraintTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass timeConstraintMetaClass = UMLPackage.eINSTANCE.getTimeConstraint();

	/**
	 * The type to edit
	 */
	private EObject timeConstraint;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class context
	 */
	private Object referenceValueForContext;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class constrainedElement
	 */
	private Object referenceValueForConstrainedElement;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass namespaceMetaClass = UMLPackage.eINSTANCE.getNamespace();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass elementMetaClass = UMLPackage.eINSTANCE.getElement();

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
	protected void initializeExpectedModelForTimeConstraintName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, timeConstraint, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditTimeConstraintName() throws Exception {

		// Import the input model
		initializeInputModel();

		timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeConstraintName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeConstraintMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the TimeConstraint element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.TimeConstraint.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTimeConstraintVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, timeConstraint, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditTimeConstraintVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((TimeConstraint)timeConstraint).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForTimeConstraintVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeConstraintMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the TimeConstraint element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.TimeConstraint.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForTimeConstraintClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((TimeConstraint)timeConstraint).getClientDependency());
		cc.append(AddCommand.create(editingDomain, timeConstraint, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditTimeConstraintClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeConstraintClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeConstraintMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the TimeConstraint element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.TimeConstraint.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForTimeConstraintClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)timeConstraint.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, timeConstraint, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveTimeConstraintClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeConstraintClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeConstraintMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the TimeConstraint element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.TimeConstraint.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForTimeConstraintOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((TimeConstraint)timeConstraint).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, timeConstraint, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditTimeConstraintOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeConstraintOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeConstraintMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the TimeConstraint element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeConstraint.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForTimeConstraintOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, timeConstraint, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveTimeConstraintOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeConstraintOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeConstraintMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the TimeConstraint element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeConstraint.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForTimeConstraintTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((TimeConstraint)timeConstraint).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, timeConstraint, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditTimeConstraintTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeConstraintTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeConstraintMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the TimeConstraint element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeConstraint.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForTimeConstraintTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, timeConstraint, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveTimeConstraintTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTimeConstraintTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeConstraintMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the TimeConstraint element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.TimeConstraint.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForTimeConstraintFirstEvent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, timeConstraint, UMLPackage.eINSTANCE.getTimeConstraint_FirstEvent(), UPDATED_VALUE));
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
	public void testEditTimeConstraintFirstEvent() throws Exception {

		// Import the input model
		initializeInputModel();

		timeConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (timeConstraint == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTimeConstraintFirstEvent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TimeConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), timeConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(timeConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, timeConstraintMetaClass, firstInstanceOf, null);

		// Change value of the firstEvent feature of the TimeConstraint element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.TimeConstraint.Properties.firstEvent, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
