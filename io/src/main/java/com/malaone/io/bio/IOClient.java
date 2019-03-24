package com.malaone.io.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Author: xulifei
 * @Date: 17:31 2019/3/18
 * @Description: 客户端每隔两秒发送一个带有时间戳的"hello world"给服务端，服务端收到之后打印
 */
public class IOClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();
    }
}
