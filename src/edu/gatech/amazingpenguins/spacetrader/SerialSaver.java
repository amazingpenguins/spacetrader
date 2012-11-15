/**
 * Serial Saver
 */
package edu.gatech.amazingpenguins.spacetrader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * This class allows the saving and loading of game data as a HashMap.
 * Though it may not be a great way, it definitely works.
 * @author AmazingPenguins
 * @version 0.01
 */
public class SerialSaver  {
    /**
     * Field GAMEDATALOC.
     */
    private static final String GAMEDATALOC = "GameData.ser";

    /**
     * Utilize serial saving to save the storeMap HashMap to a file.
     * @param storeMap The HashMap<Class<\?>, Object> to store.
     */
    public void serializeToDisk(Map<Class<?>,Object> storeMap) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(GAMEDATALOC);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(storeMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos != null) {
                    fos.close();
                }
                if(oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Read the saved data HashMap in from the saved game serial file.
    
     * @return Save Game HashMap<Class<\?>, Object>.. */
    @SuppressWarnings("unchecked")
    public Map<Class<?>,Object> serializeFromDisk() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(GAMEDATALOC);
            ois = new ObjectInputStream(fis);
            final Map<Class<?>,Object> storeMap = 
                    (HashMap<Class<?>, Object>) ois.readObject();
            return storeMap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null) {
                    fis.close();
                }
                if(ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "Serial Saver";
    }
}
