package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.DiagramDescriptionSpec;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLFactory;

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
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), stateMachine)) {
			if ("State Machine Diagram".equals(representation.getName())
					&& representation instanceof DiagramDescriptionSpec)
				return representation;
		}
		return null;
	}

}
