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

import java.util.List;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
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
import org.obeonetwork.dsl.uml2.design.services.LogServices;
import org.obeonetwork.dsl.uml2.design.services.UMLServices;

/**
 * A switch that handle the edge reconnections for each UML types.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
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

	public void setReconnectKind(int reconnectKind) {
		this.reconnectKind = reconnectKind;
	}

	public void setOldPointedElement(Element oldPointedElement) {
		this.oldPointedElement = oldPointedElement;
	}

	public void setNewPointedElement(Element newPointedElement) {
		this.newPointedElement = newPointedElement;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseGeneralization(Generalization generalization) {
		if (newPointedElement instanceof Classifier) {
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
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Class) {
				interfaceRealization.getClients().clear();
				((Class)newPointedElement).getInterfaceRealizations().add(interfaceRealization);
			}
		} else {
			if (newPointedElement instanceof Interface) {
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
	public Element caseDependency(Dependency dependency) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Classifier) {
				dependency.getClients().clear();
				((Classifier)newPointedElement).getClientDependencies().add(dependency);
			}
		} else {
			if (newPointedElement instanceof Classifier) {
				dependency.getSuppliers().clear();
				dependency.getSuppliers().add((Classifier)newPointedElement);
			}
		}
		return dependency;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseComponentRealization(ComponentRealization cRealization) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Classifier) {
				Classifier realizingClassifier = (Classifier)newPointedElement;
				cRealization.getRealizingClassifiers().clear();
				cRealization.getRealizingClassifiers().add(realizingClassifier);
				cRealization.setName(realizingClassifier.getName() + "To"
						+ cRealization.getAbstraction().getName());
			}
		} else {
			if (newPointedElement instanceof Component) {
				Component abstraction = (Component)newPointedElement;
				List<Classifier> realizingClassifiers = cRealization.getRealizingClassifiers();
				String realizingClassifierName = "";
				if (!realizingClassifiers.isEmpty()) {
					realizingClassifierName = realizingClassifiers.get(0).getName();
				}
				cRealization.setAbstraction(abstraction);
				cRealization.setName(realizingClassifierName + "To" + abstraction.getName());
			}
		}
		return cRealization;
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
				Property baseProperty = extension.getStereotype().getOwnedAttribute(
						"base_" + extension.getMetaclass().getName(), extension.getMetaclass());
				Class newPointedMetaClass = (Class)((ElementImport)newPointedElement).getImportedElement();

				if (baseProperty != null && newPointedMetaClass != null) {
					baseProperty.setType((Class)((ElementImport)newPointedElement).getImportedElement());
					baseProperty.setName("base_" + newPointedMetaClass.getName());
				}
			}
		}
		return extension;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseAssociation(Association association) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Type) {
				final Property source = new UMLServices().getSource(association);
				source.setType((Type)newPointedElement);
			}
		} else {
			if (newPointedElement instanceof Type) {
				final Property target = new UMLServices().getTarget(association);
				target.setType((Type)newPointedElement);
			}
		}
		return association;
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
	public Element caseTemplateBinding(TemplateBinding tmplBinding) {
		if (newPointedElement instanceof TemplateableElement
				&& oldPointedElement instanceof TemplateableElement) {
			if (RECONNECT_SOURCE == reconnectKind) {
				TemplateableElement templateableElement = (TemplateableElement)newPointedElement;
				tmplBinding.setBoundElement(templateableElement);
			} else {
				final TemplateableElement oldTmplElement = (TemplateableElement)oldPointedElement;
				final TemplateableElement newTmplElement = (TemplateableElement)newPointedElement;
				TemplateSignature oldTemplate = oldTmplElement.getOwnedTemplateSignature();
				TemplateSignature newTemplate = newTmplElement.getOwnedTemplateSignature();
				if (newTemplate != null) {
					List<TemplateParameter> newParams = newTemplate.getOwnedParameters();
					List<TemplateParameter> oldParams = oldTemplate.getOwnedParameters();
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
		TemplateSignature templateSignature = newTmplElement.createOwnedTemplateSignature();
		tmplBinding.setSignature(templateSignature);
		List<TemplateParameter> oldParams = oldTmplElement.getOwnedTemplateSignature().getOwnedParameters();
		for (TemplateParameter templateParameter : oldParams) {
			ClassifierTemplateParameter newTemplateClassifier = UMLFactory.eINSTANCE
					.createClassifierTemplateParameter();
			Class newGenericClass = UMLFactory.eINSTANCE.createClass();
			ParameterableElement ownedDefault = templateParameter.getOwnedDefault();
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
	 * The new TemplateableElement has the same number of TemplateParameter. For each parameterSubstitutions,
	 * we need to update the formal value with the related TemplateParameter.
	 * 
	 * @param tmplBinding
	 * @param newParams
	 */
	private void handleSameTmplParamCardinality(TemplateBinding tmplBinding) {
		// Adapt the binding association with the newParams
		TemplateSignature newTmplSignature = tmplBinding.getSignature();
		List<TemplateParameterSubstitution> parameterSubstitutions = tmplBinding.getParameterSubstitutions();
		int i = 0;
		for (TemplateParameterSubstitution templateParameterSubstitution : parameterSubstitutions) {
			if (i < newTmplSignature.getOwnedParameters().size()) {
				templateParameterSubstitution.setFormal(newTmplSignature.getOwnedParameters().get(i));
			} else {
				// the model is dirty
				new LogServices().error("ReconnectSwitch.caseTemplateBinding(" + tmplBinding.getClass()
						+ ") dirty TemplateBinding model.", null);
				break;
			}
			i++;
		}
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
		TemplateSignature newTmplSignature = tmplBinding.getSignature();
		List<TemplateParameterSubstitution> paramSubs = tmplBinding.getParameterSubstitutions();
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

		TemplateSignature oldTemplate = oldTmplElement.getOwnedTemplateSignature();
		TemplateSignature newTemplate = newTmplElement.getOwnedTemplateSignature();

		List<TemplateParameter> newParams = newTemplate.getOwnedParameters();
		List<TemplateParameter> oldParams = oldTemplate.getOwnedParameters();

		for (int i = newParams.size(); i < oldParams.size(); i++) {
			ClassifierTemplateParameter newTemplateClassifier = UMLFactory.eINSTANCE
					.createClassifierTemplateParameter();
			Class newGenericClass = UMLFactory.eINSTANCE.createClass();
			ParameterableElement ownedDefault = oldParams.get(i).getOwnedDefault();
			if (ownedDefault instanceof NamedElement) {
				newGenericClass.setName(((NamedElement)ownedDefault).getName());
			}
			newTemplateClassifier.setOwnedDefault(newGenericClass);
			newTemplateClassifier.setParameteredElement(newGenericClass);

			newParams.add(newTemplateClassifier);

		}
		List<TemplateParameterSubstitution> paramSubs = tmplBinding.getParameterSubstitutions();
		for (int i = 0; i < newParams.size(); i++) {
			TemplateParameterSubstitution tmplParamSub = paramSubs.get(i);
			tmplParamSub.setFormal(newParams.get(i));
		}
	}
}
