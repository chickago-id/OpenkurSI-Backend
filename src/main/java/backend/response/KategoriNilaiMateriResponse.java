package backend.response;

import java.util.List;
import java.util.ArrayList;
import backend.model.KategoriNilaiMateri;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public class KategoriNilaiMateriResponse {
    public String status;
    public String message;
    public ArrayList<KategoriNilaiMateri> data;

    public KategoriNilaiMateriResponse(String status, String message, List<KategoriNilaiMateri> KategoriNilaiMateri) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<KategoriNilaiMateri>();

        for(KategoriNilaiMateri item:KategoriNilaiMateri) {
            this.data.add(item);
        }
    }

    public KategoriNilaiMateriResponse(String status, String message, KategoriNilaiMateri KategoriNilaiMateri) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<KategoriNilaiMateri>();

        if(KategoriNilaiMateri != null) {
            this.data.add(KategoriNilaiMateri);   
        }
    }

    public KategoriNilaiMateriResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}