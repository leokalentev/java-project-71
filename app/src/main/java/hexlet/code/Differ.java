package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static hexlet.code.DiffGenerator.generateDiff;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        if (format == null || format.isEmpty()) {
            format = "stylish";
        }
        Map<String, Object> data1 = GetData.getData(filePath1);
        Map<String, Object> data2 = GetData.getData(filePath2);
        List<Map<String, Object>> diff = generateDiff(data1, data2);

        return Formatter.format(diff, format);
    }


    public static List<String> allKeys(Map<String, Object> data1, Map<String, Object> data2) {
        List<String> result = new ArrayList<>();
        var keys1 = data1.keySet();
        for (var key : keys1) {
            result.add(key);
        }

        var keys2 = data2.keySet();
        for (var key : keys2) {
            result.add(key);
        }

        Collections.sort(result);
        List<String> uniqueResult = result.stream()
                .distinct()
                .collect(Collectors.toList());
        return uniqueResult;
    }

    protected static boolean isNested(Object value) {
        return value instanceof Map;
    }

    protected static Map<String, Object> toMap(Object value) {
        if (value instanceof Map) {
            return (Map<String, Object>) value;
        } else {
            throw new IllegalArgumentException("Expected a Map, but got: " + value.getClass());
        }
    }
}
