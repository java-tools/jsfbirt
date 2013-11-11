package com.exadel.jsfbirt.engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.eclipse.birt.core.framework.IPlatformContext;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.core.framework.PlatformServletContext;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;

import com.exadel.jsfbirt.component.html.BirtWrapper;

/**
 * This class is responsible for creating and initializing of BIRT engine API.
 * BIRT engine API is used by BIRt wrapper component to generate report HTML code.
 * @author Andrey Markavtsov
 */
public final class BirtEngine {
	
	/** BIRT engine instance*/
	private static IReportEngine birtEngine = null;

	/** BIRT configuration properties */
	private static Properties configProps = new Properties();

	/** BIRT configuration folder path */
	private static String configPath = null;
	
	private BirtEngine() {
		
	}

	/**
	 * Initializes BIRT engine properties
	 * @param birtWrapper
	 */
	private static synchronized void initBirtConfig(
			BirtWrapper birtWrapper) throws IOException {
		loadEngineProps(birtWrapper);
	}

	/**
	 * Returns initialized BIRT engine instance 
	 * @param sc ServletContext
	 * @return birtEngine
	 */
	public static synchronized IReportEngine getBirtEngine(BirtWrapper birtWrapper, ServletContext sc) throws IOException {
		if (birtEngine == null) {
			initBirtConfig(birtWrapper);
			EngineConfig config = new EngineConfig();

			if (configProps != null) {
				String logLevel = configProps.getProperty("logLevel");
				Level level = logLevel != null ? java.util.logging.Level.parse(logLevel) : Level.SEVERE;
				config.setLogConfig(configProps.getProperty("logDirectory"),
						level);
			}

			config.setEngineHome("");
			config.setResourcePath(configProps.getProperty("resources.path"));

			IPlatformContext context = new PlatformServletContext(sc);
			config.setPlatformContext(context);

			try {
				Platform.startup(config);
			} catch (Exception e) {
				throw new FacesException(e);
			}

			IReportEngineFactory factory = (IReportEngineFactory) Platform
					.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
			birtEngine = factory.createReportEngine(config);

		}
		return birtEngine;
	}

	/**
	 * Destroys BIRT engine instance
	 */
	public static synchronized void destroyBirtEngine() {
		if (birtEngine == null) {
			return;
		}
		birtEngine.shutdown();
		Platform.shutdown();
		birtEngine = null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * Loads BIRT engine properties
	 * @param birtWrapper
	 */
	private static void loadEngineProps(BirtWrapper birtWrapper)throws IOException {
		InputStream in = null;
		try {
			// Config File must be in classpath
			ClassLoader cl = Thread.currentThread().getContextClassLoader();

			configPath = birtWrapper != null ?  birtWrapper.getConfigPath() : null;

			if (configPath == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				configPath = context.getExternalContext().getInitParameter(
						"org.eclipse.birt.configPath");
				if (configPath == null) {
					throw new FacesException(
							"No configuration was defined for birt engine");
				}
			}

			in = cl.getResourceAsStream(configPath);
			if (in != null) {
				configProps.load(in);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (in != null) {
				in.close();
			}
		}

	}

}
