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
package org.eclipse.umlgen.gen.java.utils;

import java.util.Calendar;

/**
 * List of constants for the UML2Java generator.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 2.0
 */
public interface IUML2JavaConstants {

    /**
     * The extension of the UML files.
     */
    String UML_FILE_EXTENSION = "uml";

    /**
     * The key representing the uml model path.
     */
    String UML_MODEL_PATH = "uml_model_path";

    /**
     * The nature used by PDE plugin projects.
     */
    String PDE_PLUGIN_NATURE_ID = "org.eclipse.pde.PluginNature";

    /**
     * The absolute location of the folder in which the code will be generated.
     */
    String GENERATION_ROOT_PATH = "generation_root_path";

    /**
     * The key representing the default project name.
     */
    String DEFAULT_PROJECT_NAME = "default_project_name";

    /**
     * The key representing the source folder path.
     */
    String SOURCE_FOLDER_PATH = "source_folder_path";

    /**
     * The key representing the output folder path.
     */
    String OUTPUT_FOLDER_PATH = "output_folder_path";

    /**
     * The key representing the JRE execution environment.
     */
    String JRE_EXECUTION_ENVIRONMENT = "jre_execution_environment";

    /**
     * The key representing the packages to ignore during the generation.
     */
    String PACKAGES_TO_IGNORE_DURING_GENERATION = "packages_ignore_generation";

    /**
     * The key representing the packages to ignore during imports.
     */
    String PACKAGES_TO_IGNORE_DURING_IMPORTS = "packages_ignore_imports";

    /**
     * The key indicating if we should generate getters and setters.
     */
    String GENERATE_GETTERS_AND_SETTERS = "generate_getters_setters";

    /**
     * The key indicating if we should generate getters for collections.
     */
    String GENERATE_GETTERS_COLLECTIONS = "generate_getters_collections";

    /**
     * The key indicating if we should generate setters for collections.
     */
    String GENERATE_SETTERS_COLLECTIONS = "generate_setters_collections";

    /**
     * The key indicating if we should generate advanced accessors for collections.
     */
    String GENERATE_ADVANCED_ACCESSORS_COLLECTIONS = "generate_advanced_accessors_collections";

    /**
     * The key representing the author.
     */
    String AUTHOR = "author";

    /**
     * The key representing the version.
     */
    String VERSION = "version";

    /**
     * The key representing the copyright and the license.
     */
    String COPYRIGHT_AND_LICENSE = "copyright_license";

    /**
     * The key representing the components to ignore during the generation.
     */
    String COMPONENTS_TO_IGNORE = "components_ignore";

    /**
     * The key representing the kind of component architecture.
     */
    String COMPONENTS_ARCHITECTURE = "components_architecture";

    /**
     * The key representing the bundle provider.
     */
    String BUNDLE_PROVIDER = "bundle_provider";

    /**
     * The key representing the type of the ordered unique collections.
     */
    String ORDERED_UNIQUE_TYPE = "ordered_unique";

    /**
     * The key representing the type of the ordered and not unique collections.
     */
    String ORDERED_NOT_UNIQUE_TYPE = "ordered_not_unique";

    /**
     * The key representing the type of the not ordered and unique collections.
     */
    String NOT_ORDERED_UNIQUE_TYPE = "not_ordered_unique";

    /**
     * The key representing the type of the not ordered and not unique collections.
     */
    String NOT_ORDERED_NOT_UNIQUE_TYPE = "not_ordered_not_unique";

    /**
     * The key representing if we should ignore the Java types during the generation and import.
     */
    String IGNORE_JAVA_TYPES_DURING_GENERATION_AND_IMPORT = "ignore_java_types_during_generation_and_import";

    /**
     * The key representing the types to ignore during the generation.
     */
    String TYPES_TO_IGNORE_DURING_GENERATION = "types_to_ignore_during_generation";

    /**
     * The key representing the types to ignore during the imports.
     */
    String TYPES_TO_IGNORE_DURING_IMPORTS = "types_to_ignore_during_imports";

