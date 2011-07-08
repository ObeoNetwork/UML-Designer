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
/**
 * TestCase for Region
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class RegionTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass regionMetaClass = UMLPackage.eINSTANCE.getRegion();

	/**
	 * The type to edit
	 */
	private EObject region;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class stateMachine
	 */
	private Object referenceValueForStateMachine;

	/**
	 * The reference value for the reference class state
	 */
	private Object referenceValueForState;

	/**
	 * The reference value for the reference class extendedRegion
	 */
	private Object referenceValueForExtendedRegion;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass stateMetaClass = UMLPackage.eINSTANCE.getState();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass stateMachineMetaClass = UMLPackage.eINSTANCE.getStateMachine();

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
	protected void initializeExpectedModelForRegionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject region = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, region, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditRegionName() throws Exception {

		// Import the input model
		initializeInputModel();

		region = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRegionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Region element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, regionMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Region element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Region.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRegionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject region = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, region, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditRegionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		region = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Region)region).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForRegionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Region element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, regionMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Region element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Region.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRegionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject region = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Region)region).getClientDependency());
		cc.append(AddCommand.create(editingDomain, region, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditRegionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		region = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRegionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Region element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, regionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Region element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Region.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForRegionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject region = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)region.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, region, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveRegionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		region = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRegionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Region element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, regionMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Region element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Region.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForRegionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject region = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, region, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditRegionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		region = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRegionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Region element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, regionMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Region element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Region.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForRegionState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject region = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		referenceValueForState = bot.changeReferenceValue(allInstancesOf, ((Region)region).getState());
		cc.append(SetCommand.create(editingDomain, region, UMLPackage.eINSTANCE.getRegion_State(), referenceValueForState));
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
	public void testEditRegionState() throws Exception {

		// Import the input model
		initializeInputModel();

		region = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRegionState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Region element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, regionMetaClass, firstInstanceOf, null);

		// Change value of the state feature of the Region element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Region.Properties.state, allInstancesOf.indexOf(referenceValueForState)+1);

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
	protected void initializeRemoveExpectedModelForRegionState() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject region = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMetaClass);
		cc.append(SetCommand.create(editingDomain, region, UMLPackage.eINSTANCE.getRegion_State(), null));
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
	public void testRemoveRegionState() throws Exception {

		// Import the input model
		initializeInputModel();

		region = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRegionState();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Region element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, regionMetaClass, firstInstanceOf, null);

		// Change value of the state feature of the Region element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Region.Properties.state);
		

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
	protected void initializeExpectedModelForRegionExtendedRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject region = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		referenceValueForExtendedRegion = bot.changeReferenceValue(allInstancesOf, ((Region)region).getExtendedRegion());
		cc.append(SetCommand.create(editingDomain, region, UMLPackage.eINSTANCE.getRegion_ExtendedRegion(), referenceValueForExtendedRegion));
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
	public void testEditRegionExtendedRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		region = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRegionExtendedRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Region element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, regionMetaClass, firstInstanceOf, null);

		// Change value of the extendedRegion feature of the Region element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Region.Properties.extendedRegion, allInstancesOf.indexOf(referenceValueForExtendedRegion)+1);

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
	protected void initializeRemoveExpectedModelForRegionExtendedRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject region = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, regionMetaClass);
		cc.append(SetCommand.create(editingDomain, region, UMLPackage.eINSTANCE.getRegion_ExtendedRegion(), null));
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
	public void testRemoveRegionExtendedRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		region = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRegionExtendedRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Region element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, regionMetaClass, firstInstanceOf, null);

		// Change value of the extendedRegion feature of the Region element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Region.Properties.extendedRegion);
		

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
	protected void initializeExpectedModelForRegionStateMachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject region = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMachineMetaClass);
		referenceValueForStateMachine = bot.changeReferenceValue(allInstancesOf, ((Region)region).getStateMachine());
		cc.append(SetCommand.create(editingDomain, region, UMLPackage.eINSTANCE.getRegion_StateMachine(), referenceValueForStateMachine));
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
	public void testEditRegionStateMachine() throws Exception {

		// Import the input model
		initializeInputModel();

		region = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForRegionStateMachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Region element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, regionMetaClass, firstInstanceOf, null);

		// Change value of the stateMachine feature of the Region element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Region.Properties.stateMachine, allInstancesOf.indexOf(referenceValueForStateMachine)+1);

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
	protected void initializeRemoveExpectedModelForRegionStateMachine() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject region = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, stateMachineMetaClass);
		cc.append(SetCommand.create(editingDomain, region, UMLPackage.eINSTANCE.getRegion_StateMachine(), null));
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
	public void testRemoveRegionStateMachine() throws Exception {

		// Import the input model
		initializeInputModel();

		region = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (region == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForRegionStateMachine();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Region element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), regionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(regionMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, regionMetaClass, firstInstanceOf, null);

		// Change value of the stateMachine feature of the Region element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Region.Properties.stateMachine);
		

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
















}
