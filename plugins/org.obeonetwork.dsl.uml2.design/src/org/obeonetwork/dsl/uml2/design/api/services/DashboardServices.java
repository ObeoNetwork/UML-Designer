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
package org.obeonetwork.dsl.uml2.design.api.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.internal.services.ElementServices;
import org.obeonetwork.dsl.uml2.design.internal.services.LogServices;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * A set of services to handle the Dashboard.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DashboardServices {
	private static final String PACKAGE_HIERARCHY = "Package Hierarchy"; //$NON-NLS-1$

	private static final String CLASS_DIAGRAM = "Class Diagram"; //$NON-NLS-1$

	private static final String COMPONENT_DIAGRAM = "Component Diagram"; //$NON-NLS-1$

	private static final String COMPOSITE_STRUCTURE_DIAGRAM = "Composite Structure Diagram"; //$NON-NLS-1$

	private static final String DEPLOYMENT_DIAGRAM = "Deployment Diagram"; //$NON-NLS-1$

	private static final String STATEMACHINE_DIAGRAM = "State Machine Diagram"; //$NON-NLS-1$

	private static final String ACTIVITY_DIAGRAM = "Activity Diagram"; //$NON-NLS-1$

	private static final String PROFILE_DIAGRAM = "Profile Diagram"; //$NON-NLS-1$

	private static final String USECASE_DIAGRAM = "Use Case Diagram"; //$NON-NLS-1$

	private static final String SEQUENCE_DIAGRAM = "Sequence Diagram"; //$NON-NLS-1$

	private static final String DOCUMENTATION_TABLE = "Documentation Table"; //$NON-NLS-1$

	private static final String USECASE_CROSS_TABLE = "Use Case Cross Table"; //$NON-NLS-1$

	private static final String PACKAGE_CONTAINMENT = "Package Containment"; //$NON-NLS-1$

	/**
	 * ID of the dashboard diagram defined in the odesign.
	 */
	public static final String DASHBOARD_DIAGRAM_DESCRIPTION_ID = "Dashboard"; //$NON-NLS-1$

	/**
	 * ID the dashboard viewpoint defined in the odesign.
	 */
	public static final String DASHBOARD_VP = "Dashboard"; //$NON-NLS-1$

	/**
	 * Create activity diagram.
	 *
	 * @param element
	 *            Model
	 */
	public void createActivityDiagram(Activity element) {
		createRepresentation(element, ACTIVITY_DIAGRAM);
	}

	/**
	 * Create class diagram.
	 *
	 * @param element
	 *            Model
	 */
	public void createClassDiagram(Model element) {
		createRepresentation(element, CLASS_DIAGRAM);
	}

	/**
	 * Create component diagram.
	 *
	 * @param element
	 *            Model
	 */
	public void createComponentDiagram(Model element) {
		createRepresentation(element, COMPONENT_DIAGRAM);
	}

	/**
	 * Create composite structure diagram.
	 *
	 * @param element
	 *            Model
	 */
	public void createCompositeStructureDiagram(Model element) {
		createRepresentation(element, COMPOSITE_STRUCTURE_DIAGRAM);
	}

	/**
	 * Create deployment diagram.
	 *
	 * @param element
	 *            Model
	 */
	public void createDeploymentDiagram(Model element) {
		createRepresentation(element, DEPLOYMENT_DIAGRAM);
	}

	/**
	 * Create documentation table.
	 *
	 * @param element
	 *            Model
	 */
	public void createDocumentationTable(Model element) {
		createRepresentation(element, DOCUMENTATION_TABLE);
	}

	/**
	 * Create interaction a new interaction in package.
	 *
	 * @param pkg
	 *            Package containing new interaction.
	 * @return Interaction
	 */
	public Interaction createInteraction(EObject pkg) {
		final UMLFactory factory = UMLFactory.eINSTANCE;
		final Interaction interaction = factory.createInteraction();
		interaction.setName(ElementServices.INSTANCE.getNewInteractionName(pkg));
		((Package)pkg).getPackagedElements().add(interaction);
		return interaction;
	}

	/**
	 * Create package containment diagram.
	 *
	 * @param element
	 *            Model
	 */
	public void createPackageContainment(Model element) {
		createRepresentation(element, PACKAGE_CONTAINMENT);
	}

	/**
	 * Create package diagram.
	 *
	 * @param element
	 *            Model
	 */
	public void createPackageDiagram(Model element) {
		createRepresentation(element, PACKAGE_HIERARCHY);
	}

	/**
	 * Create profile diagram.
	 *
	 * @param element
	 *            Model
	 */
	public void createProfileDiagram(Profile element) {
		createRepresentation(element, PROFILE_DIAGRAM);
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
		final DRepresentation representation = DialectManager.INSTANCE.createRepresentation(
				representationName, eObject, representationDescription, session, new NullProgressMonitor());
		openRepresentation(representation);
	}

	protected void createRepresentation(NamedElement namedElement, String representationId) {
		final Session session = SessionManager.INSTANCE.getSession(namedElement);
		final RepresentationDescription representationDescription = getRepresentationDescription(namedElement,
				session, representationId);
		createRepresentation(namedElement, namedElement.getName() + " " + representationId, //$NON-NLS-1$
				representationDescription);
	}

	/**
	 * Create sequence diagram.
	 *
	 * @param element
	 *            Model
	 */
	public void createSequenceDiagram(Interaction element) {
		createRepresentation(element, SEQUENCE_DIAGRAM);
	}

	/**
	 * Create statemachine diagram.
	 *
	 * @param element
	 *            Model
	 */
	public void createStateMachineDiagram(StateMachine element) {
		createRepresentation(element, STATEMACHINE_DIAGRAM);
	}

	/**
	 * Create use case cross table.
	 *
	 * @param element
	 *            Model
	 */
	public void createUseCaseCrossTable(Model element) {
		createRepresentation(element, USECASE_CROSS_TABLE);
	}

	/**
	 * Create use case diagram.
	 *
	 * @param element
	 *            Model
	 */
	public void createUseCaseDiagram(Model element) {
		createRepresentation(element, USECASE_DIAGRAM);
	}

	/**
	 * Check if the dashboard representation already exists.
	 *
	 * @param any
	 *            Semantic element
	 * @return True if dashboard does not exist otherwise false.
	 */
	public boolean doesDashboardNotExist(EObject any) {
		final Session session = SessionManager.INSTANCE.getSession(any);
		final Collection<DRepresentation> representations = DialectManager.INSTANCE
				.getAllRepresentations(session);
		for (final DRepresentation representation : representations) {
			if (representation instanceof DSemanticDiagram) {
				final DSemanticDiagram diagram = (DSemanticDiagram)representation;
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
	private Collection<DRepresentation> getAllRepresentations(EObject any) {
		final Session session = SessionManager.INSTANCE.getSession(any);
		final Collection<DRepresentation> representations = DialectManager.INSTANCE
				.getAllRepresentations(session);
		final List<DRepresentation> result = Lists.newArrayList(representations);
		for (final DRepresentation representation : representations) {
			if (representation instanceof DSemanticDiagram) {
				final DSemanticDiagram diagram = (DSemanticDiagram)representation;
				if (DASHBOARD_DIAGRAM_DESCRIPTION_ID.equals(diagram.getDescription().getName())) {
					result.remove(representation);
				}
			}
		}

		return result;
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
		final Collection<RepresentationDescription> representationDescriptions = DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), eObject);
		for (final RepresentationDescription representationDescription : representationDescriptions) {
			if (representationDescriptionId.equals(representationDescription.getName())) {
				return representationDescription;
			}
		}
		return null;
	}

	/**
	 * Get the all representations except the dashboard sorted by name.
	 *
	 * @param any
	 *            Semantic element
	 * @return All the representations available in the session except the dashboard.
	 */
	public Collection<DRepresentation> getRepresentationsSortedByName(EObject any) {
		final Collection<DRepresentation> representations = getAllRepresentations(any);
		final Function<DRepresentation, String> getNameFunction = new Function<DRepresentation, String>() {
			public String apply(DRepresentation from) {
				return from.getName().toLowerCase();
			}
		};
		final Ordering<DRepresentation> nameOrdering = Ordering.natural().onResultOf(getNameFunction);
		return nameOrdering.immutableSortedCopy(representations);
	}

	/**
	 * Open contextual help.
	 *
	 * @param any
	 *            Semantic element
	 * @param contextID
	 *            Context ids are defined in the html/contexts.xml file in org.obeonetwork.dsl.uml2.design.doc
	 *            project.
	 */
	public void openContextHelp(EObject any, String contextID) {
		if (PlatformUI.getWorkbench() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
				&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell() != null) {

			// Context ids are defined in the html/contexts.xml file in
			// org.obeonetwork.dsl.uml2.design.doc project.
			final String contextId = "org.obeonetwork.dsl.uml2.design.doc." + contextID; //$NON-NLS-1$

			PlatformUI.getWorkbench().getHelpSystem()
					.setHelp(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), contextId);
			PlatformUI.getWorkbench().getHelpSystem().displayDynamicHelp();
		}
	}

	/**
	 * Open an existing representation.
	 *
	 * @param representation
	 *            The representation to open
	 */
	public void openRepresentation(final DRepresentation representation) {
		final Session session = SessionManager.INSTANCE
				.getSession(((DSemanticDecorator)representation).getTarget());

		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) {
					monitor.beginTask("Open representation " + representation.getName(), 100); //$NON-NLS-1$
					DialectUIManager.INSTANCE.openEditor(session, representation, monitor);
					monitor.done();
				}
			});
		} catch (final InvocationTargetException e) {
			LogServices.INSTANCE.error("Open representation " + representation.getName() + " failed", e); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (final InterruptedException e) {
			LogServices.INSTANCE.error("Open representation " + representation.getName() + " failed", e); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
