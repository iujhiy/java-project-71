package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private App app;

    @BeforeEach
    public void makeApp() {
        app = new App();
    }

    @Test
    public void testJsonFiles() throws Exception {
        var expected = """
                {
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                 + verbose: true
                }""";
        app.filepath1 = getResourcePath("file1.json");
        app.filepath2 = getResourcePath("file2.json");
        String testResult = app.call();
        assertEquals(expected, testResult);
    }

    @Test
    public void testYamlFiles() throws Exception {
        var expected = """
                {
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                 + verbose: true
                }""";
        app.filepath1 = getResourcePath("file1.yaml");
        app.filepath2 = getResourcePath("file2.yaml");
        String testResult = app.call();
        assertEquals(expected, testResult);
    }

    private String getResourcePath(String filename) {
        return getClass().getClassLoader().getResource(filename).getFile();
    }
}
