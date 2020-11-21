/**
 * 
 */
package com.vpm.pageObjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maithili.s
 *
 */
public class GlobalVariables {
	
	//Configuration variables
	public static String Browser1 = "", Browser2 = "";
	public static int process = 0;
	public static List<String> server = new ArrayList<String>();
	public static String[] featureFiles;
	public static String roomName = "", roomName1 = "";
	public static String scenarioName = "";
	
	//Project variables
	public static Boolean isJoined = false, isSessionTerminated = false, isRoomCreated = false;
	public static Map<String, Boolean> defaultControls = new HashMap<String, Boolean>();
	public static Map<String, Boolean> mediaControls = new HashMap<String, Boolean>();
	public static Map<String, Boolean> joiner_defaultControls = new HashMap<String, Boolean>();
	public static Map<String, Boolean> joiner_mediaControls = new HashMap<String, Boolean>();	
	public static double zoomValue = 0.00, previousZoomValue = 0.00; 	
	public static Boolean firstMobileInstance = false, thirdMobileInstance = false;
	public static Date currentDuration = null, totalDuration = null;
	public static String cDuration = "", tDuration = "";
	public static String playbackStatus = "";
}
