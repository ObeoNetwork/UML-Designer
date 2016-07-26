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

import java.util.Iterator;
import java.util.List;

import javax.swing.event.ChangeEvent;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.FlowFinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLPackage;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Manage the diagram elements' labels.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class LabelServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final LabelServices INSTANCE = new LabelServices();

	/**
	 * Space constant.
	 */
	private static final String SPACE = " "; //$NON-NLS-1$

	/**
	 * Sequence diagram element name suffix.
	 */
	private static final String SEQUENCE_DIAGRAM_SUFFIX = "sequence diagram"; //$NON-NLS-1$

	/**
	 * Hidden constructor.
	 */
	private LabelServices() {

	}

	/**
	 * Compute the label of the given association.
	 *
	 * @param association
	 *            the {@link Association} for which to retrieve a label.
	 * @return the computed label.
	 */
	public String computeAssociationBeginLabel(Association association) {
		return computeAssociationEndLabel(AssociationServices.INSTANCE.getSource(association));
	}

	/**
	 * Compute the label of the given association.
	 *
	 * @param association
	 *            the {@link Association} for which to retrieve a label.
	 * @param view
	 *            the edge for which to retrieve the source.
	 * @return the computed label.
	 */
	public String computeAssociationBeginLabel(Association association, DDiagramElement view) {
		final Property sourceEnd = AssociationServices.INSTANCE.getSourceEndAssociation(association, view);
		if (sourceEnd != null) {
			final DisplayLabelSwitch displayLabel = new DisplayLabelSwitch();
			return displayLabel.getAssociationEndLabel(sourceEnd);
		}
		return null;
	}

	/**
	 * Compute the label of the given association.
	 *
	 * @param association
	 *            the {@link Association} for which to retrieve a label.
	 * @return the computed label.
	 */
	public String computeAssociationEndLabel(Association association) {
		return computeAssociationEndLabel(AssociationServices.INSTANCE.getTarget(association));
	}
	/**
	 * Compute the label of the given property with mutiplicity and such.
	 *
	 * @param p
	 *            the {@link Property} for which to retrieve a label.
	 * @return the computed label.
	 */
	public String computeAssociationEndLabel(Property p) {
		final DisplayLabelSwitch displayLabel = new DisplayLabelSwitch();
		return displayLabel.getAssociationEndLabel(p);
	}

	/**
	 * Compute default name.
	 *
	 * @param element
	 *            New element
	 * @return Name for the new element, he name will looks like 'ElementType'+total of existing elements of
	 *         the same type.
	 */
	public String computeDefaultName(final EObject element) {
		Predicate<EObject> predicate = null;
		String name = element.getClass().getSimpleName();
		name = name.substring(0, name.indexOf("Impl")); //$NON-NLS-1$
		predicate = new Predicate<EObject>() {
			public boolean apply(EObject input) {
				return input.getClass().getName().equals(element.getClass().getName());
			}
		};
		if (element instanceof AssociationClass) {
			name = "AssociationClass"; //$NON-NLS-1$
		} else if (element instanceof InitialNode) {
			name = "Initial"; //$NON-NLS-1$
		} else if (element instanceof DecisionNode) {
			name = "Decision"; //$NON-NLS-1$
		} else if (element instanceof ActivityFinalNode) {
			name = "ActivityFinal"; //$NON-NLS-1$
		} else if (element instanceof StateMachine) {
			name = "StateMachine"; //$NON-NLS-1$
		} else if (element instanceof FlowFinalNode) {
			name = "FlowFinal"; //$NON-NLS-1$
		} else if (element instanceof MergeNode) {
			name = "Merge"; //$NON-NLS-1$
		} else if (element instanceof JoinNode) {
			name = "Join"; //$NON-NLS-1$
		} else if (element instanceof DataStoreNode) {
			name = "DataStore"; //$NON-NLS-1$
		} else if (element instanceof ActivityParameterNode) {
			name = "Parameter"; //$NON-NLS-1$
		} else if (element instanceof ForkNode) {
			name = "Fork"; //$NON-NLS-1$
		} else if (element instanceof EnumerationLiteral) {
			name = "Literal"; //$NON-NLS-1$
		} else if (element instanceof Port) {
			name = "port"; //$NON-NLS-1$
		} else if (element instanceof Property) {
			if (element.eContainingFeature().getFeatureID() == UMLPackage.PROPERTY__QUALIFIER) {
				name="qualifier"; //$NON-NLS-1$
			}else{
				name = "property"; //$NON-NLS-1$
			}
		} else if (element instanceof FinalState) {
			name = "Final"; //$NON-NLS-1$
		} else if (element instanceof Pseudostate) {
			final String kind = ((Pseudostate)element).getKind().getLiteral();
			name = Character.toUpperCase(kind.charAt(0)) + kind.substring(1);
		} else if (element instanceof Association) {
			final String end1 = ((Association)element).getOwnedEnds().get(0).getName();
			final String end2 = ((Association)element).getOwnedEnds().get(1).getName();
			return end1 + "To" + Character.toUpperCase(end2.charAt(0)) + end2.substring(1); //$NON-NLS-1$
		} else if (element instanceof InstanceSpecification) {
			predicate = new Predicate<EObject>() {
				public boolean apply(EObject input) {
					return input instanceof InstanceSpecification;
				}
			};
			name = "anObject"; //$NON-NLS-1$
			final List<Classifier> classifiers = ((InstanceSpecification)element).getClassifiers();
			if (!classifiers.isEmpty()) {
				final String classifierName = classifiers.get(0).getName();
				if (classifierName != null && classifierName.length() > 0) {
					if (startWithVowel(classifierName)) {
						name = "an"; //$NON-NLS-1$
					} else {
						name = "a"; //$NON-NLS-1$
					}
					name += classifierName;
				}
			}
		} else if (element instanceof AcceptEventAction) {
			final AcceptEventAction acceptEventAction = (AcceptEventAction)element;
			final List<Trigger> triggers = acceptEventAction.getTriggers();
			if (triggers.size() > 0) {
				final Trigger trigger = triggers.get(0);
				final Event event = trigger.getEvent();
				if (event instanceof TimeEvent) {
					name = "TimeEventAction"; //$NON-NLS-1$
				} else if (event instanceof SignalEvent) {
					name = "SignalEventAction"; //$NON-NLS-1$
				} else if (event instanceof CallEvent) {
					name = "CallEventAction"; //$NON-NLS-1$
				} else if (event instanceof ChangeEvent) {
					name = "AcceptEventAction"; //$NON-NLS-1$
				}
			}
		}

		final List<EObject> existingElements = Lists
				.newArrayList(Iterables.filter(element.eContainer().eContents(), predicate));

		return name + existingElements.size();
	}

	/**
	 * Compute element import label.
	 *
	 * @param element
	 *            Element import
	 * @return Label
	 */
	public String computeElementImportLabel(ElementImport element) {
		// ['«Metaclass»\n'+self.oclAsType(uml::Element).computeUmlLabel()/]
		return "«Metaclass»\n" + computeUmlLabel(element); //$NON-NLS-1$
	}

	/**
	 * Compute opaque action with body label.
	 *
	 * @param opaqueAction
	 *            Opaque action
	 * @return Label
	 */
	public String computeOpaqueActionWithBodyLabel(OpaqueAction opaqueAction) {

		final Iterator<String> it = opaqueAction.getBodies().iterator();

		if (it.hasNext()) {
			final StringBuffer buffer = new StringBuffer();
			buffer.append(ILabelConstants.NL);

			while (it.hasNext()) {
				buffer.append(ILabelConstants.NL);
				buffer.append(it.next());
			}

			return buffer.toString();
		}

		return computeUmlLabel(opaqueAction);
	}

	/**
	 * Compute profile label.
	 *
	 * @param element
	 *            Profile
	 * @return Label
	 */
	public String computeProfileLabel(Profile element) {
		// ['«Profile» \n'+self.oclAsType(uml::Element).computeUmlLabel()/]
		return "«Profile» \n" + computeUmlLabel(element); //$NON-NLS-1$
	}

	/**
	 * Compute stereotype label.
	 *
	 * @param element
	 *            Stereotype
	 * @return Label
	 */
	public String computeStereotypeLabel(Stereotype element) {
		// ['«Stereotype» \n'+self.oclAsType(uml::Element).computeUmlLabel()/]
		return "«Stereotype» \n" + computeUmlLabel(element); //$NON-NLS-1$
	}

	/**
	 * Compute the label of the given element.
	 *
	 * @param element
	 *            the {@link Element} for which to retrieve a label.
	 * @return the computed label.
	 */
	public String computeUmlLabel(Element element) {
		final DisplayLabelSwitch displayLabel = new DisplayLabelSwitch();
		if (element != null) {
			return displayLabel.doSwitch(element);
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * {@link String} to {@link Integer} bound conversion.
	 *
	 * @param bound
	 *            string description
	 * @return converted integer or <code>null</code> in case of {@link NumberFormatException}.
	 */
	public int convertBound(String bound) {
		if ("*".equals(bound)) { //$NON-NLS-1$
			return -1;
		}
		try {
			return new Integer(bound).intValue();
		} catch (final NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * Parse the edited label string and update the underlying context {@link Element}.
	 *
	 * @param context
	 *            the context object on which to execute this service.
	 * @param editedLabelContent
	 *            the content entered by the user.
	 * @return the context {@link Element}
	 */
	public Element editUmlLabel(Element context, String editedLabelContent) {
		final EditLabelSwitch editLabel = new EditLabelSwitch();
		editLabel.setEditedLabelContent(editedLabelContent);
		return editLabel.doSwitch(context);
	}

	/**
	 * Get sequence diagram label.
	 *
	 * @param interaction
	 *            Interaction associated to sequence diagram
	 * @return Sequence diagram label
	 */
	public String getSequenceDiagramName(Interaction interaction) {
		return interaction.getName() + SPACE + SEQUENCE_DIAGRAM_SUFFIX;
	}

	/**
	 * Get sequence diagram label.
	 *
	 * @param pkg
	 *            Package associated to sequence diagram
	 * @return Sequence diagram label
	 */
	public String getSequenceDiagramName(org.eclipse.uml2.uml.Package pkg) {
		return ElementServices.INSTANCE.getNewInteractionName(pkg) + SPACE + SEQUENCE_DIAGRAM_SUFFIX;
	}

	/**
	 * Get templated parameters.
	 *
	 * @param object
	 *            TemplateableElement
	 * @return Templated parameter
	 */
	public String getTemplatedParameters(TemplateableElement object) {
		final TemplateSignature ownedTemplateSignature = object.getOwnedTemplateSignature();
		final StringBuffer templateParameters = new StringBuffer();
		if (ownedTemplateSignature != null) {
			final List<TemplateParameter> ownedTemplateParameters = ownedTemplateSignature
					.getOwnedParameters();
			boolean first = true;
			for (final TemplateParameter templateParameter : ownedTemplateParameters) {
				if (first) {
					first = false;
				} else {
					templateParameters.append(", "); //$NON-NLS-1$
				}
				final ParameterableElement parameterableElement = templateParameter.getOwnedDefault();
				if (parameterableElement instanceof NamedElement) {
					final NamedElement classTempate = (NamedElement)parameterableElement;
					templateParameters.append(classTempate.getName());
				}
			}
			return " <" + templateParameters + ">"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return null;
	}

	private boolean startWithVowel(String str) {
		boolean result = false;
		if (str != null && str.length() > 0) {
			final char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};
			for (final char vowel : vowels) {
				if (str.startsWith(Character.toString(vowel))
						|| str.startsWith(Character.toString(vowel).toUpperCase())) {
					result = true;
					break;
				}
			}
		}
		return result;
	}
}
