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
            char op;
            String dati;
            do{
                dati = in.readLine();
                if (dati.equals("!")) {
                    stringaIn = "!";
                    break;
                }
                op = dati.charAt(dati.length() - 1);
                stringaIn = dati.substring(0, dati.length() - 1); // attendo che arrivi una stringa sullo stream
                System.out.println("Stringa ricevuta: " + stringaIn + " op: " + op);

                String sMod;

                switch (op) {
                    case '1':
                        sMod = stringaIn.toUpperCase(); // trasformo la stringa in maiuscola
                        break;
                    
                    case '2':
                        sMod = stringaIn.toLowerCase();
                        break;

                    case '3':
                        StringBuilder sB = new StringBuilder(stringaIn);
                        sMod = sB.reverse().toString();
                        break;

                    case '4':
                        sMod = "Lunghezza stringa: " + stringaIn.length();
                        break;
                    
                    case '!':
                        sMod = "";
                        stringaIn = "!";
                        break;
                
                    default:
                        sMod = "Inserire solo numeri da 1 a 4";
                        break;
                }

                out.writeBytes(sMod + "\n"); // rispondo al client inviando la stringa
            }while(!stringaIn.equals("!")); // se il client manda "!", il server si chiude

            System.out.println("ciao client");
        
            s0.close();    
        } catch (Exception e) {

        } 
    }
}
