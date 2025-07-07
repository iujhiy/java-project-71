package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    public void testWork() throws Exception {
        App app = new App();
        var expected = """
                {
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                 + verbose: true
                }""";
        app.filepath1 = "/home/nikita/java-project-71/app/src/test/resources/file1.json";
        app.filepath2 = "/home/nikita/java-project-71/app/src/test/resources/file2.json";
        String testResult = app.call();
        assertEquals(expected, testResult);
    }
}
