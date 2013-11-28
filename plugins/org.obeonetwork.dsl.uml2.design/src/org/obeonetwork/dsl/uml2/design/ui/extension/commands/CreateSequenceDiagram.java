package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.UMLFactory;

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
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), interaction)) {
			if ("Sequence Diagram".equals(representation.getName())
					&& representation instanceof SequenceDiagramDescription)
				return representation;
		}
		return null;
	}

}
