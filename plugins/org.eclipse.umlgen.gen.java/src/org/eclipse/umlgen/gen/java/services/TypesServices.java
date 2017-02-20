/*******************************************************************************
 * Copyright (c) 2008, 2017 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephane Begaudeau (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.java.services;

import java.util.List;

import org.eclipse.uml2.uml.LiteralNull;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * Service class to compute the types and their default values.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 2.0
 */
public class TypesServices {

    /** The double quote string. */
    private static final String DOUBLE_QUOTE = "\"";

    /** The value "0" in string. */
    private static final String STRING_ZERO = "0";

    /**
     * This returns the name of the returned type of the given UML operation.
     *
     * @param anOperation
     *            The operation.
     * @return The name of the returned type.
     */
    public String returnTypeName(Operation anOperation) {
        List<Parameter> returnResult = anOperation.returnResult();
        if (returnResult.size() == 0) {
            return "void";
        }
        return this.typeName(returnResult.get(0));
    }

    /**
     * This returns the name of the given type.
     *
     * @param aTypedElement
     *            The type.
     * @return The name of the type.
     */
    public String typeName(TypedElement aTypedElement) {
        String typeName = "Object";
        if (aTypedElement.getType() == null) {
            return typeName;
        }

        if (aTypedElement instanceof MultiplicityElement && (((MultiplicityElement)aTypedElement)
                .getUpper() > 1 || ((MultiplicityElement)aTypedElement).getUpper() == -1)) {
            MultiplicityElement multiplicityElement = (MultiplicityElement)aTypedElement;

            String collectionType = "";

            if (multiplicityElement.isOrdered() && multiplicityElement.isUnique()) {
                String orderedUniqueCollectionsType = UML2JavaConfigurationHolder
                        .getOrderedUniqueCollectionsType(multiplicityElement);
                int lastIndexOf = orderedUniqueCollectionsType.lastIndexOf('.');
                if (lastIndexOf != -1) {
                    collectionType = orderedUniqueCollectionsType.substring(lastIndexOf + 1);
                } else {
                    collectionType = orderedUniqueCollectionsType;
                }
            } else if (multiplicityElement.isOrdered() && !multiplicityElement.isUnique()) {
                String orderedNotUniqueCollectionsType = UML2JavaConfigurationHolder
                        .getOrderedNotUniqueCollectionsType(multiplicityElement);
                int lastIndexOf = orderedNotUniqueCollectionsType.lastIndexOf('.');
                if (lastIndexOf != -1) {
                    collectionType = orderedNotUniqueCollectionsType.substring(lastIndexOf + 1);
                } else {
                    collectionType = orderedNotUniqueCollectionsType;
                }
            } else if (!multiplicityElement.isOrdered() && multiplicityElement.isUnique()) {
                String notOrderedUniqueCollectionsType = UML2JavaConfigurationHolder
                        .getNotOrderedUniqueCollectionsType(multiplicityElement);
                int lastIndexOf = notOrderedUniqueCollectionsType.lastIndexOf('.');
                if (lastIndexOf != -1) {
                    collectionType = notOrderedUniqueCollectionsType.substring(lastIndexOf + 1);
                } else {
                    collectionType = notOrderedUniqueCollectionsType;
                }
            } else if (!multiplicityElement.isOrdered() && !multiplicityElement.isUnique()) {
                String notOrderedNotUniqueCollectionsType = UML2JavaConfigurationHolder
                        .getNotOrderedNotUniqueCollectionsType(multiplicityElement);
                int lastIndexOf = notOrderedNotUniqueCollectionsType.lastIndexOf('.');
                if (lastIndexOf != -1) {
                    collectionType = notOrderedNotUniqueCollectionsType.substring(lastIndexOf + 1);
                } else {
                    collectionType = notOrderedNotUniqueCollectionsType;
                }
            }

            typeName = collectionType + "<" + aTypedElement.getType().getName() + ">";
        } else {
            typeName = aTypedElement.getType().getName();
        }

        return typeName;
    }

