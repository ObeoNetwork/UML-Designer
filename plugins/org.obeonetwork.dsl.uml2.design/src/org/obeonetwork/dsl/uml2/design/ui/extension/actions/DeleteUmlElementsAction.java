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
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.uml2.uml.Element;
import org.obeonetwork.dsl.uml2.design.Activator;

import fr.obeo.dsl.common.ui.tools.api.editing.EditingDomainService;
import fr.obeo.dsl.viewpoint.DRepresentation;
import fr.obeo.dsl.viewpoint.DSemanticDiagram;
import fr.obeo.dsl.viewpoint.business.api.dialect.DialectManager;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.api.session.SessionManager;

public class DeleteUmlElementsAction extends Action {
	private static ImageDescriptor image;
	static {
		URL url = Activator.getDefault().getBundle().getEntry("icons/deleteModel.gif");
		image = ImageDescriptor.createFromURL(url);
	}
	
	private List<Element> elements;
	
	public DeleteUmlElementsAction(List<Element> elements) {
		this.elements = elements;
	}	

	@Override
	public String getId() {
		return "org.obeonetwork.dsl.uml2.design.ui.extension.actions.DeleteUmlElementAction";
	}

	@Override
	public String getText() {
		return "Delete from model";
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return image;
	}

	@Override
	public void run() {
		TransactionalEditingDomain editingDomain = EditingDomainService.getInstance().getEditingDomainProvider().getEditingDomain();
		RecordingCommand cmd = new RecordingCommand(editingDomain, "Delete from model") {
			protected void doExecute() {
				// Get Session
				Session session = null;
				for (Element elt : elements) {
					if (session == null) {
						session = SessionManager.INSTANCE.getSession(elt);
					}
					elt.destroy();
				}
				// Refresh all diagrams
				if (session != null) {
					Collection<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentations(session);
					for (DRepresentation representation : representations) {
						// We test if the root element still exists
						if (representation instanceof DSemanticDiagram) {
							DSemanticDiagram diagram = (DSemanticDiagram)representation;
							if ((diagram.getTarget() != null) && (DialectManager.INSTANCE.canRefresh(representation))) {
									DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
							}
						}
					}
				}
			}
		};
		editingDomain.getCommandStack().execute(cmd);
	}
}
