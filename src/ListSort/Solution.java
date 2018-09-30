package ListSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by john on 2018/5/27.
 */

class TimeNode implements Comparable<TimeNode>{
    double beginT;
    double endT;

    TimeNode(double begin, double end) {
        beginT = begin;
        endT = end;
    }


    @Override
    public int compareTo(TimeNode o) {
        if (this.beginT > o.beginT) {
            return 1;
        } else if (this.beginT < o.beginT) {
            return -1;
        } else {
            if (this.endT > o.endT) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}

public class Solution {

    private static List<TimeNode> list = new ArrayList<>();

    public static void main(String[] args) {
        List<TimeNode> res = new ArrayList<>();
        list.add(new TimeNode(2, 4));
        list.add(new TimeNode(2.15, 3.15));
        list.add(new TimeNode(4.15, 5));
        list.add(new TimeNode(1, 3));
        Collections.sort(list);
        double E = 0;
        for (TimeNode n : list) {
            if (n.beginT > E) {
                res.add(new TimeNode(E,n.beginT));
                E = n.endT;
            }else{
                if (n.endT > E) {
                    E = n.endT;
                }
            }
        }
        if (E < 24) {
            res.add(new TimeNode(E,24));
        }
        for (TimeNode n : res) {
            System.out.println(n.beginT+" "+n.endT);
        }
    }
}
