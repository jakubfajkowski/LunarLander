package loader;

import game.model.Player;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Client {
    static public Properties getDataFromServer(String serverName, int port, int fileNumber) {

        Properties properties;

        try {
            Socket socket = new Socket(serverName, port);

            // do pisania do serwera
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            // tu po kolei komendy do serwera wedlug protokolu
            pw.println("GET DATA " + fileNumber);

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is));

            String lineFromFile;

            StringBuilder strBuilder = new StringBuilder();
            while((lineFromFile = br.readLine()) != null)
            {
                strBuilder.append(lineFromFile+"\n");
            }

            //obrobienie danych otrzymanych od serwera
            InputStream stream = new ByteArrayInputStream(strBuilder.toString().getBytes(StandardCharsets.UTF_8));;
            properties = PropertiesLoader.loadFromInputStream(stream);


            socket.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            return null;
        }
        return properties;
    }

    static public void sendRecordToServer(String serverName, int port, Player player)
    {
        try {
            Socket socket = new Socket(serverName, port);

            // do pisania do serwera
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            // tu po kolei komendy do serwera wedlug protokolu i doklejenie do tego wynikow
            pw.println("SET SCORES "+ player.getName() + " " + player.getScore());


            socket.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
        }
    }
}
