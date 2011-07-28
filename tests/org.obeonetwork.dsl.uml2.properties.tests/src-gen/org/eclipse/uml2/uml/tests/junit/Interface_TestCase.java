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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;
import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.exceptions.WidgetInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsResourceUtils;
import org.eclipse.emf.eef.runtime.tests.utils.UIConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
/**
 * TestCase for Interface
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class Interface_TestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass interfaceMetaClass = UMLPackage.eINSTANCE.getInterface();

	/**
	 * The type to edit
	 */
	private EObject interface;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

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
	 * The reference value for the reference class redefinedInterface
	 */
	private Object referenceValueForRedefinedInterface;

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
	protected void initializeExpectedModelForInterfaceName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditInterfaceName() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Interface element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Interface_.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInterfaceVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditInterfaceVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Interface)interface).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForInterfaceVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Interface element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Interface_.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInterfaceClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Interface)interface).getClientDependency());
		cc.append(AddCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditInterfaceClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Interface element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Interface_.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForInterfaceClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)interface.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveInterfaceClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInterfaceClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Interface element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Interface_.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInterfaceIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditInterfaceIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Interface element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Interface_.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInterfaceOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Interface)interface).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditInterfaceOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Interface element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Interface_.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForInterfaceOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveInterfaceOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInterfaceOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Interface element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Interface_.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForInterfaceTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Interface)interface).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditInterfaceTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Interface element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Interface_.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForInterfaceTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveInterfaceTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInterfaceTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Interface element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Interface_.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForInterfaceIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditInterfaceIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the Interface element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Interface_.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForInterfacePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((Interface)interface).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditInterfacePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfacePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Interface element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Interface_.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForInterfacePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)interface.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveInterfacePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInterfacePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Interface element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Interface_.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInterfaceRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((Interface)interface).getRepresentation());
		cc.append(SetCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditInterfaceRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Interface element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Interface_.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForInterfaceRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveInterfaceRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInterfaceRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Interface element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Interface_.Properties.representation);
		

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
	protected void initializeExpectedModelForInterfaceUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((Interface)interface).getUseCase());
		cc.append(AddCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditInterfaceUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Interface element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Interface_.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForInterfaceUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)interface.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveInterfaceUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInterfaceUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Interface element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Interface_.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForInterfaceRedefinedInterface() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interfaceMetaClass);
		referenceValueForRedefinedInterface = bot.changeReferenceValue(allInstancesOf, ((Interface)interface).getRedefinedInterface());
		cc.append(AddCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getInterface_RedefinedInterface(), referenceValueForRedefinedInterface));
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
	public void testEditInterfaceRedefinedInterface() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForInterfaceRedefinedInterface();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the redefinedInterface feature of the Interface element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Interface_.Properties.redefinedInterface, referenceValueForRedefinedInterface);

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
	protected void initializeRemoveExpectedModelForInterfaceRedefinedInterface() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject interface = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)interface.eGet(UMLPackage.eINSTANCE.getInterface_RedefinedInterface());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, interface, UMLPackage.eINSTANCE.getInterface_RedefinedInterface(), allReferencedInstances.get(0)));
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
	public void testRemoveInterfaceRedefinedInterface() throws Exception {

		// Import the input model
		initializeInputModel();

		interface = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (interface == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForInterfaceRedefinedInterface();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Interface element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), interfaceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(interfaceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, interfaceMetaClass, firstInstanceOf, null);

		// Change value of the redefinedInterface feature of the Interface element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Interface_.Properties.redefinedInterface, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
























}
