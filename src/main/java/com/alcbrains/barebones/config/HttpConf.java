package com.alcbrains.barebones.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpConf {

    private final Map<String, String> configEntries;

    public HttpConf(String path) throws IOException {

        configEntries = Files.lines(Paths.get(new File("src/main/resources/httpd.conf").getAbsolutePath()))
                // ignore comments
                .filter(s -> !s.matches("^#+"))
                .filter(s -> s.matches("^\\w+:\\w+"))
                .collect(Collectors.toMap(k -> k.split(":")[0], v -> v.split(":")[1]));
    }

    public Map<String,String> getConfigEntries() {
        return configEntries;
    }

}
