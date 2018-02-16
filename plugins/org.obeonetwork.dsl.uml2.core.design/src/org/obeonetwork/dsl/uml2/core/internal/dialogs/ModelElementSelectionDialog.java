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
package org.obeonetwork.dsl.uml2.core.internal.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.obeonetwork.dsl.uml2.core.api.wizards.ModelElementsSelectionDialogPatternMatcher;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Dialog to ask user to select an element.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ModelElementSelectionDialog extends org.eclipse.ui.dialogs.SelectionDialog {

	private class SelectionDialogTreeContentProvider extends AdapterFactoryContentProvider {
		/**
		 * Constructor.
		 *
		 * @param adapterFactory
		 *            Adapter factory
		 */
		public SelectionDialogTreeContentProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object[] getChildren(Object parentElement) {
			return super.getChildren(parentElement);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Object[] getElements(Object object) {
			if (object instanceof Collection) {
				return ((Collection)object).toArray();
			}
			return super.getElements(object);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasChildren(Object element) {
			return super.hasChildren(element);
		}
	}

	private class SelectionDialogTreeLabelProvider extends SiriusCommonLabelProvider {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public Color getBackground(Object element) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Color getForeground(final Object element) {

			Color foreground = null;

			if (!isSelectable.apply(element)) {
				foreground = VisualBindingManager.getDefault().getColorFromName("light_gray"); //$NON-NLS-1$
			}
			return foreground;
		}

		/**
		 * Set the selectable predicate. Predicate to find the selectable elements in the tree viewer.
		 *
		 * @param isSelectablePredicate
		 *            Selectable elements predicate
		 * @return Selectable predicate
		 */
		public Predicate<Object> setSelectablePredicate(Predicate<Object> isSelectablePredicate) {
			return isSelectable = isSelectablePredicate != null ? isSelectablePredicate
					: Predicates.alwaysFalse();
		}
	}

	/**
	 * Returns the adapter factory used by this viewer.
	 *
	 * @return The adapter factory used by this viewer.
	 */
	private static AdapterFactory getAdapterFactory() {
		final List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
		factories.add(new UMLItemProviderAdapterFactory());
		factories.add(new ResourceItemProviderAdapterFactory());
		factories.add(new EcoreItemProviderAdapterFactory());
		factories.add(new ReflectiveItemProviderAdapterFactory());
		return new ComposedAdapterFactory(factories);
	}

	/**
	 * A String used to identify the Text allowing user to type regular expression (can be used for testing).
	 */
	public static final String REGEXP_TOOL_TIP = "Expression that will be used to filer elements by name (for example 'abc', 'a?c', '*c'...)"; //$NON-NLS-1$

	/**
	 * The title of the Group allowing user to type Regular Expressions and filter the selection.
	 */
	private static final String REGEXP_TITLE = "Filter elements by name"; //$NON-NLS-1$

	/**
	 * The String explaining to user how to use regular expressions.
	 */
	private static final String REGEXP_EXPLANATIONS = "? = any character, * = any String"; //$NON-NLS-1$

	private final SelectionDialogTreeLabelProvider labelProvider;

	private ITreeContentProvider contentProvider;

	private TreeViewer treeViewer;

	private final int width = 60;

	private final int height = 18;

	private Predicate<Object> isSelectable;

	/**
	 * A matcher used to determine if a given element is matching the regular expression typed by user. It's
	 * updated each time the user modify the regular expression.
	 */
	protected ModelElementsSelectionDialogPatternMatcher patternMatcher;

	/**
	 * Constructor.
	 */
	public ModelElementSelectionDialog() {
		super(PlatformUI.getWorkbench().getDisplay().getActiveShell());
		initContentProvider();
		labelProvider = new SelectionDialogTreeLabelProvider();
		labelProvider.setSelectablePredicate(isSelectable);
		patternMatcher = new ModelElementsSelectionDialogPatternMatcher(""); //$NON-NLS-1$
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite composite = (Composite)super.createDialogArea(parent);
		createMessageArea(composite);
		treeViewer = createTreeViewer(composite);
		treeViewer.expandAll();
		final GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = convertWidthInCharsToPixels(width);
		data.heightHint = convertHeightInCharsToPixels(height);
		final Tree treeWidget = treeViewer.getTree();
		treeWidget.setLayoutData(data);
		treeWidget.setFont(parent.getFont());
		return composite;
	}

	/**
	 * This method has been overridden to be able to insert selection buttons between the top label and the
	 * tree viewer. {@inheritDoc}
	 */
	@Override
	protected Label createMessageArea(Composite composite) {
		final Label createMessageArea = super.createMessageArea(composite);
		// creating a text zone to allow user to type regular expressions
		createRegexpTypeZone(composite);
		return createMessageArea;
	}

	/**
	 * Creates a zone in which user will be able to type a Regular Expression to filter the shown elements.
	 *
	 * @param composite
	 *            the parent composite
	 */
	private void createRegexpTypeZone(Composite composite) {
		// Step 1 : create Group
		final Group expregGroup = new Group(composite, SWT.NONE);
		expregGroup.setText(REGEXP_TITLE);
		final GridLayout expregLayout = new GridLayout();
		expregGroup.setLayout(expregLayout);
		expregGroup.setFont(composite.getFont());
		expregGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Step 2 : create explanations zone
		final Label explanationsLabel = new Label(expregGroup, SWT.NONE);
		explanationsLabel.setText(REGEXP_EXPLANATIONS);

		// Step 3 : create the text zone in which user will type the expreg
		final Text regularExpressionText = new Text(expregGroup, SWT.BORDER);
		regularExpressionText.setToolTipText(REGEXP_TOOL_TIP);
		regularExpressionText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Step 4 : add modify listener to this textZone
		regularExpressionText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				final String typedRegex = ((Text)e.getSource()).getText();
				// Each time the regular expression is modified, the
				// patternMatcher is updated
				setPatternMatcher(new ModelElementsSelectionDialogPatternMatcher(typedRegex));
				refresh();
			}
		});
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
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			private void doSelectionChanged(Object[] array) {
				setResult(Arrays.asList(array));
			}

			public void selectionChanged(SelectionChangedEvent event) {
				doSelectionChanged(((IStructuredSelection)event.getSelection()).toArray());
			}
		});
		final Collection<EObject> roots = getAllRootsInSessions();
		final Collection<Object> inputs = Sets.newHashSet();
		inputs.addAll(roots);
		treeViewer.setInput(inputs);
		treeViewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				final Predicate<Object> isMatchinExpregPredicate = getRegexpMatchPredicate();
				return isOrHasDescendant(element, Predicates.and(isSelectable, isMatchinExpregPredicate));
			}
		});
		return treeViewer;
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
	 * Returns a Predicate indicating if an object is matching the Regular Expression currently typed by user.
	 *
	 * @return a Predicate indicating if an object is matching the Regular Expression currently typed by user
	 */
	public Predicate<Object> getRegexpMatchPredicate() {
		return patternMatcher.getMatchPredicate();
	}

	/**
	 * Init the content provider.
	 *
	 * @param notifications
	 * @param includeNodeLabel
	 *            include node label (if there are on border) in the tree content
	 */
	protected void initContentProvider() {
		final AdapterFactory adapterFactory = getAdapterFactory();
		contentProvider = new SelectionDialogTreeContentProvider(adapterFactory);
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
	 * Refreshes this dialog's viewer.
	 */
	public void refresh() {
		treeViewer.refresh();
	}

	/**
	 * Sets the matcher used to determine if a given DDiagramElement is matching the regular expression typed
	 * by user.
	 *
	 * @param patternMatcher
	 *            the patternMatcher to set
	 */
	public void setPatternMatcher(ModelElementsSelectionDialogPatternMatcher patternMatcher) {
		this.patternMatcher = patternMatcher;
	}

	/**
	 * Set selectable predicate.
	 *
	 * @param isSelectablePredicate
	 *            Selectable elements.
	 */
	public void setSelectablePredicate(Predicate isSelectablePredicate) {
		isSelectable = isSelectablePredicate;
	}
}
