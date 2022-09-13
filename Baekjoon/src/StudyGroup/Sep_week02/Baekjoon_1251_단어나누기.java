package StudyGroup.Sep_week02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Baekjoon_1251_단어나누기 {
	static ArrayList<String> list = new ArrayList<>();
	static String word;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		word = br.readLine();
		String[] w = new String[3];
		
		for(int i=1; i<word.length()-1; i++) {
			for(int j=i+1; j<word.length(); j++) {
				w[0] = word.substring(0, i);
				w[1] = word.substring(i, j);
				w[2] = word.substring(j, word.length());
				
				for(int k=0; k<3; k++) {
					for(int l=w[k].length()-1; l>=0; l--) {
						sb.append(Character.toString(w[k].charAt(l)));
					}
				}
				
				list.add(sb.toString());
				sb.setLength(0);
			}
		}
		
		Collections.sort(list);
		System.out.println(list.get(0));
	}
}
