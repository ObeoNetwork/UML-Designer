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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.resource.UMLResource;

import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.api.session.SessionManager;

/**
 * Services for UML.
 * 
 * @author Cedric Brun <a href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class UMLServices {

	/**
	 * Returns a human readable string for the specified visibility.
	 * 
	 * @param visibilityString
	 *            the visibility.
	 * @return a human readable string for the specified visibility.
	 */
	public String visibilityToString(String visibilityString) {
		final VisibilityKind visibilityKind = VisibilityKind.getByName(visibilityString);
		String str = "";
		switch (visibilityKind.getValue()) {
			case VisibilityKind.PRIVATE:
				str = "-";
				break;
			case VisibilityKind.PUBLIC:
				str = "+";
				break;
			case VisibilityKind.PROTECTED:
				str = "#";
				break;
			case VisibilityKind.PACKAGE:
			default:
				break;
		}
		return str;
	}

	/**
	 * Import the package containing default UML primitive types.
	 * 
	 * @param namespace
	 *            Namespace into which importing the types
	 */
	public void importUmlPrimitiveTypes(Namespace namespace) {
		importPrimitiveTypes(namespace, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if the default UML primitive types are already imported.
	 * 
	 * @param namespace
	 *            Namespace to be checked
	 * @return true if the types are already imported, else false
	 */
	public boolean areUmlPrimitiveTypesImported(Namespace namespace) {
		return arePrimitiveTypesImported(namespace, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Import the package containing default Java primitive types.
	 * 
	 * @param namespace
	 *            Namespace into which importing the types
	 */
	public void importJavaPrimitiveTypes(Namespace namespace) {
		importPrimitiveTypes(namespace, UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if the default Java primitive types are already imported.
	 * 
	 * @param namespace
	 *            Namespace to be checked
	 * @return true if the types are already imported, else false
	 */
	public boolean areJavaPrimitiveTypesImported(Namespace namespace) {
		return arePrimitiveTypesImported(namespace, UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Import the package containing default Ecore primitive types.
	 * 
	 * @param namespace
	 *            Namespace into which importing the types
	 */
	public void importEcorePrimitiveTypes(Namespace namespace) {
		importPrimitiveTypes(namespace, UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if the default Ecore primitive types are already imported.
	 * 
	 * @param namespace
	 *            Namespace to be checked
	 * @return true if the types are already imported, else false
	 */
	public boolean areEcorePrimitiveTypesImported(Namespace namespace) {
		return arePrimitiveTypesImported(namespace, UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Import the package containing default XML primitive types.
	 * 
	 * @param namespace
	 *            Namespace into which importing the types
	 */
	public void importXmlPrimitiveTypes(Namespace namespace) {
		importPrimitiveTypes(namespace, UMLResource.XML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if the default XML primitive types are already imported.
	 * 
	 * @param namespace
	 *            Namespace to be checked
	 * @return true if the types are already imported, else false
	 */
	public boolean areXmlPrimitiveTypesImported(Namespace namespace) {
		return arePrimitiveTypesImported(namespace, UMLResource.XML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Loads & import library into the {@link Namespace}.
	 * @param namespace the {@link Namespace} context
	 * @param libraryUri the URI of the library to load.
	 */
	private void importPrimitiveTypes(Namespace namespace, String libraryUri) {
		final ResourceSet resourceSet = namespace.eResource().getResourceSet();
		final Resource resource = resourceSet.getResource(URI.createURI(libraryUri), true);
		// Add the resource to the session's semantic resources
		final Session session = SessionManager.INSTANCE.getSession(namespace);
		if (session != null) {
			session.addSemanticResource(resource, false);
		}
		final Package root = (Package)EcoreUtil
				.getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);
		// We check if a package import already exists
		if (!namespace.getImportedPackages().contains(root)) {
			namespace.createPackageImport(root);
		}
	}

	/**
	 * Check if the given library is already imported in the given {@link Namespace}.
	 * 
	 * @param namespace the {@link Namespace} context to inspect
	 * @param libraryUri URI of the library.
	 * @return <code>true</code> if the library is imported, <code>false</code> otherwise
	 */
	private boolean arePrimitiveTypesImported(Namespace namespace, String libraryUri) {
		final ResourceSet resourceSet = namespace.eResource().getResourceSet();
		final Resource resource = resourceSet.getResource(URI.createURI(libraryUri), true);
		final Package root = (Package)EcoreUtil
				.getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);
		// We check if a package import already exists
		return namespace.getImportedPackages().contains(root);
	}

	/**
	 * Return the source of an association.
	 * 
	 * @param association the {@link Association} context
	 * @return first end of the association
	 */
	public static Property getSource(Association association) {
		if (association.getMemberEnds() != null && association.getMemberEnds().size() > 0) {
			return association.getMemberEnds().get(0);
		}
		return null;
	}

	/**
	 * Return the target of an association.
	 * 
	 * @param association the {@link Association} context
	 * @return second end of the association
	 */
	public static Property getTarget(Association association) {
		if (association.getMemberEnds() != null && association.getMemberEnds().size() > 1) {
			return association.getMemberEnds().get(1);
		}
		return null;
	}

	/**
	 * Indicates if a property has "shared" as its aggregation kind.
	 * 
	 * @param p
	 *            property to test
	 * @return boolean
	 */
	public boolean isShared(Property p) {
		return AggregationKind.SHARED_LITERAL.equals(p.getAggregation());
	}

	/**
	 * Indicates if a property has "composite" as its aggregation kind.
	 * 
	 * @param p
	 *            property to test
	 * @return boolean
	 */
	public boolean isComposite(Property p) {
		return AggregationKind.COMPOSITE_LITERAL.equals(p.getAggregation());
	}
}
