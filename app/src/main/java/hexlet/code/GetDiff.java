package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GetDiff {
    public static ArrayList<Map<String, Object>> getDiff(Map<String, Object> firstFileData,
                                                          Map<String, Object> secondFileData) {
        var resultDiff = new ArrayList<Map<String, Object>>();
        firstFileData.forEach((key, value) -> {
            var mapDiff = new LinkedHashMap<String, Object>();
            if (!secondFileData.containsKey(key)) {
                mapDiff.put("removed:" + key, value);
            } else if (!Objects.equals(secondFileData.get(key), (firstFileData.get(key)))) {
                mapDiff.put("old value:" + key, firstFileData.get(key));
                mapDiff.put("new value:" + key, secondFileData.get(key));
            } else if (Objects.equals(secondFileData.get(key), (firstFileData.get(key)))) {
                mapDiff.put("unchanged:" + key, value);
            }
            resultDiff.add(mapDiff);
        });

        secondFileData.forEach((key, value) -> {
            if (!firstFileData.containsKey(key)) {
                Map<String, Object> mapDiff = new LinkedHashMap<>();
                mapDiff.put("added:" + key, value);
                resultDiff.add(mapDiff);
            }
        });
        return resultDiff;
    }
}
