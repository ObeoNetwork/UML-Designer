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
package org.obeonetwork.dsl.uml2.migration;

import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.osgi.framework.Version;

/**
 * Use the Sirius migration framework to migrate UML Designer 3.0 aird to UML
 * Designer 4.0. The viewpoints were renamed and the representation reordered.
 * 
 * @author Melanie Bats <a
 *         href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDesignerRepresentationsFileMigrationParticipant extends
		AbstractRepresentationsFileMigrationParticipant {
	/**
	 * The VP version for which this migration is added.
	 */
	private static final Version MIGRATION_VERSION = new Version("8.1.0");

	public Version getMigrationVersion() {
		return MIGRATION_VERSION;
	}

	public Option<String> getNewFragment(String uriFragment) {
		if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Reused%20Description']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Reused%20Description']",
							"//@ownedViewpoints[name='Capture']/@ownedRepresentations[name='Reused%20Description']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Package%20Hierarchy']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Package%20Hierarchy']",
							"//@ownedViewpoints[name='Capture']/@ownedRepresentations[name='Package%20Hierarchy']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Behavioral%20Modeling']/@ownedRepresentations[name='Use%20Case%20Diagram']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Behavioral%20Modeling']/@ownedRepresentations[name='Use%20Case%20Diagram']",
							"//@ownedViewpoints[name='Capture']/@ownedRepresentations[name='Use%20Case%20Diagram']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Class%20Diagram']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Class%20Diagram']",
							"//@ownedViewpoints[name='Design']/@ownedRepresentations[name='Class%20Diagram']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Component%20Diagram']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Component%20Diagram']",
							"//@ownedViewpoints[name='Design']/@ownedRepresentations[name='Component%20Diagram']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Composite%20Structure%20Diagram']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Composite%20Structure%20Diagram']",
							"//@ownedViewpoints[name='Design']/@ownedRepresentations[name='Composite%20Structure%20Diagram']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Deployment%20Diagram']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Structural%20Modeling']/@ownedRepresentations[name='Deployment%20Diagram']",
							"//@ownedViewpoints[name='Design']/@ownedRepresentations[name='Deployment%20Diagram']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Behavioral%20Modeling']/@ownedRepresentations[name='State%20Machine%20Diagram']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Behavioral%20Modeling']/@ownedRepresentations[name='State%20Machine%20Diagram']",
							"//@ownedViewpoints[name='Design']/@ownedRepresentations[name='State%20Machine%20Diagram']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Behavioral%20Modeling']/@ownedRepresentations[name='Activity%20Diagram']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Behavioral%20Modeling']/@ownedRepresentations[name='Activity%20Diagram']",
							"//@ownedViewpoints[name='Design']/@ownedRepresentations[name='Activity%20Diagram']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Behavioral%20Modeling']/@ownedRepresentations[name='Sequence%20Diagram']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Behavioral%20Modeling']/@ownedRepresentations[name='Sequence%20Diagram']",
							"//@ownedViewpoints[name='Design']/@ownedRepresentations[name='Sequence%20Diagram']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Extensions']/@ownedRepresentations[name='Documentation%20Table']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Extensions']/@ownedRepresentations[name='Documentation%20Table']",
							"//@ownedViewpoints[name='Review']/@ownedRepresentations[name='Documentation%20Table']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Extensions']/@ownedRepresentations[name='Package%20Containment']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Extensions']/@ownedRepresentations[name='Package%20Containment']",
							"//@ownedViewpoints[name='Review']/@ownedRepresentations[name='Package%20Containment']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Extensions']/@ownedRepresentations[name='Use%20Case%20Cross%20Table']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Extensions']/@ownedRepresentations[name='Use%20Case%20Cross%20Table']",
							"//@ownedViewpoints[name='Review']/@ownedRepresentations[name='Use%20Case%20Cross%20Table']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Extensions']/@ownedRepresentations[name='Use%20Case%20Cross%20Table']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Extensions']/@ownedRepresentations[name='Use%20Case%20Cross%20Table']",
							"//@ownedViewpoints[name='Review']/@ownedRepresentations[name='Use%20Case%20Cross%20Table']");
			return Options.newSome(newUriFragment);
		} else if (uriFragment
				.contains("//@ownedViewpoints[name='UML%20Profile%20Design']/@ownedRepresentations[name='Profile%20Diagram']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='UML%20Profile%20Design']/@ownedRepresentations[name='Profile%20Diagram']",
							"//@ownedViewpoints[name='Extend']/@ownedRepresentations[name='Profile%20Diagram']");
			return Options.newSome(newUriFragment);
		}
		return super.getNewFragment(uriFragment);
	}
}
