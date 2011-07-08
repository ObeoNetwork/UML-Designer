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
import org.eclipse.uml2.uml.DeploymentSpecification;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for DeploymentSpecification
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class DeploymentSpecificationPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass deploymentSpecificationMetaClass = UMLPackage.eINSTANCE.getDeploymentSpecification();

	/**
	 * The type to edit
	 */
	private EObject deploymentSpecification;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class deployment
	 */
	private Object referenceValueForDeployment;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;
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
	 * The EClass of the reference to edit
	 */
	private EClass deploymentMetaClass = UMLPackage.eINSTANCE.getDeployment();
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
	protected void initializeExpectedModelForDeploymentSpecificationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditDeploymentSpecificationName() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the DeploymentSpecification element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForDeploymentSpecificationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditDeploymentSpecificationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((DeploymentSpecification)deploymentSpecification).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the DeploymentSpecification element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForDeploymentSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((DeploymentSpecification)deploymentSpecification).getClientDependency());
		cc.append(AddCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditDeploymentSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DeploymentSpecification element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForDeploymentSpecificationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)deploymentSpecification.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveDeploymentSpecificationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeploymentSpecificationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the DeploymentSpecification element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForDeploymentSpecificationIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditDeploymentSpecificationIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the DeploymentSpecification element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForDeploymentSpecificationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((DeploymentSpecification)deploymentSpecification).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditDeploymentSpecificationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the DeploymentSpecification element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForDeploymentSpecificationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveDeploymentSpecificationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeploymentSpecificationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the DeploymentSpecification element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForDeploymentSpecificationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((DeploymentSpecification)deploymentSpecification).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditDeploymentSpecificationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the DeploymentSpecification element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForDeploymentSpecificationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveDeploymentSpecificationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeploymentSpecificationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the DeploymentSpecification element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForDeploymentSpecificationIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditDeploymentSpecificationIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isAbstract feature of the DeploymentSpecification element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.isAbstract, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForDeploymentSpecificationPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((DeploymentSpecification)deploymentSpecification).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditDeploymentSpecificationPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the DeploymentSpecification element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.powertypeExtent, referenceValueForPowertypeExtent, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForDeploymentSpecificationPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)deploymentSpecification.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveDeploymentSpecificationPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeploymentSpecificationPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the DeploymentSpecification element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForDeploymentSpecificationRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((DeploymentSpecification)deploymentSpecification).getRepresentation());
		cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditDeploymentSpecificationRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the representation feature of the DeploymentSpecification element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForDeploymentSpecificationRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveDeploymentSpecificationRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeploymentSpecificationRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the representation feature of the DeploymentSpecification element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.representation, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForDeploymentSpecificationUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((DeploymentSpecification)deploymentSpecification).getUseCase());
		cc.append(AddCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditDeploymentSpecificationUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the useCase feature of the DeploymentSpecification element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.useCase, referenceValueForUseCase, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForDeploymentSpecificationUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)deploymentSpecification.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveDeploymentSpecificationUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeploymentSpecificationUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the useCase feature of the DeploymentSpecification element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForDeploymentSpecificationFileName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getArtifact_FileName(), UPDATED_VALUE));
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
	public void testEditDeploymentSpecificationFileName() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationFileName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the fileName feature of the DeploymentSpecification element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.fileName, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForDeploymentSpecificationDeploymentLocation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getDeploymentSpecification_DeploymentLocation(), UPDATED_VALUE));
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
	public void testEditDeploymentSpecificationDeploymentLocation() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationDeploymentLocation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the deploymentLocation feature of the DeploymentSpecification element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.deploymentLocation, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForDeploymentSpecificationExecutionLocation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getDeploymentSpecification_ExecutionLocation(), UPDATED_VALUE));
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
	public void testEditDeploymentSpecificationExecutionLocation() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationExecutionLocation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the executionLocation feature of the DeploymentSpecification element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.executionLocation, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForDeploymentSpecificationDeployment() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, deploymentMetaClass);
		referenceValueForDeployment = bot.changeReferenceValue(allInstancesOf, ((DeploymentSpecification)deploymentSpecification).getDeployment());
		cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getDeploymentSpecification_Deployment(), referenceValueForDeployment));
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
	public void testEditDeploymentSpecificationDeployment() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeploymentSpecificationDeployment();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the deployment feature of the DeploymentSpecification element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.deployment, allInstancesOf.indexOf(referenceValueForDeployment)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForDeploymentSpecificationDeployment() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, deploymentMetaClass);
		cc.append(SetCommand.create(editingDomain, deploymentSpecification, UMLPackage.eINSTANCE.getDeploymentSpecification_Deployment(), null));
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
	public void testRemoveDeploymentSpecificationDeployment() throws Exception {

		// Import the input model
		initializeInputModel();

		deploymentSpecification = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (deploymentSpecification == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeploymentSpecificationDeployment();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the DeploymentSpecification element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deploymentSpecificationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deploymentSpecificationMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the deployment feature of the DeploymentSpecification element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.DeploymentSpecification.Properties.deployment, bot.selectNode(modelEditor, firstInstanceOf));
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






























}
