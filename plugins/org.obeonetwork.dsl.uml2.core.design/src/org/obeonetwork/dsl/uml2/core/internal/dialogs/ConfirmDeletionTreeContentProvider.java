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
package org.obeonetwork.dsl.uml2.core.internal.dialogs;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;

import com.google.common.collect.Lists;

/**
 * Confirm deletion tree content provider. The content provider re-attached the removed elements in the tree
 * to show what will be deleted by the operation.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ConfirmDeletionTreeContentProvider extends AdapterFactoryContentProvider {

	Collection<Object> notifications;

	/**
	 * Constructor.
	 *
	 * @param adapterFactory
	 *            Adapter factory
	 * @param notifications
	 *            Deletion notifications
	 */
	public ConfirmDeletionTreeContentProvider(AdapterFactory adapterFactory,
			Collection<Object> notifications) {
		super(adapterFactory);
		this.notifications = notifications;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		final List<Object> children = Lists.newArrayList();
		if (parentElement instanceof EObject) {
			for (final Object child : super.getChildren(parentElement)) {
				children.add(child);
			}

			for (final Object object : notifications) {
				if (object instanceof Notification
						&& parentElement.equals(((Notification)object).getNotifier())) {
					children.add(((Notification)object).getOldValue());
				}
			}
		}

		if (parentElement instanceof Notification) {
			final Notification notification = (Notification)parentElement;
			if (notification.getOldValue() instanceof EObject) {
				final EObject value = (EObject)notification.getOldValue();
				return super.getChildren(value);
			}
		}
		return children.toArray();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getElements(Object object) {
		if (object instanceof Collection) {
			return ((Collection)object).toArray();
		}
		return super.getElements(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasChildren(Object element) {
		final Object[] children = getChildren(element);
		if (children != null && children.length != 0) {
			return true;
		}
		if (element instanceof Notification) {
			final Notification notification = (Notification)element;
			if (notification.getOldValue() instanceof EObject) {
				final EObject value = (EObject)notification.getOldValue();
				return super.hasChildren(value);
			}
		}
		for (final Object object : notifications) {
			if (element instanceof Notification) {
				if (object.equals(((Notification)object).getNotifier())) {
					return true;
				}
			}
		}
		return false;
	}
}
