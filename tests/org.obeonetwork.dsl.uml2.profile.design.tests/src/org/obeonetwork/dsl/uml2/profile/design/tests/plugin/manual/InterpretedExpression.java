/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.profile.design.tests.plugin.manual;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

public class InterpretedExpression {

	private String expression;

	private EObject declaration;

	private EAttribute feature;

	public InterpretedExpression(String expression, EObject declaration, EAttribute feature) {
		super();
		this.expression = expression;
		this.declaration = declaration;
		this.feature = feature;
	}

	public String getExpression() {
		return expression;
	}

	public EObject getDeclaration() {
		return declaration;
	}

	public EAttribute getFeature() {
		return feature;
	}

}
