package org.obeonetwork.dsl.uml2.design.tests.services.compositestructure;

import org.obeonetwork.dsl.uml2.design.services.CompositeStructureServices;
import org.obeonetwork.dsl.uml2.design.tests.Activator;

public class CompositeStructureServicesTest extends AbstractCompositeStructueTests {

	private CompositeStructureServices cSService;

	/**
	 * The test resource URI.
	 */
	private static final String RESOURCE_URI = Activator.PLUGIN_ID
			+ "/resources/compositeStructure_relatedElements.uml";

	@Override
	public String getRessourceURI() {
		return RESOURCE_URI;
	}

	public void testHandleUnmeaningViews() {

	}
}
