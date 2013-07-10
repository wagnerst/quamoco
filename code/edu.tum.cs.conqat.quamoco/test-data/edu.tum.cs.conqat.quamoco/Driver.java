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

/*--------------------------------------------------------------------------+
   $Id: Driver.java 4974 2012-02-17 09:34:10Z lochmann $
 |                                                                          |
 | Copyright 2005-2009 Technische Universitaet Muenchen                     |
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
 +--------------------------------------------------------------------------*/
package edu.tum.cs.conqat.driver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import edu.tum.cs.commons.collections.HashedListMap;
import edu.tum.cs.commons.collections.PairList;
import edu.tum.cs.commons.options.AOption;
import edu.tum.cs.commons.string.StringUtils;
import edu.tum.cs.commons.system.PerformanceMonitor;
import edu.tum.cs.conqat.ConQATInfo;
import edu.tum.cs.conqat.bundle.BundleException;
import edu.tum.cs.conqat.bundle.BundleInfo;
import edu.tum.cs.conqat.bundle.BundlesConfiguration;
import edu.tum.cs.conqat.distributed.IRemoteWorker;
import edu.tum.cs.conqat.distributed.JobDistributorBase;
import edu.tum.cs.conqat.distributed.LocalJobDistributor;
import edu.tum.cs.conqat.distributed.RemoteJobDistributor;
import edu.tum.cs.conqat.distributed.RemoteWorker;
import edu.tum.cs.conqat.driver.declaration.BlockDeclaration;
import edu.tum.cs.conqat.driver.error.BlockFileException;
import edu.tum.cs.conqat.driver.error.DriverException;
import edu.tum.cs.conqat.driver.error.EDriverExceptionType;
import edu.tum.cs.conqat.driver.error.EnvironmentException;
import edu.tum.cs.conqat.driver.error.ErrorLocation;
import edu.tum.cs.conqat.driver.info.BlockInfo;
import edu.tum.cs.conqat.driver.instance.BlockInstance;
import edu.tum.cs.conqat.driver.instance.ExecutionContext;
import edu.tum.cs.conqat.driver.specification.BlockSpecification;
import edu.tum.cs.conqat.driver.specification.SpecificationLoader;
import edu.tum.cs.conqat.driver.util.PropertyUtils;

/**
 * This is ConQAT's main class. The driver calls the different steps of ConQAT
 * processing in the right order.
 * 
 * @author Florian Deissenboeck
 * @author Tilman Seifert
 * @author Benjamin Hummel
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating GREEN Hash: BE183F11613D1A56CCF793888C3AB837
 * 
 */
public class Driver extends BundleCommandLineBase {

	/** Name of the default config file. */
	private static final String DEFAULT_BLOCK_FILE_NAME = "conqat."
			+ ConQATInfo.BLOCK_FILE_EXTENSION;

	/** Suffix of the default properties file */
	public static final String DEFAULT_PROPERTIES_FILE_SUFFIX = ".properties";

	/** Logger */
	private static final Logger logger = Logger.getLogger(Driver.class);

	/**
	 * The logging configuration file. Null indicates to use the default
	 * location.
	 */
	private File loggingConfigFile = null;

	/** The block file or block name to be used use. */
	private String rootBlock = DEFAULT_BLOCK_FILE_NAME;

	/** dry run (= only check configuration) or real run? */
	private boolean dryRun = false;

	/**
	 * If true all block-specs are compiled, otherwise only those that are
	 * required are compiled.
	 */
	private boolean compileAll = false;

	/** If this is positive, the driver runs in worker mode. */
	private int workerPort = -1;

	/** The hosts used for remote calculation. */
	private final List<String> remoteHosts = new ArrayList<String>();

	/** File from which properties are read */
	private File propertiesFile;

	/**
	 * The instrumentation interface used (if nothing is set externally use the
	 * default).
	 */
	private ConQATInstrumentation instrumentation = new ConQATInstrumentation();

	/** Properties set from the command line. */
	private final HashedListMap<String, String> commandLineProperties = new HashedListMap<String, String>();

	/**
	 * Main method:
	 * <ol>
	 * <li>interpret command line,</li>
	 * <li>create the bundle configuration,</li>
	 * <li>trigger the execution,</li>
	 * <li>catch exceptions.</li>
	 * <ol>
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.initFromCommandLine(args);

		DriverUtils.initLogger(driver.loggingConfigFile);
		logger.info("ConQAT " + ConQATInfo.DIST_VERSION + " (core "
				+ ConQATInfo.CORE_VERSION + ") running on Java "
				+ System.getProperty("java.version") + " ["
				+ System.getProperty("java.vm.name") + " "
				+ System.getProperty("java.vm.version") + "]");

		try {
			BundlesConfiguration bundleConfig = driver.loadBundles();
			if (driver.workerPort > 0) {
				driver.provideWorker();
			} else {
				driver.drive(bundleConfig);
			}
		} catch (BundleException e) {
			logger.fatal("Bundle exception: " + e.getMessage() + " @ "
					+ e.getLocationsAsString());
			System.exit(1);
		} catch (DriverException e) {
			logger.fatal("Reading block '" + driver.rootBlock + "' failed: "
					+ e.getMessage() + " @ " + e.getLocationsAsString());
			System.exit(1);
		}
	}

	/** Makes the driver expose a remote worker interface. */
	private void provideWorker() {
		IRemoteWorker worker = new RemoteWorker();
		try {
			UnicastRemoteObject.exportObject(worker);
			Registry registry = LocateRegistry.createRegistry(workerPort);
			registry.bind(IRemoteWorker.class.getSimpleName(), worker);
			System.out.println("Worker waiting on port " + workerPort);
		} catch (RemoteException e) {
			System.err.println("Could not publish remote worker: "
					+ e.getMessage());
		} catch (AlreadyBoundException e) {
			System.err.println("Could not bind remote worker: "
					+ e.getMessage());
		}
	}

