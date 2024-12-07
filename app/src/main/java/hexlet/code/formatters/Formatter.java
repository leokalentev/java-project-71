package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> diff, String format) {
        switch (format == null ? "stylish" : format) {
            case "plain":
                return Plain.format(diff);
            case "stylish":
                return Stylish.format(diff);
            case "json":
                return JsonFormatter.format(diff);
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
