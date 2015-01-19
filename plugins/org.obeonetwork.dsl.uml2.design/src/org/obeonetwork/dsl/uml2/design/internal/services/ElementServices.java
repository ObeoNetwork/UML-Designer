/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * Services to handle typed Element concerns.
 *
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ElementServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final ElementServices INSTANCE = new ElementServices();

	/**
	 * Scenario element name prefix.
	 */
	private static final String SCENARIO_PREFIX = "Scenario_"; //$NON-NLS-1$

	/**
	 * Operation element name prefix.
	 */
	private static final String OPERATION_PREFIX = "Operation_"; //$NON-NLS-1$

	/**
	 * Hidden constructor.
	 */
	private ElementServices() {

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
		for (final EObject root : roots) {
			final Type result = findTypeByNameFrom(root, typeName);
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Find a {@link Type} element that match the given name in the ResourceSet of the given element.
	 *
	 * @param object
	 *            the object for which to find a corresponding type.
	 * @param typeName
	 *            the type name to match.
	 * @return the found {@link Type} element or <code>null</code>
	 */
	public Type findTypeByName(EObject object, String typeName) {
		final Type result = findTypeByName(getAllRootsInResourceSet(object), typeName);
		return result;
	}

	/**
	 * Iterate over the root children to find a {@link Type} element with the given name.
	 *
	 * @param root
	 *            the root object to iterate.
	 * @param typeName
	 *            the name to match
	 * @return the found {@link Type} or <code>null</code>
	 */
	private Type findTypeByNameFrom(EObject root, String typeName) {
		if (root instanceof Type && nameMatches((Type)root, typeName)) {
			return (Type)root;
		}

		for (final Iterator<EObject> i = root.eAllContents(); i.hasNext();) {
			final EObject obj = i.next();
			if (obj instanceof Type && nameMatches((Type)obj, typeName)) {
				return (Type)obj;
			}
		}

		return null;
	}

	/**
	 * Retrieves all the root elements of the resource in the resource set of the given context object.
	 *
	 * @param context
	 *            the context object on which to execute this service.
	 * @return a {@link Collection} of all the root element of the current resource set.
	 */
	private Collection<EObject> getAllRootsInResourceSet(EObject context) {
		final Resource res = context.eResource();
		if (res != null && res.getResourceSet() != null) {
			final Collection<EObject> roots = new ArrayList<EObject>();
			for (final Resource childRes : res.getResourceSet().getResources()) {
				roots.addAll(childRes.getContents());
			}
			return roots;
		}
		return Collections.emptySet();
	}

	/**
	 * Get the namespace associated to a named element.
	 *
	 * @param element
	 *            Named element
	 * @return Namespace, if the element is a model or a profile the namespace is itself
	 */
	public Namespace getNamespace(NamedElement element) {
		if (element instanceof Model || element instanceof Profile) {
			return (Namespace)element;
		}
		return element.getNamespace();
	}

	/**
	 * Parse the edited package to find the name of the new interaction.
	 *
	 * @param pkg
	 *            the container {@link Package} object.
	 * @return Name for new interaction.
	 */
	public String getNewInteractionName(EObject pkg) {
		final StringBuffer name = new StringBuffer(SCENARIO_PREFIX);
		name.append(getNumberOfElements(((Package)pkg).getPackagedElements(), SCENARIO_PREFIX));
		return name.toString();
	}

	/**
	 * Parse the edited class to find the name of the new operation.
	 *
	 * @param type
	 *            the container {@link org.eclipse.uml2.uml.Type} object.
	 * @return New operation name
	 */
	public String getNewOperationName(org.eclipse.uml2.uml.Type type) {
		String name = ""; //$NON-NLS-1$
		List<Operation> operations = null;
		if (type instanceof Class) {
			operations = ((Class)type).getOperations();
		} else if (type instanceof Interface) {
			operations = ((Interface)type).getOperations();
		} else if (type instanceof Component) {
			operations = ((Component)type).getOperations();
		} else if (type instanceof DataType) {
			operations = ((DataType)type).getOperations();
		}
		if (operations != null) {
			name = OPERATION_PREFIX + (operations.size() + 1);
		}
		return name;
	}

	/**
	 * Search the index of the last created element.
	 *
	 * @param elements
	 *            List of elements.
	 * @param prefix
	 *            Prefix defining the index
	 * @return The index to use for a new element
	 */
	@SuppressWarnings("rawtypes")
	private int getNumberOfElements(List elements, String prefix) {
		int lastUsedIndex = -1;
		for (final Object element : elements) {
			final String name = ((NamedElement)element).getName();
			if (name != null && name.startsWith(prefix)) {
				final int index = Integer.valueOf(name.substring(name.lastIndexOf("_") + 1)).intValue(); //$NON-NLS-1$
				if (index > lastUsedIndex) {
					lastUsedIndex = index;
				}
			}
		}

		return lastUsedIndex + 1;
	}

	/**
	 * Import the package containing default Ecore primitive types.
	 *
	 * @param element
	 *            Named element
	 */
	public void importEcorePrimitiveTypes(NamedElement element) {
		ElementServices.INSTANCE.importPrimitiveTypes(element, UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Import the package containing default Java primitive types.
	 *
	 * @param element
	 *            Named element
	 */
	public void importJavaPrimitiveTypes(NamedElement element) {
		ElementServices.INSTANCE.importPrimitiveTypes(element, UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Loads & import library into the {@link Namespace}.
	 *
	 * @param element
	 *            Named element
	 * @param libraryUri
	 *            the URI of the library to load.
	 */
	public void importPrimitiveTypes(NamedElement element, String libraryUri) {
		final Namespace namespace = getNamespace(element);
		final ResourceSet resourceSet = namespace.eResource().getResourceSet();
		final Resource resource = resourceSet.getResource(URI.createURI(libraryUri), true);
		// Add the resource to the session's semantic resources
		final Session session = SessionManager.INSTANCE.getSession(namespace);
		if (session != null) {
			session.addSemanticResource(resource.getURI(), new NullProgressMonitor());
		}
		final Package root = (Package)EcoreUtil.getObjectByType(resource.getContents(),
				UMLPackage.Literals.PACKAGE);
		// We check if a package import already exists
		if (!namespace.getImportedPackages().contains(root)) {
			namespace.createPackageImport(root);
		}
	}

	/**
	 * Import the package containing default UML primitive types.
	 *
	 * @param element
	 *            Named element
	 */
	public void importUmlPrimitiveTypes(NamedElement element) {
		ElementServices.INSTANCE.importPrimitiveTypes(element, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Import the package containing default XML primitive types.
	 *
	 * @param element
	 *            Named element
	 */
	public void importXmlPrimitiveTypes(NamedElement element) {
		ElementServices.INSTANCE.importPrimitiveTypes(element, UMLResource.XML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Check if an element is a component.
	 *
	 * @param element
	 *            Element
	 * @return True if element is an instance of component
	 */
	public boolean isComponent(EObject element) {
		return element instanceof Component;
	}

	/**
	 * Check if the given element's name match the given String.
	 *
	 * @param namedElt
	 *            the {@link NamedElement} to check.
	 * @param name
	 *            the name to match.
	 * @return <code>true</code> if the name match, <code>false</code> otherwise.
	 */
	private boolean nameMatches(NamedElement namedElt, String name) {
		if (namedElt != null && namedElt.getName() != null && name != null) {
			return namedElt.getName().trim().equalsIgnoreCase(name.trim());
		}
		return false;
	}
}
