/*******************************************************************************
 * Copyright (c) 2013, 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.parts;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class CustomUmlViewsRepository extends UmlViewsRepository {

	/**
	 * Stereotypes view descriptor
	 */
	public static class Stereotypes {
		public static String appliedStereotypes = "uml::Stereotypes::appliedStereotypes";

	}
	
	/**
	 * Stereotype Applications view descriptor
	 */
	public static class StereotypeApplications {
		public static String stereotypeApplications = "uml::Stereotypes::stereotypeApplications";

	}
	
	/**
	 * Stereotype Application Property view descriptor
	 */
	public static class SAProperty {
		public static String stereotypeApplicationProperty = "uml::Stereotypes::stereotypeApplicationProperty";

	}

	/**
	 * Profiles view descriptor
	 */
	public static class Profiles {
		public static String appliedProfiles = "uml::Profiles::appliedProfiles";

	}

	/**
	 * Relationships view descriptor
	 */
	public static class Relationships {
		public static String relationshipsOriginating = "uml::Relationships::relationshipsOriginating";
		public static String relationshipsTargeting = "uml::Relationships::relationshipsTargeting";

	}

	/**
	 * Documentation view descriptor
	 */
	public static class Documentation {
		public static String comment = "uml::Documentation::comment";
	}

	/**
	 * General custom view descriptor
	 */
	public static class General {
		public static String memberEndOwned = "uml::General::memberEnd::owned";
		public static String memberEndNavigable = "uml::General::memberEnd::navigable";
	}

}
