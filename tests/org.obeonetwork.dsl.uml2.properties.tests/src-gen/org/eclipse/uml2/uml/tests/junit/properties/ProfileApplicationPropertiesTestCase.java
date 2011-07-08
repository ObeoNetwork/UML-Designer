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
package org.eclipse.uml2.uml.tests.junit.properties;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ProfileApplication;
/**
 * TestCase for ProfileApplication
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ProfileApplicationPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass profileApplicationMetaClass = UMLPackage.eINSTANCE.getProfileApplication();

	/**
	 * The type to edit
	 */
	private EObject profileApplication;

	/**
	 * The reference value for the reference class applyingPackage
	 */
	private Object referenceValueForApplyingPackage;

	/**
	 * The reference value for the reference class appliedProfile
	 */
	private Object referenceValueForAppliedProfile;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass profileMetaClass = UMLPackage.eINSTANCE.getProfile();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass packageMetaClass = UMLPackage.eINSTANCE.getPackage();
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
	protected void initializeExpectedModelForProfileApplicationAppliedProfile() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profileApplication = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileApplicationMetaClass);
		if (profileApplication == null)
			throw new InputModelInvalidException(profileApplicationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, profileMetaClass);
		referenceValueForAppliedProfile = bot.changeReferenceValue(allInstancesOf, ((ProfileApplication)profileApplication).getAppliedProfile());
		cc.append(SetCommand.create(editingDomain, profileApplication, UMLPackage.eINSTANCE.getProfileApplication_AppliedProfile(), referenceValueForAppliedProfile));
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
	public void testEditProfileApplicationAppliedProfile() throws Exception {

		// Import the input model
		initializeInputModel();

		profileApplication = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileApplicationMetaClass);
		if (profileApplication == null)
			throw new InputModelInvalidException(profileApplicationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProfileApplicationAppliedProfile();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ProfileApplication element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileApplicationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileApplicationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the appliedProfile feature of the ProfileApplication element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ProfileApplication.Properties.appliedProfile, allInstancesOf.indexOf(referenceValueForAppliedProfile), bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForProfileApplicationIsStrict() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profileApplication = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileApplicationMetaClass);
		if (profileApplication == null)
			throw new InputModelInvalidException(profileApplicationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, profileApplication, UMLPackage.eINSTANCE.getProfileApplication_IsStrict(), UPDATED_VALUE));
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
	public void testEditProfileApplicationIsStrict() throws Exception {

		// Import the input model
		initializeInputModel();

		profileApplication = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileApplicationMetaClass);
		if (profileApplication == null)
			throw new InputModelInvalidException(profileApplicationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProfileApplicationIsStrict();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ProfileApplication element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileApplicationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileApplicationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isStrict feature of the ProfileApplication element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ProfileApplication.Properties.isStrict, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForProfileApplicationApplyingPackage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject profileApplication = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, profileApplicationMetaClass);
		if (profileApplication == null)
			throw new InputModelInvalidException(profileApplicationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, packageMetaClass);
		referenceValueForApplyingPackage = bot.changeReferenceValue(allInstancesOf, ((ProfileApplication)profileApplication).getApplyingPackage());
		cc.append(SetCommand.create(editingDomain, profileApplication, UMLPackage.eINSTANCE.getProfileApplication_ApplyingPackage(), referenceValueForApplyingPackage));
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
	public void testEditProfileApplicationApplyingPackage() throws Exception {

		// Import the input model
		initializeInputModel();

		profileApplication = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileApplicationMetaClass);
		if (profileApplication == null)
			throw new InputModelInvalidException(profileApplicationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForProfileApplicationApplyingPackage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ProfileApplication element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), profileApplicationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(profileApplicationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the applyingPackage feature of the ProfileApplication element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ProfileApplication.Properties.applyingPackage, allInstancesOf.indexOf(referenceValueForApplyingPackage), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}








}
