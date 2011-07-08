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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.OpaqueBehavior;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for OpaqueBehavior
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class OpaqueBehaviorTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass opaqueBehaviorMetaClass = UMLPackage.eINSTANCE.getOpaqueBehavior();

	/**
	 * The type to edit
	 */
	private EObject opaqueBehavior;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class redefinedBehavior
	 */
	private Object referenceValueForRedefinedBehavior;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class precondition
	 */
	private Object referenceValueForPrecondition;

	/**
	 * The reference value for the reference class specification
	 */
	private Object referenceValueForSpecification;

	/**
	 * The reference value for the reference class classifierBehavior
	 */
	private Object referenceValueForClassifierBehavior;

	/**
	 * The reference value for the reference class postcondition
	 */
	private Object referenceValueForPostcondition;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

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
	private EClass constraintMetaClass = UMLPackage.eINSTANCE.getConstraint();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass dependencyMetaClass = UMLPackage.eINSTANCE.getDependency();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass behaviorMetaClass = UMLPackage.eINSTANCE.getBehavior();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass behavioralFeatureMetaClass = UMLPackage.eINSTANCE.getBehavioralFeature();
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
	protected void initializeExpectedModelForOpaqueBehaviorName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditOpaqueBehaviorName() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the OpaqueBehavior element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOpaqueBehaviorVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditOpaqueBehaviorVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((OpaqueBehavior)opaqueBehavior).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the OpaqueBehavior element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOpaqueBehaviorClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((OpaqueBehavior)opaqueBehavior).getClientDependency());
		cc.append(AddCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditOpaqueBehaviorClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the OpaqueBehavior element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForOpaqueBehaviorClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)opaqueBehavior.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveOpaqueBehaviorClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueBehaviorClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the OpaqueBehavior element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOpaqueBehaviorIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditOpaqueBehaviorIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the OpaqueBehavior element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOpaqueBehaviorOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((OpaqueBehavior)opaqueBehavior).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditOpaqueBehaviorOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the OpaqueBehavior element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForOpaqueBehaviorOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveOpaqueBehaviorOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueBehaviorOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the OpaqueBehavior element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForOpaqueBehaviorTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((OpaqueBehavior)opaqueBehavior).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditOpaqueBehaviorTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the OpaqueBehavior element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForOpaqueBehaviorTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveOpaqueBehaviorTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueBehaviorTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the OpaqueBehavior element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForOpaqueBehaviorIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditOpaqueBehaviorIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the OpaqueBehavior element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOpaqueBehaviorPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((OpaqueBehavior)opaqueBehavior).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditOpaqueBehaviorPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the OpaqueBehavior element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForOpaqueBehaviorPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)opaqueBehavior.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveOpaqueBehaviorPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueBehaviorPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the OpaqueBehavior element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOpaqueBehaviorRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((OpaqueBehavior)opaqueBehavior).getRepresentation());
		cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditOpaqueBehaviorRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the OpaqueBehavior element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForOpaqueBehaviorRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveOpaqueBehaviorRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueBehaviorRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the OpaqueBehavior element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.representation);
		

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
	protected void initializeExpectedModelForOpaqueBehaviorUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((OpaqueBehavior)opaqueBehavior).getUseCase());
		cc.append(AddCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditOpaqueBehaviorUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the OpaqueBehavior element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForOpaqueBehaviorUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)opaqueBehavior.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveOpaqueBehaviorUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueBehaviorUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the OpaqueBehavior element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOpaqueBehaviorIsActive() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getClass_IsActive(), UPDATED_VALUE));
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
	public void testEditOpaqueBehaviorIsActive() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorIsActive();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the isActive feature of the OpaqueBehavior element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.isActive, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOpaqueBehaviorIsReentrant() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getBehavior_IsReentrant(), UPDATED_VALUE));
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
	public void testEditOpaqueBehaviorIsReentrant() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorIsReentrant();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the isReentrant feature of the OpaqueBehavior element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.isReentrant, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOpaqueBehaviorPrecondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPrecondition = bot.changeReferenceValue(allInstancesOf, ((OpaqueBehavior)opaqueBehavior).getPrecondition());
		cc.append(AddCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getBehavior_Precondition(), referenceValueForPrecondition));
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
	public void testEditOpaqueBehaviorPrecondition() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorPrecondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the precondition feature of the OpaqueBehavior element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.precondition, referenceValueForPrecondition);

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
	protected void initializeRemoveExpectedModelForOpaqueBehaviorPrecondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)opaqueBehavior.eGet(UMLPackage.eINSTANCE.getBehavior_Precondition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getBehavior_Precondition(), allReferencedInstances.get(0)));
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
	public void testRemoveOpaqueBehaviorPrecondition() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueBehaviorPrecondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the precondition feature of the OpaqueBehavior element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.precondition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOpaqueBehaviorPostcondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, constraintMetaClass);
		referenceValueForPostcondition = bot.changeReferenceValue(allInstancesOf, ((OpaqueBehavior)opaqueBehavior).getPostcondition());
		cc.append(AddCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getBehavior_Postcondition(), referenceValueForPostcondition));
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
	public void testEditOpaqueBehaviorPostcondition() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorPostcondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the postcondition feature of the OpaqueBehavior element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.postcondition, referenceValueForPostcondition);

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
	protected void initializeRemoveExpectedModelForOpaqueBehaviorPostcondition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)opaqueBehavior.eGet(UMLPackage.eINSTANCE.getBehavior_Postcondition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getBehavior_Postcondition(), allReferencedInstances.get(0)));
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
	public void testRemoveOpaqueBehaviorPostcondition() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueBehaviorPostcondition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the postcondition feature of the OpaqueBehavior element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.postcondition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForOpaqueBehaviorBody() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getOpaqueBehavior_Body(), UPDATED_VALUE));
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
	public void testEditOpaqueBehaviorBody() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorBody();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the body feature of the OpaqueBehavior element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.body, UPDATED_VALUE);

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
	protected void initializeExpectedModelForOpaqueBehaviorLanguage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueBehavior, UMLPackage.eINSTANCE.getOpaqueBehavior_Language(), UPDATED_VALUE));
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
	public void testEditOpaqueBehaviorLanguage() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueBehavior = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (opaqueBehavior == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueBehaviorLanguage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the OpaqueBehavior element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueBehaviorMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueBehaviorMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, opaqueBehaviorMetaClass, firstInstanceOf, null);

		// Change value of the language feature of the OpaqueBehavior element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.OpaqueBehavior.Properties.language, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


































}
