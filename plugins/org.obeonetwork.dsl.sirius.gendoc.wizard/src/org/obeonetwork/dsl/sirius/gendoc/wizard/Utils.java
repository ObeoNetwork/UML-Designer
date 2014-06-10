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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * This class comes from :pserver:cvs.gforge.enseeiht.fr:/cvsroot/topcased-gendoc
 * ,plugins/gendoc2/org.topcased.gendoc2.wizard,org.topcased.gendoc2.wizard.
 */
public class Utils {
	private static List<IGendoc2Runner> runners = null;

	/**
	 * @return the list of all Runners
	 */
	public static List<IGendoc2Runner> getAllRunners() {
		if (runners == null) {
			runners = new LinkedList<IGendoc2Runner>();
			IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor(
					Activator.PLUGIN_ID, "runner");
			for (IConfigurationElement e : elements) {
				try {
					IGendoc2Runner runner = (IGendoc2Runner)e.createExecutableExtension("Instance");
					runners.add(runner);
				} catch (CoreException e1) {
					e1.printStackTrace();
				}
			}
		}
		return runners;
	}

	/**
	 * @return the list of all extensions of the template we need in order to execute document generation
	 */
	public static List<Pattern> getAllExtensions() {
		List<IGendoc2Runner> runners = getAllRunners();
		List<Pattern> result = new LinkedList<Pattern>();
		for (IGendoc2Runner r : runners) {
			if (r.getPattern() != null) {
				result.add(r.getPattern());
			}
		}
		return result;
	}

	/**
	 * @param fileName
	 *            name with extension of file on witch user has click
	 * @return return true, if the fileName has one of the good extension
	 */
	public static boolean matches(String fileName) {
		List<Pattern> patterns = getAllExtensions();
		for (Pattern p : patterns) {
			if (p != null) {
				if (p.matcher(fileName).matches()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param fileName
	 *            name of the model selected in order to generate its documentation
	 * @return the list of all the template possible for document generation .
	 */
	public static List<IGendoc2Runner> getRunners(String fileName) {
		List<IGendoc2Runner> runners = getAllRunners();
		List<IGendoc2Runner> result = new ArrayList<IGendoc2Runner>(runners.size());
		for (IGendoc2Runner g : runners) {
			if (g.getPattern().matcher(fileName).matches()) {
				result.add(g);
			}
		}
		return result;
	}

}
