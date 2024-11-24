package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String filepath) throws Exception {
        ObjectMapper yamlData = new ObjectMapper(new YAMLFactory());
        Map<String, Object> data = yamlData.readValue(new File(filepath), new TypeReference<Map<String, Object>>() { });
        return data;
    }
}
