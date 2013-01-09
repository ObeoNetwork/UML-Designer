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

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Deployment;
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
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
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

import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.api.session.SessionManager;

/**
 * A switch implementation retrieving all the elements which might be related to a single one.
 * 
 * @author Cedric Brun <a href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 */
public class RelatedElementsSwitch extends UMLSwitch<List<EObject>> {

	private Set<EObject> relateds;

	private Collection<Setting> xRefs;

	private ECrossReferenceAdapter referencer;

	public RelatedElementsSwitch() {

	}

	public RelatedElementsSwitch(ECrossReferenceAdapter xRef) {
		this.referencer = xRef;

	}

	public List<EObject> getRelatedElements(EObject ctx) {
		Session sess = SessionManager.INSTANCE.getSession(ctx);
		relateds = Sets.newLinkedHashSet();
		if (sess != null) {
			xRefs = sess.getSemanticCrossReferencer().getInverseReferences(ctx);
		} else if (referencer != null) {
			xRefs = referencer.getInverseReferences(ctx);
		}
		doSwitch(ctx);
		relateds.remove(ctx);
		if (ctx instanceof Actor || ctx instanceof UseCase) {
			clearAssociationsFromResult();
		}
		return ImmutableList.copyOf(relateds);
	}

	@Override
	public List<EObject> caseType(Type object) {
		for (Setting xRef : xRefs) {
			if (xRef.getEObject() instanceof Property) {
				if ((((Property)xRef.getEObject()).getAssociation()) != null) {
					for (Property pro : (((Property)xRef.getEObject()).getAssociation()).getMemberEnds()) {
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
	public List<EObject> caseFeature(Feature object) {
		relateds.addAll(new RelatedElementsSwitch().getRelatedElements(object.eContainer()));
		return super.caseFeature(object);
	}

	@Override
	public List<EObject> casePackage(Package object) {
		for (Setting xRef : xRefs) {
			if (xRef.getEObject() instanceof PackageImport) {
				relateds.add(((PackageImport)xRef.getEObject()).getImportedPackage());
			}
		}
		relateds.addAll(object.getOwnedElements());
		return super.casePackage(object);
	}

	@Override
	public List<EObject> caseOperation(Operation object) {
		for (Parameter param : object.getOwnedParameters()) {
			relateds.add(param.getType());
		}
		return super.caseOperation(object);
	}

	@Override
	public List<EObject> caseNamedElement(NamedElement object) {
		for (Setting xRef : xRefs) {
			if (xRef.getEObject() instanceof Relationship) {
				relateds.addAll(((Relationship)xRef.getEObject()).getRelatedElements());
			}
		}
		relateds.add(object.getModel());
		return super.caseNamedElement(object);
	}

	@Override
	public List<EObject> casePackageableElement(PackageableElement object) {
		for (Setting xRef : xRefs) {
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
	public List<EObject> caseClassifier(Classifier object) {
		for (Setting xRef : xRefs) {
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
				relateds.add((UseCase)xRef.getEObject());
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
	public List<EObject> caseUseCase(UseCase object) {
		for (Setting xRef : xRefs) {
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

	@Override
	public List<EObject> caseExecutionEnvironment(ExecutionEnvironment object) {
		for (Deployment depl : object.getDeployments()) {
			relateds.addAll(depl.getDeployedArtifacts());
		}
		return super.caseExecutionEnvironment(object);
	}

	@Override
	public List<EObject> caseArtifact(Artifact object) {

		return super.caseArtifact(object);
	}

	@Override
	public List<EObject> caseInterface(Interface object) {
		for (Setting xRef : xRefs) {
			if (xRef.getEObject() instanceof InterfaceRealization) {
				relateds.add(((InterfaceRealization)xRef.getEObject()).getImplementingClassifier());
			}
		}
		return super.caseInterface(object);
	}

}
