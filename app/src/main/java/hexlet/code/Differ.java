package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static hexlet.code.DiffGenerator.generateDiff;

public class Differ {
    private static final String DEFAULT_FORMAT = "stylish";
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        if (format == null || format.isEmpty()) {
            format = DEFAULT_FORMAT;
        }
        Map<String, Object> data1 = GetData.getData(filePath1);
        Map<String, Object> data2 = GetData.getData(filePath2);
        List<Map<String, Object>> diff = generateDiff(data1, data2);

        return Formatter.format(diff, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        String defaultFormat = DEFAULT_FORMAT;
        return generate(filePath1, filePath2, defaultFormat);
    }

    public static List<String> allKeys(Map<String, Object> data1, Map<String, Object> data2) {
        List<String> result = new ArrayList<>();
        Set<String> keys1 = data1.keySet();
        for (String key : keys1) {
            result.add(key);
        }

        Set<String> keys2 = data2.keySet();
        for (String key : keys2) {
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
