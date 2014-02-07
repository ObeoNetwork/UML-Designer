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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
		this.source = source;
		this.target = target;
		setTitle("Extend existing RelationShip");
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

	@Override
	protected Control createDialogArea(final Composite parent) {
		this.setHeaderMessageText("Give a name to the stereotype and select the relationship metaclass that will be extended by this stereotype.");
		Composite dialogArea = (Composite) super.createDialogArea(parent);

		Composite compositeHeaderMessage = this.getCompositeHeaderMessage();

		GridLayout layout = (GridLayout) compositeHeaderMessage.getLayout();
		layout.numColumns = 2;

		compositeHeaderMessage.setLayout(layout);

		final Label headerLabelAssociationName = new Label(
				compositeHeaderMessage, SWT.NONE);

		headerLabelAssociationName.setText("Stereotype name:");

		final Text associationNameMessage = new Text(compositeHeaderMessage,
				SWT.SEARCH);
		associationNameMessage.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				if (e != null) {
					final Object src = e.getSource();
					if (src instanceof Text) {
						final Text txt = (Text) src;
						final String myText = txt.getText();
						associationName = myText;
						final Button okButton = getOkButton();
						if (okButton != null) {
							okButton.setEnabled(associationName.length() != 0);
						}
					}
				}
			}
		});
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		associationNameMessage.setLayoutData(gridData);
		compositeHeaderMessage.setLayoutData(gridData);

		associationNameMessage.setText(basicAssociationName(source, target));
		parent.getParent().getShell().setMinimumSize(710, 700);
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
					.iterator(); iter.hasNext();) {
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
