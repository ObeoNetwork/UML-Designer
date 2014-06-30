package org.obeonetwork.dsl.uml2.profile.design.services;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLFactory;

public class ProfileServices {
	/**
	 * Create a profile under a package.
	 * 
	 * @param pkg
	 *            The package
	 * @return A profile
	 */
	public Profile initProfileForPackage(org.eclipse.uml2.uml.Package pkg) {
		Profile profile = getProfile(pkg);
		pkg.getPackagedElements().add(profile);
		return profile;
	}

	/**
	 * Get a profile.
	 * 
	 * @param parent
	 *            Parent
	 * @return Activity
	 */
	private Profile getProfile(NamedElement parent) {
		// Check if a profile already exists
		if (parent.eContents() != null && parent.eContents().size() > 0) {
			for (EObject obj : parent.eContents()) {
				if (obj instanceof Profile) {
					// There's already a profile
					// Do nothing
					return (Profile)obj;
				}
			}
		}
		Profile profile = UMLFactory.eINSTANCE.createProfile();
		String profileLabel = parent.getName() + " profile";
		profile.setName(profileLabel);
		return profile;
	}
}
