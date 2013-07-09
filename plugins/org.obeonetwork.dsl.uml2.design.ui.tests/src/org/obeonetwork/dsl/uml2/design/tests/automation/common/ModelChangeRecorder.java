/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.tests.automation.common;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ModelChangeRecorder extends ResourceSetListenerImpl {

	private List<Notification> changes = Lists.newArrayList();

	@Override
	public boolean isPostcommitOnly() {
		return true;
	}

	/**
	 * The default implementation of this method does nothing.
	 */
	public void resourceSetChanged(ResourceSetChangeEvent event) {
		for (Notification notif : event.getNotifications()) {
			if (!notif.isTouch())
				changes.add(notif);
		}
	}

	public void startRecording(TransactionalEditingDomain domain) {
		changes = Lists.newArrayList();
		domain.addResourceSetListener(this);
	}

	public void stopRecording(TransactionalEditingDomain domain) {
		domain.removeResourceSetListener(this);
	}

	

	public ImmutableListMultimap<EClass, EObject> changedObjectsPerType() {
		Iterable<EObject> changedObjects = Iterables.transform(changes,
				Notifications.toChangedEObject);
		return EObjects.perType(changedObjects);
	}

	public Iterable<EObject> attachedObjects() {
		return Iterables.concat(Iterables.transform(
				Iterables.filter(changes,
						Predicates.and(Notifications.containmentRef, Notifications.addition)),
						Notifications.toObjectValue));
	}

	public Iterable<EObject> detachedObjects() {
		return Iterables.concat(Iterables.transform(
				Iterables.filter(changes,
						Predicates.and(Notifications.containmentRef,Notifications. deletion)),
						Notifications.toObjectValue));
	}

}
