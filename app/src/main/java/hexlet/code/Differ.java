package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        var resultFileDiffers = new ArrayList<String>();
        var firstFileData = getData(filepath1);
        var secondFileData = getData(filepath2);
        var result =  Stylish.generate(firstFileData, secondFileData, resultFileDiffers);
        if (formatName.equals("plain")) {
            result = Plain.generate(firstFileData, secondFileData, resultFileDiffers);
        } else if (formatName.equals("json")) {
            result = JsonFormatter.generate(firstFileData, secondFileData);
        }
        System.out.println(result);
        return result;
    }

    public static String generate(String filepath1,
                                  String filepath2) throws Exception {
        var resultFileDiffers = new ArrayList<String>();
        var firstFileData = getData(filepath1);
        var secondFileData = getData(filepath2);
        var result = Stylish.generate(firstFileData, secondFileData, resultFileDiffers);
        System.out.println(result);
        return result;
    }

    public static Map<String, Object> getData(String filepath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        var isFileYaml = path.toString().endsWith(".yaml");
        if (isFileYaml) {
            return Parser.parsingFromYamlToJson(path);
        }
        var fileData = path.toFile();
        return mapper.readValue(fileData, new TypeReference<>() { });
    }
}
