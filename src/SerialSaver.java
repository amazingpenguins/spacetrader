import java.io.*;
import java.util.HashMap;

/**
 * User: ryree0
 * Date: 11/12/12
 * Time: 1:31 PM
 * This class allows the saving and loading of game data as a HashMap.
 * Though it may not be a great way, it definitely works.
 */
public class SerialSaver  {
    private final String GAMEDATALOC="GameData.ser";

    /**
     * Utilize serial saving to save the storeMap HashMap to a file.
     * @param storeMap The HashMap<Class<\?>, Object> to store.
     */
    public void serializeToDisk(HashMap<Class<?>, Object> storeMap) {
        try {
            FileOutputStream fos = new FileOutputStream(GAMEDATALOC);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(storeMap);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the saved data HashMap in from the saved game serial file.
     * @return Save Game HashMap<Class<\?>, Object>..
     */
    public HashMap<Class<?>, Object> serializeFromDisk() {
        try {
            FileInputStream fis = new FileInputStream(GAMEDATALOC);
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("Unchecked")
            HashMap<Class<?>, Object> storeMap = (HashMap<Class<?>, Object>)ois.readObject();
            ois.close();
            return storeMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
