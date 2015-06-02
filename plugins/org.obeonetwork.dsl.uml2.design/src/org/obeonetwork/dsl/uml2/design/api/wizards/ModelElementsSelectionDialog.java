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

package org.obeonetwork.dsl.uml2.design.api.wizards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Device;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionEnvironment;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.obeonetwork.dsl.uml2.design.internal.listeners.UmlDesignerSessionManagerListener;
import org.obeonetwork.dsl.uml2.design.internal.services.ElementServices;
import org.obeonetwork.dsl.uml2.design.internal.services.UIServices;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Dialog to select semantic model elements. Derived from @see DiagramElementsSelectionDialog.
 *
 * @author Melani Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class ModelElementsSelectionDialog {

	/**
	 * A customized version of CheckedTreeSelectionDialog with a combo to filter the view to show all
	 * elements/only checked elements/only unchecked elements.
	 */
	protected final class CustomTreeSelectionDialog extends CheckedTreeSelectionDialog {

		/**
		 * A String used to identify the Text allowing user to type regular expression (can be used for
		 * testing).
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

		/**
		 * A matcher used to determine if a given DDiagramElement is matching the regular expression typed by
		 * user. It's updated each time the user modify the regular expression.
		 */
		protected ModelElementsSelectionDialogPatternMatcher patternMatcher;

		/**
		 * Collection of elements currently checked by user.
		 */
		private final Set<Object> checkedElements = Sets.newHashSet();

		/**
		 * Constructor.
		 *
		 * @param parent
		 *            Shell
		 * @param labelProvider
		 *            Label provider
		 * @param contentProvider
		 *            Content provider
		 */
		private CustomTreeSelectionDialog(Shell parent, ILabelProvider labelProvider,
				ITreeContentProvider contentProvider) {
			super(parent, labelProvider, contentProvider);
			patternMatcher = new ModelElementsSelectionDialogPatternMatcher(""); //$NON-NLS-1$
		}

		/**
		 * Add button.
		 *
		 * @param parent
		 *            Composite
		 * @param toolTipText
		 *            Tooltip
		 * @param image
		 *            Image
		 * @param action
		 *            Add action
		 * @return Add button
		 */
		private Button addButton(Composite parent, String toolTipText, Image image, SelectionListener action) {
			final Button button = new Button(parent, SWT.PUSH);
			button.setToolTipText(toolTipText);
			button.setImage(image);
			button.addSelectionListener(action);
			return button;
		}

		/**
		 * Check all elements in tree viewer.
		 */
		@SuppressWarnings({"rawtypes", "unchecked"})
		private void checkAll() {
			final Collection roots = (Collection)getTreeViewer().getInput();
			setRecursiveState(roots, true);
		}

		/**
		 * Collapse tree viewer.
		 */
		private void collapseAll() {
			getTreeViewer().collapseAll();
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.jface.dialogs.Dialog#createContents(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		protected Control createContents(Composite parent) {
			final Control result = super.createContents(parent);
			getTreeViewer().setCheckStateProvider(new ICheckStateProvider() {

				public boolean isChecked(Object element) {
					return checkedElements.contains(element);
				}

				public boolean isGrayed(Object element) {
					return isGrayed.apply(element);
				}
			});
			getTreeViewer().addCheckStateListener(new ICheckStateListener() {
				public void checkStateChanged(CheckStateChangedEvent event) {
					if (!isGrayed.apply(event.getElement())) {
						if (event.getChecked()) {
							checkedElements.add(event.getElement());
						} else {
							checkedElements.remove(event.getElement());
						}
					}
				}
			});
			getTreeViewer().expandToLevel(2);
			return result;
		}

		/**
		 * This method has been overridden to be able to insert selection buttons between the top label and
		 * the tree viewer. {@inheritDoc}
		 */
		@Override
		protected Label createMessageArea(Composite composite) {
			final Label createMessageArea = super.createMessageArea(composite);

			createSelectionButtonsAfterMessageArea(composite);

			// creating a text zone to allow user to type regular expressions
			createRegexpTypeZone(composite);
			return createMessageArea;
		}

		/**
		 * Creates a zone in which user will be able to type a Regular Expression to filter the shown
		 * elements.
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
					updateFilteringMode(mode);
				}
			});
		}

		/**
		 * This method has been overridden to remove the selection buttons that are generically created after
		 * the tree viewer. This method should not return a null value. Otherwise, in case of empty list we
		 * will have a NPE. {@inheritDoc}
		 */
		@Override
		protected Composite createSelectionButtons(Composite composite) {
			final Composite buttonComposite = new Composite(composite, SWT.RIGHT) {
				/**
				 * This method has been overridden to have an "empty" size for this composite. {@inheritDoc}
				 *
				 * @see org.eclipse.swt.widgets.Composite#computeSize(int, int, boolean)
				 */
				@Override
				public Point computeSize(int wHint, int hHint, boolean b) {
					return super.computeSize(0, 0, b);
				}
			};
			buttonComposite.setVisible(false);
			return buttonComposite;
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
			choices.add(FilteringMode.SHOW_ONLY_DIRECT_CHILDREN.getName());
			choices.add(FilteringMode.SHOW_ONLY_UNDISPLAYED_ELEMENTS.getName());
			choices.add(FilteringMode.SHOW_ALL.getName());
			choices.select(0);
			choices.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					switch (choices.getSelectionIndex()) {
						case 0:
							updateFilteringMode(FilteringMode.SHOW_ONLY_DIRECT_CHILDREN);
							break;
						case 1:
							updateFilteringMode(FilteringMode.SHOW_ONLY_UNDISPLAYED_ELEMENTS);
							break;
						case 2:
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
			addButton(buttonComposite,
					"Check All", //$NON-NLS-1$
					DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.CHECK_ALL_ICON),
					new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					checkAll();
				}
			}).setLayoutData(data);

			addButton(buttonComposite,
					"Uncheck All", //$NON-NLS-1$
					DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.UNCHECK_ALL_ICON),
					new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					uncheckAll();
				}
			}).setLayoutData(data);

			addButton(buttonComposite,
					"Expand All", //$NON-NLS-1$
					DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.EXPAND_ALL_ICON),
					new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					expandAll();
				}
			}).setLayoutData(data);

			addButton(buttonComposite,
					"Collapse All", //$NON-NLS-1$
					DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.COLLAPSE_ALL_ICON),
					new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					collapseAll();
				}
			}).setLayoutData(data);

			return buttonComposite;
		}

		private void expandAll() {
			getTreeViewer().expandAll();
		}

		/**
		 * Returns the elements currently checked by user.
		 *
		 * @return the elements currently checked by user
		 */
		public Set<Object> getCheckedElements() {
			return checkedElements;
		}

		/**
		 * Returns a Predicate indicating if an object is matching the Regular Expression currently typed by
		 * user.
		 *
		 * @return a Predicate indicating if an object is matching the Regular Expression currently typed by
		 *         user
		 */
		public Predicate<Object> getRegexpMatchPredicate() {
			return patternMatcher.getMatchPredicate();
		}

		/**
		 * Indicates if the given element is matching the currently typed regular expression.
		 *
		 * @param element
		 *            the element to test
		 * @return true if the given element is matching the currently typed regular expression, false
		 *         otherwise.
		 */
		public boolean isMatchingExpregOrHasMatchingExpregDescendantsAllMode(Object element) {
			return isOrHasDescendant(element, getRegexpMatchPredicate());
		}

		/**
		 * Indicates if the given element is checked <b>AND</b> is matching the currently typed regular
		 * expression.
		 *
		 * @param element
		 *            the element to test
		 * @return true if the given element is checked <b>AND</b> is matching the currently typed regular
		 *         expression, false otherwise.
		 */
		public boolean isMatchingExpregOrHasMatchingExpregDescendantsCheckedMode(Object element) {
			final Predicate<Object> isCheckedElementPredicate = Predicates.in(checkedElements);
			final Predicate<Object> isMatchinExpregPredicate = getRegexpMatchPredicate();
			return isOrHasDescendant(element,
					Predicates.and(isCheckedElementPredicate, isMatchinExpregPredicate));
		}

		/**
		 * Indicates if the given element is checked <b>AND</b> is matching the currently typed regular
		 * expression.
		 *
		 * @param element
		 *            the element to test
		 * @return true if the given element is checked <b>AND</b> is matching the currently typed regular
		 *         expression, false otherwise.
		 */
		@SuppressWarnings("unchecked")
		public boolean isMatchingExpregOrHasMatchingExpregDescendantsSubMode(Object element) {
			if (element instanceof EObject) {
				@SuppressWarnings("rawtypes")
				final List subElements = Lists.newArrayList();
				Iterators.addAll(subElements,
						Iterators.filter(eObject.eAllContents(), Predicates.instanceOf(Element.class)));
				final Predicate<Object> isSubElementPredicate = Predicates.in(subElements);
				final Predicate<Object> isMatchinExpregPredicate = getRegexpMatchPredicate();
				return isOrHasDescendant(element,
						Predicates.and(isSubElementPredicate, isMatchinExpregPredicate));
			}
			return false;
		}

		/**
		 * Indicates if the given element is unchecked <b>AND</b> is matching the currently typed regular
		 * expression.
		 *
		 * @param element
		 *            the element to test
		 * @return true if the given element is unchecked <b>AND</b> is matching the currently typed regular
		 *         expression, false otherwise.
		 */
		public boolean isMatchingExpregOrHasMatchingExpregDescendantsUncheckedMode(Object element) {
			final Predicate<Object> isUncheckedElementPredicate = Predicates.not(Predicates
					.in(checkedElements));
			final Predicate<Object> isMatchinExpregPredicate = getRegexpMatchPredicate();
			return isOrHasDescendant(element,
					Predicates.and(isUncheckedElementPredicate, isMatchinExpregPredicate));
		}

		/**
		 * Indicates if the given element is checked <b>AND</b> is matching the currently typed regular
		 * expression.
		 *
		 * @param element
		 *            the element to test
		 * @return true if the given element is checked <b>AND</b> is matching the currently typed regular
		 *         expression, false otherwise.
		 */
		public boolean isMatchingExpregOrHasMatchingExpregDescendantsUnrepresentedMode(Object element) {
			if (element instanceof EObject) {
				@SuppressWarnings("rawtypes")
				final Collection existingElementsOnDiagram = UIServices.INSTANCE.getDisplayedNodes(diagram);
				@SuppressWarnings("unchecked")
				final Predicate<Object> isSubElementPredicate = Predicates.not(Predicates
						.in(existingElementsOnDiagram));
				final Predicate<Object> isMatchinExpregPredicate = getRegexpMatchPredicate();
				return isOrHasDescendant(element,
						Predicates.and(isSubElementPredicate, isMatchinExpregPredicate));
			}
			return false;
		}

		/**
		 * Indicates if the given element or at least one of its children checks the given predicate.
		 *
		 * @param element
		 *            the element to check
		 * @param pred
		 *            the predicate to use
		 * @return true if the given element or at least one of its children checks the given predicate, false
		 *         otherwise
		 */
		private boolean isOrHasDescendant(Object element, final Predicate<Object> pred) {
			final boolean matches = pred.apply(element);
			if (matches) {
				return true;
			}
			return Iterables.any(Arrays.asList(contentProvider.getChildren(element)),
					new Predicate<Object>() {
				public boolean apply(Object input) {
					return isOrHasDescendant(input, pred);
				}
			});
		}

		/**
		 * Refreshes this dialog's viewer.
		 */
		public void refresh() {
			getTreeViewer().refresh();
		}

		@Override
		public void setInitialElementSelections(@SuppressWarnings("rawtypes") List selectedElements) {
			@SuppressWarnings("unchecked")
			final List<Object> filteredSeletection = Lists.newArrayList(Iterables.filter(selectedElements,
					Predicates.not(isGrayed)));
			checkedElements.addAll(filteredSeletection);
			super.setInitialElementSelections(filteredSeletection);
		}

		@Override
		public void setInitialSelections(Object[] selectedElements) {
			setInitialElementSelections(Lists.newArrayList(selectedElements));
		}

		/**
		 * Sets the matcher used to determine if a given DDiagramElement is matching the regular expression
		 * typed by user.
		 *
		 * @param patternMatcher
		 *            the patternMatcher to set
		 */
		public void setPatternMatcher(ModelElementsSelectionDialogPatternMatcher patternMatcher) {
			this.patternMatcher = patternMatcher;
		}

		/**
		 * Check / Uncheck elements recursively.
		 *
		 * @param elements
		 *            Elements
		 * @param state
		 *            State check/uncheck
		 */
		private void setRecursiveState(Collection<Object> elements, boolean state) {
			for (final Object element : elements) {
				setRecursiveState(element, state);
			}
		}

		private void setRecursiveState(final Object element, final boolean state) {
			getTreeViewer().setChecked(element, state);
			if (!isGrayed.apply(element)) {
				if (state) {
					checkedElements.add(element);
				} else {
					checkedElements.remove(element);
				}
			}
			for (final Object child : contentProvider.getChildren(element)) {
				setRecursiveState(child, state);
			}
		}

		/**
		 * Uncheck all elements in tree viewer.
		 */
		@SuppressWarnings("unchecked")
		private void uncheckAll() {
			@SuppressWarnings("rawtypes")
			final Collection roots = (Collection)getTreeViewer().getInput();
			setRecursiveState(roots, false);
		}

		/**
		 * Updates the treeViewer after a change in the filteringMode or the typed regular expression.
		 *
		 * @param filteringMode
		 *            the new filtering mode
		 */
		@SuppressWarnings("deprecation")
		public void updateFilteringMode(FilteringMode filteringMode) {
			mode = filteringMode;
			refresh();
			// We expand the tree so that all elements matching the regular
			// expression (i.e. all visible leafs) are correctly shown
			getTreeViewer().expandToLevel(2);
			getTreeViewer().setAllChecked(false);
			for (final Object element : checkedElements) {
				getTreeViewer().setChecked(element, true);
			}
		}
	}

	/**
	 * Represents the various kinds of filtering supported by the tree viewer.
	 *
	 * @author pcdavid
	 */
	protected enum FilteringMode {
		/**
		 * Filtering mode in which all elements are considered.
		 */
		SHOW_ALL("all elements"), //$NON-NLS-1$
		/**
		 * Filtering mode in which all elements are considered.
		 */
		SHOW_ONLY_DIRECT_CHILDREN("only direct children"), //$NON-NLS-1$
		/**
		 * Filtering mode in which only elements which are not already displayed on diagram are considered.
		 */
		SHOW_ONLY_UNDISPLAYED_ELEMENTS("only undisplayed elements"); //$NON-NLS-1$

		private final String name;

		private FilteringMode(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private class ModeFilter extends ViewerFilter {

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			boolean show = true;
			// Step 1: only showing check/unchecked element if required
			switch (mode) {
				case SHOW_ALL:
					show = dialog.isMatchingExpregOrHasMatchingExpregDescendantsAllMode(element);
					break;
				case SHOW_ONLY_DIRECT_CHILDREN:
					show = dialog.isMatchingExpregOrHasMatchingExpregDescendantsSubMode(element);
					break;
				case SHOW_ONLY_UNDISPLAYED_ELEMENTS:
					show = dialog.isMatchingExpregOrHasMatchingExpregDescendantsUnrepresentedMode(element);
					break;
				default:
					show = true;
					break;
			}

			return show;
		}
	}

	private class SelectionDialogLabelProvider extends SiriusCommonLabelProvider {

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
			if (isGrayed.apply(element)) {
				foreground = VisualBindingManager.getDefault().getColorFromName("light_gray"); //$NON-NLS-1$
			}
			return foreground;
		}
	}

	final Map<EObject, Boolean> isOrHasDescendantCache = UmlDesignerSessionManagerListener
			.getDescendantCache();

	private static final Function<Object, Void> DO_NOTHING = new Function<Object, Void>() {
		public Void apply(Object from) {
			return null;
		}
	};

	/**
	 * The internal dialog used by this dialog.
	 */
	protected CustomTreeSelectionDialog dialog;

	/**
	 * The eObject associated to this dialog.
	 */
	protected EObject eObject;

	/**
	 * The filtering mode currently associated to the tree viewer. Can be :
	 * <ul>
	 * <li>All elements</li>
	 * <li>Only direct children</li>
	 * <li>Only undisplayed elements</li>
	 * </ul>
	 */
	protected FilteringMode mode = FilteringMode.SHOW_ONLY_DIRECT_CHILDREN;

	private final String title;

	private final String message;

	// Grayed elements will not be selectable.
	private Predicate<Object> isGrayed = Predicates.alwaysFalse();

	private Function<Object, Void> selectedAction = DO_NOTHING;

	private ModelFilteredTreeContentProvider contentProvider;

	private DDiagram diagram;

	/**
	 * Constructor.
	 *
	 * @param title
	 *            the title of the dialog window.
	 * @param message
	 *            the message for the dialog.
	 */
	public ModelElementsSelectionDialog(String title, String message) {
		this.title = title;
		this.message = message;
	}

	/**
	 * Updates the status of the elements according to the user request.
	 *
	 * @param selectedBefore
	 *            all (and only) the elements in the diagram which were actually pinned before the action.
	 * @param selectedAfter
	 *            all (and only) the elements in the diagram which should be pinned as requested by the user.
	 */
	protected void applyRequestedChanges(Set<Object> selectedBefore, Set<Object> selectedAfter) {
		for (final Object dde : Sets.difference(selectedAfter, selectedBefore)) {
			selectedAction.apply(dde);
		}
	}

	/**
	 * Asks the user to edit the set of elements which should be selected/match the criterion being edited.
	 *
	 * @param parent
	 *            the parent shell to use if user interaction requires opening new windows.
	 * @param initialSelection
	 *            the set of elements to display as checked on dialog opening.
	 * @return the new set of all the elements in the diagram which were selected by the user, or
	 *         <code>Options.newNone()</code> if the user canceled the operation.
	 */
	protected Option<Set<Object>> askUserForNewSelection(Shell parent, Set<Object> initialSelection) {
		setupDialog(parent, initialSelection);
		final int result = dialog.open();
		if (result == Window.OK) {
			final Set<Object> selectedAfter = getElementsSelectedAfter();
			return Options.newSome(selectedAfter);
		}
		return Options.newNone();
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

	/**
	 * @param parent
	 * @return
	 */
	private Set<Object> getAllChildren(Object parent) {
		final Set<Object> result = new HashSet<Object>();
		final Object[] children = contentProvider.getChildren(parent);
		for (final Object element : children) {
			result.add(element);
			result.addAll(getAllChildren(element));
		}
		return result;
	}

	/**
	 * Return all selected elements of the diagram that are display in the tree.
	 *
	 * @return All selected elements of the diagram that are display in the tree.
	 */
	protected Set<Object> getAllSelectedElements() {
		final Set<Object> treeElements = getAllChildren(eObject);
		return Sets.newHashSet(Iterators.filter(treeElements.iterator(), Predicates.not(isGrayed)));
	}

	/**
	 * Get displayable elements according to diagram kind.
	 *
	 * @param selectedContainer
	 *            Selected container
	 * @param diagram
	 *            Diagram
	 * @return Predicate to select only displayable element according to diagram kind
	 */
	private Predicate<Object> getDisplayablePredicate() {
		return new Predicate<Object>() {
			public boolean apply(Object input) {
				if (input instanceof Element) {
					return isOrHasDescendant((Element)input, isValidForDiagram());
				}
				return false;
			}
		};
	}

	/**
	 * Returns the elements selected when the dialog is about to close.
	 *
	 * @return the elements selected when the dialog is about to close
	 */
	protected Set<Object> getElementsSelectedAfter() {
		final Set<Object> selectedElements = Sets.newHashSet();
		for (final Object obj : dialog.checkedElements) {
			selectedElements.add(obj);
		}
		return selectedElements;
	}

	private Predicate<Object> getValidsForClassDiagram() {
		final Predicate<Object> validForClassDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				return input instanceof Package || input instanceof Interface || input instanceof DataType
						|| "Class".equals(((EObject)input).eClass().getName()) //$NON-NLS-1$
						|| "Component".equals(((EObject)input).eClass().getName()); //$NON-NLS-1$
			}
		};
		return validForClassDiagram;
	}

	private Predicate<Object> getValidsForComponentDiagram() {
		final Predicate<Object> validForComponentDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				return input instanceof Package || input instanceof Interface
						|| "Class".equals(((EObject)input).eClass().getName()) //$NON-NLS-1$
						|| "Component".equals(((EObject)input).eClass().getName()); //$NON-NLS-1$
			}
		};
		return validForComponentDiagram;
	}

	private Predicate<Object> getValidsForCompositeDiagram() {
		final Predicate<Object> validForCompositeDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				if (input instanceof StructuredClassifier) {
					return !(input instanceof Interaction || input instanceof StateMachine || input instanceof Activity);
				}
				return input instanceof Package || input instanceof Interface
						|| "Port".equals(((EObject)input).eClass().getName()) //$NON-NLS-1$
						|| "Property".equals(((EObject)input).eClass().getName()); //$NON-NLS-1$

			}
		};
		return validForCompositeDiagram;
	}

	private Predicate<Object> getValidsForCompositeStructureDiagram(final EObject container) {
		final Predicate<Object> validForCompositeDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				return "Property".equals(((EObject)input).eClass().getName()) && container.equals(((EObject)input).eContainer()); //$NON-NLS-1$

			}
		};
		return validForCompositeDiagram;
	}

	private Predicate<Object> getValidsForDeploymentDiagram() {
		final Predicate<Object> validForDeploymentDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				return input instanceof Package && !(input instanceof Profile)
						|| input instanceof ExecutionEnvironment || input instanceof Node
						|| input instanceof Artifact || input instanceof Device || input instanceof Component;
			}
		};
		return validForDeploymentDiagram;
	}

	/**
	 * Get valid elements for a diagram.
	 *
	 * @param cur
	 *            Element
	 * @return List of valid elements for the current representation
	 */
	private Predicate<Object> getValidsForPackageDiagram() {
		final Predicate<Object> validForPackageDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				return input instanceof Package;
			}
		};
		return validForPackageDiagram;
	}

	private Predicate<Object> getValidsForUseCaseDiagram() {
		final Predicate<Object> validForUseCaseDiagram = new Predicate<Object>() {

			public boolean apply(Object input) {
				return input instanceof Package || input instanceof Class || input instanceof Component
						|| input instanceof Artifact || input instanceof DataType
						|| input instanceof Interface || input instanceof Collaboration
						|| input instanceof UseCase;
			}
		};
		return validForUseCaseDiagram;
	}

	/**
	 * Init the content provider.
	 *
	 * @param includeNodeLabel
	 *            include node label (if there are on border) in the tree content
	 */
	protected void initContentProvider() {
		final AdapterFactory adapterFactory = getAdapterFactory();
		contentProvider = new ModelFilteredTreeContentProvider(adapterFactory, getDisplayablePredicate());
	}

	/**
	 * Indicates if the given element or at least one of its children checks the given predicate.
	 *
	 * @param input
	 *            the element to check
	 * @param predicate
	 *            the predicate to use
	 * @return true if the given element or at least one of its children checks the given predicate, false
	 *         otherwise
	 */
	@SuppressWarnings("boxing")
	private boolean isOrHasDescendant(Element element, final Predicate<Object> predicate) {
		if (isOrHasDescendantCache.containsKey(element)) {
			return isOrHasDescendantCache.get(element);
		}

		if (predicate.apply(element)) {
			isOrHasDescendantCache.put(element, true);
			return true;
		}

		final List<Element> elements = element.getOwnedElements();
		for (final Element ownedElement : elements) {
			if (predicate.apply(ownedElement)) {
				isOrHasDescendantCache.put(element, true);
				return true;
			}
			isOrHasDescendantCache.put(element, false);
		}

		return false;
	}

	/**
	 * Get valid elements for a diagram.
	 *
	 * @param element
	 *            Element
	 * @param containerView
	 *            Container view
	 * @return List of valid elements for the current representation
	 */
	private Predicate<Object> isValidForDiagram() {
		Predicate<Object> results = Predicates.alwaysTrue();
		if (diagram instanceof DSemanticDiagram) {
			final DiagramDescription description = ((DSemanticDiagram)diagram).getDescription();

			if ("Class Diagram".equals(description.getName()) //$NON-NLS-1$
					|| "Profile Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForClassDiagram();
			} else if ("Component Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForComponentDiagram();
			} else if ("Composite Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForCompositeDiagram();
			} else if ("Composite Structure Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForCompositeStructureDiagram(((DSemanticDiagram)diagram).getTarget());
			} else if ("Deployment Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForDeploymentDiagram();
			} else if ("Package Hierarchy".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForPackageDiagram();
			} else if ("Use Case Diagram".equals(description.getName())) { //$NON-NLS-1$
				results = getValidsForUseCaseDiagram();
			}
		}

		return results;
	}

	/**
	 * Asks the end-user for a list of elements to select/de-select, and applies the corresponding changes.
	 *
	 * @param parent
	 *            the shell to use to interact with the user, if required.
	 * @param selectedContainer
	 *            Selected semantic container
	 * @param ddiagram
	 *            the diagram whose elements to edit.
	 * @param includeNodeLabel
	 *            include node label (if there are on border) in the tree content
	 * @return <code>true</code> if the operation was correctly executed, <code>false</code> if it was
	 *         canceled by the user.
	 */
	public List<Object> open(Shell parent, EObject selectedContainer, DDiagram ddiagram,
			boolean includeNodeLabel) {
		final List<Object> result = Lists.newArrayList();
		eObject = selectedContainer;
		diagram = ddiagram;

		initContentProvider();

		final Set<Object> allSelectedElements = Collections.unmodifiableSet(getAllSelectedElements());
		final Option<Set<Object>> response = askUserForNewSelection(parent, allSelectedElements);
		if (response.some()) {
			final Set<Object> selectedAfter = response.get();
			result.addAll(selectedAfter);
		}
		eObject = null;
		dialog = null;
		contentProvider = null;
		return result;
	}

	/**
	 * Sets the predicate to use to detect which elements of the diagram are selected, in the sense of the
	 * criterion to be edited.
	 *
	 * @param isGrayedPredicate
	 *            the predicate to used to detect selected elements of the diagram.
	 */
	@SuppressWarnings("unchecked")
	public void setGrayedPredicate(Predicate<EObject> isGrayedPredicate) {
		isGrayed = Predicates.and((Predicate<? super Object>)(isGrayedPredicate != null ? isGrayedPredicate
				: Predicates.alwaysFalse()), isValidForDiagram());
	}

	/**
	 * Sets the operation to be applied on elements which are newly selected by the user.
	 *
	 * @param selectedAction
	 *            the operation to apply to newly selected elements.
	 */
	public void setSelectedAction(Function<Object, Void> selectedAction) {
		this.selectedAction = selectedAction;
	}

	/**
	 * Create an configure a selection dialog which allows the user to select a sub-set of the elements in the
	 * diagram.
	 *
	 * @param parent
	 *            the parent shell.
	 * @param initialSelection
	 *            the set of elements to display as checked on dialog opening.
	 */
	protected void setupDialog(Shell parent, Set<Object> initialSelection) {
		dialog = new CustomTreeSelectionDialog(parent, new SelectionDialogLabelProvider(), contentProvider);
		dialog.setTitle(title);

		String msg = message;
		if (!Predicates.alwaysFalse().equals(isGrayed)) {
			final StringBuilder sb = new StringBuilder(message);
			sb.append("\n"); //$NON-NLS-1$
			sb.append("The wizard will have no effect on grayed elements."); //$NON-NLS-1$
			msg = sb.toString();
		}
		dialog.setMessage(msg);
		final Collection<Element> roots = ElementServices.INSTANCE.getAllRootsInSession(eObject);

		dialog.setInput(roots);
		dialog.addFilter(new ModeFilter());
		dialog.setInitialElementSelections(Lists.newArrayList(initialSelection));
	}
}
