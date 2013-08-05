package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.UMLFactory;

import fr.obeo.dsl.viewpoint.business.api.dialect.DialectManager;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.internal.metamodel.description.spec.DiagramDescriptionSpec;
import fr.obeo.dsl.viewpoint.description.RepresentationDescription;

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
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(), activity)) {
			if ("Activity Diagram".equals(representation.getName())
					&& representation instanceof DiagramDescriptionSpec)
				return representation;
		}
		return null;
	}

}
