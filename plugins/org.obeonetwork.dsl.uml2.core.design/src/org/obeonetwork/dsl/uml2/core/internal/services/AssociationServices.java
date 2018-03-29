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
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeListSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeSpec;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Class;
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
}
