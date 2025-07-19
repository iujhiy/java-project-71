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

                    case "added":
                        result.add(String.format("Property %s was added with value: %s",
                                modDependenceClass(propertyName),
                                modDependenceClass(map.get(fullKey))));
                        break;
                    default:
                        throw new RuntimeException("Unknown actionType" + actionType);
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
                || value instanceof Number
                || value instanceof Character) {
            return value.toString();
        } else if (isComplexValue(value)) {
            return "[complex value]";
        } else {
            return value.toString();
        }
    }

    private static boolean isComplexValue(Object value) {
        return value instanceof Iterable
                || value instanceof Map
                || value.getClass().isArray();
    }
}
