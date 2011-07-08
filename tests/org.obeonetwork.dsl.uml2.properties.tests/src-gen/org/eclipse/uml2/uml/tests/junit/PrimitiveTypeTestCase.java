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
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for PrimitiveType
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class PrimitiveTypeTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass primitiveTypeMetaClass = UMLPackage.eINSTANCE.getPrimitiveType();

	/**
	 * The type to edit
	 */
	private EObject primitiveType;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
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
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

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
	protected void initializeExpectedModelForPrimitiveTypeName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditPrimitiveTypeName() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPrimitiveTypeName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the PrimitiveType element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPrimitiveTypeVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditPrimitiveTypeVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((PrimitiveType)primitiveType).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForPrimitiveTypeVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the PrimitiveType element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPrimitiveTypeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((PrimitiveType)primitiveType).getClientDependency());
		cc.append(AddCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditPrimitiveTypeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPrimitiveTypeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the PrimitiveType element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForPrimitiveTypeClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)primitiveType.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemovePrimitiveTypeClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPrimitiveTypeClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the PrimitiveType element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPrimitiveTypeIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditPrimitiveTypeIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPrimitiveTypeIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the PrimitiveType element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPrimitiveTypeOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((PrimitiveType)primitiveType).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditPrimitiveTypeOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPrimitiveTypeOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the PrimitiveType element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForPrimitiveTypeOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemovePrimitiveTypeOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPrimitiveTypeOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the PrimitiveType element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForPrimitiveTypeTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((PrimitiveType)primitiveType).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditPrimitiveTypeTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPrimitiveTypeTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the PrimitiveType element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForPrimitiveTypeTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemovePrimitiveTypeTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPrimitiveTypeTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the PrimitiveType element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForPrimitiveTypeIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditPrimitiveTypeIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPrimitiveTypeIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the PrimitiveType element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForPrimitiveTypePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((PrimitiveType)primitiveType).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditPrimitiveTypePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPrimitiveTypePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the PrimitiveType element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForPrimitiveTypePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)primitiveType.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemovePrimitiveTypePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPrimitiveTypePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the PrimitiveType element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForPrimitiveTypeRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((PrimitiveType)primitiveType).getRepresentation());
		cc.append(SetCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditPrimitiveTypeRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPrimitiveTypeRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the PrimitiveType element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForPrimitiveTypeRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemovePrimitiveTypeRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPrimitiveTypeRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the PrimitiveType element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.representation);
		

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
	protected void initializeExpectedModelForPrimitiveTypeUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((PrimitiveType)primitiveType).getUseCase());
		cc.append(AddCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditPrimitiveTypeUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPrimitiveTypeUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the PrimitiveType element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForPrimitiveTypeUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)primitiveType.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, primitiveType, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemovePrimitiveTypeUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		primitiveType = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (primitiveType == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForPrimitiveTypeUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the PrimitiveType element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), primitiveTypeMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(primitiveTypeMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, primitiveTypeMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the PrimitiveType element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.PrimitiveType.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
