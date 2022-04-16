package com.dabin;

import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * @author: 程序员大彬
 * @time: 2022-04-15 00:06
 *
 * 第四步：利用 100 大小的小顶堆得到出现频率 top 100 的单词
 * 到这里，经过外部排序后，文件中所有的单词都是有序的，相同的单词都是挨着的
 * 所以，可以顺序遍历有序的文件，得到出现频率 top 100 的单词
 */

public class TopWords {

    class Pair {
        String word;
        int cnt;

        Pair(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }

    public String[] top100(String fileName) throws Exception {

        // 1. 初始化一个 101 大小的小顶堆，因为在 57 行先添加数量会达到 100 如果数量为 100 会导致小顶堆进行一次 grow
        // 堆中每个节点存放的是单词及其出现的次数
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(101, Comparator.comparingInt(o -> o.cnt));


        // 前一个单词
        String prevWord = null;
        // 前一个单词出现的次数
        int prevCnt = 0;

        BufferedReader br = FileIOUtils.getReader(fileName);
        // 当前的单词
        String currWord = null;
        // 2. 读取有序文件中每一行的单词，然后统计其出现的次数，并保留出现次数 top 100 的单词
        while ((currWord = br.readLine()) != null) {
            // 如果当前的单词和前一个单词不是同一个单词
            // 那么需要处理前一个单词
            if (!currWord.equals(prevWord)) {
                // 如果堆的大小小于 100，那么直接将前一个单词及其出现的次数放入堆中
                Pair pair = new Pair(prevWord, prevCnt);
                if (minHeap.size() < 100) {
                    minHeap.add(pair);
                } else {
                    // 先添加
                    minHeap.add(pair);
                    // 再删除，这样小顶堆会自动将最小的元素移出
                    minHeap.poll();
                }

                // 将当前单词设置为前一个单词
                prevWord = currWord;
                // 前一个单词出现的次数清零
                prevCnt = 0;
            }
            // 统计前一个单词出现的次数
            prevCnt++;
        }

        //3. 最终堆中的 100 个单词就是出现频率最高的单词了，输出即可
        String[] res = new String[100];
        int index = 0;
        while (!minHeap.isEmpty()) {
            res[index++] = minHeap.poll().word;
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        // 这个斜杠兼容 linux 以及 windows, 或者使用 File.separator
        String fileName = "data/top100/sorted_words.txt";
        String[] res = new TopWords().top100(fileName);
        System.out.println(Arrays.toString(res));
    }

}