    /**
     * This returns the default value of the given type.
     *
     * @param aTypedElement
     *            The type.
     * @return The default value.
     */
    public String defaultValue(TypedElement aTypedElement) {
        String defaultValue = "";

        boolean shortcut = false;
        if (aTypedElement.getType() == null) {
            shortcut = true;
        } else if (aTypedElement instanceof Property) {
            Property property = (Property)aTypedElement;
            String aDefault = property.getDefault();
            if (aDefault != null && aDefault.length() > 0) {
                if ("String".equals(aTypedElement.getType().getName()) && !aDefault.startsWith(DOUBLE_QUOTE)
                        && !aDefault.endsWith(DOUBLE_QUOTE)) {
                    // Add quotes for strings if there are not quotes
                    defaultValue = DOUBLE_QUOTE + aDefault + DOUBLE_QUOTE;
                } else {
                    defaultValue = aDefault;
                }
                shortcut = true;
            }
            if (!shortcut) {
                ValueSpecification valueSpecification = property.getDefaultValue();
                if (valueSpecification instanceof LiteralNull) {
                    defaultValue = "null";
                    shortcut = true;
                }
            }
        }

        if (shortcut) {
            return defaultValue;
        }

        if (aTypedElement instanceof MultiplicityElement && (((MultiplicityElement)aTypedElement)
                .getUpper() > 1 || ((MultiplicityElement)aTypedElement).getUpper() == -1)) {
            MultiplicityElement multiplicityElement = (MultiplicityElement)aTypedElement;

            String collectionType = "";

            if (multiplicityElement.isOrdered() && multiplicityElement.isUnique()) {
                String orderedUniqueCollectionsType = UML2JavaConfigurationHolder
                        .getOrderedUniqueCollectionsType(multiplicityElement);
                int lastIndexOf = orderedUniqueCollectionsType.lastIndexOf('.');
                if (lastIndexOf != -1) {
                    collectionType = orderedUniqueCollectionsType.substring(lastIndexOf + 1);
                } else {
                    collectionType = orderedUniqueCollectionsType;
                }
            } else if (multiplicityElement.isOrdered() && !multiplicityElement.isUnique()) {
                String orderedNotUniqueCollectionsType = UML2JavaConfigurationHolder
                        .getOrderedNotUniqueCollectionsType(multiplicityElement);
                int lastIndexOf = orderedNotUniqueCollectionsType.lastIndexOf('.');
                if (lastIndexOf != -1) {
                    collectionType = orderedNotUniqueCollectionsType.substring(lastIndexOf + 1);
                } else {
                    collectionType = orderedNotUniqueCollectionsType;
                }
            } else if (!multiplicityElement.isOrdered() && multiplicityElement.isUnique()) {
                String notOrderedUniqueCollectionsType = UML2JavaConfigurationHolder
                        .getNotOrderedUniqueCollectionsType(multiplicityElement);
                int lastIndexOf = notOrderedUniqueCollectionsType.lastIndexOf('.');
                if (lastIndexOf != -1) {
                    collectionType = notOrderedUniqueCollectionsType.substring(lastIndexOf + 1);
                } else {
                    collectionType = notOrderedUniqueCollectionsType;
                }
            } else if (!multiplicityElement.isOrdered() && !multiplicityElement.isUnique()) {
                String notOrderedNotUniqueCollectionsType = UML2JavaConfigurationHolder
                        .getNotOrderedNotUniqueCollectionsType(multiplicityElement);
                int lastIndexOf = notOrderedNotUniqueCollectionsType.lastIndexOf('.');
                if (lastIndexOf != -1) {
                    collectionType = notOrderedNotUniqueCollectionsType.substring(lastIndexOf + 1);
                } else {
                    collectionType = notOrderedNotUniqueCollectionsType;
                }
            }

            defaultValue = "new " + collectionType + "<" + aTypedElement.getType().getName() + ">()";
        } else if ("String".equals(aTypedElement.getType().getName())) {
            defaultValue = "\"\"";
        } else if ("Boolean".equals(aTypedElement.getType().getName())) {
            defaultValue = "Boolean.FALSE";
        } else if ("boolean".equals(aTypedElement.getType().getName())) {
            defaultValue = "false";
        } else if ("Integer".equals(aTypedElement.getType().getName())) {
            defaultValue = "Integer.valueOf(0)";
        } else if ("int".equals(aTypedElement.getType().getName())) {
            defaultValue = STRING_ZERO;
        } else if ("Char".equals(aTypedElement.getType().getName())) {
            defaultValue = "Character.valueOf(0)";
        } else if ("char".equals(aTypedElement.getType().getName())) {
            defaultValue = STRING_ZERO;
        } else if ("Long".equals(aTypedElement.getType().getName())) {
            defaultValue = "Long.valueOf(0L)";
        } else if ("long".equals(aTypedElement.getType().getName())) {
            defaultValue = "0L";
        } else if ("Double".equals(aTypedElement.getType().getName())) {
            defaultValue = "Double.valueOf(0D)";
        } else if ("double".equals(aTypedElement.getType().getName())) {
            defaultValue = "0D";
        } else if ("Float".equals(aTypedElement.getType().getName())) {
            defaultValue = "Float.valueOf(0F)";
        } else if ("float".equals(aTypedElement.getType().getName())) {
            defaultValue = "0F";
        } else if ("Byte".equals(aTypedElement.getType().getName())) {
            defaultValue = "Byte.valueOf(\"+0\")";
        } else if ("byte".equals(aTypedElement.getType().getName())) {
            defaultValue = STRING_ZERO;
        } else if ("Date".equals(aTypedElement.getType().getName())) {
            defaultValue = "new Date()";
        } else if ("short".equals(aTypedElement.getType().getName())) {
            defaultValue = STRING_ZERO;
        } else {
            defaultValue = "null";
        }

        return defaultValue;
    }
}
