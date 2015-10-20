/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Dialog to ask user confirmation after an element deletion.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ConfirmDeletionDialog extends MessageDialog {

	/**
	 * Represents the various kinds of filtering supported by the tree viewer.
	 */
	protected enum FilteringMode {
		/**
		 * Filtering mode in which all elements are considered.
		 */
		SHOW_ALL("all elements"), //$NON-NLS-1$
		/**
		 * Filtering mode in which only deleted elements are considered.
		 */
		SHOW_ONLY_DELETED_ELEMENTS("only deleted elements"); //$NON-NLS-1$

		private final String name;

		private FilteringMode(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private class ModeFilter extends ViewerFilter {
		Predicate<Object> isDeletedPredicate;

		public ModeFilter(Predicate<Object> isDeletedPredicate) {
			this.isDeletedPredicate = isDeletedPredicate;
		}

		private boolean isMatchingOrHasMatchingDescendantsOnlyDeletedElementsMode(Object element) {
			if (element instanceof EObject) {
				return isOrHasDescendant(element, isDeletedPredicate);
			}
			return false;
		}

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			boolean show = true;
			// Step 1: only showing deleted element if required
			switch (mode) {
				case SHOW_ONLY_DELETED_ELEMENTS:
					show = isMatchingOrHasMatchingDescendantsOnlyDeletedElementsMode(element);
					break;
				default:
					show = true;
					break;
			}

			return show;
		}
	}

	private ModeFilter filter;

	private TreeViewer treeViewer;

	private final ConfirmDeletionTreeLabelProvider labelProvider;

	private ITreeContentProvider contentProvider;

	private final int width = 60;

	private final int height = 18;

	/**
	 * The filtering mode currently associated to the tree viewer. Can be :
	 * <ul>
	 * <li>All elements</li>
	 * <li>Only deleted elements</li>
	 * </ul>
	 */
	protected FilteringMode mode = FilteringMode.SHOW_ONLY_DELETED_ELEMENTS;

	/**
	 * Constructor.
	 *
	 * @param allDetachedObjects
	 *            Semantic elements to delete
	 */
	public ConfirmDeletionDialog(final Map<EObject, Object> allDetachedObjects) {
		super(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Deletion", null, //$NON-NLS-1$
				"The following red elements will be updated/deleted. Confirm deletion ?", 0, new String[] { //$NON-NLS-1$
						IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL},
				1);
		initContentProvider(allDetachedObjects.values());
		labelProvider = new ConfirmDeletionTreeLabelProvider();
		final Predicate<Object> isDeletedPredicate = new Predicate<Object>() {
			public boolean apply(Object input) {
				return allDetachedObjects.keySet().contains(input);
			}
		};
		labelProvider.setDeletedPredicate(isDeletedPredicate);
		filter = new ModeFilter(isDeletedPredicate);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite composite = (Composite)super.createDialogArea(parent);
		treeViewer = createTreeViewer(composite);
		treeViewer.expandAll();
		final GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = convertWidthInCharsToPixels(width);
		data.heightHint = convertHeightInCharsToPixels(height);
		final Tree treeWidget = treeViewer.getTree();
		treeWidget.setLayoutData(data);
		treeWidget.setFont(parent.getFont());
		createSelectionButtonsAfterMessageArea(composite);
		return composite;
	}

	/**
	 * This method has been overridden to be able to insert selection buttons between the top label and the
	 * tree viewer. {@inheritDoc}
	 */
	@Override
	protected Label createMessageArea(Composite composite) {
		final Label label = new Label(composite, SWT.NONE);
		if (message != null) {
			label.setText(message);
		}
		label.setFont(composite.getFont());
		return label;
	}

	/**
	 * Creates selection buttons.
	 *
	 * @param composite
	 *            the parent composite
	 * @return the selection buttons composite
	 */
	protected Composite createSelectionButtonsAfterMessageArea(Composite composite) {
		final Composite buttonComposite = new Composite(composite, SWT.RIGHT);
		final GridLayout layout = new GridLayout();
		layout.numColumns = 7;
		layout.makeColumnsEqualWidth = false;
		buttonComposite.setLayout(layout);
		buttonComposite.setFont(composite.getFont());

		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		composite.setData(data);

		new Label(buttonComposite, SWT.LEAD).setText("Show"); //$NON-NLS-1$
		final Combo choices = new Combo(buttonComposite, SWT.READ_ONLY);
		choices.add(FilteringMode.SHOW_ONLY_DELETED_ELEMENTS.getName());
		choices.add(FilteringMode.SHOW_ALL.getName());
		choices.select(0);
		choices.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (choices.getSelectionIndex()) {
					case 0:
						updateFilteringMode(FilteringMode.SHOW_ONLY_DELETED_ELEMENTS);
						break;
					case 1:
						updateFilteringMode(FilteringMode.SHOW_ALL);
						break;
					default:
						throw new RuntimeException();
				}
			}
		});
		data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 2;
		choices.setLayoutData(data);

		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;

		return buttonComposite;
	}

	/**
	 * Creates the tree viewer.
	 *
	 * @param parent
	 *            the parent composite
	 * @return the tree viewer
	 */
	protected TreeViewer createTreeViewer(Composite parent) {
		treeViewer = new TreeViewer(parent);
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(labelProvider);
		final Collection<EObject> roots = getAllRootsInSessions();
		final Collection<Object> inputs = Sets.newHashSet();
		inputs.addAll(roots);
		treeViewer.addFilter(filter);
		treeViewer.setInput(inputs);
		return treeViewer;
	}

	/**
	 * Returns the adapter factory used by this viewer.
	 *
	 * @return The adapter factory used by this viewer.
	 */
	private AdapterFactory getAdapterFactory() {
		final List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
		factories.add(new UMLItemProviderAdapterFactory());
		factories.add(new ResourceItemProviderAdapterFactory());
		factories.add(new EcoreItemProviderAdapterFactory());
		factories.add(new ReflectiveItemProviderAdapterFactory());
		return new ComposedAdapterFactory(factories);
	}

	private Collection<EObject> getAllRootsInSessions() {
		final Collection<EObject> roots = new ArrayList<EObject>();
		for (final Session session : SessionManager.INSTANCE.getSessions()) {
			for (final Resource childRes : session.getSemanticResources()) {
				for (final EObject eObject : childRes.getContents()) {
					if (eObject instanceof Element) {
						roots.add(eObject);
					}
				}
			}
		}
		return roots;
	}

	/**
	 * Init the content provider.
	 *
	 * @param notifications
	 * @param includeNodeLabel
	 *            include node label (if there are on border) in the tree content
	 */
	protected void initContentProvider(Collection<Object> notifications) {
		final AdapterFactory adapterFactory = getAdapterFactory();
		contentProvider = new ConfirmDeletionTreeContentProvider(adapterFactory, notifications);
	}

	private boolean isOrHasDescendant(Object element, final Predicate<Object> pred) {
		final boolean matches = pred.apply(element);
		if (matches) {
			return true;
		}
		return Iterables.any(Arrays.asList(contentProvider.getChildren(element)), new Predicate<Object>() {
			public boolean apply(Object input) {
				return isOrHasDescendant(input, pred);
			}
		});
	}

	/**
	 * Open dialog.
	 *
	 * @return True if user confirm the deletion otherwise false
	 */
	public boolean openConfirm() {
		final int result[] = new int[] {0};
		result[0] = super.open();
		return result[0] == IDialogConstants.OK_ID;
	}

	/**
	 * Refreshes this dialog's viewer.
	 */
	public void refresh() {
		treeViewer.refresh();
	}

	/**
	 * Updates the treeViewer after a change in the filteringMode or the typed regular expression.
	 *
	 * @param filteringMode
	 *            the new filtering mode
	 */
	public void updateFilteringMode(FilteringMode filteringMode) {
		mode = filteringMode;
		refresh();
		// We expand the tree so that all elements matching the regular
		// expression (i.e. all visible leafs) are correctly shown
		treeViewer.expandAll();
	}
}
