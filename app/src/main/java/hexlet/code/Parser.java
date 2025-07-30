package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();
    private static final ObjectMapper JSON_MAPPER = new JsonMapper();

    public static Map<String, Object> parsingFormat(String data, String format) throws Exception {
        if (format.equals("json")) {
            return JSON_MAPPER.readValue(data, new TypeReference<>() { });
        } else if (format.equals("yaml") || format.equals("yml")) {
            return YAML_MAPPER.readValue(data, new TypeReference<>() { });
        } else {
            throw new Exception("Unknow format: " + format);
        }
    }
}
