/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.editPart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.ExtensionActivator;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.NodeImageExtension;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.editPart.listener.PropagateFigureListenerAtConnectionFigure;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.figure.RotatableSVGWorkspaceImageFigure;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.figure.listener.RotatableImageListener;

/**
 * @author Axel Richard <a href="mailto:axel.richard@obeo.fr">axel.richard@obeo.fr</a>
 */
public class RotatableBorderedNodeEditPart extends AbstractRotatableImageEditPart {

	public RotatableBorderedNodeEditPart(View view) {
		super(view);
	}

	@Override
	public void figureHasChanged() {
		if (getContainerBorderedNodeFigure() != null) {
			int side = DBorderItemLocator.findClosestSideOfParent(primaryShape.getBounds(),
					getContainerBorderedNodeFigure().getBounds());
			switch (side) {
				case PositionConstants.SOUTH:
					setFigureAtBottom();
					break;
				case PositionConstants.NORTH:
					setFigureAtTop();
					break;
				case PositionConstants.WEST:
					setFigureAtLeft();
					break;
				case PositionConstants.EAST:
					setFigureAtRight();
					break;
			}
		}

	}

	@Override
	protected IFigure createNodeShape() {
		CustomStyle imageStyle = (CustomStyle) resolveSemanticElement();
		
		NodeImageExtension imageExtension = ExtensionActivator.getDefault()
				.getBestImageExtension(imageStyle.getId());

		primaryShape = new RotatableSVGWorkspaceImageFigure(imageExtension.getMode(), imageExtension.getTopImage(),
				imageExtension.getLeftImage(), imageExtension.getBottomImage(),
				imageExtension.getRightImage());

		listener = new RotatableImageListener(this);
		primaryShape.addFigureListener(listener);

		EditPart parentEditPart = getParent();
		if (parentEditPart instanceof GraphicalEditPart) {
			GraphicalEditPart parentGraphicalEditPart = (GraphicalEditPart)parentEditPart;
			NodeListener dEdgeEditPartListener = new PropagateFigureListenerAtConnectionFigure(listener);
			parentGraphicalEditPart.addNodeListener(dEdgeEditPartListener);
		}

		return primaryShape;
	}

	/**
	 * @return the BorderedNodeFigure
	 */
	private BorderedNodeFigure getContainerBorderedNodeFigure() {
		BorderedNodeFigure borderedNodeFigure = null;
		IBorderItemLocator borderItemLocator = getBorderItemLocator();
		if (borderItemLocator != null) {
			IBorderedShapeEditPart borderNodeEditPart = getContainerBorderedNodeEditpart();
			if (borderNodeEditPart != null) {
				borderedNodeFigure = borderNodeEditPart.getBorderedFigure();
			}
		}
		return borderedNodeFigure;
	}

	/**
	 * @return the bordered node edit part.
	 */
	private IBorderedShapeEditPart getContainerBorderedNodeEditpart() {
		IGraphicalEditPart current = this;
		IBorderedShapeEditPart borderedNodeEditPart = null;
		while (current != null && borderedNodeEditPart == null) {
			if (current instanceof AbstractBorderedShapeEditPart) {
				borderedNodeEditPart = (IBorderedShapeEditPart)current;
			}
			current = (IGraphicalEditPart)current.getParent();
		}
		return borderedNodeEditPart;
	}

	/**
	 * @return the border item locator
	 */
	private IBorderItemLocator getBorderItemLocator() {
		IBorderItemLocator borderItemLocator = null;
		IDiagramBorderNodeEditPart borderNodeEditPart = getBorderedNodeEditPart();

		if (borderNodeEditPart instanceof IBorderItemEditPart) {
			borderItemLocator = ((IBorderItemEditPart)borderNodeEditPart).getBorderItemLocator();
		}

		return borderItemLocator;
	}

	/**
	 * @return the diagram bordered node edit part.
	 */
	private IDiagramBorderNodeEditPart getBorderedNodeEditPart() {
		EditPart current = this;
		IDiagramBorderNodeEditPart borderNodeEditPart = null;
		while (current != null && borderNodeEditPart == null) {
			if (current instanceof IDiagramBorderNodeEditPart) {
				borderNodeEditPart = (IDiagramBorderNodeEditPart)current;
			}
			current = current.getParent();
		}
		return borderNodeEditPart;
	}

}
