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
package org.eclipse.uml2.uml.parts;

// Start of user code for imports
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;



// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public interface ClassifierTemplateParameterPropertiesEditionPart {

	/**
	 * @return the signature
	 * 
	 */
	public EObject getSignature();

	/**
	 * Init the signature
	 * @param settings the combo setting
	 */
	public void initSignature(EObjectFlatComboSettings settings);

	/**
	 * Defines a new signature
	 * @param newValue the new signature to set
	 * 
	 */
	public void setSignature(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setSignatureButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the signature edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToSignature(ViewerFilter filter);

	/**
	 * Adds the given filter to the signature edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToSignature(ViewerFilter filter);


	/**
	 * @return the parameteredElement
	 * 
	 */
	public EObject getParameteredElement();

	/**
	 * Init the parameteredElement
	 * @param settings the combo setting
	 */
	public void initParameteredElement(EObjectFlatComboSettings settings);

	/**
	 * Defines a new parameteredElement
	 * @param newValue the new parameteredElement to set
	 * 
	 */
	public void setParameteredElement(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setParameteredElementButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the parameteredElement edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToParameteredElement(ViewerFilter filter);

	/**
	 * Adds the given filter to the parameteredElement edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToParameteredElement(ViewerFilter filter);


	/**
	 * @return the default
	 * 
	 */
	public EObject getDefault_();

	/**
	 * Init the default
	 * @param settings the combo setting
	 */
	public void initDefault_(EObjectFlatComboSettings settings);

	/**
	 * Defines a new default
	 * @param newValue the new default to set
	 * 
	 */
	public void setDefault_(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setDefault_ButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the default edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToDefault_(ViewerFilter filter);

	/**
	 * Adds the given filter to the default edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToDefault_(ViewerFilter filter);


	/**
	 * @return the allowSubstitutable
	 * 
	 */
	public Boolean getAllowSubstitutable();

	/**
	 * Defines a new allowSubstitutable
	 * @param newValue the new allowSubstitutable to set
	 * 
	 */
	public void setAllowSubstitutable(Boolean newValue);




	/**
	 * Init the constrainingClassifier
	 * @param settings settings for the constrainingClassifier ReferencesTable 
	 */
	public void initConstrainingClassifier(ReferencesTableSettings settings);

	/**
	 * Update the constrainingClassifier
	 * @param newValue the constrainingClassifier to update
	 * 
	 */
	public void updateConstrainingClassifier();

	/**
	 * Adds the given filter to the constrainingClassifier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToConstrainingClassifier(ViewerFilter filter);

	/**
	 * Adds the given filter to the constrainingClassifier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToConstrainingClassifier(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the constrainingClassifier table
	 * 
	 */
	public boolean isContainedInConstrainingClassifierTable(EObject element);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * 
	 */
	public String getTitle();

	// Start of user code for additional methods
	
	// End of user code

}
