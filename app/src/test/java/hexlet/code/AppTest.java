package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private App app;
    private final String expectedStylish = """
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
        assertEquals(expectedStylish, testResult);
    }

    @Test
    public void testYamlFiles() throws Exception {
        app.filepath1 = getResourcePath("file1.yaml");
        app.filepath2 = getResourcePath("file2.yaml");
        app.format = "stylish";
        String testResult = app.call();
        assertEquals(expectedStylish, testResult);
    }

    @Test
    public void testPlainFormat() throws Exception {
        var expectedPlain = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        app.filepath1 = getResourcePath("file1.yaml");
        app.filepath2 = getResourcePath("file2.json");
        app.format = "plain";
        String testResult = app.call();
        assertEquals(expectedPlain, testResult);
    }

    @Test
    public void testJsonFormat() throws Exception {
        var expectedJson = "{\"chars1\":{\"old\":[\"a\",\"b\",\"c\"],\"new\":[\"a\",\"b\",\"c\"]},"
                + "\"chars2\":{\"old\":[\"d\",\"e\",\"f\"],\"new\":false},\"checked\":{\"old\":false,"
                + "\"new\":true},\"default\":{\"old\":null,\"new\":[\"value1\",\"value2\"]},\"id\":{\"old"
                + "\":45,\"new\":null},\"key1\":{\"removed\":\"value1\"},\"key2\":{\"added\":\"value2\"}"
                + ",\"numbers1\":{\"old\":[1,2,3,4],\"new\":[1,2,3,4]},\"numbers2\":{\"old\":[2,3,4,5],\""
                + "new\":[22,33,44,55]},\"numbers3\":{\"removed\":[3,4,5]},\"numbers4\":{\"added\":[4,5,"
                + "6]},\"obj1\":{\"added\":{\"nestedKey\":\"value\",\"isNested\":true}},\"setting1\":{"
                + "\"old\":\"Some value\",\"new\":\"Another value\"},\"setting2\":{\"old\":200,\"new\":3"
                + "00},\"setting3\":{\"old\":true,\"new\":\"none\"}}";
        app.filepath1 = getResourcePath("file1.yaml");
        app.filepath2 = getResourcePath("file2.json");
        app.format = "json";
        String testResult = app.call();
        assertEquals(expectedJson, testResult);
    }

    private String getResourcePath(String filename) {
        return getClass().getClassLoader().getResource(filename).getFile();
    }
}
