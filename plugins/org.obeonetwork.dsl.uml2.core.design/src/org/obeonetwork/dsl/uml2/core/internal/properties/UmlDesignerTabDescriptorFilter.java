/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.core.internal.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.eef.properties.ui.api.IEEFTabDescriptor;
import org.eclipse.eef.properties.ui.api.IEEFTabDescriptorFilter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.ui.business.api.editor.ISiriusEditor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;

public class UmlDesignerTabDescriptorFilter implements IEEFTabDescriptorFilter {

	private static final String UML_GENERAL_TAB_LABEL = "General"; //$NON-NLS-1$

	private static final String UML_ADVANCED_TAB_LABEL = "Advanced"; //$NON-NLS-1$

	private static final String SIRIUS_DEFAULT_TAB_ID = "org.eclipse.sirius.ui.tools.views.model.explorer.tab"; //$NON-NLS-1$

	private static final String SIRIUS_EXTENSION_TAB_ID = "property.tab.semantic.extension"; //$NON-NLS-1$

	private static final String SIRIUS_EXTENSIONS_TAB_ID = "Extensions"; //$NON-NLS-1$

	private static final String SIRIUS_SEMANTIC_TAB_ID = "property.tab.semantic"; //$NON-NLS-1$

	private static final String SIRIUS_BEHAVIORS_TAB_ID = "property.tab.behaviors"; //$NON-NLS-1$

	private static final String SIRIUS_DOC_TAB_ID = "property.tab.documentation"; //$NON-NLS-1$

	private static final String SIRIUS_STYLE_TAB_ID = "property.tab.style"; //$NON-NLS-1$

	private static final String SIRIUS_APPEARANCE_TAB_ID = "property.tab.AppearancePropertySection"; //$NON-NLS-1$

	private static final String SIRIUS_DIAG_PROP_SECT_TAB_ID = "property.tab.DiagramPropertySection"; //$NON-NLS-1$


	List<String> siriusTabsId = new ArrayList<String>(
			Arrays.asList(
					SIRIUS_EXTENSION_TAB_ID, SIRIUS_EXTENSIONS_TAB_ID, SIRIUS_SEMANTIC_TAB_ID,
					SIRIUS_BEHAVIORS_TAB_ID, SIRIUS_DOC_TAB_ID, SIRIUS_STYLE_TAB_ID, SIRIUS_APPEARANCE_TAB_ID,
					SIRIUS_DIAG_PROP_SECT_TAB_ID));

	List<String> UmlTabs = new ArrayList<String>(
			Arrays.asList(UML_GENERAL_TAB_LABEL, UML_ADVANCED_TAB_LABEL));

	public boolean filter(IEEFTabDescriptor tabDescriptor) {
		final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage();

		final IWorkbenchPartReference partRef = activePage.getActivePartReference();
		final IWorkbenchPart part = partRef.getPart(false);
		if (part instanceof ISiriusEditor) {
			final ISelection selection = activePage.getSelection();
			if (selection instanceof StructuredSelection && !((StructuredSelection)selection).isEmpty()) {
				final Object selectedObject = ((StructuredSelection)selection).getFirstElement();
				final Object semantic = getTarget(selectedObject);
				if (semantic instanceof Element) {
					return UmlTabs.contains(tabDescriptor.getLabel())
							|| siriusTabsId.contains(tabDescriptor.getId());
				}
			}
		}
		return true;
	}

	/**
	 * Get semantic object from a selection in Sirius editor.
	 *
	 * @param selectedObject
	 * @return the semantic object
	 */
	public Object getTarget(Object selectedObject) {
		Object result = null;
		if (selectedObject instanceof EditPart) {
			final EditPart selectedEditPart = (EditPart) selectedObject;
			final View view = (View) selectedEditPart.getModel();
			if (view.getElement() instanceof DSemanticDecorator)
			{
				final DSemanticDecorator diagram = (DSemanticDecorator) view.getElement();
				if (null != diagram) {
					result = diagram.getTarget();
				}
			}
		} else if (selectedObject instanceof DSemanticDecorator) {
			final DSemanticDecorator vpe = (DSemanticDecorator) selectedObject;
			result = vpe.getTarget();
		} else if (selectedObject instanceof DRepresentationElement) {
			final DRepresentationElement vpe = (DRepresentationElement) selectedObject;
			final List<EObject> elements = vpe.getSemanticElements();
			for (final EObject element : elements) {
				result = element;
			}
		}
		return result;
	}
}
