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
package org.obeonetwork.dsl.uml2.design.services.internal;

import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.util.UMLSwitch;

/**
 * A switch that handle the label edition for each UML types.
 *
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 */
public class EditLabelSwitch extends UMLSwitch<Element> implements ILabelConstants {

	/**
	 * The guard suffix constant.
	 */
	private static final String GUARD_SUFFIX = "_guard";
	
	/**
	 * The raw label content edited by the user. 
	 */
	private String editedLabelContent;

	public void setEditedLabelContent(String editedLabelContent) {
		this.editedLabelContent = editedLabelContent;
	}

	@Override
	public Element caseComment(Comment comment) {
		comment.setBody(editedLabelContent);
		return comment;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseProperty(Property object) {
		PropertyServices.parseInputLabel(object, editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseOperation(Operation object) {
		OperationServices.parseInputLabel(object, editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseActivityEdge(ActivityEdge object) {
		OpaqueExpression expr = (OpaqueExpression)object.getGuard();
		if (expr == null) {
			expr = UMLFactory.eINSTANCE.createOpaqueExpression();
			expr.setName(object.getName() + GUARD_SUFFIX);
			object.setGuard(expr);
		}

		if (editedLabelContent.matches("\\[.*\\]")) {
			// Remove the first & last bracket character.
			editedLabelContent = editedLabelContent.substring(1, editedLabelContent.length() - 1);
		}

		expr.getBodies().clear();
		expr.getBodies().add(editedLabelContent);

		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseTransition(Transition object) {
		OpaqueExpression expr;
		Constraint constraint = object.getGuard();
		if (constraint != null) {
			expr = (OpaqueExpression)constraint.getSpecification();
			if (expr == null) {
				expr = UMLFactory.eINSTANCE.createOpaqueExpression();
				expr.setName(object.getName() + GUARD_SUFFIX);
				constraint.setSpecification(expr);
			}
		} else {
			constraint = UMLFactory.eINSTANCE.createConstraint();
			constraint.setName(object.getName() + "_transition");
			expr = UMLFactory.eINSTANCE.createOpaqueExpression();
			expr.setName(object.getName() + GUARD_SUFFIX);
			constraint.setSpecification(expr);
			object.setGuard(constraint);
		}

		if (editedLabelContent.matches("\\[.*\\]")) {
			// Remove the first & last bracket character.
			editedLabelContent = editedLabelContent.substring(1, editedLabelContent.length() - 1);
		}

		expr.getBodies().clear();
		expr.getBodies().add(editedLabelContent);

		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseNamedElement(NamedElement object) {
		// FIXME We should detect the stereotype brackets instead of trying to replace the generated label that might have been modified... 
		final String stereotype = DisplayLabelSwitch.computeStereotypes(object);
		if (stereotype != null && !"".equals(stereotype)) {
			editedLabelContent = editedLabelContent.replace(stereotype, "");
		}
		object.setName(editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseTypedElement(TypedElement object) {
		final String[] splittedLabel = editedLabelContent.split("\\s*:\\s*");

		if (splittedLabel.length == 1) {
			return caseNamedElement(object);
		} else {
			object.setName(splittedLabel[0]);

			// TODO retrieve and set the element type
		}

		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseDataStoreNode(DataStoreNode object) {
		editedLabelContent = editedLabelContent.replaceFirst("(<<Datastore>>|\u00ABDatastore\u00BB)\\s*", "");

		return caseNamedElement(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseRegion(Region region) {
		// Name of a region is in the form "OwnerName : RegionName"
		final int pos = editedLabelContent.indexOf(":");
		if (pos > -1) {
			final String ownerName = editedLabelContent.substring(0, pos).trim();
			if (region.getOwner() instanceof NamedElement) {
				((NamedElement)region.getOwner()).setName(ownerName);
			}
			region.setName(editedLabelContent.substring(pos + 1).trim());
		} else {
			region.setName(editedLabelContent.trim());
		}

		return region;
	}

}
