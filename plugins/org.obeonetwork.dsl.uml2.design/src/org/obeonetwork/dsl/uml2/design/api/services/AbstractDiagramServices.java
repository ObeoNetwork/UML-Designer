/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

package org.obeonetwork.dsl.uml2.design.api.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.internal.helper.task.operations.CreateViewTask;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeContainerSpec;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.ExecutionEnvironment;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Manifestation;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.internal.services.LabelServices;
import org.obeonetwork.dsl.uml2.design.internal.services.ReconnectSwitch;
import org.obeonetwork.dsl.uml2.design.internal.services.RelatedServices;
import org.obeonetwork.dsl.uml2.design.internal.services.SemanticElementsSwitch;
import org.obeonetwork.dsl.uml2.design.internal.services.UIServices;
import org.obeonetwork.dsl.uml2.design.internal.wizards.Messages;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A set of services used by diagrams.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public abstract class AbstractDiagramServices {

	/**
	 * Compute default name.
	 *
	 * @param element
	 *            New element
	 * @return Name for the new element, he name will looks like 'ElementType'+total of existing elements of
	 *         the same type.
	 */
	public String computeDefaultName(final EObject element) {
		return LabelServices.INSTANCE.computeDefaultName(element);
	}

	/**
	 * Compute the label of the given element.
	 *
	 * @param element
	 *            the {@link Element} for which to retrieve a label.
	 * @return the computed label.
	 */
	public String computeUmlLabel(Element element) {
		return LabelServices.INSTANCE.computeUmlLabel(element);
	}

	/**
	 * Create view.
	 *
	 * @param semanticElement
	 *            Semantic element
	 * @param containerView
	 *            Container view
	 * @param session
	 *            Session
	 * @param containerViewVariable
	 *            Name of the container view variable
	 */
	protected void createView(final EObject semanticElement, final DSemanticDecorator containerView,
			final Session session, final String containerViewVariable) {
		// Get all available mappings applicable for the copiedElement in the
		// current container
		final List<DiagramElementMapping> semanticElementMappings = getMappings(semanticElement,
				containerView, session);

		// Build a createView tool
		final CreateView createViewOp = ToolFactory.eINSTANCE.createCreateView();
		for (final DiagramElementMapping copiedElementMapping : semanticElementMappings) {
			final DiagramElementMapping tmpCopiedElementMapping = copiedElementMapping;
			createViewOp.setMapping(tmpCopiedElementMapping);
			final String containerViewExpression = "var:" + containerViewVariable; //$NON-NLS-1$
			createViewOp.setContainerViewExpression(containerViewExpression);

			session.getTransactionalEditingDomain().getCommandStack()
			.execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

				@Override
				protected void doExecute() {
					try {
						// Get the command context
						DRepresentation representation = null;
						if (containerView instanceof DRepresentation) {
							representation = (DRepresentation)containerView;
						} else if (containerView instanceof DDiagramElement) {
							representation = ((DDiagramElement)containerView).getParentDiagram();
						}

						final CommandContext context = new CommandContext(semanticElement,
								representation);

						// Execute the create view task
						final CreateViewTask task = new CreateViewTask(context, session
								.getModelAccessor(), createViewOp, session.getInterpreter());
						task.execute();

						final Object createdView = session.getInterpreter().getVariable(
								createViewOp.getVariableName());
						if (createdView instanceof DDiagramElement) {
							final DDiagramElement element = (DDiagramElement)createdView;
							HideFilterHelper.INSTANCE.reveal(element);
						}
					} catch (final MetaClassNotFoundException e) {
						UMLDesignerPlugin.log(IStatus.ERROR, NLS
								.bind(Messages.UmlModelWizard_UI_ErrorMsg_BadFileExtension,
										semanticElement), e);
					} catch (final FeatureNotFoundException e) {
						UMLDesignerPlugin.log(IStatus.ERROR, NLS
								.bind(Messages.UmlModelWizard_UI_ErrorMsg_BadFileExtension,
										semanticElement), e);
					}
				}
			});
		}
	}

	/**
	 * Default height.
	 *
	 * @param any
	 *            Any
	 * @return The default height.
	 */
	public int defaultHeight(EObject any) {
		return UIServices.INSTANCE.defaultHeight();
	}

	/**
	 * Default width.
	 *
	 * @param any
	 *            Any
	 * @return The default width.
	 */
	public int defaultWidth(EObject any) {
		return UIServices.INSTANCE.defaultWidth();
	}

	/**
	 * Drop a semantic element and create the corresponding view in the given container
	 *
	 * @param newContainer
	 *            Semantic container
	 * @param semanticElement
	 *            Element to drop
	 * @param containerView
	 *            Container view
	 * @param moveSemanticElement
	 *            True to move the dropped semantic element or false to just show the element on a diagram
	 */
	private void drop(final Element newContainer, final Element semanticElement,
			final DSemanticDecorator containerView, boolean moveSemanticElement) {
		final Session session = SessionManager.INSTANCE.getSession(newContainer);
		final Element oldContainer = semanticElement.getOwner();
		if (moveSemanticElement && oldContainer != newContainer) {
			// Move the semantic element to the selected container
			final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
			// The feature is set to null because the domain will deduce it
			Command cmd = AddCommand.create(domain, newContainer, null, semanticElement);
			if (cmd.canExecute()) {
				cmd.execute();
			}
			cmd = RemoveCommand.create(domain, oldContainer, null, semanticElement);
			if (cmd.canExecute()) {
				cmd.execute();
			}

			if (semanticElement instanceof UseCase) {
				// Reset the current element as subject
				cmd = SetCommand.create(domain, semanticElement, UMLPackage.Literals.USE_CASE__SUBJECT,
						SetCommand.UNSET_VALUE);
				if (cmd.canExecute()) {
					cmd.execute();
				}
				final List<Element> subjects = new ArrayList<Element>();
				subjects.add(newContainer);
				cmd = SetCommand.create(domain, semanticElement, UMLPackage.Literals.USE_CASE__SUBJECT,
						subjects);
				if (cmd.canExecute()) {
					cmd.execute();
				}
			}
		}

		// Show the semantic element on the diagram
		showView(semanticElement, containerView, session, "newContainerView"); //$NON-NLS-1$
	}

	/**
	 * Drop a semantic element from a diagram and create the corresponding view in the given container
	 *
	 * @param newContainer
	 *            Semantic container
	 * @param semanticElement
	 *            Element to drop
	 * @param containerView
	 *            Container view
	 */
	public void dropFromDiagram(final Element newContainer, final Element semanticElement,
			final DSemanticDecorator containerView) {
		drop(newContainer, semanticElement, containerView, true);
	}

	/**
	 * Drop a semantic element and create the corresponding view in the given container
	 *
	 * @param newContainer
	 *            Semantic container
	 * @param semanticElement
	 *            Element to drop
	 * @param containerView
	 *            Container view
	 */
	public void dropFromModel(final Element newContainer, final Element semanticElement,
			final DSemanticDecorator containerView) {
		drop(newContainer, semanticElement, containerView, !(containerView instanceof DSemanticDiagram));
	}

	/**
	 * Get all available root packages.
	 *
	 * @param element
	 *            ELement
	 * @return List of root packages
	 */
	public List<Package> getAllAvailableRootPackages(Element element) {
		// <%script type="uml.Element" name="allAvailableRootPackages"%>
		// <%(getRootContainer().filter("Package") + rootPackagesFromImportedModel).nMinimize()%>
		final List<Package> packages = Lists.newArrayList();
		packages.add(element.getModel());
		packages.addAll(Lists.newArrayList(Iterables.filter(element.getModel().getImportedPackages(),
				Model.class)));
		return packages;
	}

	/**
	 * Get all root elements of the current session.
	 *
	 * @param any
	 *            The element to retrieve a session
	 * @return root elements
	 */
	public Collection<EObject> getAllRootsInSession(EObject any) {
		final Session session = SessionManager.INSTANCE.getSession(any);
		final Collection<EObject> roots = new ArrayList<EObject>();
		if (session != null) {
			for (final Resource childRes : session.getSemanticResources()) {
				roots.addAll(childRes.getContents());
			}
		}
		return roots;
	}

	/**
	 * Get all the hidden existing diagram elements related to a semantic element.
	 *
	 * @param semanticElement
	 *            Semantic element
	 * @param containerView
	 *            Container view
	 * @return List of all existing diagram elements for the given semantic element which are currently hidden
	 *         in the diagram
	 */
	private List<DDiagramElement> getHiddenExistingDiagramElements(EObject semanticElement,
			DSemanticDecorator containerView) {
		final List<DDiagramElement> existingDiagramElements = Lists.newArrayList();
		if (containerView instanceof DSemanticDiagram) {
			for (final DDiagramElement element : ((DSemanticDiagram)containerView).getDiagramElements()) {
				if (semanticElement.equals(element.getTarget())) {
					final DDiagramElementQuery query = new DDiagramElementQuery(element);
					if (query.isHidden()) {
						existingDiagramElements.add(element);
					}
					// Get the hidden parent container of the element to reveal, in order to reveal all the
					// hierarchy
					existingDiagramElements.addAll(getHiddenParentContainerViews(element));
				}
			}
		}
		return existingDiagramElements;
	}

	/**
	 * Get all the hidden diagram element in the hierarchy of a given diagram element.
	 *
	 * @param diagramElement
	 *            Diagram element
	 * @return List of all the hidden parent container element
	 */
	private List<DDiagramElement> getHiddenParentContainerViews(DDiagramElement diagramElement) {
		final List<DDiagramElement> containerViews = Lists.newArrayList();
		EObject containerView = diagramElement.eContainer();
		while (!(containerView instanceof DDiagram) && containerView instanceof DDiagramElement) {
			final DDiagramElementQuery query = new DDiagramElementQuery((DDiagramElement)containerView);
			if (query.isHidden()) {
				containerViews.add((DDiagramElement)containerView);
			}
			containerView = containerView.eContainer();
		}
		return containerViews;
	}

	/**
	 * Get mappings available for a given container view.
	 *
	 * @param containerView
	 *            Container view
	 * @param session
	 *            Session
	 * @return List of mappings which could not be null
	 */
	protected List<DiagramElementMapping> getMappings(final DSemanticDecorator containerView, Session session) {
		final List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();

		if (containerView instanceof DSemanticDiagram) {

			for (final DiagramElementMapping mapping : ((DSemanticDiagram)containerView).getDescription()
					.getAllContainerMappings()) {
				if (!mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
			for (final DiagramElementMapping mapping : ((DSemanticDiagram)containerView).getDescription()
					.getAllNodeMappings()) {
				if (!mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
		} else if (containerView instanceof DNodeContainerSpec) {
			for (final DiagramElementMapping mapping : ((DNodeContainerSpec)containerView).getActualMapping()
					.getAllContainerMappings()) {
				if (!mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
			for (final DiagramElementMapping mapping : ((DNodeContainerSpec)containerView).getActualMapping()
					.getAllNodeMappings()) {
				if (!mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
		}
		return mappings;
	}

	/**
	 * Get mappings available for a semantic element and a given container view.
	 *
	 * @param semanticElement
	 *            Semantic element
	 * @param containerView
	 *            Container view
	 * @param session
	 *            Session
	 * @return List of mappings which could not be null
	 */
	protected List<DiagramElementMapping> getMappings(final EObject semanticElement,
			final DSemanticDecorator containerView, Session session) {
		final ModelAccessor modelAccessor = session.getModelAccessor();
		final List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();

		if (containerView instanceof DSemanticDiagram) {

			for (final DiagramElementMapping mapping : ((DSemanticDiagram)containerView).getDescription()
					.getAllContainerMappings()) {
				final String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass) && !mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
			for (final DiagramElementMapping mapping : ((DSemanticDiagram)containerView).getDescription()
					.getAllNodeMappings()) {
				final String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass) && !mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
		} else if (containerView instanceof DNodeContainerSpec) {
			for (final DiagramElementMapping mapping : ((DNodeContainerSpec)containerView).getActualMapping()
					.getAllContainerMappings()) {
				final String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass) && !mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
			for (final DiagramElementMapping mapping : ((DNodeContainerSpec)containerView).getActualMapping()
					.getAllNodeMappings()) {
				final String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass) && !mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
		}
		return mappings;
	}

	/**
	 * Get related elements.
	 *
	 * @param cur
	 *            Element
	 * @return Related elements
	 */
	public Collection<EObject> getRelated(EObject cur) {
		return RelatedServices.INSTANCE.getRelated(cur);
	}

	/**
	 * return the list of semantic elements we should bind with the given element in the property view.
	 *
	 * @param e
	 *            a semantic element.
	 * @return the list of semantic elements we should bind with the given element in the property view.
	 */
	public Collection<EObject> getSemanticElements(EObject e) {
		return new SemanticElementsSwitch().getSemanticElements(e);
	}

	/**
	 * Use to inactivate a viewpoint.
	 *
	 * @param element
	 *            Element
	 * @return Just always return false
	 */
	public boolean inactive(Element element) {
		return false;
	}

	/**
	 * States if the given object is related to the context {@link Classifier}.
	 *
	 * @param toFilter
	 *            the candidate to check for relation
	 * @param context
	 *            the classifier context object.
	 * @return <code>true</code> if the given object is related to the context {@link Classifier},
	 *         <code>false</code> otherwise.
	 */
	public boolean isRelated(EObject toFilter, EObject context) {
		boolean res = false;
		if (toFilter.equals(context)) {
			res = true;
		} else if (context instanceof Classifier) {
			if (toFilter instanceof Generalization) {
				res = ((Classifier)context).getGeneralizations().contains(toFilter)
						|| ((Generalization)toFilter).getGeneral() == context;
			} else if (toFilter instanceof InterfaceRealization && context instanceof Class) {
				res = ((Class)context).getInterfaceRealizations().contains(toFilter)
						|| ((InterfaceRealization)toFilter).getContract() == context;
			} else if (toFilter instanceof Association) {
				res = ((Classifier)context).getAssociations().contains(toFilter);
			} else if (toFilter instanceof Artifact && context instanceof ExecutionEnvironment) {
				for (final Deployment deployment : ((ExecutionEnvironment)context).getDeployments()) {
					if (deployment.getSuppliers().contains(toFilter)) {
						res = true;
					}
				}
			} else if (toFilter instanceof ExecutionEnvironment && context instanceof Artifact) {
				for (final Deployment deployment : ((ExecutionEnvironment)toFilter).getDeployments()) {
					if (deployment.getSuppliers().contains(context)) {
						res = true;
					}
				}
			} else if (toFilter instanceof PackageableElement && context instanceof Artifact) {
				res = ((Artifact)context).getManifestations().contains(toFilter);
				for (final Manifestation manifestation : ((Artifact)context).getManifestations()) {
					if (manifestation.getTargets().contains(toFilter)) {
						res = true;
					}
				}
			} else if (toFilter instanceof Artifact && context instanceof PackageableElement) {
				res = ((Artifact)toFilter).getManifestations().contains(context);
				for (final Manifestation manifestation : ((Artifact)toFilter).getManifestations()) {
					if (manifestation.getTargets().contains(context)) {
						res = true;
					}
				}
			} else if (toFilter instanceof Feature) {
				res = isRelated(toFilter.eContainer(), context);
			} else if (toFilter instanceof Classifier) {
				if (context == toFilter) {
					return false;
				}
				res = context == toFilter;

				if (context instanceof EncapsulatedClassifier && toFilter instanceof EncapsulatedClassifier) {
					if (!res) {
						if (((EncapsulatedClassifier)context).getOwnedElements().contains(toFilter)) {
							res = true;
						} else {
							for (final Port portContext : ((EncapsulatedClassifier)context).getOwnedPorts()) {
								if (portIsRelated(toFilter, portContext)) {
									res = true;
									break;
								}
							}
						}
					}
				}

				if (context instanceof EncapsulatedClassifier && toFilter instanceof Property) {
					if (!res) {
						if (((EncapsulatedClassifier)context).getOwnedAttributes().contains(toFilter)) {
							res = true;
						}
					}
				}

				// is it a generalization end
				if (!res) {
					for (final Generalization generalization : ((Classifier)context).getGeneralizations()) {
						if (generalization.getGeneral() == toFilter) {
							res = true;
							break;
						}
					}
				}
				// is it a generalization opposite end
				if (!res) {
					for (final Generalization generalization : ((Classifier)toFilter).getGeneralizations()) {
						if (generalization.getGeneral() == context) {
							res = true;
							break;
						}
					}
				}
				if (toFilter instanceof NamedElement && context instanceof NamedElement) {
					// is it a dependency end
					if (!res) {
						for (final Dependency dependency : ((NamedElement)context).getClientDependencies()) {
							if (dependency.getClients().contains(toFilter)
									|| dependency.getSuppliers().contains(toFilter)) {
								res = true;
								break;
							}
						}
					}
				}
				if (context instanceof NamedElement && toFilter instanceof NamedElement) {
					// is it a dependency end
					if (!res) {
						for (final Dependency dependency : ((NamedElement)toFilter).getClientDependencies()) {
							if (dependency.getClients().contains(context)
									|| dependency.getSuppliers().contains(context)) {
								res = true;
								break;
							}
						}
					}
				}
				if (toFilter instanceof Interface && context instanceof Class) {
					// is it a realization end
					if (!res) {
						for (final InterfaceRealization realization : ((Class)context)
								.getInterfaceRealizations()) {
							if (realization.getContract() == toFilter) {
								res = true;
								break;
							}
						}
					}
				}
				if (context instanceof Interface && toFilter instanceof Class) {
					// is it a realization end
					if (!res) {
						for (final InterfaceRealization realization : ((Class)toFilter)
								.getInterfaceRealizations()) {
							if (realization.getContract() == context) {
								res = true;
								break;
							}
						}
					}
				}

				// is it an association end
				if (!res) {
					final List<Association> toFilterAsso = ((Classifier)toFilter).getAssociations();
					final List<Association> contextAsso = ((Classifier)context).getAssociations();
					for (final Association association : toFilterAsso) {
						if (contextAsso.contains(association)) {
							res = true;
							break;
						}
					}
				}
			} else if (toFilter instanceof Package) {
				for (final EObject content : toFilter.eContents()) {
					if (isRelated(content, context)) {
						res = true;
						break;
					}
				}
			}
		} else if (context instanceof Package) {
			if (toFilter instanceof Package) {
				res = ((Package)context).getNestedPackages().contains(toFilter)
						|| ((Package)context).getImportedPackages().contains(toFilter);
				for (final PackageImport packageImport : ((Package)context).getPackageImports()) {
					if (packageImport.getImportedPackage().equals(toFilter)) {
						res = true;
					}
				}
			} else {
				res = ((Package)context).getOwnedElements().contains(toFilter);
			}
		} else if (context instanceof Port) {
			res = portIsRelated(toFilter, (Port)context);
		}

		return res;
	}

	/**
	 * Check if an element is related to a context.
	 *
	 * @param toFilter
	 *            the candidate to check for relation
	 * @param context
	 *            the classifier context object
	 * @return True if element is a description
	 */
	public boolean isRelated(EObject toFilter, List<EObject> context) {
		boolean related = false;
		for (final EObject eObject : context) {
			related = isRelated(toFilter, eObject);
			if (related) {
				break;
			}
		}
		return related;
	}

	/**
	 * Check if a semantic element can be represented in a given container view.
	 *
	 * @param container
	 *            Semantic container
	 * @param semanticElement
	 *            Element to test
	 * @param containerView
	 *            Container view
	 * @return True if element is valid for the current container view
	 */
	public boolean isValidElementForContainerView(final Element container, final Element semanticElement,
			final DSemanticDecorator containerView) {
		final Session session = SessionManager.INSTANCE.getSession(container);

		// Get all available mappings applicable for the selected element in the
		// current container
		final List<DiagramElementMapping> semanticElementMappings = getMappings(semanticElement,
				containerView, session);

		return semanticElementMappings.size() > 0;
	}

	/**
	 * Mark for auto size.
	 *
	 * @param any
	 *            Any
	 * @return the given auto sized object
	 */
	public EObject markForAutosize(EObject any) {
		return UIServices.INSTANCE.markForAutosize(any);
	}

	private boolean portIsRelated(EObject toFilter, Port portContext) {

		if (portContext == toFilter) {
			return false;
		}

		if (toFilter instanceof Port) {
			final List<ConnectorEnd> ends = portContext.getEnds();
			for (final ConnectorEnd portEnd : ends) {
				final EObject eContainer = portEnd.eContainer();
				if (eContainer instanceof Connector) {
					final Connector connector = (Connector)eContainer;
					final EList<ConnectorEnd> connectorEnds = connector.getEnds();
					for (final ConnectorEnd connectorEnd : connectorEnds) {
						if (connectorEnd.getRole() != null && connectorEnd.getRole().equals(toFilter)) {
							return true;
						}
					}
				}
			}
		} else if (toFilter instanceof EncapsulatedClassifier) {
			final List<Port> ownedPortsToFilter = ((EncapsulatedClassifier)toFilter).getOwnedPorts();
			for (final Port portToFilter : ownedPortsToFilter) {
				if (portIsRelated(portToFilter, portContext)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Generic service used to process treatments on a reconnect The processing has to be defined by
	 * overriding the corresponding caseXXX.
	 *
	 * @param context
	 *            Element attached to the existing edge
	 * @param edgeView
	 *            Represents the graphical new edge
	 * @param sourceView
	 *            Represents the graphical element pointed by the edge before reconnecting
	 * @param targetView
	 *            Represents the graphical element pointed by the edge after reconnecting
	 * @param source
	 *            Represents the semantic element pointed by the edge before reconnecting
	 * @param target
	 *            Represents the semantic element pointed by the edge after reconnecting
	 * @return the Element attached to the edge once it has been modified
	 */
	public Element reconnectEdge(Element context, DEdge edgeView, EdgeTarget sourceView,
			EdgeTarget targetView, Element source, Element target) {
		final ReconnectSwitch reconnectService = new ReconnectSwitch();

		// The edge view represents the new graphical edge
		// with testing of its source and target nodes we can
		// know if the user reconnected the source or the target of the edge
		if (edgeView.getSourceNode().equals(targetView)) {
			reconnectService.setReconnectKind(ReconnectSwitch.RECONNECT_SOURCE);
		} else {
			reconnectService.setReconnectKind(ReconnectSwitch.RECONNECT_TARGET);
		}
		reconnectService.setOldPointedElement(source);
		reconnectService.setNewPointedElement(target);
		return reconnectService.doSwitch(context);
	}

	/**
	 * Show the given semantic element on the diagram. If the element already exists but is hidden juste
	 * reveal it, otherwise create a new view.
	 *
	 * @param semanticElement
	 *            Semantic element
	 * @param containerView
	 *            Container view
	 * @param session
	 *            Session
	 */
	protected void showView(final EObject semanticElement, final DSemanticDecorator containerView,
			final Session session, String containerViewVariable) {
		// Check if the dropped element already exists in the diagram but is hidden
		final List<DDiagramElement> hiddenDiagramElements = getHiddenExistingDiagramElements(semanticElement,
				containerView);
		if (!hiddenDiagramElements.isEmpty()) {
			// Just reveal the elements
			for (final DDiagramElement existingDiagramElement : hiddenDiagramElements) {
				HideFilterHelper.INSTANCE.reveal(existingDiagramElement);
			}
		} else {
			// Else create a new element
			// Create the view for the dropped element
			createView(semanticElement, containerView, session, containerViewVariable);
		}
	}
}
