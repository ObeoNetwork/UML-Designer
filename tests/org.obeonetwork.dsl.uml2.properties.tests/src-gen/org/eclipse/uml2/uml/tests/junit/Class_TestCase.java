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
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.Behavior;
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
 * TestCase for Class
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class Class_TestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass classMetaClass = UMLPackage.eINSTANCE.getClass();

	/**
	 * The type to edit
	 */
	private EObject class;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class classifierBehavior
	 */
	private Object referenceValueForClassifierBehavior;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;
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
	protected void initializeExpectedModelForClassName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditClassName() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClassName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Class element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Class_.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForClassVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditClassVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Class)class).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForClassVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Class element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Class_.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForClassClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Class)class).getClientDependency());
		cc.append(AddCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditClassClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClassClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Class element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Class_.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForClassClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)class.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveClassClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClassClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Class element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Class_.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForClassIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditClassIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClassIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Class element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Class_.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForClassOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Class)class).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditClassOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClassOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Class element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Class_.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForClassOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveClassOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClassOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Class element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Class_.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForClassTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Class)class).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditClassTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClassTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Class element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Class_.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForClassTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveClassTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClassTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Class element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Class_.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForClassIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditClassIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClassIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the Class element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Class_.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForClassPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((Class)class).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditClassPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClassPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Class element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Class_.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForClassPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)class.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveClassPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClassPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Class element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Class_.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForClassRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((Class)class).getRepresentation());
		cc.append(SetCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditClassRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClassRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Class element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Class_.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForClassRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveClassRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClassRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Class element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Class_.Properties.representation);
		

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
	protected void initializeExpectedModelForClassUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((Class)class).getUseCase());
		cc.append(AddCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditClassUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClassUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Class element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Class_.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForClassUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)class.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveClassUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForClassUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Class element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Class_.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForClassIsActive() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject class = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, class, UMLPackage.eINSTANCE.getClass_IsActive(), UPDATED_VALUE));
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
	public void testEditClassIsActive() throws Exception {

		// Import the input model
		initializeInputModel();

		class = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (class == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForClassIsActive();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Class element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), classMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(classMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, classMetaClass, firstInstanceOf, null);

		// Change value of the isActive feature of the Class element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Class_.Properties.isActive, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
























}
