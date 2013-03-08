/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.editPart.listener;

import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.NodeListener;

public class PropagateFigureListenerAtConnectionFigure implements NodeListener {

	private FigureListener figureListener;

	/**
	 * Constructor.
	 */
	public PropagateFigureListenerAtConnectionFigure(FigureListener figureListener) {
		this.figureListener = figureListener;
	}

	/**
	 * {@inheritDoc}
	 */
	public void removingSourceConnection(ConnectionEditPart connection, int index) {
		IFigure figure = connection.getFigure();
		figure.removeFigureListener(figureListener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removingTargetConnection(ConnectionEditPart connection, int index) {
		IFigure figure = connection.getFigure();
		figure.removeFigureListener(figureListener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void sourceConnectionAdded(ConnectionEditPart connection, int index) {
		IFigure figure = connection.getFigure();
		figure.addFigureListener(figureListener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void targetConnectionAdded(ConnectionEditPart connection, int index) {
		IFigure figure = connection.getFigure();
		figure.addFigureListener(figureListener);
	}
}
