package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class swea_5658_보물상자비밀번호 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_5658.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			String arr = br.readLine();
			List<Integer> list = new ArrayList<>();
			for(int i=0; i<N/4; i++) {
				int start = 0;
				int end = N/4;
				for(int j=0; j<4; j++) {
					String hex = arr.substring(start, end);
					int num = Integer.parseInt(hex, 16); //16진수 -> 10진수로 바꾸기
					if(!list.contains(num)) list.add(num);
					start = end;
					end += N/4;
				}
				arr = arr.charAt(N-1) + arr.substring(0,N-1);
			}
			Collections.sort(list);
			sb.append("#" + tc + " " + list.get(list.size()-K) + "\n");
		}
		System.out.print(sb);
	}
}
