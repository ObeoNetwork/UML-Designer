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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.OpaqueAction;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for OpaqueAction
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class OpaqueActionPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass opaqueActionMetaClass = UMLPackage.eINSTANCE.getOpaqueAction();

	/**
	 * The type to edit
	 */
	private EObject opaqueAction;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class inInterruptibleRegion
	 */
	private Object referenceValueForInInterruptibleRegion;

	/**
	 * The reference value for the reference class inPartition
	 */
	private Object referenceValueForInPartition;

	/**
	 * The reference value for the reference class inStructuredNode
	 */
	private Object referenceValueForInStructuredNode;

	/**
	 * The reference value for the reference class outgoing
	 */
	private Object referenceValueForOutgoing;

	/**
	 * The reference value for the reference class activity
	 */
	private Object referenceValueForActivity;

	/**
	 * The reference value for the reference class redefinedNode
	 */
	private Object referenceValueForRedefinedNode;

	/**
	 * The reference value for the reference class incoming
	 */
	private Object referenceValueForIncoming;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;
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
	protected void initializeExpectedModelForOpaqueActionName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditOpaqueActionName() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueActionName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the name feature of the OpaqueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.OpaqueAction.Properties.name, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOpaqueActionVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditOpaqueActionVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((OpaqueAction)opaqueAction).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForOpaqueActionVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the OpaqueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.OpaqueAction.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOpaqueActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((OpaqueAction)opaqueAction).getClientDependency());
		cc.append(AddCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditOpaqueActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the OpaqueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OpaqueAction.Properties.clientDependency, referenceValueForClientDependency, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOpaqueActionClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)opaqueAction.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveOpaqueActionClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueActionClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the clientDependency feature of the OpaqueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OpaqueAction.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOpaqueActionIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditOpaqueActionIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueActionIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the isLeaf feature of the OpaqueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.OpaqueAction.Properties.isLeaf, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOpaqueActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		referenceValueForInStructuredNode = bot.changeReferenceValue(allInstancesOf, ((OpaqueAction)opaqueAction).getInStructuredNode());
		cc.append(SetCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), referenceValueForInStructuredNode));
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
	public void testEditOpaqueActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the OpaqueAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OpaqueAction.Properties.inStructuredNode, allInstancesOf.indexOf(referenceValueForInStructuredNode)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOpaqueActionInStructuredNode() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, structuredActivityNodeMetaClass);
		cc.append(SetCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(), null));
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
	public void testRemoveOpaqueActionInStructuredNode() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueActionInStructuredNode();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inStructuredNode feature of the OpaqueAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OpaqueAction.Properties.inStructuredNode, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForOpaqueActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		referenceValueForActivity = bot.changeReferenceValue(allInstancesOf, ((OpaqueAction)opaqueAction).getActivity());
		cc.append(SetCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), referenceValueForActivity));
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
	public void testEditOpaqueActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the OpaqueAction element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OpaqueAction.Properties.activity, allInstancesOf.indexOf(referenceValueForActivity)+1, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOpaqueActionActivity() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityMetaClass);
		cc.append(SetCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getActivityNode_Activity(), null));
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
	public void testRemoveOpaqueActionActivity() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueActionActivity();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the activity feature of the OpaqueAction element 
		bot.removePropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.OpaqueAction.Properties.activity, bot.selectNode(modelEditor, firstInstanceOf));
		

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
	protected void initializeExpectedModelForOpaqueActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, activityPartitionMetaClass);
		referenceValueForInPartition = bot.changeReferenceValue(allInstancesOf, ((OpaqueAction)opaqueAction).getInPartition());
		cc.append(AddCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), referenceValueForInPartition));
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
	public void testEditOpaqueActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the OpaqueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OpaqueAction.Properties.inPartition, referenceValueForInPartition, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOpaqueActionInPartition() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)opaqueAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InPartition());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getActivityNode_InPartition(), allReferencedInstances.get(0)));
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
	public void testRemoveOpaqueActionInPartition() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueActionInPartition();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inPartition feature of the OpaqueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OpaqueAction.Properties.inPartition, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOpaqueActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, interruptibleActivityRegionMetaClass);
		referenceValueForInInterruptibleRegion = bot.changeReferenceValue(allInstancesOf, ((OpaqueAction)opaqueAction).getInInterruptibleRegion());
		cc.append(AddCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), referenceValueForInInterruptibleRegion));
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
	public void testEditOpaqueActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the OpaqueAction element 
		bot.editPropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OpaqueAction.Properties.inInterruptibleRegion, referenceValueForInInterruptibleRegion, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeRemoveExpectedModelForOpaqueActionInInterruptibleRegion() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)opaqueAction.eGet(UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(), allReferencedInstances.get(0)));
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
	public void testRemoveOpaqueActionInInterruptibleRegion() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForOpaqueActionInInterruptibleRegion();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the inInterruptibleRegion feature of the OpaqueAction element 
		bot.removePropertyAdvancedReferencesTableFeature(propertiesView, UmlViewsRepository.OpaqueAction.Properties.inInterruptibleRegion, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOpaqueActionBody() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getOpaqueAction_Body(), UPDATED_VALUE));
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
	public void testEditOpaqueActionBody() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueActionBody();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the body feature of the OpaqueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.OpaqueAction.Properties.body, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForOpaqueActionLanguage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, opaqueAction, UMLPackage.eINSTANCE.getOpaqueAction_Language(), UPDATED_VALUE));
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
	public void testEditOpaqueActionLanguage() throws Exception {

		// Import the input model
		initializeInputModel();

		opaqueAction = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (opaqueAction == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForOpaqueActionLanguage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the OpaqueAction element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), opaqueActionMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(opaqueActionMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the language feature of the OpaqueAction element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.OpaqueAction.Properties.language, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






















}
