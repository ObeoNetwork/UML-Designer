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

package org.obeonetwork.dsl.uml2.design.tests.automation;

import org.junit.runners.model.Statement;

public class ContextStatement extends Statement {
	private final Statement base;
	private IContext context;

	public ContextStatement(Statement base, IContext context) {
		this.base = base;
		this.context = context;
	}

	@Override
	public void evaluate() throws Throwable {
		context.setup();
		try {
			base.evaluate();
		} finally {
			context.tearDown();
		}
	}
}

