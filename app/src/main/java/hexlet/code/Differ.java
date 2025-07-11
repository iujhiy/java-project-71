package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ArrayList;

public class Differ {
    public static String generate(Map<String, Object> firstFileData,
                                  Map<String, Object> secondFileData,
                                  String formatName) {
        var resultFileDiffers = new ArrayList<String>();
        if (formatName.equals("plain")) {
            return Plain.generate(firstFileData, secondFileData, resultFileDiffers);
        } else {
            return Stylish.generate(firstFileData, secondFileData, resultFileDiffers);
        }
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
