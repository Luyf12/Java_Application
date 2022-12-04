package Model;

import java.util.ArrayList;
import java.io.*;

public class Load {
    Load(){}

    public static void loadItems(File f) throws FileNotFoundException, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        try {
            Model.now_Items = (ArrayList<Item>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Failed while loading a file");
        }
        ois.close();
    }
}