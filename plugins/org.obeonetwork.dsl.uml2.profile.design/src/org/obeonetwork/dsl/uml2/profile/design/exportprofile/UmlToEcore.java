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
package org.obeonetwork.dsl.uml2.profile.design.exportprofile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.editor.actions.DiagnosticAction;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.eclipse.uml2.uml.util.UMLUtil.UML2EcoreConverter;
import org.obeonetwork.dsl.uml2.design.services.LogServices;

/**
 * This class provides method to create an ecore model from an UML model.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class UmlToEcore extends DiagnosticAction {
	/**
	 * file extension of ecore file.
	 */
	protected static final String ECORE_FILE_EXTENSION = "ecore";

	/**
	 * Constructor of this class.
	 */
	public UmlToEcore() {
		super();
	}

	/**
	 * Create an ecore model from an uml model.
	 * 
	 * @param profile
	 *            the uml file
	 * @return an ecore model
	 */
	public Resource umlToEcore(final org.eclipse.uml2.uml.Package profile) {

		final Map<String, String> options = initAllOptionsToProcess();

		final Collection<EPackage> ecorePackages = UMLUtil.convertToEcore(
				profile, options, null, null);

		final Resource umlProfileResource = profile.eResource();

		final ResourceSet resourceSet = umlProfileResource.getResourceSet();

		final URI uri = resourceSet.getURIConverter()
				.normalize(umlProfileResource.getURI()).trimFileExtension()
				.trimSegments(1);

		final List<Resource> resources = new ArrayList<Resource>();
		Resource resource = null;
		for (EPackage ePackage : ecorePackages) {

			resources.add(resource = resourceSet.createResource(uri
					.appendSegment(ePackage.getName()).appendFileExtension(
							ECORE_FILE_EXTENSION)));

			resource.getContents().add(ePackage);
		}

		for (Resource r : resources) {

			try {
				r.save(null);
			} catch (Exception e) {

				new LogServices().error("umlToEcore(" + profile.getClass()
						+ ") not handled", e);
				e.printStackTrace();
			}
		}

		return resources.get(0);
	}

	/**
	 * initiate the option of the ecore creation.
	 * 
	 * @return map of option.
	 */
	protected Map<String, String> initAllOptionsToProcess() {
		final Map<String, String> options = new HashMap<String, String>();

		options.put(UML2EcoreConverter.OPTION__ECORE_TAGGED_VALUES,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__REDEFINING_OPERATIONS,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__REDEFINING_PROPERTIES,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__SUBSETTING_PROPERTIES,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__UNION_PROPERTIES,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__DERIVED_FEATURES,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__DUPLICATE_OPERATIONS,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__DUPLICATE_OPERATION_INHERITANCE,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__DUPLICATE_FEATURES,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__DUPLICATE_FEATURE_INHERITANCE,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__SUPER_CLASS_ORDER,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__ANNOTATION_DETAILS,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__INVARIANT_CONSTRAINTS,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__OPERATION_BODIES,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__COMMENTS,
				UMLUtil.OPTION__PROCESS);

		options.put(UML2EcoreConverter.OPTION__CAMEL_CASE_NAMES,
				UMLUtil.OPTION__IGNORE);

		return options;
	}
}
