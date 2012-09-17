package org.obeonetwork.dsl.uml2.design.tests.services;

import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import fr.obeo.dsl.viewpoint.description.util.DescriptionSwitch;

@RunWith(value = Parameterized.class)
public class DiagramElementMappingSpecificationTests {

	private static Set<String> directEditWhiteList = Sets.newHashSet("PH_Import", "SD_Lifeline EOL",
			"UCD_Generalization", "UCD_Include", "UCD_Extend", "CD_BrokenAssociation",
			"CD_BrokenAssociationToClasses");

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
		if (underTest instanceof EdgeMapping) {
			if (((EdgeMapping)underTest).getReconnections().size() ==0) {
				fail("EdgeMapping : " + underTest.getName() + " has no reconnect tool.");
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
