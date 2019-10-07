package backend.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

public class NilaiHurufResponse {
    public String status;
    public String message;
    public ArrayList<NilaiHuruf> data;

    public NilaiHurufResponse (String status, String message, List <NilaiHuruf> nilaiHuruf){
        this.status = status;
        this.message = message;
        this.data = new ArrayList<NilaiHuruf>();

        for (NilaiHuruf item:nilaiHuruf){
            this.data.add(item);
        }
    }

    public  NilaiHurufResponse (String status, String message, NilaiHuruf nilaiHuruf){
        this.status = status;
        this.message = message;
        this.data= new ArrayList<NilaiHuruf>();

        if (nilaiHuruf !=null){
            this.data.add(nilaiHuruf);
        }
    }

    public NilaiHurufResponse (String status, String message){
        this.status = status;
        this.message = message;
    }

}
