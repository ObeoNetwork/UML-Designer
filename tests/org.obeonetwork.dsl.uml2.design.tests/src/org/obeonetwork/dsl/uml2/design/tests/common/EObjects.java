/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.tests.common;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;

public class EObjects {

	public static ImmutableListMultimap<EClass, EObject> perType(
			Iterable<EObject> objects) {
		return Multimaps.index(objects, EObjects.toEclass);
	}

	public static Function<EObject,EClass> toEclass = new Function<EObject, EClass>() {

		public EClass apply(EObject arg0) {
			return arg0.eClass();
		}
	};
	
}
