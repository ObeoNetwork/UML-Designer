/*******************************************************************************
 * Copyright (c) 2009, 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.figure;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

/**
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class CenteredAnchor extends AbstractConnectionAnchor {

	/**
	 * Constructor.
	 * 
	 * @param owner
	 *            the figure that this anchor is associated with..
	 */
	public CenteredAnchor(IFigure owner) {
		super(owner/* , new PrecisionPoint(0, 0) */);
	}

	/**
	 * {@inheritDoc}
	 */
	public Point getLocation(Point reference) {
		// return new Point(0, 0);
		Point location = getReferencePoint();
		return location;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Point getReferencePoint() {
		// return new Point(0, 0);
		return super.getReferencePoint();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @Override protected Rectangle getBox() { // TODO Auto-generated method stub return new Rectangle(new
	 *           PrecisionPoint(0, 0), new PrecisionPoint(0, 0)); }/
	 **/

}
