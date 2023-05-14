package Server;

public class PacchettoC
{
    String nomeUtente;
    String password;
    String numeroDestinatario;
    String testo;
    String dataInvio;
    int codice;

    PacchettoC (String numeroDestinatario, String testo, int codice)
    {
        this.numeroDestinatario = numeroDestinatario;
        this.testo = testo;
        this.codice = codice;
    }
}
