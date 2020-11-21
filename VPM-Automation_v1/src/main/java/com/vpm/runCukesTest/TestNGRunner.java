package com.vpm.runCukesTest;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author maithili.s
 *
 */

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vpm.helpers.DataHelper;
import com.vpm.helpers.Log;
import com.vpm.pageObjects.GlobalVariables;

import io.cucumber.core.options.RuntimeOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

@CucumberOptions(features = { "classpath:com/vpm/features/A_LandingPage.feature",
		"classpath:com/vpm/features/B_Server_Settings.feature",
		"classpath:com/vpm/features/C_ChatRoom_With_Default_Controls.feature",
		"classpath:com/vpm/features/Cross-Server-Connection.feature",
		"classpath:com/vpm/features/D_Ending_A_Call.feature", "classpath:com/vpm/features/E_Suspend_A_Session.feature",
		"classpath:com/vpm/features/F_ZoomIn_ZoomOut.feature", "classpath:com/vpm/features/G_StabilityCheck.feature",
		"classpath:com/vpm/features/H_ChatRoom_with_multiple_control_validations.feature",
		"classpath:com/vpm/features/I_Joiner_With_MultipleLiveSessions.feature",
		"classpath:com/vpm/features/J_Multiple_Room_Join_Validations.feature",
		"classpath:com/vpm/features/K_VideoPlayerFunctions.feature" }, plugin = { "pretty",
				"json:target/cucumber-reports/CucumberTestReport.json" }, glue = {
						"com.vpm.stepDefinitions" }, monochrome = true)
public class TestNGRunner extends CustomAbstractTestNGCucumberTests {
	private TestNGCucumberRunner testNGCucumberRunner;

	String[] args;
	String tagValue = "";

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		getExecutorData();
		args = GlobalVariables.featureFiles;
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable {
		testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
	}

	@DataProvider()
	public Iterator<Object[]> scenarios() {
		ArrayList<Object[]> modifiedList = new ArrayList<>();
		if (testNGCucumberRunner == null) {
			return modifiedList.iterator();
		}
		modifiedList = filterTheFeature(testNGCucumberRunner.provideScenarios());
		return modifiedList.iterator();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		if (testNGCucumberRunner == null) {
			return;
		}
		testNGCucumberRunner.finish();

		generateReportForJsonFiles(new File("target/"),
				Arrays.asList("target/cucumber-reports/CucumberTestReport.json"));
		try {
			Runtime.getRuntime().exec("adb -s emulator-5554 emu kill");
			Runtime.getRuntime().exec("adb -s emulator-5556 emu kill");
			Runtime.getRuntime().exec("adb -s emulator-5558 emu kill");
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void generateReportForJsonFiles(File reportOutputDirectory, List<String> jsonFiles) {
		String projectName = "VPM - (" + GlobalVariables.server.get(0) + ")";
		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
	}

	// Method to Add Features and Tags at runtime
	private ArrayList<Object[]> filterTheFeature(Object[][] data) {
		List<String> featureList = Arrays.asList(args);
		ArrayList<Object[]> modifiedList = new ArrayList<>();
		if (tagValue == null || tagValue.isEmpty()) {
			return getFeatureList(data);
		}

		List<String> tagList = Arrays.asList(tagValue.split(","));
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				PickleWrapper pickleEventWrapper = (PickleWrapper) data[i][0];
				if (!pickleEventWrapper.getPickle().getTags().isEmpty()) {
					for (String aTag : tagList) {
						int tagSize = pickleEventWrapper.getPickle().getTags().size();
						for (int tag = 0; tag < tagSize; tag++) {
							if (aTag.contains(pickleEventWrapper.getPickle().getTags().get(tag))) {
								for (String arg : args) {
									String scenario = "";
									scenario = data[i][0].toString();
									String scenario1 = "";
									if (scenario.contains("Chat room creation with media control combinations")) {
										scenario = scenario.split("LA-")[0].trim();																				
									}
									if (scenario.replaceAll("\"", "").contains(arg)) {
										modifiedList.add(data[i]);
										System.out.println(arg + "--------------" + scenario);
										break;
									}
								}
							}
						}
						break;
					}
				}

			}
		}
		return modifiedList;
	}

	// Method to get the configuration from external file
	private void getExecutorData() {
		DataHelper dh = new DataHelper();
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<String>();
		set = dh.getExecutor("Executor.xlsm");
		System.out.println("--------------------------------------------------------------");
		System.out.println(set);
		String featureFiles = "";
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			// featureFiles = featureFiles + "classpath:com/vpm/features/" +it.next()+",";
			featureFiles = featureFiles + it.next() + ",";
		}
		GlobalVariables.featureFiles = featureFiles.split(",");
		System.out.println(GlobalVariables.server);
		String platform1 = GlobalVariables.Browser1;
		String platform2 = GlobalVariables.Browser2;
		switch (platform1) {
		case "Chrome":
			switch (platform2) {
			case "Chrome":
				tagValue = "@Chrome";
				break;
			case "Android":
				tagValue = "@Chrome-Android";
				break;
			case "IOS":
				tagValue = "@Chrome-IOS";
				break;
			}
			break;
		case "Android":
			switch (platform2) {
			case "Chrome":
				tagValue = "@Android-Chrome";
				break;
			case "Android":
				tagValue = "@Android";
				break;
			case "IOS":
				tagValue = "@Android-IOS";
				break;
			}
			break;
		case "IOS":
			switch (platform2) {
			case "Chrome":
				tagValue = "@IOS-Chrome";
				break;
			case "Android":
				tagValue = "@IOS-Android";
				break;
			case "IOS":
				tagValue = "@IOS";
				break;
			}
			break;
		}
		String features = "";
		switch (tagValue) {
		case "@Android":
		case "@IOS":
		case "@Android-IOS":
		case "@IOS-Android":
			for (String featureFile : GlobalVariables.featureFiles) {
				features = features + featureFile.concat(" on mobile,");
			}
			break;
		case "@Chrome-Android":
		case "@Chrome-IOS":
			for (String featureFile : GlobalVariables.featureFiles) {
				if (featureFile.contains("Landing page") || featureFile.contains("Validations of Server Settings")
						|| featureFile.contains("Video player")
						|| featureFile.contains("Joiner with multiple live sessions")
						|| featureFile.contains("sequential chat sessions")) {
					features = features + featureFile.concat(",");
				} else {
					features = features + featureFile.concat(" on Web-Mobile,");
				}
			}
			break;
		case "@IOS-Chrome":
		case "@Android-Chrome":
			for (String featureFile : GlobalVariables.featureFiles) {
				if (featureFile.contains("Landing page")
						|| featureFile.contains("Validations of Server Settings on mobile")
						|| featureFile.contains("Video player")
						|| featureFile.contains("Joiner with multiple live sessions")
						|| featureFile.contains("sequential chat sessions")) {
					features = features + featureFile.concat(" on mobile,");
				} else {
					features = features + featureFile.concat(" on Mobile-Web,");
				}
			}
			break;
		}
		GlobalVariables.featureFiles = features.split(",");
		if (features.contains("Landing page") || features.contains("Validations of Server Settings")
				|| features.contains("Video player") || features.contains("Joiner with multiple live sessions")
				|| features.contains("sequential chat sessions")) {
			tagValue = tagValue + "," + "@" + platform1;
		}
	}

	private ArrayList<Object[]> getFeatureList(Object[][] data) {
		ArrayList<Object[]> modifiedList = new ArrayList<>();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				modifiedList.add(data[i]);
			}
		}
		return modifiedList;
	}
}
