package info.creepershift.veritas.common.parser;

import info.creepershift.veritas.common.Reference;

import java.io.File;

/**
 * ED-Veritas
 * Created by Max on 5/23/2017.
 */
public class ParserThread extends Thread {

    private volatile Thread thread;
    private int sleepTimer = 5000;
    private File currentFile;
    private JParser parser;

    public ParserThread() {
        thread = new Thread(this);
        thread.start();
    }

    public void stopThread() {
        thread = null;
    }


    @Override
    public void run() {

        Thread thisThread = Thread.currentThread();

        while (thread == thisThread) {

            /*
            We do our work in here baby
             */

            File[] journals = new File(Reference.journalDir).listFiles();

            if (journals != null && journals.length > 0) {

                File latestFile = getLatestFile(journals);
                if (!latestFile.equals(currentFile)) {
                    parser = new JParser(latestFile);
                }else{
                    parser.update();
                }
            }
            try {
                sleep(sleepTimer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    private File getLatestFile(File[] files) {

        File latestFile = null;
        String latestString = null;

        for (File file : files) {

            try {
                String[] s = file.getName().split("^Journal.");
                String[] s1 = s[1].split(".01.log$");

                if (latestFile == null) {
                    latestFile = file;
                    latestString = s1[0];
                } else if (Double.parseDouble(s1[0]) > Double.parseDouble(latestString)) {
                    latestFile = file;
                    latestString = s1[0];
                }


            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                //TODO: print error to logger
            }

        }

        return latestFile;
    }


}
