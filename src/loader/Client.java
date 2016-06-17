package loader;

import game.model.Player;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.IntSummaryStatistics;
import java.util.Properties;

public class Client {
    private final String READ_SCORES = "READ SCORES";
    private final String MAP_LIST = "MAP LIST";
    private final String LOAD_MAP = "LOAD MAP";
    private final String WRITE_SCORE = "WRITE SCORE";
    private final String END_OF_MESSAGE = "<END>";

    private InetSocketAddress address;
    private Socket socket;

    public Client(InetSocketAddress address) {
        this.address = address;
    }

    public Client() throws ClientException {
        try {
            File propertiesFile = new File("./netconfig");
            Properties properties = PropertiesLoader.loadFromInputStream(new FileInputStream(propertiesFile));
            this.address = new InetSocketAddress(properties.getProperty("IP"), Integer.parseInt(properties.getProperty("PORT")));
        }
        catch (FileNotFoundException e) {
            throw new ClientException(e.getMessage() + "SPECIFY NETWORK SETTINGS");
        }
    }

    public String sendRequest(String request) throws ClientException{
        try {
            socket = new Socket();
            socket.connect(address);

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is));

            pw.println(request);

            String response;
            StringBuilder sb = new StringBuilder();
            do {
                response = br.readLine();
                sb.append(response).append("\n");
            } while (!response.contains(END_OF_MESSAGE));

            socket.close();
            return sb.toString().split(END_OF_MESSAGE)[0];
        }
        catch (IOException e){
            throw new ClientException(e.getMessage());
        }
    }

    public Properties getHallOfFameFromServer() throws ClientException{
        String response = sendRequest(READ_SCORES);
        InputStream inputStream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));

        return PropertiesLoader.loadFromInputStream(inputStream);
    }

    public String getMapListFromServer() throws ClientException {
        String mapList = sendRequest(MAP_LIST);
        if(mapList.equals("")) throw new ClientException("Server has no maps.");
        return mapList;
    }

    public Properties getMapFromServer(String mapName) throws ClientException {
        String response = sendRequest(LOAD_MAP + " " + mapName);
        InputStream inputStream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));

        Properties properties = PropertiesLoader.loadFromInputStream(inputStream);

        if(properties.isEmpty()) throw new ClientException("Server has no map named " + mapName + ".");

        return properties;
    }

    public void sendRecordToServer(Player player) throws ClientException{
        sendRequest(WRITE_SCORE + " " + player.printScore());
    }

    public InetSocketAddress getAdress(){
        return address;
    }
}
