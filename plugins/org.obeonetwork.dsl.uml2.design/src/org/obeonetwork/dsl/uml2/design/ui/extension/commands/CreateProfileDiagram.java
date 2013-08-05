package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLFactory;

import fr.obeo.dsl.viewpoint.business.api.dialect.DialectManager;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.internal.metamodel.description.spec.DiagramDescriptionSpec;
import fr.obeo.dsl.viewpoint.description.RepresentationDescription;

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
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(), profile)) {
			if ("Profile Diagram".equals(representation.getName())
					&& representation instanceof DiagramDescriptionSpec)
				return representation;
		}
		return null;
	}

}
