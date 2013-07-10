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

/*-----------------------------------------------------------------------+
 | edu.tum.cs.conqat.quamoco.inspection
 |                                                                       |
   $Id: ChecklistModelLoader.java 29845 2010-08-23 19:17:49Z niessner $            
 |                                                                       |
 | Copyright (c)  2004-2010 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package edu.tum.cs.conqat.inspection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import edu.tum.cs.conqat.commons.ConQATParamDoc;
import edu.tum.cs.conqat.commons.ConQATProcessorBase;
import edu.tum.cs.conqat.conqatdoc.ConQATDoc;
import edu.tum.cs.conqat.core.AConQATAttribute;
import edu.tum.cs.conqat.core.AConQATParameter;
import edu.tum.cs.conqat.core.AConQATProcessor;
import edu.tum.cs.conqat.core.ConQATException;
import edu.tum.cs.conqat.inspection.cm.Checklist;
import edu.tum.cs.conqat.inspection.cm.CmPackage;

/**
 * {@link ConQATDoc}
 * 
 * @author niessner
 * @author $Author: niessner $
 * @version $Rev: 29845 $
 * @levd.rating YELLOW Hash: 9FA9A93F3DB3FC20400DF7A21D990F69
 */
@AConQATProcessor(description = "This processor loads Checklist models "
		+ "from disk.")
public class ChecklistModelLoader extends ConQATProcessorBase {

	/** {@ConQAT.Doc} */
	private final List<String> inputDirs = new ArrayList<String>();

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = ConQATParamDoc.INPUT_NAME, minOccurrences = 1, description = ""
			+ "Directory which contains .cm files")
	public void addInputDir(
			@AConQATAttribute(name = "dir", description = "Directory which contains .cm files") String dir) {
		inputDirs.add(dir);
	}

	/** {@ConQAT.Doc} */
	public Checklist[] process() throws ConQATException {
		List<String> checklistPaths;
		try {
			checklistPaths = unzipChecklistModels();
		} catch (IOException e) {
			throw new ConQATException("Error unzipping checklists: "
					+ e.getMessage());
		}
		List<Checklist> checklists = resolveChecklists(checklistPaths);

		Checklist[] checklistArray = new Checklist[checklists.size()];
		int i = 0;
		for (Checklist checklist : checklists) {
			checklistArray[i] = checklist;
			i++;
		}

		return checklistArray;

	}

	/**
	 * Unzips all Checklist Models and extracts the actual checklists.
	 * 
	 * @return the absolute paths of found checklists.
	 */
	private List<String> unzipChecklistModels() throws IOException {
		List<String> checklistPaths = new ArrayList<String>();
		List<String> filenames = getFilenames();
		int i = 0;
		for (String zipPath : filenames) {
			BufferedInputStream bufInStream = new BufferedInputStream(
					new FileInputStream(zipPath));
			ZipInputStream zipInput = new ZipInputStream(bufInStream);
			String outputPath = (new File(zipPath)).getParent() + "\\Checklist"
					+ i + ".cl";
			checklistPaths.add(outputPath);
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(outputPath));
			ZipEntry entry = zipInput.getNextEntry();
			while (entry != null) {
				if (entry.getName().equals("Checklist")) {
					byte[] buffer = new byte[1024];
					int length = zipInput.read(buffer);
					while (length >= 0) {
						out.write(buffer, 0, length);
						length = zipInput.read(buffer);
					}
					i++;
					break;
				}
				entry = zipInput.getNextEntry();
			}
			out.close();
			zipInput.close();
		}
		return checklistPaths;
	}

	/**
	 * Finds all .cm files in the specified directory.
	 */
	private List<String> getFilenames() {
		List<String> filenames = new ArrayList<String>();
		FilenameFilter filter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".cm");
			}

		};
		for (String inputDir : inputDirs) {
			File directory = new File(inputDir);
			String[] foundNames = directory.list(filter);
			for (String foundName : foundNames) {
				filenames.add(directory.getAbsolutePath() + "\\" + foundName);
			}
		}
		return filenames;
	}

	/**
	 * Resolves all checklists from given checklist paths.
	 */
	private List<Checklist> resolveChecklists(List<String> checklistPaths) {
		// initialize model
		CmPackage.eINSTANCE.eClass();

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("cl",
				new XMIResourceFactoryImpl());
		ResourceSet resourceSet = new ResourceSetImpl();

		for (String checklistPath : checklistPaths) {
			resourceSet.getResource(URI.createFileURI(checklistPath), true);
			// clean up extracted checklist file
			(new File(checklistPath)).delete();
		}

		EcoreUtil.resolveAll(resourceSet);

		List<Checklist> checklists = new ArrayList<Checklist>();
		for (Resource resource : resourceSet.getResources()) {
			for (EObject eObject : resource.getContents()) {
				if (eObject instanceof Checklist) {
					checklists.add((Checklist) eObject);
				}
			}
		}

		return checklists;
	}

}
