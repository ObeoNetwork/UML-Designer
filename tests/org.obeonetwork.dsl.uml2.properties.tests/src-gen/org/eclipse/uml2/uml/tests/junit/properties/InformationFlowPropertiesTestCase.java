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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.InformationFlow;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for InformationFlow
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class InformationFlowPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass informationFlowMetaClass = UMLPackage.eINSTANCE.getInformationFlow();

	/**
	 * The type to edit
	 */
	private EObject informationFlow;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class informationTarget
	 */
	private Object referenceValueForInformationTarget;

	/**
	 * The reference value for the reference class realizingActivityEdge
	 */
	private Object referenceValueForRealizingActivityEdge;

	/**
	 * The reference value for the reference class realizingMessage
	 */
	private Object referenceValueForRealizingMessage;

	/**
	 * The reference value for the reference class realizingConnector
	 */
	private Object referenceValueForRealizingConnector;

	/**
	 * The reference value for the reference class realization
	 */
	private Object referenceValueForRealization;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class conveyed
	 */
	private Object referenceValueForConveyed;

	/**
	 * The reference value for the reference class informationSource
	 */
	private Object referenceValueForInformationSource;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass namedElementMetaClass = UMLPackage.eINSTANCE.getNamedElement();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass messageMetaClass = UMLPackage.eINSTANCE.getMessage();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass connectorMetaClass = UMLPackage.eINSTANCE.getConnector();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass classifierMetaClass = UMLPackage.eINSTANCE.getClassifier();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass relationshipMetaClass = UMLPackage.eINSTANCE.getRelationship();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityEdgeMetaClass = UMLPackage.eINSTANCE.getActivityEdge();

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
	protected void initializeExpectedModelForInformationFlowName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditInformationFlowName() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationFlowName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the InformationFlow element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.InformationFlow.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForInformationFlowVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditInformationFlowVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((InformationFlow)informationFlow).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForInformationFlowVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the InformationFlow element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.InformationFlow.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForInformationFlowClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((InformationFlow)informationFlow).getClientDependency());
		cc.append(AddCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditInformationFlowClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationFlowClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InformationFlow element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationFlow.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInformationFlowClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)informationFlow.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveInformationFlowClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInformationFlowClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InformationFlow element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationFlow.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForInformationFlowOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((InformationFlow)informationFlow).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditInformationFlowOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationFlowOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the InformationFlow element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInformationFlowOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveInformationFlowOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInformationFlowOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the InformationFlow element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForInformationFlowTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((InformationFlow)informationFlow).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditInformationFlowTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationFlowTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the InformationFlow element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InformationFlow.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInformationFlowTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveInformationFlowTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInformationFlowTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the InformationFlow element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.InformationFlow.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForInformationFlowRealizingConnector() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, connectorMetaClass);
		referenceValueForRealizingConnector = bot.changeReferenceValue(allInstancesOf, ((InformationFlow)informationFlow).getRealizingConnector());
		cc.append(AddCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getInformationFlow_RealizingConnector(), referenceValueForRealizingConnector));
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
	public void testEditInformationFlowRealizingConnector() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationFlowRealizingConnector();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the realizingConnector feature of the InformationFlow element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationFlow.Properties.realizingConnector, referenceValueForRealizingConnector, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInformationFlowRealizingConnector() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)informationFlow.eGet(UMLPackage.eINSTANCE.getInformationFlow_RealizingConnector());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getInformationFlow_RealizingConnector(), allReferencedInstances.get(0)));
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
	public void testRemoveInformationFlowRealizingConnector() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInformationFlowRealizingConnector();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the realizingConnector feature of the InformationFlow element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationFlow.Properties.realizingConnector, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForInformationFlowRealizingMessage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, messageMetaClass);
		referenceValueForRealizingMessage = bot.changeReferenceValue(allInstancesOf, ((InformationFlow)informationFlow).getRealizingMessage());
		cc.append(AddCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getInformationFlow_RealizingMessage(), referenceValueForRealizingMessage));
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
	public void testEditInformationFlowRealizingMessage() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInformationFlowRealizingMessage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the realizingMessage feature of the InformationFlow element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationFlow.Properties.realizingMessage, referenceValueForRealizingMessage, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForInformationFlowRealizingMessage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)informationFlow.eGet(UMLPackage.eINSTANCE.getInformationFlow_RealizingMessage());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, informationFlow, UMLPackage.eINSTANCE.getInformationFlow_RealizingMessage(), allReferencedInstances.get(0)));
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
	public void testRemoveInformationFlowRealizingMessage() throws Exception {

		// Import the input model
		initializeInputModel();

		informationFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (informationFlow == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInformationFlowRealizingMessage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the InformationFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), informationFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(informationFlowMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the realizingMessage feature of the InformationFlow element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.InformationFlow.Properties.realizingMessage, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
