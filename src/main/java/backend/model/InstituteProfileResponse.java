package backend.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public class InstituteProfileResponse {
    public String status;
    public String message;
    public ArrayList<InstituteProfile> data;

    public InstituteProfileResponse(String status, String message, List<InstituteProfile> objIP) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<InstituteProfile>();
        for(InstituteProfile item:objIP) {
            this.data.add(item);
        }
    }

    public InstituteProfileResponse(String status, String message, InstituteProfile objIP) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<InstituteProfile>();
        if(objIP != null) {
            this.data.add(objIP);   
        }
    }

    public InstituteProfileResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}