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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.ReclassifyObjectAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for ReclassifyObjectAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ReclassifyObjectActionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass reclassifyObjectActionMetaClass = UMLPackage.eINSTANCE.getReclassifyObjectAction();

	/**
	 * The type to edit
	 */
	private EObject reclassifyObjectAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class oldClassifier
	 */
	private Object referenceValueForOldClassifier;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class newClassifier
	 */
	private Object referenceValueForNewClassifier;
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
	private EClass activityNodeMetaClass = UMLPackage.eINSTANCE.getActivityNode();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass classifierMetaClass = UMLPackage.eINSTANCE.getClassifier();

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
	protected void initializeExpectedModelForReclassifyObjectActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditReclassifyObjectActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReclassifyObjectActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the ReclassifyObjectAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReclassifyObjectActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditReclassifyObjectActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ReclassifyObjectAction)reclassifyObjectAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForReclassifyObjectActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the ReclassifyObjectAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReclassifyObjectActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((ReclassifyObjectAction)reclassifyObjectAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditReclassifyObjectActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReclassifyObjectActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReclassifyObjectAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReclassifyObjectActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)reclassifyObjectAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveReclassifyObjectActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReclassifyObjectActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the ReclassifyObjectAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReclassifyObjectActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditReclassifyObjectActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReclassifyObjectActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the ReclassifyObjectAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReclassifyObjectActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((ReclassifyObjectAction)reclassifyObjectAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditReclassifyObjectActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReclassifyObjectActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReclassifyObjectAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReclassifyObjectActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveReclassifyObjectActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReclassifyObjectActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the ReclassifyObjectAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForReclassifyObjectActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((ReclassifyObjectAction)reclassifyObjectAction).getActivity());
		cc.append(SetCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditReclassifyObjectActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReclassifyObjectActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ReclassifyObjectAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReclassifyObjectActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveReclassifyObjectActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReclassifyObjectActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the ReclassifyObjectAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForReclassifyObjectActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((ReclassifyObjectAction)reclassifyObjectAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditReclassifyObjectActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReclassifyObjectActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReclassifyObjectAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReclassifyObjectActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)reclassifyObjectAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveReclassifyObjectActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReclassifyObjectActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the ReclassifyObjectAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReclassifyObjectActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((ReclassifyObjectAction)reclassifyObjectAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditReclassifyObjectActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReclassifyObjectActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReclassifyObjectAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForReclassifyObjectActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)reclassifyObjectAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveReclassifyObjectActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForReclassifyObjectActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the ReclassifyObjectAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForReclassifyObjectActionIsReplaceAll() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, reclassifyObjectAction, UMLPackage.eINSTANCE.getReclassifyObjectAction_IsReplaceAll(), UPDATED_VALUE));
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
	public void testEditReclassifyObjectActionIsReplaceAll() throws Exception {

		// Import the input model
		initializeInputModel();

		reclassifyObjectAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (reclassifyObjectAction == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForReclassifyObjectActionIsReplaceAll();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the ReclassifyObjectAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), reclassifyObjectActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(reclassifyObjectActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isReplaceAll feature of the ReclassifyObjectAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.ReclassifyObjectAction.Properties.isReplaceAll, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




















}
