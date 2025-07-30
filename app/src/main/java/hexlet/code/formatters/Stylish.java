package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Stylish {
    public static String generate(ArrayList<Map<String, Object>> resultDiff) {
        List<String> result = new ArrayList<>();

        for (Map<String, Object> map : resultDiff) {
            Set<String> keys = map.keySet();
            String[] keyArray = keys.toArray(new String[0]);

            for (var fullKey: keyArray) {
                String actionType = fullKey.substring(0, fullKey.indexOf(":"));
                String propertyName = fullKey.substring(fullKey.indexOf(":") + 1);

                switch (actionType) {
                    case "removed":
                        result.add("- " + propertyName + ": " + map.get(fullKey));
                        break;

                    case "unchanged":
                        result.add("  " + propertyName + ": " + map.get(fullKey));
                        break;

                    case "old value":
                        Object oldValue = map.get(fullKey);
                        int currentIndex = Arrays.asList(keyArray).indexOf(fullKey);
                        if (currentIndex < keyArray.length - 1) {
                            String nextKey = keyArray[currentIndex + 1];
                            Object newValue = map.get(nextKey);
                            result.add("- " + propertyName + ": " + oldValue);
                            result.add("+ " + propertyName + ": " + newValue);
                        }
                        break;

                    case "new value":
                        break;

                    case "added":
                        result.add("+ " + propertyName + ": " + map.get(fullKey));
                        break;

                    default:
                        throw new RuntimeException("unknow actionType: " + actionType);
                }
            }
        }
        var resultSort = result.stream()
                .map(s -> "  " + s)
                .collect(Collectors.joining("\n"));
        return "{\n" + resultSort + "\n}";
    }
}
