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
package org.obeonetwork.dsl.uml2.design.internal.dialogs;

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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.internal.services.LogServices;

/**
 * A selection dialog message which provide a list of metaclass.
 *
 * @author Mohamed-Lamine BOUKHANOUFA <a href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class ImportMetaclassDialog extends FilteredItemsSelectionDialog {
	/**
	 * Class marking a label provider that provides styled text labels and images. It provides the text and/or
	 * image for the label of a given element.
	 */
	public class ILabelProviderForMetaclass implements ILabelProvider, IStyledLabelProvider {
		public void addListener(final ILabelProviderListener listener) {
			// Nothing
		}

		public void dispose() {

			// Nothing
		}

		public Image getImage(final Object element) {
			if (element != null) {
				if (element instanceof Classifier) {
					final Classifier classifier = (Classifier)element;
					if (!classifier.isAbstract()) {
						final Image image = new Image(Display.getCurrent(), umlIconPath
								+ ((Classifier)element).getName() + ".gif"); //$NON-NLS-1$
						return image;
					}
					return null;
				}
				return null;
			}
			return null;
		}

		public StyledString getStyledText(final Object element) {
			final StyledString styledString = new StyledString();
			if (element instanceof Class) {
				final Class metaclass = (Class)element;
				if (metaclass.isAbstract()) {
					styledString.append(metaclass.getName(), StyledString.DECORATIONS_STYLER);
				} else if (profile.getImportedMember(metaclass.getName()) != null) {
					styledString.append(metaclass.getName(), StyledString.QUALIFIER_STYLER);
				} else {
					styledString.append(metaclass.getName());
				}
			}
			return styledString;
		}

		public String getText(final Object element) {
			if (element != null) {
				if (element instanceof NamedElement) {
					final NamedElement namedElement = (NamedElement)element;
					if (profile.getImportedMember(namedElement.getName()) != null) {
						return namedElement.getName() + ", already imported."; //$NON-NLS-1$
					}
					return namedElement.getName();
				}
				return element.toString();
			}
			return ""; //$NON-NLS-1$
		}

		public boolean isLabelProperty(final Object element, final String property) {
			return false;
		}

		public void removeListener(final ILabelProviderListener listener) {
			// Nothing
		}
	}

	/**
	 * Filter to show juste concrete and/or sub metaclass.
	 *
	 * @author Mohamed-Lamine BOUKHANOUFA <a href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
	 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
	 */
	private class MetaclassFilter extends ItemsFilter {
		private final boolean concreteMetaclassFilter = concrete;

		private final boolean subClassMetaclassFilter = subClass;

		private final String superClassTextMetaclassFilter = superClassName;

		@Override
		public boolean equalsFilter(final ItemsFilter filter) {
			final MetaclassFilter resourceFilter = (MetaclassFilter)filter;
			if (concreteMetaclassFilter != resourceFilter.concreteMetaclassFilter
					|| subClassMetaclassFilter != resourceFilter.subClassMetaclassFilter
					|| !superClassTextMetaclassFilter.equals(resourceFilter.superClassTextMetaclassFilter)) {
				return false;
			}
			return super.equalsFilter(filter);
		}

		@Override
		public boolean isConsistentItem(final Object item) {
			return true;
		}

		@Override
		public boolean isSubFilter(final ItemsFilter filter) {
			final MetaclassFilter resourceFilter = (MetaclassFilter)filter;
			if (concreteMetaclassFilter != resourceFilter.concreteMetaclassFilter
					|| subClassMetaclassFilter != resourceFilter.subClassMetaclassFilter
					|| !superClassTextMetaclassFilter.equals(resourceFilter.superClassTextMetaclassFilter)) {
				return false;
			}
			return super.isSubFilter(filter);
		}

		@Override
		public boolean matchItem(final Object item) {
			if (item instanceof Classifier) {
				final Classifier namedItem = (Classifier)item;

				if (concrete && subClass) {
					return !namedItem.isAbstract() && isSubClassOfSuperClassName((Class)item)
							&& matches(namedItem.getName());
				} else if (subClass) {
					return isSubClassOfSuperClassName((Class)item) && matches(namedItem.getName());
				} else if (concrete) {
					return !namedItem.isAbstract() && matches(namedItem.getName());
				}
				return matches(namedItem.getName());
			}
			return matches(item.toString());
		}
	}

	/**
	 * Action to show only the concrete metaclass.
	 *
	 * @author Mohamed-Lamine BOUKHANOUFA <a href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
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

	/**
	 * Return a classes list of the class in the metamodel passed in parameter.
	 *
	 * @param metaModel
	 *            Metamodel
	 * @return a classes list of the class in the metaModel.
	 */
	public static ArrayList<Class> getUMLMetaClasses(final Model metaModel) {
		final ArrayList<Class> metaClasses = new ArrayList<Class>();

		for (final Element element : metaModel.getOwnedElements()) {
			if (element instanceof Class) {
				metaClasses.add((Class)element);
			}
		}
		return metaClasses;
	}

	/**
	 * the path to the UML icon in the eclipse platform.
	 */
	private static String umlIconPath = ""; //$NON-NLS-1$

	static {
		try {
			umlIconPath = FileLocator.toFileURL(
					Platform.getBundle("org.eclipse.uml2.uml.edit").getResource("icons/full/obj16")) //$NON-NLS-1$ //$NON-NLS-2$
					.getPath();
		} catch (final IOException e) {
			LogServices.INSTANCE.error("FileLocator.toFileURL in ImportMetaclassDialog() not handled", e); //$NON-NLS-1$
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
	 * a combo to purpose all possible metaclass in UML.
	 */
	private Combo combo;

	/**
	 * the name of the class to use in the filter subClass.
	 */
	private String superClassName = ""; //$NON-NLS-1$

	/**
	 * The header message composite of the Dialog.
	 */
	private Composite compositeHeaderMessage;

	/**
	 * The header message of the Dialog.
	 */
	private String headerMessageText = "Message."; //$NON-NLS-1$

	/**
	 * resource set .
	 */
	private final ResourceSet resourceSet = new ResourceSetImpl();

	/**
	 * resource.
	 */
	private final Resource resource = resourceSet.getResource(URI.createURI(UMLResource.UML_METAMODEL_URI),
			true);

	/**
	 * the UML metamodel model.
	 */
	private final Model umlMetamodel = (Model)resource.getContents().get(0);

	/**
	 * a list of all class of the metaclass UML.
	 */
	private final ArrayList<Class> umlMetaclasses = getUMLMetaClasses(umlMetamodel);

	private final String DIALOG_SETTINGS = "FilteredMetaclassesSelectionDialog"; //$NON-NLS-1$

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
	 *            indicates whether dialog allows to select more than one position in its list of items
	 */
	public ImportMetaclassDialog(final Shell shell, final Profile profile, final boolean multi) {
		super(shell, multi);
		this.profile = profile;
		concrete = false; // default value
		subClass = false; // default value
		setTitle("Import UML Metaclasses"); //$NON-NLS-1$
		setInitialPattern("?"); //$NON-NLS-1$
		setListLabelProvider(labelProviderForMetaclass);
		// setSelectionHistory(new ResourceSelectionHistory());
	}

	@Override
	protected void applyFilter() {
		super.applyFilter();
		concreteButton.setSelection(concrete);
		showOnlyConcreteMetaclassAction.setChecked(concrete);
	}

	@Override
	protected Control createDialogArea(final Composite parent) {

		compositeHeaderMessage = new Composite(parent, SWT.NONE);

		final GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);

		compositeHeaderMessage.setLayout(layout);
		final Label headerMessage = new Label(compositeHeaderMessage, SWT.NONE);

		headerMessage.setText(headerMessageText);
		final GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		headerMessage.setLayoutData(gridData);

		final Control dialogArea1 = super.createDialogArea(parent);
		dialogArea1.setEnabled(true);
		parent.getParent().getShell().setMinimumSize(600, 700);
		applyFilter();
		setDetailsLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				String text = new String();
				if (element instanceof Class) {
					final Class myClass = (Class)element;
					for (final Comment comment : myClass.getOwnedComments()) {
						if (text.isEmpty()) {
							text = text + comment.getBody();
						} else {
							text = text + " " + comment.getBody(); //$NON-NLS-1$
						}
					}
				}
				return text;
			}
		});
		return dialogArea1;
	}

	@SuppressWarnings("unused")
	@Override
	protected Control createExtendedContentArea(final Composite parent) {

		final Composite subClassButtonComposite = new Composite(parent, SWT.BORDER);
		final GridLayout layout = new GridLayout(2, false);
		layout.numColumns = 2;

		final GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;

		subClassButtonComposite.setLayoutData(gridData);

		subClassButtonComposite.setLayout(layout);

		final GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;

		concreteButton = new Button(subClassButtonComposite, SWT.CHECK);
		concreteButton.setText("Only concrete Metaclass"); //$NON-NLS-1$
		concreteButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(final SelectionEvent e) {
				// Nothing
			}

			public void widgetSelected(final SelectionEvent e) {
				if (concrete != ((Button)e.widget).getSelection()) {
					concrete = ((Button)e.widget).getSelection();
					applyFilter();
				}
			}
		});

		concreteButton.setLayoutData(gd);

		subClassButton = new Button(subClassButtonComposite, SWT.CHECK);
		subClassButton.setText("Only sub classes of:"); //$NON-NLS-1$
		subClassButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(final SelectionEvent e) {
				// Nothing
			}

			public void widgetSelected(final SelectionEvent e) {
				if (subClass != ((Button)e.widget).getSelection()) {
					subClass = ((Button)e.widget).getSelection();
					combo.setEnabled(subClass);
					applyFilter();
				}
			}
		});

		combo = new Combo(subClassButtonComposite, SWT.DROP_DOWN);
		combo.setEnabled(subClass);
		combo.setItems(getMetaclassesNames());
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (e != null) {
					final Object src = e.getSource();
					if (src instanceof Combo) {
						final Combo comboEvent = (Combo)src;
						superClassName = comboEvent.getText();
					}
				}
				applyFilter();
			}

		});
		combo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				if (e != null) {
					final Object src = e.getSource();
					if (src instanceof Combo) {
						final Combo myCombo = (Combo)src;
						final String myText = myCombo.getText();
						for (int i = 0; i < myCombo.getItemCount(); i++) {
							final String item = myCombo.getItem(i);
							if (myText != null && !myText.isEmpty() && item.equalsIgnoreCase(myText)) {
								superClassName = myText;
								break;
							} else if (myText == null || myText.isEmpty()) {
								superClassName = ""; //$NON-NLS-1$
							}
						}
					} else {
						superClassName = ""; //$NON-NLS-1$
					}
				}
				applyFilter();
			}
		});

		new AutoCompleteField(combo, new ComboContentAdapter(), combo.getItems());
		combo.setLayoutData(gridData);

		return parent;
	}

	@Override
	protected ItemsFilter createFilter() {
		return new MetaclassFilter();
	}

	@Override
	protected void fillContentProvider(final AbstractContentProvider contentProvider,
			final ItemsFilter itemsFilter, final IProgressMonitor progressMonitor) throws CoreException {
		progressMonitor.beginTask("Searching", umlMetaclasses.size()); //$NON-NLS-1$
		for (final Iterator<Class> iter = umlMetaclasses.iterator(); iter.hasNext();) {
			contentProvider.add(iter.next(), itemsFilter);
			progressMonitor.worked(1);
		}
		progressMonitor.done();
	}

	@Override
	protected void fillViewMenu(final IMenuManager menuManager) {
		super.fillViewMenu(menuManager);
		menuManager.add(showOnlyConcreteMetaclassAction);
	}

	/**
	 * Get composite header message.
	 *
	 * @return Composite
	 */
	public Composite getCompositeHeaderMessage() {
		return compositeHeaderMessage;
	}

	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = UMLDesignerPlugin.getDefault().getDialogSettings()
				.getSection(DIALOG_SETTINGS);
		if (settings == null) {
			settings = UMLDesignerPlugin.getDefault().getDialogSettings().addNewSection(DIALOG_SETTINGS);
		}
		return settings;
	}

	@Override
	public String getElementName(final Object item) {
		return ((NamedElement)item).getName();
	}

	/**
	 * @return the header message.
	 */
	public String getHeaderMessageText() {
		return headerMessageText;
	}

	@Override
	protected Comparator<Object> getItemsComparator() {
		return new Comparator<Object>() {
			public int compare(final Object arg0, final Object arg1) {
				return ((NamedElement)arg0).getName().compareTo(((NamedElement)arg1).getName());
			}
		};
	}

	private String[] getMetaclassesNames() {
		final ArrayList<String> metaclassesNames = new ArrayList<String>();
		for (final Class metaclass : umlMetaclasses) {
			metaclassesNames.add(metaclass.getName());
		}
		final String[] metaclassesNamesArray = new String[metaclassesNames.size()];
		metaclassesNames.toArray(metaclassesNamesArray);
		return metaclassesNamesArray;
	}

	/**
	 * Return a classes list of the class in the UML metamodel.
	 *
	 * @return a classes list of the class in the UML metamodel.
	 */
	public ArrayList<Class> getUmlMetaclasses() {
		return umlMetaclasses;
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
	 * Test if superClassName is an UML metaclass name.
	 *
	 * @param nameToTest
	 *            if is it a metaclass name.
	 * @return true if superClassName is an UML metaclass name.
	 */
	public boolean isMetaclass(final String nameToTest) {
		if (umlMetamodel.getMember(nameToTest, true, null) != null
				&& umlMetamodel.getMember(nameToTest, true, null) instanceof Class) {
			return true;
		}
		return false;
	}

	protected boolean isSubClassOfSuperClassName(final Class item) {
		if (item.getName().equalsIgnoreCase(superClassName)) {
			return true;
		} else if (isMetaclass(superClassName)) {
			final Class superClass = (Class)umlMetamodel.getMember(superClassName, true, null);
			for (final Class superClassOfItem : item.getSuperClasses()) {
				if (superClassOfItem.equals(superClass)) {
					return true;
				} else if (!superClassOfItem.getName().equalsIgnoreCase("Element") //$NON-NLS-1$
						&& isSubClassOfSuperClassName(superClassOfItem)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Set the header message.
	 *
	 * @param headerMessage
	 */
	public void setHeaderMessageText(String headerMessage) {
		headerMessageText = headerMessage;
	}

	@Override
	protected IStatus validateItem(final Object item) {
		return Status.OK_STATUS;
	}
}
