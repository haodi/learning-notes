package learning.lock;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SpiltString {
    public static void main(String[] args) {
        spilt("1234567890", 7);
    }

    public static List<String> spilt(String str, int length) {
        if (str.length() == 0) {
            return Collections.emptyList();
        }

        if (length == 0 || str.length() <= length) {
            return Collections.singletonList(str);
        }

        List<String> results = new LinkedList<>();

        int start = 0;
        for (int i = 0; i < Math.ceil((double) str.length() / length); i++) {
            int end = (i + 1) * length > str.length() ? str.length() : (i + 1) * length;
            String subString = str.substring(start, end);
            start = end;

            results.add(subString);
            System.out.println(subString);
        }

        return results;
    }
}
