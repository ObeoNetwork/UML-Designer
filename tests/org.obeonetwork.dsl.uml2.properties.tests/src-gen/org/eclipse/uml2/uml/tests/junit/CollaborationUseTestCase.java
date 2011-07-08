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
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for CollaborationUse
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class CollaborationUseTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass collaborationUseMetaClass = UMLPackage.eINSTANCE.getCollaborationUse();

	/**
	 * The type to edit
	 */
	private EObject collaborationUse;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class type
	 */
	private Object referenceValueForType;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass collaborationMetaClass = UMLPackage.eINSTANCE.getCollaboration();

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
	protected void initializeExpectedModelForCollaborationUseName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaborationUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationUseMetaClass);
		if (collaborationUse == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, collaborationUse, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditCollaborationUseName() throws Exception {

		// Import the input model
		initializeInputModel();

		collaborationUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationUseMetaClass);
		if (collaborationUse == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationUseName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CollaborationUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationUseMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the CollaborationUse element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CollaborationUse.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCollaborationUseVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaborationUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationUseMetaClass);
		if (collaborationUse == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, collaborationUse, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditCollaborationUseVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		collaborationUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationUseMetaClass);
		if (collaborationUse == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((CollaborationUse)collaborationUse).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForCollaborationUseVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CollaborationUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationUseMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the CollaborationUse element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.CollaborationUse.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCollaborationUseClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaborationUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationUseMetaClass);
		if (collaborationUse == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((CollaborationUse)collaborationUse).getClientDependency());
		cc.append(AddCommand.create(editingDomain, collaborationUse, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditCollaborationUseClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		collaborationUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationUseMetaClass);
		if (collaborationUse == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationUseClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CollaborationUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationUseMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CollaborationUse element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CollaborationUse.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForCollaborationUseClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaborationUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationUseMetaClass);
		if (collaborationUse == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)collaborationUse.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, collaborationUse, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveCollaborationUseClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		collaborationUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationUseMetaClass);
		if (collaborationUse == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCollaborationUseClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CollaborationUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationUseMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CollaborationUse element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.CollaborationUse.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCollaborationUseType() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaborationUse = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationUseMetaClass);
		if (collaborationUse == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationMetaClass);
		referenceValueForType = bot.changeReferenceValue(allInstancesOf, ((CollaborationUse)collaborationUse).getType());
		cc.append(SetCommand.create(editingDomain, collaborationUse, UMLPackage.eINSTANCE.getCollaborationUse_Type(), referenceValueForType));
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
	public void testEditCollaborationUseType() throws Exception {

		// Import the input model
		initializeInputModel();

		collaborationUse = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationUseMetaClass);
		if (collaborationUse == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationUseType();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the CollaborationUse element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationUseMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationUseMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationUseMetaClass, firstInstanceOf, null);

		// Change value of the type feature of the CollaborationUse element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.CollaborationUse.Properties.type, allInstancesOf.indexOf(referenceValueForType));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}










}
