/*******************************************************************************
 * Copyright (c) 2008, 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephane Begaudeau (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.java.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.umlgen.gen.java.utils.IUML2JavaConstants;

/**
 * This class contains the properties of the generation.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 2.0
 */
public class UML2JavaConfigurationHolder extends AdapterImpl {

    /**
     * The map containing the parameters of the generation.
     */
    private Map<String, Object> configuration = new HashMap<String, Object>();

    /**
     * Puts a new entry into the configuration.
     *
     * @param key
     *            The key of the entry
     * @param value
     *            The value of the entry
     */
    public void put(String key, Object value) {
        this.configuration.put(key, value);
    }

    /**
     * Returns the absolute location of the folder in which the code will be generated.
     *
     * @param eObject
     *            The model element
     * @return The absolute location of the folder in which the code will be generated.
     */
    public static String getGenerationRootPath(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringValue(eObject, IUML2JavaConstants.GENERATION_ROOT_PATH);
    }

    /**
     * Returns the default project name.
     *
     * @param eObject
     *            The model element
     * @return The default project name.
     */
    public static String getDefaultProjectName(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringValue(eObject, IUML2JavaConstants.DEFAULT_PROJECT_NAME);
    }

    /**
     * Returns the source folder path.
     *
     * @param eObject
     *            The model element
     * @return The source folder path.
     */
    public static String getSourceFolderPath(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringValue(eObject, IUML2JavaConstants.SOURCE_FOLDER_PATH);
    }

    /**
     * Returns the output folder path.
     *
     * @param eObject
     *            The model element
     * @return The output folder path.
     */
    public static String getOutputFolderPath(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringValue(eObject, IUML2JavaConstants.OUTPUT_FOLDER_PATH);
    }

    /**
     * Returns the JRE execution environment.
     *
     * @param eObject
     *            The model element
     * @return The JRE execution environment.
     */
    public static String getJREExecutionEnvironment(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringValue(eObject,
                IUML2JavaConstants.JRE_EXECUTION_ENVIRONMENT);
    }

    /**
     * Returns the list of packages name that should not be generated.
     *
     * @param eObject
     *            The model element
     * @return The list of packages name that should not be generated.
     */
    public static List<String> getPackagesToIgnoreDuringGeneration(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringListValue(eObject,
                IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_GENERATION);
    }

    /**
     * Returns the list of packages name that should be ignored during the import computation.
     *
     * @param eObject
     *            The model element
     * @return The list of packages name that should be ignored during the import computation.
     */
    public static List<String> getPackagesToIgnoreDuringImports(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringListValue(eObject,
                IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_IMPORTS);
    }

    /**
     * Indicates if we should generate getters and setters.
     *
     * @param eObject
     *            The model element
     * @return <code>true</code> if we should generate getters and setters, <code>false</code> otherwise.
     */
    public static boolean shouldGenerateGettersAndSetters(EObject eObject) {
        return UML2JavaConfigurationHolder.getBooleanValue(eObject,
                IUML2JavaConstants.GENERATE_GETTERS_AND_SETTERS);
    }

    /**
     * Indicates if we should generate getters for collections.
     *
     * @param eObject
     *            The model element
     * @return <code>true</code> if we should generate getters for collections, <code>false</code> otherwise.
     */
    public static boolean shouldGenerateGettersForCollections(EObject eObject) {
        return UML2JavaConfigurationHolder.getBooleanValue(eObject,
                IUML2JavaConstants.GENERATE_GETTERS_COLLECTIONS);
    }

    /**
     * Indicates if we should generate setters for collections.
     *
     * @param eObject
     *            The model element
     * @return <code>true</code> if we should generate setters for collections, <code>false</code> otherwise.
     */
    public static boolean shouldGenerateSettersForCollections(EObject eObject) {
        return UML2JavaConfigurationHolder.getBooleanValue(eObject,
                IUML2JavaConstants.GENERATE_SETTERS_COLLECTIONS);
    }

