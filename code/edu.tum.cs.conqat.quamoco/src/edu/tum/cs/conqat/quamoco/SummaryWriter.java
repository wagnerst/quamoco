/*-------------------------------------------------------------------------+
|                                                                          |
| Copyright 2012 Technische Universitaet Muenchen and                      |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
| Licensed under the Apache License, Version 2.0 (the "License");          |
| you may not use this file except in compliance with the License.         |
| You may obtain a copy of the License at                                  |
|                                                                          |
|    http://www.apache.org/licenses/LICENSE-2.0                            |
|                                                                          |
| Unless required by applicable law or agreed to in writing, software      |
| distributed under the License is distributed on an "AS IS" BASIS,        |
| WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. |
| See the License for the specific language governing permissions and      |
| limitations under the License.                                           |
|                                                                          |
+-------------------------------------------------------------------------*/

package edu.tum.cs.conqat.quamoco;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.Factor;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.conqat.quamoco.qiesl.QPoints;

/**
 * {@ConQAT.Doc}
 * 
 * @author Florian Deissenboeck
 * @author $Author: heineman $
 * @version $Rev: 3646 $
 * @levd.rating YELLOW Hash: FB032160031FFA5756800A57F4D25DC5
 */
@AConQATProcessor(description = "This processor writes a summary of the "
		+ "analysis result to the disk.")
public class SummaryWriter extends ConQATProcessorBase {

	/** The scope of the evaluated system. */
	private IConQATNode scope;

	/** directory where to store it. */
	private String dir;

	/** name of the project */
	private String projectname;

	/** names of the factors, that are written as columns */
	private final List<String> resultColumns = new LinkedList<String>();

	/** type of system (java, c#, etc) */
	private String systemType;

	/**
	 * The quality model that is saved.
	 */
	protected QualityModel[] qualityModels;

	/** Result of the evaluation. */
	private QualityModelResult qualityModelResult;

	/** Set input filename. */
	@AConQATParameter(name = "output-dir", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "Name of the output dir.")
	public void addOutputDir(
			@AConQATAttribute(name = "dir", description = "dir") String dir) {
		this.dir = dir;
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "scope", description = "scope", minOccurrences = 0, maxOccurrences = 1)
	public void setScope(
			@AConQATAttribute(name = "scope", description = "scope") IConQATNode scope) {
		this.scope = scope;
	}

	/** Set input filename. */
	@AConQATParameter(name = "projectname", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "Name of the output dir.")
	public void addProjectName(
			@AConQATAttribute(name = "projectname", description = "projectname") String projectname) {
		this.projectname = projectname;
	}

	/** Set input filename. */
	@AConQATParameter(name = "systemtype", minOccurrences = 0, maxOccurrences = 1, description = ""
			+ "Type of system that is analyzed.")
	public void setSystemType(
			@AConQATAttribute(name = "attr", description = "system type") String systemtype) {
		this.systemType = systemtype;
	}

	/** Set input filename. */
	@AConQATParameter(name = "result", description = ""
			+ "Qualified name of the factor to be shown.")
	public void addResultProperty(
			@AConQATAttribute(name = "factor", description = "qualitfied name") String property) {
		this.resultColumns.add(property);
	}

	/**
	 * Set the quality model.
	 */
	@AConQATParameter(name = "quality-model", minOccurrences = 0, maxOccurrences = 1, description = ""
			+ "The quality model.")
	public void setQualityModel(
			@AConQATAttribute(name = "model", description = "quality model") QualityModel[] qm) {
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
			(new File(this.dir)).mkdirs();
			FileOutputStream fout = new FileOutputStream(this.dir
					+ "/summary.txt");
			try {

				/** output project name */
				String result = "<tr><td><a href=\"" + projectname
						+ "/quamoco-report/index.html\">" + projectname
						+ "</a></td>";

				if (systemType == null) {
					result += "<td>-</td>";
				} else {
					result += "<td>" + this.systemType + "</td>";
				}

				/** output status */
				if (this.qualityModels == null || this.scope == null) {
					result += "<td style=\"color:red;\">FAILED";
				} else {
					boolean yellow = isYellow();
					if (yellow) {
						result += "<td style=\"color:orange;\"><nobr>SEVERE WARNING</nobr>";
					} else {
						result += "<td style=\"color:green;\">SUCCEDED";
					}
				}
				result += "<br><span style=\"font-size:10pt;\"><nobr>"
						+ loggingSummary() + "</nobr></span></td>";

				Date startDate = getStartDate();

				DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				/** output date */
				result += "<td><nobr>" + (df.format(startDate))
						+ "</nobr></td>";

				/** duration */
				long millis = getExecutionTime();
				long sec = millis / 1000;
				long min = sec / 60;
				sec -= 60 * min;

				result += "<td><nobr>" + min + "m " + sec + "s"
						+ "</nobr></td>";

				/** output loc */
				result += outputLoC();

				/** output result */
				result += outputResultAsColumns();

				result += "</tr>";

				fout.write(result.getBytes());

			} finally {
				fout.close();
			}
		} catch (IOException e) {
			getLogger().warn(e);
		}

