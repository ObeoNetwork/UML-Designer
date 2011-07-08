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
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.AssociationClass;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for AssociationClass
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class AssociationClassTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass associationClassMetaClass = UMLPackage.eINSTANCE.getAssociationClass();

	/**
	 * The type to edit
	 */
	private EObject associationClass;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class classifierBehavior
	 */
	private Object referenceValueForClassifierBehavior;

	/**
	 * The reference value for the reference class clientDependency
	 */
	private Object referenceValueForClientDependency;

	/**
	 * The reference value for the reference class representation
	 */
	private Object referenceValueForRepresentation;

	/**
	 * The reference value for the reference class navigableOwnedEnd
	 */
	private Object referenceValueForNavigableOwnedEnd;

	/**
	 * The reference value for the reference class owningTemplateParameter
	 */
	private Object referenceValueForOwningTemplateParameter;

	/**
	 * The reference value for the reference class redefinedClassifier
	 */
	private Object referenceValueForRedefinedClassifier;

	/**
	 * The reference value for the reference class useCase
	 */
	private Object referenceValueForUseCase;

	/**
	 * The reference value for the reference class memberEnd
	 */
	private Object referenceValueForMemberEnd;

	/**
	 * The reference value for the reference class powertypeExtent
	 */
	private Object referenceValueForPowertypeExtent;

	/**
	 * The reference value for the reference class templateParameter
	 */
	private Object referenceValueForTemplateParameter;
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
	protected void initializeExpectedModelForAssociationClassName() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getNamedElement_Name(), UPDATED_VALUE));
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
	public void testEditAssociationClassName() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassName();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the name feature of the AssociationClass element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.name, UPDATED_VALUE);

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
	protected void initializeExpectedModelForAssociationClassVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getNamedElement_Visibility(), UPDATED_VALUE));
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
	public void testEditAssociationClassVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((AssociationClass)associationClass).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForAssociationClassVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the AssociationClass element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForAssociationClassClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, dependencyMetaClass);
		referenceValueForClientDependency = bot.changeReferenceValue(allInstancesOf, ((AssociationClass)associationClass).getClientDependency());
		cc.append(AddCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), referenceValueForClientDependency));
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
	public void testEditAssociationClassClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the AssociationClass element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.clientDependency, referenceValueForClientDependency);

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
	protected void initializeRemoveExpectedModelForAssociationClassClientDependency() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)associationClass.eGet(UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), allReferencedInstances.get(0)));
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
	public void testRemoveAssociationClassClientDependency() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAssociationClassClientDependency();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the clientDependency feature of the AssociationClass element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.clientDependency, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForAssociationClassIsLeaf() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(), UPDATED_VALUE));
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
	public void testEditAssociationClassIsLeaf() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassIsLeaf();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the isLeaf feature of the AssociationClass element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.isLeaf, UPDATED_VALUE);

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
	protected void initializeExpectedModelForAssociationClassOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForOwningTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((AssociationClass)associationClass).getOwningTemplateParameter());
		cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), referenceValueForOwningTemplateParameter));
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
	public void testEditAssociationClassOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the AssociationClass element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.owningTemplateParameter, allInstancesOf.indexOf(referenceValueForOwningTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForAssociationClassOwningTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(), null));
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
	public void testRemoveAssociationClassOwningTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAssociationClassOwningTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the owningTemplateParameter feature of the AssociationClass element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.owningTemplateParameter);
		

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
	protected void initializeExpectedModelForAssociationClassTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForTemplateParameter = bot.changeReferenceValue(allInstancesOf, ((AssociationClass)associationClass).getTemplateParameter());
		cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), referenceValueForTemplateParameter));
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
	public void testEditAssociationClassTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the AssociationClass element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.templateParameter, allInstancesOf.indexOf(referenceValueForTemplateParameter)+1);

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
	protected void initializeRemoveExpectedModelForAssociationClassTemplateParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(), null));
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
	public void testRemoveAssociationClassTemplateParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAssociationClassTemplateParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the templateParameter feature of the AssociationClass element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.templateParameter);
		

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
	protected void initializeExpectedModelForAssociationClassIsAbstract() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getClassifier_IsAbstract(), UPDATED_VALUE));
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
	public void testEditAssociationClassIsAbstract() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassIsAbstract();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the isAbstract feature of the AssociationClass element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.isAbstract, UPDATED_VALUE);

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
	protected void initializeExpectedModelForAssociationClassPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, generalizationSetMetaClass);
		referenceValueForPowertypeExtent = bot.changeReferenceValue(allInstancesOf, ((AssociationClass)associationClass).getPowertypeExtent());
		cc.append(AddCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), referenceValueForPowertypeExtent));
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
	public void testEditAssociationClassPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the AssociationClass element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.powertypeExtent, referenceValueForPowertypeExtent);

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
	protected void initializeRemoveExpectedModelForAssociationClassPowertypeExtent() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)associationClass.eGet(UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(), allReferencedInstances.get(0)));
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
	public void testRemoveAssociationClassPowertypeExtent() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAssociationClassPowertypeExtent();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the powertypeExtent feature of the AssociationClass element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.powertypeExtent, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForAssociationClassRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		referenceValueForRepresentation = bot.changeReferenceValue(allInstancesOf, ((AssociationClass)associationClass).getRepresentation());
		cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getClassifier_Representation(), referenceValueForRepresentation));
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
	public void testEditAssociationClassRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the AssociationClass element 
		bot.editEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.representation, allInstancesOf.indexOf(referenceValueForRepresentation)+1);

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
	protected void initializeRemoveExpectedModelForAssociationClassRepresentation() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, collaborationUseMetaClass);
		cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getClassifier_Representation(), null));
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
	public void testRemoveAssociationClassRepresentation() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAssociationClassRepresentation();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the representation feature of the AssociationClass element
		bot.removeEObjectFlatComboViewerFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.representation);
		

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
	protected void initializeExpectedModelForAssociationClassUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, useCaseMetaClass);
		referenceValueForUseCase = bot.changeReferenceValue(allInstancesOf, ((AssociationClass)associationClass).getUseCase());
		cc.append(AddCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getClassifier_UseCase(), referenceValueForUseCase));
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
	public void testEditAssociationClassUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the AssociationClass element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.useCase, referenceValueForUseCase);

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
	protected void initializeRemoveExpectedModelForAssociationClassUseCase() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)associationClass.eGet(UMLPackage.eINSTANCE.getClassifier_UseCase());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getClassifier_UseCase(), allReferencedInstances.get(0)));
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
	public void testRemoveAssociationClassUseCase() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAssociationClassUseCase();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the useCase feature of the AssociationClass element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.useCase, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForAssociationClassIsActive() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getClass_IsActive(), UPDATED_VALUE));
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
	public void testEditAssociationClassIsActive() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassIsActive();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the isActive feature of the AssociationClass element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.isActive, UPDATED_VALUE);

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
	protected void initializeExpectedModelForAssociationClassMemberEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForMemberEnd = bot.changeReferenceValue(allInstancesOf, ((AssociationClass)associationClass).getMemberEnd());
		cc.append(AddCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getAssociation_MemberEnd(), referenceValueForMemberEnd));
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
	public void testEditAssociationClassMemberEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassMemberEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the memberEnd feature of the AssociationClass element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.memberEnd, referenceValueForMemberEnd);

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
	protected void initializeRemoveExpectedModelForAssociationClassMemberEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)associationClass.eGet(UMLPackage.eINSTANCE.getAssociation_MemberEnd());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getAssociation_MemberEnd(), allReferencedInstances.get(0)));
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
	public void testRemoveAssociationClassMemberEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAssociationClassMemberEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the memberEnd feature of the AssociationClass element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.memberEnd, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

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
	protected void initializeExpectedModelForAssociationClassIsDerived() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getAssociation_IsDerived(), UPDATED_VALUE));
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
	public void testEditAssociationClassIsDerived() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassIsDerived();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the isDerived feature of the AssociationClass element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.isDerived, UPDATED_VALUE);

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
	protected void initializeExpectedModelForAssociationClassNavigableOwnedEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, propertyMetaClass);
		referenceValueForNavigableOwnedEnd = bot.changeReferenceValue(allInstancesOf, ((AssociationClass)associationClass).getNavigableOwnedEnd());
		cc.append(AddCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), referenceValueForNavigableOwnedEnd));
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
	public void testEditAssociationClassNavigableOwnedEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForAssociationClassNavigableOwnedEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the navigableOwnedEnd feature of the AssociationClass element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.navigableOwnedEnd, referenceValueForNavigableOwnedEnd);

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
	protected void initializeRemoveExpectedModelForAssociationClassNavigableOwnedEnd() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject associationClass = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)associationClass.eGet(UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, associationClass, UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), allReferencedInstances.get(0)));
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
	public void testRemoveAssociationClassNavigableOwnedEnd() throws Exception {

		// Import the input model
		initializeInputModel();

		associationClass = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (associationClass == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForAssociationClassNavigableOwnedEnd();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the AssociationClass element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), associationClassMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(associationClassMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, associationClassMetaClass, firstInstanceOf, null);

		// Change value of the navigableOwnedEnd feature of the AssociationClass element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.AssociationClass.Properties.navigableOwnedEnd, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






























}
