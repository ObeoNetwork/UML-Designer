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

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
/**
 * TestCase for ElementImport
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class ElementImportTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass elementImportMetaClass = UMLPackage.eINSTANCE.getElementImport();

	/**
	 * The type to edit
	 */
	private EObject elementImport;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class importingNamespace
	 */
	private Object referenceValueForImportingNamespace;

	/**
	 * The reference value for the reference class importedElement
	 */
	private Object referenceValueForImportedElement;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass packageableElementMetaClass = UMLPackage.eINSTANCE.getPackageableElement();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass namespaceMetaClass = UMLPackage.eINSTANCE.getNamespace();
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
	protected void initializeExpectedModelForElementImportVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject elementImport = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, elementImportMetaClass);
		if (elementImport == null)
			throw new InputModelInvalidException(elementImportMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, elementImport, UMLPackage.eINSTANCE.getElementImport_Visibility(), UPDATED_VALUE));
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
	public void testEditElementImportVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		elementImport = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), elementImportMetaClass);
		if (elementImport == null)
			throw new InputModelInvalidException(elementImportMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((ElementImport)elementImport).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForElementImportVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ElementImport element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), elementImportMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(elementImportMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, elementImportMetaClass, firstInstanceOf, null);

		// Change value of the visibility feature of the ElementImport element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ElementImport.Properties.visibility, UPDATED_VALUE);

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
	protected void initializeExpectedModelForElementImportAlias() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject elementImport = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, elementImportMetaClass);
		if (elementImport == null)
			throw new InputModelInvalidException(elementImportMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, elementImport, UMLPackage.eINSTANCE.getElementImport_Alias(), UPDATED_VALUE));
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
	public void testEditElementImportAlias() throws Exception {

		// Import the input model
		initializeInputModel();

		elementImport = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), elementImportMetaClass);
		if (elementImport == null)
			throw new InputModelInvalidException(elementImportMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForElementImportAlias();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF wizard (by double click) to edit the ElementImport element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), elementImportMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(elementImportMetaClass.getName());

		SWTBotShell wizardShell = bot.prepareBatchEditing(modelEditor, elementImportMetaClass, firstInstanceOf, null);

		// Change value of the alias feature of the ElementImport element 
				bot.editTextFeature(wizardShell, UmlViewsRepository.ElementImport.Properties.alias, UPDATED_VALUE);

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






}
