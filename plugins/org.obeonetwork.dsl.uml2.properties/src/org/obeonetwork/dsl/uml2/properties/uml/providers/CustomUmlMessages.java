/*******************************************************************************
 * Copyright (c) 2009, 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.providers;

import org.eclipse.osgi.util.NLS;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class CustomUmlMessages {
	private static final String BUNDLE_NAME = "org.obeonetwork.dsl.uml2.properties.uml.providers.customUmlMessages"; //$NON-NLS-1$

	public static String Stereotypes_ReadOnly;

	public static String Stereotypes_Part_Title;

	public static String Profiles_ReadOnly;

	public static String Profiles_Part_Title;

	public static String Relationships_ReadOnly;

	public static String Relationships_Part_Title;

	public static String StereotypesPropertiesEditionPart_AppliedStereotypesLabel;

	public static String ProfilesPropertiesEditionPart_AppliedProfilesLabel;

	public static String RelationshipsPropertiesEditionPart_RelationshipsOriginatingLabel;

	public static String RelationshipsPropertiesEditionPart_RelationshipsTargetingLabel;

	public static String Documentation_ReadOnly;

	public static String Documentation_Part_Title;

	public static String DocumentationPropertiesEditionPart_CommentLabel;
	
	public static String StereotypeApplicationsPropertiesEditionPart_StereotypeApplicationsLabel;
	
	public static String StereotypeApplications_Part_Title;
	
	public static String StereotypeApplicationProperty_Part_Title;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, CustomUmlMessages.class);
	}

	private CustomUmlMessages() {
		// protect instanciation
	}
}
