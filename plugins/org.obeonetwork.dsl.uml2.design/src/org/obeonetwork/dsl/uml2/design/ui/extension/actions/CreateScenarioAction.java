/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.ui.extension.actions;

import java.net.URL;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.services.internal.NamedElementServices;

import fr.obeo.dsl.common.ui.tools.api.editing.EditingDomainService;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.api.session.SessionManager;

/**
 * An action to delete the selected UML elements.
 * 
 * @author Mélanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class CreateScenarioAction extends Action {
	/**
	 * The create icon.
	 */
	private static ImageDescriptor image;

	static {
		final URL url = UMLDesignerPlugin.getDefault().getBundle().getEntry("icons/NewUmlModel.gif");
		image = ImageDescriptor.createFromURL(url);
	}

	/**
	 * The list of packages.
	 */
	private List<Package> packages;

	/**
	 * Constructor.
	 * 
	 * @param packages
	 *            the packages on which "Create Scenario" menu is added.
	 */
	public CreateScenarioAction(List<Package> packages) {
		this.packages = packages;
	}

	@Override
	public String getId() {
		return "org.obeonetwork.dsl.uml2.design.ui.extension.actions.CreateScenarioAction";
	}

	@Override
	public String getText() {
		return "Create Scenario";
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return image;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		final TransactionalEditingDomain editingDomain = EditingDomainService.getInstance()
				.getEditingDomainProvider().getEditingDomain();
		final RecordingCommand cmd = new RecordingCommand(editingDomain, "Create Scenario") {
			protected void doExecute() {
				// Get Session
				Session session = null;
				for (Package pkg : packages) {
					if (session == null) {
						session = SessionManager.INSTANCE.getSession(pkg);
					}
					Interaction interaction = UMLFactory.eINSTANCE.createInteraction();
					interaction.setName(NamedElementServices.getNewInteractionName(pkg));
					pkg.getPackagedElements().add(interaction);
				}
			}
		};
		editingDomain.getCommandStack().execute(cmd);
	}
}
