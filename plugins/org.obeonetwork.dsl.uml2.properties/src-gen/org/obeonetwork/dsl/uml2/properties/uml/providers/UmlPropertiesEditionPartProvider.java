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

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionPartProvider;

import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.AbstractionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.AcceptCallActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.AcceptEventActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ActionExecutionSpecificationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ActionInputPinPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ActivityFinalNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ActivityParameterNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ActivityPartitionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ActivityPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ActorPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.AddStructuralFeatureValueActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.AddVariableValueActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.AnyReceiveEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ArtifactPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.AssociationClassPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.AssociationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.BehaviorExecutionSpecificationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.BroadcastSignalActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CallBehaviorActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CallEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CallOperationActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CentralBufferNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ChangeEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.Class_PropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ClassifierTemplateParameterPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ClausePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ClearAssociationActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ClearStructuralFeatureActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ClearVariableActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CollaborationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CollaborationUsePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CombinedFragmentPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CommentPropertiesEditionPartForm;

import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CommunicationPathPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ComponentPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ComponentRealizationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ConditionalNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ConnectableElementTemplateParameterPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ConnectionPointReferencePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ConnectorEndPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ConnectorPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ConsiderIgnoreFragmentPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ConstraintPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ContinuationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ControlFlowPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CreateLinkActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CreateLinkObjectActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CreateObjectActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.CreationEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DataStoreNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DataTypePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DecisionNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DependencyPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DeploymentPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DeploymentSpecificationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DestroyLinkActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DestroyObjectActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DestructionEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DevicePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DurationConstraintPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DurationIntervalPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DurationObservationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.DurationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ElementImportPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.EnumerationLiteralPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.EnumerationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ExceptionHandlerPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ExecutionEnvironmentPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ExecutionEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ExecutionOccurrenceSpecificationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ExpansionNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ExpansionRegionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ExpressionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ExtendPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ExtensionEndPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ExtensionPointPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ExtensionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.FinalStatePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.FlowFinalNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ForkNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.FunctionBehaviorPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.GatePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.GeneralOrderingPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.GeneralizationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.GeneralizationSetPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ImagePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.IncludePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InformationFlowPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InformationItemPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InitialNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InputPinPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InstanceSpecificationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InstanceValuePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InteractionConstraintPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InteractionOperandPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InteractionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InteractionUsePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InterfaceRealizationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.Interface_PropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.InterruptibleActivityRegionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.IntervalConstraintPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.IntervalPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.JoinNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.LifelinePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.LinkEndCreationDataPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.LinkEndDataPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.LinkEndDestructionDataPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.LiteralBooleanPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.LiteralIntegerPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.LiteralNullPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.LiteralStringPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.LiteralUnlimitedNaturalPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.LoopNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ManifestationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.MergeNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.MessageOccurrenceSpecificationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.MessagePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ModelPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.NodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ObjectFlowPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.OccurrenceSpecificationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.OpaqueActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.OpaqueBehaviorPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.OpaqueExpressionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.OperationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.OperationTemplateParameterPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.OutputPinPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.PackageImportPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.PackageMergePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.Package_PropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ParameterPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ParameterSetPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.PartDecompositionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.PinPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.PortPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.PrimitiveTypePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ProfileApplicationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ProfilePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.PropertyPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ProtocolConformancePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ProtocolStateMachinePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ProtocolTransitionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.PseudostatePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.QualifierValuePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.RaiseExceptionActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReadExtentActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReadIsClassifiedObjectActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReadLinkActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReadLinkObjectEndActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReadLinkObjectEndQualifierActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReadSelfActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReadStructuralFeatureActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReadVariableActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.RealizationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReceiveOperationEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReceiveSignalEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReceptionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReclassifyObjectActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.RedefinableTemplateSignaturePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReduceActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.RegionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.RemoveStructuralFeatureValueActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.RemoveVariableValueActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ReplyActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.SendObjectActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.SendOperationEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.SendSignalActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.SendSignalEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.SequenceNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.SignalEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.SignalPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.SlotPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.StartClassifierBehaviorActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.StartObjectBehaviorActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.StateInvariantPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.StateMachinePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.StatePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.StereotypePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.StringExpressionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.StructuredActivityNodePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.SubstitutionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TemplateBindingPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TemplateParameterPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TemplateParameterSubstitutionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TemplateSignaturePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TestIdentityActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TimeConstraintPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TimeEventPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TimeExpressionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TimeIntervalPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TimeObservationPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TransitionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.TriggerPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.UnmarshallActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.UsagePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.UseCasePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ValuePinPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.ValueSpecificationActionPropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.VariablePropertiesEditionPartForm;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.AbstractionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.AcceptCallActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.AcceptEventActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ActionExecutionSpecificationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ActionInputPinPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ActivityFinalNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ActivityParameterNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ActivityPartitionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ActivityPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ActorPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.AddStructuralFeatureValueActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.AddVariableValueActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.AnyReceiveEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ArtifactPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.AssociationClassPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.AssociationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.BehaviorExecutionSpecificationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.BroadcastSignalActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CallBehaviorActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CallEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CallOperationActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CentralBufferNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ChangeEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.Class_PropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ClassifierTemplateParameterPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ClausePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ClearAssociationActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ClearStructuralFeatureActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ClearVariableActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CollaborationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CollaborationUsePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CombinedFragmentPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CommentPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CommunicationPathPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ComponentPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ComponentRealizationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ConditionalNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ConnectableElementTemplateParameterPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ConnectionPointReferencePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ConnectorEndPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ConnectorPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ConsiderIgnoreFragmentPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ConstraintPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ContinuationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ControlFlowPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CreateLinkActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CreateLinkObjectActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CreateObjectActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.CreationEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DataStoreNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DataTypePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DecisionNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DependencyPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DeploymentPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DeploymentSpecificationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DestroyLinkActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DestroyObjectActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DestructionEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DevicePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DurationConstraintPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DurationIntervalPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DurationObservationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.DurationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ElementImportPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.EnumerationLiteralPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.EnumerationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ExceptionHandlerPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ExecutionEnvironmentPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ExecutionEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ExecutionOccurrenceSpecificationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ExpansionNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ExpansionRegionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ExpressionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ExtendPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ExtensionEndPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ExtensionPointPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ExtensionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.FinalStatePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.FlowFinalNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ForkNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.FunctionBehaviorPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.GatePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.GeneralOrderingPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.GeneralizationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.GeneralizationSetPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ImagePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.IncludePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InformationFlowPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InformationItemPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InitialNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InputPinPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InstanceSpecificationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InstanceValuePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InteractionConstraintPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InteractionOperandPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InteractionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InteractionUsePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InterfaceRealizationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.Interface_PropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.InterruptibleActivityRegionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.IntervalConstraintPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.IntervalPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.JoinNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.LifelinePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.LinkEndCreationDataPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.LinkEndDataPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.LinkEndDestructionDataPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.LiteralBooleanPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.LiteralIntegerPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.LiteralNullPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.LiteralStringPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.LiteralUnlimitedNaturalPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.LoopNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ManifestationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.MergeNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.MessageOccurrenceSpecificationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.MessagePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ModelPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.NodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ObjectFlowPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.OccurrenceSpecificationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.OpaqueActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.OpaqueBehaviorPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.OpaqueExpressionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.OperationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.OperationTemplateParameterPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.OutputPinPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.PackageImportPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.PackageMergePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.Package_PropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ParameterPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ParameterSetPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.PartDecompositionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.PinPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.PortPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.PrimitiveTypePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ProfileApplicationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ProfilePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.PropertyPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ProtocolConformancePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ProtocolStateMachinePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ProtocolTransitionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.PseudostatePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.QualifierValuePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.RaiseExceptionActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReadExtentActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReadIsClassifiedObjectActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReadLinkActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReadLinkObjectEndActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReadLinkObjectEndQualifierActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReadSelfActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReadStructuralFeatureActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReadVariableActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.RealizationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReceiveOperationEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReceiveSignalEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReceptionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReclassifyObjectActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.RedefinableTemplateSignaturePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReduceActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.RegionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.RemoveStructuralFeatureValueActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.RemoveVariableValueActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ReplyActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.SendObjectActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.SendOperationEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.SendSignalActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.SendSignalEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.SequenceNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.SignalEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.SignalPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.SlotPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.StartClassifierBehaviorActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.StartObjectBehaviorActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.StateInvariantPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.StateMachinePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.StatePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.StereotypePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.StringExpressionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.StructuredActivityNodePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.SubstitutionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TemplateBindingPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TemplateParameterPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TemplateParameterSubstitutionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TemplateSignaturePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TestIdentityActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TimeConstraintPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TimeEventPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TimeExpressionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TimeIntervalPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TimeObservationPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TransitionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.TriggerPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.UnmarshallActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.UsagePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.UseCasePropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ValuePinPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.ValueSpecificationActionPropertiesEditionPartImpl;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.VariablePropertiesEditionPartImpl;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 * @generated
 */
