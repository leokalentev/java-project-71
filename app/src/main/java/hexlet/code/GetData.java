package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class GetData {
    public static Map<String, Object> getData(String filepath) throws Exception {
        ObjectMapper result = new ObjectMapper();
        return result.readValue(new File(filepath), new TypeReference<Map<String, Object>>() {});
    }
}
