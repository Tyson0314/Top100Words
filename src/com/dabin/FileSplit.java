package com.dabin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author: 程序员大彬
 * @time: 2022-04-15 00:02
 *
 *  第一步：将一个大文件切割成多个小文件
 *  每个小文件存放100000个单词
 *  小文件都写到这个目录下：data\top100\raw_data\
 */
public class FileSplit {

    public void splitFile(String fileName) throws IOException {
        int fileNum = 0;
        String fileSuffix = "data\\top100\\raw_data\\";
        String littleFileName = fileSuffix + fileNum;

        // 记录每个小文件的单词数
        long wordCount = 0;

        BufferedWriter bw = FileIOUtils.getWriter(littleFileName);

        BufferedReader br = FileIOUtils.getReader(fileName);
        String line = null;
        while ((line = br.readLine()) != null) {
            // 如果当前小文件单词数大于100000，那么关闭当前小文件，写下一个小文件
            if (++wordCount >= 100000) {
                bw.flush();
                // 关闭当前小文件的输出流
                FileIOUtils.closeWriter(bw);

                // 拿到下一个小文件的输出流，开始写下一个小文件
                fileNum++;
                littleFileName = fileSuffix + fileNum;
                bw = FileIOUtils.getWriter(littleFileName);
                wordCount = 0;
            }

            bw.write(line);
            bw.newLine();
        }

        bw.flush();

        FileIOUtils.closeReader(br);
    }


    public static void main(String[] args) throws IOException {
        String fileName = "data\\top100\\words.txt";
        new FileSplit().splitFile(fileName);
    }
}
