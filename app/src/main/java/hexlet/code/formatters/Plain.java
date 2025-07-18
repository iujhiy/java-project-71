package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Plain {
    public static String generate(ArrayList<Map<String, Object>> resultDiff) {
        List<Map<String, Object>> resultPlain = new ArrayList<>(resultDiff);
        List<String> result = new ArrayList<>();

        for (Map<String, Object> map : resultPlain) {
            Set<String> keys = map.keySet();
            String[] keyArray = keys.toArray(new String[0]);

            for (int i = 0; i < keyArray.length; i++) {
                String fullKey = keyArray[i];
                String actionType = fullKey.substring(0, fullKey.indexOf(":"));
                String propertyName = fullKey.substring(fullKey.indexOf(":") + 1);

                switch (actionType) {
                    case "removed":
                        result.add(String.format("Property %s was removed",
                                modDependenceClass(propertyName)));
                        break;

                    case "unchanged" :
                        break;

                    case "old value":
                        Object oldValue = map.get(keyArray[i]);
                        Object newValue = map.get(keyArray[i + 1]);
                        result.add(String.format("Property %s was updated. From %s to %s",
                                modDependenceClass(propertyName),
                                modDependenceClass(oldValue),
                                modDependenceClass(newValue)));
                        i++;
                        break;

                    default:
                        result.add(String.format("Property %s was added with value: %s",
                                modDependenceClass(propertyName),
                                modDependenceClass(map.get(fullKey))));
                        break;
                }
            }
        }

        return result.stream()
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
