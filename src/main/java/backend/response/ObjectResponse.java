package backend.response;

import java.util.List;

import java.util.ArrayList;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public class ObjectResponse {
    public String status;
    public String message;
    public ArrayList<Object> data;

    public ObjectResponse(String status, String message, List<Object> objObject) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Object>();

        for(Object item:objObject) {
            this.data.add(item);
        }
    }

    public ObjectResponse(String status, String message, Object objObject) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<Object>();

        if(objObject != null) {
            this.data.add(objObject);   
        }
    }

    public ObjectResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}