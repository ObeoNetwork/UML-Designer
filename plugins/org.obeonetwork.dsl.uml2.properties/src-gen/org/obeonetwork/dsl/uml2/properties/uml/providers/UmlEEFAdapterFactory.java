/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.providers;

import org.eclipse.emf.common.notify.Adapter;

import org.eclipse.uml2.uml.util.UMLAdapterFactory;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 * @generated
 */
public class UmlEEFAdapterFactory extends UMLAdapterFactory {

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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createPackageAdapter()
   * @generated
   */
  public Adapter createPackageAdapter() {
    return new Package_PropertiesEditionProvider();
  }

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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createElementImportAdapter()
   * @generated
   */
  public Adapter createElementImportAdapter() {
    return new ElementImportPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createPackageImportAdapter()
   * @generated
   */
  public Adapter createPackageImportAdapter() {
    return new PackageImportPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createConstraintAdapter()
   * @generated
   */
  public Adapter createConstraintAdapter() {
    return new ConstraintPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTemplateBindingAdapter()
   * @generated
   */
  public Adapter createTemplateBindingAdapter() {
    return new TemplateBindingPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTemplateSignatureAdapter()
   * @generated
   */
  public Adapter createTemplateSignatureAdapter() {
    return new TemplateSignaturePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTemplateParameterAdapter()
   * @generated
   */
  public Adapter createTemplateParameterAdapter() {
    return new TemplateParameterPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTemplateParameterSubstitutionAdapter()
   * @generated
   */
  public Adapter createTemplateParameterSubstitutionAdapter() {
    return new TemplateParameterSubstitutionPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createGeneralizationSetAdapter()
   * @generated
   */
  public Adapter createGeneralizationSetAdapter() {
    return new GeneralizationSetPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createAbstractionAdapter()
   * @generated
   */
  public Adapter createAbstractionAdapter() {
    return new AbstractionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createRealizationAdapter()
   * @generated
   */
  public Adapter createRealizationAdapter() {
    return new RealizationPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createSubstitutionAdapter()
   * @generated
   */
  public Adapter createSubstitutionAdapter() {
    return new SubstitutionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createOpaqueExpressionAdapter()
   * @generated
   */
  public Adapter createOpaqueExpressionAdapter() {
    return new OpaqueExpressionPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createConnectorEndAdapter()
   * @generated
   */
  public Adapter createConnectorEndAdapter() {
    return new ConnectorEndPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDeploymentAdapter()
   * @generated
   */
  public Adapter createDeploymentAdapter() {
    return new DeploymentPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDeploymentSpecificationAdapter()
   * @generated
   */
  public Adapter createDeploymentSpecificationAdapter() {
    return new DeploymentSpecificationPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createManifestationAdapter()
   * @generated
   */
  public Adapter createManifestationAdapter() {
    return new ManifestationPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createClassAdapter()
   * @generated
   */
  public Adapter createClassAdapter() {
    return new Class_PropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInterfaceRealizationAdapter()
   * @generated
   */
  public Adapter createInterfaceRealizationAdapter() {
    return new InterfaceRealizationPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReceptionAdapter()
   * @generated
   */
  public Adapter createReceptionAdapter() {
    return new ReceptionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createSignalAdapter()
   * @generated
   */
  public Adapter createSignalAdapter() {
    return new SignalPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createProtocolStateMachineAdapter()
   * @generated
   */
  public Adapter createProtocolStateMachineAdapter() {
    return new ProtocolStateMachinePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createRegionAdapter()
   * @generated
   */
  public Adapter createRegionAdapter() {
    return new RegionPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTriggerAdapter()
   * @generated
   */
  public Adapter createTriggerAdapter() {
    return new TriggerPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createStateAdapter()
   * @generated
   */
  public Adapter createStateAdapter() {
    return new StatePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createConnectionPointReferenceAdapter()
   * @generated
   */
  public Adapter createConnectionPointReferenceAdapter() {
    return new ConnectionPointReferencePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createPseudostateAdapter()
   * @generated
   */
  public Adapter createPseudostateAdapter() {
    return new PseudostatePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createProtocolConformanceAdapter()
   * @generated
   */
  public Adapter createProtocolConformanceAdapter() {
    return new ProtocolConformancePropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExtensionAdapter()
   * @generated
   */
  public Adapter createExtensionAdapter() {
    return new ExtensionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExtensionEndAdapter()
   * @generated
   */
  public Adapter createExtensionEndAdapter() {
    return new ExtensionEndPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createImageAdapter()
   * @generated
   */
  public Adapter createImageAdapter() {
    return new ImagePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createProfileAdapter()
   * @generated
   */
  public Adapter createProfileAdapter() {
    return new ProfilePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createModelAdapter()
   * @generated
   */
  public Adapter createModelAdapter() {
    return new ModelPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createParameterSetAdapter()
   * @generated
   */
  public Adapter createParameterSetAdapter() {
    return new ParameterSetPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createOperationTemplateParameterAdapter()
   * @generated
   */
  public Adapter createOperationTemplateParameterAdapter() {
    return new OperationTemplateParameterPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createConnectableElementTemplateParameterAdapter()
   * @generated
   */
  public Adapter createConnectableElementTemplateParameterAdapter() {
    return new ConnectableElementTemplateParameterPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCollaborationUseAdapter()
   * @generated
   */
  public Adapter createCollaborationUseAdapter() {
    return new CollaborationUsePropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createUseCaseAdapter()
   * @generated
   */
  public Adapter createUseCaseAdapter() {
    return new UseCasePropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExtendAdapter()
   * @generated
   */
  public Adapter createExtendAdapter() {
    return new ExtendPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExtensionPointAdapter()
   * @generated
   */
  public Adapter createExtensionPointAdapter() {
    return new ExtensionPointPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createRedefinableTemplateSignatureAdapter()
   * @generated
   */
  public Adapter createRedefinableTemplateSignatureAdapter() {
    return new RedefinableTemplateSignaturePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createClassifierTemplateParameterAdapter()
   * @generated
   */
  public Adapter createClassifierTemplateParameterAdapter() {
    return new ClassifierTemplateParameterPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExpressionAdapter()
   * @generated
   */
  public Adapter createExpressionAdapter() {
    return new ExpressionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createStringExpressionAdapter()
   * @generated
   */
  public Adapter createStringExpressionAdapter() {
    return new StringExpressionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createUsageAdapter()
   * @generated
   */
  public Adapter createUsageAdapter() {
    return new UsagePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createPackageMergeAdapter()
   * @generated
   */
  public Adapter createPackageMergeAdapter() {
    return new PackageMergePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createProfileApplicationAdapter()
   * @generated
   */
  public Adapter createProfileApplicationAdapter() {
    return new ProfileApplicationPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInstanceSpecificationAdapter()
   * @generated
   */
  public Adapter createInstanceSpecificationAdapter() {
    return new InstanceSpecificationPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createSlotAdapter()
   * @generated
   */
  public Adapter createSlotAdapter() {
    return new SlotPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createLiteralIntegerAdapter()
   * @generated
   */
  public Adapter createLiteralIntegerAdapter() {
    return new LiteralIntegerPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createLiteralStringAdapter()
   * @generated
   */
  public Adapter createLiteralStringAdapter() {
    return new LiteralStringPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createLiteralBooleanAdapter()
   * @generated
   */
  public Adapter createLiteralBooleanAdapter() {
    return new LiteralBooleanPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createLiteralNullAdapter()
   * @generated
   */
  public Adapter createLiteralNullAdapter() {
    return new LiteralNullPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInstanceValueAdapter()
   * @generated
   */
  public Adapter createInstanceValueAdapter() {
    return new InstanceValuePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createLiteralUnlimitedNaturalAdapter()
   * @generated
   */
  public Adapter createLiteralUnlimitedNaturalAdapter() {
    return new LiteralUnlimitedNaturalPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createOpaqueBehaviorAdapter()
   * @generated
   */
  public Adapter createOpaqueBehaviorAdapter() {
    return new OpaqueBehaviorPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createFunctionBehaviorAdapter()
   * @generated
   */
  public Adapter createFunctionBehaviorAdapter() {
    return new FunctionBehaviorPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createOpaqueActionAdapter()
   * @generated
   */
  public Adapter createOpaqueActionAdapter() {
    return new OpaqueActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createStructuredActivityNodeAdapter()
   * @generated
   */
  public Adapter createStructuredActivityNodeAdapter() {
    return new StructuredActivityNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createActivityAdapter()
   * @generated
   */
  public Adapter createActivityAdapter() {
    return new ActivityPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createVariableAdapter()
   * @generated
   */
  public Adapter createVariableAdapter() {
    return new VariablePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createActivityPartitionAdapter()
   * @generated
   */
  public Adapter createActivityPartitionAdapter() {
    return new ActivityPartitionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInterruptibleActivityRegionAdapter()
   * @generated
   */
  public Adapter createInterruptibleActivityRegionAdapter() {
    return new InterruptibleActivityRegionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExceptionHandlerAdapter()
   * @generated
   */
  public Adapter createExceptionHandlerAdapter() {
    return new ExceptionHandlerPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createPinAdapter()
   * @generated
   */
  public Adapter createPinAdapter() {
    return new PinPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createOutputPinAdapter()
   * @generated
   */
  public Adapter createOutputPinAdapter() {
    return new OutputPinPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInputPinAdapter()
   * @generated
   */
  public Adapter createInputPinAdapter() {
    return new InputPinPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createSendSignalActionAdapter()
   * @generated
   */
  public Adapter createSendSignalActionAdapter() {
    return new SendSignalActionPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCallBehaviorActionAdapter()
   * @generated
   */
  public Adapter createCallBehaviorActionAdapter() {
    return new CallBehaviorActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createSequenceNodeAdapter()
   * @generated
   */
  public Adapter createSequenceNodeAdapter() {
    return new SequenceNodePropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInitialNodeAdapter()
   * @generated
   */
  public Adapter createInitialNodeAdapter() {
    return new InitialNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createActivityParameterNodeAdapter()
   * @generated
   */
  public Adapter createActivityParameterNodeAdapter() {
    return new ActivityParameterNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createValuePinAdapter()
   * @generated
   */
  public Adapter createValuePinAdapter() {
    return new ValuePinPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createMessageAdapter()
   * @generated
   */
  public Adapter createMessageAdapter() {
    return new MessagePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInteractionAdapter()
   * @generated
   */
  public Adapter createInteractionAdapter() {
    return new InteractionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createLifelineAdapter()
   * @generated
   */
  public Adapter createLifelineAdapter() {
    return new LifelinePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInteractionUseAdapter()
   * @generated
   */
  public Adapter createInteractionUseAdapter() {
    return new InteractionUsePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createPartDecompositionAdapter()
   * @generated
   */
  public Adapter createPartDecompositionAdapter() {
    return new PartDecompositionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createGateAdapter()
   * @generated
   */
  public Adapter createGateAdapter() {
    return new GatePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createGeneralOrderingAdapter()
   * @generated
   */
  public Adapter createGeneralOrderingAdapter() {
    return new GeneralOrderingPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createOccurrenceSpecificationAdapter()
   * @generated
   */
  public Adapter createOccurrenceSpecificationAdapter() {
    return new OccurrenceSpecificationPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInteractionOperandAdapter()
   * @generated
   */
  public Adapter createInteractionOperandAdapter() {
    return new InteractionOperandPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInteractionConstraintAdapter()
   * @generated
   */
  public Adapter createInteractionConstraintAdapter() {
    return new InteractionConstraintPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createStateInvariantAdapter()
   * @generated
   */
  public Adapter createStateInvariantAdapter() {
    return new StateInvariantPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createActionExecutionSpecificationAdapter()
   * @generated
   */
  public Adapter createActionExecutionSpecificationAdapter() {
    return new ActionExecutionSpecificationPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createBehaviorExecutionSpecificationAdapter()
   * @generated
   */
  public Adapter createBehaviorExecutionSpecificationAdapter() {
    return new BehaviorExecutionSpecificationPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExecutionEventAdapter()
   * @generated
   */
  public Adapter createExecutionEventAdapter() {
    return new ExecutionEventPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCreationEventAdapter()
   * @generated
   */
  public Adapter createCreationEventAdapter() {
    return new CreationEventPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDestructionEventAdapter()
   * @generated
   */
  public Adapter createDestructionEventAdapter() {
    return new DestructionEventPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createSendOperationEventAdapter()
   * @generated
   */
  public Adapter createSendOperationEventAdapter() {
    return new SendOperationEventPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createSendSignalEventAdapter()
   * @generated
   */
  public Adapter createSendSignalEventAdapter() {
    return new SendSignalEventPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createMessageOccurrenceSpecificationAdapter()
   * @generated
   */
  public Adapter createMessageOccurrenceSpecificationAdapter() {
    return new MessageOccurrenceSpecificationPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExecutionOccurrenceSpecificationAdapter()
   * @generated
   */
  public Adapter createExecutionOccurrenceSpecificationAdapter() {
    return new ExecutionOccurrenceSpecificationPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReceiveOperationEventAdapter()
   * @generated
   */
  public Adapter createReceiveOperationEventAdapter() {
    return new ReceiveOperationEventPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReceiveSignalEventAdapter()
   * @generated
   */
  public Adapter createReceiveSignalEventAdapter() {
    return new ReceiveSignalEventPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCallEventAdapter()
   * @generated
   */
  public Adapter createCallEventAdapter() {
    return new CallEventPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createChangeEventAdapter()
   * @generated
   */
  public Adapter createChangeEventAdapter() {
    return new ChangeEventPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createSignalEventAdapter()
   * @generated
   */
  public Adapter createSignalEventAdapter() {
    return new SignalEventPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createAnyReceiveEventAdapter()
   * @generated
   */
  public Adapter createAnyReceiveEventAdapter() {
    return new AnyReceiveEventPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createForkNodeAdapter()
   * @generated
   */
  public Adapter createForkNodeAdapter() {
    return new ForkNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createFlowFinalNodeAdapter()
   * @generated
   */
  public Adapter createFlowFinalNodeAdapter() {
    return new FlowFinalNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCentralBufferNodeAdapter()
   * @generated
   */
  public Adapter createCentralBufferNodeAdapter() {
    return new CentralBufferNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createMergeNodeAdapter()
   * @generated
   */
  public Adapter createMergeNodeAdapter() {
    return new MergeNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDecisionNodeAdapter()
   * @generated
   */
  public Adapter createDecisionNodeAdapter() {
    return new DecisionNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createObjectFlowAdapter()
   * @generated
   */
  public Adapter createObjectFlowAdapter() {
    return new ObjectFlowPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createActivityFinalNodeAdapter()
   * @generated
   */
  public Adapter createActivityFinalNodeAdapter() {
    return new ActivityFinalNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createComponentRealizationAdapter()
   * @generated
   */
  public Adapter createComponentRealizationAdapter() {
    return new ComponentRealizationPropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createNodeAdapter()
   * @generated
   */
  public Adapter createNodeAdapter() {
    return new NodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCommunicationPathAdapter()
   * @generated
   */
  public Adapter createCommunicationPathAdapter() {
    return new CommunicationPathPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDeviceAdapter()
   * @generated
   */
  public Adapter createDeviceAdapter() {
    return new DevicePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExecutionEnvironmentAdapter()
   * @generated
   */
  public Adapter createExecutionEnvironmentAdapter() {
    return new ExecutionEnvironmentPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCombinedFragmentAdapter()
   * @generated
   */
  public Adapter createCombinedFragmentAdapter() {
    return new CombinedFragmentPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createContinuationAdapter()
   * @generated
   */
  public Adapter createContinuationAdapter() {
    return new ContinuationPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createConsiderIgnoreFragmentAdapter()
   * @generated
   */
  public Adapter createConsiderIgnoreFragmentAdapter() {
    return new ConsiderIgnoreFragmentPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCreateObjectActionAdapter()
   * @generated
   */
  public Adapter createCreateObjectActionAdapter() {
    return new CreateObjectActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDestroyObjectActionAdapter()
   * @generated
   */
  public Adapter createDestroyObjectActionAdapter() {
    return new DestroyObjectActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTestIdentityActionAdapter()
   * @generated
   */
  public Adapter createTestIdentityActionAdapter() {
    return new TestIdentityActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReadSelfActionAdapter()
   * @generated
   */
  public Adapter createReadSelfActionAdapter() {
    return new ReadSelfActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReadStructuralFeatureActionAdapter()
   * @generated
   */
  public Adapter createReadStructuralFeatureActionAdapter() {
    return new ReadStructuralFeatureActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createClearStructuralFeatureActionAdapter()
   * @generated
   */
  public Adapter createClearStructuralFeatureActionAdapter() {
    return new ClearStructuralFeatureActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createRemoveStructuralFeatureValueActionAdapter()
   * @generated
   */
  public Adapter createRemoveStructuralFeatureValueActionAdapter() {
    return new RemoveStructuralFeatureValueActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createAddStructuralFeatureValueActionAdapter()
   * @generated
   */
  public Adapter createAddStructuralFeatureValueActionAdapter() {
    return new AddStructuralFeatureValueActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createLinkEndDataAdapter()
   * @generated
   */
  public Adapter createLinkEndDataAdapter() {
    return new LinkEndDataPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createQualifierValueAdapter()
   * @generated
   */
  public Adapter createQualifierValueAdapter() {
    return new QualifierValuePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReadLinkActionAdapter()
   * @generated
   */
  public Adapter createReadLinkActionAdapter() {
    return new ReadLinkActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createLinkEndCreationDataAdapter()
   * @generated
   */
  public Adapter createLinkEndCreationDataAdapter() {
    return new LinkEndCreationDataPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCreateLinkActionAdapter()
   * @generated
   */
  public Adapter createCreateLinkActionAdapter() {
    return new CreateLinkActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDestroyLinkActionAdapter()
   * @generated
   */
  public Adapter createDestroyLinkActionAdapter() {
    return new DestroyLinkActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createLinkEndDestructionDataAdapter()
   * @generated
   */
  public Adapter createLinkEndDestructionDataAdapter() {
    return new LinkEndDestructionDataPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createClearAssociationActionAdapter()
   * @generated
   */
  public Adapter createClearAssociationActionAdapter() {
    return new ClearAssociationActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createBroadcastSignalActionAdapter()
   * @generated
   */
  public Adapter createBroadcastSignalActionAdapter() {
    return new BroadcastSignalActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createSendObjectActionAdapter()
   * @generated
   */
  public Adapter createSendObjectActionAdapter() {
    return new SendObjectActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createValueSpecificationActionAdapter()
   * @generated
   */
  public Adapter createValueSpecificationActionAdapter() {
    return new ValueSpecificationActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTimeExpressionAdapter()
   * @generated
   */
  public Adapter createTimeExpressionAdapter() {
    return new TimeExpressionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDurationAdapter()
   * @generated
   */
  public Adapter createDurationAdapter() {
    return new DurationPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createIntervalAdapter()
   * @generated
   */
  public Adapter createIntervalAdapter() {
    return new IntervalPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDurationIntervalAdapter()
   * @generated
   */
  public Adapter createDurationIntervalAdapter() {
    return new DurationIntervalPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createIntervalConstraintAdapter()
   * @generated
   */
  public Adapter createIntervalConstraintAdapter() {
    return new IntervalConstraintPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTimeConstraintAdapter()
   * @generated
   */
  public Adapter createTimeConstraintAdapter() {
    return new TimeConstraintPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTimeIntervalAdapter()
   * @generated
   */
  public Adapter createTimeIntervalAdapter() {
    return new TimeIntervalPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDurationConstraintAdapter()
   * @generated
   */
  public Adapter createDurationConstraintAdapter() {
    return new DurationConstraintPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTimeObservationAdapter()
   * @generated
   */
  public Adapter createTimeObservationAdapter() {
    return new TimeObservationPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createDurationObservationAdapter()
   * @generated
   */
  public Adapter createDurationObservationAdapter() {
    return new DurationObservationPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createFinalStateAdapter()
   * @generated
   */
  public Adapter createFinalStateAdapter() {
    return new FinalStatePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createTimeEventAdapter()
   * @generated
   */
  public Adapter createTimeEventAdapter() {
    return new TimeEventPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReadVariableActionAdapter()
   * @generated
   */
  public Adapter createReadVariableActionAdapter() {
    return new ReadVariableActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createClearVariableActionAdapter()
   * @generated
   */
  public Adapter createClearVariableActionAdapter() {
    return new ClearVariableActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createAddVariableValueActionAdapter()
   * @generated
   */
  public Adapter createAddVariableValueActionAdapter() {
    return new AddVariableValueActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createRemoveVariableValueActionAdapter()
   * @generated
   */
  public Adapter createRemoveVariableValueActionAdapter() {
    return new RemoveVariableValueActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createRaiseExceptionActionAdapter()
   * @generated
   */
  public Adapter createRaiseExceptionActionAdapter() {
    return new RaiseExceptionActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createActionInputPinAdapter()
   * @generated
   */
  public Adapter createActionInputPinAdapter() {
    return new ActionInputPinPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInformationItemAdapter()
   * @generated
   */
  public Adapter createInformationItemAdapter() {
    return new InformationItemPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createInformationFlowAdapter()
   * @generated
   */
  public Adapter createInformationFlowAdapter() {
    return new InformationFlowPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReadExtentActionAdapter()
   * @generated
   */
  public Adapter createReadExtentActionAdapter() {
    return new ReadExtentActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReclassifyObjectActionAdapter()
   * @generated
   */
  public Adapter createReclassifyObjectActionAdapter() {
    return new ReclassifyObjectActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReadIsClassifiedObjectActionAdapter()
   * @generated
   */
  public Adapter createReadIsClassifiedObjectActionAdapter() {
    return new ReadIsClassifiedObjectActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createStartClassifierBehaviorActionAdapter()
   * @generated
   */
  public Adapter createStartClassifierBehaviorActionAdapter() {
    return new StartClassifierBehaviorActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReadLinkObjectEndActionAdapter()
   * @generated
   */
  public Adapter createReadLinkObjectEndActionAdapter() {
    return new ReadLinkObjectEndActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReadLinkObjectEndQualifierActionAdapter()
   * @generated
   */
  public Adapter createReadLinkObjectEndQualifierActionAdapter() {
    return new ReadLinkObjectEndQualifierActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createCreateLinkObjectActionAdapter()
   * @generated
   */
  public Adapter createCreateLinkObjectActionAdapter() {
    return new CreateLinkObjectActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createAcceptEventActionAdapter()
   * @generated
   */
  public Adapter createAcceptEventActionAdapter() {
    return new AcceptEventActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createAcceptCallActionAdapter()
   * @generated
   */
  public Adapter createAcceptCallActionAdapter() {
    return new AcceptCallActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReplyActionAdapter()
   * @generated
   */
  public Adapter createReplyActionAdapter() {
    return new ReplyActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createUnmarshallActionAdapter()
   * @generated
   */
  public Adapter createUnmarshallActionAdapter() {
    return new UnmarshallActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createReduceActionAdapter()
   * @generated
   */
  public Adapter createReduceActionAdapter() {
    return new ReduceActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createStartObjectBehaviorActionAdapter()
   * @generated
   */
  public Adapter createStartObjectBehaviorActionAdapter() {
    return new StartObjectBehaviorActionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createJoinNodeAdapter()
   * @generated
   */
  public Adapter createJoinNodeAdapter() {
    return new JoinNodePropertiesEditionProvider();
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
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createConditionalNodeAdapter()
   * @generated
   */
  public Adapter createConditionalNodeAdapter() {
    return new ConditionalNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createClauseAdapter()
   * @generated
   */
  public Adapter createClauseAdapter() {
    return new ClausePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createLoopNodeAdapter()
   * @generated
   */
  public Adapter createLoopNodeAdapter() {
    return new LoopNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExpansionNodeAdapter()
   * @generated
   */
  public Adapter createExpansionNodeAdapter() {
    return new ExpansionNodePropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createExpansionRegionAdapter()
   * @generated
   */
  public Adapter createExpansionRegionAdapter() {
    return new ExpansionRegionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createProtocolTransitionAdapter()
   * @generated
   */
  public Adapter createProtocolTransitionAdapter() {
    return new ProtocolTransitionPropertiesEditionProvider();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.uml2.uml.util.UMLAdapterFactory#createAssociationClassAdapter()
   * @generated
   */
  public Adapter createAssociationClassAdapter() {
    return new AssociationClassPropertiesEditionProvider();
  }

}
