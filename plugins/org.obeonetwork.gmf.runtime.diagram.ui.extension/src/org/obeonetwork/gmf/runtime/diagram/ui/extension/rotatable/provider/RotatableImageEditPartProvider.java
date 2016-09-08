/*******************************************************************************
 * Copyright (c) 2013, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

package org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.provider;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.draw2d.ui.graph.BorderNode;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.ExtensionActivator;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.NodeImageExtension;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.editPart.RotatableBorderedNodeEditPart;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.editPart.RotatableImageBasedOnFirstSourceConnectionEditPart;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.editPart.RotatableNodeEditPart;

/**
 * Specific Edit Part Provider for rotatable image
 * 
 * @author nlepine
 * @author hmarchadour
 * @author arichard
 */
public class RotatableImageEditPartProvider extends AbstractEditPartProvider {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider#getNodeEditPartClass(org.eclipse.gmf.runtime.notation.View)
	 */
	protected Class<?> getNodeEditPartClass(final View view) {
		final EObject semanticElement = ViewUtil.resolveSemanticElement(view);
		if (semanticElement instanceof CustomStyle) {
			final CustomStyle customStyle = (CustomStyle)semanticElement;
			if (customStyleSupported(customStyle)) {
				if (isBorderedNode(semanticElement)) {
					return RotatableBorderedNodeEditPart.class;
				}
				return RotatableImageBasedOnFirstSourceConnectionEditPart.class;
			}
		} else if (view instanceof Node) {
			List<Object> children = view.getChildren();
			for (Object child : children) {
				if (child instanceof View) {
					final EObject childSemanticElement = ViewUtil.resolveSemanticElement((View)child);
					if (childSemanticElement instanceof CustomStyle) {
						final CustomStyle customStyle = (CustomStyle)childSemanticElement;
						if (customStyleSupported(customStyle)) {
							if (!isBorderedNode(semanticElement)) {
								return RotatableNodeEditPart.class;
							}
						}
					}
				}
			}
		}
		return super.getNodeEditPartClass(view);
	}

	private boolean isBorderedNode(EObject object) {
		if (object instanceof CustomStyle) {
			EObject container = object.eContainer();
			if (container instanceof DNode) {
				return isBorderedNode(container);
			}
		} else if (DiagramPackage.Literals.ABSTRACT_DNODE__OWNED_BORDERED_NODES.equals(object.eContainingFeature())) {
			return true;
		}
		return false;
	}

	private boolean customStyleSupported(CustomStyle customStyle) {

		boolean ret = false;
		for (NodeImageExtension desc : ExtensionActivator.getDefault().getImageExtensions()) {
			if (customStyle.getId() != null && customStyle.getId().equals(desc.getId())) {
				ret = true;
				break;
			}
		}
		return ret;
	}
}
