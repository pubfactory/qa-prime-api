package com.prime.tests.FPJ;

public class TetsingJava {
	private static int sizeCount;

	public static void main(String[] args) {
		showContent(51, 50);
	}

	public static void showContent(int actualCount, int filtersizecount) {
		int count = 0;
		if (actualCount > filtersizecount) {
			int size = actualCount / filtersizecount;
			for (int i = 0; i < size; i++) {
				sizeCount = actualCount - filtersizecount;
				actualCount = sizeCount;
				count++;
				if (actualCount < filtersizecount && actualCount>0) {
					count++;
				}
			}
			System.out.println("count :" + count);
		} else if (actualCount == filtersizecount) {
			count++;
			System.out.println("count :" + count);
		}
	}
}
