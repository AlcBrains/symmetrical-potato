package com.alcbrains.barebones.threadworker;

import com.alcbrains.barebones.requests.ResponseStatus;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ThreadWorker extends Thread {

    Socket socket;
    SimpleDateFormat sdf;

    public ThreadWorker(Socket socket) {
        this.socket = socket;
        sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
    }


    @Override
    public void run() {
        PrintWriter out = null;
        String request = "";
        BufferedReader br = null;
        try {
            InputStream sis = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(sis));
            out = new PrintWriter(socket.getOutputStream(), true);
            request = br.readLine();
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String[] requestParam = request.split(" ");
        String path = requestParam[1];


        File file = new File(path);
        assert out != null;

        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            String res = ResponseStatus.NOT_FOUND.label;
            out.write(res + "Content-Type: text/html; charset=UTF-8\nReferrer-Policy: no-referrer\nContent-Length:0");
            out.close();
            return;
        }
        BufferedReader bfr = new BufferedReader(fr);
        String line = "";
        while (true) {
            try {
                if ((line = bfr.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.write(line);
        }

        try {
            bfr.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();

    }
}
