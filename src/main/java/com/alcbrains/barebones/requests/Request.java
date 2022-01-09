package com.alcbrains.barebones.requests;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface Request {

    public HttpResponse createResponse() throws IOException, InterruptedException;

    public int getContentLength();

    default LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }

}
