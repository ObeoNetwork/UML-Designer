package org.obeonetwork.dsl.uml2.profile.design.services;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

/**
 * This class provides methods to delete the Profile element. 
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr">mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class DeleteUMLElement {

	/**
	 * Constructor of the class.
	 */
	public DeleteUMLElement() {
		super();
	}

	/**
	 * deleteUMLElment delete an UML element and all its references.
	 * 
	 * @param element
	 *            to delete.
	 * @param profile
	 *            owner of element.
	 */
	public void deleteUMLElement(final Element element, final Profile profile) {
		if (element instanceof ElementImport) {
			deleteElementImport((ElementImport) element, profile);
		} else if (element instanceof Stereotype) {
			deleteStereotype((Stereotype) element, profile);
		} else if (element instanceof Extension) {
			deleteExtension((Extension) element, profile);
		} else {
			EcoreUtil.delete(element, true);
		}
	}

	/**
	 * deleteExtension delete an extension and all its references (base_
	 * attributes of the stereotype).
	 * 
	 * @param extension
	 *            to delete.
	 * @param profile
	 *            owner of extension.
	 */
	public void deleteExtension(final Extension extension, final Profile profile) {
		for (Element relatedElement : extension.getRelatedElements()) {
			if (relatedElement instanceof Stereotype && extension.getMetaclass() != null) {
				final EList<Property> properties = ((Stereotype) relatedElement)
						.getAttributes();
				int propertiesSize = properties.size();
				for (int i = 0; i < propertiesSize; i++) {
					if (properties.get(i).getAssociation() != null
							&& properties.get(i).getAssociation().equals(extension)) {
						properties.get(i).destroy();
						i--;
						propertiesSize--;
					}
				}
			}
		}
		EcoreUtil.delete(extension, true);
	}

	/**
	 * deleteStereotype delete an stereotype and all its references (Extensions,
	 * Generalizations).
	 * 
	 * @param stereotype
	 *            to delete.
	 * @param profile
	 *            owner of stereotype.
	 */
	public void deleteStereotype(final Stereotype stereotype,
			final Profile profile) {
		for (Extension extension : profile.getOwnedExtensions(false)) {
			if (extension.getEndType(stereotype.getName()) != null) {
				final Property baseProperty = extension.getMemberEnd("base_"
						+ extension.getMetaclass().getName(),
						extension.getMetaclass());
				if (baseProperty != null && baseProperty.getOwner().equals(stereotype)) {
					EcoreUtil.delete(extension, true);
				}
			}
		}
		EcoreUtil.delete(stereotype, true);
	}
	
	/**
	 * deleteElementImport delete an elementImport and all its references (base_
	 * attributes of the stereotypes).
	 * 
	 * @param elementImport
	 *            to delete.
	 * @param profile
	 *            owner of elementimport.
	 */
	public void deleteElementImport(final ElementImport elementImport,
			final Profile profile) {
		for (Extension extension : profile.getOwnedExtensions(false)) {
			if (extension.getMetaclass() != null
					&& extension.getMetaclass().equals(elementImport.getImportedElement())) {
				deleteExtension(extension, profile);
			}
		}
		EcoreUtil.delete(elementImport, true);
	}
}
