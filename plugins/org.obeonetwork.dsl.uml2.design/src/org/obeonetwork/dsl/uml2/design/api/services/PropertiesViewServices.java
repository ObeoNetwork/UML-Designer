/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.selection.EObjectPaneBasedSelectionWizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AttributeOwner;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.obeonetwork.dsl.uml2.design.internal.services.AssociationServices;
import org.obeonetwork.dsl.uml2.design.internal.services.ChangePropertyOwnerSwitch;
import org.obeonetwork.dsl.uml2.design.internal.services.LabelServices;
import org.obeonetwork.dsl.uml2.design.internal.services.PropertiesLabelSwitch;
import org.obeonetwork.dsl.uml2.design.internal.services.PropertyGeneralSwitch;
import org.obeonetwork.dsl.uml2.design.internal.services.RelationshipLabelSwitch;
import org.obeonetwork.dsl.uml2.design.internal.services.StereotypeServices;

import com.google.common.collect.Lists;

/**
 * Services for properties views.
 *
 * @author Frederic Bats <a href="mailto:emailadress">frederic.bats@obeo.fr</a>
 */
public class PropertiesViewServices {

	/**
	 * Add association end.
	 *
	 * @param association
	 *            selected association
	 */
	public void addAssociationEnd(Association association) {
		AssociationServices.INSTANCE.addAssociationEnd(association);
	}

	private void computeProfileChildren(TreeItemWrapper parent, Profile profile, Element elem) {
		final ReusedDescriptionServices service = new ReusedDescriptionServices();
		final Collection<Stereotype> stereotypes = service.getAllStereotypes(elem, profile);
		for (final EObject obj : stereotypes) {
			if (obj instanceof Stereotype && !elem.getAppliedStereotypes().contains(obj)
					&& ((Stereotype)obj).containingProfile().equals(profile)) {
				final TreeItemWrapper treeItem = new TreeItemWrapper(obj, parent);
				parent.getChildren().add(treeItem);
			}
		}
	}

	/**
	 * Compute label for relationShip
	 *
	 * @param relation
	 *            relation between two elements
	 * @return label
	 */
	public String computeRelationshipLabel(Element relation) {
		final RelationshipLabelSwitch labelSwitch = new RelationshipLabelSwitch();
		return labelSwitch.doSwitch(relation);
	}

	/**
	 * Get aggregation kind name.
	 *
	 * @param kind
	 *            aggregation kind
	 * @return name
	 */
	public String getAggregationKindName(AggregationKind kind) {
		return kind.getName();
	}

	/**
	 * Get Aggregation kinds.
	 *
	 * @param end
	 *            property
	 * @return list of aggregation kinds
	 */
	public List<AggregationKind> getAggregationKinds(Property end) {
		return AggregationKind.VALUES;
	}

	/**
	 * Get association ends.
	 *
	 * @param asso
	 *            association
	 * @return list of association ends
	 */
	public List<Property> getAssociationEnds(Association asso) {
		return asso.getMemberEnds();
	}

	/**
	 * Get attributes.
	 *
	 * @param elem
	 *            artifact
	 * @return list of owned attributes and tagged values
	 */
	public List<Object> getAttributes(Artifact elem) {
		return getAttributes(elem, UMLPackage.eINSTANCE.getArtifact_OwnedAttribute());
	}

	/**
	 * Get attributes.
	 *
	 * @param elem
	 *            Datatype
	 * @return list of owned attributes and tagged values
	 */
	public List<Object> getAttributes(DataType elem) {
		return getAttributes(elem, UMLPackage.eINSTANCE.getDataType_OwnedAttribute());
	}

	/**
	 * Get attributes.
	 *
	 * @param elem
	 *            Element
	 * @return list tagged values
	 */
	public List<Object> getAttributes(Element elem) {
		return getAttributes(elem, null);
	}

