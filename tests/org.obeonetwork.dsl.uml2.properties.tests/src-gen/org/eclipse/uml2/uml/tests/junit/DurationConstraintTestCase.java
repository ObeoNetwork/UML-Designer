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
import org.eclipse.uml2.uml.DurationConstraint;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for DurationConstraint
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class DurationConstraintTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass durationConstraintMetaClass = UMLPackage.eINSTANCE.getDurationConstraint();

	/**
	 * The type to edit
	 */
	private EObject durationConstraint;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class context
	 */
	private Object referenceValueForContext;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class constrainedElement
	 */
	private Object referenceValueForConstrainedElement;
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
	protected void initializeExpectedModelForDurationConstraintName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, durationConstraint, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditDurationConstraintName() throws Exception {

		// Import the input model
		initializeInputModel();

		durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationConstraintName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationConstraintMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the DurationConstraint element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DurationConstraint.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDurationConstraintVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, durationConstraint, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditDurationConstraintVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((DurationConstraint)durationConstraint).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForDurationConstraintVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationConstraintMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the DurationConstraint element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DurationConstraint.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDurationConstraintClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((DurationConstraint)durationConstraint).getClientDependency());
		cc.append(AddCommand.create(editingDomain, durationConstraint, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditDurationConstraintClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationConstraintClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationConstraintMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DurationConstraint element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DurationConstraint.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForDurationConstraintClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)durationConstraint.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, durationConstraint, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveDurationConstraintClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDurationConstraintClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationConstraintMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DurationConstraint element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.DurationConstraint.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDurationConstraintOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((DurationConstraint)durationConstraint).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, durationConstraint, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditDurationConstraintOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationConstraintOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationConstraintMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the DurationConstraint element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DurationConstraint.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDurationConstraintOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, durationConstraint, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveDurationConstraintOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDurationConstraintOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationConstraintMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the DurationConstraint element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DurationConstraint.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForDurationConstraintTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((DurationConstraint)durationConstraint).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, durationConstraint, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditDurationConstraintTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationConstraintTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationConstraintMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the DurationConstraint element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DurationConstraint.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDurationConstraintTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, durationConstraint, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveDurationConstraintTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDurationConstraintTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationConstraintMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the DurationConstraint element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.DurationConstraint.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForDurationConstraintFirstEvent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, durationConstraint, UMLPackage.eINSTANCE.getDurationConstraint_FirstEvent(), UPDATED_VALUE));
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
	public void testEditDurationConstraintFirstEvent() throws Exception {

		// Import the input model
		initializeInputModel();

		durationConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (durationConstraint == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDurationConstraintFirstEvent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the DurationConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), durationConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(durationConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, durationConstraintMetaClass, firstInstanceOf, null);

		// Change value of the firstEvent feature of the DurationConstraint element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.DurationConstraint.Properties.firstEvent, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
