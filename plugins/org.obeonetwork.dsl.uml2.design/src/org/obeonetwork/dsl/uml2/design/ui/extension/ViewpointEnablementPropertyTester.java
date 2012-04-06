package org.obeonetwork.dsl.uml2.design.ui.extension;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;

import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.api.session.SessionManager;
import fr.obeo.dsl.viewpoint.description.Viewpoint;

public class ViewpointEnablementPropertyTester extends PropertyTester {

	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof EObject) {
			Session foundSession = SessionManager.INSTANCE.getSession((EObject)receiver);
			if (foundSession != null) {
				for (Viewpoint vp : foundSession.getSelectedViewpoints()) {
					if (vp.getName().contains(property))
						return true;

				}
			}

		}
		return false;
	}

}
