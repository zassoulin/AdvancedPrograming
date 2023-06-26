package com.advancedprograming.dictionaryserver.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
public class BookScrabbleHandler implements ClientHandler {
	BufferedReader in;
	PrintWriter out;

	@Override
	public void handleClient(InputStream inFromclient, OutputStream outToClient) {
		in = new BufferedReader(new InputStreamReader(inFromclient));
		out = new PrintWriter(outToClient, true);

		String line = null;
		try {
			line = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (line != null)
		{
			String[] args = line.split(",");
			System.out.println(line);
			boolean answer = handleArgs(args);
			System.out.println(answer);
			out.println(answer ? "true" : "false");
		}
	}

	private boolean handleArgs(String[] args)
	{
		// The minimum args length is 3 because it consists of one command ["C", "Q"], at least one book, and one word
		if (args.length < 3) {
			System.err.println("Invalid input from client: \"" + args + "\"");
			return false;
		}

		String command = args[0];
		String[] booksAndWord = Arrays.copyOfRange(args, 1, args.length);

		if (command.equals("C"))
			return DictionaryManager.get().challenge(booksAndWord);
		else if (command.equals("Q"))
			return DictionaryManager.get().query(booksAndWord);

		System.err.println("Invalid command from client: \"" + command + "\"");
		return false;
	}

	@Override
	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
	}

}
