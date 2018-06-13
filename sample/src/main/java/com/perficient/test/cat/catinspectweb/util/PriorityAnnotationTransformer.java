//package com.perficient.test.cat.catinspectweb.util;
//
//import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.log;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import mx4j.log.Log;
//
//import org.testng.IAnnotationTransformer;
//import org.testng.annotations.ITestAnnotation;
//import org.testng.annotations.Test;
//
//public class PriorityAnnotationTransformer implements IAnnotationTransformer {
//
//	@Override
//	public void transform(ITestAnnotation p_annotation, Class p_testClass,
//			Constructor p_testConstructor, Method p_testMethod) {
//		// p_annotation.setGroups(group(p_testMethod));
//		// for (String gro : p_annotation.getGroups())
//		p_annotation.setPriority(getTestCaseID(p_testMethod));
//		// p_annotation.setTimeOut(setTimeOutHere(p_testMethod));
//		p_annotation.setEnabled(setRunCases(p_testMethod));
//
//	}
//
//	private int getTestCaseID(Method p_testMethod) {
//		//try {
//			// CtClass cc =
//			// s_ClassPool.get(p_testMethod.getDeclaringClass().getCanonicalName());
//			// CtMethod methodX = cc.getDeclaredMethod(p_testMethod.getName());
////			log.info("test case name is:"+p_testMethod.getName());
////			String methodname=p_testMethod.getName();
//////			String regex = String.format("verify_TC\\d+");
////			String regex = "verify_TC\\d*";
////			Pattern pattern = Pattern.compile(regex);
////			Matcher m=pattern.matcher(methodname);
//			//Matcher m = Pattern.compile(regex).matcher(methodname);
////			log.info("m.find is:::"+m.find());
////			log.info("m.group2 is:"+m.group());	
////			String temp=m.group();
////		
////			Pattern.matches(regex, p_testMethod.getName());
////			Pattern.compile(regex).matcher(p_testMethod.getName()).find();
////			log.info(Pattern.compile(regex).matcher(p_testMethod.getName()).find());
//			//log.info("match !!!!"+Pattern.compile(regex).matcher(p_testMethod.getName()).group(0));
//			 log.info("test case");
//			   String paternStr = "verify_TC\\d+";  
//		        Pattern pattern = Pattern.compile(paternStr);  
//		        Matcher matcher = pattern.matcher(p_testMethod.getName()); 
//		        String temp="";
//		        if (matcher.find()) {  
//		            log.info(matcher.group(0));  
//			      temp=matcher.group(0);
//		        }
//		        int priority = Integer.parseInt(FunctionUtil
//						.getTheValueBetweenStringAAndB(temp, "TC", ""));
//			return priority;
////		} catch (Exception e) {
////			throw new RuntimeException("Getting of TestCase ID of method "
////					+ p_testMethod.getName() + " failed", e);
////		}
//	}
//
//	private String[] group(Method p_testMethod) {
//		try {
//			String testMathodName = p_testMethod.getName().toLowerCase();
//			String[] groups = p_testMethod.getAnnotation(Test.class).groups();
//			List<String> addGroup = new ArrayList<String>();
//			if (groups != null) {
//				for (String gro : groups) {
//					addGroup.add(gro);
//				}
//			}
//
//			// add special group to high priority page
//
//			if (testMathodName.contains("supplierdashboard")
//					|| testMathodName.contains("facilitydashboard")
//					|| testMathodName.contains("mar")
//					|| testMathodName.contains("orderinfo")
//					|| testMathodName.contains("supplierscorecard")
//					|| testMathodName.contains("settings")
//					|| testMathodName.contains("mycoresuppliers")
//					|| testMathodName.contains("alerts")
//					|| testMathodName.contains("situationsandevents")
//					|| testMathodName.contains("qualitydesktop")
//					|| testMathodName.contains("categorymanager")
//					|| testMathodName.contains("directordesktop")
//					|| testMathodName.contains("segmentmanagerdesktop")
//					|| testMathodName.contains("sitebuyerdesktop")) {
//				addGroup.add("highPriorityPage");
//			}
//
//			// String[] caseIdStrings = {
//			// "TC002",
//			// };
//			// for(String id : caseIdStrings)
//			// if (testMathodName.contains(id))
//			// addGroup.add("not-run");
//
//			int size = addGroup.size();
//			return addGroup.toArray(new String[size]);
//
//		} catch (Exception e) {
//			throw new RuntimeException("Getting and Adding group of method "
//					+ p_testMethod + " failed", e);
//		}
//	}
//
//	private boolean setRunCasesSandbox(Method p_testMethod) {
//		String[] notRunOnSandbox = { "TC244", "TC247", "TC248", "TC245",
//				"TC292" };
//		String testMathodName = p_testMethod.getName().toLowerCase();
//		// String[] groupString = null;
//		int flag = 0;
//		 System.out.println("1111" + testMathodName);
//		for (int i = 0; i < notRunOnSandbox.length; i++) {
//			if (testMathodName.contains(notRunOnSandbox[i].toLowerCase())) {
//				flag = 1;
//				break;
//			}
//		}
//		if (TestCaseBase.envNameFlag.equals("S") && flag == 1) {
//			return false;
//		} else {
//			return true;
//		}
//
//	}
//
//	private boolean setRunCases(Method p_testMethod) {
//		String[] notRunCase = {};
//
//		String[] runCase = {
//				"68",
//				"69",
//				"72",
//				"500",
//				"502",
//				"504",
//				"505",
//				"516",
//				"1823",
//				"1891",
//				"1907",
//				"1910",
//				"1919",
//				"1955",
//				"1996",
//				"1997",
//				"2032",
//				"2033",
//				"2043",
//				"2056",
//				"2079",
//				"2143",
//				"2333",
//				"2335",
//				"2339",
//				"2341",
//				"2344",
//				"2409",
//				"2557",
//				"2594",
//				"2595",
//				"2596"
//		};
//
//		// String testMathodName = p_testMethod.getName().toLowerCase();
//
//		int flag = 0;
//		// System.out.println("1111" + p_testMethod.getName());
//		log.info("1111" + p_testMethod.getName());
//		for (int i = 0; i < notRunCase.length; i++) {
//			if (getTestCaseID(p_testMethod) == Integer.parseInt(notRunCase[i])) {
//				flag = 1;
//				break;
//			}
//		}
//		int temp = 0;
//		if (runCase.length == 0) {
//			temp = 1;
//		} else {
//			for (int i = 0; i < runCase.length; i++) {
//				if (getTestCaseID(p_testMethod) == Integer.parseInt(runCase[i])) {
//					temp = 1;
//					break;
//				}
//			}
//		}
//
//		if (flag == 1) {
//			return false;
//		} else if (temp == 1 && flag == 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	private int setTimeOutHere(Method p_testMethod) {
//		int timeout;
//		String testMathodName = p_testMethod.getName().toLowerCase();
//		if (testMathodName.contains("excel")) {
//			timeout = 2400000;
//		} else {
//			timeout = 1200000;
//		}
//		return timeout;
//	}
//}