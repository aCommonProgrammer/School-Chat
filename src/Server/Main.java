package Server;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Server s = new Server(5555);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}