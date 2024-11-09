package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testDiffer() throws Exception {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("host", "hexlet.io");
        data1.put("timeout", 50);
        data1.put("proxy", "123.234.53.22");
        data1.put("follow", false);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("timeout", 20);
        data2.put("verbose", true);
        data2.put("host", "hexlet.io");

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
