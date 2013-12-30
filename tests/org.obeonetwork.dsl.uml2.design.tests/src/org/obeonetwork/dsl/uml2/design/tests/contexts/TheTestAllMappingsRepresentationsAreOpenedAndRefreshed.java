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

import static org.junit.Assert.assertTrue;

// Start of user code TheTestAllMappingsRepresentationsAreOpenedAndRefreshed imports
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.dsl.uml2.design.tests.Activator;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

// End of user code

/**
 * Context : The test all mappings representations are opened and refreshed
 */
public class TheTestAllMappingsRepresentationsAreOpenedAndRefreshed extends UnactivateUmlUsage {
	// Start of user code TheTestAllMappingsRepresentationsAreOpenedAndRefreshed variables
	protected UMLDesignerBot bot;

	Collection<DiagramElementMapping> mappings;

	private static final String RESOURCE_URI = Activator.PLUGIN_ID
			+ "/resources/testAllMappings/representations.aird";

	private static Set<String> mappingsWhiteList = Sets.newHashSet("CD_ReusedMappingForFeatures",
			"CO_U_ComponentOrPort2Interface", "CO_IR_Interface2ComponentOrPort",
			"CO_C_RequiredInterface2ProvidedInterface", "CO_C_SubProvidedInterface2Port",
			"CO_C_Port2SubProvidedInterface", "CO_C_Port2SubRequiredInterface",
			"CO_C_SubRequiredInterface2Port", "OD_InstanceSlot", "OD_CompositeSlot", "OD_Slot",
			"OD_PrimitiveType", "OD_ClassType", "OD_EnumerationType", "OD_PrimitiveTypeType",
			"OD_InterfaceType", "OD_Class", "OD_Enumeration", "OD_Interface",
			"CS_C_SubProvidedInterface2Port", "CS_C_Port2SubRequiredInterface", "PD_ElementImport",
			"PD_Extension", "PD_Stereotype", "TypeStereotypeDescription", "stereotypeLink", "AD_Pin",
			"SD_Lifeline Execution", "SD_Execution", "SD_Lifeline EOL", "SD_Message", "LifelineComment",
			"LifelineCommentLink");

	Collection<String> specifiedMappings;

	DAnalysis root;

	// End of user code

	@Override
	public void setup() {
		super.setup();
		// Start of user code TheTestAllMappingsRepresentationsAreOpenedAndRefreshed setup
		bot = new UMLDesignerBot();
		ResourceSet rset = new ResourceSetImpl();
		Resource resource = rset.getResource(URI.createPlatformPluginURI(RESOURCE_URI, true), true);
		root = ((DAnalysis)resource.getContents().get(0));
		bot.importAndOpenTestAllMappings();
		specifiedMappings = getSpecifiedMappings();
		// End of user code
	}

	@Override
	public void tearDown() {
		super.tearDown();
		// Start of user code TheTestAllMappingsRepresentationsAreOpenedAndRefreshed tear down
		bot.saveChanges();
		bot.deleteTestAllMappings();
		// End of user code
	}

	/**
	 * Action : I have a look to all the diagrams
	 */
	public void actionIHaveALookToAllTheDiagrams() {
		// Start of user code IHaveALookToAllTheDiagrams
		// No action
		// End of user code
	}

	/**
	 * Behavior : All the graphical elements are still visible
	 */
	public void assertAllTheGraphicalElementsAreStillVisible() {
		// Start of user code AllTheGraphicalElementsAreStillVisible
		// Check that the test elements exist in the specification uml.odesign
		for (DView view : root.getOwnedViews()) {
			for (DRepresentation representation : view.getOwnedRepresentations()) {
				for (DRepresentationElement representationElement : representation
						.getRepresentationElements()) {
					assertTrue("The mapping of the element " + representationElement.getName()
							+ " does not exist anymore in the specification",
							specifiedMappings.contains(representationElement.getMapping().getName()));
				}
			}
		}

		// Then checks that all mappings are still represented in the test model after a diagram refresh.
		Session session = (Session)SessionManager.INSTANCE.getSessions().toArray()[0];

		for (Iterator iterator = specifiedMappings.iterator(); iterator.hasNext();) {
			String mapping = (String)iterator.next();
			if (!mappingsWhiteList.contains(mapping)) {
				boolean found = false;
				// For each mapping defined, check if an element exists in the corresponding test
				// model
				for (DView view : session.getOwnedViews()) {
					for (DRepresentation representation : view.getOwnedRepresentations()) {
						for (DRepresentationElement representationElement : representation
								.getRepresentationElements()) {
							representationElement.getMapping();
							if (mapping.equals(representationElement.getMapping().getName())) {
								found = true;
							}
						}
					}
				}
				assertTrue("No element using the mapping " + mapping
						+ " exists in the example model, the example model must be completed", found);
			}
		}
		// End of user code
	}

	// Start of user code TheTestAllMappingsRepresentationsAreOpenedAndRefreshed private methods
	private Collection<String> getSpecifiedMappings() {
		List<String> parameters = Lists.newArrayList();
		Viewpoint structural = ViewpointRegistry.getInstance().getViewpoint(
				URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/UML Structural Modeling"));
		collectMappingsFromViewpoint(parameters, structural);

		Viewpoint behavior = ViewpointRegistry.getInstance().getViewpoint(
				URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/UML Behavioral Modeling"));
		collectMappingsFromViewpoint(parameters, behavior);
		return parameters;
	}

	private static void collectMappingsFromViewpoint(List<String> parameters, Viewpoint structural) {
		Iterator<DiagramElementMapping> it = Iterators.filter(structural.eAllContents(),
				DiagramElementMapping.class);

		while (it.hasNext()) {
			DiagramElementMapping underTest = it.next();
			parameters.add(underTest.getName());
		}
	}
	// End of user code
}
