package com.alcbrains.barebones.requests;

import java.io.IOException;
import java.net.http.HttpResponse;

public class GetRequest implements Request {

    @Override
    public HttpResponse<String> createResponse() throws IOException, InterruptedException {
        return null;
    }

    @Override
    public int getContentLength() {
        return 0;
    }
}

