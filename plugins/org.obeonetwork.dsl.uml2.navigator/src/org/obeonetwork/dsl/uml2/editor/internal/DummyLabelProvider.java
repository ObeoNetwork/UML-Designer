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
package org.obeonetwork.dsl.uml2.editor.internal;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * A dummy {@link ILabelProvider} for uml designer.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class DummyLabelProvider implements ILabelProvider {

	/**
	 * {@inheritDoc}
	 */
	public void addListener(ILabelProviderListener listener) {
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeListener(ILabelProviderListener listener) {
	}

	/**
	 * {@inheritDoc}
	 */
	public Image getImage(Object element) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getText(Object element) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
	}

}
