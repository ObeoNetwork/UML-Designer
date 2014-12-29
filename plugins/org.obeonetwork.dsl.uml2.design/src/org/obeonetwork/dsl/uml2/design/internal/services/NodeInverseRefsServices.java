/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.uml2.common.util.DerivedUnionEObjectEList;
import org.eclipse.uml2.uml.Property;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

/**
 * Utilities to manage node inverse references.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class NodeInverseRefsServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final NodeInverseRefsServices INSTANCE = new NodeInverseRefsServices();

	/**
	 * Hidden constructor.
	 */
	private NodeInverseRefsServices() {

	}

	/**
	 * Retrieve the cross references of the association of all the UML elements displayed as node in a
	 * Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getAssociationInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Association"); //$NON-NLS-1$
	}

	/**
	 * Retrieve the cross references of the component realization of all the UML elements displayed as node in
	 * a Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getComponentRealizationInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "ComponentRealization"); //$NON-NLS-1$
	}

	/**
	 * Retrieve the cross references of the connector of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getConnectorInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Connector"); //$NON-NLS-1$
	}

	/**
	 * Retrieve the cross references of the dependency of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getDependencyInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Dependency"); //$NON-NLS-1$
	}

	/**
	 * Retrieve the cross references of the dependency of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getDependencyOnlyInverseRefs(DDiagram diagram) {
		final Predicate<EObject> predicate = new Predicate<EObject>() {
			public boolean apply(EObject eObj) {
				final String className = eObj.eClass().getName();
				return "Dependency".equals(className); //$NON-NLS-1$
			}
		};
		return Collections2.filter(getDependencyInverseRefs(diagram), predicate);
	}

	/**
	 * Retrieve the cross references of the dependency of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getDependencyOrManifestationInverseRefs(DDiagram diagram) {
		final Predicate<EObject> predicate = new Predicate<EObject>() {
			public boolean apply(EObject eObj) {
				final String className = eObj.eClass().getName();
				return "Dependency".equals(className) || "Manifestation".equals(className); //$NON-NLS-1$ //$NON-NLS-2$
			}
		};
		return Collections2.filter(getDependencyInverseRefs(diagram), predicate);
	}

	/**
	 * Retrieve the cross references of the deployment of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getDeploymentInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Deployment"); //$NON-NLS-1$
	}

	/**
	 * Retrieve the cross references of the generalization of all the UML elements displayed as node in a
	 * Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getGeneralizationInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Generalization"); //$NON-NLS-1$
	}

	/**
	 * Retrieve the cross references of the interface realization of all the UML elements displayed as node in
	 * a Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getInterfaceRealizationInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "InterfaceRealization"); //$NON-NLS-1$
	}

	/**
	 * Retrieve the cross references of the given type of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @param typeName
	 *            the expected type.
	 * @return the list of cross reference of the given
	 */
	@SuppressWarnings("unchecked")
	public Collection<EObject> getNodeInverseRefs(DDiagram diagram, String typeName) {
		final Set<EObject> result = Sets.newLinkedHashSet();
		if (diagram instanceof DSemanticDecorator) {
			final Session sess = SessionManager.INSTANCE
					.getSession(((DSemanticDecorator)diagram).getTarget());

			final Iterator<EObject> it = Iterators.transform(
					Iterators.filter(diagram.eAllContents(), AbstractDNode.class),
					new Function<AbstractDNode, EObject>() {

						public EObject apply(AbstractDNode input) {
							return input.getTarget();
						}
					});
			while (it.hasNext()) {
				final EObject displayedAsANode = it.next();
				if (displayedAsANode != null) {
					for (final Setting xRef : sess.getSemanticCrossReferencer().getInverseReferences(
							displayedAsANode)) {
						final EObject eObject = xRef.getEObject();

						if (xRef instanceof DerivedUnionEObjectEList) {
							for (final EObject eObject2 : (List<EObject>)xRef) {
								if (sess.getModelAccessor().eInstanceOf(eObject2, typeName)) {
									result.add(eObject2);
								}
							}
						}
						if (sess.getModelAccessor().eInstanceOf(eObject, typeName)) {
							result.add(eObject);
						}
						/*
						 * In the case of an association the real interesting object is the association linked
						 * to the Property and not the direct cross reference.
						 */
						if (eObject instanceof Property) {
							if (((Property)eObject).getAssociation() != null) {
								if (sess.getModelAccessor().eInstanceOf(((Property)eObject).getAssociation(),
										typeName)) {
									result.add(((Property)eObject).getAssociation());
								}
							}
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Retrieve the cross references of the given type of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param containerView
	 *            a diagram.
	 * @param typeName
	 *            the expected type.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getNodeInverseRefs(DDiagramElement containerView, String typeName) {
		final Set<EObject> result = Sets.newLinkedHashSet();
		final Session sess = SessionManager.INSTANCE.getSession(containerView.getTarget());

		final Iterator<EObject> it = Iterators.transform(
				Iterators.filter(containerView.eAllContents(), AbstractDNode.class),
				new Function<AbstractDNode, EObject>() {

					public EObject apply(AbstractDNode input) {
						return input.getTarget();
					}
				});
		while (it.hasNext()) {
			final EObject displayedAsANode = it.next();
			if (displayedAsANode != null) {
				for (final Setting xRef : sess.getSemanticCrossReferencer().getInverseReferences(
						displayedAsANode)) {
					final EObject eObject = xRef.getEObject();
					if (sess.getModelAccessor().eInstanceOf(eObject, typeName)) {
						result.add(eObject);
					}
					/*
					 * In the case of an association the real interesting object is the association linked to
					 * the Property and not the direct cross reference.
					 */
					if (eObject instanceof Property) {
						if (((Property)eObject).getAssociation() != null) {
							if (sess.getModelAccessor().eInstanceOf(((Property)eObject).getAssociation(),
									typeName)) {
								result.add(((Property)eObject).getAssociation());
							}
						}

					}
				}
			}
		}

		return result;
	}

	/**
	 * Retrieve the cross references of the template binding of all the UML elements displayed as node in a
	 * Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getTemplateBindingInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "TemplateBinding"); //$NON-NLS-1$
	}

	/**
	 * Retrieve the cross references of the usage of all the UML elements displayed as node in a Diagram. Note
	 * that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getUsageInverseRefs(DDiagram diagram) {
		return getNodeInverseRefs(diagram, "Usage"); //$NON-NLS-1$
	}
}
