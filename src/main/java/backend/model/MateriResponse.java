package backend.model;

import java.util.List;

// response materi
public class MateriResponse {
    public String status;
    public String message;
    public List<Materi> data;

    public MateriResponse(String status, String message, List<Materi> materi) {
        this.status = status;
        this.message = message;
        this.data = materi;
    }

    // public MateriResponse(String status, String message, Materi materi) {
    //     this.status = status;
    //     this.message = message;
    //     this.data = new ArrayList<>();

    //     if (materi != null) {
    //         this.data.add(materi);
    //     }
    // }
}