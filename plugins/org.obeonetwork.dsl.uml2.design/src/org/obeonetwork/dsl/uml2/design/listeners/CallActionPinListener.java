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
package org.obeonetwork.dsl.uml2.design.listeners;

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

	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {

		for (Iterator iter = event.getNotifications().iterator(); iter.hasNext();) {
			Notification notification = (Notification)iter.next();
			Object notifier = notification.getNotifier();

			// A pin is updated
			if (notifier instanceof Pin) {
				final Pin updatedPin = (Pin)notifier;

				// Only respond to changes to structural features of the pin
				if (notification.getFeature() instanceof EStructuralFeature) {
					final EStructuralFeature feature = (EStructuralFeature)notification.getFeature();

					EObject container = updatedPin.eContainer();
					if (container instanceof CallAction) {
						final CallAction callAction = (CallAction)container;
						if (callAction instanceof CallOperationAction) {
							CallOperationAction callOperationAction = (CallOperationAction)callAction;
							Operation operation = callOperationAction.getOperation();
							String pinName = updatedPin.getName();
							Type pinType = updatedPin.getType();
							if ("name".equals(feature.getName())) {
								pinName = notification.getOldStringValue();
							} else if ("type".equals(feature.getName())) {
								pinType = (Type)notification.getOldValue();
							}
							// Find parameter associated to the modified pin thanks to the name and type
							final org.eclipse.uml2.uml.Parameter paramToUpdate = operation.getOwnedParameter(
									pinName, pinType);

							Session session = SessionManager.INSTANCE.getSession(updatedPin);
							TransactionalEditingDomain editingDomain = session
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

				// Only respond to changes to structural features of the parameter
				if (notification.getFeature() instanceof EStructuralFeature) {
					final EStructuralFeature feature = (EStructuralFeature)notification.getFeature();

					EObject container = updatedParameter.eContainer();
					if (container instanceof Operation) {
						final Operation operation = (Operation)container;
						Session sess = SessionManager.INSTANCE.getSession(operation);
						Collection<Setting> inverseReferences = sess.getSemanticCrossReferencer()
								.getInverseReferences(operation);
						for (Setting setting : inverseReferences) {
							EObject eObject = setting.getEObject();
							if (eObject instanceof CallOperationAction) {
								CallOperationAction callOperationAction = (CallOperationAction)eObject;
								String paramName = updatedParameter.getName();
								Type paramType = updatedParameter.getType();
								if ("name".equals(feature.getName())) {
									paramName = notification.getOldStringValue();
								} else if ("type".equals(feature.getName())) {
									paramType = (Type)notification.getOldValue();
								}
								// Find pins associated to the modified parameter thanks to the name and type
								final Pin inputPinToUpdate = callOperationAction.getArgument(paramName,
										paramType);
								final Pin outputPinToUpdate = callOperationAction.getResult(paramName,
										paramType);

								Session session = SessionManager.INSTANCE.getSession(updatedParameter);
								TransactionalEditingDomain editingDomain = session
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

	public boolean isPrecommitOnly() {
		return true;
	}
}
