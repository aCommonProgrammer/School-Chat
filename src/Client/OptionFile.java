package Client;

import java.io.*;

public class OptionFile
{
    static String fileName = "options.dat";
    static OptionObject oggetto = null;

    OptionFile()
    {
        try {
            File file = new File(Main.dirPath + fileName);
            ObjectInputStream objin = new ObjectInputStream(new FileInputStream(file));
            oggetto = (OptionObject) objin.readObject();
        } catch (Exception e)
        {
            System.out.println("Errore nella lettura del file " + Main.dirPath + fileName);
            e.printStackTrace();
        }
    }

    void scrivi()
    {
        try {
            File file = new File(Main.dirPath + fileName);
            ObjectOutputStream objin = new ObjectOutputStream(new FileOutputStream(file));
            objin.writeObject(oggetto);
        } catch (Exception e)
        {
            System.out.println("Errore nella scrittura del file " + Main.dirPath + fileName);
            e.printStackTrace();
        }
    }

    void scrivi(OptionObject o)
    {
        try {
            File file = new File(Main.dirPath + fileName);
            ObjectOutputStream objin = new ObjectOutputStream(new FileOutputStream(file));
            objin.writeObject(o);
        } catch (Exception e)
        {
            System.out.println("Errore nella scrittura del file " + Main.dirPath + fileName);
            e.printStackTrace();
        }
    }

}

class OptionObject implements Serializable
{
    String userName;
    String password;
    int tema;
}
