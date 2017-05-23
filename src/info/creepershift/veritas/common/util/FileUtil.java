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
public final class FileUtil {

    private FileUtil() {
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

    public static void writeLines(File file, List<String> list) {

        try {
            Files.write(Paths.get(file.getAbsolutePath()), list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
