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
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Enumeration
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class EnumerationTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass enumerationMetaClass = UMLPackage.eINSTANCE.getEnumeration();

	/**
	 * The type to edit
	 */
	private EObject enumeration;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;
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
	protected void initializeExpectedModelForEnumerationName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditEnumerationName() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Enumeration element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForEnumerationVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditEnumerationVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Enumeration)enumeration).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForEnumerationVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Enumeration element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForEnumerationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Enumeration)enumeration).getClientDependency());
		cc.append(AddCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditEnumerationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Enumeration element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForEnumerationClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)enumeration.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveEnumerationClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForEnumerationClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Enumeration element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForEnumerationIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditEnumerationIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Enumeration element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForEnumerationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Enumeration)enumeration).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditEnumerationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Enumeration element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForEnumerationOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveEnumerationOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForEnumerationOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Enumeration element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForEnumerationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Enumeration)enumeration).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditEnumerationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Enumeration element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForEnumerationTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveEnumerationTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForEnumerationTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Enumeration element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForEnumerationIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditEnumerationIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the Enumeration element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForEnumerationPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((Enumeration)enumeration).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditEnumerationPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Enumeration element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForEnumerationPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)enumeration.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveEnumerationPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForEnumerationPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Enumeration element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForEnumerationRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((Enumeration)enumeration).getRepresentation());
		cc.append(SetCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditEnumerationRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Enumeration element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForEnumerationRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveEnumerationRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForEnumerationRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Enumeration element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.representation);
		

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
	protected void initializeExpectedModelForEnumerationUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((Enumeration)enumeration).getUseCase());
		cc.append(AddCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditEnumerationUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForEnumerationUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Enumeration element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForEnumerationUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject enumeration = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)enumeration.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, enumeration, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveEnumerationUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		enumeration = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (enumeration == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForEnumerationUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Enumeration element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), enumerationMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(enumerationMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, enumerationMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Enumeration element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Enumeration.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
