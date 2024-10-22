package client.clientserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("client partito");
        Socket s0 = new Socket("localhost", 3000); //socker che dice indirizzo e porta del server a cui connetersi

        BufferedReader in = new BufferedReader(new InputStreamReader(s0.getInputStream())); //stream dati in
        DataOutputStream out = new DataOutputStream(s0.getOutputStream()); //stream dati out

        Scanner input = new Scanner(System.in); //scanner input da tastiera
        String s;

        do {
            System.out.println("Inserire la stringa da inviare (! per chiudere la connessione)\n");
            s = input.nextLine(); //prendo la linea in input con lo scanner
            
            out.writeBytes(s + "\n"); //invio i dati al server
            
            String sM = in.readLine(); //leggo i dati che arrivano dal server
            
            System.out.println("Invio dati a server");
            
            System.out.println("Stringa maiuscola: " + sM);
        } while (!s.equals("!")); //se scrivo "!" esco dal ciclo e si chiude il client
        
        input.close();
        s0.close();
        System.out.println("client terminato");
    }
}