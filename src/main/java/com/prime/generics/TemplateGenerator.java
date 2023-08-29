package com.prime.generics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class TemplateGenerator {

	private String SUMMARYREPORTFILENAME="D:\\TeamCity\\config\\_notifications\\email\\Summaryreport.html";;

	/**
	 * This method used to generate summary report with passed, failed and untested test case status
	 * @param total
	 * @param failed
	 * @param passed
	 * @param untested
	 * @param time
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public void ftlGenerator(String total, String failed, String passed, String untested, Map<String, String> time) {
		Configuration cfg = new Configuration();
		Writer out = null;
		Writer file = null;
		File existingReport;
		try {
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
			cfg.setWrapUncheckedExceptions(true);
			cfg.setDirectoryForTemplateLoading(new File("D:\\TeamCity\\config\\_notifications\\email"));
			Template build_successful = cfg.getTemplate("QAsummaryreport.ftl");
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("Total", total);
			data.put("Failed", failed);
			data.put("Passed", passed);
			data.put("Untested", untested);
			data.put("Time", time.get("hours") + "h " + time.get("minutes") + "m " + time.get("seconds") + "s");

			// Console output
			out = new OutputStreamWriter(System.out);
			build_successful.process(data, out);
			existingReport = new File(SUMMARYREPORTFILENAME);
			if (existingReport.exists())
			{
				existingReport.delete();
				System.out.println("old Summaryreport is deleted");
			
				file = new FileWriter(new File(SUMMARYREPORTFILENAME));
			build_successful.process(data, file);
			System.out.println("New Summaryreport is generated");
			}
			else 
			{
				System.out.println("old Summaryreport is not there");
				file = new FileWriter(new File(SUMMARYREPORTFILENAME));
				build_successful.process(data, file);
				System.out.println("New Summaryreport is generated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				file.flush();
				file.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
