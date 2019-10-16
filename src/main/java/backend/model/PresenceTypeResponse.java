package backend.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public class PresenceTypeResponse {
    public String status;
    public String message;
    public ArrayList<PresenceType> data;

    public PresenceTypeResponse(String status, String message, List<PresenceType> presenceType) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<PresenceType>();

        for(PresenceType item:presenceType) {
            this.data.add(item);
        }
    }

    public PresenceTypeResponse(String status, String message, PresenceType presenceType) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<PresenceType>();

        if(presenceType != null) {
            this.data.add(presenceType);   
        }
    }

    public PresenceTypeResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}