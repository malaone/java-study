package com.malaone.io.bio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PushbackReader;
import java.io.StringReader;
import java.io.StringWriter;


/**
 * @Author: xulifei
 * @Date: 15:09 2019/3/29
 * @Description:
 */
public class TestIO2 {
    public static void printStream() throws FileNotFoundException, IOException {
        try (
                FileOutputStream fos = new FileOutputStream("tmp.txt");
                PrintStream ps = new PrintStream(fos)) {
            ps.println("普通字符串\n");
            //输出对象
            ps.println(new FileIO());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("输出完成");

    }
    public static void stringNode() throws IOException {
        String str = "天王盖地虎\n"
                + "宝塔镇河妖\n";
        char[] buf = new char[32];
        int hasRead = 0;
        //StringReader将以String字符串为节点读取数据
        try (StringReader sr = new StringReader(str)) {
            while ((hasRead = sr.read(buf)) > 0) {
                System.out.print(new String(buf, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //由于String是一个不可变类，因此创建StringWriter时，实际上是以一个StringBuffer作为输出节点
        try (StringWriter sw = new StringWriter()) {
            sw.write("黑夜给了我黑色的眼睛\n");
            sw.write("我却用它寻找光明\n");
            //toString()返回sw节点内的数据
            System.out.println(sw.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void keyIn() throws IOException {
        try (
                //InputStreamReader是从byte转成char的桥梁
                InputStreamReader reader = new InputStreamReader(System.in);
                //BufferedReader(Reader in)是char类型输入的包装类
                BufferedReader br = new BufferedReader(reader);
        ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.equals("exit")) {
                    //System.exit(1);
                    break;
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pushback() throws FileNotFoundException, IOException {
        try (PushbackReader pr = new PushbackReader(new FileReader("C:/PROJECT/JavaBasic/PROJECT_JavaBasic/src/io/FileIO.java"),64)) {
            char[] buf = new char[32];
            String lastContent = "";
            int hasRead = 0;
            while ((hasRead = pr.read(buf)) > 0) {
                String content = new String(buf, 0, hasRead);
                int targetIndex = 0;
                if ((targetIndex = (lastContent + content).indexOf("targetIndex = (lastContent + content)")) > 0) {
                    pr.unread((lastContent + content).toCharArray());
                    if (targetIndex > 32) {
                        buf = new char[targetIndex];
                    }
                    pr.read(buf , 0 , targetIndex);
                    System.out.println(new String(buf, 0 , targetIndex));
                    System.exit(0);
                } else {
                    System.out.println(lastContent);
                    lastContent = content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        printStream();
        //stringNode();
        //keyIn();
        //pushback();
    }
}
