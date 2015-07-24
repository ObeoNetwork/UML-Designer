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
package org.obeonetwork.dsl.uml2.design.api.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.internal.dialogs.ModelElementSelectionDialog;
import org.obeonetwork.dsl.uml2.design.internal.services.AssociationServices;
import org.obeonetwork.dsl.uml2.design.internal.services.ElementServices;
import org.obeonetwork.dsl.uml2.design.internal.services.LabelServices;
import org.obeonetwork.dsl.uml2.design.internal.services.NodeInverseRefsServices;
import org.obeonetwork.dsl.uml2.design.internal.services.OperationServices;
import org.obeonetwork.dsl.uml2.design.internal.services.StereotypeServices;
import org.obeonetwork.dsl.uml2.design.internal.services.UIServices;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

/**
 * A set of services to handle the Class diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ClassDiagramServices extends AbstractDiagramServices {

	/**
	 * Compute the label of the given association.
	 *
	 * @param association
	 *            the {@link Association} for which to retrieve a label.
	 * @return the computed label.
	 */
	public String computeAssociationBeginLabel(Association association) {
		return LabelServices.INSTANCE.computeAssociationBeginLabel(association);
	}

	/**
	 * Compute the label of the given association.
	 *
	 * @param association
	 *            the {@link Association} for which to retrieve a label.
	 * @return the computed label.
	 */
	public String computeAssociationEndLabel(Association association) {
		return LabelServices.INSTANCE.computeAssociationEndLabel(association);
	}

	/**
	 * Create an operation in a class.
	 *
	 * @param type
	 *            the container {@link org.eclipse.uml2.uml.Type} element
	 * @return New operation
	 */
	public Operation createOperation(org.eclipse.uml2.uml.Type type) {
		return OperationServices.INSTANCE.createOperation(type);
	}

	/**
	 * Iterate over the given {@link Collection} of root elements to find a {@link Type} element with the
	 * given name.
	 *
	 * @param roots
	 *            the elements to inspect
	 * @param typeName
	 *            the name to match
	 * @return the found {@link Type} or <code>null</code>
	 */
	public Type findTypeByName(Collection<EObject> roots, String typeName) {
		return ElementServices.INSTANCE.findTypeByName(roots, typeName);
	}

	/**
	 * With the given {@link EObject} iterate over root elements to find a {@link Type} element with the given
	 * name.
	 *
	 * @param object
	 *            the elements to inspect
	 * @param typeName
	 *            the name to match
	 * @return the found {@link Type} or <code>null</code>
	 */
	public Type findTypeByName(EObject object, String typeName) {
		return ElementServices.INSTANCE.findTypeByName(object, typeName);
	}

	private void fixAssociation(Association a, Type b) {
		final Property target = AssociationServices.INSTANCE.getTarget(a);
		final Property source = AssociationServices.INSTANCE.getSource(a);
		final Property newOne = UMLFactory.eINSTANCE.createProperty();
		newOne.setUpper(-1);
		newOne.setUpper(0);
		newOne.setType(b);
		// The name is computed by the item provider.
		if (target == null) {
			a.getOwnedEnds().add(newOne);
		} else if (source == null) {
			a.getOwnedEnds().add(newOne);
		} else {
			/*
			 * we already have both property ends, we just need to set the type
			 */
			if (target.getType() == null) {
				target.setType(b);
			} else if (source.getType() == null) {
				source.setType(b);
			}
		}

	}

	/**
	 * Fix association.
	 *
	 * @param host
	 *            Host
	 * @param a
	 *            Association
	 * @param b
	 *            Association
	 */
	public void fixAssociation(EObject host, EObject a, EObject b) {
		if (a instanceof Association && b instanceof Type) {
			fixAssociation((Association)a, (Type)b);
		} else if (b instanceof Association && a instanceof Type) {
			fixAssociation((Association)b, (Type)a);
		}
	}

	/**
	 * Get all the stereotype applications according to the selected diagram.
	 *
	 * @param diagram
	 *            Current diagram
	 * @return Stereotype applications
	 */
	public Collection<Object> getAllStereotypeApplications(DDiagram diagram) {
		final Collection<Object> results = Lists.newArrayList();
		for (final DDiagramElementContainer container : diagram.getContainers()) {
			final EObject target = container.getTarget();
			if (target instanceof Element) {
				results.addAll(((Element)target).getStereotypeApplications());
			}
		}
		return results;
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
	 * Get base class associated to a stereotype application.
	 *
	 * @param stereotypeApplication
	 *            Stereotype application
	 * @return Base class
	 */
	public Element getBaseClass(EObject stereotypeApplication) {
		return StereotypeServices.INSTANCE.getBaseClass(stereotypeApplication);
	}

	/**
	 * Get broken associations.
	 *
	 * @param container
	 *            the current container.
	 * @return a list of association which might be considered as "broken", we are not able to display them as
	 *         edges.
	 */
	public Collection<Association> getBrokenAssociations(EObject container) {
		final Collection<Association> result = new ArrayList<Association>();
		for (final EObject child : container.eContents()) {
			if (child instanceof Association && !(child instanceof AssociationClass)
					&& isBroken((Association)child)) {
				result.add((Association)child);
			}
		}
		return result;

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
		return NodeInverseRefsServices.INSTANCE.getDependencyInverseRefs(diagram);
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
		return NodeInverseRefsServices.INSTANCE.getGeneralizationInverseRefs(diagram);
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
		return NodeInverseRefsServices.INSTANCE.getInterfaceRealizationInverseRefs(diagram);
	}

	/**
	 * Get navigable owned end of an association
	 *
	 * @param association
	 *            Association
	 * @return Association
	 */
	public List<Property> getNavigableOwnedEnds(Association association) {
		final List<Property> ends = Lists.newArrayList();
		final Property source = AssociationServices.INSTANCE.getSource(association);
		final Property target = AssociationServices.INSTANCE.getTarget(association);
		if (source != null) {
			ends.add(source);
		}
		if (target != null) {
			ends.add(target);
		}
		return ends;
	}

	/**
	 * Return a set of classes from model.
	 *
	 * @param element
	 *            an element in model
	 * @return set of classes or empty collection
	 */
	public Collection<Class> getSemanticCandidatesClasses(Element element) {
		final Set<Class> classes = new HashSet<Class>();
		final TreeIterator<EObject> iterator = element.getModel().eAllContents();
		while (iterator.hasNext()) {
			final EObject object = iterator.next();
			if (isTypeOfClass(object) || object instanceof Component) {
				classes.add((Class)object);
			}
		}
		return classes;
	}

	/**
	 * Get the type of the association source end.
	 *
	 * @param association
	 *            Association
	 * @return Type of the source
	 */
	public Type getSourceType(Association association) {
		return AssociationServices.INSTANCE.getSourceType(association);
	}

	/**
	 * Get stereotype application label.
	 *
	 * @param stereotypeApplication
	 *            Stereotype application
	 * @return The stereotype name.
	 */
	public String getStereotypeApplicationLabel(EObject stereotypeApplication) {
		return stereotypeApplication.eClass().getName();
	}

	/**
	 * Get tagged value label.
	 *
	 * @param feature
	 *            Feature
	 * @param view
	 *            Stereotype application view
	 * @return Tagged value label featureName = value
	 */
	public String getTaggedValueLabel(EStructuralFeature feature, DDiagramElement view) {
		final DDiagramElement stereotypeApplicationView = (DDiagramElement)view.eContainer();
		final EObject stereotypeApplication = stereotypeApplicationView.getTarget();

		return feature.getName() + "=" + stereotypeApplication.eGet(feature); //$NON-NLS-1$
	}

	/**
	 * Get tagged values.
	 *
	 * @param stereotypeApplication
	 *            Container
	 * @return Collection of tagged values
	 */
	public Collection<Object> getTaggedValues(EObject stereotypeApplication) {
		final Collection<Object> results = Lists.newArrayList();
		for (final EStructuralFeature feature : stereotypeApplication.eClass().getEAllStructuralFeatures()) {
			if (!feature.getName().startsWith("base_")) { //$NON-NLS-1$
				results.add(feature);
			}
		}

		return results;
	}

	/**
	 * Get the type of the association target end.
	 *
	 * @param association
	 *            Association
	 * @return Type of the target
	 */
	public Type getTargetType(Association association) {
		return AssociationServices.INSTANCE.getTargetType(association);
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
		return NodeInverseRefsServices.INSTANCE.getTemplateBindingInverseRefs(diagram);
	}

	/**
	 * Get types.
	 *
	 * @param association
	 *            Association
	 * @return List of types
	 */
	public List<Type> getTypes(Association association) {
		return AssociationServices.INSTANCE.getTypes(association);
	}

	/**
	 * Return collection of visible association class in a diagram.
	 *
	 * @param diagram
	 *            Diagram
	 * @param container
	 *            Container of the associationClass
	 * @return Set of visible association Classes or empty collection
	 */
	public Collection<EObject> getVisibleAssociationClass(DDiagram diagram, EObject container) {
		final Set<EObject> associationClasses = new HashSet<EObject>();
		final Collection<EObject> displayedNodes = UIServices.INSTANCE.getDisplayedNodes(diagram);
		final Collection<EObject> associations = getAssociationInverseRefs(diagram);
		for (final EObject association : associations) {
			if (association instanceof AssociationClass) {
				final Property source = AssociationServices.INSTANCE.getSource((AssociationClass)association);
				final Property target = AssociationServices.INSTANCE.getTarget((AssociationClass)association);
				final Type sourceType = source.getType();
				final Type targetType = target.getType();
				if (sourceType != null && displayedNodes.contains(sourceType) && targetType != null
						&& displayedNodes.contains(targetType)) {
					if (container == association.eContainer()) {
						associationClasses.add(association);
					}
				}
			}
		}
		return associationClasses;
	}
	private boolean isBroken(Association child) {
		final Property target = AssociationServices.INSTANCE.getTarget(child);
		final Property source = AssociationServices.INSTANCE.getSource(child);
		if (target != null && target.getType() != null) {
			if (source != null && source.getType() != null) {
				return false;
			}
		}
		return true;
	}

	private boolean isComposite(Property property) {
		return property != null && property.isComposite();
	}

	private boolean isNavigable(Property property) {
		return property != null && property.isNavigable();
	}

	/**
	 * Check an element is not a Class.
	 *
	 * @param element
	 *            Element
	 * @return return true if the element is not a Class
	 */
	public boolean isNotTypeOfClass(EObject element) {
		return !isTypeOfClass(element);
	}

	/**
	 * Check if an element is a package.
	 *
	 * @param element
	 *            Element
	 * @return True if element is a package
	 */
	public boolean isPackage(EObject element) {
		return element instanceof Package;
	}

	private boolean isShared(Property property) {
		return property != null && AggregationKind.SHARED_LITERAL.equals(property.getAggregation());
	}

	/**
	 * Check is a feature is static.
	 *
	 * @param feature
	 *            Feature
	 * @return True if it is a static feature
	 */
	public boolean isStatic(Feature feature) {
		return feature != null && feature.isStatic();
	}

	/**
	 * Check if an element is type of class.
	 *
	 * @param element
	 *            Element
	 * @return True if element is a class
	 */
	public boolean isTypeOfClass(EObject element) {
		return "Class".equals(element.eClass().getName()); //$NON-NLS-1$
	}
	/**
	 * Open type selection dialog.
	 *
	 * @param element
	 *            Element
	 * @return Selected element
	 */
	public Element openSelectTypeDialog(Element element) {
		final ModelElementSelectionDialog dlg = new ModelElementSelectionDialog();
		dlg.setTitle("New typed property selection"); //$NON-NLS-1$
		dlg.setMessage("Please select a type for the new property :"); //$NON-NLS-1$
		dlg.setSelectablePredicate(new Predicate<Object>() {
			public boolean apply(Object input) {
				return !(input instanceof Stereotype)
						&& (input instanceof Class || input instanceof Interface || input instanceof DataType);
			}
		});
		final int status = dlg.open();
		if (status == Window.OK) {
			final Object[] results = dlg.getResult();
			if (results != null && results.length > 0) {
				return (Element)results[0];
			}
		}
		return null;
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
	 * @return True if source is navigable
	 */
	public boolean sourceIsNavigable(Association association) {
		final Property source = AssociationServices.INSTANCE.getSource(association);
		return isNavigable(source);
	}

	/**
	 * Check is an association source is navigable and composite.
	 *
	 * @param association
	 *            Association
	 * @return True if source is navigable and composite
	 */
	public boolean sourceIsNavigableAndComposite(Association association) {
		final Property source = AssociationServices.INSTANCE.getSource(association);
		return isNavigable(source) && isComposite(source);
	}

	/**
	 * Check is an association source is navigable and shared.
	 *
	 * @param association
	 *            Association
	 * @return True if source is navigable and shared
	 */
	public boolean sourceIsNavigableAndShared(Association association) {
		final Property source = AssociationServices.INSTANCE.getTarget(association);
		return isNavigable(source) && isShared(source);
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
	public boolean targetIsNavigableAndComposite(Association association) {
		final Property target = AssociationServices.INSTANCE.getTarget(association);
		return isNavigable(target) && isComposite(target);
	}

	/**
	 * Check is an association target is navigable and shared.
	 *
	 * @param association
	 *            Association
	 * @return True if target is navigable and shared
	 */
	public boolean targetIsNavigableAndShared(Association association) {
		final Property target = AssociationServices.INSTANCE.getTarget(association);
		return isNavigable(target) && isShared(target);
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
