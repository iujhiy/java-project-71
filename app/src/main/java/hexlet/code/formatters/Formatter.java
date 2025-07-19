package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Map;

public class Formatter {
    public static String formatDiff(ArrayList<Map<String, Object>> resultDiff,
                                     String formatName) throws JsonProcessingException {
        return switch (formatName) {
            case "plain" -> Plain.generate(resultDiff);
            case "json" -> JsonFormatter.generate(resultDiff);
            case "stylish" -> Stylish.generate(resultDiff);
            default -> throw new RuntimeException("unknow formatName: " + formatName);
        };
    }
}
