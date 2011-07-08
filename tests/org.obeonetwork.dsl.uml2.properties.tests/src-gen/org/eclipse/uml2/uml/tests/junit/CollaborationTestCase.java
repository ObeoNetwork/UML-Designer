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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Collaboration;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Collaboration
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class CollaborationTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass collaborationMetaClass = UMLPackage.eINSTANCE.getCollaboration();

	/**
	 * The type to edit
	 */
	private EObject collaboration;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class collaborationRole
	 */
	private Object referenceValueForCollaborationRole;

	/**
	 * The reference value for the reference class classifierBehavior
	 */
	private Object referenceValueForClassifierBehavior;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;
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
	private EClass behaviorMetaClass = UMLPackage.eINSTANCE.getBehavior();

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
	private EClass connectableElementMetaClass = UMLPackage.eINSTANCE.getConnectableElement();

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
	protected void initializeExpectedModelForCollaborationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditCollaborationName() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Collaboration element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCollaborationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditCollaborationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Collaboration)collaboration).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForCollaborationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Collaboration element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCollaborationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Collaboration)collaboration).getClientDependency());
		cc.append(AddCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditCollaborationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Collaboration element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForCollaborationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)collaboration.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveCollaborationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCollaborationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Collaboration element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCollaborationIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditCollaborationIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Collaboration element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCollaborationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Collaboration)collaboration).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditCollaborationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Collaboration element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForCollaborationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveCollaborationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCollaborationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Collaboration element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForCollaborationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Collaboration)collaboration).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditCollaborationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Collaboration element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForCollaborationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveCollaborationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCollaborationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Collaboration element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForCollaborationIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditCollaborationIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the Collaboration element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForCollaborationPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((Collaboration)collaboration).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditCollaborationPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Collaboration element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForCollaborationPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)collaboration.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveCollaborationPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCollaborationPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Collaboration element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForCollaborationRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((Collaboration)collaboration).getRepresentation());
		cc.append(SetCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditCollaborationRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Collaboration element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForCollaborationRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveCollaborationRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCollaborationRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Collaboration element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.representation);
		

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
	protected void initializeExpectedModelForCollaborationUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((Collaboration)collaboration).getUseCase());
		cc.append(AddCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditCollaborationUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForCollaborationUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Collaboration element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForCollaborationUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject collaboration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)collaboration.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, collaboration, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveCollaborationUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		collaboration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (collaboration == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForCollaborationUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Collaboration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), collaborationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(collaborationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, collaborationMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Collaboration element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Collaboration.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
