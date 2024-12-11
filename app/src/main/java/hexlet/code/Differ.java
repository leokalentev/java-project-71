package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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

    public static Set<String> allKeys(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> uniqueKeys = new TreeSet<>();
        uniqueKeys.addAll(data1.keySet());
        uniqueKeys.addAll(data2.keySet());
        return uniqueKeys;
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
