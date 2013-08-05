package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.UMLFactory;

import fr.obeo.dsl.viewpoint.business.api.dialect.DialectManager;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.description.RepresentationDescription;
import fr.obeo.dsl.viewpoint.diagram.sequence.description.SequenceDiagramDescription;

public class CreateSequenceDiagram extends AbstractCreateDiagram<Interaction> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getDiagramLabel(Interaction interaction) {
		return interaction.getName() + " " + "Sequence Diagram";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Interaction createSemanticObject() {
		return UMLFactory.eINSTANCE.createInteraction();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RepresentationDescription getDiagramDescription(Session session, Interaction interaction) {
		for (RepresentationDescription representation : DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(), interaction)) {
			if ("Sequence Diagram".equals(representation.getName())
					&& representation instanceof SequenceDiagramDescription)
				return representation;
		}
		return null;
	}

}
