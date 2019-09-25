package backend.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

// response batch
public class BatchResponse {
    public String status;
    public String message;
    public ArrayList<Batch> data;

    public BatchResponse(String status, String message, List<Batch> batch) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Batch>();

        for (Batch item : batch) {
            this.data.add(item);
        }
    }

    public BatchResponse(String status, String message, Batch batch) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Batch>();

        if (batch != null) {
            this.data.add(batch);
        }
    }

    public BatchResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

}