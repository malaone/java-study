package com.malaone.io.bio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @Author: xulifei
 * @Date: 15:06 2019/3/29
 * @Description:
 */
public class FileIO {

    public static String file2StrByStream(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(fileName);
            byte[] buf = new byte[1024];
            int readLen;

            //read()返回的是单个字节数据（字节数据可以直接转int类型)
            //read(buf)返回的是读取到的字节数，真正的数据保存在buf中
            while ((readLen = fis.read(buf)) != -1) {
                //每次最多将1024个字节转换成字符串
                //循环次数 = 文件字符数 除以 buf长度
                stringBuilder.append(new String(buf, 0 ,readLen));
                /*
                 * 将字节强制转换成字符后逐个输出，能实现和上面一样的效果。但是如果源文件是中文的话可能会乱码
                for (byte b : buf)    {
                    char ch = (char)b;
                    if (ch != '\r')
                    System.out.print(ch);
                }
                */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

    public static String file2StrByReader() {
        StringBuilder stringBuilder = new StringBuilder();

        try (
            FileReader fr = new FileReader("tmp2.txt")
        ) {
            char[] buf = new char[32];
            int readLen ;
            // 每个char都占两个字节，每个字符或者汉字都是占2个字节，
            // 因此无论buf长度为多少，总是能读取中文字符长度的整数倍，不会乱码
            while ((readLen = fr.read(buf)) > 0) {
                // 如果buf的长度大于文件每行的长度，就可以完整输出每行，否则会断行
                // 循环次数 = 文件字符数 除以 buf长度
                stringBuilder.append(new String(buf, 0, readLen));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static void fileCopy(String sourceFileName, String targetFileName){
        try (
            //在try()中打开文件会在结尾自动关闭
            FileInputStream fis = new FileInputStream(sourceFileName);
            FileOutputStream fos = new FileOutputStream(targetFileName)
        ) {
            byte[] buf = new byte[1034];
            int readLen;
            while ((readLen = fis.read(buf)) > 0) {
                fos.write(buf, 0, readLen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileWriteLine(String fileName, String content) {
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(content + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
