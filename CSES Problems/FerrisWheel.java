import java.io.*;
import java.util.*;

public class FerrisWheel {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        long[] w = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i)
            w[i] = Long.parseLong(st.nextToken());

        Arrays.sort(w);

        int i = 0, j = n - 1;
        int count = 0;

        while (i <= j) {
            if (w[i] + w[j] <= x) {
                i++;
                j--;
            } else {
                j--;
            }
            count++;
        }

        sb.append(count).append('\n');
        System.out.print(sb);
    }
}
