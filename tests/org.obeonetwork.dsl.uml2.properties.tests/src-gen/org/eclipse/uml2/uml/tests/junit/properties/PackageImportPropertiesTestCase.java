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

import org.eclipse.emf.eef.runtime.tests.SWTBotEEFTestCase;
import org.eclipse.emf.eef.runtime.tests.exceptions.InputModelInvalidException;
import org.eclipse.emf.eef.runtime.tests.utils.EEFTestsModelsUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository.PackageImport;
/**
 * TestCase for PackageImport
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 */
public class PackageImportPropertiesTestCase extends SWTBotEEFTestCase {

	/**
	 * The EClass of the type to edit
	 */
	private EClass packageImportMetaClass = UMLPackage.eINSTANCE.getPackageImport();

	/**
	 * The type to edit
	 */
	private EObject packageImport;

	/**
	 * The enum value for the enum class visibility
	 */
	private Object enumValueForVisibility;
	/**
	 * The reference value for the reference class importedPackage
	 */
	private Object referenceValueForImportedPackage;

	/**
	 * The reference value for the reference class importingNamespace
	 */
	private Object referenceValueForImportingNamespace;
	/**
	 * The EClass of the reference to edit
	 */
	private EClass namespaceMetaClass = UMLPackage.eINSTANCE.getNamespace();

	/**
	 * The EClass of the reference to edit
	 */
	private EClass packageMetaClass = UMLPackage.eINSTANCE.getPackage();
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
	protected void initializeExpectedModelForPackageImportVisibility() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject packageImport = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, packageImportMetaClass);
		if (packageImport == null)
			throw new InputModelInvalidException(packageImportMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
				cc.append(SetCommand.create(editingDomain, packageImport, UMLPackage.eINSTANCE.getPackageImport_Visibility(), UPDATED_VALUE));
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
	public void testEditPackageImportVisibility() throws Exception {

		// Import the input model
		initializeInputModel();

		packageImport = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), packageImportMetaClass);
		if (packageImport == null)
			throw new InputModelInvalidException(packageImportMetaClass.getName());

		enumValueForVisibility = bot.changeEnumLiteralValue(UMLPackage.eINSTANCE.getVisibilityKind(), ((PackageImport)packageImport).getVisibility().getLiteral());
		// Create the expected model
		initializeExpectedModelForPackageImportVisibility();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the PackageImport element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), packageImportMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(packageImportMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the visibility feature of the PackageImport element 
				bot.editPropertyEEFText(propertiesView, UmlViewsRepository.PackageImport.Properties.visibility, UPDATED_VALUE, bot.selectNode(modelEditor, firstInstanceOf));

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
	protected void initializeExpectedModelForPackageImportImportedPackage() throws InputModelInvalidException, IOException {
		// Create the expected model content by applying the attempted command on a copy of the input model content
		createExpectedModel();
		EObject packageImport = EEFTestsModelsUtils.getFirstInstanceOf(expectedModel, packageImportMetaClass);
		if (packageImport == null)
			throw new InputModelInvalidException(packageImportMetaClass.getName());
		CompoundCommand cc = new CompoundCommand();
		allInstancesOf = EEFTestsModelsUtils.getAllInstancesOf(expectedModel, packageMetaClass);
		referenceValueForImportedPackage = bot.changeReferenceValue(allInstancesOf, ((PackageImport)packageImport).getImportedPackage());
		cc.append(SetCommand.create(editingDomain, packageImport, UMLPackage.eINSTANCE.getPackageImport_ImportedPackage(), referenceValueForImportedPackage));
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
	public void testEditPackageImportImportedPackage() throws Exception {

		// Import the input model
		initializeInputModel();

		packageImport = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), packageImportMetaClass);
		if (packageImport == null)
			throw new InputModelInvalidException(packageImportMetaClass.getName());

		// Create the expected model
		initializeExpectedModelForPackageImportImportedPackage();

		// Open the input model with the treeview editor
		SWTBotEditor modelEditor = bot.openActiveModel();

		// Open the EEF properties view to edit the PackageImport element
		EObject firstInstanceOf = EEFTestsModelsUtils.getFirstInstanceOf(bot.getActiveResource(), packageImportMetaClass);
		if (firstInstanceOf == null)
			throw new InputModelInvalidException(packageImportMetaClass.getName());
		SWTBotView propertiesView = bot.prepareLiveEditing(modelEditor, firstInstanceOf, null);

		// Change value of the importedPackage feature of the PackageImport element 
		bot.editPropertyEObjectFlatComboViewerFeature(propertiesView, UmlViewsRepository.PackageImport.Properties.importedPackage, allInstancesOf.indexOf(referenceValueForImportedPackage), bot.selectNode(modelEditor, firstInstanceOf));

		// Save the modification
		bot.finalizeEdition(modelEditor);

		// Compare real model with expected model
		assertExpectedModelReached(expectedModel);

		// Delete the input model
		deleteModels();

	}






}
