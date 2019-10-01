package backend.model;

import java.util.ArrayList;
import java.util.List;

/*
Author : supi.core@gmail.com
*/

public class SesiResponse {
    public String status;
    public String message;
    public ArrayList<Sesi> data;
    
    public SesiResponse (String status, String message, List <Sesi> sesi){
        this.status=status;
        this.message=message;
        this.data = new ArrayList<Sesi>();

        for (Sesi item:sesi){
            this.data.add(item);
        }
    }

    public SesiResponse (String status, String message, Sesi sesi){
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Sesi>();

        if (sesi !=null){
            this.data.add(sesi);
        }
    }

    public SesiResponse (String status, String message){
        this.status = status;
        this.message = message;
    }

}