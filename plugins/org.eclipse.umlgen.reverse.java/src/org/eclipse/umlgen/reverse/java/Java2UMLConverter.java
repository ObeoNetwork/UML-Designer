/*******************************************************************************
 * Copyright (c) 2006, 2013 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 *    Urs Zeidler   - added generics, enumerations, importing of other types
 *                   applying of stereotypes
 *    Thomas Szadel (Atos Origin) - added Javadoc support and limit the details of the import according to the visibility.
 *    Florence VIVARES (Atos) - add reverse dedicated to java generation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java;

import java.util.HashMap;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.umlgen.reverse.java.logging.LogUtils;

/**
 * This class converts objects from the JDT model to a UML2 model.
 */
public class Java2UMLConverter extends AbstractJava2UMLConverter {

    /** prefix used to name a event. */
    private static final String SIGNAL_EVENT_PREFIX = "Event_";

    /** Tag. */
    protected String userCodeStartTag = "// Start of user code";

    /** Tag. */
    protected String userCodeStopTag = "// End of user code";

    /** Tag. */
    protected String connectorCodeStartTag = "/**  CONNECTORS.  ***/";

    /**
     * Constructor.
     */
    public Java2UMLConverter() {
        // Do nothing
    }

    /**
     * Creates the Java Primitive Types.<br>
     * or creates imports or applies profiles if the importlist is not empty We could also import these
     * Primitive Types from the UML2 Java Library but this solution is lighter.
     *
     * @param model
     *            the UML2 model to update
     * @throws CoreException
     */
    @Override
    protected void initializeModel(Package model) throws CoreException {

        if (importList != null) {
            if (importList.length > 0) {
                for (String im : importList) {
                    try {

                        URI uri = URI.createFileURI(im);

                        Package package1 = null;

                        try {
                            package1 = (Package)load(uri);
                        } catch (WrappedException we) {
                            LogUtils.logThrowable(we);
                            throwCoreException(we.getMessage());
                        }

                        if (package1 instanceof Profile) {
                            Profile profile = (Profile)package1;

                            if (!model.isProfileApplied(profile)) {
                                model.applyProfile(profile);
                            }
                        } else {
                            PackageImport packImport = model.createPackageImport(package1,
                                    VisibilityKind.PUBLIC_LITERAL);
                            LogUtils.logCreation(null, null, packImport, null);
                        }

                    } catch (Exception e) {
                        LogUtils.logThrowable(e);
                        throwCoreException(e.getMessage());
                    }
                }

            }
        }
        // Standard import
        umlLibrary = (Package)load(URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
        javaLibrary = (Package)load(URI.createURI(UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI));

        PackageImport packImport = model.createPackageImport(umlLibrary, VisibilityKind.PUBLIC_LITERAL);
        LogUtils.logCreation(null, null, packImport, null);

        PackageImport packImport2 = model.createPackageImport(javaLibrary, VisibilityKind.PUBLIC_LITERAL);
        LogUtils.logCreation(null, null, packImport2, null);

        // Ensure primitive types are created
        for (String type : PRIMITIVE_TYPES) {
            if (model.getOwnedType(type) == null) {
                if (umlLibrary.getOwnedType(type) != null) {
                    model.createElementImport(umlLibrary.getOwnedType(type));
                } else {
                    if (javaLibrary.getOwnedType(type) != null) {
                        model.createElementImport(javaLibrary.getOwnedType(type));
                    } else {
                        model.createOwnedPrimitiveType(type);
                    }
                }
            }
        }

    }

    /**
     * Initializes the created types and populated them with operation properties and inner types.
     */
    @Override
    protected void initializeTypes(HashMap<String, Type> allTypes, Package packageObject,
            IPackageFragment fragment) throws JavaModelException {
        for (IJavaElement javaElement : fragment.getChildren()) {
            try {
                if (javaElement instanceof IClassFile) {
                    LogUtils.logEntering(javaElement, "Initializing types");
                    handleType(allTypes, packageObject, fragment, null, new IImportDeclaration[] {},
                            ((IClassFile)javaElement).getType());
                    LogUtils.logExiting();

                } else if (javaElement instanceof ICompilationUnit) {
                    LogUtils.logEntering(javaElement, "Initializing types");
                    ICompilationUnit unit = (ICompilationUnit)javaElement;

                    // retrieve the list of import declarations ( used to create Package and Element imports)
                    IImportDeclaration[] importList = unit.getImports();

                    for (IType type : unit.getAllTypes()) {
                        handleType(allTypes, packageObject, fragment, unit, importList, type);

                    }
                    LogUtils.logExiting();
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.logMessage("ERROR - unknown initializeTypes");
                LogUtils.logExiting();
                LogUtils.logThrowable(e);
            }
        }
    }

    /**
     * Complete given type with any appropriate sub-elements.
     *
     * @param allTypes
     *            : HashMap of Types with the string associated
     * @param packageObject
     * @param fragment
     * @param unit
     * @param importList
     * @param type
     */
    private void handleType(HashMap<String, Type> allTypes, Package packageObject, IPackageFragment fragment,
            ICompilationUnit unit, IImportDeclaration[] importList, IType type) {
        LogUtils.logEntering(type, null);
        // retrieve the type from its qualified name
        String typeName = resolveTypeInContext(packageObject, type, type.getElementName());
        NamedElement element = allTypes.get(typeName);

        if (element instanceof Interface) {
            try {
                Interface interfaceObject = (Interface)element;

                // Super interfaces
                for (String interfaceName : type.getSuperInterfaceNames()) {

                    interfaceName = resolveTypeInContext(element, type, interfaceName);
                    Type interfaceType = findOrCreateType(element.getNearestPackage(), interfaceName);

                    if (interfaceType != null && interfaceType instanceof Classifier) {
                        Generalization gen = interfaceObject.createGeneralization((Classifier)interfaceType);
                        LogUtils.logCreation(unit, null, gen, null);
                    }
                }
                // Inner objects
                if (type.getParent() instanceof ICompilationUnit) {
                    if (!avoidDependenciesComputing() || !avoidImportElement()) {
                        createImports(importList, interfaceObject);
                    }
                }
                createProperties(type, interfaceObject);
                createOperations(type, interfaceObject);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.logThrowable(e);
                LogUtils.logExiting();
            }
        } else if (element instanceof Enumeration) {
            try {
                Enumeration enumeration = (Enumeration)element;

                for (String interfaceName : type.getSuperInterfaceNames()) {
                    interfaceName = resolveTypeInContext(element, type, interfaceName);
                    Type interfaceType = findOrCreateType(element.getNearestPackage(), interfaceName);

                    if (interfaceType != null && interfaceType instanceof Interface) {
                        Classifier classifier = (Classifier)interfaceType;
                        if (enumeration.getGeneralization(classifier) == null) {
                            Generalization gen = enumeration.createGeneralization(classifier);
                            LogUtils.logCreation(unit, null, gen, null);
                        }
                    }
                }
                // Inner objects
                if (type.getParent() instanceof ICompilationUnit) {
                    if (!avoidDependenciesComputing() || !avoidImportElement()) {
                        createImports(importList, enumeration);
                    }
                }
                createProperties(type, enumeration);
                createOperations(type, enumeration);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.logThrowable(e);
                LogUtils.logExiting();
            }
        } else if (element instanceof Class) {
            try {
                Class classObject = (Class)element;
                // Super Interfaces
                for (String interfaceName : type.getSuperInterfaceNames()) {
                    interfaceName = resolveTypeInContext(element, type, interfaceName);
                    Type interfaceType = findOrCreateType(element.getNearestPackage(), interfaceName);

                    if (interfaceType != null && interfaceType instanceof Interface) {
                        Interface interf = (Interface)interfaceType;

                        if (classObject.getInterfaceRealization(interfaceName, interf) == null) {
                            InterfaceRealization iRealization = classObject.createInterfaceRealization(
                                    interfaceName, interf);
                            LogUtils.logCreation(unit, null, iRealization, null);
                        }
                    }
                }
                // Generalization
                String superClassName = null;
                if (type.getSuperclassName() != null) {
                    superClassName = resolveTypeInContext(element, type, type.getSuperclassName());
                    Type classType = findOrCreateType(element.getNearestPackage(), superClassName);
                    if (classType != null && classType instanceof Classifier) {
                        Classifier classifier = (Classifier)classType;
                        // Create generalization only if it is needed
                        if (classObject.getGeneralization(classifier) == null) {
                            Generalization gen = classObject.createGeneralization(classifier);
                            LogUtils.logCreation(unit, null, gen, null);
                        }
                    }
                }

                // Inner objects
                if (type.getParent() instanceof ICompilationUnit) {
                    if (!avoidDependenciesComputing() || !avoidImportElement()) {
                        createImports(importList, classObject);
                    }
                }
                createProperties(type, classObject, fragment);
                createOperations(type, classObject);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.logMessage("ERROR - unknown initializeTypes catched for class element "
                        + element.getName());
                LogUtils.logExiting();
                LogUtils.logThrowable(e);
            }

        }
        LogUtils.logExiting();
    }

    /**
     * Creates the properties for a classifier, and process the annotations.
     *
     * @param type
     * @param classifier
     * @param fragment
     * @throws JavaModelException
     */
    protected void createProperties(IType type, Class classifier, IPackageFragment fragment)
            throws JavaModelException {
        for (IField field : type.getFields()) {
            if (canBeImported(field)) {
                LogUtils.logEntering(field, "creating property");
                Property propertyObject = findProperty(classifier, field.getElementName());
                if (propertyObject != null) {
                    // Just update
                    attachJavadoc(propertyObject, field);
                } else {
                    // Creates a new one
                    propertyObject = createProperty(classifier, field);
                    classifier.getOwnedAttributes().add(propertyObject);
                    LogUtils.logCreation(null, field, propertyObject, null);
                }
                LogUtils.logExiting();
            }
        }
    }

    /**
     * Creates properties in an interface.
     *
     * @param type
     * @param interfaceObject
     * @throws JavaModelException
     */
    protected void createProperties(IType type, Interface interfaceObject) throws JavaModelException {
        IField[] fields = type.getFields();
        for (IField field : fields) {
            LogUtils.logEntering(field, "creating property");
            Property propertyObject = findProperty(interfaceObject, field.getElementName());
            if (propertyObject != null) {
                attachJavadoc(interfaceObject, type);
            } else {
                propertyObject = createProperty(interfaceObject, field);
                LogUtils.logCreation(null, field, propertyObject, null);
                if (propertyObject != null) {
                    interfaceObject.getOwnedAttributes().add(propertyObject);
                }
            }
            LogUtils.logExiting();
        }
    }

    /**
     * Creates the properties for an enumeration.
     *
     * @param type
     * @param enumeration
     * @throws JavaModelException
     */
    protected void createProperties(IType type, Enumeration enumeration) throws JavaModelException {
        // For an enum, always import all the fields
        for (IField field : type.getFields()) {
            LogUtils.logEntering(field, "creating property");
            String typename = Signature.toString(Signature.getTypeErasure(Signature.getElementType(field
                    .getTypeSignature())));
            if (typename.equals(type.getElementName())) {
                EnumerationLiteral lit = enumeration.getOwnedLiteral(field.getElementName());
                if (lit == null) {
                    lit = enumeration.createOwnedLiteral(field.getElementName());
                    LiteralInteger value = UMLFactory.eINSTANCE.createLiteralInteger();
                    if (field.getConstant() instanceof Integer) {
                        value.setValue((Integer)field.getConstant());
                    }
                    lit.setSpecification(value);
                    LogUtils.logCreation(null, field, lit, null);
                }
                attachJavadoc(lit, field);
            } else if (!type.isEnum()) {
                // old style enumeration translation
                EnumerationLiteral lit = enumeration.getOwnedLiteral(field.getElementName());
                if (lit == null) {
                    lit = enumeration.createOwnedLiteral(field.getElementName());
                }
                LiteralInteger value = UMLFactory.eINSTANCE.createLiteralInteger();
                if (field.getConstant() instanceof Integer) {
                    value.setValue((Integer)field.getConstant());
                }
                lit.setSpecification(value);
                LogUtils.logCreation(null, field, lit, null);
                attachJavadoc(lit, field);
            } else {
                // true properties for enumeration
                Property propertyObject = findProperty(enumeration, field.getElementName());
                if (propertyObject == null) {
                    propertyObject = createProperty(enumeration, field);
                    enumeration.getOwnedAttributes().add(propertyObject);
                    LogUtils.logCreation(null, field, propertyObject, null);
                }
                attachJavadoc(propertyObject, field);
            }
            LogUtils.logExiting();
        }
    }

    /**
     * Process all types in the array.
     *
     * @param packageObject
     * @param types
     * @throws JavaModelException
     */
    @Override
    protected HashMap<String, Type> processTypes(Namespace packageObject, IType[] types)
            throws JavaModelException {
        HashMap<String, Type> alltypes = new HashMap<String, Type>();
        for (IType type : types) {
            LogUtils.logEntering(type, "Processing types");
            if (canBeImported(type)) {
                Classifier createTypeInPackage = (Classifier)packageObject.getMember(type.getElementName(),
                        false, UMLPackage.eINSTANCE.getClassifier());
                if (createTypeInPackage == null) {
                    createTypeInPackage = createTypeInPackage(packageObject, type);
                    LogUtils.logCreation(null, type, createTypeInPackage, null);
                }
                // TODO : Check if we comply
                // Add the types in a hashMap to retrieve them in at the location they have been created
                alltypes.put(type.getFullyQualifiedName('.'), createTypeInPackage);
                // Then ... continue
                // package : class / st�r�otype
                // Creates inner types
                IType[] innerTypes = type.getTypes();
                if (innerTypes.length > 0) {
                    alltypes.putAll(processTypes(createTypeInPackage, innerTypes));
                }
                // }
            }
            LogUtils.logExiting();
        }
        return alltypes;
    }

    /**
     * Creates the type in the package.
     *
     * @param packageObject
     * @param type
     * @return
     * @throws JavaModelException
     */
    @Override
    protected Classifier createTypeInPackage(Namespace packageObject, IType type) throws JavaModelException {

        if (type.isInterface()) {

            Interface interfaceObject = UMLFactory.eINSTANCE.createInterface();
            if (type.isAnnotation()) {
                interfaceObject.setName("@" + type.getElementName());
            } else {
                interfaceObject.setName(type.getElementName());
            }
            createTemplateParameters(type, interfaceObject);

            update(interfaceObject, type.getFlags());
            attachJavadoc(interfaceObject, type);
            addToContainment(packageObject, interfaceObject);
            return interfaceObject;
        }
        if (isEnumeration(type)) {
            Enumeration enumeration = UMLFactory.eINSTANCE.createEnumeration();
            enumeration.setName(type.getElementName());
            update(enumeration, type.getFlags());
            attachJavadoc(enumeration, type);
            addToContainment(packageObject, enumeration);
            return enumeration;
        }

        Class classObject = UMLFactory.eINSTANCE.createClass();
        classObject.setName(type.getElementName());
        createTemplateParameters(type, classObject);

        update(classObject, type.getFlags());
        attachJavadoc(classObject, type);
        addToContainment(packageObject, classObject);

        return classObject;
    }

    /**
     * Creates a model and add it to the emfResource.
     */
    @Override
    protected Package findOrCreateRootPackage() {
        // Let's try to find the root package before creating a new one
        for (EObject eObject : emfResource.getContents()) {
            if (eObject instanceof Package) {
                if (((Package)eObject).getName() == null) {
                    if (modelName == null || "".equals(modelName)) {
                        // Found !!
                        return (Package)eObject;
                    }
                } else if (((Package)eObject).getName().equals(modelName)) {
                    // Found !!
                    return (Package)eObject;
                }
            }
        }
        // Not found : creates a new one
        Model model = UMLFactory.eINSTANCE.createModel();

        Package modelout = (Package)model;
        emfResource.getContents().add(modelout);
        modelout.setName(modelName);
        LogUtils.logCreation(null, null, modelout, null);
        return modelout;
    }

    /**
     * Creates operations for enumerations.
     *
     * @param type
     * @param enumeration
     * @throws JavaModelException
     */
    protected void createOperations(IType type, Enumeration enumeration) throws JavaModelException {
        IMethod[] methods = type.getMethods();
        for (IMethod method : methods) {
            LogUtils.logEntering(method, "Creating operation");
            if (canBeImported(method) && !isStrucDiag(method)) {

                // Try to find if the operation already exists
                Operation operationObject = findOperation(enumeration, method);
                if (operationObject != null) {
                    // Just update the javadoc
                    attachJavadoc(operationObject, method);
                } else {
                    // Creates a new one
                    operationObject = createOperation(enumeration, method);
                    LogUtils.logCreation(null, method, operationObject, null);
                    enumeration.getOwnedOperations().add(operationObject);
                }
            }
            LogUtils.logExiting();
        }
    }

    /**
     * Create operations.
     *
     * @param type
     * @param interfaceObject
     * @throws JavaModelException
     * @see org.eclipse.umlgen.reverse.java.AbstractJava2UMLConverter#createOperations(org.eclipse.jdt.core.IType,
     *      org.eclipse.uml2.uml.Interface)
     */
    protected void createOperations(IType type, Interface interfaceObject) throws JavaModelException {
        IMethod[] methods = type.getMethods();
        for (IMethod method : methods) {
            LogUtils.logEntering(method, "Creating operation");
            // For an interface, always import all.
            if (!isStrucDiag(method)) {
                // Try to find if the operation already exists
                Operation operationObject = findOperation(interfaceObject, method);
                if (operationObject != null) {
                    // Just update the javadoc
                    attachJavadoc(operationObject, method);
                    LogUtils.logCreation(interfaceObject, method, operationObject, " method found");
                } else {
                    // Creates a new one
                    operationObject = createOperation(interfaceObject, method);
                    LogUtils.logCreation(interfaceObject, method, operationObject, null);
                    interfaceObject.getOwnedOperations().add(operationObject);
                }
            }
            LogUtils.logExiting();
        }
    }

    /**
     * Create operations.
     *
     * @param type
     * @param classifier
     * @throws JavaModelException
     * @see org.eclipse.umlgen.reverse.java.AbstractJava2UMLConverter#createOperations(org.eclipse.jdt.core.IType,
     *      org.eclipse.uml2.uml.Class)
     */
    private void createOperations(IType type, Class classifier) throws JavaModelException {
        for (IMethod method : type.getMethods()) {
            LogUtils.logEntering(method, "Creating operation");
            int flags = method.getFlags();

            if (canBeImported(method)) {
                String source = method.getSource().trim();
                // Don't reverse default constructor : no parameters, no source code
                if (method.isConstructor() && method.getParameterNames().length == 0) {
                    if (source.length() == 0) {
                        LogUtils.logExiting();
                        return;
                    }
                }
                // Try to find if the operation already exists
                Operation operationObject = findOperation(classifier, method);
                if (operationObject == null) {
                    // If it does not exist, Create a new one
                    operationObject = createOperation(classifier, method);
                    LogUtils.logCreation(classifier, method, operationObject, null);
                    classifier.getOwnedOperations().add(operationObject);
                } else {
                    LogUtils.logCreation(classifier, method, operationObject, " method found");
                }
                // update the javadoc
                attachJavadoc(operationObject, method);
            }
            LogUtils.logExiting();
        }
    }

    /**
     * Creates the import object: ElementImport and PackageImport.
     *
     * @param importList
     *            the list of import to create
     * @param classifier
     *            the classifier that will contain those import objects
     * @throws JavaModelException
     */
    protected void createImports(IImportDeclaration[] importList, Classifier classifier) {
        // parse all the imports
        LogUtils.logEntering(classifier, "Creating import");
        for (IImportDeclaration importDecl : importList) {
            String importedName = importDecl.getElementName();

            if (importedName.endsWith(".*")) {
                // it is a package import, retrieve the package and create the PackageImport
                String packageQName = importedName.substring(0, importedName.lastIndexOf('.'));
                if (!packageQName.endsWith(classifier.getPackage().getName())) {
                    Package importedPackage = findOrCreatePackage(classifier.getModel(), packageQName);
                    if (importedPackage != null && classifier.getPackageImport(importedPackage) == null) {
                        PackageImport packImport = classifier.createPackageImport(importedPackage,
                                VisibilityKind.PRIVATE_LITERAL);
                        LogUtils.logCreation(null, null, packImport, null);
                    }
                }
            } else {
                // it is a element import, retrieve the element and create the ElementImport
                Type importedElement = findOrCreateType(classifier.getNearestPackage(), importedName);
                if (importedElement != null && classifier.getElementImport(importedElement) == null) {
                    ElementImport elImport = classifier.createElementImport(importedElement,
                            VisibilityKind.PRIVATE_LITERAL);
                    LogUtils.logCreation(null, null, elImport, null);
                }
            }
        }
        LogUtils.logExiting();
    }

    /**
     * Find a port.
     *
     * @param classifier
     *            The classifier.
     * @param name
     *            The name of the port.
     * @return The property or null if not found.
     */
    protected Port findPort(Classifier classifier, String name) {
        for (Property prop : classifier.getAllAttributes()) {
            if (prop.getName().equals(name) && prop instanceof Port) {
                return (Port)prop;
            }
        }
        return null;
    }

    /**
     * Find port or inherited port.
     *
     * @param classifier
     *            The classifier.
     * @param name
     *            The name of the port.
     * @return The property or null if not found.
     */
    protected Port findPort2(Classifier classifier, String name) {
        Port port = findPort(classifier, name);
        if (port == null) {
            NamedElement prop = classifier.getInheritedMember(name);
            if (prop instanceof Port) {
                return (Port)prop;
            }
        }
        return port;
    }

    /**
     * Find a reception from a method.
     *
     * @param classifier
     *            The classifier.
     * @param method
     *            The java method that is a reception
     * @return The reception or null if not found.
     */
    protected Reception findReception(Classifier classifier, IMethod method) {
        BasicEList<String> paramList = new BasicEList<String>();
        // TODO: take in account parameter of reception (different from signal parameter)

        BasicEList<Type> paramTypeList = new BasicEList<Type>();

        Reception rec = null;

        if (classifier instanceof Class) {
            rec = ((Class)classifier).getOwnedReception(method.getElementName(), paramList, paramTypeList,
                    false, false);
            return rec;
        }
        if (classifier instanceof Interface) {
            rec = ((Interface)classifier).getOwnedReception(method.getElementName(), paramList,
                    paramTypeList, false, false);
            return rec;
        }
        return null;
    }

    /**
     * Create and return an a reception.
     *
     * @param classifier
     * @param method
     * @return receptionObject
     * @throws JavaModelException
     */
    protected Reception createReception(Classifier classifier, IMethod method) throws JavaModelException {

        Reception receptionObject = null;

        BasicEList<String> paramList = new BasicEList<String>();
        // TODO: take in account parameter of reception (different from signal parameter)
        BasicEList<Type> paramTypeList = new BasicEList<Type>();

        if (classifier instanceof Class) {
            receptionObject = ((Class)classifier).createOwnedReception(method.getElementName(), paramList,
                    paramTypeList);
        } else if (classifier instanceof Interface) {
            receptionObject = ((Interface)classifier).createOwnedReception(method.getElementName(),
                    paramList, paramTypeList);
        }
        attachJavadoc(receptionObject, method);
        update(receptionObject, method.getFlags());

        // attach the signal ( create signal if it does not exist)
        Signal signalObject = findSignal(classifier.getNearestPackage(), method.getElementName());
        if (signalObject == null) {
            signalObject = createSignal(classifier.getNearestPackage(), method);
            LogUtils.logCreation(null, null, signalObject, null);
        }
        receptionObject.setSignal(signalObject);
        return receptionObject;
    }

    /**
     * Creates and returns a signal.
     *
     * @param signalPackage
     *            the package that contains the signal
     * @param method
     *            the method java from which the sigan is created
     * @return the new signal
     * @throws JavaModelException
     */
    protected Signal createSignal(Package signalPackage, IMethod method) throws JavaModelException {
        // create the new signal
        Signal signalObject = UMLFactory.eINSTANCE.createSignal();
        // signalObject.setVisibility(VisibilityKind.PRIVATE_LITERAL);
        signalObject.setName(method.getElementName());

        // create the parameters
        // TODO improvements required to deal only with the method parameter that are signal attributes
        BasicEList<String> paramList = new BasicEList<String>();
        for (String param : method.getParameterNames()) {
            paramList.add(param);
        }
        BasicEList<Type> paramTypeList = new BasicEList<Type>();
        BasicEList<Integer> upperList = new BasicEList<Integer>();
        for (String param : method.getParameterTypes()) {
            Integer upper = 1;
            String typeWithoutArray = Signature.getElementType(param);
            String typeName = Signature.toString(Signature.getTypeErasure(typeWithoutArray));
            if (param.contains("[")) {
                upper = -1;
            }

            typeName = resolveTypeInContext(signalPackage, method.getDeclaringType(), typeName);
            Type paramType = findOrCreateType(signalPackage, typeName);

            if (paramType != null) {
                paramTypeList.add(paramType);
                upperList.add(upper);
            }
        }
        for (int i = 0; i < paramList.size(); i++) {
            signalObject.createOwnedAttribute(paramList.get(i), paramTypeList.get(i), 1, upperList.get(i));
        }
        LogUtils.logCreation(null, null, signalObject, null);

        // attach the signal event ( create signal event if it does not exist)
        SignalEvent signalEvent = findSignalEvent(signalPackage, SIGNAL_EVENT_PREFIX
                + method.getElementName());
        if (signalEvent == null) {
            signalEvent = createSignalEvent(signalPackage, SIGNAL_EVENT_PREFIX + method.getElementName());
        }
        signalEvent.setSignal(signalObject);
        addToContainment(signalPackage, signalObject);
        return signalObject;
    }

    /**
     * Find a signal from its name.
     *
     * @param signalPackage
     *            the package that contains the signal
     * @param signalName
     *            the signal name
     * @return The reception or null if not found.
     */
    protected Signal findSignal(Package signalPackage, String signalName) {
        for (PackageableElement element : signalPackage.getPackagedElements()) {
            if (element.getName().equals(signalName) && element instanceof Signal) {
                return (Signal)element;
            }
        }
        return null;
    }

    /**
     * Creates and returns a signal event.
     *
     * @param signalPackage
     * @param signalEventName
     * @return signalEventObject
     * @throws JavaModelException
     */
    protected SignalEvent createSignalEvent(Package signalPackage, String signalEventName)
            throws JavaModelException {
        // create the new signal
        SignalEvent signalEventObject = UMLFactory.eINSTANCE.createSignalEvent();
        signalEventObject.setName(signalEventName);
        signalPackage.getPackagedElements().add(signalEventObject);
        return signalEventObject;
    }

    /**
     * Find a signal event from its name.
     *
     * @param signalPackage
     *            the package that contains the signal
     * @param signalEventName
     *            the signal event name
     * @return The reception or null if not found.
     */
    protected SignalEvent findSignalEvent(Package signalPackage, String signalEventName) {
        for (PackageableElement element : signalPackage.getPackagedElements()) {
            if (element.getName().equals(signalEventName) && element instanceof SignalEvent) {
                return (SignalEvent)element;
            }
        }
        return null;
    }

    @Override
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
                LogUtils.logCreation(null, packageName, packageObject, " Created from Java2UMLConverter");
            }
            parent = packageObject;
        }
        return packageObject;
    }

    @Override
    protected Type createNotFoundDataType(Package packageObject, String typeName) {
        Type type = null;
        type = createDataType(packageObject.getModel(), typeName);
        return type;
    }
}
