package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.metamodel.description.spec.DiagramDescriptionSpec;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLFactory;

public class CreateProfileDiagram extends AbstractCreateDiagram<Profile> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getDiagramLabel(Profile profile) {
		return profile.getName() + " " + "Profile Diagram";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Profile createSemanticObject() {
		return UMLFactory.eINSTANCE.createProfile();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RepresentationDescription getDiagramDescription(Session session, Profile profile) {
		for (RepresentationDescription representation : DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), profile)) {
			if ("Profile Diagram".equals(representation.getName())
					&& representation instanceof DiagramDescriptionSpec)
				return representation;
		}
		return null;
	}

}
