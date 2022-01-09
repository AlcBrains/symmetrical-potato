package com.alcbrains.barebones.requests;

public enum ResponseStatus {
    OK("HTTP/1.1 200 OK\n"),
    NOT_FOUND("HTTP/1.1 404 Not Found\n"),
    INTERNAL_SERVER_ERROR("HTTP/1.1 500 Internal Server Error\n");

    public final String label;
    private ResponseStatus(String label) {
        this.label = label;
    }

}
