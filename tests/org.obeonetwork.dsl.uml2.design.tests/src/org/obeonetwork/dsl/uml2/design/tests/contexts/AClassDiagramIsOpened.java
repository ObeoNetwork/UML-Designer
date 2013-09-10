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
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.design.tests.common.EObjects;
import org.obeonetwork.dsl.uml2.design.tests.common.ModelChangeRecorder;
import org.obeonetwork.dsl.uml2.design.tests.contexts.UMLDesignerBot;
import fr.obeo.dsl.viewpoint.tests.swtbot.support.api.editor.SWTBotDesignerEditor;
import fr.obeo.dsl.viewpoint.ui.business.api.session.SessionEditorInput;
// End of user code

/**
 * Context : A class diagram is opened
 */
public class AClassDiagramIsOpened extends UnactivateUmlUsage {
// Start of user code AClassDiagramIsOpened variables
	protected UMLDesignerBot bot;
	SWTBotDesignerEditor classDiagram;

	public ModelChangeRecorder recorder;
// End of user code

	@Override
	public void setup() {
		super.setup();
		// Start of user code AClassDiagramIsOpened setup
		recorder = new ModelChangeRecorder(); 
		bot = new UMLDesignerBot();
		classDiagram = bot.openEntitiesClassDiagram();
		SessionEditorInput input = (SessionEditorInput)classDiagram.getReference().getEditor(false).getEditorInput();
		recorder.startRecording(input.getSession().getTransactionalEditingDomain());
		
		// End of user code
	}

	@Override
	public void tearDown() {
		super.tearDown();
		// Start of user code AClassDiagramIsOpened tear down
		SessionEditorInput input = (SessionEditorInput)classDiagram.getReference().getEditor(false).getEditorInput();
		recorder.stopRecording(input.getSession().getTransactionalEditingDomain());
		bot.saveChanges();
		bot.deleteTravelAgencyProject();
		// End of user code
	}
	/**
	 * Action : I click on an end of a composition in the diagram and I select another class
	 */
	public void actionIClickOnAnEndOfACompositionInTheDiagramAndISelectAnotherClass() {
		// Start of user code IClickOnAnEndOfACompositionInTheDiagramAndISelectAnotherClass
		// TODO Implement action IClickOnAnEndOfACompositionInTheDiagramAndISelectAnotherClass
		fail("Action IClickOnAnEndOfACompositionInTheDiagramAndISelectAnotherClass not implemented");
		// End of user code
	}

	/**
	 * Action : I click on an end of an association in the diagram and I select another class
	 */
	public void actionIClickOnAnEndOfAnAssociationInTheDiagramAndISelectAnotherClass() {
		// Start of user code IClickOnAnEndOfAnAssociationInTheDiagramAndISelectAnotherClass
		// TODO Implement action IClickOnAnEndOfAnAssociationInTheDiagramAndISelectAnotherClass
		fail("Action IClickOnAnEndOfAnAssociationInTheDiagramAndISelectAnotherClass not implemented");
		// End of user code
	}

	/**
	 * Action : I select the operation creation tool from the palette and I select an interface
	 */
	public void actionISelectTheOperationCreationToolFromThePaletteAndISelectAnInterface() {
		// Start of user code ISelectTheOperationCreationToolFromThePaletteAndISelectAnInterface
		// TODO Implement action ISelectTheOperationCreationToolFromThePaletteAndISelectAnInterface
		fail("Action ISelectTheOperationCreationToolFromThePaletteAndISelectAnInterface not implemented");
		// End of user code
	}

	/**
	 * Action : I select the operation creation tool from the palette and I select a class
	 */
	public void actionISelectTheOperationCreationToolFromThePaletteAndISelectAClass() {
		// Start of user code ISelectTheOperationCreationToolFromThePaletteAndISelectAClass
		// TODO Implement action ISelectTheOperationCreationToolFromThePaletteAndISelectAClass
		fail("Action ISelectTheOperationCreationToolFromThePaletteAndISelectAClass not implemented");
		// End of user code
	}

	/**
	 * Action : I select the delete from model tool in toolbar and I click on a literal in a class in the diagram
	 */
	public void actionISelectTheDeleteFromModelToolInToolbarAndIClickOnALiteralInAClassInTheDiagram() {
		// Start of user code ISelectTheDeleteFromModelToolInToolbarAndIClickOnALiteralInAClassInTheDiagram
		// TODO Implement action ISelectTheDeleteFromModelToolInToolbarAndIClickOnALiteralInAClassInTheDiagram
		fail("Action ISelectTheDeleteFromModelToolInToolbarAndIClickOnALiteralInAClassInTheDiagram not implemented");
		// End of user code
	}

	/**
	 * Action : I select the delete from model tool in toolbar and I click on a property in a class in the diagram
	 */
	public void actionISelectTheDeleteFromModelToolInToolbarAndIClickOnAPropertyInAClassInTheDiagram() {
		// Start of user code ISelectTheDeleteFromModelToolInToolbarAndIClickOnAPropertyInAClassInTheDiagram
		// TODO Implement action ISelectTheDeleteFromModelToolInToolbarAndIClickOnAPropertyInAClassInTheDiagram
		fail("Action ISelectTheDeleteFromModelToolInToolbarAndIClickOnAPropertyInAClassInTheDiagram not implemented");
		// End of user code
	}

	/**
	 * Action : I select the datatype creation tool from the palette and I click on the diagram
	 */
	public void actionISelectTheDatatypeCreationToolFromThePaletteAndIClickOnTheDiagram() {
		// Start of user code ISelectTheDatatypeCreationToolFromThePaletteAndIClickOnTheDiagram
		actionCreateAType("DataType");
		// End of user code
	}

	/**
	 * Action : I select the typed property creation tool from the palette and I select a datatype and I select a type
	 */
	public void actionISelectTheTypedPropertyCreationToolFromThePaletteAndISelectADatatypeAndISelectAType() {
		// Start of user code ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectADatatypeAndISelectAType
		// TODO Implement action ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectADatatypeAndISelectAType
		fail("Action ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectADatatypeAndISelectAType not implemented");
		// End of user code
	}

	/**
	 * Action : I select the operation creation tool from the palette and I select a primitive type
	 */
	public void actionISelectTheOperationCreationToolFromThePaletteAndISelectAPrimitiveType() {
		// Start of user code ISelectTheOperationCreationToolFromThePaletteAndISelectAPrimitiveType
		// TODO Implement action ISelectTheOperationCreationToolFromThePaletteAndISelectAPrimitiveType
		fail("Action ISelectTheOperationCreationToolFromThePaletteAndISelectAPrimitiveType not implemented");
		// End of user code
	}

