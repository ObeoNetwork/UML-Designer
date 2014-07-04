/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.services;


/**
 * Class used to debug
 * 
 * @author Stephane Thibaudeau <stephane.thibaudeau@obeo.fr>
 */
public class DebugServices {

	// /**
	// * Outputs information on the parameter and acceleo variables available
	// *
	// * @param context
	// * Object we wish to debug
	// * @return The unmodified parameter
	// */
	// public Element traceWithVariables(ENode context) {
	// System.out.println("$self : " + context);
	// EObject interpreterContext = getAnEObjectFrom(context);
	// if (interpreterContext != null) {
	// IInterpreter interpreter = InterpreterUtil.getInterpreter(interpreterContext);
	// for (String variableName : interpreter.getVariables().keySet()) {
	// System.out.println("$" + variableName + " : " + interpreter.getVariables().get(variableName));
	// }
	// }
	// System.out.println("--------------------");
	// return context;
	// }
	//
	// /**
	// * Extracts an EObject from an ENode instance
	// *
	// * @param context
	// * ENode instance
	// * @return an EObject if one has been found
	// */
	// private EObject getAnEObjectFrom(ENode context) {
	// EObject result = null;
	// try {
	// if (context.isEObject()) {
	// return context.getEObject();
	// } else if (context.isList() && context.getList().size() > 0) {
	// // We search into the list elements
	// for (Object elem : context.getList().asList()) {
	// if (elem instanceof ENode) {
	// ENode enode = (ENode)elem;
	// if (enode.isEObject()) {
	// return enode.getEObject();
	// }
	// }
	// }
	// // If we have found nothing, we search if some elements of the list are a list themselves
	// for (Object elem : context.getList().asList()) {
	// if (elem instanceof ENode) {
	// ENode enode = (ENode)elem;
	// if (enode.isList()) {
	// EObject tempResult = getAnEObjectFrom(enode);
	// if (tempResult != null) {
	// return tempResult;
	// }
	// }
	// }
	// }
	// }
	// } catch (ENodeCastException e) {
	// }
	// return result;
	// }
}
