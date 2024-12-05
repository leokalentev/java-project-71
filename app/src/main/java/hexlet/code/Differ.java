package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = GetData.getData(filePath1);
        Map<String, Object> data2 = GetData.getData(filePath2);
        List<Map<String, Object>> diff = generateDiff(data1, data2);

        if ("plain".equals(format)) {
            return Plain.format(diff);
        } else if ("stylish".equals(format) || format == null) {
            return Stylish.format(diff);
        } else if ("json".equals(format) || format == null) {
            return JsonFormatter.format(diff);
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }
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