public class UmlPropertiesEditionPartProvider implements IPropertiesEditionPartProvider {

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPartProvider#provides(java.lang.Object)
	 * @generated
	 */
	public boolean provides(Object key) {
    return key == UmlViewsRepository.class;
  }

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPartProvider#getPropertiesEditionPart(java.lang.Object, int, org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent)
	 * @generated
	 */
	public IPropertiesEditionPart getPropertiesEditionPart(Object key, int kind, IPropertiesEditionComponent component) {
    if (key == UmlViewsRepository.Comment.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CommentPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CommentPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Package_.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new Package_PropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new Package_PropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Dependency.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DependencyPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DependencyPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ElementImport.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ElementImportPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ElementImportPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.PackageImport.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new PackageImportPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new PackageImportPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Constraint.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ConstraintPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ConstraintPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Association.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new AssociationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new AssociationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.TemplateBinding.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TemplateBindingPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TemplateBindingPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.TemplateSignature.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TemplateSignaturePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TemplateSignaturePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.TemplateParameter.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TemplateParameterPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TemplateParameterPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.TemplateParameterSubstitution.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TemplateParameterSubstitutionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TemplateParameterSubstitutionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Generalization.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new GeneralizationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new GeneralizationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.GeneralizationSet.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new GeneralizationSetPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new GeneralizationSetPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Substitution.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new SubstitutionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new SubstitutionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Realization.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new RealizationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new RealizationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Abstraction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new AbstractionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new AbstractionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.OpaqueExpression.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new OpaqueExpressionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new OpaqueExpressionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Parameter.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ParameterPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ParameterPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ConnectorEnd.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ConnectorEndPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ConnectorEndPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Property.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new PropertyPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new PropertyPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Deployment.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DeploymentPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DeploymentPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.DeploymentSpecification.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DeploymentSpecificationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DeploymentSpecificationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Artifact.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ArtifactPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ArtifactPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Manifestation.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ManifestationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ManifestationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Operation.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new OperationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new OperationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Class_.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new Class_PropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new Class_PropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.InterfaceRealization.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InterfaceRealizationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InterfaceRealizationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Interface_.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new Interface_PropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new Interface_PropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Reception.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReceptionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReceptionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Signal.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new SignalPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new SignalPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ProtocolStateMachine.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ProtocolStateMachinePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ProtocolStateMachinePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.StateMachine.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new StateMachinePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new StateMachinePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Region.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new RegionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new RegionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Transition.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TransitionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TransitionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Trigger.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TriggerPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TriggerPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Port.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new PortPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new PortPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.State.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new StatePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new StatePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ConnectionPointReference.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ConnectionPointReferencePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ConnectionPointReferencePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Pseudostate.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new PseudostatePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new PseudostatePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ProtocolConformance.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ProtocolConformancePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ProtocolConformancePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Connector.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ConnectorPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ConnectorPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Extension.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ExtensionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ExtensionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ExtensionEnd.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ExtensionEndPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ExtensionEndPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Stereotype.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new StereotypePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new StereotypePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Image.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ImagePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ImagePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Profile.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ProfilePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ProfilePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Model.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ModelPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ModelPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ParameterSet.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ParameterSetPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ParameterSetPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.DataType.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DataTypePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DataTypePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.OperationTemplateParameter.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new OperationTemplateParameterPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new OperationTemplateParameterPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ConnectableElementTemplateParameter.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ConnectableElementTemplateParameterPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ConnectableElementTemplateParameterPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.CollaborationUse.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CollaborationUsePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CollaborationUsePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Collaboration.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CollaborationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CollaborationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.UseCase.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new UseCasePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new UseCasePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Include.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new IncludePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new IncludePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Extend.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ExtendPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ExtendPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ExtensionPoint.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ExtensionPointPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ExtensionPointPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.RedefinableTemplateSignature.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new RedefinableTemplateSignaturePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new RedefinableTemplateSignaturePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ClassifierTemplateParameter.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ClassifierTemplateParameterPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ClassifierTemplateParameterPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.StringExpression.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new StringExpressionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new StringExpressionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Expression.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ExpressionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ExpressionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Usage.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new UsagePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new UsagePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.PackageMerge.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new PackageMergePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new PackageMergePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ProfileApplication.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ProfileApplicationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ProfileApplicationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Enumeration.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new EnumerationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new EnumerationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.EnumerationLiteral.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new EnumerationLiteralPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new EnumerationLiteralPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.InstanceSpecification.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InstanceSpecificationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InstanceSpecificationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Slot.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new SlotPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new SlotPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.PrimitiveType.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new PrimitiveTypePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new PrimitiveTypePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.LiteralInteger.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new LiteralIntegerPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new LiteralIntegerPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.LiteralString.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new LiteralStringPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new LiteralStringPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.LiteralBoolean.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new LiteralBooleanPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new LiteralBooleanPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.LiteralNull.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new LiteralNullPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new LiteralNullPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.InstanceValue.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InstanceValuePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InstanceValuePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.LiteralUnlimitedNatural.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new LiteralUnlimitedNaturalPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new LiteralUnlimitedNaturalPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.OpaqueBehavior.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new OpaqueBehaviorPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new OpaqueBehaviorPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.FunctionBehavior.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new FunctionBehaviorPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new FunctionBehaviorPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.OpaqueAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new OpaqueActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new OpaqueActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.StructuredActivityNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new StructuredActivityNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new StructuredActivityNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Activity.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ActivityPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ActivityPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Variable.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new VariablePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new VariablePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ActivityPartition.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ActivityPartitionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ActivityPartitionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.InterruptibleActivityRegion.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InterruptibleActivityRegionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InterruptibleActivityRegionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ExceptionHandler.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ExceptionHandlerPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ExceptionHandlerPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.OutputPin.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new OutputPinPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new OutputPinPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Pin.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new PinPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new PinPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.InputPin.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InputPinPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InputPinPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.SendSignalAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new SendSignalActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new SendSignalActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.CallOperationAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CallOperationActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CallOperationActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.CallBehaviorAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CallBehaviorActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CallBehaviorActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.SequenceNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new SequenceNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new SequenceNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ControlFlow.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ControlFlowPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ControlFlowPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.InitialNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InitialNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InitialNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ActivityParameterNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ActivityParameterNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ActivityParameterNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ValuePin.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ValuePinPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ValuePinPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Message.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new MessagePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new MessagePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Interaction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InteractionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InteractionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Lifeline.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new LifelinePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new LifelinePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.PartDecomposition.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new PartDecompositionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new PartDecompositionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.InteractionUse.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InteractionUsePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InteractionUsePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Gate.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new GatePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new GatePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.GeneralOrdering.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new GeneralOrderingPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new GeneralOrderingPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.OccurrenceSpecification.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new OccurrenceSpecificationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new OccurrenceSpecificationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.InteractionOperand.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InteractionOperandPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InteractionOperandPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.InteractionConstraint.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InteractionConstraintPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InteractionConstraintPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.StateInvariant.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new StateInvariantPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new StateInvariantPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ActionExecutionSpecification.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ActionExecutionSpecificationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ActionExecutionSpecificationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.BehaviorExecutionSpecification.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new BehaviorExecutionSpecificationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new BehaviorExecutionSpecificationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ExecutionEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ExecutionEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ExecutionEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.CreationEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CreationEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CreationEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.DestructionEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DestructionEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DestructionEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.SendOperationEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new SendOperationEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new SendOperationEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.SendSignalEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new SendSignalEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new SendSignalEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.MessageOccurrenceSpecification.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new MessageOccurrenceSpecificationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new MessageOccurrenceSpecificationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ExecutionOccurrenceSpecificationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ExecutionOccurrenceSpecificationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReceiveOperationEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReceiveOperationEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReceiveOperationEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReceiveSignalEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReceiveSignalEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReceiveSignalEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Actor.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ActorPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ActorPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.CallEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CallEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CallEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ChangeEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ChangeEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ChangeEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.SignalEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new SignalEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new SignalEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.AnyReceiveEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new AnyReceiveEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new AnyReceiveEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ForkNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ForkNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ForkNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.FlowFinalNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new FlowFinalNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new FlowFinalNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.CentralBufferNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CentralBufferNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CentralBufferNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.MergeNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new MergeNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new MergeNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.DecisionNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DecisionNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DecisionNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ObjectFlow.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ObjectFlowPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ObjectFlowPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ActivityFinalNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ActivityFinalNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ActivityFinalNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ComponentRealization.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ComponentRealizationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ComponentRealizationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Component.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ComponentPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ComponentPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Node.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new NodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new NodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.CommunicationPath.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CommunicationPathPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CommunicationPathPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Device.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DevicePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DevicePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ExecutionEnvironment.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ExecutionEnvironmentPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ExecutionEnvironmentPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.CombinedFragment.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CombinedFragmentPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CombinedFragmentPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Continuation.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ContinuationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ContinuationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ConsiderIgnoreFragment.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ConsiderIgnoreFragmentPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ConsiderIgnoreFragmentPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.CreateObjectAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CreateObjectActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CreateObjectActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.DestroyObjectAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DestroyObjectActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DestroyObjectActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.TestIdentityAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TestIdentityActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TestIdentityActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReadSelfAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReadSelfActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReadSelfActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReadStructuralFeatureAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReadStructuralFeatureActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReadStructuralFeatureActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ClearStructuralFeatureAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ClearStructuralFeatureActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ClearStructuralFeatureActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.RemoveStructuralFeatureValueAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new RemoveStructuralFeatureValueActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new RemoveStructuralFeatureValueActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.AddStructuralFeatureValueAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new AddStructuralFeatureValueActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new AddStructuralFeatureValueActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.LinkEndData.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new LinkEndDataPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new LinkEndDataPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.QualifierValue.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new QualifierValuePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new QualifierValuePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReadLinkAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReadLinkActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReadLinkActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.LinkEndCreationData.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new LinkEndCreationDataPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new LinkEndCreationDataPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.CreateLinkAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CreateLinkActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CreateLinkActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.DestroyLinkAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DestroyLinkActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DestroyLinkActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.LinkEndDestructionData.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new LinkEndDestructionDataPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new LinkEndDestructionDataPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ClearAssociationAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ClearAssociationActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ClearAssociationActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.BroadcastSignalAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new BroadcastSignalActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new BroadcastSignalActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.SendObjectAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new SendObjectActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new SendObjectActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ValueSpecificationAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ValueSpecificationActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ValueSpecificationActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.TimeExpression.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TimeExpressionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TimeExpressionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Duration.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DurationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DurationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.DurationInterval.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DurationIntervalPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DurationIntervalPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Interval.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new IntervalPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new IntervalPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.TimeConstraint.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TimeConstraintPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TimeConstraintPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.IntervalConstraint.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new IntervalConstraintPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new IntervalConstraintPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.TimeInterval.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TimeIntervalPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TimeIntervalPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.DurationConstraint.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DurationConstraintPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DurationConstraintPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.TimeObservation.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TimeObservationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TimeObservationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.DurationObservation.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DurationObservationPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DurationObservationPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.FinalState.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new FinalStatePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new FinalStatePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.TimeEvent.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new TimeEventPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new TimeEventPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReadVariableAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReadVariableActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReadVariableActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ClearVariableAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ClearVariableActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ClearVariableActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.AddVariableValueAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new AddVariableValueActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new AddVariableValueActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.RemoveVariableValueAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new RemoveVariableValueActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new RemoveVariableValueActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.RaiseExceptionAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new RaiseExceptionActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new RaiseExceptionActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ActionInputPin.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ActionInputPinPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ActionInputPinPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.InformationItem.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InformationItemPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InformationItemPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.InformationFlow.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new InformationFlowPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new InformationFlowPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReadExtentAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReadExtentActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReadExtentActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReclassifyObjectAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReclassifyObjectActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReclassifyObjectActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReadIsClassifiedObjectAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReadIsClassifiedObjectActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReadIsClassifiedObjectActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.StartClassifierBehaviorAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new StartClassifierBehaviorActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new StartClassifierBehaviorActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReadLinkObjectEndAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReadLinkObjectEndActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReadLinkObjectEndActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReadLinkObjectEndQualifierAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReadLinkObjectEndQualifierActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReadLinkObjectEndQualifierActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.CreateLinkObjectAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new CreateLinkObjectActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new CreateLinkObjectActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.AcceptEventAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new AcceptEventActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new AcceptEventActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.AcceptCallAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new AcceptCallActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new AcceptCallActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReplyAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReplyActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReplyActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.UnmarshallAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new UnmarshallActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new UnmarshallActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ReduceAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ReduceActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ReduceActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.StartObjectBehaviorAction.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new StartObjectBehaviorActionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new StartObjectBehaviorActionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.JoinNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new JoinNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new JoinNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.DataStoreNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new DataStoreNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new DataStoreNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ConditionalNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ConditionalNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ConditionalNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.Clause.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ClausePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ClausePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.LoopNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new LoopNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new LoopNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ExpansionNode.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ExpansionNodePropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ExpansionNodePropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ExpansionRegion.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ExpansionRegionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ExpansionRegionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.ProtocolTransition.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new ProtocolTransitionPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new ProtocolTransitionPropertiesEditionPartForm(component);
    }
    if (key == UmlViewsRepository.AssociationClass.class) {
      if (kind == UmlViewsRepository.SWT_KIND)
        return new AssociationClassPropertiesEditionPartImpl(component);
      if (kind == UmlViewsRepository.FORM_KIND)
        return new AssociationClassPropertiesEditionPartForm(component);
    }
    return null;
  }

}
