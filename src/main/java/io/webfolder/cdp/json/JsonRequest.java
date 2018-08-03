package io.webfolder.cdp.json;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

@JsonPropertyOrder({ "id", "method", "params" })
public class JsonRequest {

    private final int id;
    private final String method;
    private final Map<String, JsonNode> params;

    public JsonRequest(int id, String method, Map<String, JsonNode> params) {
        this.id = id;
        this.method = method;
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, JsonNode> getParams() {
        return params;
    }
}
