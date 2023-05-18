package ap.scrabble.gameclient.properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ServerProperties {
	private String iniFilename;
	private String ip;
	private int port;

	public ServerProperties(String iniFilename) {
		this.iniFilename = iniFilename;
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
		if (ip == null) {
			System.err.println("ServerProperties: Missing property \"ip\"");
			return false;
		}

		if (!readPort(iniProperties)) {
			System.err.println("ServerProperties: Missing property \"port\"");
			return false;
		}

		return true;
	}

	public String getIP() { return ip; }
	public int getPort() { return port; }

	private boolean readPort(Properties iniProperties) {
		String portString = iniProperties.getProperty("port");
		if (portString == null) {
			return false;
		}
		try {
			port = Integer.parseInt(portString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
