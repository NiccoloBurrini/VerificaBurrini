package itismeucci;

import java.io.*;
import java.net.*;

public class Client {

    int serverPort;
    String serverAddress;

    Socket client;
    BufferedReader in;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    BufferedReader tastiera;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public Socket connect() {

        try {
            client = new Socket(serverAddress, serverPort);

            outVersoServer = new DataOutputStream(client.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            tastiera = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connesso al server con IP: " + serverAddress + ":" + serverPort);

        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERRORE");
            System.exit(1);
        }

        return client;
    }

    public void communicate() {

        String userInput;

        try {

            System.out.println("Digita un operazione (es. 5 + 3)");
            userInput = tastiera.readLine();
            outVersoServer.writeBytes(userInput + "\n");

            System.out.println("Server: " + inDalServer.readLine());

            System.out.println("Connessione al server chiusa");
            inDalServer.close();
            outVersoServer.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}