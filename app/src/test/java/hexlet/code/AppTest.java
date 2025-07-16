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
    public void setApp(TestInfo info) throws IOException {
        app = new App();
        if (info.getTags().contains("stylishCheck")) {
            String expectedStylishPath = getResourcePath("expectedStylish.txt");
            expectedStylishData = Files.readString(Paths.get(expectedStylishPath));
        } else if (info.getTags().contains("jsonCheck")) {
            String expectedJsonPath = getResourcePath("expectedJson.txt");
            expectedJsonData = Files.readString(Paths.get(expectedJsonPath));
        } else if (info.getTags().contains("plainCheck")) {
            String expectedPlainPath = getResourcePath("expectedPlain.txt");
            expectedPlainData = Files.readString(Paths.get(expectedPlainPath));
        }
    }

    @Test
    @Tag("stylishCheck")
    public void testStylishJsonFiles() throws Exception {
        app.filepath1 = getResourcePath("file1.json");
        app.filepath2 = getResourcePath("file2.json");
        app.format = "stylish";
        String testResult = app.call();
        assertEquals(expectedStylishData, testResult);
    }

    @Test
    @Tag("stylishCheck")
    public void testStylishYamlFiles() throws Exception {
        app.filepath1 = getResourcePath("file1.yaml");
        app.filepath2 = getResourcePath("file2.yaml");
        app.format = "stylish";
        String testResult = app.call();
        assertEquals(expectedStylishData, testResult);
    }

    @Test
    @Tag("stylishCheck")
    public void testDefaultJsonFiles() throws Exception {
        app.filepath1 = getResourcePath("file1.json");
        app.filepath2 = getResourcePath("file2.json");
        String testResult = app.call();
        assertEquals(expectedStylishData, testResult);
    }

    @Test
    @Tag("stylishCheck")
    public void testDefaultYamlFiles() throws Exception {
        app.filepath1 = getResourcePath("file1.yaml");
        app.filepath2 = getResourcePath("file2.yaml");
        String testResult = app.call();
        assertEquals(expectedStylishData, testResult);
    }

    @Test
    @Tag("plainCheck")
    public void testPlainYamlFormat() throws Exception {
        app.filepath1 = getResourcePath("file1.yaml");
        app.filepath2 = getResourcePath("file2.yaml");
        app.format = "plain";
        String testResult = app.call();
        assertEquals(expectedPlainData, testResult);
    }

    @Test
    @Tag("plainCheck")
    public void testPlainJsonFormat() throws Exception {
        app.filepath1 = getResourcePath("file1.json");
        app.filepath2 = getResourcePath("file2.json");
        app.format = "plain";
        String testResult = app.call();
        assertEquals(expectedPlainData, testResult);
    }

    @Test
    @Tag("jsonCheck")
    public void testJsonFormatByJsonFile() throws Exception {
        app.filepath1 = getResourcePath("file1.json");
        app.filepath2 = getResourcePath("file2.json");
        app.format = "json";
        String testResult = app.call();
        assertEquals(expectedJsonData, testResult);
    }

    @Test
    @Tag("jsonCheck")
    public void testJsonFormatByYamlFile() throws Exception {
        app.filepath1 = getResourcePath("file1.yaml");
        app.filepath2 = getResourcePath("file2.yaml");
        app.format = "json";
        String testResult = app.call();
        assertEquals(expectedJsonData, testResult);
    }

    private String getResourcePath(String filename) {
        return getClass().getClassLoader().getResource(filename).getFile();
    }
}
