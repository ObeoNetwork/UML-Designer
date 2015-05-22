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
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
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
import org.obeonetwork.dsl.uml2.design.internal.triggers.ConfirmDeletionTrigger;

import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Session listener.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDesignerSessionManagerListener implements SessionManagerListener {

	/**
	 * Cache to keep if an element has a descendant.
	 *
	 * @return Map
	 */
	public static Map<EObject, Boolean> getDescendantCache() {
		return descendantCache;
	}

	private final IPartService partService = PlatformUI.getWorkbench().getWorkbenchWindows()[0]
			.getPartService();

	private final Map<Session, UmlDesignerSessionListener> sessionListeners = Maps.newHashMap();

	private final OpenHelpContextListener openedEditorListener = new OpenHelpContextListener();

	private final CallActionPinListener callActionPinListener = new CallActionPinListener();

	private final CreateNewChildListener createNewChildListener = new CreateNewChildListener();

	private final DescendantCacheListener descendantCacheListener = new DescendantCacheListener();

	private final static Map<EObject, Boolean> descendantCache = Maps.newHashMap();

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
		newSession.getEventBroker().addLocalTrigger(
				ConfirmDeletionTrigger.IS_IMPACTING,
				new ConfirmDeletionTrigger(newSession.getTransactionalEditingDomain(), newSession
						.getModelAccessor(), newSession.getSemanticCrossReferencer()));
		partService.addPartListener(openedEditorListener);
		newSession.getTransactionalEditingDomain().addResourceSetListener(callActionPinListener);
		newSession.getTransactionalEditingDomain().getCommandStack()
		.addCommandStackListener(createNewChildListener);
		newSession.getTransactionalEditingDomain().addResourceSetListener(descendantCacheListener);
		final UmlDesignerSessionListener sessionListener = new UmlDesignerSessionListener(newSession);
		sessionListeners.put(newSession, sessionListener);
		newSession.addListener(sessionListener);
	}

	public void notifyRemoveSession(Session removedSession) {
		partService.removePartListener(openedEditorListener);
		removedSession.getTransactionalEditingDomain().removeResourceSetListener(callActionPinListener);
		removedSession.getTransactionalEditingDomain().getCommandStack()
		.removeCommandStackListener(createNewChildListener);
		removedSession.getTransactionalEditingDomain().removeResourceSetListener(descendantCacheListener);
		removedSession.removeListener(sessionListeners.get(removedSession));
		sessionListeners.remove(removedSession);
	}

	public void viewpointDeselected(Viewpoint deselectedSirius) {
		// Nothing
	}

	public void viewpointSelected(Viewpoint selectedSirius) {
		// Nothing
	}
}
