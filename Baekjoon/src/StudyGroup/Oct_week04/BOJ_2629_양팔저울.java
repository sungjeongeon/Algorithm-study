package StudyGroup.Oct_week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2629_양팔저울 {
	static int chuNum, marbleNum;
	static int chu[], marble[];
	static boolean isCheck[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		chuNum = Integer.parseInt(br.readLine());

		chu = new int[chuNum];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < chuNum; i++) {
			chu[i] = Integer.parseInt(st.nextToken());
		}

		marbleNum = Integer.parseInt(br.readLine());

		marble = new int[marbleNum];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < marbleNum; i++) {
			marble[i] = Integer.parseInt(st.nextToken());
		}

		isCheck = new boolean[chuNum + 1][40001];
		dp(0, 0);

		for (int i = 0; i < marbleNum; i++) {
			boolean isPossible = false;
			for (int j = 0; j < chuNum + 1; j++) {
				if (isCheck[j][marble[i]]) {
					System.out.print("Y ");
					isPossible = true;
					break;
				}
			}
			if (!isPossible)
				System.out.print("N ");
		}

	}

	private static void dp(int n, int weight) {
		if (weight < 0 || weight > 40000)
			return;
		if (isCheck[n][weight])
			return;
		isCheck[n][weight] = true;
		if (n == chuNum)
			return;

		dp(n + 1, weight + chu[n]); // 현재 무게를 더하는 경우
		dp(n + 1, weight); // 더하지 않는 경우
		dp(n + 1, (weight < chu[n]) ? chu[n] - weight : weight - chu[n]);
	}
}
