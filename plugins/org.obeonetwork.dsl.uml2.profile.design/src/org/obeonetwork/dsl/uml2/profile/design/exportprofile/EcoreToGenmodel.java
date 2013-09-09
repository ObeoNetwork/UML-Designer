package org.obeonetwork.dsl.uml2.profile.design.exportprofile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.importer.ecore.EcoreImporter;
import org.obeonetwork.dsl.uml2.profile.design.services.GenericUMLProfileTools;

/**
 * This class provide a method to generate a genmodel from an ecore model.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA
 */
public class EcoreToGenmodel {

	/**
	 * Create an instance of this class.
	 */
	public EcoreToGenmodel() {
		super();
	}

	/**
	 * This method generate a genmodel from an ecore model.
	 * 
	 * @param ecoreResource
	 *            of an ecore model, ecoreResource.
	 * @return a GenModel for the input ecore model
	 */
	public GenModel ecoreToGenmodel(final Resource ecoreResource) {
		final BasicMonitor basicMonitor = new BasicMonitor();
		basicMonitor.beginTask("Creating Genmodel", 4);

		final IFile ecoreProfileIFile = GenericUMLProfileTools
				.resourceToIFile(ecoreResource);
		final EPackage ePckageProflie = (EPackage) ecoreResource.getContents()
				.get(0);

		final EcoreImporter modelImporter = new EcoreImporter();
		modelImporter.setModelFile(ecoreProfileIFile);
		modelImporter.setModelLocation(ecoreResource.getURI().toString());
		modelImporter.setGenModelFileName(modelImporter.computeDefaultGenModelFileName());
		final IPath genModelContainerPath = ecoreProfileIFile.getParent()
				.getFullPath();
		modelImporter.setGenModelContainerPath(genModelContainerPath);
		final IPath genModelProjectLocation = ecoreProfileIFile.getParent()
				.getParent().getFullPath();
		modelImporter.setGenModelProjectLocation(genModelProjectLocation);
		modelImporter.addGenModelToResource(true);

		try {
			modelImporter.computeEPackages(basicMonitor);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		for (EPackage ePackage : modelImporter.getEPackages()) {
			modelImporter.getEPackageConvertInfo(ePackage).setConvert(
					ePckageProflie.getName().equals(ePackage.getName())
							&& ePckageProflie.getNsPrefix().equals(ePackage.getNsPrefix()));
		}
		
		modelImporter.adjustEPackages(basicMonitor);

		for (GenModel genModel : modelImporter.getExternalGenModels()) {
			final GenPackage genPackage = genModel.getGenPackages().get(0);
				 modelImporter.getReferencedGenPackages().add(genPackage);
				modelImporter.getReferenceGenPackageConvertInfo(genPackage).setValidReference(true);
		}
		modelImporter.prepareGenModelAndEPackages(basicMonitor);
		try {
			modelImporter.saveGenModelAndEPackages(basicMonitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelImporter.getGenModel();
	}


}
