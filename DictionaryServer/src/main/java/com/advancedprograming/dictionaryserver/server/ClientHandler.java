package com.advancedprograming.dictionaryserver.server;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
	void handleClient(InputStream inFromclient, OutputStream outToClient);
	void close();
}
