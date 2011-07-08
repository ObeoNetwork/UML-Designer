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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.RedefinableTemplateSignature;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for RedefinableTemplateSignature
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class RedefinableTemplateSignatureTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass redefinableTemplateSignatureMetaClass = UMLPackage.eINSTANCE.getRedefinableTemplateSignature();

	/**
	 * The type to edit
	 */
	private EObject redefinableTemplateSignature;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class template
	 */
	private Object referenceValueForTemplate;

	/**
	 * The reference value for the reference class parameter
	 */
	private Object referenceValueForParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class classifier
	 */
	private Object referenceValueForClassifier;

	/**
	 * The reference value for the reference class extendedSignature
	 */
	private Object referenceValueForExtendedSignature;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass classifierMetaClass = UMLPackage.eINSTANCE.getClassifier();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateableElementMetaClass = UMLPackage.eINSTANCE.getTemplateableElement();

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
	protected void initializeExpectedModelForRedefinableTemplateSignatureName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, redefinableTemplateSignature, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditRedefinableTemplateSignatureName() throws Exception {

		// Import the input model
		initializeInputModel();

		redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRedefinableTemplateSignatureName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RedefinableTemplateSignature element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, redefinableTemplateSignatureMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the RedefinableTemplateSignature element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.RedefinableTemplateSignature.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRedefinableTemplateSignatureVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, redefinableTemplateSignature, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditRedefinableTemplateSignatureVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((RedefinableTemplateSignature)redefinableTemplateSignature).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForRedefinableTemplateSignatureVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RedefinableTemplateSignature element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, redefinableTemplateSignatureMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the RedefinableTemplateSignature element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRedefinableTemplateSignatureClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((RedefinableTemplateSignature)redefinableTemplateSignature).getClientDependency());
		cc.append(AddCommand.create(editingDomain, redefinableTemplateSignature, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditRedefinableTemplateSignatureClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRedefinableTemplateSignatureClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RedefinableTemplateSignature element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, redefinableTemplateSignatureMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the RedefinableTemplateSignature element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForRedefinableTemplateSignatureClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)redefinableTemplateSignature.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, redefinableTemplateSignature, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveRedefinableTemplateSignatureClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRedefinableTemplateSignatureClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RedefinableTemplateSignature element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, redefinableTemplateSignatureMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the RedefinableTemplateSignature element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForRedefinableTemplateSignatureIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, redefinableTemplateSignature, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditRedefinableTemplateSignatureIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRedefinableTemplateSignatureIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RedefinableTemplateSignature element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, redefinableTemplateSignatureMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the RedefinableTemplateSignature element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRedefinableTemplateSignatureParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForParameter = bot.changeReferenceValue(allInstancesOf, ((RedefinableTemplateSignature)redefinableTemplateSignature).getParameter());
		cc.append(AddCommand.create(editingDomain, redefinableTemplateSignature, UMLPackage.eINSTANCE.getTemplateSignature_Parameter(), referenceValueForParameter));
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
	public void testEditRedefinableTemplateSignatureParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRedefinableTemplateSignatureParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RedefinableTemplateSignature element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, redefinableTemplateSignatureMetaClass, firstInstanceOf, null);

		// Change value of the parameter feature of the RedefinableTemplateSignature element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter, referenceValueForParameter);

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
	protected void initializeRemoveExpectedModelForRedefinableTemplateSignatureParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)redefinableTemplateSignature.eGet(UMLPackage.eINSTANCE.getTemplateSignature_Parameter());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, redefinableTemplateSignature, UMLPackage.eINSTANCE.getTemplateSignature_Parameter(), allReferencedInstances.get(0)));
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
	public void testRemoveRedefinableTemplateSignatureParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRedefinableTemplateSignatureParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RedefinableTemplateSignature element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, redefinableTemplateSignatureMetaClass, firstInstanceOf, null);

		// Change value of the parameter feature of the RedefinableTemplateSignature element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForRedefinableTemplateSignatureExtendedSignature() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, redefinableTemplateSignatureMetaClass);
		referenceValueForExtendedSignature = bot.changeReferenceValue(allInstancesOf, ((RedefinableTemplateSignature)redefinableTemplateSignature).getExtendedSignature());
		cc.append(AddCommand.create(editingDomain, redefinableTemplateSignature, UMLPackage.eINSTANCE.getRedefinableTemplateSignature_ExtendedSignature(), referenceValueForExtendedSignature));
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
	public void testEditRedefinableTemplateSignatureExtendedSignature() throws Exception {

		// Import the input model
		initializeInputModel();

		redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRedefinableTemplateSignatureExtendedSignature();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RedefinableTemplateSignature element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, redefinableTemplateSignatureMetaClass, firstInstanceOf, null);

		// Change value of the extendedSignature feature of the RedefinableTemplateSignature element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature, referenceValueForExtendedSignature);

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
	protected void initializeRemoveExpectedModelForRedefinableTemplateSignatureExtendedSignature() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)redefinableTemplateSignature.eGet(UMLPackage.eINSTANCE.getRedefinableTemplateSignature_ExtendedSignature());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, redefinableTemplateSignature, UMLPackage.eINSTANCE.getRedefinableTemplateSignature_ExtendedSignature(), allReferencedInstances.get(0)));
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
	public void testRemoveRedefinableTemplateSignatureExtendedSignature() throws Exception {

		// Import the input model
		initializeInputModel();

		redefinableTemplateSignature = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (redefinableTemplateSignature == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRedefinableTemplateSignatureExtendedSignature();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the RedefinableTemplateSignature element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), redefinableTemplateSignatureMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(redefinableTemplateSignatureMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, redefinableTemplateSignatureMetaClass, firstInstanceOf, null);

		// Change value of the extendedSignature feature of the RedefinableTemplateSignature element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}














}
