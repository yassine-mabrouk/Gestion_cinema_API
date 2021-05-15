package enset.com.gc;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            int k=new Random().nextInt(10);
            System.out.println(k);
        }
    }
}
