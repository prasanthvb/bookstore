package com.bookstore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.bookstore.Constants.FrameworkConstant;

public class TestBase {
	
	public static Properties prop = new Properties();

	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(FrameworkConstant.PROPERTYFILE_PATH);
			prop.load(ip);
			ip.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		String value = prop.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Config property '" + key + "' is not defined. Please check config.properties.");
		}
		return value;
	}

}
