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
package org.obeonetwork.dsl.uml2.design.services;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Property;
import org.obeonetwork.dsl.uml2.design.services.internal.DisplayLabelSwitch;
import org.obeonetwork.dsl.uml2.design.services.internal.EditLabelSwitch;
import org.obeonetwork.dsl.uml2.design.services.internal.NamedElementServices;
import org.obeonetwork.dsl.uml2.design.services.internal.TooltipLabelSwitch;

/**
 * Manage the diagram elements' labels.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class LabelServices {
	/**
	 * Space constant.
	 */
	private static final String SPACE = " ";

	/**
	 * Sequence diagram element name suffix.
	 */
	private static final String SEQUENCE_DIAGRAM_SUFFIX = "sequence diagram";

	/**
	 * Compute the label of the given element.
	 * 
	 * @param element
	 *            the {@link Element} for which to retrieve a label.
	 * @return the computed label.
	 */
	public String computeUmlLabel(Element element) {
		final DisplayLabelSwitch displayLabel = new DisplayLabelSwitch();

		return displayLabel.doSwitch(element);
	}

	/**
	 * Compute the label of the given property with mutiplicity and such.
	 * 
	 * @param element
	 *            the {@link Element} for which to retrieve a label.
	 * @return the computed label.
	 */
	public String computeAssociationEndLabel(Property p) {
		final DisplayLabelSwitch displayLabel = new DisplayLabelSwitch();
		return displayLabel.getAssociationEndLabel(p);
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
	 * Compute the tooltip for the given element.
	 * 
	 * @param context
	 *            the context object on which to execute this service.
	 * @return the computed tooltip String to display
	 */
	public String computeUmlTooltip(Element context) {
		final TooltipLabelSwitch tooltipLabel = new TooltipLabelSwitch();
		return tooltipLabel.doSwitch(context);
	}

	/**
	 * Get sequence diagram label.
	 * 
	 * @param interaction
	 *            Interaction associated to sequence diagram
	 * @return SEquence diagram label
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
		return NamedElementServices.getNewInteractionName(pkg) + SPACE + SEQUENCE_DIAGRAM_SUFFIX;
	}

	/**
	 * {@link String} to {@link Integer} bound conversion.
	 * 
	 * @param bound
	 *            string description
	 * @return converted integer or <code>null</code> in case of {@link NumberFormatException}.
	 */
	public static Integer convertBound(String bound) {
		if ("*".equals(bound)) {
			return new Integer(-1);
		}
		try {
			return new Integer(bound);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
