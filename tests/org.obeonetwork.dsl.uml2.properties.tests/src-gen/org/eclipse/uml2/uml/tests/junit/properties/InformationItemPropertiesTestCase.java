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

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.InformationItem;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for InformationItem
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class InformationItemPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass informationItemMetaClass = UMLPackage.eINSTANCE.getInformationItem();

	/**
	 * The type to edit
	 */
	private EObject informationItem;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class represented
	 */
	private Object referenceValueForRepresented;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
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
	protected void initializeExpectedModelForInformationItemName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditInformationItemName() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationItemName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the InformationItem element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.InformationItem.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForInformationItemVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditInformationItemVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((InformationItem)informationItem).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForInformationItemVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the InformationItem element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.InformationItem.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForInformationItemClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((InformationItem)informationItem).getClientDependency());
		cc.append(AddCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditInformationItemClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationItemClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InformationItem element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInformationItemClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)informationItem.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveInformationItemClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInformationItemClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InformationItem element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForInformationItemIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditInformationItemIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationItemIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the InformationItem element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.InformationItem.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForInformationItemOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((InformationItem)informationItem).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditInformationItemOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationItemOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the InformationItem element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInformationItemOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveInformationItemOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInformationItemOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the InformationItem element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForInformationItemTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((InformationItem)informationItem).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditInformationItemTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationItemTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the InformationItem element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInformationItemTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveInformationItemTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInformationItemTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the InformationItem element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForInformationItemIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditInformationItemIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationItemIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isAbstract feature of the InformationItem element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.InformationItem.Properties.isAbstract, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForInformationItemPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((InformationItem)informationItem).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditInformationItemPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationItemPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the InformationItem element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.powertypeExtent, referenceValueForPowertypeExtent, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInformationItemPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)informationItem.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveInformationItemPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInformationItemPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the InformationItem element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForInformationItemRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((InformationItem)informationItem).getRepresentation());
		cc.append(SetCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditInformationItemRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationItemRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the representation feature of the InformationItem element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInformationItemRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveInformationItemRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInformationItemRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the representation feature of the InformationItem element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.representation, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForInformationItemUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((InformationItem)informationItem).getUseCase());
		cc.append(AddCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditInformationItemUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationItemUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the useCase feature of the InformationItem element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.useCase, referenceValueForUseCase, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInformationItemUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationItem = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)informationItem.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, informationItem, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveInformationItemUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		informationItem = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (informationItem == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInformationItemUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationItem element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationItemMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationItemMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the useCase feature of the InformationItem element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationItem.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
