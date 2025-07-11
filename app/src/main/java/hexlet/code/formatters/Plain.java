package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Plain {
    public static String generate(Map<String, Object> firstFileData,
                                  Map<String, Object> secondFileData,
                                  ArrayList<String> resultFileDiffers) {
        firstFileData.forEach((key, value) -> {
            if (!secondFileData.containsKey(key)) {
                resultFileDiffers.add("Property " + modDependenceClass(key) + " was removed");
            } else if (!Objects.equals(secondFileData.get(key), (firstFileData.get(key)))) {
                var newValue = modDependenceClass(secondFileData.get(key));
                resultFileDiffers.add("Property " + modDependenceClass(key) + " was updated."
                        + " From " + modDependenceClass(value)
                        + " to " + newValue);
            }
        });
        secondFileData.forEach((key, value) -> {
            if (!firstFileData.containsKey(key)) {
                resultFileDiffers.add("Property " + modDependenceClass(key)
                        + " was added with value: "  + modDependenceClass(value));
            }
        });
        return resultFileDiffers.stream()
                .sorted(Comparator.comparing((String s) -> s))
                .collect(Collectors.joining("\n"));
    }

    private static String modDependenceClass(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Boolean
                || value instanceof Integer
                || value instanceof Long
                || value instanceof Double
                || value instanceof Float
                || value instanceof Character
                || value instanceof Byte
                || value instanceof Short) {
            return value.toString();
        } else {
            return "[complex value]";
        }
    }
}
