/**
 * 
 */
package com.vpm.runCukesTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gherkin.pickles.PickleTag;
import io.cucumber.core.options.RuntimeOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

/**
 * @author maithili.s
 *
 */
public class CustomAbstractTestNGCucumberTests {
	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable {
		// the 'featureWrapper' parameter solely exists to display the feature file in a
		// test report
		testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
	}

	/**
	 * @return returns two dimensional array of {@link CucumberFeatureWrapper}
	 *         objects.
	 */
	@DataProvider
	public Iterator<Object[]> scenarios() {
		return filterTheFeature(testNGCucumberRunner.provideScenarios()).iterator();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		testNGCucumberRunner.finish();
	}

	// Method to Add Features at runtime
	private ArrayList<Object[]> filterTheFeature(Object[][] data) {
		List<String> featureList = Arrays.asList("Landing page");
		ArrayList<Object[]> modifiedList = new ArrayList<>();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				FeatureWrapper featureWrapper = (FeatureWrapper) data[i][1];
				if (featureList.contains(featureWrapper.toString().trim().replaceAll("\"", ""))) {
					modifiedList.add(data[i]);
				}
			}
		}

		String tagValue = System.getProperty("TagName");

		if (tagValue == null || tagValue.isEmpty()) {
			return getFeatureList(data);
		}

		List<String> tagList = Arrays.asList(tagValue.split(","));
		// List<String> featureList = Arrays.asList("Web Element Functions","Working
		// with Java Script Popup");

		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				PickleWrapper pickleEventWrapper = (PickleWrapper) data[i][0];
				if (!pickleEventWrapper.getPickle().getTags().isEmpty()) {
					for (String aTag : tagList) {
						if (aTag.contains(pickleEventWrapper.getPickle().getTags().get(i))) {
							modifiedList.add(data[i]);
						}
					}
				}

			}
		}
		return modifiedList;
	}

	// Method to add tags at runtime

	/*private boolean isTagPresent(String aTag, List<String> aTagList) {

		return aTagList.stream().filter(new Predicate<PickleTag>() {
			@Override
			public boolean test(PickleTag t) {
				return aTag.equalsIgnoreCase(t.getName());
			}
		}).collect(Collectors.toList()).isEmpty();
	}*/

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
