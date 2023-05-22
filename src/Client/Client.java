package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client
{
    public static String IPAddress = "127.0.0.1";
    public static int port = 5555;
    Socket socket;
    ObjectInputStream inStream;
    ObjectOutputStream outStream;


    Client() throws IOException
    {

        //Apertura del socket e degli stream in attesa di messaggi
        socket = new Socket(IPAddress, port);
        inStream = new ObjectInputStream(socket.getInputStream());
        outStream = new ObjectOutputStream(socket.getOutputStream());

    }

    public void scrivi(String destinatario, String testo)
    {
        //outStream.writeObject(new PacchettoC());
    }
}
class PacchettoC
{
    String nomeUtente;
    String password;
    String numeroDestinatario;
    String testo;
    String dataInvio;
    int codice;

    PacchettoC (String nomeUtente, String password, String numeroDestinatario, String testo, int codice)
    {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.numeroDestinatario = numeroDestinatario;
        this.testo = testo;
        this.codice = codice;
    }
}

class PacchettoS
{
    String testo;
    String numeroMittente;
    int codice;

    PacchettoS (String numeroMittente, String testo, int codice)
    {
        this.testo = testo;
        this.codice = codice;
    }
}
