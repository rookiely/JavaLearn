package genericParadigm;

/**
 * Created by john on 2018/5/27.
 */

class Apple<T>{
    T color;
}

public class Solution extends Apple<Integer>{

    public static void main(String[] args) {
        Apple a = new Apple<>();
        a.color = "red";
        System.out.print(a.color);
    }

}
