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
import ap.scrabble.gameclient.model.message.MessageType;
import ap.scrabble.gameclient.model.properties.HostServerConfig;

public class HostToClientCommTest implements Observer {
	HostServerConfig hostServerConfig;
	SocketHostServer socketHostServer;
	RemoteClientCommunicator clientComm;
	SocketHostServerCommunicator hostComm;

	Message response = null;

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

	@Test
	public void test() {
		init();

		clientComm.writeMessage(new Message(MessageType.THIS_IS_HOST, "test"));
		Message msg = hostComm.waitForResponse();
		assertEquals(msg.arg, "test");
		hostComm.writeMessage(new Message(MessageType.HI_HOST_THIS_IS_CLIENT, "hi"));
		Message response = waitForResponse();
		assertEquals(response.arg, "hi");

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
			// Wait for a response no more than 5 seconds
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
