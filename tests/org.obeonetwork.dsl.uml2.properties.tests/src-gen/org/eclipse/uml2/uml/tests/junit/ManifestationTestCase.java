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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Manifestation;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Manifestation
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ManifestationTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass manifestationMetaClass = UMLPackage.eINSTANCE.getManifestation();

	/**
	 * The type to edit
	 */
	private EObject manifestation;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class client
	 */
	private Object referenceValueForClient;

	/**
	 * The reference value for the reference class supplier
	 */
	private Object referenceValueForSupplier;

	/**
	 * The reference value for the reference class utilizedElement
	 */
	private Object referenceValueForUtilizedElement;

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
	private EClass packageableElementMetaClass = UMLPackage.eINSTANCE.getPackageableElement();

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
	protected void initializeExpectedModelForManifestationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject manifestation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, manifestation, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditManifestationName() throws Exception {

		// Import the input model
		initializeInputModel();

		manifestation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForManifestationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Manifestation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, manifestationMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Manifestation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Manifestation.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForManifestationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject manifestation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, manifestation, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditManifestationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		manifestation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Manifestation)manifestation).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForManifestationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Manifestation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, manifestationMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Manifestation element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Manifestation.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForManifestationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject manifestation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Manifestation)manifestation).getClientDependency());
		cc.append(AddCommand.create(editingDomain, manifestation, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditManifestationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		manifestation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForManifestationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Manifestation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, manifestationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Manifestation element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Manifestation.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForManifestationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject manifestation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)manifestation.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, manifestation, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveManifestationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		manifestation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForManifestationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Manifestation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, manifestationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Manifestation element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Manifestation.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForManifestationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject manifestation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Manifestation)manifestation).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, manifestation, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditManifestationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		manifestation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForManifestationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Manifestation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, manifestationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Manifestation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Manifestation.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForManifestationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject manifestation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, manifestation, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveManifestationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		manifestation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForManifestationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Manifestation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, manifestationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Manifestation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Manifestation.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForManifestationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject manifestation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Manifestation)manifestation).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, manifestation, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditManifestationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		manifestation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForManifestationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Manifestation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, manifestationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Manifestation element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Manifestation.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForManifestationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject manifestation = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, manifestation, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveManifestationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		manifestation = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (manifestation == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForManifestationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Manifestation element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), manifestationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(manifestationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, manifestationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Manifestation element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Manifestation.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
