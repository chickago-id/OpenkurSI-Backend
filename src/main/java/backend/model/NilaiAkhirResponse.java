package backend.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : supi.core@gmail.com - sup1.core@github
 */
public class NilaiAkhirResponse {

    public String status;
    public String message;
    public ArrayList<NilaiAkhir> data;

    public NilaiAkhirResponse(String status, String message, List<NilaiAkhir> nilaiAkhir) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<NilaiAkhir>();

        for(NilaiAkhir item:nilaiAkhir){
            this.data.add(item);
        }
    }

    public NilaiAkhirResponse (String status, String message, NilaiAkhir nilaiAkhir){
        this.status = status;
        this.message = message;
        this.data = new ArrayList<NilaiAkhir>();

        if(nilaiAkhir != null){
            this.data.add(nilaiAkhir);
        }
    }

    public NilaiAkhirResponse (String status, String message){
        this.status = status;
        this.message = message;
    }

}