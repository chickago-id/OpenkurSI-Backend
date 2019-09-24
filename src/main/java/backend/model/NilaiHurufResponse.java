package backend.model;

import java.util.ArrayList;
import java.util.List;

public class NilaiHurufResponse {
    public String status;
    public String message;
    public ArrayList<NilaiHuruf> data;

    public NilaiHurufResponse (String status, String message, List <NilaiHuruf> nilaihuruf){
        this.status = status;
        this.message = message;
        this.data = new ArrayList<NilaiHuruf>();

        for (NilaiHuruf item:nilaihuruf){
            this.data.add(item);
        }
    }

    public  NilaiHurufResponse (String status, String message, NilaiHuruf nilaihuruf){
        this.status = status;
        this.message = message;
        this.data= new ArrayList<NilaiHuruf>();

        if (nilaihuruf !=null){
            this.data.add(nilaihuruf);
        }
    }

    public NilaiHurufResponse (String status, String message){
        this.status = status;
        this.message = message;
    }

}
