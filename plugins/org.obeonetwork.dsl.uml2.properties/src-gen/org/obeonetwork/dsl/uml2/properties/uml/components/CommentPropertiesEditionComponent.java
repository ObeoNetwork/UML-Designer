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
package org.obeonetwork.dsl.uml2.properties.uml.components;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 * @generated
 */
public class CommentPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for annotatedElement ReferencesTable
	 */
	private ReferencesTableSettings annotatedElementSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public CommentPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject comment, String editing_mode) {
    super(editingContext, comment, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.Comment.class;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * @not-generated
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			
			final Comment comment = (Comment)elt;
			final CommentPropertiesEditionPart basePart = (CommentPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.Comment.Properties.body))
				basePart.setBody(EcoreUtil.convertToString(UMLPackage.Literals.STRING, comment.getBody()));
			if (isAccessible(UmlViewsRepository.Comment.Properties.annotatedElement)) {
				annotatedElementSettings = new ReferencesTableSettings(comment, UMLPackage.eINSTANCE.getComment_AnnotatedElement());
				basePart.initAnnotatedElement(annotatedElementSettings);
			}
			// init filters
			
			if (isAccessible(UmlViewsRepository.Comment.Properties.annotatedElement)) {
				/*
				 * 
				 * CUSTOMIZED : removing this to make sure the element is getting visible
				basePart.addFilterToAnnotatedElement(new ViewerFilter() {
				
					
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!basePart.isContainedInAnnotatedElementTable((EObject)element));
						return element instanceof Resource;
					}
				
				});
				*/
				basePart.addFilterToAnnotatedElement(new EObjectFilter(UMLPackage.Literals.ELEMENT));
			}
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}





	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	public EStructuralFeature associatedFeature(Object editorKey) {
		if (editorKey == UmlViewsRepository.Comment.Properties.body) {
			return UMLPackage.eINSTANCE.getComment_Body();
		}
		if (editorKey == UmlViewsRepository.Comment.Properties.annotatedElement) {
			return UMLPackage.eINSTANCE.getComment_AnnotatedElement();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    Comment comment = (Comment)semanticObject;
    if (UmlViewsRepository.Comment.Properties.body == event.getAffectedEditor()) {
      comment.setBody((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.Comment.Properties.annotatedElement == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Element) {
          annotatedElementSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        annotatedElementSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        annotatedElementSettings.move(event.getNewIndex(), (Element) event.getNewValue());
      }
    }
  }

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		super.updatePart(msg);
		if (editingPart.isVisible()) {
			CommentPropertiesEditionPart basePart = (CommentPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getComment_Body().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Comment.Properties.body)){
				if (msg.getNewValue() != null) {
					basePart.setBody(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setBody("");
				}
			}
			if (UMLPackage.eINSTANCE.getComment_AnnotatedElement().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Comment.Properties.annotatedElement))
				basePart.updateAnnotatedElement();
			
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
			UMLPackage.eINSTANCE.getComment_AnnotatedElement()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
    Diagnostic ret = Diagnostic.OK_INSTANCE;
    if (event.getNewValue() != null) {
      try {
        if (UmlViewsRepository.Comment.Properties.body == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getComment_Body().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getComment_Body().getEAttributeType(), newValue);
        }
      } catch (IllegalArgumentException iae) {
        ret = BasicDiagnostic.toDiagnostic(iae);
      } catch (WrappedException we) {
        ret = BasicDiagnostic.toDiagnostic(we);
      }
    }
    return ret;
  }


	

}
