package fileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class JsonFileHandler {

    public static boolean writeToJson(String json, String outFilePath) {
        try (PrintWriter pw = new PrintWriter(
                new File(outFilePath))) {
            pw.write(json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SneakyThrows
    public static <T> T readJson(String fileName, Class<T> clazz) {
        return ObjectMapperSingleton.getInstance().readValue(
                Objects.requireNonNull(JsonFileHandler.class.getClassLoader().getResource(fileName)), clazz);
    }

    @SneakyThrows
    public static <T> T readJsonToCollection(String fileName, TypeReference<T> type) {
        return ObjectMapperSingleton.getInstance().readValue(new File(fileName), type);
    }
}
