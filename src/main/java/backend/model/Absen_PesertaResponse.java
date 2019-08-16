package backend.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

// response jadwal
public class Absen_PesertaResponse {
    public String status;
    public String message;
    public ArrayList<Absen_Peserta> data;

    public Absen_PesertaResponse(String status, String message, List<Absen_Peserta> absen_peserta) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Absen_Peserta>();

        for(Absen_Peserta item:absen_peserta) {
            this.data.add(item);
        }
    }

    public Absen_PesertaResponse(String status, String message, Absen_Peserta absen_peserta) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<Absen_Peserta>();

        if(absen_peserta != null) {
            this.data.add(absen_peserta);   
        }
    }

    public Absen_PesertaResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}