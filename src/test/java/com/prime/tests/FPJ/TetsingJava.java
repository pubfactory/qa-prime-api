package com.prime.tests.FPJ;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import io.qameta.allure.Allure;

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

		public static void main(String[]args)  {
		String str = "10.2344%2Fanpr-70-02-06";
				String finalWord = str.substring(str.length()-6, str.length());
				System.out.println( finalWord);
				
			}
}
