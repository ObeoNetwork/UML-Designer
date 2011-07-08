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
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Generalization
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class GeneralizationPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass generalizationMetaClass = UMLPackage.eINSTANCE.getGeneralization();

	/**
	 * The type to edit
	 */
	private EObject generalization;

	/**
	 * The reference value for the reference class general
	 */
	private Object referenceValueForGeneral;

	/**
	 * The reference value for the reference class specific
	 */
	private Object referenceValueForSpecific;

	/**
	 * The reference value for the reference class generalizationSet
	 */
	private Object referenceValueForGeneralizationSet;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass generalizationSetMetaClass = UMLPackage.eINSTANCE.getGeneralizationSet();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass classifierMetaClass = UMLPackage.eINSTANCE.getClassifier();
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
	protected void initializeExpectedModelForGeneralizationIsSubstitutable() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationMetaClass);
		if (generalization == null)
			throw new InputModelInvalidException(generalizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, generalization, UMLPackage.eINSTANCE.getGeneralization_IsSubstitutable(), UPDATED_VALUE));
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
	public void testEditGeneralizationIsSubstitutable() throws Exception {

		// Import the input model
		initializeInputModel();

		generalization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationMetaClass);
		if (generalization == null)
			throw new InputModelInvalidException(generalizationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralizationIsSubstitutable();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Generalization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isSubstitutable feature of the Generalization element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.Generalization.Properties.isSubstitutable, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForGeneralizationGeneralizationSet() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationMetaClass);
		if (generalization == null)
			throw new InputModelInvalidException(generalizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForGeneralizationSet = bot.changeReferenceValue(allInstancesOf, ((Generalization)generalization).getGeneralizationSet());
		cc.append(AddCommand.create(editingDomain, generalization, UMLPackage.eINSTANCE.getGeneralization_GeneralizationSet(), referenceValueForGeneralizationSet));
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
	public void testEditGeneralizationGeneralizationSet() throws Exception {

		// Import the input model
		initializeInputModel();

		generalization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationMetaClass);
		if (generalization == null)
			throw new InputModelInvalidException(generalizationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralizationGeneralizationSet();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Generalization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the generalizationSet feature of the Generalization element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.Generalization.Properties.generalizationSet, referenceValueForGeneralizationSet, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForGeneralizationGeneralizationSet() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalization = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalizationMetaClass);
		if (generalization == null)
			throw new InputModelInvalidException(generalizationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)generalization.eGet(UMLPackage.eINSTANCE.getGeneralization_GeneralizationSet());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, generalization, UMLPackage.eINSTANCE.getGeneralization_GeneralizationSet(), allReferencedInstances.get(0)));
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
	public void testRemoveGeneralizationGeneralizationSet() throws Exception {

		// Import the input model
		initializeInputModel();

		generalization = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationMetaClass);
		if (generalization == null)
			throw new InputModelInvalidException(generalizationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForGeneralizationGeneralizationSet();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the Generalization element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalizationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalizationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the generalizationSet feature of the Generalization element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.Generalization.Properties.generalizationSet, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






}
