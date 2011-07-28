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
package org.obeonetwork.dsl.uml2.properties.uml.parts.impl;

// Start of user code for imports
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class InterruptibleActivityRegionPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, InterruptibleActivityRegionPropertiesEditionPart {

	protected EObjectFlatComboViewer inActivity;
	protected ReferencesTable node;
	protected List<ViewerFilter> nodeBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> nodeFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable interruptingEdge;
	protected List<ViewerFilter> interruptingEdgeBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> interruptingEdgeFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public InterruptibleActivityRegionPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createFigure(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public Composite createFigure(final Composite parent) {
		view = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		view.setLayout(layout);
		createControls(view);
		return view;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createControls(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public void createControls(Composite view) { 
		CompositionSequence interruptibleActivityRegionStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = interruptibleActivityRegionStep.addStep(UmlViewsRepository.InterruptibleActivityRegion.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity);
		propertiesStep.addStep(UmlViewsRepository.InterruptibleActivityRegion.Properties.node);
		propertiesStep.addStep(UmlViewsRepository.InterruptibleActivityRegion.Properties.interruptingEdge);
		
		
		composer = new PartComposer(interruptibleActivityRegionStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.InterruptibleActivityRegion.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity) {
					return createInActivityFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.InterruptibleActivityRegion.Properties.node) {
					return createNodeAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.InterruptibleActivityRegion.Properties.interruptingEdge) {
					return createInterruptingEdgeAdvancedReferencesTable(parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	/**
	 * 
	 */
	protected Composite createPropertiesGroup(Composite parent) {
		Group propertiesGroup = new Group(parent, SWT.NONE);
		propertiesGroup.setText(UmlMessages.InterruptibleActivityRegionPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createInActivityFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.InterruptibleActivityRegionPropertiesEditionPart_InActivityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity, UmlViewsRepository.SWT_KIND));
		inActivity = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity, UmlViewsRepository.SWT_KIND));
		inActivity.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		inActivity.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InterruptibleActivityRegionPropertiesEditionPartImpl.this, UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getInActivity()));
			}

		});
		GridData inActivityData = new GridData(GridData.FILL_HORIZONTAL);
		inActivity.setLayoutData(inActivityData);
		inActivity.setID(UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.InterruptibleActivityRegion.Properties.inActivity, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createNodeAdvancedReferencesTable(Composite parent) {
		this.node = new ReferencesTable(UmlMessages.InterruptibleActivityRegionPropertiesEditionPart_NodeLabel, new ReferencesTableListener() {
			public void handleAdd() { addNode(); }
			public void handleEdit(EObject element) { editNode(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveNode(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromNode(element); }
			public void navigateTo(EObject element) { }
		});
		this.node.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InterruptibleActivityRegion.Properties.node, UmlViewsRepository.SWT_KIND));
		this.node.createControls(parent);
		this.node.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InterruptibleActivityRegionPropertiesEditionPartImpl.this, UmlViewsRepository.InterruptibleActivityRegion.Properties.node, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData nodeData = new GridData(GridData.FILL_HORIZONTAL);
		nodeData.horizontalSpan = 3;
		this.node.setLayoutData(nodeData);
		this.node.disableMove();
		node.setID(UmlViewsRepository.InterruptibleActivityRegion.Properties.node);
		node.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addNode() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(node.getInput(), nodeFilters, nodeBusinessFilters,
		"node", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InterruptibleActivityRegionPropertiesEditionPartImpl.this, UmlViewsRepository.InterruptibleActivityRegion.Properties.node,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				node.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveNode(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InterruptibleActivityRegionPropertiesEditionPartImpl.this, UmlViewsRepository.InterruptibleActivityRegion.Properties.node, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		node.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromNode(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InterruptibleActivityRegionPropertiesEditionPartImpl.this, UmlViewsRepository.InterruptibleActivityRegion.Properties.node, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		node.refresh();
	}

	/**
	 * 
	 */
	protected void editNode(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				node.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createInterruptingEdgeAdvancedReferencesTable(Composite parent) {
		this.interruptingEdge = new ReferencesTable(UmlMessages.InterruptibleActivityRegionPropertiesEditionPart_InterruptingEdgeLabel, new ReferencesTableListener() {
			public void handleAdd() { addInterruptingEdge(); }
			public void handleEdit(EObject element) { editInterruptingEdge(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveInterruptingEdge(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromInterruptingEdge(element); }
			public void navigateTo(EObject element) { }
		});
		this.interruptingEdge.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InterruptibleActivityRegion.Properties.interruptingEdge, UmlViewsRepository.SWT_KIND));
		this.interruptingEdge.createControls(parent);
		this.interruptingEdge.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InterruptibleActivityRegionPropertiesEditionPartImpl.this, UmlViewsRepository.InterruptibleActivityRegion.Properties.interruptingEdge, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData interruptingEdgeData = new GridData(GridData.FILL_HORIZONTAL);
		interruptingEdgeData.horizontalSpan = 3;
		this.interruptingEdge.setLayoutData(interruptingEdgeData);
		this.interruptingEdge.disableMove();
		interruptingEdge.setID(UmlViewsRepository.InterruptibleActivityRegion.Properties.interruptingEdge);
		interruptingEdge.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addInterruptingEdge() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(interruptingEdge.getInput(), interruptingEdgeFilters, interruptingEdgeBusinessFilters,
		"interruptingEdge", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InterruptibleActivityRegionPropertiesEditionPartImpl.this, UmlViewsRepository.InterruptibleActivityRegion.Properties.interruptingEdge,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				interruptingEdge.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveInterruptingEdge(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InterruptibleActivityRegionPropertiesEditionPartImpl.this, UmlViewsRepository.InterruptibleActivityRegion.Properties.interruptingEdge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		interruptingEdge.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromInterruptingEdge(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InterruptibleActivityRegionPropertiesEditionPartImpl.this, UmlViewsRepository.InterruptibleActivityRegion.Properties.interruptingEdge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		interruptingEdge.refresh();
	}

	/**
	 * 
	 */
	protected void editInterruptingEdge(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				interruptingEdge.refresh();
			}
		}
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		// Start of user code for tab synchronization

// End of user code
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#getInActivity()
	 * 
	 */
	public EObject getInActivity() {
		if (inActivity.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) inActivity.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#initInActivity(EObjectFlatComboSettings)
	 */
	public void initInActivity(EObjectFlatComboSettings settings) {
		inActivity.setInput(settings);
		if (current != null) {
			inActivity.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#setInActivity(EObject newValue)
	 * 
	 */
	public void setInActivity(EObject newValue) {
		if (newValue != null) {
			inActivity.setSelection(new StructuredSelection(newValue));
		} else {
			inActivity.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#setInActivityButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInActivityButtonMode(ButtonsModeEnum newValue) {
		inActivity.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#addFilterInActivity(ViewerFilter filter)
	 * 
	 */
	public void addFilterToInActivity(ViewerFilter filter) {
		inActivity.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#addBusinessFilterInActivity(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToInActivity(ViewerFilter filter) {
		inActivity.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#initNode(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initNode(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		node.setContentProvider(contentProvider);
		node.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#updateNode()
	 * 
	 */
	public void updateNode() {
	node.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#addFilterNode(ViewerFilter filter)
	 * 
	 */
	public void addFilterToNode(ViewerFilter filter) {
		nodeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#addBusinessFilterNode(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToNode(ViewerFilter filter) {
		nodeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#isContainedInNodeTable(EObject element)
	 * 
	 */
	public boolean isContainedInNodeTable(EObject element) {
		return ((ReferencesTableSettings)node.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#initInterruptingEdge(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInterruptingEdge(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		interruptingEdge.setContentProvider(contentProvider);
		interruptingEdge.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#updateInterruptingEdge()
	 * 
	 */
	public void updateInterruptingEdge() {
	interruptingEdge.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#addFilterInterruptingEdge(ViewerFilter filter)
	 * 
	 */
	public void addFilterToInterruptingEdge(ViewerFilter filter) {
		interruptingEdgeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#addBusinessFilterInterruptingEdge(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToInterruptingEdge(ViewerFilter filter) {
		interruptingEdgeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InterruptibleActivityRegionPropertiesEditionPart#isContainedInInterruptingEdgeTable(EObject element)
	 * 
	 */
	public boolean isContainedInInterruptingEdgeTable(EObject element) {
		return ((ReferencesTableSettings)interruptingEdge.getInput()).contains(element);
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.InterruptibleActivityRegion_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
