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
package org.obeonetwork.dsl.uml2.properties.uml.parts.forms;

// Start of user code for imports
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
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
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ClausePropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, ClausePropertiesEditionPart {

		protected ReferencesTable test;
		protected List<ViewerFilter> testBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> testFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable body;
		protected List<ViewerFilter> bodyBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> bodyFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable predecessorClause;
		protected List<ViewerFilter> predecessorClauseBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> predecessorClauseFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable successorClause;
		protected List<ViewerFilter> successorClauseBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> successorClauseFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer decider;
		protected ReferencesTable bodyOutput;
		protected List<ViewerFilter> bodyOutputBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> bodyOutputFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ClausePropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createFigure(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 * 
	 */
	public Composite createFigure(final Composite parent, final FormToolkit widgetFactory) {
		ScrolledForm scrolledForm = widgetFactory.createScrolledForm(parent);
		Form form = scrolledForm.getForm();
		view = form.getBody();
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		view.setLayout(layout);
		createControls(widgetFactory, view);
		return scrolledForm;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createControls(org.eclipse.ui.forms.widgets.FormToolkit, org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public void createControls(final FormToolkit widgetFactory, Composite view) {
		CompositionSequence clauseStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = clauseStep.addStep(UmlViewsRepository.Clause.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.Clause.Properties.test);
		propertiesStep.addStep(UmlViewsRepository.Clause.Properties.body);
		propertiesStep.addStep(UmlViewsRepository.Clause.Properties.predecessorClause);
		propertiesStep.addStep(UmlViewsRepository.Clause.Properties.successorClause);
		propertiesStep.addStep(UmlViewsRepository.Clause.Properties.decider);
		propertiesStep.addStep(UmlViewsRepository.Clause.Properties.bodyOutput);
		
		
		composer = new PartComposer(clauseStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Clause.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Clause.Properties.test) {
					return createTestReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Clause.Properties.body) {
					return createBodyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Clause.Properties.predecessorClause) {
					return createPredecessorClauseReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Clause.Properties.successorClause) {
					return createSuccessorClauseReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Clause.Properties.decider) {
					return createDeciderFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Clause.Properties.bodyOutput) {
					return createBodyOutputReferencesTable(widgetFactory, parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}
	/**
	 * 
	 */
	protected Composite createPropertiesGroup(FormToolkit widgetFactory, final Composite parent) {
		Section propertiesSection = widgetFactory.createSection(parent, Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		propertiesSection.setText(UmlMessages.ClausePropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesSectionData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesSectionData.horizontalSpan = 3;
		propertiesSection.setLayoutData(propertiesSectionData);
		Composite propertiesGroup = widgetFactory.createComposite(propertiesSection);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		propertiesSection.setClient(propertiesGroup);
		return propertiesGroup;
	}

	/**
	 * 
	 */
	protected Composite createTestReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.test = new ReferencesTable(UmlMessages.ClausePropertiesEditionPart_TestLabel, new ReferencesTableListener	() {
			public void handleAdd() { addTest(); }
			public void handleEdit(EObject element) { editTest(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveTest(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromTest(element); }
			public void navigateTo(EObject element) { }
		});
		this.test.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Clause.Properties.test, UmlViewsRepository.FORM_KIND));
		this.test.createControls(parent, widgetFactory);
		this.test.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.test, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData testData = new GridData(GridData.FILL_HORIZONTAL);
		testData.horizontalSpan = 3;
		this.test.setLayoutData(testData);
		this.test.disableMove();
		test.setID(UmlViewsRepository.Clause.Properties.test);
		test.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addTest() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(test.getInput(), testFilters, testBusinessFilters,
		"test", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.test,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				test.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveTest(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.test, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		test.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromTest(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.test, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		test.refresh();
	}

	/**
	 * 
	 */
	protected void editTest(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				test.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createBodyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.body = new ReferencesTable(UmlMessages.ClausePropertiesEditionPart_BodyLabel, new ReferencesTableListener	() {
			public void handleAdd() { addBody(); }
			public void handleEdit(EObject element) { editBody(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveBody(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromBody(element); }
			public void navigateTo(EObject element) { }
		});
		this.body.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Clause.Properties.body, UmlViewsRepository.FORM_KIND));
		this.body.createControls(parent, widgetFactory);
		this.body.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.body, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData bodyData = new GridData(GridData.FILL_HORIZONTAL);
		bodyData.horizontalSpan = 3;
		this.body.setLayoutData(bodyData);
		this.body.disableMove();
		body.setID(UmlViewsRepository.Clause.Properties.body);
		body.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addBody() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(body.getInput(), bodyFilters, bodyBusinessFilters,
		"body", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.body,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				body.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveBody(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.body, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		body.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromBody(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.body, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		body.refresh();
	}

	/**
	 * 
	 */
	protected void editBody(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				body.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createPredecessorClauseReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.predecessorClause = new ReferencesTable(UmlMessages.ClausePropertiesEditionPart_PredecessorClauseLabel, new ReferencesTableListener	() {
			public void handleAdd() { addPredecessorClause(); }
			public void handleEdit(EObject element) { editPredecessorClause(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePredecessorClause(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPredecessorClause(element); }
			public void navigateTo(EObject element) { }
		});
		this.predecessorClause.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Clause.Properties.predecessorClause, UmlViewsRepository.FORM_KIND));
		this.predecessorClause.createControls(parent, widgetFactory);
		this.predecessorClause.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.predecessorClause, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData predecessorClauseData = new GridData(GridData.FILL_HORIZONTAL);
		predecessorClauseData.horizontalSpan = 3;
		this.predecessorClause.setLayoutData(predecessorClauseData);
		this.predecessorClause.disableMove();
		predecessorClause.setID(UmlViewsRepository.Clause.Properties.predecessorClause);
		predecessorClause.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addPredecessorClause() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(predecessorClause.getInput(), predecessorClauseFilters, predecessorClauseBusinessFilters,
		"predecessorClause", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.predecessorClause,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				predecessorClause.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void movePredecessorClause(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.predecessorClause, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		predecessorClause.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromPredecessorClause(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.predecessorClause, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		predecessorClause.refresh();
	}

	/**
	 * 
	 */
	protected void editPredecessorClause(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				predecessorClause.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createSuccessorClauseReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.successorClause = new ReferencesTable(UmlMessages.ClausePropertiesEditionPart_SuccessorClauseLabel, new ReferencesTableListener	() {
			public void handleAdd() { addSuccessorClause(); }
			public void handleEdit(EObject element) { editSuccessorClause(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveSuccessorClause(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromSuccessorClause(element); }
			public void navigateTo(EObject element) { }
		});
		this.successorClause.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Clause.Properties.successorClause, UmlViewsRepository.FORM_KIND));
		this.successorClause.createControls(parent, widgetFactory);
		this.successorClause.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.successorClause, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData successorClauseData = new GridData(GridData.FILL_HORIZONTAL);
		successorClauseData.horizontalSpan = 3;
		this.successorClause.setLayoutData(successorClauseData);
		this.successorClause.disableMove();
		successorClause.setID(UmlViewsRepository.Clause.Properties.successorClause);
		successorClause.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addSuccessorClause() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(successorClause.getInput(), successorClauseFilters, successorClauseBusinessFilters,
		"successorClause", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.successorClause,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				successorClause.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveSuccessorClause(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.successorClause, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		successorClause.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromSuccessorClause(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.successorClause, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		successorClause.refresh();
	}

	/**
	 * 
	 */
	protected void editSuccessorClause(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				successorClause.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createDeciderFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ClausePropertiesEditionPart_DeciderLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Clause.Properties.decider, UmlViewsRepository.FORM_KIND));
		decider = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Clause.Properties.decider, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(decider);
		decider.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData deciderData = new GridData(GridData.FILL_HORIZONTAL);
		decider.setLayoutData(deciderData);
		decider.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.decider, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getDecider()));
			}

		});
		decider.setID(UmlViewsRepository.Clause.Properties.decider);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Clause.Properties.decider, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createBodyOutputReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.bodyOutput = new ReferencesTable(UmlMessages.ClausePropertiesEditionPart_BodyOutputLabel, new ReferencesTableListener	() {
			public void handleAdd() { addBodyOutput(); }
			public void handleEdit(EObject element) { editBodyOutput(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveBodyOutput(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromBodyOutput(element); }
			public void navigateTo(EObject element) { }
		});
		this.bodyOutput.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Clause.Properties.bodyOutput, UmlViewsRepository.FORM_KIND));
		this.bodyOutput.createControls(parent, widgetFactory);
		this.bodyOutput.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.bodyOutput, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData bodyOutputData = new GridData(GridData.FILL_HORIZONTAL);
		bodyOutputData.horizontalSpan = 3;
		this.bodyOutput.setLayoutData(bodyOutputData);
		this.bodyOutput.disableMove();
		bodyOutput.setID(UmlViewsRepository.Clause.Properties.bodyOutput);
		bodyOutput.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addBodyOutput() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(bodyOutput.getInput(), bodyOutputFilters, bodyOutputBusinessFilters,
		"bodyOutput", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.bodyOutput,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				bodyOutput.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveBodyOutput(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.bodyOutput, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		bodyOutput.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromBodyOutput(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClausePropertiesEditionPartForm.this, UmlViewsRepository.Clause.Properties.bodyOutput, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		bodyOutput.refresh();
	}

	/**
	 * 
	 */
	protected void editBodyOutput(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				bodyOutput.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#initTest(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initTest(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		test.setContentProvider(contentProvider);
		test.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#updateTest()
	 * 
	 */
	public void updateTest() {
	test.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addFilterTest(ViewerFilter filter)
	 * 
	 */
	public void addFilterToTest(ViewerFilter filter) {
		testFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addBusinessFilterTest(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToTest(ViewerFilter filter) {
		testBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#isContainedInTestTable(EObject element)
	 * 
	 */
	public boolean isContainedInTestTable(EObject element) {
		return ((ReferencesTableSettings)test.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#initBody(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initBody(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		body.setContentProvider(contentProvider);
		body.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#updateBody()
	 * 
	 */
	public void updateBody() {
	body.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addFilterBody(ViewerFilter filter)
	 * 
	 */
	public void addFilterToBody(ViewerFilter filter) {
		bodyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addBusinessFilterBody(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToBody(ViewerFilter filter) {
		bodyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#isContainedInBodyTable(EObject element)
	 * 
	 */
	public boolean isContainedInBodyTable(EObject element) {
		return ((ReferencesTableSettings)body.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#initPredecessorClause(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPredecessorClause(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		predecessorClause.setContentProvider(contentProvider);
		predecessorClause.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#updatePredecessorClause()
	 * 
	 */
	public void updatePredecessorClause() {
	predecessorClause.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addFilterPredecessorClause(ViewerFilter filter)
	 * 
	 */
	public void addFilterToPredecessorClause(ViewerFilter filter) {
		predecessorClauseFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addBusinessFilterPredecessorClause(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToPredecessorClause(ViewerFilter filter) {
		predecessorClauseBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#isContainedInPredecessorClauseTable(EObject element)
	 * 
	 */
	public boolean isContainedInPredecessorClauseTable(EObject element) {
		return ((ReferencesTableSettings)predecessorClause.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#initSuccessorClause(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initSuccessorClause(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		successorClause.setContentProvider(contentProvider);
		successorClause.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#updateSuccessorClause()
	 * 
	 */
	public void updateSuccessorClause() {
	successorClause.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addFilterSuccessorClause(ViewerFilter filter)
	 * 
	 */
	public void addFilterToSuccessorClause(ViewerFilter filter) {
		successorClauseFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addBusinessFilterSuccessorClause(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToSuccessorClause(ViewerFilter filter) {
		successorClauseBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#isContainedInSuccessorClauseTable(EObject element)
	 * 
	 */
	public boolean isContainedInSuccessorClauseTable(EObject element) {
		return ((ReferencesTableSettings)successorClause.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#getDecider()
	 * 
	 */
	public EObject getDecider() {
		if (decider.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) decider.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#initDecider(EObjectFlatComboSettings)
	 */
	public void initDecider(EObjectFlatComboSettings settings) {
		decider.setInput(settings);
		if (current != null) {
			decider.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#setDecider(EObject newValue)
	 * 
	 */
	public void setDecider(EObject newValue) {
		if (newValue != null) {
			decider.setSelection(new StructuredSelection(newValue));
		} else {
			decider.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#setDeciderButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDeciderButtonMode(ButtonsModeEnum newValue) {
		decider.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addFilterDecider(ViewerFilter filter)
	 * 
	 */
	public void addFilterToDecider(ViewerFilter filter) {
		decider.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addBusinessFilterDecider(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToDecider(ViewerFilter filter) {
		decider.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#initBodyOutput(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initBodyOutput(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		bodyOutput.setContentProvider(contentProvider);
		bodyOutput.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#updateBodyOutput()
	 * 
	 */
	public void updateBodyOutput() {
	bodyOutput.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addFilterBodyOutput(ViewerFilter filter)
	 * 
	 */
	public void addFilterToBodyOutput(ViewerFilter filter) {
		bodyOutputFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#addBusinessFilterBodyOutput(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToBodyOutput(ViewerFilter filter) {
		bodyOutputBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClausePropertiesEditionPart#isContainedInBodyOutputTable(EObject element)
	 * 
	 */
	public boolean isContainedInBodyOutputTable(EObject element) {
		return ((ReferencesTableSettings)bodyOutput.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.Clause_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
