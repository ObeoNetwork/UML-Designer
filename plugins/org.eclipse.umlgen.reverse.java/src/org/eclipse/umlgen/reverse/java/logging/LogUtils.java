/*******************************************************************************
 * Copyright (c) 2008 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Philippe Roland (Atos) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java.logging;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.umlgen.reverse.java.internal.ReversePlugin;

/**
 * Logging Utilities.
 */
public class LogUtils {

    /** Logger object. */
    private static ILog logger = ReversePlugin.getDefault().getLog();

    /** Initial tabbing. */
    private static String tabbedSpace = "";

    /** Tabbing increment for indentation. */
    private static String tabIncrement = "   ";

    /** Level of log. */
    private static int logLevel = IStatus.INFO;

    /**
     * Reset tabbing.
     */
    public static void resetTabbing() {
        tabbedSpace = "";
    }

    /**
     * Increment tabbing.
     */
    public static void incrementTabbing() {
        tabbedSpace += tabIncrement;
    }

    /**
     * Decrement tabbing.
     */
    private static void decreaseTabbing() {
        if (tabbedSpace.length() >= tabIncrement.length()) {
            tabbedSpace = tabbedSpace.substring(tabIncrement.length());
        }
    }

    /**
     * Gets the type and name of an object. Can handle UML Elements and JDT IJavaElements.
     *
     * @param object
     *            an object
     * @return the object's full name
     */
    public static String getFullName(Object object) {
        if (object instanceof IJavaElement) {
            return "Java " + getTypeName(object) + " " + ((IJavaElement)object).getElementName();
        } else if (object instanceof Element) {
            String umlName = getUMLName((Element)object);
            return "UML " + object.getClass().getSimpleName() + " " + umlName;
        } else if (object instanceof ASTNode) {
            return "AST " + getTypeName(object) + " " + getAstName((ASTNode)object);
        }
        return null;
    }

    /**
     * Return the package of the class.
     *
     * @param clazz
     * @return the package
     */
    private static Package getClassPackage(Class clazz) {
        Element parent = clazz.getOwner();
        while (parent != null && !(parent instanceof Package)) {
            parent = parent.getOwner();
        }
        return (Package)parent;
    }

    /**
     * Get first ancestor of element that is a class or a package.
     *
     * @param el
     *            an element
     * @return its first ancestor that is a class or a package
     */
    private static Element getFirstClassOrPackageOwner(Element el) {
        Element parent = el.getOwner();
        while (parent != null && !(parent instanceof Package) && !(parent instanceof Class)) {
            parent = parent.getOwner();
        }
        return parent;
    }

    /**
     * Create a log entry for a diff merge annotation. States the element to be added/deleted and its
     * immediate ancestry (class + package)
     *
     * @param diff
     * @param isAdd
     */
    public static void logDiffMergeAnnotation(EModelElement diff, boolean isAdd) {
        if (diff instanceof Element) {
            StringBuffer msgBuffer = new StringBuffer(tabbedSpace);
            msgBuffer.append(getFullName(diff));
            msgBuffer.append(" is ");
            msgBuffer.append(isAdd ? "added " : "deleted ");
            Element el = getFirstClassOrPackageOwner((Element)diff);
            if (el != null) {
                msgBuffer.append("within ");
                msgBuffer.append(getFullName(el));
                if (el instanceof Class) {
                    Package pack = getClassPackage((Class)el);
                    msgBuffer.append("in " + getFullName(pack));
                }
            }
            doLog(IStatus.INFO, msgBuffer.toString());
        }
    }

    /**
     * Create a log entry for element creation. sourceContainer , source and comment are optional.
     *
     * @param sourceContainer
     * @param source
     * @param createdElement
     * @param comment
     */
    public static void logCreation(Object sourceContainer, Object source, Object createdElement,
            String comment) {
        StringBuffer msgBuffer = new StringBuffer();
        if (source != null && createdElement != null) {
            String sourceName = getFullName(source);
            String createdFullName = getFullName(createdElement);
            if (sourceName != null && createdFullName != null) {
                // source type and name
                msgBuffer.append(tabbedSpace + sourceName);
                // if a container is given, add its type and name
                if (sourceContainer != null) {
                    String containerName = getFullName(sourceContainer);
                    if (containerName != null) {
                        msgBuffer.append(" within " + containerName);
                    }
                }
                // type and name (if it exists) of the created object
                msgBuffer.append(" was translated as " + createdFullName);
                // add a comment if given
                if (comment != null) {
                    msgBuffer.append(": " + comment);
                }
                doLog(IStatus.INFO, msgBuffer.toString());
                return;
            }
        } else if (createdElement != null) {
            // type and name (if it exists) of the created object
            String sourceName = getFullName(createdElement);
            msgBuffer.append(tabbedSpace + sourceName + " was created");
            // if a container is given, add its type and name
            if (sourceContainer != null) {
                String containerName = getFullName(sourceContainer);
                if (containerName != null) {
                    msgBuffer.append(" within " + containerName);
                }
            }
            // add a comment if given
            if (comment != null) {
                msgBuffer.append(": " + comment);
            }
            doLog(IStatus.INFO, msgBuffer.toString());
            return;
        }
    }

