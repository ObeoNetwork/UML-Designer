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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.InterfaceRealization;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for InterfaceRealization
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class InterfaceRealizationTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass interfaceRealizationMetaClass = UMLPackage.eINSTANCE.getInterfaceRealization();

	/**
	 * The type to edit
	 */
	private EObject interfaceRealization;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class contract
	 */
	private Object referenceValueForContract;

	/**
	 * The reference value for the reference class supplier
	 */
	private Object referenceValueForSupplier;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class implementingClassifier
	 */
	private Object referenceValueForImplementingClassifier;

	/**
	 * The reference value for the reference class client
	 */
	private Object referenceValueForClient;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass interfaceMetaClass = UMLPackage.eINSTANCE.getInterface();

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
	private EClass behavioredClassifierMetaClass = UMLPackage.eINSTANCE.getBehavioredClassifier();

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
	protected void initializeExpectedModelForInterfaceRealizationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interfaceRealization, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditInterfaceRealizationName() throws Exception {

		// Import the input model
		initializeInputModel();

		interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceRealizationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InterfaceRealization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceRealizationMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the InterfaceRealization element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InterfaceRealization.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInterfaceRealizationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interfaceRealization, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditInterfaceRealizationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((InterfaceRealization)interfaceRealization).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForInterfaceRealizationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InterfaceRealization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceRealizationMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the InterfaceRealization element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InterfaceRealization.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInterfaceRealizationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((InterfaceRealization)interfaceRealization).getClientDependency());
		cc.append(AddCommand.create(editingDomain, interfaceRealization, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditInterfaceRealizationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceRealizationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InterfaceRealization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceRealizationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InterfaceRealization element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InterfaceRealization.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForInterfaceRealizationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)interfaceRealization.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, interfaceRealization, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveInterfaceRealizationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInterfaceRealizationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InterfaceRealization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceRealizationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InterfaceRealization element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InterfaceRealization.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInterfaceRealizationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((InterfaceRealization)interfaceRealization).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, interfaceRealization, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditInterfaceRealizationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceRealizationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InterfaceRealization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceRealizationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the InterfaceRealization element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InterfaceRealization.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForInterfaceRealizationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, interfaceRealization, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveInterfaceRealizationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInterfaceRealizationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InterfaceRealization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceRealizationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the InterfaceRealization element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InterfaceRealization.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForInterfaceRealizationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((InterfaceRealization)interfaceRealization).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, interfaceRealization, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditInterfaceRealizationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceRealizationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InterfaceRealization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceRealizationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the InterfaceRealization element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InterfaceRealization.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForInterfaceRealizationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, interfaceRealization, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveInterfaceRealizationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInterfaceRealizationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InterfaceRealization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceRealizationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the InterfaceRealization element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InterfaceRealization.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForInterfaceRealizationContract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interfaceMetaClass);
		referenceValueForContract = bot.changeReferenceValue(allInstancesOf, ((InterfaceRealization)interfaceRealization).getContract());
		cc.append(SetCommand.create(editingDomain, interfaceRealization, UMLPackage.eINSTANCE.getInterfaceRealization_Contract(), referenceValueForContract));
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
	public void testEditInterfaceRealizationContract() throws Exception {

		// Import the input model
		initializeInputModel();

		interfaceRealization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (interfaceRealization == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceRealizationContract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InterfaceRealization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceRealizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceRealizationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceRealizationMetaClass, firstInstanceOf, null);

		// Change value of the contract feature of the InterfaceRealization element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InterfaceRealization.Properties.contract, allInstancesOf.indexOf(referenceValueForContract));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