	/**
	 * Action : I select an association and I set it as navigable in bi direction
	 */
	public void actionISelectAnAssociationAndISetItAsNavigableInBiDirection() {
		// Start of user code ISelectAnAssociationAndISetItAsNavigableInBiDirection
		// TODO Implement action ISelectAnAssociationAndISetItAsNavigableInBiDirection
		fail("Action ISelectAnAssociationAndISetItAsNavigableInBiDirection not implemented");
		// End of user code
	}

	/**
	 * Action : I select the typed property creation tool from the palette and I select an interface and I select a type
	 */
	public void actionISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAnInterfaceAndISelectAType() {
		// Start of user code ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAnInterfaceAndISelectAType
		// TODO Implement action ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAnInterfaceAndISelectAType
		fail("Action ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAnInterfaceAndISelectAType not implemented");
		// End of user code
	}

	/**
	 * Action : I select the add tool from the palette and I click on a
	 */
	public void actionISelectTheAddToolFromThePaletteAndIClickOnA(String iSelectTheAddToolFromThePaletteAndIClickOnA0,String iSelectTheAddToolFromThePaletteAndIClickOnA1) {
		// Start of user code ISelectTheAddToolFromThePaletteAndIClickOnA
		// TODO Implement action ISelectTheAddToolFromThePaletteAndIClickOnA
		fail("Action ISelectTheAddToolFromThePaletteAndIClickOnA not implemented");
		// End of user code
	}

	/**
	 * Action : I select the interface realization creation tool from the palette and I select a source class and a target interface
	 */
	public void actionISelectTheInterfaceRealizationCreationToolFromThePaletteAndISelectASourceClassAndATargetInterface() {
		// Start of user code ISelectTheInterfaceRealizationCreationToolFromThePaletteAndISelectASourceClassAndATargetInterface
		// TODO Implement action ISelectTheInterfaceRealizationCreationToolFromThePaletteAndISelectASourceClassAndATargetInterface
		fail("Action ISelectTheInterfaceRealizationCreationToolFromThePaletteAndISelectASourceClassAndATargetInterface not implemented");
		// End of user code
	}

	/**
	 * Action : I select the aggregation creation tool from the palette and I select a source class and a target class
	 */
	public void actionISelectTheAggregationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass() {
		// Start of user code ISelectTheAggregationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		// TODO Implement action ISelectTheAggregationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		fail("Action ISelectTheAggregationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass not implemented");
		// End of user code
	}

	/**
	 * Action : I click on an end of an aggregation in the diagram and I select another class
	 */
	public void actionIClickOnAnEndOfAnAggregationInTheDiagramAndISelectAnotherClass() {
		// Start of user code IClickOnAnEndOfAnAggregationInTheDiagramAndISelectAnotherClass
		// TODO Implement action IClickOnAnEndOfAnAggregationInTheDiagramAndISelectAnotherClass
		fail("Action IClickOnAnEndOfAnAggregationInTheDiagramAndISelectAnotherClass not implemented");
		// End of user code
	}

	/**
	 * Action : I select the delete from model tool in toolbar and I click on an association in a class in the diagram
	 */
	public void actionISelectTheDeleteFromModelToolInToolbarAndIClickOnAnAssociationInAClassInTheDiagram() {
		// Start of user code ISelectTheDeleteFromModelToolInToolbarAndIClickOnAnAssociationInAClassInTheDiagram
		// TODO Implement action ISelectTheDeleteFromModelToolInToolbarAndIClickOnAnAssociationInAClassInTheDiagram
		fail("Action ISelectTheDeleteFromModelToolInToolbarAndIClickOnAnAssociationInAClassInTheDiagram not implemented");
		// End of user code
	}

	/**
	 * Action : I click on an end of an interface realization in the diagram and I select another class
	 */
	public void actionIClickOnAnEndOfAnInterfaceRealizationInTheDiagramAndISelectAnotherClass() {
		// Start of user code IClickOnAnEndOfAnInterfaceRealizationInTheDiagramAndISelectAnotherClass
		// TODO Implement action IClickOnAnEndOfAnInterfaceRealizationInTheDiagramAndISelectAnotherClass
		fail("Action IClickOnAnEndOfAnInterfaceRealizationInTheDiagramAndISelectAnotherClass not implemented");
		// End of user code
	}

	/**
	 * Action : I select the usage creation tool from the palette and I select a source class and a target class
	 */
	public void actionISelectTheUsageCreationToolFromThePaletteAndISelectASourceClassAndATargetClass() {
		// Start of user code ISelectTheUsageCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		// TODO Implement action ISelectTheUsageCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		fail("Action ISelectTheUsageCreationToolFromThePaletteAndISelectASourceClassAndATargetClass not implemented");
		// End of user code
	}

	/**
	 * Action : I select the dependency creation tool from the palette and I select a source class and a target class
	 */
	public void actionISelectTheDependencyCreationToolFromThePaletteAndISelectASourceClassAndATargetClass() {
		// Start of user code ISelectTheDependencyCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		// TODO Implement action ISelectTheDependencyCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		fail("Action ISelectTheDependencyCreationToolFromThePaletteAndISelectASourceClassAndATargetClass not implemented");
		// End of user code
	}

	/**
	 * Action : I select the property creation tool from the palette and I select a datatype
	 */
	public void actionISelectThePropertyCreationToolFromThePaletteAndISelectADatatype() {
		// Start of user code ISelectThePropertyCreationToolFromThePaletteAndISelectADatatype
		// TODO Implement action ISelectThePropertyCreationToolFromThePaletteAndISelectADatatype
		fail("Action ISelectThePropertyCreationToolFromThePaletteAndISelectADatatype not implemented");
		// End of user code
	}

	/**
	 * Action : I select the operation creation tool from the palette and I select an enumeration
	 */
	public void actionISelectTheOperationCreationToolFromThePaletteAndISelectAnEnumeration() {
		// Start of user code ISelectTheOperationCreationToolFromThePaletteAndISelectAnEnumeration
		// TODO Implement action ISelectTheOperationCreationToolFromThePaletteAndISelectAnEnumeration
		fail("Action ISelectTheOperationCreationToolFromThePaletteAndISelectAnEnumeration not implemented");
		// End of user code
	}

	/**
	 * Action : I select a composition and I set it as navigable in bi direction
	 */
	public void actionISelectACompositionAndISetItAsNavigableInBiDirection() {
		// Start of user code ISelectACompositionAndISetItAsNavigableInBiDirection
		// TODO Implement action ISelectACompositionAndISetItAsNavigableInBiDirection
		fail("Action ISelectACompositionAndISetItAsNavigableInBiDirection not implemented");
		// End of user code
	}

