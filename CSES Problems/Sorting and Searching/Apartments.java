import java.io.*;
import java.util.*;

public class Apartments {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        int[] b = new int[m];

        // read applicants
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i)
            a[i] = Integer.parseInt(st.nextToken());

        // read apartments
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; ++i)
            b[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0, j = 0, ans = 0;
        while (i < n && j < m) {
            if (Math.abs(a[i] - b[j]) <= k) {
                ans++;
                i++;
                j++;
            } else if (a[i] < b[j] - k) {
                i++;
            } else {
                j++;
            }
        }

        sb.append(ans).append('\n');
        System.out.print(sb);
    }
}
