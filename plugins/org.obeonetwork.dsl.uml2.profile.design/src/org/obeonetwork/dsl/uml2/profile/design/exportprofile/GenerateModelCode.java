package org.obeonetwork.dsl.uml2.profile.design.exportprofile;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;
import org.eclipse.emf.codegen.util.CodeGenUtil;

/**
 * his class provide a method to generate a Mode Code from a genmodel.
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr">mohamed-lamine.boukhanoufa@obeo.fr</a>
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
			e.printStackTrace();

		}
	}
}
