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

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.internal.EditorReference;
import org.eclipse.uml2.uml.Model;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import fr.obeo.dsl.viewpoint.tests.swtbot.support.api.editor.SWTBotDesignerEditor;

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
		String currentShellName = bot.activePerspective().getLabel();

		// If designer perspective is already opened, shell name starts with
		// "Design ..."
		if (!currentShellName.startsWith(MODELING_PERSPECTIVE_NAME)) {
			bot.sleep(1000);
			bot.menu("Window").menu(OPEN_PERSPECTIVE).menu("Other...").click();
			bot.waitUntilWidgetAppears(Conditions.shellIsActive(OPEN_PERSPECTIVE));
			final SWTBotShell openPerspectiveShell = bot.shell(OPEN_PERSPECTIVE);
			openPerspectiveShell.activate();

			bot.table().select(perspectiveName);
			bot.button("OK").click();
		}
	}

	public UMLDesignerBot closeWelcome() {
		Matcher<IWorkbenchPartReference> matcher = WidgetMatcherFactory.withPartName("Welcome");
		List<SWTBotView> views = bot.views(matcher);
		for (SWTBotView swtBotView : views) {
			swtBotView.close();
		}

		return this;
	}

	public SWTBotDesignerEditor importAndOpenTravelAgency() {
		bot.resetWorkbench();
		String nameInWizard = "Travel Agency UML";

		bot.menu("File").menu("New").menu("Example...").click();
		bot.waitUntil(Conditions.shellIsActive("New Example"));

		bot.shell("New Example").bot().tree().expandNode("UML").getNode(nameInWizard).select();
		bot.button("Next >").click();
		bot.button("Finish").click();
		closeWelcome();
		bot.viewByTitle("Model Explorer").bot().tree().expandNode("TravelAgency").expandNode("agency.uml");

		waitForProgressInformationComplete();

		waitForEditor("Package Hierarchy");
		return new SWTBotDesignerEditor(bot.activeEditor().getReference(), workbench());
	}

	private void waitForEditor(final String editorName) {
		bot.waitUntil(Conditions.waitForEditor(new BaseMatcher<IEditorReference>() {

			public boolean matches(Object item) {
				System.out.println(editorName + " " + ((EditorReference)item).getName());
				return editorName.equals(((EditorReference)item).getName());
			}

			public void describeTo(Description description) {
			}

		}), 15000);
	}

	public SWTBotDesignerEditor openEntitiesClassDiagram() {

		importAndOpenTravelAgency();

		bot.viewByTitle("Model Explorer").bot().tree()
				.expandNode("TravelAgency", "agency.uml", "<Model> Travel Agency", "Entities").doubleClick();

		waitForEditor("Entities");

		return new SWTBotDesignerEditor(bot.activeEditor().getReference(), workbench());
	}

	public void importAndOpenTestAllMappings() {
		bot.resetWorkbench();
		String nameInWizard = "Test All Mappings";

		bot.menu("File").menu("New").menu("Example...").click();
		bot.waitUntil(Conditions.shellIsActive("New Example"));

		bot.shell("New Example").bot().tree().expandNode("UML").getNode(nameInWizard).select();
		bot.button("Next >").click();
		bot.button("Finish").click();
		closeWelcome();
		bot.viewByTitle("Model Explorer").bot().tree().expandNode("TestAllMappings").expandNode("model.uml");

		waitForProgressInformationComplete();

		// Open and refresh package hierarchy automatically
		waitForEditor("Test Package Hierarchy");
		new SWTBotDesignerEditor(bot.activeEditor().getReference(), workbench()).refresh();
		// Open class diagram
		bot.viewByTitle("Model Explorer").bot().tree()
				.expandNode("TestAllMappings", "model.uml", "<Model> Test", "Test Class Diagram")
				.doubleClick();
		waitForEditor("Test Class Diagram");
		new SWTBotDesignerEditor(bot.activeEditor().getReference(), workbench()).refresh();
		// Open class diagram archetype
		bot.viewByTitle("Model Explorer").bot().tree()
				.expandNode("TestAllMappings", "model.uml", "<Model> Test", "Test Class Diagram Archetypes")
				.doubleClick();
		waitForEditor("Test Class Diagram Archetypes");
		new SWTBotDesignerEditor(bot.activeEditor().getReference(), workbench()).refresh();
		// Open class diagram edges
		bot.viewByTitle("Model Explorer").bot().tree()
				.expandNode("TestAllMappings", "model.uml", "<Model> Test", "Test Class Diagram Edges")
				.doubleClick();
		waitForEditor("Test Class Diagram Edges");
		new SWTBotDesignerEditor(bot.activeEditor().getReference(), workbench()).refresh();
		// Open component diagram
		bot.viewByTitle("Model Explorer").bot().tree()
				.expandNode("TestAllMappings", "model.uml", "<Model> Test", "Test Component Diagram")
				.doubleClick();
		waitForEditor("Test Component Diagram");
		new SWTBotDesignerEditor(bot.activeEditor().getReference(), workbench()).refresh();
		// Open composite structure diagram
		bot.viewByTitle("Model Explorer")
				.bot()
				.tree()
				.expandNode("TestAllMappings", "model.uml", "<Model> Test",
						"Test Composite Structure Diagram").doubleClick();
		waitForEditor("Test Composite Structure Diagram");
		new SWTBotDesignerEditor(bot.activeEditor().getReference(), workbench()).refresh();
		// Open deployment diagram
		bot.viewByTitle("Model Explorer").bot().tree()
				.expandNode("TestAllMappings", "model.uml", "<Model> Test", "Test Deployment Diagram")
				.doubleClick();
		waitForEditor("Test Deployment Diagram");
		new SWTBotDesignerEditor(bot.activeEditor().getReference(), workbench()).refresh();
		// Open use case diagram
		bot.viewByTitle("Model Explorer").bot().tree()
				.expandNode("TestAllMappings", "model.uml", "<Model> Test", "Test Use Case Diagram")
				.doubleClick();
		waitForEditor("Test Use Case Diagram");
		new SWTBotDesignerEditor(bot.activeEditor().getReference(), workbench()).refresh();
	}

	public void createNewUMLProject() {
		bot.menu("File").menu("New").menu("UML Project").click();
		bot.text().setText("TestProject");
		bot.button("Next");
	}

	protected void waitForProgressInformationComplete() {
		bot.waitUntil(Conditions.shellIsActive("Progress Information"));
		final SWTBotShell showViewShell = bot.shell("Progress Information");
		bot.waitUntil(Conditions.shellCloses(showViewShell), 25000);
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

	public void saveChanges() {
		for (SWTBotEditor editor : bot.editors()) {
			editor.save();
		}
	}

	public void deleteTravelAgencyProject() {
		for (SWTBotEditor editor : bot.editors()) {
			editor.close();
		}
		bot.viewByTitle("Model Explorer").bot().tree().expandNode("TravelAgency").contextMenu("Delete")
				.click();
		bot.waitUntil(Conditions.shellIsActive("Delete Resources"), 25000);
		bot.button("OK").click();
	}

	public void deleteTestAllMappings() {
		for (SWTBotEditor editor : bot.editors()) {
			editor.close();
		}
		bot.viewByTitle("Model Explorer").bot().tree().expandNode("TestAllMappings").contextMenu("Delete")
				.click();
		bot.waitUntil(Conditions.shellIsActive("Delete Resources"), 25000);
		bot.button("OK").click();
	}

	public void deleteCatalogClass() {
		SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();
		bot.viewByTitle("Model Explorer")
				.bot()
				.tree()
				.expandNode("TravelAgency", "agency.uml", "<Model> Travel Agency", "<Package> Components",
						"<Component> Agency  Offers", "<Class> Catalog").select();
		ContextMenuHelper.clickContextMenu(tree, "Delete");
	}

	public SWTBotDesignerEditor importTravelAgencyProject() {
		return importAndOpenTravelAgency();
	}

	public void createAClassDiagram() {
		SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();
		bot.viewByTitle("Model Explorer").bot().tree()
				.expandNode("TravelAgency", "agency.uml", "<Model> Travel Agency").select();
		ContextMenuHelper.clickContextMenu(tree, "New Representation", "Travel Agency Class Diagram");

		bot.button("OK").click();
	}

	public SWTBotEditor getActiveEditor() {
		return bot.activeEditor();
	}

	public Model getTravelAgencyModel() {
		String sessionResourceUri = "TravelAgency/representations.aird";
		Session session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(
				sessionResourceUri, true));
		Resource semanticResource = (Resource)session.getSemanticResources().toArray()[0];
		return ((Model)semanticResource.getContents().get(0));
	}
}
