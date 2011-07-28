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
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.eclipse.uml2.uml.Clause;
import org.eclipse.uml2.uml.ExecutableNode;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ClausePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for test ReferencesTable
	 */
	private	ReferencesTableSettings testSettings;
	
	/**
	 * Settings for body ReferencesTable
	 */
	private	ReferencesTableSettings bodySettings;
	
	/**
	 * Settings for predecessorClause ReferencesTable
	 */
	private	ReferencesTableSettings predecessorClauseSettings;
	
	/**
	 * Settings for successorClause ReferencesTable
	 */
	private	ReferencesTableSettings successorClauseSettings;
	
	/**
	 * Settings for decider EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings deciderSettings;
	
	/**
	 * Settings for bodyOutput ReferencesTable
	 */
	private	ReferencesTableSettings bodyOutputSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public ClausePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject clause, String editing_mode) {
		super(editingContext, clause, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Clause.class;
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
			final Clause clause = (Clause)elt;
			final ClausePropertiesEditionPart basePart = (ClausePropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.Clause.Properties.test)) {
				testSettings = new ReferencesTableSettings(clause, UMLPackage.eINSTANCE.getClause_Test());
				basePart.initTest(testSettings);
			}
			if (isAccessible(UmlViewsRepository.Clause.Properties.body)) {
				bodySettings = new ReferencesTableSettings(clause, UMLPackage.eINSTANCE.getClause_Body());
				basePart.initBody(bodySettings);
			}
			if (isAccessible(UmlViewsRepository.Clause.Properties.predecessorClause)) {
				predecessorClauseSettings = new ReferencesTableSettings(clause, UMLPackage.eINSTANCE.getClause_PredecessorClause());
				basePart.initPredecessorClause(predecessorClauseSettings);
			}
			if (isAccessible(UmlViewsRepository.Clause.Properties.successorClause)) {
				successorClauseSettings = new ReferencesTableSettings(clause, UMLPackage.eINSTANCE.getClause_SuccessorClause());
				basePart.initSuccessorClause(successorClauseSettings);
			}
			if (isAccessible(UmlViewsRepository.Clause.Properties.decider)) {
				// init part
				deciderSettings = new EObjectFlatComboSettings(clause, UMLPackage.eINSTANCE.getClause_Decider());
				basePart.initDecider(deciderSettings);
				// set the button mode
				basePart.setDeciderButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Clause.Properties.bodyOutput)) {
				bodyOutputSettings = new ReferencesTableSettings(clause, UMLPackage.eINSTANCE.getClause_BodyOutput());
				basePart.initBodyOutput(bodyOutputSettings);
			}
			// init filters
			basePart.addFilterToTest(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInTestTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToTest(new EObjectFilter(UMLPackage.eINSTANCE.getExecutableNode()));
			// Start of user code for additional businessfilters for test
			// End of user code
			
			basePart.addFilterToBody(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInBodyTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToBody(new EObjectFilter(UMLPackage.eINSTANCE.getExecutableNode()));
			// Start of user code for additional businessfilters for body
			// End of user code
			
			basePart.addFilterToPredecessorClause(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInPredecessorClauseTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToPredecessorClause(new EObjectFilter(UMLPackage.eINSTANCE.getClause()));
			// Start of user code for additional businessfilters for predecessorClause
			// End of user code
			
			basePart.addFilterToSuccessorClause(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInSuccessorClauseTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToSuccessorClause(new EObjectFilter(UMLPackage.eINSTANCE.getClause()));
			// Start of user code for additional businessfilters for successorClause
			// End of user code
			
			basePart.addFilterToDecider(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof OutputPin);
				}
			
			});
			// Start of user code for additional businessfilters for decider
			// End of user code
			
			basePart.addFilterToBodyOutput(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInBodyOutputTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToBodyOutput(new EObjectFilter(UMLPackage.eINSTANCE.getOutputPin()));
			// Start of user code for additional businessfilters for bodyOutput
			// End of user code
			
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}









	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	protected EStructuralFeature associatedFeature(Object editorKey) {
		if (editorKey == UmlViewsRepository.Clause.Properties.test) {
			return UMLPackage.eINSTANCE.getClause_Test();
		}
		if (editorKey == UmlViewsRepository.Clause.Properties.body) {
			return UMLPackage.eINSTANCE.getClause_Body();
		}
		if (editorKey == UmlViewsRepository.Clause.Properties.predecessorClause) {
			return UMLPackage.eINSTANCE.getClause_PredecessorClause();
		}
		if (editorKey == UmlViewsRepository.Clause.Properties.successorClause) {
			return UMLPackage.eINSTANCE.getClause_SuccessorClause();
		}
		if (editorKey == UmlViewsRepository.Clause.Properties.decider) {
			return UMLPackage.eINSTANCE.getClause_Decider();
		}
		if (editorKey == UmlViewsRepository.Clause.Properties.bodyOutput) {
			return UMLPackage.eINSTANCE.getClause_BodyOutput();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		Clause clause = (Clause)semanticObject;
		if (UmlViewsRepository.Clause.Properties.test == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof ExecutableNode) {
					testSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					testSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Clause.Properties.body == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof ExecutableNode) {
					bodySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					bodySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Clause.Properties.predecessorClause == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Clause) {
					predecessorClauseSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					predecessorClauseSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Clause.Properties.successorClause == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Clause) {
					successorClauseSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					successorClauseSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Clause.Properties.decider == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				deciderSettings.setToReference((OutputPin)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				OutputPin eObject = UMLFactory.eINSTANCE.createOutputPin();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				deciderSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Clause.Properties.bodyOutput == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof OutputPin) {
					bodyOutputSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					bodyOutputSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			ClausePropertiesEditionPart basePart = (ClausePropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getClause_Test().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Clause.Properties.test))
				basePart.updateTest();
			if (UMLPackage.eINSTANCE.getClause_Body().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Clause.Properties.body))
				basePart.updateBody();
			if (UMLPackage.eINSTANCE.getClause_PredecessorClause().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Clause.Properties.predecessorClause))
				basePart.updatePredecessorClause();
			if (UMLPackage.eINSTANCE.getClause_SuccessorClause().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Clause.Properties.successorClause))
				basePart.updateSuccessorClause();
			if (UMLPackage.eINSTANCE.getClause_Decider().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Clause.Properties.decider))
				basePart.setDecider((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClause_BodyOutput().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Clause.Properties.bodyOutput))
				basePart.updateBodyOutput();
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.Clause.Properties.decider;
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
