package com.yuyakaido.android.reduxkit.server;

import android.content.Context;
import fi.iki.elonen.NanoHTTPD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Server extends NanoHTTPD {

    private final Context context;

    public Server(Context context) {
        super(8080);
        this.context = context;
    }

    @Override
    public Response serve(IHTTPSession session) {
        InputStream stream;
        BufferedReader reader;
        StringBuilder builder = new StringBuilder();
        try {
            stream = context.getAssets().open("index.html");
            reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            reader.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFixedLengthResponse(builder.toString());
    }

}
