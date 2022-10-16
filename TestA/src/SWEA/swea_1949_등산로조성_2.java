package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//등산로 
public class swea_1949_등산로조성_2 {
    static int N, K, max, ans;
    static int[][] map;
    static int[] dx = { -1, 0, 0, 1 };
    static int[] dy = { 0, -1, 1, 0 };
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("res/input_swea_1949.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            max = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    max = Math.max(map[i][j], max);
                }
            }
            ans = 0;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == max) {
                        visited[i][j] = true;
                        dfs(i, j, max, 1, 1);
                        visited[i][j] = false;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int x, int y, int height, int cut, int len) {
        for (int i = 0; i < 4; i++) {
            ans = Math.max(ans, len);

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                if (height <= map[nx][ny]) {
                    if (cut == 1) {
                        //for (int j = 1; j <= K; j++) {
                            //if (height > (map[nx][ny] - j)) {
                                //map[nx][ny] -= j;
                                dfs(nx, ny, height - 1, cut - 1, len + 1);
                                //map[nx][ny] += j;
                            //}
                        //}
                    }
                } 
                else {
                    visited[nx][ny] = true;
                    dfs(nx, ny, map[nx][ny], cut, len + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }
}