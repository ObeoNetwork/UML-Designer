/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.listeners;

import java.util.Collection;
import java.util.EventObject;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.internal.services.LabelServices;

import com.google.common.base.Strings;

/**
 * Listen to Create new child command in model explorer in order to set a default name to the newly created
 * element and select it in the treeviwer.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class CreateNewChildListener implements CommandStackListener {
	/**
	 * Rename last create element if command is a create new child.
	 *
	 * @param event
	 *            event
	 */
	public void commandStackChanged(EventObject event) {
		// Try to select the affected objects.
		final Command mostRecentCommand = ((CommandStack)event.getSource()).getMostRecentCommand();
		final Collection<?> affectedObjects = mostRecentCommand.getAffectedObjects();

		// Check command is a create new Child command
		if (mostRecentCommand != null && mostRecentCommand instanceof CreateChildCommand) {
			final Iterator<?> iterator = affectedObjects.iterator();
			while (iterator.hasNext()) {
				final Object object = iterator.next();
				if (object instanceof NamedElement && Strings.isNullOrEmpty(((NamedElement)object).getName())) {
					// compute default name
					final String defaultName = LabelServices.INSTANCE
							.computeDefaultName((NamedElement)object);
					// set default name
					if (!Strings.isNullOrEmpty(defaultName)) {
						setName((NamedElement)object, defaultName);
					}
				}
			}

			// Add selection to created element
			IViewPart modelExplorerView = null;
			try {
				modelExplorerView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
						.showView("org.eclipse.sirius.ui.tools.views.model.explorer"); //$NON-NLS-1$
				if (modelExplorerView instanceof CommonNavigator) {
					final CommonNavigator modelExplorer = (CommonNavigator)modelExplorerView;
					final Object affectedObject = affectedObjects.iterator().next();
					modelExplorer.getCommonViewer().refresh(((Element)affectedObject).getOwner());
					modelExplorer.selectReveal(new StructuredSelection(affectedObject));
				}
			} catch (final PartInitException e) {
				UMLDesignerPlugin.log(IStatus.ERROR,
						NLS.bind(Messages.UmlModelExplorer_UI_ErrorMsg_NotFound, modelExplorerView), e);
			}
		}
	}

	/**
	 * Set default name to a newly created named element.
	 *
	 * @param element
	 *            named element returned by last create new child command
	 * @param newName
	 *            new name of the element
	 */
	private void setName(NamedElement element, String newName) {
		final EditingDomain editingDomain = SessionManager.INSTANCE.getSession(element)
				.getTransactionalEditingDomain();
		final EAttribute eAttribute = UMLPackage.Literals.NAMED_ELEMENT__NAME;
		final Command setNameCmd = SetCommand.create(editingDomain, element, eAttribute, newName);
		editingDomain.getCommandStack().execute(setNameCmd);
	}
}
