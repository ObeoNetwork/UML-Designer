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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Substitution;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Substitution
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class SubstitutionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass substitutionMetaClass = UMLPackage.eINSTANCE.getSubstitution();

	/**
	 * The type to edit
	 */
	private EObject substitution;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class substitutingClassifier
	 */
	private Object referenceValueForSubstitutingClassifier;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class contract
	 */
	private Object referenceValueForContract;

	/**
	 * The reference value for the reference class client
	 */
	private Object referenceValueForClient;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class supplier
	 */
	private Object referenceValueForSupplier;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass namedElementMetaClass = UMLPackage.eINSTANCE.getNamedElement();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

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
	protected void initializeExpectedModelForSubstitutionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject substitution = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, substitution, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditSubstitutionName() throws Exception {

		// Import the input model
		initializeInputModel();

		substitution = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSubstitutionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Substitution element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, substitutionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Substitution element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Substitution.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSubstitutionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject substitution = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, substitution, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditSubstitutionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		substitution = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Substitution)substitution).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForSubstitutionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Substitution element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, substitutionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Substitution element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Substitution.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForSubstitutionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject substitution = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Substitution)substitution).getClientDependency());
		cc.append(AddCommand.create(editingDomain, substitution, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditSubstitutionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		substitution = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSubstitutionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Substitution element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, substitutionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Substitution element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Substitution.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForSubstitutionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject substitution = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)substitution.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, substitution, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveSubstitutionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		substitution = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSubstitutionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Substitution element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, substitutionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Substitution element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Substitution.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForSubstitutionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject substitution = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Substitution)substitution).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, substitution, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditSubstitutionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		substitution = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSubstitutionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Substitution element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, substitutionMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Substitution element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Substitution.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForSubstitutionOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject substitution = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, substitution, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveSubstitutionOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		substitution = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSubstitutionOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Substitution element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, substitutionMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Substitution element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Substitution.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForSubstitutionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject substitution = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Substitution)substitution).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, substitution, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditSubstitutionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		substitution = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForSubstitutionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Substitution element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, substitutionMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Substitution element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Substitution.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForSubstitutionTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject substitution = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, substitution, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveSubstitutionTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		substitution = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (substitution == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForSubstitutionTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Substitution element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), substitutionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(substitutionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, substitutionMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Substitution element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Substitution.Properties.templateParameter);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
