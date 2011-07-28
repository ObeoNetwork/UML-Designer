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
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for InteractionConstraint
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class InteractionConstraintTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass interactionConstraintMetaClass = UMLPackage.eINSTANCE.getInteractionConstraint();

	/**
	 * The type to edit
	 */
	private EObject interactionConstraint;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class context
	 */
	private Object referenceValueForContext;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class constrainedElement
	 */
	private Object referenceValueForConstrainedElement;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass namespaceMetaClass = UMLPackage.eINSTANCE.getNamespace();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass elementMetaClass = UMLPackage.eINSTANCE.getElement();

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
	protected void initializeExpectedModelForInteractionConstraintName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interactionConstraint, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditInteractionConstraintName() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionConstraintName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionConstraintMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the InteractionConstraint element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InteractionConstraint.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInteractionConstraintVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interactionConstraint, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditInteractionConstraintVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((InteractionConstraint)interactionConstraint).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForInteractionConstraintVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionConstraintMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the InteractionConstraint element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.InteractionConstraint.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInteractionConstraintClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((InteractionConstraint)interactionConstraint).getClientDependency());
		cc.append(AddCommand.create(editingDomain, interactionConstraint, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditInteractionConstraintClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionConstraintClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionConstraintMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InteractionConstraint element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InteractionConstraint.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForInteractionConstraintClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)interactionConstraint.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, interactionConstraint, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveInteractionConstraintClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInteractionConstraintClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionConstraintMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the InteractionConstraint element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.InteractionConstraint.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInteractionConstraintOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((InteractionConstraint)interactionConstraint).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, interactionConstraint, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditInteractionConstraintOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionConstraintOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionConstraintMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the InteractionConstraint element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionConstraint.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForInteractionConstraintOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, interactionConstraint, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveInteractionConstraintOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInteractionConstraintOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionConstraintMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the InteractionConstraint element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionConstraint.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForInteractionConstraintTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((InteractionConstraint)interactionConstraint).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, interactionConstraint, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditInteractionConstraintTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInteractionConstraintTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionConstraintMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the InteractionConstraint element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionConstraint.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForInteractionConstraintTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, interactionConstraint, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveInteractionConstraintTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interactionConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (interactionConstraint == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInteractionConstraintTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the InteractionConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interactionConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interactionConstraintMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interactionConstraintMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the InteractionConstraint element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.InteractionConstraint.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
