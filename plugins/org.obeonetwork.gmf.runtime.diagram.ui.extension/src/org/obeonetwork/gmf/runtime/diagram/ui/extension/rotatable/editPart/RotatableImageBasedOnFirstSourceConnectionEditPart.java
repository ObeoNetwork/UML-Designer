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
package org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.editPart;

import java.util.List;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;

/**
 * @author hmarchadour
 */
public class RotatableImageBasedOnFirstSourceConnectionEditPart extends AbstractRotatableImageEditPart implements IStyleEditPart {

	private static final int BASE_ANGLE = 45;

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            the GMF view.
	 */
	public RotatableImageBasedOnFirstSourceConnectionEditPart(View view) {
		super(view);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void figureHasChanged() {
		EditPart parentEditPart = getParent();
		if (parentEditPart instanceof GraphicalEditPart) {
			GraphicalEditPart parentGraphicalEditPart = (GraphicalEditPart)parentEditPart;
			List sourceConnections = parentGraphicalEditPart.getSourceConnections();
			if (sourceConnections.size() > 0) {
				Object sourceConnection = sourceConnections.get(0);
				if (sourceConnection instanceof ConnectionNodeEditPart) {
					ConnectionNodeEditPart dEdgeSourceConnection = (ConnectionNodeEditPart)sourceConnection;
					PolylineConnection polylineConnection = (PolylineConnection)dEdgeSourceConnection
							.getFigure();
					double angle = getFirstSegmentAngle(polylineConnection);
					if (angle > BASE_ANGLE && angle <= BASE_ANGLE * 3) {
						setFigureAtBottom();
					} else if (angle > BASE_ANGLE * 3 && angle <= BASE_ANGLE * 5) {
						setFigureAtRight();
					} else if (angle > BASE_ANGLE * 5 && angle <= BASE_ANGLE * 7) {
						setFigureAtTop();
					} else if ((angle > BASE_ANGLE * 7 && angle <= BASE_ANGLE * 8)
							|| (angle >= 0 && angle <= BASE_ANGLE * 3)) {
						setFigureAtLeft();
					}

				}
			}
		}
	}

}
