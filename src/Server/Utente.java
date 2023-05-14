package Server;

import java.io.Serializable;

public class Utente implements Serializable
{
    String username = null;
    String password = null;
    String numeroTelefonico = null;

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setNumeroTelefonico(String numeroTelefonico)
    {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getNumeroTelefonico()
    {
        return numeroTelefonico;
    }
}
