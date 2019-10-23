package backend.response;

import java.util.ArrayList;
import java.util.List;

import backend.model.Ruang;


/**
 * Author : supi.core@gmail.com
 */


public class RuangResponse {
    public String status;
    public String message;
    public ArrayList<Ruang> data;

    public RuangResponse (String status, String message, List <Ruang> ruang){
        this.status= status;
        this.message=message;
        this.data= new ArrayList<Ruang>();

        for (Ruang item:ruang){
            this.data.add(item);
        }
    }

    public RuangResponse (String status, String message, Ruang ruang){
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Ruang>();

        if (ruang != null){
            this.data.add(ruang);
        }
    }

    public RuangResponse (String status, String message){
        this.status = status;
        this.message = message;
    }
    
}