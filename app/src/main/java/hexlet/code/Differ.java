package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ArrayList;

public class Differ {
    public static String generate(String filepath1,
                                  String filepath2,
                                  String formatName) throws Exception {
        var firstFileData = getData(getPath(filepath1));
        var secondFileData = getData(getPath(filepath2));
        return formatDiff(firstFileData, secondFileData, formatName);
    }

    public static String generate(String filepath1,
                                  String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String formatDiff(Map<String, Object> firstFileData,
                                     Map<String, Object> secondFileData,
                                     String formatName) throws JsonProcessingException {
        return switch (formatName) {
            case "plain" -> Plain.generate(firstFileData, secondFileData, new ArrayList<>());
            case "json" -> JsonFormatter.generate(firstFileData, secondFileData);
            default ->
                    Stylish.generate(firstFileData, secondFileData, new ArrayList<>());
        };
    }

    private static Path getPath(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return path;
    }

    private static Map<String, Object> getData(Path filepath) throws Exception {
        var format = fileFormate(filepath);
        var data = Files.readString(filepath);
        return Parser.parsingToJson(data, format);
    }

    private static String fileFormate(Path filepath) {
        if (filepath.toString().endsWith(".json")) {
            return "json";
        }
        return "yaml";
    }
}
