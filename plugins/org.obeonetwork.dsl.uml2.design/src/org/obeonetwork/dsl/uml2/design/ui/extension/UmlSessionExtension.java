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
package org.obeonetwork.dsl.uml2.design.ui.extension;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.obeonetwork.dsl.uml2.design.ui.extension.providers.UmlAnalysisContextMenuActionProvider;
import org.obeonetwork.dsl.uml2.design.ui.extension.providers.UmlAnalysisTreeProvider;

import fr.obeo.dsl.viewpoint.ui.tools.api.views.ViewHelper;
import fr.obeo.oo1932oo.viewpoint.oo221oo.oo19oo.oo5606oo.oo1376oo.Oo13727oo;
import fr.obeo.oo1932oo.viewpoint.oo221oo.oo19oo.oo5606oo.oo1376oo.oo13598oo.oo1102oo.Oo19414oo;
import fr.obeo.oo1932oo.viewpoint.oo221oo.oo19oo.oo5606oo.oo1376oo.oo13598oo.oo1102oo.Oo20979oo;

@SuppressWarnings("restriction")
public class UmlSessionExtension {
	/**
	 * The private constructor.
	 */
	private UmlSessionExtension() {
	};

	/**
	 * Used to add an extension to the model content view.
	 */
	public static void addExtension() {
		
		// Oo19414oo = ISessionViewExtension
		final Oo19414oo extension = new Oo19414oo() {
			public ITreeContentProvider getContentProvider() {
				return new UmlAnalysisTreeProvider();
			}

			// Oo20979oo = IContextMenuActionProvider
			public Oo20979oo getContextMenuActionProvider() {
				return new UmlAnalysisContextMenuActionProvider();
			}
		};
		// Oo13727oo = ViewHelperImpl
		((Oo13727oo)ViewHelper.INSTANCE).addExtension(extension);
	}
}
