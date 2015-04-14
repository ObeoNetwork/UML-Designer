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

package org.obeonetwork.dsl.uml2.design.api.wizards;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

/**
 * Filtered tree content provider used by the mosel semantic selaction dialog tree viewer. Derived from @see
 * FilteredTreeContentProvider.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ModelFilteredTreeContentProvider extends AdapterFactoryContentProvider {
	private final Predicate<Object> predicate;

	/**
	 * Constructor.
	 *
	 * @param adapterFactory
	 *            the adapter factory to use.
	 * @param predicate
	 *            the predicate used to filter elements. It should return <code>true</code> for elements which
	 *            must be removed.
	 */
	public ModelFilteredTreeContentProvider(AdapterFactory adapterFactory, Predicate<Object> predicate) {
		super(adapterFactory);
		this.predicate = predicate;
	}

	private Object[] filter(Object[] unfiltered) {
		final List<Object> filtered = Lists.newArrayList();
		for (final Object o : unfiltered) {
			if (predicate.apply(o)) {
				filtered.add(o);
			}
		}
		return filtered.toArray(new Object[filtered.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getChildren(Object object) {
		final Object[] unfiltered = super.getChildren(object);
		return filter(unfiltered);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getElements(Object object) {
		if (object instanceof Collection) {
			return filter(((Collection)object).toArray());
		}
		final Object[] unfiltered = super.getElements(object);
		return filter(unfiltered);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasChildren(Object object) {
		final Object[] children = getChildren(object);
		return children != null && children.length != 0;
	}
}
