package server.clientserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class gestioneServizio extends Thread{
    Socket s0;

    gestioneServizio(Socket s0) {
        this.s0 = s0;
    }

    @Override
    public void run() {
        try {
            System.out.println("Un client si è collegato alla porta "+ s0.getPort() +" dove risiede il thread " + Thread.currentThread().getName());

            BufferedReader in = new BufferedReader(new InputStreamReader(s0.getInputStream())); // stream dati in entrata
            DataOutputStream out = new DataOutputStream(s0.getOutputStream()); // stream dati inn uscita
            String stringaIn;
            do{
                stringaIn = in.readLine(); // attendo che arrivi una stringa sullo stream
                System.out.println("Stringa ricevuta: " + stringaIn);
        
                String sMaiuscola = stringaIn.toUpperCase(); // trasformo la stringa in maiuscola
                out.writeBytes(sMaiuscola + "\n"); // rispondo al client inviando la stringa
            }while(!stringaIn.equals("!")); // se il client manda "!", il server si chiude
        
            s0.close();    
        } catch (Exception e) {

        } 
    }
}
