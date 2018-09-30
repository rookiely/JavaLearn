package collection;

import java.util.LinkedHashSet;
import java.util.Set;

public class CollectionSet {
    public static void main(String[] args) {
        /**
         * LinkedHashSet可以保证Set的存储顺序是按照加入的顺序
         * 而HashSet不行
         */
        Set<Integer> set = new LinkedHashSet<>();
        set.add(3);
        set.add(2);
        set.add(8);
        set.add(1);
        System.out.print(set.iterator().next());
    }
}