	/**
	 * Action : I select the property creation tool from the palette and I select a class
	 */
	public void actionISelectThePropertyCreationToolFromThePaletteAndISelectAClass() {
		// Start of user code ISelectThePropertyCreationToolFromThePaletteAndISelectAClass
		// TODO Implement action ISelectThePropertyCreationToolFromThePaletteAndISelectAClass
		fail("Action ISelectThePropertyCreationToolFromThePaletteAndISelectAClass not implemented");
		// End of user code
	}

	/**
	 * Action : I select the association creation tool from the palette and I select a source class and a target class
	 */
	public void actionISelectTheAssociationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass() {
		// Start of user code ISelectTheAssociationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		// TODO Implement action ISelectTheAssociationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		fail("Action ISelectTheAssociationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass not implemented");
		// End of user code
	}

	/**
	 * Action : I click on an end of a generalization in the diagram and I select another class
	 */
	public void actionIClickOnAnEndOfAGeneralizationInTheDiagramAndISelectAnotherClass() {
		// Start of user code IClickOnAnEndOfAGeneralizationInTheDiagramAndISelectAnotherClass
		// TODO Implement action IClickOnAnEndOfAGeneralizationInTheDiagramAndISelectAnotherClass
		fail("Action IClickOnAnEndOfAGeneralizationInTheDiagramAndISelectAnotherClass not implemented");
		// End of user code
	}

	/**
	 * Action : I select the remove tool from the palette and I click on a class in the class diagram
	 */
	public void actionISelectTheRemoveToolFromThePaletteAndIClickOnAClassInTheClassDiagram() {
		// Start of user code ISelectTheRemoveToolFromThePaletteAndIClickOnAClassInTheClassDiagram
		// TODO Implement action ISelectTheRemoveToolFromThePaletteAndIClickOnAClassInTheClassDiagram
		fail("Action ISelectTheRemoveToolFromThePaletteAndIClickOnAClassInTheClassDiagram not implemented");
		// End of user code
	}

	/**
	 * Action : I select the property creation tool from the palette and I select a primitive type
	 */
	public void actionISelectThePropertyCreationToolFromThePaletteAndISelectAPrimitiveType() {
		// Start of user code ISelectThePropertyCreationToolFromThePaletteAndISelectAPrimitiveType
		// TODO Implement action ISelectThePropertyCreationToolFromThePaletteAndISelectAPrimitiveType
		fail("Action ISelectThePropertyCreationToolFromThePaletteAndISelectAPrimitiveType not implemented");
		// End of user code
	}

	/**
	 * Action : I select the operation creation tool from the palette and I select a datatype
	 */
	public void actionISelectTheOperationCreationToolFromThePaletteAndISelectADatatype() {
		// Start of user code ISelectTheOperationCreationToolFromThePaletteAndISelectADatatype
		// TODO Implement action ISelectTheOperationCreationToolFromThePaletteAndISelectADatatype
		fail("Action ISelectTheOperationCreationToolFromThePaletteAndISelectADatatype not implemented");
		// End of user code
	}

	/**
	 * Action : I select the generalization creation tool from the palette and I select a source class and a target class
	 */
	public void actionISelectTheGeneralizationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass() {
		// Start of user code ISelectTheGeneralizationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		// TODO Implement action ISelectTheGeneralizationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		fail("Action ISelectTheGeneralizationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass not implemented");
		// End of user code
	}

	/**
	 * Action : I select the class creation tool from the tooltip bar when the mouse hovers over the diagram
	 */
	public void actionISelectTheClassCreationToolFromTheTooltipBarWhenTheMouseHoversOverTheDiagram() {
		// Start of user code ISelectTheClassCreationToolFromTheTooltipBarWhenTheMouseHoversOverTheDiagram
		// TODO Implement action ISelectTheClassCreationToolFromTheTooltipBarWhenTheMouseHoversOverTheDiagram
		fail("Action ISelectTheClassCreationToolFromTheTooltipBarWhenTheMouseHoversOverTheDiagram not implemented");
		// End of user code
	}

	/**
	 * Action : I select the primitive type creation tool from the palette and I click on the diagram
	 */
	public void actionISelectThePrimitiveTypeCreationToolFromThePaletteAndIClickOnTheDiagram() {
		// Start of user code ISelectThePrimitiveTypeCreationToolFromThePaletteAndIClickOnTheDiagram
		actionCreateAType("PrimitiveType");
		// End of user code
	}

	/**
	 * Action : I select the association class creation tool from the palette and I select a source class and a target class
	 */
	public void actionISelectTheAssociationClassCreationToolFromThePaletteAndISelectASourceClassAndATargetClass() {
		// Start of user code ISelectTheAssociationClassCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		// TODO Implement action ISelectTheAssociationClassCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		fail("Action ISelectTheAssociationClassCreationToolFromThePaletteAndISelectASourceClassAndATargetClass not implemented");
		// End of user code
	}

	/**
	 * Action : I click on an end of an association class in the diagram and I select another class
	 */
	public void actionIClickOnAnEndOfAnAssociationClassInTheDiagramAndISelectAnotherClass() {
		// Start of user code IClickOnAnEndOfAnAssociationClassInTheDiagramAndISelectAnotherClass
		// TODO Implement action IClickOnAnEndOfAnAssociationClassInTheDiagramAndISelectAnotherClass
		fail("Action IClickOnAnEndOfAnAssociationClassInTheDiagramAndISelectAnotherClass not implemented");
		// End of user code
	}

	/**
	 * Action : I select the literal creation tool from the palette and I select an enumeration
	 */
	public void actionISelectTheLiteralCreationToolFromThePaletteAndISelectAnEnumeration() {
		// Start of user code ISelectTheLiteralCreationToolFromThePaletteAndISelectAnEnumeration
		// TODO Implement action ISelectTheLiteralCreationToolFromThePaletteAndISelectAnEnumeration
		fail("Action ISelectTheLiteralCreationToolFromThePaletteAndISelectAnEnumeration not implemented");
		// End of user code
	}

	/**
	 * Action : I select the package creation tool from the palette and I click on the diagram
	 */
	public void actionISelectThePackageCreationToolFromThePaletteAndIClickOnTheDiagram() {
		// Start of user code ISelectThePackageCreationToolFromThePaletteAndIClickOnTheDiagram
		actionCreateAType("Package");
		// End of user code
	}

	/**
	 * Action : I select the remove tool from the palette and I click on a package in the class diagram
	 */
	public void actionISelectTheRemoveToolFromThePaletteAndIClickOnAPackageInTheClassDiagram() {
		// Start of user code ISelectTheRemoveToolFromThePaletteAndIClickOnAPackageInTheClassDiagram
		// TODO Implement action ISelectTheRemoveToolFromThePaletteAndIClickOnAPackageInTheClassDiagram
		fail("Action ISelectTheRemoveToolFromThePaletteAndIClickOnAPackageInTheClassDiagram not implemented");
		// End of user code
	}

