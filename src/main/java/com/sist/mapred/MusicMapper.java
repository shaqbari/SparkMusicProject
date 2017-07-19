package com.sist.mapred;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.*;

public class MusicMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final IntWritable one=new IntWritable(1);
	private Text res=new Text();

	
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
			FileReader fr=new FileReader("/home/sist/music_data/emotion.txt");
			int i=0;
			String data="";
			String[] mydata={"사랑/기쁨", "이별/슬픔", "스트레스/짜증", "우울할때", "멘붕/불안", "외로울때"};
			while ((i=fr.read())!=-1) {
				data+=String.valueOf((char)i);
			}
			String[] feel=data.split("\n");
			Pattern[] p=new Pattern[feel.length];
			for (int a = 0; a < p.length; a++) {
				p[a]=Pattern.compile(feel[a]);
				
			}
			Matcher[] m=new Matcher[feel.length];
			for (int a = 0; a < p.length; a++) {
				m[a]=p[a].matcher(value.toString());
				while (m[a].find()) {
					if (a>0&&a<=10) {
						res.set(mydata[0]);
					}else if (a>=11 && a<=18) {
						res.set(mydata[1]);
					}else if (a>=19&& a<=31) {
						res.set(mydata[2]);
					}else if (a>=32&& a<=34) {
						res.set(mydata[3]);
					}else if (a>=35&& a<=40) {
						res.set(mydata[4]);
					}else if (a>=41) {
					res.set(mydata[5]);
				}
					context.write(res, one);
				}
			}
			
	}
	
	
	
}




