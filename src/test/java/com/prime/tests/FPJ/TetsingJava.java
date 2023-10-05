package com.prime.tests.FPJ;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class TetsingJava {
//	private static int sizeCount;
//
//	public static void main(String[] args) {
//		showContent(51, 50);
//	}
//
//	public static void showContent(int actualCount, int filtersizecount) {
//		int count = 0;
//		if (actualCount > filtersizecount) {
//			int size = actualCount / filtersizecount;
//			for (int i = 0; i < size; i++) {
//				sizeCount = actualCount - filtersizecount;
//				actualCount = sizeCount;
//				count++;
//				if (actualCount < filtersizecount && actualCount>0) {
//					count++;
//				}
//			}
//			System.out.println("count :" + count);
//		} else if (actualCount == filtersizecount) {
//			count++;
//			System.out.println("count :" + count);
//		}
//	}
	
	public static void main(String[]args) throws IOException {
		File f = new File(System.getProperty("user.dir") + "//target//Assets"); 
	//	File f = new File("C:/PF/PubFactorySuite/PubFactorySuite/qa-prime-e2e/target/Assets");
//		System.out.println("F : "+f);
//		if(f.exists()){
//			System.out.println("available");
//			if (f.delete())
//			{
//				System.out.println(f.getName() + " deleted"); // getting and printing the file name
//			} else {
//				System.out.println("failed");
//			}
//		}
//		else {
//			System.out.println("not available");
//		}
//		
//	}
		FileUtils.deleteDirectory(f);
	}
}
