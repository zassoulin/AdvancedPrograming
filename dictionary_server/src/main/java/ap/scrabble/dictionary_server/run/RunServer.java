package ap.scrabble.dictionary_server.run;

import java.io.IOException;

import ap.scrabble.dictionary_server.BookScrabbleHandler;
import ap.scrabble.dictionary_server.MyServer;
import ap.scrabble.dictionary_server.properties.ServerProperties;

public class RunServer {
	private ServerProperties properties;

	public RunServer(ServerProperties properties) {
		this.properties = properties;
	}

	public boolean init() {
		return properties.init();
	}

	public void runServer() {
		MyServer s=new MyServer(properties.getPort(), new BookScrabbleHandler(),3);
		s.start(); // runs in the background

		System.out.println("Press 'q' to shutdown the server:");
		int c;
		try {
			while ((c = System.in.read()) != -1) {
				if (c == 'q' || c == 'Q') {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		s.close();
	}

	public static void main(String[] args) {
		RunServer serverRunner = new RunServer(new ServerProperties("config.ini"));
		if (!serverRunner.init()) {
			System.err.println("Failed to initialize the server");
			System.exit(-1);
		}

		System.out.println("Starting server...");
		serverRunner.runServer();
		System.out.println("Bye");
	}

}
