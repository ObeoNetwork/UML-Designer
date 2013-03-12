/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.providers;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.VisibilityKind;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionPartProvider;

import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.AttributesPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.EndsPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.GeneralPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.LiteralsPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.OperationsPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ParametersPropertiesEditionPartForm;

import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.AttributesPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.EndsPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.GeneralPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.LiteralsPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.OperationsPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ParametersPropertiesEditionPartImpl;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class UmlPropertiesEditionPartProvider implements IPropertiesEditionPartProvider {

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPartProvider#provides(java.lang.Object)
	 * @generated
	 */
	public boolean provides(Object key) {
		return key == UmlViewsRepository.class;
	}

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPartProvider#getPropertiesEditionPart(java.lang.Object, int, org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent)
	 * @generated
	 */
	public IPropertiesEditionPart getPropertiesEditionPart(Object key, int kind, IPropertiesEditionComponent component) {
		if (key == UmlViewsRepository.General.class) {
			if (kind == UmlViewsRepository.SWT_KIND)
				return new GeneralPropertiesEditionPartImpl(component);
			if (kind == UmlViewsRepository.FORM_KIND)
				return new GeneralPropertiesEditionPartForm(component);
		}
		if (key == UmlViewsRepository.Operations.class) {
			if (kind == UmlViewsRepository.SWT_KIND)
				return new OperationsPropertiesEditionPartImpl(component);
			if (kind == UmlViewsRepository.FORM_KIND)
				return new OperationsPropertiesEditionPartForm(component);
		}
		if (key == UmlViewsRepository.Parameters.class) {
			if (kind == UmlViewsRepository.SWT_KIND)
				return new ParametersPropertiesEditionPartImpl(component);
			if (kind == UmlViewsRepository.FORM_KIND)
				return new ParametersPropertiesEditionPartForm(component);
		}
		if (key == UmlViewsRepository.Attributes.class) {
			if (kind == UmlViewsRepository.SWT_KIND)
				return new AttributesPropertiesEditionPartImpl(component);
			if (kind == UmlViewsRepository.FORM_KIND)
				return new AttributesPropertiesEditionPartForm(component);
		}
		if (key == UmlViewsRepository.Literals.class) {
			if (kind == UmlViewsRepository.SWT_KIND)
				return new LiteralsPropertiesEditionPartImpl(component);
			if (kind == UmlViewsRepository.FORM_KIND)
				return new LiteralsPropertiesEditionPartForm(component);
		}
		if (key == UmlViewsRepository.Ends.class) {
			if (kind == UmlViewsRepository.SWT_KIND)
				return new EndsPropertiesEditionPartImpl(component);
			if (kind == UmlViewsRepository.FORM_KIND)
				return new EndsPropertiesEditionPartForm(component);
		}
		return null;
	}

}
