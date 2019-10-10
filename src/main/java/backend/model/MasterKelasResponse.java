package backend.model;

import java.util.List;
import java.util.ArrayList;

// response materi
public class MasterKelasResponse {
    public String status;
    public String message;
    public ArrayList<MasterKelas> data;

    public MasterKelasResponse(String status, String message, List<MasterKelas> masterKelas) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<MasterKelas>();

        for (MasterKelas item : masterKelas) {
            this.data.add(item);
        }
    }

    public MasterKelasResponse(String status, String message, MasterKelas masterKelas) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<MasterKelas>();

        if (masterKelas != null) {
            this.data.add(masterKelas);
        }
    }

    public MasterKelasResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}