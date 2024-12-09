package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

public class JsonFormatter {

    public static String format(List<Map<String, Object>> diff) {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(diff);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сериализации в JSON", e);
        }
    }
}
