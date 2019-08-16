package backend.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

// response jadwal
public class Absen_InstrukturResponse {
    public String status;
    public String message;
    public ArrayList<Absen_Instruktur> data;

    public Absen_InstrukturResponse(String status, String message, List<Absen_Instruktur> absen_instruktur) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Absen_Instruktur>();

        for(Absen_Instruktur item:absen_instruktur) {
            this.data.add(item);
        }
    }

    public Absen_InstrukturResponse(String status, String message, Absen_Instruktur absen_instruktur) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<Absen_Instruktur>();

        if(absen_instruktur != null) {
            this.data.add(absen_instruktur);   
        }
    }

    public Absen_InstrukturResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}