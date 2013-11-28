package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.metamodel.description.spec.DiagramDescriptionSpec;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.UMLFactory;

public class CreateActivityDiagram extends AbstractCreateDiagram<Activity> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getDiagramLabel(Activity activity) {
		return activity.getName() + " " + "Activity Diagram";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Activity createSemanticObject() {
		return UMLFactory.eINSTANCE.createActivity();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RepresentationDescription getDiagramDescription(Session session, Activity activity) {
		for (RepresentationDescription representation : DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), activity)) {
			if ("Activity Diagram".equals(representation.getName())
					&& representation instanceof DiagramDescriptionSpec)
				return representation;
		}
		return null;
	}

}
