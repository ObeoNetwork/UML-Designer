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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Artifact;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Artifact
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ArtifactTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass artifactMetaClass = UMLPackage.eINSTANCE.getArtifact();

	/**
	 * The type to edit
	 */
	private EObject artifact;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

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
	protected void initializeExpectedModelForArtifactName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditArtifactName() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForArtifactName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Artifact element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Artifact.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForArtifactVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditArtifactVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Artifact)artifact).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForArtifactVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Artifact element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Artifact.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForArtifactClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Artifact)artifact).getClientDependency());
		cc.append(AddCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditArtifactClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForArtifactClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Artifact element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Artifact.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForArtifactClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)artifact.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveArtifactClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForArtifactClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Artifact element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Artifact.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForArtifactIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditArtifactIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForArtifactIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Artifact element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Artifact.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForArtifactOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Artifact)artifact).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditArtifactOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForArtifactOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Artifact element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Artifact.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForArtifactOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveArtifactOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForArtifactOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Artifact element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Artifact.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForArtifactTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Artifact)artifact).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditArtifactTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForArtifactTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Artifact element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Artifact.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForArtifactTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveArtifactTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForArtifactTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Artifact element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Artifact.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForArtifactIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditArtifactIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForArtifactIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the Artifact element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Artifact.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForArtifactPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((Artifact)artifact).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditArtifactPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForArtifactPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Artifact element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Artifact.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForArtifactPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)artifact.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveArtifactPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForArtifactPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Artifact element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Artifact.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForArtifactRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((Artifact)artifact).getRepresentation());
		cc.append(SetCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditArtifactRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForArtifactRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Artifact element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Artifact.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForArtifactRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveArtifactRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForArtifactRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Artifact element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Artifact.Properties.representation);
		

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
	protected void initializeExpectedModelForArtifactUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((Artifact)artifact).getUseCase());
		cc.append(AddCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditArtifactUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForArtifactUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Artifact element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Artifact.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForArtifactUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)artifact.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveArtifactUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForArtifactUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Artifact element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Artifact.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForArtifactFileName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject artifact = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, artifact, UMLPackage.eINSTANCE.getArtifact_FileName(), UPDATED_VALUE));
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
	public void testEditArtifactFileName() throws Exception {

		// Import the input model
		initializeInputModel();

		artifact = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (artifact == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForArtifactFileName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Artifact element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), artifactMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(artifactMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, artifactMetaClass, firstInstanceOf, null);

		// Change value of the fileName feature of the Artifact element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Artifact.Properties.fileName, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
























}
