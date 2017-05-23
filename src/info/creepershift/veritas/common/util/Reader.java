package info.creepershift.veritas.common.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * ED-Veritas
 * Created by Max on 5/23/2017.
 */
public final class Reader {

    private Reader() {
    }


    public static List<String> readLines(File file) {

        try {
            List<String> list;
            list = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
