package StudyGroup.Sep_week02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon_1722_순열의순서 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long [] f = new long[21];
        boolean [] c = new boolean[21]; //n엔 중복된 수가 없으므로 중복을 없앨 boolean 배열
        Arrays.fill(f, 1);
        
        for(int i=1; i<=20; i++) { //팩토리얼 구하기
            f[i] = f[i-1]*i;
        }
        
        
        int n = sc.nextInt();
        int what = sc.nextInt();
        
        int [] a = new int[n];
        if(what == 2) { //몇 번째 순열인지
            for(int i=0; i<n; i++)
                a[i] = sc.nextInt();
            
            long ans = 1; //순열은 1 번째 부터 시작
            for(int i=0; i<n; i++) {
                for(int j=1; j<a[i]; j++) {
                    if(!c[j])
                        ans += f[n-i-1];
                }
                c[a[i]]=true;
            }
            System.out.println(ans);
        }
        else if(what == 1) { //k 번째 순열 출력
            long k = sc.nextLong();
            for(int i=0; i<n; i++) {
                for(int j=1; j<=n; j++) {
                    if(c[j])
                        continue;
                    if(f[n-i-1] < k) {
                        k -= f[n-i-1];
                    }
                    else {
                        a[i] = j;
                        c[j] = true;
                        break;
                    }
                }
            }
            for(int i=0; i<n; i++) {
                System.out.print(a[i] + " ");
            }
        }
    }
}
