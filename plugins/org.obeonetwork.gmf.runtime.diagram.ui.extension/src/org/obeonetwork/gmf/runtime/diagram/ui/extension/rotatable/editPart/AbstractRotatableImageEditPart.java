/*******************************************************************************
 * Copyright (c) 2013 Obeo.
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
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Vector;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeListener;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AirStyleDefaultSizeNodeFigure;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.ExtensionActivator;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.NodeImageExtension;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.editPart.listener.PropagateFigureListenerAtConnectionFigure;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.figure.RotatableSVGWorkspaceImageFigure;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.figure.listener.RotatableImageListener;

/**
 * Edit Part for rotatable image : switch mode ROTATION or IMAGE, rotate the specific image or display four
 * images in North South East and West.
 * 
 * @author nlepine
 * @author hmarchadour
 */
public abstract class AbstractRotatableImageEditPart extends AbstractNotSelectableShapeNodeEditPart implements IStyleEditPart {

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected RotatableSVGWorkspaceImageFigure primaryShape;

	protected RotatableImageListener listener;

	public static final int TOP = 0;

	public static final int LEFT = 1;

	public static final int BOTTOM = 2;

	public static final int RIGHT = 3;

	/**
	 * Creates a new port edit part.
	 * 
	 * @param view
	 *            the GMF view.
	 */
	public AbstractRotatableImageEditPart(View view) {
		super(view);
	}

	/**
	 * The figure has changed and we check if we must update the figure
	 */
	public abstract void figureHasChanged();

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart#createDefaultEditPolicies()
	 */
	protected void createDefaultEditPolicies() {
		// empty.
	}

	/**
	 * @generated : prevent drag of elements
	 */
	public DragTracker getDragTracker(Request request) {
		return getParent().getDragTracker(request);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		super.refreshVisuals();
		RotatableSVGWorkspaceImageFigure figure = this.getPrimaryShape();

		getSourceConnections();

		EObject element = this.resolveSemanticElement();
		if (element instanceof CustomStyle) {
			CustomStyle imageStyle = (CustomStyle)element;
			figure.refreshFigure(imageStyle);
			((GraphicalEditPart)this.getParent()).setLayoutConstraint(this, this.getFigure(),
					new Rectangle(0, 0, figure.getPreferredSize().width, figure.getPreferredSize().height));
		}
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		LayoutEditPolicy lep = new LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		CustomStyle imageStyle = (CustomStyle)resolveSemanticElement();

		NodeImageExtension imageExtension = ExtensionActivator.getDefault()
				.getBestImageExtension(imageStyle.getId());

		primaryShape = new RotatableSVGWorkspaceImageFigure(imageExtension.getMode(),
				imageExtension.getTopImage(), imageExtension.getLeftImage(), imageExtension.getBottomImage(),
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
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#deactivate()
	 */
	public void deactivate() {
		super.deactivate();
		primaryShape.removeFigureListener(listener);
		primaryShape.removeAncestorListener(listener);
		primaryShape.removePropertyChangeListener(listener);
	}

	/**
	 * @generated
	 */
	public RotatableSVGWorkspaceImageFigure getPrimaryShape() {
		return (RotatableSVGWorkspaceImageFigure)primaryShape;
	}

	/**
	 * @generated.
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new AirStyleDefaultSizeNodeFigure(getMapMode().DPtoLP(40),
				getMapMode().DPtoLP(40));
		return result;
	}

	/**
	 * @generated
	 */
	public EditPolicy getPrimaryDragEditPolicy() {
		EditPolicy result = super.getPrimaryDragEditPolicy();
		if (result instanceof ResizableEditPolicy) {
			ResizableEditPolicy ep = (ResizableEditPolicy)result;
			ep.setResizeDirections(PositionConstants.NONE);
		}
		return result;
	}

	/**
	 * Creates figure for this edit part. Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new XYLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane. Respects layout one may have set for
	 * generated figure.
	 * 
	 * @param nodeShape
	 *            instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		return nodeShape;
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @return the metamodel type
	 */
	protected Class<?> getMetamodelType() {
		return CustomStyle.class;
	}

	public void setFigureAtRight() {
		primaryShape.setRightImgAsCurrent();
	}

	public void setFigureAtBottom() {
		primaryShape.setBottomImgAsCurrent();
	}

	public void setFigureAtLeft() {
		primaryShape.setLeftImgAsCurrent();
	}

	public void setFigureAtTop() {
		primaryShape.setTopImgAsCurrent();
	}

	/**
	 * Angle in degrees [0..360]
	 * 
	 * @param polylineConnection
	 * @return the angle in degrees.
	 */
	public static double getFirstSegmentAngle(PolylineConnection polylineConnection) {

		PointList points = polylineConnection.getPoints();
		PrecisionPoint firstPoint = new PrecisionPoint(points.getFirstPoint());
		PrecisionPoint secondPoint = new PrecisionPoint(points.getPoint(1));

		Vector edgeVector = new Vector(firstPoint, secondPoint);

		double atan2 = Math.atan2(edgeVector.y, edgeVector.x);

		double degrees = Math.toDegrees(atan2);
		if (degrees < 0) {
			degrees = -degrees;
		} else {
			degrees = 180 + (180 - degrees);
		}
		return degrees;
	}
}
