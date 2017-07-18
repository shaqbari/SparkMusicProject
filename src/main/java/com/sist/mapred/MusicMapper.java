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
					res.set(m[a].group());
					context.write(res, one);
				}
			}
			
	}
	
	
	
}




