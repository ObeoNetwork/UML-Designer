/*******************************************************************************
 * Copyright (c) 2008 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 *    Urs Zeidler   - refactored and and added some basic methods
 *    Thomas Szadel (Atos Origin) - added Javadoc support and limit the details of the import according to the visibility.
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java;

import com.google.common.collect.Iterables;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeParameter;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.ui.JavadocContentAccess;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.umlgen.reverse.java.internal.ReversePlugin;
import org.eclipse.umlgen.reverse.java.logging.Java2UMLLogListener;
import org.eclipse.umlgen.reverse.java.logging.LogUtils;
import org.eclipse.umlgen.reverse.java.preferencesStore.Java2UMLPreferencesStoreConstants;
import org.eclipse.umlgen.reverse.java.preferencesStore.Java2UMLPreferencesStoreManager;

/**
 * The basic converter class defines a basic converting flow and provides support methods.
 */
public abstract class AbstractJava2UMLConverter {

    /** The primitive types. */
    protected static final String[] PRIMITIVE_TYPES = {
            // Java
            "boolean", "byte", "char", "double", "float", "int", "long", "short",
            // UML
            "Boolean", "Integer", "String", "UnlimitedNatural", };

    /** Name of the annotation used for Javadoc. */
    private static final String JAVA_REVERSER_DOC = "http://www.eclipse.org/umlgen/reverse/java/doc";

    /** Language. */
    private static final String BEHAVIOUR_LANGUAGE = "JAVA";

    /**
     * Enumeration for the generation of the activities.
     */
    public enum ActivityGeneration {
        /** No activity. */
        NONE,
        /** Only annotated activities. */
        ANNOTATED,
        /** All activities. */
        ALL
    }

    /** a list of model uris. */
    protected String[] importList;

    /** Resource. */
    protected Resource emfResource;

    /** Name of the model. */
    protected String modelName;

    /** Public literal object. */
    protected VisibilityKind visibility = VisibilityKind.PUBLIC_LITERAL;

    /** The java project. */
    protected IJavaProject javaProject;

    /** A HashMap of Types with a String associated. */
    protected HashMap<String, Type> typeMap = new HashMap<String, Type>();

    /** Log of the shared instance. */
    protected ILog logger = ReversePlugin.getDefault().getLog();

    /** List of port. */
    protected List<String> portList;

    /** List of state. */
    protected List<String> stateList;

    /** The package of UML Library. */
    Package umlLibrary;

    /** The package of JAVA Library. */
    Package javaLibrary;

    /**
     * Limit the details of the import by skipping all elements whose visibility is lesser than the given one.
     *
     * @param visibility
     *            The visibility.
     */
    public void setVisibility(VisibilityKind visibility) {
        this.visibility = visibility;
    }

    /**
     * Returns The max visibility to import.
     *
     * @return The visibility
     */
    public VisibilityKind getVisibility() {
        return visibility;
    }

    /**
     * set the list of uris to import.
     *
     * @param importList
     *            list imported
     */
    public void setImportList(String[] importList) {
        this.importList = importList;

    }

