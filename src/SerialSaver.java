import java.io.*;
import java.util.HashMap;

/**
 * User: ryree0
 * Date: 11/12/12
 * Time: 1:31 PM
 * This class allows the saving and loading of game data as a HashMap.
 * Though it may not be a great way, it definitely works.
 * @author ryree0
 * @version $Revision: 1.0 $
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
    public void serializeToDisk(HashMap<Class<?>,Object> storeMap) {
        try {
            final FileOutputStream fos = new FileOutputStream(GAMEDATALOC);
            final ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(storeMap);
            fos.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the saved data HashMap in from the saved game serial file.
    
     * @return Save Game HashMap<Class<\?>, Object>.. */
    @SuppressWarnings("unchecked")
    public HashMap<Class<?>, Object> serializeFromDisk() {
        try {
            final FileInputStream fis = new FileInputStream(GAMEDATALOC);
            final ObjectInputStream ois = new ObjectInputStream(fis);
            final HashMap<Class<?>, Object> storeMap = (HashMap<Class<?>, Object>) ois.readObject();
            fis.close();
            ois.close();
            return storeMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
