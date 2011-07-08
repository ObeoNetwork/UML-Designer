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
import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;
/**
 * TestCase for TemplateSignature
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class TemplateSignatureTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass templateSignatureMetaClass = UMLPackage.eINSTANCE.getTemplateSignature();

	/**
	 * The type to edit
	 */
	private EObject templateSignature;

	/**
	 * The reference value for the reference class template
	 */
	private Object referenceValueForTemplate;

	/**
	 * The reference value for the reference class parameter
	 */
	private Object referenceValueForParameter;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateParameterMetaClass = UMLPackage.eINSTANCE.getTemplateParameter();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass templateableElementMetaClass = UMLPackage.eINSTANCE.getTemplateableElement();
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
	protected void initializeExpectedModelForTemplateSignatureParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject templateSignature = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, templateSignatureMetaClass);
		if (templateSignature == null)
			throw new InputModelInvalidException(templateSignatureMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, templateParameterMetaClass);
		referenceValueForParameter = bot.changeReferenceValue(allInstancesOf, ((TemplateSignature)templateSignature).getParameter());
		cc.append(AddCommand.create(editingDomain, templateSignature, UMLPackage.eINSTANCE.getTemplateSignature_Parameter(), referenceValueForParameter));
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
	public void testEditTemplateSignatureParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		templateSignature = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), templateSignatureMetaClass);
		if (templateSignature == null)
			throw new InputModelInvalidException(templateSignatureMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForTemplateSignatureParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TemplateSignature element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), templateSignatureMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(templateSignatureMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, templateSignatureMetaClass, firstInstanceOf, null);

		// Change value of the parameter feature of the TemplateSignature element 
		bot.editAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.TemplateSignature.Properties.parameter, referenceValueForParameter);

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
	protected void initializeRemoveExpectedModelForTemplateSignatureParameter() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject templateSignature = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, templateSignatureMetaClass);
		if (templateSignature == null)
			throw new InputModelInvalidException(templateSignatureMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		List<EObject> allReferencedInstances = (List<EObject>)templateSignature.eGet(UMLPackage.eINSTANCE.getTemplateSignature_Parameter());
		if (allReferencedInstances.size() > 0) {
			cc.append(RemoveCommand.create(editingDomain, templateSignature, UMLPackage.eINSTANCE.getTemplateSignature_Parameter(), allReferencedInstances.get(0)));
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
	public void testRemoveTemplateSignatureParameter() throws Exception {

		// Import the input model
		initializeInputModel();

		templateSignature = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), templateSignatureMetaClass);
		if (templateSignature == null)
			throw new InputModelInvalidException(templateSignatureMetaClass.getName());

		// Create the expected model
		initializeRemoveExpectedModelForTemplateSignatureParameter();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the TemplateSignature element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), templateSignatureMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(templateSignatureMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, templateSignatureMetaClass, firstInstanceOf, null);

		// Change value of the parameter feature of the TemplateSignature element 
		bot.removeAdvancedReferencesTableFeature(wizardShell, UmlViewsRepository.TemplateSignature.Properties.parameter, UmlMessages.PropertiesEditionPart_RemoveListViewerLabel);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}




}
