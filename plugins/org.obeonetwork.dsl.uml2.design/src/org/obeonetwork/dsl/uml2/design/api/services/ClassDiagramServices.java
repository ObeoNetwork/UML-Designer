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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
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
	 * Compute label forn_ary association edge.
	 *
	 * @param association
	 * @param view
	 *            edge
	 * @return label
	 */
	public String computeAssociationNAryBeginLabel(Association association, DDiagramElement view) {
		return LabelServices.INSTANCE.computeAssociationBeginLabel(association, view);
	}

	/**
	 * Create a new association.
	 *
	 * @param object
	 * @param source
	 *            selected source
	 * @param target
	 *            selected Target
	 */
	public void createAssociation(EObject object, Element source, Element target) {
		if (source.eContainer() instanceof Package) {
			// tool creation association edge
			if (!(source instanceof Association || target instanceof Association
					|| source instanceof AssociationClass || target instanceof AssociationClass)) {

				final Association association = UMLFactory.eINSTANCE.createAssociation();
				final Property end1 = createAssociationEnd((Type)source);
				association.getMemberEnds().add(end1);
				final Property end2 = createAssociationEnd((Type)target);
				association.getMemberEnds().add(end2);

				association.getOwnedEnds().add(end1);
				association.getOwnedEnds().add(end2);
				((Package)source.eContainer()).getPackagedElements().add(association);
				association.getNavigableOwnedEnds().addAll(getNavigableOwnedEnds(association));

			} else if (source instanceof Association || target instanceof Association) {
				Association association;
				Type type;

				if (source instanceof Association){
					association=(Association)source;
					type = (Type)target;
				}else{
					association = (Association)target;
					type = (Type)source;
				}

				if (isBroken(association)) { // Look for broken association
					fixAssociation(association, type);
				} else { // create new end
					final Property end = createAssociationEnd(type);
					association.getOwnedEnds().add(end);
					association.getMemberEnds().add(end);
				}
			}
		}
	}

	private Property createAssociationEnd(Type type) {
		final Property property = UMLFactory.eINSTANCE.createProperty();
		property.setName(getAssociationEndsName(type));
		property.setType(type);
		property.setLower(0);
		property.setUpper(-1);
		return property;
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
	 * Delete selected edge fron a N-Ary association
	 *
	 * @param association
	 *            association
	 * @param element
	 *            edge to delete
	 */
	public void deleteNAryAssociation(Association association, DDiagramElement element){
		if (isNary(association)){
			final Property end = AssociationServices.INSTANCE.getSourceEndAssociation(association, element);
			end.eContainer();
			EcoreUtil.delete(end);
		}
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

	private void fixAssociation(Association association, Type type) {
		final EList<Property> ends = association.getMemberEnds();
		final List<Property> brokenEnds = new ArrayList<Property>();
		for (final Property end : ends) {
			if (end.getType() == null) {
				brokenEnds.add(end);
			}
		}

		if (brokenEnds.size() > 1) {
			// If several broken links exist user have to select the link to reconnect.

			final ListDialog dialog = new ListDialog(Display.getCurrent().getActiveShell());
			dialog.setTitle("Reconnect broken association end:"); //$NON-NLS-1$
			dialog.setMessage("Please select the end to reconnect: "); //$NON-NLS-1$
			dialog.setInput(brokenEnds.toArray());
			dialog.setContentProvider(new ArrayContentProvider());
			dialog.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return ((NamedElement)element).getName();
				}
			});
			dialog.setInitialSelections(new Object[] {brokenEnds.get(0)});

			final int status = dialog.open();
			if (status == Window.OK) {
				final Object[] types = dialog.getResult();
				if (types != null && types.length == 1) {
					final Property endToFix = (Property)types[0];
					endToFix.setType(type);
					endToFix.setName(getAssociationEndsName(type));
				}
			}
		} else {
			final Property endToFix = brokenEnds.get(0);
			endToFix.setType(type);
			endToFix.setName(getAssociationEndsName(type));
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
	 * Get abstract label format.
	 *
	 * @param object
	 *            EObject
	 * @return Abstract label format
	 */
	public FontFormat getAbstractBoldLabelFormat(EObject object) {
		// Fix to return bold/italic when bug will be fixed on sirius
		return FontFormat.BOLD_LITERAL;
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
	 * Get list of association. Check in diagram only two ends are presents.
	 *
	 * @param container
	 *            package
	 * @param diagram
	 *            diagram
	 * @return list of binary association
	 */
	public List<Association> getAssociation(Package container, DDiagram diagram) {
		final List<Association> result = new ArrayList<Association>();
		final Collection<EObject> associations = getAssociationInverseRefs(diagram);
		final EList<DDiagramElement> elements = diagram.getDiagramElements();
		// List semantic elements visible in diagram
		final List<EObject> visibleSemanticEnds = new ArrayList<EObject>();
		for (final DDiagramElement element : elements) {
			visibleSemanticEnds.add(element.getTarget());
		}
		for (final EObject object : associations) {
			final Association association = (Association)object;
			final EList<Property> ends = association.getMemberEnds();
			int visibleEnds = 0;
			for (final Property end : ends) {
				if (visibleSemanticEnds.contains(end.getType())) {
					visibleEnds++;
				}
				if (end.getType() == null) { // Broken association case
					visibleEnds++;
				}
			}
			// Association should be visible in self container
			// At least one of the ends is visible in diagram
			if (visibleEnds <= 2) {
				result.add(association);
			}
		}
		return result;
	}

	private String getAssociationEndsName(Type type) {
		String name = ((NamedElement)type).getName();
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
			if (child instanceof Association
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
	 * Get list of n-ary association. Check in diagram if more than two ends are presents.
	 *
	 * @param container
	 *            package
	 * @param diagram
	 *            diagram
	 * @return list of n-ary association
	 */
	public List<Association> getNAryAssociation(Package container, DDiagram diagram) {
		final List<Association> result = new ArrayList<Association>();
		final Collection<EObject> associations = getAssociationInverseRefs(diagram);
		for (final EObject object : associations) {
			final Association association = (Association)object;
			// Association should be visible in self container
			// At least one of the ends is visible in diagram
			final EList<DDiagramElement> elements = diagram.getDiagramElements();
			// check if at least more than 2 ends are displayed in diagram
			final List<EObject> visibleEndsList = new ArrayList<EObject>();
			for (final DDiagramElement element : elements) {
				visibleEndsList.add(element.getTarget());
			}
			final EList<Property> ends = association.getMemberEnds();
			int visibleEnds = 0;
			for (final Property end : ends) {
				if (visibleEndsList.contains(end.getType())) {
					visibleEnds++;
				}
				if (end.getType() == null) { // Broken association case
					visibleEnds++;
				}
			}
			if (association.eContainer().equals(container) && visibleEnds > 2) {
				result.add(association);
			}
		}
		return result;
	}

	/**
	 * Get source for n-ary association.
	 *
	 * @param association
	 *            association
	 * @return list of source.
	 */
	public EList<Type> getNAryAssociationSource(Association association) {
		return association.getEndTypes();
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
	 * Get the type of the association source end.
	 * @param association association
	 * @param diagram diagram
	 * @return type of the source
	 *
	 */
	public Type getSourceType(Association association, DDiagram diagram) {
		final EList<DDiagramElement> elements = diagram.getDiagramElements();
		// List semantic elements visible in diagram
		final List<EObject> visibleEnds = new ArrayList<EObject>();
		for (final DDiagramElement element : elements) {
			visibleEnds.add(element.getTarget());
		}
		final EList<Property> ends = association.getMemberEnds();
		for (final Property end : ends) {
			if (visibleEnds.contains(end.getType())) {
				return end.getType();
			}
		}
		return null;
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
	 * Get the list of broken ends.
	 *
	 * @param association
	 *            association
	 * @return List
	 */
	public List<Type> getTargetBrokenAssociationToClasse(Association association) {
		if (!isNary(association)) {
			return AssociationServices.INSTANCE.getTypes(association);
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
		return AssociationServices.INSTANCE.getTargetType(association);
	}

	/**
	 * Get the type of the association target end.
	 *
	 * @param association
	 *            association
	 * @param diagram
	 *            diagram
	 * @return Type of the target
	 */
	// FIXME check if previous method could be replaced by this one
	public Type getTargetType(Association association, DDiagram diagram) {

		final EList<DDiagramElement> elements = diagram.getDiagramElements();

		// List semantic elements visible in diagram
		final List<EObject> diagramElements = new ArrayList<EObject>();
		for (final DDiagramElement element : elements) {
			diagramElements.add(element.getTarget());
		}

		final List<Property> ends = association.getMemberEnds();
		// find source index in list
		int sourceIndex = 0;
		for (int i = 0; i < ends.size(); i++) {
			if (diagramElements.contains(ends.get(i).getType())) {
				sourceIndex = i;
				break;
			}
		}

		// find target from the end of the list
		int targetIndex = ends.size() - 1;
		while (targetIndex > 0 && targetIndex > sourceIndex) {
			if (diagramElements.contains(ends.get(targetIndex).getType())) {
				return ends.get(targetIndex).getType();
			}
			targetIndex--;
		}
		return null;
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
		final EList<Property> ends = child.getMemberEnds();
		for (final Property end : ends) {
			if (end.getType() == null) {
				return true;
			}
		}
		return false;
	}

	private boolean isComposite(Property property) {
		return property != null && property.isComposite();
	}

	private boolean isNary(Association association) {
		if (association != null && association.getMembers() != null && association.getMembers().size() > 2) {
			return true;
		}
		return false;
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
	 * Is a composite or aggregation association is valid.
	 *
	 * @param association
	 * @return true if an aggregation or a composite association is binary
	 */
	public boolean isValidAggregationCompositeAssociation(Association association){
		final EList<Property> ends = association.getMemberEnds();
		if (ends.size()>2){
			for (final Property end : ends){
				if (end.getAggregation().getValue() != AggregationKind.NONE) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check if an association can be created. Both selected elements are not association. If an association
	 * is selected is should not be an aggregation or a composite
	 *
	 * @param self
	 *            association
	 * @param preSource
	 *            User select element as source
	 * @param preTarget
	 *            user select element as target
	 * @return true if valid
	 */
	public boolean isValidAssociation(EObject self, Element preSource,Element preTarget){

		if (preSource instanceof Association && preTarget instanceof Association){
			return false; // Source and Target are not association
		}else if (preSource instanceof Association || preTarget instanceof Association){
			// Verify association is not a binary association
			Association association;
			if (preSource instanceof Association){
				association=(Association)preSource;
			}else{
				association=(Association)preTarget;
			}

			final EList<Property> ends = association.getMemberEnds();
			for (final Property end : ends){
				if (isComposite(end) || isShared(end)) {
					return false;
				}
			}
		}

		return true;
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
				return !(input instanceof Stereotype) && (input instanceof Class || input instanceof Interface
						|| input instanceof DataType);
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
