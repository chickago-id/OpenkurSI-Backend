package backend.response;

import java.util.ArrayList;
import java.util.List;

import backend.model.Status;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

public class StatusResponse {

    public String status;
    public String message;
    public ArrayList<Status> data;

    public StatusResponse(String status, String message, List<Status> statuses){
        this.message = message;
        this.status = status;
        this.data = new ArrayList<Status>();

        for ( Status item:statuses){
            this.data.add(item);
        }
    }

    public StatusResponse (String status, String message, Status statuses) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Status>();

        if (statuses != null) {
            this.data.add(statuses);
        }
    }

    public StatusResponse (String status, String message){
        this.status = status;
        this.message = message;
    }
} 