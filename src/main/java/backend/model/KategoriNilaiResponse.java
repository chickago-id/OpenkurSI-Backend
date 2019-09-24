package backend.model;

import java.util.List;
import java.util.ArrayList;

// response kategori nilai
public class KategoriNilaiResponse {
    public String status;
    public String message;
    public ArrayList<KategoriNilai> data;

    public KategoriNilaiResponse(String status, String message, List<KategoriNilai> kategoriNilai) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<KategoriNilai>();

        for(KategoriNilai item:kategoriNilai) {
            this.data.add(item);
        }
    }

    public KategoriNilaiResponse(String status, String message, KategoriNilai kategoriNilai) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<KategoriNilai>();

        if(kategoriNilai != null) {
            this.data.add(kategoriNilai);   
        }
    }

    public KategoriNilaiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}