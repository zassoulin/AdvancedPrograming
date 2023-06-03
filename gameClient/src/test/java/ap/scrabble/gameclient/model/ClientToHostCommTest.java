package ap.scrabble.gameclient.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static java.lang.System.currentTimeMillis;

import java.util.Observable;
import java.util.Observer;

import ap.scrabble.gameclient.model.client.HostMessageHandler;
import ap.scrabble.gameclient.model.client.SocketHostServerCommunicator;
import ap.scrabble.gameclient.model.host.ClientMessageHandler;
import ap.scrabble.gameclient.model.host.RemoteClientCommunicator;
import ap.scrabble.gameclient.model.host.SocketHostServer;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.util.MessageType;
import ap.scrabble.gameclient.model.properties.HostServerConfig;

public class ClientToHostCommTest implements Observer {
	HostServerConfig hostServerConfig;
	SocketHostServer socketHostServer;
	RemoteClientCommunicator clientComm;
	SocketHostServerCommunicator hostComm;

	Message response = null;

	boolean clientFinished = false;
	boolean hostFinished = false;

	private void config() {
		hostServerConfig = new HostServerConfig("host_server.ini");
	}

	private void hostServer() {
		socketHostServer = new SocketHostServer(
			hostServerConfig.getPort(),6, RemoteClientCommunicator::create, ClientMessageHandler::create);
		socketHostServer.start();
	}

	private void clientConnect() {
		hostComm = SocketHostServerCommunicator.create(
			hostServerConfig.getIP(), hostServerConfig.getPort(), HostMessageHandler.create());
		hostComm.start();
	}

	private void sleep() {
		try {
			Thread.sleep(500, 0);
		} catch (InterruptedException e) {
			assertTrue("Interrupted sleep", false);
		}
	}

	private void init() {
		config();
		hostServer();
		sleep();
		clientConnect();
		sleep();
		clientComm = (RemoteClientCommunicator)socketHostServer.getClient(0);
		GameManager.get().addObserver(this);
	}

	private void close() {
		hostComm.close();
		socketHostServer.close();
	}

	private void clientThreadFunc() {
		Message response = hostComm.writeAndReceiveMessage(new Message(MessageType.HELLO_HOST, "hello"));
		assertEquals(response.arg, "response");
		clientFinished = true;
	}

	private void hostThreadFunc() {
		Message msg = waitForResponse();
		assertEquals(msg.arg, "hello");
		clientComm.writeMessage(new Message(MessageType.RESPONSE_HOST, "response"));
		hostFinished = true;
	}

	@Test
	public void test() {
		init();

		Thread clientThread = new Thread(this::clientThreadFunc);
		Thread hostThread = new Thread(this::hostThreadFunc);
		clientFinished = false;
		hostFinished = false;
		hostThread.start();
		clientThread.start();

		try {
			hostThread.join(1_500);
		} catch (InterruptedException e) {
			assertTrue("Waited too long for host thread", false);
		}
		try {
			clientThread.join(1_500);
		} catch (InterruptedException e) {
			assertTrue("Waited too long for client thread", false);
		}
		assertTrue("Client didn't finish in time", clientFinished);
		assertTrue("Host didn't finish in time", hostFinished);

		long startTime = currentTimeMillis();
		close();
		assertTrue(currentTimeMillis() - startTime < 2_000);
	}

	@Override
	public void update(Observable o, Object arg) {
		assertTrue(o instanceof GameManager);
		assertTrue(arg instanceof Message);
		notifyResponse((Message)arg);
	}

	public Message waitForResponse() {
		synchronized (this) {
			long waitTime = 0;
			long startTime = currentTimeMillis();
			while (response == null && waitTime < 1_500) {
				try {
					this.wait(5_000 - waitTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				};
				waitTime = currentTimeMillis() - startTime;
			}
			return response;
		}
	}

	private void notifyResponse(Message msg) {
		synchronized (this) {
			response = msg;
			this.notifyAll();
		}
	}
}
