package entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvailableFormats {

    private String mediaType;
    private List<String> formats;
}
