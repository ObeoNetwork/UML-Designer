/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.profile.design.dialogs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.obeonetwork.dsl.uml2.profile.design.services.UMLServices;

/**
 * This class provides a dialog message for the creation of an extra association
 * between two stereotypes.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class ExtraAssociationSelectionDialog extends ImportMetaclassDialog {

	/**
	 * Boolean to test if the ExtraAssociation is selected.
	 */
	private boolean isExtraAssociation;

	/**
	 * The source of the association.
	 */
	private final Element source;

	/**
	 * The target of the association.
	 */
	private final Element target;

	/**
	 * The name of the association.
	 */
	private String associationName = "associationName";

	/**
	 * A list of the possible relationship between the metaclasses extended by
	 * source and target.
	 */
	private final ArrayList<EObject> possibleMetaClassRelationShip;

	/**
	 * a check button to active the extra association creation.
	 */
	private Button checkButtonExtraAsso;

	/**
	 * A constructor for this class.
	 * 
	 * @param shell
	 *            a shell
	 * @param profile
	 *            the profile target of the current work
	 * @param source
	 *            the source stereotype of the association.
	 * @param target
	 *            the target stereotype of the association.
	 */
	public ExtraAssociationSelectionDialog(final Shell shell,
			final Profile profile, final Element source, final Element target) {
		this(shell, profile, source, target, true);
	}

	/**
	 * Creates a new instance of the class.
	 * 
	 * @param shell
	 *            a shell
	 * @param profile
	 *            target of the current work
	 * @param source
	 *            stereotype of the association.
	 * @param target
	 *            stereotype of the association.
	 * @param multi
	 *            indicates whether dialog allows to select more than one
	 *            position in its list of items
	 */
	public ExtraAssociationSelectionDialog(final Shell shell,
			final Profile profile, final Element source, final Element target,
			final boolean multi) {
		super(shell, profile, multi);
		isExtraAssociation = false; // default value
		this.source = source;
		this.target = target;
		setTitle("Create an association or an extra association");
		refresh();
		possibleMetaClassRelationShip = (ArrayList<EObject>) getAllPossibleMetaclassRelationShip(
				(Class) source, (Class) target);
	}

	/**
	 * return the name of the association, associationName.
	 * 
	 * @return the name of the association.
	 */
	public final String getAssociationName() {
		return associationName;
	}

	/**
	 * return the state of the variable isExtraAssociation.
	 * 
	 * @return true if the extra association will be created, else return false.
	 */
	public final boolean isExtraAssociation() {
		return isExtraAssociation;
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		final GridData gd = new GridData(GridData.FILL_HORIZONTAL);

		final Composite composite = new Composite(parent, SWT.NONE);
		final GridLayout layout = new GridLayout();
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		composite.setLayout(layout);
		// composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		final Label headerLabelAssociationName = new Label(composite, SWT.NONE);
		headerLabelAssociationName.setText("Association name:");
		final Text associationNameMessage = new Text(composite, SWT.SEARCH);
		associationNameMessage.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				if (e != null) {
					final Object src = e.getSource();
					if (src instanceof Text) {
						final Text txt = (Text) src;
						final String myText = txt.getText();
						associationName = myText;
						final Button okButton = getOkButton();
						if (okButton != null) {
							okButton.setEnabled(!associationName.isEmpty());
						}
					}
				}
			}
		});

		associationNameMessage.setText(basicAssociationName(source, target));
		associationNameMessage.setLayoutData(gd);

		checkButtonExtraAsso = new Button(composite, SWT.CHECK);
		checkButtonExtraAsso.setText("Create an extra association"); //$NON-NLS-1$
		checkButtonExtraAsso.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
			}

			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (isExtraAssociation != ((Button) e.widget).getSelection()) {
					isExtraAssociation = ((Button) e.widget).getSelection();
					getDialogArea().setEnabled(isExtraAssociation);
					final Button okButton = getOkButton();
					okButton.setEnabled(!isExtraAssociation);
					applyFilter();
				}
			}
		});
		final Control dialogArea = super.createDialogArea(parent);
		checkButtonExtraAsso.setSelection(isExtraAssociation);
		dialogArea.setEnabled(isExtraAssociation);
		applyFilter();

		return dialogArea;
	}

	/**
	 * Compute the basic name of the association using the name of the source
	 * and target.
	 * 
	 * @param sourceStereotype
	 *            for the association
	 * @param targetStereotype
	 *            for the association
	 * @return name of the association using the names of sourceStereotype and
	 *         targetStereotype
	 */
	protected String basicAssociationName(final Element sourceStereotype,
			final Element targetStereotype) {
		if (sourceStereotype instanceof NamedElement
				&& targetStereotype instanceof NamedElement) {
			associationName = ((NamedElement) sourceStereotype).getName()
					+ "To" + ((NamedElement) targetStereotype).getName();
		}
		return associationName;
	}

	@Override
	protected Control createExtendedContentArea(final Composite parent) {
		return super.createExtendedContentArea(parent);
	}

	@Override
	protected void fillContentProvider(
			final AbstractContentProvider contentProvider,
			final ItemsFilter itemsFilter,
			final IProgressMonitor progressMonitor) throws CoreException {
		if (possibleMetaClassRelationShip != null) {
			progressMonitor.beginTask(
					"Searching", possibleMetaClassRelationShip.size()); //$NON-NLS-1$
			for (final Iterator<EObject> iter = possibleMetaClassRelationShip
					.iterator(); iter.hasNext(); ) {
				contentProvider.add(iter.next(), itemsFilter);
				progressMonitor.worked(1);
			}
		}
		progressMonitor.done();
	}

	/**
	 * Find all possible relationship between the metaclasses extended by the
	 * stereotypeSource and the stereotypeTarget (or MetaclassTarget).
	 * 
	 * @param stereotypeSource
	 *            of association
	 * @param stereotypeTarget
	 *            of association
	 * @return a list of all possible relationship between the metaclasses
	 *         extended by the stereotypeSource and the stereotypeTarget (or
	 *         MetaclassTarget)
	 */
	protected List<EObject> getAllPossibleMetaclassRelationShip(
			final Type stereotypeSource, final Type stereotypeTarget) {
		final List<EObject> eligibleRelationShips = new ArrayList<EObject>();
		final List<Class> relationShipSubClasses = getAllSubClasses((Class) super
				.getUmlMetamodel().getMember("Relationship"));
		relationShipSubClasses.add((Class) super.getUmlMetamodel().getMember(
				"Relationship"));

		final List<Class> mataclassesSource = getAllExtendedMetaClasses((Class) stereotypeSource);
		final List<Class> mataclassesTarget = getAllExtendedMetaClasses((Class) stereotypeTarget);

		for (Class relationShipSubClasse : relationShipSubClasses) {
			final ArrayList<Property> properties = UMLServices
					.getRelationShipSourceTargetPeroperties(relationShipSubClasse);
			final Type candidateTypeSource = properties.get(0).getType();
			final Type candidateTypeTarget = properties.get(1).getType();

			if (UMLServices
					.isIn((Class) candidateTypeSource, mataclassesSource)
					&& UMLServices.isIn((Class) candidateTypeTarget,
							mataclassesTarget)) {
				eligibleRelationShips.add(relationShipSubClasse);
			}
		}
		return eligibleRelationShips;
	}

	/**
	 * Test if a property named : "propName" is in the reference list
	 * "Subsetted Property" of one of the properties owned by one relationship
	 * of the list relationShips.
	 * 
	 * @param relationShips
	 *            list of relationShip to test
	 * @param propName
	 *            the property name
	 * @return true if property named "propName" found in the Subsetted
	 *         Property.
	 * @see org.obeonetwork.dsl.uml2.profile.design.services.UMLServices
	 *      #isInSubSet(Property, String)
	 */
	protected boolean testSuperClassAttributes(final List<Class> relationShips,
			final String propName) {
		for (Class relationShip : relationShips) {
			for (Property propertyToTest : relationShip.getAttributes()) {
				if (UMLServices.isInSubSet(propertyToTest, propName)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Find all metaclasses extended by the stereotype: stereotypeClass.
	 * 
	 * @param stereotypeClass
	 *            the stereotype to test
	 * @return all metaclasses extended by the stereotype: stereotypeClass.
	 */
	protected List<Class> getAllExtendedMetaClasses(final Class stereotypeClass) {
		final List<Class> classes = new ArrayList<Class>();
		if (stereotypeClass instanceof Stereotype) {
			final Stereotype stereotype = (Stereotype) stereotypeClass;
			for (Property property : stereotype.getOwnedAttributes()) {
				if ((property.getAssociation() != null && (property
						.getAssociation() instanceof Extension))
						&& property.getName().equals(
								"base_" + property.getType().getName())) {
					final Class metaClass = (Class) property.getType();
					classes.add(metaClass);
					for (Class superClass : UMLServices
							.getAllSuperClasses(metaClass)) {
						if (!UMLServices.isIn(superClass, classes)) {
							classes.add(superClass);
						}
					}
				}
			}
		} else {
			classes.add(stereotypeClass);
			return classes;
		}
		return classes;
	}

	/**
	 * Find all subclasses of the class: superClass.
	 * 
	 * @param superClass
	 *            the super class to test
	 * @return all sub classes of a class superClass
	 */
	protected List<Class> getAllSubClasses(final Class superClass) {
		final List<Class> subClasses = new ArrayList<Class>();
		for (Class metaClass : super.getUmlMetaclasses()) {
			if (UMLServices.isSubClass(metaClass, superClass)) {
				subClasses.add(metaClass);
			}
		}
		return subClasses;
	}

}
