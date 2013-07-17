/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

package org.obeonetwork.dsl.uml2.design.tests.contexts;

import static org.junit.Assert.*;
	import org.obeonetwork.dsl.uml2.design.tests.automation.Context;

// Start of user code AClassDiagramIsOpened imports
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.design.tests.common.EObjects;
import org.obeonetwork.dsl.uml2.design.tests.common.ModelChangeRecorder;
import org.obeonetwork.dsl.uml2.design.tests.contexts.UMLDesignerBot;
import org.obeonetwork.dsl.uml2.usage.preferences.UsagePreferences;
import fr.obeo.dsl.viewpoint.tests.swtbot.support.api.editor.SWTBotDesignerEditor;
import fr.obeo.dsl.viewpoint.ui.business.api.session.SessionEditorInput;
// End of user code

/**
 * Context : A class diagram is opened
 */
public class AClassDiagramIsOpened extends Context {
// Start of user code AClassDiagramIsOpened variables
	protected UMLDesignerBot bot;
	SWTBotDesignerEditor classDiagram;

	public ModelChangeRecorder recorder;
// End of user code

	@Override
	public void setup() {
		// Start of user code AClassDiagramIsOpened setup
		recorder = new ModelChangeRecorder(); 
		UsagePreferences preferences = new UsagePreferences();
		preferences.storeUserAnswer(IDialogConstants.NO_ID);
		bot = new UMLDesignerBot();
		classDiagram = bot.openEntitiesClassDiagram();
		SessionEditorInput input = (SessionEditorInput)classDiagram.getReference().getEditor(false).getEditorInput();
		recorder.startRecording(input.getSession().getTransactionalEditingDomain());
		// End of user code
	}

	@Override
	public void tearDown() {
		// Start of user code AClassDiagramIsOpened tear down
		SessionEditorInput input = (SessionEditorInput)classDiagram.getReference().getEditor(false).getEditorInput();
		recorder.stopRecording(input.getSession().getTransactionalEditingDomain());
		bot.saveChanges();
		bot.deleteTravelAgencyProject();
		// End of user code
	}
	/**
	 * Action : I select the datatype creation tool from the palette
	 */
	public void actionISelectTheDatatypeCreationToolFromThePalette() {
		// Start of user code ISelectTheDatatypeCreationToolFromThePalette
		// TODO Implement action ISelectTheDatatypeCreationToolFromThePalette
		fail("Action ISelectTheDatatypeCreationToolFromThePalette not implemented");
		// End of user code
	}

	/**
	 * Action : I click on the diagram
	 */
	public void actionIClickOnTheDiagram() {
		// Start of user code IClickOnTheDiagram
		// TODO Implement action IClickOnTheDiagram
		fail("Action IClickOnTheDiagram not implemented");
		// End of user code
	}

	/**
	 * Action : I select the enumeration creation tool from the palette and I click on the diagram
	 */
	public void actionISelectTheEnumerationCreationToolFromThePaletteAndIClickOnTheDiagram() {
		// Start of user code ISelectTheEnumerationCreationToolFromThePaletteAndIClickOnTheDiagram
		actionCreateAType("Enumeration");
		// End of user code
	}

	/**
	 * Action : I select the interface creation tool from the palette
	 */
	public void actionISelectTheInterfaceCreationToolFromThePalette() {
		// Start of user code ISelectTheInterfaceCreationToolFromThePalette
		// TODO Implement action ISelectTheInterfaceCreationToolFromThePalette
		fail("Action ISelectTheInterfaceCreationToolFromThePalette not implemented");
		// End of user code
	}

	/**
	 * Action : I click on a class in the diagram
	 */
	public void actionIClickOnAClassInTheDiagram() {
		// Start of user code IClickOnAClassInTheDiagram
		// TODO Implement action IClickOnAClassInTheDiagram
		fail("Action IClickOnAClassInTheDiagram not implemented");
		// End of user code
	}

	/**
	 * Action : I select the primitive type creation tool from the palette
	 */
	public void actionISelectThePrimitiveTypeCreationToolFromThePalette() {
		// Start of user code ISelectThePrimitiveTypeCreationToolFromThePalette
		// TODO Implement action ISelectThePrimitiveTypeCreationToolFromThePalette
		fail("Action ISelectThePrimitiveTypeCreationToolFromThePalette not implemented");
		// End of user code
	}

	/**
	 * Action : I select the class creation tool from the palette and I click on the diagram
	 */
	public void actionISelectTheClassCreationToolFromThePaletteAndIClickOnTheDiagram() {
		// Start of user code ISelectTheClassCreationToolFromThePaletteAndIClickOnTheDiagram
		actionCreateAType("Class");
		// End of user code
	}

	/**
	 * Action : I select the package creation tool from the palette
	 */
	public void actionISelectThePackageCreationToolFromThePalette() {
		// Start of user code ISelectThePackageCreationToolFromThePalette
		// TODO Implement action ISelectThePackageCreationToolFromThePalette
		fail("Action ISelectThePackageCreationToolFromThePalette not implemented");
		// End of user code
	}

