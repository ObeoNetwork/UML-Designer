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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.obeonetwork.dsl.uml2.design.api.utils.UmlViewpoints;
import org.osgi.framework.Version;

/**
 * Use the Sirius migration framework to migrate UML Designer 4.0 aird to UML
 * Designer 5.0. The viewpoints were renamed and the representation reordered.
 * 
 * @author Melanie Bats <a
 *         href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDesigner4To5RepresentationsFileMigrationParticipant extends
		AbstractRepresentationsFileMigrationParticipant {
	/**
	 * The VP version for which this migration is added.
	 */
	private static final Version MIGRATION_VERSION = new Version("9.0.0");

	public Version getMigrationVersion() {
		return MIGRATION_VERSION;
	}

	public Option<String> getNewFragment(String uriFragment) {
		if (uriFragment
				.contains("//@ownedViewpoints[name='Capture']/@ownedRepresentations[name='Reused%20Description']")) {
			String newUriFragment = uriFragment
					.replace(
							"//@ownedViewpoints[name='Capture']/@ownedRepresentations[name='Reused%20Description']",
							"//@ownedViewpoints[name='Reused']/@ownedRepresentations[name='Reused%20Description']");
			return Options.newSome(newUriFragment);
		}
		return super.getNewFragment(uriFragment);
	}
}
