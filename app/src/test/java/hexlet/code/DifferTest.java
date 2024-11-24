package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testDifferJson() throws Exception {
        String filepath1 = "src/main/resources/file1.json";
        String filepath2 = "src/main/resources/file2.json";

        Map<String, Object> data1 = GetData.getData(filepath1);
        Map<String, Object> data2 = GetData.getData(filepath2);

        String afterFunc = Differ.generate(data1, data2);
        String result = "{ \n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        assertEquals(result, afterFunc);
    }

    @Test
    public void testDifferYml() throws Exception {
        String filepath1 = "src/main/resources/file3.yml";
        String filepath2 = "src/main/resources/file4.yml";

        Map<String, Object> data1 = Parser.parser(filepath1);
        Map<String, Object> data2 = Parser.parser(filepath2);

        String afterFunc = Differ.generate(data1, data2);
        String result = "{ \n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        assertEquals(result, afterFunc);
    }
}
