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

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Profile version.
 *
 * @author Mohamed-Lamine BOUKHANOUFA <a href="mailto:mohamed-lamine.boukhanoufa@obeo.fr" >mohamed-lamine.
 *         boukhanoufa@obeo.fr</a>
 */
public class Version {
	/**
	 * Creates a version given the specific String.
	 *
	 * @param version
	 *            the string to parse
	 * @return the version value corresponding to the String
	 * @throws IllegalArgumentException
	 */
	public static Version parseVersion(String version) throws IllegalArgumentException {
		if (version == null) {
			return EMPTY_VERSION;
		}

		final String trimmedVersion = version.trim();
		if (trimmedVersion.length() == 0) {
			return EMPTY_VERSION;
		}
		return new Version(trimmedVersion);
	}

	/**
	 * The empty version "0.0.0". Equivalent to calling <code>new Version(0,0,0)</code>
	 */
	public static final Version EMPTY_VERSION = new Version(0, 0, 0);

	/** separator for the version string */
	private static final String SEPARATOR = "."; //$NON-NLS-1$

	/** micro version number. */
	private int micro;

	/** minor version number. */
	private int minor;

	/** major version number. */
	private int major;

	/**
	 * Creates a new empty Version.
	 */
	@SuppressWarnings("unused")
	public Version() {
		new Version(0, 0, 0);
	}

	/**
	 * Creates a new Version.
	 *
	 * @param major
	 *            the major version value (positive number)
	 * @param minor
	 *            the minor version value (positive number)
	 * @param micro
	 *            the micro version value (positive number)
	 */
	public Version(final int major, final int minor, final int micro) {
		this.major = major;
		this.minor = minor;
		this.micro = micro;
	}

	/**
	 * Creates a new Version, parsing a string value.
	 *
	 * @param value
	 *            the string representing the version
	 * @throws IllegalArgumentException
	 *             Occurs while parsing version
	 */
	public Version(final String value) throws IllegalArgumentException {
		try {
			final StringTokenizer st = new StringTokenizer(value, SEPARATOR, true);
			major = Integer.parseInt(st.nextToken());

			if (st.hasMoreTokens()) {
				st.nextToken(); // consume delimiter
				minor = Integer.parseInt(st.nextToken());

				if (st.hasMoreTokens()) {
					st.nextToken(); // consume delimiter
					micro = Integer.parseInt(st.nextToken());

					if (st.hasMoreTokens()) {
						throw new IllegalArgumentException("invalid version format"); //$NON-NLS-1$
					}
				}
			}
		} catch (final NoSuchElementException e) {
			throw new IllegalArgumentException("invalid version format"); //$NON-NLS-1$
		}
	}

	/**
	 * Returns the major version number.
	 *
	 * @return The major version number
	 */
	public int getMajor() {
		return major;
	}

	/**
	 * Returns the micro version number.
	 *
	 * @return The micro version number
	 */
	public int getMicro() {
		return micro;
	}

	/**
	 * Returns the minor version number.
	 *
	 * @return The minor version number
	 */
	public int getMinor() {
		return minor;
	}

	/**
	 * Returns the string corresponding to the version.
	 *
	 * @return * Returns the string corresponding to the version
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return major + SEPARATOR + minor + SEPARATOR + micro;
	}

	/**
	 * Updates the version numbers.
	 *
	 * @param majorPara
	 *            the new major value
	 * @param minorPara
	 *            the new minor value
	 * @param microPara
	 *            the new micro value
	 */
	public void updateVersion(final int majorPara, final int minorPara, final int microPara) {
		major = majorPara;
		minor = minorPara;
		micro = microPara;
	}

}
