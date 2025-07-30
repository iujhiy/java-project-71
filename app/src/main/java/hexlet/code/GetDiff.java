package hexlet.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class GetDiff {
    public static ArrayList<Map<String, Object>> getDiff(Map<String, Object> firstFileData,
                                                          Map<String, Object> secondFileData) {
        Set<String> keys = new TreeSet<>(Comparator.naturalOrder());
        keys.addAll(firstFileData.keySet());
        keys.addAll(secondFileData.keySet());
        var resultDiff = new ArrayList<Map<String, Object>>();

        for (var key: keys) {
            var mapDiff = new LinkedHashMap<String, Object>();
            var value1 = firstFileData.get(key);
            var value2 = secondFileData.get(key);
            if (!secondFileData.containsKey(key)) {
                mapDiff.put("removed:" + key, value1);
            }  else if (!firstFileData.containsKey(key)) {
                mapDiff.put("added:" + key, value2);
            } else if (!Objects.equals(value1, (value2))) {
                mapDiff.put("old value:" + key, value1);
                mapDiff.put("new value:" + key, value2);
            } else {
                mapDiff.put("unchanged:" + key, value1);
            }
            resultDiff.add(mapDiff);
        }
        return resultDiff;
    }
}
