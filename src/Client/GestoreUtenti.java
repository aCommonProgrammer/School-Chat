package Client;

import Server.Server;

import java.io.*;
import java.util.Vector;

public class GestoreUtenti
{
    static Vector<Utente> ListaUtenti = null;
    static String fileName = "lista_utenti.dat";

    public static void aggiornaLista()
    {
        //Lettura del vettore contenuto nel file e inserimento dello stesso nella lista
        try
        {
            File f = new File(Main.dirPath + fileName);
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream objIn = new ObjectInputStream(fin);

            ListaUtenti = (Vector<Utente>) objIn.readObject();

            objIn.close();
            fin.close();
        } catch (Exception e)
        {
            System.out.println("Errore nell'aggiornameto della lista");
            e.printStackTrace();
        }
    }

    public static void agggiornaFile()
    {
        //Scrittura del vettore contenuto nella lista nel file
        try
        {
            File f = new File(Main.dirPath + fileName);
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream objOut = new ObjectOutputStream(fout);

            objOut.writeObject(ListaUtenti);

            objOut.close();
            fout.close();
        } catch (Exception e)
        {
            System.out.println("Errore nell'aggiornameto del file");
            e.printStackTrace();
        }
    }

    public static void inserisci(Utente utente) { ListaUtenti.add(utente); }

    public static void rimuovi(Utente utente) { ListaUtenti.removeElement(utente); }

    public static void rimuovi(int posizioneUtente) { ListaUtenti.removeElementAt(posizioneUtente); }

    //Se il valore di ritorno è -1 significa che non è presente quell'utente
    public static int cercaPosizioneUtente(Utente utente)
    {
        for(int p=0; p < ListaUtenti.size(); p++) {
            Utente confronto = (Utente) ListaUtenti.elementAt(p);
            if (confronto == utente)
                return p;
        }
        return -1;
    }

    public static boolean cercaUtente(Utente utente)
    {
        for(int p=0; p < ListaUtenti.size(); p++) {
            Utente confronto = (Utente) ListaUtenti.elementAt(p);
            if (confronto == utente)
                return true;
        }
        return false;
    }

    public static Utente cercaUtente(String nomeUtente, String password)
    {
        for(int p=0; p < ListaUtenti.size(); p++)
        {
            Utente confronto = (Utente) ListaUtenti.elementAt(p);
            if (confronto.getUsername().equals(nomeUtente) && confronto.getPassword().equals(password))
                return confronto;
        }
        return null;
    }
}

class Utente implements Serializable
{
    String username = null;
    String password = null;
    String numeroTelefonico = null;

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setNumeroTelefonico(String numeroTelefonico) { this.numeroTelefonico = numeroTelefonico; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getNumeroTelefonico() { return numeroTelefonico; }
}