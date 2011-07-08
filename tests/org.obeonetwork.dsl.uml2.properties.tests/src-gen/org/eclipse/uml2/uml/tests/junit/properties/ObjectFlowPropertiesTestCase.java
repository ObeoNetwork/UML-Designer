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
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ObjectFlow
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ObjectFlowPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass objectFlowMetaClass = UMLPackage.eINSTANCE.getObjectFlow();

	/**
	 * The type to edit
	 */
	private EObject objectFlow;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class target
	 */
	private Object referenceValueForTarget;

	/**
	 * The reference value for the reference class redefinedEdge
	 */
	private Object referenceValueForRedefinedEdge;

	/**
	 * The reference value for the reference class source
	 */
	private Object referenceValueForSource;

	/**
	 * The reference value for the reference class selection
	 */
	private Object referenceValueForSelection;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class interrupts
	 */
	private Object referenceValueForInterrupts;

	/**
	 * The reference value for the reference class transformation
	 */
	private Object referenceValueForTransformation;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass interruptibleActivityRegionMetaClass = UMLPackage.eINSTANCE.getInterruptibleActivityRegion();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass structuredActivityNodeMetaClass = UMLPackage.eINSTANCE.getStructuredActivityNode();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityMetaClass = UMLPackage.eINSTANCE.getActivity();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass behaviorMetaClass = UMLPackage.eINSTANCE.getBehavior();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityNodeMetaClass = UMLPackage.eINSTANCE.getActivityNode();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityPartitionMetaClass = UMLPackage.eINSTANCE.getActivityPartition();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass activityEdgeMetaClass = UMLPackage.eINSTANCE.getActivityEdge();

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
	protected void initializeExpectedModelForObjectFlowName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditObjectFlowName() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForObjectFlowName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the ObjectFlow element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ObjectFlow.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForObjectFlowVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditObjectFlowVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ObjectFlow)objectFlow).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForObjectFlowVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the ObjectFlow element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ObjectFlow.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForObjectFlowClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ObjectFlow)objectFlow).getClientDependency());
		cc.append(AddCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditObjectFlowClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForObjectFlowClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ObjectFlow element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ObjectFlow.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForObjectFlowClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)objectFlow.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveObjectFlowClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForObjectFlowClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ObjectFlow element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ObjectFlow.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForObjectFlowIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditObjectFlowIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForObjectFlowIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ObjectFlow element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ObjectFlow.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForObjectFlowInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ObjectFlow)objectFlow).getInPartition());
		cc.append(AddCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getActivityEdge_InPartition(), referenceValueForInPartition));
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
	public void testEditObjectFlowInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForObjectFlowInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ObjectFlow element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ObjectFlow.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForObjectFlowInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)objectFlow.eGet(UMLPackage.eINSTANCE.getActivityEdge_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getActivityEdge_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveObjectFlowInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForObjectFlowInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ObjectFlow element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ObjectFlow.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForObjectFlowInterrupts() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInterrupts = bot.changeReferenceValue(allInstancesOf, ((ObjectFlow)objectFlow).getInterrupts());
		cc.append(SetCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getActivityEdge_Interrupts(), referenceValueForInterrupts));
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
	public void testEditObjectFlowInterrupts() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForObjectFlowInterrupts();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the interrupts feature of the ObjectFlow element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ObjectFlow.Properties.interrupts, allInstancesOf.indexOf(referenceValueForInterrupts)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForObjectFlowInterrupts() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		cc.append(SetCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getActivityEdge_Interrupts(), null));
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
	public void testRemoveObjectFlowInterrupts() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForObjectFlowInterrupts();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the interrupts feature of the ObjectFlow element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ObjectFlow.Properties.interrupts, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForObjectFlowInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ObjectFlow)objectFlow).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getActivityEdge_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditObjectFlowInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForObjectFlowInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ObjectFlow element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ObjectFlow.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForObjectFlowInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getActivityEdge_InStructuredNode(), null));
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
	public void testRemoveObjectFlowInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForObjectFlowInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ObjectFlow element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ObjectFlow.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForObjectFlowActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ObjectFlow)objectFlow).getActivity());
		cc.append(SetCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getActivityEdge_Activity(), referenceValueForActivity));
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
	public void testEditObjectFlowActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForObjectFlowActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ObjectFlow element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ObjectFlow.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForObjectFlowActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getActivityEdge_Activity(), null));
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
	public void testRemoveObjectFlowActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForObjectFlowActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ObjectFlow element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ObjectFlow.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForObjectFlowIsMulticast() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getObjectFlow_IsMulticast(), UPDATED_VALUE));
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
	public void testEditObjectFlowIsMulticast() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForObjectFlowIsMulticast();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isMulticast feature of the ObjectFlow element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ObjectFlow.Properties.isMulticast, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForObjectFlowIsMultireceive() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, objectFlow, UMLPackage.eINSTANCE.getObjectFlow_IsMultireceive(), UPDATED_VALUE));
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
	public void testEditObjectFlowIsMultireceive() throws Exception {

		// Import the input model
		initializeInputModel();

		objectFlow = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (objectFlow == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForObjectFlowIsMultireceive();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ObjectFlow element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), objectFlowMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(objectFlowMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isMultireceive feature of the ObjectFlow element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ObjectFlow.Properties.isMultireceive, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
