package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_4014_활주로건설 {
	static int N, X, map[][], map2[][];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String split[] = br.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			X = Integer.parseInt(split[1]);
			
			map = new int[N][N];
			map2 = new int[N][N];
			
			for(int i=0; i<N; i++) {
				split = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = map2[j][i] = Integer.parseInt(split[j]);
				}
			}
			
			System.out.println("#" + tc + " " + process());
		}
	}
	
	private static int process() {
		int count = 0;
		for(int i=0; i<N; i++) {
			if(makeRoad(map[i])) count++; //수평 활주로 건설체크
			if(makeRoad(map2[i])) count++; //수직 활주로 건설체크
		}
		return count;
	}
	
	// 해당 지형 정보로 활주로 건설이 가능하면 true, 불가능하면 false 리턴
	private static boolean makeRoad(int[] road) {
		int beforeHeight = road[0], size = 0;
		int idx = 0;
		
		while(idx<N) {
			if(beforeHeight == road[idx]) { //동일 높이
				size++;
				idx++;
			}else if(beforeHeight+1 == road[idx]) { //이전보다 1 높음 : 오르막 경사 설치 체크
				if(size<X) return false; //X길이 미만이면 활주로 건설 불가
				
				beforeHeight++;
				size = 1;
				idx++;
			}else if(beforeHeight-1 == road[idx]) { //이전보다 1 낮음 : 내리막 경사 설치 체크
				int count = 0;
				for(int k=idx; k<N; k++) {
					if(road[k] != beforeHeight-1) return false;
					
					if(++count == X) break;
				}
				
				if(count < X) return false;
				
				beforeHeight--;
				idx += X;
				size = 0;
			}else { // 높이가 2이상 차이
				return false;
			}
		}
		
		return true;
	}
}
