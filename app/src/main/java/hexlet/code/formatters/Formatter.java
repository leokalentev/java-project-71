package hexlet.code.formatters;

import hexlet.code.DiffConstants;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> diff, String format) {
        switch (format) {
            case DiffConstants.FORMAT_PLAIN:
                return Plain.format(diff);
            case DiffConstants.FORMAT_STYLISH:
                return Stylish.format(diff);
            case DiffConstants.FORMAT_JSON:
                return JsonFormatter.format(diff);
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
