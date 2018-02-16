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

import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.swt.graphics.Color;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Confirm deletion tree label provider.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class ConfirmDeletionTreeLabelProvider extends SiriusCommonLabelProvider {

	private Predicate<Object> isDeleted = Predicates.alwaysFalse();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getBackground(Object element) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getForeground(final Object element) {

		Color foreground = null;

		if (isDeleted.apply(element)) {
			foreground = VisualBindingManager.getDefault().getColorFromName("red"); //$NON-NLS-1$
		} else {
			foreground = VisualBindingManager.getDefault().getColorFromName("light_gray"); //$NON-NLS-1$
		}
		return foreground;
	}

	/**
	 * Set the deleted predicate. Predicate to find the deleted elements that will be represented in red in
	 * the tree viewer.
	 *
	 * @param isDeletedPredicate
	 *            Deleted elements predicate
	 * @return Deleted predicate
	 */
	public Predicate<Object> setDeletedPredicate(Predicate<Object> isDeletedPredicate) {
		return isDeleted = isDeletedPredicate != null ? isDeletedPredicate : Predicates.alwaysFalse();
	}
}
