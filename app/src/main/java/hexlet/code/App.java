package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.Map;
import static hexlet.code.GetData.getData;
import static hexlet.code.GetNameFile.getNameFile;
import static hexlet.code.Parser.parser;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]",
            defaultValue = "stylish")
    private String format;

    @Override
    public void run() {
        try {
            if (getNameFile(filepath1).endsWith(".json") && getNameFile(filepath2).endsWith(".json")) {
                Map<String, Object> data1 = getData(filepath1);
                Map<String, Object> data2 = getData(filepath2);
                System.out.println(Differ.generate(data1, data2));
            } else if (getNameFile(filepath1).endsWith(".yml") && getNameFile(filepath2).endsWith("yml")) {
                Map<String, Object> data1 = parser(filepath1);
                Map<String, Object> data2 = parser(filepath2);
                System.out.println(Differ.generate(data1, data2));
            }
        } catch (Exception e) {
            System.err.println("Error reading or parsing files: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }
}
