package com.sist.music;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Component;

@Component
public class HadoopManager {
	@Autowired
	private Configuration conf;
	
	@Autowired
	private JobRunner jobRunner;
	
	public void hadoopFileDelete(){
		System.out.println("들어오나?");
		try {
			FileSystem fs=FileSystem.get(conf);
			if (fs.exists(new Path("/music_input_ns1/daum.txt"))) {
				fs.delete(new Path("/music_input_ns1/daum.txt"), true);
			}
			if (fs.exists(new Path("/music_input_ns1/naver.txt"))) {
				fs.delete(new Path("/music_input_ns1/naver.txt"), true);
			}
			if (fs.exists(new Path("/music_output_ns1/"))) {
				fs.delete(new Path("/music_output_ns1/"), true);
			}
			fs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void copyFromLocal(){
		try {
			FileSystem fs=FileSystem.get(conf);
			fs.copyFromLocalFile(new Path("/home/sist/music_data/daum.txt"), new Path("/music_input_ns1/daum.txt"));
			fs.copyFromLocalFile(new Path("/home/sist/music_data/naver.txt"), new Path("/music_input_ns1/naver.txt"));
			fs.close();
			System.out.println("들어오나?2");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void copyToLocal(){
		try {
			FileSystem fs=FileSystem.get(conf);
			fs.copyToLocalFile(new Path("/music_output_ns1/part-r-00000"), new Path("/home/sist/music_data/music_result"));
			
			fs.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void mapReduceExcute(){
		try {
			jobRunner.call();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
}
