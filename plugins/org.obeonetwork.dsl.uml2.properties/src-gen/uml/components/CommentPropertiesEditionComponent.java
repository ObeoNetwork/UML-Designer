/**
 * Generated with Acceleo
 */
package uml.components;


/**
 * 
 * 
 */
public class CommentPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for annotatedElement ReferencesTable
	 */
	private ReferencesTableSettings annotatedElementSettings;
	
	
	/**
	 * Default constructor
	 * 
	 */
	public CommentPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject , String editing_mode) {
		super(editingContext, , editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Comment.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			
			final org.eclipse.emf.ecore.impl.DynamicEObjectImpl@4b9def27 (eClass: org.eclipse.emf.ecore.impl.EClassImpl@6dc01039 (name: OclInvalid_Class) (instanceClassName: null) (abstract: false, interface: false))  = ()elt;
			final CommentPropertiesEditionPart basePart = (CommentPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.Comment.Properties.body))
				basePart.setBody(EEFConverterUtil.convertToString(, .getBody()));
			
			if (isAccessible(UmlViewsRepository.Comment.Properties.annotatedElement)) {
				annotatedElementSettings = new ReferencesTableSettings(, ());
				basePart.initAnnotatedElement(annotatedElementSettings);
			}
			// init filters
			
			if (isAccessible(UmlViewsRepository.Comment.Properties.annotatedElement)) {
				basePart.addFilterToAnnotatedElement(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!basePart.isContainedInAnnotatedElementTable((EObject)element));
						return element instanceof Resource;
					}
				
				});
				basePart.addFilterToAnnotatedElement(new EObjectFilter());
			}
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}





	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	public EStructuralFeature associatedFeature(Object editorKey) {
		if (editorKey == UmlViewsRepository.Comment.Properties.body) {
			return ();
		}
		if (editorKey == UmlViewsRepository.Comment.Properties.annotatedElement) {
			return ();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		  = ()semanticObject;
		if (UmlViewsRepository.Comment.Properties.body == event.getAffectedEditor()) {
			.setBody((java.lang.String)EEFConverterUtil.createFromString(, (String)event.getNewValue()));
		}
		if (UmlViewsRepository.Comment.Properties.annotatedElement == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof org.eclipse.emf.ecore.impl.DynamicEObjectImpl@4b9def27 (eClass: org.eclipse.emf.ecore.impl.EClassImpl@6dc01039 (name: OclInvalid_Class) (instanceClassName: null) (abstract: false, interface: false))) {
					annotatedElementSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				annotatedElementSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				annotatedElementSettings.move(event.getNewIndex(), () event.getNewValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		super.updatePart(msg);
		if (editingPart.isVisible()) {
			CommentPropertiesEditionPart basePart = (CommentPropertiesEditionPart)editingPart;
			if (().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Comment.Properties.body)) {
				if (msg.getNewValue() != null) {
					basePart.setBody(EcoreUtil.convertToString(, msg.getNewValue()));
				} else {
					basePart.setBody("");
				}
			}
			if (().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Comment.Properties.annotatedElement))
				basePart.updateAnnotatedElement();
			
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
	 */
	@Override
	protected NotificationFilter[] getNotificationFilters() {
		NotificationFilter filter = new EStructuralFeatureNotificationFilter(
			(),
			()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getHelpContent(java.lang.Object, int)
	 * 
	 */
	public String getHelpContent(Object key, int kind) {
		if (key == UmlViewsRepository.Comment.Properties.body)
			return ; //$NON-NLS-1$
		if (key == UmlViewsRepository.Comment.Properties.annotatedElement)
			return ; //$NON-NLS-1$
		return super.getHelpContent(key, kind);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			try {
				if (UmlViewsRepository.Comment.Properties.body == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(().getEAttributeType(), newValue);
				}
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}


	

}
