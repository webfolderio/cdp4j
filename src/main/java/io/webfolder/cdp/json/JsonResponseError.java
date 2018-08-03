package io.webfolder.cdp.json;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonResponseError {

    private Double code;
    private String message;
    private JsonNode data;

    public Double getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public JsonNode getData() {
        return data;
    }
}
