package hexlet.code;

import static hexlet.code.Differ.generate;

public class Formatter {
    public static String chooseFormate(String filepath1, String filepath2,
                                     String formatName) throws Exception {
        String result = switch (formatName) {
            case "plain" -> generate(filepath1, filepath2, "plain");
            case "json" -> generate(filepath1, filepath2, "json");
            default -> generate(filepath1, filepath2);
        };
        System.out.println(result);
        return result;
    }
}
