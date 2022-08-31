package Backtracking;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Baekjoon_1759 {
	static int L, C;
	static String alpha[];
	static String alphaarr[];
	static ArrayList<String> anslist = new ArrayList<String>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String split[] = br.readLine().split(" ");
		L = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);
		
		alpha = new String[C];
		alphaarr = new String[L];
		
		alpha = br.readLine().split(" ");

		comb(0,0);
		Collections.sort(anslist);
		for(int i=0; i<anslist.size(); i++) {
			System.out.println(anslist.get(i));
		}
	}
	
	private static void comb(int cnt, int start) { //cnt:직전까지 뽑은 순열에 포함된수의 개수, start:시도할 수의 시작위치
      if(cnt == L) {
			String vowel[] = {"a", "e", "i", "o", "u"};
			int vowelcnt = 0;
			for(int i=0; i<vowel.length; i++) {
				if(Arrays.asList(alphaarr).contains(vowel[i])) {
					vowelcnt++;
				}
			}
			//System.out.println(vowelcnt);
			if(vowelcnt>=1 && L-vowelcnt >= 2) {
				String tmp = "";
				for(int i=0; i<L; i++) {
					tmp += alphaarr[i];
				}
				char[] tmpArr = tmp.toCharArray();
				Arrays.sort(tmpArr);
				tmp = new String(tmpArr);
				anslist.add(tmp);
			}
			return;
		}
		
 		for(int i=start; i<C; i++) {
			alphaarr[cnt] = alpha[i];
 			comb(cnt+1, i+1);
		}
		
	}
}
