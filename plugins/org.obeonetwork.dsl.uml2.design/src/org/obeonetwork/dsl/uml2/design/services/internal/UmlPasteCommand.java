package org.obeonetwork.dsl.uml2.design.services.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.NamedElement;

import fr.obeo.dsl.viewpoint.diagram.tools.internal.commands.emf.PasteFromViewpointClipboardCommand;

public class UmlPasteCommand extends PasteFromViewpointClipboardCommand {
	/**
	 * Editing domain.
	 */
	private TransactionalEditingDomain ted;

	private EObject container;

	private EObject copiedSemanticElement;

	public UmlPasteCommand(TransactionalEditingDomain domain, EObject semanticContainer) {
		super(domain, semanticContainer);
		ted = domain;
		container = semanticContainer;
	}

	@Override
	protected void preExecute() {
		// Change the name to show that it is a copy
		for (Object object : ted.getClipboard()) {
			if (object instanceof NamedElement) {
				((NamedElement)object).setName(getCopiedElementName((NamedElement)object, container));
			}
		}
	}

	/**
	 * Get a unique name for the copied element in the container.
	 * 
	 * @param copiedElement
	 *            Copied element
	 * @param container
	 *            Container
	 * @return Unique name for copy
	 */
	private String getCopiedElementName(NamedElement copiedElement, EObject container) {
		int count = 0;
		String copiedElementName = (copiedElement).getName() + "_Copy";
		for (EObject eContent : container.eContents()) {
			if (eContent instanceof NamedElement) {
				if (copiedElementName.equals(((NamedElement)eContent).getName()))
					count++;
			}
		}
		if (count > 0) {
			copiedElementName += count;
		}
		return copiedElementName;
	}

	@Override
	protected void postExecute() {
		super.postExecute();
		for (Object object : ted.getClipboard()) {
			if (object instanceof NamedElement) {
				for (EObject eContent : container.eContents()) {
					if (eContent instanceof NamedElement) {
						if (((NamedElement)object).getName().equals(((NamedElement)eContent).getName()))
							copiedSemanticElement = eContent;
					}
				}
			}
		}
	}

	/**
	 * Get the semantic copied element.
	 * 
	 * @return The semantic copied element.
	 */
	public EObject getCopiedSemanticElement() {
		return copiedSemanticElement;
	}

}
