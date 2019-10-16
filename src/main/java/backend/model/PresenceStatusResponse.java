package backend.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public class PresenceStatusResponse {
    public String status;
    public String message;
    public ArrayList<PresenceStatus> data;
    public PresenceStatusResponse(String status, String message, List<PresenceStatus> listPS) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<PresenceStatus>();
        for(PresenceStatus item:listPS) {
            this.data.add(item);
        }
    }

    public PresenceStatusResponse(String status, String message, PresenceStatus objPS) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<PresenceStatus>();
        if(objPS != null) {
            this.data.add(objPS);   
        }
    }

    public PresenceStatusResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}