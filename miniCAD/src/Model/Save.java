package Model;

import java.io.*;

public class Save {
    Save(){}

    public static void saveItems(File f) throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(Model.now_Items);
        oos.close();
    }
}