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
package org.eclipse.emf.eef.runtime.ui.widgets.referencestable;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Operation;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class OperationsTableSettings extends ReferencesTableSettings {
    protected EObject source;

    private ITableOperations operations;

    public OperationsTableSettings(EObject source, ITableOperations operations) {
        super(source, null);
        this.source = source;
        this.operations = operations;
    }

    public Object[] getValue() {
        return operations.getValues();
    }

    public Object choiceOfValues(AdapterFactory adapterFactory) {
        return operations.choiceOfValues();
    }

    public boolean isAffectingFeature(EStructuralFeature feature) {
        throw new UnsupportedOperationException();
    }

    public boolean isAffectingEvent(Notification notification) {
        return true;
    }

    public EObject getSource() {
        return source;
    }

    public EClassifier getEType() {
        throw new UnsupportedOperationException();
    }

    public EReference getLastReference() {
        throw new UnsupportedOperationException();
    }

    public void move(int newIndex, Operation newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeFromReference(EObject parameter) {
        operations.remove(parameter);
    }

    public void addToReference(EObject newValue) {
        operations.add(newValue);
    }

    @Override
    public boolean contains(EObject toCheck) {
        return false;
    }
}
