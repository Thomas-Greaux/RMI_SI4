package streaming;

import java.io.*;
import java.util.Arrays;

public class Streamer {
    private static final int B_SIZE = 10;
    private long progress = 0;
    private long length = 0;
    private String file_name = "";
    private File file;

    public Streamer(String file_name) {
        this.file_name = file_name;
        file = new File(file_name);
        length = file.length();
    }

    public byte[] next_bytes(){
        if((progress >= length) && (progress > 0)) return new byte[0];
        byte[] res = new byte[B_SIZE];
        InputStream is = null; //TODO optimiser pour ne pas recreer un IS a chaque fois
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error : file not found");
            e.printStackTrace();
            return new byte[0];
        }
        try {
            is.skip(progress);
        } catch (IOException e) {
            System.out.println("Error when skipping");
            e.printStackTrace();
            return new byte[0];
        }
        try {
            progress += is.read(res, 0,  B_SIZE);
        } catch (IOException e) {
            System.out.println("Error when reading");
            e.printStackTrace();
            return new byte[0];
        }

        return res;
    }
}