	/**
	 * Create the configuration and trigger the execution.
	 * <p>
	 * For testing purposes, the bundleConfig may be <code>null</code>, but then
	 * {@link #compileAll} may not be used and some feature may not work
	 * correctly.
	 * 
	 * @throws DriverException
	 *             if reading the configuration fails.
	 */
	public void drive(BundlesConfiguration bundleConfig) throws DriverException {
		logger.info("ConQAT using block '" + rootBlock + "'.");

		PerformanceMonitor monitor = PerformanceMonitor.create(true);

		BlockInstance configInstance = prepareMainInstance(bundleConfig);

		if (dryRun) {
			logger.info("Configuration '" + rootBlock + "' seems to be OK.");
		} else {
			JobDistributorBase jobDistributor = createJobDistributor();
			BlockInfo configurationInfo = new BlockInfo(configInstance);

			if (instrumentation.beginExecution(configurationInfo)) {
				configInstance.execute(new ExecutionContext(configurationInfo,
						bundleConfig), instrumentation, jobDistributor);
			}
			instrumentation.endExecution();
			jobDistributor.shutdown();
		}

		monitor.stop();
		logger.info("Max memory: "
				+ StringUtils.format(monitor.getMaxMemUsageInKBs()) + "kB.");
		logger.info("Total time: " + monitor.getSeconds() + "s");
	}

	/**
	 * Creates the job distributor used based on the contents of the
	 * {@link #remoteHosts} list.
	 */
	protected JobDistributorBase createJobDistributor() {
		PairList<String, IRemoteWorker> remoteWorkers = new PairList<String, IRemoteWorker>();
		for (String host : remoteHosts) {
			try {
				remoteWorkers.add(host, (IRemoteWorker) Naming.lookup("//" + host
						+ "/" + IRemoteWorker.class.getSimpleName()));
				logger.info("Established connection to " + host);
			} catch (MalformedURLException e) {
				logger.error("Invalid hostname/URL: " + host
						+ " (ignoring host)");
			} catch (RemoteException e) {
				logger.error("Connection to " + host + " failed: "
						+ e.getMessage(), e);
			} catch (NotBoundException e) {
				logger.error("Connection to " + host + " failed: "
						+ e.getMessage(), e);
			}
		}

		if (remoteWorkers.isEmpty()) {
			return new LocalJobDistributor();
		}
		return new RemoteJobDistributor(remoteWorkers);
	}

	/**
	 * Creates the main instance for the config file, which is then used for
	 * actually executing it.
	 */
	private BlockInstance prepareMainInstance(BundlesConfiguration bundleConfig)
			throws NoClassDefFoundError, EnvironmentException, DriverException {
		Set<BundleInfo> bundles = new HashSet<BundleInfo>();

		// This can be null if no bundle collections and locations are provided
		if (bundleConfig != null) {
			bundles = bundleConfig.getBundles();
		}

		/*
		 * to determine the base directory we can treat the root block as a file
		 * in any case. If it is a qualified block name the base dir will
		 * default to the current working dir (as a qualified block name has no
		 * slashes and thus is interpreted as a plain file here)
		 */
		SpecificationLoader specLoader = new SpecificationLoader(new File(
				rootBlock).getParentFile(), bundles);
		if (compileAll) {
			DriverUtils.compileAllProcessorsAndBlocks(bundleConfig, specLoader);
		}

		// create configuration

		// first try to load as fully qualified block name
		BlockSpecification configSpecification = specLoader
				.getBlockSpecification(rootBlock);

		// fall back: treat as file
		if (configSpecification == null) {
			File configFile = new File(rootBlock);
			if (!configFile.canRead()) {
				throw new BlockFileException(EDriverExceptionType.IO_ERROR,
						configFile + " does not exists!", ErrorLocation.UNKNOWN);
			}

			configSpecification = new BlockFileReader(specLoader)
					.readBlockFile(configFile);
			configSpecification.initialize();
		}

		HashedListMap<String, String> properties = determineProperties();
		BlockDeclaration configDeclaration = new BlockDeclaration(
				configSpecification, specLoader, properties);

		BlockInstance configInstance = configDeclaration.instantiate(null);
		return configInstance;
	}

