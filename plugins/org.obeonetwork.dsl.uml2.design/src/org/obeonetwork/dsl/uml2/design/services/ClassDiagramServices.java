package org.obeonetwork.dsl.uml2.design.services;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ClassDiagramServices {
	final UMLFactory factory = UMLFactory.eINSTANCE;

	final UMLServices umlServices = new UMLServices();

	/**
	 * Get the type of the association source end.
	 * 
	 * @param association
	 *            Association
	 * @return Type of the source
	 */
	public Type getSourceType(Association association) {
		Property source = getSource(association);
		if (source != null)
			return source.getType();
		return null;
	}

	/**
	 * Get the type of the association target end.
	 * 
	 * @param association
	 *            Association
	 * @return Type of the target
	 */
	public Type getTargetType(Association association) {
		Property target = getTarget(association);
		if (target != null)
			return target.getType();
		return null;
	}

	public List<Type> getTypes(Association association) {
		List<Type> types = Lists.newArrayList();
		types.add(getSourceType(association));
		types.add(getTargetType(association));
		return types;
	}

	public void createAssociation(Package pkg, Classifier source, Classifier target) {
		System.out.println("Create asso");
	}

	public List<Property> getMemberEnds(Association association) {
		List<Property> ends = Lists.newArrayList();
		ends.add(association.getMemberEnds().get(0));
		ends.add(association.getMemberEnds().get(1));
		return ends;
	}

	/**
	 * Return the source of an association.
	 * 
	 * @param association
	 *            the {@link Association} context
	 * @return first end of the association
	 */
	public Property getSource(Association association) {
		if (association.getMemberEnds() != null && association.getMemberEnds().size() > 0) {
			return association.getMemberEnds().get(0);
		}
		return null;
	}

	/**
	 * Return the target of an association.
	 * 
	 * @param association
	 *            the {@link Association} context
	 * @return second end of the association
	 */
	public Property getTarget(Association association) {
		if (association.getMemberEnds() != null && association.getMemberEnds().size() > 1) {
			return association.getMemberEnds().get(1);
		}
		return null;
	}

	/**
	 * Get navigable owned end of an association
	 * 
	 * @param association
	 *            Association
	 * @return Association
	 */
	public List<Property> getNavigableOwnedEnds(Association association) {
		List<Property> ends = Lists.newArrayList();
		Property source = getSource(association);
		Property target = getTarget(association);
		if (source != null) {
			ends.add(source);
		}
		if (target != null) {
			ends.add(target);
		}
		return ends;
	}

	/**
	 * Get all available types in model.
	 * 
	 * @param pkg
	 *            Package
	 * @return All the available types
	 */
	public Set<Type> getAvailableTypes(Package pkg) {
		Set<Type> availableTypes = Sets.newHashSet();
		Set<Package> availablePackages = getAvailablePackages(pkg);
		for (Package availablePackage : availablePackages) {
			Set<Type> types = Sets.newHashSet(Iterables.filter(availablePackage.getOwnedTypes(),
					new Predicate<EObject>() {
						public boolean apply(EObject input) {
							return input instanceof Class || input instanceof Interface
									|| input instanceof DataType;
						}
					}));
			availableTypes.addAll(types);
		}
		return availableTypes;
	}

	/**
	 * Get all available packages in model.
	 * 
	 * @param pkg
	 *            Package
	 * @return All the available packages
	 */
	public Set<Package> getAvailablePackages(Package pkg) {
		Set<Package> packages = Sets.newHashSet();
		packages.add(pkg);
		for (Iterator<EObject> iterator = pkg.getModel().eAllContents(); iterator.hasNext();) {
			EObject eObject = iterator.next();
			if (eObject instanceof Package) {
				packages.add((Package)eObject);
				for (PackageImport packageImport : pkg.getPackageImports()) {
					packages.add(packageImport.getImportedPackage());
				}
			}
		}

		return packages;
	}

	private boolean isNavigable(Property property) {
		return property != null && property.isNavigable();
	}

	private boolean isComposite(Property property) {
		return property != null && property.isComposite();
	}

	private boolean isShared(Property property) {
		return property != null && umlServices.isShared(property);
	}

	public boolean targetIsNavigable(Association association) {
		Property target = getTarget(association);
		return isNavigable(target);
	}

	public boolean targetIsNavigableAndComposite(Association association) {
		Property target = getTarget(association);
		return isNavigable(target) && isComposite(target);
	}

	public boolean targetIsNavigableAndShared(Association association) {
		Property target = getTarget(association);
		return isNavigable(target) && isShared(target);
	}

	public boolean targetIsComposite(Association association) {
		Property target = getTarget(association);
		return isComposite(target);
	}

	public boolean targetIsShared(Association association) {
		Property target = getTarget(association);
		return isShared(target);
	}

	public boolean sourceIsNavigable(Association association) {
		Property source = getSource(association);
		return isNavigable(source);
	}

	public boolean sourceIsNavigableAndComposite(Association association) {
		Property source = getSource(association);
		return isNavigable(source) && isComposite(source);
	}

	public boolean sourceIsNavigableAndShared(Association association) {
		Property source = getTarget(association);
		return isNavigable(source) && isShared(source);
	}

	public boolean sourceIsComposite(Association association) {
		Property source = getSource(association);
		return isComposite(source);
	}

	public boolean sourceIsShared(Association association) {
		Property source = getSource(association);
		return isShared(source);
	}
}
