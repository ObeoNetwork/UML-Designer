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

package org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable;

/**
 * Describe image extension.
 * 
 * @author nlepine
 * @author hmarchadour
 */
public class NodeImageExtension {

	/**
	 * mode : 0 -> 4 images.
	 */
	public static final int IMAGE = 0;

	/**
	 * mode : 1 image with rotation.
	 */
	public static final int ROTATION = 1;

	/**
	 * Fields corresponding to image extension
	 */
	public static final String RIGHT_IMAGE = "right";

	public static final String LEFT_IMAGE = "left";

	public static final String BOTTOM_IMAGE = "bottom";

	public static final String TOP_IMAGE = "top";

	public static final String ID = "id";

	/**
	 * The extension id
	 */
	public static final String IMAGE_EXTENSION_ID = "org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatableNode";

	/**
	 * Extension image id
	 */
	protected String id;

	/**
	 * Extension mode : IMAGE or ROTATION
	 */
	protected int mode;

	/**
	 * Extension top image
	 */
	protected String topImage;

	/**
	 * Extension bottom image
	 */
	protected String bottomImage;

	/**
	 * Extension left image
	 */
	protected String leftImage;

	/**
	 * Extension right image
	 */
	protected String rightImage;

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param topImage
	 * @param bottomImage
	 * @param leftImage
	 * @param rightImage
	 */
	public NodeImageExtension(String id, String topImage, String bottomImage, String leftImage,
			String rightImage) {
		this.id = id;
		this.topImage = topImage;
		this.bottomImage = bottomImage;
		this.leftImage = leftImage;
		this.rightImage = rightImage;
		this.mode = ROTATION;
		if (topImage != null && bottomImage != null && leftImage != null && rightImage != null) {
			this.mode = IMAGE;
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the mode
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * @return the top image
	 */
	public String getTopImage() {
		return topImage;
	}

	/**
	 * @return the bottom image
	 */
	public String getBottomImage() {
		return bottomImage;
	}

	/**
	 * @return the left image
	 */
	public String getLeftImage() {
		return leftImage;
	}

	/**
	 * @return the right image
	 */
	public String getRightImage() {
		return rightImage;
	}
}
