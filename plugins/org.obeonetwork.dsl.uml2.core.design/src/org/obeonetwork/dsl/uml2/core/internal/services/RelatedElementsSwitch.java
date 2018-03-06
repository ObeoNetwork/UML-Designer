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
package org.obeonetwork.dsl.uml2.core.internal.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.ExecutionEnvironment;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageMerge;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.util.UMLSwitch;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A switch implementation retrieving all the elements which might be related to a single one.
 *
 * @author Cedric Brun <a href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class RelatedElementsSwitch extends UMLSwitch<List<EObject>> {

	private Set<EObject> relateds;

	private Collection<Setting> xRefs;

	private ECrossReferenceAdapter referencer;

	/**
	 * Constructor.
	 */
	public RelatedElementsSwitch() {

	}

	/**
	 * Constructor.
	 *
	 * @param xRef
	 *            Cross referencer
	 */
	public RelatedElementsSwitch(ECrossReferenceAdapter xRef) {
		referencer = xRef;

	}

	@Override
	public List<EObject> caseArtifact(Artifact object) {

		return super.caseArtifact(object);
	}

	@Override
	public List<EObject> caseClassifier(Classifier object) {
		for (final Setting xRef : xRefs) {
			if (xRef.getEObject() instanceof Generalization) {
				relateds.add(((Generalization)xRef.getEObject()).getGeneral());
			}
			if (xRef.getEObject() instanceof InterfaceRealization) {
				relateds.add(((InterfaceRealization)xRef.getEObject()).getContract());
			}
			if (xRef.getEObject() instanceof Feature) {
				relateds.addAll(((org.eclipse.uml2.uml.Feature)xRef.getEObject()).getFeaturingClassifiers());
			}
			if (xRef.getEObject() instanceof UseCase) {
				relateds.add(xRef.getEObject());
			}
		}
		/*
		 * Nested Classes
		 */
		relateds.addAll(Lists.newArrayList(Iterables.filter(object.getOwnedElements(), Classifier.class)));
		relateds.addAll(object.getFeatures());
		return super.caseClassifier(object);
	}

	@Override
	public List<EObject> caseConnectableElement(ConnectableElement object) {

		for (final ConnectorEnd end : object.getEnds()) {
			final EObject connector = end.eContainer();
			if (connector != null && connector instanceof Connector && !relateds.contains(connector)) {
				relateds.add(connector);
				caseConnector((Connector)connector);
			}
		}

		return super.caseConnectableElement(object);
	}

	@Override
	public List<EObject> caseConnector(Connector object) {
		final List<ConnectorEnd> ends = object.getEnds();
		for (final ConnectorEnd end : ends) {
			final ConnectableElement role = end.getRole();
			if (role != null && !relateds.contains(role)) {
				relateds.add(role);
				if (role instanceof Port) {
					casePort((Port)role);
				}
			}
		}
		return super.caseConnector(object);
	}

	@Override
	public List<EObject> caseEncapsulatedClassifier(EncapsulatedClassifier object) {
		for (final Port port : object.getOwnedPorts()) {
			if (!relateds.contains(port)) {
				relateds.add(port);
				casePort(port);
				caseConnectableElement(port);
			}
		}
		return super.caseEncapsulatedClassifier(object);
	}

	@Override
	public List<EObject> caseExecutionEnvironment(ExecutionEnvironment object) {
		for (final Deployment depl : object.getDeployments()) {
			relateds.addAll(depl.getDeployedArtifacts());
		}
		return super.caseExecutionEnvironment(object);
	}

	@Override
	public List<EObject> caseFeature(Feature object) {
		relateds.addAll(new RelatedElementsSwitch().getRelatedElements(object.eContainer()));
		return super.caseFeature(object);
	}

	@Override
	public List<EObject> caseInterface(Interface object) {
		for (final Setting xRef : xRefs) {
			if (xRef.getEObject() instanceof InterfaceRealization) {
				relateds.add(((InterfaceRealization)xRef.getEObject()).getImplementingClassifier());
			}
		}
		return super.caseInterface(object);
	}

	@Override
	public List<EObject> caseNamedElement(NamedElement object) {
		for (final Setting xRef : xRefs) {
			if (xRef.getEObject() instanceof Relationship) {
				relateds.addAll(((Relationship)xRef.getEObject()).getRelatedElements());
			}
		}
		relateds.add(object.getModel());
		return super.caseNamedElement(object);
	}

	@Override
	public List<EObject> caseOperation(Operation object) {
		for (final Parameter param : object.getOwnedParameters()) {
			relateds.add(param.getType());
		}
		return super.caseOperation(object);
	}

	@Override
	public List<EObject> casePackage(Package object) {
		for (final Setting xRef : xRefs) {
			if (xRef.getEObject() instanceof PackageImport) {
				relateds.add(((PackageImport)xRef.getEObject()).getImportedPackage());
			}
			if (xRef.getEObject() instanceof PackageMerge) {
				relateds.add(((PackageMerge)xRef.getEObject()).getMergedPackage());
			}
		}
		relateds.addAll(object.getOwnedElements());
		return super.casePackage(object);
	}

	@Override
	public List<EObject> casePackageableElement(PackageableElement object) {
		for (final Setting xRef : xRefs) {
			if (xRef.getEObject() instanceof InterfaceRealization) {
				relateds.add(((InterfaceRealization)xRef.getEObject()).getContract());
			}
			if (xRef.getEObject() instanceof Feature) {
				relateds.addAll(((org.eclipse.uml2.uml.Feature)xRef.getEObject()).getFeaturingClassifiers());
			}
		}
		return super.casePackageableElement(object);
	}

	@Override
	public List<EObject> casePort(Port object) {

		relateds.addAll(object.getRedefinedPorts());
		relateds.add(object.eContainer());

		return super.casePort(object);
	}

	@Override
	public List<EObject> caseType(Type object) {
		for (final Setting xRef : xRefs) {
			if (xRef.getEObject() instanceof Property) {
				if (((Property)xRef.getEObject()).getAssociation() != null) {
					for (final Property pro : ((Property)xRef.getEObject()).getAssociation()
							.getMemberEnds()) {
						relateds.add(pro);
						relateds.add(pro.getType());
					}
				}

			}
		}
		relateds.add(object.getPackage());
		return super.caseType(object);
	}

	@Override
	public List<EObject> caseUseCase(UseCase object) {
		for (final Setting xRef : xRefs) {
			if (xRef.getEObject() instanceof Extend) {
				relateds.add(((Extend)xRef.getEObject()).getExtendedCase());
				relateds.add(((Extend)xRef.getEObject()).getExtension());
			}
			if (xRef.getEObject() instanceof Include) {
				relateds.add(((Include)xRef.getEObject()).getAddition());
				relateds.add(((Include)xRef.getEObject()).getIncludingCase());
			}

		}
		return super.caseUseCase(object);
	}

	private void clearAssociationsFromResult() {
		/*
		 * We don't want to retrieve the Associations themselves or they will appear as "potential subjects"
		 * in the use case diagram.
		 */
		relateds = Sets.newLinkedHashSet(Iterables.filter(relateds, new Predicate<EObject>() {

			public boolean apply(EObject input) {
				return !(input instanceof Association);
			}
		}));
	}

	/**
	 * Get related elements.
	 *
	 * @param ctx
	 *            Context
	 * @return List of related elements
	 */
	public List<EObject> getRelatedElements(EObject ctx) {
		final Session sess = SessionManager.INSTANCE.getSession(ctx);
		relateds = Sets.newLinkedHashSet();
		if (sess != null) {
			xRefs = sess.getSemanticCrossReferencer().getInverseReferences(ctx);
		} else if (referencer != null) {
			xRefs = referencer.getInverseReferences(ctx);
		}
		doSwitch(ctx);
		relateds.remove(ctx);
		// hack to prevent some null element in relateds for a unknown reason.
		relateds.remove(null);

		if (ctx instanceof Actor || ctx instanceof UseCase) {
			clearAssociationsFromResult();
		}
		return ImmutableList.copyOf(relateds);
	}
}
