package hexlet.code;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class Differ {
    public static String generate(Map<String, Object> firstFileData,
                                  Map<String, Object> secondFileData){
        var resultFileDiffers = new ArrayList<String>();
        firstFileData.forEach((key, value) -> {
            if (!secondFileData.containsKey(key)) {
                resultFileDiffers.add("- " + key + ": " + value);
            } else if (secondFileData.get(key).equals(firstFileData.get(key))) {
                resultFileDiffers.add("  " + key + ": "  + value);
            } else if (!secondFileData.get(key).equals(firstFileData.get(key))) {
                resultFileDiffers.add("- " + key + ": " + value);
                resultFileDiffers.add("+ " + key + ": " + secondFileData.get(key));
            }
        });
        secondFileData.forEach((key, value) -> {
            if (!firstFileData.containsKey(key)) {
                resultFileDiffers.add("+ " + key + ": " + value);
            }
        });
        var result = resultFileDiffers.stream()
                .map(element -> " " + element)
                .sorted(Comparator.comparing(s -> s.charAt(3)))
                .collect(Collectors.joining("\n"));;
        return "{\n" + result + "\n}";
    }
}
