package backend.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class KelasResponse {
    public String status;
    public String message;
    public ArrayList<Kelas> data;

    public KelasResponse(String status, String message, List<Kelas> kelas) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Kelas>();

        for(Kelas item:kelas) {
            this.data.add(item);
        }
    }

    public KelasResponse(String status, String message, Kelas kelas) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<Kelas>();

        if(kelas != null) {
            this.data.add(kelas);   
        }
    }

    public KelasResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}