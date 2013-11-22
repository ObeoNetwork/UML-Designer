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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class Notifications {

	public static final Predicate<Notification> containmentRef = new Predicate<Notification>() {

		public boolean apply(Notification msg) {
			return (msg.getFeature() instanceof EReference)
					&& ((EReference) msg.getFeature()).isContainment();
		}
	};
	public static final Predicate<Notification> attribute = new Predicate<Notification>() {

		public boolean apply(Notification msg) {
			return (msg.getFeature() instanceof EAttribute);
		}
	};
	public static final Predicate<Notification> addition = new Predicate<Notification>() {

		public boolean apply(Notification msg) {
			return msg.getEventType() == Notification.ADD
					|| msg.getEventType() == Notification.ADD_MANY;
		}
	};
	public static final Predicate<Notification> deletion = new Predicate<Notification>() {

		public boolean apply(Notification msg) {
			return msg.getEventType() == Notification.REMOVE
					|| msg.getEventType() == Notification.REMOVE_MANY;
		}
	};
	public static final Predicate<Notification> setunset = new Predicate<Notification>() {

		public boolean apply(Notification msg) {
			return msg.getEventType() == Notification.SET
					|| msg.getEventType() == Notification.UNSET;
		}
	};
	public static  Function<Notification, Iterable<EObject>> toObjectValue = new Function<Notification, Iterable<EObject>>() {

		public Iterable<EObject> apply(Notification from) {
			if (from.getNewValue() instanceof EObject) {
				return Lists.newArrayList((EObject) from.getNewValue());
			} else if (from.getNewValue() instanceof Collection) {
				return (Iterable<EObject>) from.getNewValue();
			}
			return Collections.emptyList();
		}
	};
	
	public static Function<Notification,EObject> toChangedEObject = new Function<Notification, EObject>() {

		@Override
		public EObject apply(Notification arg0) {
			return (EObject) arg0.getNotifier();
		}
	};

}
