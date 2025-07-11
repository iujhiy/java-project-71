package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private App app;
    private final String expected = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";

    @BeforeEach
    public void makeApp() {
        app = new App();
    }

    @Test
    public void testJsonFiles() throws Exception {
        app.filepath1 = getResourcePath("file1.json");
        app.filepath2 = getResourcePath("file2.json");
        app.format = "stylish";
        String testResult = app.call();
        assertEquals(expected, testResult);
    }

    @Test
    public void testYamlFiles() throws Exception {
        app.filepath1 = getResourcePath("file1.yaml");
        app.filepath2 = getResourcePath("file2.yaml");
        app.format = "stylish";
        String testResult = app.call();
        assertEquals(expected, testResult);
    }

    private String getResourcePath(String filename) {
        return getClass().getClassLoader().getResource(filename).getFile();
    }
}
