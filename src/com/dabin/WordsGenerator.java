package com.dabin;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @author: 程序员大彬
 * @time: 2022-04-15 00:01
 *
 *  准备阶段：准备 1G 的文件，文件中每一行一个单词，每个单词的大小小于等于 16 字节
 *  数据写道当前工程目录下的文件：data\top100\words.txt
 */
public class WordsGenerator {
    private static Random r = new Random();

    public static void main(String[] args) throws IOException {

        BufferedWriter writer = FileIOUtils.getWriter(".\\data\\top100\\words.txt");

        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
        int m = chars.length;

        for (int i = 0; i < 400000; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < 4 + r.nextInt(12); j++) {
                line.append(chars[r.nextInt(m)]);
            }
            if (line.length() == 0) continue;
            writer.write(line.toString());
            writer.newLine();
        }

        FileIOUtils.closeWriter(writer);

    }
}
