package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command (name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<String> {
    @Option(names = { "-f", "--format" },
            paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish")
    private String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public String call() throws Exception {
        var result = Differ.generate(filepath1, filepath2, format);
        System.out.println(result);
        return result;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setFilepath1(String filepath1) {
        this.filepath1 = filepath1;
    }

    public void setFilepath2(String filepath2) {
        this.filepath2 = filepath2;
    }
}
