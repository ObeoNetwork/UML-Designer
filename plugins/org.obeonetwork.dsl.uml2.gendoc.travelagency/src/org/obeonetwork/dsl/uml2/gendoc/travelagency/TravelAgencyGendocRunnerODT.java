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

import java.net.URL;

import org.obeonetwork.dsl.sirius.gendoc.wizard.IGendoc2Template;

/**
 * The gendoc template implementation for travel agency example with ODT files.
 * 
 * @author Axel Richard <a
 *         href="mailto:axel.richard@obeo.fr">axel.richard@obeo.fr</a>
 *
 */
public class TravelAgencyGendocRunnerODT implements IGendoc2Template {

	public String getOutPutExtension() {
		return ".odt";
	}

	public URL getTemplate() {
		return Activator.getDefault().getBundle().getEntry("/templates/travel_agency_template.odt");
	}

	public String getModelKey() {
		return "travel_agency_model";
	}

	public String getOutputKey() {
		return "travel_agency_output";
	}

	public String getAirdDiagramKey() {
		return "travel_agency_aird";
	}

	public String getDescription() {
		return "ODT template to generate the documentation of travel agency";
	}

}
