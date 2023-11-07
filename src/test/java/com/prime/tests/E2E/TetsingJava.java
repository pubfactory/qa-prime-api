package com.prime.tests.E2E;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.prime.generics.Helper;

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

public static void main(String[] args) {
        String fileName ="anpr-article-p31.pdf";
         String[] str1 = fileName.split("-");
        List<String>l= Helper.INSTANCE.convertArrayToList(str1);
        System.out.println(Helper.INSTANCE.convertArrayToList(l.get(2).split("pd")).get(0)+"xml");
    }
 
			}

