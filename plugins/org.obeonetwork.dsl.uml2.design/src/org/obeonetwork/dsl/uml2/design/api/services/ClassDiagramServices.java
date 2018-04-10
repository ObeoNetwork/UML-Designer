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
import java.util.Arrays;
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
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.core.api.services.AbstractDiagramServices;
import org.obeonetwork.dsl.uml2.core.internal.services.AssociationServices;
import org.obeonetwork.dsl.uml2.core.internal.services.DirectEditLabelSwitch;
import org.obeonetwork.dsl.uml2.core.internal.services.DisplayLabelSwitch;
import org.obeonetwork.dsl.uml2.core.internal.services.EditLabelSwitch;
import org.obeonetwork.dsl.uml2.core.internal.services.LabelServices;
import org.obeonetwork.dsl.uml2.core.internal.services.NodeInverseRefsServices;
import org.obeonetwork.dsl.uml2.core.internal.services.OperationServices;
import org.obeonetwork.dsl.uml2.core.internal.services.StereotypeServices;
import org.obeonetwork.dsl.uml2.core.internal.services.UIServices;
import org.obeonetwork.dsl.uml2.design.api.wizards.ModelElementsSelectionDialog;
import org.obeonetwork.dsl.uml2.design.internal.dialogs.ModelElementSelectionDialog;
import org.obeonetwork.dsl.uml2.design.internal.services.ElementServices;

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
	 * Create an association between two elements.
	 *
	 * @param source
	 *            association source
	 * @param target
	 *            association target
	 * @return The association
	 */
	private Association createAssociation(Element source, Element target) {
		final Association association = UMLFactory.eINSTANCE.createAssociation();
		final Property end1 = AssociationServices.INSTANCE.createAssociationEnd((Type)source);
		association.getMemberEnds().add(end1);
		final Property end2 = AssociationServices.INSTANCE.createAssociationEnd((Type)target);
		association.getMemberEnds().add(end2);

		association.getOwnedEnds().add(end1);
		association.getOwnedEnds().add(end2);
		((Package)source.eContainer()).getPackagedElements().add(association);
		association.getNavigableOwnedEnds().addAll(getNavigableOwnedEnds(association));
		return association;
	}

	/**
	 * Create a new association.
	 *
	 * @param object
	 *            Object
	 * @param source
	 *            selected source
	 * @param target
	 *            selected Target
	 * @param sourceView
	 *            Source view
	 * @param targetView
	 *            Target view
	 * @return Association
	 */
	public Association createAssociation(EObject object, Element source, Element target, EObject sourceView,
			EObject targetView) {
		if (source.eContainer() instanceof Package) {
			// tool creation association edge
			if (!(source instanceof Association || target instanceof Association)) {
				return createAssociation(source, target);
			} else if ((source instanceof AssociationClass || target instanceof AssociationClass)
					&& (sourceView instanceof DEdge || targetView instanceof DEdge)) {
				// try to connect association from/to associationClas (edge part)
				return createAssociationAddEnd(source, target);
			} else if (source instanceof AssociationClass || target instanceof AssociationClass
					&& (sourceView instanceof DNodeList || targetView instanceof DNodeList)) {
				// try to connect association from/to associationClas (container part)
				return createAssociation(source, target);
			} else if (source instanceof Association || target instanceof Association) {
				return createAssociationAddEnd(source, target);
			}
		}
		return null;
	}

	/**
	 * Add an end to an existing association.
	 *
	 * @param source
	 *            Association or element
	 * @param target
	 *            element or association
	 */
	private Association createAssociationAddEnd(Element source, Element target) {
		Association association;
		Type type;
		if (source instanceof Association) {
			association = (Association)source;
			type = (Type)target;
		} else {
			association = (Association)target;
			type = (Type)source;
		}

		if (isBroken(association)) { // Look for broken association
			fixAssociation(association, type);
		} else { // create new end
			final Property end = AssociationServices.INSTANCE.createAssociationEnd(type);
			association.getNavigableOwnedEnds().add(end);
			association.getOwnedEnds().add(end);
		}
		return association;
	}



	/**
	 * Precondition for n-ary association creation.
	 *
	 * @param object
	 *            selected association
	 * @return true if association is binary and no end have no qualifiers
	 */
	public boolean createNaryAssociationPrecondition(EObject object) {
		return AssociationServices.INSTANCE.createNaryAssociationPrecondition(object);
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
	 * Create new qualifier for association
	 *
	 * @param association
	 *            selected association
	 */
	public void createQualifier(Association association) {
		// Display a selection pop-up to choose the end
		final ListDialog dialog = new ListDialog(Display.getCurrent().getActiveShell());
		dialog.setTitle("Qualifier creation"); //$NON-NLS-1$
		dialog.setMessage("Please select the end to create new Qualifier:"); //$NON-NLS-1$
		dialog.setInput(association.getMemberEnds().toArray());
		dialog.setContentProvider(new ArrayContentProvider());
		dialog.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((NamedElement)element).getName();
			}
		});
		dialog.setInitialSelections(new Object[] {association.getMemberEnds().get(0)});

		final int status = dialog.open();
		if (status == Window.OK) {
			final Property qualifier = UMLFactory.eINSTANCE.createProperty();
			final Property end = (Property)dialog.getResult()[0];
			end.getQualifiers().add(qualifier);
			qualifier.setName(computeDefaultName(qualifier));
		}
	}

	/**
	 * Delete selected edge fron a N-Ary association
	 *
	 * @param association
	 *            association
	 * @param element
	 *            edge to delete
	 */
	public void deleteNAryAssociation(Association association, DDiagramElement element) {
		if (isNary(association)) {
			final Property end = AssociationServices.INSTANCE.getSourceEndAssociation(association, element);
			end.eContainer();
			EcoreUtil.delete(end);
		}
	}

	/**
	 * Edit the qualifier label
	 *
	 * @param context
	 *            property association end element
	 * @param editedLabelContent
	 *            edit label content
	 * @return end
	 */
	public Element editQualifierLabel(Property context, String editedLabelContent) {
		final DisplayLabelSwitch displayLabelSwitch = new DisplayLabelSwitch();
		final EditLabelSwitch editLabel = new EditLabelSwitch();
		// Separator for direct edit
		final ArrayList<String> labels = new ArrayList<String>(
				Arrays.asList(editedLabelContent.split(DirectEditLabelSwitch.QUALIFIER_SEPARATOR)));
		// List qualifiers
		final EList<Property> qualifiers = context.getQualifiers();
		// Check for changes
		if (labels.size() != qualifiers.size()) {
			// FIXME frb
			qualifiers.clear();
			for (final String label : labels) {
				final Property qualifier = UMLFactory.eINSTANCE.createProperty();
				qualifiers.add(qualifier);
				editLabel.setEditedLabelContent(label);
				editLabel.doSwitch(qualifier);
			}
		} else {
			// Only rename element
			int index = 0;
			for (final String label : labels) {
				if (!label.equals(displayLabelSwitch.doSwitch(qualifiers.get(index)))) {
					editLabel.setEditedLabelContent(label);
					editLabel.doSwitch(qualifiers.get(index));
				}
				index++;
			}
		}
		return context;
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
					endToFix.setName(AssociationServices.INSTANCE.getAssociationEndsName(type));
				}
			}
		} else {
			final Property endToFix = brokenEnds.get(0);
			endToFix.setType(type);
			endToFix.setName(AssociationServices.INSTANCE.getAssociationEndsName(type));
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
	public List<FontFormat> getAbstractBoldLabelFormat(EObject object) {
		// Fix to return bold/italic when bug will be fixed on sirius
		final List<FontFormat> fontFormats = new ArrayList<FontFormat>();
		fontFormats.add(FontFormat.BOLD_LITERAL);
		fontFormats.add(FontFormat.ITALIC_LITERAL);
		return fontFormats;
	}

	/**
	 * Get abstract label format.
	 *
	 * @param object
	 *            EObject
	 * @return Abstract label format
	 */
	public FontFormat getAbstractItalicLabelFormat(EObject object) {
		// Fix to return bold/italic when bug will be fixed on sirius
		return FontFormat.ITALIC_LITERAL;
	}

	/**
	 * Get all the stereotype applications according to the selected diagram.
	 *
	 * @param diagram
	 *            Current diagram
	 * @return Stereotype applications
	 */
	public Collection<Object> getAllStereotypeApplications(DDiagram diagram) {
		return org.obeonetwork.dsl.uml2.core.internal.services.StereotypeServices.INSTANCE
				.getAllStereotypeApplications(diagram);
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
		for (final EObject object : associations) {
			final Association association = (Association)object;
			if (getVisibleAssociationEnds(association, diagram).size() <= 2) {
				result.add(association);
			}
		}
		return result;
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
			if (child instanceof Association && isBroken((Association)child)) {
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
			if (association.eContainer().equals(container)
					&& getVisibleAssociationEnds(association, diagram).size() > 2) {
				// Check if n-ary association container is current container to avoid to display twice
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
	 * Get the type of the association source end.
	 *
	 * @param association
	 *            association
	 * @param diagram
	 *            diagram
	 * @return type of the source
	 */
	public Element getSourceType(Association association, DDiagram diagram) {
		final EList<DDiagramElement> elements = diagram.getDiagramElements();
		// List semantic elements visible in diagram
		final List<EObject> visibleEnds = new ArrayList<EObject>();
		for (final DDiagramElement element : elements) {
			visibleEnds.add(element.getTarget());
		}
		final EList<Property> ends = association.getMemberEnds();
		for (final Property end : ends) {
			if (visibleEnds.contains(end.getType())) {
				if (end.getQualifiers().isEmpty()) {
					return end.getType();
				}
				return end;
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
		return org.obeonetwork.dsl.uml2.core.internal.services.StereotypeServices.INSTANCE
				.getStereotypeApplicationLabel(stereotypeApplication);
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
	 *            association
	 * @param diagram
	 *            diagram
	 * @return Type of the target
	 */
	public Element getTargetType(Association association, DDiagram diagram) {

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
				if (ends.get(targetIndex).getQualifiers().isEmpty()) {
					return ends.get(targetIndex).getType();
				}
				return ends.get(targetIndex);
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
	public Collection<EObject> getVisibleAssociationClass(DSemanticDiagram diagram, EObject container) {
		final Set<EObject> associationClasses = new HashSet<EObject>();
		final Collection<EObject> displayedNodes = UIServices.INSTANCE.getDisplayedNodes(diagram);
		final Collection<EObject> associations = getAssociationInverseRefs(diagram);
		for (final EObject association : associations) {
			if (association instanceof AssociationClass) {
				final Property source = AssociationServices.INSTANCE.getSource((AssociationClass)association);
				final Property target = AssociationServices.INSTANCE.getTarget((AssociationClass)association);
				final Type sourceType = source.getType();
				final Type targetType = target.getType();
				final Package parent = ((AssociationClass)association).getNearestPackage();

				// An association class is visible in its parent if the parent is visible, else it is visible
				// directly on the diagram
				if ((container.equals(parent)
						|| !displayedNodes.contains(parent) && container.equals(diagram.getTarget()))
						&& sourceType != null && displayedNodes.contains(sourceType) && targetType != null
						&& displayedNodes.contains(targetType)) {
					associationClasses.add(association);
				}
			}
		}
		return associationClasses;
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

	private boolean isBroken(Association child) {
		final EList<Property> ends = child.getMemberEnds();
		for (final Property end : ends) {
			if (end.getType() == null) {
				return true;
			}
		}
		return false;
	}



	private boolean isNary(Association association) {
		if (association != null && association.getMembers() != null && association.getMembers().size() > 2) {
			return true;
		}
		return false;
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
	public boolean isValidAggregationCompositeAssociation(Association association) {
		final EList<Property> ends = association.getMemberEnds();
		if (ends.size() > 2) {
			for (final Property end : ends) {
				if (end.getAggregation().getValue() != AggregationKind.NONE) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check if an association can be created.
	 *
	 * @param self
	 *            association
	 * @param preSource
	 *            selected element
	 * @return true if valid
	 */
	public boolean isValidAssociation(EObject self, Element preSource) {
		if (preSource instanceof Association) {
			// Verify association ends don't have qualifiers
			final EList<Property> ends = ((Association)preSource).getMemberEnds();
			for (final Property end : ends) {
				if (!end.getQualifiers().isEmpty()) {
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
	public boolean isValidAssociation(EObject self, Element preSource, Element preTarget) {

		if (preSource instanceof Association && preTarget instanceof Association) {
			return false; // Source and Target are not association
		} else if (preSource instanceof Association || preTarget instanceof Association) {
			// Verify association is not a binary association
			Association association;
			if (preSource instanceof Association) {
				association = (Association)preSource;
			} else {
				association = (Association)preTarget;
			}

			final EList<Property> ends = association.getMemberEnds();
			for (final Property end : ends) {
				if (AssociationServices.INSTANCE.isComposite(end)
						|| AssociationServices.INSTANCE.isShared(end)) {
					return false;
				}
				if (!end.getQualifiers().isEmpty()) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Check if an Nary association can be created. Both selected elements are not association. If an
	 * association is selected is should not be an aggregation or a composite
	 *
	 * @param self
	 *            association
	 * @param preSource
	 *            User select element as source
	 * @param preTarget
	 *            user select element as target
	 * @return true if valid
	 */
	public boolean isValidNaryAssociation(EObject self, Element preSource, Element preTarget) {

		if (preSource instanceof Association || preTarget instanceof Association) {
			return false;
		}

		return true;
	}

	/**
	 * Open the select existing element dialog.
	 *
	 * @param source
	 *            Selected element
	 * @param target
	 *            Selected element
	 * @param sourceContainerView
	 *            Source container view
	 */
	public void openSelectNaryAssociationEndsDialog(final EObject source, final EObject target,
			DSemanticDecorator sourceContainerView) {

		DDiagram diagram = null;
		EObject parent = sourceContainerView.eContainer();
		while (diagram == null && parent != null) {
			if (parent instanceof DDiagram) {
				diagram = (DDiagram)parent;
			}
			parent = parent.eContainer();
		}

		Model model = null;
		if (source instanceof Element) {
			model = ((Element)source).getModel();
		}

		final ModelElementsSelectionDialog dlg = new ModelElementsSelectionDialog("Create N-ary Association", //$NON-NLS-1$
				"Select additional elements to add to the association." + System.lineSeparator() //$NON-NLS-1$
				+ " At least one element have to be selected, else n-Ary association will not be created " //$NON-NLS-1$
				+ System.lineSeparator());

		dlg.setGrayedPredicate(new Predicate<EObject>() {

			public boolean apply(EObject input) {
				if (input.equals(source) || input.equals(target)) {
					return true;
				} else if (input instanceof Class || input instanceof AssociationClass
						|| input instanceof Interface || input instanceof Enumeration
						|| input instanceof DataType || input instanceof PrimitiveType) {
					return false;
				}
				return true;
			}
		});

		@SuppressWarnings("rawtypes")
		final List elementsToAdd = dlg.open(PlatformUI.getWorkbench().getDisplay().getActiveShell(), model,
				diagram, true);
		if (elementsToAdd.size() > 0) {
			final Association association = createAssociation((Element)source, (Element)target);
			for (final Object element : elementsToAdd) {
				if (element instanceof Type) {
					final Property end = AssociationServices.INSTANCE.createAssociationEnd((Type)element);
					association.getOwnedEnds().add(end);
					association.getMemberEnds().add(end);
					association.getNavigableOwnedEnds().add(end);
				}
			}
		}
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
		return AssociationServices.INSTANCE.sourceIsComposite(association);
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
		return AssociationServices.INSTANCE
				.sourceIsNavigable(association, element);
	}

	/**
	 * Check is an association source is navigable and composite.
	 *
	 * @param association
	 *            Association
	 * @return True if source is navigable and composite
	 */
	public boolean sourceIsNavigableAndTargetIsComposite(Association association) {
		return AssociationServices.INSTANCE.sourceIsNavigableAndTargetIsComposite(association);
	}

	/**
	 * Check is an association source is navigable and shared.
	 *
	 * @param association
	 *            Association
	 * @return True if source is navigable and shared
	 */
	public boolean sourceIsNavigableAndTargetIsShared(Association association) {
		return AssociationServices.INSTANCE.sourceIsNavigableAndTargetIsShared(association);
	}

	/**
	 * Check is an association source is shared.
	 *
	 * @param association
	 *            Association
	 * @return True if source is shared
	 */
	public boolean sourceIsShared(Association association) {
		return AssociationServices.INSTANCE.sourceIsShared(association);
	}

	/**
	 * Check is an association target is composite.
	 *
	 * @param association
	 *            Association
	 * @return True if target is composite
	 */
	public boolean targetIsComposite(Association association) {
		return AssociationServices.INSTANCE.targetIsComposite(association);
	}

	/**
	 * Check is an association target is navigable.
	 *
	 * @param association
	 *            Association
	 * @return True if target is navigable
	 */
	public boolean targetIsNavigable(Association association) {
		return AssociationServices.INSTANCE.targetIsNavigable(association);
	}

	/**
	 * Check is an association target is navigable and composite.
	 *
	 * @param association
	 *            Association
	 * @return True if target is navigable and composite
	 */
	public boolean targetIsNavigableAndSourceIsComposite(Association association) {
		return AssociationServices.INSTANCE.targetIsNavigableAndSourceIsComposite(association);
	}

	/**
	 * Check is an association target is navigable and shared.
	 *
	 * @param association
	 *            Association
	 * @return True if target is navigable and shared
	 */
	public boolean targetIsNavigableAndSourceIsShared(Association association) {
		return AssociationServices.INSTANCE.targetIsNavigableAndSourceIsShared(association);
	}

	/**
	 * Check is an association target is shared.
	 *
	 * @param association
	 *            Association
	 * @return True if target is shared
	 */
	public boolean targetIsShared(Association association) {
		return AssociationServices.INSTANCE.targetIsShared(association);
	}
}
