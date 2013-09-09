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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.obeonetwork.dsl.uml2.profile.design.Activator;

/**
 * A selection dialog message which provide a list of metaclass.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class ImportMetaclassDialog extends FilteredItemsSelectionDialog {
	/**
	 * the path to the UML icon in the eclipse platform.
	 */
	private static String umlIconPath = "";
	static {
		try {
			umlIconPath = FileLocator.toFileURL(
					Platform.getBundle("org.eclipse.uml2.uml.edit")
							.getResource("icons/full/obj16")).getPath();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * profile target of the metaclass import.
	 */
	private final Profile profile;

	/**
	 * a filter to show just the concrete metaclass.
	 */
	private boolean concrete;

	/**
	 * a filter to show just a subclass of a specified class.
	 */
	private boolean subClass;

	/**
	 * a button to to change the value of concrete.
	 */
	private Button concreteButton;

	/**
	 * a button to to change the value of subClass.
	 */
	private Button subClassButton;

	/**
	 * the text of the class to use in the filter subClass.
	 */
	private Text superClassText;

	/**
	 * a combo to purpose all possible metaclass in UML.
	 */
	private Combo combo;

	/**
	 * the name of the class to use in the filter subClass.
	 */
	private String superClassName = "";

	/**
	 * resource set .
	 */
	private final ResourceSet resourceSet = new ResourceSetImpl();

	/**
	 * resource.
	 */
	private final Resource resource = resourceSet.getResource(
			URI.createURI(UMLResource.UML_METAMODEL_URI), true);

	/**
	 * the UML metamodel model.
	 */
	private final Model umlMetamodel = (Model) resource.getContents().get(0);

	/**
	 * a list of all class of the metaclass UML.
	 */
	private final ArrayList<Class> umlMetaclasses = getUMLMetaClasses(umlMetamodel);

	private final String DIALOG_SETTINGS = "FilteredMetaclassesSelectionDialog";

	private final Action showOnlyConcreteMetaclassAction = new ShowOnlyConcreteMetaclassAction();

	private final ILabelProviderForMetaclass labelProviderForMetaclass = new ILabelProviderForMetaclass();

	/**
	 * create an instance of this class.
	 * 
	 * @param shell
	 *            the shell
	 * @param profile
	 *            in which the the metaclass will be imported.
	 */
	public ImportMetaclassDialog(final Shell shell, final Profile profile) {
		this(shell, profile, false);
	}

	/**
	 * Creates a new instance of the class.
	 * 
	 * @param shell
	 *            the shell
	 * @param profile
	 *            in which the the metaclass will be imported.
	 * @param multi
	 *            indicates whether dialog allows to select more than one
	 *            position in its list of items
	 */
	public ImportMetaclassDialog(final Shell shell, final Profile profile,
			final boolean multi) {
		super(shell, multi);
		this.profile = profile;
		concrete = false; // default value
		subClass = false; // default value
		setTitle("Import UML Metaclasses");
		setInitialPattern("?");
		setListLabelProvider(labelProviderForMetaclass);
		// setSelectionHistory(new ResourceSelectionHistory());
	}

	@Override
	protected Control createExtendedContentArea(final Composite parent) {
		final GridData gd = new GridData(GridData.FILL_HORIZONTAL);

		concreteButton = new Button(parent, SWT.CHECK);
		concreteButton.setText("Only concrete Metaclass"); //$NON-NLS-1$
		concreteButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
			}

			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (concrete != ((Button) e.widget).getSelection()) {
					concrete = ((Button) e.widget).getSelection();
					applyFilter();
				}
			}
		});

		subClassButton = new Button(parent, SWT.CHECK);
		subClassButton.setText("Only sub classes of:"); //$NON-NLS-1$
		subClassButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
			}

			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (subClass != ((Button) e.widget).getSelection()) {
					subClass = ((Button) e.widget).getSelection();
					superClassText.setEnabled(subClass);
					combo.setEnabled(subClass);
					applyFilter();
				}
			}
		});
		superClassText = new Text(parent, SWT.SEARCH);
		superClassText.setEnabled(subClass);
		superClassText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(final ModifyEvent e) {
				if (e != null) {
					final Object src = e.getSource();
					if (src instanceof Text) {
						final Text txt = (Text) src;
						final String myText = txt.getText();
						superClassName = myText;
					} else {
						superClassName = "";
					}
				}
				applyFilter();
			}
		});
		superClassText.setLayoutData(gd);
		combo = new Combo(parent, SWT.READ_ONLY);
		combo.setEnabled(subClass);
		combo.setItems(getMetaclassesNames());
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (e != null) {
					final Object src = e.getSource();
					if (src instanceof Combo) {
						final Combo comboEvent = (Combo) src;
						superClassName = comboEvent.getText();
						superClassText.setText(superClassName);
					}
				}
				applyFilter();
			}
		});
		combo.setLayoutData(gd);

		return superClassText;
	}

	/**
	 * Return the UML metamodel model.
	 * 
	 * @return the UML metamodel model.
	 */
	public Model getUmlMetamodel() {
		return umlMetamodel;
	}

	/**
	 * Return a classes list of the class in the UML metamodel.
	 * 
	 * @return a classes list of the class in the UML metamodel.
	 */
	public ArrayList<Class> getUmlMetaclasses() {
		return umlMetaclasses;
	}

	private String[] getMetaclassesNames() {
		final ArrayList<String> metaclassesNames = new ArrayList<String>();
		for (Class metaclass : umlMetaclasses) {
			metaclassesNames.add(metaclass.getName());
		}
		final String[] metaclassesNamesArray = new String[metaclassesNames
				.size()];
		metaclassesNames.toArray(metaclassesNamesArray);
		return metaclassesNamesArray;
	}

	/**
	 * Action to show only the concrete metaclass.
	 * 
	 * @author Mohamed-Lamine BOUKHANOUFA <a
	 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
	 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
	 */
	private class ShowOnlyConcreteMetaclassAction extends Action {
		/**
		 * Creates a new instance of the action.
		 */
		public ShowOnlyConcreteMetaclassAction() {
			super("Only concerete Metaclass", //$NON-NLS-1$
					IAction.AS_CHECK_BOX);
		}

		@Override
		public void run() {
			if (concrete != isChecked()) {
				concrete = isChecked();
				applyFilter();
			}
		}
	}

	@Override
	protected void applyFilter() {
		super.applyFilter();
		concreteButton.setSelection(concrete);
		showOnlyConcreteMetaclassAction.setChecked(concrete);
	}

	@Override
	protected void fillViewMenu(final IMenuManager menuManager) {
		super.fillViewMenu(menuManager);
		menuManager.add(showOnlyConcreteMetaclassAction);
	}

	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = Activator.getDefault().getDialogSettings()
				.getSection(DIALOG_SETTINGS);
		if (settings == null) {
			settings = Activator.getDefault().getDialogSettings()
					.addNewSection(DIALOG_SETTINGS);
		}
		return settings;
	}

	@Override
	protected IStatus validateItem(final Object item) {
		return Status.OK_STATUS;
	}

	/**
	 * Filter to show juste concrete and/or sub metaclass.
	 * 
	 * @author Mohamed-Lamine BOUKHANOUFA <a
	 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
	 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
	 */
	private class MetaclassFilter extends ItemsFilter {
		private final boolean concreteMetaclassFilter = ImportMetaclassDialog.this.concrete;

		private final boolean subClassMetaclassFilter = ImportMetaclassDialog.this.subClass;

		private final String superClassTextMetaclassFilter = ImportMetaclassDialog.this.superClassName;

		@Override
		public boolean matchItem(final Object item) {
			if (item instanceof Classifier) {
				final Classifier namedItem = (Classifier) item;

				if (concrete && subClass) {
					return (!namedItem.isAbstract())
							&& (isSubClassOfSuperClassName((Class) item))
							&& (matches(namedItem.getName()));
				} else if (subClass) {
					return isSubClassOfSuperClassName((Class) item)
							&& matches(namedItem.getName());
				} else if (concrete) {
					return !namedItem.isAbstract()
							&& matches(namedItem.getName());
				}
				return matches(namedItem.getName());
			}
			return matches(item.toString());
		}

		@Override
		public boolean equalsFilter(final ItemsFilter filter) {
			final MetaclassFilter resourceFilter = (MetaclassFilter) filter;
			if ((concreteMetaclassFilter != resourceFilter.concreteMetaclassFilter)
					|| (subClassMetaclassFilter != resourceFilter.subClassMetaclassFilter)
					|| (!superClassTextMetaclassFilter
							.equals(resourceFilter.superClassTextMetaclassFilter)))
				return false;
			return super.equalsFilter(filter);
		}

		@Override
		public boolean isSubFilter(final ItemsFilter filter) {
			final MetaclassFilter resourceFilter = (MetaclassFilter) filter;
			if ((concreteMetaclassFilter != resourceFilter.concreteMetaclassFilter)
					|| (subClassMetaclassFilter != resourceFilter.subClassMetaclassFilter)
					|| (!superClassTextMetaclassFilter
							.equals(resourceFilter.superClassTextMetaclassFilter)))
				return false;
			return super.isSubFilter(filter);
		}

		@Override
		public boolean isConsistentItem(final Object item) {
			return true;
		}
	}

	@Override
	protected ItemsFilter createFilter() {
		return new MetaclassFilter();
	}

	protected boolean isSubClassOfSuperClassName(final Class item) {
		if (item.getName().equalsIgnoreCase(superClassName))
			return true;
		else if (isMetaclass(superClassName)) {
			final Class superClass = (Class) umlMetamodel.getMember(
					superClassName, true, null);
			for (Class superClassOfItem : item.getSuperClasses()) {
				if (superClassOfItem.equals(superClass))
					return true;
				else if (!superClassOfItem.getName()
						.equalsIgnoreCase("Element")
						&& isSubClassOfSuperClassName(superClassOfItem)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Test if superClassName is an UML metaclass name.
	 * 
	 * @param nameToTest
	 *            if is it a metaclass name.
	 * @return true if superClassName is an UML metaclass name.
	 */
	public boolean isMetaclass(final String nameToTest) {
		if ((umlMetamodel.getMember(nameToTest, true, null) != null)
				&& (umlMetamodel.getMember(nameToTest, true, null) instanceof Class))
			return true;
		return false;
	}

	@Override
	protected Comparator<Object> getItemsComparator() {
		return new Comparator<Object>() {
			@Override
			public int compare(final Object arg0, final Object arg1) {
				return ((NamedElement) arg0).getName().compareTo(
						((NamedElement) arg1).getName());
			}
		};
	}

	@Override
	protected void fillContentProvider(
			final AbstractContentProvider contentProvider,
			final ItemsFilter itemsFilter,
			final IProgressMonitor progressMonitor) throws CoreException {
		progressMonitor.beginTask("Searching", umlMetaclasses.size()); //$NON-NLS-1$
		for (Iterator<Class> iter = umlMetaclasses.iterator(); iter.hasNext();) {
			contentProvider.add(iter.next(), itemsFilter);
			progressMonitor.worked(1);
		}
		progressMonitor.done();
	}

	@Override
	public String getElementName(final Object item) {
		return ((NamedElement) item).getName();
	}

	/**
	 * Return a classes list of the class in the metamodel passed in parameter.
	 * 
	 * @return a classes list of the class in the metaModel.
	 */
	public static ArrayList<Class> getUMLMetaClasses(final Model metaModel) {
		final ArrayList<Class> metaClasses = new ArrayList();

		for (Element element : metaModel.getOwnedElements()) {
			if (element instanceof Class)
				metaClasses.add((Class) element);
		}
		return metaClasses;
	}

	/**
	 * Class marking a label provider that provides styled text labels and
	 * images. It provides the text and/or image for the label of a given
	 * element.
	 */
	public class ILabelProviderForMetaclass implements ILabelProvider, IStyledLabelProvider {

		@Override
		public void addListener(final ILabelProviderListener listener) {

		}

		@Override
		public void dispose() {

		}

		@Override
		public boolean isLabelProperty(final Object element,
				final String property) {
			return false;
		}

		@Override
		public void removeListener(final ILabelProviderListener listener) {

		}

		@Override
		public Image getImage(final Object element) {
			if (element != null) {
				if (element instanceof Classifier) {
					final Classifier classifier = (Classifier) element;
					if (!classifier.isAbstract()) {
						final Image image = new Image(Display.getCurrent(),
								umlIconPath + ((Classifier) element).getName()
										+ ".gif");
						return image;
					} else {
						return null;
					}
				} else
					return null;
			}
			return null;
		}

		@Override
		public String getText(final Object element) {
			if (element != null) {
				if (element instanceof NamedElement) {
					final NamedElement namedElement = (NamedElement) element;
					if (profile.getImportedMember(namedElement.getName()) != null) {
						return namedElement.getName() + ", already imported.";
					} else {
						return namedElement.getName();
					}
				} else
					return element.toString();
			} else
				return "";
		}

		@Override
		public StyledString getStyledText(final Object element) {
			final StyledString styledString = new StyledString();
			if (element instanceof Class) {
				final Class metaclass = (Class) element;
				if (metaclass.isAbstract()) {
					styledString.append(metaclass.getName(),
							StyledString.DECORATIONS_STYLER);
				} else if (profile.getImportedMember(metaclass.getName()) != null) {
					styledString.append(metaclass.getName(),
							StyledString.QUALIFIER_STYLER);
				} else {
					styledString.append(metaclass.getName());
				}
			}
			return styledString;
		}
	}
}
