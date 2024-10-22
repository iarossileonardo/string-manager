package server.clientserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server in ascolto");
        ServerSocket sS0 = new ServerSocket(3000); //Porta dove il server aspetta richiesta
        do {
            Socket s0 = sS0.accept(); //quando arriva una connessione, viene accettata e rende la nuova porta su cui avverra il vero passaggio di dati   
            gestioneServizio gS = new gestioneServizio(s0);
            gS.start();
        } while(true);
    }
}