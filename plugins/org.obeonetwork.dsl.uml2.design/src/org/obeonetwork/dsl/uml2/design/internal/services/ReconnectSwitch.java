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
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.DeployedArtifact;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.DeploymentTarget;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.util.UMLSwitch;

/**
 * A switch that handle the edge reconnections for each UML types.
 *
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo .fr</a>
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class ReconnectSwitch extends UMLSwitch<Element> {
	/**
	 * Source reconnection kind constant.
	 */
	public static final int RECONNECT_SOURCE = 0;

	/**
	 * Target reconnection kind constant.
	 */
	public static final int RECONNECT_TARGET = 1;

	/**
	 * The current reconnection kind.
	 */
	private int reconnectKind;

	/**
	 * The old pointed element.
	 */
	private Element oldPointedElement;

	/**
	 * The new pointed element.
	 */
	private Element newPointedElement;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseAssociation(Association association) {
		if (isReconnectable(association)) {
			if (RECONNECT_SOURCE == reconnectKind) {
				final Property source = AssociationServices.INSTANCE.getSource(association);
				source.setType((Type)newPointedElement);
			} else {
				final Property target = AssociationServices.INSTANCE.getTarget(association);
				target.setType((Type)newPointedElement);
			}
		}
		return association;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseComponent(Component component) {
		if (RECONNECT_TARGET == reconnectKind) {
			if (newPointedElement instanceof Package) {
				((Package)newPointedElement).getPackagedElements().add(component);
			} else if (newPointedElement instanceof Component) {
				((Component)newPointedElement).getPackagedElements().add(component);
			}
		} else {
			if (newPointedElement instanceof PackageableElement) {
				final EObject newParent = component.eContainer();
				if (newParent instanceof Package) {
					((Package)newParent).getPackagedElements().add((PackageableElement)newPointedElement);
				} else if (newParent instanceof Component) {
					((Component)newParent).getPackagedElements().add((PackageableElement)newPointedElement);
				}

				final EObject root = EcoreUtil.getRootContainer(component);
				if (root instanceof Package) {
					((Package)root).getPackagedElements().add(component);
				}
			}
		}
		return component;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseComponentRealization(ComponentRealization cRealization) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Classifier) {
				final Classifier realizingClassifier = (Classifier)newPointedElement;
				cRealization.getRealizingClassifiers().clear();
				cRealization.getRealizingClassifiers().add(realizingClassifier);
				cRealization.setName(realizingClassifier.getName() + "To" //$NON-NLS-1$
						+ cRealization.getAbstraction().getName());
			}
		} else {
			if (newPointedElement instanceof Component) {
				final Component abstraction = (Component)newPointedElement;
				final List<Classifier> realizingClassifiers = cRealization.getRealizingClassifiers();
				String realizingClassifierName = ""; //$NON-NLS-1$
				if (!realizingClassifiers.isEmpty()) {
					realizingClassifierName = realizingClassifiers.get(0).getName();
				}
				cRealization.setAbstraction(abstraction);
				cRealization.setName(realizingClassifierName + "To" + abstraction.getName()); //$NON-NLS-1$
			}
		}
		return cRealization;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseConnector(Connector connector) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof ConnectableElement) {
				connector.getEnds().get(0).setRole((ConnectableElement)newPointedElement);
			}
		} else {
			if (newPointedElement instanceof ConnectableElement) {
				connector.getEnds().get(1).setRole((ConnectableElement)newPointedElement);
			}
		}
		return connector;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseDependency(Dependency dependency) {
		if (isReconnectable(dependency)) {
			if (RECONNECT_SOURCE == reconnectKind) {
				dependency.getClients().clear();
				dependency.getClients().add((NamedElement)newPointedElement);
			} else {
				dependency.getSuppliers().clear();
				dependency.getSuppliers().add((NamedElement)newPointedElement);
			}
		}
		return dependency;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseDeployment(Deployment deployment) {
		if (newPointedElement instanceof Classifier) {
			if (RECONNECT_SOURCE == reconnectKind) {
				deployment.getSuppliers().clear();
				deployment.getSuppliers().add((Classifier)newPointedElement);

				deployment.getDeployedArtifacts().clear();
				deployment.getDeployedArtifacts().add((DeployedArtifact)newPointedElement);
			} else {
				deployment.getClients().clear();
				((Classifier)newPointedElement).getClientDependencies().add(deployment);
				((DeploymentTarget)newPointedElement).getDeployments().add(deployment);
			}
			return deployment;
		}
		return deployment;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseExtension(Extension extension) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Stereotype) {
				for (int i = 0; i < extension.getMemberEnds().size(); i++) {
					if (extension.getMemberEnds().get(i) instanceof ExtensionEnd) {
						((ExtensionEnd)extension.getMemberEnds().get(i)).setType((Type)newPointedElement);
					} else {
						((Stereotype)newPointedElement).getOwnedAttributes().add(0,
								extension.getMemberEnds().get(i));
					}
				}
			}
		} else {
			if (newPointedElement instanceof ElementImport) {
				final Property baseProperty = extension.getStereotype().getOwnedAttribute(
						"base_" + extension.getMetaclass().getName(), extension.getMetaclass()); //$NON-NLS-1$
				final Class newPointedMetaClass = (Class)((ElementImport)newPointedElement)
						.getImportedElement();

				if (baseProperty != null && newPointedMetaClass != null) {
					baseProperty.setType((Class)((ElementImport)newPointedElement).getImportedElement());
					baseProperty.setName("base_" + newPointedMetaClass.getName()); //$NON-NLS-1$
				}
			}
		}
		return extension;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseGeneralization(Generalization generalization) {
		if (isReconnectable(generalization)) {
			if (RECONNECT_SOURCE == reconnectKind) {
				generalization.setSpecific((Classifier)newPointedElement);
			} else {
				generalization.setGeneral((Classifier)newPointedElement);
			}
		}
		return generalization;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseInterfaceRealization(InterfaceRealization interfaceRealization) {
		if (isReconnectable(interfaceRealization)) {
			if (RECONNECT_SOURCE == reconnectKind) {
				interfaceRealization.getClients().clear();
				((Class)newPointedElement).getInterfaceRealizations().add(interfaceRealization);
			} else {
				interfaceRealization.getSuppliers().clear();
				interfaceRealization.getSuppliers().add((Interface)newPointedElement);
			}
		}
		return interfaceRealization;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element casePackage(Package pkg) {
		if (RECONNECT_TARGET == reconnectKind) {
			if (newPointedElement instanceof Package) {
				((Package)newPointedElement).getPackagedElements().add(pkg);
			} else if (newPointedElement instanceof Component) {
				((Component)newPointedElement).getPackagedElements().add(pkg);
			}
		} else {
			if (newPointedElement instanceof PackageableElement) {
				final EObject newParent = pkg.eContainer();
				if (newParent instanceof Package) {
					((Package)newParent).getPackagedElements().add((PackageableElement)newPointedElement);
				} else if (newParent instanceof Component) {
					((Component)newParent).getPackagedElements().add((PackageableElement)newPointedElement);
				}

				final EObject root = EcoreUtil.getRootContainer(pkg);
				if (root instanceof Package) {
					((Package)root).getPackagedElements().add(pkg);
				}
			}
		}
		return pkg;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseTemplateBinding(TemplateBinding tmplBinding) {
		if (isReconnectable(tmplBinding)) {
			if (RECONNECT_SOURCE == reconnectKind) {
				final TemplateableElement templateableElement = (TemplateableElement)newPointedElement;
				tmplBinding.setBoundElement(templateableElement);
			} else {
				final TemplateableElement oldTmplElement = (TemplateableElement)oldPointedElement;
				final TemplateableElement newTmplElement = (TemplateableElement)newPointedElement;
				final TemplateSignature oldTemplate = oldTmplElement.getOwnedTemplateSignature();
				final TemplateSignature newTemplate = newTmplElement.getOwnedTemplateSignature();
				if (newTemplate != null) {
					final List<TemplateParameter> newParams = newTemplate.getOwnedParameters();
					final List<TemplateParameter> oldParams = oldTemplate.getOwnedParameters();
					tmplBinding.setSignature(newTemplate);

					if (newParams.size() > oldParams.size()) {
						handleHigherTmplParamCardinality(tmplBinding);
					} else if (newParams.size() < oldParams.size()) {
						handleLowerTmplParamCardinality(tmplBinding, oldTmplElement, newTmplElement);
					} else {
						handleSameTmplParamCardinality(tmplBinding);
					}
				} else {
					createANewTemplateSignature(tmplBinding, oldTmplElement, newTmplElement);
				}
			}
		}
		return tmplBinding;
	}

	/**
	 * The new TemplateableElement hasn't a template signature. We need to create with the same signature as
	 * on the old TemplateableElement and update the binding.
	 *
	 * @param tmplBinding
	 * @param oldTmplElement
	 * @param newTmplElement
	 */
	private void createANewTemplateSignature(TemplateBinding tmplBinding, TemplateableElement oldTmplElement,
			TemplateableElement newTmplElement) {
		final TemplateSignature templateSignature = newTmplElement.createOwnedTemplateSignature();
		tmplBinding.setSignature(templateSignature);
		final List<TemplateParameter> oldParams = oldTmplElement.getOwnedTemplateSignature()
				.getOwnedParameters();
		for (final TemplateParameter templateParameter : oldParams) {
			final ClassifierTemplateParameter newTemplateClassifier = UMLFactory.eINSTANCE
					.createClassifierTemplateParameter();
			final Class newGenericClass = UMLFactory.eINSTANCE.createClass();
			final ParameterableElement ownedDefault = templateParameter.getOwnedDefault();
			if (ownedDefault instanceof NamedElement) {
				newGenericClass.setName(((NamedElement)ownedDefault).getName());
			}
			newTemplateClassifier.setOwnedDefault(newGenericClass);
			newTemplateClassifier.setParameteredElement(newGenericClass);

			templateSignature.getOwnedParameters().add(newTemplateClassifier);
		}
		// Adapt the binding associations
		handleSameTmplParamCardinality(tmplBinding);
	}

	/**
	 * The new TemplateableElement has a higher number of TemplateParameter. For each parameterSubstitutions,
	 * we need to update the formal value with the related TemplateParameter. In a second time, we need to add
	 * missing parameterSubstitutions.
	 *
	 * @param tmplBinding
	 * @param newParams
	 */
	private void handleHigherTmplParamCardinality(TemplateBinding tmplBinding) {
		// We adapt the binding association
		final TemplateSignature newTmplSignature = tmplBinding.getSignature();
		final List<TemplateParameterSubstitution> paramSubs = tmplBinding.getParameterSubstitutions();
		for (int i = 0; i < newTmplSignature.getOwnedParameters().size(); i++) {
			TemplateParameterSubstitution tmplParamSub;
			if (i < paramSubs.size()) {
				tmplParamSub = paramSubs.get(i);
			} else {
				tmplParamSub = tmplBinding.createParameterSubstitution();
			}
			tmplParamSub.setFormal(newTmplSignature.getOwnedParameters().get(i));
		}
	}

	/**
	 * The new TemplateableElement has a lower number of TemplateParameter. firstly, we add missing
	 * TemplateParameters. Secondly, for each parameterSubstitutions, we need to update the formal value with
	 * the related TemplateParameter.
	 *
	 * @param tmplBinding
	 * @param newParams
	 */
	private void handleLowerTmplParamCardinality(TemplateBinding tmplBinding,
			TemplateableElement oldTmplElement, TemplateableElement newTmplElement) {

		final TemplateSignature oldTemplate = oldTmplElement.getOwnedTemplateSignature();
		final TemplateSignature newTemplate = newTmplElement.getOwnedTemplateSignature();

		final List<TemplateParameter> newParams = newTemplate.getOwnedParameters();
		final List<TemplateParameter> oldParams = oldTemplate.getOwnedParameters();

		for (int i = newParams.size(); i < oldParams.size(); i++) {
			final ClassifierTemplateParameter newTemplateClassifier = UMLFactory.eINSTANCE
					.createClassifierTemplateParameter();
			final Class newGenericClass = UMLFactory.eINSTANCE.createClass();
			final ParameterableElement ownedDefault = oldParams.get(i).getOwnedDefault();
			if (ownedDefault instanceof NamedElement) {
				newGenericClass.setName(((NamedElement)ownedDefault).getName());
			}
			newTemplateClassifier.setOwnedDefault(newGenericClass);
			newTemplateClassifier.setParameteredElement(newGenericClass);

			newParams.add(newTemplateClassifier);

		}
		final List<TemplateParameterSubstitution> paramSubs = tmplBinding.getParameterSubstitutions();
		for (int i = 0; i < newParams.size(); i++) {
			final TemplateParameterSubstitution tmplParamSub = paramSubs.get(i);
			tmplParamSub.setFormal(newParams.get(i));
		}
	}

	/**
	 * The new TemplateableElement has the same number of TemplateParameter. For each parameterSubstitutions,
	 * we need to update the formal value with the related TemplateParameter.
	 *
	 * @param tmplBinding
	 * @param newParams
	 */
	private void handleSameTmplParamCardinality(TemplateBinding tmplBinding) {
		// Adapt the binding association with the newParams
		final TemplateSignature newTmplSignature = tmplBinding.getSignature();
		final List<TemplateParameterSubstitution> parameterSubstitutions = tmplBinding
				.getParameterSubstitutions();
		int i = 0;
		for (final TemplateParameterSubstitution templateParameterSubstitution : parameterSubstitutions) {
			if (i < newTmplSignature.getOwnedParameters().size()) {
				templateParameterSubstitution.setFormal(newTmplSignature.getOwnedParameters().get(i));
			} else {
				// the model is dirty
				LogServices.INSTANCE.error("ReconnectSwitch.caseTemplateBinding(" + tmplBinding.getClass() //$NON-NLS-1$
						+ ") dirty TemplateBinding model.", null); //$NON-NLS-1$
				break;
			}
			i++;
		}
	}

	private boolean isReconnectable(Element element) {
		final ReconnectPreconditionSwitch reconnectPreconditionService = new ReconnectPreconditionSwitch();
		reconnectPreconditionService.setReconnectKind(reconnectKind);
		reconnectPreconditionService.setNewPointedElement(newPointedElement);
		reconnectPreconditionService.setOldPointedElement(oldPointedElement);
		return reconnectPreconditionService.isReconnectable(element);
	}

	/**
	 * Set new pointed element.
	 *
	 * @param newPointedElement
	 *            New pointed element
	 */
	public void setNewPointedElement(Element newPointedElement) {
		this.newPointedElement = newPointedElement;
	}

	/**
	 * Set old pointed element.
	 *
	 * @param oldPointedElement
	 *            Old pointed element
	 */
	public void setOldPointedElement(Element oldPointedElement) {
		this.oldPointedElement = oldPointedElement;
	}

	/**
	 * Set reconnect kind.
	 *
	 * @param reconnectKind
	 *            Reconnect kind
	 */
	public void setReconnectKind(int reconnectKind) {
		this.reconnectKind = reconnectKind;
	}
}
