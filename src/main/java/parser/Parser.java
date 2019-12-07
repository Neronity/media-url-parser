package parser;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.AvailableFormats;
import entities.MediaFile;
import fileUtils.JsonFileHandler;
import fileUtils.ObjectMapperSingleton;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private final List<AvailableFormats> availableFormats;
    private static final String CONFIG_PATH = "src/main/resources/config/availableFormats.json";

    {
        availableFormats = JsonFileHandler
                .readJsonToCollection(CONFIG_PATH, new TypeReference<List<AvailableFormats>>() {});
    }

    private List<MediaFile> getMediaListFromPage(String url) {
        List<MediaFile> mediaFiles = new ArrayList<>();

        getAllUrlsFromPage(url).forEach(e -> mediaFiles.add(new MediaFile(e)));

        return mediaFiles;
    }

    @SneakyThrows
    private Set<String> getAllUrlsFromPage(String url) {
        Document doc = Jsoup.connect(url).get();
        Set<String> matches = new HashSet<>();
        String prePattern = "(((https:\\/\\/)|(http:\\/\\/)).*\\.%s)";

        for (AvailableFormats formats : availableFormats) {
            for (String format : formats.getFormats()) {
                String pattern = String.format(prePattern, format);
                Matcher m = Pattern.compile(pattern).matcher(doc.html());
                while (m.find()) {
                    String[] separateMatches = m.group().split("\"");
                    for (String s : separateMatches) {
                        if (s.startsWith("http") && s.endsWith("." + format)) {
                            matches.add(s);
                        }
                    }
                }
            }
        }

        return matches;
    }

    private List<String> getInput(String filePath) {
        return JsonFileHandler.readJsonToCollection(filePath, new TypeReference<List<String>>() {
        });
    }

    @SneakyThrows
    public List<MediaFile> parsePages(String filePath) {
        List<MediaFile> result = new ArrayList<>();

        for (String url : getInput(filePath)) {
            result.addAll(getMediaListFromPage(url));
        }

        return result;
    }
}