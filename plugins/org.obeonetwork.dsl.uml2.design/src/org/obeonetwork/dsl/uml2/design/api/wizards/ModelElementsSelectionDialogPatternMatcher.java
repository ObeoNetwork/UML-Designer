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

import org.eclipse.gmf.runtime.common.core.util.StringMatcher;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;

import com.google.common.base.Predicate;

/**
 * Pattern matcher for model elements selection dialog. Derived from @see
 * DiagramElementsSelectionDialogPatternMatcher.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ModelElementsSelectionDialogPatternMatcher {

	private Predicate<Object> matchPredicate;

	private final StringMatcher stringMatcher;

	/**
	 * Creates a new PatternMatcher.
	 *
	 * @param expreg
	 *            the regular expression (for example '?a?' or 'abc' or '*c') ; <code>null</code> or empty
	 *            regular expression will be replaced by '*'
	 */
	public ModelElementsSelectionDialogPatternMatcher(String expreg) {
		String computedExpreg = expreg;
		if (expreg == null) {
			computedExpreg = ""; //$NON-NLS-1$
		}
		// If the regular expression ends with a space, we have to use the exact
		// value of the given expreg
		if (computedExpreg.endsWith(" ")) { //$NON-NLS-1$
			computedExpreg = computedExpreg.substring(0, computedExpreg.lastIndexOf(' '));
		} else {
			// Otherwise, we add a star to make 'XYZ' recognized by the 'X'
			// expreg (as in quick outline for example)
			computedExpreg = computedExpreg + "*"; //$NON-NLS-1$
		}
		stringMatcher = new StringMatcher(computedExpreg, true, false);

	}

	/**
	 * Creates a {@link Predicate} that can be applied on any Object. This predicates will return true if the
	 * tested element is a {@link DDiagramElement} and that its name is matching the regular expression used
	 * to construct this Matcher.
	 *
	 * @return a {@link Predicate} that can be applied on any Object to determine if whether it's matching the
	 *         regular expression used to construct this Matcher
	 */
	public Predicate<Object> getMatchPredicate() {
		if (matchPredicate == null) {
			matchPredicate = new Predicate<Object>() {

				public boolean apply(Object input) {
					final ICommonLabelProvider labelProvider = UMLDesignerPlugin.getDefault()
							.getLabelProvider();
					final String displayedLabel = labelProvider.getText(input);
					return stringMatcher.match(displayedLabel);
				}
			};
		}
		return matchPredicate;
	}
}