	/**
	 * Compute the properties that override the properties specified in the
	 * ConQAT configuration.
	 * <p>
	 * Properties specified via the command line have preference over properties
	 * specified via a properties file. If no properties file is given, an
	 * attempt to read the default property file is made.
	 */
	private HashedListMap<String, String> determineProperties()
			throws EnvironmentException {

		if (propertiesFile == null) {
			// We intentionally use the name 'somefile.cqb.properties' here
			propertiesFile = new File(
					(rootBlock + DEFAULT_PROPERTIES_FILE_SUFFIX));
		}

		HashedListMap<String, String> fileProperties = null;
		if (propertiesFile.canRead()) {
			logger.info("Reading properties from file: " + propertiesFile);
			try {
				fileProperties = PropertyUtils
						.parseCqProperties(propertiesFile);
			} catch (IOException e) {
				throw new EnvironmentException(
						EDriverExceptionType.PROPERTY_FILE_READ_ERROR,
						"Cannot read property file.", e, new ErrorLocation(
								propertiesFile));
			}
		}

		if (fileProperties == null) {
			return commandLineProperties;
		}

		for (String key : commandLineProperties.getKeys()) {
			// command line properties replace file properties
			fileProperties.removeList(key);
			fileProperties.addAll(key, commandLineProperties.getList(key));
		}

		return fileProperties;
	}

	/** Set configuration file for logger. */
	@AOption(shortName = 'l', longName = "log", description = "set logger config file")
	public void setLoggingConfig(String loggingConfig) {
		loggingConfigFile = new File(loggingConfig);
		if (!loggingConfigFile.canRead()) {
			System.err.println("Logging config file not readable: "
					+ loggingConfigFile);
			System.exit(3);
		}
	}

	/** Set the name of the config file used (root block). */
	@AOption(shortName = 'f', longName = "config", description = "the file containing the root block (config); "
			+ "may also be qualified block name ["
			+ DEFAULT_BLOCK_FILE_NAME
			+ "]")
	public void setConfigFileName(String rootBlockName) {
		// We do not yet check for existence here, as this is done later anyways
		rootBlock = rootBlockName;
	}

	/** Set the dry-run option: only check config file */
	@AOption(shortName = 'n', longName = "dry-run", description = "only check config file")
	public void setDryRun() {
		dryRun = true;
	}

	/** Set the compile-all option: compile all block-specs */
	@AOption(shortName = 'a', longName = "compile-all", description = "compile all block-specs")
	public void setCompileAll() {
		compileAll = true;
	}

	/** Set properties file */
	@AOption(shortName = 's', longName = "properties-file", description = ""
			+ "override properties in the used configuration with properties from a property file")
	public void setPropertyFile(String propertiesFilename) {
		propertiesFile = new File(propertiesFilename);

		// we need this explicit check here, as we want to raise an exception if
		// the explicitly given properties file is missing, while we do not
		// raise
		// one if the default properties file is missing, as the later is
		// optional.
		if (!propertiesFile.canRead()) {
			throw new IllegalArgumentException("Cannot find properties file: "
					+ propertiesFilename);
		}
	}

	/** Adds a property which is set via the command line. */
	@AOption(shortName = 'p', longName = "property", description = ""
			+ "override a property (name=value) in the used configuration "
			+ "(or read from a property file via the -s option.)")
	public void addCommandLineProperty(String nameValue) {
		String[] parts = nameValue.split("=", 2);
		if (parts.length < 2) {
			throw new IllegalArgumentException(
					"Given property must be of format <name>=<value>!");
		}
		commandLineProperties.add(parts[0], parts[1]);
	}

	/** Sets the instrumentation interface used. */
	public void setInstrumentation(ConQATInstrumentation instrumentation) {
		this.instrumentation = instrumentation;
	}

	/** Set worker mode. */
	@AOption(shortName = 'i', longName = "instrument", description = ""
			+ "Provides the name of a class used for instrumentation.")
	public void setInstrumentationClass(String className) {
		try {
			instrumentation = (ConQATInstrumentation) Class.forName(className)
					.newInstance();
		} catch (Exception e) {
			// This catches all: ClassCast, ClassNotFound, etc.
			// IMHO this "reduced" error handling is ok, as this is an option
			// for the ConQAT power user.
			System.err.println("Could not load class " + className + ": "
					+ e.getMessage());
			System.exit(1);
		}
	}

	/** Set worker mode. */
	@AOption(shortName = 'w', longName = "worker", description = ""
			+ "Set worker mode. ConQAT will wait for remote connections. "
			+ "Provide port to listen on as parameter.")
	public void activateWorkerMode(int port) {
		if (port <= 0 || port >= 0xffff) {
			throw new IllegalArgumentException(
					"Port value must be positive 16-bit value!");
		}
		workerPort = port;
	}

	/** Add remote worker. */
	@AOption(shortName = 'r', longName = "remote", description = ""
			+ "Adds the address (host:port) of a remote worker.")
	public void addRemoteWorker(String workerAddress) {
		remoteHosts.add(workerAddress);
	}
}
