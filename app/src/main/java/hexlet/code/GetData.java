package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.Map;

public class GetData {
    public static Map<String, Object> getData(String filepath) throws Exception {
        ObjectMapper objectMapper;
        if (filepath.endsWith(".yml") || filepath.endsWith(".yaml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            objectMapper = new ObjectMapper();
        }
        return objectMapper.readValue(new File(filepath), new TypeReference<>() {
        });
    }
}
