/**
 * 
 */
package com.vpm.helpers;

import java.util.HashSet;
import java.util.Set;

import com.vpm.pageObjects.GlobalVariables;

/**
 * @author maithili.s
 *
 */
public class ExecutorData {

	
	public static void main(String args[]) {
		DataHelper dh = new DataHelper();
		Set<String> set = new HashSet<String>(); 
		set = dh.getExecutor("Executor.xlsm");
		System.out.println("--------------------------------------------------------------");
		System.out.println(set);
		System.out.println(GlobalVariables.server);
	}
}
