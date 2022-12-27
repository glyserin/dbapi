// C035384 정세린 
package com.example.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Crawling {

	public static void main(String[] args) {
		Crawling C = new Crawling();
		System.out.println(C.Crawl());
	}
	
	public ArrayList<ArrayList<ArrayList<String>>> Crawl() {
		Document doc = null;
		
		try {
			doc = Jsoup.connect("https://apl.hongik.ac.kr/lecture/dbms").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<ArrayList<ArrayList<String>>> allstudents = new ArrayList<>();  // 전체 학생들 
		Elements unorderedlists = doc.getElementsByClass("n8H08c UVNKR");
		
		ArrayList<String> degrees = new ArrayList<>();  // 전체 학위 이름들
		Elements h2divs = doc.getElementsByClass("CjVfdc");
		
		for (Element h2div : h2divs) {
			String h2divtext = h2div.text();
//			System.out.println(h2divtext);
			if ("PhD Students".equals(h2divtext)) {
		        degrees.add("phd");
			} else if ("Master Students".equals(h2divtext)) {
		        degrees.add("master");
			} else if ("Undergraduate Students".equals(h2divtext)) {
		        degrees.add("undergrad");
			}
		}
//		System.out.println(degrees);
		
		int degreecnt = 0;
		for (Element unorderedlist : unorderedlists) {
			ArrayList<ArrayList<String>> degreestudents = new ArrayList<>();  // 학위별 학생들의 정보를 저장할 list 
			Elements listitems = unorderedlist.getElementsByClass("CDt4Ke zfr3Q");
			for (Element listitem : listitems) {
				String text = listitem.text();
				String[] strArr = text.split(", ");  // 배열에 각각 정보 저장 
				List<String> list = Arrays.asList(strArr);  // 배열을 list로 
				ArrayList<String> arraylist = new ArrayList<>();  // 각 학생들 이름, 이메일, 졸업년도, 학위 
				arraylist.addAll(list);
				arraylist.add(degrees.get(degreecnt));
				degreestudents.add(arraylist);
			}
			allstudents.add(degreestudents);
			degreecnt += 1;

		}
				
//		System.out.println(allstudents.toString());
		return allstudents;
	}
}

