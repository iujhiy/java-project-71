package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashMap;

public class JsonFormatter {
    public static String generate(Map<String, Object> firstFileData,
                                  Map<String, Object> secondFileData) throws JsonProcessingException {
        Map<String, Map<String, Object>> jsonMap = new TreeMap<>();
        firstFileData.forEach((key, value) -> {
            Map<String, Object> innerMap = new LinkedHashMap<>();
            if (!secondFileData.containsKey(key)) {
                innerMap.put("removed", value);
                jsonMap.put(key, innerMap);
            } else {
                innerMap.put("old", firstFileData.get(key));
                innerMap.put("new", secondFileData.get(key));
                jsonMap.put(key, innerMap);
            }
        });

        secondFileData.forEach((key, value) -> {
            Map<String, Object> innerMap = new LinkedHashMap<>();
            if (!firstFileData.containsKey(key)) {
                innerMap.put("added", value);
                jsonMap.put(key, innerMap);
            }
        });
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jsonMap);
    }
}