	/**
	 * Get attributes.
	 *
	 * @param element
	 *            element
	 * @param attrFeature
	 *            OwnedAttribute Feature or null
	 * @return list tagged values
	 */
	public List<Object> getAttributes(Element element, EStructuralFeature attrFeature) {
		final List<Object> attributes = new ArrayList<Object>();
		if (attrFeature != null) {
			if (element.eGet(attrFeature) instanceof List) {
				attributes.addAll((List<?>)element.eGet(attrFeature));
			}
		}
		attributes.addAll(getTaggedValues(element));
		return attributes;
	}

	/**
	 * Get attributes.
	 *
	 * @param elem
	 *            Interface
	 * @return list of owned attributes and tagged values
	 */
	public List<Object> getAttributes(Interface elem) {
		return getAttributes(elem, UMLPackage.eINSTANCE.getInterface_OwnedAttribute());
	}

	/**
	 * Get attributes.
	 *
	 * @param elem
	 *            StructuredClassifier
	 * @return list of owned attributes and tagged values
	 */
	public List<Object> getAttributes(StructuredClassifier elem) {
		return getAttributes(elem, UMLPackage.eINSTANCE.getStructuredClassifier_OwnedAttribute());
	}

	/**
	 * Get attribute label
	 *
	 * @param element
	 * @param umlElement
	 * @return label
	 */
	public String getAttributesLabel(EObject element, Element umlElement) {
		if (element instanceof Element) {
			final String label = LabelServices.INSTANCE.computeUmlLabel((Element)element);
			return label;
		} else if (element instanceof EAttribute || element instanceof EReference) {
			final Stereotype str = retrieveStereotype(element, umlElement);
			String stereotypeName = "Stereotype"; //$NON-NLS-1$
			if (str != null) {
				stereotypeName = str.getName();
			}
			final String type = ((EStructuralFeature)element).getEType().getName();

			return " «" + stereotypeName + "» :: " + ((EStructuralFeature)element).getName() + " : " + type; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return "undefined name";
	}

	/**
	 * Get bound value
	 *
	 * @param value
	 *            int
	 * @return value to display
	 */
	public String getBoundValue(int value) {
		return LabelServices.INSTANCE.convertBound(value);
	}

	/**
	 * Comment widget help label.
	 *
	 * @param element
	 *            element
	 * @return label
	 */
	public String getCommentHelpLabel(Element element) {
		String help = ""; //$NON-NLS-1$
		String className = element.getClass().getSimpleName();
		if (className.endsWith("Impl")) {
			className = className.substring(0, className.length() - 4);
		}
		help = "Comment for " + className;
		return help;
	}

	public String getConnectorEndName(ConnectorEnd end) {
		final ConnectableElement role = end.getRole();
		if (role != null) {
			return LabelServices.INSTANCE.computeUmlLabel(role);
		}
		return "Undefined Role";
	}

	/**
	 * @param connector
	 * @return
	 */
	public List<ConnectorEnd> getConnectorEnds(Connector connector) {
		final List<ConnectorEnd> res = new ArrayList<ConnectorEnd>();

		res.addAll(connector.getEnds());
		return res;
	}

	/**
	 * @param feature
	 * @param element
	 * @return help
	 */
	public String getHelp(EStructuralFeature feature, Element element) {
		return "The " + feature.getName() + " of " + element.eClass().getName(); //$NON-NLS-1$
	}

	/**
	 * Get a List of Element.
	 *
	 * @param elem
	 *            UML Element
	 * @return list
	 */
	public List<Element> getList(Element elem) {
		final ArrayList<Element> ret = new ArrayList<Element>();
		ret.add(elem);
		return ret;
	}

	/**
	 * Get Operations.
	 *
	 * @param element
	 *            element
	 * @return owned operations
	 */
	public List<Object> getOperations(Element element) {
		final List<Object> operations = new ArrayList<Object>();
		final EList<EStructuralFeature> features = element.eClass().getEAllStructuralFeatures();
		for (final EStructuralFeature feature : features) {
			if (UMLPackage.eINSTANCE.getInterface_OwnedOperation().equals(feature)
					|| UMLPackage.eINSTANCE.getDataType_OwnedOperation().equals(feature)
					|| UMLPackage.eINSTANCE.getClass_OwnedOperation().equals(feature)
					|| UMLPackage.eINSTANCE.getArtifact_OwnedOperation().equals(feature)) {
				if (element.eGet(feature) instanceof List) {
					operations.addAll((List<?>)element.eGet(feature));
				}
			}
		}
		return operations;
	}

	/**
	 * Get relationship for which classifier is the origin.
	 *
	 * @param element
	 *            the classifier
	 * @param origin
	 *            True for origins, False for targeting
	 * @return list of relationships
	 */
	public List<Relationship> getRelationShipOrigin(Element element, boolean origin) {
		final List<Relationship> relations = element.getRelationships();
		final List<Relationship> origins = new ArrayList<Relationship>();
		final List<Relationship> targets = new ArrayList<Relationship>();
		for (final Relationship rel : relations) {
			if (!rel.getRelatedElements().isEmpty() && rel.getRelatedElements().get(0).equals(element)) {
				origins.add(rel);
			} else {
				targets.add(rel);
			}
		}
		if (origin) {
			return origins;
		}
		return targets;
	}

	/**
	 * Get relationship for which vertex is the origin.
	 *
	 * @param element
	 *            the vertex
	 * @param origin
	 *            True for origins, False for targeting
	 * @return list of relationships
	 */
	public EList<Transition> getRelationShipOrigin(Vertex element, boolean origin) {
		if (origin) {
			return element.getOutgoings();
		}
		return element.getIncomings();
	}

	/**
	 * Help label for relationships origin widget.
	 *
	 * @param element
	 * @return help label
	 */
	public String getRelationshipOriginatingHelpLabel(Element element) {
		String help = "";
		String className = element.getClass().getSimpleName();
		if (className.endsWith("Impl")) {
			className = className.substring(0, className.length() - 4);
		}
		help = "The Relationship from " + className;
		return help;
	}

	/**
	 * Help Label for relationship targets widget.
	 *
	 * @param element
	 * @return help label
	 */
	public String getRelationshipTargetingHelpLabel(Element element) {
		String help = "";
		String className = element.getClass().getSimpleName();
		if (className.endsWith("Impl")) {
			className = className.substring(0, className.length() - 4);
		}
		help = "The Relationship to " + className;
		return help;
	}

	/**
	 * Get tagged values label for a Structural feature.
	 *
	 * @param feature
	 *            feature
	 * @return label
	 */
	public String getTaggedValueLabel(EStructuralFeature feature) {
		String name = ""; //$NON-NLS-1$
		if (feature.eContainer() != null) {
			name = ((Stereotype)feature.eContainer()).getName() + "."; //$NON-NLS-1$
		}
		return name + feature.getName();
	}

	/**
	 * Get tagged values.
	 *
	 * @param element
	 * @return Collection of tagged Values
	 */
	public Collection<Object> getTaggedValues(Element element) {
		final Collection<Object> results = Lists.newArrayList();
		final EList<EObject> strApplications = element.getStereotypeApplications();
		for (final EObject stereotypeApplication : strApplications) {
			results.addAll(getTaggedValues(stereotypeApplication));
		}
		return results;
	}

	/**
	 * See class diagram services.
	 *
	 * @param stereotypeApplication
	 * @return collection
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
	 * Get Tagged value value.
	 *
	 * @param feature
	 *            feature
	 * @param element
	 *            UML Element
	 * @return value
	 */
	public Object getTaggedValuesValue(EStructuralFeature feature, Element element) {
		final Stereotype stereotype = retrieveStereotype(feature, element);
		if (stereotype != null) {
			return element.getValue(stereotype, feature.getName());
		}
		return null;
	}

	/**
	 * Get widget Label.
	 *
	 * @param element
	 *            UML Element
	 * @param structuralFeature
	 *            feature
	 * @return a widget label.
	 */
	public String getWidgetLabel(Element element, EStructuralFeature structuralFeature) {
		final PropertiesLabelSwitch labelSwitch = new PropertiesLabelSwitch(structuralFeature);
		return labelSwitch.doSwitch(element);
	}

	/**
	 * Attribute can be added
	 *
	 * @param elem
	 *            Element
	 * @return true if element is an AttributeOwner
	 */
	public boolean isAttributesOwner(Element elem) {
		if (elem instanceof AttributeOwner) {
			return true;
		}
		return false;
	}

	/**
	 * Is displayed feature in advanced group. to avoid duplicated information between General and Advanced.
	 *
	 * @param feature
	 *            feature to test
	 * @return true if feature it have to be displayed
	 */
	public boolean isDisplayedAdvancedFeature(EStructuralFeature feature) {
		if (UMLPackage.Literals.ASSOCIATION__MEMBER_END.equals(feature)
				|| UMLPackage.Literals.ASSOCIATION__NAVIGABLE_OWNED_END.equals(feature)) {
			return false;
		}
		return true;
	}

	/**
	 * Is Attribute group have to be displayed.
	 *
	 * @param toTest
	 *            element
	 * @return true if display is need
	 */
	public boolean isDisplayedAttributesList(Artifact toTest) {
		return isDisplayedAttributesList(UMLPackage.eINSTANCE.getArtifact_OwnedAttribute(), toTest);
	}

	/**
	 * Is Attribute group have to be displayed.
	 *
	 * @param toTest
	 *            element
	 * @return true if display is need
	 */
	public boolean isDisplayedAttributesList(DataType toTest) {
		return isDisplayedAttributesList(UMLPackage.eINSTANCE.getDataType_OwnedAttribute(), toTest);
	}

	/**
	 * Is Attribute group have to be displayed.
	 *
	 * @param toTest
	 *            element
	 * @return true if display is need
	 */
	public boolean isDisplayedAttributesList(Element toTest) {
		return isDisplayedAttributesList(null, toTest);
	}

	/**
	 * Is Attribute group have to be displayed.
	 *
	 * @param toTest
	 *            element
	 * @return true if display is need
	 */
	public boolean isDisplayedAttributesList(EStructuralFeature feature, Element elem) {
		if (feature != null) {
			return isGeneralFeature(feature, elem);
		}
		return false;
	}

	/**
	 * Is Attribute group have to be displayed.
	 *
	 * @param toTest
	 *            element
	 * @return true if display is need
	 */
	public boolean isDisplayedAttributesList(Interface toTest) {
		return isDisplayedAttributesList(UMLPackage.eINSTANCE.getInterface_OwnedAttribute(), toTest);
	}

	/**
	 * Is Attribute group have to be displayed.
	 *
	 * @param toTest
	 *            element
	 * @return true if display is need
	 */
	public boolean isDisplayedAttributesList(StructuredClassifier toTest) {
		return isDisplayedAttributesList(UMLPackage.eINSTANCE.getStructuredClassifier_OwnedAttribute(),
				toTest);
	}

	/**
	 * Conditions to display Operations.
	 *
	 * @param toTest
	 *            Element
	 * @return true if is displayed
	 */
	public boolean isDisplayedOperationsList(Element toTest) {
		if (toTest instanceof Class) {
			final PropertyGeneralSwitch generalSwitch = new PropertyGeneralSwitch(
					UMLPackage.eINSTANCE.getClass_OwnedOperation());
			return generalSwitch.doSwitch(toTest).booleanValue();
		}
		if (toTest instanceof Interface) {
			final PropertyGeneralSwitch generalSwitch = new PropertyGeneralSwitch(
					UMLPackage.eINSTANCE.getInterface_OwnedOperation());
			return generalSwitch.doSwitch(toTest).booleanValue();
		}
		if (toTest instanceof DataType) {
			final PropertyGeneralSwitch generalSwitch = new PropertyGeneralSwitch(
					UMLPackage.eINSTANCE.getDataType_OwnedOperation());
			return generalSwitch.doSwitch(toTest).booleanValue();
		}
		if (toTest instanceof Artifact) {
			final PropertyGeneralSwitch generalSwitch = new PropertyGeneralSwitch(
					UMLPackage.eINSTANCE.getArtifact_OwnedOperation());
			return generalSwitch.doSwitch(toTest).booleanValue();
		}
		return false;
	}

	/**
	 * Conditions to display Operations.
	 *
	 * @param feature
	 *            EStructuralFeature
	 * @return true if is displayed
	 */
	public boolean isDisplayedOperationsList(EStructuralFeature feature) {
		final boolean result = UMLPackage.eINSTANCE.getInterface_OwnedOperation().equals(feature)
				|| UMLPackage.eINSTANCE.getClass_OwnedOperation().equals(feature)
				|| UMLPackage.eINSTANCE.getDataType_OwnedOperation().equals(feature)
				|| UMLPackage.eINSTANCE.getArtifact_OwnedOperation().equals(feature);
		return result;
	}

	/**
	 * Condition for displaying Relationships group
	 *
	 * @param toTest
	 *            Selected element
	 * @return true if group have to be displayed
	 */
	public boolean isDisplayedRelationshipsGroup(Element toTest) {
		if (toTest != null) {
			return toTest instanceof org.eclipse.uml2.uml.Package || toTest instanceof Class
					|| toTest instanceof Interface || toTest instanceof DataType
					|| toTest instanceof PrimitiveType || toTest instanceof Enumeration
					|| toTest instanceof Parameter
					|| toTest instanceof Property || toTest instanceof Component || toTest instanceof Port
					|| toTest instanceof Actor || toTest instanceof Artifact
					|| toTest instanceof Collaboration || toTest instanceof FinalState
					|| toTest instanceof Vertex || toTest instanceof Region || toTest instanceof UseCase;
		}
		return false;
	}

	/**
	 * Is an enumeration literal.
	 *
	 * @param feature
	 *            Feature
	 * @return true if feature is an enumeration literal
	 */
	public boolean isEnumerationLiterals(EStructuralFeature feature) {
		if (UMLPackage.Literals.ENUMERATION__OWNED_LITERAL.equals(feature)) {
			return true;
		}
		return false;
	}

	/**
	 * Conditions for displaying feature widget in general tab
	 *
	 * @param feature
	 *            the feature to display
	 * @param eObject
	 *            the selected EObject
	 * @return true if feature have to be displayed
	 */
	public boolean isGeneralFeature(EStructuralFeature feature, EObject eObject) {
		final PropertyGeneralSwitch generalSwitch = new PropertyGeneralSwitch(feature);
		return generalSwitch.doSwitch(eObject).booleanValue();
	}

	/**
	 * Is navigable association end.
	 *
	 * @param end
	 *            property
	 * @return true if navigable
	 */
	public boolean isNavigableEnd(Property end) {
		return end.isNavigable();
	}

	/**
	 * Conditions for displaying feature widget in Advanced tab
	 *
	 * @param feature
	 *            the feature to display
	 * @param eObject
	 *            the selected EObject
	 * @return true if feature have to be displayed
	 */
	public boolean isNotGeneralFeature(EStructuralFeature feature, EObject eObject) {
		return !isGeneralFeature(feature, eObject);
	}

	/**
	 * Is owned association end.
	 *
	 * @param property
	 *            end
	 * @return true if is owned by an association
	 */
	public boolean isOwnedEnd(Property property) {
		return property.getOwner() instanceof Association;
	}

	/**
	 * Attribute can be removed for a element.
	 *
	 * @param owner
	 *            element to test
	 * @param selection
	 *            attributes selection
	 * @return true if selected Attributes are owned by element
	 */
	public boolean isRemovableAttribute(Element owner, ArrayList<Object> selection) {
		// Check attributes are owned by parent
		final boolean res = true;
		final Iterator<Object> iterator = selection.iterator();
		while (iterator.hasNext() && res) {
			final Object attribute = iterator.next();
			if (!(attribute instanceof EObject)) {
				return false;
			} else if (attribute instanceof EObject && !owner.equals(((EObject)attribute).eContainer())) {
				return false;
			}
		}
		return res;
	}

	/**
	 * Is tagged value available.
	 *
	 * @param elem
	 *            UML Element
	 * @return true if Element is stereotyped and have tag values.
	 */
	public boolean isTaggedValuesAvailable(Element elem) {
		return !getTaggedValues(elem).isEmpty();
	}

	/**
	 * Reorganize attributes order.
	 *
	 * @param element
	 *            parent
	 * @param attribute
	 *            attribute
	 * @param up
	 *            true if up
	 */
	public void organizeUpAttributes(Property attribute, Element element, boolean up) {
		if (attribute.eContainer().equals(element)) {
			EStructuralFeature feature = null;
			if (element instanceof StructuredClassifier) {
				feature = UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE;
			} else if (element instanceof Interface) {
				feature = UMLPackage.Literals.INTERFACE__OWNED_ATTRIBUTE;
			} else if (element instanceof DataType) {
				feature = UMLPackage.Literals.DATA_TYPE__OWNED_ATTRIBUTE;
			}

			if (feature != null) {
				final EList<Property> ownedAttributes = (EList<Property>)element.eGet(feature);
				final int index = ownedAttributes.lastIndexOf(attribute);
				if (index > 0 && up) {
					ownedAttributes.move(index, index - 1);
				} else if (index < ownedAttributes.size() - 1 && !up) {
					ownedAttributes.move(index, index + 1);
				}
			}
		}
	}

	/**
	 * Remove an end or association.
	 *
	 * @param association
	 *            selected association
	 */
	public void removeAssociationEnd(final Association association) {
		AssociationServices.INSTANCE.removeAssociationEnd(association);
	}

	/**
	 * Remove an end or association.
	 *
	 * @param associations
	 *            selected association
	 */
	public void removeAssociationEnd(Object associations) {
		System.out.println("Stop");
		// for (final Association association : associations) {
		// AssociationServices.INSTANCE.removeAssociationEnd(association);
		// }
	}

	private Stereotype retrieveStereotype(EObject tagVal, Element umlElement) {
		final EList<EObject> strApplications = umlElement.getStereotypeApplications();
		for (final EObject stereotypeApplication : strApplications) {
			for (final Object feature : getTaggedValues(stereotypeApplication)) {
				if (feature != null && feature.equals(tagVal)) {
					final Stereotype stereotype = UMLUtil.getStereotype(stereotypeApplication);
					return stereotype;
				}
			}
		}
		return null;
	}

	private Stereotype retrieveStereotype(EStructuralFeature feature, Element element) {
		final EList<EObject> strApplications = element.getStereotypeApplications();
		for (final EObject stereotypeApplication : strApplications) {
			for (final Object locFeature : getTaggedValues(stereotypeApplication)) {
				if (locFeature != null && locFeature.equals(feature)) {
					final Stereotype stereotype = UMLUtil.getStereotype(stereotypeApplication);
					return stereotype;
				}
			}
		}
		return null;
	}

	/**
	 * Select a Stereotype to apply.
	 *
	 * @param elem
	 *            package
	 */
	public void selectProfile(Package elem) {

		final String windowTitle = "Select profiles to apply";
		final String wizardPageTitle = "Select profiles to apply";
		final ImageDescriptor wizardPageTitleImage = null;
		final String choiceOfValuesMessage = "Applicable profiles";
		final String selectedValuesMessage = "Profiles to apply";

		final AdapterFactory factory = new UMLItemProviderAdapterFactory();

		final EObjectPaneBasedSelectionWizard stereotypeSelectionWizard = new EObjectPaneBasedSelectionWizard(
				windowTitle, wizardPageTitle, wizardPageTitleImage, choiceOfValuesMessage,
				selectedValuesMessage, factory);
		final TreeItemWrapper input = new TreeItemWrapper(null, null);
		final ReusedDescriptionServices service = new ReusedDescriptionServices();
		final List<EObject> coll = service.getAllStereotypesAndProfiles(elem);
		for (final EObject obj : coll) {
			if (obj instanceof Profile && !elem.getAppliedProfiles().contains(obj)) {
				final TreeItemWrapper treeItem = new TreeItemWrapper(obj, input);
				input.getChildren().add(treeItem);
			}
		}
		final Collection<? extends EObject> preSelection = elem.getAppliedProfiles();
		stereotypeSelectionWizard.init(input, preSelection);

		final WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(),
				stereotypeSelectionWizard);
		if (dialog.open() == Window.OK) {
			final Collection<EObject> selection = stereotypeSelectionWizard.getSelectedEObjects();
			final List<Profile> stereotypes = new ArrayList<Profile>();
			for (final EObject object : selection) {
				if (object instanceof Profile) {
					stereotypes.add((Profile)object);
				}
			}
			StereotypeServices.INSTANCE.applyAllProfiles(elem, stereotypes);
		}
	}

