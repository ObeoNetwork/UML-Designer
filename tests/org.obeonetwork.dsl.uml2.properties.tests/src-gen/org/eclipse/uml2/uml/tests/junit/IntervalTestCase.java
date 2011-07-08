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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Interval;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Interval
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class IntervalTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass intervalMetaClass = UMLPackage.eINSTANCE.getInterval();

	/**
	 * The type to edit
	 */
	private EObject interval;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class max
	 */
	private Object referenceValueForMax;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class min
	 */
	private Object referenceValueForMin;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass typeMetaClass = UMLPackage.eINSTANCE.getType();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass valueSpecificationMetaClass = UMLPackage.eINSTANCE.getValueSpecification();

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
	protected void initializeExpectedModelForIntervalName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interval = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interval, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditIntervalName() throws Exception {

		// Import the input model
		initializeInputModel();

		interval = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIntervalName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interval element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, intervalMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Interval element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Interval.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForIntervalVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interval = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interval, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditIntervalVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		interval = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Interval)interval).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForIntervalVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interval element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, intervalMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Interval element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Interval.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForIntervalClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interval = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Interval)interval).getClientDependency());
		cc.append(AddCommand.create(editingDomain, interval, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditIntervalClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interval = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIntervalClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interval element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, intervalMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Interval element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Interval.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForIntervalClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interval = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)interval.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, interval, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveIntervalClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interval = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForIntervalClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interval element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, intervalMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Interval element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Interval.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForIntervalOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interval = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Interval)interval).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, interval, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditIntervalOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interval = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIntervalOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interval element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, intervalMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Interval element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Interval.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForIntervalOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interval = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, interval, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveIntervalOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interval = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForIntervalOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interval element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, intervalMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Interval element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Interval.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForIntervalTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interval = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Interval)interval).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, interval, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditIntervalTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interval = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIntervalTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interval element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, intervalMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Interval element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Interval.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForIntervalTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interval = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, interval, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveIntervalTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interval = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (interval == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForIntervalTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interval element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, intervalMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Interval element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Interval.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
