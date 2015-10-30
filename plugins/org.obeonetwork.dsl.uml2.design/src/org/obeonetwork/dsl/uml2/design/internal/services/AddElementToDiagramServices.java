/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Device;
import org.eclipse.uml2.uml.ExecutionEnvironment;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.UseCase;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * @author Bats Frederic <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class AddElementToDiagramServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final AddElementToDiagramServices INSTANCE = new AddElementToDiagramServices();

	/**
	 * Hidden constructor.
	 */
	private AddElementToDiagramServices() {

	}

	/**
	 * Get mappings available for a semantic element and a given diagram.
	 *
	 * @param semanticElement
	 *            Semantic element
	 * @param diagram
	 *            Container view
	 * @param session
	 *            Session
	 * @return List of mappings which could not be null
	 */
	public List<DiagramElementMapping> getValidMappingForDiagram(final EObject semanticElement,
			final DSemanticDiagram diagram, Session session) {
		final List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();
		// check semantic element could be add to diagram
		if (!isValidForDiagram(diagram, null).apply(semanticElement)) {
			return mappings;
		}

		final ModelAccessor modelAccessor = session.getModelAccessor();

		for (final DiagramElementMapping mapping : diagram.getDescription().getAllContainerMappings()) {
			final String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
			if (modelAccessor.eInstanceOf(semanticElement, domainClass) && !mapping.isCreateElements()) {
				mappings.add(mapping);
			}
		}
		for (final DiagramElementMapping mapping : diagram.getDescription().getAllNodeMappings()) {
			final String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
			if (modelAccessor.eInstanceOf(semanticElement, domainClass) && !mapping.isCreateElements()) {
				mappings.add(mapping);
			}
		}

		return mappings;
	}

	private Predicate<Object> getValidsForClassDiagram() {
		final Predicate<Object> validForClassDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				return input instanceof Package || input instanceof Interface || input instanceof DataType
						|| "Class".equals(((EObject)input).eClass().getName()) //$NON-NLS-1$
						|| "Component".equals(((EObject)input).eClass().getName()); //$NON-NLS-1$
			}
		};
		return validForClassDiagram;
	}

	private Predicate<Object> getValidsForComponentDiagram() {
		final Predicate<Object> validForComponentDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				return input instanceof Package || input instanceof Interface
						|| "Class".equals(((EObject)input).eClass().getName()) //$NON-NLS-1$
						|| "Component".equals(((EObject)input).eClass().getName()); //$NON-NLS-1$
			}
		};
		return validForComponentDiagram;
	}

	private Predicate<Object> getValidsForCompositeDiagram() {
		final Predicate<Object> validForCompositeDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				if (input instanceof StructuredClassifier) {
					return !(input instanceof Interaction || input instanceof StateMachine
							|| input instanceof Activity);
				}
				return input instanceof Package || input instanceof Interface
						|| "Port".equals(((EObject)input).eClass().getName()) //$NON-NLS-1$
						|| "Property".equals(((EObject)input).eClass().getName()); //$NON-NLS-1$

			}
		};
		return validForCompositeDiagram;
	}


	private Predicate<Object> getValidsForCompositeStructureDiagram(final DDiagram diagram,
			final EObject container) {
		final Predicate<Object> validForCompositeDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				final EObject containerDiagram = ((DSemanticDiagram)diagram).getTarget();
				if (input instanceof StructuredClassifier && containerDiagram instanceof StructuredClassifier
						|| input instanceof Property && !(input instanceof Port)
						&& container.equals(((EObject)input).eContainer())) {
					return EcoreUtil.isAncestor(containerDiagram, (EObject)input);
				}
				if (input instanceof Interface && isInterfacesLayerActive(diagram)) {
					return true;
				}
				return input instanceof StructuredClassifier;
			}
		};
		return validForCompositeDiagram;
	}

	private Predicate<Object> getValidsForDeploymentDiagram() {
		final Predicate<Object> validForDeploymentDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				return input instanceof Package && !(input instanceof Profile)
						|| input instanceof ExecutionEnvironment || input instanceof Node
						|| input instanceof Artifact || input instanceof Device || input instanceof Component;
			}
		};
		return validForDeploymentDiagram;
	}

	/**
	 * Get valid elements for a diagram.
	 *
	 * @param cur
	 *            Element
	 * @return List of valid elements for the current representation
	 */
	private Predicate<Object> getValidsForPackageDiagram() {
		final Predicate<Object> validForPackageDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				return input instanceof Package;
			}
		};
		return validForPackageDiagram;
	}

	private Predicate<Object> getValidsForUseCaseDiagram() {
		final Predicate<Object> validForUseCaseDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				return input instanceof Package || input instanceof Class || input instanceof Component
						|| input instanceof Artifact || input instanceof DataType
						|| input instanceof Interface || input instanceof Collaboration
						|| input instanceof UseCase;
			}
		};
		return validForUseCaseDiagram;
	}

	/**
	 * Is the interfaces layer active. Diagram element
	 *
	 * @return True if the layer named "Interfaces" is active otherwise false
	 */
	private boolean isInterfacesLayerActive(DDiagram diagram) {
		for (final Layer activeLayer : diagram.getActivatedLayers()) {
			if ("Interfaces".equals(activeLayer.getName())) { //$NON-NLS-1$
				return true;
			}
		}
		return false;
	}

	/**
	 * Get valid elements for a diagram.
	 *
	 * @param diagram
	 *            diagram
	 * @param container
	 *            selected container
	 * @return List of valid elements for the current representation
	 */
	public Predicate<Object> isValidForDiagram(DDiagram diagram, EObject container) {
		Predicate<Object> results = Predicates.alwaysTrue();
		if (diagram instanceof DSemanticDiagram) {
			final DiagramDescription description = ((DSemanticDiagram)diagram).getDescription();

			if ("Class Diagram".equals(description.getName()) //$NON-NLS-1$
					|| "Profile Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForClassDiagram();
			} else if ("Component Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForComponentDiagram();
			} else if ("Composite Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForCompositeDiagram();
			} else if ("Composite Structure Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForCompositeStructureDiagram(diagram, container);
			} else if ("Deployment Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForDeploymentDiagram();
			} else if ("Package Hierarchy".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForPackageDiagram();
			} else if ("Use Case Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForUseCaseDiagram();
			}
		}

		return results;
	}
}
