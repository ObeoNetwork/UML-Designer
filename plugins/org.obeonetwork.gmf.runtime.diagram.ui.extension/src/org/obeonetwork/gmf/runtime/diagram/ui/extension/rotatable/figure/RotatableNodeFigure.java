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

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import fr.obeo.dsl.viewpoint.diagram.ui.tools.api.figure.AirDefaultSizeNodeFigure;
import fr.obeo.dsl.viewpoint.diagram.ui.tools.api.figure.anchor.AnchorProvider;

/**
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class RotatableNodeFigure extends AirDefaultSizeNodeFigure {

	/**
	 * Constructor.
	 * 
	 * @param defSize
	 * @param anchorProvider
	 */
	public RotatableNodeFigure(Dimension defSize, AnchorProvider anchorProvider) {
		super(defSize, anchorProvider);
	}

	public RotatableNodeFigure(final int width, final int height, final AnchorProvider anchorProvider) {
		super(width, height, anchorProvider);
	}

	public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {
		return new CenteredAnchor(this);
	}
}
