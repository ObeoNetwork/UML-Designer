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
package org.obeonetwork.dsl.uml2.design.services;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Package;

/**
 * This class provides needed services for filtering.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class FilterServices {

	/**
	 * States if the given object is related to the context {@link Classifier}.
	 * 
	 * @param toFilter the candidate to check for relation
	 * @param context the classifier context object.
	 * @return <code>true</code> if the given object is related to the context {@link Classifier}, <code>false</code> otherwise.
	 */
	public boolean isRelated(EObject toFilter, Classifier context) {
		boolean res = false;
		if (toFilter instanceof Generalization) {
			res = context.getGeneralizations().contains(toFilter)
					|| ((Generalization)toFilter).getGeneral() == context;
		} else if (toFilter instanceof InterfaceRealization && context instanceof Class) {
			res = ((Class)context).getInterfaceRealizations().contains(toFilter)
					|| ((InterfaceRealization)toFilter).getContract() == context;
		} else if (toFilter instanceof Association) {
			res = context.getAssociations().contains(toFilter);
		} else if (toFilter instanceof Feature) {
			res = isRelated(toFilter.eContainer(), context);
		} else if (toFilter instanceof Classifier) {
			res = context == toFilter;
			// is it a generalization end
			if (!res) {
				for (Generalization generalization : context.getGeneralizations()) {
					if (generalization.getGeneral() == toFilter) {
						res = true;
						break;
					}
				}
			}
			// is it a generalization opposite end
			if (!res) {
				for (Generalization generalization : ((Classifier)toFilter).getGeneralizations()) {
					if (generalization.getGeneral() == context) {
						res = true;
						break;
					}
				}
			}
			if (toFilter instanceof Interface && context instanceof Class) {
				// is it a realization end
				if (!res) {
					for (InterfaceRealization realization : ((Class)context).getInterfaceRealizations()) {
						if (realization.getContract() == toFilter) {
							res = true;
							break;
						}
					}
				}
			}
			// is it an association end
			if (!res) {
				final List<Association> toFilterAsso = ((Classifier)toFilter).getAssociations();
				final List<Association> contextAsso = context.getAssociations();
				for (Association association : toFilterAsso) {
					if (contextAsso.contains(association)) {
						res = true;
						break;
					}
				}
			}
		} else if (toFilter instanceof Package) {
			for (EObject content : toFilter.eContents()) {
				if (isRelated(content, context)) {
					res = true;
					break;
				}
			}
		}
		return res;
	}
}