    /**
     * Indicates if we should generate advanced accessors for collections.
     *
     * @param eObject
     *            The model element
     * @return <code>true</code> if we should generate advanced accessors for collections, <code>false</code>
     *         otherwise.
     */
    public static boolean shouldGenerateAdvancedAccessorsForCollections(EObject eObject) {
        return UML2JavaConfigurationHolder.getBooleanValue(eObject,
                IUML2JavaConstants.GENERATE_ADVANCED_ACCESSORS_COLLECTIONS);
    }

    /**
     * Returns the author.
     *
     * @param eObject
     *            The model element
     * @return The author.
     */
    public static String getAuthor(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringValue(eObject, IUML2JavaConstants.AUTHOR);
    }

    /**
     * Returns the version.
     *
     * @param eObject
     *            The model element
     * @return The version.
     */
    public static String getVersion(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringValue(eObject, IUML2JavaConstants.VERSION);
    }

    /**
     * Returns the copyright and the license.
     *
     * @param eObject
     *            The model element
     * @return The copyright and the license.
     */
    public static String getCopyrightAndLicense(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringValue(eObject, IUML2JavaConstants.COPYRIGHT_AND_LICENSE);
    }

    /**
     * Returns the list of the components that should be ignored.
     *
     * @param eObject
     *            The model element
     * @return The list of the components that should be ignored.
     */
    public static List<String> getComponentsToIgnore(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringListValue(eObject,
                IUML2JavaConstants.COMPONENTS_TO_IGNORE);
    }

    /**
     * Returns the kind of component-based architecture to use.
     *
     * @param eObject
     *            The model element
     * @return The kind of component-based architecture to use.
     */
    public static String getComponentBasedArchitecture(EObject eObject) {
        return UML2JavaConfigurationHolder
                .getStringValue(eObject, IUML2JavaConstants.COMPONENTS_ARCHITECTURE);
    }

    /**
     * Returns the bundle provider.
     *
     * @param eObject
     *            The model element
     * @return The bundle provider.
     */
    public static String getBundleProvider(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringValue(eObject, IUML2JavaConstants.BUNDLE_PROVIDER);
    }

    /**
     * Returns the type of the ordered and unique collections.
     *
     * @param eObject
     *            The model element
     * @return The type of the ordered and unique collections.
     */
    public static String getOrderedUniqueCollectionsType(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringValue(eObject, IUML2JavaConstants.ORDERED_UNIQUE_TYPE);
    }

    /**
     * Returns the type of the ordered and not unique collections.
     *
     * @param eObject
     *            The model element
     * @return The type of the ordered and not unique collections.
     */
    public static String getOrderedNotUniqueCollectionsType(EObject eObject) {
        return UML2JavaConfigurationHolder
                .getStringValue(eObject, IUML2JavaConstants.ORDERED_NOT_UNIQUE_TYPE);
    }

    /**
     * Returns the type of the not ordered and unique collections.
     *
     * @param eObject
     *            The model element
     * @return The type of the not ordered and unique collections.
     */
    public static String getNotOrderedUniqueCollectionsType(EObject eObject) {
        return UML2JavaConfigurationHolder
                .getStringValue(eObject, IUML2JavaConstants.NOT_ORDERED_UNIQUE_TYPE);
    }

    /**
     * Returns the type of the not ordered and not unique collections.
     *
     * @param eObject
     *            The model element
     * @return The type of the not ordered and not unique collections.
     */
    public static String getNotOrderedNotUniqueCollectionsType(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringValue(eObject,
                IUML2JavaConstants.NOT_ORDERED_NOT_UNIQUE_TYPE);
    }

    /**
     * Returns <code>true</code> if we should ignore the Java types during the generation, <code>false</code>
     * otherwise.
     *
     * @param eObject
     *            The model element
     * @return <code>true</code> if we should ignore the Java types during the generation, <code>false</code>
     *         otherwise.
     */
    public static boolean shouldIgnoreJavaTypes(EObject eObject) {
        return UML2JavaConfigurationHolder.getBooleanValue(eObject,
                IUML2JavaConstants.IGNORE_JAVA_TYPES_DURING_GENERATION_AND_IMPORT);
    }

