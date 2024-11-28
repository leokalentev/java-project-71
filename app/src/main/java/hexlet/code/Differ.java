package hexlet.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static hexlet.code.GetData.getData;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);

        if ("stylish".equals(format) || format == null) {
            return Stylish.format(generateDiff(data1, data2));
        }

        throw new IllegalArgumentException("Unsupported format: " + format);
    }

    private static List<Map<String, Object>> generateDiff(Map<String, Object> data1, Map<String, Object> data2) {
        List<Map<String, Object>> diff = new ArrayList<>();
        var keys = allKeys(data1, data2);

        for (var key : keys) {
            var value1 = data1.get(key);
            var value2 = data2.get(key);

            Map<String, Object> entry = new HashMap<>();
            entry.put("key", key);

            if (data1.containsKey(key) && data2.containsKey(key)) {
                if (value1 == null && value2 == null) {
                    entry.put("type", "UNCHANGED");
                    entry.put("value", null);
                } else if (value1 != null && value1.equals(value2)) {
                    entry.put("type", "UNCHANGED");
                    entry.put("value", value1);
                } else if (isNested(value1) && isNested(value2)) {
                    entry.put("type", "UNCHANGED");
                    entry.put("value", generateDiff(toMap(value1), toMap(value2)));
                } else {
                    entry.put("type", "UPDATED");
                    entry.put("oldValue", value1);
                    entry.put("newValue", value2);
                }
            } else if (data1.containsKey(key)) {
                entry.put("type", "REMOVED");
                entry.put("oldValue", value1);
            } else {
                entry.put("type", "ADDED");
                entry.put("newValue", value2);
            }

            diff.add(entry);
        }

        return diff;
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

    private static boolean isNested(Object value) {
        return value instanceof Map;
    }

    private static Map<String, Object> toMap(Object value) {
        return (Map<String, Object>) value;
    }
}
