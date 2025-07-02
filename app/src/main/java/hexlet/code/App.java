package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command (name = "gendiff", mixinStandardHelpOptions = true, description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: ${DEFAULT-VALUE}]", defaultValue = "stylish")
    File archive;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    File file1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    File file2;
    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
    @Override
    public void run() {
        System.out.println("Hello World!");
    }
}
