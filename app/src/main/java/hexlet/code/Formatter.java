package hexlet.code;

import static hexlet.code.Differ.getData;

public class Formatter {
    public static String chooseFormate(String filepath1, String filepath2,
                                     String formatName) throws Exception {
        if (formatName.equals("plain")) {
            return differFiles(filepath1, filepath2, "plain");
        } else if (formatName.equals("json")) {
            return differFiles(filepath1, filepath2, "json");
        } else {
            return differFiles(filepath1, filepath2, "stylish");
        }
    }

    private static String differFiles(String filepath1, String filepath2, String formatName)
            throws Exception {
        var firstFileData = getData(filepath1);
        var secondFileData = getData(filepath2);
        var differResultFiles = Differ.generate(firstFileData, secondFileData, formatName);
        System.out.println(differResultFiles);
        return differResultFiles;
    }
}