    /**
     * The default values.
     *
     * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
     * @since 2.0
     */
    public interface Default {

        /**
         * The default name of the project where the code will be generated.
         */
        String DEFAULT_DEFAULT_PROJECT_NAME = "org.eclipse.uml.to.java";

        /**
         * The path of the default source folder.
         */
        String DEFAULT_SOURCE_FOLDER_PATH = "src/main/java";

        /**
         * The path of the default output folder.
         */
        String DEFAULT_OUTPUT_FOLDER_PATH = "target/classes";

        /**
         * The default JRE execution environment.
         */
        String DEFAULT_JRE_EXECUTION_ENVIRONMENT = "JavaSE-1.7";

        /**
         * The default components to ignore during the generation.
         */
        String DEFAULT_COMPONENTS_TO_IGNORE = "java, ";

        /**
         * The type OSGi component artifacts to generate.
         */
        String DEFAULT_COMPONENT_ARTIFACTS_TYPE_OSGI = "OSGi Bundles and Subsystems";

        /**
         * The type Eclipse component artifacts to generate.
         */
        String DEFAULT_COMPONENT_ARTIFACTS_TYPE_ECLIPSE = "Eclipse Plugins, Features and Update Sites";

        /**
         * The type Java component artifacts to generate.
         */
        String DEFAULT_COMPONENT_ARTIFACTS_TYPE_JAVA = "Java Projects";

        /**
         * The default bundle provider name.
         */
        String DEFAULT_BUNDLE_PROVIDER_NAME = "Obeo Network";

        /**
         * The default packages that should not be generated.
         */
        String DEFAULT_PACKAGES_TO_IGNORE_DURING_GENERATION = "java, ";

        /**
         * The default packages that should be ignored from the import declaration.
         */
        String DEFAULT_PACKAGES_TO_IGNORE_DURING_IMPORTS = "java.lang, ";

        /**
         * The default author.
         */
        String DEFAULT_AUTHOR = System.getProperty("user.name");

        /**
         * The default version.
         */
        String DEFAULT_VERSION = "1.0.0";

        /**
         * The default license and copyright.
         */
        String DEFAULT_COPYRIGHT_AND_LICENSE = Calendar.getInstance().get(Calendar.YEAR)
                + ", All rights reserved.";

        /**
         * The default ordered unique Java type.
         */
        String DEFAULT_ORDERED_UNIQUE = "java.util.LinkedHashSet";

        /**
         * The default ordered not unique Java type.
         */
        String DEFAULT_ORDERED_NOT_UNIQUE = "java.util.ArrayList";

        /**
         * The default not ordered unique Java type.
         */
        String DEFAULT_NOT_ORDERED_UNIQUE = "java.util.HashSet";

        /**
         * The default not ordered not unique Java type.
         */
        String DEFAULT_NOT_ORDERED_NOT_UNIQUE = "java.util.ArrayList";

        /**
         * The default value to indicate if we should generate getters and setters.
         */
        boolean DEFAULT_GENERATE_GETTERS_AND_SETTERS = true;

        /**
         * The default value to indicate if we should generate getters for collections.
         */
        boolean DEFAULT_GENERATE_GETTERS_COLLECTIONS = true;

        /**
         * The default value to indicate if we should generate setters for collections.
         */
        boolean DEFAULT_GENERATE_SETTERS_COLLECTIONS = false;

        /**
         * The default value to indicate if we should generate advanced accessors for collections.
         */
        boolean DEFAULT_GENERATE_ADVANCED_ACCESSORS_COLLECTIONS = false;

        /**
         * The default value to indicate if we should generate and import Java types.
         */
        boolean DEFAULT_IGNORE_JAVA_TYPES_DURING_GENERATION_AND_IMPORT = true;

        /**
         * The default types to ignore during the generation.
         */
        String DEFAULT_TYPES_TO_IGNORE_DURING_GENERATION = "";

        /**
         * The default types to ignore during the import declaration.
         */
        String DEFAULT_TYPES_TO_IGNORE_DURING_IMPORTS = "";
    }
}
