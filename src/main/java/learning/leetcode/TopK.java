package learning.leetcode;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class TopK {
    private static final String PATH = "/data/temp/top_k_file.txt";
    private static final long FILE_SIZE = 2 * 1024 * 1024 * 1024L;
    private static final Random RANDOM = new Random();
    private static final String[] WORD = new String[]{
            "apple", "orange", "pear", "banana", "tangerine", "grape", "blueberry", "passion fruit", "watermelon"
    };

    private static PriorityQueue<String[]> queue = new PriorityQueue<String[]>(new Comparator<String[]>() {
        public int compare(String[] m, String[] n) {
            return Integer.parseInt(m[1]) - Integer.parseInt(n[1]);
        }
    });

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        if (!Files.exists(Paths.get(PATH))) {
            Files.createFile(Paths.get(PATH));
        }

//        initFile();
        countWorld(3);

        System.out.println("Done in " + (System.currentTimeMillis() - startTime) + "ms.");
    }

    private static void initFile() throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get(PATH), StandardCharsets.UTF_8, StandardOpenOption.APPEND);

        while (Files.size(Paths.get(PATH)) < FILE_SIZE) {
            int index = RANDOM.nextInt(WORD.length);
            String word = WORD[index];

            bw.write(word);
            bw.newLine();
        }

        bw.close();
    }

    // 建立一个文件名为单词的文件，内容为该单词出现的次数
    private static void countWorld(int k) throws IOException {
        if (k <= 0) {
            return;
        }

        Files.lines(Paths.get(PATH)).forEach(word -> {
            Path path = Paths.get(PATH).getParent().resolve("word_count").resolve(word);
            try {
                if (!Files.exists(path)) {
                    Files.createFile(path);
                }

                BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
                bufferedWriter.write(word);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Files.list(Paths.get(PATH).getParent().resolve("word_count")).forEach(wordFile -> {
            try {
                long count = Files.lines(wordFile.toAbsolutePath()).count();

                // 取频率最高的前k个，维护一个小顶堆
                if (queue.size() == k) {
                    if (Integer.parseInt(queue.peek()[1]) < count) {
                        queue.poll();
                        queue.offer(new String[]{wordFile.getFileName().toString(), String.valueOf(count)});
                    }
                } else {
                    queue.offer(new String[]{wordFile.getFileName().toString(), String.valueOf(count)});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < k; i++) {
            String[] ret = queue.poll();
            if (ret != null) {
                System.out.print(ret[0] + ":" + Integer.parseInt(ret[1]) + " ");
            }
        }
    }
}
