package com.foo.common.base.utils;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FooUtilsIOHelper {
	public static void main(String[] args) throws Exception {

		File input = new File(
				"F:\\SkyDrive/javascript/ajaxSource/hellokitty.html");

		Document doc = Jsoup.parse(input, "UTF-8");

		Elements pngs = doc.select("table#archiveResult tr:gt(0)");

		for (Element element : pngs) {
			System.out.println(element.select("td.name").text());
			System.out.println(element.select("a:eq(1)").attr("href"));
		}

	}
}
