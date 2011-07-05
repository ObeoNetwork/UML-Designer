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
import org.obeonetwork.dsl.uml2.properties.uml.parts.forms.*;
import org.obeonetwork.dsl.uml2.properties.uml.parts.impl.*;




/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class UmlPropertiesEditionPartProvider implements IPropertiesEditionPartProvider {

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPartProvider#provides(java.lang.Object)
	 * 
	 */
	public boolean provides(Object key) {
		return key == UmlViewsRepository.class;
	}

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPartProvider#getPropertiesEditionPart(java.lang.Object, int, org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent)
	 * 
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