    /**
     * test if a primitive type.
     *
     * @param name
     *            of the types
     * @return true if is a primitive types
     */
    public static boolean isPrimitiveType(String name) {

        for (String type : PRIMITIVE_TYPES) {
            if (type.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Convert an Package or a Java project into a UML2 model.
     *
     * @param javaElement
     *            the JavaElement
     * @param actGen
     * @return The UML2 model or <code>null</code> if the IJavaElement is not a Package or a Java Project.
     * @throws CoreException
     *             , if the resource is null or some critical error occur while importing.
     */
    private Package convert(IJavaElement javaElement, ActivityGeneration actGen) throws CoreException {
        String path = null;
        IResource res = javaElement.getResource();
        if (res != null) {
            path = res.getLocation().toOSString();
        } else {
            path = javaElement.getPath().toString();
        }
        File dir = new File(path);
        Java2UMLLogListener listener = new Java2UMLLogListener(dir, modelName);
        logger.addLogListener(listener);
        LogUtils.resetTabbing();

        if (emfResource == null) {
            throwCoreException("The resource can't be null.");
        }

        switch (javaElement.getElementType()) {
            case IJavaElement.JAVA_PROJECT:
                javaProject = (IJavaProject)javaElement;
                return doConvertion((IJavaProject)javaElement, actGen);
            case IJavaElement.PACKAGE_FRAGMENT:
                javaProject = ((IPackageFragment)javaElement).getJavaProject();
                return doConvertion((IPackageFragment)javaElement, actGen);
            case IJavaElement.PACKAGE_FRAGMENT_ROOT:
                javaProject = ((IPackageFragmentRoot)javaElement).getJavaProject();
                return doConvertion((IPackageFragmentRoot)javaElement, actGen);
            default:
                return null;
        }
    }

    /**
     * Do the convertion.
     *
     * @param packageModel
     *            the package model
     * @param actGen
     * @return The UML2 model
     */
    private Package doConvertion(IPackageFragmentRoot packageModel, ActivityGeneration actGen) {
        // Let's try to find the package before creating a new one
        Model model = null;
        for (EObject eObject : emfResource.getContents()) {
            if (eObject instanceof Model) {
                if (((Model)eObject).getName() == null) {
                    if ("".equals(modelName) || modelName == null) {
                        // Found !!
                        model = (Model)eObject;
                    }
                } else if (((Model)eObject).getName().equals(modelName)) {
                    // Found !!
                    model = (Model)eObject;
                }
            }
        }

        if (model == null) {
            model = UMLFactory.eINSTANCE.createModel();
            model.setName(modelName);
            emfResource.getContents().add(model);
            LogUtils.logCreation(null, packageModel, model, null);
        }
        LogUtils.logMessage("First conversion pass");

        List<IJavaElement> fragments;
        try {
            fragments = Arrays.asList(((IPackageFragmentRoot)packageModel).getChildren());
            for (IPackageFragment f : Iterables.filter(fragments, IPackageFragment.class)) {
                convert(model, f, actGen);
            }
        } catch (JavaModelException e) {
            e.printStackTrace();
            LogUtils.logThrowable(e);
        } catch (CoreException e) {
            e.printStackTrace();
            LogUtils.logThrowable(e);
        }

        LogUtils.logMessage("Second pass : generating package usages");
        // Generic post-process : generate package usages
        generatePackageUsages(model);
        return model;

    }

    /**
     * Convert an Package or a Java project into a UML2 profile.
     *
     * @param javaElement
     *            the JavaElement
     * @param actGen
     * @return The UML2 model or <code>null</code> if the IJavaElement is not a Package or a Java Project.
     * @throws CoreException
     */
    protected Package convert2Profile(IJavaElement javaElement, ActivityGeneration actGen)
            throws CoreException {
        return convert(javaElement, actGen);
    }

    /**
     * Convert a Java Package Fragment into a UML2 PAckage and update the given UML2 Model.
     *
     * @param model
     *            The UML2 model where the Package must be inserted
     * @param fragment
     *            the Package Fragment
     * @param actGen
     * @return The updated UML2 model.
     * @throws CoreException
     */
    public Model convert(Model model, IPackageFragment fragment, ActivityGeneration actGen)
            throws CoreException {
        LogUtils.logEntering(fragment, null);
        initializeModel(model);

        Package packageObject = findOrCreatePackage(model, fragment);
        LogUtils.logEntering(packageObject, null);

        createTypes(packageObject, fragment);
        initializeTypes(typeMap, packageObject, fragment);
        createAssociations(packageObject);

        if (actGen == ActivityGeneration.ALL || actGen == ActivityGeneration.ANNOTATED) {
            LogUtils.logMessage("Generic post-process : Creating activities");
            generateActivities(packageObject, fragment, actGen == ActivityGeneration.ANNOTATED);
        }
        LogUtils.logExiting();
        LogUtils.logExiting();
        return model;
    }

    /**
     * Generates activity model.
     *
     * @param packageObject
     * @param fragment
     * @param isAnnotatedOnly
     */
    protected void generateActivities(Package packageObject, IPackageFragment fragment,
            boolean isAnnotatedOnly) {
        try {
            for (IJavaElement javaElement : fragment.getChildren()) {
                if (javaElement instanceof org.eclipse.jdt.internal.core.CompilationUnit) {
                    LogUtils.logEntering(javaElement, "Creating activities");
                    org.eclipse.jdt.internal.core.CompilationUnit unit = (org.eclipse.jdt.internal.core.CompilationUnit)javaElement;
                    ASTParser parser = ASTParser.newParser(AST.JLS3); // handles JDK 1.0, 1.1, 1.2, 1.3, 1.4,
                    // 1.5, 1.6
                    parser.setSource(unit);
                    // In order to parse 1.5 code, some compiler options need to be set to 1.5
                    Map options = JavaCore.getOptions();
                    JavaCore.setComplianceOptions(JavaCore.VERSION_1_5, options);
                    parser.setCompilerOptions(options);
                    CompilationUnit result = (CompilationUnit)parser.createAST(null);

                    result.accept(new JavaReverseCUVisitor(packageObject, isAnnotatedOnly, null));
                    LogUtils.logExiting();
                }
            }
        } catch (JavaModelException e) {
            e.printStackTrace();
            LogUtils.logThrowable(e);
            LogUtils.logExiting();
        }
    }

    /**
     * Initialize all types.
     *
     * @param allTypes
     *            a HashMap oh all types
     * @param packageObject
     * @param fragment
     *            a fragment of the Package
     * @throws JavaModelException
     */
    protected abstract void initializeTypes(HashMap<String, Type> allTypes, Package packageObject,
            IPackageFragment fragment) throws JavaModelException;

    /**
     * process all types.
     *
     * @param packageObject
     * @param types
     * @throws JavaModelException
     * @return a HashMap of Types with the String associated
     */
    protected abstract HashMap<String, Type> processTypes(Namespace packageObject, IType[] types)
            throws JavaModelException;

    /**
     * creates the root package.
     *
     * @return a package
     */
    protected abstract Package findOrCreateRootPackage();

    /**
     * Creates the necessary resources to process the import, like creating of imports or loading the UML
     * metamodel.
     *
     * @param model
     *            a package model
     * @throws CoreException
     */
    protected abstract void initializeModel(Package model) throws CoreException;

    /**
     * Convert a Java project into a UML2 model.
     *
     * @param project
     *            the Java Project
     * @param actGen
     * @return The UML2 model.
     * @throws CoreException
     */
    private Package doConvertion(IJavaProject project, ActivityGeneration actGen) throws CoreException {
        LogUtils.logEntering(project, null);
        Package model = findOrCreateRootPackage();

        initializeModel(model);

        LogUtils.logMessage("Creating types ");
        IPackageFragment[] children = project.getPackageFragments();
        for (IPackageFragment fragment : children) {
            if (fragment.getCompilationUnits().length > 0) {
                LogUtils.logEntering(fragment, null);
                Package packageObject = findOrCreatePackage(model, fragment);
                if (packageObject != null) {
                    createTypes(packageObject, fragment);
                }
                LogUtils.logExiting();
            }
        }

        LogUtils.logMessage("Initializing types");
        for (IPackageFragment fragment : children) {
            if (fragment.getCompilationUnits().length > 0) {
                LogUtils.logEntering(fragment, null);
                Package packageObject = findOrCreatePackage(model, fragment);
                if (packageObject != null) {
                    initializeTypes(typeMap, packageObject, fragment);
                }
                LogUtils.logExiting();
            }
        }

        LogUtils.logMessage("Generic post-process : Creating usages and associations");
        for (IPackageFragment fragment : children) {
            if (fragment.getCompilationUnits().length > 0) {
                LogUtils.logEntering(fragment, null);
                Package packageObject = findOrCreatePackage(model, fragment);
                // createUsages to move as an action on the UML model
                if (!avoidDependenciesComputing() && packageObject != null) {
                    createUsages(packageObject);
                }
                if (packageObject != null) {
                    createAssociations(packageObject);
                }
                LogUtils.logExiting();
            }
        }

        LogUtils.logMessage("Generic post-process : Creating activities");
        if (actGen == ActivityGeneration.ALL || actGen == ActivityGeneration.ANNOTATED) {
            for (IPackageFragment fragment : children) {
                if (fragment.getCompilationUnits().length > 0) {
                    LogUtils.logEntering(fragment, null);
                    Package packageObject = findOrCreatePackage(model, fragment);
                    if (packageObject != null) {
                        generateActivities(packageObject, fragment, actGen == ActivityGeneration.ANNOTATED);
                    }
                    LogUtils.logExiting();
                }
            }
        }
        return model;
    }

    /**
     * Convert a Java project into a UML2 model.
     *
     * @param packageModel
     *            the Java Package
     * @param actGen
     * @return The UML2 model.
     * @throws CoreException
     */
    private Model doConvertion(IPackageFragment packageModel, ActivityGeneration actGen) throws CoreException {
        // Let's try to find the package before creating a new one
        Model model = null;
        for (EObject eObject : emfResource.getContents()) {
            if (eObject instanceof Model) {
                if (((Model)eObject).getName() == null) {
                    if (modelName == null || "".equals(modelName)) {
                        // Found !!
                        model = (Model)eObject;
                    }
                } else if (((Model)eObject).getName().equals(modelName)) {
                    // Found !!
                    model = (Model)eObject;
                }

            }
        }

        if (model == null) {
            model = UMLFactory.eINSTANCE.createModel();
            model.setName(modelName);
            emfResource.getContents().add(model);
            LogUtils.logCreation(null, packageModel, model, null);
        }
        LogUtils.logMessage("First conversion pass");
        convert(model, packageModel, actGen);

        LogUtils.logMessage("Second pass : generating package usages");
        // Generic post-process : generate package usages
        generatePackageUsages(model);
        return model;
    }

    /**
     * Generate package Usages.
     *
     * @param umlPack
     *            the UML package
     */
    private void generatePackageUsages(Package umlPack) {
        createUsages(umlPack);
        for (EObject obj : umlPack.getPackagedElements()) {
            if (obj instanceof Package) {
                generatePackageUsages((Package)obj);
            }
        }
    }

    /**
     * Resolve a type in the context of another type using imports and inheritance.
     *
     * @param element
     *            : the element from which we get the Model
     * @param type
     *            : the enclosing type
     * @param fullyQualifiedName
     *            : the name of the type we want to resolve
     * @return : the resolved type fully qualified name
     */
    protected String resolveTypeInContext(Element element, IType type, String fullyQualifiedName) {
        if (Java2UMLConverter.isPrimitiveType(fullyQualifiedName)) {
            return fullyQualifiedName;
        }
        String[][] ancestorType;
        try {
            ancestorType = type.resolveType(fullyQualifiedName);
            if (ancestorType != null && ancestorType[0] != null) {
                if (ancestorType[0][0] == null || ancestorType[0][0].length() == 0) {
                    return ancestorType[0][1];
                } else {
                    return ancestorType[0][0].concat(".").concat(ancestorType[0][1]);
                }
            }
            if (type.getParent().getElementType() == IJavaElement.TYPE) {
                IType parentType = (IType)type.getParent();
                ancestorType = parentType.resolveType(fullyQualifiedName);
                if (ancestorType != null && ancestorType[0] != null) {
                    if (ancestorType[0][0] == null) {
                        return ancestorType[0][1];
                    } else {
                        return ancestorType[0][0].concat(".").concat(ancestorType[0][1]);
                    }
                }
            }
        } catch (JavaModelException e) {
            e.printStackTrace();
            LogUtils.logThrowable(e);
        }
        if (type.getCompilationUnit() != null) {
            try {
                IImportDeclaration[] allImports = type.getCompilationUnit().getImports();
                for (IImportDeclaration importDeclaration : allImports) {
                    if (importDeclaration.getElementName().endsWith("*")) {
                        String importPackage = importDeclaration.getElementName().substring(0,
                                importDeclaration.getElementName().lastIndexOf("."));
                        String invisibleImport = findPossibleExistingType(element, importPackage,
                                fullyQualifiedName);
                        if (invisibleImport != null) {
                            return importPackage.concat(".").concat(invisibleImport);
                        }
                    }
                    if (importDeclaration.getElementName().endsWith(fullyQualifiedName)) {
                        return importDeclaration.getElementName();
                    }
                }
            } catch (JavaModelException e) {
                e.printStackTrace();
                LogUtils.logThrowable(e);
            }

        }
        return fullyQualifiedName;
    }

    /**
     * Explore an Import Package in the model to find a Type possibly created.
     *
     * @param element
     *            : the element
     * @param packageName
     *            : the name of the package we want to explore
     * @param typeName
     *            : the name of the type
     * @return : the type name if the type is found
     */
    private String findPossibleExistingType(Element element, String packageName, String typeName) {
        StringTokenizer tokenizer = new StringTokenizer(packageName, ".");

        Package parent = element.getModel();
        Package packageObject = null;

        int tokensCount = tokenizer.countTokens();

        for (int i = 0; i < tokensCount && parent != null; i++) {
            String pathElementName = tokenizer.nextToken();
            packageObject = ((Package)parent).getNestedPackage(pathElementName, false,
                    UMLPackage.Literals.PACKAGE, false);
            parent = packageObject;
        }
        if (parent != null && parent.getOwnedType(typeName, false, UMLPackage.Literals.TYPE, false) != null) {
            return typeName;
        }
        return null;
    }

    /**
     * finds or create a package in the model for the fragment.
     *
     * @param model
     *            : the package model
     * @param fragment
     * @return Package
     */
    protected Package findOrCreatePackage(Package model, IPackageFragment fragment) {
        String qualifiedName = fragment.getElementName();
        return findOrCreatePackage(model, qualifiedName);
    }

    /**
     * finds or create a package in the model from the qualifed package name.
     *
     * @param model
     *            : the model package
     * @param packageQName
     *            the qualified name of the package
     * @return packageObject
     */
    protected Package findOrCreatePackage(Package model, String packageQName) {
        // Creates recursively the hierarchy of packages
        Package packageObject = findPackageInModel(model, packageQName);

        // search in the import package
        if (packageObject == null && model != null) {
            for (Package importedPackage : model.getImportedPackages()) {
                packageObject = findPackageInModel(importedPackage, packageQName);
                if (packageObject != null) {
                    return packageObject;
                }
            }
        }
        // if the package is not found, create it
        if (packageObject == null && model != null) {
            packageObject = createPackage(model, packageQName);
            LogUtils.logCreation(null, null, packageObject, null);
        }
        return packageObject;
    }

    /**
     * finds a package in the model from the qualifed package name.
     *
     * @param model
     *            : the model package
     * @param packageQName
     *            the qualified name of the package
     * @return packageObject
     */
    protected Package findPackageInModel(Package model, String packageQName) {
        // Creates recursively the hierarchy of packages
        StringTokenizer tokenizer = new StringTokenizer(packageQName, ".");
        Package parent = model;
        Package packageObject = model;

        // search the package if not found, search in the imported package, if not found, create it
        while (tokenizer.hasMoreTokens() && packageObject != null) {
            String packageName = tokenizer.nextToken();
            packageObject = parent.getNestedPackage(packageName);
            parent = packageObject;
        }
        return packageObject;
    }

    /**
     * create a package in the model from the qualifed package name.
     *
     * @param model
     *            : the model package
     * @param packageQName
     *            the qualified name of the package
     * @return packageObject
     */
    protected Package createPackage(Package model, String packageQName) {
        // Creates recursively the hierarchy of packages
        StringTokenizer tokenizer = new StringTokenizer(packageQName, ".");
        Package parent = model;
        Package packageObject = model;

        // search the package if not found, search in the imported package, if not found, create it
        while (tokenizer.hasMoreTokens()) {
            String packageName = tokenizer.nextToken();
            packageObject = parent.getNestedPackage(packageName);
            if (packageObject == null) {
                packageObject = parent.createNestedPackage(packageName);
                LogUtils.logCreation(null, packageName, packageObject,
                        " Created from AbstractJava2UMLConverter");
            }
            parent = packageObject;
        }
        return packageObject;
    }

    /**
     * create a data type (intermediare package will be created).
     *
     * @param model
     *            : the model package the target model
     * @param qualifiedName
     *            the qualifed name of the type to retrieve
     * @return type
     */
    protected Type createDataType(Package model, String qualifiedName) {
        Type type = model.getOwnedType(qualifiedName, false, UMLPackage.Literals.TYPE, false);

        if (isPrimitiveType(qualifiedName)) {
            if (type == null && umlLibrary != null) {
                type = umlLibrary.getOwnedType(qualifiedName);
            }
            if (type == null && javaLibrary != null) {
                type = javaLibrary.getOwnedType(qualifiedName);
            }
        }
        if (type != null) {
            return type;
        }

        StringTokenizer tokenizer = new StringTokenizer(qualifiedName, ".");
        Package parent = model;
        Package packageObject = model;
        Classifier classifier = null;
        int tokensCount = tokenizer.countTokens();

        for (int i = 0; i < tokensCount - 1; i++) {
            String packageName = tokenizer.nextToken();
            // examine for the last token if it is a Classifier (that include the future type)
            if (i == tokensCount - 2) {
                classifier = (Classifier)parent.getMember(packageName, false, UMLPackage.Literals.CLASSIFIER);
            }
            if (classifier == null) {
                packageObject = ((Package)parent).getNestedPackage(packageName, false,
                        UMLPackage.Literals.PACKAGE, true);
            }
            parent = packageObject;
        }

        String dataTypeName = qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1, qualifiedName
                .length());

        // Imported types
        if (importList != null) {
            for (String imp : importList) {
                try {
                    Package lib = (Package)load(URI.createURI(imp));

                    if (lib instanceof Model) {
                        type = lib.getOwnedType(qualifiedName);
                        if (type != null) {
                            model.createElementImport(type);
                            return type;
                        }

                        if (lib.getOwnedMember(dataTypeName) instanceof Type) {
                            return (Type)lib.getOwnedMember(dataTypeName);
                        }
                        List<Package> liste = lib.getNestedPackages();
                        for (PackageableElement pack : liste) {
                            type = ((Package)pack).getOwnedType(dataTypeName);
                            if (type != null) {
                                if (model.getElementImport(type) != null) {
                                    model.createElementImport(type);
                                }
                                return type;
                            }
                        }
                    }
                } catch (CoreException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    LogUtils.logThrowable(e);
                }
            }
        }
        if (type == null) {
            type = (DataType)parent.getOwnedType(dataTypeName, false, UMLPackage.Literals.DATA_TYPE, true);
        }
        return type;
    }

    /**
     * Find Type in the target model.
     *
     * @param model
     *            : the target model
     * @param qualifiedName
     *            : the qualifed name of the type to retrieve
     * @return type
     */
    protected Type findTypeInModel(Package model, String qualifiedName) {
        Type type = model.getOwnedType(qualifiedName, false, UMLPackage.Literals.TYPE, false);
        if (type != null) {
            return type;
        }
        StringTokenizer tokenizer = new StringTokenizer(qualifiedName, ".");
        Package parent = model;
        Package packageObject = model;
        Classifier classifier = null;
        int tokensCount = tokenizer.countTokens();

        for (int i = 0; i < tokensCount - 1; i++) {
            String packageName = tokenizer.nextToken();
            // examine for the last token if it is a Classifier (that include the future type)
            if (i == tokensCount - 2) {
                classifier = (Classifier)parent.getMember(packageName, false, UMLPackage.Literals.CLASSIFIER);
            }
            if (classifier == null) {
                packageObject = ((Package)parent).getNestedPackage(packageName, false,
                        UMLPackage.Literals.PACKAGE, false);
                if (packageObject == null) {
                    return null;
                }
            }
            parent = packageObject;
        }

        String dataTypeName = qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1, qualifiedName
                .length());
        if (classifier != null) {
            type = (Type)classifier.getOwnedMember(dataTypeName, false, UMLPackage.Literals.TYPE);
        } else {
            type = parent.getOwnedType(dataTypeName, false, UMLPackage.Literals.CLASSIFIER, false);
        }
        return type;
    }

    /**
     * Loads an uml model from the uri.
     *
     * @param uri
     * @return pPackage
     * @throws CoreException
     *             : Error with the package.
     */
    protected org.eclipse.uml2.uml.Package loadUML2Model(URI uri) throws CoreException {

        ResourceSet resourceSet = new ResourceSetImpl();
        org.eclipse.uml2.uml.Package pPackage = null;

        try {
            Resource resource = resourceSet.getResource(uri, true);
            pPackage = (org.eclipse.uml2.uml.Package)EcoreUtil.getObjectByType(resource.getContents(),
                    UMLPackage.eINSTANCE.getPackage());
        } catch (WrappedException we) {
            LogUtils.logThrowable(we);
            throwCoreException(we.getMessage());
        }
        return pPackage;
    }

    /**
     * Checks for qualified name.
     *
     * @param name
     * @return boolean
     */
    protected boolean isQualifiedName(String name) {
        return name.indexOf(".") != -1;
    }

    /**
     * A lookup for the root package.
     *
     * @param packageObject
     * @return parentPackage
     */
    protected Package findRootPackage(Package packageObject) {
        Package parentPackage = (Package)packageObject.getOwner();
        // it is the root package so we return it
        if (parentPackage == null) {
            return packageObject;
        }

        while (parentPackage.getOwner() != null) {
            parentPackage = (Package)parentPackage.getOwner();
        }

        return parentPackage;
    }

    /**
     * Checks the model imports for a type.
     *
     * @param model
     *            : the model package
     * @param typeName
     *            : the name of the type
     * @return Type
     */
    protected Type findinImportPackages(Package model, String typeName) {

        String find = " findinImportPackages ";

        if ("void".equals(typeName) || model == null) {
            LogUtils.logCreation(null, model, typeName, find + typeName);
            return null;
        }

        PackageableElement importedMember = model.getImportedMember(typeName);
        if (importedMember instanceof Type) {
            LogUtils.logCreation(null, model, importedMember, find + typeName);
            return (Type)importedMember;
        }

        for (Package importedPackage : model.getImportedPackages()) {
            Type type = findTypeInModel(importedPackage, typeName);

            if (type != null) {
                LogUtils.logCreation(null, model, type, find + typeName);
                return type;
            }
            for (Package pack : importedPackage.getNestedPackages()) {
                NamedElement elt = pack.getOwnedMember(typeName);
                if (elt instanceof Type) {
                    LogUtils.logCreation(null, model, elt, find + typeName);
                    return (Type)elt;
                }
            }
        }
        LogUtils.logCreation(null, model, null, find + typeName);
        return null;
    }

    /**
     * Finds a type or creates it.
     *
     * @param packageObject
     * @param typeName
     *            : the name of the type
     * @return type
     */
    protected Type findOrCreateType(Package packageObject, String typeName) {

        // first check: Look for the type in the typeMap map (all the types created during the type creation
        // process)
        Type type = typeMap.get(typeName);

        // second check: retrieve the type in the target model
        if (type == null && packageObject.getModel() != null) {
            type = findTypeInModel(packageObject.getModel(), typeName);
        }

        // third check: retrieve type in imported target model
        if (type == null) {
            type = findinImportPackages(packageObject.getModel(), typeName);
        }

        // fourth check: retrieve type as generic type
        if (type == null) {
            type = findGenericType(packageObject.getModel(), typeName);
        }

        // the type is not found, create a data type in the correct package (intermediate packages will be
        // created)
        if (typeName != null && !"void".equals(typeName)) {
            if (isPrimitiveType(typeName)) {
                if (type == null && umlLibrary != null) {
                    type = umlLibrary.getOwnedType(typeName);
                }
                if (type == null && javaLibrary != null) {
                    type = javaLibrary.getOwnedType(typeName);
                }
            }
            if (type == null) {
                type = createNotFoundDataType(packageObject.getModel(), typeName);
            }
        }
        return type;
    }

    /**
     * Create not found classes as DataType (separate method in order to be able to change strategy : for
     * instance look inside imported models, or filter some element).
     *
     * @param packageObject
     * @param typeName
     *            a name of type
     * @return type
     */
    protected Type createNotFoundDataType(Package packageObject, String typeName) {
        Type type = createDataType(packageObject.getModel(), typeName);
        return type;
    }

    /**
     * Return a newElement or the old one if the new one is also a DataType.
     *
     * @param existing
     *            DataType that was created because we need to type properties before reversing the Classifier
     *            or Interface
     * @param newElement
     *            : the element targeted by all properties or other that need it
     * @return the newElement or the old one if the new one is also a DataType.
     */
    public Type muteDataType(Type existing, Type newElement) {
        if (newElement instanceof DataType) {
            return (DataType)existing;
        }
        ECrossReferenceAdapter crossAdpater = CacheAdapter.getCrossReferenceAdapter(existing);
        if (crossAdpater != null) {
            for (Setting aSetting : crossAdpater.getInverseReferences(existing, true)) {
                EObject eObject = aSetting.getEObject();
                if (aSetting.getEStructuralFeature() instanceof EReference) {
                    EReference ref = (EReference)aSetting.getEStructuralFeature();
                    if (!ref.isContainment() && !ref.isUnsettable() && ref.isChangeable()) {
                        if (ref.isMany() && !ref.isDerived()) {
                            EList<EObject> candidates = new BasicEList<EObject>((EList<EObject>)eObject.eGet(
                                    aSetting.getEStructuralFeature(), true));
                            if (candidates.contains(existing)) {
                                candidates.set(candidates.indexOf(existing), newElement);
                                eObject.eSet(ref, candidates);
                            }
                        } else if (eObject.eGet(aSetting.getEStructuralFeature(), true).equals(existing)) {
                            eObject.eSet(aSetting.getEStructuralFeature(), newElement);
                        }
                    }
                } else if (aSetting.getEStructuralFeature() instanceof EAttribute) {
                    EAttribute ref = (EAttribute)aSetting.getEStructuralFeature();
                    if (eObject.eGet(ref, true).equals(existing)) {
                        if (!ref.isUnsettable() && ref.isChangeable()) {
                            eObject.eSet(aSetting.getEStructuralFeature(), newElement);
                        }
                    }
                }
            }
        }
        return newElement;
    }

    /**
     * looks for a generic type.
     *
     * @param packageObject
     * @param interfaceName
     *            the name of the interface
     * @return Type or null
     */
    protected Type findGenericType(Package packageObject, String interfaceName) {
        int indexOf = interfaceName.indexOf('<');
        if (indexOf > 0) {
            String name = interfaceName.substring(0, indexOf);
            return findTypeInModel(packageObject, name);
        }
        return null;
    }

    /**
     * Creates the associations for the types in the package.
     *
     * @param packageObject
     * @throws JavaModelException
     */
    protected void createAssociations(Package packageObject) throws JavaModelException {
        List<PackageableElement> workingCopy = new ArrayList<PackageableElement>(packageObject
                .getPackagedElements());
        for (PackageableElement element : workingCopy) {
            if (element instanceof Interface) {
                LogUtils.logEntering(element, "Creating associations");
                Interface interfaceObject = (Interface)element;
                Iterator<Property> itProp = interfaceObject.getOwnedAttributes().iterator();
                while (itProp.hasNext()) {
                    Property property = itProp.next();
                    Type targetType = property.getType();
                    if (targetType instanceof StructuredClassifier || targetType instanceof Interface) {
                        if (!(targetType.getOwner() instanceof TemplateParameter)) {
                            createAssociation(property);
                        }
                    }
                }
                LogUtils.logExiting();
            }
            if (element instanceof StructuredClassifier) {
                LogUtils.logEntering(element, "Creating associations");
                StructuredClassifier classifierObject = (StructuredClassifier)element;
                for (Property property : classifierObject.getOwnedAttributes()) {
                    Type targetType = property.getType();
                    if (targetType instanceof StructuredClassifier || targetType instanceof Interface) {
                        if (!(targetType.getOwner() instanceof TemplateParameter)) {
                            createAssociation(property);
                        }
                    }
                }
                LogUtils.logExiting();
            }
        }
    }

    /**
     * Creates an association between the owner of the property and the type.
     *
     * @param property
     */
    protected void createAssociation(Property property) {
        if (property.getAssociation() == null) {
            Property opposite = foundOpposite(property);
            Element propOwner = property.getOwner();
            Association association = UMLFactory.eINSTANCE.createAssociation();
            association.getMemberEnds().add(property);
            if (opposite != null) {
                association.getMemberEnds().add(opposite);
                association.setName(property.getName() + "_" + opposite.getName());
            } else {
                Property target = association.createOwnedEnd("target", (Type)propOwner);
                association.setName(property.getName() + "_" + target.getName());
            }
            property.getNearestPackage().getPackagedElements().add(association);
            LogUtils.logCreation(null, null, property, null);
        }
    }

    /**
     * Found the opposite of the property.
     *
     * @param source
     *            the source property
     * @return target or null
     */
    protected Property foundOpposite(Property source) {
        Property target = null;

        Type propType = source.getType();
        Element propOwner = source.getOwner();
        if (propType instanceof StructuredClassifier || propType instanceof Interface) {
            // Search if the property type has a property of the type of
            // propOwner
            List<Property> properties = null;
            if (propType instanceof StructuredClassifier) {
                properties = ((StructuredClassifier)propType).getOwnedAttributes();
            }
            if (propType instanceof Interface) {
                properties = ((Interface)propType).getOwnedAttributes();
            }

            Iterator<Property> it = properties.iterator();
            while (it.hasNext() && target == null) {
                Property prop = it.next();
                // Check that is a different property
                if (prop != source && propOwner.equals(prop.getType())) {
                    target = prop;
                }
            }
        }

        return target;
    }

    /**
     * Creates the types of the fragment in the package.
     *
     * @param packageObject
     * @param fragment
     * @throws JavaModelException
     */
    protected void createTypes(Package packageObject, IPackageFragment fragment) throws JavaModelException {
        for (IJavaElement element : fragment.getChildren()) {
            if (element instanceof ICompilationUnit) {
                LogUtils.logEntering(element, "Creating types");
                typeMap.putAll(processTypes(packageObject, ((ICompilationUnit)element).getTypes()));
                LogUtils.logExiting();
            } else if (element instanceof IClassFile) {
                LogUtils.logEntering(element, "Creating types");
                IClassFile cf = (IClassFile)element;
                typeMap.putAll(processTypes(packageObject, new IType[] {cf.getType() }));
                LogUtils.logExiting();
            }
        }
    }

    /**
     * Creates a type (class, enumeration, interface) in the package.
     *
     * @param packageObject
     * @param type
     * @return a Classifier
     * @throws JavaModelException
     */
    protected Classifier createTypeInPackage(Namespace packageObject, IType type) throws JavaModelException {
        if (type.isInterface()) {
            Interface interfaceObject = UMLFactory.eINSTANCE.createInterface();
            if (type.isAnnotation()) {
                interfaceObject.setName("@" + type.getElementName());
            } else {
                interfaceObject.setName(type.getElementName());
            }
            LogUtils.logCreation(null, type, interfaceObject, null);
            createTemplateParameters(type, interfaceObject);
            update(interfaceObject, type.getFlags());
            addToContainment(packageObject, interfaceObject);
            return interfaceObject;

        }
        if (isEnumeration(type)) {
            Enumeration enumeration = UMLFactory.eINSTANCE.createEnumeration();
            enumeration.setName(type.getElementName());
            LogUtils.logCreation(null, type, enumeration, null);
            update(enumeration, type.getFlags());
            addToContainment(packageObject, enumeration);
            return enumeration;
        }

        Class classObject = UMLFactory.eINSTANCE.createClass();
        classObject.setName(type.getElementName());
        LogUtils.logCreation(null, type, classObject, null);
        createTemplateParameters(type, classObject);
        update(classObject, type.getFlags());
        addToContainment(packageObject, classObject);
        return classObject;

    }

    /**
     * Test if the type is an enumeration.
     *
     * @param type
     * @return true if the type is an enumeration
     * @throws JavaModelException
     */
    protected boolean isEnumeration(IType type) throws JavaModelException {
        // java enumeration
        if (type.isEnum()) {
            return true;
        }
        // Old style translation for enumeration : no methods, only public static final fields
        if (type.isClass() && type.getMethods().length == 0) {
            for (IField enum0 : type.getFields()) {
                if (!Flags.isPublic(enum0.getFlags())) {
                    return false;
                }
                if (!Flags.isStatic(enum0.getFlags())) {
                    return false;
                }
                if (!Flags.isFinal(enum0.getFlags())) {
                    return false;
                }
            }
            return true;
        }
        // Otherwise
        return false;
    }

    /**
     * Creates a template parameter for a classifier (a generic).
     *
     * @param type
     * @param classObject
     * @throws JavaModelException
     */
    protected void createTemplateParameters(IType type, Classifier classObject) throws JavaModelException {
        LogUtils.logEntering(type, "Creating template parameters");
        ITypeParameter[] typeParameters = type.getTypeParameters();
        if (typeParameters.length > 0) {
            TemplateSignature templateSignature = classObject.createOwnedTemplateSignature();

            for (ITypeParameter typeParameter : typeParameters) {
                ClassifierTemplateParameter classifierTemplateParameter = UMLFactory.eINSTANCE
                        .createClassifierTemplateParameter();
                templateSignature.getOwnedParameters().add(classifierTemplateParameter);

                ParameterableElement parameteredElement = classifierTemplateParameter
                        .createOwnedParameteredElement(UMLPackage.Literals.CLASS);
                if (parameteredElement instanceof Class) {
                    Class clazz = (Class)parameteredElement;
                    clazz.setName(typeParameter.getElementName());
                } // end of if (parameteredElement instanceof Class)
                LogUtils.logCreation(type, typeParameter, parameteredElement, null);
            }
        }
        LogUtils.logExiting();
    }

    /**
     * Adds a classifier to it containment feature.
     *
     * @param packageObject
     * @param classifierObject
     */
    protected void addToContainment(Namespace packageObject, Classifier classifierObject) {
        if (packageObject instanceof Package) {
            Package po = (Package)packageObject;
            po.getPackagedElements().add(classifierObject);
            return;
        } // end of if (packageObject instanceof Package)
        if (packageObject instanceof Class) {
            Class clazz = (Class)packageObject;
            clazz.getNestedClassifiers().add(classifierObject);
            return;
        } // end of if (packageObject instanceof Class)
        if (packageObject instanceof Interface) {
            Interface i = (Interface)packageObject;
            i.getNestedClassifiers().add(classifierObject);
            return;
        } // end of if (packageObject instanceof Class)

    }

    /**
     * Looks up a template parameter in an element.
     *
     * @param element
     * @param fieldName
     *            the name of the field
     * @return classifier or null
     */
    protected Type findTemplateParameter(Element element, String fieldName) {
        for (Element child : element.allOwnedElements()) {
            if (child instanceof Classifier) {
                Classifier classifier = (Classifier)child;
                if (classifier.getName().equals(fieldName)) {
                    return classifier;
                }
            } else if (child instanceof Operation) {
                Operation operation = (Operation)child;
            }
        }
        return null;
    }

    /**
     * Update the visibility.
     *
     * @param element
     * @param flags
     * @throws JavaModelException
     */
    protected void update(NamedElement element, int flags) throws JavaModelException {
        if (Flags.isAbstract(flags)) {
            if (element instanceof Classifier) {
                Classifier cl = (Classifier)element;
                cl.setIsAbstract(true);
            } else if (element instanceof BehavioralFeature) {
                BehavioralFeature bf = (BehavioralFeature)element;
                bf.setIsAbstract(true);
            }
        }

        if (Flags.isStatic(flags)) {
            if (element instanceof BehavioralFeature) {
                BehavioralFeature bf = (BehavioralFeature)element;
                bf.setIsStatic(true);
            } else if (element instanceof Feature) {
                Feature feature = (Feature)element;
                feature.setIsStatic(true);
            }
        }

        if (Flags.isFinal(flags)) {
            if (element instanceof BehavioralFeature) {
                BehavioralFeature bf = (BehavioralFeature)element;
                bf.setIsLeaf(true);
            } else if (element instanceof Feature) {
                Feature feature = (Feature)element;
                feature.setIsLeaf(true);
            }
        }

        if (Flags.isPrivate(flags)) {
            element.setVisibility(VisibilityKind.PRIVATE_LITERAL);
        } else if (Flags.isProtected(flags)) {
            element.setVisibility(VisibilityKind.PROTECTED_LITERAL);
        } else if (Flags.isPublic(flags)) {
            element.setVisibility(VisibilityKind.PUBLIC_LITERAL);
        } else if (Flags.isPackageDefault(flags)) {
            if (element instanceof Property) {
                Property property = (Property)element;
                if (property.getOwner() instanceof Interface) {
                    Interface i = (Interface)property.getOwner();
                    property.setVisibility(i.getVisibility());
                }
            } else if (element instanceof Operation) {
                Operation op = (Operation)element;
                if (op.getOwner() instanceof Interface) {
                    Interface i = (Interface)op.getOwner();
                    op.setVisibility(i.getVisibility());
                }
            } else {
                element.setVisibility(VisibilityKind.PACKAGE_LITERAL);
            }
        }
    }

    /**
     * Returns the simple name of a fragment.
     *
     * @param fragment
     * @return name
     */
    protected String getName(IPackageFragment fragment) {
        String name = "";
        String fullName = fragment.getElementName();
        int dotIndex = fullName.lastIndexOf('.');
        if (dotIndex > 0) {
            name = fullName.substring(dotIndex + 1);
        }

        return name;
    }

    /**
     * Check whether the member can be imported or not according to its visibility.
     *
     * @param member
     *            The member.
     * @return True if it can, false otherwise.
     * @throws JavaModelException
     */
    protected boolean canBeImported(IMember member) throws JavaModelException {
        int flag = member.getFlags();
        switch (visibility) {
            case PRIVATE_LITERAL:
                return true; // All is accepted
            case PROTECTED_LITERAL:
                return Flags.isPublic(flag) || Flags.isPackageDefault(flag) || Flags.isProtected(flag);
            case PACKAGE_LITERAL:
                return Flags.isPublic(flag) || Flags.isPackageDefault(flag);
            case PUBLIC_LITERAL:
            default:
                return Flags.isPublic(flag);
        }
    }

    /**
     * Check whether the element is a part of a port:communication mechanism.
     *
     * @param member
     *            The member.
     * @return True if it can, false otherwise.
     * @throws JavaModelException
     */
    protected boolean isStrucDiag(IMember member) throws JavaModelException {
        boolean ret = false;

        if ("initPortsGenerator".equals(member.getElementName())) {
            ret = true;
        } else if ("makeConnections".equals(member.getElementName())) {
            ret = true;
        }
        return ret;
    }

    /**
     * Returns the Javadoc associated to a member.
     *
     * @param member
     *            The member.
     * @return The Javadoc or empty string if none.
     * @throws JavaModelException
     *             Error during introspection.
     */
    protected String getJavadoc(IMember member) throws JavaModelException {
        Reader lReader = JavadocContentAccess.getContentReader(member, true);
        if (lReader != null) {
            try {
                BufferedReader in = new BufferedReader(lReader);
                StringBuffer javadoc = new StringBuffer();
                int c;
                boolean lReturnAdded = false;
                while ((c = in.read()) != -1) {
                    if (c == '\n' || c == '\r') {
                        // Nothing
                        if (!lReturnAdded) {
                            javadoc.append(System.getProperty("line.separator"));
                            lReturnAdded = true;
                        }
                    } else {
                        lReturnAdded = false;
                        javadoc.append((char)c);
                    }
                }
                in.close();
                return javadoc.toString();
            } catch (IOException e) {
                LogUtils.logThrowable(e);
                // Cannot occur
            }
        }
        return "";
    }

    /**
     * Find an operation.
     *
     * @param classifier
     *            The classifier.
     * @param method
     *            The method.
     * @return The operation or null if not found.
     */
    protected Operation findOperation(Classifier classifier, IMethod method) {
        try {
            BasicEList<String> paramList = new BasicEList<String>();
            for (String param : method.getParameterNames()) {
                paramList.add(param);
            }

            BasicEList<Type> paramTypeList = new BasicEList<Type>();
            for (String param : method.getParameterTypes()) {
                String typeWithoutArray = Signature.getElementType(param);
                String typeName = Signature.toString(Signature.getTypeErasure(typeWithoutArray));
                typeName = resolveTypeInContext(classifier, method.getDeclaringType(), typeName);
                Type paramType = findOrCreateType(classifier.getNearestPackage(), typeName);
                if (paramType != null) {
                    paramTypeList.add(paramType);
                }
            }
            Operation op = null;
            if (classifier instanceof Class) {
                op = ((Class)classifier).getOwnedOperation(method.getElementName(), paramList, paramTypeList,
                        false, false);
                if (op != null) {
                    op.setClass_((Class)classifier);
                }
                return op;
            }
            if (classifier instanceof Interface) {
                op = ((Interface)classifier).getOwnedOperation(method.getElementName(), paramList,
                        paramTypeList, false, false);
                if (op != null) {
                    op.setInterface((Interface)classifier);
                }
                return op;
            }
            if (classifier instanceof DataType) {
                op = ((DataType)classifier).getOwnedOperation(method.getElementName(), paramList,
                        paramTypeList, false, false);
                if (op != null) {
                    op.setDatatype((DataType)classifier);
                }
                return op;
            }
            if (classifier instanceof PrimitiveType) {
                ReversePlugin.log("The operation was not stored in the content of a Primitive Type : "
                        + classifier.getName(), IStatus.ERROR);
                op = ((PrimitiveType)classifier).getOwnedOperation(method.getElementName(), paramList,
                        paramTypeList, false, false);
                if (op != null) {
                    op.setDatatype((PrimitiveType)classifier);
                }
                return op;
            }
            // Never Happens normally
            if (classifier instanceof Enumeration) {
                ReversePlugin.log("The operation was not stored in the content of an Enumeration : "
                        + classifier.getName(), IStatus.ERROR);
                op = ((Enumeration)classifier).getOwnedOperation(method.getElementName(), paramList,
                        paramTypeList, false, false);
                if (op != null) {
                    op.setDatatype((Enumeration)classifier);
                }
                return op;
            }
            ReversePlugin.log("The operation was not stored in the content of : " + classifier.getName(),
                    IStatus.ERROR);
        } catch (JavaModelException e) {
            e.printStackTrace();
            LogUtils.logThrowable(e);
        }
        return null;
    }

    /**
     * Create an opaqueBehavior containing the code of the function.
     *
     * @param operation
     *            : the operation that relates the function
     * @param sourceMethod
     *            : the IMethod element
     * @return the new FunctionBehavior
     */
    protected OpaqueBehavior createOpaqueBehavior(Operation operation, IMethod sourceMethod) {
        OpaqueBehavior opaque = UMLFactory.eINSTANCE.createOpaqueBehavior();

        // set Name
        opaque.setName(operation.getName());

        // add Parameters
        for (Parameter parameter : operation.getOwnedParameters()) {
            opaque.createOwnedParameter(parameter.getName(), parameter.getType());
        }

        // reference the operation
        opaque.setSpecification(operation);

        // set the visibility
        opaque.setVisibility(operation.getVisibility());

        // set the language (JAVA)
        opaque.getLanguages().add(BEHAVIOUR_LANGUAGE);

        // add the body
        try {
            String body = sourceMethod.getSource();
            int beginIndex = body.indexOf('{');
            int endIndex = body.lastIndexOf('}');
            if (beginIndex != -1 && endIndex != -1 && endIndex > beginIndex) {
                body = body.substring(beginIndex + 1, endIndex);
            }
            body = body.replaceAll(">", "&gt;");
            opaque.getBodies().add(body);
        } catch (JavaModelException e) {
            e.printStackTrace();
            LogUtils.logThrowable(e);
        }
        return opaque;
    }

    /**
     * Update the opaquebehavior containing the code of the function.
     *
     * @param opaque
     * @param sourceMethod
     *            : the IMethod element
     */
    protected void updateOpaqueBehaviorBody(OpaqueBehavior opaque, IMethod sourceMethod) {
        // update the body
        try {
            opaque.getBodies().remove(0);
            String body = sourceMethod.getSource();
            int beginIndex = body.indexOf('{');
            int endIndex = body.lastIndexOf('}');
            if (beginIndex != -1 && endIndex != -1 && endIndex > beginIndex) {
                body = body.substring(beginIndex + 1, endIndex);

            }
            opaque.getBodies().add(body);
        } catch (JavaModelException e) {
            e.printStackTrace();
            LogUtils.logThrowable(e);
        }
    }

    /**
     * Create and return an operation for a type.
     *
     * @param element
     * @param method
     * @return operationObject
     * @throws JavaModelException
     */
    protected Operation createOperation(Element element, IMethod method) throws JavaModelException {
        String methodName = method.getElementName();

        Operation operationObject = UMLFactory.eINSTANCE.createOperation();
        operationObject.setName(methodName);

        update(operationObject, method.getFlags());

        int flags = method.getFlags();
        operationObject.setIsAbstract(Flags.isAbstract(flags));
        operationObject.setIsStatic(Flags.isStatic(flags));

        attachJavadoc(operationObject, method);

        createParameters(element, operationObject, method);

        if (method.exists() && !method.isConstructor()) {
            createTemplateParameters(method, operationObject);

            String returnTypeSig = method.getReturnType();
            int arrayCount = Signature.getArrayCount(returnTypeSig);

            String returnTypeWithoutArray = Signature.getElementType(returnTypeSig);
            String returnTypeName = Signature.toString(Signature.getTypeErasure(returnTypeWithoutArray));

            Type returnType = findTemplateParameter(element, returnTypeName);
            if (returnType == null) {
                returnType = findTemplateParameter(operationObject, returnTypeName);
            }

            if (returnType == null) {
                returnTypeName = resolveTypeInContext(element, method.getDeclaringType(), returnTypeName);
                returnType = findOrCreateType(element.getNearestPackage(), returnTypeName);
            }

            // Type returnType = findOrCreateType(element.getNearestPackage(),
            // returnTypeName);
            if (returnType != null) {
                Parameter parameter = UMLFactory.eINSTANCE.createParameter();
                // parameter.setName("");
                parameter.setDirection(ParameterDirectionKind.RETURN_LITERAL);
                parameter.setType(returnType);
                // handle array case with ONE dimension
                if (arrayCount == 1) {
                    parameter.setUpper(-1);
                } else if (arrayCount > 1) {
                    // handle array case with more than ONE dimension
                    // TODO find the best way to represent it in UML
                }

                operationObject.getOwnedParameters().add(parameter);
            }
        }

        // set the container
        if (element instanceof Class) {
            operationObject.setClass_((Class)element);
        }
        if (element instanceof Interface) {
            operationObject.setInterface((Interface)element);
        }
        if (element instanceof DataType) {
            operationObject.setDatatype((DataType)element);
        }
        if (element instanceof PrimitiveType) {
            operationObject.setDatatype((PrimitiveType)element);
        }
        // Never Happens normally
        if (element instanceof Enumeration) {
            ReversePlugin.log("The operation was not stored in the content of an Enumeration : "
                    + ((Enumeration)element).getName(), IStatus.ERROR);
            operationObject.setDatatype((Enumeration)element);
        }
        return operationObject;
    }

    /**
     * Creates a template parameter for a method.
     *
     * @param method
     * @param operationObject
     * @throws JavaModelException
     *             Java error.
     */
    private void createTemplateParameters(IMethod method, Operation operationObject)
            throws JavaModelException {
        ITypeParameter[] typeParameters = method.getTypeParameters();
        for (ITypeParameter typeParameter : typeParameters) {
            TemplateSignature templateSignature = operationObject.getOwnedTemplateSignature();
            // FIXME See how to update the template signature
            if (templateSignature == null) {
                templateSignature = operationObject.createOwnedTemplateSignature();
                ClassifierTemplateParameter classifierTemplateParameter = UMLFactory.eINSTANCE
                        .createClassifierTemplateParameter();
                templateSignature.getOwnedParameters().add(classifierTemplateParameter);
                ParameterableElement parameteredElement = classifierTemplateParameter
                        .createOwnedParameteredElement(UMLPackage.Literals.CLASS);
                if (parameteredElement instanceof Class) {
                    Class clazz = (Class)parameteredElement;
                    clazz.setName(typeParameter.getElementName());
                } // end of if (parameteredElement instanceof Class)
            }
        }
    }

    /**
     * Creates the parameters for a method.
     *
     * @param element
     * @param operation
     * @param method
     * @throws JavaModelException
     */
    protected void createParameters(Element element, Operation operation, IMethod method)
            throws JavaModelException {
        String[] paramNames = method.getParameterNames();
        String[] paramTypeSigs = method.getParameterTypes();
        for (int i = 0; i < paramNames.length; i++) {
            String name = paramNames[i];
            String typeSig = paramTypeSigs[i];
            int arrayCount = Signature.getArrayCount(typeSig);

            Parameter parameter = UMLFactory.eINSTANCE.createParameter();
            parameter.setName(name);

            String typeWithoutArray = Signature.getElementType(typeSig);
            String typeName = Signature.toString(Signature.getTypeErasure(typeWithoutArray));

            Type paramType = findTemplateParameter(element, typeName);
            if (paramType == null) {
                typeName = resolveTypeInContext(element, method.getDeclaringType(), typeName);
                paramType = findOrCreateType(element.getNearestPackage(), typeName);
            }

            if (paramType != null) {
                parameter.setType(paramType);
            }

            // handle array case with ONE dimension
            if (arrayCount == 1) {
                parameter.setUpper(-1);
            } else if (arrayCount > 1) {
                // handle array case with more than ONE dimension
                // TODO find the best way to represent it in UML
            }
            operation.getOwnedParameters().add(parameter);
        }
    }

    /**
     * Creates and returns a property.
     *
     * @param element
     * @param field
     * @return a Property
     * @throws JavaModelException
     */
    protected Property createProperty(Element element, IField field) throws JavaModelException {
        boolean isCollection = false;
        boolean isSet = false;
        boolean isList = false;
        // no need for an isArray, see arraycount variable

        String fieldName = field.getElementName();
        String fieldTypeSig = field.getTypeSignature();
        String fieldTypeWithoutArray = Signature.getElementType(fieldTypeSig);
        Property propertyObject = null;

        int arrayCount = Signature.getArrayCount(fieldTypeSig);
        String fieldTypeName = Signature.toString(Signature.getTypeErasure(fieldTypeWithoutArray));
        Type fieldType = findTemplateParameter(element, fieldTypeName);

        if (fieldType == null) {
            fieldTypeName = resolveTypeInContext(element, field.getDeclaringType(), fieldTypeName);
            fieldType = findOrCreateType(element.getNearestPackage(), fieldTypeName);
        }

        String[] typeArguments = Signature.getTypeArguments(fieldTypeSig);
        // X<Y> types - handle collections
        if (typeArguments != null && typeArguments.length == 1) {
            // Get the X class and see if it is a subclass of Set, List or Collection
            // TODO get datatype, crawl back up packages for full qualified name,
            // get actual class, compare to collections, List, Set
            java.lang.Class<?> mainClass = null;
            try {
                mainClass = java.lang.Class.forName(fieldTypeName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                LogUtils.logThrowable(e);
            }
            if (mainClass != null && Collection.class.isAssignableFrom(mainClass)) {
                isCollection = true;
                fieldTypeWithoutArray = typeArguments[0];
                String fieldTypeWithoutArrayName = Signature.toString(Signature
                        .getTypeErasure(fieldTypeWithoutArray));
                fieldType = findTemplateParameter(element, fieldTypeWithoutArrayName);
                if (fieldType == null) {
                    fieldTypeWithoutArrayName = resolveTypeInContext(element, field.getDeclaringType(),
                            fieldTypeWithoutArrayName);
                    fieldType = findOrCreateType(element.getNearestPackage(), fieldTypeWithoutArrayName);
                }
                if (mainClass != null && Set.class.isAssignableFrom(mainClass)) {
                    isSet = true;
                } else if (mainClass != null && List.class.isAssignableFrom(mainClass)) {
                    isList = true;
                }
            }
        }

        // retrieve the affectation
        String affectation = retrieveFieldAffectation(field);

        // create the attribute
        propertyObject = null;
        String created = " created ";
        if (element instanceof Class) {
            propertyObject = ((Class)element).createOwnedAttribute(fieldName, fieldType);
            LogUtils.logCreation(element, null, propertyObject, created);
        } else if (element instanceof Enumeration) {
            propertyObject = ((Enumeration)element).createOwnedAttribute(fieldName, fieldType);
            LogUtils.logCreation(element, null, propertyObject, created);
        } else if (element instanceof Interface) {
            propertyObject = ((Interface)element).createOwnedAttribute(fieldName, fieldType);
            LogUtils.logCreation(element, null, propertyObject, created);
        }

        if (propertyObject == null) {
            ReversePlugin.log("The property can not be created.", Status.ERROR);
            return null;
        }

        attachJavadoc(propertyObject, field);
        update(propertyObject, field.getFlags());

        // No need to check the null (done with instanceof which returns always false)
        if (field.getConstant() instanceof String) {
            LiteralString defaultValue = (LiteralString)propertyObject.createDefaultValue("", propertyObject
                    .getType(), UMLFactory.eINSTANCE.createLiteralString().eClass());
            defaultValue.setValue((String)field.getConstant());
        } else if (field.getConstant() instanceof Integer) {
            LiteralInteger defaultValue = (LiteralInteger)propertyObject.createDefaultValue("",
                    propertyObject.getType(), UMLFactory.eINSTANCE.createLiteralInteger().eClass());
            defaultValue.setValue((Integer)field.getConstant());
        } else if (field.getConstant() instanceof Boolean) {
            LiteralBoolean defaultValue = (LiteralBoolean)propertyObject.createDefaultValue("",
                    propertyObject.getType(), UMLFactory.eINSTANCE.createLiteralBoolean().eClass());
            defaultValue.setValue((Boolean)field.getConstant());
        } else if (affectation != null && arrayCount == 0) {
            LiteralString defaultValue = (LiteralString)propertyObject.createDefaultValue("", propertyObject
                    .getType(), UMLFactory.eINSTANCE.createLiteralString().eClass());
            defaultValue.setValue(affectation);
        }

        // handle array case with ONE dimension
        if (arrayCount == 1) {
            propertyObject.setUpper(-1);

            if (affectation != null) {
                String size = retrieveInitDimSize(affectation, 1);
                if (size != null) {
                    LiteralString defaultValue = (LiteralString)propertyObject.createDefaultValue("",
                            propertyObject.getType(), UMLFactory.eINSTANCE.createLiteralString().eClass());
                    defaultValue.setValue(size);
                }
            }
        } else if (arrayCount > 1) {
            // handle array case with more than ONE dimension
            String stringTypeName = resolveTypeInContext(element, field.getDeclaringType(), "String");
            Type stringType = findOrCreateType(element.getNearestPackage(), stringTypeName);
            for (int i = 1; i <= arrayCount; i++) {
                // create a qualifier property for each dimension
                String qualifierName = "dimension_" + i;
                Property prop = propertyObject.createQualifier(qualifierName, stringType);
                prop.setUpper(-1);
                if (affectation != null) {
                    String size = retrieveInitDimSize(affectation, i);
                    if (size != null) {
                        LiteralString defaultValue = (LiteralString)prop.createDefaultValue("", prop
                                .getType(), UMLFactory.eINSTANCE.createLiteralString().eClass());
                        defaultValue.setValue(size);
                    }
                }
            }
        }
        // handle Collections
        if (isCollection) {
            if (propertyObject.getUpper() < 2) {
                propertyObject.setUpper(-1);
            }
            propertyObject.setIsOrdered(false);
            propertyObject.setIsUnique(false);
        }
        // handle Sets and Lists specifically
        if (isSet) {
            if (propertyObject.getUpper() < 2) {
                propertyObject.setUpper(-1);
            }
            propertyObject.setIsUnique(true);
            propertyObject.setIsOrdered(true);
        } else if (isList) {
            if (propertyObject.getUpper() < 2) {
                propertyObject.setUpper(-1);
            }
            propertyObject.setIsOrdered(true);
            propertyObject.setIsUnique(false);
        }
        // TODO handle Maps
        return propertyObject;
    }

    /**
     * Retrieve the arraySize from an initialization.
     *
     * @param initialization
     * @param dim
     *            the dimension of the array we want to retrieve the size
     * @return the size as a String Type
     */
    private String retrieveInitDimSize(String initialization, int dim) {
        if (initialization == null) {
            return null;
        }
        int i = 1;
        while (initialization.indexOf('[') != -1) {
            int end = initialization.indexOf(']');
            if (dim == i) {
                int begin = initialization.indexOf('[');
                if (end != -1 && begin != -1 && end - begin >= 2) {
                    return initialization.substring(begin + 1, end);
                }
                return null;
            }
            if (end == -1) {
                return null;
            }
            initialization = initialization.substring(end + 1);
            i++;
        }
        return null;
    }

    /**
     * Retrieve the field affectation.
     *
     * @param field
     *            the field
     * @return the affectation found inthe type (null if no affectation
     */
    private String retrieveFieldAffectation(IField field) {
        String source;
        String intialization = null;
        try {
            source = field.getSource();

            int equalIndex = source.indexOf('=');
            int dotIndex = source.lastIndexOf(';');

            if (equalIndex != -1 && dotIndex > equalIndex) {
                intialization = source.substring(equalIndex + 1, dotIndex).trim();
            }
        } catch (JavaModelException e) {
            e.printStackTrace();
            LogUtils.logThrowable(e);
        }
        return intialization;
    }

    /**
     * Find a property.
     *
     * @param classifier
     *            The classifier.
     * @param name
     *            The name of the property.
     * @return The property or null if not found.
     */
    protected Property findProperty(Classifier classifier, String name) {
        for (Property prop : classifier.getAllAttributes()) {
            if (prop.getName().equals(name)) {
                return prop;
            }
        }
        return null;
    }

    /**
     * Attach the javadoc to an element.
     *
     * @param element
     *            The EMF element.
     * @param member
     *            The JDT Element.
     * @throws JavaModelException
     *             Error.
     */
    protected void attachJavadoc(Element element, IMember member) throws JavaModelException {
        String javadoc = getJavadoc(member);
        if (!"".equals(javadoc)) {
            EAnnotation annotation = element.getEAnnotation(JAVA_REVERSER_DOC);
            if (annotation == null) {
                annotation = element.createEAnnotation(JAVA_REVERSER_DOC);
            }
            annotation.getDetails().put("documentation", javadoc);
        }
    }

    /**
     * Create usages (between packages for the moment).
     *
     * @param packageObject
     *            the Java Package
     */
    private void createUsages(Package packageObject) {
        ArrayList<Package> usedPackageList = new ArrayList<Package>();
        LogUtils.logEntering(packageObject, "Creating usages");
        // build the list of used packages
        for (PackageableElement elementInPackage : packageObject.getPackagedElements()) {
            if (elementInPackage instanceof Interface) {
                Interface interfaceObject = (Interface)elementInPackage;
                for (Package importedPackage : interfaceObject.getImportedPackages()) {
                    if (!usedPackageList.contains(importedPackage)) {
                        usedPackageList.add(importedPackage);
                    }
                }

                for (PackageableElement element : interfaceObject.getImportedElements()) {
                    Package importedPackage = element.getNearestPackage();
                    if (!usedPackageList.contains(importedPackage)) {
                        usedPackageList.add(importedPackage);
                    }
                }

                // destroy the imported element if it is needed ( generation RTSJ constraint)
                if (!avoidImportElement()) {
                    interfaceObject.getElementImports().clear();
                    interfaceObject.getPackageImports().clear();
                }

            } else if (elementInPackage instanceof StructuredClassifier) {

                StructuredClassifier classifierObject = (StructuredClassifier)elementInPackage;
                for (Package importedPackage : classifierObject.getImportedPackages()) {
                    if (!usedPackageList.contains(importedPackage)) {
                        usedPackageList.add(importedPackage);
                    }
                }

                for (PackageableElement element : classifierObject.getImportedElements()) {
                    Package importedPackage = element.getNearestPackage();
                    if (!usedPackageList.contains(importedPackage)) {
                        usedPackageList.add(importedPackage);
                    }
                }

                // destroy the imported element if it is needed ( generation RTSJ constraint)
                if (avoidImportElement()) {
                    classifierObject.getElementImports().clear();
                    classifierObject.getPackageImports().clear();
                }
            }
        }

        // create a Usage relation for each package used by this one
        for (Package usedPackage : usedPackageList) {
            Usage tmprel = packageObject.createUsage(usedPackage);
            tmprel.setName(packageObject.getName() + "_" + usedPackage.getName());
            LogUtils.logCreation(null, null, packageObject, null);
        }
        LogUtils.logExiting();
    }

    /**
     * Return true if the elementImport and packageImport have to be reversed.
     *
     * @return true if the elementImport and packageImport have to be reversed
     */
    protected boolean avoidImportElement() {
        if (javaProject == null) {
            return true;
        }
        IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProject(javaProject.getElementName());
        IPreferenceStore store = Java2UMLPreferencesStoreManager.getPreferenceStore(iProject);
        return store.getBoolean(Java2UMLPreferencesStoreConstants.REVERSE_IMPORTS_NAME);
    }

    /**
     * Return true if the elementImport and packageImport have to be reversed.
     *
     * @return true if the elementImport and packageImport have to be reversed
     */
    protected boolean avoidDependenciesComputing() {
        if (javaProject == null) {
            return true;
        }
        IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProject(javaProject.getElementName());
        IPreferenceStore store = Java2UMLPreferencesStoreManager.getPreferenceStore(iProject);
        return store.getBoolean(Java2UMLPreferencesStoreConstants.COMPUTE_DEPENDENCIES_NAME);
    }

    /**
     * Convenient method to throw a core exception.
     *
     * @param message
     *            : the message of the exception
     * @throws CoreException
     *             Error with a status
     */
    protected void throwCoreException(String message) throws CoreException {
        IStatus status = new Status(IStatus.ERROR, ReversePlugin.getId(), IStatus.OK, message, null);
        throw new CoreException(status);
    }

    /**
     * The convert method will convert all processable java elements to uml elements and store them in the
     * resource.
     *
     * @param javaElement
     *            : the java element
     * @param pEmfResource
     * @return a Package
     * @throws CoreException
     */
    public Package convert(IJavaElement javaElement, Resource pEmfResource) throws CoreException {
        return convert(javaElement, pEmfResource, ActivityGeneration.NONE);
    }

    /**
     * The convert method will convert all processable java elements to uml elements and store them in the
     * resource.
     *
     * @param javaElement
     *            : the jkava element
     * @param pEmfResource
     *            : the resource where the element are stored
     * @param actGen
     * @return a Package
     * @throws CoreException
     */
    public Package convert(IJavaElement javaElement, Resource pEmfResource, ActivityGeneration actGen)
            throws CoreException {
        this.emfResource = pEmfResource;
        return convert(javaElement, actGen);
    }

    /**
     * Set the model name, to the model, quite important for a profile model.
     *
     * @param modelName
     *            : the name of the model
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * Load model as package with name uri.
     *
     * @param uri
     * @return a Package
     * @throws CoreException
     */
    protected Package load(URI uri) throws CoreException {
        Package vPackage = null;

        try {
            ResourceSet resourceSet = new ResourceSetImpl();
            // Load the requested resource
            Resource resource = resourceSet.getResource(uri, true);

            // Get the first (should be only) package from it
            vPackage = (Package)EcoreUtil
                    .getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);
        } catch (WrappedException we) {
            LogUtils.logThrowable(we);
            throwCoreException(we.getMessage());
        }
        return vPackage;
    }
}
