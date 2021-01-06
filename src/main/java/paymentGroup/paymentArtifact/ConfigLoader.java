package paymentGroup.paymentArtifact;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * To load the following configuration in DataFileConfig.properties
 *  - whether to load data file on app start up
 *  - the output interval time in milliseconds when displaying on console
 *
 */
public class ConfigLoader {
	private static Properties prop = new Properties();
	private final static String DATA_FILE_CONFIG = "DataFileConfig.properties";
	private final static String READ_DATA_FILE_ON_START_UP = "readDataFileOnStartUp";
	private final static String OUTPUT_INTERVAL_TIME = "outputIntervalTime";
	
	public static void loadProperties() {
		try {
			ClassLoader classLoader = ConfigLoader.class.getClassLoader();
			prop.load(classLoader.getResourceAsStream(DATA_FILE_CONFIG));
		} catch (IOException e) {
			System.out.println("Could not load data config file");
			e.printStackTrace();
		}
	}
	
	public static boolean readDataFileOnStartUp() {
		return Boolean.parseBoolean(prop.getProperty(READ_DATA_FILE_ON_START_UP));
	}
	
	public static int getOutputIntervalTime() {
		return Integer.parseInt(prop.getProperty(OUTPUT_INTERVAL_TIME));
	}
}
