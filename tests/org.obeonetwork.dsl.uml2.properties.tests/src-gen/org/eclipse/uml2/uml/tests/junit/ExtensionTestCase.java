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
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;

import sun.security.x509.Extension;
/**
 * TestCase for Extension
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ExtensionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass extensionMetaClass = UMLPackage.eINSTANCE.getExtension();

	/**
	 * The type to edit
	 */
	private EObject extension;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
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
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class navigableOwnedEnd
	 */
	private Object referenceValueForNavigableOwnedEnd;

	/**
	 * The reference value for the reference class memberEnd
	 */
	private Object referenceValueForMemberEnd;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

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
	private EClass propertyMetaClass = UMLPackage.eINSTANCE.getProperty();

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
	protected void initializeExpectedModelForExtensionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditExtensionName() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Extension element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Extension.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditExtensionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Extension)extension).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForExtensionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Extension element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Extension.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Extension)extension).getClientDependency());
		cc.append(AddCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditExtensionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Extension element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Extension.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForExtensionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)extension.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveExtensionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Extension element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Extension.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExtensionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditExtensionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Extension element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Extension.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Extension)extension).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditExtensionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Extension element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Extension.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForExtensionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveExtensionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Extension element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Extension.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForExtensionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Extension)extension).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditExtensionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Extension element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Extension.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForExtensionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveExtensionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Extension element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Extension.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForExtensionIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditExtensionIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the Extension element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Extension.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((Extension)extension).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditExtensionPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Extension element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Extension.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForExtensionPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)extension.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveExtensionPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Extension element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Extension.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExtensionRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((Extension)extension).getRepresentation());
		cc.append(SetCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditExtensionRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Extension element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Extension.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForExtensionRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveExtensionRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Extension element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Extension.Properties.representation);
		

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
	protected void initializeExpectedModelForExtensionUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((Extension)extension).getUseCase());
		cc.append(AddCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditExtensionUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Extension element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Extension.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForExtensionUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)extension.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveExtensionUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Extension element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Extension.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExtensionMemberEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForMemberEnd = bot.changeReferenceValue(allInstancesOf, ((Extension)extension).getMemberEnd());
		cc.append(AddCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getAssociation_MemberEnd(), referenceValueForMemberEnd));
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
	public void testEditExtensionMemberEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionMemberEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the memberEnd feature of the Extension element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Extension.Properties.memberEnd, referenceValueForMemberEnd);

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
	protected void initializeRemoveExpectedModelForExtensionMemberEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)extension.eGet(UMLPackage.eINSTANCE.getAssociation_MemberEnd());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getAssociation_MemberEnd(), allReferencedInstances.get(0)));
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
	public void testRemoveExtensionMemberEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionMemberEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the memberEnd feature of the Extension element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Extension.Properties.memberEnd, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForExtensionIsDerived() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getAssociation_IsDerived(), UPDATED_VALUE));
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
	public void testEditExtensionIsDerived() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionIsDerived();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the isDerived feature of the Extension element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Extension.Properties.isDerived, UPDATED_VALUE);

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
	protected void initializeExpectedModelForExtensionNavigableOwnedEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForNavigableOwnedEnd = bot.changeReferenceValue(allInstancesOf, ((Extension)extension).getNavigableOwnedEnd());
		cc.append(AddCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), referenceValueForNavigableOwnedEnd));
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
	public void testEditExtensionNavigableOwnedEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForExtensionNavigableOwnedEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the navigableOwnedEnd feature of the Extension element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Extension.Properties.navigableOwnedEnd, referenceValueForNavigableOwnedEnd);

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
	protected void initializeRemoveExpectedModelForExtensionNavigableOwnedEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject extension = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)extension.eGet(UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, extension, UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), allReferencedInstances.get(0)));
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
	public void testRemoveExtensionNavigableOwnedEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		extension = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (extension == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForExtensionNavigableOwnedEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Extension element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), extensionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(extensionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, extensionMetaClass, firstInstanceOf, null);

		// Change value of the navigableOwnedEnd feature of the Extension element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Extension.Properties.navigableOwnedEnd, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




























}
