package ap.scrabble.gameclient.model.properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DictionaryServerConfig {
	private String iniFilename;
	private String ip;
	private int port;
	private String[] books;

	public DictionaryServerConfig(String iniFilename) {
		this.iniFilename = iniFilename;
		init();
	}

	public boolean init() {
		Properties iniProperties = new Properties();
		try {
			iniProperties.load(new FileReader(iniFilename));
		} catch (IOException e) {
			System.err.println("ServerProperties: Couldn't open configuration file \"%s\"".formatted(iniFilename));
			return false;
		}

		ip = iniProperties.getProperty("ip");
		if (ip == null || ip.isBlank()) {
			System.err.println("ServerProperties: Missing property \"ip\"");
			return false;
		}

		if (!readPort(iniProperties)) {
			System.err.println("ServerProperties: Missing property \"port\"");
			return false;
		}

		if (!readBooks(iniProperties)) {
			System.err.println("ServerProperties: Missing property \"books\"");
			return false;
		}

		return true;
	}

	public String getIP() { return ip; }
	public int getPort() { return port; }
	public String[] getBooks(){ return books; }

	private boolean readPort(Properties iniProperties) {
		String portString = iniProperties.getProperty("port");
		if (portString == null || portString.isBlank()) {
			return false;
		}
		try {
			port = Integer.parseInt(portString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private boolean readBooks(Properties iniProperties) {
		String booksString = iniProperties.getProperty("books");
		if (booksString == null || booksString.isBlank()) {
			return false;
		}
		books = booksString.split(",");
		for (String book : books) {
			if (book.isBlank()) {
				return false;
			}
		}
		return true;
	}
}
