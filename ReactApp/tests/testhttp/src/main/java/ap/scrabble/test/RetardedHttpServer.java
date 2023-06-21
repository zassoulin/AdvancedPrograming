package ap.scrabble.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class RetardedHttpServer {
	public static void main(String[] args) {
		// Create the HTTP server
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(8080), 0);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("\nCouldn't open HTTP server on port 8080. Exiting.");
			System.exit(1);
		}

		// Define the request handler
		server.createContext("/", new MyHandler());

		// Start the server
		server.start();

		System.out.println("Server started on port 8080");

		System.out.println("Press 'q' to shutdown the server:\n");
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

		server.stop(6);
	}

	static class MyHandler implements HttpHandler {
		static void PrintHeader(Headers headers) {
			System.out.print("Header:");
			headers.forEach((String str, List<String> arr) -> {
				System.out.print("\t" + str);
				arr.forEach((String subStr) -> {
					System.out.print(" " + subStr);
				});
				System.out.println();
			});
		}

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			// Print request
			System.out.println("Request");
			System.out.println("URI: " + exchange.getRequestURI().toASCIIString());
			PrintHeader(exchange.getRequestHeaders());
			System.out.println("Method: " + exchange.getRequestMethod());
			System.out.println();

			// Set the response headers
			exchange.getResponseHeaders().set("Content-Type", "text/plain");
			exchange.sendResponseHeaders(200, 0);
			
			// Write the response body
			String response = "mah balls";
			OutputStream outputStream = exchange.getResponseBody();
			outputStream.write(response.getBytes());
			outputStream.close();

			// Print response
			System.out.println("Response");
			PrintHeader(exchange.getResponseHeaders());
			System.out.println("Body:\t" + response);
			System.out.println();
		}
	}
}
