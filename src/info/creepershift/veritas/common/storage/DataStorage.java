package info.creepershift.veritas.common.storage;

import info.creepershift.veritas.common.util.Reader;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * ED-Veritas
 * Created by Max on 5/23/2017.
 */
public class DataStorage {

    private final String baseDir = System.getProperty("user.home") + "\\AppData\\Roaming\\Veritas";
    private File dataFile;
    private String currentJournal;
    private String currentTime;
    private static DataStorage INSTANCE;

    private DataStorage() {
        this.dataFile = getOrCreateFile();
        readFile();
    }


    public static DataStorage get() {
        if (INSTANCE == null) {
            return new DataStorage();
        } else {
            return INSTANCE;
        }
    }


    private void readFile(){
        List<String> list = Reader.readLines(dataFile);

        if(list != null && list.size() == 2){
            currentJournal = list.get(0);
            currentTime = list.get(1);
        }



    }







    private File getOrCreateFile() {

        File data = new File(baseDir + "\\AppData.txt");

        if (data.exists()) {
            return data;
        } else {
            data.mkdirs();
            try {
                data.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

    }


}
