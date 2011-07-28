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
import org.eclipse.uml2.uml.GeneralOrdering;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for GeneralOrdering
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class GeneralOrderingPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass generalOrderingMetaClass = UMLPackage.eINSTANCE.getGeneralOrdering();

	/**
	 * The type to edit
	 */
	private EObject generalOrdering;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class after
	 */
	private Object referenceValueForAfter;

	/**
	 * The reference value for the reference class before
	 */
	private Object referenceValueForBefore;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass occurrenceSpecificationMetaClass = UMLPackage.eINSTANCE.getOccurrenceSpecification();

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
	protected void initializeExpectedModelForGeneralOrderingName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, generalOrdering, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditGeneralOrderingName() throws Exception {

		// Import the input model
		initializeInputModel();

		generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralOrderingName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the GeneralOrdering element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the GeneralOrdering element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.GeneralOrdering.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForGeneralOrderingVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, generalOrdering, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditGeneralOrderingVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((GeneralOrdering)generalOrdering).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForGeneralOrderingVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the GeneralOrdering element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the GeneralOrdering element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.GeneralOrdering.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForGeneralOrderingClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((GeneralOrdering)generalOrdering).getClientDependency());
		cc.append(AddCommand.create(editingDomain, generalOrdering, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditGeneralOrderingClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralOrderingClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the GeneralOrdering element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the GeneralOrdering element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.GeneralOrdering.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForGeneralOrderingClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)generalOrdering.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, generalOrdering, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveGeneralOrderingClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForGeneralOrderingClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the GeneralOrdering element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the GeneralOrdering element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.GeneralOrdering.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForGeneralOrderingBefore() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, occurrenceSpecificationMetaClass);
		referenceValueForBefore = bot.changeReferenceValue(allInstancesOf, ((GeneralOrdering)generalOrdering).getBefore());
		cc.append(SetCommand.create(editingDomain, generalOrdering, UMLPackage.eINSTANCE.getGeneralOrdering_Before(), referenceValueForBefore));
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
	public void testEditGeneralOrderingBefore() throws Exception {

		// Import the input model
		initializeInputModel();

		generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralOrderingBefore();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the GeneralOrdering element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the before feature of the GeneralOrdering element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.GeneralOrdering.Properties.before, allInstancesOf.indexOf(referenceValueForBefore), bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForGeneralOrderingAfter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, occurrenceSpecificationMetaClass);
		referenceValueForAfter = bot.changeReferenceValue(allInstancesOf, ((GeneralOrdering)generalOrdering).getAfter());
		cc.append(SetCommand.create(editingDomain, generalOrdering, UMLPackage.eINSTANCE.getGeneralOrdering_After(), referenceValueForAfter));
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
	public void testEditGeneralOrderingAfter() throws Exception {

		// Import the input model
		initializeInputModel();

		generalOrdering = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (generalOrdering == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForGeneralOrderingAfter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the GeneralOrdering element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), generalOrderingMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(generalOrderingMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the after feature of the GeneralOrdering element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.GeneralOrdering.Properties.after, allInstancesOf.indexOf(referenceValueForAfter), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
