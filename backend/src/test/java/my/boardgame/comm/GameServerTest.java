/*
package my.boardgame.comm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameServerTest {
    @Mock
    private Socket mockClientSocket;

    @Test
    public void testHandleConnection() throws IOException {
        String text = "Incoming";

        // Prepare mock input and output streams
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(mockClientSocket.getInputStream()).thenReturn(inputStream);
        when(mockClientSocket.getOutputStream()).thenReturn(outputStream);

        */
/*
        WebSocketService.handleConnection(mockClientSocket);

        // Verify that the server received the message and responded correctly
        verify(mockClientSocket, times(1)).getInputStream();
        verify(mockClientSocket, times(1)).getOutputStream();

        String response = outputStream.toString();
        assert (response.contains("Server received your message: " + text));
         *//*

    }
}
*/
