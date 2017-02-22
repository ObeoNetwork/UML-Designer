/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.editor.internal;

import java.text.Collator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.sorter.RepresentationInSemanticSorter;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.eclipse.uml2.uml.Element;
import org.obeonetwork.dsl.uml2.navigator.Activator;

/**
 * Specific Viewer Sorter for the model explorer view, that allows to sort alphabetically model elements.
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 */
@SuppressWarnings({"restriction"})
public class UMLDesignerViewerSorter extends RepresentationInSemanticSorter {

	final ICommonLabelProvider labelProvider = new SiriusCommonLabelProvider();
	
	public UMLDesignerViewerSorter() {
	}

	public UMLDesignerViewerSorter(Collator collator) {
		super(collator);
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		boolean sortActionEnabled = Activator.getDefault().getPreferenceStore().getBoolean("UMLDesignerModelExplorerSortingAction.isChecked");
		if (sortActionEnabled && e1 instanceof Element && e2 instanceof Element) {
			String e1Label = labelProvider.getText(e1);
			String e2Label = labelProvider.getText(e2);
			if (e1Label != null && e2Label != null) {
				return Collator.getInstance().compare(e1Label, e2Label);
			}
		}
		return super.compare(viewer, e1, e2);
	}
}
