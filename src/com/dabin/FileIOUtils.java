package com.dabin;

import java.io.*;

/**
 * @author: 程序员大彬
 * @time: 2022-04-15 00:01
 */
public class FileIOUtils {

    /**
     * 拿到指定文件的输入流，并包装成文件 BufferedReader
     */
    public static BufferedReader getReader(String name) {
        try {
            System.out.println(name);
            FileInputStream inputStream = new FileInputStream(name);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            return br;
        } catch (IOException e) {
            throw new RuntimeException("getReader IOException", e);
        }
    }

    /**
     * 拿到指定文件的输出流，并包装成文件 BufferedWriter
     */
    public static BufferedWriter getWriter(String name) {
        try {
            System.out.println("getWriter filename " + name);
            File file = new File(name);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(name);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            return bw;
        } catch (IOException e) {
            throw new RuntimeException("getWriter IOException", e);
        }
    }

    public static void closeReader(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException("closeReader IOException", e);
            }
        }
    }

    public static void closeWriter(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("closeWriter IOException", e);
            }
        }
    }
}
