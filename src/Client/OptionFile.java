package Client;

import java.io.Serializable;

public class OptionFile
{
    static String fileName = "lista_utenti.dat";

}

class OptionObject implements Serializable
{
    String userName;
    String password;
}
