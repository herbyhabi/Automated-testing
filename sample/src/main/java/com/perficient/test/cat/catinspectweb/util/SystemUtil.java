package com.perficient.test.cat.catinspectweb.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;



public class SystemUtil {
	 
	public static Properties resource = null;

	
	/**
	 * load the test data in properties file
	 * @param filePath : The relative path of the properties file
	 * 
	 */
	static public Properties loadPropertiesResources(String fileName){
		InputStream data_input = SystemUtil.class.getResourceAsStream(fileName);
		resource = new Properties();
			try {
				resource.load(data_input);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resource;
	}
	
	/**
	 * Return the all the key in the Properties
	 * @param filePath : The relative path of the properties file
	 * @return : ArrayList<String>
	 * 
	 */
	
	@SuppressWarnings("rawtypes")
	static public ArrayList<String> loadProperties(String fileName){
		InputStream data_input = SystemUtil.class.getResourceAsStream(fileName);
		resource = new Properties();
			try {
				resource.load(data_input);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			@SuppressWarnings("unchecked")
			ArrayList<String> listOfNames = new ArrayList(resource.keySet());
			return listOfNames;
			
			
	}
	
	
	
	/**
	 * Killer drivers
	 * 
	 */
	public static void driverKiller() throws Exception
	{
	
	  //Define Parameters
	  final String kILL = "taskkill /IM ";
	  
	  //Kill the IE driver's  process
	  String ieProcess = "IEDriverServer.exe"; //IE process
	  Runtime.getRuntime().exec(kILL+ ieProcess); 
	  Thread.sleep(1000);
	  
	  //Kill the chrome driver's  process
	  String chormeProcess = "chromedriver.exe"; 
	  Runtime.getRuntime().exec(kILL+ chormeProcess); 
	  Thread.sleep(1000);
	  
	  //Kill the fire fox driver's  process
	  String firefoxProcess = "firefoxdriver.exe";
	  Runtime.getRuntime().exec(kILL+ firefoxProcess); 
	  Thread.sleep(1000);
	}
	
	
	
}
