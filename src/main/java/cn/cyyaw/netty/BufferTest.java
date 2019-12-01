package cn.cyyaw.netty;

import java.nio.IntBuffer;

public class BufferTest {


    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i);
        }


    }


}
