package ap.scrabble.dictionary_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {

	int port;
	int maxNumOfThreads;
	boolean stop;
	Thread serverThread;
	final ClientHandler ch; // to seperate a generic client and server, we give the responsibility of handeling the client to the clientHandler thus seperating the server and client and making it more modular

	public MyServer(int port, ClientHandler ch, int maxNumOfThreads) {
		this.port=port;
		this.ch=ch;
		this.maxNumOfThreads = maxNumOfThreads;
	}

	public void start() {
		System.out.println("Server started");
		stop=false;
		serverThread = new Thread(()->startsServer());
		serverThread.start();
	}

	private void startsServer() {
		ExecutorService executor = Executors.newFixedThreadPool(maxNumOfThreads);
		try {
			ServerSocket server = new ServerSocket(port);
			server.setSoTimeout(1000);
			while (!stop) {
				try {
					Socket client = server.accept();
					executor.execute(() ->run(client));
				} catch (SocketTimeoutException e) {}
			}
			server.close();
			executor.shutdown();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void run(Socket client) {
		try {
			synchronized (ch) {
				ch.handleClient(client.getInputStream(), client.getOutputStream());
				ch.close();
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() { // possible fail here - wait for all clients to finish?
		System.out.println("Server shutting down...");
		stop = true;
		try {
			serverThread.join(5_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
