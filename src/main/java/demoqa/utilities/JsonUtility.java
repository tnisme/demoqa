package demoqa.utilities;

import demoqa.exceptions.FailedToReadFileException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class JsonUtility {

    private JsonUtility() {
        // private constructor to prevent instantiation
    }

    /**
     * Reads a JSON file from the classpath and returns it as a JSONObject.
     * @param path the path to the JSON file in the classpath
     * @return JSONObject containing the parsed JSON data
     * @throws IllegalArgumentException if path is null or empty
     * @throws IllegalStateException if file is not found
     * @throws FailedToReadFileException if there's an error reading the file
     */
    public static JSONObject readJson(String path) {
        Objects.requireNonNull(path, "Path cannot be null");
        if (path.trim().isEmpty()) {
            throw new IllegalArgumentException("Path cannot be empty");
        }

        try (InputStream is = JsonUtility.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalStateException("File not found: " + path);
            }
            String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return new JSONObject(content);
        } catch (IOException e) {
            throw new FailedToReadFileException("Error reading file: " + path, e);
        }
    }
}
