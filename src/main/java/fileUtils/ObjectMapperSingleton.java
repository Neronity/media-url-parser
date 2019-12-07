package fileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperSingleton {

    private static ObjectMapper objectMapper;

    public static ObjectMapper getInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        return objectMapper;
    }
}
