package com.prime.generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import io.qameta.allure.Allure;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class ZipOperations {
	ZipParameters parameters = new ZipParameters();
	static File zipFilePath;
	static BasePage basePage;

	public ZipOperations() {
		// TODO Auto-generated constructor stub
		try {
			basePage = new BasePage(WebDriverManager.getDriver());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to check directory or file present in specified folder
	 * 
	 * @param subDirPath
	 * @exception Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static void getSubdirs(File subDirPath) throws Exception {
		try {
			for (File currentFile : subDirPath.listFiles()) {
				if (currentFile.isDirectory()) {
					getSubdirs(currentFile.getAbsoluteFile());
				} else if (currentFile.isFile()) {
					System.out.println(currentFile.getName());
					System.out.println(currentFile.getPath());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to get the sub directories xml file path
	 * 
	 * @param subDirPath
	 * @return String
	 * @exception Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static String getSubdirsXml(File subDirPath) throws Exception {
		String path = null;
		try {
			for (File currentFile : subDirPath.listFiles()) {
				if (currentFile.isDirectory()) {
					getSubdirsXml(currentFile.getAbsoluteFile());
				} else if (currentFile.isFile() && currentFile.getPath().endsWith("XML")
						|| currentFile.getPath().endsWith("xml")) {
					path = currentFile.getPath();
					return path;
				}
			}
			return path;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * This method is used to extract the zip files and return file path
	 * 
	 * @param zipPath
	 * @return String
	 * @exception Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static String extractZipFiles(String zipPath) throws Exception {
		String path = null;
		try {
			zipFilePath = new File(zipPath);
			System.out.println(FilenameUtils.removeExtension(zipFilePath.getName()));
			ZipFile zipfile = new ZipFile(zipFilePath.getParent() + "/" + zipFilePath.getName());
			System.out.println(zipFilePath.getParent() + "\\" + FilenameUtils.removeExtension(zipFilePath.getName()));
			zipfile.extractAll(zipFilePath.getParent() + "\\" + FilenameUtils.removeExtension(zipFilePath.getName()));
			path = zipFilePath.getParent() + "\\" + FilenameUtils.removeExtension(zipFilePath.getName());
			// getSubdirs(new File(zipFilePath.getParent() + "\\" +
			// FilenameUtils.removeExtension(zipFilePath.getName())));
			// modifyXml(getSubdirsXml(new File(zipFilePath.getParent() + "\\" +
			// FilenameUtils.removeExtension(zipFilePath.getName()))), "//PRODUCT_TYPE",
			// "NEWTEST");
			zipFilePath.delete();
			Helper.INSTANCE.logEventInfoToReport(WebDriverManager.getDriver(), "ExtractZipFiles",
					"Old zip file has  been deleted");
			return path;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getMessage();
			Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "error", "ExtractZipfiles", e.getMessage());
		}
		return path;

	}

	/**
	 * This method is used to get the Main package name
	 * 
	 * @param mainPkgPath
	 * @return String
	 * @exception Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static String getMainPackageName(File mainPkgPath) throws Exception {
		try {
			for (File currentFile : mainPkgPath.listFiles()) {
				if (currentFile.isFile()) {
					return currentFile.getName();
				}
			}
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "Fail", "Zip Main package ", e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * This method is used to clean the Folder
	 * 
	 * @param path
	 * @exception Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static void cleanFolders(String path) throws Exception {
		FileUtils.cleanDirectory(new File(path + "/"));
	}

	/**
	 * This method is used to get the file path using sub directly path, file type
	 * and option as an argument
	 * 
	 * @param subDirPath
	 * @param filetype
	 * @param option
	 * @return String
	 * @exception Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static String getFilePath(File subDirPath, String filetype, String option) throws Exception {
		String getfilePath = null;
		try {
			for (File currentFile : subDirPath.listFiles()) {
				if (currentFile.isFile()) {
					if (FilenameUtils.getExtension(currentFile.getName()).equalsIgnoreCase(filetype)) {
						getfilePath = currentFile.getAbsolutePath();
					}
				} else if (currentFile.isDirectory()) {
					if (currentFile.getName().contains(filetype) || currentFile.getName().equalsIgnoreCase(filetype)) {
						getfilePath = currentFile.getAbsolutePath();
						break;
					} else {
						getFilePath(currentFile.getAbsoluteFile(), filetype, option);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getfilePath;
	}

	/**
	 * This method is used to modify the Xml file
	 * 
	 * @param xmlFilePath
	 * @param xpath
	 * @param Field
	 * @param value
	 * @exception Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static void modifyXml(String xmlFilePath, String xpath, String Field, String value) throws Exception {
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

			builderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			builderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			// added on 27-07-2021 for drop schema validation of spring xml
			builderFactory.setFeature("http://xml.org/sax/features/namespaces", false);
			builderFactory.setFeature("http://xml.org/sax/features/validation", false);

			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new File(xmlFilePath));

			// Get a node using XPath
			XPath xPath = XPathFactory.newInstance().newXPath();
			// String expression = "//salary";
			Node node = (Node) xPath.evaluate(xpath, document, XPathConstants.NODE);

			// Set the node content
			System.out.println("value to input into XML----->" + value);
			node.setTextContent(value);
			// Write changes to a file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			if (xmlFilePath.contains("BIND")) {

				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, document.getDoctype().getSystemId());
				transformer.transform(new DOMSource(document), new StreamResult(new File(xmlFilePath)));
			} else if (xmlFilePath.contains("SDE-IP")) {
				transformer.transform(new DOMSource(document), new StreamResult(new File(xmlFilePath)));
			} else if (xmlFilePath.contains("UGATE")) {
				transformer.transform(new DOMSource(document), new StreamResult(new File(xmlFilePath)));
			} else {
				transformer.transform(new DOMSource(document),
						new StreamResult(new File(xmlFilePath).getParent() + "\\" + "order_mps_" + value + ".xml"));
				new File(xmlFilePath).delete();
			}
			Allure.step(Field + " value modified to => " + value + " in XML Location " + xmlFilePath);
		} catch (Exception e) {
			Assert.fail("Error while modifying XML File: " + xmlFilePath + " xpath " + xpath + " with value as => "
					+ value);
		}

	}

	/**
	 * This method is used to get the XML element value by using Xpath
	 * 
	 * @param xmlFilePath
	 * @param xpath
	 * @return String
	 * @exception Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static String getXMLElementValueByXPath(String xmlFilePath, String xpath) throws Exception {
		String textNode = null;
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

			builderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			builderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new File(xmlFilePath));

			// Get a node using XPath
			XPath xPath = XPathFactory.newInstance().newXPath();
			// String expression = "//salary";
			Node node = (Node) xPath.evaluate(xpath, document, XPathConstants.NODE);

			textNode = node.getTextContent();

			Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "pass", xmlFilePath,
					xpath + " xpath value: " + textNode);
		} catch (Exception e) {
			e.printStackTrace();
			Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "error", xmlFilePath + xpath,
					e.getMessage());
		}
		return textNode;
	}

	/**
	 * This method is used to archive the file(zip file)
	 * 
	 * @param path
	 * @exception Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static void archiveFile(String path) throws Exception {

		File zipFilePath;
		ZipParameters parameters = new ZipParameters();
		ZipFile zipfile;
		try {
			zipFilePath = new File(path);
			zipfile = new ZipFile(zipFilePath.getParent() + "/" + zipFilePath.getName());

			// set compression method to store compression
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

			// Set the compression level
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			parameters.setIncludeRootFolder(false);
			// Add folder to the zip file
			zipfile.addFolder(zipFilePath.getParent() + "\\" + FilenameUtils.removeExtension(zipFilePath.getName()),
					parameters);
			Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "pass", "ArchiveFile",
					"File has been archived" + zipFilePath.getParent() + "\\"
							+ FilenameUtils.removeExtension(zipFilePath.getName()));
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "error", "ArchiveFile", e.getMessage());
		}
	}

}