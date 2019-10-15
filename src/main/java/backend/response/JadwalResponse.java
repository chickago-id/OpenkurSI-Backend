package backend.response;

import java.util.List;

import backend.model.Jadwal;

import java.util.ArrayList;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

public class JadwalResponse {
    public String status;
    public String message;
    public ArrayList<Jadwal> data;

    public JadwalResponse(String status, String message, List<Jadwal> jadwal) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Jadwal>();

        for(Jadwal item:jadwal) {
            this.data.add(item);
        }
    }

    public JadwalResponse(String status, String message, Jadwal jadwal) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<Jadwal>();

        if(jadwal != null) {
            this.data.add(jadwal);   
        }
    }

    public JadwalResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}