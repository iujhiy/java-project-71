package hexlet.code;

import java.util.Comparator;
import java.util.Map;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(Map<String, Object> firstFileData,
                                  Map<String, Object> secondFileData) {
        var resultFileDiffers = new ArrayList<String>();
        firstFileData.forEach((key, value) -> {
            if (!secondFileData.containsKey(key)) {
                resultFileDiffers.add("- " + key + ": " + value);
            } else if (Objects.equals(secondFileData.get(key), (firstFileData.get(key)))) {
                resultFileDiffers.add("  " + key + ": "  + value);
            } else if (!Objects.equals(secondFileData.get(key), (firstFileData.get(key)))) {
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
                .map(s -> "  " + s)
                .sorted(Comparator.comparing((String s) -> s.substring(3, s.indexOf(":")))
                        .thenComparing(s -> s.startsWith(" ") ? 0 : 1) // Unchanged lines first
                        .thenComparing(s -> s.startsWith("-") ? 0 : 1)) // Then deletions before additions
                .collect(Collectors.joining("\n"));
        return "{\n" + result + "\n}";
    }
}
