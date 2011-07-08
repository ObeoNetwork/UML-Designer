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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Profile;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Profile
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ProfileTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass profileMetaClass = UMLPackage.eINSTANCE.getProfile();

	/**
	 * The type to edit
	 */
	private EObject profile;

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
	 * The reference value for the reference class metamodelReference
	 */
	private Object referenceValueForMetamodelReference;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class metaclassReference
	 */
	private Object referenceValueForMetaclassReference;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass packageImportMetaClass = UMLPackage.eINSTANCE.getPackageImport();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass elementImportMetaClass = UMLPackage.eINSTANCE.getElementImport();

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
	protected void initializeExpectedModelForProfileName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditProfileName() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProfileName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Profile element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Profile.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProfileVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditProfileVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Profile)profile).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForProfileVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Profile element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Profile.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForProfileClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Profile)profile).getClientDependency());
		cc.append(AddCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditProfileClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProfileClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Profile element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Profile.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForProfileClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)profile.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveProfileClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProfileClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Profile element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Profile.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForProfileOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Profile)profile).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditProfileOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProfileOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Profile element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Profile.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForProfileOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveProfileOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProfileOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Profile element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Profile.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForProfileTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Profile)profile).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditProfileTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProfileTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Profile element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Profile.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForProfileTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveProfileTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProfileTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Profile element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Profile.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForProfileMetaclassReference() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, elementImportMetaClass);
		referenceValueForMetaclassReference = bot.changeReferenceValue(allInstancesOf, ((Profile)profile).getMetaclassReference());
		cc.append(AddCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getProfile_MetaclassReference(), referenceValueForMetaclassReference));
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
	public void testEditProfileMetaclassReference() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProfileMetaclassReference();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the metaclassReference feature of the Profile element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Profile.Properties.metaclassReference, referenceValueForMetaclassReference);

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
	protected void initializeRemoveExpectedModelForProfileMetaclassReference() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)profile.eGet(UMLPackage.eINSTANCE.getProfile_MetaclassReference());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getProfile_MetaclassReference(), allReferencedInstances.get(0)));
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
	public void testRemoveProfileMetaclassReference() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProfileMetaclassReference();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the metaclassReference feature of the Profile element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Profile.Properties.metaclassReference, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForProfileMetamodelReference() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, packageImportMetaClass);
		referenceValueForMetamodelReference = bot.changeReferenceValue(allInstancesOf, ((Profile)profile).getMetamodelReference());
		cc.append(AddCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getProfile_MetamodelReference(), referenceValueForMetamodelReference));
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
	public void testEditProfileMetamodelReference() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProfileMetamodelReference();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the metamodelReference feature of the Profile element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Profile.Properties.metamodelReference, referenceValueForMetamodelReference);

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
	protected void initializeRemoveExpectedModelForProfileMetamodelReference() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profile = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)profile.eGet(UMLPackage.eINSTANCE.getProfile_MetamodelReference());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, profile, UMLPackage.eINSTANCE.getProfile_MetamodelReference(), allReferencedInstances.get(0)));
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
	public void testRemoveProfileMetamodelReference() throws Exception {

		// Import the input model
		initializeInputModel();

		profile = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (profile == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForProfileMetamodelReference();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Profile element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, profileMetaClass, firstInstanceOf, null);

		// Change value of the metamodelReference feature of the Profile element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Profile.Properties.metamodelReference, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
