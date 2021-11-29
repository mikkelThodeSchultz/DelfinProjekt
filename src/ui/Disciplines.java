package ui;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Optional;


public enum Disciplines {
    CRAWL,
    BACK_CRAWL,
    BUTTERFLY,
    BREASTSTROKE;
}



