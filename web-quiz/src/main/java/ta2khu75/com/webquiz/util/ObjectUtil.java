package ta2khu75.com.webquiz.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectUtil {
    private ObjectUtil() {
        throw new IllegalStateException("Utility class");
    }
    private static final ObjectMapper objectMapper=new ObjectMapper();

    public static String toString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
