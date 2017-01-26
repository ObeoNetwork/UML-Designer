/*******************************************************************************
 * Copyright (c) 2011, 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephane Begaudeau (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.java.services;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * General purpose services.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 1.0
 */
public class CommonServices {

    /**
     * Returns the current date.
     * 
     * @return The date.
     */
    public String reqDate() {
        Date date = new Date(); // to get the date
        Locale locale = Locale.getDefault(); // to get the language of the system
        DateFormat dateFormatShort = DateFormat.getDateInstance(DateFormat.LONG, locale);
        return dateFormatShort.format(date);
    }

    /**
     * Returns the current time.
     * 
     * @return The time.
     */
    public String reqTime() {
        Date date = new Date();
        return DateFormat.getTimeInstance(DateFormat.LONG).format(date);
    }

    /**
     * Formats the javadoc.
     * 
     * @param documentation
     *            The given documentation
     * @return The javadoc formatted
     */
    public String formatJavadoc(String documentation) {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(documentation);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            stringBuilder.append(" * " + line);
            if (scanner.hasNextLine()) {
                stringBuilder.append("<br />" + System.getProperty("line.separator"));
            } else {
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }
        scanner.close();
        return stringBuilder.toString();
    }
}