	/**
	 * Action : I select the class creation tool from the palette and I select in the diagram a package as container
	 */
	public void actionISelectTheClassCreationToolFromThePaletteAndISelectInTheDiagramAPackageAsContainer() {
		// Start of user code ISelectTheClassCreationToolFromThePaletteAndISelectInTheDiagramAPackageAsContainer
		// TODO Implement action ISelectTheClassCreationToolFromThePaletteAndISelectInTheDiagramAPackageAsContainer
		fail("Action ISelectTheClassCreationToolFromThePaletteAndISelectInTheDiagramAPackageAsContainer not implemented");
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
	 * Action : I select the property creation tool from the palette and I select an interface
	 */
	public void actionISelectThePropertyCreationToolFromThePaletteAndISelectAnInterface() {
		// Start of user code ISelectThePropertyCreationToolFromThePaletteAndISelectAnInterface
		// TODO Implement action ISelectThePropertyCreationToolFromThePaletteAndISelectAnInterface
		fail("Action ISelectThePropertyCreationToolFromThePaletteAndISelectAnInterface not implemented");
		// End of user code
	}

	/**
	 * Action : I select the typed property creation tool from the palette and I select a primitive type and I select a type
	 */
	public void actionISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAPrimitiveTypeAndISelectAType() {
		// Start of user code ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAPrimitiveTypeAndISelectAType
		// TODO Implement action ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAPrimitiveTypeAndISelectAType
		fail("Action ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAPrimitiveTypeAndISelectAType not implemented");
		// End of user code
	}

	/**
	 * Action : I select the classifier creation tool from the palette and I select a source class and a target class
	 */
	public void actionISelectTheClassifierCreationToolFromThePaletteAndISelectASourceClassAndATargetClass() {
		// Start of user code ISelectTheClassifierCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		// TODO Implement action ISelectTheClassifierCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		fail("Action ISelectTheClassifierCreationToolFromThePaletteAndISelectASourceClassAndATargetClass not implemented");
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
	 * Action : I select the package creation tool from the palette and I select in the diagram a package as container
	 */
	public void actionISelectThePackageCreationToolFromThePaletteAndISelectInTheDiagramAPackageAsContainer() {
		// Start of user code ISelectThePackageCreationToolFromThePaletteAndISelectInTheDiagramAPackageAsContainer
		// TODO Implement action ISelectThePackageCreationToolFromThePaletteAndISelectInTheDiagramAPackageAsContainer
		fail("Action ISelectThePackageCreationToolFromThePaletteAndISelectInTheDiagramAPackageAsContainer not implemented");
		// End of user code
	}

	/**
	 * Action : I select a aggregation and I set it as navigable in bi direction
	 */
	public void actionISelectAAggregationAndISetItAsNavigableInBiDirection() {
		// Start of user code ISelectAAggregationAndISetItAsNavigableInBiDirection
		// TODO Implement action ISelectAAggregationAndISetItAsNavigableInBiDirection
		fail("Action ISelectAAggregationAndISetItAsNavigableInBiDirection not implemented");
		// End of user code
	}

	/**
	 * Action : I select the composition creation tool from the palette and I select a source class and a target class
	 */
	public void actionISelectTheCompositionCreationToolFromThePaletteAndISelectASourceClassAndATargetClass() {
		// Start of user code ISelectTheCompositionCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		// TODO Implement action ISelectTheCompositionCreationToolFromThePaletteAndISelectASourceClassAndATargetClass
		fail("Action ISelectTheCompositionCreationToolFromThePaletteAndISelectASourceClassAndATargetClass not implemented");
		// End of user code
	}

	/**
	 * Action : I select the typed property creation tool from the palette and I select a class and I select a type
	 */
	public void actionISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAClassAndISelectAType() {
		// Start of user code ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAClassAndISelectAType
		// TODO Implement action ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAClassAndISelectAType
		fail("Action ISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAClassAndISelectAType not implemented");
		// End of user code
	}

	/**
	 * Action : I select the interface creation tool from the palette and I click on the diagram
	 */
	public void actionISelectTheInterfaceCreationToolFromThePaletteAndIClickOnTheDiagram() {
		// Start of user code ISelectTheInterfaceCreationToolFromThePaletteAndIClickOnTheDiagram
		actionCreateAType("Interface");
		// End of user code
	}

	/**
	 * Action : I select the delete from model tool in toolbar and I click on a class in the diagram
	 */
	public void actionISelectTheDeleteFromModelToolInToolbarAndIClickOnAClassInTheDiagram() {
		// Start of user code ISelectTheDeleteFromModelToolInToolbarAndIClickOnAClassInTheDiagram
		// TODO Implement action ISelectTheDeleteFromModelToolInToolbarAndIClickOnAClassInTheDiagram
		fail("Action ISelectTheDeleteFromModelToolInToolbarAndIClickOnAClassInTheDiagram not implemented");
		// End of user code
	}

	/**
	 * Behavior : An interface is created in the model
	 */
	public void assertAnInterfaceIsCreatedInTheModel() {
		// Start of user code AnInterfaceIsCreatedInTheModel
		assertElementCreatedInUmlModel("Interface1",UMLPackage.eINSTANCE.getInterface());		
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
	 * Behavior : The interface realization is connected to the new class on the diagram
	 */
	public void assertTheInterfaceRealizationIsConnectedToTheNewClassOnTheDiagram() {
		// Start of user code TheInterfaceRealizationIsConnectedToTheNewClassOnTheDiagram
		// TODO Implement behavior TheInterfaceRealizationIsConnectedToTheNewClassOnTheDiagram
		fail("Behavior TheInterfaceRealizationIsConnectedToTheNewClassOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A usage is created in the model
	 */
	public void assertAUsageIsCreatedInTheModel() {
		// Start of user code AUsageIsCreatedInTheModel
		// TODO Implement behavior AUsageIsCreatedInTheModel
		fail("Behavior AUsageIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : An operation appears in the primitive type on the diagram
	 */
	public void assertAnOperationAppearsInThePrimitiveTypeOnTheDiagram() {
		// Start of user code AnOperationAppearsInThePrimitiveTypeOnTheDiagram
		// TODO Implement behavior AnOperationAppearsInThePrimitiveTypeOnTheDiagram
		fail("Behavior AnOperationAppearsInThePrimitiveTypeOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The package exists in the model
	 */
	public void assertThePackageExistsInTheModel() {
		// Start of user code ThePackageExistsInTheModel
		// TODO Implement behavior ThePackageExistsInTheModel
		fail("Behavior ThePackageExistsInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : The property types equals the selected type
	 */
	public void assertThePropertyTypesEqualsTheSelectedType() {
		// Start of user code ThePropertyTypesEqualsTheSelectedType
		// TODO Implement behavior ThePropertyTypesEqualsTheSelectedType
		fail("Behavior ThePropertyTypesEqualsTheSelectedType not implemented");
		// End of user code
	}
	/**
	 * Behavior : A property appears in the datatype on the diagram
	 */
	public void assertAPropertyAppearsInTheDatatypeOnTheDiagram() {
		// Start of user code APropertyAppearsInTheDatatypeOnTheDiagram
		// TODO Implement behavior APropertyAppearsInTheDatatypeOnTheDiagram
		fail("Behavior APropertyAppearsInTheDatatypeOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The association class end is set to the new class in the model
	 */
	public void assertTheAssociationClassEndIsSetToTheNewClassInTheModel() {
		// Start of user code TheAssociationClassEndIsSetToTheNewClassInTheModel
		// TODO Implement behavior TheAssociationClassEndIsSetToTheNewClassInTheModel
		fail("Behavior TheAssociationClassEndIsSetToTheNewClassInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A class appears in the parent package on the diagram
	 */
	public void assertAClassAppearsInTheParentPackageOnTheDiagram() {
		// Start of user code AClassAppearsInTheParentPackageOnTheDiagram
		// TODO Implement behavior AClassAppearsInTheParentPackageOnTheDiagram
		fail("Behavior AClassAppearsInTheParentPackageOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : An association is created in the model
	 */
	public void assertAnAssociationIsCreatedInTheModel() {
		// Start of user code AnAssociationIsCreatedInTheModel
		// TODO Implement behavior AnAssociationIsCreatedInTheModel
		fail("Behavior AnAssociationIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A package is created in the model
	 */
	public void assertAPackageIsCreatedInTheModel() {
		// Start of user code APackageIsCreatedInTheModel
		assertElementCreatedInUmlModel("Package7",UMLPackage.eINSTANCE.getPackage());		
		// End of user code
	}
	/**
	 * Behavior : A property is created in the interface in the model
	 */
	public void assertAPropertyIsCreatedInTheInterfaceInTheModel() {
		// Start of user code APropertyIsCreatedInTheInterfaceInTheModel
		// TODO Implement behavior APropertyIsCreatedInTheInterfaceInTheModel
		fail("Behavior APropertyIsCreatedInTheInterfaceInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A dependency appears between the classes on the diagram
	 */
	public void assertADependencyAppearsBetweenTheClassesOnTheDiagram() {
		// Start of user code ADependencyAppearsBetweenTheClassesOnTheDiagram
		// TODO Implement behavior ADependencyAppearsBetweenTheClassesOnTheDiagram
		fail("Behavior ADependencyAppearsBetweenTheClassesOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : An association class appears between the classes on the diagram
	 */
	public void assertAnAssociationClassAppearsBetweenTheClassesOnTheDiagram() {
		// Start of user code AnAssociationClassAppearsBetweenTheClassesOnTheDiagram
		// TODO Implement behavior AnAssociationClassAppearsBetweenTheClassesOnTheDiagram
		fail("Behavior AnAssociationClassAppearsBetweenTheClassesOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : An aggregation appears between the classes on the diagram
	 */
	public void assertAnAggregationAppearsBetweenTheClassesOnTheDiagram() {
		// Start of user code AnAggregationAppearsBetweenTheClassesOnTheDiagram
		// TODO Implement behavior AnAggregationAppearsBetweenTheClassesOnTheDiagram
		fail("Behavior AnAggregationAppearsBetweenTheClassesOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A classifier is created in the model
	 */
	public void assertAClassifierIsCreatedInTheModel() {
		// Start of user code AClassifierIsCreatedInTheModel
		// TODO Implement behavior AClassifierIsCreatedInTheModel
		fail("Behavior AClassifierIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : The class does not appear anymore on the diagram
	 */
	public void assertTheClassDoesNotAppearAnymoreOnTheDiagram() {
		// Start of user code TheClassDoesNotAppearAnymoreOnTheDiagram
		actionCreateAType("Enumeration");
		// End of user code
	}
	/**
	 * Behavior : A datatype appears on the diagram
	 */
	public void assertADatatypeAppearsOnTheDiagram() {
		// Start of user code ADatatypeAppearsOnTheDiagram
		assertElementAppearsOnTheDiagram("DataType0");
		// End of user code
	}
	/**
	 * Behavior : An interface appears on the diagram
	 */
	public void assertAnInterfaceAppearsOnTheDiagram() {
		// Start of user code AnInterfaceAppearsOnTheDiagram
		assertElementAppearsOnTheDiagram("Interface1");
		// End of user code
	}
	/**
	 * Behavior : A package is created in the parent package
	 */
	public void assertAPackageIsCreatedInTheParentPackage() {
		// Start of user code APackageIsCreatedInTheParentPackage
		// TODO Implement behavior APackageIsCreatedInTheParentPackage
		fail("Behavior APackageIsCreatedInTheParentPackage not implemented");
		// End of user code
	}
	/**
	 * Behavior : The association is deleted from the model
	 */
	public void assertTheAssociationIsDeletedFromTheModel() {
		// Start of user code TheAssociationIsDeletedFromTheModel
		// TODO Implement behavior TheAssociationIsDeletedFromTheModel
		fail("Behavior TheAssociationIsDeletedFromTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : An operation appears in the datatype on the diagram
	 */
	public void assertAnOperationAppearsInTheDatatypeOnTheDiagram() {
		// Start of user code AnOperationAppearsInTheDatatypeOnTheDiagram
		// TODO Implement behavior AnOperationAppearsInTheDatatypeOnTheDiagram
		fail("Behavior AnOperationAppearsInTheDatatypeOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A composition is created in the model
	 */
	public void assertACompositionIsCreatedInTheModel() {
		// Start of user code ACompositionIsCreatedInTheModel
		// TODO Implement behavior ACompositionIsCreatedInTheModel
		fail("Behavior ACompositionIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A datatype is created in the model
	 */
	public void assertADatatypeIsCreatedInTheModel() {
		// Start of user code ADatatypeIsCreatedInTheModel
		assertElementCreatedInUmlModel("DataType0",UMLPackage.eINSTANCE.getDataType());	
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
	 * Behavior : An operation appears in the class on the diagram
	 */
	public void assertAnOperationAppearsInTheClassOnTheDiagram() {
		// Start of user code AnOperationAppearsInTheClassOnTheDiagram
		// TODO Implement behavior AnOperationAppearsInTheClassOnTheDiagram
		fail("Behavior AnOperationAppearsInTheClassOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The composition is connected to the new class on the diagram
	 */
	public void assertTheCompositionIsConnectedToTheNewClassOnTheDiagram() {
		// Start of user code TheCompositionIsConnectedToTheNewClassOnTheDiagram
		// TODO Implement behavior TheCompositionIsConnectedToTheNewClassOnTheDiagram
		fail("Behavior TheCompositionIsConnectedToTheNewClassOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : Arrows are visible on the composition ends on the diagram
	 */
	public void assertArrowsAreVisibleOnTheCompositionEndsOnTheDiagram() {
		// Start of user code ArrowsAreVisibleOnTheCompositionEndsOnTheDiagram
		// TODO Implement behavior ArrowsAreVisibleOnTheCompositionEndsOnTheDiagram
		fail("Behavior ArrowsAreVisibleOnTheCompositionEndsOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The composition end is set to the new class in the model
	 */
	public void assertTheCompositionEndIsSetToTheNewClassInTheModel() {
		// Start of user code TheCompositionEndIsSetToTheNewClassInTheModel
		// TODO Implement behavior TheCompositionEndIsSetToTheNewClassInTheModel
		fail("Behavior TheCompositionEndIsSetToTheNewClassInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : An operation appears in the interface on the diagram
	 */
	public void assertAnOperationAppearsInTheInterfaceOnTheDiagram() {
		// Start of user code AnOperationAppearsInTheInterfaceOnTheDiagram
		// TODO Implement behavior AnOperationAppearsInTheInterfaceOnTheDiagram
		fail("Behavior AnOperationAppearsInTheInterfaceOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : An aggregation is created in the model
	 */
	public void assertAnAggregationIsCreatedInTheModel() {
		// Start of user code AnAggregationIsCreatedInTheModel
		// TODO Implement behavior AnAggregationIsCreatedInTheModel
		fail("Behavior AnAggregationIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A property is created in the primitive type in the model
	 */
	public void assertAPropertyIsCreatedInThePrimitiveTypeInTheModel() {
		// Start of user code APropertyIsCreatedInThePrimitiveTypeInTheModel
		// TODO Implement behavior APropertyIsCreatedInThePrimitiveTypeInTheModel
		fail("Behavior APropertyIsCreatedInThePrimitiveTypeInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A generalization appears between the classes on the diagram
	 */
	public void assertAGeneralizationAppearsBetweenTheClassesOnTheDiagram() {
		// Start of user code AGeneralizationAppearsBetweenTheClassesOnTheDiagram
		// TODO Implement behavior AGeneralizationAppearsBetweenTheClassesOnTheDiagram
		fail("Behavior AGeneralizationAppearsBetweenTheClassesOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The interface realization end is set to the new class in the model
	 */
	public void assertTheInterfaceRealizationEndIsSetToTheNewClassInTheModel() {
		// Start of user code TheInterfaceRealizationEndIsSetToTheNewClassInTheModel
		// TODO Implement behavior TheInterfaceRealizationEndIsSetToTheNewClassInTheModel
		fail("Behavior TheInterfaceRealizationEndIsSetToTheNewClassInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : The aggregation is connected to the new class on the diagram
	 */
	public void assertTheAggregationIsConnectedToTheNewClassOnTheDiagram() {
		// Start of user code TheAggregationIsConnectedToTheNewClassOnTheDiagram
		// TODO Implement behavior TheAggregationIsConnectedToTheNewClassOnTheDiagram
		fail("Behavior TheAggregationIsConnectedToTheNewClassOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The association is connected to the new class on the diagram
	 */
	public void assertTheAssociationIsConnectedToTheNewClassOnTheDiagram() {
		// Start of user code TheAssociationIsConnectedToTheNewClassOnTheDiagram
		// TODO Implement behavior TheAssociationIsConnectedToTheNewClassOnTheDiagram
		fail("Behavior TheAssociationIsConnectedToTheNewClassOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : An association appears between the classes on the diagram
	 */
	public void assertAnAssociationAppearsBetweenTheClassesOnTheDiagram() {
		// Start of user code AnAssociationAppearsBetweenTheClassesOnTheDiagram
		// TODO Implement behavior AnAssociationAppearsBetweenTheClassesOnTheDiagram
		fail("Behavior AnAssociationAppearsBetweenTheClassesOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A property is created in the datatype in the model
	 */
	public void assertAPropertyIsCreatedInTheDatatypeInTheModel() {
		// Start of user code APropertyIsCreatedInTheDatatypeInTheModel
		// TODO Implement behavior APropertyIsCreatedInTheDatatypeInTheModel
		fail("Behavior APropertyIsCreatedInTheDatatypeInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : The generalization end is set to the new class in the model
	 */
	public void assertTheGeneralizationEndIsSetToTheNewClassInTheModel() {
		// Start of user code TheGeneralizationEndIsSetToTheNewClassInTheModel
		// TODO Implement behavior TheGeneralizationEndIsSetToTheNewClassInTheModel
		fail("Behavior TheGeneralizationEndIsSetToTheNewClassInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A primitive type is created in the model
	 */
	public void assertAPrimitiveTypeIsCreatedInTheModel() {
		// Start of user code APrimitiveTypeIsCreatedInTheModel
		assertElementCreatedInUmlModel("PrimitiveType0",UMLPackage.eINSTANCE.getPrimitiveType());	
		// End of user code
	}
	/**
	 * Behavior : A new element appears in the
	 */
	public void assertANewElementAppearsInThe(String aNewElementAppearsInThe0,String aNewElementAppearsInThe1) {
		// Start of user code ANewElementAppearsInThe
		// TODO Implement behavior ANewElementAppearsInThe
		fail("Behavior ANewElementAppearsInThe not implemented");
		// End of user code
	}
	/**
	 * Behavior : The association does not appear anymore on the diagram
	 */
	public void assertTheAssociationDoesNotAppearAnymoreOnTheDiagram() {
		// Start of user code TheAssociationDoesNotAppearAnymoreOnTheDiagram
		// TODO Implement behavior TheAssociationDoesNotAppearAnymoreOnTheDiagram
		fail("Behavior TheAssociationDoesNotAppearAnymoreOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : An operation is created in the class in the model
	 */
	public void assertAnOperationIsCreatedInTheClassInTheModel() {
		// Start of user code AnOperationIsCreatedInTheClassInTheModel
		// TODO Implement behavior AnOperationIsCreatedInTheClassInTheModel
		fail("Behavior AnOperationIsCreatedInTheClassInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : The property does not appear anymore on the diagram
	 */
	public void assertThePropertyDoesNotAppearAnymoreOnTheDiagram() {
		// Start of user code ThePropertyDoesNotAppearAnymoreOnTheDiagram
		// TODO Implement behavior ThePropertyDoesNotAppearAnymoreOnTheDiagram
		fail("Behavior ThePropertyDoesNotAppearAnymoreOnTheDiagram not implemented");
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
	 * Behavior : A package appears on the diagram
	 */
	public void assertAPackageAppearsOnTheDiagram() {
		// Start of user code APackageAppearsOnTheDiagram
		assertElementAppearsOnTheDiagram("Package7");
		// End of user code
	}
	/**
	 * Behavior : A dependency is created in the model
	 */
	public void assertADependencyIsCreatedInTheModel() {
		// Start of user code ADependencyIsCreatedInTheModel
		// TODO Implement behavior ADependencyIsCreatedInTheModel
		fail("Behavior ADependencyIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : An interface realization is created in the model
	 */
	public void assertAnInterfaceRealizationIsCreatedInTheModel() {
		// Start of user code AnInterfaceRealizationIsCreatedInTheModel
		// TODO Implement behavior AnInterfaceRealizationIsCreatedInTheModel
		fail("Behavior AnInterfaceRealizationIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : An operation appears in the enumeration on the diagram
	 */
	public void assertAnOperationAppearsInTheEnumerationOnTheDiagram() {
		// Start of user code AnOperationAppearsInTheEnumerationOnTheDiagram
		// TODO Implement behavior AnOperationAppearsInTheEnumerationOnTheDiagram
		fail("Behavior AnOperationAppearsInTheEnumerationOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The package does not appear anymore on the diagram
	 */
	public void assertThePackageDoesNotAppearAnymoreOnTheDiagram() {
		// Start of user code ThePackageDoesNotAppearAnymoreOnTheDiagram
		// TODO Implement behavior ThePackageDoesNotAppearAnymoreOnTheDiagram
		fail("Behavior ThePackageDoesNotAppearAnymoreOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A primitive type appears on the diagram
	 */
	public void assertAPrimitiveTypeAppearsOnTheDiagram() {
		// Start of user code APrimitiveTypeAppearsOnTheDiagram
		assertElementCreatedInUmlModel("PrimitiveType0",UMLPackage.eINSTANCE.getPrimitiveType());
		// End of user code
	}
	/**
	 * Behavior : An operation is created in the interface in the model
	 */
	public void assertAnOperationIsCreatedInTheInterfaceInTheModel() {
		// Start of user code AnOperationIsCreatedInTheInterfaceInTheModel
		// TODO Implement behavior AnOperationIsCreatedInTheInterfaceInTheModel
		fail("Behavior AnOperationIsCreatedInTheInterfaceInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : Arrows are visible on the aggregation ends on the diagram
	 */
	public void assertArrowsAreVisibleOnTheAggregationEndsOnTheDiagram() {
		// Start of user code ArrowsAreVisibleOnTheAggregationEndsOnTheDiagram
		// TODO Implement behavior ArrowsAreVisibleOnTheAggregationEndsOnTheDiagram
		fail("Behavior ArrowsAreVisibleOnTheAggregationEndsOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : Arrows are visible on the association ends on the diagram
	 */
	public void assertArrowsAreVisibleOnTheAssociationEndsOnTheDiagram() {
		// Start of user code ArrowsAreVisibleOnTheAssociationEndsOnTheDiagram
		// TODO Implement behavior ArrowsAreVisibleOnTheAssociationEndsOnTheDiagram
		fail("Behavior ArrowsAreVisibleOnTheAssociationEndsOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : An association class is created in the model
	 */
	public void assertAnAssociationClassIsCreatedInTheModel() {
		// Start of user code AnAssociationClassIsCreatedInTheModel
		// TODO Implement behavior AnAssociationClassIsCreatedInTheModel
		fail("Behavior AnAssociationClassIsCreatedInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : The class exists in the model
	 */
	public void assertTheClassExistsInTheModel() {
		// Start of user code TheClassExistsInTheModel
		// TODO Implement behavior TheClassExistsInTheModel
		fail("Behavior TheClassExistsInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A literal appears in the enumeration on the diagram
	 */
	public void assertALiteralAppearsInTheEnumerationOnTheDiagram() {
		// Start of user code ALiteralAppearsInTheEnumerationOnTheDiagram
		// TODO Implement behavior ALiteralAppearsInTheEnumerationOnTheDiagram
		fail("Behavior ALiteralAppearsInTheEnumerationOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : An interface realization appears between the class and the interface on the diagram
	 */
	public void assertAnInterfaceRealizationAppearsBetweenTheClassAndTheInterfaceOnTheDiagram() {
		// Start of user code AnInterfaceRealizationAppearsBetweenTheClassAndTheInterfaceOnTheDiagram
		// TODO Implement behavior AnInterfaceRealizationAppearsBetweenTheClassAndTheInterfaceOnTheDiagram
		fail("Behavior AnInterfaceRealizationAppearsBetweenTheClassAndTheInterfaceOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A literal is created in the enumeration in the model
	 */
	public void assertALiteralIsCreatedInTheEnumerationInTheModel() {
		// Start of user code ALiteralIsCreatedInTheEnumerationInTheModel
		// TODO Implement behavior ALiteralIsCreatedInTheEnumerationInTheModel
		fail("Behavior ALiteralIsCreatedInTheEnumerationInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A property appears in the class on the diagram
	 */
	public void assertAPropertyAppearsInTheClassOnTheDiagram() {
		// Start of user code APropertyAppearsInTheClassOnTheDiagram
		// TODO Implement behavior APropertyAppearsInTheClassOnTheDiagram
		fail("Behavior APropertyAppearsInTheClassOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The association end is set to the new class in the model
	 */
	public void assertTheAssociationEndIsSetToTheNewClassInTheModel() {
		// Start of user code TheAssociationEndIsSetToTheNewClassInTheModel
		// TODO Implement behavior TheAssociationEndIsSetToTheNewClassInTheModel
		fail("Behavior TheAssociationEndIsSetToTheNewClassInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A composition appears between the classes on the diagram
	 */
	public void assertACompositionAppearsBetweenTheClassesOnTheDiagram() {
		// Start of user code ACompositionAppearsBetweenTheClassesOnTheDiagram
		// TODO Implement behavior ACompositionAppearsBetweenTheClassesOnTheDiagram
		fail("Behavior ACompositionAppearsBetweenTheClassesOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A package appears in the parent package on the diagram
	 */
	public void assertAPackageAppearsInTheParentPackageOnTheDiagram() {
		// Start of user code APackageAppearsInTheParentPackageOnTheDiagram
		// TODO Implement behavior APackageAppearsInTheParentPackageOnTheDiagram
		fail("Behavior APackageAppearsInTheParentPackageOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The association class is connected to the new class on the diagram
	 */
	public void assertTheAssociationClassIsConnectedToTheNewClassOnTheDiagram() {
		// Start of user code TheAssociationClassIsConnectedToTheNewClassOnTheDiagram
		// TODO Implement behavior TheAssociationClassIsConnectedToTheNewClassOnTheDiagram
		fail("Behavior TheAssociationClassIsConnectedToTheNewClassOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The literal does not appear anymore on the diagram
	 */
	public void assertTheLiteralDoesNotAppearAnymoreOnTheDiagram() {
		// Start of user code TheLiteralDoesNotAppearAnymoreOnTheDiagram
		// TODO Implement behavior TheLiteralDoesNotAppearAnymoreOnTheDiagram
		fail("Behavior TheLiteralDoesNotAppearAnymoreOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The property is deleted from the model
	 */
	public void assertThePropertyIsDeletedFromTheModel() {
		// Start of user code ThePropertyIsDeletedFromTheModel
		// TODO Implement behavior ThePropertyIsDeletedFromTheModel
		fail("Behavior ThePropertyIsDeletedFromTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : The aggregation end is set to the new class in the model
	 */
	public void assertTheAggregationEndIsSetToTheNewClassInTheModel() {
		// Start of user code TheAggregationEndIsSetToTheNewClassInTheModel
		// TODO Implement behavior TheAggregationEndIsSetToTheNewClassInTheModel
		fail("Behavior TheAggregationEndIsSetToTheNewClassInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : An operation is created in the enumeration in the model
	 */
	public void assertAnOperationIsCreatedInTheEnumerationInTheModel() {
		// Start of user code AnOperationIsCreatedInTheEnumerationInTheModel
		// TODO Implement behavior AnOperationIsCreatedInTheEnumerationInTheModel
		fail("Behavior AnOperationIsCreatedInTheEnumerationInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A classifier appears between the classes on the diagram
	 */
	public void assertAClassifierAppearsBetweenTheClassesOnTheDiagram() {
		// Start of user code AClassifierAppearsBetweenTheClassesOnTheDiagram
		// TODO Implement behavior AClassifierAppearsBetweenTheClassesOnTheDiagram
		fail("Behavior AClassifierAppearsBetweenTheClassesOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A class is created in the parent package
	 */
	public void assertAClassIsCreatedInTheParentPackage() {
		// Start of user code AClassIsCreatedInTheParentPackage
		// TODO Implement behavior AClassIsCreatedInTheParentPackage
		fail("Behavior AClassIsCreatedInTheParentPackage not implemented");
		// End of user code
	}
	/**
	 * Behavior : The association is bi navigable in the model
	 */
	public void assertTheAssociationIsBiNavigableInTheModel() {
		// Start of user code TheAssociationIsBiNavigableInTheModel
		// TODO Implement behavior TheAssociationIsBiNavigableInTheModel
		fail("Behavior TheAssociationIsBiNavigableInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : The generalization is connected to the new class on the diagram
	 */
	public void assertTheGeneralizationIsConnectedToTheNewClassOnTheDiagram() {
		// Start of user code TheGeneralizationIsConnectedToTheNewClassOnTheDiagram
		// TODO Implement behavior TheGeneralizationIsConnectedToTheNewClassOnTheDiagram
		fail("Behavior TheGeneralizationIsConnectedToTheNewClassOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : The aggregation is bi navigable in the model
	 */
	public void assertTheAggregationIsBiNavigableInTheModel() {
		// Start of user code TheAggregationIsBiNavigableInTheModel
		// TODO Implement behavior TheAggregationIsBiNavigableInTheModel
		fail("Behavior TheAggregationIsBiNavigableInTheModel not implemented");
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
	 * Behavior : A usage appears between the classes on the diagram
	 */
	public void assertAUsageAppearsBetweenTheClassesOnTheDiagram() {
		// Start of user code AUsageAppearsBetweenTheClassesOnTheDiagram
		// TODO Implement behavior AUsageAppearsBetweenTheClassesOnTheDiagram
		fail("Behavior AUsageAppearsBetweenTheClassesOnTheDiagram not implemented");
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
	 * Behavior : The composition is bi navigable in the model
	 */
	public void assertTheCompositionIsBiNavigableInTheModel() {
		// Start of user code TheCompositionIsBiNavigableInTheModel
		// TODO Implement behavior TheCompositionIsBiNavigableInTheModel
		fail("Behavior TheCompositionIsBiNavigableInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : The literal is deleted from the model
	 */
	public void assertTheLiteralIsDeletedFromTheModel() {
		// Start of user code TheLiteralIsDeletedFromTheModel
		// TODO Implement behavior TheLiteralIsDeletedFromTheModel
		fail("Behavior TheLiteralIsDeletedFromTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A property appears in the primitive type on the diagram
	 */
	public void assertAPropertyAppearsInThePrimitiveTypeOnTheDiagram() {
		// Start of user code APropertyAppearsInThePrimitiveTypeOnTheDiagram
		// TODO Implement behavior APropertyAppearsInThePrimitiveTypeOnTheDiagram
		fail("Behavior APropertyAppearsInThePrimitiveTypeOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A property appears in the interface on the diagram
	 */
	public void assertAPropertyAppearsInTheInterfaceOnTheDiagram() {
		// Start of user code APropertyAppearsInTheInterfaceOnTheDiagram
		// TODO Implement behavior APropertyAppearsInTheInterfaceOnTheDiagram
		fail("Behavior APropertyAppearsInTheInterfaceOnTheDiagram not implemented");
		// End of user code
	}
	/**
	 * Behavior : A property is created in the class in the model
	 */
	public void assertAPropertyIsCreatedInTheClassInTheModel() {
		// Start of user code APropertyIsCreatedInTheClassInTheModel
		// TODO Implement behavior APropertyIsCreatedInTheClassInTheModel
		fail("Behavior APropertyIsCreatedInTheClassInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : An operation is created in the primitive type in the model
	 */
	public void assertAnOperationIsCreatedInThePrimitiveTypeInTheModel() {
		// Start of user code AnOperationIsCreatedInThePrimitiveTypeInTheModel
		// TODO Implement behavior AnOperationIsCreatedInThePrimitiveTypeInTheModel
		fail("Behavior AnOperationIsCreatedInThePrimitiveTypeInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : An operation is created in the datatype in the model
	 */
	public void assertAnOperationIsCreatedInTheDatatypeInTheModel() {
		// Start of user code AnOperationIsCreatedInTheDatatypeInTheModel
		// TODO Implement behavior AnOperationIsCreatedInTheDatatypeInTheModel
		fail("Behavior AnOperationIsCreatedInTheDatatypeInTheModel not implemented");
		// End of user code
	}
	/**
	 * Behavior : A generalization is created in the model
	 */
	public void assertAGeneralizationIsCreatedInTheModel() {
		// Start of user code AGeneralizationIsCreatedInTheModel
		// TODO Implement behavior AGeneralizationIsCreatedInTheModel
		fail("Behavior AGeneralizationIsCreatedInTheModel not implemented");
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
