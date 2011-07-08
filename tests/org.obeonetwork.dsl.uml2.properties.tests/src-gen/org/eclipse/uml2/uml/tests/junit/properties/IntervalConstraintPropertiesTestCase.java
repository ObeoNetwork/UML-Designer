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
package org.eclipse.uml2.uml.tests.junit.properties;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.IntervalConstraint;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for IntervalConstraint
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class IntervalConstraintPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass intervalConstraintMetaClass = UMLPackage.eINSTANCE.getIntervalConstraint();

	/**
	 * The type to edit
	 */
	private EObject intervalConstraint;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class constrainedElement
	 */
	private Object referenceValueForConstrainedElement;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class context
	 */
	private Object referenceValueForContext;
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
	protected void initializeExpectedModelForIntervalConstraintName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, intervalConstraint, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditIntervalConstraintName() throws Exception {

		// Import the input model
		initializeInputModel();

		intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIntervalConstraintName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the IntervalConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the IntervalConstraint element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.IntervalConstraint.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForIntervalConstraintVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, intervalConstraint, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditIntervalConstraintVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((IntervalConstraint)intervalConstraint).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForIntervalConstraintVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the IntervalConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the IntervalConstraint element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.IntervalConstraint.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForIntervalConstraintClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((IntervalConstraint)intervalConstraint).getClientDependency());
		cc.append(AddCommand.create(editingDomain, intervalConstraint, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditIntervalConstraintClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIntervalConstraintClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the IntervalConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the IntervalConstraint element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.IntervalConstraint.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForIntervalConstraintClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)intervalConstraint.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, intervalConstraint, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveIntervalConstraintClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForIntervalConstraintClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the IntervalConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the IntervalConstraint element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.IntervalConstraint.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForIntervalConstraintOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((IntervalConstraint)intervalConstraint).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, intervalConstraint, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditIntervalConstraintOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIntervalConstraintOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the IntervalConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the IntervalConstraint element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.IntervalConstraint.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForIntervalConstraintOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, intervalConstraint, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveIntervalConstraintOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForIntervalConstraintOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the IntervalConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the IntervalConstraint element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.IntervalConstraint.Properties.owningTemplateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForIntervalConstraintTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((IntervalConstraint)intervalConstraint).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, intervalConstraint, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditIntervalConstraintTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForIntervalConstraintTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the IntervalConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the IntervalConstraint element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.IntervalConstraint.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForIntervalConstraintTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, intervalConstraint, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveIntervalConstraintTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		intervalConstraint = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (intervalConstraint == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForIntervalConstraintTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the IntervalConstraint element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), intervalConstraintMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(intervalConstraintMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the templateParameter feature of the IntervalConstraint element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.IntervalConstraint.Properties.templateParameter, bot.selectNode(modelEditor, firstInstanceOf));
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}












}
