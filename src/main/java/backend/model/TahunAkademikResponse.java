package backend.model;

import java.util.List;
import java.util.ArrayList;

// response materi
public class TahunAkademikResponse {
    public String status;
    public String message;
    public ArrayList<TahunAkademik> data;

    public TahunAkademikResponse(String status, String message, List<TahunAkademik> tahunAkademik) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<TahunAkademik>();

        for (TahunAkademik item : tahunAkademik) {
            this.data.add(item);
        }
    }

    public TahunAkademikResponse(String status, String message, TahunAkademik tahunAkademik) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<TahunAkademik>();

        if (tahunAkademik != null) {
            this.data.add(tahunAkademik);
        }
    }

    public TahunAkademikResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}