package ap.scrabble.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class MyServer {
	private int port;
	private int maxNumOfThreads;
	private boolean stop;
	private Thread serverThread;
	private List<ClientHandler> clients;

	public MyServer(int port, int maxNumOfThreads) {
		this.port=port;
		this.maxNumOfThreads = maxNumOfThreads;
		this.clients = new ArrayList<>();
	}

	public void start() {
		System.out.println("Host Server started");
		stop=false;
		serverThread = new Thread(()->run());
		serverThread.start();
	}

	private void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			serverSocket.setSoTimeout(500);
			while (!stop) {
				Assert.assertCond(clients.size() <= maxNumOfThreads, "SocketHostServer: Too many clients (max number of threads allowed is %d)".formatted(maxNumOfThreads));
				try {
					Socket clientSocket = serverSocket.accept();
					ClientHandler clientHandler = new ClientHandler(clientSocket);
					clients.add(clientHandler);
					clientHandler.start();
				} catch (SocketTimeoutException e) {}
			}
			for (ClientHandler client : clients) {
				client.close();
			}
			serverSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		System.out.println("Server shutting down...");
		stop = true;
		try {
			serverThread.join(6_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private class ClientHandler {
		private Socket socket;
	
		private InputStream in;
		private OutputStream out;
	
		private Thread listenThread;
		private boolean stop;
	
		public ClientHandler(Socket socket) {
			this.socket = socket;
			try {
				this.in = socket.getInputStream();
				this.out = socket.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		private void listen(){
			try {
				out.write(
("HTTP HTTP/1.1 200 OK\n" +
"Content-Type: text/plain\n" +
"\n" +
"mah balls")
.getBytes(StandardCharsets.UTF_8));
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (!stop) {
				try {
					int i = in.read();
					if (i == -1) {
						stop = true;
						break;
					}
					String c = Character.toString(i);
					System.out.print(c);
				} catch (IOException e) {
					stop = true;
					break;
				}
			}
			System.out.println();
		}

		public void start() {
			stop = false;
			listenThread = new Thread(this::listen);
			listenThread.start();
		}

		public void close() {
			stop = true;
			try {
				listenThread.join(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class Assert {
	public static void assertCond(boolean condition, String msg) {
		if (!condition) {
			throw new RuntimeException(msg);
		}
	}
}

public class RetardedHttpServer
{
	public static void main( String[] args )
	{
		MyServer s = new MyServer(8080, 32);
		s.start();

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
}