	/**
	 * Select stereotypes to apply.
	 *
	 * @param elem
	 *            element
	 */
	public void selectStereotype(Element elem) {

		final String windowTitle = "Select stereotypes to apply";
		final String wizardPageTitle = "Select stereotypes to apply";
		final ImageDescriptor wizardPageTitleImage = null;
		final String choiceOfValuesMessage = "Applicable stereotypes";
		final String selectedValuesMessage = "Stereotypes to apply";

		final AdapterFactory factory = new UMLItemProviderAdapterFactory();

		final EObjectPaneBasedSelectionWizard stereotypeSelectionWizard = new EObjectPaneBasedSelectionWizard(
				windowTitle, wizardPageTitle, wizardPageTitleImage, choiceOfValuesMessage,
				selectedValuesMessage, factory);

		final TreeItemWrapper input = new TreeItemWrapper(null, null);
		final ReusedDescriptionServices service = new ReusedDescriptionServices();

		final Collection<Profile> coll = service.getAllProfilesInPlatform(elem);
		for (final Profile profile : coll) {
			final TreeItemWrapper treeItem = new TreeItemWrapper(profile, input);
			input.getChildren().add(treeItem);
			computeProfileChildren(treeItem, profile, elem);
		}

		final Collection<? extends EObject> preSelection = elem.getAppliedStereotypes();
		stereotypeSelectionWizard.init(input, preSelection);

		final WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(),
				stereotypeSelectionWizard);
		if (dialog.open() == Window.OK) {
			final Collection<EObject> selection = stereotypeSelectionWizard.getSelectedEObjects();
			final List<Stereotype> stereotypes = new ArrayList<Stereotype>();
			for (final EObject object : selection) {
				if (object instanceof Stereotype) {
					stereotypes.add((Stereotype)object);
				}
			}
			StereotypeServices.INSTANCE.applyAllStereotypes(elem, stereotypes);
		}
	}

	/**
	 * Set aggregation kind.
	 *
	 * @param end
	 *            property
	 * @param kind
	 *            aggregation kind
	 */
	public void setAggregationKind(Property end, AggregationKind kind) {
		end.setAggregation(kind);
	}

	/**
	 * Set navigable association end.
	 *
	 * @param end
	 *            Property
	 * @param newValue
	 *            boolean
	 */
	public void setIsNavigableEnd(Property end, boolean newValue) {
		end.setIsNavigable(newValue);
	}

	/**
	 * Set is owned association end.
	 *
	 * @param property
	 *            end
	 * @param newValue
	 *            boolean
	 */
	public void setIsOwnedEnd(Property property, boolean newValue) {
		final boolean wasOwned = isOwnedEnd(property);
		if (newValue != wasOwned) {
			final Association association = property.getAssociation();
			if (newValue) {
				association.getOwnedEnds().add(property);
			} else {
				if (association.getMemberEnds().size() == 2) {
					final ChangePropertyOwnerSwitch changeOwnerService = new ChangePropertyOwnerSwitch(
							property);
					changeOwnerService.doSwitch(property.getOtherEnd().getType());
				} else if (association.getMemberEnds().size() > 2) {
					final List<Type> types = new ArrayList<Type>();
					for (final Property member : association.getMemberEnds()) {
						if (member.getType() != null && !member.equals(property)) {
							types.add(member.getType());
						}
					}
					final ListDialog dialog = new ListDialog(Display.getCurrent().getActiveShell());
					dialog.setTitle("Owner selection:");
					dialog.setMessage("Please select the new owner: ");
					dialog.setInput(types.toArray());
					dialog.setContentProvider(new ArrayContentProvider());
					dialog.setLabelProvider(new LabelProvider() {
						@Override
						public String getText(Object element) {
							return ((NamedElement)element).getName();
						}
					});
					dialog.setInitialSelections(new Object[] {types.get(0)});

					if (dialog.open() == Window.OK) {
						final Object[] selection = dialog.getResult();
						if (selection != null && selection.length == 1) {
							final ChangePropertyOwnerSwitch changeOwnerService = new ChangePropertyOwnerSwitch(
									property);
							changeOwnerService.doSwitch((EObject)selection[0]);
						}
					}
				}
			}
		}

	}

	private int setPropertyBound(int oldValue, String value) {
		try {
			return LabelServices.INSTANCE.convertBound(value);
		} catch (final NumberFormatException e) {
			// nothing todo
		}
		return oldValue;
	}

	/**
	 * Set lower bound value.
	 *
	 * @param end
	 *            property
	 * @param oldValue
	 *            current value
	 * @param value
	 *            new value
	 */
	public void setPropertyLowerBound(Property end, int oldValue, String value) {
		end.setLower(setPropertyBound(oldValue, value));
	}

	/**
	 * Set upper bound value.
	 *
	 * @param end
	 *            property
	 * @param oldValue
	 *            current value
	 * @param value
	 *            new value
	 */
	public void setPropertyUpperBound(Property end, int oldValue, String value) {
		end.setUpper(setPropertyBound(oldValue, value));
	}

	/**
	 * Set the tagged value.
	 *
	 * @param feature
	 *            the feature
	 * @param element
	 *            UML Element
	 * @param value
	 *            value
	 */
	public void setTaggedValuesValue(EStructuralFeature feature, Element element, Object value) {
		final Stereotype stereotype = retrieveStereotype(feature, element);
		if (value != null) {
			element.setValue(stereotype, feature.getName(), value);
		}
	}

	public void toto(Collection coll) {
		System.out.println(coll);
	}

	/**
	 * Unapply a Profile for a package.
	 *
	 * @param pkg
	 *            package
	 * @param selection
	 *            lis of profile
	 */
	public void unapplyProfiles(Package pkg, ArrayList<Profile> selection) {
		StereotypeServices.INSTANCE.unapplyProfile(pkg, selection);
	}

	/**
	 * Unapply Stereotypes.
	 *
	 * @param element
	 *            element
	 * @param selection
	 *            Collection of stereotypes
	 */
	public void unapplyStereotypes(Element element, Collection<Stereotype> selection) {
		final List<Stereotype> appliedStereotypes = new ArrayList<Stereotype>(selection);
		StereotypeServices.INSTANCE.unapplyStereotypes(element, appliedStereotypes);
	}
}
