package ap.scrabble.gameclient.model;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class SocketDictionaryServerCommunicatorTest extends TestCase {
//    @InjectMocks
//    SocketDictionaryServerCommunicator dictionaryServerCommunicator;
//    @Mock
//    Socket socket;
    @Test
    public void testRunClientQueryRequest() throws IOException {
//        OutputStream outputStream = new ByteArrayOutputStream();
//        InputStream inputStream = new ByteArrayInputStream("Yessss".getBytes());
//        Mockito.when(socket.getOutputStream()).thenReturn(outputStream);
//        Mockito.when(socket.getInputStream()).thenReturn(inputStream);
////        DictionaryServerCommunicator dictionaryServerCommunicator = new SocketDictionaryServerCommunicator("localhost",42069);
//
//        System.out.println(dictionaryServerCommunicator.runClientQueryRequest("10575","s1.txt","s2.txt"));
    }

    public void testRunClientChallengeRequest() {
    }
}