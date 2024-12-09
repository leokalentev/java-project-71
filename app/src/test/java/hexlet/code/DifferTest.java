package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testDifferJson() throws Exception {
        String filepath1 = "src/main/resources/file1.json";
        String filepath2 = "src/main/resources/file2.json";

        String afterFunc = Differ.generate(filepath1, filepath2);
        String result = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";

        assertEquals(result, afterFunc);
    }

    @Test
    public void testDifferYml() throws Exception {
        String filepath1 = "src/main/resources/file3.yml";
        String filepath2 = "src/main/resources/file4.yml";

        String afterFunc = Differ.generate(filepath1, filepath2);
        String result = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";

        assertEquals(result, afterFunc);
    }

    @Test
    void testPlainFormatter() throws Exception {
        String filepath1 = "src/main/resources/file1.json";
        String filepath2 = "src/main/resources/file2.json";

        String result = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;

        String actual = Differ.generate(filepath1, filepath2, "plain");
        assertEquals(result.trim(), actual.trim());
    }

    @Test
    void testJsonFormatter() throws Exception {
        String filepath1 = "src/main/resources/file1.json";
        String filepath2 = "src/main/resources/file2.json";

        String result = """
                [ {
                  "type" : "UNCHANGED",
                  "value" : [ "a", "b", "c" ],
                  "key" : "chars1"
                }, {
                  "newValue" : false,
                  "oldValue" : [ "d", "e", "f" ],
                  "type" : "UPDATED",
                  "key" : "chars2"
                }, {
                  "newValue" : true,
                  "oldValue" : false,
                  "type" : "UPDATED",
                  "key" : "checked"
                }, {
                  "newValue" : [ "value1", "value2" ],
                  "oldValue" : null,
                  "type" : "UPDATED",
                  "key" : "default"
                }, {
                  "newValue" : null,
                  "oldValue" : 45,
                  "type" : "UPDATED",
                  "key" : "id"
                }, {
                  "oldValue" : "value1",
                  "type" : "REMOVED",
                  "key" : "key1"
                }, {
                  "newValue" : "value2",
                  "type" : "ADDED",
                  "key" : "key2"
                }, {
                  "type" : "UNCHANGED",
                  "value" : [ 1, 2, 3, 4 ],
                  "key" : "numbers1"
                }, {
                  "newValue" : [ 22, 33, 44, 55 ],
                  "oldValue" : [ 2, 3, 4, 5 ],
                  "type" : "UPDATED",
                  "key" : "numbers2"
                }, {
                  "oldValue" : [ 3, 4, 5 ],
                  "type" : "REMOVED",
                  "key" : "numbers3"
                }, {
                  "newValue" : [ 4, 5, 6 ],
                  "type" : "ADDED",
                  "key" : "numbers4"
                }, {
                  "newValue" : {
                    "nestedKey" : "value",
                    "isNested" : true
                  },
                  "type" : "ADDED",
                  "key" : "obj1"
                }, {
                  "newValue" : "Another value",
                  "oldValue" : "Some value",
                  "type" : "UPDATED",
                  "key" : "setting1"
                }, {
                  "newValue" : 300,
                  "oldValue" : 200,
                  "type" : "UPDATED",
                  "key" : "setting2"
                }, {
                  "newValue" : "none",
                  "oldValue" : true,
                  "type" : "UPDATED",
                  "key" : "setting3"
                } ]
                """;

        String actual = Differ.generate(filepath1, filepath2, "json");
        String normalizedExpected = result.replace("\r\n", "\n").trim();
        String normalizedActual = actual.replace("\r\n", "\n").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }
}
