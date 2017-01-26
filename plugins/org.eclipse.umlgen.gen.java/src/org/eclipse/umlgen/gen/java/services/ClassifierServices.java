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
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * Various services for the classifiers.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 2.0
 */
public class ClassifierServices {

    /**
     * get all inherited operations from the given UML class.
     *
     * @param aClass
     *            The UML class.
     * @return The list of operations.
     */
    public List<Operation> getAllInheritedOperations(org.eclipse.uml2.uml.Class aClass) {
        LinkedHashSet<Operation> inheritedOperations = new LinkedHashSet<Operation>();

        // Everything inherited from the classes
        List<Class> allGeneralizedClasses = this.getAllGeneralizedClasses(aClass);
        for (Class aGeneralizedClass : allGeneralizedClasses) {
            List<Operation> operations = aGeneralizedClass.getOperations();
            for (Operation operation : operations) {
                if (operation.isAbstract()) {
                    inheritedOperations.add(operation);
                }
            }
        }

        List<Interface> implementedInterfaces = aClass.getImplementedInterfaces();
        for (Interface anInterface : implementedInterfaces) {
            inheritedOperations.addAll(anInterface.getAllOperations());
        }

        List<Operation> operationsToRemove = new ArrayList<Operation>();
        for (Operation inheritedOperation : inheritedOperations) {
            boolean shouldRemoveOperation = false;

            // See if it's not implemented
            List<Operation> ownedOperations = aClass.getOwnedOperations();
            for (Operation ownedOperation : ownedOperations) {
                if (this.areEqual(ownedOperation, inheritedOperation)) {
                    shouldRemoveOperation = true;
                    break;
                }
            }

            // See if a parent is not implementing it
            if (!shouldRemoveOperation) {
                for (Class aGeneralizedClass : allGeneralizedClasses) {
                    List<Operation> generalizedClassOperations = aGeneralizedClass.getOwnedOperations();
                    for (Operation generalizedClassOperation : generalizedClassOperations) {
                        if (generalizedClassOperation != inheritedOperation
                                && this.canOverride(generalizedClassOperation, inheritedOperation)
                                && this.areEqual(generalizedClassOperation, inheritedOperation)) {
                            shouldRemoveOperation = true;
                            break;
                        }
                    }
                }
            }

            // See if we don't have the same operation to implement with a stronger visibility
            // public > package > protected > private
            if (!shouldRemoveOperation) {
                for (Operation otherInheritedOperation : inheritedOperations) {
                    if (this.areEqual(inheritedOperation, otherInheritedOperation)) {
                        if (VisibilityKind.PUBLIC_LITERAL.equals(otherInheritedOperation.getVisibility())
                                && !VisibilityKind.PUBLIC_LITERAL.equals(inheritedOperation.getVisibility())) {
                            // Public > everything
                            shouldRemoveOperation = true;
                            break;
                        } else if (VisibilityKind.PACKAGE_LITERAL.equals(otherInheritedOperation
                                .getVisibility())
                                && !VisibilityKind.PUBLIC_LITERAL.equals(inheritedOperation.getVisibility())) {
                            // Package > protected and private only
                            shouldRemoveOperation = true;
                            break;
                        } else if (VisibilityKind.PROTECTED_LITERAL.equals(otherInheritedOperation
                                .getVisibility())
                                && VisibilityKind.PRIVATE_LITERAL.equals(inheritedOperation.getVisibility())) {
                            // Protected > private only
                            shouldRemoveOperation = true;
                            break;
                        }
                    }
                }
            }

            if (shouldRemoveOperation) {
                operationsToRemove.add(inheritedOperation);
            }
        }
        inheritedOperations.removeAll(operationsToRemove);

        List<Operation> operations = new ArrayList<Operation>();
        operations.addAll(inheritedOperations);
        return operations;
    }

    /**
     * Check if the given generalized UML operation can be overridden.
     *
     * @param generalizedClassOperation
     *            the generalized UML operation.
     * @param inheritedOperation
     *            a inherited operation.
     * @return True if it can be overridden.
     */
    private boolean canOverride(Operation generalizedClassOperation, Operation inheritedOperation) {
        return !generalizedClassOperation.isAbstract()
                && !VisibilityKind.PRIVATE_LITERAL.equals(generalizedClassOperation.getVisibility());
    }

    /**
     * Check if the two given UML operation seems to be equal.
     *
     * @param generalizedClassOperation
     *            The first operation.
     * @param inheritedOperation
     *            The second one.
     * @return True if equal.
     */
    // CHECKSTYLE:OFF
    private boolean areEqual(Operation generalizedClassOperation, Operation inheritedOperation) {
        // CHECKSTYLE:ON
        if (generalizedClassOperation.getName() != null
                && generalizedClassOperation.getName().equals(inheritedOperation.getName())) {
            EList<Parameter> generalizedClassOperationParameters = generalizedClassOperation
                    .getOwnedParameters();
            int generalizedClassOperationParametersSize = generalizedClassOperationParameters.size();
            EList<Parameter> inheritedOperationParameters = inheritedOperation.getOwnedParameters();

            EList<Parameter> returnResult = generalizedClassOperation.returnResult();
            int returnResultSize = returnResult.size();
            EList<Parameter> inheritedOperationReturnResult = inheritedOperation.returnResult();

            if (generalizedClassOperationParametersSize == inheritedOperationParameters.size()
                    && returnResultSize == inheritedOperationReturnResult.size()) {

                for (int i = 0; i < generalizedClassOperationParametersSize; i++) {
                    Type inheritedOperationParameterType = inheritedOperationParameters.get(i).getType();
                    Type generalizedClassOperationParameterType = generalizedClassOperationParameters.get(i)
                            .getType();

                    // CHECKSTYLE:OFF
                    if (inheritedOperationParameterType == null) {
                        if (generalizedClassOperationParameterType != null) {
                            return false;
                        }
                    } else {
                        if (!inheritedOperationParameterType
                                .conformsTo(generalizedClassOperationParameterType)) {
                            return false;
                        }
                    }
                    // CHECKSTYLE:ON
                }

                for (int i = 0; i < returnResultSize; i++) {
                    Type inheritedOperationReturnResultType = inheritedOperationReturnResult.get(i).getType();
                    Type returnResultType = returnResult.get(i).getType();

                    // CHECKSTYLE:OFF
                    if (inheritedOperationReturnResultType == null) {
                        if (returnResultType != null) {
                            return false;
                        }
                    } else {
                        if (!inheritedOperationReturnResultType.conformsTo(returnResultType)) {
                            return false;
                        }
                    }
                    // CHECKSTYLE:ON
                }

                return true;
            }
        }

        return false;
    }

    /**
     * get all generalized UML classes from the given UML class.
     *
     * @param aClass
     *            The UML class.
     * @return The list of classes.
     */
    private List<org.eclipse.uml2.uml.Class> getAllGeneralizedClasses(org.eclipse.uml2.uml.Class aClass) {
        List<org.eclipse.uml2.uml.Class> generalizedClass = new ArrayList<org.eclipse.uml2.uml.Class>();

        List<Generalization> generalizations = aClass.getGeneralizations();
        for (Generalization generalization : generalizations) {
            Classifier aClassifier = generalization.getGeneral();
            if (aClassifier instanceof org.eclipse.uml2.uml.Class) {
                org.eclipse.uml2.uml.Class anInheritedClass = (org.eclipse.uml2.uml.Class)aClassifier;
                generalizedClass.add(anInheritedClass);
                generalizedClass.addAll(this.getAllGeneralizedClasses(anInheritedClass));
            }
        }

        return generalizedClass;
    }
}
