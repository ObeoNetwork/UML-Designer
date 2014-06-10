/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.gendoc.travelagency;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.obeonetwork.dsl.sirius.gendoc.wizard.IGendoc2Runner;
import org.obeonetwork.dsl.sirius.gendoc.wizard.IGendoc2Template;

public class TravelAgencyGendocRunner implements IGendoc2Runner {

	List<IGendoc2Template> myTemplates = new ArrayList<IGendoc2Template>();
	
	public TravelAgencyGendocRunner() {
		myTemplates.add(new TravelAgencyGendocRunnerODT());
	}
	
	public Pattern getPattern() {
		return Pattern.compile(".*\\.uml");
	}

	public List<IGendoc2Template> getGendoc2Templates() {
		return myTemplates;
	}

	public String getLabel() {
		return "UML Travel Agency Documentation Generation";
	}

}
