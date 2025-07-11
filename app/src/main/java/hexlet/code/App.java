package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.concurrent.Callable;

@Command (name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {
    @Option(names = { "-f", "--format" },
            paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish")
    String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String filepath2;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public String call() throws Exception {
        if (!format.equals("stylish")) {
            return "Something another";
        }
        var firstFileData = getData(filepath1);
        var secondFileData = getData(filepath2);
        var differResultFiles = Differ.generate(firstFileData, secondFileData);
        System.out.println(differResultFiles);
        return differResultFiles;
    }

    public static Map<String, Object> getData(String filepath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        var isFileYaml = path.toString().endsWith(".yaml");
        if (isFileYaml) {
            return Parser.parsingFromYamlToJson(path);
        }
        var fileData = path.toFile();
        Map<String, Object> data = mapper.readValue(fileData, new TypeReference<>() { });
        return data;
    }
}
