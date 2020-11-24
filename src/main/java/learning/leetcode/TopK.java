package learning.leetcode;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private static PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
        public int compare(Integer m, Integer n) {
            return m - n;
        }
    });

    public static void main(String[] args) throws IOException {
        initFile();
        countWorld(3);
    }

    private static void initFile() throws IOException {
        OutputStream fileOutputStream = new FileOutputStream(PATH);

        while (Files.size(Paths.get(PATH)) < FILE_SIZE) {
            int index = RANDOM.nextInt(WORD.length);
            String word = WORD[index];
            fileOutputStream.write(word.getBytes());
            fileOutputStream.write("\r\n".getBytes());
        }

        fileOutputStream.flush();
        fileOutputStream.close();
    }

    // 建立一个文件名为单词的文件，内容为该单词出现的次数
    private static void countWorld(int k) throws IOException {
        if (k <= 0) {
            return;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
        String word;
        while ((word = bufferedReader.readLine()) != null) {
            try {
                Path currentWorldCountPath = Paths.get(PATH).getParent().resolve("word_count").resolve(word);
                if (Files.exists(currentWorldCountPath)) {
                    BufferedReader fileReader = new BufferedReader(new FileReader(currentWorldCountPath.toFile()));
                    String count = fileReader.readLine();
                    int currentCount = 0;
                    if (count != null) {
                        currentCount = Integer.parseInt(count) + 1;
                    }

                    try (OutputStream outputStream = new FileOutputStream(currentWorldCountPath.toString())) {
                        outputStream.write(String.valueOf(currentCount).getBytes());
                        outputStream.flush();
                    }

                    // 取频率最高的前k个，维护一个小顶堆
                    if (queue.size() == k) {
                        if (queue.peek() < currentCount) {
                            queue.poll();
                            queue.offer(currentCount);
                        }
                    } else {
                        queue.offer(currentCount);
                    }

                    System.out.println("current word: " + word + " count: " + queue.peek());
                } else {
                    if (currentWorldCountPath.toFile().createNewFile()) {
                        try (FileOutputStream fileOutputStream = new FileOutputStream(currentWorldCountPath.toString())) {
                            fileOutputStream.write("0".getBytes());
                            fileOutputStream.flush();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
