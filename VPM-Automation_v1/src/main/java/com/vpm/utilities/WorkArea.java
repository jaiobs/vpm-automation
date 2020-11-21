package com.vpm.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import org.openqa.selenium.support.FindBy;

import com.vpm.managers.ConfigManager;
import com.vpm.pageObjects.BaseClass;
import com.vpm.pageObjects.Videos_Mobile;

public class WorkArea {

	public static void main(String args[]) throws IOException, URISyntaxException {
		
		DateFormat dateFormat = new SimpleDateFormat("ss:mm");
		Date currentDuration = null;
		Date totalDuration = null;
		try {
			currentDuration = dateFormat.parse("00:10");
			totalDuration = dateFormat.parse("00:20");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println(dateFormat.format(currentDuration) +"---------------"+dateFormat.format(totalDuration));
			
		//System.out.println("/usr/local/bin:" + System.getenv("PATH"));
	}
}
