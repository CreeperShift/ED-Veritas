package info.creepershift.veritas.common.parser;

import info.creepershift.veritas.common.data.DataHandler;
import info.creepershift.veritas.common.storage.DataStorage;
import info.creepershift.veritas.common.storage.MaterialData;
import info.creepershift.veritas.common.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ED-Veritas
 * Created by Max on 5/24/2017.
 */
public class JParser {

    private File journal;
    private String timestamp;
    private List<String> list;


    public JParser(File file) {
        journal = file;
        processJournal();
    }

    public void update() {
        processJournal();
    }

    private int currentLine = 0;

    private void processJournal() {

        List<String> lines = FileUtil.readLines(journal);
        if (lines != null && lines.size() > 0) {

            if (list != null) {
                if (list.size() == lines.size()) {
                    return;
                }
            }
            list = lines;


            while (currentLine < lines.size()) {

                String line = lines.get(currentLine);

                if (line.contains("\"event\":\"LoadGame\",")) {
                    //loadgame
                } else if (line.contains(", \"event\":\"MaterialCollected\",")) {
                    //matcollected
                } else if (line.contains(", \"event\":\"MaterialDiscarded\",")) {
                    //mat discarded
                } else if (line.contains(" \"event\":\"Materials\",")) {
                    materialDump(line);
                }

                currentLine++;

            }


        }
    }


    /*
    When you log in, the game prints a complete material dump.
    We use that to set the material list as that will always be correct.
    */
    private void materialDump(String line) {
        String[][] dataRaw = LineParser.parseMaterialDump(line, "Raw");
        String[][] dataEncoded = LineParser.parseMaterialDump(line, "Encoded");

        if (dataRaw != null) {


            List<MaterialData> mats = new ArrayList<>();
            for (int i = 0; i < dataRaw[0].length; i++) {
                mats.add(new MaterialData(dataRaw[0][i], Integer.parseInt(dataRaw[1][i]), "Raw"));
            }


            for (int i = 0; i < (dataEncoded != null ? dataEncoded[0].length : 0); i++) {
                mats.add(new MaterialData(dataEncoded[0][i], Integer.parseInt(dataEncoded[1][i]), "Encoded"));
            }
            DataHandler.get().setMaterialDump(mats);
            //Main.controller.controller.displayTable();
        }
    }


    private void updateData() {
        DataStorage.get().updateData(journal.getName(), currentLine);
    }


}
