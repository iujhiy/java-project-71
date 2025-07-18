package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private App app;
    private String expectedStylishData;
    private String expectedJsonData;
    private String expectedPlainData;

    @BeforeEach
    public void setUp(TestInfo info) throws IOException {
        app = new App();
        if (info.getTags().contains("stylishCheck")
            || info.getTags().contains("defaultCheck")) {
            expectedStylishData = readExpectedData("expectedStylish.txt");
        } else if (info.getTags().contains("jsonCheck")) {
            expectedJsonData = readExpectedData("expectedJson.txt");
        } else if (info.getTags().contains("plainCheck")) {
            expectedPlainData = readExpectedData("expectedPlain.txt");
        }
    }

    @Test
    @Tag("stylishCheck")
    public void testStylishJsonFiles() throws Exception {
        configureApp("file1.json", "file2.json", "stylish");
        assertEquals(expectedStylishData, app.call());
    }

    @Test
    @Tag("stylishCheck")
    public void testStylishYamlFiles() throws Exception {
        configureApp("file1.yaml", "file2.yaml", "stylish");
        assertEquals(expectedStylishData, app.call());
    }

    @Test
    @Tag("defaultCheck")
    public void testDefaultJsonFiles() throws Exception {
        configureApp("file1.json", "file2.json", null);
        assertEquals(expectedStylishData, app.call());
    }

    @Test
    @Tag("defaultCheck")
    public void testDefaultYamlFiles() throws Exception {
        configureApp("file1.yaml", "file2.yaml", null);
        assertEquals(expectedStylishData, app.call());
    }

    @Test
    @Tag("plainCheck")
    public void testPlainYamlFormat() throws Exception {
        configureApp("file1.yaml", "file2.yaml", "plain");
        assertEquals(expectedPlainData, app.call());
    }

    @Test
    @Tag("plainCheck")
    public void testPlainJsonFormat() throws Exception {
        configureApp("file1.json", "file2.json", "plain");
        assertEquals(expectedPlainData, app.call());
    }

    @Test
    @Tag("jsonCheck")
    public void testJsonFormatByJsonFile() throws Exception {
        configureApp("file1.json", "file2.json", "json");
        assertEquals(expectedJsonData, app.call());
    }

    @Test
    @Tag("jsonCheck")
    public void testJsonFormatByYamlFile() throws Exception {
        configureApp("file1.yaml", "file2.yaml", "json");
        assertEquals(expectedJsonData, app.call());
    }

    private void configureApp(String file1, String file2, String format) {
        app.setFilepath1(getResourcePath(file1));
        app.setFilepath2(getResourcePath(file2));
        if (format != null) {
            app.setFormat(format);
        }
    }

    private String readExpectedData(String filename) throws IOException {
        String path = getResourcePath(filename);
        return Files.readString(Paths.get(path));
    }

    private String getResourcePath(String filename) {
        return getClass().getClassLoader().getResource(filename).getFile();
    }
}
