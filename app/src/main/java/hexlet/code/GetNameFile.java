package hexlet.code;

import java.io.File;

public class GetNameFile {
    public static String getNameFile(String filepath) {
        File file = new File(filepath);
        return file.getName();
    }
}
