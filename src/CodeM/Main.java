package CodeM;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by john on 2018/6/7.
 */


public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat( "0.00");
        int n, m, sumY = 0;
        double sumT = 0;
        n = in.nextInt();
        m = in.nextInt();
        for (int i = 0; i < n; i++) {
            int pv = in.nextInt();
            int flag = in.nextInt();
            if (flag == 1) {
                sumT += pv * 0.8;
                sumY += pv;
            } else {
                sumT += pv;
                sumY += pv;
            }
        }
        int maxM = 0;
        int maxJ = 0;
        int sumZ;
        for (int i = 0; i < m; i++) {
            int mv = in.nextInt();
            int jv = in.nextInt();
            if (mv >= maxM && sumY >= mv) {
                if (jv > maxJ) {
                    maxM = mv;
                    maxJ = jv;
                }
            }
        }
        sumZ = sumY - maxJ;
        System.out.print(df.format(sumT < sumZ ? sumT : sumZ));
    }
}
