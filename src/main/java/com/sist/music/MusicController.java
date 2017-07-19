package com.sist.music;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.daum.DaumManager;
import com.sist.manager.MusicManager;
import com.sist.manager.MusicVO;
import com.sist.naver.NaverManager;
import com.sist.r.RManager;
import com.sist.review.DataDirectory;
import com.sist.weather.WeatherManager;
import com.sist.weather.WeatherVO;

@Controller
public class MusicController {

	@Autowired
	private MusicManager mgr;
	
	@Autowired
	private WeatherManager wgr;
	
	@Autowired
	private DataDirectory dd;
	
	@Autowired
	private DaumManager dm;
	
	@Autowired
	private NaverManager nm;
	
	@Autowired
	private HadoopManager hm;
	
	@Autowired
	private RManager rm;
	
	@RequestMapping("main/main.do")
	public String main_main(Model model){
		List<MusicVO> list=mgr.melonTop100();
		List<WeatherVO> wlist=wgr.todayGetWeather();
		
		
		model.addAttribute("feel_data", dd.dataDir1());
		model.addAttribute("genre_data", dd.dataDir2());
		model.addAttribute("list", list);
		model.addAttribute("wlist", wlist);
		model.addAttribute("main_jsp", "default.jsp");
		
		return "main/main";
	}
	
	@RequestMapping("main/music_feel_find.do")
	public String main_feel_find(String feel_data, Model moel){
		System.out.println(feel_data);
		
		return "main/main";
	}
	
	@RequestMapping("main/music_genre_find.do")
	public String main_genre_find(String genre_data, Model moel){
		System.out.println(genre_data);

		return "main/main";
	}
	
	@RequestMapping("main/detail.do")
	public String main_detail(String title, Model model){
		dm.daumReviewManager(title);
		nm.naverBlogData(title);
		nm.naverXmlParse();
		
		hm.hadoopFileDelete();
		hm.copyFromLocal();
		hm.mapReduceExcute();
		hm.copyToLocal();
		
		MusicVO vo=mgr.musicDetailDAta(title);
		model.addAttribute("vo", vo);
		
		rm.barchart();
		rm.wordcloud();
		
		return "main/detail";
	}
	
}























