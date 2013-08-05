package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLFactory;

import fr.obeo.dsl.viewpoint.business.api.dialect.DialectManager;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.internal.metamodel.description.spec.DiagramDescriptionSpec;
import fr.obeo.dsl.viewpoint.description.RepresentationDescription;

public class CreateStateMachineDiagram extends AbstractCreateDiagram<StateMachine> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getDiagramLabel(StateMachine stateMachine) {
		return stateMachine.getName() + " " + "State Machine Diagram";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected StateMachine createSemanticObject() {
		return UMLFactory.eINSTANCE.createStateMachine();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RepresentationDescription getDiagramDescription(Session session, StateMachine stateMachine) {
		for (RepresentationDescription representation : DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(), stateMachine)) {
			if ("State Machine Diagram".equals(representation.getName())
					&& representation instanceof DiagramDescriptionSpec)
				return representation;
		}
		return null;
	}

}
