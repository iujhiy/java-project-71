package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Path;
import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parsingFromYamlToJson(Path filepath) throws Exception {
        var fileData = filepath.toFile();
        Map<String, Object> yamlContent = YAML_MAPPER.readValue(fileData, new TypeReference<>() { });
        var jsonData = JSON_MAPPER.writeValueAsString(yamlContent);
        Map<String, Object> data = JSON_MAPPER.readValue(jsonData,
                new TypeReference<>() { });
        return data;
    }
}
