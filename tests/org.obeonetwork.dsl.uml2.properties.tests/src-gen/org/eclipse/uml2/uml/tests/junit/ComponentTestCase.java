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
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Component
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ComponentTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass componentMetaClass = UMLPackage.eINSTANCE.getComponent();

	/**
	 * The type to edit
	 */
	private EObject component;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

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
	protected void initializeExpectedModelForComponentName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditComponentName() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForComponentName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Component element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Component.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForComponentVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditComponentVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Component)component).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForComponentVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Component element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Component.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForComponentClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Component)component).getClientDependency());
		cc.append(AddCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditComponentClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForComponentClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Component element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Component.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForComponentClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)component.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveComponentClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForComponentClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Component element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Component.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForComponentIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditComponentIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForComponentIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Component element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Component.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForComponentOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Component)component).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditComponentOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForComponentOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Component element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Component.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForComponentOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveComponentOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForComponentOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Component element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Component.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForComponentTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Component)component).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditComponentTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForComponentTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Component element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Component.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForComponentTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveComponentTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForComponentTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Component element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Component.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForComponentIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditComponentIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForComponentIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the Component element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Component.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForComponentPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((Component)component).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditComponentPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForComponentPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Component element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Component.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForComponentPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)component.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveComponentPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForComponentPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Component element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Component.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForComponentRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((Component)component).getRepresentation());
		cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditComponentRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForComponentRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Component element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Component.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForComponentRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveComponentRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForComponentRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Component element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Component.Properties.representation);
		

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
	protected void initializeExpectedModelForComponentUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((Component)component).getUseCase());
		cc.append(AddCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditComponentUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForComponentUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Component element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Component.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForComponentUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)component.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveComponentUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForComponentUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Component element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Component.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForComponentIsActive() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getClass_IsActive(), UPDATED_VALUE));
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
	public void testEditComponentIsActive() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForComponentIsActive();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the isActive feature of the Component element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Component.Properties.isActive, UPDATED_VALUE);

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
	protected void initializeExpectedModelForComponentIsIndirectlyInstantiated() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject component = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, component, UMLPackage.eINSTANCE.getComponent_IsIndirectlyInstantiated(), UPDATED_VALUE));
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
	public void testEditComponentIsIndirectlyInstantiated() throws Exception {

		// Import the input model
		initializeInputModel();

		component = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (component == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForComponentIsIndirectlyInstantiated();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Component element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), componentMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(componentMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, componentMetaClass, firstInstanceOf, null);

		// Change value of the isIndirectlyInstantiated feature of the Component element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Component.Properties.isIndirectlyInstantiated, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}


























}
