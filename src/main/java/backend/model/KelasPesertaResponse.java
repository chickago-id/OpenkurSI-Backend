package backend.model;

import java.util.List;
import java.util.ArrayList;

public class KelasPesertaResponse {
    public String status;
    public String message;
    public ArrayList<KelasPeserta> data;

    public KelasPesertaResponse(String status, String message, List<KelasPeserta> kelaspeserta) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<KelasPeserta>();

        for(KelasPeserta item:kelaspeserta) {
            this.data.add(item);
        }
    }

    public KelasPesertaResponse(String status, String message, KelasPeserta kelaspeserta) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<KelasPeserta>();

        if(kelaspeserta != null) {
            this.data.add(kelaspeserta);   
        }
    }

    public KelasPesertaResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}