    /**
     * Log the act of a visitor entering an object.
     *
     * @param object
     *            the object
     * @param comment
     *            the comment
     */
    public static void logEntering(Object object, String comment) {
        StringBuffer msgBuffer = new StringBuffer();
        if (object != null) {
            String fullName = getFullName(object);
            if (fullName != null) {
                // type and name (if it exists) of the created object
                msgBuffer.append(tabbedSpace + "Entering " + fullName);
                // add a comment if given
                if (comment != null) {
                    msgBuffer.append(": " + comment);
                }
                doLog(IStatus.INFO, msgBuffer.toString());
                incrementTabbing();
            }
            return;
        }
    }

    /**
     * Log the act of a visitor exiting an object.
     */
    public static void logExiting() {
        decreaseTabbing();
    }

    /**
     * Log a free comment.
     *
     * @param comment
     *            a string comment
     */
    public static void logMessage(String comment) {
        if (comment != null) {
            doLog(IStatus.WARNING, comment);
        }
    }

    /**
     * Log a throwable element.
     *
     * @param throwable
     *            the throwable element
     */
    public static void logThrowable(Throwable throwable) {
        if (logLevel <= IStatus.INFO) {
            String mess = throwable.getMessage();
            if (mess == null) {
                mess = "";
            }
            StringBuffer msgBuffer = new StringBuffer(mess);
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            for (StackTraceElement el : stackTrace) {
                msgBuffer.append("\n" + el.toString());
            }
            doLog(IStatus.ERROR, msgBuffer.toString());
        } else {
            doLog(IStatus.ERROR, throwable.getMessage());
        }
    }

    /**
     * Log a new Status with the given level and message.
     *
     * @param level
     *            the level
     * @param message
     *            a message
     */
    private static void doLog(int level, String message) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        if (message != null && level >= logLevel) {
            logger.log(new Status(level, ReversePlugin.getId(), date + ": " + message));
        }
    }

    /**
     * Return the name of the createdElement.
     *
     * @param createdElement
     * @return the name
     */
    private static String getUMLName(Element createdElement) {
        // TODO handle relationships
        if (createdElement instanceof NamedElement) {
            return ((NamedElement)createdElement).getName();
        } else {
            return "unnamed";
        }
    }

    /**
     * Returns the name of the node.
     *
     * @param node
     * @return the name
     */
    private static String getAstName(ASTNode node) {
        String name = null;
        switch (node.getNodeType()) {
            case ASTNode.COMPILATION_UNIT:
                name = ((CompilationUnit)node).getJavaElement().getElementName();
                break;
            case ASTNode.METHOD_DECLARATION:
                name = ((MethodDeclaration)node).getName().getIdentifier();
                break;
            case ASTNode.METHOD_INVOCATION:
                name = ((MethodInvocation)node).getName().getIdentifier();
                break;
            case ASTNode.VARIABLE_DECLARATION_STATEMENT:
                // TODO can we improve on this?
                name = ((VariableDeclarationStatement)node).toString().trim();
                break;
            case ASTNode.EXPRESSION_STATEMENT:
                // TODO can we improve on this?
                name = ((ExpressionStatement)node).toString().trim();
                break;
            default:
                name = node.toString().trim();
        }
        return name;
    }

    /**
     * Returns the name of the element.
     *
     * @param element
     * @return the name
     */
    private static String getTypeName(Object element) {
        String className = element.getClass().getSimpleName();
        return className == null ? "element" : className;
    }

    /**
     * Set minimum logging level.
     *
     * @param iLogLevel
     *            the desired minimum logging level
     */
    public static void setLogLevel(int iLogLevel) {
        logLevel = iLogLevel;
    }

}
