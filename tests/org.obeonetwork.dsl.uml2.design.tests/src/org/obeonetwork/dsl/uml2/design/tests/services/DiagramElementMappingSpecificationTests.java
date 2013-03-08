package org.obeonetwork.dsl.uml2.design.tests.services;

import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Strings;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import fr.obeo.dsl.viewpoint.business.api.componentization.ViewpointRegistry;
import fr.obeo.dsl.viewpoint.description.DiagramElementMapping;
import fr.obeo.dsl.viewpoint.description.EdgeMapping;
import fr.obeo.dsl.viewpoint.description.Viewpoint;
import fr.obeo.dsl.viewpoint.description.style.BasicLabelStyleDescription;
import fr.obeo.dsl.viewpoint.description.tool.ReconnectEdgeDescription;
import fr.obeo.dsl.viewpoint.description.tool.ReconnectionKind;
import fr.obeo.dsl.viewpoint.description.util.DescriptionSwitch;

@RunWith(value = Parameterized.class)
public class DiagramElementMappingSpecificationTests {

	private static Set<String> directEditWhiteList = Sets.newHashSet("PH_Import", "SD_Lifeline EOL",
			"UCD_Generalization", "UCD_Include", "UCD_Extend", "CD_BrokenAssociation",
			"CD_BrokenAssociationToClasses", "SD_Lifeline Execution");

	private static Set<String> reconnectWhiteList = Sets.newHashSet("CD_BrokenAssociationToClasses",
			"CD_NestedClass", "CD_BrokenAssociation", "CD_AssociationClassToAssociation", "PD_Extension",
			"OD_InstanceSlot", "OD_CompositeSlot", "OD_InstanceSlot_Composition", "OD_ClassType",
			"OD_EnumerationType", "OD_PrimitiveTypeType", "OD_InterfaceType", "SD_Message",
			"CS_IR_Port2ProvidedInterface", "CS_IR_SubPort2ProvidedInterface",
			"CS_U_SubPort2RequiredInterface", "CS_U_RequiredInterface2Port",
			"CS_U_RequiredInterface2SubPort", "CS_C_SubProvidedInterface2Port",
			"CS_C_ProvidedInterface2Port", "CS_C_RequiredInterface2Port",
			"CS_C_ProvidedInterface2RequiredInterface", "CS_C_Property2Property",
			"CS_C_Port2SubRequiredInterface", "CS_C_SubProvidedInterface2SubRequiredInterface",
			"stereotypeLink");

	private DiagramElementMapping underTest;

	public DiagramElementMappingSpecificationTests(DiagramElementMapping mapping) {
		this.underTest = mapping;
	}

	@Parameters
	public static Collection<Object[]> data() {
		List<Object[]> parameters = Lists.newArrayList();
		Viewpoint structural = ViewpointRegistry.getInstance().getViewpoint(
				URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/UML Structural Modeling"));
		collectMappingsFromViewpoint(parameters, structural);

		Viewpoint behavior = ViewpointRegistry.getInstance().getViewpoint(
				URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/UML Behavioral Modeling"));
		collectMappingsFromViewpoint(parameters, behavior);
		return parameters;
	}

	private static void collectMappingsFromViewpoint(List<Object[]> parameters, Viewpoint structural) {
		Iterator<DiagramElementMapping> it = Iterators.filter(structural.eAllContents(),
				DiagramElementMapping.class);

		while (it.hasNext()) {
			DiagramElementMapping underTest = it.next();
			parameters.add(new Object[] {underTest});
		}
	}

	@Test
	public void directEdit() {
		if (underTest.getLabelDirectEdit() == null && isDisplayingALabel(underTest)
				&& !directEditWhiteList.contains(underTest.getName())) {
			fail("Mapping : " + underTest.getName() + " has no direct edit and is not in the white list");
		}
	}

	@Test
	public void reconnect() {
		if (underTest instanceof EdgeMapping && !reconnectWhiteList.contains(underTest.getName())) {
			EList<ReconnectEdgeDescription> reconnections = ((EdgeMapping)underTest).getReconnections();
			if (reconnections.size() == 0) {
				fail("EdgeMapping : " + underTest.getName() + " has no reconnect tool.");
			} else if (reconnections.size() == 1
					&& ReconnectionKind.RECONNECT_BOTH_LITERAL != reconnections.get(0).getReconnectionKind()) {

				if (ReconnectionKind.RECONNECT_SOURCE_LITERAL == reconnections.get(0).getReconnectionKind()) {
					fail("EdgeMapping : " + underTest.getName() + " has no target reconnect tool.");
				} else {
					fail("EdgeMapping : " + underTest.getName() + " has no source reconnect tool.");
				}

			} else if (reconnections.size() == 2) {

				if (ReconnectionKind.RECONNECT_SOURCE_LITERAL == reconnections.get(0).getReconnectionKind()
						&& ReconnectionKind.RECONNECT_SOURCE_LITERAL == reconnections.get(1)
								.getReconnectionKind()) {
					fail("EdgeMapping : " + underTest.getName()
							+ " has two source reconnect tools and no taget reconnect tool.");
				} else if (ReconnectionKind.RECONNECT_TARGET_LITERAL == reconnections.get(0)
						.getReconnectionKind()
						&& ReconnectionKind.RECONNECT_TARGET_LITERAL == reconnections.get(1)
								.getReconnectionKind()) {
					fail("EdgeMapping : " + underTest.getName()
							+ " has two taget reconnect tools and no source reconnect tool.");
				}

			} else if (reconnections.size() >= 2) {
				fail("EdgeMapping : " + underTest.getName() + " has more than two reconnect tools.");
			}
		}
	}

	private boolean isDisplayingALabel(DiagramElementMapping underTest) {
		BasicLabelStyleDescription labelDescription = new DescriptionSwitch<BasicLabelStyleDescription>() {

			public BasicLabelStyleDescription caseNodeMapping(
					fr.obeo.dsl.viewpoint.description.NodeMapping object) {
				return object.getStyle();
			};

			public BasicLabelStyleDescription caseEdgeMapping(
					fr.obeo.dsl.viewpoint.description.EdgeMapping object) {
				return object.getStyle().getCenterLabelStyleDescription();
			};

			public BasicLabelStyleDescription caseContainerMapping(
					fr.obeo.dsl.viewpoint.description.ContainerMapping object) {
				return object.getStyle();
			};

		}.doSwitch(underTest);

		return labelDescription != null && !Strings.isNullOrEmpty(labelDescription.getLabelExpression());
	}

}
