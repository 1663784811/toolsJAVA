package cn.cyyaw.util.http;

public class HttpTest {


    public static void main(String args[]) {


        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new RquestTest(i + ""));
            thread.start();

            System.out.println("==============开启线程== " + i + "===========");
        }
    }

}
