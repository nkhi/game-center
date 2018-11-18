package fall2018.csc2017.game_center;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Abstract file processor used to read and write objects to files. This class provides
 * encapsulation so that the file path is only accessible by this Object and will not be changed
 * after its creation.
 *
 * @param <T> The type of the Object to be written
 */
public abstract class FileProcessor<T> extends AppCompatActivity {

    /**
     * The object to store.
     */
    private T saveFile;

    /**
     * The filepath to store the object.
     */
    private String filePath;

    /**
     * Return the saved file
     *
     * @return The saved file
     */
    public T getSaveFile() {
        return saveFile;
    }

    /**
     * Set the saved file
     *
     * @param saveFile the Object to be saved
     */
    public void setSaveFile(T saveFile) {
        this.saveFile = saveFile;
    }

    /**
     * Sets the file path
     *
     * @param filePath the file path of the Object to be saved
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads file to saveFile from filePath. Safe to suppress unchecked cast because we are
     * catching the ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    protected void readFile() {
        try {
            InputStream inputStream = this.openFileInput(filePath);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                saveFile = (T) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("Reading file", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Reading file", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("Reading file", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Writes the saveFile object to filePath.
     */
    protected void writeFile() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(filePath, MODE_PRIVATE));
            outputStream.writeObject(saveFile);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
