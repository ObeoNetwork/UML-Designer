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
import org.eclipse.uml2.uml.CommunicationPath;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for CommunicationPath
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class CommunicationPathPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass communicationPathMetaClass = UMLPackage.eINSTANCE.getCommunicationPath();

	/**
	 * The type to edit
	 */
	private EObject communicationPath;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class navigableOwnedEnd
	 */
	private Object referenceValueForNavigableOwnedEnd;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class memberEnd
	 */
	private Object referenceValueForMemberEnd;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass generalizationSetMetaClass = UMLPackage.eINSTANCE.getGeneralizationSet();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass useCaseMetaClass = UMLPackage.eINSTANCE.getUseCase();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass collaborationUseMetaClass = UMLPackage.eINSTANCE.getCollaborationUse();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass classifierMetaClass = UMLPackage.eINSTANCE.getClassifier();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass propertyMetaClass = UMLPackage.eINSTANCE.getProperty();

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
	protected void initializeExpectedModelForCommunicationPathName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditCommunicationPathName() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the CommunicationPath element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.CommunicationPath.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForCommunicationPathVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditCommunicationPathVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((CommunicationPath)communicationPath).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForCommunicationPathVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the CommunicationPath element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.CommunicationPath.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForCommunicationPathClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((CommunicationPath)communicationPath).getClientDependency());
		cc.append(AddCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditCommunicationPathClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CommunicationPath element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForCommunicationPathClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)communicationPath.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveCommunicationPathClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCommunicationPathClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the CommunicationPath element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForCommunicationPathIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditCommunicationPathIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the CommunicationPath element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.CommunicationPath.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForCommunicationPathOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((CommunicationPath)communicationPath).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditCommunicationPathOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the CommunicationPath element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForCommunicationPathOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveCommunicationPathOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCommunicationPathOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the CommunicationPath element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForCommunicationPathTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((CommunicationPath)communicationPath).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditCommunicationPathTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the CommunicationPath element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForCommunicationPathTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveCommunicationPathTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCommunicationPathTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the CommunicationPath element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForCommunicationPathIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditCommunicationPathIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isAbstract feature of the CommunicationPath element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.CommunicationPath.Properties.isAbstract, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForCommunicationPathPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((CommunicationPath)communicationPath).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditCommunicationPathPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the CommunicationPath element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.powertypeExtent, referenceValueForPowertypeExtent, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForCommunicationPathPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)communicationPath.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveCommunicationPathPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCommunicationPathPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the CommunicationPath element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForCommunicationPathRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((CommunicationPath)communicationPath).getRepresentation());
		cc.append(SetCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditCommunicationPathRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the representation feature of the CommunicationPath element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForCommunicationPathRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveCommunicationPathRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCommunicationPathRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the representation feature of the CommunicationPath element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.representation, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForCommunicationPathUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((CommunicationPath)communicationPath).getUseCase());
		cc.append(AddCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditCommunicationPathUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the useCase feature of the CommunicationPath element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.useCase, referenceValueForUseCase, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForCommunicationPathUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)communicationPath.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveCommunicationPathUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCommunicationPathUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the useCase feature of the CommunicationPath element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForCommunicationPathMemberEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForMemberEnd = bot.changeReferenceValue(allInstancesOf, ((CommunicationPath)communicationPath).getMemberEnd());
		cc.append(AddCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getAssociation_MemberEnd(), referenceValueForMemberEnd));
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
	public void testEditCommunicationPathMemberEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathMemberEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the memberEnd feature of the CommunicationPath element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.memberEnd, referenceValueForMemberEnd, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForCommunicationPathMemberEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)communicationPath.eGet(UMLPackage.eINSTANCE.getAssociation_MemberEnd());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getAssociation_MemberEnd(), allReferencedInstances.get(0)));
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
	public void testRemoveCommunicationPathMemberEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCommunicationPathMemberEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the memberEnd feature of the CommunicationPath element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.memberEnd, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForCommunicationPathIsDerived() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getAssociation_IsDerived(), UPDATED_VALUE));
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
	public void testEditCommunicationPathIsDerived() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathIsDerived();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isDerived feature of the CommunicationPath element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.CommunicationPath.Properties.isDerived, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForCommunicationPathNavigableOwnedEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForNavigableOwnedEnd = bot.changeReferenceValue(allInstancesOf, ((CommunicationPath)communicationPath).getNavigableOwnedEnd());
		cc.append(AddCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), referenceValueForNavigableOwnedEnd));
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
	public void testEditCommunicationPathNavigableOwnedEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCommunicationPathNavigableOwnedEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the navigableOwnedEnd feature of the CommunicationPath element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.navigableOwnedEnd, referenceValueForNavigableOwnedEnd, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForCommunicationPathNavigableOwnedEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)communicationPath.eGet(UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, communicationPath, UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), allReferencedInstances.get(0)));
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
	public void testRemoveCommunicationPathNavigableOwnedEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		communicationPath = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (communicationPath == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCommunicationPathNavigableOwnedEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the CommunicationPath element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), communicationPathMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(communicationPathMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the navigableOwnedEnd feature of the CommunicationPath element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.CommunicationPath.Properties.navigableOwnedEnd, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




























}
