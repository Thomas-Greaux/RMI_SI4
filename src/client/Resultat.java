package client;

import java.io.Serializable;

/**
 * Created by DALLA-NORA ENZO in 2017, for Solar Belle Planète.
 * This code is owned by DALLA-NORA ENZO. All rights reserved.
 */
public class Resultat implements Serializable{

    private int param;

    public Resultat(int param){
        this.param = param;
    }

    public int getParam() {
        return param;
    }

    @Override
    public String toString(){
        return "This result contains : " + param;
    }
}
