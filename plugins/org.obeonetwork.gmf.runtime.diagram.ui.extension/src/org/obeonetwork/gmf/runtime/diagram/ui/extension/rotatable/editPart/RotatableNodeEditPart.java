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
package org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.editPart;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.figure.anchor.AnchorProvider;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.figure.RotatableNodeFigure;

/**
 * @author Hugo Marchadour <a
 *         href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class RotatableNodeEditPart extends DNodeEditPart {

	private DefaultSizeNodeFigure nodePlate = null;

	/**
	 * Constructor.
	 * 
	 * @param view
	 */
	public RotatableNodeEditPart(View view) {
		super(view);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = null;
		final EObject eObj = resolveSemanticElement();
		if (eObj instanceof DStylizable && eObj instanceof DDiagramElement) {
			final DStylizable viewNode = (DStylizable) eObj;
			final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE
					.getStyleConfiguration(
							((DDiagramElement) eObj).getDiagramElementMapping(),
							viewNode.getStyle());
			final AnchorProvider anchorProvider = styleConfiguration
					.getAnchorProvider();
			result = new RotatableNodeFigure(getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(5), anchorProvider);
			nodePlate = result;
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connEditPart) {
		return nodePlate.getTargetConnectionAnchorAt(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return nodePlate.getTargetConnectionAnchorAt(null);
	}
}
