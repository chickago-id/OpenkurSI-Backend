package backend.model;

import java.util.List;
import java.util.ArrayList;

// response nilai siswa
public class NilaiSiswaResponse {
    public String status;
    public String message;
    public ArrayList<NilaiSiswa> data;

    public NilaiSiswaResponse(String status, String message, List<NilaiSiswa> nilaiSiswa) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<NilaiSiswa>();

        for(NilaiSiswa item:nilaiSiswa) {
            this.data.add(item);
        }
    }

    public NilaiSiswaResponse(String status, String message, NilaiSiswa nilaiSiswa) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<NilaiSiswa>();

        if(nilaiSiswa != null) {
            this.data.add(nilaiSiswa);   
        }
    }

    public NilaiSiswaResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}