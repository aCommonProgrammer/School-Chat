package Server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server
{
    int port; //porta sulla quale viene aperto il servizio (la porta viene impostata nel costruttore)

    ServerSocket serverSocket;

    final int MAX_CONNESSIONI = 50; //numero massimo di connessioni contemporaneamente

    int nConnessioni = 0; //numero connessioni attuali

    Vector<ClientSocket> clientSockets = new Vector<>(); //Vettore contenete le connessiioni attive

    Server(int port) throws Exception //Apre il server e avvia un thread che accetta le connessioni dei vari client
    {
        boolean r = new File(Main.dirPath).mkdir(); //crea la cartella per i file del server
        if(!r)
            throw new Exception("Impossible to create the directory for the server files");

        this.port = port;

        try{ serverSocket = new ServerSocket(port); }
        catch (IOException exception)
        {
            System.out.println("Errore nell'apertura del server!");
            exception.printStackTrace();
            System.exit(1);
        }

        Thread accettazioneConnessioni = new Thread()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    if (nConnessioni < MAX_CONNESSIONI)
                    {
                        try { clientSockets.elementAt(nConnessioni).socket = serverSocket.accept(); }
                        catch (IOException e) {
                            System.out.println("Errore nell'accettazione della connessione " + nConnessioni + "!");
                            e.printStackTrace();
                        }

                        try
                        {
                            clientSockets.elementAt(nConnessioni).inStream = new ObjectInputStream(clientSockets.elementAt(nConnessioni).socket.getInputStream());
                            clientSockets.elementAt(nConnessioni).outStream = new ObjectOutputStream(clientSockets.elementAt(nConnessioni).socket.getOutputStream());
                        }
                        catch (IOException e)
                        {
                            System.out.println("Errore nell'apertura degli stream del client " + nConnessioni + "!");
                            e.printStackTrace();
                        }

                        ricezione(clientSockets.elementAt(nConnessioni));

                        nConnessioni++;
                    }
                }
            }
        };

        accettazioneConnessioni.start();


    }

    public void ricezione(ClientSocket client) //riceve i pacchetti e li passa alla funzione operazione per eseguire la procedura necessaria
    {
        Thread ricevitore = new Thread()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        PacchettoC messaggio = (PacchettoC) client.inStream.readObject();
                        operazione(client,messaggio);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        ricevitore.start();
    }

    public void invio(ClientSocket client, PacchettoS messaggio)
    {
        try
        {
            client.outStream.writeObject(messaggio);
        }
        catch (IOException e)
        {e.printStackTrace();}
    }

    public void operazione(ClientSocket client, PacchettoC messaggio)
    {
        switch (messaggio.codice)
        {
            case 0: Utente utente = GestoreUtenti.cercaUtente(messaggio.nomeUtente, messaggio.password);
                if(null != utente)
                {
                    invio(client, new PacchettoS("", "",1));
                    client.autenticato = true;
                    client.utente = utente;
                }
                else
                    invio(client, new PacchettoS("","", -1));
                break;
            case 2: ClientSocket destinatario = cercaUtenteConnesso(client.utente.getNumeroTelefonico());
                if (null != destinatario)
                    invio(destinatario, new PacchettoS(client.utente.getNumeroTelefonico(), messaggio.testo,4));
                else
                    invio(client, new PacchettoS("","", -4));
                break;
        }
    }

    public ClientSocket cercaUtenteConnesso(String numeroTelefonico) {
        for (int cont = 0; cont < nConnessioni; cont++) {
            if (clientSockets.elementAt(cont).utente.getNumeroTelefonico().equals(numeroTelefonico))
                return clientSockets.elementAt(cont);
        }
        return null;
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

class ClientSocket //Classe per la gestione dello stream con il client e l'autenticazione
{
    Socket socket;
    ObjectInputStream inStream;
    ObjectOutputStream outStream;
    boolean autenticato = false;
    public Utente utente = null;
}