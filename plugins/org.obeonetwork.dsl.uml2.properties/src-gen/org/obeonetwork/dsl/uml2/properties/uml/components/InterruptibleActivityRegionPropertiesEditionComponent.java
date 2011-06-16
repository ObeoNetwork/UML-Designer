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

// Start of user code for imports
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.InterruptibleActivityRegion;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.parts.InterruptibleActivityRegionPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class InterruptibleActivityRegionPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for inActivity EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings inActivitySettings;
	
	/**
	 * Settings for node ReferencesTable
	 */
	private	ReferencesTableSettings nodeSettings;
	
	/**
	 * Settings for interruptingEdge ReferencesTable
	 */
	private	ReferencesTableSettings interruptingEdgeSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public InterruptibleActivityRegionPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject interruptibleActivityRegion, String editing_mode) {
		super(editingContext, interruptibleActivityRegion, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.InterruptibleActivityRegion.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			final InterruptibleActivityRegion interruptibleActivityRegion = (InterruptibleActivityRegion)elt;
			final InterruptibleActivityRegionPropertiesEditionPart basePart = (InterruptibleActivityRegionPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity)) {
				// init part
				inActivitySettings = new EObjectFlatComboSettings(interruptibleActivityRegion, UMLPackage.eINSTANCE.getActivityGroup_InActivity());
				basePart.initInActivity(inActivitySettings);
				// set the button mode
				basePart.setInActivityButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.InterruptibleActivityRegion.Properties.node)) {
				nodeSettings = new ReferencesTableSettings(interruptibleActivityRegion, UMLPackage.eINSTANCE.getInterruptibleActivityRegion_Node());
				basePart.initNode(nodeSettings);
			}
			if (isAccessible(UmlViewsRepository.InterruptibleActivityRegion.Properties.interruptingEdge)) {
				interruptingEdgeSettings = new ReferencesTableSettings(interruptibleActivityRegion, UMLPackage.eINSTANCE.getInterruptibleActivityRegion_InterruptingEdge());
				basePart.initInterruptingEdge(interruptingEdgeSettings);
			}
			// init filters
			basePart.addFilterToInActivity(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Activity); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for inActivity
			
			// End of user code
			
			basePart.addFilterToNode(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInNodeTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToNode(new EObjectFilter(UMLPackage.eINSTANCE.getActivityNode()));
			// Start of user code for additional businessfilters for node
			
			// End of user code
			
			basePart.addFilterToInterruptingEdge(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInInterruptingEdgeTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToInterruptingEdge(new EObjectFilter(UMLPackage.eINSTANCE.getActivityEdge()));
			// Start of user code for additional businessfilters for interruptingEdge
			
			// End of user code
			
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}






	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		InterruptibleActivityRegion interruptibleActivityRegion = (InterruptibleActivityRegion)semanticObject;
		if (UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				inActivitySettings.setToReference((Activity)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Activity eObject = UMLFactory.eINSTANCE.createActivity();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				inActivitySettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.InterruptibleActivityRegion.Properties.node == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ActivityNode) {
					nodeSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					nodeSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InterruptibleActivityRegion.Properties.interruptingEdge == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ActivityEdge) {
					interruptingEdgeSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					interruptingEdgeSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			InterruptibleActivityRegionPropertiesEditionPart basePart = (InterruptibleActivityRegionPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getActivityGroup_InActivity().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity))
				basePart.setInActivity((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getInterruptibleActivityRegion_Node().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InterruptibleActivityRegion.Properties.node))
				basePart.updateNode();
			if (UMLPackage.eINSTANCE.getInterruptibleActivityRegion_InterruptingEdge().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InterruptibleActivityRegion.Properties.interruptingEdge))
				basePart.updateInterruptingEdge();
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			try {
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}

}
