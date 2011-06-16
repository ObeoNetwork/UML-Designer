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
package org.eclipse.uml2.uml.parts;

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class UmlViewsRepository {

	public static final int SWT_KIND = 0;

	public static final int FORM_KIND = 1;


	/**
	 * Comment view descriptor
	 * 
	 */
	public static class Comment {
		public static class Properties {
	
			
			public static String body = "uml::Comment::properties::body";
			
			
			public static String annotatedElement = "uml::Comment::properties::annotatedElement";
			
	
		}
	
	}

	/**
	 * Package view descriptor
	 * 
	 */
	public static class Package_ {
		public static class Properties {
	
			
			public static String name = "uml::Package::properties::name";
			
			
			public static String visibility = "uml::Package::properties::visibility";
			
			
			public static String clientDependency = "uml::Package::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Package::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Package::properties::templateParameter";
			
	
		}
	
	}

	/**
	 * Dependency view descriptor
	 * 
	 */
	public static class Dependency {
		public static class Properties {
	
			
			public static String name = "uml::Dependency::properties::name";
			
			
			public static String visibility = "uml::Dependency::properties::visibility";
			
			
			public static String clientDependency = "uml::Dependency::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Dependency::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Dependency::properties::templateParameter";
			
			
			public static String supplier = "uml::Dependency::properties::supplier";
			
			
			public static String client = "uml::Dependency::properties::client";
			
	
		}
	
	}

	/**
	 * ElementImport view descriptor
	 * 
	 */
	public static class ElementImport {
		public static class Properties {
	
			
			public static String visibility = "uml::ElementImport::properties::visibility";
			
			
			public static String alias = "uml::ElementImport::properties::alias";
			
			
			public static String importedElement = "uml::ElementImport::properties::importedElement";
			
			
			public static String importingNamespace = "uml::ElementImport::properties::importingNamespace";
			
	
		}
	
	}

	/**
	 * PackageImport view descriptor
	 * 
	 */
	public static class PackageImport {
		public static class Properties {
	
			
			public static String visibility = "uml::PackageImport::properties::visibility";
			
			
			public static String importedPackage = "uml::PackageImport::properties::importedPackage";
			
			
			public static String importingNamespace = "uml::PackageImport::properties::importingNamespace";
			
	
		}
	
	}

	/**
	 * Constraint view descriptor
	 * 
	 */
	public static class Constraint {
		public static class Properties {
	
			
			public static String name = "uml::Constraint::properties::name";
			
			
			public static String visibility = "uml::Constraint::properties::visibility";
			
			
			public static String clientDependency = "uml::Constraint::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Constraint::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Constraint::properties::templateParameter";
			
			
			public static String constrainedElement = "uml::Constraint::properties::constrainedElement";
			
			
			public static String context = "uml::Constraint::properties::context";
			
	
		}
	
	}

	/**
	 * Association view descriptor
	 * 
	 */
	public static class Association {
		public static class Properties {
	
			
			public static String name = "uml::Association::properties::name";
			
			
			public static String visibility = "uml::Association::properties::visibility";
			
			
			public static String clientDependency = "uml::Association::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Association::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Association::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Association::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Association::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Association::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Association::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Association::properties::representation";
			
			
			public static String useCase = "uml::Association::properties::useCase";
			
			
			public static String memberEnd = "uml::Association::properties::memberEnd";
			
			
			public static String isDerived = "uml::Association::properties::isDerived";
			
			
			public static String navigableOwnedEnd = "uml::Association::properties::navigableOwnedEnd";
			
	
		}
	
	}

	/**
	 * TemplateBinding view descriptor
	 * 
	 */
	public static class TemplateBinding {
		public static class Properties {
	
			
			public static String signature = "uml::TemplateBinding::properties::signature";
			
			
			public static String boundElement = "uml::TemplateBinding::properties::boundElement";
			
	
		}
	
	}

	/**
	 * TemplateSignature view descriptor
	 * 
	 */
	public static class TemplateSignature {
		public static class Properties {
	
			
			public static String parameter = "uml::TemplateSignature::properties::parameter";
			
			
			public static String template = "uml::TemplateSignature::properties::template";
			
	
		}
	
	}

	/**
	 * TemplateParameter view descriptor
	 * 
	 */
	public static class TemplateParameter {
		public static class Properties {
	
			
			public static String signature = "uml::TemplateParameter::properties::signature";
			
			
			public static String parameteredElement = "uml::TemplateParameter::properties::parameteredElement";
			
			
			public static String default_ = "uml::TemplateParameter::properties::default";
			
	
		}
	
	}

	/**
	 * TemplateParameterSubstitution view descriptor
	 * 
	 */
	public static class TemplateParameterSubstitution {
		public static class Properties {
	
			
			public static String formal = "uml::TemplateParameterSubstitution::properties::formal";
			
			
			public static String actual = "uml::TemplateParameterSubstitution::properties::actual";
			
			
			public static String templateBinding = "uml::TemplateParameterSubstitution::properties::templateBinding";
			
	
		}
	
	}

	/**
	 * Generalization view descriptor
	 * 
	 */
	public static class Generalization {
		public static class Properties {
	
			
			public static String isSubstitutable = "uml::Generalization::properties::isSubstitutable";
			
			
			public static String general = "uml::Generalization::properties::general";
			
			
			public static String generalizationSet = "uml::Generalization::properties::generalizationSet";
			
			
			public static String specific = "uml::Generalization::properties::specific";
			
	
		}
	
	}

	/**
	 * GeneralizationSet view descriptor
	 * 
	 */
	public static class GeneralizationSet {
		public static class Properties {
	
			
			public static String name = "uml::GeneralizationSet::properties::name";
			
			
			public static String visibility = "uml::GeneralizationSet::properties::visibility";
			
			
			public static String clientDependency = "uml::GeneralizationSet::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::GeneralizationSet::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::GeneralizationSet::properties::templateParameter";
			
			
			public static String isCovering = "uml::GeneralizationSet::properties::isCovering";
			
			
			public static String isDisjoint = "uml::GeneralizationSet::properties::isDisjoint";
			
			
			public static String powertype = "uml::GeneralizationSet::properties::powertype";
			
			
			public static String generalization = "uml::GeneralizationSet::properties::generalization";
			
	
		}
	
	}

	/**
	 * Substitution view descriptor
	 * 
	 */
	public static class Substitution {
		public static class Properties {
	
			
			public static String name = "uml::Substitution::properties::name";
			
			
			public static String visibility = "uml::Substitution::properties::visibility";
			
			
			public static String clientDependency = "uml::Substitution::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Substitution::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Substitution::properties::templateParameter";
			
			
			public static String supplier = "uml::Substitution::properties::supplier";
			
			
			public static String client = "uml::Substitution::properties::client";
			
			
			public static String contract = "uml::Substitution::properties::contract";
			
			
			public static String substitutingClassifier = "uml::Substitution::properties::substitutingClassifier";
			
	
		}
	
	}

	/**
	 * Realization view descriptor
	 * 
	 */
	public static class Realization {
		public static class Properties {
	
			
			public static String name = "uml::Realization::properties::name";
			
			
			public static String visibility = "uml::Realization::properties::visibility";
			
			
			public static String clientDependency = "uml::Realization::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Realization::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Realization::properties::templateParameter";
			
			
			public static String supplier = "uml::Realization::properties::supplier";
			
			
			public static String client = "uml::Realization::properties::client";
			
	
		}
	
	}

	/**
	 * Abstraction view descriptor
	 * 
	 */
	public static class Abstraction {
		public static class Properties {
	
			
			public static String name = "uml::Abstraction::properties::name";
			
			
			public static String visibility = "uml::Abstraction::properties::visibility";
			
			
			public static String clientDependency = "uml::Abstraction::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Abstraction::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Abstraction::properties::templateParameter";
			
			
			public static String supplier = "uml::Abstraction::properties::supplier";
			
			
			public static String client = "uml::Abstraction::properties::client";
			
	
		}
	
	}

	/**
	 * OpaqueExpression view descriptor
	 * 
	 */
	public static class OpaqueExpression {
		public static class Properties {
	
			
			public static String name = "uml::OpaqueExpression::properties::name";
			
			
			public static String visibility = "uml::OpaqueExpression::properties::visibility";
			
			
			public static String clientDependency = "uml::OpaqueExpression::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::OpaqueExpression::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::OpaqueExpression::properties::templateParameter";
			
			
			public static String type = "uml::OpaqueExpression::properties::type";
			
			
			public static String body = "uml::OpaqueExpression::properties::body";
			
			
			public static String language = "uml::OpaqueExpression::properties::language";
			
			
			public static String behavior = "uml::OpaqueExpression::properties::behavior";
			
	
		}
	
	}

	/**
	 * Parameter view descriptor
	 * 
	 */
	public static class Parameter {
		public static class Properties {
	
			
			public static String name = "uml::Parameter::properties::name";
			
			
			public static String visibility = "uml::Parameter::properties::visibility";
			
			
			public static String clientDependency = "uml::Parameter::properties::clientDependency";
			
			
			public static String type = "uml::Parameter::properties::type";
			
			
			public static String owningTemplateParameter = "uml::Parameter::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Parameter::properties::templateParameter";
			
			
			public static String isOrdered = "uml::Parameter::properties::isOrdered";
			
			
			public static String isUnique = "uml::Parameter::properties::isUnique";
			
			
			public static String parameterSet = "uml::Parameter::properties::parameterSet";
			
			
			public static String operation = "uml::Parameter::properties::operation";
			
			
			public static String direction = "uml::Parameter::properties::direction";
			
			
			public static String isException = "uml::Parameter::properties::isException";
			
			
			public static String isStream = "uml::Parameter::properties::isStream";
			
			
			public static String effect = "uml::Parameter::properties::effect";
			
	
		}
	
	}

	/**
	 * ConnectorEnd view descriptor
	 * 
	 */
	public static class ConnectorEnd {
		public static class Properties {
	
			
			public static String isOrdered = "uml::ConnectorEnd::properties::isOrdered";
			
			
			public static String isUnique = "uml::ConnectorEnd::properties::isUnique";
			
			
			public static String role = "uml::ConnectorEnd::properties::role";
			
			
			public static String partWithPort = "uml::ConnectorEnd::properties::partWithPort";
			
	
		}
	
	}

	/**
	 * Property view descriptor
	 * 
	 */
	public static class Property {
		public static class Properties {
	
			
			public static String name = "uml::Property::properties::name";
			
			
			public static String visibility = "uml::Property::properties::visibility";
			
			
			public static String clientDependency = "uml::Property::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Property::properties::isLeaf";
			
			
			public static String isStatic = "uml::Property::properties::isStatic";
			
			
			public static String type = "uml::Property::properties::type";
			
			
			public static String isOrdered = "uml::Property::properties::isOrdered";
			
			
			public static String isUnique = "uml::Property::properties::isUnique";
			
			
			public static String isReadOnly = "uml::Property::properties::isReadOnly";
			
			
			public static String owningTemplateParameter = "uml::Property::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Property::properties::templateParameter";
			
			
			public static String class_ = "uml::Property::properties::class";
			
			
			public static String datatype = "uml::Property::properties::datatype";
			
			
			public static String isDerived = "uml::Property::properties::isDerived";
			
			
			public static String isDerivedUnion = "uml::Property::properties::isDerivedUnion";
			
			
			public static String aggregation = "uml::Property::properties::aggregation";
			
			
			public static String redefinedProperty = "uml::Property::properties::redefinedProperty";
			
			
			public static String owningAssociation = "uml::Property::properties::owningAssociation";
			
			
			public static String subsettedProperty = "uml::Property::properties::subsettedProperty";
			
			
			public static String association = "uml::Property::properties::association";
			
			
			public static String associationEnd = "uml::Property::properties::associationEnd";
			
	
		}
	
	}

	/**
	 * Deployment view descriptor
	 * 
	 */
	public static class Deployment {
		public static class Properties {
	
			
			public static String name = "uml::Deployment::properties::name";
			
			
			public static String visibility = "uml::Deployment::properties::visibility";
			
			
			public static String clientDependency = "uml::Deployment::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Deployment::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Deployment::properties::templateParameter";
			
			
			public static String supplier = "uml::Deployment::properties::supplier";
			
			
			public static String client = "uml::Deployment::properties::client";
			
			
			public static String deployedArtifact = "uml::Deployment::properties::deployedArtifact";
			
			
			public static String location = "uml::Deployment::properties::location";
			
	
		}
	
	}

	/**
	 * DeploymentSpecification view descriptor
	 * 
	 */
	public static class DeploymentSpecification {
		public static class Properties {
	
			
			public static String name = "uml::DeploymentSpecification::properties::name";
			
			
			public static String visibility = "uml::DeploymentSpecification::properties::visibility";
			
			
			public static String clientDependency = "uml::DeploymentSpecification::properties::clientDependency";
			
			
			public static String isLeaf = "uml::DeploymentSpecification::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::DeploymentSpecification::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::DeploymentSpecification::properties::templateParameter";
			
			
			public static String isAbstract = "uml::DeploymentSpecification::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::DeploymentSpecification::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::DeploymentSpecification::properties::redefinedClassifier";
			
			
			public static String representation = "uml::DeploymentSpecification::properties::representation";
			
			
			public static String useCase = "uml::DeploymentSpecification::properties::useCase";
			
			
			public static String fileName = "uml::DeploymentSpecification::properties::fileName";
			
			
			public static String deploymentLocation = "uml::DeploymentSpecification::properties::deploymentLocation";
			
			
			public static String executionLocation = "uml::DeploymentSpecification::properties::executionLocation";
			
			
			public static String deployment = "uml::DeploymentSpecification::properties::deployment";
			
	
		}
	
	}

	/**
	 * Artifact view descriptor
	 * 
	 */
	public static class Artifact {
		public static class Properties {
	
			
			public static String name = "uml::Artifact::properties::name";
			
			
			public static String visibility = "uml::Artifact::properties::visibility";
			
			
			public static String clientDependency = "uml::Artifact::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Artifact::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Artifact::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Artifact::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Artifact::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Artifact::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Artifact::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Artifact::properties::representation";
			
			
			public static String useCase = "uml::Artifact::properties::useCase";
			
			
			public static String fileName = "uml::Artifact::properties::fileName";
			
	
		}
	
	}

	/**
	 * Manifestation view descriptor
	 * 
	 */
	public static class Manifestation {
		public static class Properties {
	
			
			public static String name = "uml::Manifestation::properties::name";
			
			
			public static String visibility = "uml::Manifestation::properties::visibility";
			
			
			public static String clientDependency = "uml::Manifestation::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Manifestation::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Manifestation::properties::templateParameter";
			
			
			public static String supplier = "uml::Manifestation::properties::supplier";
			
			
			public static String client = "uml::Manifestation::properties::client";
			
			
			public static String utilizedElement = "uml::Manifestation::properties::utilizedElement";
			
	
		}
	
	}

	/**
	 * Operation view descriptor
	 * 
	 */
	public static class Operation {
		public static class Properties {
	
			
			public static String name = "uml::Operation::properties::name";
			
			
			public static String visibility = "uml::Operation::properties::visibility";
			
			
			public static String clientDependency = "uml::Operation::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Operation::properties::isLeaf";
			
			
			public static String isStatic = "uml::Operation::properties::isStatic";
			
			
			public static String isAbstract = "uml::Operation::properties::isAbstract";
			
			
			public static String method = "uml::Operation::properties::method";
			
			
			public static String concurrency = "uml::Operation::properties::concurrency";
			
			
			public static String raisedException = "uml::Operation::properties::raisedException";
			
			
			public static String owningTemplateParameter = "uml::Operation::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Operation::properties::templateParameter";
			
			
			public static String interface_ = "uml::Operation::properties::interface";
			
			
			public static String class_ = "uml::Operation::properties::class";
			
			
			public static String isQuery = "uml::Operation::properties::isQuery";
			
			
			public static String precondition = "uml::Operation::properties::precondition";
			
			
			public static String postcondition = "uml::Operation::properties::postcondition";
			
			
			public static String redefinedOperation = "uml::Operation::properties::redefinedOperation";
			
			
			public static String datatype = "uml::Operation::properties::datatype";
			
			
			public static String bodyCondition = "uml::Operation::properties::bodyCondition";
			
	
		}
	
	}

	/**
	 * Class view descriptor
	 * 
	 */
	public static class Class_ {
		public static class Properties {
	
			
			public static String name = "uml::Class::properties::name";
			
			
			public static String visibility = "uml::Class::properties::visibility";
			
			
			public static String clientDependency = "uml::Class::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Class::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Class::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Class::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Class::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Class::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Class::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Class::properties::representation";
			
			
			public static String useCase = "uml::Class::properties::useCase";
			
			
			public static String classifierBehavior = "uml::Class::properties::classifierBehavior";
			
			
			public static String isActive = "uml::Class::properties::isActive";
			
	
		}
	
	}

	/**
	 * InterfaceRealization view descriptor
	 * 
	 */
	public static class InterfaceRealization {
		public static class Properties {
	
			
			public static String name = "uml::InterfaceRealization::properties::name";
			
			
			public static String visibility = "uml::InterfaceRealization::properties::visibility";
			
			
			public static String clientDependency = "uml::InterfaceRealization::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::InterfaceRealization::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::InterfaceRealization::properties::templateParameter";
			
			
			public static String supplier = "uml::InterfaceRealization::properties::supplier";
			
			
			public static String client = "uml::InterfaceRealization::properties::client";
			
			
			public static String contract = "uml::InterfaceRealization::properties::contract";
			
			
			public static String implementingClassifier = "uml::InterfaceRealization::properties::implementingClassifier";
			
	
		}
	
	}

	/**
	 * Interface view descriptor
	 * 
	 */
	public static class Interface_ {
		public static class Properties {
	
			
			public static String name = "uml::Interface::properties::name";
			
			
			public static String visibility = "uml::Interface::properties::visibility";
			
			
			public static String clientDependency = "uml::Interface::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Interface::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Interface::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Interface::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Interface::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Interface::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Interface::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Interface::properties::representation";
			
			
			public static String useCase = "uml::Interface::properties::useCase";
			
			
			public static String redefinedInterface = "uml::Interface::properties::redefinedInterface";
			
	
		}
	
	}

	/**
	 * Reception view descriptor
	 * 
	 */
	public static class Reception {
		public static class Properties {
	
			
			public static String name = "uml::Reception::properties::name";
			
			
			public static String visibility = "uml::Reception::properties::visibility";
			
			
			public static String clientDependency = "uml::Reception::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Reception::properties::isLeaf";
			
			
			public static String isStatic = "uml::Reception::properties::isStatic";
			
			
			public static String isAbstract = "uml::Reception::properties::isAbstract";
			
			
			public static String method = "uml::Reception::properties::method";
			
			
			public static String concurrency = "uml::Reception::properties::concurrency";
			
			
			public static String raisedException = "uml::Reception::properties::raisedException";
			
			
			public static String signal = "uml::Reception::properties::signal";
			
	
		}
	
	}

	/**
	 * Signal view descriptor
	 * 
	 */
	public static class Signal {
		public static class Properties {
	
			
			public static String name = "uml::Signal::properties::name";
			
			
			public static String visibility = "uml::Signal::properties::visibility";
			
			
			public static String clientDependency = "uml::Signal::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Signal::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Signal::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Signal::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Signal::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Signal::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Signal::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Signal::properties::representation";
			
			
			public static String useCase = "uml::Signal::properties::useCase";
			
	
		}
	
	}

	/**
	 * ProtocolStateMachine view descriptor
	 * 
	 */
	public static class ProtocolStateMachine {
		public static class Properties {
	
			
			public static String name = "uml::ProtocolStateMachine::properties::name";
			
			
			public static String visibility = "uml::ProtocolStateMachine::properties::visibility";
			
			
			public static String clientDependency = "uml::ProtocolStateMachine::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ProtocolStateMachine::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::ProtocolStateMachine::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::ProtocolStateMachine::properties::templateParameter";
			
			
			public static String isAbstract = "uml::ProtocolStateMachine::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::ProtocolStateMachine::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::ProtocolStateMachine::properties::redefinedClassifier";
			
			
			public static String representation = "uml::ProtocolStateMachine::properties::representation";
			
			
			public static String useCase = "uml::ProtocolStateMachine::properties::useCase";
			
			
			public static String classifierBehavior = "uml::ProtocolStateMachine::properties::classifierBehavior";
			
			
			public static String isActive = "uml::ProtocolStateMachine::properties::isActive";
			
			
			public static String isReentrant = "uml::ProtocolStateMachine::properties::isReentrant";
			
			
			public static String redefinedBehavior = "uml::ProtocolStateMachine::properties::redefinedBehavior";
			
			
			public static String precondition = "uml::ProtocolStateMachine::properties::precondition";
			
			
			public static String postcondition = "uml::ProtocolStateMachine::properties::postcondition";
			
			
			public static String specification = "uml::ProtocolStateMachine::properties::specification";
			
			
			public static String submachineState = "uml::ProtocolStateMachine::properties::submachineState";
			
			
			public static String extendedStateMachine = "uml::ProtocolStateMachine::properties::extendedStateMachine";
			
	
		}
	
	}

	/**
	 * StateMachine view descriptor
	 * 
	 */
	public static class StateMachine {
		public static class Properties {
	
			
			public static String name = "uml::StateMachine::properties::name";
			
			
			public static String visibility = "uml::StateMachine::properties::visibility";
			
			
			public static String clientDependency = "uml::StateMachine::properties::clientDependency";
			
			
			public static String isLeaf = "uml::StateMachine::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::StateMachine::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::StateMachine::properties::templateParameter";
			
			
			public static String isAbstract = "uml::StateMachine::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::StateMachine::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::StateMachine::properties::redefinedClassifier";
			
			
			public static String representation = "uml::StateMachine::properties::representation";
			
			
			public static String useCase = "uml::StateMachine::properties::useCase";
			
			
			public static String classifierBehavior = "uml::StateMachine::properties::classifierBehavior";
			
			
			public static String isActive = "uml::StateMachine::properties::isActive";
			
			
			public static String isReentrant = "uml::StateMachine::properties::isReentrant";
			
			
			public static String redefinedBehavior = "uml::StateMachine::properties::redefinedBehavior";
			
			
			public static String precondition = "uml::StateMachine::properties::precondition";
			
			
			public static String postcondition = "uml::StateMachine::properties::postcondition";
			
			
			public static String specification = "uml::StateMachine::properties::specification";
			
			
			public static String submachineState = "uml::StateMachine::properties::submachineState";
			
			
			public static String extendedStateMachine = "uml::StateMachine::properties::extendedStateMachine";
			
	
		}
	
	}

	/**
	 * Region view descriptor
	 * 
	 */
	public static class Region {
		public static class Properties {
	
			
			public static String name = "uml::Region::properties::name";
			
			
			public static String visibility = "uml::Region::properties::visibility";
			
			
			public static String clientDependency = "uml::Region::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Region::properties::isLeaf";
			
			
			public static String state = "uml::Region::properties::state";
			
			
			public static String extendedRegion = "uml::Region::properties::extendedRegion";
			
			
			public static String stateMachine = "uml::Region::properties::stateMachine";
			
	
		}
	
	}

	/**
	 * Transition view descriptor
	 * 
	 */
	public static class Transition {
		public static class Properties {
	
			
			public static String name = "uml::Transition::properties::name";
			
			
			public static String visibility = "uml::Transition::properties::visibility";
			
			
			public static String clientDependency = "uml::Transition::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Transition::properties::isLeaf";
			
			
			public static String kind = "uml::Transition::properties::kind";
			
			
			public static String container = "uml::Transition::properties::container";
			
			
			public static String source = "uml::Transition::properties::source";
			
			
			public static String target = "uml::Transition::properties::target";
			
			
			public static String redefinedTransition = "uml::Transition::properties::redefinedTransition";
			
			
			public static String guard = "uml::Transition::properties::guard";
			
	
		}
	
	}

	/**
	 * Trigger view descriptor
	 * 
	 */
	public static class Trigger {
		public static class Properties {
	
			
			public static String name = "uml::Trigger::properties::name";
			
			
			public static String visibility = "uml::Trigger::properties::visibility";
			
			
			public static String clientDependency = "uml::Trigger::properties::clientDependency";
			
			
			public static String event = "uml::Trigger::properties::event";
			
			
			public static String port = "uml::Trigger::properties::port";
			
	
		}
	
	}

	/**
	 * Port view descriptor
	 * 
	 */
	public static class Port {
		public static class Properties {
	
			
			public static String name = "uml::Port::properties::name";
			
			
			public static String visibility = "uml::Port::properties::visibility";
			
			
			public static String clientDependency = "uml::Port::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Port::properties::isLeaf";
			
			
			public static String isStatic = "uml::Port::properties::isStatic";
			
			
			public static String type = "uml::Port::properties::type";
			
			
			public static String isOrdered = "uml::Port::properties::isOrdered";
			
			
			public static String isUnique = "uml::Port::properties::isUnique";
			
			
			public static String isReadOnly = "uml::Port::properties::isReadOnly";
			
			
			public static String owningTemplateParameter = "uml::Port::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Port::properties::templateParameter";
			
			
			public static String class_ = "uml::Port::properties::class";
			
			
			public static String datatype = "uml::Port::properties::datatype";
			
			
			public static String isDerived = "uml::Port::properties::isDerived";
			
			
			public static String isDerivedUnion = "uml::Port::properties::isDerivedUnion";
			
			
			public static String aggregation = "uml::Port::properties::aggregation";
			
			
			public static String redefinedProperty = "uml::Port::properties::redefinedProperty";
			
			
			public static String owningAssociation = "uml::Port::properties::owningAssociation";
			
			
			public static String subsettedProperty = "uml::Port::properties::subsettedProperty";
			
			
			public static String association = "uml::Port::properties::association";
			
			
			public static String associationEnd = "uml::Port::properties::associationEnd";
			
			
			public static String isBehavior = "uml::Port::properties::isBehavior";
			
			
			public static String isService = "uml::Port::properties::isService";
			
			
			public static String redefinedPort = "uml::Port::properties::redefinedPort";
			
			
			public static String protocol = "uml::Port::properties::protocol";
			
	
		}
	
	}

	/**
	 * State view descriptor
	 * 
	 */
	public static class State {
		public static class Properties {
	
			
			public static String name = "uml::State::properties::name";
			
			
			public static String visibility = "uml::State::properties::visibility";
			
			
			public static String clientDependency = "uml::State::properties::clientDependency";
			
			
			public static String isLeaf = "uml::State::properties::isLeaf";
			
			
			public static String container = "uml::State::properties::container";
			
			
			public static String submachine = "uml::State::properties::submachine";
			
			
			public static String redefinedState = "uml::State::properties::redefinedState";
			
	
		}
	
	}

	/**
	 * ConnectionPointReference view descriptor
	 * 
	 */
	public static class ConnectionPointReference {
		public static class Properties {
	
			
			public static String name = "uml::ConnectionPointReference::properties::name";
			
			
			public static String visibility = "uml::ConnectionPointReference::properties::visibility";
			
			
			public static String clientDependency = "uml::ConnectionPointReference::properties::clientDependency";
			
			
			public static String container = "uml::ConnectionPointReference::properties::container";
			
			
			public static String entry = "uml::ConnectionPointReference::properties::entry";
			
			
			public static String exit = "uml::ConnectionPointReference::properties::exit";
			
			
			public static String state = "uml::ConnectionPointReference::properties::state";
			
	
		}
	
	}

	/**
	 * Pseudostate view descriptor
	 * 
	 */
	public static class Pseudostate {
		public static class Properties {
	
			
			public static String name = "uml::Pseudostate::properties::name";
			
			
			public static String visibility = "uml::Pseudostate::properties::visibility";
			
			
			public static String clientDependency = "uml::Pseudostate::properties::clientDependency";
			
			
			public static String container = "uml::Pseudostate::properties::container";
			
			
			public static String kind = "uml::Pseudostate::properties::kind";
			
			
			public static String stateMachine = "uml::Pseudostate::properties::stateMachine";
			
			
			public static String state = "uml::Pseudostate::properties::state";
			
	
		}
	
	}

	/**
	 * ProtocolConformance view descriptor
	 * 
	 */
	public static class ProtocolConformance {
		public static class Properties {
	
			
			public static String generalMachine = "uml::ProtocolConformance::properties::generalMachine";
			
			
			public static String specificMachine = "uml::ProtocolConformance::properties::specificMachine";
			
	
		}
	
	}

	/**
	 * Connector view descriptor
	 * 
	 */
	public static class Connector {
		public static class Properties {
	
			
			public static String name = "uml::Connector::properties::name";
			
			
			public static String visibility = "uml::Connector::properties::visibility";
			
			
			public static String clientDependency = "uml::Connector::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Connector::properties::isLeaf";
			
			
			public static String isStatic = "uml::Connector::properties::isStatic";
			
			
			public static String type = "uml::Connector::properties::type";
			
			
			public static String redefinedConnector = "uml::Connector::properties::redefinedConnector";
			
			
			public static String kind = "uml::Connector::properties::kind";
			
			
			public static String contract = "uml::Connector::properties::contract";
			
	
		}
	
	}

	/**
	 * Extension view descriptor
	 * 
	 */
	public static class Extension {
		public static class Properties {
	
			
			public static String name = "uml::Extension::properties::name";
			
			
			public static String visibility = "uml::Extension::properties::visibility";
			
			
			public static String clientDependency = "uml::Extension::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Extension::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Extension::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Extension::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Extension::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Extension::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Extension::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Extension::properties::representation";
			
			
			public static String useCase = "uml::Extension::properties::useCase";
			
			
			public static String memberEnd = "uml::Extension::properties::memberEnd";
			
			
			public static String isDerived = "uml::Extension::properties::isDerived";
			
			
			public static String navigableOwnedEnd = "uml::Extension::properties::navigableOwnedEnd";
			
	
		}
	
	}

	/**
	 * ExtensionEnd view descriptor
	 * 
	 */
	public static class ExtensionEnd {
		public static class Properties {
	
			
			public static String name = "uml::ExtensionEnd::properties::name";
			
			
			public static String visibility = "uml::ExtensionEnd::properties::visibility";
			
			
			public static String clientDependency = "uml::ExtensionEnd::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ExtensionEnd::properties::isLeaf";
			
			
			public static String isStatic = "uml::ExtensionEnd::properties::isStatic";
			
			
			public static String type = "uml::ExtensionEnd::properties::type";
			
			
			public static String isOrdered = "uml::ExtensionEnd::properties::isOrdered";
			
			
			public static String isUnique = "uml::ExtensionEnd::properties::isUnique";
			
			
			public static String isReadOnly = "uml::ExtensionEnd::properties::isReadOnly";
			
			
			public static String owningTemplateParameter = "uml::ExtensionEnd::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::ExtensionEnd::properties::templateParameter";
			
			
			public static String class_ = "uml::ExtensionEnd::properties::class";
			
			
			public static String datatype = "uml::ExtensionEnd::properties::datatype";
			
			
			public static String isDerived = "uml::ExtensionEnd::properties::isDerived";
			
			
			public static String isDerivedUnion = "uml::ExtensionEnd::properties::isDerivedUnion";
			
			
			public static String aggregation = "uml::ExtensionEnd::properties::aggregation";
			
			
			public static String redefinedProperty = "uml::ExtensionEnd::properties::redefinedProperty";
			
			
			public static String owningAssociation = "uml::ExtensionEnd::properties::owningAssociation";
			
			
			public static String subsettedProperty = "uml::ExtensionEnd::properties::subsettedProperty";
			
			
			public static String association = "uml::ExtensionEnd::properties::association";
			
			
			public static String associationEnd = "uml::ExtensionEnd::properties::associationEnd";
			
	
		}
	
	}

	/**
	 * Stereotype view descriptor
	 * 
	 */
	public static class Stereotype {
		public static class Properties {
	
			
			public static String name = "uml::Stereotype::properties::name";
			
			
			public static String visibility = "uml::Stereotype::properties::visibility";
			
			
			public static String clientDependency = "uml::Stereotype::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Stereotype::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Stereotype::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Stereotype::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Stereotype::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Stereotype::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Stereotype::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Stereotype::properties::representation";
			
			
			public static String useCase = "uml::Stereotype::properties::useCase";
			
			
			public static String classifierBehavior = "uml::Stereotype::properties::classifierBehavior";
			
			
			public static String isActive = "uml::Stereotype::properties::isActive";
			
	
		}
	
	}

	/**
	 * Image view descriptor
	 * 
	 */
	public static class Image {
		public static class Properties {
	
			
			public static String content = "uml::Image::properties::content";
			
			
			public static String location = "uml::Image::properties::location";
			
			
			public static String format = "uml::Image::properties::format";
			
	
		}
	
	}

	/**
	 * Profile view descriptor
	 * 
	 */
	public static class Profile {
		public static class Properties {
	
			
			public static String name = "uml::Profile::properties::name";
			
			
			public static String visibility = "uml::Profile::properties::visibility";
			
			
			public static String clientDependency = "uml::Profile::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Profile::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Profile::properties::templateParameter";
			
			
			public static String metaclassReference = "uml::Profile::properties::metaclassReference";
			
			
			public static String metamodelReference = "uml::Profile::properties::metamodelReference";
			
	
		}
	
	}

	/**
	 * Model view descriptor
	 * 
	 */
	public static class Model {
		public static class Properties {
	
			
			public static String name = "uml::Model::properties::name";
			
			
			public static String visibility = "uml::Model::properties::visibility";
			
			
			public static String clientDependency = "uml::Model::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Model::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Model::properties::templateParameter";
			
			
			public static String viewpoint = "uml::Model::properties::viewpoint";
			
	
		}
	
	}

	/**
	 * ParameterSet view descriptor
	 * 
	 */
	public static class ParameterSet {
		public static class Properties {
	
			
			public static String name = "uml::ParameterSet::properties::name";
			
			
			public static String visibility = "uml::ParameterSet::properties::visibility";
			
			
			public static String clientDependency = "uml::ParameterSet::properties::clientDependency";
			
			
			public static String parameter = "uml::ParameterSet::properties::parameter";
			
	
		}
	
	}

	/**
	 * DataType view descriptor
	 * 
	 */
	public static class DataType {
		public static class Properties {
	
			
			public static String name = "uml::DataType::properties::name";
			
			
			public static String visibility = "uml::DataType::properties::visibility";
			
			
			public static String clientDependency = "uml::DataType::properties::clientDependency";
			
			
			public static String isLeaf = "uml::DataType::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::DataType::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::DataType::properties::templateParameter";
			
			
			public static String isAbstract = "uml::DataType::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::DataType::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::DataType::properties::redefinedClassifier";
			
			
			public static String representation = "uml::DataType::properties::representation";
			
			
			public static String useCase = "uml::DataType::properties::useCase";
			
	
		}
	
	}

	/**
	 * OperationTemplateParameter view descriptor
	 * 
	 */
	public static class OperationTemplateParameter {
		public static class Properties {
	
			
			public static String signature = "uml::OperationTemplateParameter::properties::signature";
			
			
			public static String parameteredElement = "uml::OperationTemplateParameter::properties::parameteredElement";
			
			
			public static String default_ = "uml::OperationTemplateParameter::properties::default";
			
	
		}
	
	}

	/**
	 * ConnectableElementTemplateParameter view descriptor
	 * 
	 */
	public static class ConnectableElementTemplateParameter {
		public static class Properties {
	
			
			public static String signature = "uml::ConnectableElementTemplateParameter::properties::signature";
			
			
			public static String parameteredElement = "uml::ConnectableElementTemplateParameter::properties::parameteredElement";
			
			
			public static String default_ = "uml::ConnectableElementTemplateParameter::properties::default";
			
	
		}
	
	}

	/**
	 * CollaborationUse view descriptor
	 * 
	 */
	public static class CollaborationUse {
		public static class Properties {
	
			
			public static String name = "uml::CollaborationUse::properties::name";
			
			
			public static String visibility = "uml::CollaborationUse::properties::visibility";
			
			
			public static String clientDependency = "uml::CollaborationUse::properties::clientDependency";
			
			
			public static String type = "uml::CollaborationUse::properties::type";
			
	
		}
	
	}

	/**
	 * Collaboration view descriptor
	 * 
	 */
	public static class Collaboration {
		public static class Properties {
	
			
			public static String name = "uml::Collaboration::properties::name";
			
			
			public static String visibility = "uml::Collaboration::properties::visibility";
			
			
			public static String clientDependency = "uml::Collaboration::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Collaboration::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Collaboration::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Collaboration::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Collaboration::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Collaboration::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Collaboration::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Collaboration::properties::representation";
			
			
			public static String useCase = "uml::Collaboration::properties::useCase";
			
			
			public static String classifierBehavior = "uml::Collaboration::properties::classifierBehavior";
			
			
			public static String collaborationRole = "uml::Collaboration::properties::collaborationRole";
			
	
		}
	
	}

	/**
	 * UseCase view descriptor
	 * 
	 */
	public static class UseCase {
		public static class Properties {
	
			
			public static String name = "uml::UseCase::properties::name";
			
			
			public static String visibility = "uml::UseCase::properties::visibility";
			
			
			public static String clientDependency = "uml::UseCase::properties::clientDependency";
			
			
			public static String isLeaf = "uml::UseCase::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::UseCase::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::UseCase::properties::templateParameter";
			
			
			public static String isAbstract = "uml::UseCase::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::UseCase::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::UseCase::properties::redefinedClassifier";
			
			
			public static String representation = "uml::UseCase::properties::representation";
			
			
			public static String useCase_ = "uml::UseCase::properties::useCase_";
			
			
			public static String classifierBehavior = "uml::UseCase::properties::classifierBehavior";
			
			
			public static String subject = "uml::UseCase::properties::subject";
			
	
		}
	
	}

	/**
	 * Include view descriptor
	 * 
	 */
	public static class Include {
		public static class Properties {
	
			
			public static String name = "uml::Include::properties::name";
			
			
			public static String visibility = "uml::Include::properties::visibility";
			
			
			public static String clientDependency = "uml::Include::properties::clientDependency";
			
			
			public static String addition = "uml::Include::properties::addition";
			
			
			public static String includingCase = "uml::Include::properties::includingCase";
			
	
		}
	
	}

	/**
	 * Extend view descriptor
	 * 
	 */
	public static class Extend {
		public static class Properties {
	
			
			public static String name = "uml::Extend::properties::name";
			
			
			public static String visibility = "uml::Extend::properties::visibility";
			
			
			public static String clientDependency = "uml::Extend::properties::clientDependency";
			
			
			public static String extendedCase = "uml::Extend::properties::extendedCase";
			
			
			public static String extensionLocation = "uml::Extend::properties::extensionLocation";
			
			
			public static String extension = "uml::Extend::properties::extension";
			
	
		}
	
	}

	/**
	 * ExtensionPoint view descriptor
	 * 
	 */
	public static class ExtensionPoint {
		public static class Properties {
	
			
			public static String name = "uml::ExtensionPoint::properties::name";
			
			
			public static String visibility = "uml::ExtensionPoint::properties::visibility";
			
			
			public static String clientDependency = "uml::ExtensionPoint::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ExtensionPoint::properties::isLeaf";
			
			
			public static String useCase = "uml::ExtensionPoint::properties::useCase";
			
	
		}
	
	}

	/**
	 * RedefinableTemplateSignature view descriptor
	 * 
	 */
	public static class RedefinableTemplateSignature {
		public static class Properties {
	
			
			public static String name = "uml::RedefinableTemplateSignature::properties::name";
			
			
			public static String visibility = "uml::RedefinableTemplateSignature::properties::visibility";
			
			
			public static String clientDependency = "uml::RedefinableTemplateSignature::properties::clientDependency";
			
			
			public static String isLeaf = "uml::RedefinableTemplateSignature::properties::isLeaf";
			
			
			public static String parameter = "uml::RedefinableTemplateSignature::properties::parameter";
			
			
			public static String template = "uml::RedefinableTemplateSignature::properties::template";
			
			
			public static String extendedSignature = "uml::RedefinableTemplateSignature::properties::extendedSignature";
			
			
			public static String classifier = "uml::RedefinableTemplateSignature::properties::classifier";
			
	
		}
	
	}

	/**
	 * ClassifierTemplateParameter view descriptor
	 * 
	 */
	public static class ClassifierTemplateParameter {
		public static class Properties {
	
			
			public static String signature = "uml::ClassifierTemplateParameter::properties::signature";
			
			
			public static String parameteredElement = "uml::ClassifierTemplateParameter::properties::parameteredElement";
			
			
			public static String default_ = "uml::ClassifierTemplateParameter::properties::default";
			
			
			public static String allowSubstitutable = "uml::ClassifierTemplateParameter::properties::allowSubstitutable";
			
			
			public static String constrainingClassifier = "uml::ClassifierTemplateParameter::properties::constrainingClassifier";
			
	
		}
	
	}

	/**
	 * StringExpression view descriptor
	 * 
	 */
	public static class StringExpression {
		public static class Properties {
	
			
			public static String name = "uml::StringExpression::properties::name";
			
			
			public static String visibility = "uml::StringExpression::properties::visibility";
			
			
			public static String clientDependency = "uml::StringExpression::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::StringExpression::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::StringExpression::properties::templateParameter";
			
			
			public static String type = "uml::StringExpression::properties::type";
			
			
			public static String symbol = "uml::StringExpression::properties::symbol";
			
			
			public static String owningExpression = "uml::StringExpression::properties::owningExpression";
			
	
		}
	
	}

	/**
	 * Expression view descriptor
	 * 
	 */
	public static class Expression {
		public static class Properties {
	
			
			public static String name = "uml::Expression::properties::name";
			
			
			public static String visibility = "uml::Expression::properties::visibility";
			
			
			public static String clientDependency = "uml::Expression::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Expression::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Expression::properties::templateParameter";
			
			
			public static String type = "uml::Expression::properties::type";
			
			
			public static String symbol = "uml::Expression::properties::symbol";
			
	
		}
	
	}

	/**
	 * Usage view descriptor
	 * 
	 */
	public static class Usage {
		public static class Properties {
	
			
			public static String name = "uml::Usage::properties::name";
			
			
			public static String visibility = "uml::Usage::properties::visibility";
			
			
			public static String clientDependency = "uml::Usage::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Usage::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Usage::properties::templateParameter";
			
			
			public static String supplier = "uml::Usage::properties::supplier";
			
			
			public static String client = "uml::Usage::properties::client";
			
	
		}
	
	}

	/**
	 * PackageMerge view descriptor
	 * 
	 */
	public static class PackageMerge {
		public static class Properties {
	
			
			public static String mergedPackage = "uml::PackageMerge::properties::mergedPackage";
			
			
			public static String receivingPackage = "uml::PackageMerge::properties::receivingPackage";
			
	
		}
	
	}

	/**
	 * ProfileApplication view descriptor
	 * 
	 */
	public static class ProfileApplication {
		public static class Properties {
	
			
			public static String appliedProfile = "uml::ProfileApplication::properties::appliedProfile";
			
			
			public static String isStrict = "uml::ProfileApplication::properties::isStrict";
			
			
			public static String applyingPackage = "uml::ProfileApplication::properties::applyingPackage";
			
	
		}
	
	}

	/**
	 * Enumeration view descriptor
	 * 
	 */
	public static class Enumeration {
		public static class Properties {
	
			
			public static String name = "uml::Enumeration::properties::name";
			
			
			public static String visibility = "uml::Enumeration::properties::visibility";
			
			
			public static String clientDependency = "uml::Enumeration::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Enumeration::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Enumeration::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Enumeration::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Enumeration::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Enumeration::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Enumeration::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Enumeration::properties::representation";
			
			
			public static String useCase = "uml::Enumeration::properties::useCase";
			
	
		}
	
	}

	/**
	 * EnumerationLiteral view descriptor
	 * 
	 */
	public static class EnumerationLiteral {
		public static class Properties {
	
			
			public static String name = "uml::EnumerationLiteral::properties::name";
			
			
			public static String visibility = "uml::EnumerationLiteral::properties::visibility";
			
			
			public static String clientDependency = "uml::EnumerationLiteral::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::EnumerationLiteral::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::EnumerationLiteral::properties::templateParameter";
			
			
			public static String classifier = "uml::EnumerationLiteral::properties::classifier";
			
			
			public static String enumeration = "uml::EnumerationLiteral::properties::enumeration";
			
	
		}
	
	}

	/**
	 * InstanceSpecification view descriptor
	 * 
	 */
	public static class InstanceSpecification {
		public static class Properties {
	
			
			public static String name = "uml::InstanceSpecification::properties::name";
			
			
			public static String visibility = "uml::InstanceSpecification::properties::visibility";
			
			
			public static String clientDependency = "uml::InstanceSpecification::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::InstanceSpecification::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::InstanceSpecification::properties::templateParameter";
			
			
			public static String classifier = "uml::InstanceSpecification::properties::classifier";
			
	
		}
	
	}

	/**
	 * Slot view descriptor
	 * 
	 */
	public static class Slot {
		public static class Properties {
	
			
			public static String definingFeature = "uml::Slot::properties::definingFeature";
			
			
			public static String owningInstance = "uml::Slot::properties::owningInstance";
			
	
		}
	
	}

	/**
	 * PrimitiveType view descriptor
	 * 
	 */
	public static class PrimitiveType {
		public static class Properties {
	
			
			public static String name = "uml::PrimitiveType::properties::name";
			
			
			public static String visibility = "uml::PrimitiveType::properties::visibility";
			
			
			public static String clientDependency = "uml::PrimitiveType::properties::clientDependency";
			
			
			public static String isLeaf = "uml::PrimitiveType::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::PrimitiveType::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::PrimitiveType::properties::templateParameter";
			
			
			public static String isAbstract = "uml::PrimitiveType::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::PrimitiveType::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::PrimitiveType::properties::redefinedClassifier";
			
			
			public static String representation = "uml::PrimitiveType::properties::representation";
			
			
			public static String useCase = "uml::PrimitiveType::properties::useCase";
			
	
		}
	
	}

	/**
	 * LiteralInteger view descriptor
	 * 
	 */
	public static class LiteralInteger {
		public static class Properties {
	
			
			public static String name = "uml::LiteralInteger::properties::name";
			
			
			public static String visibility = "uml::LiteralInteger::properties::visibility";
			
			
			public static String clientDependency = "uml::LiteralInteger::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::LiteralInteger::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::LiteralInteger::properties::templateParameter";
			
			
			public static String type = "uml::LiteralInteger::properties::type";
			
			
			public static String value = "uml::LiteralInteger::properties::value";
			
	
		}
	
	}

	/**
	 * LiteralString view descriptor
	 * 
	 */
	public static class LiteralString {
		public static class Properties {
	
			
			public static String name = "uml::LiteralString::properties::name";
			
			
			public static String visibility = "uml::LiteralString::properties::visibility";
			
			
			public static String clientDependency = "uml::LiteralString::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::LiteralString::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::LiteralString::properties::templateParameter";
			
			
			public static String type = "uml::LiteralString::properties::type";
			
			
			public static String value = "uml::LiteralString::properties::value";
			
	
		}
	
	}

	/**
	 * LiteralBoolean view descriptor
	 * 
	 */
	public static class LiteralBoolean {
		public static class Properties {
	
			
			public static String name = "uml::LiteralBoolean::properties::name";
			
			
			public static String visibility = "uml::LiteralBoolean::properties::visibility";
			
			
			public static String clientDependency = "uml::LiteralBoolean::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::LiteralBoolean::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::LiteralBoolean::properties::templateParameter";
			
			
			public static String type = "uml::LiteralBoolean::properties::type";
			
			
			public static String value = "uml::LiteralBoolean::properties::value";
			
	
		}
	
	}

	/**
	 * LiteralNull view descriptor
	 * 
	 */
	public static class LiteralNull {
		public static class Properties {
	
			
			public static String name = "uml::LiteralNull::properties::name";
			
			
			public static String visibility = "uml::LiteralNull::properties::visibility";
			
			
			public static String clientDependency = "uml::LiteralNull::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::LiteralNull::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::LiteralNull::properties::templateParameter";
			
			
			public static String type = "uml::LiteralNull::properties::type";
			
	
		}
	
	}

	/**
	 * InstanceValue view descriptor
	 * 
	 */
	public static class InstanceValue {
		public static class Properties {
	
			
			public static String name = "uml::InstanceValue::properties::name";
			
			
			public static String visibility = "uml::InstanceValue::properties::visibility";
			
			
			public static String clientDependency = "uml::InstanceValue::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::InstanceValue::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::InstanceValue::properties::templateParameter";
			
			
			public static String type = "uml::InstanceValue::properties::type";
			
			
			public static String instance = "uml::InstanceValue::properties::instance";
			
	
		}
	
	}

	/**
	 * LiteralUnlimitedNatural view descriptor
	 * 
	 */
	public static class LiteralUnlimitedNatural {
		public static class Properties {
	
			
			public static String name = "uml::LiteralUnlimitedNatural::properties::name";
			
			
			public static String visibility = "uml::LiteralUnlimitedNatural::properties::visibility";
			
			
			public static String clientDependency = "uml::LiteralUnlimitedNatural::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::LiteralUnlimitedNatural::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::LiteralUnlimitedNatural::properties::templateParameter";
			
			
			public static String type = "uml::LiteralUnlimitedNatural::properties::type";
			
			
			public static String value = "uml::LiteralUnlimitedNatural::properties::value";
			
	
		}
	
	}

	/**
	 * OpaqueBehavior view descriptor
	 * 
	 */
	public static class OpaqueBehavior {
		public static class Properties {
	
			
			public static String name = "uml::OpaqueBehavior::properties::name";
			
			
			public static String visibility = "uml::OpaqueBehavior::properties::visibility";
			
			
			public static String clientDependency = "uml::OpaqueBehavior::properties::clientDependency";
			
			
			public static String isLeaf = "uml::OpaqueBehavior::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::OpaqueBehavior::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::OpaqueBehavior::properties::templateParameter";
			
			
			public static String isAbstract = "uml::OpaqueBehavior::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::OpaqueBehavior::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::OpaqueBehavior::properties::redefinedClassifier";
			
			
			public static String representation = "uml::OpaqueBehavior::properties::representation";
			
			
			public static String useCase = "uml::OpaqueBehavior::properties::useCase";
			
			
			public static String classifierBehavior = "uml::OpaqueBehavior::properties::classifierBehavior";
			
			
			public static String isActive = "uml::OpaqueBehavior::properties::isActive";
			
			
			public static String isReentrant = "uml::OpaqueBehavior::properties::isReentrant";
			
			
			public static String redefinedBehavior = "uml::OpaqueBehavior::properties::redefinedBehavior";
			
			
			public static String precondition = "uml::OpaqueBehavior::properties::precondition";
			
			
			public static String postcondition = "uml::OpaqueBehavior::properties::postcondition";
			
			
			public static String specification = "uml::OpaqueBehavior::properties::specification";
			
			
			public static String body = "uml::OpaqueBehavior::properties::body";
			
			
			public static String language = "uml::OpaqueBehavior::properties::language";
			
	
		}
	
	}

	/**
	 * FunctionBehavior view descriptor
	 * 
	 */
	public static class FunctionBehavior {
		public static class Properties {
	
			
			public static String name = "uml::FunctionBehavior::properties::name";
			
			
			public static String visibility = "uml::FunctionBehavior::properties::visibility";
			
			
			public static String clientDependency = "uml::FunctionBehavior::properties::clientDependency";
			
			
			public static String isLeaf = "uml::FunctionBehavior::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::FunctionBehavior::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::FunctionBehavior::properties::templateParameter";
			
			
			public static String isAbstract = "uml::FunctionBehavior::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::FunctionBehavior::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::FunctionBehavior::properties::redefinedClassifier";
			
			
			public static String representation = "uml::FunctionBehavior::properties::representation";
			
			
			public static String useCase = "uml::FunctionBehavior::properties::useCase";
			
			
			public static String classifierBehavior = "uml::FunctionBehavior::properties::classifierBehavior";
			
			
			public static String isActive = "uml::FunctionBehavior::properties::isActive";
			
			
			public static String isReentrant = "uml::FunctionBehavior::properties::isReentrant";
			
			
			public static String redefinedBehavior = "uml::FunctionBehavior::properties::redefinedBehavior";
			
			
			public static String precondition = "uml::FunctionBehavior::properties::precondition";
			
			
			public static String postcondition = "uml::FunctionBehavior::properties::postcondition";
			
			
			public static String specification = "uml::FunctionBehavior::properties::specification";
			
			
			public static String body = "uml::FunctionBehavior::properties::body";
			
			
			public static String language = "uml::FunctionBehavior::properties::language";
			
	
		}
	
	}

	/**
	 * OpaqueAction view descriptor
	 * 
	 */
	public static class OpaqueAction {
		public static class Properties {
	
			
			public static String name = "uml::OpaqueAction::properties::name";
			
			
			public static String visibility = "uml::OpaqueAction::properties::visibility";
			
			
			public static String clientDependency = "uml::OpaqueAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::OpaqueAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::OpaqueAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::OpaqueAction::properties::activity";
			
			
			public static String outgoing = "uml::OpaqueAction::properties::outgoing";
			
			
			public static String incoming = "uml::OpaqueAction::properties::incoming";
			
			
			public static String inPartition = "uml::OpaqueAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::OpaqueAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::OpaqueAction::properties::redefinedNode";
			
			
			public static String body = "uml::OpaqueAction::properties::body";
			
			
			public static String language = "uml::OpaqueAction::properties::language";
			
	
		}
	
	}

	/**
	 * StructuredActivityNode view descriptor
	 * 
	 */
	public static class StructuredActivityNode {
		public static class Properties {
	
			
			public static String name = "uml::StructuredActivityNode::properties::name";
			
			
			public static String visibility = "uml::StructuredActivityNode::properties::visibility";
			
			
			public static String clientDependency = "uml::StructuredActivityNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::StructuredActivityNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::StructuredActivityNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::StructuredActivityNode::properties::activity";
			
			
			public static String outgoing = "uml::StructuredActivityNode::properties::outgoing";
			
			
			public static String incoming = "uml::StructuredActivityNode::properties::incoming";
			
			
			public static String inPartition = "uml::StructuredActivityNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::StructuredActivityNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::StructuredActivityNode::properties::redefinedNode";
			
			
			public static String inActivity = "uml::StructuredActivityNode::properties::inActivity";
			
			
			public static String mustIsolate = "uml::StructuredActivityNode::properties::mustIsolate";
			
	
		}
	
	}

	/**
	 * Activity view descriptor
	 * 
	 */
	public static class Activity {
		public static class Properties {
	
			
			public static String name = "uml::Activity::properties::name";
			
			
			public static String visibility = "uml::Activity::properties::visibility";
			
			
			public static String clientDependency = "uml::Activity::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Activity::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Activity::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Activity::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Activity::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Activity::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Activity::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Activity::properties::representation";
			
			
			public static String useCase = "uml::Activity::properties::useCase";
			
			
			public static String classifierBehavior = "uml::Activity::properties::classifierBehavior";
			
			
			public static String isActive = "uml::Activity::properties::isActive";
			
			
			public static String isReentrant = "uml::Activity::properties::isReentrant";
			
			
			public static String redefinedBehavior = "uml::Activity::properties::redefinedBehavior";
			
			
			public static String precondition = "uml::Activity::properties::precondition";
			
			
			public static String postcondition = "uml::Activity::properties::postcondition";
			
			
			public static String specification = "uml::Activity::properties::specification";
			
			
			public static String isReadOnly = "uml::Activity::properties::isReadOnly";
			
			
			public static String partition = "uml::Activity::properties::partition";
			
			
			public static String isSingleExecution = "uml::Activity::properties::isSingleExecution";
			
	
		}
	
	}

	/**
	 * Variable view descriptor
	 * 
	 */
	public static class Variable {
		public static class Properties {
	
			
			public static String name = "uml::Variable::properties::name";
			
			
			public static String visibility = "uml::Variable::properties::visibility";
			
			
			public static String clientDependency = "uml::Variable::properties::clientDependency";
			
			
			public static String type = "uml::Variable::properties::type";
			
			
			public static String owningTemplateParameter = "uml::Variable::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Variable::properties::templateParameter";
			
			
			public static String isOrdered = "uml::Variable::properties::isOrdered";
			
			
			public static String isUnique = "uml::Variable::properties::isUnique";
			
			
			public static String scope = "uml::Variable::properties::scope";
			
			
			public static String activityScope = "uml::Variable::properties::activityScope";
			
	
		}
	
	}

	/**
	 * ActivityPartition view descriptor
	 * 
	 */
	public static class ActivityPartition {
		public static class Properties {
	
			
			public static String name = "uml::ActivityPartition::properties::name";
			
			
			public static String visibility = "uml::ActivityPartition::properties::visibility";
			
			
			public static String clientDependency = "uml::ActivityPartition::properties::clientDependency";
			
			
			public static String inActivity = "uml::ActivityPartition::properties::inActivity";
			
			
			public static String isDimension = "uml::ActivityPartition::properties::isDimension";
			
			
			public static String isExternal = "uml::ActivityPartition::properties::isExternal";
			
			
			public static String node = "uml::ActivityPartition::properties::node";
			
			
			public static String superPartition = "uml::ActivityPartition::properties::superPartition";
			
			
			public static String represents = "uml::ActivityPartition::properties::represents";
			
			
			public static String edge = "uml::ActivityPartition::properties::edge";
			
	
		}
	
	}

	/**
	 * InterruptibleActivityRegion view descriptor
	 * 
	 */
	public static class InterruptibleActivityRegion {
		public static class Properties {
	
			
			public static String inActivity = "uml::InterruptibleActivityRegion::properties::inActivity";
			
			
			public static String node = "uml::InterruptibleActivityRegion::properties::node";
			
			
			public static String interruptingEdge = "uml::InterruptibleActivityRegion::properties::interruptingEdge";
			
	
		}
	
	}

	/**
	 * ExceptionHandler view descriptor
	 * 
	 */
	public static class ExceptionHandler {
		public static class Properties {
	
			
			public static String handlerBody = "uml::ExceptionHandler::properties::handlerBody";
			
			
			public static String exceptionInput = "uml::ExceptionHandler::properties::exceptionInput";
			
			
			public static String exceptionType = "uml::ExceptionHandler::properties::exceptionType";
			
			
			public static String protectedNode = "uml::ExceptionHandler::properties::protectedNode";
			
	
		}
	
	}

	/**
	 * OutputPin view descriptor
	 * 
	 */
	public static class OutputPin {
		public static class Properties {
	
			
			public static String name = "uml::OutputPin::properties::name";
			
			
			public static String visibility = "uml::OutputPin::properties::visibility";
			
			
			public static String clientDependency = "uml::OutputPin::properties::clientDependency";
			
			
			public static String isLeaf = "uml::OutputPin::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::OutputPin::properties::inStructuredNode";
			
			
			public static String activity = "uml::OutputPin::properties::activity";
			
			
			public static String outgoing = "uml::OutputPin::properties::outgoing";
			
			
			public static String incoming = "uml::OutputPin::properties::incoming";
			
			
			public static String inPartition = "uml::OutputPin::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::OutputPin::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::OutputPin::properties::redefinedNode";
			
			
			public static String type = "uml::OutputPin::properties::type";
			
			
			public static String ordering = "uml::OutputPin::properties::ordering";
			
			
			public static String isControlType = "uml::OutputPin::properties::isControlType";
			
			
			public static String inState = "uml::OutputPin::properties::inState";
			
			
			public static String selection = "uml::OutputPin::properties::selection";
			
			
			public static String isOrdered = "uml::OutputPin::properties::isOrdered";
			
			
			public static String isUnique = "uml::OutputPin::properties::isUnique";
			
			
			public static String isControl = "uml::OutputPin::properties::isControl";
			
	
		}
	
	}

	/**
	 * Pin view descriptor
	 * 
	 */
	public static class Pin {
		public static class Properties {
	
			
			public static String name = "uml::Pin::properties::name";
			
			
			public static String visibility = "uml::Pin::properties::visibility";
			
			
			public static String clientDependency = "uml::Pin::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Pin::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::Pin::properties::inStructuredNode";
			
			
			public static String activity = "uml::Pin::properties::activity";
			
			
			public static String outgoing = "uml::Pin::properties::outgoing";
			
			
			public static String incoming = "uml::Pin::properties::incoming";
			
			
			public static String inPartition = "uml::Pin::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::Pin::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::Pin::properties::redefinedNode";
			
			
			public static String type = "uml::Pin::properties::type";
			
			
			public static String ordering = "uml::Pin::properties::ordering";
			
			
			public static String isControlType = "uml::Pin::properties::isControlType";
			
			
			public static String inState = "uml::Pin::properties::inState";
			
			
			public static String selection = "uml::Pin::properties::selection";
			
			
			public static String isOrdered = "uml::Pin::properties::isOrdered";
			
			
			public static String isUnique = "uml::Pin::properties::isUnique";
			
			
			public static String isControl = "uml::Pin::properties::isControl";
			
	
		}
	
	}

	/**
	 * InputPin view descriptor
	 * 
	 */
	public static class InputPin {
		public static class Properties {
	
			
			public static String name = "uml::InputPin::properties::name";
			
			
			public static String visibility = "uml::InputPin::properties::visibility";
			
			
			public static String clientDependency = "uml::InputPin::properties::clientDependency";
			
			
			public static String isLeaf = "uml::InputPin::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::InputPin::properties::inStructuredNode";
			
			
			public static String activity = "uml::InputPin::properties::activity";
			
			
			public static String outgoing = "uml::InputPin::properties::outgoing";
			
			
			public static String incoming = "uml::InputPin::properties::incoming";
			
			
			public static String inPartition = "uml::InputPin::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::InputPin::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::InputPin::properties::redefinedNode";
			
			
			public static String type = "uml::InputPin::properties::type";
			
			
			public static String ordering = "uml::InputPin::properties::ordering";
			
			
			public static String isControlType = "uml::InputPin::properties::isControlType";
			
			
			public static String inState = "uml::InputPin::properties::inState";
			
			
			public static String selection = "uml::InputPin::properties::selection";
			
			
			public static String isOrdered = "uml::InputPin::properties::isOrdered";
			
			
			public static String isUnique = "uml::InputPin::properties::isUnique";
			
			
			public static String isControl = "uml::InputPin::properties::isControl";
			
	
		}
	
	}

	/**
	 * SendSignalAction view descriptor
	 * 
	 */
	public static class SendSignalAction {
		public static class Properties {
	
			
			public static String name = "uml::SendSignalAction::properties::name";
			
			
			public static String visibility = "uml::SendSignalAction::properties::visibility";
			
			
			public static String clientDependency = "uml::SendSignalAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::SendSignalAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::SendSignalAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::SendSignalAction::properties::activity";
			
			
			public static String outgoing = "uml::SendSignalAction::properties::outgoing";
			
			
			public static String incoming = "uml::SendSignalAction::properties::incoming";
			
			
			public static String inPartition = "uml::SendSignalAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::SendSignalAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::SendSignalAction::properties::redefinedNode";
			
			
			public static String onPort = "uml::SendSignalAction::properties::onPort";
			
			
			public static String signal = "uml::SendSignalAction::properties::signal";
			
	
		}
	
	}

	/**
	 * CallOperationAction view descriptor
	 * 
	 */
	public static class CallOperationAction {
		public static class Properties {
	
			
			public static String name = "uml::CallOperationAction::properties::name";
			
			
			public static String visibility = "uml::CallOperationAction::properties::visibility";
			
			
			public static String clientDependency = "uml::CallOperationAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::CallOperationAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::CallOperationAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::CallOperationAction::properties::activity";
			
			
			public static String outgoing = "uml::CallOperationAction::properties::outgoing";
			
			
			public static String incoming = "uml::CallOperationAction::properties::incoming";
			
			
			public static String inPartition = "uml::CallOperationAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::CallOperationAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::CallOperationAction::properties::redefinedNode";
			
			
			public static String onPort = "uml::CallOperationAction::properties::onPort";
			
			
			public static String isSynchronous = "uml::CallOperationAction::properties::isSynchronous";
			
			
			public static String operation = "uml::CallOperationAction::properties::operation";
			
	
		}
	
	}

	/**
	 * CallBehaviorAction view descriptor
	 * 
	 */
	public static class CallBehaviorAction {
		public static class Properties {
	
			
			public static String name = "uml::CallBehaviorAction::properties::name";
			
			
			public static String visibility = "uml::CallBehaviorAction::properties::visibility";
			
			
			public static String clientDependency = "uml::CallBehaviorAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::CallBehaviorAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::CallBehaviorAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::CallBehaviorAction::properties::activity";
			
			
			public static String outgoing = "uml::CallBehaviorAction::properties::outgoing";
			
			
			public static String incoming = "uml::CallBehaviorAction::properties::incoming";
			
			
			public static String inPartition = "uml::CallBehaviorAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::CallBehaviorAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::CallBehaviorAction::properties::redefinedNode";
			
			
			public static String onPort = "uml::CallBehaviorAction::properties::onPort";
			
			
			public static String isSynchronous = "uml::CallBehaviorAction::properties::isSynchronous";
			
			
			public static String behavior = "uml::CallBehaviorAction::properties::behavior";
			
	
		}
	
	}

	/**
	 * SequenceNode view descriptor
	 * 
	 */
	public static class SequenceNode {
		public static class Properties {
	
			
			public static String name = "uml::SequenceNode::properties::name";
			
			
			public static String visibility = "uml::SequenceNode::properties::visibility";
			
			
			public static String clientDependency = "uml::SequenceNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::SequenceNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::SequenceNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::SequenceNode::properties::activity";
			
			
			public static String outgoing = "uml::SequenceNode::properties::outgoing";
			
			
			public static String incoming = "uml::SequenceNode::properties::incoming";
			
			
			public static String inPartition = "uml::SequenceNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::SequenceNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::SequenceNode::properties::redefinedNode";
			
			
			public static String inActivity = "uml::SequenceNode::properties::inActivity";
			
			
			public static String mustIsolate = "uml::SequenceNode::properties::mustIsolate";
			
	
		}
	
	}

	/**
	 * ControlFlow view descriptor
	 * 
	 */
	public static class ControlFlow {
		public static class Properties {
	
			
			public static String name = "uml::ControlFlow::properties::name";
			
			
			public static String visibility = "uml::ControlFlow::properties::visibility";
			
			
			public static String clientDependency = "uml::ControlFlow::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ControlFlow::properties::isLeaf";
			
			
			public static String source = "uml::ControlFlow::properties::source";
			
			
			public static String target = "uml::ControlFlow::properties::target";
			
			
			public static String redefinedEdge = "uml::ControlFlow::properties::redefinedEdge";
			
			
			public static String inPartition = "uml::ControlFlow::properties::inPartition";
			
			
			public static String interrupts = "uml::ControlFlow::properties::interrupts";
			
			
			public static String inStructuredNode = "uml::ControlFlow::properties::inStructuredNode";
			
			
			public static String activity = "uml::ControlFlow::properties::activity";
			
	
		}
	
	}

	/**
	 * InitialNode view descriptor
	 * 
	 */
	public static class InitialNode {
		public static class Properties {
	
			
			public static String name = "uml::InitialNode::properties::name";
			
			
			public static String visibility = "uml::InitialNode::properties::visibility";
			
			
			public static String clientDependency = "uml::InitialNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::InitialNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::InitialNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::InitialNode::properties::activity";
			
			
			public static String outgoing = "uml::InitialNode::properties::outgoing";
			
			
			public static String incoming = "uml::InitialNode::properties::incoming";
			
			
			public static String inPartition = "uml::InitialNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::InitialNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::InitialNode::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * ActivityParameterNode view descriptor
	 * 
	 */
	public static class ActivityParameterNode {
		public static class Properties {
	
			
			public static String name = "uml::ActivityParameterNode::properties::name";
			
			
			public static String visibility = "uml::ActivityParameterNode::properties::visibility";
			
			
			public static String clientDependency = "uml::ActivityParameterNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ActivityParameterNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ActivityParameterNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::ActivityParameterNode::properties::activity";
			
			
			public static String outgoing = "uml::ActivityParameterNode::properties::outgoing";
			
			
			public static String incoming = "uml::ActivityParameterNode::properties::incoming";
			
			
			public static String inPartition = "uml::ActivityParameterNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ActivityParameterNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ActivityParameterNode::properties::redefinedNode";
			
			
			public static String type = "uml::ActivityParameterNode::properties::type";
			
			
			public static String ordering = "uml::ActivityParameterNode::properties::ordering";
			
			
			public static String isControlType = "uml::ActivityParameterNode::properties::isControlType";
			
			
			public static String inState = "uml::ActivityParameterNode::properties::inState";
			
			
			public static String selection = "uml::ActivityParameterNode::properties::selection";
			
			
			public static String parameter = "uml::ActivityParameterNode::properties::parameter";
			
	
		}
	
	}

	/**
	 * ValuePin view descriptor
	 * 
	 */
	public static class ValuePin {
		public static class Properties {
	
			
			public static String name = "uml::ValuePin::properties::name";
			
			
			public static String visibility = "uml::ValuePin::properties::visibility";
			
			
			public static String clientDependency = "uml::ValuePin::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ValuePin::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ValuePin::properties::inStructuredNode";
			
			
			public static String activity = "uml::ValuePin::properties::activity";
			
			
			public static String outgoing = "uml::ValuePin::properties::outgoing";
			
			
			public static String incoming = "uml::ValuePin::properties::incoming";
			
			
			public static String inPartition = "uml::ValuePin::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ValuePin::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ValuePin::properties::redefinedNode";
			
			
			public static String type = "uml::ValuePin::properties::type";
			
			
			public static String ordering = "uml::ValuePin::properties::ordering";
			
			
			public static String isControlType = "uml::ValuePin::properties::isControlType";
			
			
			public static String inState = "uml::ValuePin::properties::inState";
			
			
			public static String selection = "uml::ValuePin::properties::selection";
			
			
			public static String isOrdered = "uml::ValuePin::properties::isOrdered";
			
			
			public static String isUnique = "uml::ValuePin::properties::isUnique";
			
			
			public static String isControl = "uml::ValuePin::properties::isControl";
			
	
		}
	
	}

	/**
	 * Message view descriptor
	 * 
	 */
	public static class Message {
		public static class Properties {
	
			
			public static String name = "uml::Message::properties::name";
			
			
			public static String visibility = "uml::Message::properties::visibility";
			
			
			public static String clientDependency = "uml::Message::properties::clientDependency";
			
			
			public static String messageSort = "uml::Message::properties::messageSort";
			
			
			public static String receiveEvent = "uml::Message::properties::receiveEvent";
			
			
			public static String sendEvent = "uml::Message::properties::sendEvent";
			
			
			public static String connector = "uml::Message::properties::connector";
			
			
			public static String interaction = "uml::Message::properties::interaction";
			
	
		}
	
	}

	/**
	 * Interaction view descriptor
	 * 
	 */
	public static class Interaction {
		public static class Properties {
	
			
			public static String name = "uml::Interaction::properties::name";
			
			
			public static String visibility = "uml::Interaction::properties::visibility";
			
			
			public static String clientDependency = "uml::Interaction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Interaction::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Interaction::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Interaction::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Interaction::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Interaction::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Interaction::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Interaction::properties::representation";
			
			
			public static String useCase = "uml::Interaction::properties::useCase";
			
			
			public static String classifierBehavior = "uml::Interaction::properties::classifierBehavior";
			
			
			public static String isActive = "uml::Interaction::properties::isActive";
			
			
			public static String isReentrant = "uml::Interaction::properties::isReentrant";
			
			
			public static String redefinedBehavior = "uml::Interaction::properties::redefinedBehavior";
			
			
			public static String precondition = "uml::Interaction::properties::precondition";
			
			
			public static String postcondition = "uml::Interaction::properties::postcondition";
			
			
			public static String specification = "uml::Interaction::properties::specification";
			
			
			public static String covered = "uml::Interaction::properties::covered";
			
			
			public static String enclosingInteraction = "uml::Interaction::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::Interaction::properties::enclosingOperand";
			
	
		}
	
	}

	/**
	 * Lifeline view descriptor
	 * 
	 */
	public static class Lifeline {
		public static class Properties {
	
			
			public static String name = "uml::Lifeline::properties::name";
			
			
			public static String visibility = "uml::Lifeline::properties::visibility";
			
			
			public static String clientDependency = "uml::Lifeline::properties::clientDependency";
			
			
			public static String represents = "uml::Lifeline::properties::represents";
			
			
			public static String interaction = "uml::Lifeline::properties::interaction";
			
			
			public static String decomposedAs = "uml::Lifeline::properties::decomposedAs";
			
			
			public static String coveredBy = "uml::Lifeline::properties::coveredBy";
			
	
		}
	
	}

	/**
	 * PartDecomposition view descriptor
	 * 
	 */
	public static class PartDecomposition {
		public static class Properties {
	
			
			public static String name = "uml::PartDecomposition::properties::name";
			
			
			public static String visibility = "uml::PartDecomposition::properties::visibility";
			
			
			public static String clientDependency = "uml::PartDecomposition::properties::clientDependency";
			
			
			public static String covered = "uml::PartDecomposition::properties::covered";
			
			
			public static String enclosingInteraction = "uml::PartDecomposition::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::PartDecomposition::properties::enclosingOperand";
			
			
			public static String refersTo = "uml::PartDecomposition::properties::refersTo";
			
	
		}
	
	}

	/**
	 * InteractionUse view descriptor
	 * 
	 */
	public static class InteractionUse {
		public static class Properties {
	
			
			public static String name = "uml::InteractionUse::properties::name";
			
			
			public static String visibility = "uml::InteractionUse::properties::visibility";
			
			
			public static String clientDependency = "uml::InteractionUse::properties::clientDependency";
			
			
			public static String covered = "uml::InteractionUse::properties::covered";
			
			
			public static String enclosingInteraction = "uml::InteractionUse::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::InteractionUse::properties::enclosingOperand";
			
			
			public static String refersTo = "uml::InteractionUse::properties::refersTo";
			
	
		}
	
	}

	/**
	 * Gate view descriptor
	 * 
	 */
	public static class Gate {
		public static class Properties {
	
			
			public static String name = "uml::Gate::properties::name";
			
			
			public static String visibility = "uml::Gate::properties::visibility";
			
			
			public static String clientDependency = "uml::Gate::properties::clientDependency";
			
			
			public static String message = "uml::Gate::properties::message";
			
	
		}
	
	}

	/**
	 * GeneralOrdering view descriptor
	 * 
	 */
	public static class GeneralOrdering {
		public static class Properties {
	
			
			public static String name = "uml::GeneralOrdering::properties::name";
			
			
			public static String visibility = "uml::GeneralOrdering::properties::visibility";
			
			
			public static String clientDependency = "uml::GeneralOrdering::properties::clientDependency";
			
			
			public static String before = "uml::GeneralOrdering::properties::before";
			
			
			public static String after = "uml::GeneralOrdering::properties::after";
			
	
		}
	
	}

	/**
	 * OccurrenceSpecification view descriptor
	 * 
	 */
	public static class OccurrenceSpecification {
		public static class Properties {
	
			
			public static String name = "uml::OccurrenceSpecification::properties::name";
			
			
			public static String visibility = "uml::OccurrenceSpecification::properties::visibility";
			
			
			public static String clientDependency = "uml::OccurrenceSpecification::properties::clientDependency";
			
			
			public static String covered = "uml::OccurrenceSpecification::properties::covered";
			
			
			public static String enclosingInteraction = "uml::OccurrenceSpecification::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::OccurrenceSpecification::properties::enclosingOperand";
			
			
			public static String toBefore = "uml::OccurrenceSpecification::properties::toBefore";
			
			
			public static String event = "uml::OccurrenceSpecification::properties::event";
			
			
			public static String toAfter = "uml::OccurrenceSpecification::properties::toAfter";
			
	
		}
	
	}

	/**
	 * InteractionOperand view descriptor
	 * 
	 */
	public static class InteractionOperand {
		public static class Properties {
	
			
			public static String name = "uml::InteractionOperand::properties::name";
			
			
			public static String visibility = "uml::InteractionOperand::properties::visibility";
			
			
			public static String clientDependency = "uml::InteractionOperand::properties::clientDependency";
			
			
			public static String covered = "uml::InteractionOperand::properties::covered";
			
			
			public static String enclosingInteraction = "uml::InteractionOperand::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::InteractionOperand::properties::enclosingOperand";
			
	
		}
	
	}

	/**
	 * InteractionConstraint view descriptor
	 * 
	 */
	public static class InteractionConstraint {
		public static class Properties {
	
			
			public static String name = "uml::InteractionConstraint::properties::name";
			
			
			public static String visibility = "uml::InteractionConstraint::properties::visibility";
			
			
			public static String clientDependency = "uml::InteractionConstraint::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::InteractionConstraint::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::InteractionConstraint::properties::templateParameter";
			
			
			public static String constrainedElement = "uml::InteractionConstraint::properties::constrainedElement";
			
			
			public static String context = "uml::InteractionConstraint::properties::context";
			
	
		}
	
	}

	/**
	 * StateInvariant view descriptor
	 * 
	 */
	public static class StateInvariant {
		public static class Properties {
	
			
			public static String name = "uml::StateInvariant::properties::name";
			
			
			public static String visibility = "uml::StateInvariant::properties::visibility";
			
			
			public static String clientDependency = "uml::StateInvariant::properties::clientDependency";
			
			
			public static String covered = "uml::StateInvariant::properties::covered";
			
			
			public static String enclosingInteraction = "uml::StateInvariant::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::StateInvariant::properties::enclosingOperand";
			
	
		}
	
	}

	/**
	 * ActionExecutionSpecification view descriptor
	 * 
	 */
	public static class ActionExecutionSpecification {
		public static class Properties {
	
			
			public static String name = "uml::ActionExecutionSpecification::properties::name";
			
			
			public static String visibility = "uml::ActionExecutionSpecification::properties::visibility";
			
			
			public static String clientDependency = "uml::ActionExecutionSpecification::properties::clientDependency";
			
			
			public static String covered = "uml::ActionExecutionSpecification::properties::covered";
			
			
			public static String enclosingInteraction = "uml::ActionExecutionSpecification::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::ActionExecutionSpecification::properties::enclosingOperand";
			
			
			public static String start = "uml::ActionExecutionSpecification::properties::start";
			
			
			public static String finish = "uml::ActionExecutionSpecification::properties::finish";
			
			
			public static String action = "uml::ActionExecutionSpecification::properties::action";
			
	
		}
	
	}

	/**
	 * BehaviorExecutionSpecification view descriptor
	 * 
	 */
	public static class BehaviorExecutionSpecification {
		public static class Properties {
	
			
			public static String name = "uml::BehaviorExecutionSpecification::properties::name";
			
			
			public static String visibility = "uml::BehaviorExecutionSpecification::properties::visibility";
			
			
			public static String clientDependency = "uml::BehaviorExecutionSpecification::properties::clientDependency";
			
			
			public static String covered = "uml::BehaviorExecutionSpecification::properties::covered";
			
			
			public static String enclosingInteraction = "uml::BehaviorExecutionSpecification::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::BehaviorExecutionSpecification::properties::enclosingOperand";
			
			
			public static String start = "uml::BehaviorExecutionSpecification::properties::start";
			
			
			public static String finish = "uml::BehaviorExecutionSpecification::properties::finish";
			
			
			public static String behavior = "uml::BehaviorExecutionSpecification::properties::behavior";
			
	
		}
	
	}

	/**
	 * ExecutionEvent view descriptor
	 * 
	 */
	public static class ExecutionEvent {
		public static class Properties {
	
			
			public static String name = "uml::ExecutionEvent::properties::name";
			
			
			public static String visibility = "uml::ExecutionEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::ExecutionEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::ExecutionEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::ExecutionEvent::properties::templateParameter";
			
	
		}
	
	}

	/**
	 * CreationEvent view descriptor
	 * 
	 */
	public static class CreationEvent {
		public static class Properties {
	
			
			public static String name = "uml::CreationEvent::properties::name";
			
			
			public static String visibility = "uml::CreationEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::CreationEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::CreationEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::CreationEvent::properties::templateParameter";
			
	
		}
	
	}

	/**
	 * DestructionEvent view descriptor
	 * 
	 */
	public static class DestructionEvent {
		public static class Properties {
	
			
			public static String name = "uml::DestructionEvent::properties::name";
			
			
			public static String visibility = "uml::DestructionEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::DestructionEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::DestructionEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::DestructionEvent::properties::templateParameter";
			
	
		}
	
	}

	/**
	 * SendOperationEvent view descriptor
	 * 
	 */
	public static class SendOperationEvent {
		public static class Properties {
	
			
			public static String name = "uml::SendOperationEvent::properties::name";
			
			
			public static String visibility = "uml::SendOperationEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::SendOperationEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::SendOperationEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::SendOperationEvent::properties::templateParameter";
			
			
			public static String operation = "uml::SendOperationEvent::properties::operation";
			
	
		}
	
	}

	/**
	 * SendSignalEvent view descriptor
	 * 
	 */
	public static class SendSignalEvent {
		public static class Properties {
	
			
			public static String name = "uml::SendSignalEvent::properties::name";
			
			
			public static String visibility = "uml::SendSignalEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::SendSignalEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::SendSignalEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::SendSignalEvent::properties::templateParameter";
			
			
			public static String signal = "uml::SendSignalEvent::properties::signal";
			
	
		}
	
	}

	/**
	 * MessageOccurrenceSpecification view descriptor
	 * 
	 */
	public static class MessageOccurrenceSpecification {
		public static class Properties {
	
			
			public static String name = "uml::MessageOccurrenceSpecification::properties::name";
			
			
			public static String visibility = "uml::MessageOccurrenceSpecification::properties::visibility";
			
			
			public static String clientDependency = "uml::MessageOccurrenceSpecification::properties::clientDependency";
			
			
			public static String covered = "uml::MessageOccurrenceSpecification::properties::covered";
			
			
			public static String enclosingInteraction = "uml::MessageOccurrenceSpecification::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::MessageOccurrenceSpecification::properties::enclosingOperand";
			
			
			public static String toBefore = "uml::MessageOccurrenceSpecification::properties::toBefore";
			
			
			public static String event = "uml::MessageOccurrenceSpecification::properties::event";
			
			
			public static String toAfter = "uml::MessageOccurrenceSpecification::properties::toAfter";
			
			
			public static String message = "uml::MessageOccurrenceSpecification::properties::message";
			
	
		}
	
	}

	/**
	 * ExecutionOccurrenceSpecification view descriptor
	 * 
	 */
	public static class ExecutionOccurrenceSpecification {
		public static class Properties {
	
			
			public static String name = "uml::ExecutionOccurrenceSpecification::properties::name";
			
			
			public static String visibility = "uml::ExecutionOccurrenceSpecification::properties::visibility";
			
			
			public static String clientDependency = "uml::ExecutionOccurrenceSpecification::properties::clientDependency";
			
			
			public static String covered = "uml::ExecutionOccurrenceSpecification::properties::covered";
			
			
			public static String enclosingInteraction = "uml::ExecutionOccurrenceSpecification::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::ExecutionOccurrenceSpecification::properties::enclosingOperand";
			
			
			public static String toBefore = "uml::ExecutionOccurrenceSpecification::properties::toBefore";
			
			
			public static String event = "uml::ExecutionOccurrenceSpecification::properties::event";
			
			
			public static String toAfter = "uml::ExecutionOccurrenceSpecification::properties::toAfter";
			
			
			public static String execution = "uml::ExecutionOccurrenceSpecification::properties::execution";
			
	
		}
	
	}

	/**
	 * ReceiveOperationEvent view descriptor
	 * 
	 */
	public static class ReceiveOperationEvent {
		public static class Properties {
	
			
			public static String name = "uml::ReceiveOperationEvent::properties::name";
			
			
			public static String visibility = "uml::ReceiveOperationEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::ReceiveOperationEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::ReceiveOperationEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::ReceiveOperationEvent::properties::templateParameter";
			
			
			public static String operation = "uml::ReceiveOperationEvent::properties::operation";
			
	
		}
	
	}

	/**
	 * ReceiveSignalEvent view descriptor
	 * 
	 */
	public static class ReceiveSignalEvent {
		public static class Properties {
	
			
			public static String name = "uml::ReceiveSignalEvent::properties::name";
			
			
			public static String visibility = "uml::ReceiveSignalEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::ReceiveSignalEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::ReceiveSignalEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::ReceiveSignalEvent::properties::templateParameter";
			
			
			public static String signal = "uml::ReceiveSignalEvent::properties::signal";
			
	
		}
	
	}

	/**
	 * Actor view descriptor
	 * 
	 */
	public static class Actor {
		public static class Properties {
	
			
			public static String name = "uml::Actor::properties::name";
			
			
			public static String visibility = "uml::Actor::properties::visibility";
			
			
			public static String clientDependency = "uml::Actor::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Actor::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Actor::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Actor::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Actor::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Actor::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Actor::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Actor::properties::representation";
			
			
			public static String useCase = "uml::Actor::properties::useCase";
			
			
			public static String classifierBehavior = "uml::Actor::properties::classifierBehavior";
			
	
		}
	
	}

	/**
	 * CallEvent view descriptor
	 * 
	 */
	public static class CallEvent {
		public static class Properties {
	
			
			public static String name = "uml::CallEvent::properties::name";
			
			
			public static String visibility = "uml::CallEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::CallEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::CallEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::CallEvent::properties::templateParameter";
			
			
			public static String operation = "uml::CallEvent::properties::operation";
			
	
		}
	
	}

	/**
	 * ChangeEvent view descriptor
	 * 
	 */
	public static class ChangeEvent {
		public static class Properties {
	
			
			public static String name = "uml::ChangeEvent::properties::name";
			
			
			public static String visibility = "uml::ChangeEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::ChangeEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::ChangeEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::ChangeEvent::properties::templateParameter";
			
	
		}
	
	}

	/**
	 * SignalEvent view descriptor
	 * 
	 */
	public static class SignalEvent {
		public static class Properties {
	
			
			public static String name = "uml::SignalEvent::properties::name";
			
			
			public static String visibility = "uml::SignalEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::SignalEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::SignalEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::SignalEvent::properties::templateParameter";
			
			
			public static String signal = "uml::SignalEvent::properties::signal";
			
	
		}
	
	}

	/**
	 * AnyReceiveEvent view descriptor
	 * 
	 */
	public static class AnyReceiveEvent {
		public static class Properties {
	
			
			public static String name = "uml::AnyReceiveEvent::properties::name";
			
			
			public static String visibility = "uml::AnyReceiveEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::AnyReceiveEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::AnyReceiveEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::AnyReceiveEvent::properties::templateParameter";
			
	
		}
	
	}

	/**
	 * ForkNode view descriptor
	 * 
	 */
	public static class ForkNode {
		public static class Properties {
	
			
			public static String name = "uml::ForkNode::properties::name";
			
			
			public static String visibility = "uml::ForkNode::properties::visibility";
			
			
			public static String clientDependency = "uml::ForkNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ForkNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ForkNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::ForkNode::properties::activity";
			
			
			public static String outgoing = "uml::ForkNode::properties::outgoing";
			
			
			public static String incoming = "uml::ForkNode::properties::incoming";
			
			
			public static String inPartition = "uml::ForkNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ForkNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ForkNode::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * FlowFinalNode view descriptor
	 * 
	 */
	public static class FlowFinalNode {
		public static class Properties {
	
			
			public static String name = "uml::FlowFinalNode::properties::name";
			
			
			public static String visibility = "uml::FlowFinalNode::properties::visibility";
			
			
			public static String clientDependency = "uml::FlowFinalNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::FlowFinalNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::FlowFinalNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::FlowFinalNode::properties::activity";
			
			
			public static String outgoing = "uml::FlowFinalNode::properties::outgoing";
			
			
			public static String incoming = "uml::FlowFinalNode::properties::incoming";
			
			
			public static String inPartition = "uml::FlowFinalNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::FlowFinalNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::FlowFinalNode::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * CentralBufferNode view descriptor
	 * 
	 */
	public static class CentralBufferNode {
		public static class Properties {
	
			
			public static String name = "uml::CentralBufferNode::properties::name";
			
			
			public static String visibility = "uml::CentralBufferNode::properties::visibility";
			
			
			public static String clientDependency = "uml::CentralBufferNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::CentralBufferNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::CentralBufferNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::CentralBufferNode::properties::activity";
			
			
			public static String outgoing = "uml::CentralBufferNode::properties::outgoing";
			
			
			public static String incoming = "uml::CentralBufferNode::properties::incoming";
			
			
			public static String inPartition = "uml::CentralBufferNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::CentralBufferNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::CentralBufferNode::properties::redefinedNode";
			
			
			public static String type = "uml::CentralBufferNode::properties::type";
			
			
			public static String ordering = "uml::CentralBufferNode::properties::ordering";
			
			
			public static String isControlType = "uml::CentralBufferNode::properties::isControlType";
			
			
			public static String inState = "uml::CentralBufferNode::properties::inState";
			
			
			public static String selection = "uml::CentralBufferNode::properties::selection";
			
	
		}
	
	}

	/**
	 * MergeNode view descriptor
	 * 
	 */
	public static class MergeNode {
		public static class Properties {
	
			
			public static String name = "uml::MergeNode::properties::name";
			
			
			public static String visibility = "uml::MergeNode::properties::visibility";
			
			
			public static String clientDependency = "uml::MergeNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::MergeNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::MergeNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::MergeNode::properties::activity";
			
			
			public static String outgoing = "uml::MergeNode::properties::outgoing";
			
			
			public static String incoming = "uml::MergeNode::properties::incoming";
			
			
			public static String inPartition = "uml::MergeNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::MergeNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::MergeNode::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * DecisionNode view descriptor
	 * 
	 */
	public static class DecisionNode {
		public static class Properties {
	
			
			public static String name = "uml::DecisionNode::properties::name";
			
			
			public static String visibility = "uml::DecisionNode::properties::visibility";
			
			
			public static String clientDependency = "uml::DecisionNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::DecisionNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::DecisionNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::DecisionNode::properties::activity";
			
			
			public static String outgoing = "uml::DecisionNode::properties::outgoing";
			
			
			public static String incoming = "uml::DecisionNode::properties::incoming";
			
			
			public static String inPartition = "uml::DecisionNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::DecisionNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::DecisionNode::properties::redefinedNode";
			
			
			public static String decisionInput = "uml::DecisionNode::properties::decisionInput";
			
			
			public static String decisionInputFlow = "uml::DecisionNode::properties::decisionInputFlow";
			
	
		}
	
	}

	/**
	 * ObjectFlow view descriptor
	 * 
	 */
	public static class ObjectFlow {
		public static class Properties {
	
			
			public static String name = "uml::ObjectFlow::properties::name";
			
			
			public static String visibility = "uml::ObjectFlow::properties::visibility";
			
			
			public static String clientDependency = "uml::ObjectFlow::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ObjectFlow::properties::isLeaf";
			
			
			public static String source = "uml::ObjectFlow::properties::source";
			
			
			public static String target = "uml::ObjectFlow::properties::target";
			
			
			public static String redefinedEdge = "uml::ObjectFlow::properties::redefinedEdge";
			
			
			public static String inPartition = "uml::ObjectFlow::properties::inPartition";
			
			
			public static String interrupts = "uml::ObjectFlow::properties::interrupts";
			
			
			public static String inStructuredNode = "uml::ObjectFlow::properties::inStructuredNode";
			
			
			public static String activity = "uml::ObjectFlow::properties::activity";
			
			
			public static String isMulticast = "uml::ObjectFlow::properties::isMulticast";
			
			
			public static String isMultireceive = "uml::ObjectFlow::properties::isMultireceive";
			
			
			public static String transformation = "uml::ObjectFlow::properties::transformation";
			
			
			public static String selection = "uml::ObjectFlow::properties::selection";
			
	
		}
	
	}

	/**
	 * ActivityFinalNode view descriptor
	 * 
	 */
	public static class ActivityFinalNode {
		public static class Properties {
	
			
			public static String name = "uml::ActivityFinalNode::properties::name";
			
			
			public static String visibility = "uml::ActivityFinalNode::properties::visibility";
			
			
			public static String clientDependency = "uml::ActivityFinalNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ActivityFinalNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ActivityFinalNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::ActivityFinalNode::properties::activity";
			
			
			public static String outgoing = "uml::ActivityFinalNode::properties::outgoing";
			
			
			public static String incoming = "uml::ActivityFinalNode::properties::incoming";
			
			
			public static String inPartition = "uml::ActivityFinalNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ActivityFinalNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ActivityFinalNode::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * ComponentRealization view descriptor
	 * 
	 */
	public static class ComponentRealization {
		public static class Properties {
	
			
			public static String name = "uml::ComponentRealization::properties::name";
			
			
			public static String visibility = "uml::ComponentRealization::properties::visibility";
			
			
			public static String clientDependency = "uml::ComponentRealization::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::ComponentRealization::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::ComponentRealization::properties::templateParameter";
			
			
			public static String supplier = "uml::ComponentRealization::properties::supplier";
			
			
			public static String client = "uml::ComponentRealization::properties::client";
			
			
			public static String abstraction = "uml::ComponentRealization::properties::abstraction";
			
			
			public static String realizingClassifier = "uml::ComponentRealization::properties::realizingClassifier";
			
	
		}
	
	}

	/**
	 * Component view descriptor
	 * 
	 */
	public static class Component {
		public static class Properties {
	
			
			public static String name = "uml::Component::properties::name";
			
			
			public static String visibility = "uml::Component::properties::visibility";
			
			
			public static String clientDependency = "uml::Component::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Component::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Component::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Component::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Component::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Component::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Component::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Component::properties::representation";
			
			
			public static String useCase = "uml::Component::properties::useCase";
			
			
			public static String classifierBehavior = "uml::Component::properties::classifierBehavior";
			
			
			public static String isActive = "uml::Component::properties::isActive";
			
			
			public static String isIndirectlyInstantiated = "uml::Component::properties::isIndirectlyInstantiated";
			
	
		}
	
	}

	/**
	 * Node view descriptor
	 * 
	 */
	public static class Node {
		public static class Properties {
	
			
			public static String name = "uml::Node::properties::name";
			
			
			public static String visibility = "uml::Node::properties::visibility";
			
			
			public static String clientDependency = "uml::Node::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Node::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Node::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Node::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Node::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Node::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Node::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Node::properties::representation";
			
			
			public static String useCase = "uml::Node::properties::useCase";
			
			
			public static String classifierBehavior = "uml::Node::properties::classifierBehavior";
			
			
			public static String isActive = "uml::Node::properties::isActive";
			
	
		}
	
	}

	/**
	 * CommunicationPath view descriptor
	 * 
	 */
	public static class CommunicationPath {
		public static class Properties {
	
			
			public static String name = "uml::CommunicationPath::properties::name";
			
			
			public static String visibility = "uml::CommunicationPath::properties::visibility";
			
			
			public static String clientDependency = "uml::CommunicationPath::properties::clientDependency";
			
			
			public static String isLeaf = "uml::CommunicationPath::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::CommunicationPath::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::CommunicationPath::properties::templateParameter";
			
			
			public static String isAbstract = "uml::CommunicationPath::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::CommunicationPath::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::CommunicationPath::properties::redefinedClassifier";
			
			
			public static String representation = "uml::CommunicationPath::properties::representation";
			
			
			public static String useCase = "uml::CommunicationPath::properties::useCase";
			
			
			public static String memberEnd = "uml::CommunicationPath::properties::memberEnd";
			
			
			public static String isDerived = "uml::CommunicationPath::properties::isDerived";
			
			
			public static String navigableOwnedEnd = "uml::CommunicationPath::properties::navigableOwnedEnd";
			
	
		}
	
	}

	/**
	 * Device view descriptor
	 * 
	 */
	public static class Device {
		public static class Properties {
	
			
			public static String name = "uml::Device::properties::name";
			
			
			public static String visibility = "uml::Device::properties::visibility";
			
			
			public static String clientDependency = "uml::Device::properties::clientDependency";
			
			
			public static String isLeaf = "uml::Device::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::Device::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Device::properties::templateParameter";
			
			
			public static String isAbstract = "uml::Device::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::Device::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::Device::properties::redefinedClassifier";
			
			
			public static String representation = "uml::Device::properties::representation";
			
			
			public static String useCase = "uml::Device::properties::useCase";
			
			
			public static String classifierBehavior = "uml::Device::properties::classifierBehavior";
			
			
			public static String isActive = "uml::Device::properties::isActive";
			
	
		}
	
	}

	/**
	 * ExecutionEnvironment view descriptor
	 * 
	 */
	public static class ExecutionEnvironment {
		public static class Properties {
	
			
			public static String name = "uml::ExecutionEnvironment::properties::name";
			
			
			public static String visibility = "uml::ExecutionEnvironment::properties::visibility";
			
			
			public static String clientDependency = "uml::ExecutionEnvironment::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ExecutionEnvironment::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::ExecutionEnvironment::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::ExecutionEnvironment::properties::templateParameter";
			
			
			public static String isAbstract = "uml::ExecutionEnvironment::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::ExecutionEnvironment::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::ExecutionEnvironment::properties::redefinedClassifier";
			
			
			public static String representation = "uml::ExecutionEnvironment::properties::representation";
			
			
			public static String useCase = "uml::ExecutionEnvironment::properties::useCase";
			
			
			public static String classifierBehavior = "uml::ExecutionEnvironment::properties::classifierBehavior";
			
			
			public static String isActive = "uml::ExecutionEnvironment::properties::isActive";
			
	
		}
	
	}

	/**
	 * CombinedFragment view descriptor
	 * 
	 */
	public static class CombinedFragment {
		public static class Properties {
	
			
			public static String name = "uml::CombinedFragment::properties::name";
			
			
			public static String visibility = "uml::CombinedFragment::properties::visibility";
			
			
			public static String clientDependency = "uml::CombinedFragment::properties::clientDependency";
			
			
			public static String covered = "uml::CombinedFragment::properties::covered";
			
			
			public static String enclosingInteraction = "uml::CombinedFragment::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::CombinedFragment::properties::enclosingOperand";
			
			
			public static String interactionOperator = "uml::CombinedFragment::properties::interactionOperator";
			
	
		}
	
	}

	/**
	 * Continuation view descriptor
	 * 
	 */
	public static class Continuation {
		public static class Properties {
	
			
			public static String name = "uml::Continuation::properties::name";
			
			
			public static String visibility = "uml::Continuation::properties::visibility";
			
			
			public static String clientDependency = "uml::Continuation::properties::clientDependency";
			
			
			public static String covered = "uml::Continuation::properties::covered";
			
			
			public static String enclosingInteraction = "uml::Continuation::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::Continuation::properties::enclosingOperand";
			
			
			public static String setting = "uml::Continuation::properties::setting";
			
	
		}
	
	}

	/**
	 * ConsiderIgnoreFragment view descriptor
	 * 
	 */
	public static class ConsiderIgnoreFragment {
		public static class Properties {
	
			
			public static String name = "uml::ConsiderIgnoreFragment::properties::name";
			
			
			public static String visibility = "uml::ConsiderIgnoreFragment::properties::visibility";
			
			
			public static String clientDependency = "uml::ConsiderIgnoreFragment::properties::clientDependency";
			
			
			public static String covered = "uml::ConsiderIgnoreFragment::properties::covered";
			
			
			public static String enclosingInteraction = "uml::ConsiderIgnoreFragment::properties::enclosingInteraction";
			
			
			public static String enclosingOperand = "uml::ConsiderIgnoreFragment::properties::enclosingOperand";
			
			
			public static String interactionOperator = "uml::ConsiderIgnoreFragment::properties::interactionOperator";
			
			
			public static String message = "uml::ConsiderIgnoreFragment::properties::message";
			
	
		}
	
	}

	/**
	 * CreateObjectAction view descriptor
	 * 
	 */
	public static class CreateObjectAction {
		public static class Properties {
	
			
			public static String name = "uml::CreateObjectAction::properties::name";
			
			
			public static String visibility = "uml::CreateObjectAction::properties::visibility";
			
			
			public static String clientDependency = "uml::CreateObjectAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::CreateObjectAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::CreateObjectAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::CreateObjectAction::properties::activity";
			
			
			public static String outgoing = "uml::CreateObjectAction::properties::outgoing";
			
			
			public static String incoming = "uml::CreateObjectAction::properties::incoming";
			
			
			public static String inPartition = "uml::CreateObjectAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::CreateObjectAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::CreateObjectAction::properties::redefinedNode";
			
			
			public static String classifier = "uml::CreateObjectAction::properties::classifier";
			
	
		}
	
	}

	/**
	 * DestroyObjectAction view descriptor
	 * 
	 */
	public static class DestroyObjectAction {
		public static class Properties {
	
			
			public static String name = "uml::DestroyObjectAction::properties::name";
			
			
			public static String visibility = "uml::DestroyObjectAction::properties::visibility";
			
			
			public static String clientDependency = "uml::DestroyObjectAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::DestroyObjectAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::DestroyObjectAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::DestroyObjectAction::properties::activity";
			
			
			public static String outgoing = "uml::DestroyObjectAction::properties::outgoing";
			
			
			public static String incoming = "uml::DestroyObjectAction::properties::incoming";
			
			
			public static String inPartition = "uml::DestroyObjectAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::DestroyObjectAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::DestroyObjectAction::properties::redefinedNode";
			
			
			public static String isDestroyLinks = "uml::DestroyObjectAction::properties::isDestroyLinks";
			
			
			public static String isDestroyOwnedObjects = "uml::DestroyObjectAction::properties::isDestroyOwnedObjects";
			
	
		}
	
	}

	/**
	 * TestIdentityAction view descriptor
	 * 
	 */
	public static class TestIdentityAction {
		public static class Properties {
	
			
			public static String name = "uml::TestIdentityAction::properties::name";
			
			
			public static String visibility = "uml::TestIdentityAction::properties::visibility";
			
			
			public static String clientDependency = "uml::TestIdentityAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::TestIdentityAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::TestIdentityAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::TestIdentityAction::properties::activity";
			
			
			public static String outgoing = "uml::TestIdentityAction::properties::outgoing";
			
			
			public static String incoming = "uml::TestIdentityAction::properties::incoming";
			
			
			public static String inPartition = "uml::TestIdentityAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::TestIdentityAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::TestIdentityAction::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * ReadSelfAction view descriptor
	 * 
	 */
	public static class ReadSelfAction {
		public static class Properties {
	
			
			public static String name = "uml::ReadSelfAction::properties::name";
			
			
			public static String visibility = "uml::ReadSelfAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ReadSelfAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ReadSelfAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ReadSelfAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ReadSelfAction::properties::activity";
			
			
			public static String outgoing = "uml::ReadSelfAction::properties::outgoing";
			
			
			public static String incoming = "uml::ReadSelfAction::properties::incoming";
			
			
			public static String inPartition = "uml::ReadSelfAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ReadSelfAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ReadSelfAction::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * ReadStructuralFeatureAction view descriptor
	 * 
	 */
	public static class ReadStructuralFeatureAction {
		public static class Properties {
	
			
			public static String name = "uml::ReadStructuralFeatureAction::properties::name";
			
			
			public static String visibility = "uml::ReadStructuralFeatureAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ReadStructuralFeatureAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ReadStructuralFeatureAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ReadStructuralFeatureAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ReadStructuralFeatureAction::properties::activity";
			
			
			public static String outgoing = "uml::ReadStructuralFeatureAction::properties::outgoing";
			
			
			public static String incoming = "uml::ReadStructuralFeatureAction::properties::incoming";
			
			
			public static String inPartition = "uml::ReadStructuralFeatureAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ReadStructuralFeatureAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ReadStructuralFeatureAction::properties::redefinedNode";
			
			
			public static String structuralFeature = "uml::ReadStructuralFeatureAction::properties::structuralFeature";
			
	
		}
	
	}

	/**
	 * ClearStructuralFeatureAction view descriptor
	 * 
	 */
	public static class ClearStructuralFeatureAction {
		public static class Properties {
	
			
			public static String name = "uml::ClearStructuralFeatureAction::properties::name";
			
			
			public static String visibility = "uml::ClearStructuralFeatureAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ClearStructuralFeatureAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ClearStructuralFeatureAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ClearStructuralFeatureAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ClearStructuralFeatureAction::properties::activity";
			
			
			public static String outgoing = "uml::ClearStructuralFeatureAction::properties::outgoing";
			
			
			public static String incoming = "uml::ClearStructuralFeatureAction::properties::incoming";
			
			
			public static String inPartition = "uml::ClearStructuralFeatureAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ClearStructuralFeatureAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ClearStructuralFeatureAction::properties::redefinedNode";
			
			
			public static String structuralFeature = "uml::ClearStructuralFeatureAction::properties::structuralFeature";
			
	
		}
	
	}

	/**
	 * RemoveStructuralFeatureValueAction view descriptor
	 * 
	 */
	public static class RemoveStructuralFeatureValueAction {
		public static class Properties {
	
			
			public static String name = "uml::RemoveStructuralFeatureValueAction::properties::name";
			
			
			public static String visibility = "uml::RemoveStructuralFeatureValueAction::properties::visibility";
			
			
			public static String clientDependency = "uml::RemoveStructuralFeatureValueAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::RemoveStructuralFeatureValueAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::RemoveStructuralFeatureValueAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::RemoveStructuralFeatureValueAction::properties::activity";
			
			
			public static String outgoing = "uml::RemoveStructuralFeatureValueAction::properties::outgoing";
			
			
			public static String incoming = "uml::RemoveStructuralFeatureValueAction::properties::incoming";
			
			
			public static String inPartition = "uml::RemoveStructuralFeatureValueAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::RemoveStructuralFeatureValueAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::RemoveStructuralFeatureValueAction::properties::redefinedNode";
			
			
			public static String structuralFeature = "uml::RemoveStructuralFeatureValueAction::properties::structuralFeature";
			
			
			public static String isRemoveDuplicates = "uml::RemoveStructuralFeatureValueAction::properties::isRemoveDuplicates";
			
	
		}
	
	}

	/**
	 * AddStructuralFeatureValueAction view descriptor
	 * 
	 */
	public static class AddStructuralFeatureValueAction {
		public static class Properties {
	
			
			public static String name = "uml::AddStructuralFeatureValueAction::properties::name";
			
			
			public static String visibility = "uml::AddStructuralFeatureValueAction::properties::visibility";
			
			
			public static String clientDependency = "uml::AddStructuralFeatureValueAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::AddStructuralFeatureValueAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::AddStructuralFeatureValueAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::AddStructuralFeatureValueAction::properties::activity";
			
			
			public static String outgoing = "uml::AddStructuralFeatureValueAction::properties::outgoing";
			
			
			public static String incoming = "uml::AddStructuralFeatureValueAction::properties::incoming";
			
			
			public static String inPartition = "uml::AddStructuralFeatureValueAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::AddStructuralFeatureValueAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::AddStructuralFeatureValueAction::properties::redefinedNode";
			
			
			public static String structuralFeature = "uml::AddStructuralFeatureValueAction::properties::structuralFeature";
			
			
			public static String isReplaceAll = "uml::AddStructuralFeatureValueAction::properties::isReplaceAll";
			
	
		}
	
	}

	/**
	 * LinkEndData view descriptor
	 * 
	 */
	public static class LinkEndData {
		public static class Properties {
	
			
			public static String value = "uml::LinkEndData::properties::value";
			
			
			public static String end = "uml::LinkEndData::properties::end";
			
	
		}
	
	}

	/**
	 * QualifierValue view descriptor
	 * 
	 */
	public static class QualifierValue {
		public static class Properties {
	
			
			public static String qualifier = "uml::QualifierValue::properties::qualifier";
			
			
			public static String value = "uml::QualifierValue::properties::value";
			
	
		}
	
	}

	/**
	 * ReadLinkAction view descriptor
	 * 
	 */
	public static class ReadLinkAction {
		public static class Properties {
	
			
			public static String name = "uml::ReadLinkAction::properties::name";
			
			
			public static String visibility = "uml::ReadLinkAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ReadLinkAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ReadLinkAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ReadLinkAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ReadLinkAction::properties::activity";
			
			
			public static String outgoing = "uml::ReadLinkAction::properties::outgoing";
			
			
			public static String incoming = "uml::ReadLinkAction::properties::incoming";
			
			
			public static String inPartition = "uml::ReadLinkAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ReadLinkAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ReadLinkAction::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * LinkEndCreationData view descriptor
	 * 
	 */
	public static class LinkEndCreationData {
		public static class Properties {
	
			
			public static String value = "uml::LinkEndCreationData::properties::value";
			
			
			public static String end = "uml::LinkEndCreationData::properties::end";
			
			
			public static String isReplaceAll = "uml::LinkEndCreationData::properties::isReplaceAll";
			
			
			public static String insertAt = "uml::LinkEndCreationData::properties::insertAt";
			
	
		}
	
	}

	/**
	 * CreateLinkAction view descriptor
	 * 
	 */
	public static class CreateLinkAction {
		public static class Properties {
	
			
			public static String name = "uml::CreateLinkAction::properties::name";
			
			
			public static String visibility = "uml::CreateLinkAction::properties::visibility";
			
			
			public static String clientDependency = "uml::CreateLinkAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::CreateLinkAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::CreateLinkAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::CreateLinkAction::properties::activity";
			
			
			public static String outgoing = "uml::CreateLinkAction::properties::outgoing";
			
			
			public static String incoming = "uml::CreateLinkAction::properties::incoming";
			
			
			public static String inPartition = "uml::CreateLinkAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::CreateLinkAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::CreateLinkAction::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * DestroyLinkAction view descriptor
	 * 
	 */
	public static class DestroyLinkAction {
		public static class Properties {
	
			
			public static String name = "uml::DestroyLinkAction::properties::name";
			
			
			public static String visibility = "uml::DestroyLinkAction::properties::visibility";
			
			
			public static String clientDependency = "uml::DestroyLinkAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::DestroyLinkAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::DestroyLinkAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::DestroyLinkAction::properties::activity";
			
			
			public static String outgoing = "uml::DestroyLinkAction::properties::outgoing";
			
			
			public static String incoming = "uml::DestroyLinkAction::properties::incoming";
			
			
			public static String inPartition = "uml::DestroyLinkAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::DestroyLinkAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::DestroyLinkAction::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * LinkEndDestructionData view descriptor
	 * 
	 */
	public static class LinkEndDestructionData {
		public static class Properties {
	
			
			public static String value = "uml::LinkEndDestructionData::properties::value";
			
			
			public static String end = "uml::LinkEndDestructionData::properties::end";
			
			
			public static String isDestroyDuplicates = "uml::LinkEndDestructionData::properties::isDestroyDuplicates";
			
			
			public static String destroyAt = "uml::LinkEndDestructionData::properties::destroyAt";
			
	
		}
	
	}

	/**
	 * ClearAssociationAction view descriptor
	 * 
	 */
	public static class ClearAssociationAction {
		public static class Properties {
	
			
			public static String name = "uml::ClearAssociationAction::properties::name";
			
			
			public static String visibility = "uml::ClearAssociationAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ClearAssociationAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ClearAssociationAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ClearAssociationAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ClearAssociationAction::properties::activity";
			
			
			public static String outgoing = "uml::ClearAssociationAction::properties::outgoing";
			
			
			public static String incoming = "uml::ClearAssociationAction::properties::incoming";
			
			
			public static String inPartition = "uml::ClearAssociationAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ClearAssociationAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ClearAssociationAction::properties::redefinedNode";
			
			
			public static String association = "uml::ClearAssociationAction::properties::association";
			
	
		}
	
	}

	/**
	 * BroadcastSignalAction view descriptor
	 * 
	 */
	public static class BroadcastSignalAction {
		public static class Properties {
	
			
			public static String name = "uml::BroadcastSignalAction::properties::name";
			
			
			public static String visibility = "uml::BroadcastSignalAction::properties::visibility";
			
			
			public static String clientDependency = "uml::BroadcastSignalAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::BroadcastSignalAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::BroadcastSignalAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::BroadcastSignalAction::properties::activity";
			
			
			public static String outgoing = "uml::BroadcastSignalAction::properties::outgoing";
			
			
			public static String incoming = "uml::BroadcastSignalAction::properties::incoming";
			
			
			public static String inPartition = "uml::BroadcastSignalAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::BroadcastSignalAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::BroadcastSignalAction::properties::redefinedNode";
			
			
			public static String onPort = "uml::BroadcastSignalAction::properties::onPort";
			
			
			public static String signal = "uml::BroadcastSignalAction::properties::signal";
			
	
		}
	
	}

	/**
	 * SendObjectAction view descriptor
	 * 
	 */
	public static class SendObjectAction {
		public static class Properties {
	
			
			public static String name = "uml::SendObjectAction::properties::name";
			
			
			public static String visibility = "uml::SendObjectAction::properties::visibility";
			
			
			public static String clientDependency = "uml::SendObjectAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::SendObjectAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::SendObjectAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::SendObjectAction::properties::activity";
			
			
			public static String outgoing = "uml::SendObjectAction::properties::outgoing";
			
			
			public static String incoming = "uml::SendObjectAction::properties::incoming";
			
			
			public static String inPartition = "uml::SendObjectAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::SendObjectAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::SendObjectAction::properties::redefinedNode";
			
			
			public static String onPort = "uml::SendObjectAction::properties::onPort";
			
	
		}
	
	}

	/**
	 * ValueSpecificationAction view descriptor
	 * 
	 */
	public static class ValueSpecificationAction {
		public static class Properties {
	
			
			public static String name = "uml::ValueSpecificationAction::properties::name";
			
			
			public static String visibility = "uml::ValueSpecificationAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ValueSpecificationAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ValueSpecificationAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ValueSpecificationAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ValueSpecificationAction::properties::activity";
			
			
			public static String outgoing = "uml::ValueSpecificationAction::properties::outgoing";
			
			
			public static String incoming = "uml::ValueSpecificationAction::properties::incoming";
			
			
			public static String inPartition = "uml::ValueSpecificationAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ValueSpecificationAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ValueSpecificationAction::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * TimeExpression view descriptor
	 * 
	 */
	public static class TimeExpression {
		public static class Properties {
	
			
			public static String name = "uml::TimeExpression::properties::name";
			
			
			public static String visibility = "uml::TimeExpression::properties::visibility";
			
			
			public static String clientDependency = "uml::TimeExpression::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::TimeExpression::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::TimeExpression::properties::templateParameter";
			
			
			public static String type = "uml::TimeExpression::properties::type";
			
			
			public static String observation = "uml::TimeExpression::properties::observation";
			
	
		}
	
	}

	/**
	 * Duration view descriptor
	 * 
	 */
	public static class Duration {
		public static class Properties {
	
			
			public static String name = "uml::Duration::properties::name";
			
			
			public static String visibility = "uml::Duration::properties::visibility";
			
			
			public static String clientDependency = "uml::Duration::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Duration::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Duration::properties::templateParameter";
			
			
			public static String type = "uml::Duration::properties::type";
			
			
			public static String observation = "uml::Duration::properties::observation";
			
	
		}
	
	}

	/**
	 * DurationInterval view descriptor
	 * 
	 */
	public static class DurationInterval {
		public static class Properties {
	
			
			public static String name = "uml::DurationInterval::properties::name";
			
			
			public static String visibility = "uml::DurationInterval::properties::visibility";
			
			
			public static String clientDependency = "uml::DurationInterval::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::DurationInterval::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::DurationInterval::properties::templateParameter";
			
			
			public static String type = "uml::DurationInterval::properties::type";
			
			
			public static String min = "uml::DurationInterval::properties::min";
			
			
			public static String max = "uml::DurationInterval::properties::max";
			
	
		}
	
	}

	/**
	 * Interval view descriptor
	 * 
	 */
	public static class Interval {
		public static class Properties {
	
			
			public static String name = "uml::Interval::properties::name";
			
			
			public static String visibility = "uml::Interval::properties::visibility";
			
			
			public static String clientDependency = "uml::Interval::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::Interval::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::Interval::properties::templateParameter";
			
			
			public static String type = "uml::Interval::properties::type";
			
			
			public static String min = "uml::Interval::properties::min";
			
			
			public static String max = "uml::Interval::properties::max";
			
	
		}
	
	}

	/**
	 * TimeConstraint view descriptor
	 * 
	 */
	public static class TimeConstraint {
		public static class Properties {
	
			
			public static String name = "uml::TimeConstraint::properties::name";
			
			
			public static String visibility = "uml::TimeConstraint::properties::visibility";
			
			
			public static String clientDependency = "uml::TimeConstraint::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::TimeConstraint::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::TimeConstraint::properties::templateParameter";
			
			
			public static String constrainedElement = "uml::TimeConstraint::properties::constrainedElement";
			
			
			public static String context = "uml::TimeConstraint::properties::context";
			
			
			public static String firstEvent = "uml::TimeConstraint::properties::firstEvent";
			
	
		}
	
	}

	/**
	 * IntervalConstraint view descriptor
	 * 
	 */
	public static class IntervalConstraint {
		public static class Properties {
	
			
			public static String name = "uml::IntervalConstraint::properties::name";
			
			
			public static String visibility = "uml::IntervalConstraint::properties::visibility";
			
			
			public static String clientDependency = "uml::IntervalConstraint::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::IntervalConstraint::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::IntervalConstraint::properties::templateParameter";
			
			
			public static String constrainedElement = "uml::IntervalConstraint::properties::constrainedElement";
			
			
			public static String context = "uml::IntervalConstraint::properties::context";
			
	
		}
	
	}

	/**
	 * TimeInterval view descriptor
	 * 
	 */
	public static class TimeInterval {
		public static class Properties {
	
			
			public static String name = "uml::TimeInterval::properties::name";
			
			
			public static String visibility = "uml::TimeInterval::properties::visibility";
			
			
			public static String clientDependency = "uml::TimeInterval::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::TimeInterval::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::TimeInterval::properties::templateParameter";
			
			
			public static String type = "uml::TimeInterval::properties::type";
			
			
			public static String min = "uml::TimeInterval::properties::min";
			
			
			public static String max = "uml::TimeInterval::properties::max";
			
	
		}
	
	}

	/**
	 * DurationConstraint view descriptor
	 * 
	 */
	public static class DurationConstraint {
		public static class Properties {
	
			
			public static String name = "uml::DurationConstraint::properties::name";
			
			
			public static String visibility = "uml::DurationConstraint::properties::visibility";
			
			
			public static String clientDependency = "uml::DurationConstraint::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::DurationConstraint::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::DurationConstraint::properties::templateParameter";
			
			
			public static String constrainedElement = "uml::DurationConstraint::properties::constrainedElement";
			
			
			public static String context = "uml::DurationConstraint::properties::context";
			
			
			public static String firstEvent = "uml::DurationConstraint::properties::firstEvent";
			
	
		}
	
	}

	/**
	 * TimeObservation view descriptor
	 * 
	 */
	public static class TimeObservation {
		public static class Properties {
	
			
			public static String name = "uml::TimeObservation::properties::name";
			
			
			public static String visibility = "uml::TimeObservation::properties::visibility";
			
			
			public static String clientDependency = "uml::TimeObservation::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::TimeObservation::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::TimeObservation::properties::templateParameter";
			
			
			public static String event = "uml::TimeObservation::properties::event";
			
			
			public static String firstEvent = "uml::TimeObservation::properties::firstEvent";
			
	
		}
	
	}

	/**
	 * DurationObservation view descriptor
	 * 
	 */
	public static class DurationObservation {
		public static class Properties {
	
			
			public static String name = "uml::DurationObservation::properties::name";
			
			
			public static String visibility = "uml::DurationObservation::properties::visibility";
			
			
			public static String clientDependency = "uml::DurationObservation::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::DurationObservation::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::DurationObservation::properties::templateParameter";
			
			
			public static String event = "uml::DurationObservation::properties::event";
			
			
			public static String firstEvent = "uml::DurationObservation::properties::firstEvent";
			
	
		}
	
	}

	/**
	 * FinalState view descriptor
	 * 
	 */
	public static class FinalState {
		public static class Properties {
	
			
			public static String name = "uml::FinalState::properties::name";
			
			
			public static String visibility = "uml::FinalState::properties::visibility";
			
			
			public static String clientDependency = "uml::FinalState::properties::clientDependency";
			
			
			public static String isLeaf = "uml::FinalState::properties::isLeaf";
			
			
			public static String container = "uml::FinalState::properties::container";
			
			
			public static String submachine = "uml::FinalState::properties::submachine";
			
			
			public static String redefinedState = "uml::FinalState::properties::redefinedState";
			
	
		}
	
	}

	/**
	 * TimeEvent view descriptor
	 * 
	 */
	public static class TimeEvent {
		public static class Properties {
	
			
			public static String name = "uml::TimeEvent::properties::name";
			
			
			public static String visibility = "uml::TimeEvent::properties::visibility";
			
			
			public static String clientDependency = "uml::TimeEvent::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::TimeEvent::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::TimeEvent::properties::templateParameter";
			
			
			public static String isRelative = "uml::TimeEvent::properties::isRelative";
			
	
		}
	
	}

	/**
	 * ReadVariableAction view descriptor
	 * 
	 */
	public static class ReadVariableAction {
		public static class Properties {
	
			
			public static String name = "uml::ReadVariableAction::properties::name";
			
			
			public static String visibility = "uml::ReadVariableAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ReadVariableAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ReadVariableAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ReadVariableAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ReadVariableAction::properties::activity";
			
			
			public static String outgoing = "uml::ReadVariableAction::properties::outgoing";
			
			
			public static String incoming = "uml::ReadVariableAction::properties::incoming";
			
			
			public static String inPartition = "uml::ReadVariableAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ReadVariableAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ReadVariableAction::properties::redefinedNode";
			
			
			public static String variable = "uml::ReadVariableAction::properties::variable";
			
	
		}
	
	}

	/**
	 * ClearVariableAction view descriptor
	 * 
	 */
	public static class ClearVariableAction {
		public static class Properties {
	
			
			public static String name = "uml::ClearVariableAction::properties::name";
			
			
			public static String visibility = "uml::ClearVariableAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ClearVariableAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ClearVariableAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ClearVariableAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ClearVariableAction::properties::activity";
			
			
			public static String outgoing = "uml::ClearVariableAction::properties::outgoing";
			
			
			public static String incoming = "uml::ClearVariableAction::properties::incoming";
			
			
			public static String inPartition = "uml::ClearVariableAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ClearVariableAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ClearVariableAction::properties::redefinedNode";
			
			
			public static String variable = "uml::ClearVariableAction::properties::variable";
			
	
		}
	
	}

	/**
	 * AddVariableValueAction view descriptor
	 * 
	 */
	public static class AddVariableValueAction {
		public static class Properties {
	
			
			public static String name = "uml::AddVariableValueAction::properties::name";
			
			
			public static String visibility = "uml::AddVariableValueAction::properties::visibility";
			
			
			public static String clientDependency = "uml::AddVariableValueAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::AddVariableValueAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::AddVariableValueAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::AddVariableValueAction::properties::activity";
			
			
			public static String outgoing = "uml::AddVariableValueAction::properties::outgoing";
			
			
			public static String incoming = "uml::AddVariableValueAction::properties::incoming";
			
			
			public static String inPartition = "uml::AddVariableValueAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::AddVariableValueAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::AddVariableValueAction::properties::redefinedNode";
			
			
			public static String variable = "uml::AddVariableValueAction::properties::variable";
			
			
			public static String isReplaceAll = "uml::AddVariableValueAction::properties::isReplaceAll";
			
	
		}
	
	}

	/**
	 * RemoveVariableValueAction view descriptor
	 * 
	 */
	public static class RemoveVariableValueAction {
		public static class Properties {
	
			
			public static String name = "uml::RemoveVariableValueAction::properties::name";
			
			
			public static String visibility = "uml::RemoveVariableValueAction::properties::visibility";
			
			
			public static String clientDependency = "uml::RemoveVariableValueAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::RemoveVariableValueAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::RemoveVariableValueAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::RemoveVariableValueAction::properties::activity";
			
			
			public static String outgoing = "uml::RemoveVariableValueAction::properties::outgoing";
			
			
			public static String incoming = "uml::RemoveVariableValueAction::properties::incoming";
			
			
			public static String inPartition = "uml::RemoveVariableValueAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::RemoveVariableValueAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::RemoveVariableValueAction::properties::redefinedNode";
			
			
			public static String variable = "uml::RemoveVariableValueAction::properties::variable";
			
			
			public static String isRemoveDuplicates = "uml::RemoveVariableValueAction::properties::isRemoveDuplicates";
			
	
		}
	
	}

	/**
	 * RaiseExceptionAction view descriptor
	 * 
	 */
	public static class RaiseExceptionAction {
		public static class Properties {
	
			
			public static String name = "uml::RaiseExceptionAction::properties::name";
			
			
			public static String visibility = "uml::RaiseExceptionAction::properties::visibility";
			
			
			public static String clientDependency = "uml::RaiseExceptionAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::RaiseExceptionAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::RaiseExceptionAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::RaiseExceptionAction::properties::activity";
			
			
			public static String outgoing = "uml::RaiseExceptionAction::properties::outgoing";
			
			
			public static String incoming = "uml::RaiseExceptionAction::properties::incoming";
			
			
			public static String inPartition = "uml::RaiseExceptionAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::RaiseExceptionAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::RaiseExceptionAction::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * ActionInputPin view descriptor
	 * 
	 */
	public static class ActionInputPin {
		public static class Properties {
	
			
			public static String name = "uml::ActionInputPin::properties::name";
			
			
			public static String visibility = "uml::ActionInputPin::properties::visibility";
			
			
			public static String clientDependency = "uml::ActionInputPin::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ActionInputPin::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ActionInputPin::properties::inStructuredNode";
			
			
			public static String activity = "uml::ActionInputPin::properties::activity";
			
			
			public static String outgoing = "uml::ActionInputPin::properties::outgoing";
			
			
			public static String incoming = "uml::ActionInputPin::properties::incoming";
			
			
			public static String inPartition = "uml::ActionInputPin::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ActionInputPin::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ActionInputPin::properties::redefinedNode";
			
			
			public static String type = "uml::ActionInputPin::properties::type";
			
			
			public static String ordering = "uml::ActionInputPin::properties::ordering";
			
			
			public static String isControlType = "uml::ActionInputPin::properties::isControlType";
			
			
			public static String inState = "uml::ActionInputPin::properties::inState";
			
			
			public static String selection = "uml::ActionInputPin::properties::selection";
			
			
			public static String isOrdered = "uml::ActionInputPin::properties::isOrdered";
			
			
			public static String isUnique = "uml::ActionInputPin::properties::isUnique";
			
			
			public static String isControl = "uml::ActionInputPin::properties::isControl";
			
	
		}
	
	}

	/**
	 * InformationItem view descriptor
	 * 
	 */
	public static class InformationItem {
		public static class Properties {
	
			
			public static String name = "uml::InformationItem::properties::name";
			
			
			public static String visibility = "uml::InformationItem::properties::visibility";
			
			
			public static String clientDependency = "uml::InformationItem::properties::clientDependency";
			
			
			public static String isLeaf = "uml::InformationItem::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::InformationItem::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::InformationItem::properties::templateParameter";
			
			
			public static String isAbstract = "uml::InformationItem::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::InformationItem::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::InformationItem::properties::redefinedClassifier";
			
			
			public static String representation = "uml::InformationItem::properties::representation";
			
			
			public static String useCase = "uml::InformationItem::properties::useCase";
			
			
			public static String represented = "uml::InformationItem::properties::represented";
			
	
		}
	
	}

	/**
	 * InformationFlow view descriptor
	 * 
	 */
	public static class InformationFlow {
		public static class Properties {
	
			
			public static String name = "uml::InformationFlow::properties::name";
			
			
			public static String visibility = "uml::InformationFlow::properties::visibility";
			
			
			public static String clientDependency = "uml::InformationFlow::properties::clientDependency";
			
			
			public static String owningTemplateParameter = "uml::InformationFlow::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::InformationFlow::properties::templateParameter";
			
			
			public static String realization = "uml::InformationFlow::properties::realization";
			
			
			public static String conveyed = "uml::InformationFlow::properties::conveyed";
			
			
			public static String informationSource = "uml::InformationFlow::properties::informationSource";
			
			
			public static String informationTarget = "uml::InformationFlow::properties::informationTarget";
			
			
			public static String realizingActivityEdge = "uml::InformationFlow::properties::realizingActivityEdge";
			
			
			public static String realizingConnector = "uml::InformationFlow::properties::realizingConnector";
			
			
			public static String realizingMessage = "uml::InformationFlow::properties::realizingMessage";
			
	
		}
	
	}

	/**
	 * ReadExtentAction view descriptor
	 * 
	 */
	public static class ReadExtentAction {
		public static class Properties {
	
			
			public static String name = "uml::ReadExtentAction::properties::name";
			
			
			public static String visibility = "uml::ReadExtentAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ReadExtentAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ReadExtentAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ReadExtentAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ReadExtentAction::properties::activity";
			
			
			public static String outgoing = "uml::ReadExtentAction::properties::outgoing";
			
			
			public static String incoming = "uml::ReadExtentAction::properties::incoming";
			
			
			public static String inPartition = "uml::ReadExtentAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ReadExtentAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ReadExtentAction::properties::redefinedNode";
			
			
			public static String classifier = "uml::ReadExtentAction::properties::classifier";
			
	
		}
	
	}

	/**
	 * ReclassifyObjectAction view descriptor
	 * 
	 */
	public static class ReclassifyObjectAction {
		public static class Properties {
	
			
			public static String name = "uml::ReclassifyObjectAction::properties::name";
			
			
			public static String visibility = "uml::ReclassifyObjectAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ReclassifyObjectAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ReclassifyObjectAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ReclassifyObjectAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ReclassifyObjectAction::properties::activity";
			
			
			public static String outgoing = "uml::ReclassifyObjectAction::properties::outgoing";
			
			
			public static String incoming = "uml::ReclassifyObjectAction::properties::incoming";
			
			
			public static String inPartition = "uml::ReclassifyObjectAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ReclassifyObjectAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ReclassifyObjectAction::properties::redefinedNode";
			
			
			public static String isReplaceAll = "uml::ReclassifyObjectAction::properties::isReplaceAll";
			
			
			public static String oldClassifier = "uml::ReclassifyObjectAction::properties::oldClassifier";
			
			
			public static String newClassifier = "uml::ReclassifyObjectAction::properties::newClassifier";
			
	
		}
	
	}

	/**
	 * ReadIsClassifiedObjectAction view descriptor
	 * 
	 */
	public static class ReadIsClassifiedObjectAction {
		public static class Properties {
	
			
			public static String name = "uml::ReadIsClassifiedObjectAction::properties::name";
			
			
			public static String visibility = "uml::ReadIsClassifiedObjectAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ReadIsClassifiedObjectAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ReadIsClassifiedObjectAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ReadIsClassifiedObjectAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ReadIsClassifiedObjectAction::properties::activity";
			
			
			public static String outgoing = "uml::ReadIsClassifiedObjectAction::properties::outgoing";
			
			
			public static String incoming = "uml::ReadIsClassifiedObjectAction::properties::incoming";
			
			
			public static String inPartition = "uml::ReadIsClassifiedObjectAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ReadIsClassifiedObjectAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ReadIsClassifiedObjectAction::properties::redefinedNode";
			
			
			public static String isDirect = "uml::ReadIsClassifiedObjectAction::properties::isDirect";
			
			
			public static String classifier = "uml::ReadIsClassifiedObjectAction::properties::classifier";
			
	
		}
	
	}

	/**
	 * StartClassifierBehaviorAction view descriptor
	 * 
	 */
	public static class StartClassifierBehaviorAction {
		public static class Properties {
	
			
			public static String name = "uml::StartClassifierBehaviorAction::properties::name";
			
			
			public static String visibility = "uml::StartClassifierBehaviorAction::properties::visibility";
			
			
			public static String clientDependency = "uml::StartClassifierBehaviorAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::StartClassifierBehaviorAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::StartClassifierBehaviorAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::StartClassifierBehaviorAction::properties::activity";
			
			
			public static String outgoing = "uml::StartClassifierBehaviorAction::properties::outgoing";
			
			
			public static String incoming = "uml::StartClassifierBehaviorAction::properties::incoming";
			
			
			public static String inPartition = "uml::StartClassifierBehaviorAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::StartClassifierBehaviorAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::StartClassifierBehaviorAction::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * ReadLinkObjectEndAction view descriptor
	 * 
	 */
	public static class ReadLinkObjectEndAction {
		public static class Properties {
	
			
			public static String name = "uml::ReadLinkObjectEndAction::properties::name";
			
			
			public static String visibility = "uml::ReadLinkObjectEndAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ReadLinkObjectEndAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ReadLinkObjectEndAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ReadLinkObjectEndAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ReadLinkObjectEndAction::properties::activity";
			
			
			public static String outgoing = "uml::ReadLinkObjectEndAction::properties::outgoing";
			
			
			public static String incoming = "uml::ReadLinkObjectEndAction::properties::incoming";
			
			
			public static String inPartition = "uml::ReadLinkObjectEndAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ReadLinkObjectEndAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ReadLinkObjectEndAction::properties::redefinedNode";
			
			
			public static String end = "uml::ReadLinkObjectEndAction::properties::end";
			
	
		}
	
	}

	/**
	 * ReadLinkObjectEndQualifierAction view descriptor
	 * 
	 */
	public static class ReadLinkObjectEndQualifierAction {
		public static class Properties {
	
			
			public static String name = "uml::ReadLinkObjectEndQualifierAction::properties::name";
			
			
			public static String visibility = "uml::ReadLinkObjectEndQualifierAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ReadLinkObjectEndQualifierAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ReadLinkObjectEndQualifierAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ReadLinkObjectEndQualifierAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ReadLinkObjectEndQualifierAction::properties::activity";
			
			
			public static String outgoing = "uml::ReadLinkObjectEndQualifierAction::properties::outgoing";
			
			
			public static String incoming = "uml::ReadLinkObjectEndQualifierAction::properties::incoming";
			
			
			public static String inPartition = "uml::ReadLinkObjectEndQualifierAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ReadLinkObjectEndQualifierAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ReadLinkObjectEndQualifierAction::properties::redefinedNode";
			
			
			public static String qualifier = "uml::ReadLinkObjectEndQualifierAction::properties::qualifier";
			
	
		}
	
	}

	/**
	 * CreateLinkObjectAction view descriptor
	 * 
	 */
	public static class CreateLinkObjectAction {
		public static class Properties {
	
			
			public static String name = "uml::CreateLinkObjectAction::properties::name";
			
			
			public static String visibility = "uml::CreateLinkObjectAction::properties::visibility";
			
			
			public static String clientDependency = "uml::CreateLinkObjectAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::CreateLinkObjectAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::CreateLinkObjectAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::CreateLinkObjectAction::properties::activity";
			
			
			public static String outgoing = "uml::CreateLinkObjectAction::properties::outgoing";
			
			
			public static String incoming = "uml::CreateLinkObjectAction::properties::incoming";
			
			
			public static String inPartition = "uml::CreateLinkObjectAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::CreateLinkObjectAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::CreateLinkObjectAction::properties::redefinedNode";
			
	
		}
	
	}

	/**
	 * AcceptEventAction view descriptor
	 * 
	 */
	public static class AcceptEventAction {
		public static class Properties {
	
			
			public static String name = "uml::AcceptEventAction::properties::name";
			
			
			public static String visibility = "uml::AcceptEventAction::properties::visibility";
			
			
			public static String clientDependency = "uml::AcceptEventAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::AcceptEventAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::AcceptEventAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::AcceptEventAction::properties::activity";
			
			
			public static String outgoing = "uml::AcceptEventAction::properties::outgoing";
			
			
			public static String incoming = "uml::AcceptEventAction::properties::incoming";
			
			
			public static String inPartition = "uml::AcceptEventAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::AcceptEventAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::AcceptEventAction::properties::redefinedNode";
			
			
			public static String isUnmarshall = "uml::AcceptEventAction::properties::isUnmarshall";
			
	
		}
	
	}

	/**
	 * AcceptCallAction view descriptor
	 * 
	 */
	public static class AcceptCallAction {
		public static class Properties {
	
			
			public static String name = "uml::AcceptCallAction::properties::name";
			
			
			public static String visibility = "uml::AcceptCallAction::properties::visibility";
			
			
			public static String clientDependency = "uml::AcceptCallAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::AcceptCallAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::AcceptCallAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::AcceptCallAction::properties::activity";
			
			
			public static String outgoing = "uml::AcceptCallAction::properties::outgoing";
			
			
			public static String incoming = "uml::AcceptCallAction::properties::incoming";
			
			
			public static String inPartition = "uml::AcceptCallAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::AcceptCallAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::AcceptCallAction::properties::redefinedNode";
			
			
			public static String isUnmarshall = "uml::AcceptCallAction::properties::isUnmarshall";
			
	
		}
	
	}

	/**
	 * ReplyAction view descriptor
	 * 
	 */
	public static class ReplyAction {
		public static class Properties {
	
			
			public static String name = "uml::ReplyAction::properties::name";
			
			
			public static String visibility = "uml::ReplyAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ReplyAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ReplyAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ReplyAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ReplyAction::properties::activity";
			
			
			public static String outgoing = "uml::ReplyAction::properties::outgoing";
			
			
			public static String incoming = "uml::ReplyAction::properties::incoming";
			
			
			public static String inPartition = "uml::ReplyAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ReplyAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ReplyAction::properties::redefinedNode";
			
			
			public static String replyToCall = "uml::ReplyAction::properties::replyToCall";
			
	
		}
	
	}

	/**
	 * UnmarshallAction view descriptor
	 * 
	 */
	public static class UnmarshallAction {
		public static class Properties {
	
			
			public static String name = "uml::UnmarshallAction::properties::name";
			
			
			public static String visibility = "uml::UnmarshallAction::properties::visibility";
			
			
			public static String clientDependency = "uml::UnmarshallAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::UnmarshallAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::UnmarshallAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::UnmarshallAction::properties::activity";
			
			
			public static String outgoing = "uml::UnmarshallAction::properties::outgoing";
			
			
			public static String incoming = "uml::UnmarshallAction::properties::incoming";
			
			
			public static String inPartition = "uml::UnmarshallAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::UnmarshallAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::UnmarshallAction::properties::redefinedNode";
			
			
			public static String unmarshallType = "uml::UnmarshallAction::properties::unmarshallType";
			
	
		}
	
	}

	/**
	 * ReduceAction view descriptor
	 * 
	 */
	public static class ReduceAction {
		public static class Properties {
	
			
			public static String name = "uml::ReduceAction::properties::name";
			
			
			public static String visibility = "uml::ReduceAction::properties::visibility";
			
			
			public static String clientDependency = "uml::ReduceAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ReduceAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ReduceAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::ReduceAction::properties::activity";
			
			
			public static String outgoing = "uml::ReduceAction::properties::outgoing";
			
			
			public static String incoming = "uml::ReduceAction::properties::incoming";
			
			
			public static String inPartition = "uml::ReduceAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ReduceAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ReduceAction::properties::redefinedNode";
			
			
			public static String reducer = "uml::ReduceAction::properties::reducer";
			
			
			public static String isOrdered = "uml::ReduceAction::properties::isOrdered";
			
	
		}
	
	}

	/**
	 * StartObjectBehaviorAction view descriptor
	 * 
	 */
	public static class StartObjectBehaviorAction {
		public static class Properties {
	
			
			public static String name = "uml::StartObjectBehaviorAction::properties::name";
			
			
			public static String visibility = "uml::StartObjectBehaviorAction::properties::visibility";
			
			
			public static String clientDependency = "uml::StartObjectBehaviorAction::properties::clientDependency";
			
			
			public static String isLeaf = "uml::StartObjectBehaviorAction::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::StartObjectBehaviorAction::properties::inStructuredNode";
			
			
			public static String activity = "uml::StartObjectBehaviorAction::properties::activity";
			
			
			public static String outgoing = "uml::StartObjectBehaviorAction::properties::outgoing";
			
			
			public static String incoming = "uml::StartObjectBehaviorAction::properties::incoming";
			
			
			public static String inPartition = "uml::StartObjectBehaviorAction::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::StartObjectBehaviorAction::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::StartObjectBehaviorAction::properties::redefinedNode";
			
			
			public static String onPort = "uml::StartObjectBehaviorAction::properties::onPort";
			
			
			public static String isSynchronous = "uml::StartObjectBehaviorAction::properties::isSynchronous";
			
	
		}
	
	}

	/**
	 * JoinNode view descriptor
	 * 
	 */
	public static class JoinNode {
		public static class Properties {
	
			
			public static String name = "uml::JoinNode::properties::name";
			
			
			public static String visibility = "uml::JoinNode::properties::visibility";
			
			
			public static String clientDependency = "uml::JoinNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::JoinNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::JoinNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::JoinNode::properties::activity";
			
			
			public static String outgoing = "uml::JoinNode::properties::outgoing";
			
			
			public static String incoming = "uml::JoinNode::properties::incoming";
			
			
			public static String inPartition = "uml::JoinNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::JoinNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::JoinNode::properties::redefinedNode";
			
			
			public static String isCombineDuplicate = "uml::JoinNode::properties::isCombineDuplicate";
			
	
		}
	
	}

	/**
	 * DataStoreNode view descriptor
	 * 
	 */
	public static class DataStoreNode {
		public static class Properties {
	
			
			public static String name = "uml::DataStoreNode::properties::name";
			
			
			public static String visibility = "uml::DataStoreNode::properties::visibility";
			
			
			public static String clientDependency = "uml::DataStoreNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::DataStoreNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::DataStoreNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::DataStoreNode::properties::activity";
			
			
			public static String outgoing = "uml::DataStoreNode::properties::outgoing";
			
			
			public static String incoming = "uml::DataStoreNode::properties::incoming";
			
			
			public static String inPartition = "uml::DataStoreNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::DataStoreNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::DataStoreNode::properties::redefinedNode";
			
			
			public static String type = "uml::DataStoreNode::properties::type";
			
			
			public static String ordering = "uml::DataStoreNode::properties::ordering";
			
			
			public static String isControlType = "uml::DataStoreNode::properties::isControlType";
			
			
			public static String inState = "uml::DataStoreNode::properties::inState";
			
			
			public static String selection = "uml::DataStoreNode::properties::selection";
			
	
		}
	
	}

	/**
	 * ConditionalNode view descriptor
	 * 
	 */
	public static class ConditionalNode {
		public static class Properties {
	
			
			public static String name = "uml::ConditionalNode::properties::name";
			
			
			public static String visibility = "uml::ConditionalNode::properties::visibility";
			
			
			public static String clientDependency = "uml::ConditionalNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ConditionalNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ConditionalNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::ConditionalNode::properties::activity";
			
			
			public static String outgoing = "uml::ConditionalNode::properties::outgoing";
			
			
			public static String incoming = "uml::ConditionalNode::properties::incoming";
			
			
			public static String inPartition = "uml::ConditionalNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ConditionalNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ConditionalNode::properties::redefinedNode";
			
			
			public static String inActivity = "uml::ConditionalNode::properties::inActivity";
			
			
			public static String mustIsolate = "uml::ConditionalNode::properties::mustIsolate";
			
			
			public static String isDeterminate = "uml::ConditionalNode::properties::isDeterminate";
			
			
			public static String isAssured = "uml::ConditionalNode::properties::isAssured";
			
	
		}
	
	}

	/**
	 * Clause view descriptor
	 * 
	 */
	public static class Clause {
		public static class Properties {
	
			
			public static String test = "uml::Clause::properties::test";
			
			
			public static String body = "uml::Clause::properties::body";
			
			
			public static String predecessorClause = "uml::Clause::properties::predecessorClause";
			
			
			public static String successorClause = "uml::Clause::properties::successorClause";
			
			
			public static String decider = "uml::Clause::properties::decider";
			
			
			public static String bodyOutput = "uml::Clause::properties::bodyOutput";
			
	
		}
	
	}

	/**
	 * LoopNode view descriptor
	 * 
	 */
	public static class LoopNode {
		public static class Properties {
	
			
			public static String name = "uml::LoopNode::properties::name";
			
			
			public static String visibility = "uml::LoopNode::properties::visibility";
			
			
			public static String clientDependency = "uml::LoopNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::LoopNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::LoopNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::LoopNode::properties::activity";
			
			
			public static String outgoing = "uml::LoopNode::properties::outgoing";
			
			
			public static String incoming = "uml::LoopNode::properties::incoming";
			
			
			public static String inPartition = "uml::LoopNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::LoopNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::LoopNode::properties::redefinedNode";
			
			
			public static String inActivity = "uml::LoopNode::properties::inActivity";
			
			
			public static String mustIsolate = "uml::LoopNode::properties::mustIsolate";
			
			
			public static String isTestedFirst = "uml::LoopNode::properties::isTestedFirst";
			
			
			public static String bodyPart = "uml::LoopNode::properties::bodyPart";
			
			
			public static String setupPart = "uml::LoopNode::properties::setupPart";
			
			
			public static String decider = "uml::LoopNode::properties::decider";
			
			
			public static String test = "uml::LoopNode::properties::test";
			
			
			public static String loopVariable = "uml::LoopNode::properties::loopVariable";
			
			
			public static String bodyOutput = "uml::LoopNode::properties::bodyOutput";
			
	
		}
	
	}

	/**
	 * ExpansionNode view descriptor
	 * 
	 */
	public static class ExpansionNode {
		public static class Properties {
	
			
			public static String name = "uml::ExpansionNode::properties::name";
			
			
			public static String visibility = "uml::ExpansionNode::properties::visibility";
			
			
			public static String clientDependency = "uml::ExpansionNode::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ExpansionNode::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ExpansionNode::properties::inStructuredNode";
			
			
			public static String activity = "uml::ExpansionNode::properties::activity";
			
			
			public static String outgoing = "uml::ExpansionNode::properties::outgoing";
			
			
			public static String incoming = "uml::ExpansionNode::properties::incoming";
			
			
			public static String inPartition = "uml::ExpansionNode::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ExpansionNode::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ExpansionNode::properties::redefinedNode";
			
			
			public static String type = "uml::ExpansionNode::properties::type";
			
			
			public static String ordering = "uml::ExpansionNode::properties::ordering";
			
			
			public static String isControlType = "uml::ExpansionNode::properties::isControlType";
			
			
			public static String inState = "uml::ExpansionNode::properties::inState";
			
			
			public static String selection = "uml::ExpansionNode::properties::selection";
			
			
			public static String regionAsOutput = "uml::ExpansionNode::properties::regionAsOutput";
			
			
			public static String regionAsInput = "uml::ExpansionNode::properties::regionAsInput";
			
	
		}
	
	}

	/**
	 * ExpansionRegion view descriptor
	 * 
	 */
	public static class ExpansionRegion {
		public static class Properties {
	
			
			public static String name = "uml::ExpansionRegion::properties::name";
			
			
			public static String visibility = "uml::ExpansionRegion::properties::visibility";
			
			
			public static String clientDependency = "uml::ExpansionRegion::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ExpansionRegion::properties::isLeaf";
			
			
			public static String inStructuredNode = "uml::ExpansionRegion::properties::inStructuredNode";
			
			
			public static String activity = "uml::ExpansionRegion::properties::activity";
			
			
			public static String outgoing = "uml::ExpansionRegion::properties::outgoing";
			
			
			public static String incoming = "uml::ExpansionRegion::properties::incoming";
			
			
			public static String inPartition = "uml::ExpansionRegion::properties::inPartition";
			
			
			public static String inInterruptibleRegion = "uml::ExpansionRegion::properties::inInterruptibleRegion";
			
			
			public static String redefinedNode = "uml::ExpansionRegion::properties::redefinedNode";
			
			
			public static String inActivity = "uml::ExpansionRegion::properties::inActivity";
			
			
			public static String mustIsolate = "uml::ExpansionRegion::properties::mustIsolate";
			
			
			public static String mode = "uml::ExpansionRegion::properties::mode";
			
			
			public static String inputElement = "uml::ExpansionRegion::properties::inputElement";
			
			
			public static String outputElement = "uml::ExpansionRegion::properties::outputElement";
			
	
		}
	
	}

	/**
	 * ProtocolTransition view descriptor
	 * 
	 */
	public static class ProtocolTransition {
		public static class Properties {
	
			
			public static String name = "uml::ProtocolTransition::properties::name";
			
			
			public static String visibility = "uml::ProtocolTransition::properties::visibility";
			
			
			public static String clientDependency = "uml::ProtocolTransition::properties::clientDependency";
			
			
			public static String isLeaf = "uml::ProtocolTransition::properties::isLeaf";
			
			
			public static String kind = "uml::ProtocolTransition::properties::kind";
			
			
			public static String container = "uml::ProtocolTransition::properties::container";
			
			
			public static String source = "uml::ProtocolTransition::properties::source";
			
			
			public static String target = "uml::ProtocolTransition::properties::target";
			
			
			public static String redefinedTransition = "uml::ProtocolTransition::properties::redefinedTransition";
			
			
			public static String guard = "uml::ProtocolTransition::properties::guard";
			
			
			public static String postCondition = "uml::ProtocolTransition::properties::postCondition";
			
			
			public static String preCondition = "uml::ProtocolTransition::properties::preCondition";
			
	
		}
	
	}

	/**
	 * AssociationClass view descriptor
	 * 
	 */
	public static class AssociationClass {
		public static class Properties {
	
			
			public static String name = "uml::AssociationClass::properties::name";
			
			
			public static String visibility = "uml::AssociationClass::properties::visibility";
			
			
			public static String clientDependency = "uml::AssociationClass::properties::clientDependency";
			
			
			public static String isLeaf = "uml::AssociationClass::properties::isLeaf";
			
			
			public static String owningTemplateParameter = "uml::AssociationClass::properties::owningTemplateParameter";
			
			
			public static String templateParameter = "uml::AssociationClass::properties::templateParameter";
			
			
			public static String isAbstract = "uml::AssociationClass::properties::isAbstract";
			
			
			public static String powertypeExtent = "uml::AssociationClass::properties::powertypeExtent";
			
			
			public static String redefinedClassifier = "uml::AssociationClass::properties::redefinedClassifier";
			
			
			public static String representation = "uml::AssociationClass::properties::representation";
			
			
			public static String useCase = "uml::AssociationClass::properties::useCase";
			
			
			public static String classifierBehavior = "uml::AssociationClass::properties::classifierBehavior";
			
			
			public static String isActive = "uml::AssociationClass::properties::isActive";
			
			
			public static String memberEnd = "uml::AssociationClass::properties::memberEnd";
			
			
			public static String isDerived = "uml::AssociationClass::properties::isDerived";
			
			
			public static String navigableOwnedEnd = "uml::AssociationClass::properties::navigableOwnedEnd";
			
	
		}
	
	}

}
