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
package org.obeonetwork.dsl.uml2.core.internal.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeListSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeSpec;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.core.api.wizards.ModelElementsSelectionDialog;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

/**
 * Services to handle typed Association concerns.
 *
 * @author Melanie Bats
 *         <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class AssociationServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final AssociationServices INSTANCE = new AssociationServices();

	/**
	 * Hidden constructor.
	 */
	private AssociationServices() {

	}

	/**
	 * Add Association end.
	 *
	 * @param association
	 *            association
	 */
	public void addAssociationEnd(final Association association) {
		if (createNaryAssociationPrecondition(association)) {
			final ModelElementsSelectionDialog dlg = new ModelElementsSelectionDialog("Create N-ary Association", "Select additional elements to add to the association." + System.lineSeparator()
			+ " At least one element have to be selected, else n-Ary association will not be created " + System.lineSeparator());
			final List<Element> endOwners = new ArrayList<Element>();
			for (final Property property : association.getMemberEnds()) {
				endOwners.add(property.getType());
			}
			dlg.setGrayedPredicate(new Predicate<EObject>() {
				public boolean apply(EObject input) {
					if (endOwners.contains(input)) {
						return true;
					} else if (input instanceof Class || input instanceof AssociationClass || input instanceof Interface || input instanceof Enumeration || input instanceof DataType
							|| input instanceof PrimitiveType) {
						return false;
					}
					return true;
				}
			});
			final List<?> elementsToAdd = dlg.open(association);
			if (elementsToAdd.size() > 0) {
				for (final Object element : elementsToAdd) {
					if (element instanceof Type) {
						final Property end = createAssociationEnd((Type) element);
						association.getOwnedEnds().add(end);
						association.getMemberEnds().add(end);
						association.getNavigableOwnedEnds().add(end);
					}
				}
			}
		}
	}

	/**
	 * Create association end.
	 *
	 * @param type
	 *            type of end
	 * @return property
	 */
	public Property createAssociationEnd(Type type) {
		final Property property = UMLFactory.eINSTANCE.createProperty();
		property.setName(getAssociationEndsName(type));
		property.setType(type);
		property.setLower(0);
		property.setUpper(-1);
		return property;
	}

	/**
	 * Precondition for n-ary association creation.
	 *
	 * @param object
	 *            selected association
	 * @return true if association is binary and no end have no qualifiers
	 */
	public boolean createNaryAssociationPrecondition(EObject object) {
		// aql:self.oclIsKindOf(uml::Association) and
		// self.oclAsType(uml::Association).getEndTypes()->size()<=2
		if (object instanceof Association) {
			boolean res = true;
			// if (((Association)object).getMemberEnds().size() == 2) {
			for (final Property end : ((Association) object).getMemberEnds()) {
				if (!end.getQualifiers().isEmpty()) {
					res = false;
				}
			}
			// }
			return res;
		}
		return false;
	}

	/**
	 * Compute Association end name.
	 *
	 * @param type
	 *            type of end
	 * @return name
	 */
	public String getAssociationEndsName(Type type) {
		String name = ((NamedElement) type).getName();
		if (!com.google.common.base.Strings.isNullOrEmpty(name)) {
			final char c[] = name.toCharArray();
			c[0] = Character.toLowerCase(c[0]);
			name = new String(c) + 's';
		}
		return name;
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
		return NodeInverseRefsServices.INSTANCE.getAssociationInverseRefs(diagram);
	}

	/**
	 * Get association end qualifier for a classifier.
	 *
	 * @param classifier
	 *            association end
	 * @param diagram
	 *            Diagram
	 * @return List of qualifier
	 */
	public List<Property> getSemanticCandidatesQualifier(Classifier classifier, DDiagram diagram) {
		final List<Property> qualifiers = new ArrayList<Property>();
		final Collection<EObject> associations = getAssociationInverseRefs(diagram);
		for (final EObject association : associations) {
			for (final Property end : ((Association)association).getMemberEnds()) {
				if (((Association)association).getMemberEnds().size() <= 2 && end.getType().equals(classifier)
						&& !end.getQualifiers().isEmpty()
						&& getVisibleAssociationEnds((Association)association, diagram).size() >= 2) {
					qualifiers.add(end);
				}
			}
		}
		return qualifiers;
	}

	/**
	 * Return the source of an association.
	 *
	 * @param association
	 *            the {@link Association} context
	 * @return first end of the association
	 */
	public Property getSource(Association association) {
		if (association.getMemberEnds() != null && association.getMemberEnds().size() > 0) {
			return association.getMemberEnds().get(0);
		}
		return null;
	}

	/**
	 * Get the source of an n-ary association.
	 *
	 * @param association
	 *            the association
	 * @param view
	 *            the edge to retrieve the source end
	 * @return end
	 */
	public Property getSourceEndAssociation(Association association, DDiagramElement view) {

		if (view instanceof DEdge) {
			final DEdge edge = (DEdge) view;
			final List<Property> members = association.getMemberEnds();
			final EdgeTarget edgeSource = edge.getSourceNode();
			List<EObject> sourceSemanticElements = new ArrayList<EObject>();

			if (edgeSource instanceof DNodeListSpec) {
				// edge source is a classifier
				final DNodeListSpec source = (DNodeListSpec) edgeSource;
				sourceSemanticElements = source.getSemanticElements();
				for (final EObject sourceEObject : sourceSemanticElements) {
					for (final Property member : members) {
						if (sourceEObject.equals(member.getType())) {
							return member;
						}
					}
				}
			} else if (edgeSource instanceof DNodeSpec) {
				// edge source is a qualifier
				sourceSemanticElements = ((DNodeSpec) edgeSource).getSemanticElements();
				for (final EObject sourceEObject : sourceSemanticElements) {
					for (final Property member : members) {
						if (sourceEObject.equals(member)) {
							return member;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Get the type of the association source end.
	 *
	 * @param association
	 *            Association
	 * @return Type of the source
	 */
	public Type getSourceType(Association association) {
		final Property source = getSource(association);
		if (source != null) {
			return source.getType();
		}
		return null;
	}

	/**
	 * Return the target of an association.
	 *
	 * @param association
	 *            the {@link Association} context
	 * @return second end of the association
	 */
	public Property getTarget(Association association) {
		if (association.getMemberEnds() != null && association.getMemberEnds().size() > 1) {
			return association.getMemberEnds().get(1);
		}
		return null;
	}

	/**
	 * Get the type of the association target end.
	 *
	 * @param association
	 *            Association
	 * @return Type of the target
	 */
	public Type getTargetType(Association association) {
		final Property target = getTarget(association);
		if (target != null) {
			return target.getType();
		}
		return null;
	}

	/**
	 * Get types.
	 *
	 * @param association
	 *            Association
	 * @return List of types
	 */
	public List<Type> getTypes(Association association) {
		final List<Type> types = Lists.newArrayList();
		types.add(getSourceType(association));
		types.add(getTargetType(association));
		return types;
	}

	private List<Property> getVisibleAssociationEnds(Association association, DDiagram diagram) {
		final List<Property> ends = new ArrayList<Property>();
		// Association should be visible in self container
		// At least one of the ends is visible in diagram
		final EList<DDiagramElement> elements = diagram.getDiagramElements();
		// check if at least more than 2 ends are displayed in diagram
		final List<EObject> visibleEndsList = new ArrayList<EObject>();
		for (final DDiagramElement element : elements) {
			visibleEndsList.add(element.getTarget());
		}
		final EList<Property> associationEnds = association.getMemberEnds();
		for (final Property end : associationEnds) {
			if (visibleEndsList.contains(end.getType())) {
				ends.add(end);
			}
			if (end.getType() == null) { // Broken association case
				ends.add(end);
			}
		}
		return ends;
	}

	public boolean isComposite(Property property) {
		return property != null && property.isComposite();
	}

	private boolean isNavigable(Property property) {
		return property != null && property.isNavigable();
	}

	public boolean isShared(Property property) {
		return property != null && AggregationKind.SHARED_LITERAL.equals(property.getAggregation());
	}

	/**
	 * Remove ends or association.
	 *
	 * @param association
	 *            association
	 */
	public void removeAssociationEnd(final Association association) {
		final EList<Property> ends = association.getMemberEnds();
		final ModelElementsSelectionDialog dlg = new ModelElementsSelectionDialog("Remove end Association", //$NON-NLS-1$
				"Select end to remove from association." + System.lineSeparator() //$NON-NLS-1$
				+ " If all ends are selected except one the association will be deleted else end are remove from association " //$NON-NLS-1$
				+ System.lineSeparator());
		final List<Element> endOwners = new ArrayList<Element>();

		dlg.setGrayedPredicate(new Predicate<EObject>() {

			public boolean apply(EObject input) {
				if (endOwners.contains(input)) {
					return true;
				} else if (input instanceof Property) {
					return false;
				}
				return true;
			}
		});
		final List<?> elementsToAdd = dlg.open(association);
		final Session session = SessionManager.INSTANCE.getSession(association);
		TransactionalEditingDomain editingDomain = null;
		if (session != null) {
			editingDomain = session.getTransactionalEditingDomain();
		}
		if (editingDomain != null && elementsToAdd.size() > 0 && ends.containsAll(elementsToAdd) && ends.size() - elementsToAdd.size() >= 2) {
			final RecordingCommand command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					for (final Object element : elementsToAdd) {
						if (element instanceof Element) {
							((Element) element).destroy();
						}
					}
				}
			};
			editingDomain.getCommandStack().execute(command);
		} else if (editingDomain != null && elementsToAdd.size() > 0 && ends.containsAll(elementsToAdd) && ends.size() - elementsToAdd.size() < 2) {
			final RecordingCommand command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					association.destroy();
				}
			};
			editingDomain.getCommandStack().execute(command);
		}
	}

	/**
	 * Check is an association source is composite.
	 *
	 * @param association
	 *            Association
	 * @return True if source is composite
	 */
	public boolean sourceIsComposite(Association association) {
		final Property source = AssociationServices.INSTANCE.getSource(association);
		return isComposite(source);
	}

	/**
	 * Check is an association source is navigable.
	 *
	 * @param association
	 *            Association
	 * @param element
	 *            Edge element
	 * @return True if source is navigable
	 */
	public boolean sourceIsNavigable(Association association, DDiagramElement element) {
		final Property source = AssociationServices.INSTANCE.getSourceEndAssociation(association, element);
		return isNavigable(source);
	}

	/**
	 * Check is an association source is navigable and composite.
	 *
	 * @param association
	 *            Association
	 * @return True if source is navigable and composite
	 */
	public boolean sourceIsNavigableAndTargetIsComposite(Association association) {
		final Property source = AssociationServices.INSTANCE.getSource(association);
		final Property target = AssociationServices.INSTANCE.getTarget(association);
		return isNavigable(source) && isComposite(target);
	}

	/**
	 * Check is an association source is navigable and shared.
	 *
	 * @param association
	 *            Association
	 * @return True if source is navigable and shared
	 */
	public boolean sourceIsNavigableAndTargetIsShared(Association association) {
		final Property source = AssociationServices.INSTANCE.getSource(association);
		final Property target = AssociationServices.INSTANCE.getTarget(association);
		return isNavigable(source) && isShared(target);
	}

	/**
	 * Check is an association source is shared.
	 *
	 * @param association
	 *            Association
	 * @return True if source is shared
	 */
	public boolean sourceIsShared(Association association) {
		final Property source = AssociationServices.INSTANCE.getSource(association);
		return isShared(source);
	}

	/**
	 * Check is an association target is composite.
	 *
	 * @param association
	 *            Association
	 * @return True if target is composite
	 */
	public boolean targetIsComposite(Association association) {
		final Property target = AssociationServices.INSTANCE.getTarget(association);
		return isComposite(target);
	}

	/**
	 * Check is an association target is navigable.
	 *
	 * @param association
	 *            Association
	 * @return True if target is navigable
	 */
	public boolean targetIsNavigable(Association association) {
		final Property target = AssociationServices.INSTANCE.getTarget(association);
		return isNavigable(target);
	}

	/**
	 * Check is an association target is navigable and composite.
	 *
	 * @param association
	 *            Association
	 * @return True if target is navigable and composite
	 */
	public boolean targetIsNavigableAndSourceIsComposite(Association association) {
		final Property target = AssociationServices.INSTANCE.getTarget(association);
		final Property source = AssociationServices.INSTANCE.getSource(association);
		return isNavigable(target) && isComposite(source);
	}
	/**
	 * Check is an association target is navigable and shared.
	 *
	 * @param association
	 *            Association
	 * @return True if target is navigable and shared
	 */
	public boolean targetIsNavigableAndSourceIsShared(Association association) {
		final Property target = AssociationServices.INSTANCE.getTarget(association);
		final Property source = AssociationServices.INSTANCE.getSource(association);
		return isNavigable(target) && isShared(source);
	}

	/**
	 * Check is an association target is shared.
	 *
	 * @param association
	 *            Association
	 * @return True if target is shared
	 */
	public boolean targetIsShared(Association association) {
		final Property target = AssociationServices.INSTANCE.getTarget(association);
		return isShared(target);
	}
}
