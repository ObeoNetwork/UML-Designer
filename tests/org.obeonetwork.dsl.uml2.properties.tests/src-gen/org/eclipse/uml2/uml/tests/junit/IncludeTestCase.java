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
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Include
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class IncludeTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass includeMetaClass = UMLPackage.eINSTANCE.getInclude();

	/**
	 * The type to edit
	 */
	private EObject include;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class includingCase
	 */
	private Object referenceValueForIncludingCase;

	/**
	 * The reference value for the reference class addition
	 */
	private Object referenceValueForAddition;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass useCaseMetaClass = UMLPackage.eINSTANCE.getUseCase();

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
	protected void initializeExpectedModelForIncludeName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject include = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, include, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditIncludeName() throws Exception {

		// Import the input model
		initializeInputModel();

		include = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIncludeName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Include element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, includeMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Include element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Include.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForIncludeVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject include = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, include, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditIncludeVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		include = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Include)include).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForIncludeVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Include element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, includeMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Include element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Include.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForIncludeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject include = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Include)include).getClientDependency());
		cc.append(AddCommand.create(editingDomain, include, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditIncludeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		include = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIncludeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Include element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, includeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Include element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Include.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForIncludeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject include = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)include.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, include, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveIncludeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		include = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForIncludeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Include element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, includeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Include element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Include.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForIncludeAddition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject include = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForAddition = bot.changeReferenceValue(allInstancesOf, ((Include)include).getAddition());
		cc.append(SetCommand.create(editingDomain, include, UMLPackage.eINSTANCE.getInclude_Addition(), referenceValueForAddition));
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
	public void testEditIncludeAddition() throws Exception {

		// Import the input model
		initializeInputModel();

		include = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIncludeAddition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Include element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, includeMetaClass, firstInstanceOf, null);

		// Change value of the addition feature of the Include element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Include.Properties.addition, allInstancesOf.indexOf(referenceValueForAddition));

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
	protected void initializeExpectedModelForIncludeIncludingCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject include = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForIncludingCase = bot.changeReferenceValue(allInstancesOf, ((Include)include).getIncludingCase());
		cc.append(SetCommand.create(editingDomain, include, UMLPackage.eINSTANCE.getInclude_IncludingCase(), referenceValueForIncludingCase));
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
	public void testEditIncludeIncludingCase() throws Exception {

		// Import the input model
		initializeInputModel();

		include = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (include == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIncludeIncludingCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Include element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), includeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(includeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, includeMetaClass, firstInstanceOf, null);

		// Change value of the includingCase feature of the Include element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Include.Properties.includingCase, allInstancesOf.indexOf(referenceValueForIncludingCase));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
