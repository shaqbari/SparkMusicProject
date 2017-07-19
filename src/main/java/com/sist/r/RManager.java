package com.sist.r;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

@Component
public class RManager {
	public void barchart() {
		try {
			RConnection rc=new RConnection();
			//out.write voidEval() ==> 결과값가져올때는 eval()
			rc.voidEval("data<-read.table(\"/home/sist/music_data/music_result\")");
			rc.voidEval("png(\"/home/sist/bigdataDev2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMusicProject/main/barchart.png\")");
			rc.voidEval("barplot(data$V2, names.arg=data$V1, col=rainbow(6))");
			rc.voidEval("dev.off()");
			rc.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void wordcloud() {
		try {
			RConnection rc=new RConnection();
			rc.voidEval("library(rJava)");
			rc.voidEval("library(KoNLP)");
			rc.voidEval("library(wordcloud)");
			rc.voidEval("data<-readLines(\"/home/sist/music_data/naver.txt\")");
			rc.voidEval("png(\"/home/sist/bigdataDev2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMusicProject/main/wordcloud.png\", width=450, height=450)");
			rc.voidEval("data2<-sapply(data, extractNoun, USE.NAMES = F)");
			rc.voidEval("data3<-unlist(data2)");
			rc.voidEval("data4<-Filter(function(x){nchar(x)>=2}, data3)");
			rc.voidEval("data5<-table(data4)");
			rc.voidEval("wordcloud(names(data5), freq = data5, scale = c(5, 1), rot.per = 0.25, min.freq = 1, random.order = F, random.color = T, colors = rainbow(15))");
			rc.voidEval("dev.off()");
			rc.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}
	
}
