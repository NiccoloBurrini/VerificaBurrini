package itismeucci;

import java.io.*;
import java.net.*;

public class Server {

    int port;
    ServerSocket server;
    Socket client;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Server(int port) {

        this.port = port;
    }

    public void connect() {

        try {
            server = new ServerSocket(port);

        } catch (IOException e) {
        }
    }

    public void communicate() {
        try {

            client = server.accept();

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());

            System.out.println("Client connesso sulla porta: " + port);
            String stringaRicevuta = inDalClient.readLine();
            System.out.println("Client: " + stringaRicevuta);

            String[] list = stringaRicevuta.split(" ");

            Double n1 = Double.parseDouble(list[0]);
            Double n2 = Double.parseDouble(list[2]);
            String simbolo = list[1];

            Double risultato = 0.0;
            if (simbolo.equals("+")) {
                risultato = n1 + n2;
            } else if (simbolo.equals("-")) {
                risultato = n1 - n2;
            } else if (simbolo.equals("*")) {
                risultato = n1 * n2;
            } else if (simbolo.equals("/")) {
                risultato = n1 / n2;
            } else {
                System.out.println("ERRORE: simbolo non riconosciuto");
            }
            outVersoClient.writeBytes("RISULTATO:" + String.valueOf(risultato) + "\n");
            System.out.println("RISULTATO:" + risultato);
            inDalClient.close();
            outVersoClient.close();
            client.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERRORE");
            System.exit(1);
        }
    }
}
