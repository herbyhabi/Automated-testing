package com.perficient.test.cat.catinspectweb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	private static String reportName = "regressionReport";
	private final String defectList = "defectList.xlsx";
	private final String pathString = "RegressionReport";
	private static String mode = "mode.xlsx";
	private static String tempFile = "temp.xlsx";
	private String newFile;
	private final String[] header = { "Failed Test Case Name", "Script Status","Failed Reasons", "Comments", "Exceptions", "Package" };
	private File newf;
	//private File oldf;
	private File tempf;
	private static String FailAnalysis = "Fail Analysis";
	private static String LastResult = "Last Analysis Result";
	private static String Summary = "CI Reports Analysis Summary";
	private static String CaseHaveRun = "Cases have run";
	private FileOutputStream fos;
	private FileInputStream fis; 
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		try {
			updateSummary(arg0);
			updateReport();
			moveToOutPut();
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}

	}

	// update CI Reports Analysis Summary sheet
	private void updateSummary(ITestContext arg0) throws Exception {
		fos = new FileOutputStream(tempf);
		fis = new FileInputStream(newf);
		Workbook file = new XSSFWorkbook(fis);
		Sheet summarySheet = file.getSheet(Summary);
		try {
			summarySheet.getRow(2).getCell(1).setCellValue(getEnvironment());
			summarySheet.getRow(2).getCell(3).setCellValue(arg0.getPassedTests().size());
			summarySheet.getRow(2).getCell(4).setCellValue(arg0.getPassedTests().size()/arg0.getAllTestMethods().length);
			summarySheet.getRow(2).getCell(5).setCellValue(arg0.getFailedTests().size() + arg0.getSkippedTests().size());
			summarySheet.getRow(2).getCell(6).setCellValue((arg0.getFailedTests().size() + arg0.getSkippedTests().size())/arg0.getAllTestMethods().length);
			summarySheet.getRow(2).getCell(2).setCellValue(arg0.getAllTestMethods().length);

			file.write(fos);
		} finally {
			fos.close();
			fis.close();
		}
	}

	private String getEnvironment() {
		
		return TestCaseBase.envFlag;
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		try {
			initFile();
			updateReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initFile() throws Exception {

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		Date d = new Date();
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DAY_OF_MONTH, -2);
//		String currentTime = sdf.format(d);
//		String oldTimeString = sdf.format(calendar.getTime());
		newFile = reportName + ".xlsx";
//		String oldFile = reportName + oldTimeString + ".xlsx";
		
		newf = new File(pathString, newFile);
//		oldf = new File(pathString, oldFile);
		tempf = new File(pathString, tempFile);

		if (newf.exists() || tempf.exists()) {
			newf.delete();
//			oldf.delete();
			tempf.delete();
		}

		fos = new FileOutputStream(tempf);
		fis = new FileInputStream(pathString + "/" + mode);
		Workbook tempFile = new XSSFWorkbook(fis);
		tempFile.write(fos);
		fos.close();
		fis.close();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		try {
			log.info(arg0.getName()+". This test case failed!");
			updateCaseHaveRunSheet(arg0, "Failed");
			updateReport();
			updateFailAnalysisSheet(arg0);
			updateReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateFailAnalysisSheet(ITestResult arg0) throws Exception {
		fos = new FileOutputStream(tempf);
		fis = new FileInputStream(newf);
		FileInputStream fis2 = new FileInputStream(pathString + "/" + defectList);
		Workbook file = new XSSFWorkbook(fis);
		Workbook fileDefect = new XSSFWorkbook(fis2);
		Sheet defectList = fileDefect.getSheet(FailAnalysis);
		Sheet lastResult = fileDefect.getSheet(LastResult);
		Sheet failAnalysis = file.getSheet(FailAnalysis);
		Row row;
		// Update Fail Analysis sheet
		try {		
			String name = getCaseName(arg0);
			String failedCode = getFailedCode(arg0);
			String packagname=getPackageName(arg0);
			row = failAnalysis.createRow(failAnalysis.getLastRowNum() + 1);
			int flag = searchIssue(lastResult, arg0);
			if (flag > -1) {
				for (int k = 0; k < header.length - 1; k++) {
					row = copyRow(lastResult.getRow(flag),row);
					log.info(row.getCell(0).getStringCellValue());
				}
			} else {				
				flag = searchIssue(defectList, arg0);
				log.info("flag "+flag);
				if (flag > -1){
					row = copyRow(defectList.getRow(flag),row);
				}
				else{
					log.info(name);
					row.createCell(0).setCellValue(name);
					row.createCell(4).setCellValue(failedCode);
					row.createCell(5).setCellValue(packagname);
				}
			}
			
			//Update failed class
			IdentityHashMap<Object, Object> failInfoHashMap = new IdentityHashMap<>();
			failInfoHashMap = getFailedClass(arg0);
			Iterator<Entry<Object, Object>> iterator = failInfoHashMap.entrySet().iterator();
	        String fileInfo = "";
	        while(iterator.hasNext()) {
	        	Entry<Object, Object> entry = iterator.next();	        	
	        	fileInfo += entry.getKey() + " " + entry.getValue() + "\n";
	        }
	        row.createCell(4).setCellValue(fileInfo);
	        
			file.write(fos);
		} finally {
			fos.close();
			fis.close();
		}
	}
	
	private Row copyRow(Row from, Row to){
		for (int k = 0; k < header.length - 1; k++) {
		
			if(from.getCell(k)==null){
				continue;
			}
			to.createCell(k).setCellValue(from.getCell(k).getStringCellValue());
			}
		return to;
	}
	
	private int getTotalNum(Sheet sheet){
		int num = sheet.getLastRowNum();
		for(int i = sheet.getLastRowNum(); i > 0; i-- ){
			if(sheet.getRow(i) == null){
				num--;
			}
		}
		return num;
	}
	
	private int searchIssue(Sheet sheet, ITestResult arg0){
		String name = getCaseName(arg0);
		String failedCode = getFailedCode(arg0);
		int flag = -1;
		log.info("last row num "+getTotalNum(sheet));
		for (int i = 1; i <= getTotalNum(sheet); i++) {
			
			if(sheet.getRow(i).getCell(4) != null){
				String	exceptionInTemp = sheet.getRow(i).getCell(4).getStringCellValue();
				String caseNameInTemp = sheet.getRow(i).getCell(0).getStringCellValue();
				if (failedCode.equals(exceptionInTemp) && name.equals(caseNameInTemp) && !exceptionInTemp.isEmpty()) {
					flag = i;
					break;
				}
			}
		}
		
		return flag;
	}

	private String getFailedCode(ITestResult arg0) {
		String failedFileName;
		int lineNumber;
		String failedCode = "";
		for (int i = 0; i < arg0.getThrowable().getStackTrace().length; i++){
			if (arg0.getThrowable().getStackTrace()[i].getClassName().contains("com.perficient.test.cat.pccaurora")) {
				failedFileName = getFailedFileName(arg0.getThrowable().getStackTrace()[i].getClassName());
				
//				log.info("Class name"+arg0.getThrowable().getStackTrace()[i].getClassName());
//				log.info("Failed FileName"+failedFileName);
				
				lineNumber = arg0.getThrowable().getStackTrace()[i].getLineNumber();
				
	//			log.info("Line Number"+lineNumber);
				
				try {
					
					failedCode += readAppointedLineNumber(new File(failedFileName), lineNumber);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return failedCode;
	}
	
	private String getPackageName(ITestResult arg0){
		
		String packagename=arg0.getInstanceName().substring(arg0.getInstanceName().indexOf("testcase.")+9,arg0.getInstanceName().indexOf(".TC"));
		log.info("package name is:"+packagename);
		return packagename;
	}
	
	private IdentityHashMap<Object, Object> getFailedClass(ITestResult arg0){
		IdentityHashMap<Object, Object> failInfoHashMap = new IdentityHashMap<>();
		for (int i = 0; i < arg0.getThrowable().getStackTrace().length; i++){
			if (arg0.getThrowable().getStackTrace()[i].getClassName().contains("com.perficient.test.cat.pccaurora")) {
				failInfoHashMap.put(arg0.getThrowable().getStackTrace()[i].getFileName(), arg0.getThrowable().getStackTrace()[i].getLineNumber()+"");
			}
		}
		
		return failInfoHashMap;
	}

	private String readAppointedLineNumber(File sourceFile, int lineNumber) throws Exception {
		FileReader in = new FileReader(sourceFile);
		LineNumberReader reader = new LineNumberReader(in);
		String s = reader.readLine();
		int line = 0;
		try {
			while (s != null) {
				line++;
				if (line == lineNumber) {
					break;
				}
				s = reader.readLine();
			}
			s += reader.readLine();
			s += reader.readLine();
			return s;
		} finally {
			reader.close();
			in.close();
		}
	}

	private String getFailedFileName(String name) {
		name = name.replaceAll("\\.", "/");
		if (name.contains("$")){
			name = name.substring(0, name.indexOf("$"));
		
		name = "src/main/java/" + name + ".java";
		log.info("Failed File Name"+name);
		}
		return name;
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		try {
			addSkippedCase(arg0);
			updateReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addSkippedCase(ITestResult arg0) throws Exception {
		fos = new FileOutputStream(tempf);
		fis = new FileInputStream(newf);
		Workbook file = new XSSFWorkbook(fis);
		try {
			// Update case have run sheet
			Sheet caseHaveRun = file.getSheet(CaseHaveRun);
			Row row = caseHaveRun.createRow(caseHaveRun.getLastRowNum() + 1);
			row.createCell(0).setCellValue(getCaseName(arg0).substring(0, getCaseName(arg0).indexOf("_")));
			row.createCell(1).setCellValue("Skipped");

			// update fail analysis sheet 
			String name = getCaseName(arg0);
			Sheet failAnalysis = file.getSheet(FailAnalysis);
			row = failAnalysis.createRow(failAnalysis.getLastRowNum() + 1);
			row.createCell(0).setCellValue(name);
			row.createCell(3).setCellValue("Skipped");
			file.write(fos);
		} finally {
			fos.close();
			fis.close();
		}
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		try {
			updateCaseHaveRunSheet(arg0, "Passed");
			updateReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateCaseHaveRunSheet(ITestResult arg0, String status) throws Exception {
		fos = new FileOutputStream(tempf);
		fis = new FileInputStream(newf);
		Workbook file = new XSSFWorkbook(fis);
		Sheet caseHaveRun = file.getSheet(CaseHaveRun);
		Row row = caseHaveRun.createRow(caseHaveRun.getLastRowNum() + 1);
		try {
			row.createCell(0).setCellValue(getCaseName(arg0).substring(0, getCaseName(arg0).indexOf("_")));
			row.createCell(1).setCellValue(status);
			file.write(fos);
		} finally {
			fos.close();
			fis.close();
			
		}
	}

	private String getCaseName(ITestResult arg0) {
		String name = arg0.getInstanceName().substring(arg0.getInstanceName().indexOf(".TC") + 1);
/*		log.info("GetInstanceName is:"+arg0.getInstanceName());
		log.info("gettestname is:"+arg0.getTestName());
		log.info("getinstance is:"+arg0.getInstance());*/
		return name;
	}

	private void updateReport() throws Exception {
		fos = new FileOutputStream(newf);
		fis = new FileInputStream(tempf);
		Workbook file = new XSSFWorkbook(fis);
		try {
			file.write(fos);
		} finally {
			fos.close();
			fis.close();
		}
	}
	
	private void moveToOutPut(){
		if(new File("test-output/" + newFile).exists()){
			new File("test-output/" + newFile).delete();
		}
		newf.renameTo(new File("test-output/" + newFile));
	}
}
