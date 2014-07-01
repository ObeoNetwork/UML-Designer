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
package org.obeonetwork.dsl.uml2.design.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.StateMachine;

import com.google.common.collect.Lists;

/**
 * A set of services to handle the Dashboard.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DashboardServices {

	LogServices log = new LogServices();

	private static final String PACKAGE_HIERARCHY = "Package Hierarchy";

	private static final String CLASS_DIAGRAM = "Class Diagram";

	private static final String COMPONENT_DIAGRAM = "Component Diagram";

	private static final String COMPOSITE_STRUCTURE_DIAGRAM = "Composite Structure Diagram";

	private static final String DEPLOYMENT_DIAGRAM = "Deployment Diagram";

	private static final String STATEMACHINE_DIAGRAM = "State Machine Diagram";

	private static final String ACTIVITY_DIAGRAM = "Activity Diagram";

	private static final String PROFILE_DIAGRAM = "Profile Diagram";

	private static final String USECASE_DIAGRAM = "Use Case Diagram";

	private static final String SEQUENCE_DIAGRAM = "Sequence Diagram";

	private static final String DOCUMENTATION_TABLE = "Documentation Table";

	private static final String USECASE_CROSS_TABLE = "Use Case Cross Table";

	private static final String PACKAGE_CONTAINMENT = "Package Containment";

	/**
	 * ID of the dashboard diagram defined in the odesign.
	 */
	public static final String DASHBOARD_DIAGRAM_DESCRIPTION_ID = "Dashboard";

	/**
	 * ID the dashboard viewpoint defined in the odesign.
	 */
	public static final String DASHBOARD_VP = "Dashboard";

	/**
	 * Check if the dashboard representation already exists.
	 * 
	 * @param model
	 *            Semantic element
	 * @return True if dashboard does not exist otherwise false.
	 */
	public boolean doesDashboardNotExist(EObject any) {
		Session session = SessionManager.INSTANCE.getSession(any);
		Collection<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentations(session);
		for (DRepresentation representation : representations) {
			if (representation instanceof DSemanticDiagram) {
				DSemanticDiagram diagram = (DSemanticDiagram)representation;
				if (DASHBOARD_DIAGRAM_DESCRIPTION_ID.equals(diagram.getDescription().getName())) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Get the all representations except the dashboard.
	 * 
	 * @param any
	 *            Semantic element
	 * @return All the representations available in the session except the dashboard.
	 */
	public Collection<DRepresentation> getAllRepresentations(EObject any) {
		Session session = SessionManager.INSTANCE.getSession(any);
		Collection<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentations(session);
		List<DRepresentation> result = Lists.newArrayList(representations);
		for (DRepresentation representation : representations) {
			if (representation instanceof DSemanticDiagram) {
				DSemanticDiagram diagram = (DSemanticDiagram)representation;
				if (DASHBOARD_DIAGRAM_DESCRIPTION_ID.equals(diagram.getDescription().getName())) {
					result.remove(representation);
				}
			}
		}

		return result;
	}

	/**
	 * Get all the root uml model elements which define a dashboard.
	 * 
	 * @return List of model eleemnts.
	 */
	public List<EObject> getUmlModelsWithDashboard() {
		List<EObject> results = Lists.newArrayList();
		// Get all available dashboards
		Collection<Session> sessions = SessionManager.INSTANCE.getSessions();
		for (Session session : sessions) {
			// Check if the dashboard viewpoint is active for the current session
			Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
			boolean isDashboardViewpointActive = false;
			for (Viewpoint viewpoint : selectedViewpoints) {
				if (DASHBOARD_VP.equals(viewpoint.getName())) {
					isDashboardViewpointActive = true;
				}
			}
			if (isDashboardViewpointActive) {
				// Check if a dashboard representation exists for the current session
				Collection<DRepresentation> representations = DialectManager.INSTANCE
						.getAllRepresentations(session);
				Iterator<DRepresentation> iterator = representations.iterator();
				boolean findDashboard = false;
				while (iterator.hasNext() && !findDashboard) {
					DRepresentation representation = (DRepresentation)iterator.next();
					if (representation instanceof DSemanticDiagram) {
						DSemanticDiagram diagram = (DSemanticDiagram)representation;
						if (DashboardServices.DASHBOARD_DIAGRAM_DESCRIPTION_ID.equals(diagram
								.getDescription().getName())) {
							EObject target = ((DSemanticDiagram)representation).getTarget();
							results.add(target);
							findDashboard = true;
						}
					}
				}
			}
		}
		return results;
	}

	/**
	 * Open an existing representation.
	 * 
	 * @param representation
	 *            The representation to open
	 * @param monitor
	 */
	public void openRepresentation(final DRepresentation representation) {
		final Session session = SessionManager.INSTANCE.getSession(((DSemanticDecorator)representation)
				.getTarget());

		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) {
					monitor.beginTask("Open representation " + representation.getName(), 100);
					DialectUIManager.INSTANCE.openEditor(session, representation, monitor);
					monitor.done();
				}
			});
		} catch (InvocationTargetException e) {
			log.error("Open representation " + representation.getName() + " failed", e);
		} catch (InterruptedException e) {
			log.error("Open representation " + representation.getName() + " failed", e);
		}
	}

	public void createPackageDiagram(Model element) {
		createRepresentation(element, PACKAGE_HIERARCHY);
	}

	public void createClassDiagram(Model element) {
		createRepresentation(element, CLASS_DIAGRAM);
	}

	public void createComponentDiagram(Model element) {
		createRepresentation(element, COMPONENT_DIAGRAM);
	}

	public void createCompositeStructureDiagram(Model element) {
		createRepresentation(element, COMPOSITE_STRUCTURE_DIAGRAM);
	}

	public void createDeploymentDiagram(Model element) {
		createRepresentation(element, DEPLOYMENT_DIAGRAM);
	}

	public void createStateMachineDiagram(StateMachine element) {
		createRepresentation(element, STATEMACHINE_DIAGRAM);
	}

	public void createActivityDiagram(Activity element) {
		createRepresentation(element, ACTIVITY_DIAGRAM);
	}

	public void createProfileDiagram(Profile element) {
		createRepresentation(element, PROFILE_DIAGRAM);
	}

	public void createUseCaseDiagram(Model element) {
		createRepresentation(element, USECASE_DIAGRAM);
	}

	public void createSequenceDiagram(Interaction element) {
		createRepresentation(element, SEQUENCE_DIAGRAM);
	}

	public void createDocumentationTable(Model element) {
		createRepresentation(element, DOCUMENTATION_TABLE);
	}

	public void createUseCaseCrossTable(Model element) {
		createRepresentation(element, USECASE_CROSS_TABLE);
	}

	public void createPackageContainment(Model element) {
		createRepresentation(element, PACKAGE_CONTAINMENT);
	}

	protected void createRepresentation(NamedElement namedElement, String representationId) {
		Session session = SessionManager.INSTANCE.getSession(namedElement);
		RepresentationDescription representationDescription = getRepresentationDescription(namedElement,
				session, representationId);
		createRepresentation(namedElement, namedElement.getName() + " " + representationId,
				representationDescription);
	}

	/**
	 * Get a representation description.
	 * 
	 * @param eObject
	 *            Semantic object
	 * @param session
	 *            Session
	 * @param representationDescriptionId
	 *            Representation description id
	 * @return Representation description
	 */
	private RepresentationDescription getRepresentationDescription(EObject eObject, Session session,
			String representationDescriptionId) {
		Collection<RepresentationDescription> representationDescriptions = DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), eObject);
		for (RepresentationDescription representationDescription : representationDescriptions) {
			if (representationDescriptionId.equals(representationDescription.getName())) {
				return representationDescription;
			}
		}
		return null;
	}

	/**
	 * Create a new representation.
	 * 
	 * @param eObject
	 *            Semantic element
	 * @param representationName
	 *            Representation name
	 * @param representationDescription
	 *            Representation description
	 */
	private void createRepresentation(final EObject eObject, final String representationName,
			final RepresentationDescription representationDescription) {
		final Session session = SessionManager.INSTANCE.getSession(eObject);
		DRepresentation representation = DialectManager.INSTANCE.createRepresentation(representationName,
				eObject, representationDescription, session, new NullProgressMonitor());
		openRepresentation(representation);
	}
}
