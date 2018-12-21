package com.yuyakaido.android.reduxkit.server;

import fi.iki.elonen.NanoHTTPD;

public class Server extends NanoHTTPD {

    public Server() {
        super(8080);
    }

    @Override
    public Response serve(IHTTPSession session) {
        return newFixedLengthResponse("Hello!");
    }

}
