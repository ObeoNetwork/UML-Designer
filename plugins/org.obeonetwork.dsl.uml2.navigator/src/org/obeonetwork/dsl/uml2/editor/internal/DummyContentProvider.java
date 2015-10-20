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

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * A dummy {@link ITreeContentProvider} for uml designer.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class DummyContentProvider implements ITreeContentProvider {

	/**
	 * {@inheritDoc}
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getElements(Object inputElement) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getChildren(Object parentElement) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getParent(Object element) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasChildren(Object element) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {

	}
}
