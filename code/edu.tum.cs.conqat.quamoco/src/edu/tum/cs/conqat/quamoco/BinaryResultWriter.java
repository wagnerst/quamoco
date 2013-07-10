package edu.tum.cs.conqat.quamoco;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.commons.node.IConQATNode;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.core.logging.ConQATLoggingEvent;
import org.conqat.engine.core.logging.ELogLevel;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import de.quamoco.qm.DoubleInterval;
import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.FindingsMeasurementResult;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.NumberMeasurementResult;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelResult;


/**
 * {@ConQAT.Doc}
 * 
 * @author Florian Deissenboeck
 * @author $Author: heineman $
 * @version $Rev: 3646 $
 * @levd.rating YELLOW Hash: FB032160031FFA5756800A57F4D25DC5
 */
@AConQATProcessor(description = "This processor writes the eval result of all factors, measures, etc. to a binary file, read by the binary-result-reader.")
public class BinaryResultWriter extends ConQATProcessorBase {

	/** directory where to store it. */
	private String dir;

	/** name of the project */
	private String projectname;

	/**
	 * The quality model that is saved.
	 */
	protected QualityModel[] qualityModels;
	
	/** Result of the evaluation. */
	private QualityModelResult qualityModelResult;

	/**
	 * Name of the quality model
	 */
	protected String qualityModelName;

	/** Set input filename. */
	@AConQATParameter(name = "output-dir", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "Name of the output dir.")
	public void addOutputDir(
			@AConQATAttribute(name = "dir", description = "dir") String dir) {
		this.dir = dir;
	}

	/** Set input filename. */
	@AConQATParameter(name = "projectname", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "Name of the output dir.")
	public void addProjectName(
			@AConQATAttribute(name = "projectname", description = "projectname") String projectname) {
		this.projectname = projectname;
	}

	/** Set input filename. */
	@AConQATParameter(name = "qualitymodelname", minOccurrences = 0, maxOccurrences = 1, description = ""
			+ "Type name of the quality model.")
	public void setSystemType(
			@AConQATAttribute(name = "attr", description = "quality model name") String systemtype) {
		this.qualityModelName = systemtype;
	}

	/**
	 * Set the quality model.
	 */
	@AConQATParameter(name = "quality-model", minOccurrences = 0, maxOccurrences = 1, description = ""
			+ "The quality model.")
	public void setQualityModel(
			@AConQATAttribute(name = "model", description = "quality model") QualityModel[]  qm) {
		this.qualityModels = qm;
		
		for (Resource resource : qualityModels[0].eResource().getResourceSet()
				.getResources()) {
			EObject root = resource.getContents().get(0);
			if (root instanceof QualityModelResult) {
				qualityModelResult = (QualityModelResult) root;
			}
		}
	}

	/**
	 * Saves the model.
	 * 
	 * @throws ConQATException
	 */
	@Override
	public Object process() throws ConQATException {

		try {

			final File outputfile = new File(this.dir);
			outputfile.mkdirs();
			ObjectOutputStream fout = new ObjectOutputStream(
					new FileOutputStream(this.dir + "/"+projectname.replaceAll("[^a-zA-Z0-9]", "_")+".binaryresult"));
			
			// Version
			fout.writeInt(1);
			
			try {
				fout.writeObject(this.qualityModelName);
				fout.writeObject(this.projectname);
				
				for(MeasurementResult mr: qualityModelResult.getMeasurementResults()) {
					if(mr instanceof FindingsMeasurementResult) {
						FindingsMeasurementResult fmr = (FindingsMeasurementResult) mr;
						double findingcount = fmr.getCount();
						
						fout.writeObject("MEASURE-FINDINGS");
						fout.writeObject(mr.getResultsFrom().getDetermines().getQualifiedName());
						fout.writeDouble(findingcount);
					} else if(mr instanceof NumberMeasurementResult) {
						NumberMeasurementResult nmr = (NumberMeasurementResult) mr;
						DoubleInterval di = nmr.getValue();
						
						fout.writeObject("MEASURE-NUMBER");
						fout.writeObject(mr.getResultsFrom().getDetermines().getQualifiedName());
						fout.writeDouble(di.getLower());
						fout.writeDouble(di.getUpper());
					}
				}
				
				for(EvaluationResult er : qualityModelResult.getEvaluationResults()) {
					fout.writeObject("FACTOR");
					fout.writeObject(er.getResultsFrom().getEvaluates().getQualifiedName());
					fout.writeDouble(er.getValue().getLower());
					fout.writeDouble(er.getValue().getUpper());
				}
				
				// Signal end of file
				fout.writeObject("");


			} finally {
				fout.close();
			}
		} catch (IOException e) {
			getLogger().warn(e);
		}

		return this.qualityModels;
	}

}
