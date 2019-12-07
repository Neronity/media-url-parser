package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MediaFile {

    private String url;

    public MediaFile() {
        url = "";
    }
}
