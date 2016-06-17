package loader;

import game.model.Player;
import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.junit.Assert.*;

/**
 * Created by Jakub Fajkowski on 16-Jun-16.
 */
public class ClientTest {

    @Test
    public void testConnection() throws Exception {


            InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 6969);

            Socket socket = new Socket();
            socket.connect(inetSocketAddress);

            // do pisania do serwera
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is));

            pw.println("LOAD MAP spac");
            String response;
            StringBuilder sb = new StringBuilder();
            do {
                response = br.readLine();
                sb.append(response).append("\n");
            } while (!response.contains("<END>"));

        System.out.print(sb.toString());
            socket.close();
    }

    @Test
    public void testSendRecordToServer() throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 6969);
        Client client = new Client(inetSocketAddress);

        client.sendRecordToServer(new Player("Fajka", 1000));
        System.out.print(client.getHallOfFameFromServer().toString());
    }

    @Test
    public void testSendRequest() throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 6969);
        Client client = new Client(inetSocketAddress);

        String actualResponse = client.sendRequest("HALO");
        String expectedResponse = "ERROR\n";
        assertTrue(expectedResponse.equals(actualResponse));
    }

    @Test
    public void testGetHallOfFameFromServer() throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 6969);
        Client client = new Client(inetSocketAddress);

        System.out.print(client.getHallOfFameFromServer().toString());
    }

    @Test
    public void testGetMapListFromServer() throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 6969);
        Client client = new Client(inetSocketAddress);

        System.out.print(client.getMapListFromServer());
    }

    @Test
    public void testGetMapFromServer() throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 6969);
        Client client = new Client(inetSocketAddress);

        System.out.print(client.getMapFromServer("aaaaa").toString());
    }
}