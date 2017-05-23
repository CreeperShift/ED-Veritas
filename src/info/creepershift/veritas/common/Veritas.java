package info.creepershift.veritas.common;

import info.creepershift.veritas.common.storage.DataStorage;

import java.io.IOException;

/**
 * ED-Veritas
 * Created by Max on 5/23/2017.
 */
public class Veritas {

    public static void main(String[] args){


        try {
            new DataStorage("test");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
