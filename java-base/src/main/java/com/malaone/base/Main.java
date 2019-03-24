package com.malaone.base;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: xulifei
 * @Date: 15:35 2019/3/4
 * @Description:
 */
public class Main {


    public static void main(String[] args) {
        Buffer buffer = ByteBuffer.allocate(48);
        buffer.rewind();
    }
}
