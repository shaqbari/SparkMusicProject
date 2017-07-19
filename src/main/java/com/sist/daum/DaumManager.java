package com.sist.daum;

import java.io.FileWriter;
import java.net.*;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class DaumManager {
	public void daumReviewManager(String title){
		try {
			String data="";
			for (int i = 0; i < 3; i++) {
				data+=daumReviewData(i, title);
				
			}
			FileWriter fw=new FileWriter("/home/sist/music_data/daum.txt");
			fw.write(data.replaceAll("[^가-힣 ]", ""));//한글과 공백!이외에는 지운다.
			fw.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public String daumReviewData(int i, String title){
		String review="";
		try {
			// https://apis.daum.net/search/blog
			String key="61ffeb36aeadc7fdf0fc2e572f1b462c";
			String strUrl="https://apis.daum.net/search/blog?apiKey="+key+"&result=20&output=xml&pageno="+i+"&q="+URLEncoder.encode(title, "UTF-8");
			URL url=new URL(strUrl);
			JAXBContext jc=JAXBContext.newInstance(Channel.class);
			Unmarshaller un=jc.createUnmarshaller();
			Channel channel=(Channel) un.unmarshal(url);
			List<Item> list=channel.getItem();
			for (Item item : list) {
				review+=item.getDescription()+"\n";
				
			}
			review=review.substring(0, review.lastIndexOf("\n"));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return review;
	}
}
