package demoqa.utility;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public class JsonUtility {

    private JsonUtility() {
        // private constructor to prevent instantiation
    }

    /**
     * Reads a JSON file from the classpath and returns it as a JSONObject.
     * @param path
     * @return
     */
    public static JSONObject readJson(String path) {
        try (InputStream is = JsonUtility.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalStateException("File not found: " + path);
            }
            String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return new JSONObject(content);
        } catch (IOException e) {
            throw new UncheckedIOException("Error reading file: " + path, e);
        }
    }
}
