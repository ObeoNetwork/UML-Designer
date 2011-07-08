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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.Device;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for Device
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class DeviceTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass deviceMetaClass = UMLPackage.eINSTANCE.getDevice();

	/**
	 * The type to edit
	 */
	private EObject device;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class classifierBehavior
	 */
	private Object referenceValueForClassifierBehavior;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

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
	protected void initializeExpectedModelForDeviceName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditDeviceName() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeviceName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the Device element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Device.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDeviceVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditDeviceVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((Device)device).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForDeviceVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the Device element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Device.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDeviceClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((Device)device).getClientDependency());
		cc.append(AddCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditDeviceClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeviceClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Device element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Device.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForDeviceClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)device.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveDeviceClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeviceClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the Device element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Device.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDeviceIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditDeviceIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeviceIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the Device element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Device.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDeviceOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Device)device).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditDeviceOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeviceOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Device element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Device.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDeviceOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveDeviceOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeviceOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the Device element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Device.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForDeviceTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((Device)device).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditDeviceTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeviceTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Device element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Device.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForDeviceTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveDeviceTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeviceTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the Device element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Device.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForDeviceIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditDeviceIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeviceIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the Device element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Device.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForDevicePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((Device)device).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditDevicePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDevicePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Device element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Device.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForDevicePowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)device.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveDevicePowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDevicePowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the Device element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Device.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDeviceRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((Device)device).getRepresentation());
		cc.append(SetCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditDeviceRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeviceRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Device element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Device.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForDeviceRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveDeviceRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeviceRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the Device element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.Device.Properties.representation);
		

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
	protected void initializeExpectedModelForDeviceUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((Device)device).getUseCase());
		cc.append(AddCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditDeviceUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeviceUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Device element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Device.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForDeviceUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)device.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveDeviceUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForDeviceUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the Device element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.Device.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForDeviceIsActive() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject device = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, device, UMLPackage.eINSTANCE.getClass_IsActive(), UPDATED_VALUE));
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
	public void testEditDeviceIsActive() throws Exception {

		// Import the input model
		initializeInputModel();

		device = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (device == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForDeviceIsActive();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the Device element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), deviceMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(deviceMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, deviceMetaClass, firstInstanceOf, null);

		// Change value of the isActive feature of the Device element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.Device.Properties.isActive, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}
























}
