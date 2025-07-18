package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Differ {
    public static String generate(String filepath1,
                                  String filepath2,
                                  String formatName) throws Exception {
        var firstFileData = getData(getPath(filepath1));
        var secondFileData = getData(getPath(filepath2));
        var resultDiff = getDiff(firstFileData, secondFileData);
        return formatDiff(resultDiff, formatName);
    }

    public static String generate(String filepath1,
                                  String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static ArrayList<Map<String, Object>> getDiff(Map<String, Object> firstFileData,
                                                          Map<String, Object> secondFileData) {
        var resultDiff = new ArrayList<Map<String, Object>>();
        firstFileData.forEach((key, value) -> {
            var mapDiff = new LinkedHashMap<String, Object>();
            if (!secondFileData.containsKey(key)) {
                mapDiff.put("removed:" + key, value);
            } else if (!Objects.equals(secondFileData.get(key), (firstFileData.get(key)))) {
                mapDiff.put("old value:" + key, firstFileData.get(key));
                mapDiff.put("new value:" + key, secondFileData.get(key));
            } else if (Objects.equals(secondFileData.get(key), (firstFileData.get(key)))) {
                mapDiff.put("unchanged:" + key, value);
            }
            resultDiff.add(mapDiff);
        });

        secondFileData.forEach((key, value) -> {
            if (!firstFileData.containsKey(key)) {
                Map<String, Object> mapDiff = new LinkedHashMap<>();
                mapDiff.put("added:" + key, value);
                resultDiff.add(mapDiff);
            }
        });
        return resultDiff;
    }

    private static String formatDiff(ArrayList<Map<String, Object>> resultDiff,
                                     String formatName) throws JsonProcessingException {
        return switch (formatName) {
            case "plain" -> Plain.generate(resultDiff);
            case "json" -> JsonFormatter.generate(resultDiff);
            default ->
                    Stylish.generate(resultDiff);
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
