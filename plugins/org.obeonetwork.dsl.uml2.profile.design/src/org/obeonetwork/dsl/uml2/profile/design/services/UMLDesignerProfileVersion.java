/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.profile.design.services;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.uml2.uml.Profile;

/**
 * Information about Profile Version: Version, date, Author, Copyright, Comment.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class UMLDesignerProfileVersion {

	/** key for version detail */
	private final String UML_DESIGNER_PROFILE_VERSION_KEY = "Version";

	/** key for date detail */
	private final String UML_DESIGNER_PROFILE_DATE_KEY = "Date";

	/** key for author detail */
	private final String UML_DESIGNER_PROFILE_AUTHOR_KEY = "Author";

	/** key for copyright detail */
	private final String UML_DESIGNER_PROFILE_COPYRIGHT_KEY = "Copyright";

	/** key for comment detail */
	private final String UML_DESIGNER_PROFILE_COMMENT_KEY = "Comment";

	/** source for eAnnotation that qualifies the profile definition */
	private final String UML_DESIGNER_PROFILE_EANNOTATION_SOURCE = "UMLDesignerProfileVersion";

	private Version version;

	private String date;

	private String author;

	private String copyRight;

	private String comment;

	/**
	 * Constructor of this class.
	 */
	public UMLDesignerProfileVersion() {
		version = new Version();
		date = "";
		author = "";
		copyRight = "";
		comment = "";
	}

	/**
	 * Constructor of this class.
	 * 
	 * @param version
	 *            number
	 * @param date
	 *            of version
	 * @param author
	 *            of version
	 * @param copyRight
	 *            of version
	 * @param comment
	 *            of version
	 */
	public UMLDesignerProfileVersion(final Version version, final String date,
			final String author, final String copyRight, final String comment) {
		this.version = version;
		this.date = date;
		this.author = author;
		this.copyRight = copyRight;
		this.comment = comment;

	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(final Version version) {
		this.version = version;
	}

	public String getDate() {
		return date;
	}

	public void setDate(final String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public String getCopyRight() {
		return copyRight;
	}

	public void setCopyRight(final String copyRight) {
		this.copyRight = copyRight;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	/**
	 * Return the version of the profile.
	 * 
	 * @param profile
	 *            from what get the version.
	 * @return the version.
	 */
	public UMLDesignerProfileVersion getProfileVersion(final Profile profile) {
		UMLDesignerProfileVersion uMLDesignerProfileVersion = null;
		final EPackage profileDefinition = profile.getDefinition();
		if (profileDefinition != null
				&& profileDefinition
						.getEAnnotation(UML_DESIGNER_PROFILE_EANNOTATION_SOURCE) != null) {
			final EAnnotation profileEAnnotation = profileDefinition
					.getEAnnotation(UML_DESIGNER_PROFILE_EANNOTATION_SOURCE);
			uMLDesignerProfileVersion = parseEAnnotationVersion(profileEAnnotation);
		} else {
			uMLDesignerProfileVersion = new UMLDesignerProfileVersion();
		}

		return uMLDesignerProfileVersion;
	}

	private UMLDesignerProfileVersion parseEAnnotationVersion(
			final EAnnotation profileEAnnotation) {
		final String versionValue = profileEAnnotation.getDetails().get(
				UML_DESIGNER_PROFILE_VERSION_KEY);

		Version versionFromAnnotation;
		try {
			versionFromAnnotation = Version.parseVersion(versionValue);
		} catch (final IllegalArgumentException e) {
			versionFromAnnotation = Version.EMPTY_VERSION;
		}

		final String dateFromAnnotation = profileEAnnotation.getDetails().get(
				UML_DESIGNER_PROFILE_DATE_KEY);

		final String authorFromAnnotation = profileEAnnotation.getDetails()
				.get(
				UML_DESIGNER_PROFILE_AUTHOR_KEY);

		final String copyRightFromAnnotation = profileEAnnotation.getDetails()
				.get(
				UML_DESIGNER_PROFILE_COPYRIGHT_KEY);

		final String commentFromAnnotation = profileEAnnotation.getDetails()
				.get(
				UML_DESIGNER_PROFILE_COMMENT_KEY);

		final UMLDesignerProfileVersion uMLDesignerProfileVersion = new UMLDesignerProfileVersion(
				versionFromAnnotation, dateFromAnnotation,
				(authorFromAnnotation != null) ? authorFromAnnotation : "",
				(copyRightFromAnnotation != null) ? copyRightFromAnnotation
						: "",
				(commentFromAnnotation != null) ? commentFromAnnotation : "");
		return uMLDesignerProfileVersion;
	}

	/**
	 * Initiate an annotation with a given version.
	 * 
	 * @param eAnnotation
	 *            to initiate
	 * @param uMLDesignerProfileVersion
	 *            the version
	 * @return annotation
	 */
	public EAnnotation initEAnnotationVersion(final EAnnotation eAnnotation,
			final UMLDesignerProfileVersion uMLDesignerProfileVersion) {

		eAnnotation
.setSource(UML_DESIGNER_PROFILE_EANNOTATION_SOURCE);

		eAnnotation.getDetails().put(
UML_DESIGNER_PROFILE_VERSION_KEY,
				uMLDesignerProfileVersion.getVersion().toString());

		eAnnotation.getDetails().put(
UML_DESIGNER_PROFILE_DATE_KEY,
				uMLDesignerProfileVersion.getDate());

		eAnnotation.getDetails().put(
UML_DESIGNER_PROFILE_AUTHOR_KEY,
				uMLDesignerProfileVersion.getAuthor());

		eAnnotation.getDetails().put(
UML_DESIGNER_PROFILE_COPYRIGHT_KEY,
				uMLDesignerProfileVersion.getCopyRight());

		eAnnotation.getDetails().put(
UML_DESIGNER_PROFILE_COMMENT_KEY,
				uMLDesignerProfileVersion.getComment());

		return eAnnotation;
	}

}
