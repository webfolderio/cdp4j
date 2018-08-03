package io.webfolder.cdp.json;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonResponse {

    private Integer id;
    private String method;
    private JsonResponseError error;
    private JsonNode params;
    private JsonNode result;

    public Integer getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public JsonResponseError getError() {
        return error;
    }

    public JsonNode getParams() {
        return params;
    }

    public JsonNode getResult() {
        return result;
    }
}
