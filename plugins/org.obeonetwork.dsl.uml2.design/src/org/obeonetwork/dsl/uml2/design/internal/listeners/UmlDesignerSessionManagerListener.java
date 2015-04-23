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
package org.obeonetwork.dsl.uml2.design.internal.listeners;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.ProfileApplication;
import org.obeonetwork.dsl.uml2.design.internal.triggers.AutosizeTrigger;

import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

/**
 * Session listener.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDesignerSessionManagerListener implements SessionManagerListener {

	private final IPartService partService = PlatformUI.getWorkbench().getWorkbenchWindows()[0]
			.getPartService();

	private final OpenHelpContextListener openedEditorListener = new OpenHelpContextListener();

	private final CallActionPinListener callActionPinListener = new CallActionPinListener();

	private final CreateNewChildListener createNewChildListener = new CreateNewChildListener();

	public void notify(Session updated, int notification) {
		// Nothing
		if (notification == SessionListener.OPENED) {

			final Set<EPackage> profileEPackages = Sets.newLinkedHashSet();

			for (final Resource semResources : updated.getSemanticResources()) {
				final Iterator<ProfileApplication> it = Iterators.filter(
						EcoreUtil.getAllProperContents(semResources, true), ProfileApplication.class);
				while (it.hasNext()) {
					final ProfileApplication cur = it.next();
					final EPackage found = cur.getAppliedDefinition();
					if (found != null) {
						profileEPackages.add(found);
					}
				}
			}
			final Set<MetamodelDescriptor> descriptorsForInterpreter = Sets.newLinkedHashSet();
			for (final EPackage pak : profileEPackages) {
				descriptorsForInterpreter.add(new EcoreMetamodelDescriptor(pak));
			}
			updated.getInterpreter().activateMetamodels(descriptorsForInterpreter);

		}
	}

	public void notifyAddSession(Session newSession) {
		newSession.getEventBroker().addLocalTrigger(AutosizeTrigger.IS_GMF_NODE_ATTACHMENT,
				new AutosizeTrigger(newSession.getTransactionalEditingDomain()));
		partService.addPartListener(openedEditorListener);
		newSession.getTransactionalEditingDomain().addResourceSetListener(callActionPinListener);
		newSession.getTransactionalEditingDomain().getCommandStack()
				.addCommandStackListener(createNewChildListener);
	}

	public void notifyRemoveSession(Session removedSession) {
		partService.removePartListener(openedEditorListener);
		removedSession.getTransactionalEditingDomain().removeResourceSetListener(callActionPinListener);
		removedSession.getTransactionalEditingDomain().getCommandStack()
				.removeCommandStackListener(createNewChildListener);
	}

	public void viewpointDeselected(Viewpoint deselectedSirius) {
		// Nothing
	}

	public void viewpointSelected(Viewpoint selectedSirius) {
		// Nothing
	}

}
