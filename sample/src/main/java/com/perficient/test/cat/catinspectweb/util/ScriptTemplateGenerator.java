package com.perficient.test.cat.catinspectweb.util;

import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


public class ScriptTemplateGenerator {


    public static void main(String[] args) throws Exception {

        generate(2422);


    }

    private static final String NAME = "-CASENAME-";
    private static final String USER = "-USER-";
    private static final String DESIGNER = "-DESIGNER-";
    private static final String DATE = "-DATE-";
    private static final String DESC = "-DESCRIPTION-";
    private static final String STEPS = "-STEPS-";
    private static final String GROUP = "-GROUP-";
//	private static final String FILE = "CaseSource/Tests with Design Steps.docx";

    private static XWPFDocument doc;
    private static int caseNum = 1;
    private static int bodyIdx = 1;

    private static List<IBodyElement> bodyElemnts;

    private static XWPFTable tableInfo;

    private static XWPFTable tableDesc;

    private static XWPFTable tableStep;

    private ScriptTemplateGenerator() {
    }

    static {
        try {
            doc = new XWPFDocument(new FileInputStream(new File("CaseSource").listFiles()[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bodyElemnts = doc.getBodyElements();
    }

    private static String getTitle() {
        Iterator<XWPFParagraph> pIter = doc.getParagraphsIterator();
        int count = 0;
        while (pIter.hasNext() && !pIter.next().getText().contains(": " + caseNum + " ")) {
            count++;
        }
        String title = doc.getParagraphs().get(count).getText().split(" - ")[1].replaceAll("[_\\-\\.]|([A-z]{2}_?\\d{3}_?TC\\d{3}_?)", "").trim();
        String[] titleTokens = title.split(" ");
        title = "";
        for (String word : titleTokens) {
            title += StringUtils.capitalize(word.toLowerCase());
        }
        bodyIdx = doc.getPosOfParagraph(doc.getParagraphs().get(count));
        String prifix = "";
        if (caseNum < 100 && caseNum > 9) {
            prifix = "0" + caseNum;
        } else if (caseNum < 10) {
            prifix = "00" + caseNum;
        } else {
            prifix += caseNum;
        }
        return prifix + "_" + title;
    }

    private static String getSteps() {
        List<XWPFTableRow> rs = tableStep.getRows();
        String ret = "";
        String temp1 = "";
        String temp2 = "";
        for (int i = 1; i < rs.size(); i++) {
            temp1 = rs.get(i).getCell(0).getText().trim();
            ret += "\t\t// " + temp1 + ": \n";
            String[] desc = rs.get(i).getCell(1).getText().split("\n");
            for (String d : desc)
                temp2 = d.trim().replaceAll("\"", "\'");
            ret += "\t\t//    " + temp2 + "\n\t\ttest.log(LogStatus.INFO, \"***" + temp1 + ": " + temp2 + "\");";
            ret += "\n\n";
        }
        return ret;
    }

    private static String notifyDesc(String desc) {
        String[] lines = desc.split("\n");
        String ret = "";
        for (String l : lines) {
            ret += l + "<br>\n * ";
        }
        return ret;
    }

    public static void generate(int caseNumber) throws Exception {
        caseNum = caseNumber;
        String title = getTitle();
        String user = System.getProperty("user.name");

        tableInfo = (XWPFTable) bodyElemnts.get(bodyIdx + 1);
        tableDesc = (XWPFTable) bodyElemnts.get(bodyIdx + 3);
        tableStep = (XWPFTable) bodyElemnts.get(bodyIdx + 6);

        String designer = tableInfo.getRow(2).getCell(1).getText();

        String desc = notifyDesc(tableDesc.getRow(1).getCell(0).getText());

        String steps = getSteps();


        BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/perficient/test/cat/pccaurora/util/template"));
        String template = "";
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            template += line + "\n";
        }
        br.close();

        SimpleDateFormat format = new SimpleDateFormat("dd / MMM / YYYY");
        String date = format.format(Calendar.getInstance().getTime());

        String reslt = template
                .replace(NAME, title)
                .replace(DESC, desc)
                .replace(USER, user)
                .replace(DESIGNER, designer)
                .replace(STEPS, steps)
                .replace(DATE, date);
        log.info(reslt);

        String folder = "src/main/java/com/perficient/test/cat/pccaurora/testcase/debug/";
        String fileName = "TC" + title + ".java";
        File dest = new File(folder, fileName);
        if (dest.exists()) {
            log.info("This script exists in the folder.\nDo you want to override it?[Y/n]");
            if (System.in.read() != 'Y') {
                log.info("Action aborted.");
                return;
            }
        }
        FileWriter fw = new FileWriter(dest);
        fw.write(reslt);
        fw.close();
        log.info("Done.");
    }


}
