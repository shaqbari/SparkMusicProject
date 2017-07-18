package com.sist.manager;

import java.net.URLEncoder;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class MusicManager {
	public static void main(String[] args) {
		MusicManager melonManager=new MusicManager();
		/*String url=melonManager.youtubeGetLink("마지막처럼");
		System.out.println(url);*/
		melonManager.melonTop100();
	}
	
	public List<MusicVO> melonTop100(){
		List<MusicVO> list=new ArrayList<MusicVO>();
		
		try {
			Document doc=Jsoup.connect("http://www.melon.com/chart/index.htm").get();
			
			//div class list
			Elements rank=doc.select("tr.lst50 td.t_left div.wrap span.rank");
			Elements title=doc.select("tr.lst50 td.t_left div.ellipsis strong a");
			Elements singer=doc.select("tr.lst50 td.t_left div.ellipsis a.play_artist span");
			
			for (int a = 0; a < 10; a++) {
				System.out.println(rank.get(a).text()+" ; "+title.get(a).text()+" ; "+singer.get(a).text() );
			
				MusicVO vo=new MusicVO();
				vo.setRank(Integer.parseInt(rank.get(a).text().trim()));
				vo.setTitle(title.get(a).text());
				vo.setSinger(singer.get(a).text());
				vo.setLink(youtubeGetLink(title.get(a).text()));
				System.out.println(vo.getTitle()+"==="+vo.getLink());
				
				list.add(vo);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return list;		
	}
	
	public String youtubeGetLink(String title) {
		String url="";
		
		try {
			//Document doc=Jsoup.connect("https://www.youtube.com/results?search_query="+URLEncoder.encode(title, "UTF-8")).get();
			Document doc=Jsoup.connect("https://www.youtube.com/results?search_query="+title).get();
			Elements te=doc.select("div.yt-lockup-content h3 a");
			Element tElem=te.get(0);
			String temp=tElem.attr("href");
			//System.out.println(temp);// watch?v=ah4DWCUEF0k
			//https://youtube.com/embed/ah4DWCUEF0k
			
			temp=temp.substring(temp.lastIndexOf("=")+1, temp.length());
			//System.out.println(temp);
			
			url="https://youtube.com/embed/"+temp;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return url;
	}
	
}
