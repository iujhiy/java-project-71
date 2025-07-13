package hexlet.code;

import static hexlet.code.Differ.generate;

public class Formatter {
    public static String chooseFormate(String filepath1, String filepath2,
                                     String formatName) throws Exception {
        if (formatName.equals("plain")) {
            return generate(filepath1, filepath2, "plain");
        } else if (formatName.equals("json")) {
            return generate(filepath1, filepath2, "json");
        } else {
            return generate(filepath1, filepath2, "stylish");
        }
    }
}
