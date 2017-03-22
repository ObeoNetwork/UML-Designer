/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.properties;

import java.util.List;

import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageMerge;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.obeonetwork.dsl.uml2.design.internal.services.LabelServices;

/**
 * Compute relationship labels to display in relationships group.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class RelationshipLabelSwitch extends UMLSwitch<String> {


	/**
	 * Constructor.
	 */
	public RelationshipLabelSwitch() {
		super();
	}

	@Override
	public String caseAbstraction(Abstraction object) {
		return "Abstraction: " + getSourceNames(object) + " to " + getTargetNames(object);
	}

	@Override
	public String caseAssociation(Association object) {
		return "Association: " + LabelServices.INSTANCE.computeUmlLabel(object);
	}

	@Override
	public String caseAssociationClass(AssociationClass object) {
		return "AssociationClass: " + LabelServices.INSTANCE.computeUmlLabel(object);
	}

	@Override
	public String caseComponentRealization(ComponentRealization object) {
		return "ComponentRealization: " + getSourceNames(object) + " to " + getTargetNames(object);
	}
	@Override
	public String caseControlFlow(ControlFlow object) {
		return "ControlFlow: " + object.getSource().getName() + " to " + object.getTarget().getName();
	}

	@Override
	public String caseDependency(Dependency object) {
		return "Dependency: " + getSourceNames(object) + " depends on " + getTargetNames(object);
	}

	@Override
	public String caseExtend(Extend object) {
		return "Extend: " + getSourceNames(object) + " extends " + getTargetNames(object);
	}

	@Override
	public String caseGeneralization(Generalization object) {
		return "Generalization: " + getSourceNames(object) + " to " + getTargetNames(object);
	}

	@Override
	public String caseInclude(Include object) {
		return "Include: " + getSourceNames(object) + " includes " + getTargetNames(object);
	}

	@Override
	public String caseInterfaceRealization(InterfaceRealization object) {
		return "InterfaceRealization: " + getSourceNames(object) + " to " + getTargetNames(object);
	}

	@Override
	public String casePackageImport(PackageImport object) {
		return "Import: " + getSourceNames(object) + " imports " + getTargetNames(object);
	}

	@Override
	public String casePackageMerge(PackageMerge object) {
		return "Merge: " + getSourceNames(object) + " merges " + getTargetNames(object);
	}

	@Override
	public String caseTemplateBinding(TemplateBinding object) {
		return "TemplateBinding: " + getSourceNames(object) + " to " + getTargetNames(object); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public String caseTransition(Transition object) {
		return "Transition: " + getSourceNames(object) + " to " + getTargetNames(object);
	}


	@Override
	public String caseUsage(Usage object) {
		return "Usage: " + getSourceNames(object) + " uses " + getTargetNames(object);
	}

	private String getNames(List<Element> entries){
		final String nameSeparator = "_"; //$NON-NLS-1$
		String names = ""; //$NON-NLS-1$
		boolean first = true;
		for (final Element source : entries) {
			if (first && source instanceof NamedElement){
				names = names + ((NamedElement)source).getName();
				first = false;
			}else if (!first && source instanceof NamedElement) {
				names = names + nameSeparator+ ((NamedElement)source).getName();
			}
		}
		return names;
	}

	private String getSourceNames(DirectedRelationship relation) {
		return getNames(relation.getSources());
	}

	private String getSourceNames(Transition transition) {
		String sourcesNames = ""; //$NON-NLS-1$
		final Element source = transition.getSource();
		if (source instanceof NamedElement) {
			sourcesNames = ((NamedElement)source).getName();
		}
		return sourcesNames;
	}

	private String getTargetNames(DirectedRelationship relation) {
		return getNames(relation.getTargets());
	}

	private String getTargetNames(Transition transition) {
		String targetsNames = ""; //$NON-NLS-1$
		final Element target = transition.getTarget();
		if (target instanceof NamedElement) {
			targetsNames = ((NamedElement)target).getName();
		}
		return targetsNames;
	}

}
