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
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class UmlEEFAdapterFactory extends UMLAdapterFactory {

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCommentAdapter()
	 * 
	 */
	public Adapter createCommentAdapter() {
		return new CommentPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createPackageAdapter()
	 * 
	 */
	public Adapter createPackageAdapter() {
		return new Package_PropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDependencyAdapter()
	 * 
	 */
	public Adapter createDependencyAdapter() {
		return new DependencyPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createElementImportAdapter()
	 * 
	 */
	public Adapter createElementImportAdapter() {
		return new ElementImportPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createPackageImportAdapter()
	 * 
	 */
	public Adapter createPackageImportAdapter() {
		return new PackageImportPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createConstraintAdapter()
	 * 
	 */
	public Adapter createConstraintAdapter() {
		return new ConstraintPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createAssociationAdapter()
	 * 
	 */
	public Adapter createAssociationAdapter() {
		return new AssociationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTemplateBindingAdapter()
	 * 
	 */
	public Adapter createTemplateBindingAdapter() {
		return new TemplateBindingPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTemplateSignatureAdapter()
	 * 
	 */
	public Adapter createTemplateSignatureAdapter() {
		return new TemplateSignaturePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTemplateParameterAdapter()
	 * 
	 */
	public Adapter createTemplateParameterAdapter() {
		return new TemplateParameterPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTemplateParameterSubstitutionAdapter()
	 * 
	 */
	public Adapter createTemplateParameterSubstitutionAdapter() {
		return new TemplateParameterSubstitutionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createGeneralizationAdapter()
	 * 
	 */
	public Adapter createGeneralizationAdapter() {
		return new GeneralizationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createGeneralizationSetAdapter()
	 * 
	 */
	public Adapter createGeneralizationSetAdapter() {
		return new GeneralizationSetPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createAbstractionAdapter()
	 * 
	 */
	public Adapter createAbstractionAdapter() {
		return new AbstractionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createRealizationAdapter()
	 * 
	 */
	public Adapter createRealizationAdapter() {
		return new RealizationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createSubstitutionAdapter()
	 * 
	 */
	public Adapter createSubstitutionAdapter() {
		return new SubstitutionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createOpaqueExpressionAdapter()
	 * 
	 */
	public Adapter createOpaqueExpressionAdapter() {
		return new OpaqueExpressionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createParameterAdapter()
	 * 
	 */
	public Adapter createParameterAdapter() {
		return new ParameterPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createConnectorEndAdapter()
	 * 
	 */
	public Adapter createConnectorEndAdapter() {
		return new ConnectorEndPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createPropertyAdapter()
	 * 
	 */
	public Adapter createPropertyAdapter() {
		return new PropertyPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDeploymentAdapter()
	 * 
	 */
	public Adapter createDeploymentAdapter() {
		return new DeploymentPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createArtifactAdapter()
	 * 
	 */
	public Adapter createArtifactAdapter() {
		return new ArtifactPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDeploymentSpecificationAdapter()
	 * 
	 */
	public Adapter createDeploymentSpecificationAdapter() {
		return new DeploymentSpecificationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createManifestationAdapter()
	 * 
	 */
	public Adapter createManifestationAdapter() {
		return new ManifestationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createOperationAdapter()
	 * 
	 */
	public Adapter createOperationAdapter() {
		return new OperationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createClassAdapter()
	 * 
	 */
	public Adapter createClassAdapter() {
		return new Class_PropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInterfaceRealizationAdapter()
	 * 
	 */
	public Adapter createInterfaceRealizationAdapter() {
		return new InterfaceRealizationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInterfaceAdapter()
	 * 
	 */
	public Adapter createInterfaceAdapter() {
		return new Interface_PropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReceptionAdapter()
	 * 
	 */
	public Adapter createReceptionAdapter() {
		return new ReceptionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createSignalAdapter()
	 * 
	 */
	public Adapter createSignalAdapter() {
		return new SignalPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createStateMachineAdapter()
	 * 
	 */
	public Adapter createStateMachineAdapter() {
		return new StateMachinePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createProtocolStateMachineAdapter()
	 * 
	 */
	public Adapter createProtocolStateMachineAdapter() {
		return new ProtocolStateMachinePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createRegionAdapter()
	 * 
	 */
	public Adapter createRegionAdapter() {
		return new RegionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTransitionAdapter()
	 * 
	 */
	public Adapter createTransitionAdapter() {
		return new TransitionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTriggerAdapter()
	 * 
	 */
	public Adapter createTriggerAdapter() {
		return new TriggerPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createPortAdapter()
	 * 
	 */
	public Adapter createPortAdapter() {
		return new PortPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createStateAdapter()
	 * 
	 */
	public Adapter createStateAdapter() {
		return new StatePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createConnectionPointReferenceAdapter()
	 * 
	 */
	public Adapter createConnectionPointReferenceAdapter() {
		return new ConnectionPointReferencePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createPseudostateAdapter()
	 * 
	 */
	public Adapter createPseudostateAdapter() {
		return new PseudostatePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createProtocolConformanceAdapter()
	 * 
	 */
	public Adapter createProtocolConformanceAdapter() {
		return new ProtocolConformancePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createConnectorAdapter()
	 * 
	 */
	public Adapter createConnectorAdapter() {
		return new ConnectorPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createExtensionAdapter()
	 * 
	 */
	public Adapter createExtensionAdapter() {
		return new ExtensionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createExtensionEndAdapter()
	 * 
	 */
	public Adapter createExtensionEndAdapter() {
		return new ExtensionEndPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createStereotypeAdapter()
	 * 
	 */
	public Adapter createStereotypeAdapter() {
		return new StereotypePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createImageAdapter()
	 * 
	 */
	public Adapter createImageAdapter() {
		return new ImagePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createProfileAdapter()
	 * 
	 */
	public Adapter createProfileAdapter() {
		return new ProfilePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createModelAdapter()
	 * 
	 */
	public Adapter createModelAdapter() {
		return new ModelPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createParameterSetAdapter()
	 * 
	 */
	public Adapter createParameterSetAdapter() {
		return new ParameterSetPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDataTypeAdapter()
	 * 
	 */
	public Adapter createDataTypeAdapter() {
		return new DataTypePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createOperationTemplateParameterAdapter()
	 * 
	 */
	public Adapter createOperationTemplateParameterAdapter() {
		return new OperationTemplateParameterPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createConnectableElementTemplateParameterAdapter()
	 * 
	 */
	public Adapter createConnectableElementTemplateParameterAdapter() {
		return new ConnectableElementTemplateParameterPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCollaborationUseAdapter()
	 * 
	 */
	public Adapter createCollaborationUseAdapter() {
		return new CollaborationUsePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCollaborationAdapter()
	 * 
	 */
	public Adapter createCollaborationAdapter() {
		return new CollaborationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createUseCaseAdapter()
	 * 
	 */
	public Adapter createUseCaseAdapter() {
		return new UseCasePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createIncludeAdapter()
	 * 
	 */
	public Adapter createIncludeAdapter() {
		return new IncludePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createExtendAdapter()
	 * 
	 */
	public Adapter createExtendAdapter() {
		return new ExtendPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createExtensionPointAdapter()
	 * 
	 */
	public Adapter createExtensionPointAdapter() {
		return new ExtensionPointPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createRedefinableTemplateSignatureAdapter()
	 * 
	 */
	public Adapter createRedefinableTemplateSignatureAdapter() {
		return new RedefinableTemplateSignaturePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createClassifierTemplateParameterAdapter()
	 * 
	 */
	public Adapter createClassifierTemplateParameterAdapter() {
		return new ClassifierTemplateParameterPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createExpressionAdapter()
	 * 
	 */
	public Adapter createExpressionAdapter() {
		return new ExpressionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createStringExpressionAdapter()
	 * 
	 */
	public Adapter createStringExpressionAdapter() {
		return new StringExpressionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createUsageAdapter()
	 * 
	 */
	public Adapter createUsageAdapter() {
		return new UsagePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createPackageMergeAdapter()
	 * 
	 */
	public Adapter createPackageMergeAdapter() {
		return new PackageMergePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createProfileApplicationAdapter()
	 * 
	 */
	public Adapter createProfileApplicationAdapter() {
		return new ProfileApplicationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createEnumerationAdapter()
	 * 
	 */
	public Adapter createEnumerationAdapter() {
		return new EnumerationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInstanceSpecificationAdapter()
	 * 
	 */
	public Adapter createInstanceSpecificationAdapter() {
		return new InstanceSpecificationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createEnumerationLiteralAdapter()
	 * 
	 */
	public Adapter createEnumerationLiteralAdapter() {
		return new EnumerationLiteralPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createSlotAdapter()
	 * 
	 */
	public Adapter createSlotAdapter() {
		return new SlotPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createPrimitiveTypeAdapter()
	 * 
	 */
	public Adapter createPrimitiveTypeAdapter() {
		return new PrimitiveTypePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createLiteralIntegerAdapter()
	 * 
	 */
	public Adapter createLiteralIntegerAdapter() {
		return new LiteralIntegerPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createLiteralStringAdapter()
	 * 
	 */
	public Adapter createLiteralStringAdapter() {
		return new LiteralStringPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createLiteralBooleanAdapter()
	 * 
	 */
	public Adapter createLiteralBooleanAdapter() {
		return new LiteralBooleanPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createLiteralNullAdapter()
	 * 
	 */
	public Adapter createLiteralNullAdapter() {
		return new LiteralNullPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInstanceValueAdapter()
	 * 
	 */
	public Adapter createInstanceValueAdapter() {
		return new InstanceValuePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createLiteralUnlimitedNaturalAdapter()
	 * 
	 */
	public Adapter createLiteralUnlimitedNaturalAdapter() {
		return new LiteralUnlimitedNaturalPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createOpaqueBehaviorAdapter()
	 * 
	 */
	public Adapter createOpaqueBehaviorAdapter() {
		return new OpaqueBehaviorPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createFunctionBehaviorAdapter()
	 * 
	 */
	public Adapter createFunctionBehaviorAdapter() {
		return new FunctionBehaviorPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createOpaqueActionAdapter()
	 * 
	 */
	public Adapter createOpaqueActionAdapter() {
		return new OpaqueActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createStructuredActivityNodeAdapter()
	 * 
	 */
	public Adapter createStructuredActivityNodeAdapter() {
		return new StructuredActivityNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createActivityAdapter()
	 * 
	 */
	public Adapter createActivityAdapter() {
		return new ActivityPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createVariableAdapter()
	 * 
	 */
	public Adapter createVariableAdapter() {
		return new VariablePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createActivityPartitionAdapter()
	 * 
	 */
	public Adapter createActivityPartitionAdapter() {
		return new ActivityPartitionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInterruptibleActivityRegionAdapter()
	 * 
	 */
	public Adapter createInterruptibleActivityRegionAdapter() {
		return new InterruptibleActivityRegionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createExceptionHandlerAdapter()
	 * 
	 */
	public Adapter createExceptionHandlerAdapter() {
		return new ExceptionHandlerPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createPinAdapter()
	 * 
	 */
	public Adapter createPinAdapter() {
		return new PinPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createOutputPinAdapter()
	 * 
	 */
	public Adapter createOutputPinAdapter() {
		return new OutputPinPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInputPinAdapter()
	 * 
	 */
	public Adapter createInputPinAdapter() {
		return new InputPinPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createSendSignalActionAdapter()
	 * 
	 */
	public Adapter createSendSignalActionAdapter() {
		return new SendSignalActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCallOperationActionAdapter()
	 * 
	 */
	public Adapter createCallOperationActionAdapter() {
		return new CallOperationActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCallBehaviorActionAdapter()
	 * 
	 */
	public Adapter createCallBehaviorActionAdapter() {
		return new CallBehaviorActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createSequenceNodeAdapter()
	 * 
	 */
	public Adapter createSequenceNodeAdapter() {
		return new SequenceNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createControlFlowAdapter()
	 * 
	 */
	public Adapter createControlFlowAdapter() {
		return new ControlFlowPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInitialNodeAdapter()
	 * 
	 */
	public Adapter createInitialNodeAdapter() {
		return new InitialNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createActivityParameterNodeAdapter()
	 * 
	 */
	public Adapter createActivityParameterNodeAdapter() {
		return new ActivityParameterNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createValuePinAdapter()
	 * 
	 */
	public Adapter createValuePinAdapter() {
		return new ValuePinPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createMessageAdapter()
	 * 
	 */
	public Adapter createMessageAdapter() {
		return new MessagePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInteractionAdapter()
	 * 
	 */
	public Adapter createInteractionAdapter() {
		return new InteractionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createLifelineAdapter()
	 * 
	 */
	public Adapter createLifelineAdapter() {
		return new LifelinePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInteractionUseAdapter()
	 * 
	 */
	public Adapter createInteractionUseAdapter() {
		return new InteractionUsePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createPartDecompositionAdapter()
	 * 
	 */
	public Adapter createPartDecompositionAdapter() {
		return new PartDecompositionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createGateAdapter()
	 * 
	 */
	public Adapter createGateAdapter() {
		return new GatePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createGeneralOrderingAdapter()
	 * 
	 */
	public Adapter createGeneralOrderingAdapter() {
		return new GeneralOrderingPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createOccurrenceSpecificationAdapter()
	 * 
	 */
	public Adapter createOccurrenceSpecificationAdapter() {
		return new OccurrenceSpecificationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInteractionOperandAdapter()
	 * 
	 */
	public Adapter createInteractionOperandAdapter() {
		return new InteractionOperandPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInteractionConstraintAdapter()
	 * 
	 */
	public Adapter createInteractionConstraintAdapter() {
		return new InteractionConstraintPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createStateInvariantAdapter()
	 * 
	 */
	public Adapter createStateInvariantAdapter() {
		return new StateInvariantPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createActionExecutionSpecificationAdapter()
	 * 
	 */
	public Adapter createActionExecutionSpecificationAdapter() {
		return new ActionExecutionSpecificationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createBehaviorExecutionSpecificationAdapter()
	 * 
	 */
	public Adapter createBehaviorExecutionSpecificationAdapter() {
		return new BehaviorExecutionSpecificationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createExecutionEventAdapter()
	 * 
	 */
	public Adapter createExecutionEventAdapter() {
		return new ExecutionEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCreationEventAdapter()
	 * 
	 */
	public Adapter createCreationEventAdapter() {
		return new CreationEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDestructionEventAdapter()
	 * 
	 */
	public Adapter createDestructionEventAdapter() {
		return new DestructionEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createSendOperationEventAdapter()
	 * 
	 */
	public Adapter createSendOperationEventAdapter() {
		return new SendOperationEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createSendSignalEventAdapter()
	 * 
	 */
	public Adapter createSendSignalEventAdapter() {
		return new SendSignalEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createMessageOccurrenceSpecificationAdapter()
	 * 
	 */
	public Adapter createMessageOccurrenceSpecificationAdapter() {
		return new MessageOccurrenceSpecificationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createExecutionOccurrenceSpecificationAdapter()
	 * 
	 */
	public Adapter createExecutionOccurrenceSpecificationAdapter() {
		return new ExecutionOccurrenceSpecificationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReceiveOperationEventAdapter()
	 * 
	 */
	public Adapter createReceiveOperationEventAdapter() {
		return new ReceiveOperationEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReceiveSignalEventAdapter()
	 * 
	 */
	public Adapter createReceiveSignalEventAdapter() {
		return new ReceiveSignalEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createActorAdapter()
	 * 
	 */
	public Adapter createActorAdapter() {
		return new ActorPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCallEventAdapter()
	 * 
	 */
	public Adapter createCallEventAdapter() {
		return new CallEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createChangeEventAdapter()
	 * 
	 */
	public Adapter createChangeEventAdapter() {
		return new ChangeEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createSignalEventAdapter()
	 * 
	 */
	public Adapter createSignalEventAdapter() {
		return new SignalEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createAnyReceiveEventAdapter()
	 * 
	 */
	public Adapter createAnyReceiveEventAdapter() {
		return new AnyReceiveEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createForkNodeAdapter()
	 * 
	 */
	public Adapter createForkNodeAdapter() {
		return new ForkNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createFlowFinalNodeAdapter()
	 * 
	 */
	public Adapter createFlowFinalNodeAdapter() {
		return new FlowFinalNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCentralBufferNodeAdapter()
	 * 
	 */
	public Adapter createCentralBufferNodeAdapter() {
		return new CentralBufferNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createMergeNodeAdapter()
	 * 
	 */
	public Adapter createMergeNodeAdapter() {
		return new MergeNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDecisionNodeAdapter()
	 * 
	 */
	public Adapter createDecisionNodeAdapter() {
		return new DecisionNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createObjectFlowAdapter()
	 * 
	 */
	public Adapter createObjectFlowAdapter() {
		return new ObjectFlowPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createActivityFinalNodeAdapter()
	 * 
	 */
	public Adapter createActivityFinalNodeAdapter() {
		return new ActivityFinalNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createComponentRealizationAdapter()
	 * 
	 */
	public Adapter createComponentRealizationAdapter() {
		return new ComponentRealizationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createComponentAdapter()
	 * 
	 */
	public Adapter createComponentAdapter() {
		return new ComponentPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createNodeAdapter()
	 * 
	 */
	public Adapter createNodeAdapter() {
		return new NodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCommunicationPathAdapter()
	 * 
	 */
	public Adapter createCommunicationPathAdapter() {
		return new CommunicationPathPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDeviceAdapter()
	 * 
	 */
	public Adapter createDeviceAdapter() {
		return new DevicePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createExecutionEnvironmentAdapter()
	 * 
	 */
	public Adapter createExecutionEnvironmentAdapter() {
		return new ExecutionEnvironmentPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCombinedFragmentAdapter()
	 * 
	 */
	public Adapter createCombinedFragmentAdapter() {
		return new CombinedFragmentPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createContinuationAdapter()
	 * 
	 */
	public Adapter createContinuationAdapter() {
		return new ContinuationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createConsiderIgnoreFragmentAdapter()
	 * 
	 */
	public Adapter createConsiderIgnoreFragmentAdapter() {
		return new ConsiderIgnoreFragmentPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCreateObjectActionAdapter()
	 * 
	 */
	public Adapter createCreateObjectActionAdapter() {
		return new CreateObjectActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDestroyObjectActionAdapter()
	 * 
	 */
	public Adapter createDestroyObjectActionAdapter() {
		return new DestroyObjectActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTestIdentityActionAdapter()
	 * 
	 */
	public Adapter createTestIdentityActionAdapter() {
		return new TestIdentityActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReadSelfActionAdapter()
	 * 
	 */
	public Adapter createReadSelfActionAdapter() {
		return new ReadSelfActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReadStructuralFeatureActionAdapter()
	 * 
	 */
	public Adapter createReadStructuralFeatureActionAdapter() {
		return new ReadStructuralFeatureActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createClearStructuralFeatureActionAdapter()
	 * 
	 */
	public Adapter createClearStructuralFeatureActionAdapter() {
		return new ClearStructuralFeatureActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createRemoveStructuralFeatureValueActionAdapter()
	 * 
	 */
	public Adapter createRemoveStructuralFeatureValueActionAdapter() {
		return new RemoveStructuralFeatureValueActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createAddStructuralFeatureValueActionAdapter()
	 * 
	 */
	public Adapter createAddStructuralFeatureValueActionAdapter() {
		return new AddStructuralFeatureValueActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createLinkEndDataAdapter()
	 * 
	 */
	public Adapter createLinkEndDataAdapter() {
		return new LinkEndDataPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createQualifierValueAdapter()
	 * 
	 */
	public Adapter createQualifierValueAdapter() {
		return new QualifierValuePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReadLinkActionAdapter()
	 * 
	 */
	public Adapter createReadLinkActionAdapter() {
		return new ReadLinkActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createLinkEndCreationDataAdapter()
	 * 
	 */
	public Adapter createLinkEndCreationDataAdapter() {
		return new LinkEndCreationDataPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCreateLinkActionAdapter()
	 * 
	 */
	public Adapter createCreateLinkActionAdapter() {
		return new CreateLinkActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDestroyLinkActionAdapter()
	 * 
	 */
	public Adapter createDestroyLinkActionAdapter() {
		return new DestroyLinkActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createLinkEndDestructionDataAdapter()
	 * 
	 */
	public Adapter createLinkEndDestructionDataAdapter() {
		return new LinkEndDestructionDataPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createClearAssociationActionAdapter()
	 * 
	 */
	public Adapter createClearAssociationActionAdapter() {
		return new ClearAssociationActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createBroadcastSignalActionAdapter()
	 * 
	 */
	public Adapter createBroadcastSignalActionAdapter() {
		return new BroadcastSignalActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createSendObjectActionAdapter()
	 * 
	 */
	public Adapter createSendObjectActionAdapter() {
		return new SendObjectActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createValueSpecificationActionAdapter()
	 * 
	 */
	public Adapter createValueSpecificationActionAdapter() {
		return new ValueSpecificationActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTimeExpressionAdapter()
	 * 
	 */
	public Adapter createTimeExpressionAdapter() {
		return new TimeExpressionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDurationAdapter()
	 * 
	 */
	public Adapter createDurationAdapter() {
		return new DurationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createIntervalAdapter()
	 * 
	 */
	public Adapter createIntervalAdapter() {
		return new IntervalPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDurationIntervalAdapter()
	 * 
	 */
	public Adapter createDurationIntervalAdapter() {
		return new DurationIntervalPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createIntervalConstraintAdapter()
	 * 
	 */
	public Adapter createIntervalConstraintAdapter() {
		return new IntervalConstraintPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTimeConstraintAdapter()
	 * 
	 */
	public Adapter createTimeConstraintAdapter() {
		return new TimeConstraintPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTimeIntervalAdapter()
	 * 
	 */
	public Adapter createTimeIntervalAdapter() {
		return new TimeIntervalPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDurationConstraintAdapter()
	 * 
	 */
	public Adapter createDurationConstraintAdapter() {
		return new DurationConstraintPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTimeObservationAdapter()
	 * 
	 */
	public Adapter createTimeObservationAdapter() {
		return new TimeObservationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDurationObservationAdapter()
	 * 
	 */
	public Adapter createDurationObservationAdapter() {
		return new DurationObservationPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createFinalStateAdapter()
	 * 
	 */
	public Adapter createFinalStateAdapter() {
		return new FinalStatePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createTimeEventAdapter()
	 * 
	 */
	public Adapter createTimeEventAdapter() {
		return new TimeEventPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReadVariableActionAdapter()
	 * 
	 */
	public Adapter createReadVariableActionAdapter() {
		return new ReadVariableActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createClearVariableActionAdapter()
	 * 
	 */
	public Adapter createClearVariableActionAdapter() {
		return new ClearVariableActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createAddVariableValueActionAdapter()
	 * 
	 */
	public Adapter createAddVariableValueActionAdapter() {
		return new AddVariableValueActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createRemoveVariableValueActionAdapter()
	 * 
	 */
	public Adapter createRemoveVariableValueActionAdapter() {
		return new RemoveVariableValueActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createRaiseExceptionActionAdapter()
	 * 
	 */
	public Adapter createRaiseExceptionActionAdapter() {
		return new RaiseExceptionActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createActionInputPinAdapter()
	 * 
	 */
	public Adapter createActionInputPinAdapter() {
		return new ActionInputPinPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInformationItemAdapter()
	 * 
	 */
	public Adapter createInformationItemAdapter() {
		return new InformationItemPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createInformationFlowAdapter()
	 * 
	 */
	public Adapter createInformationFlowAdapter() {
		return new InformationFlowPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReadExtentActionAdapter()
	 * 
	 */
	public Adapter createReadExtentActionAdapter() {
		return new ReadExtentActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReclassifyObjectActionAdapter()
	 * 
	 */
	public Adapter createReclassifyObjectActionAdapter() {
		return new ReclassifyObjectActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReadIsClassifiedObjectActionAdapter()
	 * 
	 */
	public Adapter createReadIsClassifiedObjectActionAdapter() {
		return new ReadIsClassifiedObjectActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createStartClassifierBehaviorActionAdapter()
	 * 
	 */
	public Adapter createStartClassifierBehaviorActionAdapter() {
		return new StartClassifierBehaviorActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReadLinkObjectEndActionAdapter()
	 * 
	 */
	public Adapter createReadLinkObjectEndActionAdapter() {
		return new ReadLinkObjectEndActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReadLinkObjectEndQualifierActionAdapter()
	 * 
	 */
	public Adapter createReadLinkObjectEndQualifierActionAdapter() {
		return new ReadLinkObjectEndQualifierActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createCreateLinkObjectActionAdapter()
	 * 
	 */
	public Adapter createCreateLinkObjectActionAdapter() {
		return new CreateLinkObjectActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createAcceptEventActionAdapter()
	 * 
	 */
	public Adapter createAcceptEventActionAdapter() {
		return new AcceptEventActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createAcceptCallActionAdapter()
	 * 
	 */
	public Adapter createAcceptCallActionAdapter() {
		return new AcceptCallActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReplyActionAdapter()
	 * 
	 */
	public Adapter createReplyActionAdapter() {
		return new ReplyActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createUnmarshallActionAdapter()
	 * 
	 */
	public Adapter createUnmarshallActionAdapter() {
		return new UnmarshallActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createReduceActionAdapter()
	 * 
	 */
	public Adapter createReduceActionAdapter() {
		return new ReduceActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createStartObjectBehaviorActionAdapter()
	 * 
	 */
	public Adapter createStartObjectBehaviorActionAdapter() {
		return new StartObjectBehaviorActionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createJoinNodeAdapter()
	 * 
	 */
	public Adapter createJoinNodeAdapter() {
		return new JoinNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createDataStoreNodeAdapter()
	 * 
	 */
	public Adapter createDataStoreNodeAdapter() {
		return new DataStoreNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createConditionalNodeAdapter()
	 * 
	 */
	public Adapter createConditionalNodeAdapter() {
		return new ConditionalNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createClauseAdapter()
	 * 
	 */
	public Adapter createClauseAdapter() {
		return new ClausePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createLoopNodeAdapter()
	 * 
	 */
	public Adapter createLoopNodeAdapter() {
		return new LoopNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createExpansionNodeAdapter()
	 * 
	 */
	public Adapter createExpansionNodeAdapter() {
		return new ExpansionNodePropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createExpansionRegionAdapter()
	 * 
	 */
	public Adapter createExpansionRegionAdapter() {
		return new ExpansionRegionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createProtocolTransitionAdapter()
	 * 
	 */
	public Adapter createProtocolTransitionAdapter() {
		return new ProtocolTransitionPropertiesEditionProvider();
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.uml2.uml.util.UmlAdapterFactory#createAssociationClassAdapter()
	 * 
	 */
	public Adapter createAssociationClassAdapter() {
		return new AssociationClassPropertiesEditionProvider();
	}

}
