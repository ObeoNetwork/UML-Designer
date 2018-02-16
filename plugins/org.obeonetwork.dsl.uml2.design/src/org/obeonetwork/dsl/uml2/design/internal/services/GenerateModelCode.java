/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.services;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.obeonetwork.dsl.uml2.core.internal.services.LogServices;

/**
 * This class provide a method to generate a Mode Code from a genmodel.
 *
 * @author Mohamed-Lamine BOUKHANOUFA <a href="mailto:mohamed-lamine.boukhanoufa@obeo.fr" >mohamed-lamine.
 *         boukhanoufa@obeo.fr</a>
 */
public class GenerateModelCode {

	/**
	 * Create an instance of this class.
	 */
	public GenerateModelCode() {
	}

	/**
	 * Generates the Model Code from genmodel.
	 *
	 * @param genModel
	 *            model
	 */
	public void generateModelCode(final GenModel genModel) {
		final IProgressMonitor progressMonitor = new NullProgressMonitor();
		try {

			genModel.reconcile();
			genModel.setCanGenerate(true);
			final Generator gen = GenModelUtil.createGenerator(genModel);
			gen.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE,
					CodeGenUtil.EclipseUtil.createMonitor(progressMonitor, 3));
		} catch (final Exception e) {
			LogServices.INSTANCE.error("generateModelCode(" + genModel.getClass() //$NON-NLS-1$
			+ ") not handled", e); //$NON-NLS-1$

		}
	}
}
