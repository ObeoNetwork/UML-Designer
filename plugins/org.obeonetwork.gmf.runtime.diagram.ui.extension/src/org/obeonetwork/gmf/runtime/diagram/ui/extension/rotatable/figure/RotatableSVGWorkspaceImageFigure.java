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

package org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.figure;

import java.util.Iterator;

import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.eclipse.sirius.viewpoint.ContainerStyle;
import org.eclipse.sirius.viewpoint.CustomStyle;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.WorkspaceImage;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.ExtensionActivator;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.NodeImageExtension;

/**
 * Rotatable Workspace Image Figure : switch mode ROTATION or IMAGE, rotate the
 * specific image or display four images in North South East and West.
 * 
 * @author nlepine
 * @author hmarchadour
 */
public class RotatableSVGWorkspaceImageFigure extends SVGWorkspaceImageFigure {

	private String bottomImgPath;

	private String leftImgPath;

	private String rightImgPath;

	private String topImgPath;

	private String currentImgPath;

	/**
	 * Creates a rotative image
	 * 
	 * @param path
	 *            the path of the top image.
	 */
	public RotatableSVGWorkspaceImageFigure(int mode, String topImgPath,
			String leftImgPath, String bottomImgPath, String rightImgPath) {
		super();

		this.topImgPath = topImgPath;
		this.bottomImgPath = bottomImgPath;
		this.leftImgPath = leftImgPath;
		this.rightImgPath = rightImgPath;

		currentImgPath = topImgPath;
		refreshFigure();
	}

	/**
	 * Refresh the figure.
	 * 
	 * @param imageStyle
	 *            the image associated to the figure
	 */
	public void refreshFigure(final CustomStyle imageStyle) {
		boolean found = false;
		Iterator<NodeImageExtension> iterator = ExtensionActivator.getDefault()
				.getImageExtensions().iterator();
		while (iterator.hasNext() && !found) {
			NodeImageExtension desc = (NodeImageExtension) iterator.next();
			if (imageStyle.getId() != null
					&& imageStyle.getId().equals(desc.getId())) {
				if (currentImgPath != null) {
					if (currentImgPath.equals(bottomImgPath)) {
						currentImgPath = desc.getBottomImage();
					} else if (currentImgPath.equals(leftImgPath)) {
						currentImgPath = desc.getLeftImage();
					} else if (currentImgPath.equals(rightImgPath)) {
						currentImgPath = desc.getRightImage();
					} else {
						currentImgPath = desc.getTopImage();
					}
				} else {
					currentImgPath = desc.getTopImage();
				}
				topImgPath = desc.getTopImage();
				bottomImgPath = desc.getBottomImage();
				leftImgPath = desc.getLeftImage();
				rightImgPath = desc.getRightImage();
				found = true;
			}
		}
		refreshFigure();
		this.repaint();
	}

	private void refreshFigure() {
		WorkspaceImage createWorkspaceImage = ViewpointFactory.eINSTANCE
				.createWorkspaceImage();
		createWorkspaceImage.setWorkspacePath(currentImgPath);
		refreshFigure(createWorkspaceImage);
	}

	/**
	 * Refresh the figure.
	 * 
	 * @param containerStyle
	 *            the style of the container
	 */
	public void refreshFigure(final ContainerStyle containerStyle) {

		if (containerStyle instanceof CustomStyle) {
			refreshFigure((CustomStyle) containerStyle);
		}
	}

	/**
	 * @return the currentImgPath
	 */
	public String getCurrentImgPath() {
		return currentImgPath;
	}

	/**
	 * @param currentImgPath
	 *            the currentImgPath to set
	 */
	public void setCurrentImgPath(String currentImgPath) {
		this.currentImgPath = currentImgPath;
	}

	/**
	 * @return the bottomImgPath
	 */
	public String getBottomImgPath() {
		return bottomImgPath;
	}

	/**
	 * @return the leftImgPath
	 */
	public String getLeftImgPath() {
		return leftImgPath;
	}

	/**
	 * @return the rightImgPath
	 */
	public String getRightImgPath() {
		return rightImgPath;
	}

	/**
	 * @return the topImgPath
	 */
	public String getTopImgPath() {
		return topImgPath;
	}

	/**
	 * @param bottomImgPath
	 *            the bottomImgPath to set
	 */
	public void setBottomImgAsCurrent() {
		currentImgPath = bottomImgPath;
		refreshFigure();
	}

	/**
	 * @param leftImgPath
	 *            the leftImgPath to set
	 */
	public void setLeftImgAsCurrent() {
		currentImgPath = leftImgPath;
		refreshFigure();
	}

	/**
	 * @param rightImgPath
	 *            the rightImgPath to set
	 */
	public void setRightImgAsCurrent() {
		currentImgPath = rightImgPath;
		refreshFigure();
	}

	/**
	 * @param topImgPath
	 *            the topImgPath to set
	 */
	public void setTopImgAsCurrent() {
		currentImgPath = topImgPath;
		refreshFigure();
	}

}
