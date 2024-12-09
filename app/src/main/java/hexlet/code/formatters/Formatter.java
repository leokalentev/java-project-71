package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {

    private static final String DEFAULT_FORMAT = "stylish";
    private static final String FORMAT_PLAIN = "plain";
    private static final String FORMAT_STYLISH = "stylish";
    private static final String FORMAT_JSON = "json";

    public static String format(List<Map<String, Object>> diff, String format) {
        String effectiveFormat = format == null ? DEFAULT_FORMAT : format;

        switch (effectiveFormat) {
            case FORMAT_PLAIN:
                return Plain.format(diff);
            case FORMAT_STYLISH:
                return Stylish.format(diff);
            case FORMAT_JSON:
                return JsonFormatter.format(diff);
            default:
                throw new IllegalArgumentException("Unsupported format: " + effectiveFormat);
        }
    }
}
