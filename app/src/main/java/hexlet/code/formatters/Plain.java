package hexlet.code.formatters;

import hexlet.code.DiffConstants;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get(DiffConstants.KEY);
            String type = (String) entry.get(DiffConstants.TYPE);

            switch (type) {
                case DiffConstants.ADDED -> {
                    Object newValue = entry.get(DiffConstants.NEW_VALUE);
                    result.append(String.format("Property '%s' was added with value: %s%n", key,
                            formatValue(newValue)));
                }
                case DiffConstants.REMOVED ->
                        result.append(String.format("Property '%s' was removed%n", key));
                case DiffConstants.UPDATED -> {
                    Object oldValue = entry.get(DiffConstants.OLD_VALUE);
                    Object newValue = entry.get(DiffConstants.NEW_VALUE);
                    result.append(String.format("Property '%s' was updated. From %s to %s%n", key,
                            formatValue(oldValue),
                            formatValue(newValue)));
                }
                case DiffConstants.UNCHANGED -> {
                    // Пропускаем
                }
                default -> throw new IllegalArgumentException("Unknown diff type: " + type);
            }
        }

        return result.toString().replace("\r\n", "\n").trim();
    }

    private static String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
}
