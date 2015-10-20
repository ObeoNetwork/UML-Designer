/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.editor.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.editor.UMLEditorPlugin;
import org.eclipse.uml2.uml.editor.presentation.UMLActionBarContributor;

/**
 * Action provider to contribute to the model explorer view.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class UmlCommonActionProvider extends CommonActionProvider {
	private Collection<IAction> createChildActions;

	private Map<String, Collection<IAction>> createChildSubmenuActions;

	private ICommonViewerWorkbenchSite viewSite;

	/**
	 * Overridden to have a reference to the model explorer view. {@inheritDoc}
	 */
	@Override
	public void init(final ICommonActionExtensionSite aSite) {
		super.init(aSite);
		viewSite = (ICommonViewerWorkbenchSite)aSite.getViewSite();
	}

	/**
	 * Overridden to add to the <code>menuManager</code> "New Child" sub menu to create model elements.
	 * {@inheritDoc}
	 */
	@Override
	public void fillContextMenu(IMenuManager menuManager) {
		// Prepare for CreateChild item addition or removal.
		//
		MenuManager createChildMenuManager = new MenuManager(
				UMLEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item")); //$NON-NLS-1$
		menuManager.insertBefore("additions", createChildMenuManager); //$NON-NLS-1$
		ISelection selection = getContext().getSelection();
		if (selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1) {
			Object object = ((IStructuredSelection)selection).getFirstElement();

			if (object instanceof EObject) {
				EObject eObject = (EObject)object;
				EditingDomain domain = TransactionUtil.getEditingDomain(eObject);
				if (domain != null && UMLPackage.eINSTANCE == eObject.eClass().eContainer()) {
					Collection<?> newChildDescriptors = domain.getNewChildDescriptors(object, null);

					if (newChildDescriptors != null) {
						// Code duplicated from
						// UMLActionBarContributor#selectionChanged
						if (createChildMenuManager != null) {
							depopulateManager(createChildMenuManager, createChildSubmenuActions);
							depopulateManager(createChildMenuManager, createChildActions);
						}

						// Generate actions for selection; populate and redraw
						// the menus.
						createChildActions = generateCreateChildActions(newChildDescriptors, selection);
						createChildSubmenuActions = extractSubmenuActions(createChildActions);
						if (createChildMenuManager != null) {
							populateManager(createChildMenuManager, createChildSubmenuActions, null);
							populateManager(createChildMenuManager, createChildActions, null);
							createChildMenuManager.update(true);
						}
					}
					if (isRenamableUmlModelElement(eObject)) {
						menuManager.add(new RenameUmlModelElementAction(domain, eObject));
					}
				}
			}
		}
	}

	private boolean isRenamableUmlModelElement(EObject selection) {
		boolean isRenamableUmlModelElement = selection instanceof NamedElement;
		return isRenamableUmlModelElement;
	}

	/**
	 * This removes from the specified <code>manager</code> all
	 * {@link org.eclipse.jface.action.ActionContributionItem}s based on the
	 * {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection. Code
	 * duplicated from UMLActionBarContributor
	 * 
	 * @see UMLActionBarContributor#depopulateManager
	 */
	private void depopulateManager(IContributionManager manager, Collection<? extends IAction> actions) {
		if (actions != null) {
			IContributionItem[] items = manager.getItems();
			for (int i = 0; i < items.length; i++) {
				// Look into SubContributionItems
				//
				IContributionItem contributionItem = items[i];
				while (contributionItem instanceof SubContributionItem) {
					contributionItem = ((SubContributionItem)contributionItem).getInnerItem();
				}

				// Delete the ActionContributionItems with matching action.
				//
				if (contributionItem instanceof ActionContributionItem) {
					IAction action = ((ActionContributionItem)contributionItem).getAction();
					if (actions.contains(action)) {
						manager.remove(contributionItem);
					}
				}
			}
		}
	}

	/**
	 * This extracts those actions in the <code>submenuActions</code> collection whose text is qualified and
	 * returns a map of these actions, keyed by submenu text. Code duplicated from UMLActionBarContributor
	 * 
	 * @see UMLActionBarContributor#extractSubmenuActions
	 */
	private Map<String, Collection<IAction>> extractSubmenuActions(Collection<IAction> createActions) {
		Map<String, Collection<IAction>> createSubmenuActions = new LinkedHashMap<String, Collection<IAction>>();
		if (createActions != null) {
			for (Iterator<IAction> actions = createActions.iterator(); actions.hasNext();) {
				IAction action = actions.next();
				StringTokenizer st = new StringTokenizer(action.getText(), "|"); //$NON-NLS-1$
				if (st.countTokens() == 2) {
					String text = st.nextToken().trim();
					Collection<IAction> submenuActions = createSubmenuActions.get(text);
					if (submenuActions == null) {
						createSubmenuActions.put(text, submenuActions = new ArrayList<IAction>());
					}
					action.setText(st.nextToken().trim());
					submenuActions.add(action);
					actions.remove();
				}
			}
		}
		return createSubmenuActions;
	}

	/**
	 * This populates the specified <code>manager</code> with {@link org.eclipse.jface.action.MenuManager}s
	 * containing {@link org.eclipse.jface.action.ActionContributionItem}s based on the
	 * {@link org.eclipse.jface.action.IAction}s contained in the <code>submenuActions</code> collection, by
	 * inserting them before the specified contribution item <code>contributionID</code>. If
	 * <code>contributionID</code> is <code>null</code>, they are simply added. Code duplicated from
	 * UMLActionBarContributor
	 * 
	 * @see UMLActionBarContributor#populateManager
	 */
	private void populateManager(IContributionManager manager,
			Map<String, Collection<IAction>> submenuActions, String contributionID) {
		if (submenuActions != null) {
			for (Map.Entry<String, Collection<IAction>> entry : submenuActions.entrySet()) {
				MenuManager submenuManager = new MenuManager(entry.getKey());
				if (contributionID != null) {
					manager.insertBefore(contributionID, submenuManager);
				} else {
					manager.add(submenuManager);
				}
				populateManager(submenuManager, entry.getValue(), null);
			}
		}
	}

	/**
	 * This removes from the specified <code>manager</code> all {@link org.eclipse.jface.action.MenuManager}s
	 * and their {@link org.eclipse.jface.action.ActionContributionItem}s based on the
	 * {@link org.eclipse.jface.action.IAction}s contained in the <code>submenuActions</code> map. Code
	 * duplicated from UMLActionBarContributor
	 * 
	 * @see UMLActionBarContributor#depopulateManager
	 */
	private void depopulateManager(IContributionManager manager,
			Map<String, Collection<IAction>> submenuActions) {
		if (submenuActions != null) {
			IContributionItem[] items = manager.getItems();
			for (int i = 0; i < items.length; i++) {
				IContributionItem contributionItem = items[i];
				if (contributionItem instanceof MenuManager) {
					MenuManager submenuManager = (MenuManager)contributionItem;
					if (submenuActions.containsKey(submenuManager.getMenuText())) {
						depopulateManager(submenuManager, submenuActions.get(contributionItem));
						manager.remove(contributionItem);
					}
				}
			}
		}
	}

	/**
	 * This populates the specified <code>manager</code> with
	 * {@link org.eclipse.jface.action.ActionContributionItem}s based on the
	 * {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection, by
	 * inserting them before the specified contribution item <code>contributionID</code>. If
	 * <code>contributionID</code> is <code>null</code>, they are simply added. Code duplicated from
	 * UMLActionBarContributor
	 * 
	 * @see UMLActionBarContributor#populateManager
	 */
	private void populateManager(IContributionManager manager, Collection<? extends IAction> actions,
			String contributionID) {
		if (actions != null) {
			for (IAction action : actions) {
				if (contributionID != null) {
					manager.insertBefore(contributionID, action);
				} else {
					manager.add(action);
				}
			}
		}
	}

	/**
	 * This generates a {@link org.eclipse.emf.edit.ui.action.CreateChildAction} for each object in
	 * <code>descriptors</code>, and returns the collection of these actions. Code duplicated from
	 * UMLActionBarContributor
	 * 
	 * @see UMLActionBarContributor#generateCreateChildActionsGen
	 */
	private Collection<IAction> generateCreateChildActionsGen(Collection<?> descriptors,
			ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			for (Object descriptor : descriptors) {
				actions.add(new CreateChildAction(viewSite.getPart(), selection, descriptor));
			}
		}
		return actions;
	}

	/**
	 * Code duplicated from UMLActionBarContributor
	 * 
	 * @see UMLActionBarContributor#generateCreateChildActionsGen
	 */
	private Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection) {
		List<IAction> createChildActions = (List<IAction>)generateCreateChildActionsGen(descriptors,
				selection);

		Collections.<IAction> sort(createChildActions, new Comparator<IAction>() {

			public int compare(IAction a1, IAction a2) {
				return CommonPlugin.INSTANCE.getComparator().compare(a1.getText(), a2.getText());
			}
		});

		return createChildActions;
	}
}
