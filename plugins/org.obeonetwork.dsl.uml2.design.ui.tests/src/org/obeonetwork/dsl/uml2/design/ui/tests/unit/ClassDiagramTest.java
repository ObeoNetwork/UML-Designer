package org.obeonetwork.dsl.uml2.design.ui.tests.unit;

import junit.framework.TestCase;

import org.obeonetwork.dsl.uml2.design.ui.tests.automation.UMLDesignerBot;

import fr.obeo.dsl.viewpoint.tests.swtbot.support.api.editor.SWTBotDesignerEditor;

public class ClassDiagramTest extends TestCase {

	private UMLDesignerBot bot;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		bot = new UMLDesignerBot();
	}

	public void testDiagramOpening() throws Exception {
		bot.closeWelcome();
		bot.openModelingPerspective();
		SWTBotDesignerEditor editor = bot.importAndOpenTravelAgency();
		editor.directEditType("Hello swtbot world !",
				editor.getEditPart("Travel Agency"));

	}
}
