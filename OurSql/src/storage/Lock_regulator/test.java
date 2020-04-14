package storage.Lock_regulator;

import java.io.File;
import storage.Storage.XMLUtils;

public class test implements Runnable{
    public static void main(String[] args) {
//        for(int i = 0; i < 1000 ; i++){
//            System.out.println("The value of thread 1 is : "+i);
//        }
        Thread t1 = new Thread(new test());
        Thread t2 = new Thread(new test());
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000 ; i++) {
            System.out.println("The value of thread 2 is : " + i);
        }
    }
}
