package org.obeonetwork.dsl.uml2.design.tests.ui.manual;

import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.TestAllMappingsContext;

public class AllMappingTests {
	@Rule
	public TestAllMappingsContext context = new TestAllMappingsContext();

	@Test
	public void checkAllMappings() throws Exception {
		context.checkTestElementsExistInSpecification();
		context.checkMappingsExistInTestModel();
	}
}
