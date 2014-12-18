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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Sets;

public class ServiceTestsUtils {
	private static Set<String> acceleoWhiteList = Sets.newHashSet("->", "eContainer", "toLowerFirst",
			"toUpperFirst", "not ", "eClass");

	private static Set<String> umlWhiteList = Sets.newHashSet("getStereotypeApplications",
			"getNearestPackage", "getStereotype","getAppliedStereotypes");

	public static void collectDeclaredServicesFromUmlDesignerViewpoints(Set<Method> allDeclaredServices) {
		Set<JavaExtension> allExtensions = new HashSet<JavaExtension>();
		collectJavaExtensionsFromUmlDesignerViewpoints(allExtensions);
		for (JavaExtension extension : allExtensions) {
			try {
				@SuppressWarnings("rawtypes")
				Class clazz = Class.forName(extension.getQualifiedClassName());
				for (Method method : clazz.getDeclaredMethods()) {
					allDeclaredServices.add(method);
				}
			} catch (ClassNotFoundException e) {
				// Nothing to do, this is checked by the {@link JavaExtensionTests}
			}
		}
	}

	private static void collectServiceExpressionFromUmlDesignerViewpoints(
			Set<InterpretedExpression> allExpressions, String vpName) {
		Viewpoint structural = ViewpointRegistry.getInstance().getViewpoint(
				URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/" + vpName));
		collectServiceExpressionsFromViewpoint(structural, allExpressions);
	}

	private static void collectServiceExpressionsFromViewpoint(Viewpoint structural,
			Set<InterpretedExpression> allExpressions) {
		Iterator<EObject> it = structural.eAllContents();
		while (it.hasNext()) {
			EObject underTest = it.next();
			for (EAttribute attr : underTest.eClass().getEAllAttributes()) {
				if (attr.getEType() == DescriptionPackage.eINSTANCE.getInterpretedExpression()) {
					Object expr = underTest.eGet(attr);
					if (expr instanceof String && ((String)expr).length() > 0 && isService(((String)expr))) {
						allExpressions.add(new InterpretedExpression((String)expr, underTest, attr));
					}
				}
			}
		}
	}

	private static boolean isService(String expr) {
		if (isServiceInterpreterCall(expr) || isAcceleoServiceCall(expr)) {
			return true;
		}
		return false;
	}

	private static String getAcceleoServiceCall(String expr) {
		if (expr.startsWith("[") && expr.endsWith("/]")) {
			String[] splitExpr = expr.split("\\.");
			for (String exprPart : splitExpr) {
				if (exprPart.matches("\\w+ *\\([^\\)]*\\).*") && !exprPart.startsWith("ocl")
						&& !containsAcceleoKeywords(exprPart) && !containsUmlOperations(exprPart)) {
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
			if (expression.contains(keywords))
				return true;
		}
		return false;
	}

	private static boolean isAcceleoServiceCall(String expr) {
		return getAcceleoServiceCall(expr) != null;
	}

	private static boolean isServiceInterpreterCall(String expr) {
		return getServiceInterpreterCall(expr) != null;
	}

	public static String getServiceCall(String expression) {
		if (isServiceInterpreterCall(expression)) {
			return getServiceInterpreterCall(expression);
		}
		if (isAcceleoServiceCall(expression)) {
			return getAcceleoServiceCall(expression);
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
					if (!service.equals(exprPart) && !containsUmlOperations(service)) {
						return service;
					}
				}

				if (!containsUmlOperations(splitExpr[splitExpr.length - 1])) {
					return getServiceName(splitExpr[splitExpr.length - 1]);
				}
			}

			if (!containsUmlOperations(expr)) {
				return getServiceName(expr);
			}
		}
		return null;
	}

	private static String getServiceName(String expr) {
		if (expr.contains("(") && expr.contains(")")) {
			return expr.substring(0, expr.indexOf("("));
		}
		return expr;
	}

	public static void collectServiceExpressionFromUmlDesignerViewpoints(
			Set<InterpretedExpression> allServiceExpressions) {
		collectServiceExpressionFromUmlDesignerViewpoints(allServiceExpressions, "Capture");
		collectServiceExpressionFromUmlDesignerViewpoints(allServiceExpressions, "Design");
		collectServiceExpressionFromUmlDesignerViewpoints(allServiceExpressions, "Review");
		collectServiceExpressionFromUmlDesignerViewpoints(allServiceExpressions, "Extend");
	}

	public static void collectJavaExtensionsFromUmlDesignerViewpoints(Set<JavaExtension> allExtensions) {
		collectJavaExtensionsFromUmlDesignerViewpoints("Capture", allExtensions);
		collectJavaExtensionsFromUmlDesignerViewpoints("Design", allExtensions);
		collectJavaExtensionsFromUmlDesignerViewpoints("Review", allExtensions);
		collectJavaExtensionsFromUmlDesignerViewpoints("Extend", allExtensions);
	}

	private static void collectJavaExtensionsFromUmlDesignerViewpoints(String vpName,
			Set<JavaExtension> allExtensions) {
		Viewpoint structural = ViewpointRegistry.getInstance().getViewpoint(
				URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/" + vpName));
		collectJavaExtensionsFromViewpoint(structural, allExtensions);
	}

	private static void collectJavaExtensionsFromViewpoint(Viewpoint structural,
			Set<JavaExtension> allExtensions) {
		allExtensions.addAll(structural.getOwnedJavaExtensions());
	}
}
