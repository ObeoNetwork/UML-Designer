/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.tests.plugin.manual;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.dsl.uml2.design.api.services.ReusedDescriptionServices;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ServiceTestsUtils {
	private static final String VP_EXTEND = "Extend";

	private static final String VP_REUSED = "Reused";

	private static final String VP_REVIEW = "Review";

	private static final String VP_DESIGN = "Design";

	private static final String VP_CAPTURE = "Capture";

	private static final String VP_DASHBOARD = "Dashboard";

	private static final String UML_VP_URI = "viewpoint:/org.obeonetwork.dsl.uml2.design/";

	public static Set<String> acceleoWhiteList = Sets.newHashSet("->", "eContainer", "toLowerFirst",
		"toUpperFirst", "not", "eClass", "eAllContents", "eContents", "equalsIgnoreCase");

	public static Set<String> umlWhiteList = Sets.newHashSet(getAllUmlOperations());

	public static Set<String> siriusWhiteList = Sets.newHashSet("getTarget");

	/**
	 * Services declared in the java code but not used in the odesign. These services are used by java
	 * code declaring at runtime new sirius tools.
	 * 
	 * @see ReusedDescriptionServices#getContainerView(org.eclipse.uml2.uml.Element,
	 *      org.eclipse.sirius.diagram.DDiagramElement)
	 * @see ReusedDescriptionServices#getContainerView(org.eclipse.uml2.uml.Element,
	 *      org.eclipse.sirius.diagram.DDiagramElementContainer)
	 * @see ReusedDescriptionServices#getContainerView(org.eclipse.uml2.uml.Element,
	 *      org.eclipse.sirius.diagram.DSemanticDiagram)
	 */
	public static Set<String> runtimeWhiteList = Sets.newHashSet("getContainerView");

	public static void collectDeclaredServicesFromUmlDesignerViewpoints(Set<Method> allDeclaredServices) {
	collectDeclaredServicesFromDesignerViewpoints(allDeclaredServices, UML_VP_URI, VP_CAPTURE);
	collectDeclaredServicesFromDesignerViewpoints(allDeclaredServices, UML_VP_URI, VP_DESIGN);
	collectDeclaredServicesFromDesignerViewpoints(allDeclaredServices, UML_VP_URI, VP_REVIEW);
	collectDeclaredServicesFromDesignerViewpoints(allDeclaredServices, UML_VP_URI, VP_EXTEND);
	collectDeclaredServicesFromDesignerViewpoints(allDeclaredServices, UML_VP_URI, VP_REUSED);
	collectDeclaredServicesFromDesignerViewpoints(allDeclaredServices, UML_VP_URI, VP_DASHBOARD);
	}

	public static void collectDeclaredServicesFromDesignerViewpoints(Set<Method> allDeclaredServices,
		String vpUri, String vpName) {
	Set<JavaExtension> allExtensions = new HashSet<JavaExtension>();
	collectJavaExtensionsFromDesignerViewpoints(vpUri, vpName, allExtensions);
	for (JavaExtension extension : allExtensions) {
		try {
		@SuppressWarnings("rawtypes")
		Class clazz = Class.forName(extension.getQualifiedClassName());
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.getModifiers() == Modifier.PUBLIC) {
			allDeclaredServices.add(method);
			}

		}
		} catch (ClassNotFoundException e) {
		// Nothing to do, this is checked by the {@link JavaExtensionTests}
		}
	}
	}

	public static void collectDeclaredServicesFromUmlDesignerViewpoint(Set<Method> allDeclaredServices,
		String viewpointName) {
	Set<JavaExtension> allExtensions = new HashSet<JavaExtension>();
	collectJavaExtensionsFromUmlDesignerViewpoint(allExtensions, viewpointName);
	for (JavaExtension extension : allExtensions) {
		try {
		@SuppressWarnings("rawtypes")
		Class clazz = Class.forName(extension.getQualifiedClassName());
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.getModifiers() == Modifier.PUBLIC) {
			allDeclaredServices.add(method);
			}
		}
		} catch (ClassNotFoundException e) {
		// Nothing to do, this is checked by the {@link JavaExtensionTests}
		}
	}
	}

	public static void collectServicesFromDesignerViewpoint(Set<Method> allServices, String vpName,
		String vpUri) {
	Set<JavaExtension> allExtensions = new HashSet<JavaExtension>();
	collectJavaExtensionsFromDesignerViewpoints(vpUri, vpName, allExtensions);
	for (JavaExtension extension : allExtensions) {
		try {
		@SuppressWarnings("rawtypes")
		Class clazz = Class.forName(extension.getQualifiedClassName());
		for (Method method : clazz.getMethods()) {
			if (method.getModifiers() == Modifier.PUBLIC) {
			allServices.add(method);
			}
		}
		} catch (ClassNotFoundException e) {
		// Nothing to do, this is checked by the {@link JavaExtensionTests}
		}
	}
	}

	public static void collectServicesFromUmlDesignerViewpoint(Set<Method> allServices,
		String viewpointName) {
	collectServicesFromDesignerViewpoint(allServices, viewpointName, UML_VP_URI);
	}

	protected static void collectServiceExpressionFromDesignerViewpoints(
		Set<InterpretedExpression> allExpressions, String vpUri, String vpName) {
	Viewpoint structural = ViewpointRegistry.getInstance().getViewpoint(URI.createURI(vpUri + vpName));
	collectServiceExpressionsFromViewpoint(structural, allExpressions);
	}

	private static void collectServiceExpressionFromUmlDesignerViewpoints(
		Set<InterpretedExpression> allExpressions, String vpName) {
	collectServiceExpressionFromDesignerViewpoints(allExpressions, UML_VP_URI, vpName);
	}

	private static void collectServiceExpressionsFromViewpoint(Viewpoint structural,
		Set<InterpretedExpression> allExpressions) {
	Iterator<EObject> it = structural.eAllContents();
	while (it.hasNext()) {
		EObject underTest = it.next();
		for (EAttribute attr : underTest.eClass().getEAllAttributes()) {
		if (attr.getEType() == DescriptionPackage.eINSTANCE.getInterpretedExpression()) {
			Object expr = underTest.eGet(attr);

			if (expr instanceof String && ((String)expr).length() > 0) {
			System.out.println(expr + " : " + isService(((String)expr)));
			if (isService(((String)expr)))
				allExpressions.add(new InterpretedExpression((String)expr, underTest, attr));
			}
		}
		}
	}
	}

	private static boolean isService(String expr) {
	if (isServiceInterpreterCall(expr) || isMTLServiceCall(expr) || isAQLServiceCall(expr)) {
		return true;
	}
	return false;
	}

	private static String getMTLServiceCall(String expr) {
	if (expr.startsWith("[") && expr.endsWith("/]")) {
		String[] splitExpr = expr.replace("[", "").replace("/]", "").split("\\.");
		for (String exprPart : splitExpr) {
		if (exprPart.matches("\\w+ *\\(.*") && !exprPart.startsWith("ocl")
			&& !containsAcceleoKeywords(exprPart)
			&& !containsUmlOperations(getServiceName(exprPart))
			&& !containsSiriusOperations(getServiceName(exprPart))) {
			return getServiceName(exprPart);
		}
		}
	}
	return null;
	}

	private static String getAQLServiceCall(String expression) {
	if (expression.startsWith("aql:")) {
		String[] splitExpr = expression.split("\\.");
		for (String exprPart : splitExpr) {
		if (exprPart.matches("\\w+ *\\(.*") && !exprPart.startsWith("ocl")
			&& !containsAcceleoKeywords(exprPart)
			&& !containsUmlOperations(getServiceName(exprPart))
			&& !containsSiriusOperations(getServiceName(exprPart))) {
			return getServiceName(exprPart);
		}
		}
	}
	return null;
	}

	private static boolean containsAcceleoKeywords(String expression) {
	for (String keywords : acceleoWhiteList) {
		if (expression.contains(keywords))
		return true;
	}
	return false;
	}

	private static boolean containsUmlOperations(String expression) {
	for (String keywords : umlWhiteList) {
		if (expression.equals(keywords))
		return true;
	}
	return false;
	}

	private static boolean containsSiriusOperations(String expression) {
	for (String keywords : siriusWhiteList) {
		if (expression.equals(keywords))
		return true;
	}
	return false;
	}

	private static boolean isMTLServiceCall(String expr) {
	return getMTLServiceCall(expr) != null;
	}

	private static boolean isAQLServiceCall(String expr) {
	return getAQLServiceCall(expr) != null;
	}

	private static boolean isServiceInterpreterCall(String expr) {
	return getServiceInterpreterCall(expr) != null;
	}

	public static String getServiceCall(String expression) {
	if (isServiceInterpreterCall(expression)) {
		return getServiceInterpreterCall(expression);
	}
	if (isMTLServiceCall(expression)) {
		return getMTLServiceCall(expression);
	}
	if (isAQLServiceCall(expression)) {
		return getAQLServiceCall(expression);
	}
	return null;
	}

	private static String getServiceInterpreterCall(String expression) {
	if (expression.startsWith("service:")) {
		String expr = expression.substring("service:".length());
		if (expression.contains(".")) {
		String[] splitExpr = expr.split("\\.");
		for (String exprPart : splitExpr) {
			String service = getServiceName(exprPart);
			if (!service.equals(exprPart) && !containsUmlOperations(service)
				&& !containsSiriusOperations(service)) {
			return service;
			}
		}

		String service = splitExpr[splitExpr.length - 1];
		if (!containsUmlOperations(service) && !containsSiriusOperations(service)) {
			return getServiceName(service);
		}
		} else if (!containsUmlOperations(expr) && !containsSiriusOperations(expr)) {
		return getServiceName(expr);
		}
	}
	return null;
	}

	private static String getServiceName(String expr) {
	if (expr.contains("(")) {
		return expr.substring(0, expr.indexOf("("));
	}
	return expr;
	}

	public static void collectServiceExpressionFromUmlDesignerViewpoints(
		Set<InterpretedExpression> allServiceExpressions) {
	collectServiceExpressionFromUmlDesignerViewpoints(allServiceExpressions, VP_CAPTURE);
	collectServiceExpressionFromUmlDesignerViewpoints(allServiceExpressions, VP_DESIGN);
	collectServiceExpressionFromUmlDesignerViewpoints(allServiceExpressions, VP_REVIEW);
	collectServiceExpressionFromUmlDesignerViewpoints(allServiceExpressions, VP_EXTEND);
	collectServiceExpressionFromUmlDesignerViewpoints(allServiceExpressions, VP_REUSED);
	collectServiceExpressionFromUmlDesignerViewpoints(allServiceExpressions, VP_DASHBOARD);
	}

	public static void collectServiceExpressionFromUmlDesignerViewpoint(
		Set<InterpretedExpression> allServiceExpressions, String viewpointName) {
	collectServiceExpressionFromUmlDesignerViewpoints(allServiceExpressions, viewpointName);
	}

	public static void collectJavaExtensionsFromUmlDesignerViewpoints(Set<JavaExtension> allExtensions) {
	collectJavaExtensionsFromUmlDesignerViewpoints(VP_CAPTURE, allExtensions);
	collectJavaExtensionsFromUmlDesignerViewpoints(VP_DESIGN, allExtensions);
	collectJavaExtensionsFromUmlDesignerViewpoints(VP_REVIEW, allExtensions);
	collectJavaExtensionsFromUmlDesignerViewpoints(VP_EXTEND, allExtensions);
	collectJavaExtensionsFromUmlDesignerViewpoints(VP_REUSED, allExtensions);
	collectJavaExtensionsFromUmlDesignerViewpoints(VP_DASHBOARD, allExtensions);
	}

	public static void collectJavaExtensionsFromUmlDesignerViewpoint(Set<JavaExtension> allExtensions,
		String viewpointName) {
	collectJavaExtensionsFromUmlDesignerViewpoints(viewpointName, allExtensions);
	}

	private static void collectJavaExtensionsFromUmlDesignerViewpoints(String vpName,
		Set<JavaExtension> allExtensions) {
	collectJavaExtensionsFromDesignerViewpoints(UML_VP_URI, vpName, allExtensions);
	}

	protected static void collectJavaExtensionsFromDesignerViewpoints(String vpUri, String vpName,
		Set<JavaExtension> allExtensions) {
	Viewpoint structural = ViewpointRegistry.getInstance().getViewpoint(URI.createURI(vpUri + vpName));
	collectJavaExtensionsFromViewpoint(structural, allExtensions);
	}

	private static void collectJavaExtensionsFromViewpoint(Viewpoint structural,
		Set<JavaExtension> allExtensions) {
	allExtensions.addAll(structural.getOwnedJavaExtensions());
	}

	private static List<String> getAllUmlOperations() {
	// Obtain a new resource set
	ResourceSet resSet = new ResourceSetImpl();

	// Create a resource
	Resource res = resSet.getResource(URI.createURI("http://www.eclipse.org/uml2/2.0.0/UML"), true);
	EObject root = res.getContents().get(0);

	// Get all eOperations
	List<EObject> umlOperations = Lists.newArrayList();
	Iterators.addAll(umlOperations,
		Iterators.filter(root.eAllContents(), Predicates.instanceOf(EOperation.class)));

	// Get eOperation names
	List<String> result = Lists.newArrayList();
	for (EObject umlOperation : umlOperations) {
		result.add(((EOperation)umlOperation).getName());
	}
	return result;
	}
}
