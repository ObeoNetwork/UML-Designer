/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.profile.design.services;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.design.services.UIServices;

/**
 * Services to handle UI concerns.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class UIServicesForProfile extends UIServices {

	/**
	 * Constructor of this class.
	 */
	public UIServicesForProfile() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dropFromDiagram(final Element newContainer,
			final Element semanticElement,
			final DSemanticDecorator containerView) {
		final Session session = SessionManager.INSTANCE
				.getSession(newContainer);
		boolean doTheDrop = true;

		final Element oldContainer = semanticElement.getOwner();
		final TransactionalEditingDomain domain = session
				.getTransactionalEditingDomain();
		Command cmd = null;
		if (newContainer instanceof Profile && oldContainer instanceof Profile
				&& newContainer != oldContainer) {
			final Profile newProfile = (Profile) newContainer;
			final Profile oldProfile = (Profile) oldContainer;

			// Add the ElementImport to 'MetaclassReference' of newContainer
			// (profile)
			if (semanticElement instanceof ElementImport) {
				final ElementImport elementImport = (ElementImport) semanticElement;
				if (canDropElementImport(elementImport, oldProfile, newProfile)) {
					cmd = AddCommand.create(domain, newContainer,
							UMLPackage.Literals.PROFILE__METACLASS_REFERENCE,
							elementImport);
					if (cmd.canExecute()) {
						cmd.execute();
					}
				} else {
					doTheDrop = false;
					if (newProfile.getImportedMember(elementImport
							.getImportedElement().getName()) == null) {
						newProfile.createMetaclassReference(elementImport
								.getImportedElement());
					}
				}
			}
			// Move the Element import and extension of a stereotype if the
			// newContainer is a profile
			if (semanticElement instanceof Stereotype) {
				for (Property property : ((Stereotype) semanticElement)
						.getAttributes()) {
					if (property.getName().startsWith("base_")) {
						Extension extension = null;
						if (property.getAssociation() != null
								&& property.getAssociation() instanceof Extension)
							extension = (Extension) property.getAssociation();
						if (extension != null
								&& ((Profile) oldContainer)
										.getElementImport(extension
												.getMetaclass()) != null) {
							final ElementImport elementImport = ((Profile) oldContainer)
									.getElementImport(extension.getMetaclass());
							dropFromDiagram(newContainer, extension,
									containerView);
							if (elementImport != null)
								dropFromDiagram(newContainer, elementImport,
										containerView);
						}
					}
				}

				// Reset the current element as subject
				cmd = AddCommand.create(domain, newContainer,
						UMLPackage.Literals.PROFILE__METACLASS_REFERENCE,
						semanticElement);
				if (cmd.canExecute()) {
					cmd.execute();
				}
			}
		}
		if (doTheDrop)
			super.dropFromDiagram(newContainer, semanticElement, containerView);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dropFromModel(final Element newContainer,
			final Element semanticElement,
			final DSemanticDecorator containerView) {
		super.dropFromModel(newContainer, semanticElement, containerView);
	}

	/**
	 * Test if is possible to drop an ElementImport from an old profile to a new
	 * profile.
	 * 
	 * @param elementImport
	 * @param oldProfile
	 * @param newProfile
	 * @return true if is possible to drop, else false.
	 */
	public boolean canDropElementImport(final ElementImport elementImport,
			final Profile oldProfile, final Profile newProfile) {
		if (newProfile.getImportedMember(elementImport.getImportedElement()
				.getName()) != null)
			return false;
		for (Extension extension : oldProfile.getOwnedExtensions(false)) {
			if (extension.getMetaclass() != null
					&& extension.getMetaclass().equals(
							elementImport.getImportedElement())) {
				return false;
			}
		}
		return true;
	}
}
