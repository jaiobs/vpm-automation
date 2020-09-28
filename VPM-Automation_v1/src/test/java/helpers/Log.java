package helpers;
import org.apache.log4j.Logger;

public class Log {

	private static Logger log = Logger.getLogger(Log.class.getName()); 
	
	public static void startTestCase(String sTestCaseName){
		log.info("Started Test case");
	}


	public static void endTestCase(String sTestCaseName){
		log.info("Ended Test Case");
	}

	public static void info(String message)
	{
		log.info(message);
	}
	
	public static void debug(String message)
	{
		log.debug(message);
	}
}