		return this.qualityModels;
	}

	/** Return the execution time */
	private long getExecutionTime() {
		return this.getProcessorInfo().getConfigurationInformation()
				.getExecutionTime();
	}

	/** Calculates the start date */
	private Date getStartDate() {
		long current = (new Date()).getTime();
		current -= getExecutionTime();
		return new Date(current);
	}

	/** Determines if one block failed with an error. */
	private boolean isYellow() {
		for (String processor : this.getProcessorInfo().getLogManager()
				.getLoggingProcessors()) {
			for (ConQATLoggingEvent event : this.getProcessorInfo()
					.getLogManager().getLogMessages(processor)) {
				if (event.getLevel() == ELogLevel.ERROR
						|| event.getLevel() == ELogLevel.FATAL) {
					String msg = String.valueOf(event.getMessage());
					if (!msg.contains("Could not create element for")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Determines if one block failed with an error.
	 */
	private String loggingSummary() {
		HashMap<ELogLevel, Integer> summary = new HashMap<ELogLevel, Integer>();
		for (String processor : this.getProcessorInfo().getLogManager()
				.getLoggingProcessors()) {
			for (ConQATLoggingEvent event : this.getProcessorInfo()
					.getLogManager().getLogMessages(processor)) {
				Integer val = summary.get(event.getLevel());
				if (val == null) {
					val = 0;
				}
				val++;
				summary.put(event.getLevel(), val);
			}
		}

		String res = "";

		{
			Integer val = summary.get(ELogLevel.FATAL);
			if (val == null) {
				val = 0;
			}
			res += "f: " + val;
		}
		{
			Integer val = summary.get(ELogLevel.ERROR);
			if (val == null) {
				val = 0;
			}
			res += " e: " + val;
		}
		{
			Integer val = summary.get(ELogLevel.WARN);
			if (val == null) {
				val = 0;
			}
			res += " w: " + val;
		}
		return res;
	}

	/**
	 * Output the Lines of Code
	 */
	private String outputLoC() {
		String result = "<td align=\"right\">";
		if (this.scope == null) {
			result += "-";
		} else {
			Object loc = scope.getValue("LoC");

			if (loc == null) {
				result += "-";
			} else if (loc instanceof Number) {
				long linesOfCode = ((Number) loc).longValue();
				DecimalFormat df = (DecimalFormat) NumberFormat
						.getInstance(Locale.GERMAN);
				df.applyPattern("#,###,##0");
				result += df.format(linesOfCode);
			} else {
				result += loc.toString();
			}
		}
		result += "</td>";
		return result;
	}

	/**
	 * Outputs each value of resultColumns as a column in the table
	 */
	protected String outputResultAsColumns() {
		String result = "";
		for (String propertyQN : this.resultColumns) {
			result += "<td><nobr>"; // style=\"font-size:10pt;\"
			if (this.qualityModels == null) {
				result += "-";
			} else {
				Factor factor = getFactor(propertyQN);
				if (factor == null) {
					result += "-";
				} else {
					EvaluationResult evaluationResult = QmModelUtils.getResult(
							qualityModelResult, factor);
					result += "school grade=" + EvaluationResultFormatter.format(evaluationResult.getSchoolGrade()) + 
							" value="+EvaluationResultFormatter.format(QPoints
							.valueOf(evaluationResult.getValue()));
				}
			}
			result += "</nobr></td>";
		}
		return result;
	}

	/** Returns the factor for the given name */
	private Factor getFactor(String qname) {
		for (QualityModel qualityModel : this.qualityModels) {
			TreeIterator<EObject> i = qualityModel.eAllContents();
			while (i.hasNext()) {
				EObject child = i.next();

				if (child instanceof Factor) {
					if (((Factor) child).getQualifiedName().equals(qname)) {
						return (Factor) child;
					}
				}
			}
		}
		return null;
	}

}
