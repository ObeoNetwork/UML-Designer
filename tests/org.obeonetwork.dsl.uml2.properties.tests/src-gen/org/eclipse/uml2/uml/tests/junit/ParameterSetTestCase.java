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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ParameterSet;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ParameterSet
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ParameterSetTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass parameterSetMetaClass = UMLPackage.eINSTANCE.getParameterSet();

	/**
	 * The type to edit
	 */
	private EObject parameterSet;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class parameter
	 */
	private Object referenceValueForParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass parameterMetaClass = UMLPackage.eINSTANCE.getParameter();

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
	protected void initializeExpectedModelForParameterSetName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, parameterSet, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditParameterSetName() throws Exception {

		// Import the input model
		initializeInputModel();

		parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterSetName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ParameterSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, parameterSetMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ParameterSet element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ParameterSet.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForParameterSetVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, parameterSet, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditParameterSetVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ParameterSet)parameterSet).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForParameterSetVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ParameterSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, parameterSetMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ParameterSet element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ParameterSet.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForParameterSetClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ParameterSet)parameterSet).getClientDependency());
		cc.append(AddCommand.create(editingDomain, parameterSet, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditParameterSetClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterSetClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ParameterSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, parameterSetMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ParameterSet element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ParameterSet.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForParameterSetClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)parameterSet.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, parameterSet, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveParameterSetClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForParameterSetClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ParameterSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, parameterSetMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ParameterSet element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ParameterSet.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForParameterSetParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, parameterMetaClass);
		referenceValueForParameter = bot.changeReferenceValue(allInstancesOf, ((ParameterSet)parameterSet).getParameter());
		cc.append(AddCommand.create(editingDomain, parameterSet, UMLPackage.eINSTANCE.getParameterSet_Parameter(), referenceValueForParameter));
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
	public void testEditParameterSetParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForParameterSetParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ParameterSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, parameterSetMetaClass, firstInstanceOf, null);

		// Change value of the parameter feature of the ParameterSet element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ParameterSet.Properties.parameter, referenceValueForParameter);

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
	protected void initializeRemoveExpectedModelForParameterSetParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)parameterSet.eGet(UMLPackage.eINSTANCE.getParameterSet_Parameter());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, parameterSet, UMLPackage.eINSTANCE.getParameterSet_Parameter(), allReferencedInstances.get(0)));
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
	public void testRemoveParameterSetParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		parameterSet = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (parameterSet == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForParameterSetParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ParameterSet element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), parameterSetMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(parameterSetMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, parameterSetMetaClass, firstInstanceOf, null);

		// Change value of the parameter feature of the ParameterSet element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ParameterSet.Properties.parameter, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}










}
