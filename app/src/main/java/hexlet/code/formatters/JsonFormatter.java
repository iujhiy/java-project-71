package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.LinkedHashMap;

public class JsonFormatter {
    public static String generate(ArrayList<Map<String, Object>> resultDiff) throws JsonProcessingException {
        Map<String, Map<String, Object>> result = new TreeMap<>();

        for (Map<String, Object> map : resultDiff) {
            Set<String> keys = map.keySet();
            String[] keyArray = keys.toArray(new String[0]);

            for (int i = 0; i < keyArray.length; i++) {
                Map<String, Object> innerMap = new LinkedHashMap<>();
                String fullKey = keyArray[i];
                String actionType = fullKey.substring(0, fullKey.indexOf(":"));
                String propertyName = fullKey.substring(fullKey.indexOf(":") + 1);

                switch (actionType) {
                    case "removed":
                        innerMap.put("removed", map.get(fullKey));
                        result.put(propertyName, innerMap);
                        break;

                    case "unchanged" :
                        innerMap.put("unchanged", map.get(fullKey));
                        result.put(propertyName, innerMap);
                        break;

                    case "old value":
                        Object oldValue = map.get(keyArray[i]);
                        Object newValue = map.get(keyArray[i + 1]);
                        innerMap.put("old value", oldValue);
                        innerMap.put("new value", newValue);
                        result.put(propertyName, innerMap);
                        i++;
                        break;

                    default:
                        innerMap.put("added", map.get(fullKey));
                        result.put(propertyName, innerMap);
                        break;
                }
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(result);
    }
}
