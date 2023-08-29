package com.prime.generics;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class EmailReporter {


	static String systemPath=System.getProperty("user.dir");
	static File reportFilePath = new File(systemPath+"/SummaryReport.html");

	static String htmlContent;

	/**
	 * This method is used to generate the summary report
	 * @param totalTestCount
	 * @param failedCases
	 * @param passedCases
	 * @param untestedCases
	 * @param time
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static void reportBuilder(String totalTestCount, String failedCases, String passedCases,
			String untestedCases, Map<String, String> time) {

		htmlContent = "<html>\r\n" + "<head>\r\n"
				+ "<style>table {border-collapse: collapse;}table, td, th {border: 1px solid black;}</style>\r\n"
				+ "</head>\r\n" + "<body><left>\r\n" + "<table>\r\n" + "<tr>\r\n" + "<td>\r\n"
				+ "<font size='4' color='Blue'>TestCases : " + totalTestCount + "</font>\r\n"
				+ "&nbsp;</td><td><font size='4' color='Green'>Passed : " + passedCases + "</font>&nbsp;</td>\r\n"
				+ "<td><font size='4' color='Red'>Failed : " + failedCases + "</font>&nbsp;</td>\r\n"
				+ "<td><font size='4' color='Black'>Untested : " + untestedCases + "</font>&nbsp;</td>\r\n"
				+ "</tr>\r\n" + "<tr>\r\n" + "<td colspan='4'><font size='4' color='Black'>Time Taken : "
				+ time.get("hours") + "h " + time.get("minutes") + "m " + time.get("seconds") + "s"
				+ "</font>&nbsp;</td>\r\n" + "</table>\r\n" + "</left>\r\n </body>\r\n" + "</html>";

		try {
			FileUtils.write(reportFilePath, htmlContent);
			System.out.println("Summary report has been Generated");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
