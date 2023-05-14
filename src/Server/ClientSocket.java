package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket //Classe per la gestione dello stream con il client e l'autenticazione
{
    Socket socket;
    ObjectInputStream inStream;
    ObjectOutputStream outStream;
    boolean autenticato = false;
    public Utente utente = null;
}
