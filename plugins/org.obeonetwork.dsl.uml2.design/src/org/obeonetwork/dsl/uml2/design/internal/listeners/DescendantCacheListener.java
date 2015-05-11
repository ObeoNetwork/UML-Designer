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

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.uml2.uml.Element;

/**
 * To listen element updates to clear the descendant cache when the model is updated.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DescendantCacheListener extends ResourceSetListenerImpl {

	@Override
	public boolean isPrecommitOnly() {
		return true;
	}

	@Override
	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
		for (final Iterator<?> iter = event.getNotifications().iterator(); iter.hasNext();) {
			final Notification notification = (Notification)iter.next();
			final Object notifier = notification.getNotifier();

			// An element is updated
			if (notifier instanceof Element) {
				final Element updatedElement = (Element)notifier;

				// Only respond to changes to structural features of the element
				if (notification.getFeature() instanceof EStructuralFeature) {
					notification.getFeature();
					final Session session = SessionManager.INSTANCE.getSession(updatedElement);
					if (session != null) {
						final TransactionalEditingDomain editingDomain = session
								.getTransactionalEditingDomain();
						return new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								// Clear cache
								UmlDesignerSessionManagerListener.getDescendantCache().clear();
							}
						};
					}
				}
			}
		}
		return null;
	}
}
