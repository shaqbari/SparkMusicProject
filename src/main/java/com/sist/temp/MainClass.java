package com.sist.temp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.snu.ids.ha.ma.MExpression;
import org.snu.ids.ha.ma.MorphemeAnalyzer;
import org.snu.ids.ha.ma.Sentence;

public class MainClass {
	public static void main(String[] args) throws Exception{
		// string to analyze
		String string = "다음 뮤직에는 이번에 방금그곡까지 다"
							+ " 열어두었던 장소로 자리매김하고 있는데요. 그런데"
							+ " 다음 뮤직이 갑자기 폐지된다는 소식이 전해진다고 하던데,"
							+"왜 폐지될 예정에 있나요?"
							+"그러면 다음 뮤직이 폐지되면 앞으로 카카오뮤직으로 대체될 것인가요?"
							+"알려주시면 감사하겠습니다. ^^";

		// init MorphemeAnalyzer
		MorphemeAnalyzer ma = new MorphemeAnalyzer();

		// create logger, null then System.out is set as a default logger
		ma.createLogger(null);

		// analyze morpheme without any post processing 
		List ret = ma.analyze(string);

		// refine spacing
		ret = ma.postProcess(ret);

		// leave the best analyzed result
		ret = ma.leaveJustBest(ret);

		// divide result to setences
		List stl = ma.divideToSentences(ret);

		// print the result
		for( int i = 0; i < stl.size(); i++ ) {
			Sentence st = (Sentence) stl.get(i);
			//System.out.println("===>  " + st.getSentence());
			String[] data={"NNG", "NNM", "NNP"};
			Pattern[] p=new Pattern[data.length];
			for (int a = 0; a < p.length; a++) {
				p[a]=Pattern.compile(data[a]);
				
			}
			Matcher[] m=new Matcher[data.length];
			for (int a = 0; a < st.size(); a++) {
				//System.out.println(st.get(a));
				for (int b = 0; b < m.length; b++) {
					m[b]=p[b].matcher(st.get(a).toString());
					if (m[b].find()) {
						System.out.println(st.get(a).toString());
					}
				}
			}
			
			
			/*for( int j = 0; j < st.size(); j++ ) {
				System.out.println(st.get(j));
			}*/
		}

		ma.closeLogger();
		
	}
	
}
