package com.alcbrains.barebones;


import com.alcbrains.barebones.config.HttpConf;
import com.alcbrains.barebones.threadworker.ThreadWorker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {

    static int port;
    static ServerSocket serverSocket;
    static HttpConf httpConfig;

    public static void main(String args[]) throws IOException {

        httpConfig = new HttpConf("conf/httpd.conf");
//      We'll deal with MIMETypes in a bit
//      mimeTypes = new MimeTypes("/conf/mime.types");
        port = Integer.parseInt(httpConfig.getConfigEntries().get("port"));
        serverSocket = new ServerSocket(port);
        listen();

    }

    public static void listen() throws IOException {

        System.out.printf("Server started, listening on port %d%n", port);
        while (true) {
            Socket socket = serverSocket.accept();
            ThreadWorker response = new ThreadWorker(socket);
            response.start();

            socket = null;
        }

    }


}
/*
https://www.codeproject.com/Tips/1040097/Create-a-Simple-Web-Server-in-Java-HTTP-Server
we can probably skip the sockets, I guess
 */
