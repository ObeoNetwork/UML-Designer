/*******************************************************************************
 * Copyright (c) 2009, 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.parts;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.part.impl.SectionPropertiesEditingPart;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.ModelExplorerView;
import org.eclipse.ui.IWorkbenchPart;

public abstract class CustomSectionPropertiesEditingPart extends
		SectionPropertiesEditingPart {

	public CustomSectionPropertiesEditingPart() {
		super();
	}

	public CustomSectionPropertiesEditingPart(
			IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	@Override
	protected void initializeEditingDomain(IWorkbenchPart part,
			ISelection selection) {
		super.initializeEditingDomain(part, selection);
		if (getEditingDomain() == null) {
			if (part instanceof ModelExplorerView) {
				if (selection instanceof StructuredSelection) {
					Object firstElement = ((StructuredSelection) selection)
							.getFirstElement();
					if (firstElement instanceof EObject) {
						TransactionalEditingDomain siriusEditingDomain = TransactionUtil
								.getEditingDomain((EObject) firstElement);
						if (siriusEditingDomain != null) {
							setEditingDomain(siriusEditingDomain);
						}
					}
				}
			}
		}
	}

}