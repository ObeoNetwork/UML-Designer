/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.providers;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.VisibilityKind;

import org.eclipse.emf.common.notify.Adapter;

import org.eclipse.uml2.uml.util.UMLAdapterFactory;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class UmlEEFAdapterFactory extends UMLAdapterFactory {

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDependencyAdapter()
	 * @generated
	 */
	public Adapter createDependencyAdapter() {
		return new DependencyPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCommentAdapter()
	 * @generated
	 */
	public Adapter createCommentAdapter() {
		return new CommentPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createClassAdapter()
	 * @generated
	 */
	public Adapter createClassAdapter() {
		return new Class_PropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createStereotypeAdapter()
	 * @generated
	 */
	public Adapter createStereotypeAdapter() {
		return new StereotypePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createPackageAdapter()
	 * @generated
	 */
	public Adapter createPackageAdapter() {
		return new Package_PropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createAssociationAdapter()
	 * @generated
	 */
	public Adapter createAssociationAdapter() {
		return new AssociationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createPropertyAdapter()
	 * @generated
	 */
	public Adapter createPropertyAdapter() {
		return new PropertyPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createConnectorEndAdapter()
	 * @generated
	 */
	public Adapter createConnectorEndAdapter() {
		return new ConnectorEndPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createArtifactAdapter()
	 * @generated
	 */
	public Adapter createArtifactAdapter() {
		return new ArtifactPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createOperationAdapter()
	 * @generated
	 */
	public Adapter createOperationAdapter() {
		return new OperationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInterfaceAdapter()
	 * @generated
	 */
	public Adapter createInterfaceAdapter() {
		return new Interface_PropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createStateMachineAdapter()
	 * @generated
	 */
	public Adapter createStateMachineAdapter() {
		return new StateMachinePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createVertexAdapter()
	 * @generated
	 */
	public Adapter createVertexAdapter() {
		return new VertexPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createPortAdapter()
	 * @generated
	 */
	public Adapter createPortAdapter() {
		return new PortPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTransitionAdapter()
	 * @generated
	 */
	public Adapter createTransitionAdapter() {
		return new TransitionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createParameterAdapter()
	 * @generated
	 */
	public Adapter createParameterAdapter() {
		return new ParameterPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDataTypeAdapter()
	 * @generated
	 */
	public Adapter createDataTypeAdapter() {
		return new DataTypePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createEnumerationAdapter()
	 * @generated
	 */
	public Adapter createEnumerationAdapter() {
		return new EnumerationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createEnumerationLiteralAdapter()
	 * @generated
	 */
	public Adapter createEnumerationLiteralAdapter() {
		return new EnumerationLiteralPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createPrimitiveTypeAdapter()
	 * @generated
	 */
	public Adapter createPrimitiveTypeAdapter() {
		return new PrimitiveTypePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCollaborationAdapter()
	 * @generated
	 */
	public Adapter createCollaborationAdapter() {
		return new CollaborationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createGeneralizationAdapter()
	 * @generated
	 */
	public Adapter createGeneralizationAdapter() {
		return new GeneralizationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExtendAdapter()
	 * @generated
	 */
	public Adapter createExtendAdapter() {
		return new ExtendPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createIncludeAdapter()
	 * @generated
	 */
	public Adapter createIncludeAdapter() {
		return new IncludePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createConnectorAdapter()
	 * @generated
	 */
	public Adapter createConnectorAdapter() {
		return new ConnectorPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createControlNodeAdapter()
	 * @generated
	 */
	public Adapter createControlNodeAdapter() {
		return new ControlNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createActorAdapter()
	 * @generated
	 */
	public Adapter createActorAdapter() {
		return new ActorPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createAssociationClassAdapter()
	 * @generated
	 */
	public Adapter createAssociationClassAdapter() {
		return new AssociationClassPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCallOperationActionAdapter()
	 * @generated
	 */
	public Adapter createCallOperationActionAdapter() {
		return new CallOperationActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createComponentAdapter()
	 * @generated
	 */
	public Adapter createComponentAdapter() {
		return new ComponentPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createControlFlowAdapter()
	 * @generated
	 */
	public Adapter createControlFlowAdapter() {
		return new ControlFlowPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDataStoreNodeAdapter()
	 * @generated
	 */
	public Adapter createDataStoreNodeAdapter() {
		return new DataStoreNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createFinalStateAdapter()
	 * @generated
	 */
	public Adapter createFinalStateAdapter() {
		return new FinalStatePropertiesEditionProvider();
	}

}
