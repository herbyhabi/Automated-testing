package com.perficient.test.cat.catinspectweb.testdata;

import java.util.TreeMap;

public class CommonTestData {
	
	public static final String QAURL = "https://catinspectweb-qa.cat.com/";
	public static final String DevURL = "https://catinspectweb-dev.cat.com/";
	public static final String ProdURL = "https://catinspectweb.cat.com/";
	
	public static final int MAX_WAIT_FOR_PAGE = 180;
	public static final int MAX_WAIT_FOR_ELEMENT = 30;
	public static final String DOWNLOADDIR="./Download";
	public static final String TESTDATADIR="./TestData";
	
	// Accounts 
      public static TreeMap<String, String> defalutPassword = new TreeMap<>();
//
//    //CWS IDs
//    public static String fengm1 = "fengm1";
//    //CWS password
//    static {
//        defalutPassword.put(fengm1, "Wsx12edc");
        
      //CWS IDs
      public static String zhangc42 = "zhangc42";
      public static String fengm1 = "fengm1";
      //CWS password
      static {
          defalutPassword.put(zhangc42, "Zhh131109zhh");
          defalutPassword.put(fengm1, "Perficient12");
       
    }
    
    public static String getPswd(String userName){
    	return defalutPassword.get(userName);
    }	
    
    
    public static final String productTilePrefix = "//div[@class='productdesccontent' and contains(text(),'%s')]/ancestor::div[@class='product' and @style]";
	
}
