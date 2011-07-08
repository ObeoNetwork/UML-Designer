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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.GeneralizationSet;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for GeneralizationSet
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class GeneralizationSetTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass generalizationSetMetaClass = UMLPackage.eINSTANCE.getGeneralizationSet();

	/**
	 * The type to edit
	 */
	private EObject generalizationSet;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class generalization
	 */
	private Object referenceValueForGeneralization;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class powertype
	 */
	private Object referenceValueForPowertype;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass generalizationMetaClass = UMLPackage.eINSTANCE.getGeneralization();

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
	protected void initializeExpectedModelForGeneralizationSetName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditGeneralizationSetName() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralizationSetName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the GeneralizationSet element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForGeneralizationSetVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditGeneralizationSetVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((GeneralizationSet)generalizationSet).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForGeneralizationSetVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the GeneralizationSet element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForGeneralizationSetClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((GeneralizationSet)generalizationSet).getClientDependency());
		cc.append(AddCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditGeneralizationSetClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralizationSetClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the GeneralizationSet element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForGeneralizationSetClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)generalizationSet.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveGeneralizationSetClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForGeneralizationSetClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the GeneralizationSet element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForGeneralizationSetOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((GeneralizationSet)generalizationSet).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditGeneralizationSetOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralizationSetOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the GeneralizationSet element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForGeneralizationSetOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveGeneralizationSetOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForGeneralizationSetOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the GeneralizationSet element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForGeneralizationSetTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((GeneralizationSet)generalizationSet).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditGeneralizationSetTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralizationSetTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the GeneralizationSet element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForGeneralizationSetTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveGeneralizationSetTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForGeneralizationSetTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the GeneralizationSet element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForGeneralizationSetIsCovering() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getGeneralizationSet_IsCovering(), UPDATED_VALUE));
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
	public void testEditGeneralizationSetIsCovering() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralizationSetIsCovering();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the isCovering feature of the GeneralizationSet element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.isCovering, UPDATED_VALUE);

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
	protected void initializeExpectedModelForGeneralizationSetIsDisjoint() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getGeneralizationSet_IsDisjoint(), UPDATED_VALUE));
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
	public void testEditGeneralizationSetIsDisjoint() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralizationSetIsDisjoint();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the isDisjoint feature of the GeneralizationSet element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.isDisjoint, UPDATED_VALUE);

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
	protected void initializeExpectedModelForGeneralizationSetGeneralization() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationMetaClass);
		referenceValueForGeneralization = bot.changeReferenceValue(allInstancesOf, ((GeneralizationSet)generalizationSet).getGeneralization());
		cc.append(AddCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getGeneralizationSet_Generalization(), referenceValueForGeneralization));
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
	public void testEditGeneralizationSetGeneralization() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralizationSetGeneralization();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the generalization feature of the GeneralizationSet element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.generalization, referenceValueForGeneralization);

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
	protected void initializeRemoveExpectedModelForGeneralizationSetGeneralization() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)generalizationSet.eGet(UMLPackage.eINSTANCE.getGeneralizationSet_Generalization());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, generalizationSet, UMLPackage.eINSTANCE.getGeneralizationSet_Generalization(), allReferencedInstances.get(0)));
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
	public void testRemoveGeneralizationSetGeneralization() throws Exception {

		// Import the input model
		initializeInputModel();

		generalizationSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (generalizationSet == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForGeneralizationSetGeneralization();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the GeneralizationSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, generalizationSetMetaClass, firstInstanceOf, null);

		// Change value of the generalization feature of the GeneralizationSet element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.GeneralizationSet.Properties.generalization, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


















}
