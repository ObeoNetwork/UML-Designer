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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ExtensionPoint;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ExtensionPoint
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ExtensionPointTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass extensionPointMetaClass = UMLPackage.eINSTANCE.getExtensionPoint();

	/**
	 * The type to edit
	 */
	private EObject extensionPoint;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
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
	protected void initializeExpectedModelForExtensionPointName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionPoint, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditExtensionPointName() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionPointName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionPoint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionPointMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the ExtensionPoint element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionPoint.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionPointVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionPoint, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditExtensionPointVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ExtensionPoint)extensionPoint).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForExtensionPointVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionPoint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionPointMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ExtensionPoint element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionPoint.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionPointClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ExtensionPoint)extensionPoint).getClientDependency());
		cc.append(AddCommand.create(editingDomain, extensionPoint, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditExtensionPointClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionPointClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionPoint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionPointMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExtensionPoint element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExtensionPoint.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForExtensionPointClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)extensionPoint.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, extensionPoint, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveExtensionPointClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionPointClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionPoint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionPointMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ExtensionPoint element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.ExtensionPoint.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExtensionPointIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extensionPoint, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditExtensionPointIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionPointIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionPoint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionPointMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ExtensionPoint element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ExtensionPoint.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionPointUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((ExtensionPoint)extensionPoint).getUseCase());
		cc.append(SetCommand.create(editingDomain, extensionPoint, UMLPackage.eINSTANCE.getExtensionPoint_UseCase(), referenceValueForUseCase));
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
	public void testEditExtensionPointUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		extensionPoint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (extensionPoint == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionPointUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ExtensionPoint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionPointMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionPointMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionPointMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the ExtensionPoint element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.ExtensionPoint.Properties.useCase, allInstancesOf.indexOf(referenceValueForUseCase));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
