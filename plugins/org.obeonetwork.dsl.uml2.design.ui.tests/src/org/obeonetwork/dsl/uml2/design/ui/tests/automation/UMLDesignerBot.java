package org.obeonetwork.dsl.uml2.design.ui.tests.automation;

import java.util.List;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPartReference;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import fr.obeo.dsl.viewpoint.tests.swtbot.support.api.editor.SWTBotDesignerEditor;
import fr.obeo.dsl.viewpoint.ui.business.api.dialect.DialectEditor;

public class UMLDesignerBot {

	private SWTWorkbenchBot bot;

	public UMLDesignerBot() {
		bot = new SWTWorkbenchBot();
	}

	public SWTWorkbenchBot workbench() {
		return bot;
	}

	private static final String MODELING_PERSPECTIVE_NAME = "Modeling";

	private static final String OPEN_PERSPECTIVE = "Open Perspective";

	public UMLDesignerBot openModelingPerspective() {
		openPerspective(MODELING_PERSPECTIVE_NAME);
		return this;
	}

	private void openPerspective(final String perspectiveName) {
		String currentShellName = bot.activeShell().getText();

		// If designer perspective is already opened, shell name starts with
		// "Design ..."
		if (!currentShellName.startsWith(MODELING_PERSPECTIVE_NAME)) {
			bot.sleep(1000);
			bot.menu("Window").menu(OPEN_PERSPECTIVE).menu("Other...").click();
			bot.waitUntilWidgetAppears(Conditions
					.shellIsActive(OPEN_PERSPECTIVE));
			final SWTBotShell openPerspectiveShell = bot
					.shell(OPEN_PERSPECTIVE);
			openPerspectiveShell.activate();

			bot.table().select(perspectiveName);
			bot.button("OK").click();
		}
	}

	public UMLDesignerBot closeWelcome() {
		Matcher<IWorkbenchPartReference> matcher = WidgetMatcherFactory
				.withPartName("Welcome");
		List<SWTBotView> views = bot.views(matcher);
		for (SWTBotView swtBotView : views) {
			swtBotView.close();
		}

		return this;
	}

	public SWTBotDesignerEditor importAndOpenTravelAgency() {
		String nameInWizard = "Travel Agency UML";
		bot.menu("File").menu("New").menu("Example...").click();

		bot.waitUntil(Conditions.shellIsActive("New Example"));

		bot.tree().expandNode("Modeler").getNode(nameInWizard).select();
		bot.button("Next >").click();
		bot.button("Finish").click();
		bot.viewByTitle("Model Explorer").bot().tree()
				.expandNode("TravelAgency").select();

		waitForMigrationPopup();

		bot.waitUntil(
				Conditions.waitForEditor(new BaseMatcher<IEditorReference>() {

					public boolean matches(Object item) {
						return item instanceof DialectEditor;
					}

					public void describeTo(Description description) {
						// TODO Auto-generated method stub

					}

				}), 15000);
		return new SWTBotDesignerEditor(bot.editors().get(0).getReference(),
				workbench());
	}

	protected void waitForMigrationPopup() {
		bot.waitUntil(Conditions.shellIsActive("Migration"));
		final SWTBotShell showViewShell = bot.shell("Migration");
		showViewShell.activate();
		showViewShell.close();
	}

	protected SWTBotView openProjectExplorer() {
		bot.menu("Window").menu("Show View").menu("Other...").click();
		bot.waitUntilWidgetAppears(Conditions.shellIsActive("Show View"));
		final SWTBotShell showViewShell = bot.shell("Show View");
		showViewShell.activate();

		bot.tree().expandNode("General").getNode("Project Explorer").select();
		bot.button("OK").click();
		SWTBotView viewByTitle = bot.viewByTitle("Project Explorer");
		viewByTitle.setFocus();
		return viewByTitle;
	}
}
