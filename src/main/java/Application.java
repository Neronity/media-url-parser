import com.fasterxml.jackson.core.JsonProcessingException;
import fileUtils.JsonFileHandler;
import fileUtils.ObjectMapperSingleton;
import parser.Parser;

public class Application {

    public static void main(String[] args) throws JsonProcessingException {
        JsonFileHandler.writeToJson(
                ObjectMapperSingleton.getInstance().writeValueAsString(new Parser().parsePages(args[0])),
                args[1]);
    }
}
