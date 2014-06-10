/*****************************************************************************
 * Copyright (c) 2011 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Barthelemy HABA (Atos Origin) barthelemy.haba@atosorigin.com - 
 *
 *****************************************************************************/
//CHECKSTYLE:OFF
package org.obeonetwork.dsl.sirius.gendoc.wizard;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;

/**
 * This class comes from :pserver:cvs.gforge.enseeiht.fr:/cvsroot/topcased-gendoc
 * ,plugins/gendoc2/org.topcased.gendoc2.wizard,org.topcased.gendoc2.wizard.
 */
public class WizardPropertyTester extends PropertyTester {

	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		if (receiver instanceof IFile) {
			IFile part = (IFile) receiver;
			return Utils.matches(part.getName());
		}
		return false;
	}

}
