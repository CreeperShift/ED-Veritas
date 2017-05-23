package info.creepershift.veritas.common.storage;

import info.creepershift.veritas.common.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ED-Veritas
 * Created by Max on 5/23/2017.
 */
public class DataStorage {

    private final String baseDir = System.getProperty("user.home") + "\\AppData\\Roaming\\Veritas";
    private File dataFile;
    private String currentJournal;
    private int currentLine;
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

    private void writeFile() {
        ArrayList<String> list = new ArrayList<>();
        list.add(currentJournal);
        list.add(currentLine+"");
        FileUtil.writeLines(dataFile, list);
    }

    private void readFile() {
        List<String> list = FileUtil.readLines(dataFile);

        if (list != null && list.size() == 2) {
            currentJournal = list.get(0);
            currentLine = Integer.parseInt(list.get(1));
        }

    }


    public String getCurrentJournal() {
        return currentJournal;
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public void setCurrentJournal(String currentJournal) {
        this.currentJournal = currentJournal;
        writeFile();
    }

    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
        writeFile();
    }

    public void updateData(String currentJournal, int currentLine){
        this.currentLine = currentLine;
        this.currentJournal = currentJournal;
        writeFile();
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
