package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.GetDiff.getDiff;
import static hexlet.code.formatters.Formatter.formatDiff;

public class Differ {
    public static String generate(String filepath1,
                                  String filepath2,
                                  String formatName) throws Exception {
        var firstFileData = getData(getPath(filepath1));
        var secondFileData = getData(getPath(filepath2));
        var resultDiff = getDiff(firstFileData, secondFileData);
        var result = formatDiff(resultDiff, formatName);
        System.out.println(result);
        return result;
    }

    public static String generate(String filepath1,
                                  String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static Path getPath(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return path;
    }

    private static Map<String, Object> getData(Path filepath) throws Exception {
        var format = getDataFormat(filepath.toString());
        var data = Files.readString(filepath);
        return Parser.parsingToJson(data, format);
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
