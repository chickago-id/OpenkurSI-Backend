package backend.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public class AccessLevelResponse {
    public String status;
    public String message;
    public ArrayList<AccessLevel> data;

    public AccessLevelResponse(String status, String message, List<AccessLevel> AccessLevel) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<AccessLevel>();

        for(AccessLevel item:AccessLevel) {
            this.data.add(item);
        }
    }

    public AccessLevelResponse(String status, String message, AccessLevel AccessLevel) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<AccessLevel>();

        if(AccessLevel != null) {
            this.data.add(AccessLevel);   
        }
    }

    public AccessLevelResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}