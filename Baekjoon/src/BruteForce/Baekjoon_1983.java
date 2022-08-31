package BruteForce;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon_1983 {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("jeongeon/input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        for(int tc=1; tc<=T; tc++) {
            sb.append("#" + tc + " ");

            //n명의 학생과 k번째 학생
            String nk[] = in.readLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);

            // n명의 학생 점수 한줄씩 입력
            double score[] = new double[n];
            for (int i = 0; i < n; i++) { //학생들의 점수 계산
                String scores[] = in.readLine().split(" "); //한줄씩
                double score1 = Double.parseDouble(scores[0]);
                double score2 = Double.parseDouble(scores[1]);
                double score3 = Double.parseDouble(scores[2]);
                score[i] = score1 * 0.35 + score2 * 0.45 + score3 * 0.2;
            }

            double st = score[k-1]; //정답인 학생의 점수
            int stIndex = 0; //정답인 학생의 인덱스
            String credit[] = {"D0", "C-", "C0", "C+", "B-", "B0", "B+", "A-","A0","A+"};
            Arrays.sort(score);
            for (int i = 0; i < score.length; i++) {
                if (st == score[i]) { //정답인 학생의 인덱스 찾기
                    stIndex = i;
                }
            }
            sb.append(credit[stIndex / (n/10)]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