	/**
	 * Action : I select the delete from model tool in toolbar
	 */
	public void actionISelectTheDeleteFromModelToolInToolbar() {
		// Start of user code ISelectTheDeleteFromModelToolInToolbar
		// TODO Implement action ISelectTheDeleteFromModelToolInToolbar
		fail("Action ISelectTheDeleteFromModelToolInToolbar not implemented");
		// End of user code
	}

	/**
	 * Behavior : A datatype appears on the diagram
	 */
	public void assertADatatypeAppearsOnTheDiagram() {
		// Start of user code ADatatypeAppearsOnTheDiagram
		// TODO Implement behavior ADatatypeAppearsOnTheDiagram
		fail("Behavior ADatatypeAppearsOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : An enumeration is created in the model
	 */
	public void assertAnEnumerationIsCreatedInTheModel() {
		// Start of user code AnEnumerationIsCreatedInTheModel
		assertElementCreatedInUmlModel("Enumeration0",UMLPackage.eINSTANCE.getEnumeration());
		// End of user code
	}
	/**
	 * Behavior : A class is created in the model
	 */
	public void assertAClassIsCreatedInTheModel() {
		// Start of user code AClassIsCreatedInTheModel
		assertElementCreatedInUmlModel("Class1",UMLPackage.eINSTANCE.getClass_());
		// End of user code
	}
	/**
	 * Behavior : An enumeration appears on the diagram
	 */
	public void assertAnEnumerationAppearsOnTheDiagram() {
		// Start of user code AnEnumerationAppearsOnTheDiagram
		assertElementAppearsOnTheDiagram("Enumeration0");
		// End of user code
	}
	/**
	 * Behavior : A primitive type is created in the model
	 */
	public void assertAPrimitiveTypeIsCreatedInTheModel() {
		// Start of user code APrimitiveTypeIsCreatedInTheModel
		// TODO Implement behavior APrimitiveTypeIsCreatedInTheModel
		fail("Behavior APrimitiveTypeIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : The class is deleted from the model
	 */
	public void assertTheClassIsDeletedFromTheModel() {
		// Start of user code TheClassIsDeletedFromTheModel
		// TODO Implement behavior TheClassIsDeletedFromTheModel
		fail("Behavior TheClassIsDeletedFromTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : The class does not appear anymore on the diagram
	 */
	public void assertTheClassDoesNotAppearAnymoreOnTheDiagram() {
		// Start of user code TheClassDoesNotAppearAnymoreOnTheDiagram
		// TODO Implement behavior TheClassDoesNotAppearAnymoreOnTheDiagram
		fail("Behavior TheClassDoesNotAppearAnymoreOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A datatype is created in the model
	 */
	public void assertADatatypeIsCreatedInTheModel() {
		// Start of user code ADatatypeIsCreatedInTheModel
		// TODO Implement behavior ADatatypeIsCreatedInTheModel
		fail("Behavior ADatatypeIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A package is created in the model
	 */
	public void assertAPackageIsCreatedInTheModel() {
		// Start of user code APackageIsCreatedInTheModel
		// TODO Implement behavior APackageIsCreatedInTheModel
		fail("Behavior APackageIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : An interface appears on the diagram
	 */
	public void assertAnInterfaceAppearsOnTheDiagram() {
		// Start of user code AnInterfaceAppearsOnTheDiagram
		// TODO Implement behavior AnInterfaceAppearsOnTheDiagram
		fail("Behavior AnInterfaceAppearsOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A class appears on the diagram
	 */
	public void assertAClassAppearsOnTheDiagram() {
		// Start of user code AClassAppearsOnTheDiagram
		assertElementAppearsOnTheDiagram("Class1");
		// End of user code
	}
	/**
	 * Behavior : A primitive type appears on the diagram
	 */
	public void assertAPrimitiveTypeAppearsOnTheDiagram() {
		// Start of user code APrimitiveTypeAppearsOnTheDiagram
		// TODO Implement behavior APrimitiveTypeAppearsOnTheDiagram
		fail("Behavior APrimitiveTypeAppearsOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : An interface is created in the model
	 */
	public void assertAnInterfaceIsCreatedInTheModel() {
		// Start of user code AnInterfaceIsCreatedInTheModel
		// TODO Implement behavior AnInterfaceIsCreatedInTheModel
		fail("Behavior AnInterfaceIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A package appears on the diagram
	 */
	public void assertAPackageAppearsOnTheDiagram() {
		// Start of user code APackageAppearsOnTheDiagram
		// TODO Implement behavior APackageAppearsOnTheDiagram
		fail("Behavior APackageAppearsOnTheDiagram not implemented");
		// End of user code
	}

// Start of user code AClassDiagramIsOpened private methods
	private void actionCreateAType(String toolName) {
		classDiagram.activateTool(toolName);
		classDiagram.click(10, 150);
		classDiagram.save();
	}
	
	private void assertElementCreatedInUmlModel(String elementName, EClass eClass) {
		List<EObject> createdClasses = EObjects.perType(recorder.attachedObjects()).get(eClass);
		assertEquals(createdClasses.size(),1);
		assertEquals(elementName, ((NamedElement) createdClasses.get(0)).getName());
	}

	// @Behaviour("ElementExistsInTheReferenceClassDiagram")
	private void assertElementAppearsOnTheDiagram(String elementName) {
		assertNotNull(classDiagram.getEditPart(elementName));
	}
// End of user code
}