    /**
     * Returns the list of the types to ignore during the generation.
     *
     * @param eObject
     *            The model element
     * @return The list of the types to ignore during the generation.
     */
    public static List<String> getTypesToIgnoreDuringTheGeneration(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringListValue(eObject,
                IUML2JavaConstants.TYPES_TO_IGNORE_DURING_GENERATION);
    }

    /**
     * Returns the list of the types to ignore during the import.
     *
     * @param eObject
     *            The model element
     * @return The list of the types to ignore during the import.
     */
    public static List<String> getTypesToIgnoreDuringTheImports(EObject eObject) {
        return UML2JavaConfigurationHolder.getStringListValue(eObject,
                IUML2JavaConstants.TYPES_TO_IGNORE_DURING_IMPORTS);
    }

    /**
     * Returns the configuration holder for the given model element.
     *
     * @param eObject
     *            The model element
     * @return The configuration holder for the given model element.
     */
    private static UML2JavaConfigurationHolder getConfigurationHolder(EObject eObject) {
        Resource eResource = eObject.eResource();
        EObject rootEObject = eResource.getContents().get(0);

        List<Adapter> eAdapters = rootEObject.eAdapters();
        for (Adapter adapter : eAdapters) {
            if (adapter instanceof UML2JavaConfigurationHolder) {
                return (UML2JavaConfigurationHolder)adapter;
            }
        }

        return null;
    }

    /**
     * Returns a string value from the given key.
     *
     * @param eObject
     *            The model element
     * @param key
     *            The key
     * @return A string value from the given key.
     */
    private static String getStringValue(EObject eObject, String key) {
        UML2JavaConfigurationHolder configurationHolder = UML2JavaConfigurationHolder
                .getConfigurationHolder(eObject);
        if (configurationHolder != null) {
            Object object = configurationHolder.getValue(key);
            if (object instanceof String) {
                return (String)object;
            }
        }
        return "";
    }

    /**
     * Returns a boolean value from the given key.
     *
     * @param eObject
     *            The model element
     * @param key
     *            The key
     * @return A boolean value from the given key.
     */
    private static boolean getBooleanValue(EObject eObject, String key) {
        boolean result = false;
        UML2JavaConfigurationHolder configurationHolder = UML2JavaConfigurationHolder
                .getConfigurationHolder(eObject);
        if (configurationHolder != null) {
            Object object = configurationHolder.getValue(key);
            if (object instanceof String) {
                result = Boolean.valueOf((String)object).booleanValue();
            } else if (object instanceof Boolean) {
                result = ((Boolean)object).booleanValue();
            }
        }
        return result;
    }

    /**
     * Returns a list of strings from the given key.
     *
     * @param eObject
     *            The model element
     * @param key
     *            The key
     * @return A list of strings from the given key.
     */
    private static List<String> getStringListValue(EObject eObject, String key) {
        List<String> packages = new ArrayList<String>();

        UML2JavaConfigurationHolder configurationHolder = UML2JavaConfigurationHolder
                .getConfigurationHolder(eObject);
        if (configurationHolder != null) {
            Object object = configurationHolder.getValue(key);
            if (object instanceof String) {
                String str = (String)object;

                StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
                while (stringTokenizer.hasMoreTokens()) {
                    String nextToken = stringTokenizer.nextToken();
                    String trim = nextToken.trim();
                    if (trim.length() > 0) {
                        packages.add(trim);
                    }
                }
            }
        }
        return packages;
    }

    /**
     * Returns a value for the given key.
     *
     * @param key
     *            The key
     * @return A value for the given key.
     */
    public Object getValue(String key) {
        return this.configuration.get(key);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.common.notify.impl.AdapterImpl#isAdapterForType(java.lang.Object)
     */
    @Override
    public boolean isAdapterForType(Object type) {
        return type == UML2JavaConfigurationHolder.class;
    }
}
