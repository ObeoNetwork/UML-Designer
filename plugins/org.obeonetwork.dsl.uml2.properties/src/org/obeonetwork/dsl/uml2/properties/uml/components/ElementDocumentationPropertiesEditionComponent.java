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
package org.obeonetwork.dsl.uml2.properties.uml.components;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.ui.widgets.settings.EEFEditorSettingsBuilder;
import org.eclipse.emf.eef.runtime.ui.widgets.settings.EEFEditorSettingsBuilder.EEFEditorSettingsImpl;
import org.eclipse.emf.eef.runtime.ui.widgets.settings.EEFInitializer;
import org.eclipse.emf.eef.runtime.ui.widgets.settings.NavigationStepBuilder;
import org.eclipse.uml2.types.TypesPackage;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.DocumentationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class ElementDocumentationPropertiesEditionComponent extends
		SinglePartPropertiesEditingComponent {

	public static String DOCUMENTATION_PART = "Documentation"; //$NON-NLS-1$

	/**
	 * Settings for comment editor
	 */
	protected EEFEditorSettingsImpl commentSettings = (EEFEditorSettingsImpl) EEFEditorSettingsBuilder
			.create(semanticObject, UMLPackage.eINSTANCE.getComment_Body())
			.nextStep(
					NavigationStepBuilder
							.create(UMLPackage.eINSTANCE
									.getElement_OwnedComment()).index(0)
							.init(new EEFInitializer() {

								/**
								 * {@inheritDoc}
								 * 
								 * @see org.eclipse.emf.eef.runtime.ui.widgets.settings.EEFInit#init(org.eclipse.emf.ecore.EObject)
								 */
								@Override
								protected EObject init(EObject semanticObject) {
									return org.obeonetwork.dsl.uml2.properties.service.EEFService
											.createComment(semanticObject);
								}

							}).build()).build();

	/**
	 * Default constructor
	 */
	public ElementDocumentationPropertiesEditionComponent(
			PropertiesEditingContext editingContext, EObject class_,
			String editing_mode) {
		super(editingContext, class_, editing_mode);
		parts = new String[] { DOCUMENTATION_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = CustomUmlViewsRepository.Documentation.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object,
	 *      int, org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public void initPart(Object key, int kind, EObject elt,
			ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			if (editingPart instanceof CompositePropertiesEditionPart) {
				((CompositePropertiesEditionPart) editingPart).getSettings()
						.add(commentSettings);
			}
			final DocumentationPropertiesEditionPart documentationPart = (DocumentationPropertiesEditionPart) editingPart;
			// init values
			if (commentSettings.getValue() != null
					&& isAccessible(CustomUmlViewsRepository.Documentation.comment))
				documentationPart.setComment(EcoreUtil.convertToString(
						TypesPackage.Literals.STRING,
						commentSettings.getValue()));
			// init filters

			// init values for referenced views

			// init filters for referenced views

		}
		setInitializing(false);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent#shouldProcess(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 */
	protected boolean shouldProcess(IPropertiesEditionEvent event) {
		if (event.getAffectedEditor() == CustomUmlViewsRepository.Documentation.comment) {
			return (commentSettings.getValue() == null) ? (event.getNewValue() != null)
					: (!commentSettings.getValue().equals(event.getNewValue()));
		}
		return super.shouldProcess(event);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	public EStructuralFeature associatedFeature(Object editorKey) {
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		if (CustomUmlViewsRepository.Documentation.comment == event
				.getAffectedEditor()) {
			Object oldValue = commentSettings.getValue();
			Object newValue = event.getNewValue();
			// Discard event if value does not change
			if (oldValue == null && "".equals(newValue)
					|| newValue.equals(oldValue))
				return;
			commentSettings.setValue((java.lang.String) EEFConverterUtil
					.createFromString(TypesPackage.Literals.STRING,
							(String) newValue));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		super.updatePart(msg);
		if (editingPart.isVisible()) {
			DocumentationPropertiesEditionPart documentationPart = (DocumentationPropertiesEditionPart) editingPart;
			if (!(msg.getNewValue() instanceof EObject)
					&& commentSettings.isAffectingEvent(msg)
					&& documentationPart != null
					&& isAccessible(CustomUmlViewsRepository.Documentation.comment)) {
				if (msg.getNewValue() != null) {
					documentationPart.setComment(EcoreUtil.convertToString(
							TypesPackage.Literals.STRING, msg.getNewValue()));
				} else {
					documentationPart.setComment("");
				}
			}

		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
	 */
	@Override
	protected NotificationFilter[] getNotificationFilters() {
		NotificationFilter filter = new EStructuralFeatureNotificationFilter(
				UMLPackage.eINSTANCE.getComment_Body(),
				UMLPackage.eINSTANCE.getElement_OwnedComment());
		return new NotificationFilter[] { filter, };
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object,
	 *      int)
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.General.Qualifiers.abstract_
				|| key == UmlViewsRepository.General.Qualifiers.leaf;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			try {
				if (CustomUmlViewsRepository.Documentation.comment == event
						.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil
								.createFromString(UMLPackage.eINSTANCE
										.getComment_Body().getEAttributeType(),
										(String) newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE
							.getComment_Body().getEAttributeType(), newValue);
				}
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}

	/**
	 * @ return settings for comment editor
	 */
	public EEFEditorSettingsImpl getCommentSettings() {
		return commentSettings;
	}

}
