package com.sist.review;

import org.springframework.stereotype.Component;

@Component
public class DataDirectory {
	public String[] dataDir1(){
		String[] data={"봄", "여름", "가을",	 "겨울", "화창한날", "아침",
				"오후", "저녁", "밤/새벽", "비/흐림", "크리스마스", "눈오는날"};
		
		return data;
	}
	
	public String[] dataDir2(){
		String[] data={"사랑/기쁨", "이별/슬픔", "스트레스/짜증", "우울할때", "멘붕/불안", "외로울때"};
		
		return data;
	}
}
