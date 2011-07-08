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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Dependency;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Dependency
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class DependencyTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass dependencyMetaClass = UMLPackage.eINSTANCE.getDependency();

	/**
	 * The type to edit
	 */
	private EObject dependency;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class client
	 */
	private Object referenceValueForClient;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class supplier
	 */
	private Object referenceValueForSupplier;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass namedElementMetaClass = UMLPackage.eINSTANCE.getNamedElement();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();
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
	protected void initializeExpectedModelForDependencyName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dependency = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, dependency, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditDependencyName() throws Exception {

		// Import the input model
		initializeInputModel();

		dependency = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDependencyName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Dependency element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dependencyMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Dependency element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Dependency.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDependencyVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dependency = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, dependency, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditDependencyVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		dependency = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Dependency)dependency).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForDependencyVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Dependency element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dependencyMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Dependency element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Dependency.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDependencyClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dependency = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Dependency)dependency).getClientDependency());
		cc.append(AddCommand.create(editingDomain, dependency, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditDependencyClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		dependency = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDependencyClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Dependency element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dependencyMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Dependency element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Dependency.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForDependencyClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dependency = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)dependency.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, dependency, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveDependencyClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		dependency = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDependencyClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Dependency element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dependencyMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Dependency element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Dependency.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDependencyOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dependency = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Dependency)dependency).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, dependency, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditDependencyOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		dependency = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDependencyOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Dependency element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dependencyMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Dependency element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Dependency.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDependencyOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dependency = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, dependency, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveDependencyOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		dependency = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDependencyOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Dependency element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dependencyMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Dependency element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Dependency.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForDependencyTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dependency = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Dependency)dependency).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, dependency, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditDependencyTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		dependency = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDependencyTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Dependency element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dependencyMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Dependency element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Dependency.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDependencyTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject dependency = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, dependency, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveDependencyTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		dependency = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (dependency == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDependencyTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Dependency element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), dependencyMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(dependencyMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, dependencyMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Dependency element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Dependency.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
