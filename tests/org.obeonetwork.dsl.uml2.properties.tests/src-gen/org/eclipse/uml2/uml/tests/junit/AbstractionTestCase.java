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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Abstraction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Abstraction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class AbstractionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass abstractionMetaClass = UMLPackage.eINSTANCE.getAbstraction();

	/**
	 * The type to edit
	 */
	private EObject abstraction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class client
	 */
	private Object referenceValueForClient;

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
	 * The reference value for the reference class supplier
	 */
	private Object referenceValueForSupplier;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass namedElementMetaClass = UMLPackage.eINSTANCE.getNamedElement();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

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
	protected void initializeExpectedModelForAbstractionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject abstraction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, abstraction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditAbstractionName() throws Exception {

		// Import the input model
		initializeInputModel();

		abstraction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAbstractionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Abstraction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, abstractionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Abstraction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Abstraction.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForAbstractionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject abstraction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, abstraction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditAbstractionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		abstraction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Abstraction)abstraction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForAbstractionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Abstraction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, abstractionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Abstraction element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Abstraction.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForAbstractionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject abstraction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Abstraction)abstraction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, abstraction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditAbstractionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		abstraction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAbstractionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Abstraction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, abstractionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Abstraction element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Abstraction.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForAbstractionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject abstraction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)abstraction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, abstraction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveAbstractionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		abstraction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAbstractionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Abstraction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, abstractionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Abstraction element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Abstraction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForAbstractionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject abstraction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Abstraction)abstraction).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, abstraction, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditAbstractionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		abstraction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAbstractionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Abstraction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, abstractionMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Abstraction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Abstraction.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForAbstractionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject abstraction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, abstraction, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveAbstractionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		abstraction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAbstractionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Abstraction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, abstractionMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Abstraction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Abstraction.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForAbstractionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject abstraction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Abstraction)abstraction).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, abstraction, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditAbstractionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		abstraction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAbstractionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Abstraction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, abstractionMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Abstraction element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Abstraction.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForAbstractionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject abstraction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, abstraction, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveAbstractionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		abstraction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (abstraction == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAbstractionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Abstraction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), abstractionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(abstractionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, abstractionMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Abstraction element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Abstraction.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
