/**
 * 
 */
package com.vpm.runCukesTest;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.testng.TestNG;
import org.testng.collections.Lists;

import com.vpm.helpers.DataHelper;
import com.vpm.pageObjects.GlobalVariables;


/**
 * @author maithili.s
 *
 */
public class exec {

	static TestNG testng;
	
	public static void main(String[] args) throws IOException {		
		testng = new TestNG();	
		testng.setTestClasses(new Class[] {TestNGRunner.class});		
		testng.run();
	}
}