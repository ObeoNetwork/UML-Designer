/*******************************************************************************
 * Copyright (c) 2013, 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.providers;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.AttributesCustomPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.EndsCustomPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.GeneralCustomPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.OperationsCustomPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ParametersCustomPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ProfilesPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.RelationshipsPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.StereotypeApplicationsPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.StereotypesPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.GeneralPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.SAPropertyPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlPropertiesEditionPartProvider;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class UmlCustomPropertiesEditionPartProvider extends
		UmlPropertiesEditionPartProvider {
	@Override
	public IPropertiesEditionPart getPropertiesEditionPart(Object key,
			int kind, IPropertiesEditionComponent component) {
		if (key == UmlViewsRepository.Operations.class) {
			if (kind == UmlViewsRepository.FORM_KIND)
				return new OperationsCustomPropertiesEditionPartForm(component);
		}
		if (key == UmlViewsRepository.Parameters.class) {
			if (kind == UmlViewsRepository.FORM_KIND)
				return new ParametersCustomPropertiesEditionPartForm(component);
		}
		if (key == UmlViewsRepository.Attributes.class) {
			if (kind == UmlViewsRepository.FORM_KIND)
				return new AttributesCustomPropertiesEditionPartForm(component);
		}

		if (key == CustomUmlViewsRepository.Stereotypes.class) {
			if (kind == UmlViewsRepository.FORM_KIND)
				return new StereotypesPropertiesEditionPartForm(component);
		}
		
		if (key == CustomUmlViewsRepository.StereotypeApplications.class) {
			if (kind == UmlViewsRepository.FORM_KIND)
				return new StereotypeApplicationsPropertiesEditionPartForm(component);
		}
		
		if (key == CustomUmlViewsRepository.SAProperty.class) {
			if (kind == UmlViewsRepository.SWT_KIND)
				return new SAPropertyPropertiesEditionPartImpl(component);
		}

		if (key == CustomUmlViewsRepository.Profiles.class) {
			if (kind == UmlViewsRepository.FORM_KIND)
				return new ProfilesPropertiesEditionPartForm(component);
		}

		if (key == CustomUmlViewsRepository.Relationships.class) {
			if (kind == UmlViewsRepository.FORM_KIND)
				return new RelationshipsPropertiesEditionPartForm(component);
		}

		if (key == CustomUmlViewsRepository.Documentation.class) {
			if (kind == UmlViewsRepository.FORM_KIND)
				return new RelationshipsPropertiesEditionPartForm(component);
		}

		if (key == UmlViewsRepository.Ends.class) {
			if (kind == UmlViewsRepository.FORM_KIND)
				return new EndsCustomPropertiesEditionPartForm(component);
		}

		if (key == UmlViewsRepository.General.class) {
			if (kind == UmlViewsRepository.FORM_KIND)
				return new GeneralCustomPropertiesEditionPartForm(component);
		}

		return super.getPropertiesEditionPart(key, kind, component);
	}
}