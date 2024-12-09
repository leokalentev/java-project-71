package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static final String RESOURCES_DIR = "src/main/resources/";
    private String expectedStylish;
    private String expectedPlain;
    private String expectedJson;

    @BeforeEach
    void setUp() throws Exception {
        expectedStylish = Files.readString(Path.of(RESOURCES_DIR + "expectedStylish.txt"));
        expectedPlain = Files.readString(Path.of(RESOURCES_DIR + "expectedPlain.txt"));
        expectedJson = Files.readString(Path.of(RESOURCES_DIR + "expectedJson.txt"));
    }

    @ParameterizedTest
    @MethodSource("provideFilePairsForStylish")
    void testDifferStylish(String filepath1, String filepath2) throws Exception {
        String actual = Differ.generate(filepath1, filepath2);
        String normalizedExpected = expectedStylish.replace("\r\n", "\n").trim();
        String normalizedActual = actual.replace("\r\n", "\n").trim();
        assertEquals(normalizedExpected, normalizedActual);
    }

    @ParameterizedTest
    @MethodSource("provideFilePairsForPlain")
    void testDifferPlain(String filepath1, String filepath2) throws Exception {
        String actual = Differ.generate(filepath1, filepath2, "plain");
        String normalizedExpected = expectedPlain.replace("\r\n", "\n").trim();
        String normalizedActual = actual.replace("\r\n", "\n").trim();
        assertEquals(normalizedExpected, normalizedActual);
    }

    @ParameterizedTest
    @MethodSource("provideFilePairsForJson")
    void testDifferJson(String filepath1, String filepath2) throws Exception {
        String actual = Differ.generate(filepath1, filepath2, "json");
        String normalizedExpected = expectedJson.replace("\r\n", "\n").trim();
        String normalizedActual = actual.replace("\r\n", "\n").trim();
        assertEquals(normalizedExpected, normalizedActual);
    }

    private static Stream<Arguments> provideFilePairsForStylish() {
        return Stream.of(
                Arguments.of(RESOURCES_DIR + "file1.json", RESOURCES_DIR + "file2.json"),
                Arguments.of(RESOURCES_DIR + "file3.yml", RESOURCES_DIR + "file4.yml")
        );
    }

    private static Stream<Arguments> provideFilePairsForPlain() {
        return Stream.of(
                Arguments.of(RESOURCES_DIR + "file1.json", RESOURCES_DIR + "file2.json"),
                Arguments.of(RESOURCES_DIR + "file3.yml", RESOURCES_DIR + "file4.yml")
        );
    }

    private static Stream<Arguments> provideFilePairsForJson() {
        return Stream.of(
                Arguments.of(RESOURCES_DIR + "file1.json", RESOURCES_DIR + "file2.json"),
                Arguments.of(RESOURCES_DIR + "file3.yml", RESOURCES_DIR + "file4.yml")
        );
    }
}
