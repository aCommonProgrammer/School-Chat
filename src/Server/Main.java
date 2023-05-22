package Server;

public class Main
{
    static final String dirPath = "SchoolWhatsappServer/Files/"; //Directory dove vengono memorizzati i file del server

    public static void main(String[] args)
    {
        try { Server s = new Server(5555); }
        catch (Exception e) { e.printStackTrace(); }
    }
}