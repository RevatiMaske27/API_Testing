package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Map;

@SuppressWarnings("unchecked")
public class JsonReader {
    private static Map<String, Map<String, Object>> payloads;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            payloads = mapper.readValue(
                new File("src/test/resources/payloads.json"),
                Map.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to load payloads.json", e);
        }
    }

    public static String getPayload(String endpoint) {
        try {
            return new ObjectMapper().writeValueAsString(payloads.get(endpoint));
        } catch (Exception e) {
            throw new RuntimeException("Failed to get payload for endpoint: " + endpoint, e);
        }
    }
}
