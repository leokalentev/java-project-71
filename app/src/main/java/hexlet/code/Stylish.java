package hexlet.code;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            String type = (String) entry.get("type");

            switch (type) {
                case "ADDED" -> {
                    Object newValue = entry.get("newValue");
                    result.append("  + ").append(key).append(": ").append(formatValue(newValue)).append("\n");
                }
                case "REMOVED" -> {
                    Object oldValue = entry.get("oldValue");
                    result.append("  - ").append(key).append(": ").append(formatValue(oldValue)).append("\n");
                }
                case "UNCHANGED" -> {
                    Object value = entry.get("value");
                    result.append("    ").append(key).append(": ").append(formatValue(value)).append("\n");
                }
                case "UPDATED" -> {
                    Object oldValue = entry.get("oldValue");
                    Object newValue = entry.get("newValue");
                    result.append("  - ").append(key).append(": ").append(formatValue(oldValue)).append("\n");
                    result.append("  + ").append(key).append(": ").append(formatValue(newValue)).append("\n");
                }
                default -> throw new IllegalArgumentException("Unknown diff type: " + type);
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String formatValue(Object value) {
        if (value instanceof List<?>) {
            return formatList((List<?>) value);
        } else if (value instanceof Map<?, ?>) {
            return formatMap((Map<?, ?>) value);
        } else if (value == null) {
            return "null";
        } else if (value instanceof Boolean || value instanceof Number || value instanceof String) {
            return value.toString();
        }
        throw new IllegalArgumentException("Unsupported value type: " + value.getClass().getName());
    }

    private static String formatList(List<?> list) {
        return "[" + String.join(", ", list.stream().map(Object::toString).toArray(String[]::new)) + "]";
    }

    private static String formatMap(Map<?, ?> map) {
        StringBuilder mapStr = new StringBuilder("{");
        map.forEach((k, v) -> mapStr.append(k).append("=").append(formatValue(v)).append(", "));
        if (mapStr.length() > 1) {
            mapStr.setLength(mapStr.length() - 2); // Удалить лишнюю запятую и пробел
        }
        mapStr.append("}");
        return mapStr.toString();
    }
}
