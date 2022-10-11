package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class swea_2477_차량정비소 {
	static PriorityQueue<Customer> receptionQueue, repairQueue;
	static Customer[] receptionCounter, repairCounter;
	static int[] reception, repair;
	static int N, M, K, A, B, T, ans;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_2477.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String split[] = br.readLine().split(" ");
			N = Integer.parseInt(split[0]); //접수창구의 개수
			M = Integer.parseInt(split[1]); //정비창구의 개수
			K = Integer.parseInt(split[2]); //차량정비소를 방문한 고객 수
			A = Integer.parseInt(split[3]); 
			B = Integer.parseInt(split[4]);
			
			ans = 0;
			
			receptionQueue = new PriorityQueue<>(new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					//고객번호 오름차순
					return o1.customerNo - o2.customerNo;
				}
			});
			
			repairQueue = new PriorityQueue<>(new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					if(o1.receptionEndTime == o2.receptionEndTime) {
						return o1.receptionNo - o2.receptionNo;
					}else {
						return o1.receptionEndTime - o2.receptionEndTime;
					}
				}
			});
			
			receptionCounter = new Customer[N+1];
			repairCounter = new Customer[M+1];
			
			st = new StringTokenizer(br.readLine());
			reception = new int[N+1];
			for(int i=1; i<=N; i++) {
				reception[i] = Integer.parseInt(st.nextToken()); //접수창구의 처리시간
			}
			
			st = new StringTokenizer(br.readLine());
			repair = new int[M+1];
			for(int i=1; i<=M; i++) {
				repair[i] = Integer.parseInt(st.nextToken()); //정비창구의 처리시간
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=K; i++) {
				receptionQueue.offer(new Customer(i, Integer.parseInt(st.nextToken())));
			}
			
			int time = 0;
			int cnt = 0;
			while(true) {
				// 접수 끝난 사람 정비창구로 보내기
				for(int i = 1 ; i <= N ; ++i) {
					if(receptionCounter[i] == null) continue;
					
					Customer c = receptionCounter[i];
					
					if(c.receptionStartTime + reception[i] <= time) {
//						System.out.println("[" + time + "]" + i + "번 접수창구에 " + receptionCounter[i].customerNo + "고객이 접수를 마쳤습니다." );
						c.receptionEndTime = time;
						repairQueue.offer(c);
						receptionCounter[i] = null;
					}
				}
				
				// 접수창구 채우기 
				for(int i = 1 ; i <= N ; ++i) {
					if(receptionCounter[i] == null) {
						if(!receptionQueue.isEmpty()) {
							if(receptionQueue.peek().arriveTime <= time) {
								receptionCounter[i] = receptionQueue.poll();
								receptionCounter[i].receptionNo = i;
								receptionCounter[i].receptionStartTime = time;
//								System.out.println("[" + time + "]" + i + "번 접수창구에 " + receptionCounter[i].customerNo + "고객이 접수를 시작했습니다." );
							}
						}
					}
				}
				
				// 정비 끝난 사람 내보내기
				for(int i = 1 ; i <= M ; ++i) {
					if(repairCounter[i] == null) continue;
					
					Customer c = repairCounter[i];
					
					if(c.repairStartTime + repair[i] <= time) {
//						System.out.println("[" + time + "]" + i + "번 정비창구 " + repairCounter[i].customerNo + "고객이 정비를 마쳤습니다." );
//						System.out.println(c.customerNo + "고객은 " + c.receptionNo + "접수창구와 " + c.repaireNo + "정비창구를 이용했습니다.");
						if(c.receptionNo == A && c.repairNo == B) ans += c.customerNo;
						repairCounter[i] = null;
						cnt++;
					}
				}
				
				if(cnt == K) break;
				
				// 정비창구 채우기
				for(int i = 1 ; i <= M ; ++i) {
					if(repairCounter[i] == null) {
						if(!repairQueue.isEmpty()) {
							repairCounter[i] = repairQueue.poll();
							repairCounter[i].repairNo = i;
							repairCounter[i].repairStartTime = time;
//							System.out.println("[" + time + "]" + i + "번 정비창구 " + repairCounter[i].customerNo + "고객이 정비를 시작했습니다." );
						}
					}
				}
				
				time++;
			}
			
			if(ans == 0) ans = -1;
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static class Customer {
		int customerNo;
		int receptionNo;
		int repairNo;
		int arriveTime;
		int receptionStartTime;
		int receptionEndTime;
		int repairStartTime;
		
		public Customer(int customerNo, int arriveTime) {
			this.customerNo = customerNo;
			this.arriveTime = arriveTime;
		}
	}
}
