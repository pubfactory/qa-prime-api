package com.prime.pageFactory.pages.fpj;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		List<String> list1 = new ArrayList<>();
		list.add("Article by Yukiko Nishioka");
		list.add("Article by dsko Nishioka");
		list.add("Article by vdssado Nishsadsad");
		list.add("Article by asfo Nishsadsad");
		for (String a : list) {
			list1.add(a.substring(11, a.length()));
		}
		System.out.println(list1);
	}
}
