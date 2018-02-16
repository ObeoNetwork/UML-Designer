/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.core.internal.listeners;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Type;

/**
 * To listen call action operation pin / operation parameter changes. In the UML specification a call
 * operation action should defined the pins according to the operation parameters but these element is not
 * derived. So a listener was implemented to update pins according to paremeters changes.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class CallActionPinListener extends ResourceSetListenerImpl {

	@Override
	public boolean isPrecommitOnly() {
		return true;
	}

	@Override
	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {

		for (final Iterator<?> iter = event.getNotifications().iterator(); iter.hasNext();) {
			final Notification notification = (Notification)iter.next();
			final Object notifier = notification.getNotifier();

			// A pin is updated
			if (notifier instanceof Pin) {
				final Pin updatedPin = (Pin)notifier;

				// Only respond to changes to structural features of the pin
				if (notification.getFeature() instanceof EStructuralFeature) {
					final EStructuralFeature feature = (EStructuralFeature)notification.getFeature();

					final EObject container = updatedPin.eContainer();
					if (container instanceof CallAction) {
						final CallAction callAction = (CallAction)container;
						if (callAction instanceof CallOperationAction) {
							final CallOperationAction callOperationAction = (CallOperationAction)callAction;
							final Operation operation = callOperationAction.getOperation();
							if (operation == null) {
								return null;
							}
							String pinName = updatedPin.getName();
							Type pinType = updatedPin.getType();
							if ("name".equals(feature.getName())) { //$NON-NLS-1$
								pinName = notification.getOldStringValue();
							} else if ("type".equals(feature.getName())) { //$NON-NLS-1$
								pinType = (Type)notification.getOldValue();
							}
							// Find parameter associated to the modified pin
							// thanks to the name and type
							final org.eclipse.uml2.uml.Parameter paramToUpdate = operation
									.getOwnedParameter(pinName, pinType);

							final Session session = SessionManager.INSTANCE.getSession(updatedPin);
							final TransactionalEditingDomain editingDomain = session
									.getTransactionalEditingDomain();
							return new RecordingCommand(editingDomain) {

								@Override
								protected void doExecute() {
									if (paramToUpdate != null) {
										paramToUpdate.eSet(feature, updatedPin.eGet(feature));
									}
								}
							};
						}
					}
				}

			} else if (notifier instanceof Parameter) {
				// A parameter is updated
				final Parameter updatedParameter = (Parameter)notifier;

				// Only respond to changes to structural features of the
				// parameter
				if (notification.getFeature() instanceof EStructuralFeature) {
					final EStructuralFeature feature = (EStructuralFeature)notification.getFeature();

					final EObject container = updatedParameter.eContainer();
					if (container instanceof Operation) {
						final Operation operation = (Operation)container;
						final Session sess = SessionManager.INSTANCE.getSession(operation);
						if (sess == null) {
							return null;
						}
						final Collection<Setting> inverseReferences = sess.getSemanticCrossReferencer()
								.getInverseReferences(operation);
						for (final Setting setting : inverseReferences) {
							final EObject eObject = setting.getEObject();
							if (eObject instanceof CallOperationAction) {
								final CallOperationAction callOperationAction = (CallOperationAction)eObject;
								String paramName = updatedParameter.getName();
								Type paramType = updatedParameter.getType();
								if ("name".equals(feature.getName())) { //$NON-NLS-1$
									paramName = notification.getOldStringValue();
								} else if ("type".equals(feature.getName())) { //$NON-NLS-1$
									paramType = (Type)notification.getOldValue();
								}
								// Find pins associated to the modified
								// parameter thanks to the name and type
								final Pin inputPinToUpdate = callOperationAction.getArgument(paramName,
										paramType);
								final Pin outputPinToUpdate = callOperationAction.getResult(paramName,
										paramType);

								final Session session = SessionManager.INSTANCE.getSession(updatedParameter);
								final TransactionalEditingDomain editingDomain = session
										.getTransactionalEditingDomain();
								return new RecordingCommand(editingDomain) {
									@Override
									protected void doExecute() {
										if (inputPinToUpdate != null) {
											inputPinToUpdate.eSet(feature, updatedParameter.eGet(feature));
										}
										if (outputPinToUpdate != null) {
											outputPinToUpdate.eSet(feature, updatedParameter.eGet(feature));
										}
									}
								};
							}
						}
					}
				}
			}
		}
		return null;
	}
}
