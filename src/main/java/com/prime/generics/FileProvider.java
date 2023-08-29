package com.prime.generics;

/**
 * This class is holding all the file path 
 * FOR QA and STAGING ENIVIRONMENT 
 * TESTDATA FILE LOCATION
 */
public class FileProvider {
	
	//QA
	public static final String QA_C1547138_UGATE_XML_FILEPATH=System.getProperty("user.dir") + "\\Assets\\Gates\\C1547138\\UGATE_POD_single.xml";
	public static final String QA_UGATE_POA_FILE_VERIFICATION_UATTNQBOOKS="\\\\SGGS-APP4-QA\\gates\\UGate\\signals\\poa\\UATTNQBOOKS\\";
	public static final String QA_ORDER_UGATES_MANUALDROP_LOCATION="\\\\SGGS-APP4-QA\\gates\\UGate\\order\\manual drop";

	//STAGING
	public static final String STAGING_C1547138_UGATE_XML_FILEPATH=System.getProperty("user.dir") + "\\Assets\\Gates\\C1547138\\UGATE_POD_single.xml";
	public static final String STAGING_UGATE_POA_FILE_VERIFICATION_UATTNQBOOKS="\\\\SGGS-APP4-TEST\\gates\\UGate\\signals\\poa\\UATTNQBOOKS\\";
	public static final String STAGING_ORDER_UGATES_MANUALDROP_LOCATION="\\\\SGGS-APP4-TEST\\gates\\UGate\\order\\manual drop";


}
