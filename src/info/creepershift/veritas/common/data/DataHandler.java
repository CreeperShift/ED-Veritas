package info.creepershift.veritas.common.data;

import info.creepershift.veritas.common.storage.MaterialData;

import java.util.List;

/**
 * ED-Veritas
 * Created by Max on 5/24/2017.
 */
public class DataHandler {

    private List<MaterialData> materials;

    private DataHandler() {
    }

    private static DataHandler INSTANCE;

    public static DataHandler get() {

        if (INSTANCE == null) {
            INSTANCE = new DataHandler();
            return INSTANCE;
        }
        return INSTANCE;
    }

    public void setMaterialDump(List<MaterialData> list) {

        materials = list;

    }


}
