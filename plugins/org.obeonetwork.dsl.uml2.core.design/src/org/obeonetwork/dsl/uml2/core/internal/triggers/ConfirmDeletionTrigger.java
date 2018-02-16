/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.core.internal.triggers;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.danalysis.DanglingRefRemovalTrigger;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.uml2.uml.Element;
import org.obeonetwork.dsl.uml2.core.internal.dialogs.ConfirmDeletionDialog;
import org.obeonetwork.dsl.uml2.core.preferences.UmlDesignerPreferences;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

/**
 * A {@link ModelChangeTrigger} which show a pop up dialog to confirm all the
 * potential deleted elements when a deletion occurred.
 *
 * @author Melanie Bats
 *         <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class ConfirmDeletionTrigger extends DanglingRefRemovalTrigger {
    /**
     * Priority of this {@link ModelChangeTrigger}.
     */
    public static final int DELETION_CONFIRMATION_PRIORITY = DanglingRefRemovalTrigger.DANGLING_REFERENCE_REMOVAL_PRIORITY + 1;

    /**
     * Filter {@link Notification}s which are not touch. More filtering work is
     * done later in localChangesAboutToCommit, see
     * isImpactingNotification(Collection<Notification>) which return true as
     * soon as an impacting notification is found. This is not done here for
     * performance reason: we need the container resource of the notifier.
     */
    public static final NotificationFilter IS_IMPACTING = new NotificationFilter.Custom() {
        @Override
        public boolean matches(Notification notification) {
            return !notification.isTouch();
        }
    };

    private final UmlDesignerPreferences preferences = new UmlDesignerPreferences();

    /**
     * Thanks to DanglingRefRemovalTrigger, we don't have access to the session,
     * so let's declare it again.
     */
    protected Session siriusSession;

    /**
     * Constructor.
     *
     * @see DanglingRefRemovalTrigger
     * @param session
     *            Session
     */
    public ConfirmDeletionTrigger(Session session) {
        super(session);
        siriusSession = session;
    }

    /**
     * Return the EObjects which have been detached by the given notifications
     * and their children.
     *
     * @param notifications
     *            notifications to process.
     * @param notifierToIgnore
     *            a predicate indicating if a given notification should be
     *            ignored regarding its EObject notifier or not (can be null if
     *            all notifications should be considered)
     * @return the EObjects which have been detached by the given notifications
     *         and their children.
     */
    protected Map<EObject, Object> getDetachedEObjectsAndChildren(Iterable<Notification> notifications) {
        final Map<EObject, Object> detachedEObjects = Maps.newHashMap();
        for (final Notification notification : notifications) {
            if (notification.getNotifier() instanceof EObject) {
                for (final EObject root : getNotificationValues(notification)) {
                    // Add the element and all its contents to the
                    // detachedEObjects set only once.
                    if (!detachedEObjects.containsKey(root) && root instanceof Element) {
                        detachedEObjects.put(root, notification);
                        final TreeIterator<EObject> iter = root.eAllContents();
                        while (iter.hasNext()) {
                            final EObject eObject = iter.next();
                            detachedEObjects.put(eObject, eObject);
                        }
                    }
                }
            }
        }
        return detachedEObjects;
    }

    @Override
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        if (!preferences.isDeletionConfirmationEnabled()) {
            return Options.newNone();
        }
        // Filter notifications to keep only remove notifications. This prevents
        // from getting the dialog
        // window while doing a move which means in term of notifications
        // REMOVE+ADD
        final Map<EObject, Object> allDetachedObjects = getDetachedEObjectsAndChildren(Iterables.filter(notifications, IS_DETACHMENT));
        for (final Notification notification : notifications) {
            if (notification.getEventType() == Notification.ADD) {
                if (notification.getNotifier() instanceof EObject) {
                    for (final EObject eObject : getNotificationValues(notification)) {
                        allDetachedObjects.remove(eObject);
                    }
                }
            }
        }

        if (allDetachedObjects.size() > 0) {
            final ConfirmDeletionDialog dialog = new ConfirmDeletionDialog(allDetachedObjects);
            if (!dialog.openConfirm()) {
                final Command result = new RecordingCommand(siriusSession.getTransactionalEditingDomain()) {
                    @Override
                    protected void doExecute() {
                        // Cancel operation
                        throw new OperationCanceledException("Deletion operation canceled"); //$NON-NLS-1$
                    }
                };
                return Options.newSome(result);
            }

        }
        return Options.newNone();
    }

    @Override
    public int priority() {
        return DELETION_CONFIRMATION_PRIORITY;
    }
}
