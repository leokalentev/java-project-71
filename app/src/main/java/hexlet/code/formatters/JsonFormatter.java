package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonFormatter {

    public static String format(List<Map<String, Object>> diff) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сериализации в JSON", e);
        }
    }
}
