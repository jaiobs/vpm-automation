/**
 * 
 */
package com.vpm.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * @author maithilis
 *
 */
public class FileUtilities {
	
	public static void deleteDir(String path) throws IOException {
		File f = new File(path);
		FileUtils.deleteDirectory(f);
	}

}
