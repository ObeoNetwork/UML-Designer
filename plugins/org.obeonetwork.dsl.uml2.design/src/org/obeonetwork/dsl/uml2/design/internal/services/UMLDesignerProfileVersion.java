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
package org.obeonetwork.dsl.uml2.design.internal.services;

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
	private final String UML_DESIGNER_PROFILE_VERSION_KEY = "Version"; //$NON-NLS-1$

	/** key for date detail */
	private final String UML_DESIGNER_PROFILE_DATE_KEY = "Date"; //$NON-NLS-1$

	/** key for author detail */
	private final String UML_DESIGNER_PROFILE_AUTHOR_KEY = "Author"; //$NON-NLS-1$

	/** key for copyright detail */
	private final String UML_DESIGNER_PROFILE_COPYRIGHT_KEY = "Copyright"; //$NON-NLS-1$

	/** key for comment detail */
	private final String UML_DESIGNER_PROFILE_COMMENT_KEY = "Comment"; //$NON-NLS-1$

	/** source for eAnnotation that qualifies the profile definition */
	private final String UML_DESIGNER_PROFILE_EANNOTATION_SOURCE = "UMLDesignerProfileVersion"; //$NON-NLS-1$

	private Version version;

	private String date;

	private String author;

	private String copyright;

	private String comment;

	/**
	 * Constructor of this class.
	 */
	public UMLDesignerProfileVersion() {
		version = new Version();
		date = ""; //$NON-NLS-1$
		author = ""; //$NON-NLS-1$
		copyright = ""; //$NON-NLS-1$
		comment = ""; //$NON-NLS-1$
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
		copyright = copyRight;
		this.comment = comment;

	}

	/**
	 * Get author.
	 *
	 * @return Author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Get comment.
	 *
	 * @return Comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Get copyright.
	 *
	 * @return Copyright
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * Get date.
	 *
	 * @return Date
	 */
	public String getDate() {
		return date;
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

	/**
	 * Get version
	 * @return version
	 */
	public Version getVersion() {
		return version;
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
				uMLDesignerProfileVersion.getCopyright());

		eAnnotation.getDetails().put(
				UML_DESIGNER_PROFILE_COMMENT_KEY,
				uMLDesignerProfileVersion.getComment());

		return eAnnotation;
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
				authorFromAnnotation != null ? authorFromAnnotation : "", //$NON-NLS-1$
						copyRightFromAnnotation != null ? copyRightFromAnnotation
								: "", //$NON-NLS-1$
								commentFromAnnotation != null ? commentFromAnnotation : ""); //$NON-NLS-1$
		return uMLDesignerProfileVersion;
	}

	/**
	 * Set author.
	 *
	 * @param author
	 *            Author
	 */
	public void setAuthor(final String author) {
		this.author = author;
	}

	/**
	 * Set comment.
	 *
	 * @param comment
	 *            Comment
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}

	/**
	 * Set copyright.
	 *
	 * @param copyright
	 *            copyright
	 */
	public void setCopyright(final String copyright) {
		this.copyright = copyright;
	}

	/**
	 * Set date.
	 * 
	 * @param date
	 *            Date
	 */
	public void setDate(final String date) {
		this.date = date;
	}

	/**
	 * Set version
	 * @param version Version
	 */
	public void setVersion(final Version version) {
		this.version = version;
	}

}
